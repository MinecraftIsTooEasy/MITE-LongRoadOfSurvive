package net.oilcake.mitelros.mixins.item.recipes;

import net.minecraft.Block;
import net.minecraft.ItemStack;
import net.minecraft.RecipesFurnace;
import net.minecraft.TileEntityFurnace;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.HashMap;
import java.util.Map;

@Mixin(RecipesFurnace.class)
public class RecipesFurnaceMixin {
    @Shadow
    private Map smeltingList = new HashMap();
    @Overwrite
    public ItemStack getSmeltingResult(ItemStack input_item_stack, int heat_level) {
        if (input_item_stack == null) {
            return null;
        } else {
            int input_item_id = input_item_stack.itemID;
            if (heat_level == -1) {
                return (ItemStack)this.smeltingList.get(input_item_id);
            } else {
                ItemStack result_item_stack;
                if (input_item_id == Items.claybowlRaw.itemID) {
                    result_item_stack = input_item_stack.stackSize >= 4 ? new ItemStack(Items.claybowlEmpty,4) : null;
                }
                else if (input_item_id == Block.sand.blockID) {
                    result_item_stack = (heat_level != 1 || input_item_stack.stackSize >= 4) && input_item_stack.stackSize >= 4 ? new ItemStack(heat_level == 1 ? Block.sandStone : Block.glass) : null;
                } else {
                    result_item_stack = (ItemStack)this.smeltingList.get(input_item_id);
                }

                return heat_level < TileEntityFurnace.getHeatLevelRequired(input_item_stack.itemID) ? null : result_item_stack;
            }
        }
    }


}
