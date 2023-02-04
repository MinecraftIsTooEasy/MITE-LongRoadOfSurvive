package net.oilcake.mitelros.mixins.item.food;

import net.minecraft.Item;
import net.minecraft.ItemFood;
import net.minecraft.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemFood.class)
public class ItemFoodMixin extends Item {
    @Inject(method = "<init>",at = @At("RETURN"))
    private void injectCtor(CallbackInfo callback){
        if (this.hasMaterial(Material.fruit)) {
            this.setWater(2);
        }else if(this.hasMaterial(Material.bread)){
            this.setWater(-2);
        }else if(this.hasMaterial(Material.desert)){
            this.setWater(-2);
        }else{
            this.setWater(0);
        }
    }
}
