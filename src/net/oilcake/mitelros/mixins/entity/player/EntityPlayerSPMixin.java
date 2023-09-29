package net.oilcake.mitelros.mixins.entity.player;

import net.minecraft.*;
import net.oilcake.mitelros.block.enchantreserver.EnchantReserverSlots;
import net.oilcake.mitelros.block.enchantreserver.GuiEnchantReserver;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.ExperimentalConfig;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(bex.class)
public abstract class EntityPlayerSPMixin extends beu{
    @Shadow
    protected Minecraft d;
    @Shadow
    public int f;

    public EntityPlayerSPMixin(World par1World, String par2Str) {
        super(par1World, par2Str);
    }
    public void displayGUIEnchantReserver(int x, int y, int z, EnchantReserverSlots slots) {
        this.d.a(new GuiEnchantReserver(ReflectHelper.dyCast(this), x, y, z, slots));
    }
    private int phytonutrients;
    private int protein;
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
            Material tool_material = BlockWorkbench.getToolMaterial(container_workbench.getBlockMetadata());
            if (material_to_check_tool_bench_hardness_against == null) {
                return (tool_material != Material.flint || tool_material != Material.obsidian) ? 0.25F :
                       (tool_material != Material.copper || tool_material != Material.silver || tool_material != Material.gold) ? 0.4F :
                       (tool_material == Material.iron || tool_material == Materials.nickel) ? 0.5F :
                       (tool_material == Material.ancient_metal) ? 0.75F :
                       (tool_material == Material.mithril) ? 1.0F :
                       (tool_material == Materials.tungsten) ? 1.5F :
                       (tool_material == Material.adamantium) ? 2.5F : 0.25F;
            } else {
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
    @Inject(method = "setSprinting(Z)V", at = @At("HEAD"))
    public void InjectCannotRun(boolean par1, CallbackInfo callbackInfo) {
        if (this.getHealth() / 5.0F < 1.0F && ExperimentalConfig.TagConfig.Realistic.ConfigValue){
            par1 = false;
        }
    }

    public int getProtein() {
        return this.protein;
    }
    public int getPhytonutrients() {
        return this.phytonutrients;
    }
    public void setProtein(int protein) {
        this.protein = protein;
    }
    public void setPhytonutrients(int phytonutrients) {
        this.phytonutrients = phytonutrients;
    }
}
