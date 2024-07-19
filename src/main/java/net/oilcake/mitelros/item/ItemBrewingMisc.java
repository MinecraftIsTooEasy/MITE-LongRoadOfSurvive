package net.oilcake.mitelros.item;

import net.minecraft.CreativeTabs;
import net.minecraft.Item;
import net.minecraft.Material;

public class ItemBrewingMisc extends Item {
   public ItemBrewingMisc(int id, Material material, String texture) {
      super(id, material, texture);
      this.setMaxStackSize(16);
      this.setCraftingDifficultyAsComponent(100.0F);
      this.setCreativeTab(CreativeTabs.tabMaterials);
   }

   public ItemBrewingMisc setPotionEffectExtend(String par1Str) {
      this.setPotionEffect(par1Str);
      return this;
   }
}
