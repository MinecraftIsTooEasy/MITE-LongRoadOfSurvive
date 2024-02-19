package net.oilcake.mitelros.mixins.server;

import net.minecraft.PlayerChunkMap;
import net.oilcake.mitelros.network.NoConsoleLogManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.PrintStream;

@Mixin(PlayerChunkMap.class)
public class PlayerManagerMixin {

    @Redirect(method = "updateMountedMovingPlayer", at = @At(value = "INVOKE", target = "Ljava/io/PrintStream;println(Ljava/lang/String;)V"))
    public void removeInfo(PrintStream printStream, String x){

    }
}
