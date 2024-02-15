package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.item.potion.PotionExtend;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(EntityLiving.class)
public class EntityLivingMixin extends Entity{
    public EntityLivingMixin(World par1World) {
        super(par1World);
    }
    @Overwrite
    private int getArmSwingAnimationEnd() {
        return this.isPotionActive(MobEffectList.digSpeed) ? 6 - (1 + this.getActivePotionEffect(MobEffectList.digSpeed).getAmplifier()) * 1 :
                    this.isPotionActive(MobEffectList.digSlowdown) ? 6 + (1 + this.getActivePotionEffect(MobEffectList.digSlowdown).getAmplifier()) * 2 :
                        this.isPotionActive(PotionExtend.freeze) ? 6 + (1 + this.getActivePotionEffect(PotionExtend.freeze).getAmplifier()) * 4 :
                            6;
    }
    @Shadow
    public boolean isPotionActive(MobEffectList par1Potion) {
        return false;
    }
    @Shadow
    public MobEffect getActivePotionEffect(MobEffectList par1Potion) {
        return null;
    }
    @Overwrite
    public final float getSpeedBoostVsSlowDown() {
        MobEffect slowdown_effect = this.getActivePotionEffect(MobEffectList.moveSlowdown);
        MobEffect haste_effect = this.getActivePotionEffect(MobEffectList.moveSpeed);
        MobEffect freeze_effect = this.getActivePotionEffect(PotionExtend.freeze);
        float slow_amount = slowdown_effect == null ? 0.0F : (float)(slowdown_effect.getAmplifier() + 1) * -0.2F;
        float haste_amount = haste_effect == null ? 0.0F : (float)(haste_effect.getAmplifier() + 1) * 0.2F;
        float freeze_amount = freeze_effect == null? 0.0F : (float)(freeze_effect.getAmplifier() + 1) * -0.24F;
        if (this.isInWeb) {
            slow_amount -= 0.75F;
        }

        double overall_speed_modifier = (double)(slow_amount + haste_amount + freeze_amount);
        if (overall_speed_modifier < 0.0) {
            overall_speed_modifier *= (double)(1.0F - this.getResistanceToParalysis());
        }

        return (float)overall_speed_modifier;
    }
    @Shadow
    public float getResistanceToParalysis() {
        return 0;
    }

//    public final float getMaxHealth() {
//        return entityLiving instanceof EntityPlayer ? ((EntityPlayer)entityLiving).getWaterLimit() : (float)this.getEntityAttribute(GenericAttributes.maxHealth).getAttributeValue();
//    }

//    @Overwrite
//    public void onDeath(DamageSource par1DamageSource) {
//        Entity var2 = par1DamageSource.getResponsibleEntity();
//        EntityLiving var3 = this.func_94060_bK();
//        if (this.scoreValue >= 0 && var3 != null) {
//            var3.addToPlayerScore(dyCast(this), this.scoreValue);
//        }
//
//        if (var2 != null) {
//            var2.onKillEntity(dyCast(this));
//        }
//
//        if (!this.worldObj.isRemote) {
//            int var4 = 0;
//            if (var2 instanceof EntityPlayer) {
//                var4 = EnchantmentManager.getLootingModifier((EntityLiving)var2);
//            }
//
//            if (!this.isChild() && this.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot")) {
//                if (!this.has_taken_massive_fall_damage || this.rand.nextFloat() < 0.1F) {
//                    this.dropFewItems(this.recentlyHit > 0, par1DamageSource);
//                }
//
//
//                this.dropContainedItems();
//                this.dropEquipment(this.recentlyHit > 0, var4);
//            }
//        }
//
//        this.worldObj.setEntityState(this, EnumEntityState.dead);
//    }
    @Shadow
    public EntityLiving func_94060_bK() {return null;}
    @Shadow
    protected int scoreValue;
    @Shadow
    public boolean isChild() {
        return false;
    }
    @Shadow
    public boolean has_taken_massive_fall_damage;
    @Shadow
    protected int recentlyHit;
    @Shadow
    protected void dropFewItems(boolean par1, DamageSource damage_source) {
    }
    @Shadow
    public void dropContainedItems() {}
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
        tryDamageArmor(damage_source, amount, result);
    }
    @Inject(locals = LocalCapture.CAPTURE_FAILHARD,method = "attackEntityFrom",at = @At(value = "INVOKE",shift = At.Shift.AFTER,target = "Lnet/minecraft/EntityLiving;attackEntityFromHelper(Lnet/minecraft/Damage;Lnet/minecraft/EntityDamageResult;)Lnet/minecraft/EntityDamageResult;"))
    private void injectAfterDamageCallback(Damage damage, CallbackInfoReturnable<EntityDamageResult> c, EntityDamageResult result){
        this.checkForAfterDamage(damage, result);
    }
    protected void checkForAfterDamage(Damage damage, EntityDamageResult result) {
    }
}
