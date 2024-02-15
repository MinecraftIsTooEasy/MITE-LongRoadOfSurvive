package net.oilcake.mitelros.mixins.world;

import net.minecraft.WorldType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldType.class)
public class WorldTypeMixin {
    @Shadow
    private boolean canBeCreated;
    @Inject(method = "writeNBT", at = @At("RETURN"))
    public void injectForceCreate(boolean var1, CallbackInfo callbackInfo) {
        this.canBeCreated = false;
    }
}
