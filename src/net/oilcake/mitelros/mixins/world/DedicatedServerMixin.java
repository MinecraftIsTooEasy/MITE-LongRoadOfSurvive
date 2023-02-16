package net.oilcake.mitelros.mixins.world;

import net.minecraft.ChatMessage;
import net.minecraft.DedicatedServer;
import net.minecraft.EnumChatFormat;
import net.minecraft.ServerPlayer;
import net.oilcake.mitelros.util.Constant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DedicatedServer.class)
public class DedicatedServerMixin {
    @Inject(method = "playerLoggedIn",at = @At("RETURN"))
    public void playerLoggedIn(ServerPlayer player, CallbackInfo callbackInfo) {
        player.sendChatToPlayer(ChatMessage.createFromTranslationKey("[Server]")
                .appendComponent(ChatMessage.createFromTranslationKey("MITE-LROS挂载成功,当前版本:").setColor(EnumChatFormat.DARK_GREEN))
                .appendComponent(ChatMessage.createFromText(Constant.VERSION).setColor(EnumChatFormat.DARK_RED)));
        player.sendChatToPlayer(ChatMessage.createFromTranslationKey("[MITE-LROS]")
                .appendComponent(ChatMessage.createFromTranslationKey("作者:Lee,NoRegrets,Kalsey 特别感谢:Wensc,XiaoYu").setColor(EnumChatFormat.LIGHT_PURPLE)));
        player.sendChatToPlayer(ChatMessage.createFromTranslationKey("[MITE-LROS]")
                .appendComponent(ChatMessage.createFromTranslationKey("企鹅群:706242091  更新日志链接:lucklong.cn").setColor(EnumChatFormat.LIGHT_GRAY)));
        this.updatePlayersFile();
    }
    @Shadow
    public void updatePlayersFile() {
    }
}
