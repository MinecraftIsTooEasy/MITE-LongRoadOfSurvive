package net.oilcake.mitelros.mixins.entity.player;

import java.util.List;

import ink.huix.iinjected.EntityPlayerKt;
import ink.huix.iinjected.FoodStatsKt;
import ink.huix.iinjected.InventoryPlayerKt;
import ink.huix.iinjected.WorldKt;
import net.minecraft.AchievementList;
import net.minecraft.BiomeGenBase;
import net.minecraft.Block;
import net.minecraft.ChunkCoordinates;
import net.minecraft.Curse;
import net.minecraft.Damage;
import net.minecraft.DamageSource;
import net.minecraft.Enchantment;
import net.minecraft.EnchantmentDamage;
import net.minecraft.EnchantmentHelper;
import net.minecraft.EnchantmentThorns;
import net.minecraft.Entity;
import net.minecraft.EntityClientPlayerMP;
import net.minecraft.EntityDamageResult;
import net.minecraft.EntityDragonPart;
import net.minecraft.EntityItem;
import net.minecraft.EntityLiving;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityLivingData;
import net.minecraft.EntityMob;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumEntityFX;
import net.minecraft.EnumEntityReachContext;
import net.minecraft.EnumInsulinResistanceLevel;
import net.minecraft.EnumLevelBonus;
import net.minecraft.EnumQuality;
import net.minecraft.EnumSignal;
import net.minecraft.EnumSkyBlock;
import net.minecraft.FoodStats;
import net.minecraft.ICommandSender;
import net.minecraft.IEntityMultiPart;
import net.minecraft.InventoryEnderChest;
import net.minecraft.InventoryPlayer;
import net.minecraft.Item;
import net.minecraft.ItemArmor;
import net.minecraft.ItemHorseArmor;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.MathHelper;
import net.minecraft.Minecraft;
import net.minecraft.NBTTagCompound;
import net.minecraft.NBTTagList;
import net.minecraft.Packet;
import net.minecraft.Packet85SimpleSignal;
import net.minecraft.PlayerCapabilities;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.minecraft.StatBase;
import net.minecraft.StatList;
import net.minecraft.StringHelper;
import net.minecraft.World;
import net.oilcake.mitelros.achivements.AchievementExtend;
import net.oilcake.mitelros.block.enchantreserver.EnchantReserverSlots;
import net.oilcake.mitelros.enchantment.Enchantments;
import net.oilcake.mitelros.entity.EntityUndeadGuard;
import net.oilcake.mitelros.item.ItemTotem;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.util.CurseExtend;
import net.oilcake.mitelros.util.DamageSourceExtend;
import net.oilcake.mitelros.util.ExperimentalConfig;
import net.oilcake.mitelros.util.StuckTagConfig;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin extends EntityLivingBase implements ICommandSender, EntityPlayerKt {
   public boolean isNewPlayer = true;
   private int detectorDelay = 0;
   private int lightTick = 0;
   @Shadow
   private int insulin_resistance;
   @Shadow
   public EnumInsulinResistanceLevel insulin_resistance_level;
   private int hunt_counter = 0;
   public boolean hunt_cap = false;
   private int HeatResistance;
   private int FreezingCooldown;
   private int diarrheaCounter;
   private int FreezingWarning;
   private int reduce_weight;
   public float bodyTemperature = 37.2F;
   private int temperaturePoint = 96000;
   private double dry_resist;
   public boolean Hasdrunked = false;
   private int drunk_duration = 0;
   private int water_duration = 0;
   @Shadow
   protected ItemStack itemInUse;
   @Shadow
   protected FoodStats foodStats;
   @Shadow
   public PlayerCapabilities capabilities;
   @Shadow
   public int experience;
   @Shadow
   @Final
   protected String username;
   @Shadow
   public InventoryPlayer inventory;
   @Shadow
   private InventoryEnderChest theInventoryEnderChest;
   @Shadow
   public ChunkCoordinates bed_location;
   @Shadow
   private ChunkCoordinates spawnChunk;
   @Shadow
   private boolean spawnForced;
   @Shadow
   public boolean is_cursed;
   @Shadow
   public float vision_dimming;
   @Shadow
   protected float speedOnGround;

   @Unique
   public boolean feast_trigger_salad = false;
   @Unique
   public boolean feast_trigger_porridge = false;
   @Unique
   public boolean feast_trigger_beef_stew = false;
   @Unique
   public boolean feast_trigger_cereal = false;
   @Unique
   public boolean feast_trigger_chicken_soup = false;
   @Unique
   public boolean feast_trigger_mushroom_soup = false;
   @Unique
   public boolean feast_trigger_cream_mushroom_soup = false;
   @Unique
   public boolean feast_trigger_vegetable_soup = false;
   @Unique
   public boolean feast_trigger_cream_vegetable_soup = false;
   @Unique
   public boolean feast_trigger_ice_cream = false;
   @Unique
   public boolean feast_trigger_chestnut_soup = false;
   @Unique
   public boolean feast_trigger_lemonade = false;
   @Unique
   public boolean feast_trigger_mashed_potatoes = false;
   @Unique
   public boolean feast_trigger_porkchop_stew = false;
   @Unique
   public boolean feast_trigger_pumpkin_soup = false;
   public boolean feast_trigger_sorbet = false;
   @Unique
   public boolean feast_trigger_salmon_soup = false;
   @Unique
   public boolean feast_trigger_beetroot_soup = false;
   @Unique
   private boolean rewarded_disc_damnation = false;
   @Unique
   private boolean rewarded_disc_connected = false;

   @Unique
   @Override
   public boolean isFeast_trigger_salad() {
      return feast_trigger_salad;
   }

   @Unique
   @Override
   public void setFeast_trigger_salad(boolean feast_trigger_salad) {
      this.feast_trigger_salad = feast_trigger_salad;
   }

   @Unique
   @Override
   public boolean isFeast_trigger_porridge() {
      return feast_trigger_porridge;
   }

   @Unique
   @Override
   public void setFeast_trigger_porridge(boolean feast_trigger_porridge) {
      this.feast_trigger_porridge = feast_trigger_porridge;
   }

   @Unique
   @Override
   public boolean isFeast_trigger_beef_stew() {
      return feast_trigger_beef_stew;
   }

   @Unique
   @Override
   public void setFeast_trigger_beef_stew(boolean feast_trigger_beef_stew) {
      this.feast_trigger_beef_stew = feast_trigger_beef_stew;
   }

   @Unique
   @Override
   public boolean isFeast_trigger_cereal() {
      return feast_trigger_cereal;
   }

   @Unique
   @Override
   public void setFeast_trigger_cereal(boolean feast_trigger_cereal) {
      this.feast_trigger_cereal = feast_trigger_cereal;
   }

   @Unique
   @Override
   public boolean isFeast_trigger_chicken_soup() {
      return feast_trigger_chicken_soup;
   }

   @Unique
   @Override
   public void setFeast_trigger_chicken_soup(boolean feast_trigger_chicken_soup) {
      this.feast_trigger_chicken_soup = feast_trigger_chicken_soup;
   }

   @Unique
   @Override
   public boolean isFeast_trigger_mushroom_soup() {
      return feast_trigger_mushroom_soup;
   }

   @Unique
   @Override
   public void setFeast_trigger_mushroom_soup(boolean feast_trigger_mushroom_soup) {
      this.feast_trigger_mushroom_soup = feast_trigger_mushroom_soup;
   }

   @Unique
   @Override
   public boolean isFeast_trigger_cream_mushroom_soup() {
      return feast_trigger_cream_mushroom_soup;
   }

   @Unique
   @Override
   public void setFeast_trigger_cream_mushroom_soup(boolean feast_trigger_cream_mushroom_soup) {
      this.feast_trigger_cream_mushroom_soup = feast_trigger_cream_mushroom_soup;
   }

   @Unique
   @Override
   public boolean isFeast_trigger_vegetable_soup() {
      return feast_trigger_vegetable_soup;
   }

   @Unique
   @Override
   public void setFeast_trigger_vegetable_soup(boolean feast_trigger_vegetable_soup) {
      this.feast_trigger_vegetable_soup = feast_trigger_vegetable_soup;
   }

   @Unique
   @Override
   public boolean isFeast_trigger_cream_vegetable_soup() {
      return feast_trigger_cream_vegetable_soup;
   }

   @Unique
   @Override
   public void setFeast_trigger_cream_vegetable_soup(boolean feast_trigger_cream_vegetable_soup) {
      this.feast_trigger_cream_vegetable_soup = feast_trigger_cream_vegetable_soup;
   }

   @Unique
   @Override
   public boolean isFeast_trigger_ice_cream() {
      return feast_trigger_ice_cream;
   }

   @Unique
   @Override
   public void setFeast_trigger_ice_cream(boolean feast_trigger_ice_cream) {
      this.feast_trigger_ice_cream = feast_trigger_ice_cream;
   }

   @Unique
   @Override
   public boolean isFeast_trigger_chestnut_soup() {
      return feast_trigger_chestnut_soup;
   }

   @Unique
   @Override
   public void setFeast_trigger_chestnut_soup(boolean feast_trigger_chestnut_soup) {
      this.feast_trigger_chestnut_soup = feast_trigger_chestnut_soup;
   }

   @Unique
   @Override
   public boolean isFeast_trigger_mashed_potatoes() {
      return feast_trigger_mashed_potatoes;
   }

   @Unique
   @Override
   public void setFeast_trigger_mashed_potatoes(boolean feast_trigger_mashed_potatoes) {
      this.feast_trigger_mashed_potatoes = feast_trigger_mashed_potatoes;
   }

   @Unique
   @Override
   public boolean isFeast_trigger_lemonade() {
      return feast_trigger_lemonade;
   }

   @Unique
   @Override
   public void setFeast_trigger_lemonade(boolean feast_trigger_lemonade) {
      this.feast_trigger_lemonade = feast_trigger_lemonade;
   }

   @Unique
   @Override
   public boolean isFeast_trigger_porkchop_stew() {
      return feast_trigger_porkchop_stew;
   }

   @Unique
   @Override
   public void setFeast_trigger_porkchop_stew(boolean feast_trigger_porkchop_stew) {
      this.feast_trigger_porkchop_stew = feast_trigger_porkchop_stew;
   }

   @Unique
   @Override
   public boolean isFeast_trigger_pumpkin_soup() {
      return feast_trigger_pumpkin_soup;
   }

   @Unique
   @Override
   public void setFeast_trigger_pumpkin_soup(boolean feast_trigger_pumpkin_soup) {
      this.feast_trigger_pumpkin_soup = feast_trigger_pumpkin_soup;
   }

   @Unique
   @Override
   public boolean isFeast_trigger_sorbet() {
      return feast_trigger_sorbet;
   }

   @Unique
   @Override
   public void setFeast_trigger_sorbet(boolean feast_trigger_sorbet) {
      this.feast_trigger_sorbet = feast_trigger_sorbet;
   }

   @Unique
   @Override
   public boolean isFeast_trigger_salmon_soup() {
      return feast_trigger_salmon_soup;
   }

   @Unique
   @Override
   public void setFeast_trigger_salmon_soup(boolean feast_trigger_salmon_soup) {
      this.feast_trigger_salmon_soup = feast_trigger_salmon_soup;
   }

   @Unique
   @Override
   public boolean isFeast_trigger_beetroot_soup() {
      return feast_trigger_beetroot_soup;
   }

   @Unique
   @Override
   public void setFeast_trigger_beetroot_soup(boolean feast_trigger_beetroot_soup) {
      this.feast_trigger_beetroot_soup = feast_trigger_beetroot_soup;
   }

   @Unique
   @Override
   public int getHunt_counter() {
      return this.hunt_counter;
   }

   @Unique
   @Override
   public void setHunt_counter(int counter) {
      this.hunt_counter = counter;
   }

   @Unique
   @Override
   public int getCurrent_insulin_resistance_lvl() {
      return this.insulin_resistance_level == null ? 0 : this.insulin_resistance_level.ordinal() + 1;
   }

   @Override
   @Unique
   public void setBodyTemperature(float value) {
      this.bodyTemperature = value;
   }

   @Override
   @Unique
   public float getBodyTemperature() {
      return this.bodyTemperature;
   }

   @Overwrite
   protected void jump() {
      if (!(this.getHealth() <= 5.0F) && !(this.capabilities.getWalkSpeed() < 0.05F) && this.hasFoodEnergy() || !(Boolean)ExperimentalConfig.TagConfig.Realistic.ConfigValue) {
         super.jump();
         this.addStat(StatList.jumpStat, 1);
         if (this.getAsPlayer() instanceof EntityClientPlayerMP) {
            this.getAsPlayer().getAsEntityClientPlayerMP().sendPacket((new Packet85SimpleSignal(EnumSignal.increment_stat_for_this_world_only)).setInteger(StatList.jumpStat.statId));
         }
      }

   }

   public boolean isOnLadder() {
      int x;
      int y;
      int z;
      int var0;
      if (!(Boolean)ExperimentalConfig.TagConfig.Realistic.ConfigValue || !(this.getHealth() <= 5.0F) && !(this.capabilities.getWalkSpeed() < 0.05F) && this.hasFoodEnergy()) {
         x = MathHelper.floor_double(this.posX);
         y = MathHelper.floor_double(this.boundingBox.minY);
         z = MathHelper.floor_double(this.posZ);
         var0 = this.worldObj.getBlockId(x, y, z);
         return var0 == Block.ladder.blockID || var0 == Block.vine.blockID;
      } else {
         x = MathHelper.floor_double(this.posX);
         y = MathHelper.floor_double(this.boundingBox.minY);
         z = MathHelper.floor_double(this.posZ);
         var0 = this.worldObj.getBlockId(x, y, z);
         if (var0 != Block.ladder.blockID && var0 != Block.vine.blockID) {
            float yaw = this.rotationYaw % 360.0F;
            if (yaw < -45.0F) {
               yaw += 360.0F;
            }

            int towards = (int)((yaw + 45.0F) % 360.0F) / 90;
            switch (towards) {
               case 0:
                  ++z;
                  break;
               case 1:
                  --x;
                  break;
               case 2:
                  --z;
                  break;
               case 3:
                  ++x;
                  break;
               default:
                  Minecraft.setErrorMessage("isOnLadder: Undefined Facing : " + towards + ".");
            }

            Block var1 = this.worldObj.getBlock(x, y, z);
            Block var2 = this.worldObj.getBlock(x, y + 1, z);
            return this.fallDistance == 0.0F && var1 != null && var1.isSolid(0) && var2 == null || var2 != null && !var2.isSolid(0);
         } else {
            return true;
         }
      }
   }

   @Overwrite
   public void attackTargetEntityWithCurrentItem(Entity target) {
      if (!this.isImmuneByGrace() && target.canAttackWithItem()) {
         boolean critical = this.willDeliverCriticalStrike();
         float damage = this.calcRawMeleeDamageVs(target, critical, this.isSuspendedInLiquid());
         if (damage <= 0.0F) {
            return;
         }

         if (this.isPotionActive(PotionExtend.stunning)) {
            return;
         }

         ItemStack heldItemStack = this.getHeldItemStack();
         int knockback;
         if (EnchantmentHelper.hasEnchantment(heldItemStack, Enchantments.enchantmentDestroying)) {
            knockback = EnchantmentHelper.getEnchantmentLevel(Enchantments.enchantmentDestroying, heldItemStack);
            target.worldObj.createExplosion(this, target.posX, target.posY, target.posZ, 0.0F, (float)knockback * 0.5F, true);
         }

         knockback = 0;
         if (target instanceof EntityLivingBase) {
            knockback += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase)target);
         }

         if (this.isSprinting()) {
            ++knockback;
         }

         boolean was_set_on_fire = false;
         int fire_aspect = EnchantmentHelper.getFireAspectModifier(this);
         if (target instanceof EntityLivingBase && fire_aspect > 0 && !target.isBurning()) {
            was_set_on_fire = true;
            target.setFire(1);
         }

         EntityLivingBase entity_living_base;
         if (this.onServer() && target instanceof EntityLivingBase) {
            entity_living_base = (EntityLivingBase)target;
            ItemStack item_stack_to_drop = entity_living_base.getHeldItemStack();
            if (item_stack_to_drop != null && this.rand.nextFloat() < EnchantmentHelper.getEnchantmentLevelFraction(Enchantment.disarming, this.getHeldItemStack()) && entity_living_base instanceof EntityLiving) {
               EntityLiving entity_living = (EntityLiving)entity_living_base;
               entity_living.dropItemStack(item_stack_to_drop, entity_living.height / 2.0F);
               entity_living.clearMatchingEquipmentSlot(item_stack_to_drop);
               entity_living.ticks_disarmed = 40;
            }
         }

         int stunning;
         if (this.onServer() && target instanceof EntityLivingBase) {
            entity_living_base = (EntityLivingBase)target;
            ItemStack[] item_stack_to_drop = entity_living_base.getWornItems();
            stunning = this.rand.nextInt(item_stack_to_drop.length);
            if (item_stack_to_drop[stunning] != null && this.rand.nextFloat() < EnchantmentHelper.getEnchantmentLevelFraction(Enchantments.enchantmentThresher, this.getHeldItemStack()) && entity_living_base instanceof EntityLiving entity_living) {
                entity_living.dropItemStack(item_stack_to_drop[stunning], entity_living.height / 2.0F);
               entity_living.clearMatchingEquipmentSlot(item_stack_to_drop[stunning]);
               entity_living.ticks_disarmed = 40;
            }
         }

         EntityDamageResult result = target.attackEntityFrom(new Damage(DamageSource.causePlayerDamage(ReflectHelper.dyCast(this)).setFireAspect(fire_aspect > 0), damage));
         boolean target_was_harmed = result != null && result.entityWasNegativelyAffected();
         target.onMeleeAttacked(this, result);
         if (target_was_harmed) {
            if (target instanceof EntityLivingBase) {
               stunning = EnchantmentHelper.getStunModifier(this, (EntityLivingBase)target);
               if ((double)stunning > Math.random() * 5.0) {
                  ((EntityLivingBase)target).addPotionEffect(new PotionEffect(PotionExtend.stunning.id, stunning * 60, 0));
               }

               this.heal((float)EnchantmentHelper.getVampiricTransfer(this, (EntityLivingBase)target, damage), EnumEntityFX.vampiric_gain);
               if (EnchantmentHelper.hasEnchantment(heldItemStack, Enchantments.enchantmentSweeping)) {
                  List targets = this.getNearbyEntities(5.0F, 5.0F);
                  this.attackMonsters(targets, damage * EnchantmentHelper.getEnchantmentLevelFraction(Enchantments.enchantmentSweeping, heldItemStack));
               }
            }

            if (knockback > 0) {
               target.addVelocity((double)(-MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F) * (float)knockback * 0.5F), 0.1, (double)(MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F) * (float)knockback * 0.5F));
               this.motionX *= 0.6;
               this.motionZ *= 0.6;
               this.setSprinting(false);
            }

            if (critical) {
               this.onCriticalHit(target);
            }

            if (target instanceof EntityLivingBase && EnchantmentDamage.getDamageModifiers(this.getHeldItemStack(), (EntityLivingBase)target) > 0.0F) {
               this.onEnchantmentCritical(target);
            }

            if (damage >= 40.0F) {
               this.triggerAchievement(AchievementList.overkill);
            }

            this.setLastAttackTarget(target);
            if (target instanceof EntityLivingBase) {
               if (this.worldObj.isRemote) {
                  System.out.println("EntityPlayer.attackTargetEntityWithCurrentItem() is calling EnchantmentThorns.func_92096_a() on client");
                  Minecraft.temp_debug = "player";
               }

               EnchantmentThorns.func_92096_a(this, (EntityLivingBase)target, this.rand);
            }
         }

         ItemStack held_item_stack = this.getHeldItemStack();
         Object var10 = target;
         if (target instanceof EntityDragonPart) {
            IEntityMultiPart var11 = ((EntityDragonPart)target).entityDragonObj;
            if (var11 instanceof EntityLivingBase) {
               var10 = var11;
            }
         }

         if (target_was_harmed && held_item_stack != null && var10 instanceof EntityLivingBase) {
            held_item_stack.hitEntity((EntityLivingBase)var10, (EntityPlayer)ReflectHelper.dyCast(this));
         }

         if (target instanceof EntityLivingBase) {
            this.addStat(StatList.damageDealtStat, Math.round(damage * 10.0F));
            if (fire_aspect > 0 && target_was_harmed) {
               target.setFire(fire_aspect * 4);
            } else if (was_set_on_fire) {
               target.extinguish();
            }
         }

         if (this.onServer()) {
            this.addHungerServerSide(0.3F * EnchantmentHelper.getEnduranceModifier(this));
         }
      }

   }

   @Inject(
      method = "onDeath(Lnet/minecraft/DamageSource;)V",
      at = @At("HEAD")
   )
   public void onDeath(DamageSource par1DamageSource, CallbackInfo callbackInfo) {
      if (!this.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {
         this.inventory.vanishingItems();
      }

   }

   @Override
   @Unique
   public int getWater() {
      return this.foodStats.getWater();
   }

   @Override
   @Unique
   public int addWater(int water) {
      return this.foodStats.addWater(water);
   }

   @Unique
   public void decreaseWaterServerSide(float hungerWater) {
      if (!this.capabilities.isCreativeMode && !this.capabilities.disableDamage) {
         ((FoodStatsKt) this.foodStats).decreaseWaterServerSide(hungerWater);
      }

   }

   @Overwrite
   public boolean isStarving() {
      return this.getNutrition() == 0;
   }

   public boolean DuringDehydration() {
      return this.getWater() == 0;
   }

   @Overwrite
   public boolean hasFoodEnergy() {
      return this.getSatiation() + this.getNutrition() != 0 && this.getWater() != 0;
   }

   public boolean UnderArrogance() {
      boolean Hel_Arro = false;
      boolean Cst_Arro = false;
      boolean Lgs_Arro = false;
      boolean Bts_Arro = false;
      boolean Hnd_Arro = false;
      ItemStack Helmet = this.getHelmet();
      ItemStack Cuirass = this.getCuirass();
      ItemStack Leggings = this.getLeggings();
      ItemStack Boots = this.getBoots();
      ItemStack Holding = this.getHeldItemStack();
      if (Helmet != null) {
         Hel_Arro = EnchantmentHelper.hasEnchantment(Helmet, Enchantments.enchantmentArrogance);
      }

      if (Cuirass != null) {
         Cst_Arro = EnchantmentHelper.hasEnchantment(Cuirass, Enchantments.enchantmentArrogance);
      }

      if (Leggings != null) {
         Lgs_Arro = EnchantmentHelper.hasEnchantment(Leggings, Enchantments.enchantmentArrogance);
      }

      if (Boots != null) {
         Bts_Arro = EnchantmentHelper.hasEnchantment(Boots, Enchantments.enchantmentArrogance);
      }

      if (Holding != null) {
         Hnd_Arro = EnchantmentHelper.hasEnchantment(Holding, Enchantments.enchantmentArrogance);
      }

      boolean Arro = Hel_Arro || Cst_Arro || Lgs_Arro || Bts_Arro || Hnd_Arro;
      return this.experience < 2300 && Arro;
   }

   public int getTemperatureOutcome(float env_temperature, long daytime) {
      ItemStack wearingItemStack = this.getCuirass();
      daytime %= 24000L;
      float best_temp = 1.0F;
      if (EnchantmentHelper.hasEnchantment(wearingItemStack, Enchantments.enchantmentCallofAntarctic)) {
         best_temp += 0.8F;
      }

      if (EnchantmentHelper.hasEnchantment(wearingItemStack, Enchantments.enchantmentCallofNether)) {
         best_temp -= 0.6F;
      }

      best_temp -= (float)(this.getReduce_weight() / 3) * 0.05F;
      if (this.getWorld().isOverworld()) {
         env_temperature -= daytime > 0L && daytime < 12000L ? 0.0F : 0.1F;
         if (((WorldKt) this.getWorld()).getWorldSeason() == 3) {
            env_temperature -= 0.6F;
         }
      }

      env_temperature += 0.2F * ((96.0F - (float)this.getBlockPosY()) / 256.0F);
      if (env_temperature >= best_temp + 0.3F) {
         return 192000;
      } else {
         return env_temperature <= best_temp - 0.3F ? 0 : 96000;
      }
   }

   @Override
   @Unique
   public String getEnvironmentTemperature(float base_temperature, long daytime) {
      float env_temperature = base_temperature;
      if (this.getWorld().isOverworld()) {
         env_temperature -= daytime > 0L && daytime < 12000L ? 0.0F : 0.1F;
         if (((WorldKt) this.getWorld()).getWorldSeason() == 3) {
            env_temperature -= 0.6F;
         }
      }

      env_temperature += 0.2F * ((96.0F - (float)this.getBlockPosY()) / 256.0F);
      return StringHelper.formatFloat(-4.0F + 30.0F * env_temperature, 1, 1);
   }

   @Unique
   @Override
   public String[] getTemperatureTolerance() {
      ItemStack wearingItemStack = this.getCuirass();
      float best_temp = 1.0F;
      if (EnchantmentHelper.hasEnchantment(wearingItemStack, Enchantments.enchantmentCallofAntarctic)) {
         best_temp += 0.8F;
      }

      if (EnchantmentHelper.hasEnchantment(wearingItemStack, Enchantments.enchantmentCallofNether)) {
         best_temp -= 0.6F;
      }

      best_temp -= (float)(this.getReduce_weight() / 3) * 0.05F;
      String highest_temp = StringHelper.formatFloat((best_temp + 0.3F) * 30.0F - 4.0F, 1, 1);
      String lowest_temp = StringHelper.formatFloat((best_temp - 0.3F) * 30.0F - 4.0F, 1, 1);
      return new String[]{lowest_temp, highest_temp};
   }

   public void modifyTemperature(int tp) {
      this.temperaturePoint += tp;
   }

   public int getTemperature() {
      return this.temperaturePoint;
   }

   public boolean willRepair(ItemStack holding) {
      return EnchantmentHelper.hasEnchantment(holding, Enchantments.enchantmentMending);
   }

   private void activeNegativeUndying() {
      this.clearActivePotions();
      this.setHealth(this.getMaxHealth(), true, this.getHealFX());
      this.entityFX(EnumEntityFX.smoke_and_steam);
      this.makeSound("imported.random.totem_use", 3.0F, 1.0F + this.rand.nextFloat() * 0.1F);
      this.addPotionEffect(new PotionEffect(Potion.blindness.id, 40, 4));
      this.vision_dimming += 0.75F;
      this.triggerAchievement(AchievementExtend.cheatdeath);
   }


   protected void checkForAfterDamage(Damage damage, EntityDamageResult result) {
      if (result.entityWasDestroyed()) {
         ItemStack var5 = this.getHeldItemStack();
         if (var5 != null && var5.getItem() instanceof ItemTotem) {
            result.entity_was_destroyed = false;
            ((ItemTotem) var5.getItem()).performNegativeEffect(this.getAsPlayer());
         }
         if (hunt_counter > 0){
            result.entity_was_destroyed = false;
            this.setHealth(1);
         }
      }
   }


   @Redirect(method = "attackEntityFrom",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/EntityLivingBase;attackEntityFrom(Lnet/minecraft/Damage;)Lnet/minecraft/EntityDamageResult;"))
   private EntityDamageResult redirectEntityAttack(EntityLivingBase instance, Damage damage){
      EntityDamageResult entityDamageResult = super.attackEntityFrom(damage);

      if (entityDamageResult != null && (double)this.getHealthFraction() <= 0.1D && !entityDamageResult.entityWasDestroyed()) {
         ItemStack var5 = this.getHeldItemStack();
         if (var5 != null && var5.getItem() instanceof ItemTotem) {
            entityDamageResult.entity_was_destroyed = false;
            this.activeNegativeUndying();
            this.setHeldItemStack(null);
         }
      }
      return entityDamageResult;
   }


   public int getReduce_weight() {
      int Weight = 0;
      if (this.getHelmet() != null && this.getHelmet().itemID == Items.HellhoundHelmet.itemID) {
         Weight -= 14;
      }

      if (this.getCuirass() != null && this.getCuirass().itemID == Items.HellhoundChestplate.itemID) {
         Weight -= 22;
      }

      if (this.getLeggings() != null && this.getLeggings().itemID == Items.HellhoundLeggings.itemID) {
         Weight -= 19;
      }

      if (this.getBoots() != null && this.getBoots().itemID == Items.HellhoundBoots.itemID) {
         Weight -= 11;
      }

      if (this.getHelmet() != null && this.getHelmet().itemID == Items.WolfHelmet.itemID) {
         Weight += 5;
      }

      if (this.getCuirass() != null && this.getCuirass().itemID == Items.WolfChestplate.itemID) {
         Weight += 8;
      }

      if (this.getLeggings() != null && this.getLeggings().itemID == Items.WolfLeggings.itemID) {
         Weight += 7;
      }

      if (this.getBoots() != null && this.getBoots().itemID == Items.WolfBoots.itemID) {
         Weight += 4;
      }

      if (this.getHelmet() != null && this.getHelmet().itemID == Item.helmetLeather.itemID) {
         Weight += 3;
      }

      if (this.getCuirass() != null && this.getCuirass().itemID == Item.plateLeather.itemID) {
         Weight += 4;
      }

      if (this.getLeggings() != null && this.getLeggings().itemID == Item.legsLeather.itemID) {
         Weight += 3;
      }

      if (this.getBoots() != null && this.getBoots().itemID == Item.bootsLeather.itemID) {
         Weight += 2;
      }

      if (this.getHelmet() != null) {
         Weight += 5;
      }

      if (this.getCuirass() != null) {
         Weight += 8;
      }

      if (this.getLeggings() != null) {
         Weight += 7;
      }

      if (this.getBoots() != null) {
         Weight += 4;
      }

      return Weight;
   }

   private Material getDetectorMaterial() {
      if (this.inventory.getHotbarSlotContainItem(Items.detectorEmerald) > 0) {
         return Material.emerald;
      } else {
         return this.inventory.getHotbarSlotContainItem(Items.detectorDiamond) > 0 ? Material.diamond : null;
      }
   }

   private void detectNearbyMobs(Material material) {
      if (material != null) {
         if (this.detectorDelay < 80) {
            ++this.detectorDelay;
         } else {
            this.detectorDelay = 0;
            List targets = this.getNearbyEntities(material.durability * 2.0F, material.durability);
            float range_div = Math.min(2.0F, material.durability * 2.5F / this.detectNearestMonstersDistance(targets));
            if (range_div > 0.0F) {
               this.makeSound("imported.random.warning", 0.3F, 1.0F + range_div);
               this.detectorDelay += (int)(38.0F * range_div);
            }
         }

      }
   }

   private void produceManure() {
      if (this.isPotionActive(PotionExtend.dehydration) && this.diarrheaCounter <= 1200) {
         ++this.diarrheaCounter;
      } else if (this.diarrheaCounter > 0) {
         --this.diarrheaCounter;
      }

      if (this.diarrheaCounter >= 1200) {
         this.dropItem(Item.manure);
         this.triggerAchievement(AchievementExtend.pull);
         this.diarrheaCounter = 0;
      }

   }

   private void tryEnableSacrificing() {
      if (this.hunt_counter > (this.hunt_cap ? -1 : 0)) {
         --this.hunt_counter;
      }

      if (this.hunt_counter % 80 == 79) {
         EntityUndeadGuard Belongings = new EntityUndeadGuard(this.worldObj);
         Belongings.setPosition(this.posX, this.posY, this.posZ);
         Belongings.refreshDespawnCounter(-9600);
         this.worldObj.spawnEntityInWorld(Belongings);
         Belongings.onSpawnWithEgg((EntityLivingData)null);
         Belongings.entityFX(EnumEntityFX.summoned);
      }

      if (this.hunt_counter < 0) {
         this.attackEntityFrom(new Damage(DamageSourceExtend.sacrificed, 10000.0F));
         this.hunt_counter = 0;
         this.hunt_cap = false;
      }

   }

   private void triggeringCurseExtend() {
      float light_modifier;
      if (this.hasCurse(CurseExtend.fear_of_light)) {
         light_modifier = (float)(18 - this.worldObj.getBlockLightValue(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY + (double)this.yOffset), MathHelper.floor_double(this.posZ))) / 15.0F;
          if (light_modifier < 0.5F) {
              this.hasCurse(CurseExtend.fear_of_light, true);
          }
      }

      if (this.hasCurse(CurseExtend.fear_of_darkness)) {
         light_modifier = (float)(this.worldObj.getBlockLightValue(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY + (double)this.yOffset), MathHelper.floor_double(this.posZ)) + 3) / 15.0F;
          if (light_modifier < 0.5F) {
              this.hasCurse(CurseExtend.fear_of_darkness, true);
          }
      }

   }

   private void tryDrinkWater() {
      BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(this.getBlockPosX(), this.getBlockPosZ());
      if (this.getBlockAtFeet() != null && this.getBlockAtFeet().blockMaterial == Material.water && this.isSneaking()) {
         ++this.water_duration;
      } else {
         this.water_duration = 0;
      }

      if (this.water_duration > 160) {
         this.water_duration = 0;
         if (biome != BiomeGenBase.swampRiver && biome != BiomeGenBase.swampland) {
            if (biome != BiomeGenBase.river && biome != BiomeGenBase.desertRiver) {
               ((FoodStatsKt) this.foodStats).addWater(1);
               this.addPotionEffect(new PotionEffect(PotionExtend.dehydration.id, 160, 0));
            } else {
               ((FoodStatsKt) this.foodStats).addWater(2);
            }
         } else {
            ((FoodStatsKt) this.foodStats).addWater(1);
            this.addPotionEffect(new PotionEffect(Potion.poison.id, 450, 0));
         }

         this.triggerAchievement(AchievementExtend.mashedCactus);
      }

   }

   private void handleDecreaseWater() {
      BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(this.getBlockPosX(), this.getBlockPosZ());
      this.dry_resist += ((Boolean)StuckTagConfig.TagConfig.TagHeatStroke.ConfigValue ? 2.0 : 1.0) + (double)biome.getFloatTemperature();
      if (this.isPotionActive(PotionExtend.dehydration)) {
         this.dry_resist += Math.min(80.0, (double)(this.getActivePotionEffect(PotionExtend.dehydration).getAmplifier() + 1) * 20.0);
      }

      if (this.isPotionActive(PotionExtend.heat_stroke)) {
         this.dry_resist += Math.min(80.0, (double)(this.getActivePotionEffect(PotionExtend.heat_stroke).getAmplifier() + 1) * 5.0);
      }

      if (this.isPotionActive(PotionExtend.thirsty)) {
         this.dry_resist += Math.min(80.0, (double)(this.getActivePotionEffect(PotionExtend.thirsty).getAmplifier() + 1) * 10.0);
      }

      if (this.dry_resist > 12800.0) {
         ((FoodStatsKt) this.foodStats).addWater(-1);
         this.dry_resist = 0.0;
      }

   }

   @Unique
   private void handleDecreaseDrunkDuration() {
      if (this.Hasdrunked) {
         this.drunk_duration = 6000;
         this.Hasdrunked = false;
      }

      if (this.drunk_duration > 0) {
         --this.drunk_duration;
      }

   }

   private void handleModifyBodyTemperature() {
      BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(this.getBlockPosX(), this.getBlockPosZ());
      int targetTemp = this.getTemperatureOutcome(biome.temperature, this.getWorld().getTotalWorldTime());
      if (!this.isOutdoors() && this.getWorld().isOverworld()) {
         targetTemp = MathHelper.clamp_int(targetTemp, 84000, 128000);
      }

      int heatunit;
      for(heatunit = 0; heatunit <= ((Boolean)StuckTagConfig.TagConfig.TagTempSensitivity.ConfigValue ? 1 : 0); ++heatunit) {
         if (this.temperaturePoint < targetTemp) {
            ++this.temperaturePoint;
         } else if (this.temperaturePoint > targetTemp) {
            if (this.drunk_duration > 0) {
               --this.temperaturePoint;
            }

            --this.temperaturePoint;
         }
      }

      if (this.isInFire()) {
         this.temperaturePoint += 100;
      }

      if (this.worldObj.getSavedLightValue(EnumSkyBlock.Block, this.getBlockPosX(), this.getBlockPosY() + 1, this.getBlockPosZ()) >= 14 && this.temperaturePoint < Math.max(targetTemp, 104000)) {
         this.temperaturePoint += 25;
         this.removePotionEffect(PotionExtend.freeze.id);
      }

      if (this.isInWater() && this.temperaturePoint > Math.min(88000, targetTemp)) {
         this.temperaturePoint -= 25;
         this.removePotionEffect(PotionExtend.heat_stroke.id);
      }

      this.bodyTemperature = 37.2F + (float)(1.25E-4 * (double)(this.temperaturePoint - 96000));
      int heatlevel;
      if (this.temperaturePoint < 84000) {
         this.removePotionEffect(PotionExtend.heat_stroke.id);
         heatunit = 96000 - this.temperaturePoint;
         heatlevel = Math.max(heatunit / 12000, 0);
         if (heatlevel >= 4) {
            ++this.FreezingWarning;
            this.triggerAchievement(AchievementExtend.hypothermia);
         }

         if (this.FreezingWarning > 500) {
            this.attackEntityFrom(new Damage(DamageSourceExtend.freeze, 4.0F));
            this.FreezingWarning = 0;
         }

         this.addPotionEffect(new PotionEffect(PotionExtend.freeze.id, heatunit, this.isInRain() ? heatlevel : heatlevel - 1));
      }

      if (this.temperaturePoint > 108000) {
         this.removePotionEffect(PotionExtend.freeze.id);
         heatunit = this.temperaturePoint - 96000;
         heatlevel = Math.max(heatunit / 12000, 0);
         if (heatlevel >= 2) {
            this.triggerAchievement(AchievementExtend.onburnt);
         }

         this.addPotionEffect(new PotionEffect(PotionExtend.heat_stroke.id, heatunit, heatlevel - 1));
      }

   }

   private void handleArroganceUpdate() {
      if (this.UnderArrogance()) {
         this.addPotionEffect(new PotionEffect(Potion.wither.id, 100, 1));
      }

   }

   private void handleVisionDimmingUpdate() {
      if (Minecraft.inDevMode() && this.vision_dimming > 0.1F && this.isPlayerInCreative()) {
         this.vision_dimming = 0.05F;
      }

      if (this.getHealth() < 5.0F && (Boolean)ExperimentalConfig.TagConfig.Realistic.ConfigValue) {
         this.vision_dimming = Math.max(this.vision_dimming, 1.0F - this.getHealthFraction());
      }

   }

   private void mendToolsUsingEnchantment() {
      ItemStack holding = this.getHeldItemStack();
      if (holding != null && this.willRepair(holding) && (float)holding.getRemainingDurability() / (float)holding.getMaxDamage() < 0.5F && this.getExperienceLevel() >= 10 + 15 * holding.getItem().getHardestMetalMaterial().min_harvest_level) {
         this.addExperience(-holding.getMaxDamage() / 32, false, true);
         holding.setItemDamage(holding.getItemDamage() - holding.getMaxDamage() / 8);
      }

      ItemStack[] item_stack_to_repair = this.getWornItems();

      for(int n = 0; n < item_stack_to_repair.length; ++n) {
         if (item_stack_to_repair[n] != null && this.willRepair(item_stack_to_repair[n]) && (float)item_stack_to_repair[n].getRemainingDurability() / (float)item_stack_to_repair[n].getMaxDamage() < 0.5F && this.getExperienceLevel() >= 10 + 15 * item_stack_to_repair[n].getItem().getHardestMetalMaterial().min_harvest_level) {
            this.addExperience(-50, false, true);
            item_stack_to_repair[n].setItemDamage(item_stack_to_repair[n].getItemDamage() - 1);
         }
      }

   }

   private void tryTriggerAchievement() {
      EntityItem RewardingRecord;
      if (this.feast_trigger_sorbet && this.feast_trigger_cereal && this.feast_trigger_chestnut_soup && this.feast_trigger_chicken_soup && this.feast_trigger_beef_stew
              && this.feast_trigger_cream_mushroom_soup && this.feast_trigger_cream_vegetable_soup && this.feast_trigger_ice_cream
              && this.feast_trigger_lemonade && this.feast_trigger_mashed_potatoes && this.feast_trigger_porkchop_stew && this.feast_trigger_salad
              && this.feast_trigger_pumpkin_soup && this.feast_trigger_porridge && this.feast_trigger_mushroom_soup && this.feast_trigger_vegetable_soup
              && this.feast_trigger_salmon_soup && this.feast_trigger_beetroot_soup && !this.rewarded_disc_damnation) {
         this.triggerAchievement(AchievementExtend.feast);
         this.addExperience(2500);
         this.rewarded_disc_damnation = true;
         RewardingRecord = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(Items.recordDamnation.itemID, 1));
         this.worldObj.spawnEntityInWorld(RewardingRecord);
         RewardingRecord.entityFX(EnumEntityFX.summoned);
      }

      if (this.isPotionActive(Potion.moveSpeed) && this.isPotionActive(Potion.regeneration) && this.isPotionActive(Potion.fireResistance) && this.isPotionActive(Potion.nightVision) && this.isPotionActive(Potion.damageBoost) && this.isPotionActive(Potion.resistance) && this.isPotionActive(Potion.invisibility) && !this.rewarded_disc_connected) {
         this.triggerAchievement(AchievementExtend.invincible);
         this.addExperience(2500);
         this.rewarded_disc_connected = true;
         RewardingRecord = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(Items.recordConnected.itemID, 1));
         this.worldObj.spawnEntityInWorld(RewardingRecord);
         RewardingRecord.entityFX(EnumEntityFX.summoned);
      }

   }

   @Inject(
      method = {"onLivingUpdate()V"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/EntityLivingBase;onLivingUpdate()V",
   shift = Shift.AFTER
)}
   )
   private void injectTick(CallbackInfo c) {
      if (!this.worldObj.isRemote) {
         this.detectNearbyMobs(this.getDetectorMaterial());
         this.produceManure();
         this.triggeringCurseExtend();
         this.tryEnableSacrificing();
         this.tryDrinkWater();
         this.handleDecreaseWater();
         this.handleDecreaseDrunkDuration();
         this.handleModifyBodyTemperature();
         this.handleVisionDimmingUpdate();
         this.handleArroganceUpdate();
         this.mendToolsUsingEnchantment();
      }

      this.tryTriggerAchievement();
   }

   @Unique
   @Override
   public int getFreezingCooldown() {
      return this.FreezingCooldown;
   }

   @Override
   @Unique
   public void setFreezingCooldown(int iptfreezingCooldown) {
      this.FreezingCooldown = iptfreezingCooldown;
   }

   @Unique
   @Override
   public void addFreezingCooldown(int dummy) {
      if (this.FreezingCooldown + dummy < 0) {
         this.FreezingCooldown = 0;
      } else {
         this.FreezingCooldown += dummy;
      }

   }

   @Unique
   @Override
   public float getCurrentBiomeTemperature() {
      BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(this.getBlockPosX(), this.getBlockPosZ());
      return biome.getFloatTemperature();
   }

   @Inject(
      method = {"addExperience(IZZ)V"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/FoodStats;setNutrition(IZ)V",
   ordinal = 0
)}
   )
   private void updateWater(int amount, boolean suppress_healing, boolean suppress_sound, CallbackInfo ci) {
      this.addWater(0);
   }

   @ModifyVariable(
      method = {"addExperience(IZZ)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/EntityPlayer;addScore(I)V",
   ordinal = 0
),
      ordinal = 0
   )
   private int performMending(int par00000, int amount, boolean suppress_healing, boolean suppress_sound) {
      ItemStack holding = this.getHeldItemStack();
      if (holding != null && this.willRepair(holding)) {
         while(this.getHeldItemStack().getItemDamage() >= 4 && amount > 0) {
            this.getHeldItemStack().setItemDamage(holding.getItemDamage() - 4);
            --amount;
         }
      }

      return amount;
   }

   @Shadow
   public void addExperience(int amount, boolean suppress_healing, boolean suppress_sound) {
   }

   public EntityPlayerMixin(World par1World) {
      super(par1World);
   }


   @Unique
   @Override
   public void displayGUIEnchantReserver(int x, int y, int z, EnchantReserverSlots slots) {
   }

   @Overwrite
   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
      this.diarrheaCounter = par1NBTTagCompound.getInteger("diarrheaCounter");
      this.hunt_cap = par1NBTTagCompound.getBoolean("UsedTotemOfHunt");
      this.hunt_counter = par1NBTTagCompound.getInteger("TotemDyingCounter");
      this.isNewPlayer = par1NBTTagCompound.getBoolean("isNewPlayer");
      this.FreezingCooldown = par1NBTTagCompound.getInteger("FreezingCooldown");
      this.FreezingWarning = par1NBTTagCompound.getInteger("FreezingWarning");
      this.temperaturePoint = par1NBTTagCompound.getInteger("Temperature");
      this.drunk_duration = par1NBTTagCompound.getInteger("DrunkDuration");
      this.HeatResistance = par1NBTTagCompound.getInteger("HeatResistance");
      this.experience = par1NBTTagCompound.getInteger("experience");
      if (par1NBTTagCompound.hasKey("XpTotal")) {
         this.experience = par1NBTTagCompound.getInteger("XpTotal");
      }

      super.readEntityFromNBT(par1NBTTagCompound);
      NBTTagList var2 = par1NBTTagCompound.getTagList("Inventory");
      this.inventory.readFromNBT(var2);
      this.inventory.currentItem = par1NBTTagCompound.getInteger("SelectedItemSlot");
      this.setScore(par1NBTTagCompound.getInteger("Score"));
      if (this.inBed()) {
         this.bed_location = new ChunkCoordinates(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
         this.wakeUpPlayer(true, (Entity)null);
      }

      if (par1NBTTagCompound.hasKey("SpawnX") && par1NBTTagCompound.hasKey("SpawnY") && par1NBTTagCompound.hasKey("SpawnZ")) {
         this.spawnChunk = new ChunkCoordinates(par1NBTTagCompound.getInteger("SpawnX"), par1NBTTagCompound.getInteger("SpawnY"), par1NBTTagCompound.getInteger("SpawnZ"));
         this.spawnForced = par1NBTTagCompound.getBoolean("SpawnForced");
      }

      this.foodStats.readNBT(par1NBTTagCompound);
      this.capabilities.readCapabilitiesFromNBT(par1NBTTagCompound);
      if (par1NBTTagCompound.hasKey("EnderItems")) {
         NBTTagList var3 = par1NBTTagCompound.getTagList("EnderItems");
         this.theInventoryEnderChest.loadInventoryFromNBT(var3);
      }

      this.vision_dimming = par1NBTTagCompound.getFloat("vision_dimming");
   }

   @Overwrite
   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
      par1NBTTagCompound.setInteger("diarrheaCounter", this.diarrheaCounter);
      par1NBTTagCompound.setBoolean("UsedTotemOfHunt", this.hunt_cap);
      par1NBTTagCompound.setInteger("TotemDyingCounter", this.hunt_counter);
      par1NBTTagCompound.setBoolean("isNewPlayer", this.isNewPlayer);
      par1NBTTagCompound.setInteger("FreezingCooldown", this.FreezingCooldown);
      par1NBTTagCompound.setInteger("FreezingWarning", this.FreezingWarning);
      par1NBTTagCompound.setInteger("Temperature", this.temperaturePoint);
      par1NBTTagCompound.setInteger("DrunkDuration", this.drunk_duration);
      par1NBTTagCompound.setInteger("HeatResistance", this.HeatResistance);
      par1NBTTagCompound.setInteger("experience", this.experience);
      super.writeEntityToNBT(par1NBTTagCompound);
      par1NBTTagCompound.setTag("Inventory", this.inventory.writeToNBT(new NBTTagList()));
      par1NBTTagCompound.setInteger("SelectedItemSlot", this.inventory.currentItem);
      par1NBTTagCompound.setInteger("Score", this.getScore());
      if (this.spawnChunk != null) {
         par1NBTTagCompound.setInteger("SpawnX", this.spawnChunk.posX);
         par1NBTTagCompound.setInteger("SpawnY", this.spawnChunk.posY);
         par1NBTTagCompound.setInteger("SpawnZ", this.spawnChunk.posZ);
         par1NBTTagCompound.setBoolean("SpawnForced", this.spawnForced);
      }

      this.foodStats.writeNBT(par1NBTTagCompound);
      this.capabilities.writeCapabilitiesToNBT(par1NBTTagCompound);
      par1NBTTagCompound.setTag("EnderItems", this.theInventoryEnderChest.saveInventoryToNBT());
      par1NBTTagCompound.setFloat("vision_dimming", this.vision_dimming);
   }

   @Overwrite
   public boolean canEatAndDrink() {
      if (this.isInsideOfMaterial(Material.water) && !this.canBreatheUnderwater()) {
         return false;
      } else if (this.isPotionActive(Potion.confusion)) {
         return false;
      } else {
         return this.isPotionActive(PotionExtend.heat_stroke) ? this.isPotionActive(Potion.fireResistance) : true;
      }
   }

   @Overwrite
   private void checkForArmorAchievements() {
      boolean wearing_leather = false;
      boolean wearing_full_suit_plate = true;
      boolean wearing_full_suit_adamantium_plate = true;
      boolean wearing_full_suit_wolf_fur = true;
      boolean wearing_full_suit_hellhound_fur = true;

      for(int i = 0; i < 4; ++i) {
         if (this.inventory.armorInventory[i] != null && this.inventory.armorInventory[i].getItem() instanceof ItemArmor) {
            ItemArmor armor = (ItemArmor)this.inventory.armorInventory[i].getItem();
            Material material = armor.getArmorMaterial();
            if (material == Material.leather) {
               wearing_leather = true;
            }

            if (material != Material.copper && material != Material.silver && material != Material.gold && material != Material.iron && material != Material.mithril && material != Material.adamantium && material != Material.ancient_metal && material != Materials.tungsten && material != Materials.nickel && material != Materials.ancient_metal_sacred && material != Materials.uru) {
               wearing_full_suit_plate = false;
            }

            if (material != Material.adamantium) {
               wearing_full_suit_adamantium_plate = false;
            }

            if (material != Materials.wolf_fur || !armor.isChainMail()) {
               wearing_full_suit_wolf_fur = false;
            }

            if (material != Materials.wolf_fur || armor.isChainMail()) {
               wearing_full_suit_hellhound_fur = false;
            }
         } else {
            wearing_full_suit_hellhound_fur = false;
            wearing_full_suit_plate = false;
            wearing_full_suit_adamantium_plate = false;
            wearing_full_suit_wolf_fur = false;
         }
      }

      if (wearing_leather) {
         this.triggerAchievement(AchievementList.wearLeather);
      }

      if (wearing_full_suit_plate) {
         this.triggerAchievement(AchievementList.wearAllPlateArmor);
      }

      if (wearing_full_suit_adamantium_plate) {
         this.triggerAchievement(AchievementList.wearAllAdamantiumPlateArmor);
      }

      if (wearing_full_suit_wolf_fur) {
         this.triggerAchievement(AchievementExtend.BravetheCold);
      }

      if (wearing_full_suit_hellhound_fur) {
         this.triggerAchievement(AchievementExtend.mindCools);
      }

   }

   public float getNickelArmorCoverage() {
      float coverage = 0.0F;
      ItemStack[] worn_items = this.getWornItems();

      for(int i = 0; i < worn_items.length; ++i) {
         ItemStack item_stack = worn_items[i];
         if (item_stack != null) {
            if (item_stack.isArmor()) {
               ItemArmor barding = item_stack.getItem().getAsArmor();
               if (barding.getArmorMaterial() == Materials.nickel) {
                  coverage += barding.getCoverage() * barding.getDamageFactor(item_stack, this);
               }
            } else if (item_stack.getItem() instanceof ItemHorseArmor) {
               ItemHorseArmor var6 = (ItemHorseArmor)item_stack.getItem();
               if (var6.getArmorMaterial() == Materials.nickel) {
                  coverage += var6.getCoverage();
               }
            }
         }
      }

      return coverage;
   }

   @Overwrite
   public static int getHealthLimit(int level) {
      int HealthLMTwithoutTag = Math.max(Math.min(6 + level / 5 * 2, 20), 6);
      int HealthLMTwithTag;
      if (level <= 35) {
         HealthLMTwithTag = HealthLMTwithoutTag;
      } else {
         HealthLMTwithTag = Math.max(Math.min(14 + level / 10 * 2, 40), 20);
      }

      return (Boolean)StuckTagConfig.TagConfig.TagDistortion.ConfigValue ? HealthLMTwithTag : HealthLMTwithoutTag;
   }

   @Overwrite
   public float getCurrentPlayerStrVsBlock(int x, int y, int z, boolean apply_held_item) {
      Block block = Block.blocksList[this.worldObj.getBlockId(x, y, z)];
      if (block == null) {
         return 0.0F;
      } else {
         float block_hardness = this.worldObj.getBlockHardness(x, y, z);
         if (block_hardness == 0.0F) {
            return 1.0F;
         } else {
            float min_str_vs_block = -3.4028235E38F;
            Item held_item = this.getHeldItem();
            ItemStack held_item_stack = this.getHeldItemStack();
            float str_vs_block;
            int ordinal;
            if (block.isPortable(this.worldObj, this, x, y, z)) {
               str_vs_block = min_str_vs_block = 4.0F * block_hardness;
            } else if (apply_held_item && held_item != null) {
               ordinal = this.worldObj.getBlockMetadata(x, y, z);
               str_vs_block = held_item.getStrVsBlock(block, ordinal);
               if (str_vs_block < 1.0F) {
                  return this.getCurrentPlayerStrVsBlock(x, y, z, false);
               }

               int var4 = EnchantmentHelper.getEfficiencyModifier(this);
               if (var4 > 0) {
                  float var6 = (float)(var4 * var4 + 1);
                  str_vs_block += var6;
               }
            } else {
               ordinal = this.worldObj.getBlockMetadata(x, y, z);
               if (block.blockMaterial.requiresTool(block, ordinal)) {
                  str_vs_block = 0.0F;
               } else {
                  str_vs_block = 1.0F;
               }
            }

            if (apply_held_item && held_item_stack != null) {
               ordinal = held_item_stack.getQuality() == null ? 2 : held_item_stack.getQuality().ordinal();
               float quality_modifier = (float)(ordinal - 2) * 0.0625F;
               str_vs_block *= 1.0F + quality_modifier;
            }

            if (block == Block.web) {
               boolean decrease_strength = true;
               if (apply_held_item && held_item != null && held_item.isTool() && held_item.getAsTool().isEffectiveAgainstBlock(block, 0)) {
                  decrease_strength = false;
               }

               if (decrease_strength) {
                  str_vs_block *= 0.2F;
               }
            }

            if (this.isPotionActive(Potion.digSpeed)) {
               str_vs_block *= 1.0F + (float)(this.getActivePotionEffect(Potion.digSpeed).getAmplifier() + 1) * 0.2F;
            }

            if (this.isPotionActive(Potion.digSlowdown)) {
               str_vs_block *= 1.0F - (float)(this.getActivePotionEffect(Potion.digSlowdown).getAmplifier() + 1) * 0.2F;
            }

            if (this.isPotionActive(PotionExtend.freeze)) {
               str_vs_block *= 1.0F - (float)(this.getActivePotionEffect(PotionExtend.freeze).getAmplifier() + 1) * 0.5F;
            }

            if (this.isInsideOfMaterial(Material.water) && !EnchantmentHelper.getAquaAffinityModifier(this)) {
               str_vs_block /= 5.0F;
            }

            if (!this.onGround) {
               str_vs_block /= 5.0F;
            }

            if (!this.hasFoodEnergy()) {
               str_vs_block /= 5.0F;
            }

            str_vs_block *= 1.0F + this.getLevelModifier(EnumLevelBonus.HARVESTING);
            if ((Boolean)ExperimentalConfig.TagConfig.FinalChallenge.ConfigValue) {
               str_vs_block *= 1.0F - (float)Constant.CalculateCurrentDiff() / 100.0F;
            }

            if ((Boolean)ExperimentalConfig.TagConfig.Realistic.ConfigValue) {
               str_vs_block *= Math.min((float)Math.pow((double)this.getHealth(), 2.0) / 25.0F, 1.0F);
            }

            return Math.max(str_vs_block, min_str_vs_block);
         }
      }
   }

   public void attackMonsters(List targets, float damage) {
      for(int i = 0; i < targets.size(); ++i) {
         EntityLivingBase entityMonster = targets.get(i) instanceof EntityLivingBase ? (EntityLivingBase)targets.get(i) : null;
         if (entityMonster != null && entityMonster.getDistanceSqToEntity(this) <= (double)this.getReach(EnumEntityReachContext.FOR_MELEE_ATTACK, entityMonster) && entityMonster.canEntityBeSeenFrom(this.posX, this.getEyePosY(), this.posZ, 5.0)) {
            entityMonster.attackEntityFrom(new Damage(DamageSource.causePlayerDamage(this.getAsPlayer()), damage));
         }
      }

   }

   public float detectNearestMonstersDistance(List targets) {
      float distance = -1.0F;

      for(int i = 0; i < targets.size(); ++i) {
         EntityMob entityMonster = targets.get(i) instanceof EntityMob ? (EntityMob)targets.get(i) : null;
         if (entityMonster != null) {
            if (distance < 0.0F) {
               distance = (float)entityMonster.getDistanceSqToEntity(this);
            } else {
               distance = Math.min(distance, (float)entityMonster.getDistanceSqToEntity(this));
            }
         }
      }

      return distance;
   }

   @Overwrite
   protected void fall(float par1) {
      if (!this.capabilities.allowFlying) {
         if (par1 >= 2.0F) {
            this.addStat(StatList.distanceFallenStat, (int)Math.round((double)par1 * 100.0));
         }

         super.fall(par1);
      }

      if ((Boolean)ExperimentalConfig.TagConfig.TagMovingV2.ConfigValue) {
         this.setSprinting(false);
      }

   }

   @Shadow
   public boolean isUsingItem() {
      return false;
   }

   @Shadow
   public float getLevelModifier(EnumLevelBonus kind) {
      return 0.0F;
   }

   @Shadow
   public int getNutrition() {
      return 1;
   }

   @Shadow
   public int getSatiation() {
      return 1;
   }

   @Shadow
   public int getScore() {
      return 0;
   }

   @Shadow
   public ItemStack[] getWornItems() {
      return new ItemStack[0];
   }

   @Shadow
   public boolean isWearing(ItemStack itemStack) {
      return false;
   }

   @Shadow
   public boolean setWornItem(int i, ItemStack itemStack) {
      return false;
   }

   @Shadow
   public void setHeldItemStack(ItemStack itemStack) {
   }

   @Shadow
   public ItemStack getHeldItemStack() {
      return null;
   }

   @Shadow
   public ItemStack getCurrentItemOrArmor(int i) {
      return null;
   }

   @Shadow
   public void setCurrentItemOrArmor(int i, ItemStack itemStack) {
   }

   @Shadow
   public ItemStack[] getLastActiveItems() {
      return new ItemStack[0];
   }

   @Shadow
   public void wakeUpPlayer(boolean get_out_of_bed, Entity entity_to_look_at) {
   }

   @Shadow
   public EnumQuality getMinCraftingQuality(Item item, int[] applicable_skillsets) {
      return null;
   }

   @Shadow
   public boolean hasAnyOfTheseSkillsets(int[] skillsets) {
      return false;
   }

   @Shadow
   public int getCraftingExperienceCost(float quality_adjusted_crafting_difficulty) {
      return 1;
   }

   @Shadow
   public void setSizeNormal() {
   }

   @Shadow
   public void setSizeProne() {
   }

   @Shadow
   public EntityItem dropPlayerItemWithRandomChoice(ItemStack par1ItemStack, boolean par2) {
      return null;
   }

   @Shadow
   public void addStat(StatBase par1StatBase, int par2) {
   }

   @Shadow
   public void setScore(int par1) {
   }

   @Shadow
   public boolean isImmuneByGrace() {
      return false;
   }

   @Shadow
   public boolean willDeliverCriticalStrike() {
      return false;
   }

   @Shadow
   public float calcRawMeleeDamageVs(Entity target, boolean critical, boolean suspended_in_liquid) {
      return 0.0F;
   }

   @Shadow
   public void onCriticalHit(Entity par1Entity) {
   }

   @Shadow
   public void onEnchantmentCritical(Entity par1Entity) {
   }

   @Shadow
   public void addHungerServerSide(float hunger) {
   }

   @Shadow
   public void triggerAchievement(StatBase par1StatBase) {
   }

   @Shadow
   public void sendPacket(Packet packet) {
   }

   @Shadow
   public abstract void addExperience(int var1);

   @Shadow
   protected abstract void entityInit();

   @Shadow
   public abstract float getReach(EnumEntityReachContext var1, Entity var2);

   @Shadow
   public abstract double getEyePosY();

   @Shadow
   public abstract void playSound(String var1, float var2, float var3);

   @Shadow
   public void addScore(int par1) {
   }

   @Shadow
   public final int getExperienceLevel() {
      return 0;
   }

   @Shadow
   public float getHealthLimit() {
      return 0.0F;
   }

   @Shadow
   protected static final int getExperienceRequired(int level) {
      return 0;
   }

   @Shadow
   public void learnCurseEffect() {
   }

   @Shadow
   public boolean hasCurse(Curse curse) {
      return this.hasCurse(curse, false);
   }
}
