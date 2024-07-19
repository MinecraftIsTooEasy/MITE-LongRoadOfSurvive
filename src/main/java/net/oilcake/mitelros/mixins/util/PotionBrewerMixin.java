package net.oilcake.mitelros.mixins.util;

import java.util.HashMap;
import net.minecraft.Potion;
import net.minecraft.PotionHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({PotionHelper.class})
public class PotionBrewerMixin {
   @Shadow
   @Final
   private static HashMap potionRequirements;
   @Shadow
   @Final
   private static HashMap potionAmplifiers;

   @Inject(
      method = {"<clinit>()V"},
      at = {@At("RETURN")}
   )
   private static void injectClinit(CallbackInfo callbackInfo) {
      potionRequirements.put(Potion.resistance.getId(), "0 & 1 & 2 & !3 & 2+6");
      potionRequirements.put(Potion.wither.getId(), "0 & !1 & 2 & 3 & 2+6");
      potionAmplifiers.put(Potion.wither.getId(), "5");
   }
}
