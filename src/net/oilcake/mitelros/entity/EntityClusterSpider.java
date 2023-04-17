package net.oilcake.mitelros.entity;

import net.minecraft.*;

public class EntityClusterSpider extends EntityArachnid {
    int num_webs;
    public EntityClusterSpider(World par1World) {
        super(par1World, 0.4F);
        this.num_webs = 4;
    }
    protected float getSoundVolume(String sound) {
        return super.getSoundVolume(sound) * 0.5F;
    }

    protected float getSoundPitch(String sound) {
        return super.getSoundPitch(sound) * 1.4F;
    }
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.setEntityAttribute(GenericAttributes.maxHealth, 6.0);
        this.setEntityAttribute(GenericAttributes.followRange, 48.0);
        this.setEntityAttribute(GenericAttributes.attackDamage, 3.0);
        this.setEntityAttribute(GenericAttributes.movementSpeed, 0.75);
    }
    public EntityDamageResult attackEntityAsMob(Entity target) {
        EntityDamageResult result = super.attackEntityAsMob(target);
        if (result != null && !result.entityWasDestroyed()) {
            if (result.entityLostHealth() && target instanceof EntityLiving) {
                target.getAsEntityLivingBase().addPotionEffect(new MobEffect(MobEffectList.moveSlowdown.id, 450, 1));
            }

            return result;
        } else {
            return result;
        }
    }
}
