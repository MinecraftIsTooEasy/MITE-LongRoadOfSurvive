package net.oilcake.mitelros.mixins.item;

import java.util.Iterator;
import java.util.List;
import net.minecraft.EntityPlayer;
import net.minecraft.Item;
import net.minecraft.ItemPotion;
import net.minecraft.ItemStack;
import net.minecraft.PotionEffect;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ItemPotion.class})
public class ItemPotionMixin extends Item {
   @Inject(
      method = {"<init>(I)V"},
      at = {@At("RETURN")}
   )
   private void injectCtor(CallbackInfo callback) {
      this.setWater(3);
   }

   @Shadow
   public List getEffects(ItemStack par1ItemStack) {
      return null;
   }

   @Overwrite
   public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
      if (player.onServer()) {
         List effects = this.getEffects(item_stack);
         if (effects != null) {
            Iterator i = effects.iterator();

            while(i.hasNext()) {
               player.addPotionEffect(new PotionEffect((PotionEffect)i.next()));
            }
         }

         player.addWater(this.getWater());
      }

      super.onItemUseFinish(item_stack, world, player);
   }
}
