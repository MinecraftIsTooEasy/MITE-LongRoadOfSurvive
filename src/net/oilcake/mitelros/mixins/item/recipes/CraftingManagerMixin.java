package net.oilcake.mitelros.mixins.item.recipes;

import net.minecraft.*;
import net.oilcake.mitelros.util.RecipeOther;
import net.oilcake.mitelros.util.RegisterHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CraftingManager.class)
public class CraftingManagerMixin {
    @Redirect(method = "<init>",
            at = @At(value = "INVOKE",target = "Lnet/minecraft/RecipesMITE;addCraftingRecipes(Lnet/minecraft/CraftingManager;)V"))
    private void injectRegisterRecipes(CraftingManager crafters) {
        RegisterHelper.registerAllItems();
        RecipesMITE.addCraftingRecipes(crafters);
        RegisterHelper.registerAllRecipes(crafters);
    }
    @Overwrite
    private void checkRecipe(Item item, int subtype_or_0) {
        if ((item.isCraftingProduct() || item.isRepairable()) && item.getLowestCraftingDifficultyToProduce() == Float.MAX_VALUE) {
            if (item.hasMaterial(Material.rusted_iron)) {
                Object peer;
                if (item instanceof ItemArmor) {
                    ItemArmor var10000 = (ItemArmor)item;
                    peer = ItemArmor.getMatchingArmor(item.getClass(), Material.copper, ((ItemArmor)item).isChainMail());
                } else {
                    peer = Item.getMatchingItem(item.getClass(), Material.copper);
                }

                if (peer != null) {
                    item.setLowestCraftingDifficultyToProduce(((Item)peer).getLowestCraftingDifficultyToProduce());
                }
            }

            if (item.getLowestCraftingDifficultyToProduce() == Float.MAX_VALUE) {
                //Minecraft.setErrorMessage("Warning: " + item.getItemDisplayName((ItemStack)null) + " [" + item.itemID + "] is " + (item.isCraftingComponent(subtype_or_0) ? "a crafting product" : "repairable") + " but its lowest_crafting_difficulty_to_produce cannot be determined");
            }
        }

        if (item.isCraftingComponent(subtype_or_0) && item.getCraftingDifficultyAsComponent(new ItemStack(item, 1, subtype_or_0)) < 0.0F) {
            float lowest_crafting_difficulty_to_produce = item.getLowestCraftingDifficultyToProduce();
            if (lowest_crafting_difficulty_to_produce != Float.MAX_VALUE) {
                item.setCraftingDifficultyAsComponent(lowest_crafting_difficulty_to_produce);
            } else {
                //Minecraft.setErrorMessage("Warning: " + item.getItemDisplayName((ItemStack)null) + " [" + item.itemID + "] is a crafting component but its crafting_difficulty_as_component has not been set");
            }
        }

    }


}
