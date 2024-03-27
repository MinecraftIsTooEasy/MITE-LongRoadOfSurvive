package net.oilcake.mitelros.mixins.render;

import net.minecraft.bhv;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(bhv.class)
public class RenderWitchMixin {
    @ModifyConstant(method = {
            "a(Lnet/minecraft/EntityWitch;F)V",
    }, constant = @Constant(intValue = 256))
    private static int injected(int value) {
        return net.oilcake.mitelros.util.Constant.Extended_Block_ID;
    }
}
