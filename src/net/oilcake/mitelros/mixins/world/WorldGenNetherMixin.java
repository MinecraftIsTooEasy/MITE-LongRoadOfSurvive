package net.oilcake.mitelros.mixins.world;

import net.minecraft.BiomeMeta;
import net.minecraft.StructureGenerator;
import net.minecraft.WorldGenNether;
import net.oilcake.mitelros.entity.EntityWitherBoneLord;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(WorldGenNether.class)
public abstract class WorldGenNetherMixin extends StructureGenerator {
    @Inject(method = "<init>",at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        this.spawnList.add(new BiomeMeta(EntityWitherBoneLord.class, 5, 1, 1));
    }
    @Shadow
    private List spawnList;
}
