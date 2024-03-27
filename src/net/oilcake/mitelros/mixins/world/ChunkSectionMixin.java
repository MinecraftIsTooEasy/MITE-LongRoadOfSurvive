package net.oilcake.mitelros.mixins.world;

import net.minecraft.ChunkSection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChunkSection.class)
public class ChunkSectionMixin {
    @Redirect(method = {
            "setExtBlockID",
    }, at = @At(value = "INVOKE", target = "Lnet/minecraft/Debug;setErrorMessage(Ljava/lang/String;)V"))
    public void deletePrintBlockIdMessage(String message) {

    }
}
