package net.oilcake.mitelros.util;

import net.minecraft.Item;
import net.minecraft.ItemStack;

public class RecipeOther {
    public static void registerRecipes(RecipeRegister register) {
        register.registerShapelessRecipe(new ItemStack(Item.potion, 3, 8271), true,
                new ItemStack(Item.appleGold,1,0), Item.bottleOfDisenchanting,
                new ItemStack(Item.potion,1,8227), new ItemStack(Item.potion,1,8193));
    }
}
