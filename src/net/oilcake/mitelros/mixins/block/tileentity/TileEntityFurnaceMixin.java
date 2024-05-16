package net.oilcake.mitelros.mixins.block.tileentity;

import net.minecraft.*;
import net.oilcake.mitelros.block.BlockBlastFurnace;
import net.oilcake.mitelros.block.BlockSmoker;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
    private boolean activated = false;
    @Overwrite
    public static int getHeatLevelRequired(int item_id) {
        Item item = Item.getItem(item_id);
        if (item instanceof ItemTool){
            return item.getHardestMetalMaterial() == Materials.tungsten ? 4 : item.getHardestMetalMaterial() == Material.rusted_iron ? 2 : 3;
        }else if(item instanceof ItemArmor){
            return item.getHardestMetalMaterial() == Materials.tungsten ? 4 : item.getHardestMetalMaterial() == Material.rusted_iron ? 2 : 3;
        }
        if (item_id == Block.oreAdamantium.blockID || item_id == Items.pieceAdamantium.itemID || item_id == Blocks.oreUru.blockID || item_id == Items.pieceUru.itemID) {
            return 4;
        } else if (item_id == Block.oreMithril.blockID || item_id == Blocks.oreTungsten.blockID || item_id == Items.pieceMithril.itemID || item_id == Items.pieceTungsten.itemID || item_id == Items.AncientmetalArmorPiece.itemID) {
            return 3;
        } else if (item_id == Block.oreCopper.blockID || item_id == Block.oreSilver.blockID || item_id == Block.oreGold.blockID || item_id == Block.oreIron.blockID ||
                item_id == Blocks.oreNickel.blockID || item_id == Items.pieceCopper.itemID || item_id == Items.pieceSilver.itemID || item_id == Items.pieceGold.itemID || item_id == Items.pieceGoldNether.itemID ||
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
        } else if(this.getInputItemStack().getItem() instanceof ItemFood && this.isBlastFurnace()){
            return false;
        } else if(this.getInputItemStack().getItem() instanceof ItemArmor && !this.isBlastFurnace()){
            return false;
        } else if(this.getInputItemStack().getItem() instanceof ItemTool && !this.isBlastFurnace()){
            return false;
        } else if(!(this.getInputItemStack().getItem() instanceof ItemFood) && this.isSmoker()){
            return false;
        } else if(heat_level > getHeatLevelRequired(this.getInputItemStack().getItem().itemID) + 1){
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
    public boolean isBlastFurnace(){
        return this.getFurnaceBlock() instanceof BlockBlastFurnace;
    }
    public boolean isSmoker(){
        return this.getFurnaceBlock() instanceof BlockSmoker;
    }
    public boolean canBurnbyItself(){
        return this.getFuelHeatLevel() > 2;
    }
    public void activateFurnace(){
        this.activated = true;
    }
    private boolean canNormallyWork(){
        return this.activated && this.furnaceItemStacks[1] != null;
    }
    @Overwrite
    public void updateEntity() {
        if (!this.worldObj.isRemote && !this.isBurning() && this.activated && this.furnaceItemStacks[1] == null){
            this.activated = false;
        }
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
                if (this.furnaceBurnTime == 0 && this.canSmelt(this.getFuelHeatLevel()) && (canNormallyWork() || this.canBurnbyItself())) {
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
                            if (this.isBlastFurnace()){
                                this.worldObj.playSoundEffect((double)((float)this.xCoord + 0.5F), (double)((float)this.yCoord + 0.5F), (double)((float)this.zCoord + 0.5F), "imported.random.melting");
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt(this.heat_level)) {
                    this.activateFurnace();
                    int temp = 200;
                    int item_id = this.getInputItemStack().itemID;
                    int speed_bonus = 1;
                    if(item_id == Items.pieceCopper.itemID || item_id == Items.pieceSilver.itemID || item_id == Items.pieceGold.itemID || item_id == Items.pieceGoldNether.itemID ||
                            item_id == Items.pieceIron.itemID || item_id == Items.pieceNickel.itemID){
                        speed_bonus = 4;
                    }
                    if(item_id == Items.pieceMithril.itemID || item_id == Items.pieceTungsten.itemID || item_id == Items.pieceAdamantium.itemID){
                        speed_bonus = 2;
                    }
                    this.furnaceCookTime += speed_bonus;
                    if (this.getFurnaceBlock() instanceof BlockBlastFurnace) {
                        this.furnaceCookTime += speed_bonus;
                    } else if (this.getFurnaceBlock() instanceof BlockSmoker) {
                        this.furnaceCookTime += speed_bonus;
                    }
                    if (this.furnaceCookTime >= temp) {
                        this.furnaceCookTime = 0;
                        this.smeltItem(this.heat_level);
                        //烧肉提示
                        if(this.getInputItemStack()!= null && this.getOutputItemStack().getItem() instanceof ItemMeat){
                            this.worldObj.playSoundEffect((double)((float)this.xCoord + 0.5F), (double)((float)this.yCoord + 0.5F), (double)((float)this.zCoord + 0.5F), "imported.random.sizzle");
                        }
                        //烧水提示
                        if(this.getInputItemStack()!= null && this.getOutputItemStack().getItem().hasMaterial(Materials.dangerous_water, Materials.unsafe_water, Material.water)){
                            this.worldObj.playSoundEffect((double)((float)this.xCoord + 0.5F), (double)((float)this.yCoord + 0.5F), (double)((float)this.zCoord + 0.5F), "imported.random.boil");
                        }
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
            this.activated = false;
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
    @Overwrite
    public void smeltItem(int heat_level) {
        if (this.canSmelt(heat_level)) {
            ItemStack var1 = RecipesFurnace.smelting().getSmeltingResult(this.getInputItemStack(), heat_level);
            ItemStack var10000;
            if (this.furnaceItemStacks[2] == null) {
                this.furnaceItemStacks[2] = var1.copy();
            } else if (this.furnaceItemStacks[2].itemID == var1.itemID) {
                var10000 = this.furnaceItemStacks[2];
                var10000.stackSize += var1.stackSize;
            }

            byte consumption;
            if (this.getInputItemStack().itemID == Block.sand.blockID && var1.itemID == Block.sandStone.blockID) {
                consumption = 4;
            } else if (this.getInputItemStack().itemID == Block.sand.blockID && var1.itemID == Block.glass.blockID) {
                consumption = 4;
            } else if (this.getInputItemStack().itemID == Items.claybowlRaw.itemID && var1.itemID == Items.claybowlEmpty.itemID) {
                consumption = 4;
            } else {
                consumption = 1;
            }

            var10000 = this.getInputItemStack();
            var10000.stackSize -= consumption;
            if (this.getInputItemStack().getItem() == Item.clay && var1.getItem() == Item.brick) {
                int extra_converted = Math.min(this.getOutputItemStack().getMaxStackSize() - this.getOutputItemStack().stackSize, this.getInputItemStack().stackSize);
                if (extra_converted > 3) {
                    extra_converted = 3;
                }

                var10000 = this.getOutputItemStack();
                var10000.stackSize += extra_converted;
                var10000 = this.getInputItemStack();
                var10000.stackSize -= extra_converted;
            }

            if (this.furnaceItemStacks[0].stackSize <= 0) {
                this.furnaceItemStacks[0] = null;
            }
        }

    }
    @Inject(method = "readNBT", at = @At("RETURN"))
    public void injectReadNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
        //else if (par1NBTTagCompound.hasKey("water")){
        this.activated = par1NBTTagCompound.getBoolean("activated");
        //  }
    }
    @Inject(method = "writeNBT", at = @At("RETURN"))
    public void injectWriteNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
        par1NBTTagCompound.setBoolean("activated", this.activated);
    }
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

