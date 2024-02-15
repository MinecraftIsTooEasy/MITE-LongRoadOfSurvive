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
//    private static final String attackEffect = "-0+1+2+3+5";
//
//    public String getAttackEffect(){
//        return attackEffect;
//    }

    //        spiderEyeEffect = "-0-1+2-3&   4   -4+13";
    //                                    地狱疣
    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void injectClinit(CallbackInfo callbackInfo) {

    //地狱疣+8min
        potionRequirements.put(Integer.valueOf(MobEffectList.resistance.getId()), "0 & 1 & 2 & !3 & 2+6");
        potionRequirements.put(Integer.valueOf(MobEffectList.wither.getId()), "0 & !1 & 2 & 3 & 2+6");
        potionRequirements.put(Integer.valueOf(MobEffectList.regeneration.getId()), "0 & !1 & !2 & !3 & 0+6");
        potionRequirements.put(Integer.valueOf(MobEffectList.moveSpeed.getId()), "!0 & 1 & !2 & !3 & 1+6");
        potionRequirements.put(Integer.valueOf(MobEffectList.fireResistance.getId()), "0 & 1 & !2 & !3 & 0+6");
        potionRequirements.put(Integer.valueOf(MobEffectList.heal.getId()), "0 & !1 & 2 & !3");
        potionRequirements.put(Integer.valueOf(MobEffectList.poison.getId()), "!0 & !1 & 2 & !3 & 2+6");
        potionRequirements.put(Integer.valueOf(MobEffectList.weakness.getId()), "!0 & !1 & !2 & 3 & 3+6");
        potionRequirements.put(Integer.valueOf(MobEffectList.harm.getId()), "!0 & !1 & 2 & 3");
        potionRequirements.put(Integer.valueOf(MobEffectList.moveSlowdown.getId()), "!0 & 1 & !2 & 3 & 3+6");
        potionRequirements.put(Integer.valueOf(MobEffectList.damageBoost.getId()), "0 & !1 & !2 & 3 & 3+6");
        potionRequirements.put(Integer.valueOf(MobEffectList.nightVision.getId()), "!0 & 1 & 2 & !3 & 2+6");
        potionRequirements.put(Integer.valueOf(MobEffectList.invisibility.getId()), "!0 & 1 & 2 & 3 & 2+6");
        blazePowderEffect = "+0-1-2+3&4-4+13";
        sugarEffect = "-0+1-2-3&4-4+13";
        spiderEyeEffect = "-0-1+2-3&4-4+13";
        fermentedSpiderEyeEffect = "-0+3-4+13";
        goldenCarrotEffect = "-0+1+2-3+13&4-4";
        magmaCreamEffect = "+0+1-2-3&4-4+13";
        glowstoneEffect = "+5-6-7";
        speckledMelonEffect = "+0-1+2-3&4-4+13";
        redstoneEffect = "-5+6-7";
        gunpowderEffect = "+14&13-13";
        potionAmplifiers.put(Integer.valueOf(MobEffectList.moveSpeed.getId()), "5");
        potionAmplifiers.put(Integer.valueOf(MobEffectList.digSpeed.getId()), "5");
        potionAmplifiers.put(Integer.valueOf(MobEffectList.damageBoost.getId()), "5");
        potionAmplifiers.put(Integer.valueOf(MobEffectList.regeneration.getId()), "5");
        potionAmplifiers.put(Integer.valueOf(MobEffectList.harm.getId()), "5");
        potionAmplifiers.put(Integer.valueOf(MobEffectList.heal.getId()), "5");
        potionAmplifiers.put(Integer.valueOf(MobEffectList.poison.getId()), "5");
        potionAmplifiers.put(Integer.valueOf(MobEffectList.resistance.getId()), "5");
        potionAmplifiers.put(Integer.valueOf(MobEffectList.wither.getId()), "5");
        field_77925_n = new HashMap();
        potionPrefixes = new String[]{"potion.prefix.mundane", "potion.prefix.uninteresting", "potion.prefix.bland", "potion.prefix.clear", "potion.prefix.milky", "potion.prefix.diffuse", "potion.prefix.artless", "potion.prefix.thin", "potion.prefix.awkward", "potion.prefix.flat", "potion.prefix.bulky", "potion.prefix.bungling", "potion.prefix.buttered", "potion.prefix.smooth", "potion.prefix.suave", "potion.prefix.debonair", "potion.prefix.thick", "potion.prefix.elegant", "potion.prefix.fancy", "potion.prefix.charming", "potion.prefix.dashing", "potion.prefix.refined", "potion.prefix.cordial", "potion.prefix.sparkling", "potion.prefix.potent", "potion.prefix.foul", "potion.prefix.odorless", "potion.prefix.rank", "potion.prefix.harsh", "potion.prefix.acrid", "potion.prefix.gross", "potion.prefix.stinky"};
    }
    @Shadow
    @Final
    @Mutable
    public static String sugarEffect;
    @Shadow
    @Final
    @Mutable
    public static String spiderEyeEffect;
    @Shadow
    @Final
    @Mutable
    public static String fermentedSpiderEyeEffect;
    @Shadow
    @Final
    @Mutable
    public static String speckledMelonEffect;
    @Shadow
    @Final
    @Mutable
    public static String blazePowderEffect;
    @Shadow
    @Final
    @Mutable
    public static String magmaCreamEffect;
    @Shadow
    @Final
    @Mutable
    public static  String redstoneEffect;
    @Shadow
    @Final
    @Mutable
    public static  String glowstoneEffect;
    @Shadow
    @Final
    @Mutable
    public static  String gunpowderEffect;
    @Shadow
    @Final
    @Mutable
    public static String goldenCarrotEffect;
    @Shadow
    @Final
    private static HashMap potionRequirements;
    private static final HashMap potionAmplifiers = new HashMap();
    @Shadow
    @Final
    @Mutable
    private static HashMap field_77925_n;
    @Shadow
    @Final
    @Mutable
    private static String[] potionPrefixes;
}
