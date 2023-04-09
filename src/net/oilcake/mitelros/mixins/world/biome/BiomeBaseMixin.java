//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.oilcake.mitelros.mixins.world.biome;

import net.oilcake.mitelros.util.StuckTagConfig;
import net.oilcake.mitelros.world.biome.BiomeWindsweptPleatu;
import org.spongepowered.asm.mixin.Mixin;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.oilcake.mitelros.world.BiomeBases.windsweptpleatu;

@Mixin(BiomeBase.class)
public class BiomeBaseMixin extends BiomeBase {
    public BiomeBaseMixin() {
        super(1562);
    }
//    @Inject(method = "<init>",at = @At("RETURN"))
//    public void injectCtor(CallbackInfo callbackInfo) {
//        if(StuckTagConfig.TagConfig.TagApocalypse.ConfigValue){
//            this.spawnableCreatureList.clear();
//        }
//    }
    @Overwrite
    public boolean isHillyOrMountainous() {
        return this == extremeHills || this == iceMountains || this == desertHills || this == forestHills || this == taigaHills || this == extremeHillsEdge || this == jungleHills || this == windsweptpleatu;
    }

}
