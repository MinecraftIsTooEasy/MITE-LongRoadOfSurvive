package net.oilcake.mitelros.mixins.item;

import java.util.Random;
import net.minecraft.Enchantment;
import net.minecraft.EnchantmentData;
import net.minecraft.EnumRarity;
import net.minecraft.Item;
import net.minecraft.ItemEnchantedBook;
import net.minecraft.ItemStack;
import net.minecraft.MathHelper;
import net.minecraft.WeightedRandomChestContent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({ItemEnchantedBook.class})
public class ItemEnchantedBookMixin extends Item {
   @Overwrite
   public WeightedRandomChestContent func_92112_a(Random par1Random, int par2, int par3, int par4) {
      Enchantment var5;
      for(var5 = Enchantment.enchantmentsBookList[par1Random.nextInt(Enchantment.enchantmentsBookList.length)]; var5.rarity == EnumRarity.epic || var5.rarity == EnumRarity.rare; var5 = Enchantment.enchantmentsBookList[par1Random.nextInt(Enchantment.enchantmentsBookList.length)]) {
      }

      ItemStack var6 = new ItemStack(this.itemID, 1, 0);
      int var7 = MathHelper.getRandomIntegerInRange(par1Random, 1, var5.getNumLevels());
      this.addEnchantment(var6, new EnchantmentData(var5, var7));
      return new WeightedRandomChestContent(var6, par2, par3, par4);
   }

   @Shadow
   public void addEnchantment(ItemStack par1ItemStack, EnchantmentData par2EnchantmentData) {
   }
}
