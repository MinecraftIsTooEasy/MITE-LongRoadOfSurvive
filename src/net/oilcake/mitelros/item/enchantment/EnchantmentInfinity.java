package net.oilcake.mitelros.item.enchantment;

import net.minecraft.*;

public class EnchantmentInfinity extends Enchantment {
    protected EnchantmentInfinity(int id, yq rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @Override
    public int getNumLevels() {
        return 1;
    }
    @Override
    public boolean canApplyTogether(Enchantment par1Enchantment) {
        return super.canApplyTogether(par1Enchantment) && par1Enchantment.effectId != arrow_recovery.effectId;
    }

    @Override
    public String getNameSuffix() {
        return "infinity";
    }

    @Override
    public boolean canEnchantItem(Item item) {
        return item instanceof ItemBow;
    }

    @Override
    public boolean isOnCreativeTab(CreativeModeTab creativeModeTab) {
        return creativeModeTab == CreativeModeTab.tabCombat;
    }
}
