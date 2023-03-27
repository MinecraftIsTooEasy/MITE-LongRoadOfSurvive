package net.oilcake.mitelros.mixins;

import net.minecraft.SoundsMITE;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(SoundsMITE.class)
public class SoundsMITEMixin {
    @Shadow
    private List<String> sounds = new ArrayList();
    @Inject(method = "<init>",at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        this.add("records/imported/division.ogg");
    }
    @Shadow
    private boolean add(String path) {
        return this.sounds.add(path);
    }
}
