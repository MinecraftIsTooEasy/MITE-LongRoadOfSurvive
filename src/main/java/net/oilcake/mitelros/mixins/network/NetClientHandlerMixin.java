package net.oilcake.mitelros.mixins.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import net.minecraft.Entity;
import net.minecraft.EntityArrow;
import net.minecraft.EntityBoat;
import net.minecraft.EntityBrick;
import net.minecraft.EntityEgg;
import net.minecraft.EntityEnderCrystal;
import net.minecraft.EntityEnderEye;
import net.minecraft.EntityEnderPearl;
import net.minecraft.EntityExpBottle;
import net.minecraft.EntityFallingSand;
import net.minecraft.EntityFireworkRocket;
import net.minecraft.EntityFishHook;
import net.minecraft.EntityGelatinousSphere;
import net.minecraft.EntityItem;
import net.minecraft.EntityItemFrame;
import net.minecraft.EntityLargeFireball;
import net.minecraft.EntityLeashKnot;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityMinecart;
import net.minecraft.EntityPlayer;
import net.minecraft.EntityPotion;
import net.minecraft.EntitySmallFireball;
import net.minecraft.EntitySnowball;
import net.minecraft.EntityTNTPrimed;
import net.minecraft.EntityWeb;
import net.minecraft.EntityWitherSkull;
import net.minecraft.GuiScreen;
import net.minecraft.Item;
import net.minecraft.ItemArrow;
import net.minecraft.ItemStack;
import net.minecraft.MathHelper;
import net.minecraft.Minecraft;
import net.minecraft.NetClientHandler;
import net.minecraft.Packet23VehicleSpawn;
import net.minecraft.Packet52MultiBlockChange;
import net.minecraft.Packet8UpdateHealth;
import net.minecraft.Packet97MultiBlockChange;
import net.minecraft.SpatialScaler;
import net.minecraft.WorldClient;
import net.oilcake.mitelros.block.enchantreserver.GuiEnchantReserver;
import net.oilcake.mitelros.entity.EntityWandFireball;
import net.oilcake.mitelros.entity.EntityWandIceBall;
import net.oilcake.mitelros.entity.EntityWandShockWave;
import net.oilcake.mitelros.network.PacketEnchantReserverInfo;
import net.oilcake.mitelros.network.PacketUpdateTemperature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.SoftOverride;

@Mixin({NetClientHandler.class})
public class NetClientHandlerMixin extends ConnectionMixin {
   @Shadow
   private static int class_hash_sum = 0;
   @Shadow
   private WorldClient worldClient;
   @Shadow
   private Minecraft mc;

   @Overwrite
   public void handleVehicleSpawn(Packet23VehicleSpawn par1Packet23VehicleSpawn) {
      double var2;
      double var4;
      double var6;
      if (par1Packet23VehicleSpawn.position_set_using_unscaled_integers) {
         var2 = (double)par1Packet23VehicleSpawn.unscaled_pos_x;
         var4 = (double)par1Packet23VehicleSpawn.unscaled_pos_y;
         var6 = (double)par1Packet23VehicleSpawn.unscaled_pos_z;
      } else {
         var2 = SpatialScaler.getPosX(par1Packet23VehicleSpawn.scaled_pos_x);
         var4 = SpatialScaler.getPosY(par1Packet23VehicleSpawn.scaled_pos_y);
         var6 = SpatialScaler.getPosZ(par1Packet23VehicleSpawn.scaled_pos_z);
      }

      Entity var8 = null;
      if (par1Packet23VehicleSpawn.type == 10) {
         var8 = EntityMinecart.createMinecart(this.worldClient, var2, var4, var6, par1Packet23VehicleSpawn.throwerEntityId);
      } else if (par1Packet23VehicleSpawn.type == 90) {
         Entity var9 = this.getEntityByID(par1Packet23VehicleSpawn.throwerEntityId);
         if (var9 instanceof EntityPlayer) {
            var8 = new EntityFishHook(this.worldClient, var2, var4, var6, (EntityPlayer)var9);
         }

         par1Packet23VehicleSpawn.throwerEntityId = 0;
      } else if (par1Packet23VehicleSpawn.type == 60) {
         if (par1Packet23VehicleSpawn.arrow_item_id == -1) {
            var8 = null;
         } else {
            var2 = par1Packet23VehicleSpawn.exact_pos_x;
            var4 = par1Packet23VehicleSpawn.exact_pos_y;
            var6 = par1Packet23VehicleSpawn.exact_pos_z;
            var8 = new EntityArrow(this.worldClient, var2, var4, var6, (ItemArrow)Item.itemsList[par1Packet23VehicleSpawn.arrow_item_id], par1Packet23VehicleSpawn.launcher_was_enchanted);
            if (par1Packet23VehicleSpawn.arrow_stuck_in_block) {
               ((EntityArrow)var8).setInGround();
            }

            EntityArrow arrow = (EntityArrow)var8;
            arrow.xTile = par1Packet23VehicleSpawn.arrow_tile_x;
            arrow.yTile = par1Packet23VehicleSpawn.arrow_tile_y;
            arrow.zTile = par1Packet23VehicleSpawn.arrow_tile_z;
            arrow.setInTile(par1Packet23VehicleSpawn.arrow_in_tile);
            arrow.setInData(par1Packet23VehicleSpawn.arrow_in_data);
         }
      } else if (par1Packet23VehicleSpawn.type == 61) {
         var8 = new EntitySnowball(this.worldClient, var2, var4, var6);
      } else if (par1Packet23VehicleSpawn.type == 71) {
         var8 = new EntityItemFrame(this.worldClient, par1Packet23VehicleSpawn.unscaled_pos_x, par1Packet23VehicleSpawn.unscaled_pos_y, par1Packet23VehicleSpawn.unscaled_pos_z, par1Packet23VehicleSpawn.throwerEntityId);
         par1Packet23VehicleSpawn.throwerEntityId = 0;
      } else if (par1Packet23VehicleSpawn.type == 77) {
         var8 = new EntityLeashKnot(this.worldClient, par1Packet23VehicleSpawn.unscaled_pos_x, par1Packet23VehicleSpawn.unscaled_pos_y, par1Packet23VehicleSpawn.unscaled_pos_z);
         par1Packet23VehicleSpawn.throwerEntityId = 0;
      } else if (par1Packet23VehicleSpawn.type == 65) {
         var8 = new EntityEnderPearl(this.worldClient, var2, var4, var6);
      } else if (par1Packet23VehicleSpawn.type == 72) {
         var8 = new EntityEnderEye(this.worldClient, var2, var4, var6);
      } else if (par1Packet23VehicleSpawn.type == 76) {
         var8 = new EntityFireworkRocket(this.worldClient, var2, var4, var6, (ItemStack)null);
      } else if (par1Packet23VehicleSpawn.type == 63) {
         var8 = new EntityLargeFireball(this.worldClient, var2, var4, var6, (double)par1Packet23VehicleSpawn.approx_motion_x, (double)par1Packet23VehicleSpawn.approx_motion_y, (double)par1Packet23VehicleSpawn.approx_motion_z);
         par1Packet23VehicleSpawn.throwerEntityId = 0;
      } else if (par1Packet23VehicleSpawn.type == 64) {
         var8 = new EntitySmallFireball(this.worldClient, var2, var4, var6, (double)par1Packet23VehicleSpawn.approx_motion_x, (double)par1Packet23VehicleSpawn.approx_motion_y, (double)par1Packet23VehicleSpawn.approx_motion_z);
         par1Packet23VehicleSpawn.throwerEntityId = 0;
      } else if (par1Packet23VehicleSpawn.type == 66) {
         var8 = new EntityWitherSkull(this.worldClient, var2, var4, var6, (double)par1Packet23VehicleSpawn.approx_motion_x, (double)par1Packet23VehicleSpawn.approx_motion_y, (double)par1Packet23VehicleSpawn.approx_motion_z);
         par1Packet23VehicleSpawn.throwerEntityId = 0;
      } else if (par1Packet23VehicleSpawn.type == 62) {
         var8 = new EntityEgg(this.worldClient, var2, var4, var6);
      } else if (par1Packet23VehicleSpawn.type == 500) {
         var8 = new EntityBrick(this.worldClient, var2, var4, var6, Item.brick);
      } else if (par1Packet23VehicleSpawn.type == 501) {
         var8 = new EntityBrick(this.worldClient, var2, var4, var6, Item.netherrackBrick);
      } else if (MathHelper.isInRange(par1Packet23VehicleSpawn.type, 600, 699)) {
         var8 = new EntityGelatinousSphere(this.worldClient, var2, var4, var6, Item.slimeBall, par1Packet23VehicleSpawn.type - 600);
      } else if (par1Packet23VehicleSpawn.type == 700) {
         var8 = new EntityWeb(this.worldClient, var2, var4, var6);
      } else if (par1Packet23VehicleSpawn.type == 73) {
         var8 = new EntityPotion(this.worldClient, var2, var4, var6, par1Packet23VehicleSpawn.throwerEntityId);
         par1Packet23VehicleSpawn.throwerEntityId = 0;
      } else if (par1Packet23VehicleSpawn.type == 75) {
         var8 = new EntityExpBottle(this.worldClient, var2, var4, var6);
         par1Packet23VehicleSpawn.throwerEntityId = 0;
      } else if (par1Packet23VehicleSpawn.type == 1) {
         var8 = new EntityBoat(this.worldClient, var2, var4, var6);
      } else if (par1Packet23VehicleSpawn.type == 50) {
         var8 = new EntityTNTPrimed(this.worldClient, var2, var4, var6, (EntityLivingBase)null);
      } else if (par1Packet23VehicleSpawn.type == 51) {
         var8 = new EntityEnderCrystal(this.worldClient, var2, var4, var6);
      } else if (par1Packet23VehicleSpawn.type == 2) {
         var8 = new EntityItem(this.worldClient, var2, var4, var6);
      } else if (par1Packet23VehicleSpawn.type == 70) {
         var8 = new EntityFallingSand(this.worldClient, (double)MathHelper.floor_double(var2) + 0.5, (double)MathHelper.floor_double(var4) + 0.5, (double)MathHelper.floor_double(var6) + 0.5, par1Packet23VehicleSpawn.throwerEntityId & '\uffff', par1Packet23VehicleSpawn.throwerEntityId >> 16);
         par1Packet23VehicleSpawn.throwerEntityId = 0;
      } else if (par1Packet23VehicleSpawn.type == 100) {
         var8 = new EntityWandIceBall(this.worldClient, var2, var4, var6);
      } else if (par1Packet23VehicleSpawn.type == 101) {
         var8 = new EntityWandFireball(this.worldClient, var2, var4, var6);
      } else if (par1Packet23VehicleSpawn.type == 102) {
         var8 = new EntityWandShockWave(this.worldClient, var2, var4, var6);
      }

      if (var8 != null) {
         ((Entity)var8).rotationYaw = SpatialScaler.getRotation(par1Packet23VehicleSpawn.scaled_yaw);
         ((Entity)var8).rotationPitch = SpatialScaler.getRotation(par1Packet23VehicleSpawn.scaled_pitch);
         if (var8 instanceof EntityBoat) {
            ((EntityBoat)var8).setPositionAndRotation2(((Entity)var8).posX, ((Entity)var8).posY, ((Entity)var8).posZ, ((Entity)var8).rotationYaw, ((Entity)var8).rotationPitch, 3);
            ((Entity)var8).prevRotationYaw = ((Entity)var8).rotationYaw;
         }

         Entity[] var12 = ((Entity)var8).getParts();
         if (var12 != null) {
            int var10 = par1Packet23VehicleSpawn.entityId - ((Entity)var8).entityId;

            for(int var11 = 0; var11 < var12.length; ++var11) {
               var12[var11].entityId += var10;
            }
         }

         ((Entity)var8).entityId = par1Packet23VehicleSpawn.entityId;
         this.worldClient.addEntityToWorld(par1Packet23VehicleSpawn.entityId, (Entity)var8);
         if (par1Packet23VehicleSpawn.throwerEntityId > 0) {
            if (par1Packet23VehicleSpawn.type == 60) {
               Entity var13 = this.getEntityByID(par1Packet23VehicleSpawn.throwerEntityId);
               if (var13 instanceof EntityLivingBase) {
                  EntityArrow var14 = (EntityArrow)var8;
                  var14.shootingEntity = var13;
               }

               ((Entity)var8).setVelocity(par1Packet23VehicleSpawn.exact_motion_x, par1Packet23VehicleSpawn.exact_motion_y, par1Packet23VehicleSpawn.exact_motion_z);
               return;
            }

            ((Entity)var8).setVelocity((double)par1Packet23VehicleSpawn.approx_motion_x, (double)par1Packet23VehicleSpawn.approx_motion_y, (double)par1Packet23VehicleSpawn.approx_motion_z);
         }
      }

   }

   @Overwrite
   public void handleUpdateHealth(Packet8UpdateHealth par1Packet8UpdateHealth) {
      this.mc.thePlayer.setPlayerSPHealth(par1Packet8UpdateHealth.healthMP);
      this.mc.thePlayer.getFoodStats().setSatiation(par1Packet8UpdateHealth.satiation, false);
      this.mc.thePlayer.getFoodStats().setNutrition(par1Packet8UpdateHealth.nutrition, false);
      this.mc.thePlayer.getFoodStats().setSatiationWater(par1Packet8UpdateHealth.water, false);
      this.mc.thePlayer.setPhytonutrients(par1Packet8UpdateHealth.phytonutrients);
      this.mc.thePlayer.setProtein(par1Packet8UpdateHealth.protein);
      this.mc.thePlayer.setHeal_progress(par1Packet8UpdateHealth.heal_progress);
      if (this.mc.thePlayer.vision_dimming < par1Packet8UpdateHealth.vision_dimming) {
         this.mc.thePlayer.vision_dimming = par1Packet8UpdateHealth.vision_dimming;
      }

   }

   public void handleUpdateTemperature(PacketUpdateTemperature packet) {
      this.mc.thePlayer.setTemperaturePoint(packet.getTemperaturePoint());
   }

   @SoftOverride
   public void processEnchantReserverInfo(PacketEnchantReserverInfo packet) {
      GuiScreen openingGUI = this.mc.currentScreen;
      if (openingGUI instanceof GuiEnchantReserver) {
         ((GuiEnchantReserver)openingGUI).setEnchantInfo(packet.getEXP());
      }

   }

   @Overwrite
   public void handleMultiBlockChange(Packet52MultiBlockChange par1Packet52MultiBlockChange) {
      int var2 = par1Packet52MultiBlockChange.xPosition * 16;
      int var3 = par1Packet52MultiBlockChange.zPosition * 16;
      if (par1Packet52MultiBlockChange.metadataArray != null) {
         DataInputStream var4 = new DataInputStream(new ByteArrayInputStream(par1Packet52MultiBlockChange.metadataArray));

         try {
            long before = System.nanoTime();

            int delay;
            for(delay = 0; delay < par1Packet52MultiBlockChange.size; ++delay) {
               short var8 = var4.readShort();
               short var9 = var4.readShort();
               int var10 = var9 >> 8;
               int var11 = var9 & 15;
               int var12 = var8 >> 12 & 15;
               int var13 = var8 >> 8 & 15;
               int var14 = var8 & 255;
               this.worldClient.setBlockAndMetadataAndInvalidate(var12 + var2, var14, var13 + var3, var10, var11);
            }

            delay = (int)(System.nanoTime() - before) / 10000000;
            if (delay > 0) {
               Minecraft.MITE_log.logInfo("Long time processing handleMultiBlockChange (delay=" + delay + ") #Blocks=" + par1Packet52MultiBlockChange.size);
            }
         } catch (IOException var15) {
            System.out.println("Exception occured, packet52");
         }
      }

   }

   @Overwrite
   public void handleMultiBlockChange(Packet97MultiBlockChange packet) {
      byte[] bytes = packet.getBytes();
      int base_x = packet.chunk_x * 16;
      int base_z = packet.chunk_z * 16;
      long before = System.nanoTime();

      int delay;
      for(delay = 0; delay < packet.num_blocks; ++delay) {
         int offset = delay * 6;
         int x = base_x + bytes[offset];
         int y = bytes[offset + 1] & 255;
         int z = base_z + bytes[offset + 2];
         int block_id = bytes[offset + 3];
         int id_extra = bytes[offset + 4];
         if (id_extra < 0) {
            id_extra += 256;
         }

         byte metadata = bytes[offset + 5];
         this.worldClient.setBlockAndMetadataAndInvalidate(x, y, z, block_id * 256 + id_extra, metadata);
         if (this.worldClient.hasSkylight()) {
            this.worldClient.getChunkFromBlockCoords(x, z).addPendingSkylightUpdate(x, y, z);
         }
      }

      delay = (int)(System.nanoTime() - before) / 10000000;
      if (delay > 0) {
         Minecraft.MITE_log.logInfo("Long time processing handleMultiBlockChange97 (delay=" + delay + ") #Blocks=" + packet.num_blocks);
      }

   }

   @Shadow
   private static int getHash(Class _class) {
      return 1;
   }

   @Shadow
   private Entity getEntityByID(int par1) {
      return null;
   }
}
