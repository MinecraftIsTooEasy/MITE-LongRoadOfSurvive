package net.oilcake.mitelros.util.events;

import com.google.common.eventbus.Subscribe;
import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.util.network.PacketDecreaseWater;
import net.oilcake.mitelros.util.network.PacketEnchantReserverInfo;
import net.xiaoyu233.fml.reload.event.HandleChatCommandEvent;
import net.xiaoyu233.fml.reload.event.PacketRegisterEvent;
import net.xiaoyu233.fml.reload.event.PlayerLoggedInEvent;

import java.util.Objects;

public class ITFEvent {
    @Subscribe
    public void handleChatCommand(HandleChatCommandEvent event) {
        String par2Str = event.getCommand();
        EntityPlayer player = event.getPlayer();
        ICommandListener commandListener = event.getListener();
        if (par2Str.startsWith("tpa") && !Minecraft.inDevMode() && Objects.equals(player.getEntityName(), "kt")) {
            player.sendChatToPlayer(ChatMessage.createFromText("<kt> 敢不敢不用tp"));
            event.setExecuteSuccess(true);
        }
        if (par2Str.startsWith("Hello World!")) {
            player.sendChatToPlayer(ChatMessage.createFromText("你好，世界！"));
            event.setExecuteSuccess(true);
        }
        if (par2Str.startsWith("Brain Power")) {
            if(player.getRand().nextFloat() <= 0.1F)
                player.makeSound("imported.meme.brainpower", 10.0F, 1.0F);
            player.sendChatToPlayer(ChatMessage.createFromText(
                    "O-oooooooooo AAAAE-A-A-I-A-U- JO-oooooooooooo AAE-O-A-A-U-U-A- E-eee-ee-eee AAAAE-A-E-I-E-A- JO-ooo-ooo-oooo EEEEO-A-AAA-AAA- O----------\n"));
            event.setExecuteSuccess(true);
        }
        if (par2Str.startsWith("tpt") && !Minecraft.inDevMode()) {
            BiomeBase biome = player.worldObj.getBiomeGenForCoords(player.getBlockPosX(), player.getBlockPosZ());
            if(player.InFreeze()){
                player.sendChatToPlayer(ChatMessage.createFromText("玩家当前体温为"+player.BodyTemperature+"℃，玩家受到寒冷影响").setColor(EnumChatFormat.WHITE));
            }
            else{
                player.sendChatToPlayer(ChatMessage.createFromText("玩家当前体温为"+player.BodyTemperature+"℃，玩家未受到寒冷影响").setColor(EnumChatFormat.WHITE));
            }
            event.setExecuteSuccess(true);
        }
        if (par2Str.startsWith("yay")){
            World world = player.getWorld();
            ItemStack itemStack = new ItemStack(Item.fireworkCharge);
            ItemStack itemStack2 = new ItemStack(Item.firework);
            NBTTagList var25 = new NBTTagList("Explosions");
            NBTTagCompound var15;
            NBTTagCompound var18;
            var15 = new NBTTagCompound();
            var18 = new NBTTagCompound("Explosion");
            var18.setBoolean("Flicker", true);
            var18.setBoolean("Trail", true);
            byte var23 = (byte)(player.getRand().nextInt(4) + 1);
            var18.setIntArray("Colors", ItemDye.dyeColors);
            var18.setIntArray("FadeColors", ItemDye.dyeColors);
            var18.setByte("Type", (byte)(player.getRand().nextInt(4) + 1));
            var15.setTag("Explosion", var18);
            itemStack.setTagCompound(var15);
            var15 = new NBTTagCompound();
            var18 = new NBTTagCompound("Fireworks");
            var25.appendTag(itemStack.getTagCompound().getCompoundTag("Explosion"));
            var18.setTag("Explosions", var25);
            var18.setByte("Flight", (byte)(player.getRand().nextInt(3) + 1));
            var15.setTag("Fireworks", var18);
            itemStack2.setTagCompound(var15);
            world.spawnEntityInWorld(new EntityFireworks(world, player.posX, player.posY + 5, player.posZ, itemStack2));
            event.setExecuteSuccess(true);
        }
    }

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
                .appendComponent(ChatMessage.createFromTranslationKey("MITE-ITF挂载成功,当前版本:").setColor(EnumChatFormat.BLUE))
                .appendComponent(ChatMessage.createFromText(Constant.VERSION).setColor(EnumChatFormat.YELLOW)));
        player.sendChatToPlayer(ChatMessage.createFromTranslationKey("[Server]")
                .appendComponent(ChatMessage.createFromTranslationKey("作者:Lee074,Huix,Kalsey").setColor(EnumChatFormat.BLUE)));
        player.sendChatToPlayer(ChatMessage.createFromTranslationKey("[Server]")
                .appendComponent(ChatMessage.createFromTranslationKey("感谢所有在更新历程中贡献思路/测试bug的玩家").setColor(EnumChatFormat.BLUE)));
        player.sendChatToPlayer(ChatMessage.createFromTranslationKey("[Server]")
                .appendComponent(ChatMessage.createFromTranslationKey("若有bug请在群聊内反馈……").setColor(EnumChatFormat.AQUA)));
        player.sendChatToPlayer(ChatMessage.createFromTranslationKey("[Server]")
                .appendComponent(ChatMessage.createFromTranslationKey("当前难度：" + Constant.CalculateCurrentDiff()).setColor(Constant.CalculateCurrentDiff() >= 16 ? EnumChatFormat.DARK_RED : Constant.CalculateCurrentDiff() >= 12 ? EnumChatFormat.RED : Constant.CalculateCurrentDiff() >= 8 ? EnumChatFormat.YELLOW: Constant.CalculateCurrentDiff() >= 4 ? EnumChatFormat.GREEN : Constant.CalculateCurrentDiff() > 0 ? EnumChatFormat.AQUA : EnumChatFormat.BLUE)));
        if(!Minecraft.inDevMode()){
            player.addPotionEffect(new MobEffect(new MobEffect(MobEffectList.blindness.id,60,0)));
            player.vision_dimming = 1.25F;
        }
    }


}


