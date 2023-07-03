package net.oilcake.mitelros.entity;

import net.minecraft.*;

public class EntityHusk extends EntityZombie {
    public EntityHusk(World par1World) {
        super(par1World);
    }
    public void setVillager(boolean villager, int profession) {
//        Minecraft.setErrorMessage("setVillager: why setting villager for husk?");
//        (new Exception()).printStackTrace();
    }
    public boolean isVillager() {
        return false;
    }
    public boolean catchesFireInSunlight() {
        return false;
    }

    public EntityDamageResult attackEntityAsMob(Entity target) {
        EntityDamageResult result = super.attackEntityAsMob(target);
        if (result != null && !result.entityWasDestroyed()) {
            if (result.entityLostHealth() && target instanceof EntityPlayer) {
                target.getAsEntityLivingBase().addPotionEffect(new MobEffect(MobEffectList.hunger.id, 650));
            }
            return result;
        } else {
            return result;
        }
    }

}
