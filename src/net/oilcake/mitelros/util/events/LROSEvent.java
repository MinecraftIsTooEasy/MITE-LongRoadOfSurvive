package net.oilcake.mitelros.util.events;

import com.google.common.eventbus.Subscribe;
import net.minecraft.*;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.util.network.PacketDecreaseWater;
import net.oilcake.mitelros.util.network.PacketEnchantReserverInfo;
import net.xiaoyu233.fml.reload.event.HandleChatCommandEvent;
import net.xiaoyu233.fml.reload.event.PacketRegisterEvent;
import net.xiaoyu233.fml.reload.event.PlayerLoggedInEvent;

public class LROSEvent {

    @Subscribe
    public void onPacketRegister(PacketRegisterEvent event){
        event.register(180, true, true, PacketEnchantReserverInfo.class);
        event.register(181, false, true, PacketDecreaseWater.class);
//        event.register(182,true,true, PacketReadFreezeCooldown.class);
    }

    @Subscribe
    public void onPlayerLoggedIn(PlayerLoggedInEvent event) {
        EntityPlayer player = event.getPlayer();
        player.sendChatToPlayer(ChatMessage.createFromTranslationKey("[Server]")
                .appendComponent(ChatMessage.createFromTranslationKey("MITE-ITF挂载成功,当前版本:").setColor(EnumChatFormat.AQUA))
                .appendComponent(ChatMessage.createFromText(Constant.VERSION).setColor(EnumChatFormat.BLUE)));
        player.sendChatToPlayer(ChatMessage.createFromTranslationKey("[Server]")
                .appendComponent(ChatMessage.createFromTranslationKey("作者:Lee074,NoRegrets,Kalsey").setColor(EnumChatFormat.AQUA)));
        player.sendChatToPlayer(ChatMessage.createFromTranslationKey("[Server]")
                .appendComponent(ChatMessage.createFromTranslationKey("若有bug请在群聊内反馈……").setColor(EnumChatFormat.AQUA)));
        player.sendChatToPlayer(ChatMessage.createFromTranslationKey("[Server]")
                .appendComponent(ChatMessage.createFromTranslationKey("当前难度：" + Constant.CalculateCurrentDiff()).setColor(EnumChatFormat.AQUA)));
        player.addPotionEffect(new MobEffect(new MobEffect(MobEffectList.blindness.id,60,0)));
        player.vision_dimming += 1.25F;
    }


}


