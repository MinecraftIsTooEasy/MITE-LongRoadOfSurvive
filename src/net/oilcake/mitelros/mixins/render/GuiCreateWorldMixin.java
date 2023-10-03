package net.oilcake.mitelros.mixins.render;

import net.minecraft.*;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.io.FileWriter;
import java.util.Date;
import java.util.Random;

@Mixin(avb.class)
public class GuiCreateWorldMixin extends awe {
    private awe b;
    private avf c;
    private avf d;
    private String e;
    private String p = "survival";
    private boolean q = true;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    private String E;
    private String F;
    private aut x;
    private aut y;
    private aut z;
    private aut A;
    private aut B;
    private aut C;
    private aut D;
    private String G;
    private String H;
    private int I = 2;
    public String a = "";
    private aut button_cancel;
    private aut button_skills;
    private boolean are_skills_enabled;
    @Overwrite
    public void A_() {
        Keyboard.enableRepeatEvents(true);
        this.i.clear();
        this.i.add(new aut(0, this.g / 2 - 154, this.h - 28, 152, 20, bkb.a("selectWorld.create")));
        this.i.add(this.button_cancel = new aut(1, this.g / 2 + 2, this.h - 28, 152, 20, bkb.a("gui.cancel")));
        this.i.add(this.x = new aut(2, this.g / 2 - 74, 114, 152, 20, bkb.a("selectWorld.gameMode")));
        this.i.add(this.y = new aut(3, this.g / 2 - 74, this.h - 52, 152, 20, bkb.a("selectWorld.moreWorldOptions")));
        this.i.add(this.z = new aut(4, this.g / 2 - 154, 114, 152, 20, bkb.a("selectWorld.mapFeatures")));
        this.z.i = false;
        this.z.h = false;
        this.i.add(this.A = new aut(7, this.g / 2 + 2, 150, 152, 20, bkb.a("selectWorld.bonusItems")));
        this.A.i = false;
        this.i.add(this.B = new aut(5, this.g / 2 + 2, 114, 152, 20, bkb.a("selectWorld.mapType")));
        this.B.i = false;
        this.i.add(this.C = new aut(6, this.g / 2 - 154, 150, 152, 20, bkb.a("selectWorld.allowCommands")));
        this.C.i = false;
        this.i.add(this.button_skills = new aut(9, this.g / 2 + 2, 114, 152, 20, bkb.a("selectWorld.professions")));
        this.button_skills.i = false;
        this.B.h = true;
        this.C.h = false;
        this.A.h = false;
        this.i.add(this.D = new aut(8, this.g / 2 + 5, 120, 150, 20, bkb.a("selectWorld.customizeType")));
        this.D.i = false;
        this.D.h = false;
        this.c = new avf(this.o, this.g / 2 - 100, 60, 200, 20);
        this.c.b(true);
        this.c.a(this.H);
        this.d = new avf(this.o, this.g / 2 - 100, 60, 200, 20);
        this.d.a(this.G);
        this.a(this.w);
        this.g();
        this.h();
    }
    @Shadow
    private void a(boolean par1) {
    }
    @Shadow
    private void g() {
    }
    @Overwrite
    protected void a(aut par1GuiButton) {
        if (par1GuiButton.h) {
            if (par1GuiButton.g == 1) {
                this.f.a(this.b);
            } else if (par1GuiButton.g == 0) {
                this.f.a((awe)null);
                if (this.v) {
                    return;
                }

                this.v = true;
                long var2 = (new Random()).nextLong();
                String var4 = this.d.b();
                if (!MathHelper.a(var4)) {
                    try {
                        long var5 = Long.parseLong(var4);
                        if (var5 != 0L) {
                            var2 = var5;
                        }
                    } catch (NumberFormatException var10) {
                        var2 = (long)var4.hashCode();
                    }
                }

                EnumGamemode var8 = EnumGamemode.a(this.p);
                WorldSettings var6 = new WorldSettings(var2, var8, this.q, this.u, WorldType.worldTypes[this.I], this.are_skills_enabled);
                var6.func_82750_a(this.a);
                if (this.t && !this.u) {
                    var6.enableBonusChest();
                }

                if (this.r && !this.u) {
                    var6.b();
                }

                this.f.a(this.e, this.c.b().trim(), var6);
                this.f.y.a(StatisticList.createWorldStat, 1);

                try {
                    FileWriter fw = new FileWriter("MITE/world_seeds.txt", true);
                    StringBuffer sb = new StringBuffer();
                    sb.append(this.c.b().trim());
                    sb.append(": ");
                    sb.append(var6.getSeed());
                    sb.append(" (");
                    sb.append(new Date());
                    sb.append(")" + MITEConstant.newline);
                    fw.append(sb.toString());
                    fw.close();
                } catch (Exception var9) {
                }
            } else if (par1GuiButton.g == 3) {
                this.i();
            } else if (par1GuiButton.g == 2) {
                if (this.p.equals("survival")) {
                    if (!this.s) {
                        this.r = false;
                    }

                    this.u = false;
                    this.p = "hardcore";
                    this.u = true;
                    this.C.h = false;
                    this.A.h = false;
                    this.h();
                } else {
                    if (!this.s) {
                        this.r = false;
                    }

                    this.p = "survival";
                    this.h();
                    this.u = false;
                }

                this.h();
            } else if (par1GuiButton.g == 4) {
                this.q = !this.q;
                this.h();
            } else if (par1GuiButton.g == 7) {
                this.t = !this.t;
                this.h();
            } else if (par1GuiButton.g != 5) {
                if (par1GuiButton.g == 6) {
                    this.s = true;
                    this.r = false;
                    this.h();
                } else if (par1GuiButton.g == 8) {
                    this.h();
                } else if (par1GuiButton == this.button_skills) {
                    this.are_skills_enabled = !this.are_skills_enabled;
                    this.h();
                }
            } else{
                int z = this.I + 1;
                while (WorldType.worldTypes[z] == null){
                    if( z < 15){
                        z++;
                    }
                    else{
                        z = 1;
                    }
                }
                this.I = z;
                this.h();
            }
        }

    }
    @Overwrite
    private void h() {
        this.x.f = bkb.a("selectWorld.gameMode") + " " + bkb.a("selectWorld.gameMode." + this.p);
        this.E = bkb.a("selectWorld.gameMode." + this.p + ".line1");
        this.F = bkb.a("selectWorld.gameMode." + this.p + ".line2");
        this.z.f = bkb.a("selectWorld.mapFeatures") + " ";
        if (this.q) {
            this.z.f = this.z.f + bkb.a("options.on");
        } else {
            this.z.f = this.z.f + bkb.a("options.off");
        }

        this.A.f = bkb.a("selectWorld.bonusItems") + " ";
        this.A.f = this.A.f + (this.t ? bkb.a("options.on") : bkb.a("options.off"));
        this.B.f = bkb.a("selectWorld.mapType") + " " + bkb.a(WorldType.worldTypes[this.I].b());
        this.C.f = bkb.a("selectWorld.allowCommands") + " ";
        this.C.f = this.C.f + bkb.a("options.off");
        this.button_skills.f = bkb.a("selectWorld.professions") + " " + bkb.a(this.are_skills_enabled ? "options.enabled" : "options.disabled");
    }
    @Shadow
    private void i() {
    }
}
