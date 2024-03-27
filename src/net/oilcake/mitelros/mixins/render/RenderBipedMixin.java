package net.oilcake.mitelros.mixins.render;

import net.minecraft.bgu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(bgu.class)
public class RenderBipedMixin {
    @ModifyConstant(method = {
            "a(Lnet/minecraft/EntityInsentient;F)V",
    }, constant = @Constant(intValue = 256))
    private static int ExtendedBlockID(int value) {
        return net.oilcake.mitelros.util.Constant.Extended_Block_ID;
    }
}
