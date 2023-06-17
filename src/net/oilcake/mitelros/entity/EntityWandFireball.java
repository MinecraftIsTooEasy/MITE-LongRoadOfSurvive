package net.oilcake.mitelros.entity;


import net.minecraft.*;

public class EntityWandFireball extends EntityProjectile{
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private int inTile;
    private EntityLiving thrower;
    private String throwerName;
    private int ticksInGround;
    private int ticksInAir;

    public EntityWandFireball (World world, Item item) {
        super(world);
    }
    public EntityWandFireball (World world, EntityLiving thrower) {
        super(world, thrower);
    }
    public EntityWandFireball (World world, double pos_x, double pos_y, double pos_z) {
        super(world, pos_x, pos_y, pos_z);
    }
    @Override
    protected float getGravityVelocity() {
        return 0.0F;
    }

    @Override
    public void onUpdate(){
        super.onUpdate();
        this.worldObj.spawnParticle(EnumParticle.largesmoke, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        this.worldObj.spawnParticle(EnumParticle.flame, this.posX, this.posY, this.posZ , 0.0D, 0.0D, 0.0D);
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
        Raycast raycast;
        raycast = (new Raycast(this.worldObj, current_pos, future_pos)).setForBluntProjectile(this).performVsBlocks();

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
    @Override
    protected void onImpact(RaycastCollision rc) {
        if (!this.worldObj.isRemote)
        {
            if (rc.isEntity() && rc.getEntityHit() instanceof EntityLiving)
            {
                Entity var3 = rc.getEntityHit();
                float damage = 0.0F;

                if (!((var3 instanceof EntityEarthElemental)) && !(var3 instanceof EntityBlaze) && !(var3 instanceof EntityFireElemental) && !(var3 instanceof EntityMagmaCube) && !(var3 instanceof EntityNetherspawn)){
                    damage = 6.0F;
                    var3.setFire(10);
                }
                var3.attackEntityFrom(new Damage(DamageSource.inFire, damage));
                for (int var5 = 0; var5 < 8; ++var5)
                {
                    this.worldObj.spawnParticle(EnumParticle.flame, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
                }
            }else if(rc.isEntity()){
                for (int var5 = 0; var5 < 32; ++var5)
                {
                    this.worldObj.spawnParticle(EnumParticle.flame, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
                }
            }else {
                rc.getBlockHit().onEntityCollidedWithBlock(this.worldObj, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, this);
            }
        }
        for (int var5 = 0; var5 < 32; ++var5)
        {
            this.worldObj.spawnParticle(EnumParticle.flame, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }
        if (this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
    public Item getModelItem()
    {
        return Item.fireballCharge;
    }
}
