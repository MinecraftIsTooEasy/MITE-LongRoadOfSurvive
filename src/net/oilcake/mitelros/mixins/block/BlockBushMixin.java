package net.oilcake.mitelros.mixins.block;

import net.minecraft.BiomeBase;
import net.minecraft.BlockBush;
import net.minecraft.BlockGrowingPlant;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockBush.class)
public abstract class BlockBushMixin extends BlockGrowingPlant {
    public BlockBushMixin(int block_id) {
        super(block_id);
    }
    public float getGrowthRate(World world, int x, int y, int z) {
        float growth_rate = 0.1F + (world.getWorldSeason() == 2 ? 0.15F : 0F);
        BiomeBase biome = world.getBiomeGenForCoords(x, z);
        growth_rate *= this.getTemperatureGrowthRateModifier(biome.temperature);
        growth_rate *= this.getHumidityGrowthRateModifier(biome.isHighHumidity());
        growth_rate *= this.getGlobalGrowthRateModifierFromMITE();
        return growth_rate;
    }
}
