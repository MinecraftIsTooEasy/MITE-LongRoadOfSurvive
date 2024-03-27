package net.oilcake.mitelros.mixins.util;

import net.minecraft.bdo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(bdo.class)
public class BlockBreakingFXMixin {
    @ModifyConstant(method = {
            "<init>(Lnet/minecraft/World;DDDLnet/minecraft/Item;I)V"
    }, constant = @Constant(intValue = 256))
    private static int ExtendedBlockID(int value) {
        return net.oilcake.mitelros.util.Constant.Extended_Block_ID;
    }
}
