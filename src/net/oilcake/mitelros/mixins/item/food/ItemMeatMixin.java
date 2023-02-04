package net.oilcake.mitelros.mixins.item.food;

import net.minecraft.ItemFood;
import net.minecraft.ItemMeat;
import net.minecraft.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemMeat.class)
public class ItemMeatMixin extends ItemFood {
    @Inject(method = "<init>",at = @At("RETURN"))
    private void injectCtor(CallbackInfo callback){
        this.setWater(1);
    }
}
