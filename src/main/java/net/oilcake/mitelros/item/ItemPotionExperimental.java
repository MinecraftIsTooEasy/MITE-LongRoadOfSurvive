package net.oilcake.mitelros.item;

import net.minecraft.CreativeTabs;
import net.minecraft.EntityPlayer;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.minecraft.World;

public class ItemPotionExperimental extends Item {
   public ItemPotionExperimental(int id) {
      super(id, Material.glass, "experimental_potion");
      this.setMaxStackSize(1);
      this.setCraftingDifficultyAsComponent(25.0F);
      this.setCreativeTab(CreativeTabs.tabMisc);
      this.setWater(3);
   }

   public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
      double rand = Math.random();
      if (player.onServer()) {
         if (rand < 0.25) {
            player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 800, 0));
         }

         if (rand > 0.15 && rand < 0.7) {
            player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 800, 0));
         }

         if (rand > 0.4 && rand < 0.95) {
            player.addPotionEffect(new PotionEffect(Potion.resistance.id, 800, 0));
         }

         if (rand > 0.9) {
            player.addPotionEffect(new PotionEffect(Potion.poison.id, 400, 0));
         }

         player.addWater(this.getWater());
      }

      super.onItemUseFinish(item_stack, world, player);
   }

   public int getMaxItemUseDuration(ItemStack par1ItemStack) {
      return 32;
   }

   public boolean isDrinkable(int item_subtype) {
      return true;
   }

   public Item getItemProducedOnItemUseFinish() {
      return glassBottle;
   }
}
