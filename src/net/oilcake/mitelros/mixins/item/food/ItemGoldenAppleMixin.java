package net.oilcake.mitelros.mixins.item.food;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemGoldenApple.class)
public class ItemGoldenAppleMixin extends ItemFood {

//    @Inject(method = "<init>",at = @At("RETURN"))
//    private void injectCtor(CallbackInfo callback){
//        this.setWater(10);
//    }
    @Inject(method = "<init>",at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        this.setWater(-3);
        this.setPotionEffect("+0+1+2-3+13&4-4");
    }
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
        super.onItemUseFinish(item_stack, world, player);
    }

}
