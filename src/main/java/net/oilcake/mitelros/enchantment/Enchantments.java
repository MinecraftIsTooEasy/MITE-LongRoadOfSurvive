package net.oilcake.mitelros.enchantment;

import net.minecraft.Enchantment;
import net.minecraft.EnumRarity;
import net.xiaoyu233.fml.reload.event.EnchantmentRegistryEvent;
import net.xiaoyu233.fml.reload.utils.IdUtil;

public class Enchantments {
   public static final Enchantment enchantmentMelting = new EnchantmentMelting(IdUtil.getNextEnchantmentID(), EnumRarity.rare, 10);
   public static final Enchantment enchantmentAbsorb = new EnchantmentAbsorb(IdUtil.getNextEnchantmentID(), EnumRarity.rare, 10);
   public static final Enchantment enchantmentVanishing = new EnchantmentVanishing(IdUtil.getNextEnchantmentID(), EnumRarity.rare, 15);
   public static final Enchantment enchantmentCallofNether = new EnchantmentCallofNether(IdUtil.getNextEnchantmentID(), EnumRarity.uncommon, 10);
   public static final Enchantment enchantmentDestroying = new EnchantmentDestroying(IdUtil.getNextEnchantmentID(), EnumRarity.epic, 20);;
   public static final Enchantment enchantmentInfinity = new EnchantmentInfinity(IdUtil.getNextEnchantmentID(), EnumRarity.epic, 20);
   public static final Enchantment enchantmentArrogance = new EnchantmentArrogance(IdUtil.getNextEnchantmentID(), EnumRarity.rare, 15);
   public static final Enchantment enchantmentThresher = new EnchantmentThresher(IdUtil.getNextEnchantmentID(), EnumRarity.rare, 10);
   public static final Enchantment enchantmentSweeping = new EnchantmentSweeping(IdUtil.getNextEnchantmentID(), EnumRarity.rare, 10);
   public static final Enchantment enchantmentMending = new EnchantmentMending(IdUtil.getNextEnchantmentID(), EnchantmentLevel.Ultimate, 20);
   public static final Enchantment enchantmentCallofAntarctic = new EnchantmentCallOfAntarctic(IdUtil.getNextEnchantmentID(), EnumRarity.uncommon, 10);


   public static void registerEnchantments(EnchantmentRegistryEvent event){
      event.registerEnchantment(enchantmentMelting, enchantmentAbsorb, enchantmentVanishing, enchantmentDestroying, enchantmentCallofNether, enchantmentInfinity,
              enchantmentArrogance, enchantmentThresher, enchantmentSweeping, enchantmentMending, enchantmentCallofAntarctic);
   }

}
