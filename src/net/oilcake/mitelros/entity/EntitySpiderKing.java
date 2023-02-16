package net.oilcake.mitelros.entity;

import net.minecraft.*;

import java.util.Iterator;
import java.util.List;

public class EntitySpiderKing extends EntityArachnid {
    int num_webs;
    public EntitySpiderKing(World par1World) {
        super(par1World, 1.45F);
        this.num_webs = 4;
    }

    protected String getLivingSound() {
        return "imported.mob.demonspider.say";
    }

    protected String getHurtSound() {
        return "imported.mob.demonspider.hurt";
    }

    protected String getDeathSound() {
        return "imported.mob.demonspider.death";
    }

    protected float getSoundVolume(String sound) {
        return super.getSoundVolume(sound) * 1.3F;
    }

    protected float getSoundPitch(String sound) {
        return super.getSoundPitch(sound) * 0.6F;
    }
    public boolean peacefulDuringDay() {
        return false;
    }
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.setEntityAttribute(GenericAttributes.maxHealth, 28.0);
        this.setEntityAttribute(GenericAttributes.followRange, 48.0);
        this.setEntityAttribute(GenericAttributes.attackDamage, 13.0);
        this.setEntityAttribute(GenericAttributes.movementSpeed, 0.92);
    }
    public EntityDamageResult attackEntityAsMob(Entity target) {
        EntityDamageResult result = super.attackEntityAsMob(target);
        if (result != null && !result.entityWasDestroyed()) {
            if (result.entityLostHealth() && target instanceof EntityLiving) {
                target.getAsEntityLivingBase().addPotionEffect(new MobEffect(MobEffectList.moveSlowdown.id, 200, 5));
                target.getAsEntityLivingBase().addPotionEffect(new MobEffect(MobEffectList.poison.id, 850, 0));
            }

            return result;
        } else {
            return result;
        }
    }
    private int spawnCounter;
    private int spawnSums;
    public void onUpdate()
    {
        super.onUpdate();
        if (!getWorld().isRemote)
        {
                if (spawnSums < 8)
                    if (spawnCounter < 20)
                    {
                        spawnCounter++;
                    } else
                    {
                        EntityClusterSpider clusterSpider = new EntityClusterSpider(worldObj);
                        clusterSpider.setPosition(posX+this.rand.nextInt(8)-this.rand.nextInt(8), posY, posZ-this.rand.nextInt(8)+this.rand.nextInt(8));
                        clusterSpider.refreshDespawnCounter(-9600);
                        worldObj.spawnEntityInWorld(clusterSpider);
                        clusterSpider.onSpawnWithEgg(null);
                        clusterSpider.entityFX(EnumEntityFX.summoned);
                        spawnCounter = 0;
                        spawnSums++;
                    }
        }
    }
    public int getExperienceValue() {
        return super.getExperienceValue() * 3;
    }
}
