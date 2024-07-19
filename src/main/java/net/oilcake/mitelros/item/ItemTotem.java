package net.oilcake.mitelros.item;

import net.minecraft.CreativeTabs;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumEntityFX;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.Minecraft;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.oilcake.mitelros.achivements.AchievementExtend;

public class ItemTotem extends Item {
   public ItemTotem(int id, Material material, String texture) {
      super(id, material, texture);
      this.setMaxStackSize(16);
      this.setCraftingDifficultyAsComponent(100.0F);
      this.setCreativeTab(CreativeTabs.tabTools);
   }

   private void performEffectCommon(EntityPlayer player, ItemTotem totem) {
      player.clearActivePotions();
      player.setHealth(Math.max(player.getHealth(), 2.0F), true, player.getHealFX());
      player.makeSound("imported.random.totem_use", 3.0F, 1.0F + player.getRand().nextFloat() * 0.1F);
      player.addPotionEffect(new PotionEffect(Potion.blindness.id, 40, 4));
      player.vision_dimming = 1.0F;
   }

   private void performEffectSpecified(EntityPlayer player, ItemTotem totem) {
      Material totem_material = totem.getHardestMetalMaterial();
      int i;
      if (totem_material == Material.gold) {
         player.setHealth(player.getMaxHealth(), true, player.getHealFX());

         for(i = 0; i < 8; ++i) {
            player.entityFX(EnumEntityFX.heal);
         }
      } else if (totem_material == Material.ancient_metal) {
         for(i = 0; i < 8; ++i) {
            player.entityFX(EnumEntityFX.curse_effect_learned);
         }

         player.addExperience(Math.min(player.experience / 5, 30000));
      } else if (totem_material == Material.iron) {
         for(i = 0; i < 8; ++i) {
            player.entityFX(EnumEntityFX.smoke_and_steam);
         }

         player.addPotionEffect(new PotionEffect(Potion.resistance.id, 400, (int)((1.0F - player.getHealthFraction()) * 4.0F)));
      } else if (totem_material == Materials.nickel) {
         for(i = 0; i < 8; ++i) {
            player.entityFX(EnumEntityFX.summoned);
         }

         player.setHunt_counter(400);
         player.hunt_cap = true;
         player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 400, (int)((1.0F - player.getHealthFraction()) * 2.0F)));
      } else if (totem_material == Materials.tungsten) {
         float delta = player.getHealthFraction() - 0.5F;

         for(int i = 0; i < 8; ++i) {
            player.entityFX(EnumEntityFX.smoke);
         }

         player.worldObj.createExplosion(player, player.posX, player.posY + 1.5, player.posZ, 0.0F, 4.0F - 4.0F * delta, true);
         player.setHealth(player.getMaxHealth() / 2.0F, true, player.getHealFX());
      } else {
         Minecraft.setErrorMessage("effectSpecified(): Undefined Material " + totem_material.toString() + ".");
      }

   }

   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
      ItemStack totem = player.getHeldItemStack();
      if (totem.getItem() instanceof ItemTotem) {
         this.performEffectCommon(player, (ItemTotem)totem.getItem());
         this.performEffectSpecified(player, (ItemTotem)totem.getItem());
         player.convertOneOfHeldItem((ItemStack)null);
         return true;
      } else {
         return false;
      }
   }

   public void performNegativeEffect(EntityPlayer player) {
      ItemStack totem = player.getHeldItemStack();
      if (totem.getItem() instanceof ItemTotem) {
         this.performEffectCommon(player, (ItemTotem)totem.getItem());
         this.performEffectSpecified(player, (ItemTotem)totem.getItem());
         player.triggerAchievement(AchievementExtend.cheatdeath);
         player.convertOneOfHeldItem((ItemStack)null);
      }

   }
}
