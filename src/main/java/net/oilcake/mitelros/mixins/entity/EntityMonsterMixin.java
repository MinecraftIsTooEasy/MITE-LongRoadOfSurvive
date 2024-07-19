package net.oilcake.mitelros.mixins.entity;

import net.minecraft.Damage;
import net.minecraft.DamageSource;
import net.minecraft.EnchantmentDamage;
import net.minecraft.EnchantmentHelper;
import net.minecraft.EnchantmentThorns;
import net.minecraft.Entity;
import net.minecraft.EntityCreature;
import net.minecraft.EntityDamageResult;
import net.minecraft.EntityLiving;
import net.minecraft.EntityMob;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumEntityFX;
import net.minecraft.ItemStack;
import net.minecraft.ItemTool;
import net.minecraft.MathHelper;
import net.minecraft.Minecraft;
import net.minecraft.NBTTagCompound;
import net.minecraft.PotionEffect;
import net.minecraft.SharedMonsterAttributes;
import net.minecraft.World;
import net.oilcake.mitelros.enchantment.Enchantments;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({EntityMob.class})
public class EntityMonsterMixin extends EntityCreature {
   private boolean modified_attribute = false;

   @Overwrite
   public EntityDamageResult attackEntityAsMob(Entity target) {
      if (this.isDecoy()) {
         return null;
      } else if (this.isPotionActive(PotionExtend.stunning)) {
         return null;
      } else if (target instanceof EntityPlayer && target.getAsPlayer().isImmuneByGrace()) {
         return null;
      } else {
         ItemStack held_item = this.getHeldItemStack();
         Damage damage = new Damage(DamageSource.causeMobDamage(this), (float)this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
         if (this.isFrenzied()) {
            damage.addAmount((float)this.getEntityAttributeBaseValue(SharedMonsterAttributes.attackDamage) * 0.5F);
         }

         int knockback_bonus;
         if (EnchantmentHelper.hasEnchantment(held_item, Enchantments.enchantmentDestroying)) {
            knockback_bonus = EnchantmentHelper.getEnchantmentLevel(Enchantments.enchantmentDestroying, held_item);
            target.worldObj.createExplosion(this, target.posX, target.posY, target.posZ, 0.0F, (float)knockback_bonus * 0.5F, true);
         }

         knockback_bonus = 0;
         if (target.isEntityLivingBase()) {
            damage.addAmount(EnchantmentDamage.getDamageModifiers(held_item, target.getAsEntityLivingBase()));
            knockback_bonus += EnchantmentHelper.getKnockbackModifier(this, target.getAsEntityLivingBase());
         }

         int fire_aspect = EnchantmentHelper.getFireAspectModifier(this);
         EntityDamageResult result = target.attackEntityFrom(damage.setFireAspect(fire_aspect > 0));
         if (result == null) {
            return result;
         } else {
            if (result.entityWasNegativelyAffected()) {
               if (knockback_bonus > 0) {
                  target.addVelocity((double)(-MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F) * (float)knockback_bonus * 0.5F), 0.1, (double)(MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F) * (float)knockback_bonus * 0.5F));
                  this.motionX *= 0.6;
                  this.motionZ *= 0.6;
               }

               if (fire_aspect > 0) {
                  target.setFire(fire_aspect * 4);
               }

               if (this.isBurning() && !this.hasHeldItem() && this.rand.nextFloat() < (float)this.worldObj.difficultySetting * 0.3F) {
                  target.setFire(2 * this.worldObj.difficultySetting);
               }

               if (target.isEntityLivingBase()) {
                  if (this.worldObj.isRemote) {
                     System.out.println("EntityMob.attackEntityAsMob() is calling EnchantmentThorns.func_92096_a() on client");
                     Minecraft.temp_debug = "mob";
                  }

                  EnchantmentThorns.func_92096_a(this, target.getAsEntityLivingBase(), this.rand);
                  int stunning = EnchantmentHelper.getStunModifier(this, target.getAsEntityLivingBase());
                  if ((double)stunning > Math.random() * 10.0) {
                     target.getAsEntityLivingBase().addPotionEffect(new PotionEffect(PotionExtend.stunning.id, stunning * 60, 0));
                  }

                  this.heal((float)EnchantmentHelper.getVampiricTransfer(this, target.getAsEntityLivingBase(), result.getAmountOfHealthLost()), EnumEntityFX.vampiric_gain);
               }

               if (target instanceof EntityPlayer) {
                  this.refreshDespawnCounter(-9600);
               }
            }

            return result;
         }
      }
   }

   @Overwrite
   public static EntityDamageResult attackEntityAsMob(EntityLiving attacker, Entity target) {
      if (attacker.isDecoy()) {
         return null;
      } else if (attacker.isPotionActive(PotionExtend.stunning)) {
         return null;
      } else if (target instanceof EntityPlayer && target.getAsPlayer().isImmuneByGrace()) {
         return null;
      } else {
         ItemStack held_item = attacker.getHeldItemStack();
         Damage damage = new Damage(DamageSource.causeMobDamage(attacker), (float)attacker.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
         if (attacker.isFrenzied()) {
            damage.addAmount((float)attacker.getEntityAttributeBaseValue(SharedMonsterAttributes.attackDamage) * 0.5F);
         }

         int knockback_bonus;
         if (EnchantmentHelper.hasEnchantment(held_item, Enchantments.enchantmentDestroying)) {
            knockback_bonus = EnchantmentHelper.getEnchantmentLevel(Enchantments.enchantmentDestroying, held_item);
            target.worldObj.createExplosion(attacker, target.posX, target.posY, target.posZ, 0.0F, (float)knockback_bonus * 0.5F, true);
         }

         knockback_bonus = 0;
         if (target.isEntityLivingBase()) {
            damage.addAmount(EnchantmentDamage.getDamageModifiers(held_item, target.getAsEntityLivingBase()));
            knockback_bonus += EnchantmentHelper.getKnockbackModifier(attacker, target.getAsEntityLivingBase());
         }

         int fire_aspect = EnchantmentHelper.getFireAspectModifier(attacker);
         EntityDamageResult result = target.attackEntityFrom(damage.setFireAspect(fire_aspect > 0));
         if (result == null) {
            return result;
         } else {
            if (result.entityWasNegativelyAffected()) {
               if (knockback_bonus > 0) {
                  target.addVelocity((double)(-MathHelper.sin(attacker.rotationYaw * 3.1415927F / 180.0F) * (float)knockback_bonus * 0.5F), 0.1, (double)(MathHelper.cos(attacker.rotationYaw * 3.1415927F / 180.0F) * (float)knockback_bonus * 0.5F));
                  attacker.motionX *= 0.6;
                  attacker.motionZ *= 0.6;
               }

               if (fire_aspect > 0) {
                  target.setFire(fire_aspect * 4);
               }

               if (attacker.isBurning() && !attacker.hasHeldItem() && attacker.getRand().nextFloat() < (float)attacker.worldObj.difficultySetting * 0.3F) {
                  target.setFire(2 * attacker.worldObj.difficultySetting);
               }

               if (target.isEntityLivingBase()) {
                  if (attacker.worldObj.isRemote) {
                     System.out.println("EntityMob.attackEntityAsMob() is calling EnchantmentThorns.func_92096_a() on client");
                     Minecraft.temp_debug = "mob";
                  }

                  EnchantmentThorns.func_92096_a(attacker, target.getAsEntityLivingBase(), attacker.getRand());
                  int stunning = EnchantmentHelper.getStunModifier(attacker, target.getAsEntityLivingBase());
                  if ((double)stunning > Math.random() * 10.0) {
                     target.getAsEntityLivingBase().addPotionEffect(new PotionEffect(PotionExtend.stunning.id, stunning * 60, 0));
                  }

                  attacker.heal((float)EnchantmentHelper.getVampiricTransfer(attacker, target.getAsEntityLivingBase(), result.getAmountOfHealthLost()), EnumEntityFX.vampiric_gain);
               }

               if (target instanceof EntityPlayer) {
                  attacker.refreshDespawnCounter(-9600);
               }
            }

            return result;
         }
      }
   }

   public EntityMonsterMixin(World par1World) {
      super(par1World);
   }

   @Overwrite
   public void onUpdate() {
      super.onUpdate();
      if (!this.worldObj.isRemote && !this.modified_attribute && this.getHealth() > 0.0F && (Boolean)ExperimentalConfig.TagConfig.FinalChallenge.ConfigValue) {
         this.setEntityAttribute(SharedMonsterAttributes.maxHealth, (double)(this.getMaxHealth() * (1.0F + (float)Constant.CalculateCurrentDiff() / 16.0F)));
         double attack_damage = this.getEntityAttributeValue(SharedMonsterAttributes.attackDamage);
         if (this.getHeldItemStack() != null && this.getHeldItemStack().getItem() instanceof ItemTool) {
            attack_damage -= (double)this.getHeldItemStack().getItemAsTool().getMaterialDamageVsEntity();
            attack_damage -= (double)this.getHeldItemStack().getItemAsTool().getBaseDamageVsEntity();
         }

         this.setEntityAttribute(SharedMonsterAttributes.attackDamage, attack_damage * (double)(1.0F + (float)Constant.CalculateCurrentDiff() / 32.0F));
         this.setHealth(this.getMaxHealth());
         this.modified_attribute = true;
      }

      if (!this.worldObj.isRemote && this.worldObj.difficultySetting == 0) {
         this.setDead();
      }

   }

   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
      super.writeEntityToNBT(par1NBTTagCompound);
      par1NBTTagCompound.setBoolean("modified_attribute", this.modified_attribute);
   }

   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
      super.readEntityFromNBT(par1NBTTagCompound);
      this.modified_attribute = par1NBTTagCompound.getBoolean("modified_attribute");
   }
}
