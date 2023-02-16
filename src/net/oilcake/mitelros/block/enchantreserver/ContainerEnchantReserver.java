package net.oilcake.mitelros.block.enchantreserver;

import net.minecraft.*;
import net.oilcake.mitelros.block.BlockEnchantReserver;
import net.oilcake.mitelros.util.network.PacketEnchantReserverInfo;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ContainerEnchantReserver extends Container {
    @Nullable
    private final TileEntityEnchantReserver tileEntityEnchantReserver;
    private final EnchantReserverSlots slots;
    private final int blockX,blockY,blockZ;
    private int lastEXP;



    public void addSlot(Slot slot){
        this.addSlotToContainer(slot);
    }
    public ContainerEnchantReserver(EnchantReserverSlots slots,EntityPlayer player, int x, int y, int z) {
        super(player);
        this.blockX = x;
        this.blockY = y;
        this.blockZ = z;
        slots.initSlots(this);
        this.slots = slots;
        if (!player.getWorld().isRemote) {
            this.tileEntityEnchantReserver = (TileEntityEnchantReserver) player.getWorldServer().getBlockTileEntity(x, y, z);
        }else {
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
//    @Override
//    public void detectAndSendChanges() {
//        super.detectAndSendChanges();
//        for (Object o : this.crafters) {
//            ICrafting var2 = (ICrafting) o;
//            if (this.lastEXP != this.tileEntityEnchantReserver.getEXP()) {
//                var2.sendProgressBarUpdate(this, 0, this.tileEntityEnchantReserver.EXP);
//            }
//        }
//        this.lastEXP = this.tileEntityEnchantReserver.EXP;
//    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < this.slots.getSize()){
                if (!this.mergeItemStack(itemstack1,this.slots.getSize(),this.inventorySlots.size(),false)){
                    return null;
                }
            }else {
                if (itemstack1.getItem() instanceof ItemPotion){
                    if (!this.mergeItemStack(itemstack1,this.slots.getInputIndex(),this.slots.getInputIndex() + 1,false)){
                            return null;
                    }
                }else if (itemstack1.getItem() instanceof ItemRock){
                    if (!this.mergeItemStack(itemstack1,this.slots.getOutputIndex(),this.slots.getOutputIndex() + 1,false)){
                            return null;
                        }
                    }
                }
            if (itemstack1.stackSize == 0) {
                slot.putStack(null);
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

    public List<ItemStack> getInventory() {
        List<ItemStack> nonnulllist = new ArrayList<>();

        for (Object o : this.inventorySlots) {
            nonnulllist.add(((Slot) o).getStack());
        }
        return nonnulllist;
    }
    @Override
    public void onContainerClosed(EntityPlayer par1EntityPlayer) {
        super.onContainerClosed(par1EntityPlayer);
        if (!this.world.isRemote){
            this.slots.onContainerClosed();
        }
    }

    void updateInfo() {
        if (!this.world.isRemote) {
            this.player.sendPacket(new PacketEnchantReserverInfo(this.tileEntityEnchantReserver.getEXP()));
            //System.out.println("发包, EXP=" + this.tileEntityEnchantReserver.getEXP());
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        if (this.world.getBlock(this.blockX, this.blockY, this.blockZ) instanceof BlockEnchantReserver && this.world.getBlockTileEntity(this.blockX, this.blockY, this.blockZ) instanceof TileEntityEnchantReserver) {
            return player.getDistanceSq((double)this.blockX + 0.5D, (double)this.blockY + 0.5D, (double)this.blockZ + 0.5D) <= 64.0D;
        } else {
            return false;
        }
    }
}
