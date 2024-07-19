package net.oilcake.mitelros.enchantment;

import net.minecraft.CreativeTabs;
import net.minecraft.Enchantment;
import net.minecraft.EnumRarity;
import net.minecraft.Item;
import net.minecraft.ItemCuirass;

public class EnchantmentCallOfAntarctic extends Enchantment {
   protected EnchantmentCallOfAntarctic(int id, EnumRarity rarity, int difficulty) {
      super(id, rarity, difficulty);
   }

   public int getNumLevels() {
      return 1;
   }

   public String getNameSuffix() {
      return "callofantarctic";
   }

   public boolean canEnchantItem(Item item) {
      return item instanceof ItemCuirass;
   }

   public boolean isOnCreativeTab(CreativeTabs creativeModeTab) {
      return creativeModeTab == CreativeTabs.tabTools;
   }
}
