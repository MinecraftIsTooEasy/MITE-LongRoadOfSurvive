package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityWitherBoneLord;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityZombie.class)
public class EntityZombieMixin{
    @Inject(method = "<init>",at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        this.is_smart = true;
        this.rare_drops_villager = new Item[]{Item.seeds, Item.pumpkinSeeds, Item.melonSeeds, Item.carrot, Item.potato, Item.onion, Items.seedsBeetroot};
    }
    @Shadow
    private boolean is_smart;
    @Shadow
    Item[] rare_drops_villager;
}

