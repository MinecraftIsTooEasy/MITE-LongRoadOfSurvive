package net.oilcake.mitelros.mixins.item.food;

import net.minecraft.EntityPlayer;
import net.minecraft.ItemAppleGold;
import net.minecraft.ItemFood;
import net.minecraft.ItemStack;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ItemAppleGold.class})
public class ItemGoldenAppleMixin extends ItemFood {
   @Inject(
      method = {"<init>(IIILjava/lang/String;)V"},
      at = {@At("RETURN")}
   )
   public void injectCtor(CallbackInfo callbackInfo) {
      this.setWater(-3);
      this.setPotionEffect("+0+1+2-3+13&4-4");
   }

   @Overwrite
   protected void onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
      if (par1ItemStack.getItemSubtype() == 0 && !par2World.isRemote) {
         par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.regeneration.id, 600, 0));
         par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 600, 1));
      }

      if (par1ItemStack.getItemSubtype() > 0) {
         if (!par2World.isRemote) {
            par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 1200, 3));
            par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.regeneration.id, 1200, 1));
            par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 1200, 0));
         }
      } else {
         super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
      }

   }

   public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
      super.onItemUseFinish(item_stack, world, player);
   }
}
