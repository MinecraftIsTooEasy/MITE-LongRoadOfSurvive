package net.oilcake.mitelros.mixins.world;

import net.minecraft.WorldMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(WorldMap.class)
public class WorldMapMixin {
    @ModifyConstant(method = {
            "writeMapColorsToFile"
    }, constant = @Constant(intValue = 256))
    private static int ExtendedBlockID(int value) {
        return net.oilcake.mitelros.util.Constant.Extended_Block_ID;
    }
}
