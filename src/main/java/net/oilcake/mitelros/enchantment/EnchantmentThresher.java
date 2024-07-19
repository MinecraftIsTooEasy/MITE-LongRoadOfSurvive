package net.oilcake.mitelros.enchantment;

import net.minecraft.CreativeTabs;
import net.minecraft.Enchantment;
import net.minecraft.EnumRarity;
import net.minecraft.Item;
import net.oilcake.mitelros.item.ItemMorningStar;

public class EnchantmentThresher extends Enchantment {
   protected EnchantmentThresher(int id, EnumRarity rarity, int difficulty) {
      super(id, rarity, difficulty);
   }

   public int getNumLevels() {
      return 5;
   }

   public String getNameSuffix() {
      return "thresher";
   }

   public boolean canEnchantItem(Item item) {
      return item instanceof ItemMorningStar;
   }

   public boolean isOnCreativeTab(CreativeTabs creativeModeTab) {
      return creativeModeTab == CreativeTabs.tabCombat;
   }
}
