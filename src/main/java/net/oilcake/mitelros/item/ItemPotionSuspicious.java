package net.oilcake.mitelros.item;

import net.minecraft.ChatMessageComponent;
import net.minecraft.CreativeTabs;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumChatFormatting;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.minecraft.World;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.util.ExperimentalConfig;
import net.oilcake.mitelros.util.StuckTagConfig;

public class ItemPotionSuspicious extends Item {
   public ItemPotionSuspicious(int id) {
      super(id, Material.glass, "suspicious_potion");
      this.setMaxStackSize(1);
      this.setCraftingDifficultyAsComponent(25.0F);
      this.setCreativeTab(CreativeTabs.tabMisc);
      this.setWater(1);
   }

   public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
      double rand = Math.random();
      if (player.onServer()) {
         if ((Boolean)ExperimentalConfig.TagConfig.Realistic.ConfigValue) {
            player.addPotionEffect(new PotionEffect(Potion.poison.id, (int)(450.0 * (1.0 + rand)), 0));
            player.addPotionEffect(new PotionEffect(PotionExtend.dehydration.id, (int)(160.0 * (1.0 + rand)), 0));
         } else {
            if (rand > ((Boolean)StuckTagConfig.TagConfig.TagDigest.ConfigValue ? 1.0 : 0.8)) {
               player.addPotionEffect(new PotionEffect(Potion.poison.id, 450, 0));
            }

            player.addPotionEffect(new PotionEffect(PotionExtend.dehydration.id, (int)(160.0 * (1.0 + rand)), 0));
         }

         if (rand == 0.0) {
            player.getFoodStats().addNutrition(1);
            player.sendChatToPlayer(ChatMessageComponent.createFromTranslationKey("欢迎来到Double随机等于0的欧皇大殿").setColor(EnumChatFormatting.AQUA));
         }

         player.addWater(this.getWater());
      }

      super.onItemUseFinish(item_stack, world, player);
   }

   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
      return 32;
   }

   public boolean isDrinkable(int item_subtype) {
      return true;
   }

   public Item getItemProducedOnItemUseFinish() {
      return glassBottle;
   }
}
