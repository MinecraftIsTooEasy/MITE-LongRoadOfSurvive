package net.oilcake.mitelros.item.enchantment;

import net.minecraft.*;

public class EnchantmentCallofNether extends Enchantment {
    protected EnchantmentCallofNether(int id, yq rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @Override
    public int getNumLevels() {
        return 1;
    }
    @Override
    public String getNameSuffix() {
        return "callofnether";
    }

    @Override
    public boolean canEnchantItem(Item item) {
        return item instanceof ItemCuirass;
    }

    @Override
    public boolean isOnCreativeTab(CreativeModeTab creativeModeTab) {
        return creativeModeTab == CreativeModeTab.tabCombat;
    }
}
