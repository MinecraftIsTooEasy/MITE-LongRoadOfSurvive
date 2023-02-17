package net.oilcake.mitelros.mixins.block.tileentity;

import net.minecraft.*;
import net.oilcake.mitelros.block.BlockBlastFurnace;
import net.oilcake.mitelros.block.BlockSmoker;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TileEntityFurnace.class)
public class TileEntityFurnaceMixin extends TileEntity implements IWorldInventory {
    @Shadow
    private ItemStack[] furnaceItemStacks = new ItemStack[3];
    @Shadow
    public int currentItemBurnTime;
    @Shadow
    public int furnaceBurnTime;
    @Shadow
    public int furnaceCookTime;
    @Shadow
    public int heat_level = 0;
    @Overwrite
    public static int getHeatLevelRequired(int item_id) {
        if (item_id == Block.oreAdamantium.blockID || item_id == Items.pieceAdamantium.itemID) {
            return 4;
        } else if (item_id == Block.oreMithril.blockID || item_id == Blocks.oreTungsten.blockID || item_id == Items.pieceMithril.itemID || item_id == Items.pieceTungsten.itemID) {
            return 3;
        } else if (item_id == Block.oreCopper.blockID || item_id == Block.oreSilver.blockID || item_id == Block.oreGold.blockID || item_id == Block.oreIron.blockID ||
                item_id == Blocks.oreNickel.blockID || item_id == Items.pieceSilver.itemID || item_id == Items.pieceGold.itemID || item_id == Items.pieceGoldNether.itemID ||
                item_id == Items.pieceIron.itemID || item_id == Items.pieceNickel.itemID || item_id == Block.oreNetherQuartz.blockID || item_id == Block.oreEmerald.blockID ||
                item_id == Block.oreDiamond.blockID || item_id == Block.oreRedstone.blockID || item_id == Block.oreLapis.blockID || item_id == Block.sandStone.blockID) {
            return 2;
        } else {
            return 1;
        }
    }

    @Overwrite
    private boolean canSmelt(int heat_level) {
        if (this.furnaceItemStacks[0] == null) {
            return false;
        } else if(this.getInputItemStack().getItem() instanceof ItemFood && this.getFurnaceBlock() instanceof BlockBlastFurnace){
            return false;
        } else if(!(this.getInputItemStack().getItem() instanceof ItemFood) && this.getFurnaceBlock() instanceof BlockSmoker){
            return false;
        } else {
            BlockFurnace furnace = this.getFurnaceBlock();
            if (furnace == null || !this.acceptsLargeItems() && Slot.isLargeItem(this.getInputItemStack().getItem())) {
                return false;
            } else {
                ItemStack var1 = RecipesFurnace.smelting().getSmeltingResult(this.getInputItemStack(), heat_level);
                if (var1 == null) {
                    return false;
                } else {
                    ItemStack output_item_stack = this.getOutputItemStack();
                    return output_item_stack == null ? true : (!output_item_stack.isItemStackEqualC(var1, true, false, false, true) ? false : (output_item_stack.stackSize < this.getInventoryStackLimit() && output_item_stack.stackSize < output_item_stack.getMaxStackSize() ? true : output_item_stack.stackSize < var1.getMaxStackSize()));
                }
            }
        }
    }

    //getCookProgressScaled
//    @Overwrite
//    public int d(int par1) {
//        return this.furnaceCookTime * par1 / 200;
//    }

//    @Overwrite
//    public int d(int par1) {
//        //return this.furnaceBurnTime * par1 / 10;
//        if (this.isItemValid() && this.getFurnaceBlock() instanceof BlockBlastFurnace) {
//            return this.furnaceItemStacks[0] == null ? this.furnaceCookTime * par1 / 200 : this.furnaceCookTime * par1 / (200 / Math.max(this.heat_level, 1));
//        }else {
//            return this.furnaceCookTime * par1 / 200;
//        }
//    }
    @Overwrite
    public void updateEntity() {
        if (this.worldObj.isRemote || this.furnaceBurnTime == 1 || !this.isFlooded() && !this.isSmotheredBySolidBlock()) {
            boolean var1 = this.furnaceBurnTime > 0;
            boolean var2 = false;
            if (this.furnaceBurnTime > 0) {
                float temp = 1.0F;
                if (this.getFurnaceBlock() instanceof BlockBlastFurnace || this.getFurnaceBlock() instanceof BlockSmoker) {
                    temp = 2.0F;
                }
                this.furnaceBurnTime -= temp;
            } else {
                this.heat_level = 0;
            }

            if (!this.worldObj.isRemote) {
                if (this.furnaceBurnTime == 0 && this.canSmelt(this.getFuelHeatLevel())) {
                    this.currentItemBurnTime = this.furnaceBurnTime = this.getItemBurnTime(this.furnaceItemStacks[1]);
                    if (this.furnaceBurnTime > 0) {
                        this.heat_level = this.getItemHeatLevel(this.furnaceItemStacks[1]);
                        var2 = true;
                        if (this.furnaceItemStacks[1] != null) {
                            --this.furnaceItemStacks[1].stackSize;
                            if (this.furnaceItemStacks[1].stackSize == 0) {
                                Item var3 = this.furnaceItemStacks[1].getItem().getContainerItem();
                                this.furnaceItemStacks[1] = var3 != null ? new ItemStack(var3) : null;
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt(this.heat_level)) {
                    ++this.furnaceCookTime;
                    int temp = 200;
                    if (this.getFurnaceBlock() instanceof BlockBlastFurnace) {
                        ++this.furnaceCookTime;
                    } else if (this.getFurnaceBlock() instanceof BlockSmoker) {
                        ++this.furnaceCookTime;
                    }
                    if (this.furnaceCookTime == temp) {
                        this.furnaceCookTime = 0;
                        this.smeltItem(this.heat_level);
                        var2 = true;
                    }
                } else {
                    this.furnaceCookTime = 0;
                }
                if (var1 != this.furnaceBurnTime > 0) {
                    var2 = true;
                    BlockFurnace.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
                }

            }
            if (var2) {
                this.onInventoryChanged();
            }

        } else {
            if (this.furnaceBurnTime > 0) {
                if (this.isFlooded()) {
                    this.worldObj.blockFX(EnumBlockFX.steam, this.xCoord, this.yCoord, this.zCoord);
                }
                BlockFurnace.updateFurnaceBlockState(false, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
            this.furnaceBurnTime = 0;
            this.furnaceCookTime = 0;
        }
//        if(this.furnaceCookTime == 0){
//            BlockFurnace.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
//            this.onInventoryChanged();
//        }
    }
    @Shadow
    public boolean acceptsLargeItems() {
        return false;
    }
    @Shadow
    public boolean isBurning() {
        return false;
    }
    @Shadow
    public int getItemBurnTime(ItemStack item_stack) {
        return 1;
    }
    @Shadow
    public int getFuelHeatLevel() {
        return 1;
    }
    @Shadow
    public boolean isSmotheredBySolidBlock() {
        return false;
    }
    @Shadow
    public boolean isFlooded() {
        return false;
    }
    @Shadow
    public void smeltItem(int heat_level) {
    }


//    @Overwrite
//    public int e(int par1) {
//        if (this.currentItemBurnTime == 0) {
//            this.currentItemBurnTime = 200;
//        }
//        int i = 0;
//        if (this.isItemValid() && this.getFurnaceBlock() instanceof BlockBlastFurnace) {
//            if (this.getFurnaceBlock().blockMaterial == Material.stone) {
//                i = this.furnaceBurnTime * par1 * 10 / currentItemBurnTime ;
//            } else if (this.getFurnaceBlock().blockMaterial == Material.obsidian) {
//                i = this.furnaceBurnTime * par1 * 15 / currentItemBurnTime;
//            } else if (this.getFurnaceBlock().blockMaterial == Material.netherrack) {
//                i = this.furnaceBurnTime * par1 * 20 / currentItemBurnTime;
//            }
//        }
//        return i;
//    }

    @Shadow
    public BlockFurnace getFurnaceBlock() {
        return null;
    }
    @Shadow
    public ItemStack getOutputItemStack() {
        return this.furnaceItemStacks[2];
    }

    @Shadow
    public ItemStack getInputItemStack() {
        return this.furnaceItemStacks[0];
    }
    @Shadow
    public ItemStack getFuelItemStack() {
        return this.furnaceItemStacks[1];
    }
    @Shadow
    public int getItemHeatLevel(ItemStack item_stack) {
        return 0;
    }

    @Shadow
    public int[] getSlotsForFace(int i) {
        return new int[0];
    }

    @Shadow
    public boolean canInsertItem(int i, ItemStack itemStack, int i1) {
        return false;
    }

    @Shadow
    public boolean canExtractItem(int i, ItemStack itemStack, int i1) {
        return false;
    }

    @Shadow
    public int getSizeInventory() {
        return 0;
    }

    @Shadow
    public ItemStack getStackInSlot(int i) {
        return null;
    }

    @Shadow
    public ItemStack decrStackSize(int i, int i1) {
        return null;
    }

    @Shadow
    public ItemStack getStackInSlotOnClosing(int i) {
        return null;
    }

    @Shadow
    public void setInventorySlotContents(int i, ItemStack itemStack) {

    }

    @Shadow
    public int getInventoryStackLimit() {
        return 0;
    }

    @Shadow
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return false;
    }

    @Shadow
    public void openChest() {

    }

    @Shadow
    public void closeChest() {

    }

    @Shadow
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return false;
    }

    @Shadow
    public void destroyInventory() {

    }
}

