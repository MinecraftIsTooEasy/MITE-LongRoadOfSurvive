package net.oilcake.mitelros.mixins.item.recipes;

import net.minecraft.Block;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.RecipeIngots;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RecipeIngots.class)
public class RecipeIngotsMixin {
    @Shadow
    private Object[][] recipeItems;
    @Inject(method = "<init>", at = @At("RETURN"))
    private void injectInit(CallbackInfo callbackInfo){
        this.recipeItems = new Object[][]{{Block.blockCopper, new ItemStack(Item.ingotCopper, 9), 0},
                {Block.blockSilver, new ItemStack(Item.ingotSilver, 9), 0},
                {Block.blockGold, new ItemStack(Item.ingotGold, 9), 0},
                {Block.blockIron, new ItemStack(Item.ingotIron, 9), 0},
                {Block.blockMithril, new ItemStack(Item.ingotMithril, 9), 0},
                {Block.blockAdamantium, new ItemStack(Item.ingotAdamantium, 9), 0},
                {Block.blockAncientMetal, new ItemStack(Item.ingotAncientMetal, 9), 0},
                {Block.blockDiamond, new ItemStack(Item.diamond, 9), 0},
                {Block.blockEmerald, new ItemStack(Item.emerald, 9), 0},
                {Block.blockLapis, new ItemStack(Items.lapis, 9), 0},
                {Block.blockRedstone, new ItemStack(Item.redstone, 9), 0},
                {Block.coalBlock, new ItemStack(Item.coal, 9, 0), 0},
                {Block.hay, new ItemStack(Item.wheat, 9), 0},
                {Block.blockNetherQuartz, new ItemStack(Item.netherQuartz, 4), 0},
                {Item.flint, new ItemStack(Item.chipFlint, 4), 0}};
    }
}
