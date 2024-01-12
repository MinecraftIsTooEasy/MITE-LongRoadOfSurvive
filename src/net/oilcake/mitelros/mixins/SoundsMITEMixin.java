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
        this.add("records/imported/damnation.ogg");
        this.add("records/imported/connected.ogg");
        this.add("sound/imported/random/totem_use.ogg");
        this.add("sound/imported/meme/brainpower.ogg");
        this.add("sound/imported/mob/spiderking/hit1.ogg");
        this.add("sound/imported/mob/spiderking/hit1.ogg");
        this.add("sound/imported/mob/spiderking/hit1.ogg");
        this.add("sound/imported/mob/spiderking/hit1.ogg");
        this.add("sound/imported/mob/spiderking/death.ogg");
        this.add("sound/imported/mob/spiderking/say1.ogg");
        this.add("sound/imported/mob/spiderking/say2.ogg");
        this.add("sound/imported/mob/spiderking/say3.ogg");
        this.add("sound/imported/random/melting.ogg");
    }
    @Shadow
    private boolean add(String path) {
        return this.sounds.add(path);
    }
}
