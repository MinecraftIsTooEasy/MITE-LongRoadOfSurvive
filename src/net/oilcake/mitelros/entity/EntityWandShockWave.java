package net.oilcake.mitelros.entity;

import net.minecraft.*;

public class EntityWandShockWave extends EntityProjectile{
    private Item item;

    public EntityWandShockWave(World world, Item item) {
        super(world);
        this.item = item;
    }
    public EntityWandShockWave(World world, EntityLiving thrower) {
        super(world, thrower);
        this.item = item;
    }

    public EntityWandShockWave(World world, EntityLiving thrower, Item item) {
        super(world, thrower);
        this.item = item;
    }

    public EntityWandShockWave(World world, double pos_x, double pos_y, double pos_z, Item item) {
        super(world, pos_x, pos_y, pos_z);
        this.item = item;
    }
    @Override
    protected float getGravityVelocity() {
        return 0.0F;
    }
    public void onUpdate(){
        super.onUpdate();
        this.worldObj.spawnParticle(EnumParticle.magicCrit, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        this.worldObj.spawnParticle(EnumParticle.witchMagic, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);

    }
    @Override
    protected void onImpact(RaycastCollision rc) {
        if (!this.worldObj.isRemote)
        {
            if (rc.isEntity() && rc.getEntityHit() instanceof EntityLiving)
            {
                Entity var3 = rc.getEntityHit();
                float damage = 10.0F;
                var3.attackEntityFrom(new Damage(DamageSource.divine_lightning, damage));
                EntityLightning lightingbolt = new EntityLightning(worldObj,var3.posX,var3.posY,var3.posZ);
                worldObj.spawnEntityInWorld(lightingbolt);
                lightingbolt.entityFX(EnumEntityFX.summoned);
                this.setDead();
            }else if(rc.isEntity()){
                Entity var3 = rc.getEntityHit();
                EntityLightning lightingbolt = new EntityLightning(worldObj,var3.posX,var3.posY,var3.posZ);
                worldObj.spawnEntityInWorld(lightingbolt);
                lightingbolt.entityFX(EnumEntityFX.summoned);
                this.setDead();
            }else{
                EntityLightning lightingbolt = new EntityLightning(worldObj,rc.block_hit_x, rc.block_hit_y, rc.block_hit_z);
                worldObj.spawnEntityInWorld(lightingbolt);
                lightingbolt.entityFX(EnumEntityFX.summoned);
                rc.getBlockHit().onEntityCollidedWithBlock(this.worldObj, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, this);
                this.setDead();
            }
        }
        for (int var5 = 0; var5 < 32; ++var5)
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
