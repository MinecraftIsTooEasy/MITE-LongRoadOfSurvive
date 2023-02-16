package net.oilcake.mitelros.mixins.item;

import net.minecraft.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Material.class)
public class MaterialMixin {
    public Material material;
    public Material setRequiresToolC() {
        this.requires_tool = true;
        return material;
    }
    @Shadow
    protected float durability;
    @Shadow
    protected int min_harvest_level;
    @Shadow
    protected String name;
    @Shadow
    private boolean requires_tool;
    public float getDurability() {
        return this.durability;
    }

    public int getMinHarvestLevel() {
        return this.min_harvest_level;
    }

    public String getName() {
        return this.name;
    }
}
