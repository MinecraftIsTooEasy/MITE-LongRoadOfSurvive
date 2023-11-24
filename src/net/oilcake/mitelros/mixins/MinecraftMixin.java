package net.oilcake.mitelros.mixins;

import net.minecraft.*;
import net.minecraft.client.main.Main;
import net.minecraft.server.MinecraftServer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    public aul u;
    public float ReportedGamma;

    @Overwrite
    public static String getVersionDescriptor(boolean include_formatting) {
        String red = include_formatting ? EnumChatFormat.RED.toString() : "";
        return "1.6.4-MITE" + " is too false" + (Main.is_MITE_DS ? "-DS" : "")  + (Minecraft
                .inDevMode() ? red + " DEV" : "");
    }
//    @Inject(method = "S()V", at = @At(value = "RETURN"))
//    public void ResetGammaTail(CallbackInfo callbackInfo){
//        this.u.ak = this.ReportedGamma;
//    }
//    @Inject(method = "S()V", at = @At(value = "HEAD"))
//    public void ResetGammaHead(CallbackInfo callbackInfo){
//        this.ReportedGamma = this.u.ak;
//    }

}
