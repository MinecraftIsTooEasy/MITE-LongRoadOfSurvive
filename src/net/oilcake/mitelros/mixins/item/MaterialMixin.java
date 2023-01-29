package net.oilcake.mitelros.mixins.item;

import net.minecraft.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Material.class)
public class MaterialMixin {
    @Shadow
    protected float durability;
    @Shadow
    protected int min_harvest_level;
    @Shadow
    protected String name;

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
