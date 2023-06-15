package net.oilcake.mitelros.util.events;

import com.google.common.eventbus.Subscribe;
import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.util.EnumChatFormats;
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
        if (par2Str.startsWith("tpa") && !Minecraft.inDevMode()) {
            player.sendChatToPlayer(ChatMessage.createFromText("<kt> 敢不敢不用tp"));
            event.setExecuteSuccess(true);
        }
        if (par2Str.startsWith("Hello World!")) {
            if(Objects.equals(player.getEntityName(), "HY_Creespic")){
                EntityItem a = new EntityItem(player.worldObj,player.posX,player.posY+32,player.posZ,new ItemStack(Items.MaidHelmet.itemID,1));
                player.worldObj.spawnEntityInWorld(a);
                a.entityFX(EnumEntityFX.summoned);
                EntityItem b = new EntityItem(player.worldObj,player.posX,player.posY+32,player.posZ,new ItemStack(Items.MaidChestplate.itemID,1));
                player.worldObj.spawnEntityInWorld(b);
                b.entityFX(EnumEntityFX.summoned);
                EntityItem c = new EntityItem(player.worldObj,player.posX,player.posY+32,player.posZ,new ItemStack(Items.MaidLeggings.itemID,1));
                player.worldObj.spawnEntityInWorld(c);
                c.entityFX(EnumEntityFX.summoned);
                EntityItem d = new EntityItem(player.worldObj,player.posX,player.posY+32,player.posZ,new ItemStack(Items.MaidBoots.itemID,1));
                player.worldObj.spawnEntityInWorld(d);
                d.entityFX(EnumEntityFX.summoned);
            }
            player.sendChatToPlayer(ChatMessage.createFromText("你好，世界！"));
            event.setExecuteSuccess(true);
        }
        if (par2Str.startsWith("Brain Power")) {
            if(Objects.equals(player.getEntityName(), "HY_Creespic")){
                EntityItem a = new EntityItem(player.worldObj,player.posX,player.posY+32,player.posZ,new ItemStack(Items.helmetCustom_a.itemID,1));
                player.worldObj.spawnEntityInWorld(a);
                a.entityFX(EnumEntityFX.summoned);
                EntityItem b = new EntityItem(player.worldObj,player.posX,player.posY+32,player.posZ,new ItemStack(Items.chestplateCustom_a.itemID,1));
                player.worldObj.spawnEntityInWorld(b);
                b.entityFX(EnumEntityFX.summoned);
                EntityItem c = new EntityItem(player.worldObj,player.posX,player.posY+32,player.posZ,new ItemStack(Items.leggingsCustom_a.itemID,1));
                player.worldObj.spawnEntityInWorld(c);
                c.entityFX(EnumEntityFX.summoned);
                EntityItem d = new EntityItem(player.worldObj,player.posX,player.posY+32,player.posZ,new ItemStack(Items.bootsCustom_a.itemID,1));
                player.worldObj.spawnEntityInWorld(d);
                d.entityFX(EnumEntityFX.summoned);
            }
            if(player.getRand().nextFloat() <= (Objects.equals(player.getEntityName(), "HY_Creespic") ? 10F : 0.1F))
                player.makeSound("imported.meme.brainpower", 10.0F, 1.0F);
            player.sendChatToPlayer(ChatMessage.createFromText("O-oooooooooo AAAAE-A-A-I-A-U- JO-oooooooooooo AAE-O-A-A-U-U-A- E-eee-ee-eee AAAAE-A-E-I-E-A- JO-ooo-oo-oo-oo EEEEO-A-AAA-AAAA\n" +
                    "O-oooooooooo AAAAE-A-A-I-A-U- JO-oooooooooooo AAE-O-A-A-U-U-A- E-eee-ee-eee AAAAE-A-E-I-E-A- JO-ooo-oo-oo-oo EEEEO-A-AAA-AAAA\n" +
                    "O-oooooooooo AAAAE-A-A-I-A-U- JO-oooooooooooo AAE-O-A-A-U-U-A- E-eee-ee-eee AAAAE-A-E-I-E-A- JO-ooo-ooo-oooo EEEEO-A-AAA-AAA- O----------\n" +
                    "\n"));
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
                .appendComponent(ChatMessage.createFromTranslationKey("作者:Lee074,NoRegrets,Kalsey").setColor(EnumChatFormat.BLUE)));
        player.sendChatToPlayer(ChatMessage.createFromTranslationKey("[Server]")
                .appendComponent(ChatMessage.createFromTranslationKey("若有bug请在群聊内反馈……").setColor(EnumChatFormat.AQUA)));
        player.sendChatToPlayer(ChatMessage.createFromTranslationKey("[Server]")
                .appendComponent(ChatMessage.createFromTranslationKey("当前难度：" + Constant.CalculateCurrentDiff()).setColor(Constant.CalculateCurrentDiff() >= 16 ? EnumChatFormat.DARK_RED : Constant.CalculateCurrentDiff() >= 12 ? EnumChatFormat.RED : Constant.CalculateCurrentDiff() >= 8 ? EnumChatFormat.YELLOW: Constant.CalculateCurrentDiff() >= 4 ? EnumChatFormat.GREEN : Constant.CalculateCurrentDiff() > 0 ? EnumChatFormat.AQUA : EnumChatFormat.BLUE)));
        player.addPotionEffect(new MobEffect(new MobEffect(MobEffectList.blindness.id,60,0)));
        player.vision_dimming += 1.25F;
    }


}


