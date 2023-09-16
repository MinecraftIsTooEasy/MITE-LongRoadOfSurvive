package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(ItemMeat.class)
public class ItemMeatMixin extends ItemFood {
    @Shadow public boolean is_cooked;

    public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
        if (player.onServer()) {
            if(!this.is_cooked){
                Random rand;
                rand = new Random();
                if(rand.nextInt(StuckTagConfig.TagConfig.TagDigest.ConfigValue ? 1 : 2) != 0){
                    player.addPotionEffect(new MobEffect(MobEffectList.hunger.id,600,0));
                }
            }
        }
        super.onItemUseFinish(item_stack, world, player);
    }
}
