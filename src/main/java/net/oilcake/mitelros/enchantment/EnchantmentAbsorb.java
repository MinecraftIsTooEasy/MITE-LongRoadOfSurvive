package net.oilcake.mitelros.enchantment;

import net.minecraft.CreativeTabs;
import net.minecraft.Enchantment;
import net.minecraft.EnumRarity;
import net.minecraft.Item;
import net.minecraft.ItemPickaxe;
import net.minecraft.ItemWarHammer;

public class EnchantmentAbsorb extends Enchantment {
   protected EnchantmentAbsorb(int id, EnumRarity rarity, int difficulty) {
      super(id, rarity, difficulty);
   }

   public int getNumLevels() {
      return 1;
   }

   public boolean canApplyTogether(Enchantment par1Enchantment) {
      return super.canApplyTogether(par1Enchantment) && par1Enchantment.effectId != silkTouch.effectId;
   }

   public String getNameSuffix() {
      return "absorb";
   }

   public boolean canEnchantItem(Item item) {
      return item instanceof ItemPickaxe && !(item instanceof ItemWarHammer);
   }

   public boolean isOnCreativeTab(CreativeTabs creativeModeTab) {
      return creativeModeTab == CreativeTabs.tabTools;
   }
}
