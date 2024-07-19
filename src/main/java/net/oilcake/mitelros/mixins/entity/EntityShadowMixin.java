package net.oilcake.mitelros.mixins.entity;

import net.minecraft.Entity;
import net.minecraft.EntityMob;
import net.minecraft.EntityPlayer;
import net.minecraft.EntityShadow;
import net.minecraft.World;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({EntityShadow.class})
public class EntityShadowMixin extends EntityMob {
   private boolean cursed_player = false;

   public EntityShadowMixin(World par1World) {
      super(par1World);
   }

   @Inject(
      method = {"onSpawnWithEgg(Lnet/minecraft/EntityLivingData;)Lnet/minecraft/EntityLivingData;"},
      at = {@At("HEAD")}
   )
   private void injectCanPickUpLoot(CallbackInfoReturnable callbackInfo) {
      this.setCanPickUpLoot(true);
   }

   @Inject(
      method = {"onLivingUpdate()V"},
      at = {@At("RETURN")}
   )
   private void injectSpecialAttribute(CallbackInfo callbackInfo) {
      if (this.getTarget() != null && !this.cursed_player && (Boolean)StuckTagConfig.TagConfig.TagPseudovision.ConfigValue) {
         Entity target = this.getTarget();
         if (target instanceof EntityPlayer) {
            this.cursed_player = true;
            EntityPlayer var10000 = target.getAsPlayer();
            var10000.vision_dimming += target.getAsEntityLivingBase().getAmountAfterResistance(2.0F, 4);
         }
      }

   }
}
