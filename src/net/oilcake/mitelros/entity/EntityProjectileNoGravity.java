package net.oilcake.mitelros.entity;

import net.minecraft.*;

public abstract class EntityProjectileNoGravity extends Entity implements un {
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private int inTile;
    protected boolean inGround;
    public int throwableShake;
    private EntityLiving thrower;
    private String throwerName;
    private int ticksInGround;
    private int ticksInAir;

    public EntityProjectileNoGravity(World par1World) {
        super(par1World);
        this.setSize(0.25F, 0.25F);
    }

    protected void entityInit() {
    }

    public boolean a(double par1) {
        double var3 = this.boundingBox.getAverageEdgeLength() * 4.0;
        var3 *= 64.0;
        return par1 < var3 * var3;
    }

    public EntityProjectileNoGravity(World par1World, EntityLiving par2EntityLivingBase) {
        super(par1World);
        this.thrower = par2EntityLivingBase;
        this.setSize(0.25F, 0.25F);
        this.setLocationAndAngles(par2EntityLivingBase.posX, par2EntityLivingBase.posY + (double)par2EntityLivingBase.getEyeHeight(), par2EntityLivingBase.posZ, par2EntityLivingBase.rotationYaw, par2EntityLivingBase.rotationPitch);
        this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
        this.posY -= 0.10000000149011612;
        this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;
        float var3 = 0.4F;
        this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F) * var3);
        this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F) * var3);
        this.motionY = (double)(-MathHelper.sin((this.rotationPitch + this.func_70183_g()) / 180.0F * 3.1415927F) * var3);
        float wander;
        if (par2EntityLivingBase instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)par2EntityLivingBase;
            int level = player.getExperienceLevel();
            if (level < 0) {
                wander = 5.0F + (float)level * -0.5F;
            } else {
                wander = (float)(0.5 + 4.5 / Math.pow((double)(0.8F + (float)(level + 1) / 5.0F), 2.0));
            }
        } else {
            wander = 1.0F;
        }

        if (par2EntityLivingBase.isSuspendedInLiquid()) {
            wander *= 2.0F;
        }

        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, this.func_70182_d(), wander);
    }

    public EntityProjectileNoGravity(World par1World, double par2, double par4, double par6) {
        super(par1World);
        this.ticksInGround = 0;
        this.setSize(0.25F, 0.25F);
        this.setPosition(par2, par4, par6);
        this.yOffset = 0.0F;
    }

    protected float func_70182_d() {
//        if (!(this instanceof EntityBrick) && !(this instanceof EntityGelatinousSphere)) {
//            return this instanceof EntityWeb ? 0.8F : 1.5F;
//        } else {
//            return 1.2F;
//        }
        return 1.25F;
    }

    protected float func_70183_g() {
        return 0.0F;
    }

    public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8) {
        float var9 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
        par1 /= (double)var9;
        par3 /= (double)var9;
        par5 /= (double)var9;
        par1 += this.rand.nextGaussian() * 0.007499999832361937 * (double)par8;
        par3 += this.rand.nextGaussian() * 0.007499999832361937 * (double)par8;
        par5 += this.rand.nextGaussian() * 0.007499999832361937 * (double)par8;
        par1 *= (double)par7;
        par3 *= (double)par7;
        par5 *= (double)par7;
        this.motionX = par1;
        this.motionY = par3;
        this.motionZ = par5;
        float var10 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(par1, par5) * 180.0 / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(par3, (double)var10) * 180.0 / Math.PI);
        this.ticksInGround = 0;
    }

    public void h(double par1, double par3, double par5) {
        this.motionX = par1;
        this.motionY = par3;
        this.motionZ = par5;
        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float var7 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(par1, par5) * 180.0 / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(par3, (double)var7) * 180.0 / Math.PI);
        }

    }

    public boolean cannotRaycastCollideWith(Entity entity) {
        return entity == this.getThrower() && this.ticksInAir < 5 ? true : super.cannotRaycastCollideWith(entity);
    }

    public void onUpdate() {
        this.lastTickPosX = this.posX;
        this.lastTickPosY = this.posY;
        this.lastTickPosZ = this.posZ;
        super.onUpdate();
        if (this.throwableShake > 0) {
            --this.throwableShake;
        }

        if (this.inGround) {
            int var1 = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
            if (var1 == this.inTile) {
                ++this.ticksInGround;
                if (this.ticksInGround == 1200) {
                    this.setDead();
                }

                return;
            }

            this.inGround = false;
            this.motionX *= (double)(this.rand.nextFloat() * 0.2F);
            this.motionY *= (double)(this.rand.nextFloat() * 0.2F);
            this.motionZ *= (double)(this.rand.nextFloat() * 0.2F);
            this.ticksInGround = 0;
            this.ticksInAir = 0;
        } else {
            ++this.ticksInAir;
        }

        Vec3D current_pos = this.worldObj.getVec3(this.posX, this.posY, this.posZ);
        Vec3D future_pos = this.worldObj.getVec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        Raycast raycast = (new Raycast(this.worldObj, current_pos, future_pos)).setForBluntProjectile(this).performVsBlocks();

        RaycastCollision var3 = raycast.getBlockCollision();
        if (var3 != null) {
            raycast.setLimitToBlockCollisionPoint();
        }

        if (this.onServer() && raycast.performVsEntities().hasEntityCollisions()) {
            var3 = raycast.getNearestCollision();
        }

        if (var3 != null) {
            if (var3.isBlock() && var3.getBlockHit() == Block.portal) {
                this.setInPortal(Block.portal.getDestinationDimensionID(this.worldObj, var3.block_hit_x, var3.block_hit_y, var3.block_hit_z));
            } else {
                this.onImpact(var3);
            }
        }

        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
        float var17 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0 / Math.PI);

        for(this.rotationPitch = (float)(Math.atan2(this.motionY, (double)var17) * 180.0 / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
        }

        while(this.rotationPitch - this.prevRotationPitch >= 180.0F) {
            this.prevRotationPitch += 360.0F;
        }

        while(this.rotationYaw - this.prevRotationYaw < -180.0F) {
            this.prevRotationYaw -= 360.0F;
        }

        while(this.rotationYaw - this.prevRotationYaw >= 180.0F) {
            this.prevRotationYaw += 360.0F;
        }

        this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
        this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
        float var18 = 0.99F;
        float var19 = this.getGravityVelocity();
        if (this.isInWater()) {
            for(int var7 = 0; var7 < 4; ++var7) {
                float var20 = 0.25F;
                this.worldObj.spawnParticle(EnumParticle.bubble, this.posX - this.motionX * (double)var20, this.posY - this.motionY * (double)var20, this.posZ - this.motionZ * (double)var20, this.motionX, this.motionY, this.motionZ);
            }

            var18 = 0.8F;
        }

        this.motionX *= (double)var18;
        this.motionY *= (double)var18;
        this.motionZ *= (double)var18;
        this.motionY -= (double)var19;
        this.setPosition(this.posX, this.posY, this.posZ);
    }

    protected float getGravityVelocity() {
        return 0.00F;
    }

    protected abstract void onImpact(RaycastCollision var1);

    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setShort("xTile", (short)this.xTile);
        par1NBTTagCompound.setShort("yTile", (short)this.yTile);
        par1NBTTagCompound.setShort("zTile", (short)this.zTile);
        par1NBTTagCompound.setByte("inTile", (byte)this.inTile);
        par1NBTTagCompound.setByte("shake", (byte)this.throwableShake);
        par1NBTTagCompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
        if ((this.throwerName == null || this.throwerName.length() == 0) && this.thrower != null && this.thrower instanceof EntityPlayer) {
            this.throwerName = this.thrower.getEntityName();
        }

        par1NBTTagCompound.setString("ownerName", this.throwerName == null ? "" : this.throwerName);
    }

    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        this.xTile = par1NBTTagCompound.getShort("xTile");
        this.yTile = par1NBTTagCompound.getShort("yTile");
        this.zTile = par1NBTTagCompound.getShort("zTile");
        this.inTile = par1NBTTagCompound.getByte("inTile") & 255;
        this.throwableShake = par1NBTTagCompound.getByte("shake") & 255;
        this.inGround = par1NBTTagCompound.getByte("inGround") == 1;
        this.throwerName = par1NBTTagCompound.getString("ownerName");
        if (this.throwerName != null && this.throwerName.length() == 0) {
            this.throwerName = null;
        }

    }

    public float S() {
        return 0.0F;
    }

    public EntityLiving getThrower() {
        if (this.thrower == null && this.throwerName != null && this.throwerName.length() > 0) {
            this.thrower = this.worldObj.getPlayerEntityByName(this.throwerName);
        }

        return this.thrower;
    }

    public boolean isMagical() {
        return false;
    }

    public final ItemStack getItemStack() {
        return new ItemStack(this.getModelItem());
    }
}
