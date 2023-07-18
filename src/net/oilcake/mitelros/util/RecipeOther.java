package net.oilcake.mitelros.util;

import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.oilcake.mitelros.item.Items;

public class RecipeOther {
    public static void registerRecipes(RecipeRegister register) {
                register.registerShapelessRecipe(new ItemStack(Items.ExperimentalPotion,1),true,
                Item.blazePowder,Item.netherStalkSeeds,new ItemStack(Item.potion,1,0),new ItemStack(Item.appleGold,1,0)
        );
    }
}
