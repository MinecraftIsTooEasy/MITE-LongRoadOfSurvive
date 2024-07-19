package net.oilcake.mitelros.entity;

import java.util.Calendar;
import java.util.TimeZone;
import net.minecraft.DamageSource;
import net.minecraft.Enchantment;
import net.minecraft.EnchantmentHelper;
import net.minecraft.EntityArrow;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntitySkeleton;
import net.minecraft.Item;
import net.minecraft.ItemBow;
import net.minecraft.ItemStack;
import net.minecraft.NBTTagCompound;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.minecraft.SharedMonsterAttributes;
import net.minecraft.World;
import net.oilcake.mitelros.item.Items;

public class EntityStray extends EntitySkeleton {
   private Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
   private int spawnCounter;
   int num_arrows = 4;
   private int frenzied_by_bone_lord_countdown;

   public EntityStray(World par1World) {
      super(par1World);
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.setEntityAttribute(SharedMonsterAttributes.maxHealth, 8.0);
      this.setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.28999999165534973);
      this.setEntityAttribute(SharedMonsterAttributes.attackDamage, 5.0);
   }

   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
      super.writeEntityToNBT(par1NBTTagCompound);
      par1NBTTagCompound.setByte("SkeletonType", (byte)this.getSkeletonType());
      par1NBTTagCompound.setByte("num_arrows", (byte)this.num_arrows);
   }

   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
      super.readEntityFromNBT(par1NBTTagCompound);
      if (par1NBTTagCompound.hasKey("SkeletonType")) {
         byte var2 = par1NBTTagCompound.getByte("SkeletonType");
         this.setSkeletonType(var2);
      }

      this.setCombatTask();
      this.num_arrows = par1NBTTagCompound.getByte("num_arrows");
   }

   public void onLivingUpdate() {
      if (this.worldObj.isRemote && this.getSkeletonType() == 1) {
         this.setSize(0.72F, 2.34F);
      }

      if (this.frenzied_by_bone_lord_countdown > 0) {
         this.setFrenziedByBoneLordCountdown(this.frenzied_by_bone_lord_countdown - 1);
      }

      if (this.num_arrows == 0 && this.getHeldItemStack() != null && this.getHeldItemStack().getItem() instanceof ItemBow) {
         this.setHeldItemStack((ItemStack)null);
      }

      if (this.getHeldItemStack() == null && this.getSkeletonType() == 0) {
         this.setSkeletonType(2);
         this.setCombatTask();
      }

      if (!this.getWorld().isRemote) {
         ++this.spawnCounter;
         if (this.spawnCounter > 300 && this.getHeldItemStack() != null) {
            if (this.getTarget() != null && this.getHeldItemStack().itemID == Items.FreezeWand.itemID) {
               this.getTarget().addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 350, 0));
            }

            this.spawnCounter = 0;
         }
      }

      super.onLivingUpdate();
   }

   public void addRandomWeapon() {
      if (this.getSkeletonType() == 2 && this.rand.nextInt(8) == 0) {
         this.setHeldItemStack(new ItemStack(Items.FreezeWand));
         this.Is_Wizard = true;
      } else {
         this.setHeldItemStack((new ItemStack((Item)(this.getSkeletonType() == 2 ? (this.rand.nextInt(20) == 0 ? Item.battleAxeRustedIron : Item.daggerRustedIron) : Item.bow))).randomizeForMob(this, true));
      }

   }

   public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2) {
      EntityArrow var3 = new EntityArrow(this.worldObj, this, par1EntityLivingBase, 1.6F, (float)(14 - this.worldObj.difficultySetting * 4), Item.arrowIron, false);
      int var4 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItemStack());
      int var5 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItemStack());
      double damage = (double)(par2 * 3.0F) + this.rand.nextGaussian() * 0.25 + (double)((float)this.worldObj.difficultySetting * 0.11F);
      var3.setDamage(damage);
      if (var4 > 0) {
         var3.setDamage(var3.getDamage() + (double)var4 * 0.5 + 0.5);
      }

      if (var5 > 0) {
         var3.setKnockbackStrength(var5);
      }

      if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, this.getHeldItemStack()) > 0 || this.getSkeletonType() == 1 || this.isBurning() && this.rand.nextInt(3) == 0) {
         var3.setFire(100);
      }

      this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
      this.worldObj.spawnEntityInWorld(var3);
      --this.num_arrows;
   }

   protected void addRandomEquipment() {
      this.addRandomWeapon();
   }

   public float getNaturalDefense(DamageSource damage_source) {
      return super.getNaturalDefense(damage_source) + (damage_source.bypassesMundaneArmor() ? 0.0F : 1.0F);
   }

   public boolean catchesFireInSunlight() {
      return false;
   }
}
