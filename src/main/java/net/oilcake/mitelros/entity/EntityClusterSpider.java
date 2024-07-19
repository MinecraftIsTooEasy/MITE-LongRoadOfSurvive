package net.oilcake.mitelros.entity;

import net.minecraft.Entity;
import net.minecraft.EntityArachnid;
import net.minecraft.EntityDamageResult;
import net.minecraft.EntityLivingBase;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.minecraft.SharedMonsterAttributes;
import net.minecraft.World;

public class EntityClusterSpider extends EntityArachnid {
   int num_webs = 4;

   public EntityClusterSpider(World par1World) {
      super(par1World, 0.4F);
   }

   protected float getSoundVolume(String sound) {
      return super.getSoundVolume(sound) * 0.5F;
   }

   protected float getSoundPitch(String sound) {
      return super.getSoundPitch(sound) * 1.4F;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.setEntityAttribute(SharedMonsterAttributes.maxHealth, 6.0);
      this.setEntityAttribute(SharedMonsterAttributes.attackDamage, 3.0);
      this.setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.75);
   }

   public EntityDamageResult attackEntityAsMob(Entity target) {
      EntityDamageResult result = super.attackEntityAsMob(target);
      if (result != null && !result.entityWasDestroyed()) {
         if (result.entityLostHealth() && target instanceof EntityLivingBase) {
            target.getAsEntityLivingBase().addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 450, 1));
         }

         return result;
      } else {
         return result;
      }
   }
}
