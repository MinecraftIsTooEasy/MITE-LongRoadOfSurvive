package net.oilcake.mitelros.entity;

import java.util.Iterator;
import java.util.List;
import net.minecraft.DamageSource;
import net.minecraft.Entity;
import net.minecraft.EntityArachnid;
import net.minecraft.EntityDamageResult;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityLivingData;
import net.minecraft.EntityPotion;
import net.minecraft.EnumEntityFX;
import net.minecraft.NBTTagCompound;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.minecraft.SharedMonsterAttributes;
import net.minecraft.World;

public class EntitySpiderKing extends EntityArachnid {
   private final int num_webs = 4;
   private int spawnCounter;
   private int spawnSums;
   private boolean gathering_troops = false;

   public EntitySpiderKing(World par1World) {
      super(par1World, 1.45F);
   }

   protected String getLivingSound() {
      return "imported.mob.spiderking.say";
   }

   protected String getHurtSound() {
      return "imported.mob.spiderking.hit";
   }

   protected String getDeathSound() {
      return "imported.mob.spiderking.death";
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

   public float getNaturalDefense(DamageSource damage_source) {
      return super.getNaturalDefense(damage_source) + (damage_source.bypassesMundaneArmor() ? 0.0F : 3.0F);
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.setEntityAttribute(SharedMonsterAttributes.maxHealth, 28.0);
      this.setEntityAttribute(SharedMonsterAttributes.followRange, 56.0);
      this.setEntityAttribute(SharedMonsterAttributes.attackDamage, 13.0);
      this.setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.92);
   }

   public EntityDamageResult attackEntityAsMob(Entity target) {
      EntityDamageResult result = super.attackEntityAsMob(target);
      if (result != null && !result.entityWasDestroyed()) {
         if (result.entityLostHealth() && target instanceof EntityLivingBase) {
            target.getAsEntityLivingBase().addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200, 5));
            target.getAsEntityLivingBase().addPotionEffect(new PotionEffect(Potion.poison.id, 850, 0));
         }

         return result;
      } else {
         return result;
      }
   }

   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
      super.readEntityFromNBT(par1NBTTagCompound);
      this.spawnSums = par1NBTTagCompound.getByte("num_troops_summoned");
   }

   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
      super.writeEntityToNBT(par1NBTTagCompound);
      if (this.spawnSums > 0) {
         par1NBTTagCompound.setByte("num_troops_summoned", (byte)this.spawnSums);
      }

   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (!this.getWorld().isRemote) {
         List nearby_spiders = this.worldObj.getEntitiesWithinAABB(EntityArachnid.class, this.boundingBox.expand(16.0, 8.0, 16.0));
         Iterator i = nearby_spiders.iterator();
         if (this.getTicksExistedWithOffset() % 100 == 0) {
            while(i.hasNext()) {
               EntityArachnid spiders = (EntityArachnid)i.next();
               if (spiders != this) {
                  if (this.getTarget() != null) {
                     spiders.setTarget(this.getTarget());
                  }

                  spiders.setFrenzied_counter(200);
                  if (spiders.getHealthFraction() < 1.0F) {
                     spiders.healByPercentage(0.2F);
                     spiders.entityFX(EnumEntityFX.repair);
                     spiders.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 200, 0, true));
                     spiders.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 200, 0, true));
                  }
               }
            }
         }

         if (this.getTarget() != null && !this.isNoDespawnRequired() && this.getTarget() != null) {
            this.gathering_troops = true;
            this.func_110163_bv();
         }

         if (this.spawnSums < 8 && this.gathering_troops) {
            if (this.spawnCounter < 20) {
               ++this.spawnCounter;
            } else {
               EntityClusterSpider clusterSpider = new EntityClusterSpider(this.worldObj);
               clusterSpider.setPosition(this.posX + (double)this.rand.nextInt(4) - (double)this.rand.nextInt(4), this.posY, this.posZ - (double)this.rand.nextInt(4) + (double)this.rand.nextInt(4));
               clusterSpider.refreshDespawnCounter(-9600);
               this.worldObj.spawnEntityInWorld(clusterSpider);
               clusterSpider.onSpawnWithEgg((EntityLivingData)null);
               clusterSpider.entityFX(EnumEntityFX.summoned);
               this.spawnCounter = 0;
               ++this.spawnSums;
            }
         }
      }

   }

   public void onDeathUpdate() {
      super.onDeathUpdate();
      if (this.deathTime == 20) {
         EntityPotion potion = new EntityPotion(this.worldObj, this, 16388);
         potion.setPosition(this.posX, this.posY - 1.0, this.posZ);
         this.worldObj.spawnEntityInWorld(potion);
         potion = new EntityPotion(this.worldObj, this, 16426);
         potion.setPosition(this.posX, this.posY - 1.0, this.posZ);
         this.worldObj.spawnEntityInWorld(potion);
      }

   }

   public int getExperienceValue() {
      return super.getExperienceValue() * 3;
   }
}
