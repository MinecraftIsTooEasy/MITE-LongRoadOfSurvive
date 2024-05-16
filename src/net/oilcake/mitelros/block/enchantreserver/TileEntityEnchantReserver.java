package net.oilcake.mitelros.block.enchantreserver;

import net.minecraft.*;
import net.oilcake.mitelros.block.BlockEnchantReserver;
import net.oilcake.mitelros.item.Items;

import java.util.Arrays;

public class TileEntityEnchantReserver extends TileEntity implements IWorldInventory {
    private static final int[] slots_top = new int[]{0};
    private static final int[] slots_bottom = new int[]{1};
    private static final int[] slots_sides = new int[]{1};

    private final EnchantReserverSlots slots = new EnchantReserverSlots(this);
    private final ItemStack[] items = new ItemStack[EnchantReserverSlots.slotSize];
    public EntityPlayer player;

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
    private int EXP;
    private int last_EXP = -999999;
    public int getEXP() {
        return this.EXP;
    }
    public int setEXP(int exp) {
        return this.EXP = exp;
    }

    public int getMAXEXP() {
        return MAX_EXP;
    }


//    @Override
//    public void onInventoryChanged() {
//        super.onInventoryChanged();
//        if(this.getEXP() != this.last_EXP){
//            this.slots.updateInfo();
//        }
//    }
    @Override
    public int[] getSlotsForFace(int par1) {
        return par1 == 0 ? slots_bottom : (par1 == 1 ? slots_top : slots_sides);
    }
    @Override
    public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) {
        return this.isItemValidForSlot(par1, par2ItemStack);
    }
    @Override
    public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3) {
        if (par3 == 0 && par1 == 1) {
            return !(par2ItemStack.getItem() instanceof ItemNugget) && par2ItemStack.getItem() != Item.potion;
        } else {
            return true;
        }
    }
    public void updateEntity() {
        this.slots.updateInfo();
        if (!this.getWorldObj().isRemote) {
            if(this.getEXP() != this.last_EXP){
                this.last_EXP = EXP;
            }
            ItemStack inputStack = this.slots.getInPutStack();
            if (inputStack != null) {
                Item rock = inputStack.getItem();
                if(rock instanceof ItemRock && !(rock instanceof ItemShard)){
                    int size = inputStack.stackSize;
                    int experience = ItemRock.getExperienceValueWhenSacrificed(inputStack);
                    if(inputStack.stackSize * experience + this.getEXP() <= this.getMAXEXP()){
                        this.EXP += size * experience;
                        this.slots.getInPut().putStack(null);
                    }
                }
            }

                ItemStack outputStack = this.slots.getOutPutStack();
                if (outputStack != null) {
                    if (this.getEXP() >= 200) {
                        if (outputStack.itemID == Item.potion.itemID && outputStack.stackSize * 200 <= this.getEXP() - 2000) {
                            this.EXP -= 200 * outputStack.stackSize;
                            this.slots.getOutPut().putStack(Item.expBottle.getItemStackForStatsIcon());
                        }
                    }
                    if (this.getEXP() >= 5) {
                        if (outputStack.itemID == Item.copperNugget.itemID && outputStack.stackSize * 5 <= this.getEXP() - 2000) {
                            this.EXP -= 5 * outputStack.stackSize;
                            //* size;
                            this.slots.getOutPut().putStack(new ItemStack(Item.coinCopper, outputStack.stackSize));
                        }
                    }
                    if (this.getEXP() >= 25) {
                        if (outputStack.itemID == Item.silverNugget.itemID && outputStack.stackSize * 25 <= this.getEXP() - 2000) {
                            this.EXP -= 25 * outputStack.stackSize;
                            //* size;
                            this.slots.getOutPut().putStack(new ItemStack(Item.coinSilver, outputStack.stackSize));
                        }
                    }
                    if (this.getEXP() >= 50) {
                        if (outputStack.itemID == Items.nickelNugget.itemID && outputStack.stackSize * 50 <= this.getEXP() - 2000) {
                            this.EXP -= 50 * outputStack.stackSize;
                            //* size;
                            this.slots.getOutPut().putStack(new ItemStack(Items.nickelCoin, outputStack.stackSize));
                        }
                    }
                    if (this.getEXP() >= 100) {
                        if (outputStack.itemID == Item.goldNugget.itemID && outputStack.stackSize * 100 <= this.getEXP() - 2000) {
                            this.EXP -= 100 * outputStack.stackSize;
                                    //* size;
                            this.slots.getOutPut().putStack(new ItemStack(Item.coinGold, outputStack.stackSize));
                        }
                    }
                    if (this.getEXP() >= 500) {
                        if (outputStack.itemID == Item.ancientMetalNugget.itemID && outputStack.stackSize * 500 <= this.getEXP() - 2000) {
                            this.EXP -= 500 * outputStack.stackSize;
                            //* size;
                            this.slots.getOutPut().putStack(new ItemStack(Item.coinAncientMetal, outputStack.stackSize));
                        }
                    }
                    if (this.getEXP() >= 2500) {
                        if (outputStack.itemID == Item.mithrilNugget.itemID && outputStack.stackSize * 2500 <= this.getEXP() - 2000) {
                            this.EXP -= 2500 * outputStack.stackSize;
                            //* size;
                            this.slots.getOutPut().putStack(new ItemStack(Item.coinMithril, outputStack.stackSize));
                        }
                    }
                    if (this.getEXP() >= 5000) {
                        if (outputStack.itemID == Items.tungstenNugget.itemID && outputStack.stackSize * 5000 <= this.getEXP() - 2000) {
                            this.EXP -= 5000 * outputStack.stackSize;
                            this.slots.getOutPut().putStack(new ItemStack(Items.tungstenCoin, outputStack.stackSize));
                        }
                    }
                    if (this.getEXP() >= 10000) {
                        if (outputStack.itemID == Item.adamantiumNugget.itemID && outputStack.stackSize * 10000 <= this.getEXP() - 2000) {
                            this.EXP -= 10000 * outputStack.stackSize;
                            //* size;
                            this.slots.getOutPut().putStack(new ItemStack(Item.coinAdamantium, outputStack.stackSize));
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
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        if (player.getWorld().getBlock(this.xCoord, this.yCoord, this.zCoord) instanceof BlockEnchantReserver && player.getWorld().getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) instanceof TileEntityEnchantReserver) {
            return player.getDistance((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
        } else {
            return false;
        }
    }

    public void openChest() {
        this.slots.updateInfo();
        this.isUsing = true;
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        this.EXP = par1NBTTagCompound.getInteger("EXP");
        this.slots.readFromNBT(par1NBTTagCompound, this);
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("EXP", this.EXP);
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
