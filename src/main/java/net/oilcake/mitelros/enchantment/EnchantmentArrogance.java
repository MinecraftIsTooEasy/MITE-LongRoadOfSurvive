package net.oilcake.mitelros.enchantment;

import net.minecraft.CreativeTabs;
import net.minecraft.Enchantment;
import net.minecraft.EnumRarity;
import net.minecraft.Item;
import net.oilcake.mitelros.item.Materials;

public class EnchantmentArrogance extends Enchantment {
   protected EnchantmentArrogance(int id, EnumRarity rarity, int difficulty) {
      super(id, rarity, difficulty);
   }

   public int getNumLevels() {
      return 1;
   }

   public boolean canApplyTogether(Enchantment par1Enchantment) {
      return super.canApplyTogether(par1Enchantment) && par1Enchantment.effectId != Enchantments.enchantmentVanishing.effectId;
   }

   public String getNameSuffix() {
      return "arrogance";
   }

   public boolean canEnchantItem(Item item) {
      return item.getHardestMetalMaterial() != Materials.uru;
   }

   public boolean isReverse() {
      return true;
   }

   public boolean isOnCreativeTab(CreativeTabs creativeModeTab) {
      return creativeModeTab == CreativeTabs.tabTools;
   }
}
