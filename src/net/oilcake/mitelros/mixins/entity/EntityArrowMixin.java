package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityBoneBodyguard;
import net.oilcake.mitelros.entity.EntityStray;
import net.oilcake.mitelros.entity.EntityWitherBodyguard;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
@Mixin(EntityArrow.class)
public class EntityArrowMixin extends Entity{
    public EntityArrowMixin(World par1World) {
        super(par1World);
    }
    @Shadow
    protected void entityInit() {
    }
    @Shadow
    public Entity shootingEntity;
    @Shadow
    private int ticksInGround;
    @Shadow
    private ItemStack getLauncher() {
        return null;
    }
    @Shadow
    public void readEntityFromNBT(NBTTagCompound nbtTagCompound) {
    }

    @Shadow
    public void writeEntityToNBT(NBTTagCompound nbtTagCompound) {
    }
    @Overwrite
    public void setThrowableHeading(double par1, double par3, double par5, float velocity, float par8) {
        ItemStack launcher = this.getLauncher();


        if (launcher != null && launcher.getItem() == Items.bowTungsten && this.shootingEntity instanceof EntityPlayer) {
            velocity *= 1.35F;
        }

        if (launcher != null && launcher.getItem() == Item.bowMithril && this.shootingEntity instanceof EntityPlayer) {
            velocity *= 1.25F;
        }

        if (launcher != null && launcher.getItem() == Item.bowAncientMetal && this.shootingEntity instanceof EntityPlayer) {
            velocity *= 1.1F;
        }

        float var9 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
        par1 /= var9;
        par3 /= var9;
        par5 /= var9;
        par1 += super.rand.nextGaussian() * (double)(super.rand.nextBoolean() ? -1 : 1) * 1.856746317E-314D * (double)par8;
        par3 += super.rand.nextGaussian() * (double)(super.rand.nextBoolean() ? -1 : 1) * 1.856746317E-314D * (double)par8;
        par5 += super.rand.nextGaussian() * (double)(super.rand.nextBoolean() ? -1 : 1) * 1.856746317E-314D * (double)par8;
        par1 *= velocity;
        par3 *= velocity;
        par5 *= velocity;
        super.motionX = par1;
        super.motionY = par3;
        super.motionZ = par5;
        float var10 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
        super.prevRotationYaw = super.rotationYaw = (float)(Math.atan2(par1, par5) * 0.0D / 6.984873503E-315D);
        super.prevRotationPitch = super.rotationPitch = (float)(Math.atan2(par3, var10) * 0.0D / 6.984873503E-315D);
        this.ticksInGround = 0;
    }
    @Redirect(method = "onUpdate",at = @At(ordinal = 0, value = "INVOKE",target = "Lnet/minecraft/ItemArrow;getDamage()F"))
    public float SPSkeletonExtraDamage(ItemArrow itemArrow) {
        float dummy = 0.0F;
        if (ExperimentalConfig.TagConfig.FinalChallenge.ConfigValue){
            dummy += (float) Constant.CalculateCurrentDiff() / 12.5F;
        }
        if (this.shootingEntity.getClass() == EntityStray.class) {
            dummy += 0.5F;
        }
        if (this.shootingEntity.getClass() == EntityBoneBodyguard.class) {
            dummy += 1.0F;
        }
        if (this.shootingEntity.getClass() == EntityWitherBodyguard.class) {
            dummy += 1.5F;
        }
        return (itemArrow.getDamage() + dummy );
    }
}
