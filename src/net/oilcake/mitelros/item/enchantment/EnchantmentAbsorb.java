package net.oilcake.mitelros.item.enchantment;

import net.minecraft.*;

public class EnchantmentAbsorb extends Enchantment {
    protected EnchantmentAbsorb(int id, yq rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @Override
    public int getNumLevels() {
        return 1;
    }
    @Override
    public boolean canApplyTogether(Enchantment par1Enchantment) {
        return super.canApplyTogether(par1Enchantment) && par1Enchantment.effectId != silkTouch.effectId;
    }

    @Override
    public String getNameSuffix() {
        return "absorb";
    }

    @Override
    public boolean canEnchantItem(Item item) {
        return item instanceof ItemPickaxe;
    }

    @Override
    public boolean isOnCreativeTab(CreativeModeTab creativeModeTab) {
        return creativeModeTab == CreativeModeTab.tabTools;
    }
}
