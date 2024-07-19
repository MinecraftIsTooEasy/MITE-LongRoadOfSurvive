package net.oilcake.mitelros.mixins;

import org.spongepowered.asm.mixin.Mixin;
import net.xiaoyu233.fml.util.EnumExtends;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnumExtends.class)
public class EnumExtendsMixin {

    @Inject(
            method = "buildEnumExtending",
            at = @At("RETURN")
    )
    private static void injectCtor(CallbackInfo callbackInfo) {
        ink.huix.EnumExtends.Companion.getENCHANTMENT_RARITY().build();
    }

}
