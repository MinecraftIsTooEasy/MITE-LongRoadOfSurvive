package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityZombie.class)
public class EntityZombieMixin extends EntityAnimalWatcher {
    public EntityZombieMixin(World world) {
        super(world);
    }

    @Inject(method = "<init>",at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        this.is_smart = true;
        this.rare_drops_villager = new Item[]{Item.seeds, Item.pumpkinSeeds, Item.melonSeeds, Item.carrot, Item.potato, Item.onion, Items.seedsBeetroot};
    }
    @Inject(method = "onUpdate",at = @At("RETURN"))
    public void ModifyAIInjector(CallbackInfo callbackInfo){
        if(StuckTagConfig.TagConfig.TagWorshipDark.ConfigValue){
            this.tryDisableNearbyLightSource();
        }
    }
    @Inject(method = "onSpawnWithEgg",at = @At("RETURN"),cancellable = true)
    public void ModifyAIInjector(GroupDataEntity par1EntityLivingData, CallbackInfoReturnable<GroupDataEntity> callbackInfo) {
        if(StuckTagConfig.TagConfig.TagWorshipDark.ConfigValue){
            this.tasks.addTask(4, new EntityAISeekLitTorch(this, 1.0F));
        }
    }

    @Shadow
    private boolean is_smart;
    @Shadow
    Item[] rare_drops_villager;
}

