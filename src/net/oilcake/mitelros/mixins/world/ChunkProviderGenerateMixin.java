package net.oilcake.mitelros.mixins.world;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Random;

@Mixin(ChunkProviderGenerate.class)
public abstract class ChunkProviderGenerateMixin implements IChunkProvider{
    @ModifyConstant(method = {
            "initializeNoiseField"
    }, constant = @Constant(doubleValue = 684.412, ordinal = 0))
    private static double ExtendedBlockID(double value) {
        return 513.309;
    }
}
