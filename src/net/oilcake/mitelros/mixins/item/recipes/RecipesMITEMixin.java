package net.oilcake.mitelros.mixins.item.recipes;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(RecipesMITE.class)
public class RecipesMITEMixin {
    @Overwrite
    public static void addFurnaceRecipes(RecipesFurnace furnace_recipes) {
        furnace_recipes.addSmelting(Block.sand.blockID, new ItemStack(Block.sandStone));
        furnace_recipes.addSmelting(Block.sandStone.blockID, new ItemStack(Block.glass));
        furnace_recipes.addSmelting(Item.clay.itemID, new ItemStack(Item.brick));
        furnace_recipes.addSmelting(Block.blockClay.blockID, new ItemStack(Block.hardenedClay));
        furnace_recipes.addSmelting(Block.cactus.blockID, new ItemStack(Item.dyePowder, 1, 2));
        furnace_recipes.addSmelting(Block.wood.blockID, new ItemStack(Item.coal, 1, 1));
        furnace_recipes.addSmelting(Block.netherrack.blockID, new ItemStack(Item.netherrackBrick));
        furnace_recipes.addSmelting(Block.oreCoal.blockID, new ItemStack(Item.coal));
        furnace_recipes.addSmelting(Block.oreRedstone.blockID, new ItemStack(Item.redstone, 4));
        furnace_recipes.addSmelting(Block.oreLapis.blockID, new ItemStack(Items.lapis, 4));
        furnace_recipes.addSmelting(Block.oreNetherQuartz.blockID, new ItemStack(Item.netherQuartz));
        furnace_recipes.addSmelting(Block.oreCopper.blockID, new ItemStack(Item.ingotCopper));
        furnace_recipes.addSmelting(Block.oreSilver.blockID, new ItemStack(Item.ingotSilver));
        furnace_recipes.addSmelting(Block.oreIron.blockID, new ItemStack(Item.ingotIron));
        furnace_recipes.addSmelting(Block.oreMithril.blockID, new ItemStack(Item.ingotMithril));
        furnace_recipes.addSmelting(Block.oreAdamantium.blockID, new ItemStack(Item.ingotAdamantium));
        furnace_recipes.addSmelting(Block.oreGold.blockID, new ItemStack(Item.ingotGold));
        furnace_recipes.addSmelting(Block.oreEmerald.blockID, new ItemStack(Item.emerald));
        furnace_recipes.addSmelting(Block.oreDiamond.blockID, new ItemStack(Item.diamond));

        for(int i = 0; i < Item.itemsList.length; ++i) {
            Item item = Item.getItem(i);
            if (item instanceof ItemFood) {
                ItemFood food = (ItemFood)item;
                if (food.getCookedItem() != null) {
                    furnace_recipes.addSmelting(i, new ItemStack(food.getCookedItem()));
                }
            }
        }

    }
}
