package net.oilcake.mitelros.mixins.item;

import java.util.List;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumChatFormatting;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.ItemTool;
import net.minecraft.Material;
import net.minecraft.Slot;
import net.minecraft.Translator;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ItemTool.class})
public class ItemToolMixin extends Item {
   @Shadow
   private Material effective_material;

   public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
      super.addInformation(item_stack, player, info, extended_info, slot);
      if (extended_info && item_stack != null && item_stack.getMaterialForRepairs() == Materials.nickel) {
         info.add(EnumChatFormatting.LIGHT_GRAY + Translator.getFormatted("itemtool.tooltip.slimeresistance", new Object[0]));
      }

   }

   @Inject(
      method = {"getMaterialHarvestEfficiency()F"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void InjectMaterialHarvestEfficiency(CallbackInfoReturnable callbackInfo) {
      if (this.effective_material == Materials.vibranium) {
         callbackInfo.setReturnValue(1.25F);
      } else if (this.effective_material == Materials.nickel) {
         callbackInfo.setReturnValue(2.0F);
      } else if (this.effective_material == Materials.tungsten) {
         callbackInfo.setReturnValue(2.75F);
      } else if (this.effective_material == Materials.uru) {
         callbackInfo.setReturnValue(3.0F);
      }

   }
}
