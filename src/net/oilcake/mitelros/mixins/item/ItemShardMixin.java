package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ItemShard.class)
public class ItemShardMixin extends Item {
    @Inject(at = @At("RETURN"), method = "<init>")
    private void injectXP(int id, Material material, CallbackInfo callbackInfo){
        if(material == Material.diamond){
            this.setXPReward(4);
        } else if (material == Material.emerald){
            this.setXPReward(3);
        } else if (material == Material.quartz){
            this.setXPReward(2);
        }
    }
}
