package net.oilcake.mitelros.mixins.util;

import net.minecraft.Minecraft;
import net.minecraft.ats;
import net.minecraft.aul;
import net.minecraft.aun;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.io.*;

@Mixin(aul.class)
public class GameSettingsMixin{
    public float ReportedGamma;
//    @Inject(method = "<init>(Lnet/minecraft/Minecraft;Ljava/io/File;)V",at = @At(value = "RETURN"))
//    public void ResetGammaTail(CallbackInfo callbackInfo){
//        this.ReportedGamma = 0.4F;
//    }
//    @Inject(method = "<init>(Lnet/minecraft/Minecraft;Ljava/io/File;)V",at = @At(value = "HEAD"))
//    public void ResetGammaHead(CallbackInfo callbackInfo){
//        this.ReportedGamma = 0.4F;
//    }


    @Shadow
    public float a = 1.0F;
    @Shadow
    public float b = 1.0F;
    @Shadow
    public float c = 0.5F;
    @Shadow
    public boolean d;
    @Shadow
    private int e;
    @Shadow
    public boolean f = true;
    @Shadow
    public boolean g;
    @Shadow
    public boolean h;
    @Shadow
    public int i = 3;
    @Shadow
    private boolean j = true;
    @Shadow
    public int k = 2;
    @Shadow
    public boolean l = true;
    @Shadow
    public String m = "Default";
    @Shadow
    public int n;
    @Shadow
    public boolean o = true;
    @Shadow
    public boolean p = true;
    @Shadow
    public boolean q = true;
    @Shadow
    public float r = 1.0F;
    @Shadow
    public boolean s = true;
    @Shadow
    public boolean t = true;
    @Shadow
    private boolean u;
    @Shadow
    private boolean v = true;
    @Shadow
    public boolean w;
    @Shadow
    public boolean x;
    @Shadow
    public boolean y = true;
    @Shadow
    public boolean z = true;
    @Shadow
    public boolean A;
    @Shadow
    public int B;
    @Shadow
    public int C;
    @Shadow
    public boolean D = true;
    @Shadow
    public float E = 1.0F;
    @Shadow
    public float F = 1.0F;
    @Shadow
    public float G = 0.44366196F;
    @Shadow
    public float H = 1.0F;
    @Shadow
    public ats T = new ats("key.playerlist", 41);
    @Shadow
    public ats V = new ats("key.command", 53);
    @Shadow
    public ats keyBindToggleRun = new ats("key.toggleRun", 15);
    @Shadow
    public ats[] W;
    @Shadow
    protected Minecraft X;
    @Shadow
    private File av;
    @Shadow
    public int Y;
    @Shadow
    public String ad;
    @Shadow
    public float aj;
    @Shadow
    public float ak;
    @Shadow
    public int al;
    @Shadow
    public int am;
    @Shadow
    public String an;
    @Shadow
    private float a(String par1Str) {
        return 0;
    }
    @Overwrite
    public float a(aun par1EnumOptions) {
        return par1EnumOptions == aun.e ? this.aj : (par1EnumOptions == aun.f ? this.ReportedGamma : (par1EnumOptions == aun.a ? this.a : (par1EnumOptions == aun.b ? this.b : (par1EnumOptions == aun.d ? this.c : (par1EnumOptions == aun.u ? this.r : (par1EnumOptions == aun.E ? this.H : (par1EnumOptions == aun.F ? this.G : (par1EnumOptions == aun.C ? this.E : (par1EnumOptions == aun.D ? this.F : 0.0F)))))))));
    }
    @Overwrite
    public void a(aun par1EnumOptions, float par2) {
        if (par1EnumOptions == aun.a) {
            this.a = par2;
            this.X.v.a();
        }

        if (par1EnumOptions == aun.b) {
            this.b = par2;
            this.X.v.a();
        }

        if (par1EnumOptions == aun.d) {
            this.c = par2;
        }

        if (par1EnumOptions == aun.e) {
            this.aj = par2;
        }

        if (par1EnumOptions == aun.f) {
            this.ReportedGamma = par2;
        }

        if (par1EnumOptions == aun.u) {
            this.r = par2;
            this.X.r.b().b();
        }

        if (par1EnumOptions == aun.E) {
            this.H = par2;
            this.X.r.b().b();
        }

        if (par1EnumOptions == aun.F) {
            this.G = par2;
            this.X.r.b().b();
        }

        if (par1EnumOptions == aun.D) {
            this.F = par2;
            this.X.r.b().b();
        }

        if (par1EnumOptions == aun.C) {
            this.E = par2;
            this.X.r.b().b();
        }

    }
    @Overwrite
    public void a() {
        try {
            if (!this.av.exists()) {
                return;
            }

            BufferedReader var1 = new BufferedReader(new FileReader(this.av));
            String var2 = "";

            while((var2 = var1.readLine()) != null) {
                try {
                    String[] var3 = var2.split(":");
                    if (var3[0].equals("music")) {
                        this.a = this.a(var3[1]);
                    }

                    if (var3[0].equals("sound")) {
                        this.b = this.a(var3[1]);
                    }

                    if (var3[0].equals("mouseSensitivity")) {
                        this.c = this.a(var3[1]);
                    }

                    if (var3[0].equals("fov")) {
                        this.aj = this.a(var3[1]);
                    }

                    if (var3[0].equals("gamma")) {
                        this.ReportedGamma = this.a(String.valueOf(Math.min(1.0F, Float.parseFloat(var3[1]))));
                    }

                    if (var3[0].equals("invertYMouse")) {
                        this.d = var3[1].equals("true");
                    }

                    if (var3[0].equals("viewDistance")) {
                        this.e = Integer.parseInt(var3[1]);
                    }

                    if (var3[0].equals("guiScale")) {
                        this.al = Integer.parseInt(var3[1]);
                    }

                    if (var3[0].equals("particles")) {
                        this.am = Integer.parseInt(var3[1]);
                    }

                    if (var3[0].equals("bobView")) {
                        this.f = var3[1].equals("true");
                    }

                    if (var3[0].equals("anaglyph3d")) {
                        this.g = var3[1].equals("true");
                    }

                    if (var3[0].equals("advancedOpengl")) {
                        this.h = var3[1].equals("true");
                    }

                    if (var3[0].equals("fpsLimit")) {
                        this.i = Integer.parseInt(var3[1]);
                        if (this.i == 0) {
                            this.i = 3;
                        }
                    }

                    if (var3[0].equals("difficulty")) {
                        this.Y = 3;
                    }

                    if (var3[0].equals("fancyGraphics")) {
                        this.j = var3[1].equals("true");
                    }

                    if (var3[0].equals("ao")) {
                        if (var3[1].equals("true")) {
                            this.k = 2;
                        } else if (var3[1].equals("false")) {
                            this.k = 0;
                        } else {
                            this.k = Integer.parseInt(var3[1]);
                        }
                    }

                    if (var3[0].equals("clouds")) {
                        this.l = var3[1].equals("true");
                    }

                    if (var3[0].equals("skin")) {
                        this.m = var3[1];
                    }

                    if (var3[0].equals("lastServer") && var3.length >= 2) {
                        this.ad = var2.substring(var2.indexOf(58) + 1);
                    }

                    if (var3[0].equals("lang") && var3.length >= 2) {
                        this.an = var3[1];
                    }

                    if (var3[0].equals("chatVisibility")) {
                        this.n = Integer.parseInt(var3[1]);
                    }

                    if (var3[0].equals("chatColors")) {
                        this.o = var3[1].equals("true");
                    }

                    if (var3[0].equals("chatLinks")) {
                        this.p = var3[1].equals("true");
                    }

                    if (var3[0].equals("chatLinksPrompt")) {
                        this.q = var3[1].equals("true");
                    }

                    if (var3[0].equals("chatOpacity")) {
                        this.r = this.a(var3[1]);
                    }

                    if (var3[0].equals("serverTextures")) {
                        this.s = var3[1].equals("true");
                    }

                    if (var3[0].equals("snooperEnabled")) {
                        this.t = var3[1].equals("true");
                    }

                    if (var3[0].equals("fullscreen")) {
                        this.u = var3[1].equals("true");
                    }

                    if (var3[0].equals("enableVsync")) {
                        this.v = var3[1].equals("true");
                    }

                    if (var3[0].equals("hideServerAddress")) {
                        this.w = var3[1].equals("true");
                    }

                    if (var3[0].equals("advancedItemTooltips")) {
                        this.x = var3[1].equals("true");
                    }

                    if (var3[0].equals("pauseOnLostFocus")) {
                        this.y = var3[1].equals("true");
                    }

                    if (var3[0].equals("showCape")) {
                        this.z = var3[1].equals("true");
                    }

                    if (var3[0].equals("touchscreen")) {
                        this.A = var3[1].equals("true");
                    }

                    if (var3[0].equals("overrideHeight")) {
                        this.C = Integer.parseInt(var3[1]);
                    }

                    if (var3[0].equals("overrideWidth")) {
                        this.B = Integer.parseInt(var3[1]);
                    }

                    if (var3[0].equals("heldItemTooltips")) {
                        this.D = var3[1].equals("true");
                    }

                    if (var3[0].equals("chatHeightFocused")) {
                        this.H = this.a(var3[1]);
                    }

                    if (var3[0].equals("chatHeightUnfocused")) {
                        this.G = this.a(var3[1]);
                    }

                    if (var3[0].equals("chatScale")) {
                        this.E = this.a(var3[1]);
                    }

                    if (var3[0].equals("chatWidth")) {
                        this.F = this.a(var3[1]);
                    }

                    for(int var4 = 0; var4 < this.W.length; ++var4) {
                        if (var3[0].equals("key_" + this.W[var4].c)) {
                            this.W[var4].d = Integer.parseInt(var3[1]);
                        }
                    }
                } catch (Exception var5) {
                    this.X.getLogAgent().logWarning("Skipping bad option: " + var2);
                }
            }

            ats.b();
            var1.close();
        } catch (Exception var6) {
            this.X.getLogAgent().logWarning("Failed to load options");
            var6.printStackTrace();
        }

        if (this.T.d == 15 && this.keyBindToggleRun.d == 15) {
            this.T.d = 41;
        }

    }
    @Overwrite
    public void b() {
        try {
            PrintWriter var1 = new PrintWriter(new FileWriter(this.av));
            var1.println("music:" + this.a);
            var1.println("sound:" + this.b);
            var1.println("invertYMouse:" + this.d);
            var1.println("mouseSensitivity:" + this.c);
            var1.println("fov:" + this.aj);
            var1.println("gamma:" + this.ReportedGamma);
            var1.println("viewDistance:" + this.e);
            var1.println("guiScale:" + this.al);
            var1.println("particles:" + this.am);
            var1.println("bobView:" + this.f);
            var1.println("anaglyph3d:" + this.g);
            var1.println("advancedOpengl:" + this.h);
            var1.println("fpsLimit:" + this.i);
            var1.println("difficulty:" + this.Y);
            var1.println("fancyGraphics:" + this.j);
            var1.println("ao:" + this.k);
            var1.println("clouds:" + this.l);
            var1.println("skin:" + this.m);
            var1.println("lastServer:" + this.ad);
            var1.println("lang:" + this.an);
            var1.println("chatVisibility:" + this.n);
            var1.println("chatColors:" + this.o);
            var1.println("chatLinks:" + this.p);
            var1.println("chatLinksPrompt:" + this.q);
            var1.println("chatOpacity:" + this.r);
            var1.println("serverTextures:" + this.s);
            var1.println("snooperEnabled:" + this.t);
            var1.println("fullscreen:" + this.u);
            var1.println("enableVsync:" + this.v);
            var1.println("hideServerAddress:" + this.w);
            var1.println("advancedItemTooltips:" + this.x);
            var1.println("pauseOnLostFocus:" + this.y);
            var1.println("showCape:" + this.z);
            var1.println("touchscreen:" + this.A);
            var1.println("overrideWidth:" + this.B);
            var1.println("overrideHeight:" + this.C);
            var1.println("heldItemTooltips:" + this.D);
            var1.println("chatHeightFocused:" + this.H);
            var1.println("chatHeightUnfocused:" + this.G);
            var1.println("chatScale:" + this.E);
            var1.println("chatWidth:" + this.F);

            for(int var2 = 0; var2 < this.W.length; ++var2) {
                var1.println("key_" + this.W[var2].c + ":" + this.W[var2].d);
            }

            var1.close();
        } catch (Exception var3) {
            this.X.getLogAgent().logWarning("Failed to save options");
            var3.printStackTrace();
        }

        this.c();
    }
    @Shadow
    public void c() {
    }
}
