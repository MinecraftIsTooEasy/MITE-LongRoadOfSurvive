package net.oilcake.mitelros.entity;

import java.util.Random;
import net.minecraft.AxisAlignedBB;
import net.minecraft.Block;
import net.minecraft.Damage;
import net.minecraft.DamageSource;
import net.minecraft.EntityAIFleeAttackerOrPanic;
import net.minecraft.EntityAIFollowParent;
import net.minecraft.EntityAILookIdle;
import net.minecraft.EntityAIMate;
import net.minecraft.EntityAISwimming;
import net.minecraft.EntityAITempt;
import net.minecraft.EntityAIWander;
import net.minecraft.EntityAIWatchClosest;
import net.minecraft.EntityAgeable;
import net.minecraft.EntityDamageResult;
import net.minecraft.EntityLivestock;
import net.minecraft.EntityLiving;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumParticle;
import net.minecraft.Item;
import net.minecraft.ItemBlock;
import net.minecraft.ItemStack;
import net.minecraft.MathHelper;
import net.minecraft.NBTTagCompound;
import net.minecraft.SharedMonsterAttributes;
import net.minecraft.World;

public class EntityAgarician extends EntityLivestock {
   private int max_num_mushrooms;
   private int num_mushrooms;
   public String[] types_index;
   private int type;
   private int manure_period;
   private int manure_countdown;
   private float water;
   private float food;

   public EntityAgarician(World par1World) {
      this(par1World, 0);
   }

   public EntityAgarician(World par1World, int type) {
      super(par1World);
      this.max_num_mushrooms = 16;
      this.types_index = new String[]{"Brown", "Red", "Luminescent"};
      this.setSize(1.5F, 1.5F);
      this.getNavigator().setAvoidsWater(true);
      this.tasks.addTask(0, new EntityAISwimming(this));
      this.tasks.addTask(2, new EntityAIMate(this, 1.0));
      this.tasks.addTask(3, new EntityAITempt(this, 1.25, Block.cake.blockID, false));
      this.tasks.addTask(3, new EntityAITempt(this, 1.25, Item.cake.itemID, false));
      this.tasks.addTask(4, new EntityAIFollowParent(this, 1.25));
      this.tasks.addTask(5, new EntityAIWander(this, 1.0));
      this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.tasks.addTask(7, new EntityAILookIdle(this));
      this.tasks.addTask(1, new EntityAIFleeAttackerOrPanic(this, 1.6F, 0.5F, true));
      this.setType(type);
      this.num_mushrooms = 2;
   }

   protected void entityInit() {
      super.entityInit();
   }

   public void setType(int type) {
      this.type = type;
   }

   public int getType() {
      return this.type;
   }

   public EntityAgeable createChild(EntityAgeable entityAgeable) {
      return this.spawnBabyAnimal(entityAgeable);
   }

   public EntityAgarician spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
      return new EntityAgarician(this.worldObj, this.rand.nextInt(this.types_index.length));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.setEntityAttribute(SharedMonsterAttributes.maxHealth, 30.0);
      this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(64.0);
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.175);
   }

   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
      super.writeEntityToNBT(par1NBTTagCompound);
      par1NBTTagCompound.setInteger("max_num_mushrooms", this.max_num_mushrooms);
      par1NBTTagCompound.setInteger("num_mushrooms", this.num_mushrooms);
      par1NBTTagCompound.setInteger("type", this.type);
   }

   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
      super.readEntityFromNBT(par1NBTTagCompound);
      this.max_num_mushrooms = par1NBTTagCompound.getInteger("max_num_mushrooms");
      this.num_mushrooms = par1NBTTagCompound.getInteger("num_mushrooms");
      this.type = par1NBTTagCompound.getInteger("type");
   }

   public boolean isFoodItem(ItemStack item_stack) {
      if (item_stack != null) {
         Item item = item_stack.getItem();
         if (item == Item.cake) {
            return true;
         }

         if (item instanceof ItemBlock) {
            ItemBlock item_block = (ItemBlock)item;
            if (item_block.getBlock() == Block.mushroomBrown) {
               return true;
            }
         }
      }

      return false;
   }

   public void produceGoods() {
      if (this.production_counter >= 80) {
         if (this.rand.nextInt(2) == 0) {
            if (this.rand.nextInt(30) == 0) {
               EntityAgeable var1 = this.createChild(this);
               if (var1 != null) {
                  this.setGrowingAgeAfterBreeding();
                  this.resetInLove();
                  var1.setGrowingAgeToNewborn();
                  var1.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
                  if (!this.getWorld().isRemote && var1 instanceof EntityLivestock) {
                     ((EntityLivestock)var1).adoptWellnessFromParents(this, this);
                  }

                  this.worldObj.spawnEntityInWorld(var1);
                  Random var2 = this.getRNG();

                  for(int var3 = 0; var3 < 7; ++var3) {
                     double var4 = var2.nextGaussian() * 0.02;
                     double var6 = var2.nextGaussian() * 0.02;
                     double var8 = var2.nextGaussian() * 0.02;
                     this.worldObj.spawnParticle(EnumParticle.heart, this.posX + (double)(var2.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5 + (double)(var2.nextFloat() * this.height), this.posZ + (double)(var2.nextFloat() * this.width * 2.0F) - (double)this.width, var4, var6, var8);
                  }
               }
            } else {
               this.gainMushroom();
            }
         }

         this.production_counter -= 80;
      }

   }

   public void setWater(float water) {
      if (!this.worldObj.isRemote) {
         this.water = MathHelper.clamp_float(water, 0.0F, 1.0F);
         this.setIsWell(this.isWell());
         this.setIsThirsty(this.isThirsty());
      }

   }

   public void setFood(float food) {
      if (!this.worldObj.isRemote) {
         this.food = MathHelper.clamp_float(food, 0.0F, 1.0F);
         this.setIsWell(this.isWell());
      }

   }

   public void setManurePeriod(int manure_period) {
      this.manure_period = 25000;
      this.manure_countdown = 12500;
   }

   protected void fall(float par1) {
      super.fall(par1);
   }

   protected String getLivingSound() {
      return "imported.mob.agarician.say";
   }

   protected String getHurtSound() {
      return "imported.mob.agarician.hurt";
   }

   protected String getDeathSound() {
      return "imported.mob.agarician.death";
   }

   protected float getSoundPitch(String sound) {
      return (this.isWell() ? 1.0F : 0.7F) * (this.isChild() ? 1.5F : 1.0F);
   }

   protected void gainMushroom() {
      if (this.num_mushrooms < this.max_num_mushrooms) {
         ++this.num_mushrooms;
      } else {
         this.dropItemStack(this.getDropItem());
      }

   }

   protected void updateAITasks() {
      super.updateAITasks();
   }

   protected ItemStack getDropItem() {
      return this.type == 0 ? new ItemStack(Block.mushroomBrown) : (this.type == 1 ? new ItemStack(Block.mushroomRed) : null);
   }

   protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
      if (!this.isBurning()) {
         for(int i = 0; i < this.num_mushrooms; ++i) {
            this.dropItemStack(this.getDropItem());
         }
      }

   }

   protected boolean isFoodSource(int block_id) {
      Block block = Block.blocksList[block_id];
      return block == Block.mycelium;
   }

   public int[] getFoodBlockIDs() {
      int[] block_ids = new int[]{Block.mushroomBrown.blockID, Block.mushroomCapBrown.blockID, Block.mushroomRed.blockID, Block.mushroomCapRed.blockID};
      return block_ids;
   }

   public boolean isCrowded() {
      return this.worldObj.getEntitiesWithinAABB(EntityLiving.class, this.boundingBox.expand(2.0, 0.5, 2.0)).size() > 2;
   }

   public boolean isCrowded(int x, int y, int z) {
      AxisAlignedBB bounding_box = new AxisAlignedBB();
      bounding_box.setBounds((double)(x - 2), (double)((float)y - 0.5F), (double)(z - 2), (double)(x + 2), (double)((float)y + 0.5F), (double)(z + 2));
      return this.worldObj.getEntitiesWithinAABB(EntityLiving.class, bounding_box).size() > 2;
   }

   public boolean getCanSpawnHere(boolean perform_light_check) {
      return (!perform_light_check || this.isValidLightLevel()) && super.getCanSpawnHere(perform_light_check);
   }

   public EntityDamageResult attackEntityFrom(Damage damage) {
      EntityDamageResult result = super.attackEntityFrom(damage);
      return damage.isFallDamage() ? null : result;
   }
}
