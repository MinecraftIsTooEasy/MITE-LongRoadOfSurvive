package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.nio.FloatBuffer;
import java.util.List;
import java.util.Random;

@Mixin(EntityRenderer.class)
public final class EntityRendererMixin {
//    private float a(EntityPlayer par1EntityPlayer, float par2) {
//        int var3 = par1EntityPlayer.getActivePotionEffect(MobEffectList.nightVision).getDuration();
//        return var3 > 1200 ? 1.0F : 0.7F + MathHelper.sin(((float)var3 - par2) * 3.1415927F * 0.2F) * 0.3F;
//    }
    public float pi = 3.1415927F;

    @Overwrite
    private float a(EntityPlayer par1EntityPlayer, float par2) {
        int var3 = par1EntityPlayer.getActivePotionEffect(MobEffectList.nightVision).getDuration();
        return var3 > 99 ? (0.8F + (MathHelper.sin(((float)var3 - par2) * pi * 0.01F) * 0.2F)) : Math.min((0.8F + (MathHelper.sin(((float)var3 - par2) * pi * 0.01F) * 0.2F)),((float)var3 * 0.01F));
    }
    @Overwrite
    protected void d(float par1) {
        if (this.q.i.ticksExisted >= 1) {
            float var2 = this.q.f.getRainStrength(par1);
            if (var2 > 0.0F) {
                boolean is_blood_moon = this.q.f.isBloodMoon24HourPeriod();
                this.b((double)par1);
                if (this.h == null) {
                    this.h = new float[1024];
                    this.i = new float[1024];

                    for(int var3 = 0; var3 < 32; ++var3) {
                        for(int var4 = 0; var4 < 32; ++var4) {
                            float var5 = (float)(var4 - 16);
                            float var6 = (float)(var3 - 16);
                            float var7 = MathHelper.sqrt_float(var5 * var5 + var6 * var6);
                            this.h[var3 << 5 | var4] = -var6 / var7;
                            this.i[var3 << 5 | var4] = var5 / var7;
                        }
                    }
                }

                EntityLiving var41 = this.q.i;
                bdd var42 = this.q.f;
                int var43 = MathHelper.floor_double(var41.posX);
                int var44 = MathHelper.floor_double(var41.posY);
                int var45 = MathHelper.floor_double(var41.posZ);
                bfq var8 = bfq.a;
                GL11.glDisable(2884);
                GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                GL11.glAlphaFunc(516, 0.01F);
                double var9 = var41.lastTickPosX + (var41.posX - var41.lastTickPosX) * (double)par1;
                double var11 = var41.lastTickPosY + (var41.posY - var41.lastTickPosY) * (double)par1;
                double var13 = var41.lastTickPosZ + (var41.posZ - var41.lastTickPosZ) * (double)par1;
                int var15 = MathHelper.floor_double(var11);
                byte var16 = 5;
                if (this.q.u.isFancyGraphicsEnabled()) {
                    var16 = 10;
                }

                boolean var17 = false;
                byte var18 = -1;
                float var19 = (float)this.s + par1;
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                var17 = false;
                int type = this.q.f.getPrecipitationType(this.last_precipitation_type);
                this.last_precipitation_type = type;

                for(int var20 = var45 - var16; var20 <= var45 + var16; ++var20) {
                    for(int var21 = var43 - var16; var21 <= var43 + var16; ++var21) {
                        if ((var21 != var43 || var20 != var45) && var42.chunkExistsAndIsNotEmptyFromBlockCoordsC(var21, var20)) {
                            int dx = var21 - var43;
                            int dz = var20 - var45;
                            int index_wrapped = MathHelper.getWrappedIndex(var20 * 32 + var21, 32768);
                            int var22 = (var20 - var45 + 16) * 32 + var21 - var43 + 16;
                            float var23 = this.h[var22] * 0.5F;
                            float var24 = this.i[var22] * 0.5F;
                            BiomeBase var25 = var42.getBiomeGenForCoords(var21, var20);
                            if (var25.canSpawnLightningBolt(is_blood_moon) || var25.getEnableSnow()) {
                                int var26 = var42.getPrecipitationHeight(var21, var20);
                                if (var26 < 0) {
                                    var26 = 0;
                                }

                                int var27 = var44 - var16;
                                int var28 = var44 + var16;
                                if (var27 < var26) {
                                    var27 = var26;
                                }

                                if (var28 < var26) {
                                    var28 = var26;
                                }

                                float var29 = 1.0F;
                                int var30 = var26;
                                if (var26 < var15) {
                                    var30 = var15;
                                }

                                if (var27 != var28) {
                                    double var27_adjusted = var42.getBlockRenderTopY(var21, var27 - 1, var20);
                                    this.ae.setSeed((long)(var21 * var21 * 3121 + var21 * 45238971 ^ var20 * var20 * 418711 + var20 * 13761));
                                    float var31 = var25.getFloatTemperature();
                                    double var35;
                                    float var32;
                                    float var34;
                                    if (var42.getWorldChunkManager().a(var31, var26) >= (this.q.h.getWorld().getWorldSeason() == 3 ? 1.0F : 0.15F)) {
                                        if (var18 != 0) {
                                            if (var18 >= 0) {
                                                var8.a();
                                            }

                                            var18 = 0;
                                            this.q.J().a(o);
                                            var8.b();
                                        }

                                        var32 = ((float)(this.s + var21 * var21 * 3121 + var21 * 45238971 + var20 * var20 * 418711 + var20 * 13761 & 31) + par1) / 32.0F * (3.0F + this.ae.nextFloat());
                                        double var33 = (double)((float)var21 + 0.5F) - var41.posX;
                                        var35 = (double)((float)var20 + 0.5F) - var41.posZ;
                                        float var37 = MathHelper.sqrt_double(var33 * var33 + var35 * var35) / (float)var16;
                                        var34 = 1.0F;
                                        var8.c(var42.h(var21, var30, var20, 0));
                                        var8.a(var34, var34, var34, ((1.0F - var37 * var37) * 0.5F + 0.5F) * var2);
                                        var8.b(-var9 * 1.0, -var11 * 1.0, -var13 * 1.0);
                                        if (RenderingScheme.current == 0) {
                                            var8.a((double)((float)var21 - var23) + 0.5, (double)var27, (double)((float)var20 - var24) + 0.5, (double)(0.0F * var29), (double)((float)var27 * var29 / 4.0F + var32 * var29));
                                            var8.a((double)((float)var21 + var23) + 0.5, (double)var27, (double)((float)var20 + var24) + 0.5, (double)(1.0F * var29), (double)((float)var27 * var29 / 4.0F + var32 * var29));
                                            var8.a((double)((float)var21 + var23) + 0.5, (double)var28, (double)((float)var20 + var24) + 0.5, (double)(1.0F * var29), (double)((float)var28 * var29 / 4.0F + var32 * var29));
                                            var8.a((double)((float)var21 - var23) + 0.5, (double)var28, (double)((float)var20 - var24) + 0.5, (double)(0.0F * var29), (double)((float)var28 * var29 / 4.0F + var32 * var29));
                                        } else {
                                            this.x[0] = (double)((float)var21 - var23 + 0.5F);
                                            this.y[0] = var27_adjusted;
                                            this.z[0] = (double)((float)var20 - var24 + 0.5F);
                                            this.u[0] = 0.0;
                                            this.v[0] = var27_adjusted * (double)var29 / 4.0 + (double)(var32 * var29);
                                            this.x[1] = (double)((float)var21 + var23 + 0.5F);
                                            this.y[1] = var27_adjusted;
                                            this.z[1] = (double)((float)var20 + var24 + 0.5F);
                                            this.u[1] = (double)var29;
                                            this.v[1] = this.v[0];
                                            this.x[2] = this.x[1];
                                            this.y[2] = (double)var28;
                                            this.z[2] = this.z[1];
                                            this.u[2] = (double)var29;
                                            this.v[2] = (double)((float)var28 * var29 / 4.0F + var32 * var29);
                                            this.x[3] = this.x[0];
                                            this.y[3] = (double)var28;
                                            this.z[3] = this.z[0];
                                            this.u[3] = 0.0;
                                            this.v[3] = this.v[2];
                                            var8.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
                                        }

                                        var8.b(0.0, 0.0, 0.0);
                                    } else {
                                        if (var18 != 1) {
                                            if (var18 >= 0) {
                                                var8.a();
                                            }

                                            var18 = 1;
                                            this.q.J().a(p);
                                            var8.b();
                                        }

                                        var32 = ((float)(this.s & 511) + par1) / 512.0F;
                                        float vertical_offset;
                                        float horizontal_offset;
                                        if (type == 0) {
                                            horizontal_offset = (float)RNG.int_32[index_wrapped] / 32.0F;
                                            vertical_offset = var19 * 0.004F;
                                        } else if (type == 1) {
                                            horizontal_offset = 1.0F + var19 * RNG.float_1_minus_float_1[index_wrapped] * 0.004F;
                                            vertical_offset = var19 * 0.004F;
                                        } else if (type == 2) {
                                            horizontal_offset = 1.0F + var19 * RNG.float_1_minus_float_1[index_wrapped] * 0.008F;
                                            vertical_offset = var19 * 0.004F;
                                        } else if (type == 3) {
                                            horizontal_offset = 1.0F + var19 * RNG.float_1_minus_float_1[index_wrapped] * 0.012F;
                                            vertical_offset = var19 * 0.003F;
                                        } else {
                                            horizontal_offset = this.ae.nextFloat() + var19 * 0.01F * (float)this.ae.nextGaussian();
                                            vertical_offset = this.ae.nextFloat() + var19 * (float)this.ae.nextGaussian() * 0.001F;
                                        }

                                        var34 = vertical_offset + RNG.float_1[index_wrapped];
                                        var35 = (double)((float)var21 + 0.5F) - var41.posX;
                                        double var47 = (double)((float)var20 + 0.5F) - var41.posZ;
                                        float var39 = MathHelper.sqrt_double(var35 * var35 + var47 * var47) / (float)var16;
                                        float var40 = 1.0F;
                                        var8.c((var42.h(var21, var30, var20, 0) * 3 + 15728880) / 4);
                                        var8.a(var40, var40, var40, MathHelper.clamp_float(0.85F - var39 * 0.35F, 0.0F, 1.0F) * var2);
                                        var8.b(-var9 * 1.0, -var11 * 1.0, -var13 * 1.0);
                                        if (RenderingScheme.current == 0) {
                                            var8.a((double)((float)var21 - var23) + 0.5, (double)var27, (double)((float)var20 - var24) + 0.5, (double)(0.0F * var29 + horizontal_offset), (double)((float)var27 * var29 / 4.0F + var32 * var29 + var34));
                                            var8.a((double)((float)var21 + var23) + 0.5, (double)var27, (double)((float)var20 + var24) + 0.5, (double)(1.0F * var29 + horizontal_offset), (double)((float)var27 * var29 / 4.0F + var32 * var29 + var34));
                                            var8.a((double)((float)var21 + var23) + 0.5, (double)var28, (double)((float)var20 + var24) + 0.5, (double)(1.0F * var29 + horizontal_offset), (double)((float)var28 * var29 / 4.0F + var32 * var29 + var34));
                                            var8.a((double)((float)var21 - var23) + 0.5, (double)var28, (double)((float)var20 - var24) + 0.5, (double)(0.0F * var29 + horizontal_offset), (double)((float)var28 * var29 / 4.0F + var32 * var29 + var34));
                                        } else {
                                            this.x[0] = (double)((float)var21 - var23 + 0.5F);
                                            this.y[0] = var27_adjusted;
                                            this.z[0] = (double)((float)var20 - var24 + 0.5F);
                                            this.u[0] = (double)horizontal_offset;
                                            this.v[0] = var27_adjusted * (double)var29 / 4.0 + (double)(var32 * var29) + (double)var34;
                                            this.x[1] = (double)((float)var21 + var23 + 0.5F);
                                            this.y[1] = var27_adjusted;
                                            this.z[1] = (double)((float)var20 + var24 + 0.5F);
                                            this.u[1] = (double)(var29 + horizontal_offset);
                                            this.v[1] = this.v[0];
                                            this.x[2] = this.x[1];
                                            this.y[2] = (double)var28;
                                            this.z[2] = this.z[1];
                                            this.u[2] = this.u[1];
                                            this.v[2] = (double)((float)var28 * var29 / 4.0F + var32 * var29 + var34);
                                            this.x[3] = this.x[0];
                                            this.y[3] = (double)var28;
                                            this.z[3] = this.z[0];
                                            this.u[3] = (double)horizontal_offset;
                                            this.v[3] = this.v[2];
                                            if (dx * dx + dz * dz <= 9) {
                                                float yaw = (float)MathHelper.getYawInDegrees((double)var21 + 0.5, (double)var20 + 0.5, var9, var13);
                                                Vec3D right_side = MathHelper.getNormalizedVector(yaw + 90.0F, 0.0F, var42.getWorldVec3Pool());
                                                this.x[0] = (double)((float)var21 + 0.5F) + right_side.xCoord / 2.0;
                                                this.x[1] = (double)((float)var21 + 0.5F) - right_side.xCoord / 2.0;
                                                this.x[2] = this.x[1];
                                                this.x[3] = this.x[0];
                                                this.z[0] = (double)((float)var20 + 0.5F) + right_side.zCoord / 2.0;
                                                this.z[1] = (double)((float)var20 + 0.5F) - right_side.zCoord / 2.0;
                                                this.z[2] = this.z[1];
                                                this.z[3] = this.z[0];
                                            }

                                            var8.add4VerticesWithUV(this.x, this.y, this.z, this.u, this.v);
                                        }

                                        var8.b(0.0, 0.0, 0.0);
                                    }
                                }
                            }
                        }
                    }
                }

                if (var18 >= 0) {
                    var8.a();
                }

                GL11.glEnable(2884);
                GL11.glDisable(3042);
                GL11.glAlphaFunc(516, 0.1F);
                this.a((double)par1);
            }

        }
    }
    private void g() {
        float var1 = this.q.f.getRainStrength(1.0F);
        boolean is_blood_moon = this.q.f.isBloodMoon24HourPeriod();
        if (!this.q.u.isFancyGraphicsEnabled()) {
            var1 /= 2.0F;
        }

        if (var1 != 0.0F) {
            this.ae.setSeed((long)this.s * 312987231L);
            EntityLiving var2 = this.q.i;
            bdd var3 = this.q.f;
            int var4 = MathHelper.floor_double(var2.posX);
            int var5 = MathHelper.floor_double(var2.posY);
            int var6 = MathHelper.floor_double(var2.posZ);
            byte var7 = 10;
            double var8 = 0.0;
            double var10 = 0.0;
            double var12 = 0.0;
            int var14 = 0;
            int var15 = (int)(100.0F * var1 * var1);
            if (this.q.u.am == 1) {
                var15 >>= 1;
            } else if (this.q.u.am == 2) {
                var15 = 0;
            }

            int index = Minecraft.getThreadIndex();

            for(int var16 = 0; var16 < var15; ++var16) {
                int var17 = var4 + this.ae.nextInt(var7) - this.ae.nextInt(var7);
                int var18 = var6 + this.ae.nextInt(var7) - this.ae.nextInt(var7);
                int var19 = var3.getPrecipitationHeight(var17, var18);
                int var20 = var3.getBlockId(var17, var19 - 1, var18);
                BiomeBase var21 = var3.getBiomeGenForCoords(var17, var18);
                if (var19 <= var5 + var7 && var19 >= var5 - var7 && var21.canSpawnLightningBolt(is_blood_moon) && var21.getFloatTemperature() >= (this.q.h.getWorld().getWorldSeason() == 3 ? 1.0F : 0.2F) && var20 > 0) {
                    float var22 = this.ae.nextFloat();
                    float var23 = this.ae.nextFloat();
                    Block block = Block.getBlock(var20);
                    double pos_y;
                    if (block.blockMaterial == Material.lava) {
                        pos_y = (double)((float)var19 - BlockFluids.getFluidHeightPercent(this.q.f.getBlockMetadata(var17, var19 - 1, var18)) + 0.1F + 0.125F);
                        this.q.k.a(new bel(var3, (double)((float)var17 + var22), pos_y, (double)((float)var18 + var23), 0.0, 0.0, 0.0));
                    } else {
                        if (block.blockMaterial.isLiquid()) {
                            pos_y = (double)((float)var19 - BlockFluids.getFluidHeightPercent(this.q.f.getBlockMetadata(var17, var19 - 1, var18)) + 0.1F + 0.125F);
                        } else if (block.isAlwaysStandardFormCube()) {
                            pos_y = (double)((float)var19 + 0.1F);
                        } else {
                            if (block instanceof BlockTrapdoor && BlockTrapdoor.isTrapdoorOpen(this.q.f.getBlockMetadata(var17, var19 - 1, var18))) {
                                continue;
                            }

                            block.setBlockBoundsBasedOnStateAndNeighbors(this.q.f, var17, var19 - 1, var18);
                            pos_y = (double)(var19 - 1) + block.getBlockBoundsMaxY(index) + 0.10000000149011612;
                        }

                        ++var14;
                        if (this.ae.nextInt(var14) == 0) {
                            var8 = (double)((float)var17 + var22);
                            var10 = (double)((float)var19 + 0.1F) - Block.blocksList[var20].getBlockBoundsMinY(index);
                            var12 = (double)((float)var18 + var23);
                        }

                        this.q.k.a(new bet(var3, (double)((float)var17 + var22), pos_y, (double)((float)var18 + var23)));
                    }
                }
            }

            boolean player_is_outdoors = this.q.h.isOutdoors();
            float sleep_factor = 1.0F - (float)this.q.h.falling_asleep_counter / 50.0F;
            float distance_from_rain_factor = (float)Math.pow((double)this.q.raining_strength_for_render_view_entity, 4.0);
            if (sleep_factor < 0.0F) {
                sleep_factor = 0.0F;
            }

            if (var14 > 0 && this.ae.nextInt(3) < this.af++) {
                this.af = 0;
                if (var10 > var2.posY + 1.0 && var3.getPrecipitationHeight(MathHelper.floor_double(var2.posX), MathHelper.floor_double(var2.posZ)) > MathHelper.floor_double(var2.posY)) {
                    if (player_is_outdoors) {
                        this.q.f.playSound(var8, var10, var12, "ambient.weather.rain", 0.1F * sleep_factor * distance_from_rain_factor, 0.5F, false);
                    } else {
                        this.q.f.playSound(var8, var10, var12, "ambient.weather.rain", 0.025F * sleep_factor * distance_from_rain_factor, 0.125F, false);
                    }
                } else if (player_is_outdoors) {
                    this.q.f.playSound(var8, var10, var12, "ambient.weather.rain", 0.2F * sleep_factor * distance_from_rain_factor, 1.0F, false);
                } else {
                    this.q.f.playSound(var8, var10, var12, "ambient.weather.rain", 0.05F * sleep_factor * distance_from_rain_factor, 0.25F, false);
                }
            }
        }

    }
    @Shadow
    private int af;
    @Shadow
    public void b(double par1) {
    }

    @Shadow
    public void a(double par1) {
    }
    @Shadow
    private static final bjo o = new bjo("textures/environment/rain.png");
    @Shadow
    private static final bjo p = new bjo("textures/environment/snow.png");
    @Shadow
    private Minecraft q;
    @Shadow
    public bfj c;
    @Shadow
    private int s;
    @Shadow
    private Random ae = new Random();
    @Shadow
    float[] h;
    @Shadow
    float[] i;
    @Shadow
    private double[] x = new double[4];
    @Shadow
    private double[] y = new double[4];
    @Shadow
    private double[] z = new double[4];
    @Shadow
    private double[] u = new double[4];
    @Shadow
    private double[] v = new double[4];
    @Shadow
    private float[] r = new float[4];
    @Shadow
    private float[] g = new float[4];
    @Shadow
    private float[] b = new float[4];
    @Shadow
    private int last_precipitation_type;

}
