package net.oilcake.mitelros.mixins.entity;

import net.minecraft.Entity;
import net.minecraft.EntityArrow;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemArrow;
import net.minecraft.ItemStack;
import net.minecraft.MathHelper;
import net.minecraft.World;
import net.oilcake.mitelros.entity.EntityBoneBodyguard;
import net.oilcake.mitelros.entity.EntityStray;
import net.oilcake.mitelros.entity.EntityWitherBodyguard;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;

@Mixin({EntityArrow.class})
public abstract class EntityArrowMixin extends Entity {
   @Shadow
   public Entity shootingEntity;
   @Shadow
   private int ticksInGround;

   public EntityArrowMixin(World par1World) {
      super(par1World);
   }

   @Shadow
   private ItemStack getLauncher() {
      return null;
   }

   @ModifyVariable(
      method = {"setThrowableHeading(DDDFF)V"},
      at = @At(
   ordinal = 0,
   value = "INVOKE",
   shift = Shift.AFTER,
   target = "Lnet/minecraft/EntityArrow;getLauncher()Lnet/minecraft/ItemStack;"
),
      ordinal = 0
   )
   private float extendVelocity(float par00000, double par1, double par3, double par5, float velocity, float par8) {
      ItemStack launcher = this.getLauncher();
      if (launcher != null && launcher.getItem() == Items.bowTungsten && this.shootingEntity instanceof EntityPlayer) {
         velocity *= 1.35F;
      }

      return velocity;
   }

   @ModifyVariable(
      method = {"setThrowableHeading(DDDFF)V"},
      at = @At("HEAD"),
      ordinal = 1
   )
   private float motionImpactWander(float par00000, double par1, double par3, double par5, float velocity, float par8) {
      double motionX = this.shootingEntity.motionX;
      double motionY = this.shootingEntity.motionY;
      double motionZ = this.shootingEntity.motionZ;
      boolean onGround = this.shootingEntity.onGround;
      double var9 = (double)MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
      var9 *= onGround ? 2.5 : 6.0;
      return (float)((double)par8 * var9);
   }

   @Redirect(
      method = {"onUpdate()V"},
      at = @At(
   ordinal = 0,
   value = "INVOKE",
   target = "Lnet/minecraft/ItemArrow;getDamage()F"
)
   )
   public float SPSkeletonExtraDamage(ItemArrow itemArrow) {
      float dummy = 0.0F;
      if ((Boolean)ExperimentalConfig.TagConfig.FinalChallenge.ConfigValue) {
         dummy += (float)Constant.CalculateCurrentDiff() / 12.5F;
      }

      if (this.shootingEntity.getClass() == EntityStray.class) {
         dummy += 0.5F;
      }

      if (this.shootingEntity.getClass() == EntityBoneBodyguard.class) {
         ++dummy;
      }

      if (this.shootingEntity.getClass() == EntityWitherBodyguard.class) {
         ++dummy;
      }

      return itemArrow.getDamage() + dummy;
   }
}
