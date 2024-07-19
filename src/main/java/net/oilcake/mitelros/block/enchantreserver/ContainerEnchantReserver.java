package net.oilcake.mitelros.block.enchantreserver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.Container;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemPotion;
import net.minecraft.ItemRock;
import net.minecraft.ItemStack;
import net.minecraft.Slot;
import net.oilcake.mitelros.block.BlockEnchantReserver;
import net.oilcake.mitelros.network.PacketEnchantReserverInfo;

public class ContainerEnchantReserver extends Container {
   @Nullable
   private final TileEntityEnchantReserver tileEntityEnchantReserver;
   private final EnchantReserverSlots slots;
   private final int blockX;
   private final int blockY;
   private final int blockZ;
   private int lastEXP;

   public void addSlot(Slot slot) {
      this.addSlotToContainer(slot);
   }

   public ContainerEnchantReserver(EnchantReserverSlots slots, EntityPlayer player, int x, int y, int z) {
      super(player);
      this.blockX = x;
      this.blockY = y;
      this.blockZ = z;
      slots.initSlots(this);
      this.slots = slots;
      if (!player.getWorld().isRemote) {
         this.tileEntityEnchantReserver = (TileEntityEnchantReserver)player.getWorldServer().getBlockTileEntity(x, y, z);
      } else {
         this.tileEntityEnchantReserver = null;
      }

      this.onCraftMatrixChanged(slots);

      int index;
      for(index = 0; index < 3; ++index) {
         for(int var8 = 0; var8 < 9; ++var8) {
            this.addSlot(new Slot(player.inventory, var8 + index * 9 + 9, 8 + var8 * 18, 84 + index * 18));
         }
      }

      for(index = 0; index < 9; ++index) {
         this.addSlot(new Slot(player.inventory, index, 8 + index * 18, 142));
      }

   }

   public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
      ItemStack itemstack = null;
      Slot slot = (Slot)this.inventorySlots.get(index);
      if (slot != null && slot.getHasStack()) {
         ItemStack itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();
         if (index < this.slots.getSize()) {
            if (!this.mergeItemStack(itemstack1, this.slots.getSize(), this.inventorySlots.size(), false)) {
               return null;
            }
         } else if (itemstack1.getItem() instanceof ItemPotion) {
            if (!this.mergeItemStack(itemstack1, this.slots.getInputIndex(), this.slots.getInputIndex() + 1, false)) {
               return null;
            }
         } else if (itemstack1.getItem() instanceof ItemRock && !this.mergeItemStack(itemstack1, this.slots.getOutputIndex(), this.slots.getOutputIndex() + 1, false)) {
            return null;
         }

         if (itemstack1.stackSize == 0) {
            slot.putStack((ItemStack)null);
         } else {
            slot.onSlotChanged();
         }

         if (itemstack1.stackSize == itemstack.stackSize) {
            return null;
         }

         slot.onPickupFromSlot(playerIn, itemstack1);
      }

      return itemstack;
   }

   public List getInventory() {
      List nonnulllist = new ArrayList();
      Iterator var2 = this.inventorySlots.iterator();

      while(var2.hasNext()) {
         Object o = var2.next();
         nonnulllist.add(((Slot)o).getStack());
      }

      return nonnulllist;
   }

   public void onContainerClosed(EntityPlayer par1EntityPlayer) {
      super.onContainerClosed(par1EntityPlayer);
      if (!this.world.isRemote) {
         this.slots.onContainerClosed();
      }

   }

   void updateInfo() {
      if (!this.world.isRemote) {
         this.player.sendPacket(new PacketEnchantReserverInfo(this.tileEntityEnchantReserver.getEXP()));
      }

   }

   public boolean canInteractWith(EntityPlayer player) {
      if (this.world.getBlock(this.blockX, this.blockY, this.blockZ) instanceof BlockEnchantReserver && this.world.getBlockTileEntity(this.blockX, this.blockY, this.blockZ) instanceof TileEntityEnchantReserver) {
         return player.getDistanceSq((double)this.blockX + 0.5, (double)this.blockY + 0.5, (double)this.blockZ + 0.5) <= 64.0;
      } else {
         return false;
      }
   }
}
