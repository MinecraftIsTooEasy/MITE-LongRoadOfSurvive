package net.oilcake.mitelros.mixins.render;

import net.minecraft.RenderWitch;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin({RenderWitch.class})
public class RenderWitchMixin {
   @ModifyConstant(
      method = {"func_82411_a(Lnet/minecraft/EntityWitch;F)V"},
      constant = {@Constant(
   intValue = 256
)}
   )
   private static int injected(int value) {
      return 512;
   }
}
