package net.oilcake.mitelros.mixins.item;

import java.util.List;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumChatFormatting;
import net.minecraft.Item;
import net.minecraft.ItemArrow;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.Slot;
import net.minecraft.Translator;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ItemArrow.class})
public class ItemArrowMixin extends Item {
   @Shadow
   @Final
   @Mutable
   public static Material[] material_types;
   @Shadow
   @Final
   public Material arrowhead_material;

   @Inject(
      method = {"<clinit>()V"},
      at = {@At("RETURN")}
   )
   private static void injectClinit(CallbackInfo callback) {
      material_types = new Material[]{Material.flint, Material.obsidian, Material.copper, Material.silver, Material.gold, Material.iron, Material.rusted_iron, Material.ancient_metal, Material.mithril, Material.adamantium, Materials.nickel, Materials.tungsten, Materials.magical};
   }

   @Overwrite
   public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
      if (extended_info) {
         info.add("");
         info.add(EnumChatFormatting.BLUE + Translator.getFormatted("item.tooltip.missileDamage", new Object[]{(int)this.getMaterialDamageVsEntity()}));
         info.add(EnumChatFormatting.GRAY + Translator.getFormatted("item.tooltip.missileRecovery", new Object[]{(int)(this.getChanceOfRecovery() * 100.0F)}));
         if (this.arrowhead_material == Materials.nickel) {
            info.add(EnumChatFormatting.LIGHT_GRAY + Translator.getFormatted("itemtool.tooltip.slimeresistance", new Object[0]));
         }
      }

   }

   @Shadow
   public float getChanceOfRecovery() {
      return 0.0F;
   }

   @Inject(
      method = {"getChanceOfRecovery()F"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void InjectChanceOfRecovery(CallbackInfoReturnable callbackInfo) {
      if (ReflectHelper.dyCast(this) == Items.arrowNickel) {
         callbackInfo.setReturnValue(0.7F);
      } else if (ReflectHelper.dyCast(this) == Items.arrowTungsten) {
         callbackInfo.setReturnValue(0.9F);
      } else if (ReflectHelper.dyCast(this) == Items.arrowMagical) {
         callbackInfo.setReturnValue(0.0F);
      }

   }

   @Shadow
   public float getMaterialDamageVsEntity() {
      return 1.0F;
   }
}
