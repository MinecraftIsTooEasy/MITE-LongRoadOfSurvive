package net.oilcake.mitelros.mixins.network;

import java.io.DataInput;
import java.io.IOException;
import net.minecraft.AnimalChest;
import net.minecraft.Entity;
import net.minecraft.EntityClientPlayerMP;
import net.minecraft.EntityHorse;
import net.minecraft.EntityPlayer;
import net.minecraft.InventoryBasic;
import net.minecraft.Minecraft;
import net.minecraft.NpcMerchant;
import net.minecraft.Packet;
import net.minecraft.Packet100OpenWindow;
import net.minecraft.StringHelper;
import net.minecraft.TileEntity;
import net.minecraft.TileEntityBeacon;
import net.minecraft.TileEntityBrewingStand;
import net.minecraft.TileEntityDispenser;
import net.minecraft.TileEntityDropper;
import net.minecraft.TileEntityFurnace;
import net.minecraft.TileEntityHopper;
import net.minecraft.WorldClient;
import net.oilcake.mitelros.block.enchantreserver.EnchantReserverSlots;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({Packet100OpenWindow.class})
public class Packet100OpenWindowMixin {
   @Shadow
   public int field_111008_f;
   @Shadow
   public boolean has_set_coords;
   @Shadow
   public int inventoryType;
   @Shadow
   public int slotsCount;
   @Shadow
   public boolean useProvidedWindowTitle;
   @Shadow
   public int windowId;
   @Shadow
   public String windowTitle;
   @Shadow
   public int x;
   @Shadow
   public int y;
   @Shadow
   public int z;

   @Overwrite
   public void readPacketData(DataInput par1DataInput) throws IOException {
      this.windowId = par1DataInput.readByte() & 255;
      this.inventoryType = par1DataInput.readByte() & 255;
      this.windowTitle = Packet.readString(par1DataInput, 32767);
      this.slotsCount = par1DataInput.readByte() & 255;
      this.useProvidedWindowTitle = par1DataInput.readBoolean();
      if (this.inventoryType == 11) {
         this.field_111008_f = par1DataInput.readInt();
      }

      if (this.hasCoords()) {
         this.x = par1DataInput.readInt();
         this.y = par1DataInput.readInt();
         this.z = par1DataInput.readInt();
      }

   }

   @Overwrite
   public void handleOpenWindow(EntityClientPlayerMP player) {
      WorldClient world = player.worldObj.getAsWorldClient();
      TileEntity tile_entity = world.getBlockTileEntity(this.x, this.y, this.z);
      if (this.hasTileEntity() && tile_entity == null) {
         Minecraft.setErrorMessage("handleOpenWindow: no tile entity found at " + StringHelper.getCoordsAsString(this.x, this.y, this.z));
      }

      if (this.inventoryType == 0) {
         player.displayGUIChest(this.x, this.y, this.z, new InventoryBasic(this.windowTitle, this.useProvidedWindowTitle, this.slotsCount));
         player.openContainer.windowId = this.windowId;
      } else if (this.inventoryType == 1) {
         player.displayGUIWorkbench(this.x, this.y, this.z);
         player.openContainer.windowId = this.windowId;
      } else if (this.inventoryType == 2) {
         TileEntityFurnace var4 = (TileEntityFurnace)tile_entity;
         if (this.useProvidedWindowTitle) {
            var4.setCustomInvName(this.windowTitle);
         }

         player.displayGUIFurnace(var4);
         player.openContainer.windowId = this.windowId;
      } else if (this.inventoryType == 3) {
         TileEntityDispenser var7 = (TileEntityDispenser)tile_entity;
         if (this.useProvidedWindowTitle) {
            var7.setCustomInvName(this.windowTitle);
         }

         player.displayGUIDispenser(var7);
         player.openContainer.windowId = this.windowId;
      } else if (this.inventoryType == 4) {
         player.displayGUIEnchantment(this.x, this.y, this.z, this.useProvidedWindowTitle ? this.windowTitle : null);
         player.openContainer.windowId = this.windowId;
      } else if (this.inventoryType == 5) {
         TileEntityBrewingStand var5 = (TileEntityBrewingStand)tile_entity;
         if (this.useProvidedWindowTitle) {
            var5.setCustomInvName(this.windowTitle);
         }

         player.displayGUIBrewingStand(var5);
         player.openContainer.windowId = this.windowId;
      } else if (this.inventoryType == 6) {
         player.displayGUIMerchant(new NpcMerchant(player), this.useProvidedWindowTitle ? this.windowTitle : null);
         player.openContainer.windowId = this.windowId;
      } else if (this.inventoryType == 7) {
         TileEntityBeacon var8 = (TileEntityBeacon)tile_entity;
         player.displayGUIBeacon(var8);
         if (this.useProvidedWindowTitle) {
            var8.setCustomInvName(this.windowTitle);
         }

         player.openContainer.windowId = this.windowId;
      } else if (this.inventoryType == 8) {
         tile_entity.setCustomInvName(this.windowTitle);
         player.displayGUIAnvil(this.x, this.y, this.z);
         player.openContainer.windowId = this.windowId;
      } else {
         TileEntityHopper var3;
         if (this.inventoryType == 9) {
            var3 = (TileEntityHopper)tile_entity;
            if (this.useProvidedWindowTitle) {
               var3.setCustomInvName(this.windowTitle);
            }

            player.displayGUIHopper(var3);
            player.openContainer.windowId = this.windowId;
         } else if (this.inventoryType == 10) {
            TileEntityDropper var6 = (TileEntityDropper)tile_entity;
            if (this.useProvidedWindowTitle) {
               var6.setCustomInvName(this.windowTitle);
            }

            player.displayGUIDispenser(var6);
            player.openContainer.windowId = this.windowId;
         } else if (this.inventoryType == 11) {
            Entity var9 = this.getEntityByID(player, this.field_111008_f);
            if (var9 instanceof EntityHorse) {
               player.displayGUIHorse((EntityHorse)var9, new AnimalChest(this.windowTitle, this.useProvidedWindowTitle, this.slotsCount));
               player.openContainer.windowId = this.windowId;
            }
         } else if (this.inventoryType == 12) {
            player.displayGUIChestForMinecart(new InventoryBasic(this.windowTitle, this.useProvidedWindowTitle, this.slotsCount));
            player.openContainer.windowId = this.windowId;
         } else if (this.inventoryType == 13) {
            var3 = new TileEntityHopper();
            if (this.useProvidedWindowTitle) {
               var3.setCustomInvName(this.windowTitle);
            }

            player.displayGUIHopper(var3);
            player.openContainer.windowId = this.windowId;
         } else if (this.inventoryType == 14) {
            player.displayGUIEnchantReserver(this.x, this.y, this.z, new EnchantReserverSlots(new InventoryBasic(this.windowTitle, this.useProvidedWindowTitle, this.slotsCount)));
            player.openContainer.windowId = this.windowId;
         } else {
            Minecraft.setErrorMessage("handleOpenWindow: type not handled " + this.inventoryType);
         }
      }

   }

   @Shadow
   private Entity getEntityByID(EntityPlayer player, int id) {
      return null;
   }

   @Shadow
   public boolean hasCoords() {
      return false;
   }

   @Shadow
   public boolean hasTileEntity() {
      return false;
   }
}
