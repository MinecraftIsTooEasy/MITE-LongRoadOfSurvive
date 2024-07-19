package net.oilcake.mitelros.mixins.render;

import java.util.Random;
import net.minecraft.AttributeInstance;
import net.minecraft.DamageSource;
import net.minecraft.Entity;
import net.minecraft.EntityBoat;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;
import net.minecraft.FoodStats;
import net.minecraft.Gui;
import net.minecraft.GuiIngame;
import net.minecraft.Item;
import net.minecraft.Material;
import net.minecraft.MathHelper;
import net.minecraft.Minecraft;
import net.minecraft.Potion;
import net.minecraft.ResourceLocation;
import net.minecraft.ScaledResolution;
import net.minecraft.SharedMonsterAttributes;
import net.minecraft.Tessellator;
import net.minecraft.Translator;
import net.minecraft.WeatherEvent;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin({GuiIngame.class})
public class GuiIngameMixin extends Gui {
   @Shadow
   @Final
   private static ResourceLocation vignetteTexPath;
   @Shadow
   public float prevVignetteBrightness = 1.0F;
   @Shadow
   @Final
   private Minecraft mc;
   @Shadow
   @Final
   private Random rand;
   @Shadow
   private int updateCounter;

   @Overwrite
   private void func_110327_a(int par1, int par2) {
      boolean var3 = this.mc.thePlayer.hurtResistantTime / 3 % 2 == 1;
      if (this.mc.thePlayer.hurtResistantTime < 10) {
         var3 = false;
      }

      int var4 = MathHelper.ceiling_float_int(this.mc.thePlayer.getHealth());
      int var5 = MathHelper.ceiling_float_int(this.mc.thePlayer.prevHealth);
      this.rand.setSeed((long)this.updateCounter * 312871L);
      FoodStats foodStats = this.mc.thePlayer.getFoodStats();
      int var8 = foodStats.getNutrition();
      AttributeInstance var10 = this.mc.thePlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth);
      int var11 = par1 / 2 - 91;
      int var12 = par1 / 2 + 91;
      int var13 = par2 - 39;
      float var14 = (float)var10.getAttributeValue();
      float var15 = this.mc.thePlayer.getAbsorptionAmount();
      int var16 = MathHelper.ceiling_float_int((var14 + var15) / 2.0F / 10.0F);
      int var17 = Math.max(10 - (var16 - 2), 3);
      int var18 = var13 - (var16 - 1) * var17 - 10;
      float var19 = var15;
      float total_protection = this.mc.thePlayer.getTotalProtection((DamageSource)null);
      int var20 = MathHelper.ceiling_float_int(total_protection);
      int var21 = -1;
      if (this.mc.thePlayer.isPotionActive(Potion.regeneration)) {
         var21 = this.updateCounter % MathHelper.ceiling_float_int(var14 + 5.0F);
      }

      this.mc.mcProfiler.startSection("armor");

      int var23;
      int var22;
      for(var22 = 0; var22 < 10; ++var22) {
         if (total_protection > 0.0F || this.mc.thePlayer.isWearingArmor()) {
            var23 = var11 + var22 * 8;
            if (var22 * 2 + 1 < var20) {
               this.drawTexturedModalRect(var23, var18, 34, 9, 9, 9);
            }

            if (var22 * 2 + 1 == var20) {
               this.drawTexturedModalRect(var23, var18, 25, 9, 9, 9);
            }

            if (var22 * 2 + 1 > var20) {
               this.drawTexturedModalRect(var23, var18, 16, 9, 9, 9);
            }
         }
      }

      this.mc.mcProfiler.endStartSection("health");

      int var25;
      int var27;
      int var26;
      int var28;
      for(var22 = MathHelper.ceiling_float_int((var14 + var15) / 2.0F) - 1; var22 >= 0; --var22) {
         var23 = 16;
         if (this.mc.thePlayer.isPotionActive(Potion.poison)) {
            var23 += 36;
         } else if (this.mc.thePlayer.isPotionActive(Potion.wither)) {
            var23 += 72;
         }

         byte var24 = 0;
         if (var3) {
            var24 = 1;
         }

         var25 = MathHelper.ceiling_float_int((float)(var22 + 1) / 10.0F) - 1;
         var26 = var11 + var22 % 10 * 8;
         var27 = var13 - var25 * var17;
         if (var4 <= 4) {
            var27 += this.rand.nextInt(2);
         }

         if (var22 == var21) {
            var27 -= 2;
         }

         var28 = 0;
         if (this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled()) {
            var28 = 5;
         }

         if ((float)var22 < this.mc.thePlayer.getMaxHealth() / 2.0F) {
            this.drawTexturedModalRect(var26, var27, 16 + var24 * 9, 9 * var28, 9, 9);
         }

         if (var3) {
            if (var22 * 2 + 1 < var5) {
               this.drawTexturedModalRect(var26, var27, var23 + 54, 9 * var28, 9, 9);
            }

            if (var22 * 2 + 1 == var5) {
               this.drawTexturedModalRect(var26, var27, var23 + 63, 9 * var28, 9, 9);
            }
         }

         if (var19 > 0.0F) {
            if (var19 == var15 && var15 % 2.0F == 1.0F) {
               this.drawTexturedModalRect(var26, var27, var23 + 153, 9 * var28, 9, 9);
            } else {
               this.drawTexturedModalRect(var26, var27, var23 + 144, 9 * var28, 9, 9);
            }

            var19 -= 2.0F;
         } else {
            if (var22 * 2 + 1 < var4) {
               this.drawTexturedModalRect(var26, var27, var23 + 36, 9 * var28, 9, 9);
            }

            if (var22 * 2 + 1 == var4) {
               this.drawTexturedModalRect(var26, var27, var23 + 45, 9 * var28, 9, 9);
            }
         }
      }

      Entity var34 = this.mc.thePlayer.ridingEntity;
      int water;
      if (var34 != null && !(var34 instanceof EntityBoat)) {
         if (var34 instanceof EntityLivingBase) {
            this.mc.mcProfiler.endStartSection("mountHealth");
            EntityLivingBase var38 = (EntityLivingBase)var34;
            var28 = (int)Math.ceil((double)var38.getHealth());
            float var37 = var38.getMaxHealth();
            var26 = (int)(var37 + 0.5F) / 2;
            if (var26 > 30) {
               var26 = 30;
            }

            var27 = var13;

            for(int var39 = 0; var26 > 0; var39 += 20) {
               int var29 = Math.min(var26, 10);
               var26 -= var29;

               for(int var30 = 0; var30 < var29; ++var30) {
                  byte var31 = 52;
                  byte var32 = 0;
                  int var33 = var12 - var30 * 8 - 9;
                  this.drawTexturedModalRect(var33, var27, var31 + var32 * 9, 9, 9, 9);
                  if (var30 * 2 + 1 + var39 < var28) {
                     this.drawTexturedModalRect(var33, var27, var31 + 36, 9, 9, 9);
                  }

                  if (var30 * 2 + 1 + var39 == var28) {
                     this.drawTexturedModalRect(var33, var27, var31 + 45, 9, 9, 9);
                  }
               }

               var27 -= 10;
            }
         }
      } else {
         this.mc.mcProfiler.endStartSection("food");

         for(var23 = 0; var23 < 10; ++var23) {
            var28 = var13;
            var25 = 16;
            water = 0;
            if (this.mc.thePlayer.isPotionActive(Potion.hunger) || this.mc.thePlayer.isPotionActive(PotionExtend.dehydration)) {
               var25 += 36;
               water = 13;
            }

            if (this.mc.thePlayer.isHungry() && this.updateCounter % (var8 * 3 + 1) == 0) {
               var28 = var13 + (this.rand.nextInt(3) - 1);
            }

            var27 = var12 - var23 * 8 - 9;
            if (var23 < this.mc.thePlayer.getFoodStats().getNutritionLimit() / 2) {
               this.drawTexturedModalRect(var27, var28, 16 + water * 9, 27, 9, 9);
            }

            if (var23 * 2 + 1 < var8) {
               this.drawTexturedModalRect(var27, var28, var25 + 36, 27, 9, 9);
            }

            if (var23 * 2 + 1 == var8) {
               this.drawTexturedModalRect(var27, var28, var25 + 45, 27, 9, 9);
            }
         }
      }

      this.mc.getTextureManager().bindTexture(Constant.icons_itf);
      this.mc.mcProfiler.endStartSection("water");
      water = foodStats.getWater();

      for(int temp = 0; temp < 10; ++temp) {
         var28 = var13 - 9;
         int var25 = 16;
         int var36 = 0;
         var27 = var12 - temp * 8 - 9;
         if (temp < this.mc.thePlayer.getFoodStats().getWaterLimit() / 2) {
            this.drawTexturedModalRect(var27, var28, 16 + var36 * 9, 54, 9, 9);
         }

         if (temp * 2 + 1 < water) {
            this.drawTexturedModalRect(var27, var28, var25 + 9, 54, 9, 9);
         }

         if (temp * 2 + 1 == water) {
            this.drawTexturedModalRect(var27, var28, var25 + 18, 54, 9, 9);
         }
      }

      this.mc.mcProfiler.endStartSection("air");
      if (this.mc.thePlayer.isInsideOfMaterial(Material.water)) {
         var23 = this.mc.thePlayer.getAir();
         var28 = MathHelper.ceiling_double_int((double)(var23 - 2) * 10.0 / 300.0);
         var25 = MathHelper.ceiling_double_int((double)var23 * 10.0 / 300.0) - var28;

         for(var26 = 0; var26 < var28 + var25; ++var26) {
            if (var26 < var28) {
               this.drawTexturedModalRect(var12 - var26 * 8 - 9, var18 - 9, 16, 18, 9, 9);
            } else {
               this.drawTexturedModalRect(var12 - var26 * 8 - 9, var18 - 9, 25, 18, 9, 9);
            }
         }
      }

      this.mc.mcProfiler.endSection();
   }

   @Inject(
      method = {"renderGameOverlay(FZII)V"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/Minecraft;inDevMode()Z",
   shift = Shift.BEFORE
)}
   )
   private void injectRenderPos(float par1, boolean par2, int par3, int par4, CallbackInfo ci) {
      if (!Minecraft.inDevMode() && (!this.mc.gameSettings.showDebugInfo || this.mc.gameSettings.gui_mode != 0) && Minecraft.getErrorMessage() == null) {
         String pos = Translator.getFormatted("gui.gametitle.pos", new Object[0]) + "(" + MathHelper.floor_double(this.mc.thePlayer.posX) + ", " + MathHelper.floor_double(this.mc.thePlayer.posZ) + ") ";
         String time = Translator.getFormatted("gui.gametitle.time", new Object[0]) + "(" + this.mc.thePlayer.getWorld().getHourOfDay() + ":" + this.mc.thePlayer.getWorld().getTotalWorldTime() % 1000L * 60L / 1000L + ") ";
         EntityPlayer player = this.mc.thePlayer.getAsPlayer();
         String s;
         switch (this.mc.thePlayer.getDirectionFromYaw().toString()) {
            case "EAST":
               s = Translator.getFormatted("gui.gametitle.direction.east", new Object[0]);
               break;
            case "WEST":
               s = Translator.getFormatted("gui.gametitle.direction.west", new Object[0]);
               break;
            case "NORTH":
               s = Translator.getFormatted("gui.gametitle.direction.north", new Object[0]);
               break;
            case "SOUTH":
               s = Translator.getFormatted("gui.gametitle.direction.south", new Object[0]);
               break;
            default:
               s = "null";
         }

         String moon_phase = Translator.getFormatted("gui.gametitle.moonphase." + (int)((this.mc.thePlayer.worldObj.getTotalWorldTime() + 24000L) / 24000L) % 8, new Object[0]);
         String Biome = StringUtils.substringBefore(this.mc.thePlayer.getBiome().toString(), "@").substring(19) + " ";
         if (!this.mc.thePlayer.getBiome().isFreezing() && this.mc.thePlayer.worldObj.getWorldSeason() != 3) {
            RainSnow = Translator.getFormatted("gui.gametitle.rain", new Object[0]);
         } else {
            RainSnow = Translator.getFormatted("gui.gametitle.snow", new Object[0]);
         }

         WeatherEvent event = this.mc.theWorld.getCurrentWeatherEvent();
         Random R = new Random((long)this.mc.theWorld.getDayOfWorld());
         String Weather;
         int ran2;
         if (event != null) {
            if (event.hasStorm() && this.mc.thePlayer.worldObj.getTotalWorldTime() < event.end_of_storm && this.mc.thePlayer.getWorld().getTotalWorldTime() > event.start_of_storm) {
               if (!this.mc.thePlayer.getBiome().isFreezing() && this.mc.thePlayer.worldObj.getWorldSeason() != 3) {
                  Weather = Translator.getFormatted("gui.gametitle.thundershower", new Object[0]);
               } else {
                  Weather = Translator.getFormatted("gui.gametitle.thundersnow", new Object[0]);
               }
            } else {
               ran2 = R.nextInt(3);
               if (ran2 == 0) {
                  Weather = Translator.getFormatted("gui.gametitle.light", new Object[0]) + RainSnow;
               } else if (ran2 == 1) {
                  Weather = Translator.getFormatted("gui.gametitle.moderate", new Object[0]) + RainSnow;
               } else {
                  Weather = Translator.getFormatted("gui.gametitle.heavy", new Object[0]) + RainSnow;
               }
            }
         } else {
            event = this.mc.theWorld.getNextWeatherEvent(false);
            if (event != null) {
               if (event.start - this.mc.thePlayer.worldObj.getTotalWorldTime() < 2400L) {
                  Weather = Translator.getFormatted("gui.gametitle.aboutto", new Object[0]) + RainSnow;
               } else {
                  Weather = Translator.getFormatted("gui.gametitle.overcast", new Object[0]);
               }
            } else {
               ran2 = R.nextInt(3);
               if (this.mc.thePlayer.worldObj.getHourOfDay() <= 17 && this.mc.thePlayer.worldObj.getHourOfDay() >= 5) {
                  if (ran2 == 0) {
                     Weather = Translator.getFormatted("gui.gametitle.sunny", new Object[0]);
                  } else if (ran2 == 1) {
                     Weather = Translator.getFormatted("gui.gametitle.cloudy", new Object[0]);
                  } else {
                     Weather = Translator.getFormatted("gui.gametitle.clearinglate", new Object[0]);
                  }
               } else {
                  Weather = moon_phase;
               }
            }
         }

         (new StringBuilder()).append(" ").append(s).append(" ").append(Biome).append(" ").append(Weather).toString();
         if (GuiIngame.server_load >= 0) {
            ScaledResolution sr = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
            String text = GuiIngame.server_load + "%";
            this.drawString(this.mc.fontRenderer, text, sr.getScaledWidth() - this.mc.fontRenderer.getStringWidth(text) - 2, 2, 14737632);
         }

         String t = " §c" + Translator.getFormatted("gui.gametitle.diffculty", new Object[0]) + ": " + Constant.CalculateCurrentDiff() + "§r ";
         StringBuilder var68 = (new StringBuilder()).append("MITE-ITF ");
         if ((Boolean)ExperimentalConfig.TagConfig.FinalChallenge.ConfigValue && Constant.CalculateCurrentDiff() == 25) {
            t = " §4" + Translator.getFormatted("gui.gametitle.doomsday", new Object[0]) + "§r ";
         }

         if (Constant.CalculateCurrentDiff() < 0) {
            t = " §a" + Translator.getFormatted("gui.gametitle.casual", new Object[0]) + "§r ";
         }

         if (player.getHeldItemStack() != null && player.getHeldItemStack().getItem() == Item.compass) {
            var68.append(pos);
         }

         if (player.getHeldItemStack() != null && player.getHeldItemStack().getItem() == Item.pocketSundial) {
            var68.append(time);
         }

         if (Constant.CalculateCurrentDiff() != 0) {
            var68.append(t);
         }

         var68.append(Weather);
         var68.append("   FPS=").append(Minecraft.last_fps).append(" (");
         this.drawString(this.mc.fontRenderer, var68.append(Minecraft.last_fp10s).append(")").toString(), 2, 2, 14737632);
      }

   }

   @Inject(
      locals = LocalCapture.CAPTURE_FAILHARD,
      method = {"func_110327_a(II)V"},
      at = {@At(
   value = "INVOKE_STRING",
   target = "endStartSection(Ljava/lang/String;)V",
   args = {"ldc=air"},
   shift = Shift.BEFORE
)}
   )
   private void injectRenderNutrition(int par1, int par2, CallbackInfo ci, boolean var3, int var4, int var5, FoodStats var7, int var8, AttributeInstance var10, int var11, int var12, int var13, float var14, float var15) {
      int protein = Math.max(this.mc.thePlayer.getProtein() - 800000, 0);
      int phytonutrients = Math.max(this.mc.thePlayer.getPhytonutrients() - 800000, 0);
      int var26 = var12 - 90;
      int var25 = var13 + 32;
      if (this.getNutrientsPriority(protein, phytonutrients)) {
         GL11.glPushMatrix();
         GL11.glScalef(0.6F, 1.0F, 1.0F);
         this.mc.getTextureManager().bindTexture(Constant.icons_itf);
         this.drawTexturedModalRect(var26 - 205, var25, 0, 106, 182, 6);
         this.drawTexturedModalRect(var26 - 205, var25, 0, 100, (int)(182.0F * this.getRateNutrient((long)protein)), 6);
         GL11.glPopMatrix();
         GL11.glPushMatrix();
         GL11.glScalef(0.6F, 1.0F, 1.0F);
         this.mc.getTextureManager().bindTexture(Constant.icons_itf);
         this.drawTexturedModalRect(var26 - 205, var25, 0, 94, (int)(182.0F * this.getRateNutrient((long)phytonutrients)), 6);
         GL11.glPopMatrix();
      } else {
         GL11.glPushMatrix();
         GL11.glScalef(0.6F, 1.0F, 1.0F);
         this.mc.getTextureManager().bindTexture(Constant.icons_itf);
         this.drawTexturedModalRect(var26 - 205, var25, 0, 106, 182, 6);
         this.drawTexturedModalRect(var26 - 205, var25, 0, 94, (int)(182.0F * this.getRateNutrient((long)phytonutrients)), 6);
         GL11.glPopMatrix();
         GL11.glPushMatrix();
         GL11.glScalef(0.6F, 1.0F, 1.0F);
         this.mc.getTextureManager().bindTexture(Constant.icons_itf);
         this.drawTexturedModalRect(var26 - 205, var25, 0, 100, (int)(182.0F * this.getRateNutrient((long)protein)), 6);
         GL11.glPopMatrix();
      }

   }

   @Inject(
      locals = LocalCapture.CAPTURE_FAILHARD,
      method = {"func_110327_a(II)V"},
      at = {@At(
   value = "INVOKE_STRING",
   target = "endStartSection(Ljava/lang/String;)V",
   args = {"ldc=air"},
   shift = Shift.BEFORE
)}
   )
   private void injectRenderHealProgress(int par1, int par2, CallbackInfo ci, boolean var3, int var4, int var5, FoodStats var7, int var8, AttributeInstance var10, int var11, int var12, int var13, float var14, float var15) {
      int var25 = var13 + 32;
      GL11.glPushMatrix();
      GL11.glScalef(0.6F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(Constant.icons_itf);
      this.drawTexturedModalRect((int)((double)par1 * 1.21), var25, 0, 106, 182, 6);
      this.drawTexturedModalRect((int)((double)par1 * 1.21), var25, 0, 112, (int)(182.0F * this.mc.thePlayer.getHealProgress()), 6);
      GL11.glPopMatrix();
   }

   private boolean getNutrientsPriority(int protein, int phytonutrients) {
      return protein > phytonutrients;
   }

   private float getRateNutrient(long par1) {
      par1 *= par1;
      par1 /= 160000L;
      return (float)par1 / 160000.0F;
   }

   @Redirect(
      method = {"func_110327_a(II)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/AttributeInstance;getAttributeValue()D"
)
   )
   private double redirectHealthLimit(AttributeInstance att) {
      return (double)this.mc.thePlayer.getHealthLimit();
   }

   @Inject(
      method = {"renderGameOverlay(FZII)V"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/GuiIngame;renderVignette(FII)V"
)}
   )
   private void injectNewVignette(float par1, boolean par2, int par3, int par4, CallbackInfo callbackInfo) {
      ScaledResolution var5 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
      int var6 = var5.getScaledWidth();
      int var7 = var5.getScaledHeight();
      int temperatureDelta = Math.abs(this.mc.thePlayer.getTemperaturePoint() - 96000);
      float renderStrength = Math.min(1.0F, ((float)temperatureDelta - 8000.0F) / 4000.0F);
      if (this.mc.thePlayer.getTemperaturePoint() < 92000) {
         this.renderFreezingVignette(renderStrength, var6, var7);
      } else if (this.mc.thePlayer.getTemperaturePoint() > 100000) {
         this.renderHeatingVignette(renderStrength, var6, var7);
      }

   }

   private void renderHeatingVignette(float par1, int par2, int par3) {
      this.prevVignetteBrightness = (float)((double)this.prevVignetteBrightness + (double)(par1 - this.prevVignetteBrightness) * 0.01);
      if (this.mc.theWorld.provider.drawGuiVignette()) {
         GL11.glDisable(2929);
         GL11.glDepthMask(false);
         GL11.glBlendFunc(0, 769);
         GL11.glColor4f(0.0F, par1 * 0.4F, par1, 1.0F);
         this.mc.getTextureManager().bindTexture(vignetteTexPath);
         Tessellator var4 = Tessellator.instance;
         var4.startDrawingQuads();
         var4.addVertexWithUV(0.0, (double)par3, -90.0, 0.0, 1.0);
         var4.addVertexWithUV((double)par2, (double)par3, -90.0, 1.0, 1.0);
         var4.addVertexWithUV((double)par2, 0.0, -90.0, 1.0, 0.0);
         var4.addVertexWithUV(0.0, 0.0, -90.0, 0.0, 0.0);
         var4.draw();
      } else {
         this.prevVignetteBrightness = 1.0F;
      }

      GL11.glDepthMask(true);
      GL11.glEnable(2929);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glBlendFunc(770, 771);
   }

   private void renderFreezingVignette(float par1, int par2, int par3) {
      this.prevVignetteBrightness = (float)((double)this.prevVignetteBrightness + (double)(par1 - this.prevVignetteBrightness) * 0.01);
      if (this.mc.theWorld.provider.drawGuiVignette()) {
         GL11.glDisable(2929);
         GL11.glDepthMask(false);
         GL11.glBlendFunc(0, 769);
         GL11.glColor4f(par1, par1 * 0.4F, 0.0F, 1.0F);
         this.mc.getTextureManager().bindTexture(vignetteTexPath);
         Tessellator var4 = Tessellator.instance;
         var4.startDrawingQuads();
         var4.addVertexWithUV(0.0, (double)par3, -90.0, 0.0, 1.0);
         var4.addVertexWithUV((double)par2, (double)par3, -90.0, 1.0, 1.0);
         var4.addVertexWithUV((double)par2, 0.0, -90.0, 1.0, 0.0);
         var4.addVertexWithUV(0.0, 0.0, -90.0, 0.0, 0.0);
         var4.draw();
      } else {
         this.prevVignetteBrightness = 1.0F;
      }

      GL11.glDepthMask(true);
      GL11.glEnable(2929);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glBlendFunc(770, 771);
   }
}
