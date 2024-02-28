package net.oilcake.mitelros.mixins.util;

import net.minecraft.Minecraft;
import net.minecraft.aul;
import net.minecraft.bev;
import net.minecraft.bew;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(bew.class)
public class MovementInputFromOptionsMixin extends bev {
    @Shadow
    private aul e;
    public void a() {
        if(Minecraft.O.inDevMode()){
            this.a = 0.0F;
            this.b = 0.0F;
            if (!Minecraft.O.h.isGhost()) {
                if (this.e.I.e) {
                    ++this.b;
                }

                if (this.e.K.e) {
                    --this.b;
                }

                if (this.e.J.e) {
                    ++this.a;
                }

                if (this.e.L.e) {
                    --this.a;
                }

                this.c = this.e.M.e;
                this.d = this.e.Q.e;
                if (this.d) {
                    this.a = (float)((double)this.a * 0.3);
                    this.b = (float)((double)this.b * 0.3);
                }

            }
        }else {
            if(this.a >= 0.2F){
                this.a -= 0.2F;
            }else if(this.a < -0.2F){
                this.a += 0.2F;
            }else {
                this.a = 0.0F;
            }
            if(this.b >= 0.2F){
                this.b -= 0.2F;
            }else if(this.b < -0.2F){
                this.b += 0.2F;
            }else {
                this.b = 0.0F;
            }
            if (!Minecraft.O.h.isGhost()) {
                if (this.e.I.e) {
                    if(this.b < 1.0F){
                        this.b += 0.5F;
                    }
                }

                if (this.e.K.e) {
                    if(this.b > -1.0F){
                        this.b -= 0.5F;
                    }
                }

                if (this.e.J.e) {
                    if(this.a < 1.0F){
                        this.a += 0.5F;
                    }

                }

                if (this.e.L.e) {
                    if(this.a > -1.0F){
                        this.a -= 0.5F;
                    }
                }
                this.c = this.e.M.c();
                this.d = this.e.Q.e;
                if (this.d) {
                    this.a = (float)((double)this.a * 0.5);
                    this.b = (float)((double)this.b * 0.5);
                }

            }
        }

    }
}
