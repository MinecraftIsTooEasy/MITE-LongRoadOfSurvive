package net.oilcake.mitelros.enchantment;

import net.minecraft.CreativeTabs;
import net.minecraft.Enchantment;
import net.minecraft.EnumRarity;
import net.minecraft.Item;
import net.minecraft.ItemArmor;
import net.minecraft.ItemTool;

public class EnchantmentMending extends Enchantment {
   protected EnchantmentMending(int id, EnumRarity rarity, int difficulty) {
      super(id, rarity, difficulty);
   }

   public int getNumLevels() {
      return 1;
   }

   public boolean canApplyTogether(Enchantment par1Enchantment) {
      return super.canApplyTogether(par1Enchantment) && par1Enchantment.effectId != unbreaking.effectId;
   }

   public String getNameSuffix() {
      return "mending";
   }

   public boolean canEnchantItem(Item item) {
      return item instanceof ItemTool || item instanceof ItemArmor;
   }

   public boolean isOnCreativeTab(CreativeTabs creativeModeTab) {
      return creativeModeTab == CreativeTabs.tabTools;
   }

   public boolean isTreasure() {
      return true;
   }
}
