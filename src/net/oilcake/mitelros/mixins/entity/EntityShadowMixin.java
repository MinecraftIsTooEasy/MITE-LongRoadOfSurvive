package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityShadow.class)
public class EntityShadowMixin extends EntityMonster{
    public EntityShadowMixin(World par1World) {
        super(par1World);
    }
    private boolean cursed_player = false;
    @Inject(method = "onSpawnWithEgg", at = @At("HEAD"))
    private void injectCanPickUpLoot(CallbackInfoReturnable callbackInfo){
        this.setCanPickUpLoot(true);
    }
    @Inject(method = "onLivingUpdate", at = @At("RETURN"))
    private void injectSpecialAttribute(CallbackInfo callbackInfo){
        if(this.getTarget()!=null && !this.cursed_player && StuckTagConfig.TagConfig.TagPseudovision.ConfigValue){
            Entity target = this.getTarget();
            if(target instanceof EntityPlayer){
                this.cursed_player = true;
                target.getAsPlayer().vision_dimming += target.getAsEntityLivingBase().getAmountAfterResistance(2.0F, 4);
            }
        }
    }
}
