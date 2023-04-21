package net.oilcake.mitelros.mixins.entity.player;

import net.minecraft.*;
import net.oilcake.mitelros.block.enchantreserver.EnchantReserverSlots;
import net.oilcake.mitelros.block.enchantreserver.GuiEnchantReserver;
import net.oilcake.mitelros.item.Materials;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(bex.class)
public class EntityPlayerSPMixin {
    @Shadow
    protected Minecraft d;
    public void displayGUIEnchantReserver(int x, int y, int z, EnchantReserverSlots slots) {
        this.d.a(new GuiEnchantReserver(ReflectHelper.dyCast(this), x, y, z, slots));
    }
    @Overwrite
    private float getBenchAndToolsModifier(Container container) {
        if (!(container instanceof ContainerWorkbench)) {
            return 0.0F;
        } else {
            ContainerWorkbench container_workbench = (ContainerWorkbench)container;
            SlotResult slot_crafting = (SlotResult)container_workbench.getSlot(0);
            ItemStack item_stack = slot_crafting.getStack();
            Item item = item_stack == null ? null : item_stack.getItem();
            aah recipe = container_workbench.getRecipe();
            Material material_to_check_tool_bench_hardness_against = recipe == null ? item.getHardestMetalMaterial() : recipe.getMaterialToCheckToolBenchHardnessAgainst();
            if (material_to_check_tool_bench_hardness_against == null) {
                return 0.25F;
            } else {
                Material tool_material = BlockWorkbench.getToolMaterial(container_workbench.getBlockMetadata());
                if (tool_material != Material.flint && tool_material != Material.obsidian) {
                    if (tool_material != Material.copper && tool_material != Material.silver && tool_material != Material.gold) {
                        if (tool_material == Material.iron || tool_material == Materials.nickel) {
                            return 0.5F;
                        } else if (tool_material == Material.ancient_metal) {
                            return 0.75F;
                        } else if (tool_material == Material.mithril) {
                            return 1.0F;
                        }  else if (tool_material == Materials.tungsten) {
                            return 1.5F;
                        } else if (tool_material == Material.adamantium) {
                            return 2.5F;
                        } else {
                            Minecraft.setErrorMessage("getBenchAndToolsModifier: unrecognized tool material " + tool_material);
                            return 0.0F;
                        }
                    } else {
                        return 0.4F;
                    }
                } else {
                    return 0.25F;
                }
            }
        }
    }
}
