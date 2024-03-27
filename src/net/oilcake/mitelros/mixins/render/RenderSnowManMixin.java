package net.oilcake.mitelros.mixins.render;

import net.minecraft.bho;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(bho.class)
public class RenderSnowManMixin {
    @ModifyConstant(method = {
            "a(Lnet/minecraft/EntitySnowman;F)V",
    }, constant = @Constant(intValue = 256))
    private static int ExtendedBlockID(int value) {
        return net.oilcake.mitelros.util.Constant.Extended_Block_ID;
    }
}
