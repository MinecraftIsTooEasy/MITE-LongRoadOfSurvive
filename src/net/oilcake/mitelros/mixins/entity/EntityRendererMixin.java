package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

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
    private void h(float par1) {
        bdd var2 = this.q.f;
        if (var2 != null) {
            for(int var3 = 0; var3 < 256; ++var3) {
                float skylight_brightness = (float)(var3 / 16) / 15.0F;
                float blocklight_brightness = (float)(var3 % 16) / 15.0F;
                float var4 = var2.b(1.0F) * 0.95F + 0.05F;
                float var5 = var2.provider.lightBrightnessTable[var3 / 16] * var4;
                float var6 = var2.provider.lightBrightnessTable[var3 % 16] * (this.d * 0.1F + 1.5F);
                float var7;
                if (var2.isOverworld() && var2.getMoonAscensionFactor() > 0.0F) {
                    var7 = MathHelper.clamp_float(var2.getMoonAscensionFactor(), 0.0F, 0.2F) * 5.0F;
                    var5 = var5 * (1.0F - var7) + var5 * var7 * var2.getMoonBrightness(par1, true);
                }

                if (var2.lastLightningBolt > 0) {
                    var7 = (float)Math.pow((double)this.q.raining_strength_for_render_view_entity, 4.0);
                    var5 = var2.provider.lightBrightnessTable[var3 / 16] * var7 + var5 * (1.0F - var7);
                }

                var7 = var5 * (var2.b(1.0F) * 0.65F + 0.35F);
                float var8 = var5 * (var2.b(1.0F) * 0.65F + 0.35F);
                float var11 = var6 * ((var6 * 0.6F + 0.4F) * 0.6F + 0.4F);
                float var12 = var6 * (var6 * var6 * 0.6F + 0.4F);
                float var13 = var7 + var6;
                float var14 = var8 + var11;
                float var15 = var5 + var12;
                var13 = var13 * 0.96F + 0.03F;
                var14 = var14 * 0.96F + 0.03F;
                var15 = var15 * 0.96F + 0.03F;
                float var16;
                if (this.V > 0.0F) {
                    var16 = this.W + (this.V - this.W) * par1;
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
                if (this.q.h.isPotionActive(MobEffectList.nightVision) || Minecraft.night_vision_override) {
                    var16 = Minecraft.night_vision_override ? 1.0F : this.a((EntityPlayer)this.q.h, par1);
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

                var16 = this.q.u.ak;
                var17 = 1.0F - var13;
                float var18 = 1.0F - var14;
                float var19 = 1.0F - var15;
                var17 = 1.0F - var17 * var17 * var17 * var17;
                var18 = 1.0F - var18 * var18 * var18 * var18;
                var19 = 1.0F - var19 * var19 * var19 * var19;
                var13 = var13 * (1.0F - var16) + var17 * var16;
                var14 = var14 * (1.0F - var16) + var18 * var16;
                var15 = var15 * (1.0F - var16) + var19 * var16;
                var13 = var13 * 0.96F + 0.03F;
                var14 = var14 * 0.96F + 0.03F;
                var15 = var15 * 0.96F + 0.03F;
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

                if (var3 == 0) {
                    min = 0.062F;
                    if (var13 < min) {
                        var13 = min;
                    }

                    if (var14 < min) {
                        var14 = min;
                    }

                    if (var15 < min) {
                        var15 = min;
                    }
                }

                short var20 = 255;
                int var21 = (int)(var13 * 255.0F);
                int var22 = (int)(var14 * 255.0F);
                int var23 = (int)(var15 * 255.0F);
                this.Q[var3] = var20 << 24 | var21 << 16 | var22 << 8 | var23;
            }

            this.P.a();
            this.ad = false;
        }

    }
    @Shadow
    private Minecraft q;
    @Shadow
    float d;
    @Shadow
    @Final
    private int[] Q;
    @Shadow
    private float V;
    @Shadow
    private float W;
    @Shadow
    @Final
    private bib P;
    @Shadow
    private boolean ad;
}
