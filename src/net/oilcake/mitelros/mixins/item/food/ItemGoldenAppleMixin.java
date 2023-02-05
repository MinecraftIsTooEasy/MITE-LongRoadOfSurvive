package net.oilcake.mitelros.mixins.item.food;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemGoldenApple.class)
public class ItemGoldenAppleMixin extends ItemFood {

//    @Inject(method = "<init>",at = @At("RETURN"))
//    private void injectCtor(CallbackInfo callback){
//        this.setWater(10);
//    }

    public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
        if (player.onServer()) {
            player.getFoodStats().addWater(-5);
        }
        super.onItemUseFinish(item_stack, world, player);
    }

}
