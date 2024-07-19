package net.oilcake.mitelros.mixins.item.potion;

import net.minecraft.Potion;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({Potion.class})
public class MobEffectListMixin {
   @Shadow
   @Final
   public int id;
   @Shadow
   private double effectiveness;

   @Overwrite
   public int getEffectInterval(int amplifier) {
      int interval;
      if (this.id == Potion.regeneration.id) {
         interval = 100 >> amplifier;
      } else if (this.id == Potion.poison.id) {
         interval = 100 >> amplifier;
      } else {
         if (this.id != Potion.wither.id) {
            if (this.id == Potion.hunger.id) {
               return 1;
            }

            return -1;
         }

         interval = 40 >> amplifier;
      }

      return interval < 1 ? 1 : interval;
   }
}
