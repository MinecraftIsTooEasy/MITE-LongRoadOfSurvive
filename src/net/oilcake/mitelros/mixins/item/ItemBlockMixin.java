package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import net.oilcake.mitelros.block.BlockTorchIdle;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

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
    @Overwrite
    public ItemStack getItemStackForStatsIcon() {
        Block block = this.getBlock();
        int id = block.blockID;
        if (block == Block.flowerPot || block == Block.flowerPotMulti || block == Blocks.flowerPotExtend) {
            id = Item.flowerPot.itemID;
        }

        int subtype = 0;
        if (block == Block.tallGrass) {
            subtype = 1;
        }

        return new ItemStack(id, 1, subtype);
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
