package net.oilcake.mitelros.entity;


import net.minecraft.*;

public class EntityWandFireball extends EntityProjectile{
    private Item item;

    public EntityWandFireball (World world, Item item) {
        super(world);
        this.item = item;
    }
    public EntityWandFireball (World world, EntityLiving thrower) {
        super(world, thrower);
        this.item = item;
    }

    public EntityWandFireball (World world, EntityLiving thrower, Item item) {
        super(world, thrower);
        this.item = item;
    }

    public EntityWandFireball (World world, double pos_x, double pos_y, double pos_z, Item item) {
        super(world, pos_x, pos_y, pos_z);
        this.item = item;
    }
    @Override
    protected float getGravityVelocity() {
        return 0.0F;
    }
    public void onUpdate(){
        super.onUpdate();
        this.worldObj.spawnParticle(EnumParticle.largesmoke, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        this.worldObj.spawnParticle(EnumParticle.flame, this.posX, this.posY, this.posZ , 0.0D, 0.0D, 0.0D);
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
