package net.oilcake.mitelros.mixins.enchantment;

import net.minecraft.CreativeTabs;
import net.minecraft.Enchantment;
import net.minecraft.EnchantmentArrowRecovery;
import net.minecraft.EnumRarity;
import net.minecraft.Item;
import net.oilcake.mitelros.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({EnchantmentArrowRecovery.class})
public class EnchantmentArrowRecoveryMixin extends Enchantment {
   protected EnchantmentArrowRecoveryMixin(int id, EnumRarity rarity, int difficulty) {
      super(id, rarity, difficulty);
   }

   public boolean canApplyTogether(Enchantment par1Enchantment) {
      return super.canApplyTogether(par1Enchantment) && par1Enchantment.effectId != Enchantments.enchantmentInfinity.effectId;
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
