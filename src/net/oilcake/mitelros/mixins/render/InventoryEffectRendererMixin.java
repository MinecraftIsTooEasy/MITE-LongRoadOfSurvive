//package net.oilcake.mitelros.mixins.render;
//
//import net.minecraft.*;
//import net.oilcake.mitelros.util.Constant;
//import org.lwjgl.opengl.GL11;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Overwrite;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
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
//    @Shadow
//    protected void a(float v, int i, int i1) {
//
//    }
//
//    @Inject(method = "g", at = @At(value = "INVOKE"), cancellable = true)
//    private void Injectg(CallbackInfo callbackInfo) {
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
//        }
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
