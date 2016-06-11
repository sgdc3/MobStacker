package com.kiwifisher.mobstacker.algorithms;

import org.bukkit.Material;

/**
 * Loot for rare drops. Rare drops have a chance of 2.5% + 1% per level looting.
 * 
 * @author Jikoo
 */
public class RareLoot extends Loot {

    public RareLoot(Material material) {
        super(material, 1, 0.025, false);
    }

    public RareLoot(Material material, short data) {
        super(material, data, 1, 0.025, false);
    }

    @Override
    public double getDropChance(int looting) {
        return Math.min(1, super.getDropChance(looting) + 0.01 * looting);
    }

}
