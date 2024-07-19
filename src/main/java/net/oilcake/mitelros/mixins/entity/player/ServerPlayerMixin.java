package net.oilcake.mitelros.mixins.entity.player;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.AchievementList;
import net.minecraft.BiomeGenBase;
import net.minecraft.ChatMessageComponent;
import net.minecraft.ChunkCoordinates;
import net.minecraft.Container;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.Curse;
import net.minecraft.DamageSource;
import net.minecraft.EntityPlayer;
import net.minecraft.EntityWitch;
import net.minecraft.EnumSignal;
import net.minecraft.ICrafting;
import net.minecraft.INetworkManager;
import net.minecraft.Item;
import net.minecraft.ItemInWorldManager;
import net.minecraft.ItemMapBase;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.MathHelper;
import net.minecraft.NBTTagCompound;
import net.minecraft.NetServerHandler;
import net.minecraft.Packet;
import net.minecraft.Packet100OpenWindow;
import net.minecraft.Packet43Experience;
import net.minecraft.Packet70GameEvent;
import net.minecraft.Packet85SimpleSignal;
import net.minecraft.Packet8UpdateHealth;
import net.minecraft.ReportedException;
import net.minecraft.ScoreObjective;
import net.minecraft.ScoreObjectiveCriteria;
import net.minecraft.ServerPlayer;
import net.minecraft.TileEntity;
import net.minecraft.World;
import net.minecraft.WorldProviderEnd;
import net.minecraft.WorldServer;
import net.minecraft.server.MinecraftServer;
import net.oilcake.mitelros.achivements.AchievementExtend;
import net.oilcake.mitelros.block.enchantreserver.ContainerEnchantReserver;
import net.oilcake.mitelros.block.enchantreserver.EnchantReserverSlots;
import net.oilcake.mitelros.network.PacketUpdateTemperature;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.util.StuckTagConfig;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ServerPlayer.class})
public abstract class ServerPlayerMixin extends EntityPlayer implements ICrafting {
   public boolean isNewPlayer = true;
   private int last_water = -99999999;
   private int last_FreezingCooldown = -99999999;
   private int last_phytonutrients;
   private int last_protein;
   private float last_heal_progress;
   private int last_temperaturePoint;
   @Shadow
   private int currentWindowId;
   @Shadow
   public boolean playerConqueredTheEnd;
   @Shadow
   private int protein;
   @Shadow
   private int essential_fats;
   @Shadow
   private int phytonutrients;
   @Shadow
   private float field_130068_bO;
   @Shadow
   private float lastHealth;
   @Shadow
   private int last_experience;
   @Shadow
   private int last_nutrition;
   @Shadow
   private int last_satiation;
   @Shadow
   public NetServerHandler playerNetServerHandler;
   @Shadow
   public MinecraftServer mcServer;
   @Shadow
   public short respawn_countdown;
   @Shadow
   protected int respawn_experience;

   @Inject(
      method = {"readNBT"},
      at = {@At("RETURN")}
   )
   public void injectReadNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
      this.isNewPlayer = par1NBTTagCompound.getBoolean("isNewPlayer");
   }

   @Inject(
      method = {"writeNBT"},
      at = {@At("RETURN")}
   )
   public void injectWriteNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
      par1NBTTagCompound.setBoolean("isNewPlayer", this.isNewPlayer);
   }

   @Inject(
      method = {"onDeath(Lnet/minecraft/DamageSource;)V"},
      at = {@At("INVOKE")}
   )
   public void onDeath(DamageSource par1DamageSource, CallbackInfo callbackInfo) {
      if (!this.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {
         this.inventory.vanishingItems();
      }

   }

   @Inject(
      method = {"onUpdate()V"},
      at = {@At("INVOKE")}
   )
   public void onUpdate(CallbackInfo callbackInfo) {
      this.sendPacket((new Packet85SimpleSignal(EnumSignal.malnourished)).setInteger((this.protein <= 800000 ? 1 : 0) | (this.phytonutrients <= 800000 ? 4 : 0) | this.getCurrent_insulin_resistance_lvl() << 3 | this.getInsulinResistance() << 8));
   }

   @Overwrite
   public void onUpdateEntity() {
      try {
         super.onUpdate();

         for(int var1 = 0; var1 < this.inventory.getSizeInventory(); ++var1) {
            ItemStack var6 = this.inventory.getStackInSlot(var1);
            if (var6 != null && Item.itemsList[var6.itemID].isMap() && this.playerNetServerHandler.packetSize() <= 5) {
               Packet var8 = ((ItemMapBase)Item.itemsList[var6.itemID]).createMapDataPacket(var6, this.worldObj, this);
               if (var8 != null) {
                  this.playerNetServerHandler.sendPacketToPlayer(var8);
               }
            }
         }

         int username_hash;
         int i;
         if (!this.is_cursed && (Boolean)StuckTagConfig.TagConfig.TagRejection.ConfigValue) {
            EntityWitch temp = new EntityWitch(this.worldObj);
            username_hash = 0;

            for(i = 0; i < this.username.length(); ++i) {
               username_hash += this.username.charAt(i) * i;
            }

            this.worldObj.getAsWorldServer().addCurse(this.getAsEntityPlayerMP(), temp, Curse.getRandomCurse(new Random((long)(this.rand.nextInt() + username_hash))), 0);
            this.learnCurseEffect();
         }

         float health = this.getHealth();
         username_hash = this.getSatiation();
         i = this.getNutrition();
         int FreezingCooldown = this.getFreezingCooldown();
         int water = this.getWater();
         if (water != this.last_water || FreezingCooldown != this.last_FreezingCooldown || health != this.lastHealth || username_hash != this.last_satiation || i != this.last_nutrition || this.vision_dimming > 0.0F || this.phytonutrients != this.last_phytonutrients || this.protein != this.last_protein || this.getFoodStats().getHeal_progress() != this.last_heal_progress) {
            this.playerNetServerHandler.sendPacketToPlayer(new Packet8UpdateHealth(health, username_hash, i, this.vision_dimming));
            Packet8UpdateHealth updateWater = new Packet8UpdateHealth(health, username_hash, i, this.vision_dimming);
            updateWater.setWater(water);
            updateWater.setPhytonutrients(this.phytonutrients);
            updateWater.setProtein(this.protein);
            updateWater.setHealProgress(this.getFoodStats().getHeal_progress());
            this.playerNetServerHandler.sendPacketToPlayer(updateWater);
            this.last_water = water;
            this.last_FreezingCooldown = FreezingCooldown;
            this.lastHealth = health;
            this.last_satiation = username_hash;
            this.last_nutrition = i;
            this.vision_dimming = 0.0F;
            this.last_phytonutrients = this.phytonutrients;
            this.last_protein = this.protein;
            this.last_heal_progress = this.getFoodStats().getHeal_progress();
         }

         int temperaturePoint = this.getTemperature();
         if (temperaturePoint != this.last_temperaturePoint) {
            this.playerNetServerHandler.sendPacketToPlayer(new PacketUpdateTemperature(temperaturePoint));
            PacketUpdateTemperature updateTemperature = new PacketUpdateTemperature(temperaturePoint);
            updateTemperature.setTemperaturePoint(temperaturePoint);
            this.playerNetServerHandler.sendPacketToPlayer(updateTemperature);
            this.last_temperaturePoint = temperaturePoint;
         }

         if (this.getHealth() + this.getAbsorptionAmount() != this.field_130068_bO) {
            this.field_130068_bO = this.getHealth() + this.getAbsorptionAmount();
            Collection var5 = this.getWorldScoreboard().func_96520_a(ScoreObjectiveCriteria.health);
            Iterator var7 = var5.iterator();

            while(var7.hasNext()) {
               ScoreObjective var9 = (ScoreObjective)var7.next();
               this.getWorldScoreboard().func_96529_a(this.getEntityName(), var9).func_96651_a(Arrays.asList(this));
            }
         }

         if (this.experience != this.last_experience) {
            this.last_experience = this.experience;
            this.playerNetServerHandler.sendPacketToPlayer(new Packet43Experience(this.experience));
         }

      } catch (Throwable var10) {
         Throwable var8 = var10;
         CrashReport var2 = CrashReport.makeCrashReport(var8, "Ticking player");
         CrashReportCategory var3 = var2.makeCategory("Player being ticked");
         this.addEntityCrashInfo(var3);
         throw new ReportedException(var2);
      }
   }

   public ServerPlayerMixin(World par1World, String par2Str) {
      super(par1World, par2Str);
   }

   @Overwrite
   public void setProtein(int protein) {
      this.protein = MathHelper.clamp_int(protein, 0, 960000);
   }

   @Overwrite
   public void setEssentialFats(int essential_fats) {
      this.essential_fats = MathHelper.clamp_int(essential_fats, 0, 960000);
   }

   @Overwrite
   public void setPhytonutrients(int phytonutrients) {
      this.phytonutrients = MathHelper.clamp_int(phytonutrients, 0, 960000);
   }

   @Inject(
      method = {"<init>(Lnet/minecraft/server/MinecraftServer;Lnet/minecraft/World;Ljava/lang/String;Lnet/minecraft/ItemInWorldManager;)V"},
      at = {@At("RETURN")}
   )
   private void injectInit(MinecraftServer par1MinecraftServer, World par2World, String par3Str, ItemInWorldManager par4ItemInWorldManager, CallbackInfo callback) {
      this.protein = this.essential_fats = this.phytonutrients = 960000;
   }

   @Shadow
   protected abstract void incrementWindowID();

   public void displayGUIEnchantReserver(int x, int y, int z, EnchantReserverSlots slots) {
      this.incrementWindowID();
      TileEntity tile_entity = this.worldObj.getBlockTileEntity(x, y, z);
      this.playerNetServerHandler.sendPacketToPlayer((new Packet100OpenWindow(this.currentWindowId, 14, tile_entity.getCustomInvName(), 9, tile_entity.hasCustomName())).setCoords(x, y, z));
      this.openContainer = new ContainerEnchantReserver(slots, this, x, y, z);
      this.openContainer.windowId = this.currentWindowId;
      ((ServerPlayer)ReflectHelper.dyCast(ServerPlayer.class, this)).sendContainerAndContentsToPlayer(this.openContainer, ((ContainerEnchantReserver)this.openContainer).getInventory());
      this.openContainer.addCraftingToCrafters(this);
   }

   @Overwrite
   public boolean isMalnourished() {
      return this.protein <= 800000 || this.phytonutrients <= 800000;
   }

   @Overwrite
   public boolean isDoubleMalnourished() {
      return this.protein <= 800000 && this.phytonutrients <= 800000;
   }

   public boolean isMalnourishedLv1() {
      if (this.protein <= 800000 && this.protein >= 320000) {
         return true;
      } else {
         return this.phytonutrients <= 800000 && this.phytonutrients >= 320000;
      }
   }

   public boolean isMalnourishedLv2() {
      if (this.protein < 320000 && this.protein >= 160000) {
         return true;
      } else {
         return this.phytonutrients < 320000 && this.phytonutrients >= 160000;
      }
   }

   public boolean isMalnourishedLv3() {
      if (this.protein < 160000 && this.protein >= 0) {
         return true;
      } else {
         return this.phytonutrients < 160000 && this.phytonutrients >= 0;
      }
   }

   public boolean isMalnourishedFin() {
      if (this.protein == 0) {
         return true;
      } else {
         return this.phytonutrients == 0;
      }
   }

   @Overwrite
   public float getWetnessAndMalnourishmentHungerMultiplier() {
      int x = this.getBlockPosX();
      int y = this.getFootBlockPosY();
      int z = this.getBlockPosZ();
      float rain_factor = this.isInRain() ? (this.worldObj.isThundering(true) ? 0.5F : 0.25F) : 0.0F;
      float immersion_factor = this.worldObj.getBlockMaterial(x, y + 1, z) == Material.water ? 0.5F : (this.worldObj.getBlockMaterial(x, y, z) == Material.water ? 0.25F : 0.0F);
      float wetness_factor = Math.max(rain_factor, immersion_factor);
      if (this.isInRain() && !this.worldObj.isThundering(true) && immersion_factor == 0.25F) {
         wetness_factor += 0.125F;
      }

      if (this.worldObj.isBiomeFreezing(x, z)) {
         wetness_factor *= 2.0F;
      } else if (this.worldObj.getBiomeGenForCoords(x, z).temperature >= BiomeGenBase.desertRiver.temperature) {
         wetness_factor = 0.0F;
      }

      float malnourishment_factor = this.isMalnourishedLv1() ? 0.5F : (this.isMalnourishedLv2() ? 1.0F : (this.isMalnourishedLv3() ? 3.0F : (this.isMalnourishedFin() ? 31.0F : 0.0F)));
      return 1.0F + wetness_factor + malnourishment_factor;
   }

   @Overwrite
   public void travelToDimension(int par1) {
      if (this.dimension == 1 && par1 == 1) {
         this.triggerAchievement(AchievementList.theEnd2);
         if (Constant.CalculateCurrentDiff() >= 12) {
            this.triggerAchievement(AchievementExtend.stormStriker);
         }

         this.worldObj.removeEntity(this);
         this.playerConqueredTheEnd = true;
         this.playerNetServerHandler.sendPacketToPlayer(new Packet70GameEvent(4, 0));
      } else {
         WorldServer destination_world = this.mcServer.worldServerForDimension(par1);
         if (destination_world.isUnderworld()) {
            this.worldObj.getWorldInfo().setUnderworldVisited();
         }

         if (destination_world.isTheNether()) {
            this.worldObj.getWorldInfo().setNetherVisited();
         }

         if (destination_world.isTheEnd() && destination_world.playerEntities.size() == 0) {
            ((WorldProviderEnd)this.mcServer.worldServerForDimension(par1).provider).heal_ender_dragon = true;
         }

         if (this.dimension == 0 && par1 == 1) {
            this.triggerAchievement(AchievementList.theEnd);
            ChunkCoordinates var2 = this.mcServer.worldServerForDimension(par1).getEntrancePortalLocation();
            if (var2 != null) {
               this.playerNetServerHandler.setPlayerLocation((double)var2.posX, (double)var2.posY, (double)var2.posZ, 0.0F, 0.0F);
            }

            par1 = 1;
         } else {
            this.triggerAchievement(AchievementList.portal);
         }

         this.mcServer.getConfigurationManager().transferPlayerToDimension(this.getAsEntityPlayerMP(), par1);
         this.last_experience = -1;
         this.lastHealth = -1.0F;
         this.last_nutrition = -1;
      }

   }

   @Shadow
   public INetworkManager getNetManager() {
      return null;
   }

   @Shadow
   public void sendChatToPlayer(ChatMessageComponent chatMessage) {
   }

   @Shadow
   public boolean canCommandSenderUseCommand(int i, String s) {
      return false;
   }

   @Shadow
   public ChunkCoordinates getPlayerCoordinates() {
      return null;
   }

   @Shadow
   public void sendContainerAndContentsToPlayer(Container container, List list) {
   }

   @Shadow
   public void sendSlotContents(Container container, int i, ItemStack itemStack) {
   }

   @Shadow
   public void sendProgressBarUpdate(Container container, int i, int i1) {
   }
}
