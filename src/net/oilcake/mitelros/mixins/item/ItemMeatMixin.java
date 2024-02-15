package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.util.ExperimentalConfig;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

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
            int outcome = rand.nextInt(ExperimentalConfig.TagConfig.Realistic.ConfigValue ? 1 : 2);
            if(!this.is_cooked){
                if(outcome == (StuckTagConfig.TagConfig.TagDigest.ConfigValue ? 4 : 0)){
                    player.addPotionEffect(new MobEffect(PotionExtend.dehydration.id, (int) (240 * (1 + rand.nextDouble())),0));
                }
            }else {
                player.addPotionEffect(new MobEffect(PotionExtend.thirsty.id,1280,0));
            }
        }
        super.onItemUseFinish(item_stack, world, player);
    }
}
