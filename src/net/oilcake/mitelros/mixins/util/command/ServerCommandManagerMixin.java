package net.oilcake.mitelros.mixins.util.command;

import net.minecraft.CommandDispatcher;
import net.minecraft.CommandHandler;
import net.oilcake.mitelros.util.events.command.*;
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
        this.registerCommand(new CommandTeleport());
        this.registerCommand(new CommandTestFreeze());
        this.registerCommand(new CommandFullyAir());
        this.registerCommand(new CommandCurrentSituation());
        this.registerCommand(new CommandAddCurrentSituation());
        this.registerCommand(new CommandTPA());
        this.registerCommand(new CommandHunger());
        this.registerCommand(new CommandAddCurse());
    }
}
