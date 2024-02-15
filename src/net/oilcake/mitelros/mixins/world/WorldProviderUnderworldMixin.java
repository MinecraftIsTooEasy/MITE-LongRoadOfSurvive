package net.oilcake.mitelros.mixins.world;

import net.minecraft.BiomeBase;
import net.minecraft.WorldChunkManagerHell;
import net.minecraft.WorldProvider;
import net.minecraft.WorldProviderUnderworld;
import net.oilcake.mitelros.util.StuckTagConfig;
import net.oilcake.mitelros.world.BiomeBases;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(WorldProviderUnderworld.class)
public class WorldProviderUnderworldMixin extends WorldProvider {
    public WorldProviderUnderworldMixin(int dimension_id, String name) {
        super(dimension_id, name);
    }
    @Overwrite
    public void registerWorldChunkManager()
    {
        this.worldChunkMgr = new WorldChunkManagerHell(StuckTagConfig.TagConfig.TagDeadgeothermy.ConfigValue ? BiomeBases.underworldinfreeze : BiomeBase.underworld, 1.0F, 0.0F);
    }
}
