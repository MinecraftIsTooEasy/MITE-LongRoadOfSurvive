package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.util.ExperimentalConfig;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(ItemMeat.class)
public class ItemMeatMixin extends ItemFood {
//    @Inject(method = "<init>", at = @At("RETURN"))
//    private void injectInit(CallbackInfo callbackInfo){
//        this.setWater(-1);
//    }
    @Shadow public boolean is_cooked;

    public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
        if (player.onServer()) {
            Random rand = new Random();;
            int outcome = rand.nextInt(!ExperimentalConfig.TagConfig.Realistic.ConfigValue ? 2 : 4);
            if(!this.is_cooked){
                if(outcome == 0){
                    player.addPotionEffect(new MobEffect(PotionExtend.dehydration.id,600,0));
                }
            }else {
                player.addPotionEffect(new MobEffect(PotionExtend.thirsty.id,1280,0));
            }
        }
        super.onItemUseFinish(item_stack, world, player);
    }
}
