//package net.oilcake.mitelros.mixins.item.potion;
//
//import net.minecraft.MobEffectList;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(MobEffectList.class)
//public class MobEffectListMixin {
//    @Shadow
//    private double effectiveness;
//
//    @Inject(method = "<init>(IZI)V",at = @At("RETURN"))
//    private void injectClinit(int par1, boolean par2, int par3, CallbackInfo callbackInfo){
//        if(par1 == MobEffectList.resistance.id){
//            this.effectiveness = 0.5;
//        }
//    }
//}
