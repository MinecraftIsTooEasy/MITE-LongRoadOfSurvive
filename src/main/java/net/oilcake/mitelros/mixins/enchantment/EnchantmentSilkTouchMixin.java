package net.oilcake.mitelros.mixins.enchantment;

import net.minecraft.CreativeTabs;
import net.minecraft.Enchantment;
import net.minecraft.EnchantmentUntouching;
import net.minecraft.EnumRarity;
import net.minecraft.Item;
import net.oilcake.mitelros.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({EnchantmentUntouching.class})
public class EnchantmentSilkTouchMixin extends Enchantment {
   protected EnchantmentSilkTouchMixin(int id, EnumRarity rarity, int difficulty) {
      super(id, rarity, difficulty);
   }

   @Overwrite
   public boolean canApplyTogether(Enchantment par1Enchantment) {
      return super.canApplyTogether(par1Enchantment) && par1Enchantment.effectId != fortune.effectId && par1Enchantment.effectId != Enchantments.enchantmentMelting.effectId && par1Enchantment.effectId != Enchantments.enchantmentAbsorb.effectId;
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
   public boolean isOnCreativeTab(CreativeTabs creativeModeTab) {
      return false;
   }
}
