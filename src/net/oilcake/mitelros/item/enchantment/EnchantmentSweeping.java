package net.oilcake.mitelros.item.enchantment;

import net.minecraft.*;

public class EnchantmentSweeping extends Enchantment {

    protected EnchantmentSweeping(int id, yq rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @Override
    public int getNumLevels() {
        return 5;
    }
    @Override
    public String getNameSuffix() {
        return "sweeping";
    }

    @Override
    public boolean canEnchantItem(Item item) {
        return item instanceof ItemScythe;
    }

    @Override
    public boolean isOnCreativeTab(CreativeModeTab creativeModeTab) {
        return creativeModeTab == CreativeModeTab.tabCombat;
    }
}
