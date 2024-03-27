package net.oilcake.mitelros.mixins.item;

import net.minecraft.Block;
import net.minecraft.Item;
import net.minecraft.ItemBlock;
import net.minecraft.ItemStack;
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
}
