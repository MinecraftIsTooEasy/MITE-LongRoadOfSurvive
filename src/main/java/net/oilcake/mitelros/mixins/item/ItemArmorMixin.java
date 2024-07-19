package net.oilcake.mitelros.mixins.item;

import java.util.List;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumChatFormatting;
import net.minecraft.IDamageableItem;
import net.minecraft.Item;
import net.minecraft.ItemArmor;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.Slot;
import net.minecraft.Translator;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ItemArmor.class})
public abstract class ItemArmorMixin extends Item implements IDamageableItem {
   @Mutable
   @Shadow
   @Final
   private boolean is_leather;
   @Shadow
   private Material effective_material;
   @Shadow
   @Final
   private boolean is_chain_mail;

   @Inject(
      method = {"<init>(ILnet/minecraft/Material;IZ)V"},
      at = {@At("RETURN")}
   )
   public void injectCtor(CallbackInfo callbackInfo) {
      this.is_leather = this.effective_material == Material.leather || this.effective_material == Materials.wolf_fur;
      this.setCraftingDifficultyAsComponent(this.effective_material.durability * 100.0F * (float)this.getNumComponentsForDurability());
   }

   @Inject(
      method = {"addInformation(Lnet/minecraft/ItemStack;Lnet/minecraft/EntityPlayer;Ljava/util/List;ZLnet/minecraft/Slot;)V"},
      at = {@At("TAIL")}
   )
   private void InjectInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot, CallbackInfo callbackInfo) {
      if (extended_info && item_stack != null && item_stack.getMaterialForRepairs() == Materials.nickel) {
         info.add(EnumChatFormatting.LIGHT_GRAY + Translator.getFormatted("itemarmor.tooltip.slimeresistance", new Object[0]));
      }

   }

   @Inject(
      method = {"getMultipliedDurability()I"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void InjectFixDurability(CallbackInfoReturnable callbackInfo) {
      int a = (Integer)callbackInfo.getReturnValue();
      callbackInfo.setReturnValue(a <= 0 ? 1 : a);
      callbackInfo.cancel();
   }

   @Inject(
      method = {"getMaterialProtection()I"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void InjectNewProtection(CallbackInfoReturnable callbackInfo) {
      int protection = (Integer)callbackInfo.getReturnValue();
      if (protection == 0) {
         if (this.effective_material == Materials.wolf_fur) {
            protection = 7;
            if (this.is_chain_mail) {
               protection -= 2;
            }
         } else if (this.effective_material == Materials.vibranium) {
            protection = 6;
         } else if (this.effective_material == Materials.nickel) {
            protection = 8;
         } else if (this.effective_material != Materials.tungsten && this.effective_material != Materials.ancient_metal_sacred) {
            if (this.effective_material == Materials.uru) {
               protection = 10;
            }
         } else {
            protection = 9;
         }

         if (this.is_chain_mail) {
            protection -= 2;
         }
      }

      if (protection < 0) {
         protection = 0;
      }

      callbackInfo.setReturnValue(protection);
      callbackInfo.cancel();
   }

   @Inject(
      method = {"getMultipliedProtection(Lnet/minecraft/ItemStack;)F"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void InjectQualityEnhance(ItemStack item_stack, CallbackInfoReturnable callbackInfo) {
      float protection = (Float)callbackInfo.getReturnValue();
      if (item_stack != null) {
         protection *= 0.875F + (float)item_stack.getQuality().ordinal() * 0.0625F;
         callbackInfo.setReturnValue(protection);
         callbackInfo.cancel();
      }

   }

   @Overwrite
   public final float getDamageFactor(ItemStack item_stack, EntityLivingBase owner) {
      if (owner != null && !owner.isEntityPlayer()) {
         return (Boolean)StuckTagConfig.TagConfig.TagInstinctSurvival.ConfigValue ? 0.75F : 0.5F;
      } else if (owner instanceof EntityPlayer && item_stack.getMaxDamage() > 1 && item_stack.getItemDamage() >= item_stack.getMaxDamage() - 1) {
         return 0.0F;
      } else {
         float armor_damage_factor = (Boolean)StuckTagConfig.TagConfig.TagArmament.ConfigValue ? 4.0F - (float)item_stack.getItemDamage() / (float)item_stack.getItem().getMaxDamage(item_stack) * 4.0F : 2.0F - (float)item_stack.getItemDamage() / (float)item_stack.getItem().getMaxDamage(item_stack) * 2.0F;
         if (armor_damage_factor > 1.0F) {
            armor_damage_factor = 1.0F;
         }

         return armor_damage_factor;
      }
   }

   @Shadow
   public final float getProtectionAfterDamageFactor(ItemStack item_stack, EntityLivingBase owner) {
      return 1.0F;
   }
}
