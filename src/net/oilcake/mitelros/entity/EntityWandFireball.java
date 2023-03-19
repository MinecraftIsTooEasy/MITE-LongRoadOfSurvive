package net.oilcake.mitelros.entity;


import net.minecraft.*;

public class EntityWandFireball extends EntityProjectileNoGravity{

    public EntityWandFireball(World par1World) {
        super(par1World);
    }
    public EntityWandFireball(World world, EntityLiving thrower) {
        super(world, thrower);
    }
    public void onUpdate(){
        super.onUpdate();
        this.worldObj.spawnParticle(EnumParticle.largesmoke, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
        this.worldObj.spawnParticle(EnumParticle.flame, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);

    }

    @Override
    protected void onImpact(RaycastCollision rc) {
        if (!this.worldObj.isRemote)
        {
            if (rc.isEntity())
            {
                Entity var3 = rc.getEntityHit();
                float damage = 0.0F;

                if (!((var3 instanceof EntityEarthElemental)) && !(var3 instanceof EntityBlaze) && !(var3 instanceof EntityFireElemental) && !(var3 instanceof EntityMagmaCube) && !(var3 instanceof EntityNetherspawn)){
                    damage = 6.0F;
                    var3.setFire(10);
                }
                var3.attackEntityFrom(new Damage(DamageSource.causeThrownDamage(this, this.getThrower()), damage));
            }
        }

        for (int var5 = 0; var5 < 8; ++var5)
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
