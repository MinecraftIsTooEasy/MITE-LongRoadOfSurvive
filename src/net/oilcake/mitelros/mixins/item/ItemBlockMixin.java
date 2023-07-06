package net.oilcake.mitelros.mixins.item;

import net.minecraft.Block;
import net.minecraft.Item;
import net.minecraft.ItemBlock;
import net.minecraft.ItemStack;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemBlock.class)
public class ItemBlockMixin extends Item{
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
