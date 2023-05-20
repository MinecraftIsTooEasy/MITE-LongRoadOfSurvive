package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityWolf.class)
public class EntityWolfMixin extends EntityTameableAnimal {
    @Inject(method = "<init>",at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        this.targetTasks.addTask(4, new PathfinderGoalRandomTargetNonTamed(this, EntitySkeleton.class, 200, false));
    }
    @Overwrite
    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        int looting = damage_source.getLootingModifier();
        for(int i = 0; i < rand.nextInt(2 + looting); i++){
            this.dropItem(Items.Wolf_fur.itemID, 1);
        }
    }
    public EntityWolfMixin(World par1World) {
        super(par1World);
    }

    @Shadow
    public EntityAgeable createChild(EntityAgeable entityAgeable) {
        return null;
    }

    @Shadow
    protected int getTamingOutcome(EntityPlayer entityPlayer) {
        return 0;
    }
}
