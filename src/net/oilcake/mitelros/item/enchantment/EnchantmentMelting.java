package net.oilcake.mitelros.item.enchantment;

import net.minecraft.*;

import static net.oilcake.mitelros.item.enchantment.Enchantments.enchantmentAbsorb;

public class EnchantmentMelting extends Enchantment {
    protected EnchantmentMelting(int id, yq rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @Override
    public int getNumLevels() {
        return 5;
    }
    @Override
    public boolean canApplyTogether(Enchantment par1Enchantment) {
        return super.canApplyTogether(par1Enchantment) && par1Enchantment.effectId != enchantmentAbsorb.effectId && par1Enchantment.effectId != silkTouch.effectId;
    }
    @Override
    public String getNameSuffix() {
        return "melting";
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
