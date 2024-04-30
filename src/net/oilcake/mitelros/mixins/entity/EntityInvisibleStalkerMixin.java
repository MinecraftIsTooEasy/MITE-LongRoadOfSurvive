package net.oilcake.mitelros.mixins.entity;

import net.minecraft.EntityInvisibleStalker;
import net.minecraft.EntityMonster;
import net.minecraft.GroupDataEntity;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityInvisibleStalker.class)
public class EntityInvisibleStalkerMixin extends EntityMonster {
    public EntityInvisibleStalkerMixin(World world) {
        super(world);
    }
    @Inject(method = "onSpawnWithEgg", at = @At("HEAD"))
    private void injectCanPickUpLoot(CallbackInfoReturnable callbackInfo){
        this.setCanPickUpLoot(true);
    }
}
