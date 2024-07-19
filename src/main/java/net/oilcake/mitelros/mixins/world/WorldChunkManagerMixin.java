package net.oilcake.mitelros.mixins.world;

import java.util.List;
import net.minecraft.WorldChunkManager;
import net.oilcake.mitelros.world.BiomeBases;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({WorldChunkManager.class})
public class WorldChunkManagerMixin {
   @Shadow
   private List biomesToSpawnIn;

   @Inject(
      method = {"<init>()V"},
      at = {@At("RETURN")}
   )
   private void inject(CallbackInfo callbackInfo) {
      this.biomesToSpawnIn.add(BiomeBases.windsweptpleatu);
   }
}
