package net.oilcake.mitelros.mixins.item.potion;

import net.minecraft.MobEffectList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.MobEffectList.*;

@Mixin(MobEffectList.class)
public class MobEffectListMixin {
    @Shadow
    @Final
    public int id;
    @Shadow
    private double effectiveness;

//    @Inject(method = "<init>(IZI)V",at = @At("RETURN"))
//    private void injectClinit(int par1, boolean par2, int par3, CallbackInfo callbackInfo){
//        if(par1 == MobEffectList.resistance.id){
//            this.effectiveness = 0.5;
//        }
//    }
    @Overwrite
    public int getEffectInterval(int amplifier) {
        int interval;
        if (this.id == regeneration.id) {
            interval = 100 >> amplifier;
        } else if (this.id == poison.id) {
            interval = 100 >> amplifier;
        } else {
            if (this.id != wither.id) {
                if (this.id == hunger.id) {
                    return 1;
                }

                return -1;
            }

            interval = 40 >> amplifier;
        }

        return interval < 1 ? 1 : interval;
    }
}
