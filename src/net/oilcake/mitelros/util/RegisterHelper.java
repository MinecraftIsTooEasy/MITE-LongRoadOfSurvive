package net.oilcake.mitelros.util;

import net.minecraft.CraftingManager;
import net.minecraft.ShapedRecipes;
import net.minecraft.ShapelessRecipes;
import net.oilcake.mitelros.achivements.AchievementExtend;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.mixins.item.recipes.CraftingManagerInvoker;

import java.util.ArrayList;

@SuppressWarnings("RedundantCast")
public class RegisterHelper {
    public static ArrayList<RecipesArgs> shapedRecipes = new ArrayList<>();
    public static ArrayList<RecipesArgs> shapelessRecipe = new ArrayList<>();
    private float difficulty;
    public static void registerAllItems(){
        Items.registerItems();
        Blocks.registerBlocks();
        AchievementExtend.registerAchievements();
    }
    public static void registerAllRecipes(CraftingManager crafters){
        RecipeRegister recipeRegister = new RecipeRegister();
        Blocks.registerRecipes(recipeRegister);
        Items.registerRecipes(recipeRegister);

        RecipesArgs recipesArgs;
        for (RecipesArgs shapedRecipe : shapedRecipes) {
            recipesArgs = shapedRecipe;
            ShapedRecipes shapedRecipes = ((CraftingManagerInvoker) crafters).addRecipeP(recipesArgs.result, recipesArgs.include_in_lowest_crafting_difficulty_determination, recipesArgs.inputs);
            if (recipesArgs.isExtendsNBT()){
                shapedRecipes.func_92100_c();
            }
            if (recipesArgs.isSpecialized_difficulty()){
                shapedRecipes.setDifficulty(recipesArgs.difficulty);
            }
        }
        for (RecipesArgs args : shapelessRecipe) {
            recipesArgs = args;
            ShapelessRecipes shapelessRecipes = ((CraftingManagerInvoker) crafters).addShapelessRecipeP(recipesArgs.result, recipesArgs.include_in_lowest_crafting_difficulty_determination, recipesArgs.inputs);
            if (recipesArgs.isExtendsNBT()){
                shapelessRecipes.propagateTagCompound();
            }
            if (recipesArgs.isSpecialized_difficulty()){
                shapelessRecipes.setDifficulty(recipesArgs.difficulty);
            }
        }
    }
}
