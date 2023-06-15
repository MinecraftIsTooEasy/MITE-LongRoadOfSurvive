package net.oilcake.mitelros.mixins.world;

import net.minecraft.BiomeBase;
import net.minecraft.World;
import net.minecraft.WorldGenBase;
import net.minecraft.WorldGenCaves;
import net.oilcake.mitelros.world.BiomeBases;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(WorldGenCaves.class)
public class WorldGenCavesMixin extends WorldGenBase {
    @Shadow
    protected void generateLargeCaveNode(long par1, int par3, int par4, byte[] par5ArrayOfByte, double par6, double par8, double par10) {
    }
    @Shadow
    private void generateCaveNode(long nextLong, int par4, int par5, byte[] par6ArrayOfByte, double var9, double var11, double var13, float var19, float var17, float var18, int i, int i1, double v) {
    }
    @Overwrite
    protected void recursiveGenerate(World par1World, int par2, int par3, int par4, int par5, byte[] par6ArrayOfByte) {
        BiomeBase biome = par1World.getBiomeGenForCoords(par2 * 16, par3 * 16);
        float frequency;
        if (biome != BiomeBase.plains && biome != BiomeBase.swampland) {
            if (biome == BiomeBase.iceMountains) {
                frequency = 1.5F;
            } else if (biome == BiomeBase.extremeHills) {
                frequency = 1.5F;
            }  else if (biome == BiomeBases.windsweptpleatu) {
                frequency = 2.5F;
            } else {
                frequency = 0.75F;
            }
        } else {
            frequency = 0.5F;
        }

        if (this.rand.nextInt((int)(15.0F / frequency)) == 0) {
            int var7 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(30) + 1) + 1);
            var7 = (int)((float)var7 * frequency);
            boolean increase_frequency_of_larger_tunnels = par1World.getWorldInfo().getEarliestMITEReleaseRunIn() >= 166;

            for(int var8 = 0; var8 < var7; ++var8) {
                double var9 = (double)(par2 * 16 + this.rand.nextInt(16));
                double var11 = (double)this.rand.nextInt(this.rand.nextInt(120) + 8);
                double var13 = (double)(par3 * 16 + this.rand.nextInt(16));
                int var15 = 1;
                int rarity_of_large_tunnels;
                if (!increase_frequency_of_larger_tunnels) {
                    rarity_of_large_tunnels = 10;
                } else {
                    rarity_of_large_tunnels = var11 > 23.0 && var11 < 33.0 ? 2 : 10;
                }

                if (this.rand.nextInt(4) == 0) {
                    this.generateLargeCaveNode(this.rand.nextLong(), par4, par5, par6ArrayOfByte, var9, var11, var13);
                    var15 += this.rand.nextInt(4);
                }

                for(int var16 = 0; var16 < var15; ++var16) {
                    float var17 = this.rand.nextFloat() * 3.1415927F * 2.0F;
                    float var18 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
                    float var19 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();
                    if (this.rand.nextInt(rarity_of_large_tunnels) == 0) {
                        var19 *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0F + 1.0F;
                        if (rarity_of_large_tunnels < 10) {
                            var19 *= 1.0F + this.rand.nextFloat() * 0.5F;
                        }
                    } else if (increase_frequency_of_larger_tunnels && var11 < 41.0 && var11 > 15.0 && this.rand.nextInt(2) == 0) {
                        var19 *= this.rand.nextFloat() * this.rand.nextFloat() * 1.5F + 1.0F;
                    }

                    this.generateCaveNode(this.rand.nextLong(), par4, par5, par6ArrayOfByte, var9, var11, var13, var19, var17, var18, 0, 0, 1.0);
                }
            }

        }
    }


}
