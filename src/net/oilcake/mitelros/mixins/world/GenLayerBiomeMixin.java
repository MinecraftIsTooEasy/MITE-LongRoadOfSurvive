package net.oilcake.mitelros.mixins.world;

import net.minecraft.BiomeBase;
import net.minecraft.GenLayer;
import net.minecraft.GenLayerBiome;
import net.minecraft.WorldType;
import net.oilcake.mitelros.world.BiomeBases;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GenLayerBiome.class)
public class GenLayerBiomeMixin extends GenLayer {
    @Shadow
    private BiomeBase[] allowedBiomes;


    public GenLayerBiomeMixin(long par1) {
        super(par1);
    }
    @Inject(method = "<init>", at = @At(value = "RETURN"), cancellable = true)
    private void inject(long par1,GenLayer par3GenLayer, WorldType par4WorldType,CallbackInfo callbackInfo){
        this.parent = par3GenLayer;
        if (par4WorldType == WorldType.LARGE_BIOMES) {
            this.allowedBiomes = new BiomeBase[]{BiomeBase.desert, BiomeBase.forest, BiomeBase.extremeHills, BiomeBase.swampland, BiomeBase.plains, BiomeBase.taiga, BiomeBase.jungle, BiomeBases.savanna};
        }else if (par4WorldType == WorldType.DEFAULT) {
            this.allowedBiomes = new BiomeBase[]{BiomeBase.desert, BiomeBase.forest, BiomeBase.extremeHills, BiomeBase.swampland, BiomeBase.plains, BiomeBase.taiga, BiomeBase.jungle};
        }
    }

    @Shadow
    public int[] getInts(int par1, int par2, int par3, int par4, int z) {
        return new int[]{1};
    }

}
