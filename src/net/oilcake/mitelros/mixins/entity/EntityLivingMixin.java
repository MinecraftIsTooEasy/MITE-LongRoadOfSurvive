package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static net.xiaoyu233.fml.util.ReflectHelper.dyCast;

@Mixin(EntityLiving.class)
public class EntityLivingMixin extends Entity{
    public EntityLivingMixin(World par1World) {
        super(par1World);
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
