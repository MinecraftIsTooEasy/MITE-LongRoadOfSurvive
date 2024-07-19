package net.oilcake.mitelros.mixins.enchantment;

import net.minecraft.CreativeTabs;
import net.minecraft.Enchantment;
import net.minecraft.EnchantmentPiercing;
import net.minecraft.EnumRarity;
import net.minecraft.Item;
import net.minecraft.ItemBattleAxe;
import net.minecraft.ItemPickaxe;
import net.oilcake.mitelros.item.ItemMorningStar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({EnchantmentPiercing.class})
public class EnchantmentPiercingMixin extends Enchantment {
   protected EnchantmentPiercingMixin(int id, EnumRarity rarity, int difficulty) {
      super(id, rarity, difficulty);
   }

   @Shadow
   public String getNameSuffix() {
      return null;
   }

   @Overwrite
   public boolean canEnchantItem(Item item) {
      return item.getClass() == ItemPickaxe.class || item.getClass() == ItemBattleAxe.class || item.getClass() == ItemMorningStar.class;
   }

   @Shadow
   public boolean isOnCreativeTab(CreativeTabs creativeModeTab) {
      return false;
   }
}
