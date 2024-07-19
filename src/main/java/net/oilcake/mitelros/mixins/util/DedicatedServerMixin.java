package net.oilcake.mitelros.mixins.util;

import net.minecraft.ChatMessageComponent;
import net.minecraft.DedicatedServer;
import net.minecraft.EnumChatFormatting;
import net.minecraft.Minecraft;
import net.minecraft.ServerPlayer;
import net.oilcake.mitelros.util.Constant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({DedicatedServer.class})
public class DedicatedServerMixin {
   @Inject(
      method = {"playerLoggedIn(Lnet/minecraft/ServerPlayer;)V"},
      at = {@At("RETURN")}
   )
   public void playerLoggedIn(ServerPlayer player, CallbackInfo callbackInfo) {
      player.setHealth(player.getHealth());
      player.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("[Server]").appendComponent(ChatMessageComponent.createFromTranslationKey("MITE-ITF挂载成功,当前版本:").setColor(EnumChatFormatting.AQUA)).appendComponent(ChatMessageComponent.createFromText(" R16f ").setColor(EnumChatFormatting.BLUE)));
      player.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("[Server]").appendComponent(ChatMessageComponent.createFromTranslationKey("作者:Lee074,NoRegrets,Kalsey").setColor(EnumChatFormatting.AQUA)));
      player.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("[Server]").appendComponent(ChatMessageComponent.createFromTranslationKey("若有bug请在群聊内反馈……").setColor(EnumChatFormatting.AQUA)));
      player.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("[Server]").appendComponent(ChatMessageComponent.createFromTranslationKey("当前难度：" + Constant.CalculateCurrentDiff()).setColor(EnumChatFormatting.AQUA)));
      if (!Minecraft.inDevMode()) {
         player.vision_dimming = 1.25F;
      }

   }

   @Shadow
   public void updatePlayersFile() {
   }
}
