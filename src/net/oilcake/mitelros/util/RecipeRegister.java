package net.oilcake.mitelros.util;

import net.minecraft.ItemStack;

public class RecipeRegister {
    public RecipesArgs registerShapedRecipe(ItemStack out, boolean include_in_lowest_crafting_difficulty_determination, Object... input){
        RecipesArgs e = new RecipesArgs(out, include_in_lowest_crafting_difficulty_determination,input);
        RegisterHelper.shapedRecipes.add(e);
        return e;
    }
    // 不需要配方有形状
    public RecipesArgs registerShapelessRecipe(ItemStack out, boolean include_in_lowest_crafting_difficulty_determination, Object... input){
        RecipesArgs e = new RecipesArgs(out, include_in_lowest_crafting_difficulty_determination,input);
        RegisterHelper.shapelessRecipe.add(e);
        return e;
    }
}
