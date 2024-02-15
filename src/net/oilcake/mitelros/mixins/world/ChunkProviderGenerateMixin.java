package net.oilcake.mitelros.mixins.world;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(ChunkProviderGenerate.class)
public abstract class ChunkProviderGenerateMixin implements IChunkProvider{
    @Shadow
    private BiomeBase[] biomesForGeneration;
    @Shadow
    private NoiseGeneratorOctaves noiseGen1;
    @Shadow
    private NoiseGeneratorOctaves noiseGen2;
    @Shadow
    private NoiseGeneratorOctaves noiseGen3;
    @Shadow
    public NoiseGeneratorOctaves noiseGen5;
    @Shadow
    public NoiseGeneratorOctaves noiseGen6;
    @Shadow
    double[] noise3;
    @Shadow
    double[] noise1;
    @Shadow
    double[] noise2;
    @Shadow
    double[] noise5;
    @Shadow
    double[] noise6;
    @Shadow
    float[] parabolicField;
    @Shadow
    private World worldObj;
    @Shadow
    private double[] stoneNoise = new double[256];
    @Shadow
    private double[] stone_noise_2 = new double[256];
    @Shadow
    private double[] stone_noise_3 = new double[256];
    @Shadow
    private NoiseGeneratorOctaves noiseGen8;
    @Shadow
    private Random rand;
    @Shadow
    private NoiseGeneratorOctaves noiseGen4;
    @Shadow
    private double[] noiseArray;


    @Overwrite
    private double[] initializeNoiseField(double[] par1ArrayOfDouble, int par2, int par3, int par4, int par5, int par6, int par7) {
        if (par1ArrayOfDouble == null) {
            par1ArrayOfDouble = new double[par5 * par6 * par7];
        }

        if (this.parabolicField == null) {
            this.parabolicField = new float[25];

            for(int var8 = -2; var8 <= 2; ++var8) {
                for(int var9 = -2; var9 <= 2; ++var9) {
                    float var10 = 10.0F / MathHelper.sqrt_float((float)(var8 * var8 + var9 * var9) + 0.2F);
                    this.parabolicField[var8 + 2 + (var9 + 2) * 5] = var10;
                }
            }
        }

        double var44 = 513.309;
        double var45 = 684.412;
        this.noise5 = this.noiseGen5.generateNoiseOctaves(this.noise5, par2, par4, par5, par7, 1.121, 1.121, 0.5);
        this.noise6 = this.noiseGen6.generateNoiseOctaves(this.noise6, par2, par4, par5, par7, 120.0, 120.0, 0.25);
        this.noise3 = this.noiseGen3.generateNoiseOctaves(this.noise3, par2, par3, par4, par5, par6, par7, var44 / 60.0, var45 / 160.0, var44 / 60.0);
        this.noise1 = this.noiseGen1.generateNoiseOctaves(this.noise1, par2, par3, par4, par5, par6, par7, var44, var45, var44);
        this.noise2 = this.noiseGen2.generateNoiseOctaves(this.noise2, par2, par3, par4, par5, par6, par7, var44, var45, var44);
        boolean var43 = false;
        boolean var42 = false;
        int var12 = 0;
        int var13 = 0;

        for(int var14 = 0; var14 < par5; ++var14) {
            for(int var15 = 0; var15 < par7; ++var15) {
                float var16 = 0.0F;
                float var17 = 0.0F;
                float var18 = 0.0F;
                byte var19 = 2;
                BiomeBase var20 = this.biomesForGeneration[var14 + 2 + (var15 + 2) * (par5 + 5)];

                for(int var21 = -var19; var21 <= var19; ++var21) {
                    for(int var22 = -var19; var22 <= var19; ++var22) {
                        BiomeBase var23 = this.biomesForGeneration[var14 + var21 + 2 + (var15 + var22 + 2) * (par5 + 5)];
                        float var24 = this.parabolicField[var21 + 2 + (var22 + 2) * 5] / (var23.minHeight + 2.0F);
                        if (var23.minHeight > var20.minHeight) {
                            var24 /= 2.0F;
                        }

                        var16 += var23.maxHeight * var24;
                        var17 += var23.minHeight * var24;
                        var18 += var24;
                    }
                }

                var16 /= var18;
                var17 /= var18;
                var16 = var16 * 0.9F + 0.1F;
                var17 = (var17 * 4.0F - 1.0F) / 8.0F;
                double var47 = this.noise6[var13] / 8000.0;
                if (var47 < 0.0) {
                    var47 = -var47 * 0.3;
                }

                var47 = var47 * 3.0 - 2.0;
                if (var47 < 0.0) {
                    var47 /= 2.0;
                    if (var47 < -1.0) {
                        var47 = -1.0;
                    }

                    var47 /= 1.4;
                    var47 /= 2.0;
                } else {
                    if (var47 > 1.0) {
                        var47 = 1.0;
                    }

                    var47 /= 8.0;
                }

                ++var13;

                for(int var46 = 0; var46 < par6; ++var46) {
                    double var48 = (double)var17;
                    double var26 = (double)var16;
                    var48 += var47 * 0.2;
                    var48 = var48 * (double)par6 / 16.0;
                    double var28 = (double)par6 / 2.0 + var48 * 4.0;
                    double var30 = 0.0;
                    double var32 = ((double)var46 - var28) * 12.0 * 128.0 / 128.0 / var26;
                    if (var32 < 0.0) {
                        var32 *= 4.0;
                    }

                    double var34 = this.noise1[var12] / 512.0;
                    double var36 = this.noise2[var12] / 512.0;
                    double var38 = (this.noise3[var12] / 10.0 + 1.0) / 2.0;
                    if (var38 < 0.0) {
                        var30 = var34;
                    } else if (var38 > 1.0) {
                        var30 = var36;
                    } else {
                        var30 = var34 + (var36 - var34) * var38;
                    }

                    var30 -= var32;
                    if (var46 > par6 - 4) {
                        double var40 = (double)((float)(var46 - (par6 - 4)) / 3.0F);
                        var30 = var30 * (1.0 - var40) + -10.0 * var40;
                    }

                    par1ArrayOfDouble[var12] = var30;
                    ++var12;
                }
            }
        }

        return par1ArrayOfDouble;
    }
    @Overwrite
    public void replaceBlocksForBiome(int par1, int par2, byte[] par3ArrayOfByte, BiomeBase[] par4ArrayOfBiomeGenBase) {
        boolean prevent_pockmarking = this.worldObj.getWorldInfo().getEarliestMITEReleaseRunIn() >= 165;
        boolean use_improved_stone_exposing = this.worldObj.getWorldInfo().getEarliestMITEReleaseRunIn() >= 168;
        byte var5 = 63;
        double var6 = 0.03125;
        if (use_improved_stone_exposing) {
            this.stoneNoise = this.noiseGen4.generateNoiseOctaves(this.stoneNoise, par1 * 16, 0, par2 * 16, 16, 1, 16, var6 * 2.0, var6 * 2.0, var6 * 2.0);
            this.stone_noise_2 = this.noiseGen4.generateNoiseOctaves(this.stone_noise_2, par1 * 16, 0, par2 * 16, 16, 1, 16, var6 * 16.0, var6 * 16.0, var6 * 16.0);
            this.stone_noise_3 = this.noiseGen8.generateNoiseOctaves(this.stone_noise_3, par1 * 16, 0, par2 * 16, 16, 1, 16, var6 * 32.0, var6 * 32.0, var6 * 32.0);
        } else {
            this.stoneNoise = this.noiseGen4.generateNoiseOctaves(this.stoneNoise, par1 * 16, par2 * 16, 0, 16, 16, 1, var6 * 2.0, var6 * 2.0, var6 * 2.0);
        }

        for(int var8 = 0; var8 < 16; ++var8) {
            for(int var9 = 0; var9 < 16; ++var9) {
                BiomeBase var10 = par4ArrayOfBiomeGenBase[var9 + var8 * 16];
                float var11 = var10.getFloatTemperature();
                int local_xz_index = var8 + var9 * 16;
                double var12_double;
                if (use_improved_stone_exposing) {
                    var12_double = this.stoneNoise[local_xz_index] / 3.0 + 3.0 - 0.5;
                } else {
                    var12_double = this.stoneNoise[local_xz_index] / 3.0 + 3.0 + this.rand.nextDouble() * 0.25;
                }

                int var12 = (int)var12_double;
                int var13 = -1;
                byte var14 = var10.topBlock;
                byte var15 = var10.fillerBlock;
                int threshold = use_improved_stone_exposing ? (int)(this.stone_noise_2[local_xz_index] / 3.0 + 1.0) : 0;

                for(int var16 = 127; var16 >= 0; --var16) {
                    int var17 = (var9 * 16 + var8) * 128 + var16;
                    if (var16 <= 0 + this.rand.nextInt(5)) {
                        par3ArrayOfByte[var17] = (byte)Block.bedrock.blockID;
                    } else {
                        byte var18 = par3ArrayOfByte[var17];
                        if (var18 == 0) {
                            var13 = -1;
                        } else if (var18 == Block.stone.blockID) {
                            if (var13 == -1) {
                                boolean above_water_table = var16 >= var5;
                                if (var12 <= 0) {
                                    if (!use_improved_stone_exposing) {
                                        var14 = prevent_pockmarking ? var10.topBlock : 0;
                                    } else {
                                        var14 = var12_double < 0.25 ? 0 : (threshold <= 0 && above_water_table ? var10.topBlock : 0);
                                        if (var14 == 0 && above_water_table && var12_double > 0.7) {
                                            if (var12_double > 0.95) {
                                                var14 = var10.topBlock;
                                            } else if (this.stone_noise_3[local_xz_index] < 1.0) {
                                                var14 = (byte)Block.stone.blockID;
                                            }
                                        }
                                    }

                                    var15 = (byte)Block.stone.blockID;
                                } else if (var16 >= var5 - 4 && var16 <= var5 + 1) {
                                    var14 = var10.topBlock;
                                    var15 = var10.fillerBlock;
                                }

                                if (!above_water_table && var14 == 0) {
                                    if (var11 < 0.15F) {
                                        var14 = (byte)Block.ice.blockID;
                                    } else {
                                        var14 = (byte)Block.waterStill.blockID;
                                    }
                                }

                                var13 = var12;
                                if (var16 >= var5 - 1) {
                                    par3ArrayOfByte[var17] = var14;
                                } else {
                                    par3ArrayOfByte[var17] = var15;
                                }
                            } else if (var13 > 0) {
                                --var13;
                                par3ArrayOfByte[var17] = var15;
                                if (var13 == 0 && var15 == Block.sand.blockID) {
                                    var13 = this.rand.nextInt(4);
                                    var15 = (byte)Block.sandStone.blockID;
                                }
                            }
                        }
                    }
                }
            }
        }

    }
    @Overwrite
    public void generateTerrain(int par1, int par2, byte[] par3ArrayOfByte) {
        byte var4 = 4;
        byte var5 = 16;
        byte var6 = 63;
        int var7 = var4 + 1;
        byte var8 = 17;
        int var9 = var4 + 1;
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, par1 * 4 - 2, par2 * 4 - 2, var7 + 5, var9 + 5);
        this.noiseArray = this.initializeNoiseField(this.noiseArray, par1 * var4, 0, par2 * var4, var7, var8, var9);

        for(int var10 = 0; var10 < var4; ++var10) {
            for(int var11 = 0; var11 < var4; ++var11) {
                for(int var12 = 0; var12 < var5; ++var12) {
                    double var13 = 0.125;
                    double var15 = this.noiseArray[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 0];
                    double var17 = this.noiseArray[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 0];
                    double var19 = this.noiseArray[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 0];
                    double var21 = this.noiseArray[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 0];
                    double var23 = (this.noiseArray[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 1] - var15) * var13;
                    double var25 = (this.noiseArray[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 1] - var17) * var13;
                    double var27 = (this.noiseArray[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 1] - var19) * var13;
                    double var29 = (this.noiseArray[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 1] - var21) * var13;

                    for(int var31 = 0; var31 < 8; ++var31) {
                        double var32 = 0.25;
                        double var34 = var15;
                        double var36 = var17;
                        double var38 = (var19 - var15) * var32;
                        double var40 = (var21 - var17) * var32;

                        for(int var42 = 0; var42 < 4; ++var42) {
                            int var43 = var42 + var10 * 4 << 11 | 0 + var11 * 4 << 7 | var12 * 8 + var31;
                            short var44 = 128;
                            var43 -= var44;
                            double var45 = 0.25;
                            double var49 = (var36 - var34) * var45;
                            double var47 = var34 - var49;

                            for(int var51 = 0; var51 < 4; ++var51) {
                                if ((var47 += var49) > 0.0) {
                                    par3ArrayOfByte[var43 += var44] = (byte)Block.stone.blockID;
                                } else if (var12 * 8 + var31 < var6) {
                                    par3ArrayOfByte[var43 += var44] = (byte)Block.waterStill.blockID;
                                } else {
                                    par3ArrayOfByte[var43 += var44] = 0;
                                }
                            }

                            var34 += var38;
                            var36 += var40;
                        }

                        var15 += var23;
                        var17 += var25;
                        var19 += var27;
                        var21 += var29;
                    }
                }
            }
        }
    }
//    @Shadow
//    private WorldGenBase caveGenerator = new WorldGenCaves();
//    @Shadow
//    private WorldGenStronghold strongholdGenerator = new WorldGenStronghold();
//    @Shadow
//    private WorldGenVillage villageGenerator = new WorldGenVillage();
//    @Shadow
//    private WorldGenMineshaft mineshaftGenerator = new WorldGenMineshaft();
//    @Shadow
//    private WorldGenLargeFeature scatteredFeatureGenerator = new WorldGenLargeFeature();
//    @Shadow
//    private WorldGenBase ravineGenerator = new WorldGenCanyon();
//    @Shadow
//    private MapGenCaveNetwork cave_network_generator = new MapGenCaveNetwork();
//    @Shadow
//    public static void placeRandomCobwebs(int chunk_x, int chunk_z, byte[] block_ids, Random rand) {
//    }
//    @Shadow
//    private void performSandFalls(HashMap pending_sand_falls, byte[] block_ids) {
//    }
//    @Shadow
//    @Final
//    private boolean mapFeaturesEnabled;
//    @Overwrite
//    public Chunk provideChunk(int par1, int par2) {
//        if (!this.worldObj.isChunkWithinBlockDomain(par1, par2)) {
//            Chunk chunk = new Chunk(this.worldObj, par1, par2);
//            chunk.generateSkylightMap(true);
//            return chunk;
//        } else {
//            this.rand.setSeed((long)par1 * 341873128712L + (long)par2 * 132897987541L);
//            byte[] var3 = new byte['耀'];
//            this.generateTerrain(par1, par2, var3);
//            this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
//            this.replaceBlocksForBiome(par1, par2, var3, this.biomesForGeneration);
//            this.caveGenerator.generate(this, this.worldObj, par1, par2, var3);
//            this.ravineGenerator.generate(this, this.worldObj, par1, par2, var3);
//            if (this.worldObj.getWorldInfo().getEarliestMITEReleaseRunIn() >= 139) {
//                this.cave_network_generator.generate(this, this.worldObj, par1, par2, var3);
//            }
//
//            placeRandomCobwebs(par1, par2, var3, this.rand);
//            if (this.mapFeaturesEnabled) {
//                this.mineshaftGenerator.generate(this, this.worldObj, par1, par2, var3);
//                this.villageGenerator.generate(this, this.worldObj, par1, par2, var3);
//                this.strongholdGenerator.generate(this, this.worldObj, par1, par2, var3);
//                this.scatteredFeatureGenerator.generate(this, this.worldObj, par1, par2, var3);
//            }
//
//            if (this.worldObj.pending_sand_falls != null && this.worldObj.getWorldInfo().getEarliestMITEReleaseRunIn() >= 189) {
//                this.performSandFalls(this.worldObj.pending_sand_falls, var3);
//                this.worldObj.pending_sand_falls = null;
//            }
//
//            Chunk var4 = new Chunk(this.worldObj, var3, par1, par2);
//            byte[] var5 = var4.getBiomeArray();
//
//            for(int var6 = 0; var6 < var5.length; ++var6) {
//                var5[var6] = (byte)this.biomesForGeneration[var6].biomeID;
//            }
//
//            var4.generateSkylightMap(true);
//            if (this.worldObj.pending_sand_falls != null) {
//                var4.pending_sand_falls = this.worldObj.pending_sand_falls;
//                this.worldObj.pending_sand_falls = null;
//            }
//
//            return var4;
//        }
//    }

}
