package net.oilcake.mitelros.util.events;

import com.google.common.eventbus.Subscribe;
import net.minecraft.*;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.util.network.PacketDecreaseWater;
import net.oilcake.mitelros.util.network.PacketEnchantReserverInfo;
import net.xiaoyu233.fml.reload.event.PacketRegisterEvent;
import net.xiaoyu233.fml.reload.event.PlayerLoggedInEvent;

public class LROSEvent {

//    @Subscribe
//    public void handleChatCommand(HandleChatCommandEvent event) {
//        String command = event.getCommand();
//        EntityPlayer player = event.getPlayer();
//        ICommandListener commandLtr = event.getListener();
//        World world = event.getWorld();
//
//        if (Minecraft.inDevMode()) {
//            if (command.startsWith("xsummon")) {
//                int id = Integer.parseInt(command.substring(8));
//                Entity entity = EntityTypes.createEntityByID(id, world);
//                if (entity != null) {
//                    entity.setPosition(player.posX, player.posY, player.posZ);
//                    if (entity instanceof EntityInsentient) {
//                        ((EntityInsentient) entity).onSpawnWithEgg(null);
//                    }
//
//                    world.spawnEntityInWorld(entity);
//                    commandLtr.sendChatToPlayer(ChatMessage.createFromText("已生成实体 " + entity).setColor(EnumChatFormat.LIGHT_GRAY));
//                } else {
//                    commandLtr.sendChatToPlayer(ChatMessage.createFromText("无法生成实体:ID为 " + id + " 的实体不存在!").setColor(EnumChatFormat.DARK_RED));
//                }
//                event.setExecuteSuccess(true);
//            }
//            if (command.startsWith("getwater")) {
//                commandLtr.sendChatToPlayer(ChatMessage.createFromText("水数值" +  player.getWater()).setColor(EnumChatFormat.LIGHT_GRAY));
//                event.setExecuteSuccess(true);
//            }
//            if (command.startsWith("addwater")) {
//                player.addWater();
//                commandLtr.sendChatToPlayer(ChatMessage.createFromText("水数值+10为" +  player.getWater()).setColor(EnumChatFormat.LIGHT_GRAY));
//                event.setExecuteSuccess(true);
//            }
//        }
//    }
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
        player.addPotionEffect(new MobEffect(new MobEffect(MobEffectList.blindness.id,150,0)));
        player.vision_dimming += 2.0F;
    }


}


