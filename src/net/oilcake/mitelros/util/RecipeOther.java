package net.oilcake.mitelros.util;

import net.minecraft.Item;
import net.minecraft.ItemStack;

public class RecipeOther {
    public static void registerRecipes(RecipeRegister register) {
        register.registerShapelessRecipe(new ItemStack(Item.potion, 3, 8271), true,
                new ItemStack(Item.appleGold,1,0), new ItemStack(Item.potion,1,16),
                new ItemStack(Item.potion,1,16), new ItemStack(Item.potion,1,16));
    }
}
