package net.oilcake.mitelros.item.enchantment;

import net.minecraft.*;

public class EnchantmentDestroying extends Enchantment {
    protected EnchantmentDestroying(int id, yq rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @Override
    public int getNumLevels() {
        return 3;
    }
    @Override
    public String getNameSuffix() {
        return "destroying";
    }

    @Override
    public boolean canEnchantItem(Item item) {
        return item instanceof ItemWarHammer;
    }

    @Override
    public boolean isOnCreativeTab(CreativeModeTab creativeModeTab) {
        return creativeModeTab == CreativeModeTab.tabCombat;
    }
}
