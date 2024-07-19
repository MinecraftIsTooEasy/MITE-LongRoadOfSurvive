package net.oilcake.mitelros.mixins.entity;

import java.util.Random;
import net.minecraft.BiomeGenBase;
import net.minecraft.Block;
import net.minecraft.BlockFluid;
import net.minecraft.BlockTrapDoor;
import net.minecraft.DynamicTexture;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;
import net.minecraft.EntityRainFX;
import net.minecraft.EntityRenderer;
import net.minecraft.EntitySmokeFX;
import net.minecraft.ItemRenderer;
import net.minecraft.Material;
import net.minecraft.MathHelper;
import net.minecraft.Minecraft;
import net.minecraft.Potion;
import net.minecraft.RNG;
import net.minecraft.RenderingScheme;
import net.minecraft.ResourceLocation;
import net.minecraft.Tessellator;
import net.minecraft.Vec3;
import net.minecraft.WorldClient;
import net.oilcake.mitelros.util.CurseExtend;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({EntityRenderer.class})
public final class EntityRendererMixin {
   @Shadow
   private float field_82831_U;
   @Shadow
   private float field_82832_V;
   @Shadow
   @Final
   private DynamicTexture lightmapTexture;
   @Shadow
   @Final
   private int[] lightmapColors;
   @Shadow
   private boolean lightmapUpdateNeeded;
   @Shadow
   float torchFlickerX;
   public float pi = 3.1415927F;
   @Shadow
   private int rainSoundCounter;
   @Shadow
   @Final
   private static ResourceLocation locationRainPng;
   @Shadow
   @Final
   private static ResourceLocation locationSnowPng;
   @Shadow
   private Minecraft mc;
   @Shadow
   public ItemRenderer itemRenderer;
   @Shadow
   private int rendererUpdateCount;
   @Shadow
   private Random random = new Random();
   @Shadow
   float[] rainXCoords;
   @Shadow
   float[] rainYCoords;
   @Shadow
   private double[] mouseFilterDummy2 = new double[4];
   @Shadow
   private double[] mouseFilterDummy3 = new double[4];
   @Shadow
   private double[] z = new double[4];
   @Shadow
   private double[] mouseFilterXAxis = new double[4];
   @Shadow
   private double[] v = new double[4];
   @Shadow
   private float[] farPlaneDistance = new float[4];
   @Shadow
   private float[] torchFlickerDY = new float[4];
   @Shadow
   private float[] anaglyphField = new float[4];
   @Shadow
   private int last_precipitation_type;

   @Overwrite
   private void updateLightmap(float par1) {
      WorldClient var2 = this.mc.theWorld;
      if (var2 != null) {
         for(int var3 = 0; var3 < 256; ++var3) {
            float skylight_brightness = (float)(var3 / 16) / 15.0F;
            float blocklight_brightness = (float)(var3 % 16) / 15.0F;
            float var4 = var2.getSunBrightness(1.0F) * 0.95F + 0.05F;
            float var5 = var2.provider.lightBrightnessTable[var3 / 16] * var4;
            float var6 = var2.provider.lightBrightnessTable[var3 % 16] * (this.torchFlickerX * 0.1F + 1.5F);
            float var7;
            if (var2.isOverworld() && var2.getMoonAscensionFactor() > 0.0F) {
               var7 = MathHelper.clamp_float(var2.getMoonAscensionFactor(), 0.0F, 0.2F) * 5.0F;
               var5 = var5 * (1.0F - var7) + var5 * var7 * var2.getMoonBrightness(par1, true);
            }

            if (var2.lastLightningBolt > 0) {
               var7 = (float)Math.pow((double)this.mc.raining_strength_for_render_view_entity, 4.0);
               var5 = var2.provider.lightBrightnessTable[var3 / 16] * var7 + var5 * (1.0F - var7);
            }

            var7 = var5 * (var2.getSunBrightness(1.0F) * 0.65F + 0.35F);
            float var8 = var5 * (var2.getSunBrightness(1.0F) * 0.65F + 0.35F);
            float var11 = var6 * ((var6 * 0.6F + 0.4F) * 0.6F + 0.4F);
            float var12 = var6 * (var6 * var6 * 0.6F + 0.4F);
            float var13 = var7 + var6;
            float var14 = var8 + var11;
            float var15 = var5 + var12;
            var13 = var13 * 0.96F + 0.03F;
            var14 = var14 * 0.96F + 0.03F;
            var15 = var15 * 0.96F + 0.03F;
            float var16;
            if (this.field_82831_U > 0.0F) {
               var16 = this.field_82832_V + (this.field_82831_U - this.field_82832_V) * par1;
               var13 = var13 * (1.0F - var16) + var13 * 0.7F * var16;
               var14 = var14 * (1.0F - var16) + var14 * 0.6F * var16;
               var15 = var15 * (1.0F - var16) + var15 * 0.6F * var16;
            }

            if (var2.provider.dimensionId == 1) {
               var13 = 0.22F + var6 * 0.75F;
               var14 = 0.28F + var11 * 0.75F;
               var15 = 0.25F + var12 * 0.75F;
            }

            float var17;
            if (this.mc.thePlayer.isPotionActive(Potion.nightVision) || Minecraft.night_vision_override) {
               var16 = Minecraft.night_vision_override ? 1.0F : this.getNightVisionBrightness(this.mc.thePlayer, par1);
               var17 = 1.0F / var13;
               if (var17 > 1.0F / var14) {
                  var17 = 1.0F / var14;
               }

               if (var17 > 1.0F / var15) {
                  var17 = 1.0F / var15;
               }

               var13 = var13 * (1.0F - var16) + var13 * var17 * var16;
               var14 = var14 * (1.0F - var16) + var14 * var17 * var16;
               var15 = var15 * (1.0F - var16) + var15 * var17 * var16;
            }

            if (var13 > 1.0F) {
               var13 = 1.0F;
            }

            if (var14 > 1.0F) {
               var14 = 1.0F;
            }

            if (var15 > 1.0F) {
               var15 = 1.0F;
            }

            var16 = this.mc.gameSettings.ReportedGamma * this.mc.gameSettings.ReportedGamma - 0.5F;
            if (this.mc.thePlayer.hasCurse(CurseExtend.fear_of_darkness, true)) {
               var16 = -0.5F;
            }

            var17 = 1.0F - var13;
            float var18 = 1.0F - var14;
            float var19 = 1.0F - var15;
            var17 = 1.0F - var17 * var17 * var17 * var17;
            var18 = 1.0F - var18 * var18 * var18 * var18;
            var19 = 1.0F - var19 * var19 * var19 * var19;
            var13 = var13 * (1.0F - var16) + var17 * var16;
            var14 = var14 * (1.0F - var16) + var18 * var16;
            var15 = var15 * (1.0F - var16) + var19 * var16;
            var13 = var13 * 0.96F + 0.03F * var16;
            var14 = var14 * 0.96F + 0.03F * var16;
            var15 = var15 * 0.96F + 0.03F * var16;
            float min;
            if (var2.isBloodMoonNight()) {
               min = MathHelper.clamp_float(1.0F - blocklight_brightness * 2.0F, 0.0F, 1.0F);
               var13 *= 1.0F + 1.0F * MathHelper.clamp_float(var2.getMoonAscensionFactor(), 0.0F, 0.2F) * 5.0F * skylight_brightness * min;
               var14 *= 1.0F + 0.4F * MathHelper.clamp_float(var2.getMoonAscensionFactor(), 0.0F, 0.2F) * 5.0F * skylight_brightness * min;
            } else if (var2.isHarvestMoonNight()) {
               min = MathHelper.clamp_float(1.0F - blocklight_brightness * 2.0F, 0.0F, 1.0F);
               var13 *= 1.0F + 0.6F * MathHelper.clamp_float(var2.getMoonAscensionFactor(), 0.0F, 0.2F) * 4.0F * skylight_brightness * min;
               var14 *= 1.0F + 0.4F * MathHelper.clamp_float(var2.getMoonAscensionFactor(), 0.0F, 0.2F) * 4.0F * skylight_brightness * min;
               var15 *= 1.0F - 0.2F * MathHelper.clamp_float(var2.getMoonAscensionFactor(), 0.0F, 0.2F) * 4.0F * skylight_brightness * min;
            } else if (var2.isBlueMoonNight()) {
               min = MathHelper.clamp_float(1.0F - blocklight_brightness * 2.0F, 0.0F, 1.0F);
               var15 *= 1.0F + 0.5F * MathHelper.clamp_float(var2.getMoonAscensionFactor(), 0.0F, 0.2F) * 2.5F * skylight_brightness * min;
               var14 *= 1.0F + 0.2F * MathHelper.clamp_float(var2.getMoonAscensionFactor(), 0.0F, 0.2F) * 2.5F * skylight_brightness * min;
            }

            if (var13 > 1.0F) {
               var13 = 1.0F;
            }

            if (var14 > 1.0F) {
               var14 = 1.0F;
            }

            if (var15 > 1.0F) {
               var15 = 1.0F;
            }

            if (var13 < 0.0F) {
               var13 = 0.0F;
            }

            if (var14 < 0.0F) {
               var14 = 0.0F;
            }

            if (var15 < 0.0F) {
               var15 = 0.0F;
            }

            short var20 = 255;
            int var21 = (int)(var13 * 255.0F);
            int var22 = (int)(var14 * 255.0F);
            int var23 = (int)(var15 * 255.0F);
            this.lightmapColors[var3] = var20 << 24 | var21 << 16 | var22 << 8 | var23;
         }

         this.lightmapTexture.updateDynamicTexture();
         this.lightmapUpdateNeeded = false;
      }

   }

   @Overwrite
   private float getNightVisionBrightness(EntityPlayer par1EntityPlayer, float par2) {
      int var3 = par1EntityPlayer.getActivePotionEffect(Potion.nightVision).getDuration();
      return var3 > 99 ? 0.9F + MathHelper.sin(((float)var3 - par2) * this.pi * 0.01F) * 0.1F : Math.min(0.9F + MathHelper.sin(((float)var3 - par2) * this.pi * 0.01F) * 0.1F, (float)var3 * 0.01F);
   }

   @Overwrite
   protected void renderRainSnow(float par1) {
      if (this.mc.renderViewEntity.ticksExisted >= 1) {
         float var2 = this.mc.theWorld.getRainStrength(par1);
         if (var2 > 0.0F) {
            boolean is_blood_moon = this.mc.theWorld.isBloodMoon24HourPeriod();
            this.enableLightmap((double)par1);
            if (this.rainXCoords == null) {
               this.rainXCoords = new float[1024];
               this.rainYCoords = new float[1024];

               for(int var3 = 0; var3 < 32; ++var3) {
                  for(int var4 = 0; var4 < 32; ++var4) {
                     float var5 = (float)(var4 - 16);
                     float var6 = (float)(var3 - 16);
                     float var7 = MathHelper.sqrt_float(var5 * var5 + var6 * var6);
                     this.rainXCoords[var3 << 5 | var4] = -var6 / var7;
                     this.rainYCoords[var3 << 5 | var4] = var5 / var7;
                  }
               }
            }

            EntityLivingBase var41 = this.mc.renderViewEntity;
            WorldClient var42 = this.mc.theWorld;
            int var43 = MathHelper.floor_double(var41.posX);
            int var44 = MathHelper.floor_double(var41.posY);
            int var45 = MathHelper.floor_double(var41.posZ);
            Tessellator var8 = Tessellator.instance;
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
            if (this.mc.gameSettings.isFancyGraphicsEnabled()) {
               var16 = 10;
            }

            boolean var17 = false;
            byte var18 = -1;
            float var19 = (float)this.rendererUpdateCount + par1;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            var17 = false;
            int type = this.mc.theWorld.getPrecipitationType(this.last_precipitation_type);
            this.last_precipitation_type = type;

            for(int var20 = var45 - var16; var20 <= var45 + var16; ++var20) {
               for(int var21 = var43 - var16; var21 <= var43 + var16; ++var21) {
                  if ((var21 != var43 || var20 != var45) && var42.chunkExistsAndIsNotEmptyFromBlockCoordsC(var21, var20)) {
                     int dx = var21 - var43;
                     int dz = var20 - var45;
                     int index_wrapped = MathHelper.getWrappedIndex(var20 * 32 + var21, 32768);
                     int var22 = (var20 - var45 + 16) * 32 + var21 - var43 + 16;
                     float var23 = this.rainXCoords[var22] * 0.5F;
                     float var24 = this.rainYCoords[var22] * 0.5F;
                     BiomeGenBase var25 = var42.getBiomeGenForCoords(var21, var20);
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
                           this.random.setSeed((long)(var21 * var21 * 3121 + var21 * 45238971 ^ var20 * var20 * 418711 + var20 * 13761));
                           float var31 = var25.getFloatTemperature();
                           double var35;
                           float var32;
                           float var34;
                           if (var42.getWorldChunkManager().getTemperatureAtHeight(var31, var26) >= (this.mc.thePlayer.getWorld().getWorldSeason() == 3 ? 1.0F : 0.15F)) {
                              if (var18 != 0) {
                                 if (var18 >= 0) {
                                    var8.draw();
                                 }

                                 var18 = 0;
                                 this.mc.getTextureManager().bindTexture(locationRainPng);
                                 var8.startDrawingQuads();
                              }

                              var32 = ((float)(this.rendererUpdateCount + var21 * var21 * 3121 + var21 * 45238971 + var20 * var20 * 418711 + var20 * 13761 & 31) + par1) / 32.0F * (3.0F + this.random.nextFloat());
                              double var33 = (double)((float)var21 + 0.5F) - var41.posX;
                              var35 = (double)((float)var20 + 0.5F) - var41.posZ;
                              float var37 = MathHelper.sqrt_double(var33 * var33 + var35 * var35) / (float)var16;
                              var34 = 1.0F;
                              var8.setBrightness(var42.getLightBrightnessForSkyBlocks(var21, var30, var20, 0));
                              var8.setColorRGBA_F(var34, var34, var34, ((1.0F - var37 * var37) * 0.5F + 0.5F) * var2);
                              var8.setTranslation(-var9 * 1.0, -var11 * 1.0, -var13 * 1.0);
                              if (RenderingScheme.current == 0) {
                                 var8.addVertexWithUV((double)((float)var21 - var23) + 0.5, (double)var27, (double)((float)var20 - var24) + 0.5, (double)(0.0F * var29), (double)((float)var27 * var29 / 4.0F + var32 * var29));
                                 var8.addVertexWithUV((double)((float)var21 + var23) + 0.5, (double)var27, (double)((float)var20 + var24) + 0.5, (double)(1.0F * var29), (double)((float)var27 * var29 / 4.0F + var32 * var29));
                                 var8.addVertexWithUV((double)((float)var21 + var23) + 0.5, (double)var28, (double)((float)var20 + var24) + 0.5, (double)(1.0F * var29), (double)((float)var28 * var29 / 4.0F + var32 * var29));
                                 var8.addVertexWithUV((double)((float)var21 - var23) + 0.5, (double)var28, (double)((float)var20 - var24) + 0.5, (double)(0.0F * var29), (double)((float)var28 * var29 / 4.0F + var32 * var29));
                              } else {
                                 this.mouseFilterDummy2[0] = (double)((float)var21 - var23 + 0.5F);
                                 this.mouseFilterDummy3[0] = var27_adjusted;
                                 this.z[0] = (double)((float)var20 - var24 + 0.5F);
                                 this.mouseFilterXAxis[0] = 0.0;
                                 this.v[0] = var27_adjusted * (double)var29 / 4.0 + (double)(var32 * var29);
                                 this.mouseFilterDummy2[1] = (double)((float)var21 + var23 + 0.5F);
                                 this.mouseFilterDummy3[1] = var27_adjusted;
                                 this.z[1] = (double)((float)var20 + var24 + 0.5F);
                                 this.mouseFilterXAxis[1] = (double)var29;
                                 this.v[1] = this.v[0];
                                 this.mouseFilterDummy2[2] = this.mouseFilterDummy2[1];
                                 this.mouseFilterDummy3[2] = (double)var28;
                                 this.z[2] = this.z[1];
                                 this.mouseFilterXAxis[2] = (double)var29;
                                 this.v[2] = (double)((float)var28 * var29 / 4.0F + var32 * var29);
                                 this.mouseFilterDummy2[3] = this.mouseFilterDummy2[0];
                                 this.mouseFilterDummy3[3] = (double)var28;
                                 this.z[3] = this.z[0];
                                 this.mouseFilterXAxis[3] = 0.0;
                                 this.v[3] = this.v[2];
                                 var8.add4VerticesWithUV(this.mouseFilterDummy2, this.mouseFilterDummy3, this.z, this.mouseFilterXAxis, this.v);
                              }

                              var8.setTranslation(0.0, 0.0, 0.0);
                           } else {
                              if (var18 != 1) {
                                 if (var18 >= 0) {
                                    var8.draw();
                                 }

                                 var18 = 1;
                                 this.mc.getTextureManager().bindTexture(locationSnowPng);
                                 var8.startDrawingQuads();
                              }

                              var32 = ((float)(this.rendererUpdateCount & 511) + par1) / 512.0F;
                              float horizontal_offset;
                              float vertical_offset;
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
                                 horizontal_offset = this.random.nextFloat() + var19 * 0.01F * (float)this.random.nextGaussian();
                                 vertical_offset = this.random.nextFloat() + var19 * (float)this.random.nextGaussian() * 0.001F;
                              }

                              var34 = vertical_offset + RNG.float_1[index_wrapped];
                              var35 = (double)((float)var21 + 0.5F) - var41.posX;
                              double var47 = (double)((float)var20 + 0.5F) - var41.posZ;
                              float var39 = MathHelper.sqrt_double(var35 * var35 + var47 * var47) / (float)var16;
                              float var40 = 1.0F;
                              var8.setBrightness((var42.getLightBrightnessForSkyBlocks(var21, var30, var20, 0) * 3 + 15728880) / 4);
                              var8.setColorRGBA_F(var40, var40, var40, MathHelper.clamp_float(0.85F - var39 * 0.35F, 0.0F, 1.0F) * var2);
                              var8.setTranslation(-var9 * 1.0, -var11 * 1.0, -var13 * 1.0);
                              if (RenderingScheme.current == 0) {
                                 var8.addVertexWithUV((double)((float)var21 - var23) + 0.5, (double)var27, (double)((float)var20 - var24) + 0.5, (double)(0.0F * var29 + horizontal_offset), (double)((float)var27 * var29 / 4.0F + var32 * var29 + var34));
                                 var8.addVertexWithUV((double)((float)var21 + var23) + 0.5, (double)var27, (double)((float)var20 + var24) + 0.5, (double)(1.0F * var29 + horizontal_offset), (double)((float)var27 * var29 / 4.0F + var32 * var29 + var34));
                                 var8.addVertexWithUV((double)((float)var21 + var23) + 0.5, (double)var28, (double)((float)var20 + var24) + 0.5, (double)(1.0F * var29 + horizontal_offset), (double)((float)var28 * var29 / 4.0F + var32 * var29 + var34));
                                 var8.addVertexWithUV((double)((float)var21 - var23) + 0.5, (double)var28, (double)((float)var20 - var24) + 0.5, (double)(0.0F * var29 + horizontal_offset), (double)((float)var28 * var29 / 4.0F + var32 * var29 + var34));
                              } else {
                                 this.mouseFilterDummy2[0] = (double)((float)var21 - var23 + 0.5F);
                                 this.mouseFilterDummy3[0] = var27_adjusted;
                                 this.z[0] = (double)((float)var20 - var24 + 0.5F);
                                 this.mouseFilterXAxis[0] = (double)horizontal_offset;
                                 this.v[0] = var27_adjusted * (double)var29 / 4.0 + (double)(var32 * var29) + (double)var34;
                                 this.mouseFilterDummy2[1] = (double)((float)var21 + var23 + 0.5F);
                                 this.mouseFilterDummy3[1] = var27_adjusted;
                                 this.z[1] = (double)((float)var20 + var24 + 0.5F);
                                 this.mouseFilterXAxis[1] = (double)(var29 + horizontal_offset);
                                 this.v[1] = this.v[0];
                                 this.mouseFilterDummy2[2] = this.mouseFilterDummy2[1];
                                 this.mouseFilterDummy3[2] = (double)var28;
                                 this.z[2] = this.z[1];
                                 this.mouseFilterXAxis[2] = this.mouseFilterXAxis[1];
                                 this.v[2] = (double)((float)var28 * var29 / 4.0F + var32 * var29 + var34);
                                 this.mouseFilterDummy2[3] = this.mouseFilterDummy2[0];
                                 this.mouseFilterDummy3[3] = (double)var28;
                                 this.z[3] = this.z[0];
                                 this.mouseFilterXAxis[3] = (double)horizontal_offset;
                                 this.v[3] = this.v[2];
                                 if (dx * dx + dz * dz <= 9) {
                                    float yaw = (float)MathHelper.getYawInDegrees((double)var21 + 0.5, (double)var20 + 0.5, var9, var13);
                                    Vec3 right_side = MathHelper.getNormalizedVector(yaw + 90.0F, 0.0F, var42.getWorldVec3Pool());
                                    this.mouseFilterDummy2[0] = (double)((float)var21 + 0.5F) + right_side.xCoord / 2.0;
                                    this.mouseFilterDummy2[1] = (double)((float)var21 + 0.5F) - right_side.xCoord / 2.0;
                                    this.mouseFilterDummy2[2] = this.mouseFilterDummy2[1];
                                    this.mouseFilterDummy2[3] = this.mouseFilterDummy2[0];
                                    this.z[0] = (double)((float)var20 + 0.5F) + right_side.zCoord / 2.0;
                                    this.z[1] = (double)((float)var20 + 0.5F) - right_side.zCoord / 2.0;
                                    this.z[2] = this.z[1];
                                    this.z[3] = this.z[0];
                                 }

                                 var8.add4VerticesWithUV(this.mouseFilterDummy2, this.mouseFilterDummy3, this.z, this.mouseFilterXAxis, this.v);
                              }

                              var8.setTranslation(0.0, 0.0, 0.0);
                           }
                        }
                     }
                  }
               }
            }

            if (var18 >= 0) {
               var8.draw();
            }

            GL11.glEnable(2884);
            GL11.glDisable(3042);
            GL11.glAlphaFunc(516, 0.1F);
            this.disableLightmap((double)par1);
         }
      }

   }

   private void g() {
      float var1 = this.mc.theWorld.getRainStrength(1.0F);
      boolean is_blood_moon = this.mc.theWorld.isBloodMoon24HourPeriod();
      if (!this.mc.gameSettings.isFancyGraphicsEnabled()) {
         var1 /= 2.0F;
      }

      if (var1 != 0.0F) {
         this.random.setSeed((long)this.rendererUpdateCount * 312987231L);
         EntityLivingBase var2 = this.mc.renderViewEntity;
         WorldClient var3 = this.mc.theWorld;
         int var4 = MathHelper.floor_double(var2.posX);
         int var5 = MathHelper.floor_double(var2.posY);
         int var6 = MathHelper.floor_double(var2.posZ);
         byte var7 = 10;
         double var8 = 0.0;
         double var10 = 0.0;
         double var12 = 0.0;
         int var14 = 0;
         int var15 = (int)(100.0F * var1 * var1);
         if (this.mc.gameSettings.particleSetting == 1) {
            var15 >>= 1;
         } else if (this.mc.gameSettings.particleSetting == 2) {
            var15 = 0;
         }

         int index = Minecraft.getThreadIndex();

         for(int var16 = 0; var16 < var15; ++var16) {
            int var17 = var4 + this.random.nextInt(var7) - this.random.nextInt(var7);
            int var18 = var6 + this.random.nextInt(var7) - this.random.nextInt(var7);
            int var19 = var3.getPrecipitationHeight(var17, var18);
            int var20 = var3.getBlockId(var17, var19 - 1, var18);
            BiomeGenBase var21 = var3.getBiomeGenForCoords(var17, var18);
            if (var19 <= var5 + var7 && var19 >= var5 - var7 && var21.canSpawnLightningBolt(is_blood_moon) && var21.getFloatTemperature() >= (this.mc.thePlayer.getWorld().getWorldSeason() == 3 ? 1.0F : 0.2F) && var20 > 0) {
               float var22 = this.random.nextFloat();
               float var23 = this.random.nextFloat();
               Block block = Block.getBlock(var20);
               double pos_y;
               if (block.blockMaterial == Material.lava) {
                  pos_y = (double)((float)var19 - BlockFluid.getFluidHeightPercent(this.mc.theWorld.getBlockMetadata(var17, var19 - 1, var18)) + 0.1F + 0.125F);
                  this.mc.effectRenderer.addEffect(new EntitySmokeFX(var3, (double)((float)var17 + var22), pos_y, (double)((float)var18 + var23), 0.0, 0.0, 0.0));
               } else {
                  if (block.blockMaterial.isLiquid()) {
                     pos_y = (double)((float)var19 - BlockFluid.getFluidHeightPercent(this.mc.theWorld.getBlockMetadata(var17, var19 - 1, var18)) + 0.1F + 0.125F);
                  } else if (block.isAlwaysStandardFormCube()) {
                     pos_y = (double)((float)var19 + 0.1F);
                  } else {
                     if (block instanceof BlockTrapDoor && BlockTrapDoor.isTrapdoorOpen(this.mc.theWorld.getBlockMetadata(var17, var19 - 1, var18))) {
                        continue;
                     }

                     block.setBlockBoundsBasedOnStateAndNeighbors(this.mc.theWorld, var17, var19 - 1, var18);
                     pos_y = (double)(var19 - 1) + block.getBlockBoundsMaxY(index) + 0.10000000149011612;
                  }

                  ++var14;
                  if (this.random.nextInt(var14) == 0) {
                     var8 = (double)((float)var17 + var22);
                     var10 = (double)((float)var19 + 0.1F) - Block.blocksList[var20].getBlockBoundsMinY(index);
                     var12 = (double)((float)var18 + var23);
                  }

                  this.mc.effectRenderer.addEffect(new EntityRainFX(var3, (double)((float)var17 + var22), pos_y, (double)((float)var18 + var23)));
               }
            }
         }

         boolean player_is_outdoors = this.mc.thePlayer.isOutdoors();
         float sleep_factor = 1.0F - (float)this.mc.thePlayer.falling_asleep_counter / 50.0F;
         float distance_from_rain_factor = (float)Math.pow((double)this.mc.raining_strength_for_render_view_entity, 4.0);
         if (sleep_factor < 0.0F) {
            sleep_factor = 0.0F;
         }

         if (var14 > 0 && this.random.nextInt(3) < this.rainSoundCounter++) {
            this.rainSoundCounter = 0;
            if (var10 > var2.posY + 1.0 && var3.getPrecipitationHeight(MathHelper.floor_double(var2.posX), MathHelper.floor_double(var2.posZ)) > MathHelper.floor_double(var2.posY)) {
               if (player_is_outdoors) {
                  this.mc.theWorld.playSound(var8, var10, var12, "ambient.weather.rain", 0.1F * sleep_factor * distance_from_rain_factor, 0.5F, false);
               } else {
                  this.mc.theWorld.playSound(var8, var10, var12, "ambient.weather.rain", 0.025F * sleep_factor * distance_from_rain_factor, 0.125F, false);
               }
            } else if (player_is_outdoors) {
               this.mc.theWorld.playSound(var8, var10, var12, "ambient.weather.rain", 0.2F * sleep_factor * distance_from_rain_factor, 1.0F, false);
            } else {
               this.mc.theWorld.playSound(var8, var10, var12, "ambient.weather.rain", 0.05F * sleep_factor * distance_from_rain_factor, 0.25F, false);
            }
         }
      }

   }

   private void f(float par1) {
      if (this.mc.renderViewEntity instanceof EntityPlayer) {
         EntityPlayer var2;
         float var3;
         float strafe;
         float forward;
         float var4;
         if ((Boolean)ExperimentalConfig.TagConfig.TagMovingV2.ConfigValue) {
            var2 = (EntityPlayer)this.mc.renderViewEntity;
            var3 = var2.distanceWalkedModified - var2.prevDistanceWalkedModified;
            strafe = var2.moveStrafing > 0.0F ? Math.min(0.8F, var2.moveStrafing) * var3 / 5.0F : Math.max(-0.8F, var2.moveStrafing) * var3 / 5.0F;
            forward = var2.moveForward > 0.0F ? Math.min(0.8F, var2.moveForward) * var3 / 5.0F : Math.max(-0.8F, var2.moveForward) * var3 / 5.0F;
            var4 = -(var2.distanceWalkedModified + var3 * par1);
            float var5 = var2.prevCameraYaw + (var2.cameraYaw - var2.prevCameraYaw) * par1;
            float var6 = var2.prevCameraPitch + (var2.cameraPitch - var2.prevCameraPitch) * par1;
            GL11.glTranslatef(MathHelper.sin(var4 * 3.1415927F) * var5 * 2.0F * strafe, MathHelper.cos(var4 * 3.1415927F * 2.0F) * var5 * 1.0F * forward, 0.0F);
            GL11.glTranslatef(strafe, -forward, 0.0F);
            GL11.glRotatef(MathHelper.sin(var4 * 3.1415927F) * var5 * 3.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(Math.abs(MathHelper.cos(var4 * 3.1415927F - 0.2F) * var5) * 5.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(var6, 1.0F, 0.0F, 0.0F);
         } else {
            var2 = (EntityPlayer)this.mc.renderViewEntity;
            var3 = var2.distanceWalkedModified - var2.prevDistanceWalkedModified;
            strafe = -(var2.distanceWalkedModified + var3 * par1);
            forward = var2.prevCameraYaw + (var2.cameraYaw - var2.prevCameraYaw) * par1;
            var4 = var2.prevCameraPitch + (var2.cameraPitch - var2.prevCameraPitch) * par1;
            GL11.glTranslatef(MathHelper.sin(strafe * 3.1415927F) * forward * 0.5F, -Math.abs(MathHelper.cos(strafe * 3.1415927F) * forward), 0.0F);
            GL11.glRotatef(MathHelper.sin(strafe * 3.1415927F) * forward * 3.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(Math.abs(MathHelper.cos(strafe * 3.1415927F - 0.2F) * forward) * 5.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(var4, 1.0F, 0.0F, 0.0F);
         }
      }

   }

   @Shadow
   public void enableLightmap(double par1) {
   }

   @Shadow
   public void disableLightmap(double par1) {
   }
}
