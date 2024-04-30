package net.oilcake.mitelros.enchantment;

import net.minecraft.CreativeModeTab;
import net.minecraft.Enchantment;
import net.minecraft.Item;
import net.minecraft.yq;
import net.oilcake.mitelros.item.Materials;

import static net.oilcake.mitelros.enchantment.Enchantments.enchantmentVanishing;

public class EnchantmentArrogance extends Enchantment{
    protected EnchantmentArrogance(int id, yq rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @Override
    public int getNumLevels() {
        return 1;
    }
    @Override
    public boolean canApplyTogether(Enchantment par1Enchantment) {
        return super.canApplyTogether(par1Enchantment) && par1Enchantment.effectId != enchantmentVanishing.effectId;
    }
    @Override
    public String getNameSuffix() {
        return "arrogance";
    }

    @Override
    public boolean canEnchantItem(Item item) {
        return item.getHardestMetalMaterial() != Materials.uru;
    }

    @Override
    public boolean isReverse() {
        return true;
    }

    @Override
    public boolean isOnCreativeTab(CreativeModeTab creativeModeTab) {
        return creativeModeTab == CreativeModeTab.tabTools;
    }
}
