package net.oilcake.mitelros.mixins.entity;

import net.minecraft.EntityMob;
import net.minecraft.EntityWight;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({EntityWight.class})
public class EntityWightMixin extends EntityMob {
   public EntityWightMixin(World par1World) {
      super(par1World);
   }

   @Inject(
      method = {"onSpawnWithEgg(Lnet/minecraft/EntityLivingData;)Lnet/minecraft/EntityLivingData;"},
      at = {@At("HEAD")}
   )
   private void injectCanPickUpLoot(CallbackInfoReturnable callbackInfo) {
      this.setCanPickUpLoot(true);
   }
}
