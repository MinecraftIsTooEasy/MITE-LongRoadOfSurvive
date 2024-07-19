package net.oilcake.mitelros.mixins.entity;

import net.minecraft.Damage;
import net.minecraft.DamageSource;
import net.minecraft.Entity;
import net.minecraft.EntityCubic;
import net.minecraft.EntityDamageResult;
import net.minecraft.EntityInvisibleStalker;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityWight;
import net.minecraft.NBTTagCompound;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.minecraft.World;
import net.oilcake.mitelros.entity.EntityLichShadow;
import net.oilcake.mitelros.item.potion.PotionExtend;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin({EntityLivingBase.class})
public class EntityLivingMixin extends Entity {
   @Shadow
   public int deathTime;
   @Shadow
   protected int scoreValue;
   @Shadow
   public boolean has_taken_massive_fall_damage;
   @Shadow
   protected int recentlyHit;

   public EntityLivingMixin(World par1World) {
      super(par1World);
   }

   @Overwrite
   private int getArmSwingAnimationEnd() {
      return this.isPotionActive(Potion.digSpeed) ? 6 - (1 + this.getActivePotionEffect(Potion.digSpeed).getAmplifier()) * 1 : (this.isPotionActive(Potion.digSlowdown) ? 6 + (1 + this.getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2 : (this.isPotionActive(PotionExtend.freeze) ? 6 + (1 + this.getActivePotionEffect(PotionExtend.freeze).getAmplifier()) * 4 : 6));
   }

   @Shadow
   public boolean isPotionActive(Potion par1Potion) {
      return false;
   }

   @Shadow
   public PotionEffect getActivePotionEffect(Potion par1Potion) {
      return null;
   }

   @Overwrite
   public final float getSpeedBoostVsSlowDown() {
      PotionEffect slowdown_effect = this.getActivePotionEffect(Potion.moveSlowdown);
      PotionEffect haste_effect = this.getActivePotionEffect(Potion.moveSpeed);
      PotionEffect freeze_effect = this.getActivePotionEffect(PotionExtend.freeze);
      PotionEffect stunning_effect = this.getActivePotionEffect(PotionExtend.stunning);
      float slow_amount = slowdown_effect == null ? 0.0F : (float)(slowdown_effect.getAmplifier() + 1) * -0.2F;
      float haste_amount = haste_effect == null ? 0.0F : (float)(haste_effect.getAmplifier() + 1) * 0.2F;
      float freeze_amount = freeze_effect == null ? 0.0F : (float)(freeze_effect.getAmplifier() + 1) * -0.24F;
      float stunning_amount = stunning_effect == null ? 0.0F : (float)(stunning_effect.getAmplifier() + 99) * -0.5F;
      if (this.isInWeb) {
         slow_amount -= 0.75F;
      }

      double overall_speed_modifier = (double)(slow_amount + haste_amount + freeze_amount + stunning_amount);
      if (overall_speed_modifier < 0.0) {
         overall_speed_modifier *= (double)(1.0F - this.getResistanceToParalysis());
      }

      return (float)overall_speed_modifier;
   }

   @Shadow
   public float getResistanceToParalysis() {
      return 0.0F;
   }

   @Inject(
      method = {"onDeathUpdate()V"},
      at = {@At(
   value = "FIELD",
   shift = Shift.AFTER,
   target = "Lnet/minecraft/EntityLivingBase;deathTime:I",
   ordinal = 1
)}
   )
   private void injectTick(CallbackInfo callbackInfo) {
      if (this.getAsEntityLivingBase() instanceof EntityCubic || this.getAsEntityLivingBase() instanceof EntityWight || this.getAsEntityLivingBase() instanceof EntityInvisibleStalker || this.getAsEntityLivingBase() instanceof EntityLichShadow) {
         this.deathTime = 20;
      }

   }

   @Shadow
   public EntityLivingBase func_94060_bK() {
      return null;
   }

   @Shadow
   public boolean isChild() {
      return false;
   }

   @Shadow
   protected void dropFewItems(boolean par1, DamageSource damage_source) {
   }

   @Shadow
   public void dropContainedItems() {
   }

   @Shadow
   protected void dropEquipment(boolean recently_hit_by_player, int par2) {
   }

   @Shadow
   protected void entityInit() {
   }

   @Shadow
   protected void readEntityFromNBT(NBTTagCompound nbtTagCompound) {
   }

   @Shadow
   protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {
   }

   @Shadow
   protected void tryDamageArmor(DamageSource damage_source, float amount, EntityDamageResult result) {
   }

   public void tryDamageArmorC(DamageSource damage_source, float amount, EntityDamageResult result) {
      this.tryDamageArmor(damage_source, amount, result);
   }

   @Inject(
      locals = LocalCapture.CAPTURE_FAILHARD,
      method = {"attackEntityFrom(Lnet/minecraft/Damage;)Lnet/minecraft/EntityDamageResult;"},
      at = {@At(
   value = "INVOKE",
   shift = Shift.AFTER,
   target = "Lnet/minecraft/EntityLivingBase;attackEntityFromHelper(Lnet/minecraft/Damage;Lnet/minecraft/EntityDamageResult;)Lnet/minecraft/EntityDamageResult;"
)}
   )
   private void injectAfterDamageCallback(Damage damage, CallbackInfoReturnable c, EntityDamageResult result) {
      this.checkForAfterDamage(damage, result);
   }

   protected void checkForAfterDamage(Damage damage, EntityDamageResult result) {
   }
}
