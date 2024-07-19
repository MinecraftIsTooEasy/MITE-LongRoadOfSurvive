package net.oilcake.mitelros.mixins.enchantment;


import net.minecraft.Enchantment;
import net.oilcake.mitelros.iinjected.EnchantmentII;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Enchantment.class)
public class EnchantmentMixin implements EnchantmentII {

   @Override
   @Unique
   public boolean isReverse() {
      return false;
   }

   @Unique
   @Override
   public boolean isTreasure() {
      return false;
   }
}
