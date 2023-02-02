package net.oilcake.mitelros.mixins.item;

import net.minecraft.ItemTool;
import net.minecraft.Material;
import net.minecraft.Minecraft;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemTool.class)
public class ItemToolMixin {
    @Shadow
    private float damageVsEntity;
    @Shadow
    private Material effective_material;
    @Overwrite
    public float getMaterialHarvestEfficiency() {
        if (this.effective_material == Material.wood) {
            return 1.0F;
        } else if (this.effective_material == Material.flint) {
            return 1.25F;
        } else if (this.effective_material == Material.obsidian) {
            return 1.5F;
        } else if (this.effective_material == Material.rusted_iron) {
            return 1.25F;
        } else if (this.effective_material == Material.copper) {
            return 1.75F;
        } else if (this.effective_material == Material.silver) {
            return 1.75F;
        } else if (this.effective_material == Material.gold) {
            return 1.75F;
        } else if (this.effective_material == Material.iron) {
            return 2.0F;
        } else if (this.effective_material == Material.mithril) {
            return 2.5F;
        } else if (this.effective_material == Material.adamantium) {
            return 3.0F;
        } else if (this.effective_material == Material.diamond) {
            return 2.5F;
        } else if (this.effective_material == Material.ancient_metal) {
            return 2.0F;
        } else if (this.effective_material == Materials.nickel) {
            return 2.0F;
        } else if (this.effective_material == Materials.tungsten) {
            return 2.5F;
        }else {
            Minecraft.setErrorMessage("getMaterialHarvestEfficiency: tool material not handled");
            return 0.0F;
        }
    }
}
