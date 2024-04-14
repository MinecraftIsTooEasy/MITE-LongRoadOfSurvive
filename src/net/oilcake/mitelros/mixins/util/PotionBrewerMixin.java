package net.oilcake.mitelros.mixins.util;


import net.minecraft.MobEffectList;
import net.minecraft.PotionBrewer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;

@Mixin(PotionBrewer.class)
public class PotionBrewerMixin {
    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void injectClinit(CallbackInfo callbackInfo) {
        potionRequirements.put(Integer.valueOf(MobEffectList.resistance.getId()), "0 & 1 & 2 & !3 & 2+6");
        potionRequirements.put(Integer.valueOf(MobEffectList.wither.getId()), "0 & !1 & 2 & 3 & 2+6");
        potionAmplifiers.put(Integer.valueOf(MobEffectList.wither.getId()), "5");
    }
    @Shadow
    @Final
    private static HashMap potionRequirements;
    @Shadow
    @Final
    private static HashMap potionAmplifiers;

}
