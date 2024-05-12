package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import net.oilcake.mitelros.block.BlockMetalbench;
import net.oilcake.mitelros.block.BlockTorchIdle;
import net.oilcake.mitelros.block.BlockWoodbench;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemBlock.class)
public class ItemBlockMixin extends Item{
    @ModifyConstant(method = {
            "<init>",
    }, constant = @Constant(intValue = 256))
    private static int ExtendedBlockID(int value) {
        return net.oilcake.mitelros.util.Constant.Extended_Block_ID;
    }
    @Shadow
    public Block getBlock() {
        return null;
    }
    @ModifyVariable(method = "getItemStackForStatsIcon", at = @At("RETURN"), ordinal = 1)
    private int ExtendedFlowerPotID(int id) {
        Block block = this.getBlock();
        if (block == Blocks.flowerPotExtend) {
            id = Item.flowerPot.itemID;
        }
        return id;
    }
    @Overwrite
    public String getItemDisplayName(ItemStack item_stack)
    {
        if(item_stack != null && this.getBlock() == Block.workbench){
            return Translator.get("tile.toolbench." + BlockWorkbench.getToolMaterial(item_stack.getItemSubtype()).getName() + ".name");
        }else if(item_stack != null && this.getBlock() == Blocks.metalbench){
            return Translator.get("tile.toolbench." + BlockMetalbench.getToolMaterial(item_stack.getItemSubtype()).getName() + ".name");
        }else if(item_stack != null && this.getBlock() == Blocks.woodbench){
            return Translator.get("tile.toolbench." + BlockWoodbench.getToolMaterial(item_stack.getItemSubtype()).getName() + ".name");
        }else {
            return super.getItemDisplayName(item_stack);
        }
    }
    @Overwrite
    public int getBurnTime(ItemStack item_stack) {
        if (!this.canBurnAsFuelSource()) {
            return 0;
        } else {
            Block block = this.getBlock();
            if (block == Block.wood) {
                return 1600;
            }else if(block == Block.planks || block == Block.woodDoubleSlab || block == Block.woodenButton){
                return 400;
            }else if(block == Block.woodSingleSlab || block == Block.sapling || block == Block.deadBush) {
                return 200;
            }else if(block == Block.torchWood) {
                return 800;
            }else if(block instanceof BlockRedstoneTorch) {
                return 100;
            }else if(block instanceof BlockTorchIdle) {
                return 25;
            }else if(block.blockMaterial == Material.wood){
                return 400;
            }else {
                return block == Block.coalBlock ? 16000 : 0;
            }
        }
    }
}
