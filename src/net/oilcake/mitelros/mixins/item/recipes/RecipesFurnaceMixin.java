package net.oilcake.mitelros.mixins.item.recipes;

import net.minecraft.*;
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
                if (input_item_stack.getItem() instanceof ItemArmor){
                    int quantity = (int) ((float) ((input_item_stack.getMaxDamage() - input_item_stack.getItemDamage()) / (float)input_item_stack.getMaxDamage()) * ((ItemArmor) input_item_stack.getItem()).getNumComponentsForDurability() * (input_item_stack.getItem().isChainMail() ? 4.0F : 9.0F));
                    ItemStack output;
                    quantity /= 3;
                    if(input_item_stack.getItem().getHardestMetalMaterial() == Material.rusted_iron){
                        quantity /= 3;
                        output = new ItemStack(Item.getMatchingItem(ItemNugget.class, Material.iron),quantity);
                    }else {
                        output = new ItemStack(Item.getMatchingItem(ItemNugget.class, input_item_stack.getItem().getMaterialForRepairs()),quantity);
                    }
                    if (quantity == 0) {
                        output = null;
                    }
                    result_item_stack = output;
                }else if(input_item_stack.getItem() instanceof ItemTool){
                    int quantity = (int) ((float) ((input_item_stack.getMaxDamage() - input_item_stack.getItemDamage()) / (float)input_item_stack.getMaxDamage()) * ((ItemTool) input_item_stack.getItem()).getNumComponentsForDurability() * 9.0F);
                    ItemStack output;
                    quantity /= 3;
                    if(input_item_stack.getItem().getHardestMetalMaterial() == Material.rusted_iron){
                        quantity /= 3;
                        output = new ItemStack(Item.getMatchingItem(ItemNugget.class, Material.iron),quantity);
                    }else {
                        output = new ItemStack(Item.getMatchingItem(ItemNugget.class, input_item_stack.getItem().getMaterialForRepairs()),quantity);
                    }
                    if (quantity == 0) {
                        output = null;
                    }
                    result_item_stack = output;
                }else if (input_item_id == Items.claybowlRaw.itemID) {
                    result_item_stack = input_item_stack.stackSize >= 4 ? new ItemStack(Items.claybowlEmpty,4) : null;
                }else if (input_item_id == Block.sand.blockID) {
                    result_item_stack = (heat_level != 1 || input_item_stack.stackSize >= 4) && input_item_stack.stackSize >= 4 ? new ItemStack(heat_level == 1 ? Block.sandStone : Block.glass) : null;
                }else {
                    result_item_stack = (ItemStack)this.smeltingList.get(input_item_id);
                }
                if(input_item_stack.getItem().isDamageable()){
                    if (result_item_stack != null) {
                        result_item_stack.setItemDamage(input_item_stack.getItemDamage());
                    }
                }
                return heat_level < TileEntityFurnace.getHeatLevelRequired(input_item_stack.itemID) ? null : result_item_stack;
            }
        }
    }


}
