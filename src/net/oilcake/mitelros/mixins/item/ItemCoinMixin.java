package net.oilcake.mitelros.mixins.item;

import net.minecraft.Block;
import net.minecraft.Item;
import net.minecraft.ItemCoin;
import net.minecraft.Material;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemCoin.class)
public class ItemCoinMixin extends Item {
    @Inject(method = "getExperienceValue", at = @At(value = "RETURN", ordinal = 3), cancellable = true)
    private void injectExperienceValue(CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        int exp = callbackInfoReturnable.getReturnValue();
        if(exp == 0){
            if (this.getExclusiveMaterial() == Materials.nickel) {
                callbackInfoReturnable.setReturnValue(50);
                callbackInfoReturnable.cancel();
            } else if (this.getExclusiveMaterial() == Materials.tungsten) {
                callbackInfoReturnable.setReturnValue(5000);
                callbackInfoReturnable.cancel();
            }
        }
    }
    @Inject(method = "getNuggetPeer", at = @At(value = "RETURN", ordinal = 3), cancellable = true)
    private void injectNewNuggerPeer(CallbackInfoReturnable<Item> callbackInfoReturnable) {
        Item item = callbackInfoReturnable.getReturnValue();
        if(item == null) {
            if (this.getExclusiveMaterial() == Materials.nickel) {
                callbackInfoReturnable.setReturnValue(Items.nickelNugget);
                callbackInfoReturnable.cancel();
            } else if (this.getExclusiveMaterial() == Materials.tungsten) {
                callbackInfoReturnable.setReturnValue(Items.tungstenNugget);
                callbackInfoReturnable.cancel();
            }
        }
    }
}
