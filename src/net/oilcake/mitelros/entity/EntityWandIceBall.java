package net.oilcake.mitelros.entity;

import net.minecraft.*;
import net.oilcake.mitelros.util.DamageSourceExtend;

public class EntityWandIceBall extends EntityProjectile{
    private Item item;

    public EntityWandIceBall(World world, Item item) {
        super(world);
        this.item = item;
    }
    public EntityWandIceBall(World world, EntityLiving thrower) {
        super(world, thrower);
        this.item = item;
    }

    public EntityWandIceBall(World world, EntityLiving thrower, Item item) {
        super(world, thrower);
        this.item = item;
    }

    public EntityWandIceBall(World world, double pos_x, double pos_y, double pos_z, Item item) {
        super(world, pos_x, pos_y, pos_z);
        this.item = item;
    }
    @Override
    protected float getGravityVelocity() {
        return 0.0F;
    }
    public EntityWandIceBall(World par1World) {
        super(par1World);
    }
    public void onUpdate(){
        super.onUpdate();
    }
    @Override
    protected void onImpact(RaycastCollision rc) {
        if (!this.worldObj.isRemote)
        {
            if (rc.isEntity() && rc.getEntityHit() instanceof EntityLiving)
            {
                Entity var3 = rc.getEntityHit();
                float damage = 1.0F;
                var3.getAsEntityLivingBase().addPotionEffect(new MobEffect(MobEffectList.moveSlowdown.id, 310, 2));
                if (((var3 instanceof EntityEarthElemental)) || (var3 instanceof EntityBlaze) || (var3 instanceof EntityFireElemental) || (var3 instanceof EntityMagmaCube) || (var3 instanceof EntityNetherspawn)){
                    damage = 7.5F;
                }
                var3.attackEntityFrom(new Damage(DamageSourceExtend.freeze, damage));
                this.setDead();
            }else if(rc.isEntity()){
                for (int var5 = 0; var5 < 32; ++var5)
                {
                    this.worldObj.spawnParticle(EnumParticle.snowballpoof, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
                }
            }else {
                if (rc.getNeighborOfBlockHit() == Block.fire) {
                    this.worldObj.douseFire(rc.neighbor_block_x, rc.neighbor_block_y, rc.neighbor_block_z, this);
                }
                rc.getBlockHit().onEntityCollidedWithBlock(this.worldObj, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, this);
            }
        }
        for (int var5 = 0; var5 < 32; ++var5)
        {
            this.worldObj.spawnParticle(EnumParticle.snowballpoof, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }
        if (this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
    public Item getModelItem()
    {
        return Item.snowball;
    }
}
