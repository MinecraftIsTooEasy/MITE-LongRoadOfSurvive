package net.oilcake.mitelros.mixins.item.enchantment;

import net.minecraft.CreativeModeTab;
import net.minecraft.Enchantment;
import net.minecraft.Item;
import net.minecraft.yq;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import static net.oilcake.mitelros.item.enchantment.Enchantments.enchantmentAbsorb;
import static net.oilcake.mitelros.item.enchantment.Enchantments.enchantmentRestore;

@Mixin(net.minecraft.EnchantmentSilkTouch.class)
public class EnchantmentSilkTouchMixin extends Enchantment {

    protected EnchantmentSilkTouchMixin(int id, yq rarity, int difficulty) {
        super(id, rarity, difficulty);
    }
    @Overwrite
    public boolean canApplyTogether(Enchantment par1Enchantment) {
        return super.canApplyTogether(par1Enchantment) && par1Enchantment.effectId != fortune.effectId  && par1Enchantment.effectId != enchantmentRestore.effectId && par1Enchantment.effectId != enchantmentAbsorb.effectId;
    }
    @Shadow
    public String getNameSuffix() {
        return null;
    }

    @Shadow
    public boolean canEnchantItem(Item item) {
        return false;
    }

    @Shadow
    public boolean isOnCreativeTab(CreativeModeTab creativeModeTab) {
        return false;
    }
}