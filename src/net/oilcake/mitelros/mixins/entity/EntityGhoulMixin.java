package net.oilcake.mitelros.mixins.entity;

import net.minecraft.EntityAnimalWatcher;
import net.minecraft.EntityGhoul;
import net.minecraft.GroupDataEntity;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityGhoul.class)
public class EntityGhoulMixin extends EntityAnimalWatcher {

    public EntityGhoulMixin(World world) {
        super(world);
    }
    @Inject(method = "onSpawnWithEgg", at = @At("HEAD"))
    private void injectCanPickUpLoot(CallbackInfoReturnable callbackInfo){
        this.setCanPickUpLoot(true);
    }
}
