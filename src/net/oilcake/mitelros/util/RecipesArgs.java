package net.oilcake.mitelros.util;

import net.minecraft.ItemStack;

public class RecipesArgs {
    public final ItemStack result;
    public final Object[] inputs;
    public final boolean include_in_lowest_crafting_difficulty_determination;
    public boolean specialized_difficulty;
    private boolean extendsNBT;
    public float difficulty;
    public RecipesArgs(ItemStack result,boolean include_in_lowest_crafting_difficulty_determination,Object... inputs){
        this.result = result;
        this.include_in_lowest_crafting_difficulty_determination = include_in_lowest_crafting_difficulty_determination;
        this.inputs = inputs;
        this.specialized_difficulty = false;
    }

    public boolean isExtendsNBT() {
        return extendsNBT;
    }
    public boolean isSpecialized_difficulty(){
        return specialized_difficulty;
    }
    public void resetDifficulty(int Difficulty){
        this.specialized_difficulty = true;
        this.difficulty = Difficulty;
    }
    public RecipesArgs extendsNBT() {
        this.extendsNBT = true;
        return this;
    }
}
