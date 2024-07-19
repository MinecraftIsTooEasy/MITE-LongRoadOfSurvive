package net.oilcake.mitelros.mixins.entity;

import net.minecraft.EntityPlayer;
import net.minecraft.EntityXPOrb;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({EntityXPOrb.class})
public class EntityExperienceOrbMixin {
   @Overwrite
   public static int getXPSplit(int par0) {
      return par0 / 3 > 0 ? par0 / 3 : par0 % 3;
   }

   @Redirect(
      method = {"onUpdate()V"},
      at = @At(
   ordinal = 0,
   value = "INVOKE",
   target = "Lnet/minecraft/EntityPlayer;getEyeHeight()F"
)
   )
   public float modifyOrbAdsorptionCenter(EntityPlayer entityPlayer) {
      return -1.0F;
   }
}
