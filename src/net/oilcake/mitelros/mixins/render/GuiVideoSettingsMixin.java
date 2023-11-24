package net.oilcake.mitelros.mixins.render;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(awo.class)
public class GuiVideoSettingsMixin extends awe{
    @Shadow
    protected String a = "Video Settings";
    @Shadow
    private aul c;
    @Shadow
    private boolean d;
    @Shadow
    private static aun[] e;
    @Overwrite
    public void A_() {
        this.a = bkb.a("options.videoTitle");
        this.i.clear();
        this.i.add(new aut(200, this.g / 2 - 100, this.h / 6 + 168, bkb.a("gui.done")));
        this.d = false;
        String[] var1 = new String[]{"sun.arch.data.model", "com.ibm.vm.bitmode", "os.arch"};
        String[] var2 = var1;
        int var3 = var1.length;

        int var8;
        for(var8 = 0; var8 < var3; ++var8) {
            String var5 = var2[var8];
            String var6 = System.getProperty(var5);
            if (var6 != null && var6.contains("64")) {
                this.d = true;
                break;
            }
        }

        var8 = 0;
        var3 = this.d ? 0 : -15;
        aun[] var9 = e;
        int var10 = var9.length;

        for(int var11 = 0; var11 < var10; ++var11) {
            aun var7 = var9[var11];
            if (var7.a()) {
                awk slider = new awk(var7.c(), this.g / 2 - 155 + var8 % 2 * 160, this.h / 7 + var3 + 24 * (var8 >> 1), var7, this.c.c(var7), this.c.a(var7));
                this.i.add(slider);
            } else {
                this.i.add(new awl(var7.c(), this.g / 2 - 155 + var8 % 2 * 160, this.h / 7 + var3 + 24 * (var8 >> 1), var7, this.c.c(var7)));
            }

            ++var8;
        }

    }
}
