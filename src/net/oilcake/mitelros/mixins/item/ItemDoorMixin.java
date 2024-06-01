package net.oilcake.mitelros.mixins.item;

import net.minecraft.Block;
import net.minecraft.ItemDoor;
import net.minecraft.Material;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemDoor.class)
public class ItemDoorMixin {
    @Inject(method = "getBlock", at = @At(ordinal = 8,value = "RETURN"),cancellable = true)
    private void injectNewDoor(CallbackInfoReturnable<Block> callbackInfoReturnable) {
        Block block = callbackInfoReturnable.getReturnValue();
        if(block == null){
            if (this.door_material == Materials.nickel) {
                callbackInfoReturnable.setReturnValue(Blocks.doorNickel);
            } else if (this.door_material == Materials.tungsten) {
                callbackInfoReturnable.setReturnValue(Blocks.doorTungsten);
            }
        }else {
            callbackInfoReturnable.setReturnValue(null);
        }
    }
    @Shadow
    private Material door_material;
}