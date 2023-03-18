package net.oilcake.mitelros.mixins.world;

import net.minecraft.*;
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
        this.allowedBiomes = new BiomeBase[]{BiomeBase.desert, BiomeBase.forest, BiomeBase.extremeHills, BiomeBase.swampland, BiomeBase.plains, BiomeBase.taiga, BiomeBase.jungle, BiomeBases.alps};
        this.parent = par3GenLayer;
        if (par4WorldType == WorldType.DEFAULT_1_1) {
            this.allowedBiomes = new BiomeBase[]{BiomeBase.desert, BiomeBase.forest, BiomeBase.extremeHills, BiomeBase.swampland, BiomeBase.plains, BiomeBase.taiga, BiomeBases.alps};
        }
    }

    @Shadow
    public int[] getInts(int par1, int par2, int par3, int par4, int z) {
        int[] var5 = this.parent.getInts(par1, par2, par3, par4, z);
        int[] var6 = IntCache.getIntCache(par3 * par4);

        for(int var7 = 0; var7 < par4; ++var7) {
            for(int var8 = 0; var8 < par3; ++var8) {
                this.initChunkSeed((long)(var8 + par1), (long)(var7 + par2));
                int var9 = var5[var8 + var7 * par3];
                if (var9 == 0) {
                    var6[var8 + var7 * par3] = 0;
                } else if (var9 == 1) {
                    var6[var8 + var7 * par3] = this.allowedBiomes[this.nextInt(this.allowedBiomes.length)].biomeID;
                } else {
                    int var10 = this.allowedBiomes[this.nextInt(this.allowedBiomes.length)].biomeID;
                    if (var10 == BiomeBase.taiga.biomeID) {
                        var6[var8 + var7 * par3] = var10;
                    } else {
                        var6[var8 + var7 * par3] = BiomeBase.icePlains.biomeID;
                    }
                }
            }
        }

        return var6;
    }
}
