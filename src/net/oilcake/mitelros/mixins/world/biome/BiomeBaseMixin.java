//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.oilcake.mitelros.mixins.world.biome;

import net.oilcake.mitelros.entity.EntityBoneBodyguard;
import net.oilcake.mitelros.entity.EntityMinerZombie;
import net.oilcake.mitelros.util.StuckTagConfig;
import net.oilcake.mitelros.world.biome.BiomeWindsweptPleatu;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.BiomeBase.*;
import static net.oilcake.mitelros.world.BiomeBases.windsweptpleatu;

@Mixin(BiomeBase.class)
public class BiomeBaseMixin{
    @Shadow protected List spawnableMonsterList;

    @Shadow @Final public int biomeID;

    @Inject(method = "<init>",at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        this.spawnableMonsterList.add(new BiomeMeta(EntityMinerZombie.class, (StuckTagConfig.TagConfig.TagFallenInMineLVL2.ConfigValue || StuckTagConfig.TagConfig.TagFallenInMineLVL1.ConfigValue) ? 50 : 20, 4, 4));
        this.spawnableMonsterList.add(new BiomeMeta(EntityBoneBodyguard.class, (StuckTagConfig.TagConfig.TagBattleSufferLVL2.ConfigValue || StuckTagConfig.TagConfig.TagBattleSufferLVL1.ConfigValue) ? 50 : 20,4,4));
        if(StuckTagConfig.TagConfig.TagApocalypse.ConfigValue){
            this.removeEntityFromSpawnableLists(EntityCow.class);
            this.removeEntityFromSpawnableLists(EntityChicken.class);
            this.removeEntityFromSpawnableLists(EntitySheep.class);
            this.removeEntityFromSpawnableLists(EntityPig.class);
            this.removeEntityFromSpawnableLists(EntityHorse.class);
        }
    }
    @Shadow
    public void removeEntityFromSpawnableLists(Class _class) {
    }

    @Overwrite
    public boolean isHillyOrMountainous() {
        return biomeID == extremeHills.biomeID || biomeID == iceMountains.biomeID || biomeID == desertHills.biomeID || biomeID == forestHills.biomeID || biomeID == taigaHills.biomeID || biomeID == extremeHillsEdge.biomeID || biomeID == jungleHills.biomeID || biomeID == windsweptpleatu.biomeID;
    }

}
