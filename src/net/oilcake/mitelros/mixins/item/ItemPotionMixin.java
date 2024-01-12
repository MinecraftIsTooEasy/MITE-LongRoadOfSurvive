package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.List;
@Mixin(ItemPotion.class)
public class ItemPotionMixin extends Item {
    @Inject(method = "<init>",at = @At("RETURN"))
    private void injectCtor(CallbackInfo callback){
        this.setWater(3);
    }
    @Shadow
    public List getEffects(ItemStack par1ItemStack) {
        return null;
    }
    @Overwrite
    public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
        if (player.onServer()) {
            List effects = this.getEffects(item_stack);
            if (effects != null) {
                Iterator i = effects.iterator();
                while(i.hasNext()) {
                    player.addPotionEffect(new MobEffect((MobEffect)i.next()));
                }
            }
            player.addWater(this.getWater());
        }
        super.onItemUseFinish(item_stack, world, player);
    }
}
