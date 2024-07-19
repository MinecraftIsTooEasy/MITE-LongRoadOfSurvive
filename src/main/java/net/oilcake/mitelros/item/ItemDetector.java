package net.oilcake.mitelros.item;

import net.minecraft.CreativeTabs;
import net.minecraft.Item;
import net.minecraft.Material;

public class ItemDetector extends Item {
   public ItemDetector(int id, Material material, String texture) {
      super(id, material, texture);
      this.setMaxStackSize(1);
      this.setCraftingDifficultyAsComponent(800.0F);
      this.setCreativeTab(CreativeTabs.tabTools);
      this.addMaterial(new Material[]{Material.ancient_metal, Material.gold, Materials.crystal});
   }
}
