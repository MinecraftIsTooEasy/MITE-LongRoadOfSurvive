package net.oilcake.mitelros.block.enchantreserver;

import net.minecraft.*;
import net.oilcake.mitelros.block.BlockEnchantReserver;
import net.oilcake.mitelros.item.Items;

import java.util.Arrays;

public class TileEntityEnchantReserver extends TileEntity implements IInventory {

    private final EnchantReserverSlots slots = new EnchantReserverSlots(this);
    private final ItemStack[] items = new ItemStack[EnchantReserverSlots.slotSize];

    private boolean isUsing;
    public void setItem(int index,ItemStack itemStack) {
        this.items[index] = itemStack;
    }

    public int getSizeInventory() {
        return EnchantReserverSlots.slotSize;
    }
    public boolean isUsing() {
        return this.isUsing;
    }
    public ItemStack getItem(int index) {
        return this.items[index];
    }
    public EnchantReserverSlots getSlots() {
        return this.slots;
    }

    private final int MAX_EXP = 32000;
    public int EXP;
    public int getEXP() {
        return this.EXP;
    }

    public int getMAXEXP() {
        return MAX_EXP;
    }


    public void updateEntity() {
        if (!this.getWorldObj().isRemote) {
            ItemStack inputStack = this.slots.getInPutStack();
            if (inputStack != null) {
//                if (inputStack.itemID == Item.diamond.itemID || inputStack.itemID == Item.dyePowder.itemID && inputStack.getItemSubtype() == 4 ||
//                        inputStack.itemID == Item.emerald.itemID || inputStack.itemID == Item.netherQuartz.itemID) {
//                    if (this.getEXP() < this.getMAXEXP()) {
//                        int size = inputStack.stackSize;
//                        this.EXP += ItemRock.getExperienceValueWhenSacrificed(inputStack) * size;
//                        this.slots.getInPut().putStack(null);
//                        this.slots.updateInfo();
//                    }
//                }
                if (inputStack.itemID == Item.diamond.itemID && inputStack.stackSize * 500 + this.getEXP() <= this.getMAXEXP()){
                    int size = inputStack.stackSize;
                        this.EXP += 500 * size;
                        this.slots.getInPut().putStack(null);
                        this.slots.updateInfo();
                }
                else if (inputStack.itemID == Item.emerald.itemID && inputStack.stackSize * 250 + this.getEXP() <= this.getMAXEXP()){
                    int size = inputStack.stackSize;
                    this.EXP += 250 * size;
                    this.slots.getInPut().putStack(null);
                    this.slots.updateInfo();
                }
                else if (inputStack.itemID == Item.netherQuartz.itemID && inputStack.stackSize * 50 + this.getEXP() <= this.getMAXEXP()){
                    int size = inputStack.stackSize;
                    this.EXP += 50 * size;
                    this.slots.getInPut().putStack(null);
                    this.slots.updateInfo();
                }
                else if (inputStack.itemID == Item.dyePowder.itemID && inputStack.getItemSubtype() == 4 && inputStack.stackSize * 25 + this.getEXP() <= this.getMAXEXP()){
                    int size = inputStack.stackSize;
                    this.EXP += 25 * size;
                    this.slots.getInPut().putStack(null);
                    this.slots.updateInfo();
                }
                else{}
            }
            if (this.getEXP() >= 200) {
                ItemStack outputStack = this.slots.getOutPutStack();
                if (outputStack != null) {
                    int size = outputStack.stackSize;
                    if (outputStack.itemID == Item.potion.itemID && outputStack.stackSize * 200 <= this.getEXP() + 2000) {
                        this.EXP -= 200;
                        this.slots.getOutPut().putStack(Item.expBottle.getItemStackForStatsIcon());
                        this.slots.updateInfo();
                    }
                    if (outputStack.itemID == Items.nickelNugget.itemID && outputStack.stackSize * 50 <= this.getEXP() + 2000) {
                        this.EXP -= 50 * size;
                        this.slots.getOutPut().putStack(Items.nickelCoin.getItemStackForStatsIcon());
                        this.slots.updateInfo();
                    }
                    if (outputStack.itemID == Items.tungstenNugget.itemID && outputStack.stackSize * 5000 <= this.getEXP() + 2000) {
                        this.EXP -= 5000 * size;
                        this.slots.getOutPut().putStack(Items.tungstenCoin.getItemStackForStatsIcon());
                        this.slots.updateInfo();
                    }
                }
            }

        }
    }




    @Override
    public ItemStack getStackInSlot(int i) {
        return this.items[i];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (this.items[index] != null) {
            ItemStack var3;
            if (this.items[index].stackSize <= count) {
                var3 = this.items[index];
                this.items[index] = null;
            } else {
                var3 = this.items[index].splitStack(count);
                if (this.items[index].stackSize == 0) {
                    this.items[index] = null;
                }
            }

            return var3;
        } else {
            return null;
        }
    }
    @Override
    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.items[par1] != null) {
            ItemStack var2 = this.items[par1];
            this.items[par1] = null;
            return var2;
        } else {
            return null;
        }
    }
    @Override
    //setItem
    public void setInventorySlotContents(int var1, ItemStack var2) {
        this.items[var1] = var2;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        if (player.getWorld().getBlock(this.xCoord, this.yCoord, this.zCoord) instanceof BlockEnchantReserver && player.getWorld().getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) instanceof TileEntityEnchantReserver) {
            return player.getDistance((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
        } else {
            return false;
        }
    }

    private String customName;
    public boolean b() {
        return this.customName != null && this.customName.length() > 0;
    }



    public void openChest() {
        this.slots.updateInfo();
        this.isUsing = true;
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        this.EXP = par1NBTTagCompound.getInteger("EXP");
        this.slots.readFromNBT(par1NBTTagCompound, this);
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("EXP", this.EXP);
        if (this.b()) {
            par1NBTTagCompound.setString("CustomName", this.customName);
        }

        this.slots.writeToNBT(par1NBTTagCompound);
    }

    public void closeChest() {
        this.isUsing = false;
    }

    public boolean isItemValidForSlot(int var1, ItemStack var2) {
        return this.slots.isItemValidForSlot(var1, var2);
    }
    public void dropAllItems() {
        this.slots.dropItems(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
    }

    public void destroyInventory() {
        Arrays.fill(this.items, null);
    }

}
