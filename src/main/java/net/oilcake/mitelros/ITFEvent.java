package net.oilcake.mitelros;

import com.google.common.eventbus.Subscribe;
import java.util.Objects;
import net.minecraft.BiomeGenBase;
import net.minecraft.ChatMessageComponent;
import net.minecraft.EntityFireworkRocket;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumChatFormatting;
import net.minecraft.ICommandSender;
import net.minecraft.Item;
import net.minecraft.ItemDye;
import net.minecraft.ItemStack;
import net.minecraft.Minecraft;
import net.minecraft.NBTTagCompound;
import net.minecraft.NBTTagList;
import net.minecraft.StringHelper;
import net.minecraft.World;
import net.oilcake.mitelros.achivements.AchievementExtend;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.enchantment.Enchantments;
import net.oilcake.mitelros.item.ItemGuideBook;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.network.PacketDecreaseWater;
import net.oilcake.mitelros.network.PacketEnchantReserverInfo;
import net.oilcake.mitelros.network.PacketUpdateTemperature;
import net.oilcake.mitelros.util.Constant;
import net.xiaoyu233.fml.reload.event.*;
import net.xiaoyu233.fml.reload.utils.IdUtil;

public class ITFEvent {
   
   @Subscribe
   public void handleChatCommand(HandleChatCommandEvent event) {
      String par2Str = event.getCommand();
      EntityPlayer player = event.getPlayer();
      ICommandSender commandListener = event.getListener();
      if (par2Str.startsWith("tpa") && !Minecraft.inDevMode() && Objects.equals(player.getEntityName(), "kt")) {
         player.sendChatToPlayer(ChatMessageComponent.createFromText("<kt> 敢不敢不用tp"));
         event.setExecuteSuccess(true);
      }

      if (par2Str.startsWith("Hello World!")) {
         player.sendChatToPlayer(ChatMessageComponent.createFromText("你好，世界！"));
         event.setExecuteSuccess(true);
      }

      if (par2Str.startsWith("Brain Power")) {
         if (player.rand.nextFloat() <= 0.1F) {
            player.makeSound("imported.meme.brainpower", 10.0F, 1.0F);
         }

         player.sendChatToPlayer(ChatMessageComponent.createFromText("O-oooooooooo AAAAE-A-A-I-A-U- JO-oooooooooooo AAE-O-A-A-U-U-A- E-eee-ee-eee AAAAE-A-E-I-E-A- JO-ooo-ooo-oooo EEEEO-A-AAA-AAA- O----------\n"));
         event.setExecuteSuccess(true);
      }

      if (par2Str.startsWith("tpt") && !Minecraft.inDevMode()) {
         BiomeGenBase biome = player.worldObj.getBiomeGenForCoords(player.getBlockPosX(), player.getBlockPosZ());
         player.sendChatToPlayer(ChatMessageComponent.createFromText("玩家当前体温为" + StringHelper.formatFloat(player.BodyTemperature, 1, 1) + "℃，玩家目前可接受温度范围为：" + player.getTemperatureTolerance()[0] + " " + player.getTemperatureTolerance()[1] + "，环境温度为" + player.getEnvironmentTemperature(biome.temperature, player.getWorld().getTotalWorldTime()) + "℃。").setColor(EnumChatFormatting.WHITE));
         event.setExecuteSuccess(true);
      }

      if (par2Str.startsWith("yay")) {
         World world = player.getWorld();
         ItemStack itemStack = new ItemStack(Item.fireworkCharge);
         ItemStack itemStack2 = new ItemStack(Item.firework);
         NBTTagList var25 = new NBTTagList("Explosions");
         NBTTagCompound var15 = new NBTTagCompound();
         NBTTagCompound var18 = new NBTTagCompound("Explosion");
         var18.setBoolean("Flicker", true);
         var18.setBoolean("Trail", true);
         byte var23 = (byte)(player.rand.nextInt(4) + 1);
         var18.setIntArray("Colors", ItemDye.dyeColors);
         var18.setIntArray("FadeColors", ItemDye.dyeColors);
         var18.setByte("Type", (byte)(player.rand.nextInt(4) + 1));
         var15.setTag("Explosion", var18);
         itemStack.setTagCompound(var15);
         var15 = new NBTTagCompound();
         var18 = new NBTTagCompound("Fireworks");
         var25.appendTag(itemStack.getTagCompound().getCompoundTag("Explosion"));
         var18.setTag("Explosions", var25);
         var18.setByte("Flight", (byte)(player.rand.nextInt(3) + 1));
         var15.setTag("Fireworks", var18);
         itemStack2.setTagCompound(var15);
         world.spawnEntityInWorld(new EntityFireworkRocket(world, player.posX, player.posY + 5.0, player.posZ, itemStack2));
         event.setExecuteSuccess(true);
      }

   }

   @Subscribe
   public void onItemRegister(ItemRegistryEvent event){
      Blocks.registerBlocks(event);
   }

   @Subscribe
   public void onRecipeRegister(RecipeRegistryEvent event){
      Blocks.registerRecipes(event);
   }

   @Subscribe
   public void onEnchantmentRegister(EnchantmentRegistryEvent event){
      Enchantments.registerEnchantments(event);
   }

   @Subscribe
   public void onAchievementRegister(AchievementRegistryEvent event){
      AchievementExtend.registerAchievements();
   }


   @Subscribe
   public void onPacketRegister(PacketRegisterEvent event) {
      event.register(IdUtil.getNextPacketID(), true, true, PacketEnchantReserverInfo.class);
      event.register(IdUtil.getNextPacketID(), false, true, PacketDecreaseWater.class);
      event.register(IdUtil.getNextPacketID(), true, true, PacketUpdateTemperature.class);
   }

   @Subscribe
   public void onPlayerLoggedIn(PlayerLoggedInEvent event) {
      EntityPlayer player = event.getPlayer();
      player.setHealth(player.getHealth());
      player.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("[Server]").appendComponent(ChatMessageComponent.createFromTranslationKey("MITE-ITF挂载成功,当前版本:").setColor(EnumChatFormatting.BLUE)).appendComponent(ChatMessageComponent.createFromText(" R16f ").setColor(EnumChatFormatting.YELLOW)));
      player.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("[Server]").appendComponent(ChatMessageComponent.createFromTranslationKey("作者:Lee074,Huix,Kalsey").setColor(EnumChatFormatting.BLUE)));
      player.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("[Server]").appendComponent(ChatMessageComponent.createFromTranslationKey("感谢所有在更新历程中贡献思路/测试bug的玩家").setColor(EnumChatFormatting.BLUE)));
      player.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("[Server]").appendComponent(ChatMessageComponent.createFromTranslationKey("若有bug请在群聊内反馈……").setColor(EnumChatFormatting.AQUA)));
      player.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("[Server]").appendComponent(ChatMessageComponent.createFromTranslationKey("当前难度：" + Constant.CalculateCurrentDiff()).setColor(Constant.CalculateCurrentDiff() >= 16 ? EnumChatFormatting.DARK_RED : (Constant.CalculateCurrentDiff() >= 12 ? EnumChatFormatting.RED : (Constant.CalculateCurrentDiff() >= 8 ? EnumChatFormatting.YELLOW : (Constant.CalculateCurrentDiff() >= 4 ? EnumChatFormatting.GREEN : (Constant.CalculateCurrentDiff() > 0 ? EnumChatFormatting.AQUA : EnumChatFormatting.BLUE)))))));
      if (!Minecraft.inDevMode()) {
         player.vision_dimming = 1.25F;
      }

      if (player.isNewPlayer) {
         ItemStack guide = new ItemStack(Items.guide);
         guide.setTagCompound(ItemGuideBook.generateBookContents());
         player.inventory.addItemStackToInventoryOrDropIt(guide);
         player.isNewPlayer = false;
      }

   }
}
