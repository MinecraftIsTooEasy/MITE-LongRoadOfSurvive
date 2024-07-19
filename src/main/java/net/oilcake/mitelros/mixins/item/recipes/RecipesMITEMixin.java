package net.oilcake.mitelros.mixins.item.recipes;

import net.minecraft.Block;
import net.minecraft.FurnaceRecipes;
import net.minecraft.ItemStack;
import net.minecraft.RecipesMITE;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({RecipesMITE.class})
public class RecipesMITEMixin {
   @Inject(
      method = {"addFurnaceRecipes(Lnet/minecraft/FurnaceRecipes;)V"},
      at = {@At("TAIL")}
   )
   private static void extendMelting(FurnaceRecipes furnace_recipes, CallbackInfo callbackInfo) {
      furnace_recipes.addSmelting(Block.oreLapis.blockID, new ItemStack(Items.lapis, 4));
   }
}
