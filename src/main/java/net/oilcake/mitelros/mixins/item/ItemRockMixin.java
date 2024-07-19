package net.oilcake.mitelros.mixins.item;

import net.minecraft.EntityPlayer;
import net.minecraft.Item;
import net.minecraft.ItemRock;
import net.minecraft.ItemStack;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({ItemRock.class})
public class ItemRockMixin {
   @Overwrite
   public static int getExperienceValueWhenSacrificed(ItemStack item_stack) {
      Item item = item_stack.getItem();
      return item == Items.shardAzurite ? 5 : (item == Items.lapis ? 25 : (item == Item.netherQuartz ? 50 : (item == Item.emerald ? 250 : (item == Item.diamond ? 500 : 0))));
   }

   @Overwrite
   public static boolean onItemRightClick(EntityPlayer player, ItemStack item_stack, float partial_tick, boolean ctrl_is_down) {
      return false;
   }
}
