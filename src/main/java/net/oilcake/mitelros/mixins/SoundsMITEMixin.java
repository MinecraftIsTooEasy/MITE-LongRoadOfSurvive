package net.oilcake.mitelros.mixins;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.SoundsMITE;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({SoundsMITE.class})
public class SoundsMITEMixin {
   @Shadow
   private List sounds = new ArrayList();

   @Inject(
      method = {"<init>(Lnet/minecraft/SoundManager;)V"},
      at = {@At("RETURN")}
   )
   public void injectCtor(CallbackInfo callbackInfo) {
      this.add("records/imported/damnation.ogg");
      this.add("records/imported/connected.ogg");
      this.add("sound/imported/random/totem_use.ogg");
      this.add("sound/imported/meme/brainpower.ogg");
      this.add("sound/imported/mob/spiderking/hit1.ogg");
      this.add("sound/imported/mob/spiderking/hit2.ogg");
      this.add("sound/imported/mob/spiderking/hit3.ogg");
      this.add("sound/imported/mob/spiderking/hit4.ogg");
      this.add("sound/imported/mob/spiderking/death.ogg");
      this.add("sound/imported/mob/spiderking/say1.ogg");
      this.add("sound/imported/mob/spiderking/say2.ogg");
      this.add("sound/imported/mob/spiderking/say3.ogg");
      this.add("sound/imported/random/melting.ogg");
      this.add("sound/imported/random/warning.ogg");
      this.add("sound/imported/mob/agarician/hurt1.ogg");
      this.add("sound/imported/mob/agarician/hurt2.ogg");
      this.add("sound/imported/mob/agarician/death1.ogg");
      this.add("sound/imported/mob/agarician/death2.ogg");
      this.add("sound/imported/mob/agarician/death3.ogg");
      this.add("sound/imported/mob/agarician/say.ogg");
   }

   @Shadow
   private boolean add(String path) {
      return this.sounds.add(path);
   }
}
