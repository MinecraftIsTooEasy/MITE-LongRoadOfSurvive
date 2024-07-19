package net.oilcake.mitelros.mixins.item.recipes;

import net.minecraft.CraftingManager;
import net.minecraft.ItemStack;
import net.minecraft.ShapedRecipes;
import net.minecraft.ShapelessRecipes;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({CraftingManager.class})
public interface CraftingManagerInvoker {
   @Invoker("addRecipe")
   @Intrinsic
   ShapedRecipes addRecipeP(ItemStack var1, boolean var2, Object... var3);

   @Invoker("addShapelessRecipe")
   @Intrinsic
   ShapelessRecipes addShapelessRecipeP(ItemStack var1, boolean var2, Object... var3);
}
