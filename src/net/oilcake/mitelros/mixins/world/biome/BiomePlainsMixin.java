package net.oilcake.mitelros.mixins.world.biome;

import net.minecraft.BiomeBase;
import net.minecraft.BiomePlains;
import net.minecraft.EntityHorse;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BiomePlains.class)
public class BiomePlainsMixin extends BiomeBase {
    protected BiomePlainsMixin(int par1) {
        super(par1);
    }
    @Inject(method = "<init>",at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        if(StuckTagConfig.TagConfig.TagApocalypse.ConfigValue){
            this.removeEntityFromSpawnableLists(EntityHorse.class);
        }
    }
}
