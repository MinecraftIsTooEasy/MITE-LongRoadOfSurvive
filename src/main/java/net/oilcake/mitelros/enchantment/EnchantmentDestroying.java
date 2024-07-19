package net.oilcake.mitelros.enchantment;

import net.minecraft.CreativeTabs;
import net.minecraft.Enchantment;
import net.minecraft.EnumRarity;
import net.minecraft.Item;
import net.minecraft.ItemWarHammer;

public class EnchantmentDestroying extends Enchantment {
   protected EnchantmentDestroying(int id, EnumRarity rarity, int difficulty) {
      super(id, rarity, difficulty);
   }

   public int getNumLevels() {
      return 3;
   }

   public String getNameSuffix() {
      return "destroying";
   }

   public boolean canEnchantItem(Item item) {
      return item instanceof ItemWarHammer;
   }

   public boolean isOnCreativeTab(CreativeTabs creativeModeTab) {
      return creativeModeTab == CreativeTabs.tabCombat;
   }
}
