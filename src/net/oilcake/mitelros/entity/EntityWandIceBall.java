package net.oilcake.mitelros.entity;

import net.minecraft.*;
import net.oilcake.mitelros.util.DamageSourceExtend;

public class EntityWandIceBall extends EntityProjectileNoGravity{

    public EntityWandIceBall(World world, EntityLiving thrower) {
        super(world, thrower);
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
            if (this.onServer() && rc.isBlock()){
                this.setDead();
            }
            if (rc.isEntity())
            {
                Entity var3 = rc.getEntityHit();
                float damage = 1.0F;
                var3.getAsEntityLivingBase().addPotionEffect(new MobEffect(MobEffectList.moveSlowdown.id, 310, 2));
                if (((var3 instanceof EntityEarthElemental)) || (var3 instanceof EntityBlaze) || (var3 instanceof EntityFireElemental) || (var3 instanceof EntityMagmaCube) || (var3 instanceof EntityNetherspawn)){
                    damage = 7.5F;
                }
                var3.attackEntityFrom(new Damage(DamageSourceExtend.freeze, damage));
                this.setDead();
            }
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
