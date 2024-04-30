package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemTool.class)
public class ItemToolMixin extends Item{

    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        super.addInformation(item_stack, player, info, extended_info, slot);
        if(extended_info) {
            if (item_stack != null && item_stack.getMaterialForRepairs() == Materials.nickel) {
                info.add(EnumChatFormat.LIGHT_GRAY + Translator.getFormatted("itemtool.tooltip.slimeresistance"));
            }
        }
    }
    @Inject(method = "getMaterialHarvestEfficiency", at = @At(value = "HEAD"),cancellable = true)
    private void InjectMaterialHarvestEfficiency(CallbackInfoReturnable<Float> callbackInfo){
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
    @Shadow
    private Material effective_material;

}
