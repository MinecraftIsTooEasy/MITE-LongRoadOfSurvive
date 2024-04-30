package net.oilcake.mitelros.mixins.item.recipes;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RecipesMITE.class)
public class RecipesMITEMixin {
    @Inject(method = "addFurnaceRecipes", at = @At("TAIL"))
    private static void extendMelting(RecipesFurnace furnace_recipes, CallbackInfo callbackInfo){
        furnace_recipes.addSmelting(Block.oreLapis.blockID, new ItemStack(Items.lapis, 4));
    }
}
