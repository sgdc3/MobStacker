package com.kiwifisher.mobstacker.listeners;

import com.kiwifisher.mobstacker.MobStacker;
import com.kiwifisher.mobstacker.utils.StackUtils;

import org.bukkit.Material;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerInteractEntityListener implements Listener {

    private final MobStacker plugin;

    public PlayerInteractEntityListener(MobStacker plugin) {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {

        ItemStack hand;
        if (event.getHand() == EquipmentSlot.HAND) {
            hand = event.getPlayer().getInventory().getItemInMainHand();
        } else {
            hand = event.getPlayer().getInventory().getItemInOffHand();
        }

        // Handle naming of entities.
        if (hand.getType() == Material.NAME_TAG) {
            handleNameTag(event, hand);
            return;
        }

        Entity entity = event.getRightClicked();

        // Ensure entity is a stacked animal.
        if (!(entity instanceof Animals) || StackUtils.getStackSize(entity) < 2) {
            return;
        }

        // Ensure player owns the animal if it is tameable
        if (entity instanceof Tameable && !event.getPlayer().equals(((Tameable) entity).getOwner())) {
            return;
        }

        // Ensure entity is ready to breed and item in hand is food.
        if (!plugin.getStackUtils().canBreed(entity) || !isBreedingMaterialFor(entity.getType(), hand.getType())) {
            return;
        }

        // Peel off the remainder of the stack and set entity bred.
        plugin.getStackUtils().peelOffStack(entity);
        plugin.getStackUtils().setBred(entity);

        // Attempt to re-stack once after the initial breed timer (15 seconds, 300 ticks).
        new BukkitRunnable() {
            @Override
            public void run() {
                plugin.getStackUtils().attemptToStack(entity, 1);
            }
        }.runTaskLater(plugin, 301L);

    }

    /**
     * Handles name tag usage.
     * 
     * @param event the PlayerInteractEntityEvent
     * @param hand the ItemStack in the Player's hand
     */
    private void handleNameTag(PlayerInteractEntityEvent event, ItemStack hand) {
        // Ensure tag has meta.
        if (!hand.hasItemMeta()) {
            return;
        }

        ItemMeta meta = hand.getItemMeta();

        // Ensure tag has a name set.
        if (!meta.hasDisplayName()) {
            return;
        }

        // Prevent renaming using tags matching the naming pattern if stacks will load.
        if (plugin.getConfig().getBoolean("load-existing-stacks.enabled")
                && plugin.getStackUtils().matchesStackName(meta.getDisplayName())) {
            event.setCancelled(true);
            return;
        }
 
        // Only handle if custom named mobs do not stack.
        if (plugin.getConfig().getBoolean("stack-custom-named-mobs")) {
            return;
        }

        Entity renamed = event.getRightClicked();

        // Ensure the entity is a stack.
        if (StackUtils.getStackSize(renamed) < 2) {
            return;
        }

        // Peel off the rest of the stack.
        plugin.getStackUtils().peelOffStack(renamed);

    }

    /**
     * Gets whether an entity can be bred using a material.
     * 
     * @param entity the EntityType
     * @param material the Material
     * @return true if the Material can be used to breed the EntityType
     */
    private boolean isBreedingMaterialFor(EntityType entity, Material material) {
        if (entity == EntityType.HORSE) {
            return material == Material.GOLDEN_APPLE || material == Material.GOLDEN_CARROT;
        }
        if (entity == EntityType.SHEEP || entity == EntityType.COW
                || entity == EntityType.MUSHROOM_COW) {
            return material == Material.WHEAT;
        }
        if (entity == EntityType.PIG) {
            return material == Material.CARROT_ITEM || material == Material.POTATO_ITEM
                    || material == Material.BEETROOT;
        }
        if (entity == EntityType.CHICKEN) {
            return material.name().endsWith("SEEDS");
        }
        if (entity == EntityType.WOLF) {
            return material == Material.COOKED_BEEF || material == Material.COOKED_CHICKEN
                    || material == Material.COOKED_MUTTON || material == Material.COOKED_RABBIT
                    || material == Material.GRILLED_PORK || material == Material.RAW_BEEF
                    || material == Material.RAW_CHICKEN || material == Material.RABBIT
                    || material == Material.PORK || material == Material.ROTTEN_FLESH;
        }
        if (entity == EntityType.OCELOT) {
            return material == Material.RAW_FISH;
        }
        if (entity == EntityType.RABBIT) {
            return material == Material.CARROT_ITEM || material == Material.GOLDEN_CARROT
                    || material == Material.YELLOW_FLOWER;
        }

        // Yet-unknown entity, or cannot be bred (e.g. polar bears).
        return false;
    }

}
