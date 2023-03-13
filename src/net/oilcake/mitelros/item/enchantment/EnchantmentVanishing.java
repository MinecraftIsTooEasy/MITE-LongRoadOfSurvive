package net.oilcake.mitelros.item.enchantment;

import net.minecraft.*;

import static net.oilcake.mitelros.item.enchantment.Enchantments.enchantmentAbsorb;

public class EnchantmentVanishing extends Enchantment {
    protected EnchantmentVanishing(int id, yq rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @Override
    public int getNumLevels() {
        return 1;
    }
    @Override
    public boolean canApplyTogether(Enchantment par1Enchantment) {
        return super.canApplyTogether(par1Enchantment);
    }
    @Override
    public String getNameSuffix() {
        return "vanishing";
    }

    @Override
    public boolean canEnchantItem(Item item) {
        return true;
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
