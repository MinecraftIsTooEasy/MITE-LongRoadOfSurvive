package net.oilcake.mitelros.mixins.util.command;

import net.minecraft.CommandHandler;
import net.minecraft.ServerCommandManager;
import net.oilcake.mitelros.command.CommandAddCurrentSituation;
import net.oilcake.mitelros.command.CommandAddCurse;
import net.oilcake.mitelros.command.CommandCurrentSituation;
import net.oilcake.mitelros.command.CommandDamage;
import net.oilcake.mitelros.command.CommandFullyAir;
import net.oilcake.mitelros.command.CommandHunger;
import net.oilcake.mitelros.command.CommandStoneAir;
import net.oilcake.mitelros.command.CommandTPA;
import net.oilcake.mitelros.command.CommandTeleport;
import net.oilcake.mitelros.command.CommandTestFreeze;
import net.oilcake.mitelros.command.CommandWater;
import net.oilcake.mitelros.command.CommandXsummon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ServerCommandManager.class})
public class ServerCommandManagerMixin extends CommandHandler {
   @Inject(
      method = {"<init>()V"},
      at = {@At("RETURN")}
   )
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
      this.registerCommand(new CommandDamage());
   }
}
