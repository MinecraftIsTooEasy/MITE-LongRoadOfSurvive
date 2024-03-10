package net.oilcake.mitelros.enchantment;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Unique;

public class EnchantmentMending extends Enchantment{
    protected EnchantmentMending(int id, yq rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @Override
    public int getNumLevels() {
        return 1;
    }
    @Override
    public boolean canApplyTogether(Enchantment par1Enchantment) {
        return super.canApplyTogether(par1Enchantment) && par1Enchantment.effectId != unbreaking.effectId;
    }

    @Override
    public String getNameSuffix() {
        return "mending";
    }

    @Override
    public boolean canEnchantItem(Item item) {
        return item instanceof ItemTool || item instanceof ItemArmor;
    }

    @Override
    public boolean isOnCreativeTab(CreativeModeTab creativeModeTab) {
        return creativeModeTab == CreativeModeTab.tabTools;
    }
    @Override
    public boolean isTreasure(){
        return true;
    }
}
