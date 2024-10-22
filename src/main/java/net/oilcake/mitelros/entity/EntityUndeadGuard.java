package net.oilcake.mitelros.entity;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.DamageSource;
import net.minecraft.Enchantment;
import net.minecraft.EnchantmentHelper;
import net.minecraft.EntityAIArrowAttack;
import net.minecraft.EntityAIAttackOnCollide;
import net.minecraft.EntityAIFleeSun;
import net.minecraft.EntityAIHurtByTarget;
import net.minecraft.EntityAILookIdle;
import net.minecraft.EntityAIMoveToRepairItem;
import net.minecraft.EntityAINearestAttackableTarget;
import net.minecraft.EntityAIRestrictSun;
import net.minecraft.EntityAISeekFiringPosition;
import net.minecraft.EntityAISwimming;
import net.minecraft.EntityAIWander;
import net.minecraft.EntityAIWatchClosest;
import net.minecraft.EntityArrow;
import net.minecraft.EntityLiving;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityMob;
import net.minecraft.EntityPlayer;
import net.minecraft.EntitySkeleton;
import net.minecraft.EnumEntityFX;
import net.minecraft.IEntitySelector;
import net.minecraft.IRangedAttackMob;
import net.minecraft.Item;
import net.minecraft.ItemBow;
import net.minecraft.ItemStack;
import net.minecraft.Minecraft;
import net.minecraft.NBTTagCompound;
import net.minecraft.RandomItemListEntry;
import net.minecraft.SharedMonsterAttributes;
import net.minecraft.WeightedRandom;
import net.minecraft.World;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.util.EntitySelectorHunter;

public class EntityUndeadGuard extends EntitySkeleton implements IRangedAttackMob {
   IEntitySelector hunterSelector = new EntitySelectorHunter();
   ItemStack stowed_item_stack;
   int num_arrows;
   private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0, 20, 60, 15.0F);
   private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityMob.class, 1.2, false);

   public EntityUndeadGuard(World par1World) {
      super(par1World);
      this.getNavigator().setAvoidsWater(true);
      this.tasks.clear();
      this.targetTasks.clear();
      this.setSkeletonType(2);
      this.tasks.addTask(1, new EntityAISwimming(this));
      this.tasks.addTask(2, new EntityAIRestrictSun(this));
      this.tasks.addTask(3, new EntityAIFleeSun(this, 1.0));
      this.tasks.addTask(5, new EntityAIWander(this, 0.75));
      this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.tasks.addTask(6, new EntityAILookIdle(this));
      this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
      this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, true, this.hunterSelector));
      this.tasks.addTask(4, new EntityAIMoveToRepairItem(this, 1.0F, true));
      this.setCanPickUpLoot(false);
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.27000001072883606);
      this.setEntityAttribute(SharedMonsterAttributes.attackDamage, 6.0);
      this.setEntityAttribute(SharedMonsterAttributes.maxHealth, 12.0);
   }

   public void setCombatTask() {
      this.tasks.removeTask(this.aiAttackOnCollide);
      this.tasks.removeTask(this.aiArrowAttack);
      ItemStack var1 = this.getHeldItemStack();
      if (var1 != null && var1.getItem() instanceof ItemBow && this.num_arrows > 0) {
         this.tasks.addTask(4, this.aiArrowAttack);
         this.tasks.addTask(3, new EntityAISeekFiringPosition(this, 1.0F, true));
      } else {
         this.tasks.addTask(4, this.aiAttackOnCollide);
      }

   }

   public boolean isHoldingRangedWeapon() {
      return this.getHeldItem() instanceof ItemBow;
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      if (this.num_arrows == 0 && this.getHeldItemStack() != null && this.getHeldItemStack().getItem() instanceof ItemBow) {
         this.setHeldItemStack((ItemStack)null);
      }

      if (this.getHeldItemStack() == null && this.getSkeletonType() == 0) {
         this.setSkeletonType(2);
         this.setCombatTask();
      }

      if (this.ticksExisted > 300) {
         this.entityFX(EnumEntityFX.steam_with_hiss);
         this.setDead();
      }

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

   public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2) {
      EntityArrow var3 = new EntityArrow(this.worldObj, this, par1EntityLivingBase, 1.6F, (float)(14 - this.worldObj.difficultySetting * 4), Items.arrowTungsten, false);
      int var4 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItemStack());
      int var5 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItemStack());
      double damage = (double)(par2 * 2.0F) + this.rand.nextGaussian() * 0.25 + (double)((float)this.worldObj.difficultySetting * 0.11F);
      var3.setDamage(damage);
      if (var4 > 0) {
         var3.setDamage(var3.getDamage() + (double)var4 * 0.5 + 0.5);
      }

      if (var5 > 0) {
         var3.setKnockbackStrength(var5);
      }

      this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
      this.worldObj.spawnEntityInWorld(var3);
      --this.num_arrows;
   }

   protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
   }

   public int getRandomSkeletonType(World world) {
      return 2;
   }

   public int getExperienceValue() {
      return 0;
   }

   public void addRandomWeapon() {
      List items = new ArrayList();
      items.add(new RandomItemListEntry(Item.daggerRustedIron, 2));
      if (!Minecraft.isInTournamentMode()) {
         items.add(new RandomItemListEntry(Item.swordRustedIron, 1));
         items.add(new RandomItemListEntry(Item.battleAxeRustedIron, 1));
      }

      RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, items);
      this.setHeldItemStack((new ItemStack(entry.item)).randomizeForMob(this, true));
   }

   protected void addRandomEquipment() {
      this.addRandomWeapon();
      this.setBoots((new ItemStack(Item.bootsChainRustedIron)).randomizeForMob(this, true));
      this.setLeggings((new ItemStack(Item.legsChainRustedIron)).randomizeForMob(this, true));
      this.setCuirass((new ItemStack(Item.plateChainRustedIron)).randomizeForMob(this, true));
      this.setHelmet((new ItemStack(Item.helmetChainRustedIron)).randomizeForMob(this, true));
   }
}
