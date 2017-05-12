package com.kiwifisher.mobstacker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kiwifisher.mobstacker.loot.api.ILootEntry;
import com.kiwifisher.mobstacker.loot.api.ILootPool;
import com.kiwifisher.mobstacker.loot.impl.ConditionKilledByPlayer;
import com.kiwifisher.mobstacker.loot.impl.ConditionPropertiesAdult;
import com.kiwifisher.mobstacker.loot.impl.ConditionPropertiesOnFire;
import com.kiwifisher.mobstacker.loot.impl.ConditionSlimeSize;
import com.kiwifisher.mobstacker.loot.impl.Function;
import com.kiwifisher.mobstacker.loot.impl.FunctionFurnaceSmelt;
import com.kiwifisher.mobstacker.loot.impl.FunctionLootingBonus;
import com.kiwifisher.mobstacker.loot.impl.FunctionMatchSheepWool;
import com.kiwifisher.mobstacker.loot.impl.FunctionSetData;
import com.kiwifisher.mobstacker.loot.impl.FunctionSetMeta;
import com.kiwifisher.mobstacker.loot.impl.LootEntry;
import com.kiwifisher.mobstacker.loot.impl.LootPool;
import com.kiwifisher.mobstacker.loot.impl.RandomChance;

import org.bukkit.Material;

/**
 * Generates the default loot.yml file. N.B. Due to an issue with Bukkit's serialization system, the
 * DummyItemMeta currently does not save as ItemMeta and instead saves as
 * com.kiwifisher.mobstacker.DummyItemMeta. This must (for now) be manually fixed.
 * 
 * @author Jikoo
 */
public class GenDefaultLootConfig {

    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File("loot.json")))) {
            writer.write(MobStacker.getGson().toJson(getConfigValues()));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Map<String, Collection<ILootPool>>> getConfigValues() {
        Map<String, Collection<ILootPool>> defaults = new HashMap<>();

        List<ILootPool> pools;
        LootPool pool;
        ConditionSlimeSize conditionSlimeSize;
        List<ILootEntry> entries;
        LootEntry entry;
        Function function;
        FunctionSetData functionSetData;
        RandomChance randomChance;

        pool = new LootPool();
        defaults.put("BAT", Arrays.asList(pool));
        defaults.put("ENDERMITE", Arrays.asList(pool));
        defaults.put("GIANT", Arrays.asList(pool));
        defaults.put("OCELOT", Arrays.asList(pool));
        defaults.put("SILVERFISH", Arrays.asList(pool));
        defaults.put("VILLAGER", Arrays.asList(pool));
        defaults.put("WOLF", Arrays.asList(pool));

        pool = new LootPool();
        entry = new LootEntry();
        entry.setMaterial(Material.BLAZE_ROD);
        entry.setMinimumQuantity(0);
        entry.setConditions(Arrays.asList(new ConditionKilledByPlayer()));
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        defaults.put("BLAZE", Arrays.asList(pool));

        pools = new ArrayList<>();
        pool = new LootPool();
        entry = new LootEntry();
        entry.setMaterial(Material.STRING);
        entry.setMinimumQuantity(0);
        entry.setMaximumQuantity(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        pool = new LootPool();
        pool.setConditions(Arrays.asList(new ConditionKilledByPlayer()));
        entry = new LootEntry();
        entry.setMaterial(Material.SPIDER_EYE);
        entry.setMinimumQuantity(-1);
        entry.setMaximumQuantity(1);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        defaults.put("CAVE_SPIDER", pools);
        defaults.put("SPIDER", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        pool.setConditions(Arrays.asList(new ConditionPropertiesAdult()));
        entry = new LootEntry();
        entry.setMaterial(Material.FEATHER);
        entry.setMinimumQuantity(0);
        entry.setMaximumQuantity(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        pool = new LootPool();
        pool.setConditions(Arrays.asList(new ConditionPropertiesAdult()));
        entry = new LootEntry();
        entry.setMaterial(Material.RAW_CHICKEN);
        function = new FunctionFurnaceSmelt();
        function.setConditions(Arrays.asList(new ConditionPropertiesOnFire()));
        entry.setFunctions(Arrays.asList(function, new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        defaults.put("CHICKEN", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        pool.setConditions(Arrays.asList(new ConditionPropertiesAdult()));
        entry = new LootEntry();
        entry.setMaterial(Material.LEATHER);
        entry.setMinimumQuantity(0);
        entry.setMaximumQuantity(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        pool = new LootPool();
        pool.setConditions(Arrays.asList(new ConditionPropertiesAdult()));
        entry = new LootEntry();
        entry.setMaterial(Material.RAW_BEEF);
        entry.setMaximumQuantity(3);
        function = new FunctionFurnaceSmelt();
        function.setConditions(Arrays.asList(new ConditionPropertiesOnFire()));
        entry.setFunctions(Arrays.asList(function, new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        defaults.put("COW", pools);
        defaults.put("MUSHROOM_COW", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        entry = new LootEntry();
        entry.setMaterial(Material.SULPHUR);
        entry.setMinimumQuantity(0);
        entry.setMaximumQuantity(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        defaults.put("CREEPER", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        pool.setConditions(Arrays.asList(new ConditionPropertiesAdult()));
        entry = new LootEntry();
        entry.setMaterial(Material.LEATHER);
        entry.setMinimumQuantity(0);
        entry.setMaximumQuantity(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        defaults.put("DONKEY", pools);
        defaults.put("HORSE", pools);
        defaults.put("LLAMA", pools);
        defaults.put("MULE", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        entry = new LootEntry();
        entry.setMaterial(Material.PRISMARINE_SHARD);
        entry.setMinimumQuantity(0);
        entry.setMaximumQuantity(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        pool = new LootPool();
        entries = new ArrayList<>();
        entry = new LootEntry();
        entry.setMaterial(Material.RAW_FISH);
        entry.setWeight(3);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        entries.add(entry);
        entry = new LootEntry();
        entry.setMaterial(Material.PRISMARINE_CRYSTALS);
        entry.setWeight(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        entries.add(entry);
        entries.add(new LootEntry());
        pool.setEntries(entries);
        pools.add(pool);
        pool = new LootPool();
        pool.setConditions(Arrays.asList(new ConditionKilledByPlayer()));
        entries = new ArrayList<>();
        entry = new LootEntry();
        entry.setMaterial(Material.RAW_FISH);
        entry.setWeight(60);
        entries.add(entry);
        entry = new LootEntry();
        entry.setMaterial(Material.RAW_FISH);
        entry.setWeight(25);
        functionSetData = new FunctionSetData();
        functionSetData.setMinimum((short) 1);
        entry.setFunctions(Arrays.asList(functionSetData));
        entries.add(entry);
        entry = new LootEntry();
        entry.setMaterial(Material.RAW_FISH);
        entry.setWeight(2);
        functionSetData = new FunctionSetData();
        functionSetData.setMinimum((short) 2);
        entry.setFunctions(Arrays.asList(functionSetData));
        entries.add(entry);
        entry = new LootEntry();
        entry.setMaterial(Material.RAW_FISH);
        entry.setWeight(13);
        functionSetData = new FunctionSetData();
        functionSetData.setMinimum((short) 3);
        entry.setFunctions(Arrays.asList(functionSetData));
        entries.add(entry);
        pool.setEntries(entries);
        pools.add(pool);
        defaults.put("ELDER_GUARDIAN", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        entry = new LootEntry();
        entry.setMaterial(Material.ENDER_PEARL);
        entry.setMinimumQuantity(0);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        defaults.put("ENDERMAN", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        entry = new LootEntry();
        entry.setMaterial(Material.TOTEM);
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        pool = new LootPool();
        pool.setConditions(Arrays.asList(new ConditionKilledByPlayer()));
        entry = new LootEntry();
        entry.setMaterial(Material.EMERALD);
        entry.setMinimumQuantity(0);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        defaults.put("EVOCATION_ILLAGER", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        entry = new LootEntry();
        entry.setMaterial(Material.ENDER_PEARL);
        entry.setMinimumQuantity(0);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        pool = new LootPool();
        entry = new LootEntry();
        entry.setMaterial(Material.SULPHUR);
        entry.setMinimumQuantity(0);
        entry.setMaximumQuantity(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        defaults.put("GHAST", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        entry = new LootEntry();
        entry.setMaterial(Material.PRISMARINE_SHARD);
        entry.setMinimumQuantity(0);
        entry.setMaximumQuantity(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        pool = new LootPool();
        entries = new ArrayList<>();
        entry = new LootEntry();
        entry.setMaterial(Material.RAW_FISH);
        entry.setWeight(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        entries.add(entry);
        entry = new LootEntry();
        entry.setMaterial(Material.PRISMARINE_CRYSTALS);
        entry.setWeight(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        entries.add(entry);
        entries.add(new LootEntry());
        pool.setEntries(entries);
        pools.add(pool);
        pool = new LootPool();
        pool.setConditions(Arrays.asList(new ConditionKilledByPlayer()));
        entries = new ArrayList<>();
        entry = new LootEntry();
        entry.setMaterial(Material.RAW_FISH);
        entry.setWeight(60);
        entries.add(entry);
        entry = new LootEntry();
        entry.setMaterial(Material.RAW_FISH);
        entry.setWeight(25);
        functionSetData = new FunctionSetData();
        functionSetData.setMinimum((short) 1);
        entry.setFunctions(Arrays.asList(functionSetData));
        entries.add(entry);
        entry = new LootEntry();
        entry.setMaterial(Material.RAW_FISH);
        entry.setWeight(2);
        functionSetData = new FunctionSetData();
        functionSetData.setMinimum((short) 2);
        entry.setFunctions(Arrays.asList(functionSetData));
        entries.add(entry);
        entry = new LootEntry();
        entry.setMaterial(Material.RAW_FISH);
        entry.setWeight(13);
        functionSetData = new FunctionSetData();
        functionSetData.setMinimum((short) 3);
        entry.setFunctions(Arrays.asList(functionSetData));
        entries.add(entry);
        pool.setEntries(entries);
        pools.add(pool);
        defaults.put("GUARDIAN", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        entry = new LootEntry();
        entry.setMaterial(Material.ROTTEN_FLESH);
        entry.setMinimumQuantity(0);
        entry.setMaximumQuantity(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        pool = new LootPool();
        randomChance = new RandomChance();
        randomChance.setChance(0.025);
        randomChance.setLootingModifier(0.01);
        pool.setRandomChance(randomChance);
        pool.setConditions(Arrays.asList(new ConditionKilledByPlayer()));
        entries = new ArrayList<>();
        entry = new LootEntry();
        entry.setMaterial(Material.IRON_INGOT);
        entries.add(entry);
        entry = new LootEntry();
        entry.setMaterial(Material.CARROT_ITEM);
        entries.add(entry);
        entry = new LootEntry();
        entry.setMaterial(Material.POTATO_ITEM);
        entries.add(entry);
        pool.setEntries(entries);
        pools.add(pool);
        defaults.put("HUSK", pools);
        defaults.put("ZOMBIE", pools);
        defaults.put("ZOMBIE_VILLAGER", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        entry = new LootEntry();
        entry.setMaterial(Material.RED_ROSE);
        entry.setMinimumQuantity(0);
        entry.setMaximumQuantity(2);
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        pool = new LootPool();
        entry = new LootEntry();
        entry.setMaterial(Material.IRON_INGOT);
        entry.setMinimumQuantity(3);
        entry.setMaximumQuantity(5);
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        defaults.put("IRON_GOLEM", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        entries = new ArrayList<>();
        entry = new LootEntry();
        entry.setMaterial(Material.MAGMA_CREAM);
        entry.setMinimumQuantity(-2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        conditionSlimeSize = new ConditionSlimeSize();
        conditionSlimeSize.setMaximum(0);
        entry.setConditions(Arrays.asList(conditionSlimeSize));
        entries.add(entry);
        entry = new LootEntry();
        entry.setMaterial(Material.MAGMA_CREAM);
        entry.setMinimumQuantity(-2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        conditionSlimeSize = new ConditionSlimeSize();
        conditionSlimeSize.setMinimum(2);
        entry.setConditions(Arrays.asList(conditionSlimeSize));
        entries.add(entry);
        pool.setEntries(entries);
        pools.add(pool);
        defaults.put("MAGMA_CUBE", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        pool.setConditions(Arrays.asList(new ConditionPropertiesAdult()));
        entry = new LootEntry();
        entry.setMaterial(Material.PORK);
        entry.setMaximumQuantity(3);
        function = new FunctionFurnaceSmelt();
        function.setConditions(Arrays.asList(new ConditionPropertiesOnFire()));
        entry.setFunctions(Arrays.asList(function, new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        defaults.put("PIG", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        pool.setConditions(Arrays.asList(new ConditionPropertiesAdult()));
        entries = new ArrayList<>();
        entry = new LootEntry();
        entry.setMaterial(Material.RAW_FISH);
        entry.setWeight(3);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        entries.add(entry);
        entry = new LootEntry();
        entry.setMaterial(Material.RAW_FISH);
        functionSetData = new FunctionSetData();
        functionSetData.setMinimum((short) 1);
        entry.setFunctions(Arrays.asList(functionSetData, new FunctionLootingBonus()));
        entries.add(entry);
        pool.setEntries(entries);
        pools.add(pool);
        defaults.put("POLAR_BEAR", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        pool.setConditions(Arrays.asList(new ConditionPropertiesAdult()));
        entry = new LootEntry();
        entry.setMaterial(Material.RABBIT_HIDE);
        entry.setMinimumQuantity(0);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        pool = new LootPool();
        pool.setConditions(Arrays.asList(new ConditionPropertiesAdult()));
        entry = new LootEntry();
        entry.setMaterial(Material.RABBIT);
        entry.setMinimumQuantity(0);
        function = new FunctionFurnaceSmelt();
        function.setConditions(Arrays.asList(new ConditionPropertiesOnFire()));
        entry.setFunctions(Arrays.asList(function));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        pool = new LootPool();
        pool.setConditions(Arrays.asList(new ConditionPropertiesAdult()));
        randomChance = new RandomChance();
        randomChance.setChance(0.1);
        randomChance.setLootingModifier(0.03);
        pool.setRandomChance(randomChance);
        entry = new LootEntry();
        entry.setMaterial(Material.RABBIT_FOOT);
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        defaults.put("RABBIT", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        pool.setConditions(Arrays.asList(new ConditionPropertiesAdult()));
        entry = new LootEntry();
        entry.setMaterial(Material.WOOL);
        entry.setFunctions(Arrays.asList(new FunctionMatchSheepWool()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        pool = new LootPool();
        pool.setConditions(Arrays.asList(new ConditionPropertiesAdult(), new ConditionKilledByPlayer()));
        entry = new LootEntry();
        entry.setMaterial(Material.MUTTON);
        entry.setMaximumQuantity(2);
        function = new FunctionFurnaceSmelt();
        function.setConditions(Arrays.asList(new ConditionPropertiesOnFire()));
        entry.setFunctions(Arrays.asList(function, new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        defaults.put("SHEEP", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        randomChance = new RandomChance();
        randomChance.setChance(0.5);
        randomChance.setLootingModifier(0.0625);
        pool.setRandomChance(randomChance);
        entry = new LootEntry();
        entry.setMaterial(Material.SHULKER_SHELL);
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        defaults.put("SHULKER", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        entry = new LootEntry();
        entry.setMaterial(Material.ARROW);
        entry.setMinimumQuantity(0);
        entry.setMaximumQuantity(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        pool = new LootPool();
        entry = new LootEntry();
        entry.setMaterial(Material.BONE);
        entry.setMinimumQuantity(0);
        entry.setMaximumQuantity(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        defaults.put("SKELETON", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        pool.setConditions(Arrays.asList(new ConditionPropertiesAdult()));
        entry = new LootEntry();
        entry.setMaterial(Material.BONE);
        entry.setMinimumQuantity(0);
        entry.setMaximumQuantity(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        defaults.put("SKELETON_HORSE", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        entries = new ArrayList<>();
        entry = new LootEntry();
        entry.setMaterial(Material.SLIME_BALL);
        entry.setMinimumQuantity(0);
        entry.setMaximumQuantity(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        conditionSlimeSize = new ConditionSlimeSize();
        conditionSlimeSize.setMaximum(0);
        entry.setConditions(Arrays.asList(conditionSlimeSize));
        entries.add(entry);
        pool.setEntries(entries);
        pools.add(pool);
        defaults.put("SLIME", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        entry = new LootEntry();
        entry.setMaterial(Material.SNOW_BALL);
        entry.setMinimumQuantity(0);
        entry.setMaximumQuantity(15);
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        defaults.put("SNOWMAN", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        entry = new LootEntry();
        entry.setMaterial(Material.INK_SACK);
        entry.setMinimumQuantity(1);
        entry.setMaximumQuantity(3);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        defaults.put("SQUID", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        entry = new LootEntry();
        entry.setMaterial(Material.ARROW);
        entry.setMinimumQuantity(0);
        entry.setMaximumQuantity(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        pool = new LootPool();
        entry = new LootEntry();
        entry.setMaterial(Material.BONE);
        entry.setMinimumQuantity(0);
        entry.setMaximumQuantity(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        pool = new LootPool();
        pool.setConditions(Arrays.asList(new ConditionKilledByPlayer()));
        entry = new LootEntry();
        entry.setMaterial(Material.TIPPED_ARROW);
        entry.setMinimumQuantity(0);
        Map<String, Object> serializedMeta = new HashMap<>();
        serializedMeta.put("==", "ItemMeta");
        serializedMeta.put("meta-type", "POTION");
        serializedMeta.put("potion-type", "minecraft:slowness");
        FunctionSetMeta functionSetMeta = new FunctionSetMeta();
        functionSetMeta.setSerializedMeta(serializedMeta);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus(), functionSetMeta));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        defaults.put("STRAY", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        pool.setRollsMax(3);
        entries = new ArrayList<>();
        entry = new LootEntry();
        entry.setMaterial(Material.GLOWSTONE_DUST);
        entry.setMinimumQuantity(0);
        entry.setMaximumQuantity(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        entries.add(entry);
        entry = new LootEntry();
        entry.setMaterial(Material.SUGAR);
        entry.setMinimumQuantity(0);
        entry.setMaximumQuantity(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        entries.add(entry);
        entry = new LootEntry();
        entry.setMaterial(Material.REDSTONE);
        entry.setMinimumQuantity(0);
        entry.setMaximumQuantity(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        entries.add(entry);
        entry = new LootEntry();
        entry.setMaterial(Material.SPIDER_EYE);
        entry.setMinimumQuantity(0);
        entry.setMaximumQuantity(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        entries.add(entry);
        entry = new LootEntry();
        entry.setMaterial(Material.GLASS_BOTTLE);
        entry.setMinimumQuantity(0);
        entry.setMaximumQuantity(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        entries.add(entry);
        entry = new LootEntry();
        entry.setMaterial(Material.SULPHUR);
        entry.setMinimumQuantity(0);
        entry.setMaximumQuantity(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        entries.add(entry);
        entry = new LootEntry();
        entry.setMaterial(Material.STICK);
        entry.setWeight(2);
        entry.setMinimumQuantity(0);
        entry.setMaximumQuantity(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        entries.add(entry);
        pool.setEntries(entries);
        pools.add(pool);
        defaults.put("WITCH", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        entry = new LootEntry();
        entry.setMaterial(Material.NETHER_STAR);
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        defaults.put("WITHER", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        entry = new LootEntry();
        entry.setMaterial(Material.COAL);
        entry.setMinimumQuantity(-1);
        entry.setMaximumQuantity(1);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        pool = new LootPool();
        entry = new LootEntry();
        entry.setMaterial(Material.BONE);
        entry.setMinimumQuantity(0);
        entry.setMaximumQuantity(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        pool = new LootPool();
        randomChance = new RandomChance();
        randomChance.setChance(0.025);
        randomChance.setLootingModifier(0.01);
        pool.setRandomChance(randomChance);
        pool.setConditions(Arrays.asList(new ConditionKilledByPlayer()));
        entry = new LootEntry();
        entry.setMaterial(Material.SKULL_ITEM);
        functionSetData = new FunctionSetData();
        functionSetData.setMinimum((short) 1);
        entry.setFunctions(Arrays.asList(functionSetData));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        defaults.put("WITHER_SKELETON", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        randomChance = new RandomChance();
        randomChance.setChance(0.15);
        randomChance.setLootingModifier(0.0625);
        pool.setRandomChance(randomChance);
        pool.setConditions(Arrays.asList(new ConditionKilledByPlayer()));
        entry = new LootEntry();
        entry.setMaterial(Material.IRON_AXE);
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        pool = new LootPool();
        pool.setConditions(Arrays.asList(new ConditionKilledByPlayer()));
        entry = new LootEntry();
        entry.setMaterial(Material.EMERALD);
        entry.setMinimumQuantity(0);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        defaults.put("VINDICATION_ILLAGER", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        pool.setConditions(Arrays.asList(new ConditionPropertiesAdult()));
        entry = new LootEntry();
        entry.setMaterial(Material.ROTTEN_FLESH);
        entry.setMinimumQuantity(0);
        entry.setMaximumQuantity(2);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        defaults.put("ZOMBIE_HORSE", pools);

        pools = new ArrayList<>();
        pool = new LootPool();
        entry = new LootEntry();
        entry.setMaterial(Material.ROTTEN_FLESH);
        entry.setMinimumQuantity(0);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        pool = new LootPool();
        entry = new LootEntry();
        entry.setMaterial(Material.GOLD_NUGGET);
        entry.setMinimumQuantity(0);
        entry.setFunctions(Arrays.asList(new FunctionLootingBonus()));
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        pool = new LootPool();
        randomChance = new RandomChance();
        randomChance.setChance(0.025);
        randomChance.setLootingModifier(0.01);
        pool.setRandomChance(randomChance);
        pool.setConditions(Arrays.asList(new ConditionKilledByPlayer()));
        entry = new LootEntry();
        entry.setMaterial(Material.GOLD_INGOT);
        pool.setEntries(Arrays.asList(entry));
        pools.add(pool);
        defaults.put("ZOMBIE_PIGMAN", pools);

        Map<String, Map<String, Collection<ILootPool>>> mappings = new HashMap<>();
        mappings.put("DEFAULT", defaults);

        return mappings;
    }

}