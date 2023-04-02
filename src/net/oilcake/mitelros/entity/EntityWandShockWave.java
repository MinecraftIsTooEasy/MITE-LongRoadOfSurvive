package net.oilcake.mitelros.entity;

import net.minecraft.*;

public class EntityWandShockWave extends EntityProjectileNoGravity{
    public EntityWandShockWave(World world, EntityLiving thrower) {
        super(world, thrower);
    }
    public EntityWandShockWave(World par1World) {
        super(par1World);
    }
    public void onUpdate(){
        super.onUpdate();
        this.worldObj.spawnParticle(EnumParticle.magicCrit, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
        this.worldObj.spawnParticle(EnumParticle.witchMagic, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);

    }
    @Override
    protected void onImpact(RaycastCollision rc) {
        if (!this.worldObj.isRemote)
        {
            if (rc.isEntity() && rc.getEntityHit() instanceof EntityLiving)
            {
                this.setDead();
                Entity var3 = rc.getEntityHit();
                float damage = 10.0F;
                var3.attackEntityFrom(new Damage(DamageSource.divine_lightning, damage));
                EntityLightning lightingbolt = new EntityLightning(worldObj,var3.posX,var3.posY,var3.posZ);
                worldObj.spawnEntityInWorld(lightingbolt);
                lightingbolt.entityFX(EnumEntityFX.summoned);
            }
            else{
                this.setDead();;
                EntityLightning lightingbolt = new EntityLightning(worldObj,rc.block_hit_x, rc.block_hit_y, rc.block_hit_z);
                worldObj.spawnEntityInWorld(lightingbolt);
                lightingbolt.entityFX(EnumEntityFX.summoned);
            }
        }
        if (this.onServer() && rc.isBlock()) {
            this.setDead();
            EntityLightning lightingbolt = new EntityLightning(worldObj,rc.block_hit_x, rc.block_hit_y, rc.block_hit_z);
            worldObj.spawnEntityInWorld(lightingbolt);
            lightingbolt.entityFX(EnumEntityFX.summoned);
        }

        for (int var5 = 0; var5 < 8; ++var5)
        {
            this.worldObj.spawnParticle(EnumParticle.witchMagic, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }
        if (this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
    public Item getModelItem()
    {
        return Item.eyeOfEnder;
    }
}
