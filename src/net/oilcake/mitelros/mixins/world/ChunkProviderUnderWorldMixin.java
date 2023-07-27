//package net.oilcake.mitelros.mixins.world;
//
//import net.minecraft.ChunkProviderUnderworld;
//import net.minecraft.IChunkProvider;
//import net.minecraft.NoiseGeneratorOctaves;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Overwrite;
//import org.spongepowered.asm.mixin.Shadow;
//
//@Mixin(ChunkProviderUnderworld.class)
//public abstract class ChunkProviderUnderWorldMixin implements IChunkProvider{
//    @Shadow
//    double[] noiseData1;
//    @Shadow
//    double[] noiseData2;
//    @Shadow
//    double[] noiseData3;
//    @Shadow
//    double[] noiseData4;
//    @Shadow
//    double[] noiseData5;
//    @Shadow
//    private NoiseGeneratorOctaves netherNoiseGen1;
//    @Shadow
//    private NoiseGeneratorOctaves netherNoiseGen2;
//    @Shadow
//    private NoiseGeneratorOctaves netherNoiseGen3;
//    @Shadow
//    public NoiseGeneratorOctaves netherNoiseGen6;
//    @Shadow
//    public NoiseGeneratorOctaves netherNoiseGen7;
//    @Overwrite
//    private double[] initializeNoiseField(double[] par1ArrayOfDouble, int par2, int par3, int par4, int par5, int par6, int par7) {
//        if (par1ArrayOfDouble == null) {
//            par1ArrayOfDouble = new double[par5 * par6 * par7];
//        }
//
//        double var8 = 684.412;
//        double var10 = 2053.236;
//        this.noiseData4 = this.netherNoiseGen6.generateNoiseOctaves(this.noiseData4, par2, par3, par4, par5, 1, par7, 1.0, 0.0, 1.0);
//        this.noiseData5 = this.netherNoiseGen7.generateNoiseOctaves(this.noiseData5, par2, par3, par4, par5, 1, par7, 100.0, 0.0, 100.0);
//        this.noiseData1 = this.netherNoiseGen3.generateNoiseOctaves(this.noiseData1, par2, par3, par4, par5, par6, par7, var8 / 80.0, var10 / 60.0, var8 / 80.0);
//        this.noiseData2 = this.netherNoiseGen1.generateNoiseOctaves(this.noiseData2, par2, par3, par4, par5, par6, par7, var8, var10, var8);
//        this.noiseData3 = this.netherNoiseGen2.generateNoiseOctaves(this.noiseData3, par2, par3, par4, par5, par6, par7, var8, var10, var8);
//        int var12 = 0;
//        int var13 = 0;
//        double[] var14 = new double[par6];
//
//        int var15;
//        for(var15 = 0; var15 < par6; ++var15) {
//            var14[var15] = Math.cos((double)var15 * Math.PI * 6.0 / (double)par6) * 2.0;
//            double var16 = (double)var15;
//            if (var15 > par6 / 2) {
//                var16 = (double)(par6 - 1 - var15);
//            }
//
//            if (var16 < 4.0) {
//                var16 = 4.0 - var16;
//                var14[var15] -= var16 * var16 * var16 * 10.0;
//            }
//        }
//
//        for(var15 = 0; var15 < par5; ++var15) {
//            for(int var36 = 0; var36 < par7; ++var36) {
//                double var17 = (this.noiseData4[var13] + 256.0) / 512.0;
//                if (var17 > 1.0) {
//                    var17 = 1.0;
//                }
//
//                double var19 = 0.0;
//                double var21 = this.noiseData5[var13] / 8000.0;
//                if (var21 < 0.0) {
//                    var21 = -var21;
//                }
//
//                var21 = var21 * 3.0 - 3.0;
//                if (var21 < 0.0) {
//                    var21 /= 2.0;
//                    if (var21 < -1.0) {
//                        var21 = -1.0;
//                    }
//
//                    var21 /= 1.4;
//                    var21 /= 2.0;
//                    var17 = 0.0;
//                } else {
//                    if (var21 > 1.0) {
//                        var21 = 1.0;
//                    }
//
//                    var21 /= 6.0;
//                }
//
//                var17 += 0.5;
//                var21 = var21 * (double)par6 / 16.0;
//                ++var13;
//
//                for(int var23 = 0; var23 < par6; ++var23) {
//                    double var24 = 0.0;
//                    double var26 = var14[var23];
//                    double var28 = this.noiseData2[var12] / 512.0;
//                    double var30 = this.noiseData3[var12] / 512.0;
//                    double var32 = (this.noiseData1[var12] / 10.0 + 1.0) / 2.0;
//                    if (var32 < 0.0) {
//                        var24 = var28;
//                    } else if (var32 > 1.0) {
//                        var24 = var30;
//                    } else {
//                        var24 = var28 + (var30 - var28) * var32;
//                    }
//
//                    var24 -= var26;
//                    double var34;
//                    if (var23 > par6 - 4) {
//                        var34 = (double)((float)(var23 - (par6 - 4)) / 3.0F);
//                        var24 = var24 * (1.0 - var34) + -10.0 * var34;
//                    }
//
//                    if ((double)var23 < var19) {
//                        var34 = (var19 - (double)var23) / 4.0;
//                        if (var34 < 0.0) {
//                            var34 = 0.0;
//                        }
//
//                        if (var34 > 1.0) {
//                            var34 = 1.0;
//                        }
//
//                        var24 = var24 * (1.0 - var34) + -10.0 * var34;
//                    }
//
//                    par1ArrayOfDouble[var12] = var24;
//                    ++var12;
//                }
//            }
//        }
//
//        return par1ArrayOfDouble;
//    }
//}
