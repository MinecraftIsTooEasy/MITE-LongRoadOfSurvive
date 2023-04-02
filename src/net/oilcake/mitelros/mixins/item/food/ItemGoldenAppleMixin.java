package net.oilcake.mitelros.mixins.item.food;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ItemGoldenApple.class)
public class ItemGoldenAppleMixin extends ItemFood {

//    @Inject(method = "<init>",at = @At("RETURN"))
//    private void injectCtor(CallbackInfo callback){
//        this.setWater(10);
//    }
    @Overwrite
    protected void onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par1ItemStack.getItemSubtype() == 0) {
            if (!par2World.isRemote) {
                par3EntityPlayer.addPotionEffect(new MobEffect(MobEffectList.regeneration.id, 600, 0));
            }
        }
        if (par1ItemStack.getItemSubtype() > 0) {
            if (!par2World.isRemote) {
                par3EntityPlayer.addPotionEffect(new MobEffect(MobEffectList.regeneration.id, 900, 1));
                par3EntityPlayer.addPotionEffect(new MobEffect(MobEffectList.fireResistance.id, 900, 0));
            }
        } else {
            super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
        }

    }
    public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
        if (player.onServer()) {
            player.getFoodStats().addWater(-8);
        }
        super.onItemUseFinish(item_stack, world, player);
    }

}
