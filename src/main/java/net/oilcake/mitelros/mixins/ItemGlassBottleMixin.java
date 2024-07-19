package net.oilcake.mitelros.mixins;

import net.minecraft.Item;
import net.minecraft.ItemGlassBottle;
import net.minecraft.ItemStack;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({ItemGlassBottle.class})
public class ItemGlassBottleMixin extends Item {
   @Redirect(
      method = {"onItemRightClick(Lnet/minecraft/EntityPlayer;FZ)Z"},
      at = @At(
   value = "NEW",
   target = "(Lnet/minecraft/Item;II)Lnet/minecraft/ItemStack;",
   ordinal = 0
)
   )
   private ItemStack ctorResult(Item item, int stack_size, int sub) {
      return new ItemStack(Items.SuspiciousPotion, 1, 0);
   }
}
