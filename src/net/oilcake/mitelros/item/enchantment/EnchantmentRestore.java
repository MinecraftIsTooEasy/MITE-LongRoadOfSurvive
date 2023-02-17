package net.oilcake.mitelros.item.enchantment;

import net.minecraft.*;

import static net.oilcake.mitelros.item.enchantment.Enchantments.enchantmentAbsorb;

public class EnchantmentRestore extends Enchantment {
    protected EnchantmentRestore(int id, yq rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @Override
    public int getNumLevels() {
        return 1;
    }
    @Override
    public boolean canApplyTogether(Enchantment par1Enchantment) {
        return super.canApplyTogether(par1Enchantment) && par1Enchantment.effectId != enchantmentAbsorb.effectId;
    }
    @Override
    public String getNameSuffix() {
        return "restore";
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
