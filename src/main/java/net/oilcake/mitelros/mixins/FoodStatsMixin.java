package net.oilcake.mitelros.mixins;

import net.minecraft.Damage;
import net.minecraft.DamageSource;
import net.minecraft.EnchantmentHelper;
import net.minecraft.EntityPlayer;
import net.minecraft.FoodStats;
import net.minecraft.Item;
import net.minecraft.Minecraft;
import net.minecraft.NBTTagCompound;
import net.minecraft.ServerPlayer;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.network.PacketDecreaseWater;
import net.oilcake.mitelros.util.DamageSourceExtend;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodStats.class)
public class FoodStatsMixin implements ink.huix.iinjected.FoodStatsKt {
   private float water_for_nutrition_only;
   private int water;
   private float hungerWater;
   private float global_water_rate = 1.0F;
   @Shadow
   private int satiation;
   @Shadow
   private int nutrition;
   @Shadow
   private float hunger;
   @Shadow
   private float hunger_for_nutrition_only;
   @Shadow
   private float heal_progress;
   @Shadow
   private float starve_progress;
   private float dehydration_progress;
   private float malnourished_progress;
   @Shadow
   private EntityPlayer player;

   @Inject(
      method = {"<init>(Lnet/minecraft/EntityPlayer;)V"},
      at = {@At("RETURN")}
   )
   private void injectInit(CallbackInfo callbackInfo) {
      this.water = this.getWaterLimit();
   }

   @Inject(
      method = {"readNBT(Lnet/minecraft/NBTTagCompound;)V"},
      at = {@At("RETURN")}
   )
   public void injectReadNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
      this.water_for_nutrition_only = par1NBTTagCompound.getFloat("water_for_nutrition_only");
      this.hungerWater = par1NBTTagCompound.getFloat("hungerWater");
      this.water = par1NBTTagCompound.getInteger("water");
   }

   @Inject(
      method = {"writeNBT(Lnet/minecraft/NBTTagCompound;)V"},
      at = {@At("RETURN")}
   )
   public void injectWriteNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
      par1NBTTagCompound.setInteger("water", this.water);
      par1NBTTagCompound.setFloat("hungerwater", this.hungerWater);
      par1NBTTagCompound.setFloat("water_for_nutrition_only", this.water_for_nutrition_only);
   }

   @Unique
   public int getWater() {
      return this.water;
   }

   @Inject(
      method = {"addFoodValue(Lnet/minecraft/Item;)V"},
      at = {@At("HEAD")}
   )
   public void injectaddFoodValue(Item item, CallbackInfo callbackInfo) {
      this.addWater(item.getWater());
      if (item.getWater() > 0) {
         this.player.removePotionEffect(PotionExtend.thirsty.id);
      }

   }

   @Unique
   public void setSatiationWater(int water, boolean check_limit) {
      if (check_limit) {
         this.water = Math.min(water, this.getWaterValueLimit());
      } else {
         this.water = water;
      }

   }

   @Unique
   public void setHeal_progress(float heal_progress, boolean check_limit) {
      if (check_limit) {
         this.heal_progress = Math.min(heal_progress, 1.0F);
      } else {
         this.heal_progress = heal_progress;
      }

   }

   @Unique
   @Override
   public float getHeal_progress() {
      return this.heal_progress;
   }

   @Unique
   public int addWater(int water) {
      this.setSatiationWater(this.water + water, true);
      return this.water;
   }

   @Unique
   private static float getWaterPerTick() {
      return 0.002F;
   }

   @Unique
   private static float getWaterPerFoodUnit() {
      return 4.0F;
   }

   @Unique
   private void decreaseWater(float water) {
      if (!this.player.capabilities.isCreativeMode && !this.player.capabilities.disableDamage && !this.player.isGhost() && !this.player.isZevimrgvInTournament()) {
         water *= this.global_water_rate;
         this.hungerWater = Math.min(this.hungerWater + water, 40.0F);
         if (this.player.worldObj.isRemote && this.hungerWater > 0.2F) {
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new PacketDecreaseWater(this.hungerWater));
            this.hungerWater = 0.0F;
         }
      }

   }

   @Unique
   public void decreaseWaterClientSide(float water) {
      if (!this.player.worldObj.isRemote) {
         Minecraft.setErrorMessage("addHungerClientSide: cannot decrease Water to client if not remote");
      } else {
         this.decreaseWater(water);
      }

   }

   @Unique
   @Override
   public void decreaseWaterServerSide(float hungerWater) {
      if (this.player.worldObj.isRemote) {
         Minecraft.setErrorMessage("addHungerServerSide: cannot decrease Water to server if remote");
      } else {
         this.decreaseWater(hungerWater);
      }

   }

   @Unique
   public int getWaterValueLimit() {
      return this.getWaterLimit();
   }

   @Unique
   public int getWaterLimit() {
      return Math.max(Math.min(6 + this.player.getExperienceLevel() / 5 * 2, 20), 6);
   }

   @Overwrite
   public void onUpdate(ServerPlayer par1EntityPlayer) {
      if (!par1EntityPlayer.isGhost() && !par1EntityPlayer.isZevimrgvInTournament() && !par1EntityPlayer.isDead && !(par1EntityPlayer.getHealth() <= 0.0F)) {
         par1EntityPlayer.decrementNutrients();
         par1EntityPlayer.decrementInsulinResistance();
         this.decreaseWaterServerSide(getWaterPerTick());
         if (!par1EntityPlayer.inCreativeMode()) {
            this.water_for_nutrition_only += getWaterPerTick() * 0.3F;
         }

         float hunger_factor = par1EntityPlayer.getWetnessAndMalnourishmentHungerMultiplier();
         this.addHungerServerSide(getHungerPerTick() * hunger_factor);
         if (!par1EntityPlayer.inCreativeMode()) {
            this.hunger_for_nutrition_only += getHungerPerTick() * 0.25F;
         }

         if (this.hunger >= getHungerPerFoodUnit()) {
            this.hunger -= getHungerPerFoodUnit();
            if (this.satiation > 0 || this.nutrition > 0) {
               if (this.satiation < 1 || this.hunger_for_nutrition_only + 0.001F >= getHungerPerFoodUnit() && this.nutrition > 0) {
                  --this.nutrition;
                  this.hunger_for_nutrition_only = 0.0F;
               } else {
                  --this.satiation;
               }
            }
         }

         if (this.satiation < 0) {
            this.satiation = 0;
         }

         if (this.water < 0) {
            this.water = 0;
         }

         if (par1EntityPlayer.inBed() && par1EntityPlayer.isOnHitList()) {
            par1EntityPlayer.addHungerServerSide(getHungerPerTick() * 20.0F);
            par1EntityPlayer.decreaseWaterServerSide(getWaterPerTick() * 20.0F);
         }

         if (this.player.isStarving()) {
            this.heal_progress = 0.0F;
            this.starve_progress += 0.002F;
            if (this.starve_progress >= 1.0F) {
               if (par1EntityPlayer.getHealth() > 10.0F || this.player.worldObj.difficultySetting >= 3 || par1EntityPlayer.getHealth() > 1.0F && this.player.worldObj.difficultySetting >= 2) {
                  par1EntityPlayer.attackEntityFrom(new Damage(DamageSource.starve, 1.0F));
               }

               --this.starve_progress;
               this.hunger_for_nutrition_only = 0.0F;
            }
         } else if (this.player.DuringDehydration()) {
            this.heal_progress = 0.0F;
            this.dehydration_progress += 0.002F;
            if (this.dehydration_progress >= 1.0F) {
               par1EntityPlayer.attackEntityFrom(new Damage(DamageSourceExtend.thirsty, 1.0F));
               --this.dehydration_progress;
               this.water_for_nutrition_only = 0.0F;
            }
         } else if (par1EntityPlayer.isMalnourishedFin()) {
            this.heal_progress = 0.0F;
            this.malnourished_progress += 0.002F;
            if (this.malnourished_progress >= 1.0F) {
               par1EntityPlayer.attackEntityFrom(new Damage(DamageSourceExtend.malnourished, 1.0F));
               --this.malnourished_progress;
            }
         } else {
            this.heal_progress += ((float)this.satiation * 2.0E-5F + (float)this.nutrition * 2.0E-5F) * (par1EntityPlayer.isMalnourishedLv1() ? 0.25F : (par1EntityPlayer.isMalnourishedLv2() ? 0.0F : (par1EntityPlayer.isMalnourishedLv3() ? 0.0F : 1.0F))) * (par1EntityPlayer.inBed() ? 8.0F : 1.0F) * EnchantmentHelper.getRegenerationModifier(this.player);
            this.starve_progress = 0.0F;
            if (par1EntityPlayer.worldObj.getGameRules().getGameRuleBooleanValue("naturalRegeneration") && par1EntityPlayer.shouldHeal()) {
               if (this.heal_progress >= 1.0F) {
                  par1EntityPlayer.heal(1.0F);
                  this.addHungerServerSide(1.0F);
                  this.decreaseWaterServerSide(1.0F);
                  --this.heal_progress;
               }
            } else {
               this.heal_progress = 0.0F;
            }
         }
      }

   }

   @Shadow
   public int addNutrition(int nutrition) {
      return this.nutrition;
   }

   @Shadow
   public int addSatiation(int satiation) {
      return this.satiation;
   }

   @Shadow
   public static float getHungerPerFoodUnit() {
      return 0.0F;
   }

   @Shadow
   public void addHungerServerSide(float hunger) {
   }

   @Shadow
   public static float getHungerPerTick() {
      return 0.0F;
   }

   @Shadow
   public int getNutritionLimit() {
      return 1;
   }
}
