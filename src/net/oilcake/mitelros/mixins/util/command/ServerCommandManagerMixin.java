package net.oilcake.mitelros.mixins.util.command;

import net.minecraft.CommandDispatcher;
import net.minecraft.CommandHandler;
import net.oilcake.mitelros.util.events.command.CommandWater;
import net.oilcake.mitelros.util.events.command.CommandXsummon;
import net.oilcake.mitelros.util.events.command.CommandStoneAir;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommandDispatcher.class)
public class ServerCommandManagerMixin extends CommandHandler {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void injectInit(CallbackInfo callbackInfo) {
        this.registerCommand(new CommandXsummon());
        this.registerCommand(new CommandWater());
        this.registerCommand(new CommandStoneAir());
    }

}
