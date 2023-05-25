package net.oilcake.mitelros.mixins.item.recipes;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.util.RegisterHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.CraftingManager.getResultingDurabilityFromCombiningItems;

@Mixin(CraftingManager.class)
public class CraftingManagerMixin {
    @Shadow
    private List recipes = new ArrayList();
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
                Minecraft.setErrorMessage("Warning: " + item.getItemDisplayName((ItemStack)null) + " [" + item.itemID + "] is " + (item.isCraftingComponent(subtype_or_0) ? "a crafting product" : "repairable") + " but its lowest_crafting_difficulty_to_produce cannot be determined");
            }
        }

        if (item.isCraftingComponent(subtype_or_0) && item.getCraftingDifficultyAsComponent(new ItemStack(item, 1, subtype_or_0)) < 0.0F) {
            float lowest_crafting_difficulty_to_produce = item.getLowestCraftingDifficultyToProduce();
            if (lowest_crafting_difficulty_to_produce != Float.MAX_VALUE) {
                item.setCraftingDifficultyAsComponent(lowest_crafting_difficulty_to_produce);
            } else {
                Minecraft.setErrorMessage("Warning: " + item.getItemDisplayName((ItemStack)null) + " [" + item.itemID + "] is a crafting component but its crafting_difficulty_as_component has not been set");
            }
        }

    }
    @Overwrite
    public CraftingResult findMatchingRecipe(InventoryCrafting par1InventoryCrafting, World par2World, EntityPlayer player) {
        if (player != null && player.openContainer != null) {
            player.openContainer.repair_fail_condition = 0;
        }

        int var3 = 0;
        ItemStack var4 = null;
        ItemStack var5 = null;

        int var6;
        ItemStack item_stack_sinew;
        for(var6 = 0; var6 < par1InventoryCrafting.getSizeInventory(); ++var6) {
            item_stack_sinew = par1InventoryCrafting.getStackInSlot(var6);
            if (item_stack_sinew != null) {
                if (var3 == 0) {
                    var4 = item_stack_sinew;
                }

                if (var3 == 1) {
                    var5 = item_stack_sinew;
                }

                ++var3;
            }
        }

        ItemStack item_stack_armor;
        CraftingResult crafting_result;
        if (var3 == 2 && var4.itemID == var5.itemID && var4.stackSize == 1 && var5.stackSize == 1 && Item.itemsList[var4.itemID].isRepairable()) {
            if (var4.isItemDamaged() && var5.isItemDamaged()) {
                if (var4.getQuality() != var5.getQuality()) {
                    return null;
                } else if (!var4.isItemEnchanted() && !var5.isItemEnchanted()) {
                    if (var4.isDyed() || var5.isDyed()) {
                        if (!var4.isDyed() || !var5.isDyed()) {
                            return null;
                        }

                        if (var4.getDyedColor() != var5.getDyedColor()) {
                            return null;
                        }
                    }

                    float crafting_difficulty = var4.getItem().getLowestCraftingDifficultyToProduce();
                    if (var4.getItem().hasQuality() && player != null && var4.getQuality().isHigherThan(player.getMaxCraftingQuality(crafting_difficulty, var4.getItem(), var4.getItem().getSkillsetsThatCanRepairThis()))) {
                        player.openContainer.repair_fail_condition = 1;
                    }

                    item_stack_armor = (new ItemStack(var4.itemID, 1, var4.getItemSubtype())).setItemDamage(getResultingDurabilityFromCombiningItems(var4, var5));
                    if (var4.isDyed()) {
                        item_stack_armor.copyDyedColor(var4);
                    }

                    crafting_result = (new CraftingResult(item_stack_armor, crafting_difficulty / 2.0F, var4.getItem().getSkillsetsThatCanRepairThis(), (aah)null)).setExperienceCostExempt().setQualityOverride(var4.getQuality());
                    crafting_result.setRepair();
                    return crafting_result;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else if (var3 != 2 || var4.getItem() != Item.sinew && var5.getItem() != Item.sinew && var4.getItem() != Item.silk && var5.getItem() != Item.silk && var4.getItem() != Items.Wolf_fur && var5.getItem() != Items.Wolf_fur || (!(var4.getItem() instanceof ItemArmor) || !((ItemArmor)var4.getItem()).isLeather() || var4.stackSize != 1 || !var4.isItemDamaged()) && (!(var5.getItem() instanceof ItemArmor) || !((ItemArmor)var5.getItem()).isLeather() || var5.stackSize != 1 || !var5.isItemDamaged())) {
            Container event_handler = par1InventoryCrafting.getEventHandler();

            for(var6 = 0; var6 < this.recipes.size(); ++var6) {
                aah var12 = (aah)this.recipes.get(var6);
                if (var12.matches(par1InventoryCrafting, par2World) && (!(event_handler instanceof MITEContainerCrafting) || !((MITEContainerCrafting)event_handler).isRecipeForbidden(var12))) {
                    crafting_result = var12.getCraftingResult(par1InventoryCrafting);
                    if (crafting_result == null) {
                        return null;
                    } else {
                        return event_handler instanceof MITEContainerCrafting && ((MITEContainerCrafting)event_handler).isCraftingResultForbidden(crafting_result) ? null : crafting_result;
                    }
                }
            }

            return null;
        } else {
            if (var4.getItem() != Item.sinew && var4.getItem() != Item.silk && var4.getItem() != Items.Wolf_fur) {
                item_stack_sinew = var5;
                item_stack_armor = var4;
            } else {
                item_stack_sinew = var4;
                item_stack_armor = var5;
            }

            if (item_stack_armor.getItem().hasQuality() && player != null && item_stack_armor.getQuality().isHigherThan(player.getMaxCraftingQuality(item_stack_armor.getItem().getLowestCraftingDifficultyToProduce(), item_stack_armor.getItem(), item_stack_armor.getItem().getSkillsetsThatCanRepairThis()))) {
                return null;
            } else {
                int damage = item_stack_armor.getItemDamage();
                int damage_repaired_per_sinew = item_stack_armor.getMaxDamage() / item_stack_armor.getItem().getRepairCost();
                int num_sinews_to_use = damage / damage_repaired_per_sinew;
                if (damage % damage_repaired_per_sinew != 0) {
                    ++num_sinews_to_use;
                }

                if (num_sinews_to_use > 1 && num_sinews_to_use * damage_repaired_per_sinew > damage) {
                    --num_sinews_to_use;
                }

                if (num_sinews_to_use > item_stack_sinew.stackSize) {
                    num_sinews_to_use = item_stack_sinew.stackSize;
                }

                int damage_repaired = num_sinews_to_use * damage_repaired_per_sinew;
                int damage_after_repair = Math.max(damage - damage_repaired, 0);
                ItemStack resulting_stack = item_stack_armor.copy().setItemDamage(damage_after_repair);
                crafting_result = (new CraftingResult(resulting_stack, (float)(num_sinews_to_use * 50), item_stack_armor.getItem().getSkillsetsThatCanRepairThis(), (aah)null)).setExperienceCostExempt().setQualityOverride(item_stack_armor.getQuality()).setConsumption(num_sinews_to_use);
                crafting_result.setRepair();
                return crafting_result;
            }
        }
    }
}
