package net.oilcake.mitelros.item;

import java.util.List;
import net.minecraft.CreativeTabs;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumChatFormatting;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.minecraft.Slot;
import net.minecraft.Translator;
import net.minecraft.World;
import net.oilcake.mitelros.item.potion.PotionExtend;

public class ItemWine extends Item {
   public ItemWine(int id) {
      super(id, Material.glass, "alcohol");
      this.setMaxStackSize(1);
      this.setCraftingDifficultyAsComponent(512.0F);
      this.setCreativeTab(CreativeTabs.tabMisc);
      this.setWater(2);
   }

   public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
      if (player.onServer()) {
         player.modifyTemperature(8000);
         player.Hasdrunked = true;
         player.addPotionEffect(new PotionEffect(Potion.confusion.id, 400, 0));
         player.addPotionEffect(new PotionEffect(PotionExtend.thirsty.id, 2560, 0));
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

   public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
      if (extended_info) {
         info.add(EnumChatFormatting.RED + Translator.getFormatted("itemwine.tooltip.warning", new Object[0]));
      }

   }
}
