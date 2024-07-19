package net.oilcake.mitelros.mixins.item.food;

import java.util.Random;
import net.minecraft.EntityPlayer;
import net.minecraft.Item;
import net.minecraft.ItemFood;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.minecraft.World;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ItemFood.class})
public class ItemFoodMixin extends Item {
   @Inject(
      method = {"<init>(ILnet/minecraft/Material;IIZZZLjava/lang/String;)V"},
      at = {@At("RETURN")}
   )
   private void injectInit(int id, Material material, int satiation, int nutrition, boolean has_protein, boolean has_essential_fats, boolean has_phytonutrients, String texture, CallbackInfo callbackInfo) {
      this.setWater(this.resetWaterVal(id, material));
   }

   @Inject(
      method = {"<init>(ILnet/minecraft/Material;IIIZZZLjava/lang/String;)V"},
      at = {@At("RETURN")}
   )
   private void injectInit(int id, Material material, int satiation, int nutrition, int sugar_content, boolean has_protein, boolean has_essential_fats, boolean has_phytonutrients, String texture, CallbackInfo callbackInfo) {
      this.setWater(this.resetWaterVal(id, material));
   }

   public int resetWaterVal(int id, Material material) {
      if (material == Material.fruit) {
         return (Boolean)StuckTagConfig.TagConfig.TagDryDilemma.ConfigValue ? 1 : 2;
      } else if (id == 135) {
         return (Boolean)StuckTagConfig.TagConfig.TagDryDilemma.ConfigValue ? 1 : 2;
      } else if (material == Materials.glowberries) {
         return 1;
      } else if (material != Material.cheese && id != 88) {
         if (material != Material.bread && material != Material.desert) {
            return material == Materials.agave ? 1 : 0;
         } else {
            return -1;
         }
      } else {
         return -1;
      }
   }

   @Shadow
   protected void onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
   }

   public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
      if (player.onServer()) {
         if (this.itemID == rottenFlesh.itemID) {
            player.addPotionEffect(new PotionEffect(Potion.confusion.id, 600, 0));
         }

         if (this.hasMaterial(Material.bread) || this.hasMaterial(Material.desert)) {
            player.addPotionEffect(new PotionEffect(PotionExtend.thirsty.id, 1280, 0));
         }

         Random rand;
         if (this.hasMaterial(Materials.glowberries)) {
            rand = new Random();
            if (rand.nextDouble() > ((Boolean)StuckTagConfig.TagConfig.TagDryDilemma.ConfigValue ? 0.5 : 1.0)) {
               player.addWater(-1);
            }
         }

         if (this.hasMaterial(Materials.agave)) {
            rand = new Random();
            if (rand.nextDouble() > ((Boolean)StuckTagConfig.TagConfig.TagDryDilemma.ConfigValue ? 0.2 : 0.4)) {
               player.addWater(-1);
            }
         }

         if (this.itemID == Item.egg.itemID) {
            rand = new Random();
            if (rand.nextDouble() > ((Boolean)StuckTagConfig.TagConfig.TagDryDilemma.ConfigValue ? 0.5 : 0.25)) {
               player.addWater(1);
            }
         }

         player.addFoodValue(this);
         world.playSoundAtEntity(player, "random.burp", 0.5F, player.getRand().nextFloat() * 0.1F + 0.9F);
         this.onEaten(item_stack, world, player);
      }

      super.onItemUseFinish(item_stack, world, player);
   }
}
