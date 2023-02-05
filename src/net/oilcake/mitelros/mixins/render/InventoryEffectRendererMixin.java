//package net.oilcake.mitelros.mixins.render;
//
//import net.minecraft.*;
//import net.oilcake.mitelros.util.Constant;
//import org.lwjgl.opengl.GL11;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Overwrite;
//import org.spongepowered.asm.mixin.Shadow;
//
//import java.util.Collection;
//import java.util.Iterator;
//
//@Mixin(axp.class)
//public class InventoryEffectRendererMixin extends awy {
//    public InventoryEffectRendererMixin(Container par1Container) {
//        super(par1Container);
//    }
//
//
//    protected void a(float v, int i, int i1) {
//
//    }
//
//    @Overwrite
//    private void g() {
//        int var1 = this.p - 128;
//        int var2 = this.q;
//        boolean var3 = true;
//        Collection var4 = this.f.h.getActivePotionEffects();
//        int num_effects = var4.size();
//        if (this.f.h.isMalnourished()) {
//            ++num_effects;
//        }
//
//        if (this.f.h.isInsulinResistant()) {
//            ++num_effects;
//        }
//
//        if (this.f.h.is_cursed) {
//            ++num_effects;
//        }
//
//        if (num_effects > 0) {
//            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//            GL11.glDisable(2896);
//            int var5 = 33;
//            if (num_effects > 5) {
//                var5 = 132 / (num_effects - 1);
//            }
//
//            String var11;
//            String var10;
//            bim var10000;
//            GuiIngame var10001;
//            if (this.f.h.isMalnourished()) {
//                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//                this.f.J().a(a);
//                this.b(var1, var2, 0, 166, 140, 32);
//                var10000 = this.f.J();
//                var10001 = this.f.r;
//                var10000.a(Constant.MITE_icons);
//                this.b(var1 + 6, var2 + 7, 18, 198, 18, 18);
//                var11 = bkb.a("effect.malnourished");
//                this.o.a(var11, var1 + 10 + 18 - 1, var2 + 6 + 1, 16777215);
//                var10 = ((int)this.f.f.getTotalWorldTime() - this.initial_tick) / 100 % 2 == 0 ? bkb.a("effect.malnourished.slowHealing") : bkb.a("effect.malnourished.plus50PercentHunger");
//                this.o.a(var10, var1 + 10 + 18 - 1, var2 + 6 + 10 + 1, 8355711);
//                var2 += var5;
//            }
//
//            if (this.f.h.isInsulinResistant()) {
//                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//                this.f.J().a(a);
//                this.b(var1, var2, 0, 166, 140, 32);
//                this.f.J().a(sugar_icon);
//                this.drawTexturedModalRect2(var1 + 7, var2 + 8, 16, 16);
//                EnumInsulinResistanceLevel insulin_resistance_level = this.f.h.getInsulinResistanceLevel();
//                GL11.glColor4f(insulin_resistance_level.getRedAsFloatC(), insulin_resistance_level.getGreenAsFloatC(), insulin_resistance_level.getBlueAsFloatC(), 1.0F);
//                var10000 = this.f.J();
//                var10001 = this.f.r;
//                var10000.a(Constant.MITE_icons);
//                this.b(var1 + 6, var2 + 7, 54, 198, 18, 18);
//                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//                var10 = bkb.a("effect.insulinResistance");
//                this.o.a(var10, var1 + 10 + 18 - 1, var2 + 6 + 1, 16777215);
//                var10 = StripColor.a(this.f.h.getInsulinResistance());
//                this.o.a(var10, var1 + 10 + 18 - 1, var2 + 6 + 10 + 1, 8355711);
//                var2 += var5;
//            }
//
//            if (this.f.h.is_cursed) {
//                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//                this.f.J().a(a);
//                this.b(var1, var2, 0, 166, 140, 32);
//                var10000 = this.f.J();
//                var10001 = this.f.r;
//                var10000.a(Constant.MITE_icons);
//                this.b(var1 + 6, var2 + 7, 0, 198, 18, 18);
//                var11 = bkb.a("effect.cursed");
//                this.o.a(var11, var1 + 10 + 18 - 1, var2 + 6 + 1, 16777215);
//                var10 = this.f.h.curse_effect_known ? EnumChatFormat.DARK_PURPLE + this.f.h.getCurse().getTitle() : Translator.get("curse.unknown");
//                this.o.a(var10, var1 + 10 + 18 - 1, var2 + 6 + 10 + 1, 8355711);
//                var2 += var5;
//            }
//
//            for(Iterator var6 = this.f.h.getActivePotionEffects().iterator(); var6.hasNext(); var2 += var5) {
//                MobEffect var7 = (MobEffect)var6.next();
//                MobEffectList var8 = MobEffectList.potionTypes[var7.getPotionID()];
//                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//                this.f.J().a(Constant.inventory);
//                this.b(var1, var2, 0, 166, 140, 32);
//                if (var8.d()) {
//                    int var9 = var8.e();
//                    this.b(var1 + 6, var2 + 7, 0 + var9 % 8 * 18, 198 + var9 / 8 * 18, 18, 18);
//                }
//
//                var11 = bkb.a(var8.getName());
//                if (var7.getAmplifier() == 1) {
//                    var11 = var11 + " II";
//                } else if (var7.getAmplifier() == 2) {
//                    var11 = var11 + " III";
//                } else if (var7.getAmplifier() == 3) {
//                    var11 = var11 + " IV";
//                }
//
//                this.o.a(var11, var1 + 10 + 18 - 1, var2 + 6 + 1, 16777215);
//                var10 = MobEffectList.a(var7);
//                this.o.a(var10, var1 + 10 + 18 - 1, var2 + 6 + 10 + 1, 8355711);
//            }
//        }
//
//    }
//    @Shadow
//    private int initial_tick;
//    @Shadow
//    @Final
//    private static bjo sugar_icon;
//
//
//
//}
