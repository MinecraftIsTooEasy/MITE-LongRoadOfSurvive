package net.oilcake.mitelros.mixins.render;

import net.minecraft.*;
//import net.oilcake.mitelros.item.potion.Potions;
import net.oilcake.mitelros.util.Constant;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(GuiIngame.class)
public class GuiIngameMixin extends avk {

    private void a(int par1, int par2) {
        boolean var3 = this.g.h.hurtResistantTime / 3 % 2 == 1;
        if (this.g.h.hurtResistantTime < 10) {
            var3 = false;
        }

        int var4 = MathHelper.ceiling_float_int(this.g.h.getHealth());
        int var5 = MathHelper.ceiling_float_int(this.g.h.prevHealth);
        this.f.setSeed((long) (this.i * 312871));
        FoodMetaData foodStats = this.g.h.getFoodStats();
        int var8 = foodStats.getNutrition();
        AttributeInstance var10 = this.g.h.getEntityAttribute(GenericAttributes.maxHealth);
        int var11 = par1 / 2 - 91;
        int var12 = par1 / 2 + 91;
        int var13 = par2 - 39;
        float var14 = (float) var10.getAttributeValue();
        float var15 = this.g.h.getAbsorptionAmount();
        int var16 = MathHelper.ceiling_float_int((var14 + var15) / 2.0F / 10.0F);
        int var17 = Math.max(10 - (var16 - 2), 3);
        int var18 = var13 - (var16 - 1) * var17 - 10;
        float var19 = var15;
        float total_protection = this.g.h.getTotalProtection((DamageSource) null);
        int var20 = MathHelper.ceiling_float_int(total_protection);
        int var21 = -1;
        if (this.g.h.isPotionActive(MobEffectList.regeneration)) {
            var21 = this.i % MathHelper.ceiling_float_int(var14 + 5.0F);
        }

        this.g.C.startSection("armor");

        int var23;
        int var22;
        for (var22 = 0; var22 < 10; ++var22) {
            if (total_protection > 0.0F || this.g.h.isWearingArmor()) {
                var23 = var11 + var22 * 8;
                if (var22 * 2 + 1 < var20) {
                    this.b(var23, var18, 34, 9, 9, 9);
                }

                if (var22 * 2 + 1 == var20) {
                    this.b(var23, var18, 25, 9, 9, 9);
                }

                if (var22 * 2 + 1 > var20) {
                    this.b(var23, var18, 16, 9, 9, 9);
                }
            }
        }

        this.g.C.endStartSection("health");

        int var25;
        int var27;
        int var26;
        int var28;
        for (var22 = MathHelper.ceiling_float_int((var14 + var15) / 2.0F) - 1; var22 >= 0; --var22) {
            var23 = 16;
            if (this.g.h.isPotionActive(MobEffectList.poison)) {
                var23 += 36;
            } else if (this.g.h.isPotionActive(MobEffectList.wither)) {
                var23 += 72;
            }

            byte var24 = 0;
            if (var3) {
                var24 = 1;
            }

            var25 = MathHelper.ceiling_float_int((float) (var22 + 1) / 10.0F) - 1;
            var26 = var11 + var22 % 10 * 8;
            var27 = var13 - var25 * var17;
            if (var4 <= 4) {
                var27 += this.f.nextInt(2);
            }

            if (var22 == var21) {
                var27 -= 2;
            }

            var28 = 0;
            if (this.g.f.getWorldInfo().isHardcoreModeEnabled()) {
                var28 = 5;
            }

            if ((float) var22 < this.g.h.getMaxHealth() / 2.0F) {
                this.b(var26, var27, 16 + var24 * 9, 9 * var28, 9, 9);
            }

            if (var3) {
                if (var22 * 2 + 1 < var5) {
                    this.b(var26, var27, var23 + 54, 9 * var28, 9, 9);
                }

                if (var22 * 2 + 1 == var5) {
                    this.b(var26, var27, var23 + 63, 9 * var28, 9, 9);
                }
            }

            if (var19 > 0.0F) {
                if (var19 == var15 && var15 % 2.0F == 1.0F) {
                    this.b(var26, var27, var23 + 153, 9 * var28, 9, 9);
                } else {
                    this.b(var26, var27, var23 + 144, 9 * var28, 9, 9);
                }

                var19 -= 2.0F;
            } else {
                if (var22 * 2 + 1 < var4) {
                    this.b(var26, var27, var23 + 36, 9 * var28, 9, 9);
                }

                if (var22 * 2 + 1 == var4) {
                    this.b(var26, var27, var23 + 45, 9 * var28, 9, 9);
                }
            }
        }

        Entity var34 = this.g.h.ridingEntity;
        if (var34 != null && !(var34 instanceof EntityBoat)) {
            if (var34 instanceof EntityLiving) {
                this.g.C.endStartSection("mountHealth");
                EntityLiving var38 = (EntityLiving) var34;
                var28 = (int) Math.ceil((double) var38.getHealth());
                float var37 = var38.getMaxHealth();
                var26 = (int) (var37 + 0.5F) / 2;
                if (var26 > 30) {
                    var26 = 30;
                }

                var27 = var13;

                for (int var39 = 0; var26 > 0; var39 += 20) {
                    int var29 = Math.min(var26, 10);
                    var26 -= var29;

                    for (int var30 = 0; var30 < var29; ++var30) {
                        byte var31 = 52;
                        byte var32 = 0;
                        int var33 = var12 - var30 * 8 - 9;
                        this.b(var33, var27, var31 + var32 * 9, 9, 9, 9);
                        if (var30 * 2 + 1 + var39 < var28) {
                            this.b(var33, var27, var31 + 36, 9, 9, 9);
                        }

                        if (var30 * 2 + 1 + var39 == var28) {
                            this.b(var33, var27, var31 + 45, 9, 9, 9);
                        }
                    }

                    var27 -= 10;
                }
            }
        } else {
            this.g.C.endStartSection("food");

            for (var23 = 0; var23 < 10; ++var23) {
                var28 = var13;
                var25 = 16;
                byte var36 = 0;
                if (this.g.h.isPotionActive(MobEffectList.hunger)) {
                    var25 += 36;
                    var36 = 13;
                }

                if (this.g.h.isHungry() && this.i % (var8 * 3 + 1) == 0) {
                    var28 = var13 + (this.f.nextInt(3) - 1);
                }

                var27 = var12 - var23 * 8 - 9;
                if (var23 < this.g.h.getFoodStats().getNutritionLimit() / 2) {
                    this.b(var27, var28, 16 + var36 * 9, 27, 9, 9);
                }

                if (var23 * 2 + 1 < var8) {
                    this.b(var27, var28, var25 + 36, 27, 9, 9);
                }

                if (var23 * 2 + 1 == var8) {
                    this.b(var27, var28, var25 + 45, 27, 9, 9);
                }
            }
        }
    //this.g.J().a(Constant.icons_lros);
    this.g.C.endStartSection("water");
    int water = foodStats.getWater();
        for(int temp = 0; temp < 10; ++temp) {
        var28 = var13 - 9;
        var25 = 16;
        int var36 = 0;

//        if (this.g.h.isPotionActive(Potions.dehydration)) {
//            var25 += 27;
//            var36 = 3;
//        }


        var27 = var12 - temp * 8 - 9;
        if (temp < this.g.h.getFoodStats().getWaterLimit() / 2) {
            this.b(var27, var28, 16 + var36 * 9, 54, 9, 9);
        }


        if (temp * 2 + 1 < water) {
            this.b(var27, var28, var25 + 9, 54, 9, 9);
        }

        if (temp * 2 + 1 == water) {
            this.b(var27, var28, var25 + 18, 54, 9, 9);
        }
    }

        this.g.C.endStartSection("air");
        if (this.g.h.isInsideOfMaterial(Material.water)) {
            var23 = this.g.h.getAir();
            var28 = MathHelper.ceiling_double_int((double)(var23 - 2) * 10.0 / 300.0);
            var25 = MathHelper.ceiling_double_int((double)var23 * 10.0 / 300.0) - var28;

            for(var26 = 0; var26 < var28 + var25; ++var26) {
                if (var26 < var28) {
                    this.b(var12 - var26 * 8 - 9, var18 - 9 , 16, 18, 9, 9);
                } else {
                    this.b(var12 - var26 * 8 - 9, var18 - 9, 25, 18, 9, 9);
                }
            }
        }

        this.g.C.endSection();
    }


    @Inject(method = "a(FZII)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/Minecraft;inDevMode()Z", shift = At.Shift.BEFORE))
    private void injectRenderPos(float par1, boolean par2, int par3, int par4, CallbackInfo ci) {
        if (!Minecraft.inDevMode()) {
            if (GuiIngame.server_load >= 0) {
                awf sr = new awf(this.g.u, this.g.d, this.g.e);
                String text = GuiIngame.server_load + "%";
                this.b(this.g.l, text, sr.a() - this.g.l.a(text) - 2, 2, 14737632);
            }

            StringBuilder var68 = (new StringBuilder()).append("平面位置(").append(MathHelper.floor_double(this.g.h.posX)).append(", ").append(MathHelper.floor_double(this.g.h.posZ)).append(")").append("   FPS=");
            var68.append(Minecraft.last_fps).append(" (");
            this.b(this.g.l, var68.append(Minecraft.last_fp10s).append(")").toString(), 2, 2, 14737632);
        }
    }
    @Shadow
    @Final
    private Minecraft g;
    @Shadow
    private final Random f = new Random();
    @Shadow
    private int i;
}
