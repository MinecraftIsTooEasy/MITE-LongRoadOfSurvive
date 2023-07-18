//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.oilcake.mitelros.mixins.world.biome;

import net.oilcake.mitelros.entity.EntityBoneBodyguard;
import net.oilcake.mitelros.entity.EntityMinerZombie;
import net.oilcake.mitelros.util.ExperimentalConfig;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

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
    @Shadow
    private boolean enableSnow;
    @Shadow
    private boolean enableRain;
    @Shadow
    protected List spawnableMonsterList;
    @Shadow
    protected List spawnableCreatureList;

    @Shadow @Final public int biomeID;
    public void RegenAnimals(){
        this.removeEntityFromSpawnableLists(EntityCow.class);
        this.removeEntityFromSpawnableLists(EntityChicken.class);
        this.removeEntityFromSpawnableLists(EntitySheep.class);
        this.removeEntityFromSpawnableLists(EntityPig.class);
        this.spawnableCreatureList.add(new BiomeMeta(EntitySheep.class, 5, 4, 8));
        this.spawnableCreatureList.add(new BiomeMeta(EntityPig.class, 5, 4, 8));
        this.spawnableCreatureList.add(new BiomeMeta(EntityChicken.class, 5, 4, 8));
        this.spawnableCreatureList.add(new BiomeMeta(EntityCow.class, 5, 4, 8));
        this.spawnableCreatureList.add(new BiomeMeta(EntityBoneBodyguard.class, 80, 0, 0));
    }
    public void DisgenAnimals(){
        this.removeEntityFromSpawnableLists(EntityCow.class);
        this.removeEntityFromSpawnableLists(EntityChicken.class);
        this.removeEntityFromSpawnableLists(EntitySheep.class);
        this.removeEntityFromSpawnableLists(EntityPig.class);
        this.removeEntityFromSpawnableLists(EntityHorse.class);
    }

    @Inject(method = "<init>",at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        this.spawnableMonsterList.add(new BiomeMeta(EntityMinerZombie.class, (StuckTagConfig.TagConfig.TagFallenInMineLVL2.ConfigValue || StuckTagConfig.TagConfig.TagFallenInMineLVL1.ConfigValue) ? 35 : 10, 4, 4));
        this.spawnableMonsterList.add(new BiomeMeta(EntityBoneBodyguard.class, (StuckTagConfig.TagConfig.TagBattleSufferLVL2.ConfigValue || StuckTagConfig.TagConfig.TagBattleSufferLVL1.ConfigValue) ? 35 : 10,4,4));
        if(ExperimentalConfig.TagConfig.TagCreaturesV2.ConfigValue){
            this.RegenAnimals();
        }
        if(StuckTagConfig.TagConfig.TagApocalypse.ConfigValue){
            this.DisgenAnimals();
        }
    }
    @Shadow
    public void removeEntityFromSpawnableLists(Class _class) {
    }

    @Shadow
    public float rainfall;

    @Overwrite
    public boolean isHillyOrMountainous() {
        return biomeID == extremeHills.biomeID || biomeID == iceMountains.biomeID || biomeID == desertHills.biomeID || biomeID == forestHills.biomeID || biomeID == taigaHills.biomeID || biomeID == extremeHillsEdge.biomeID || biomeID == jungleHills.biomeID || biomeID == windsweptpleatu.biomeID;
    }
    @Overwrite
    public boolean canSpawnLightningBolt(boolean is_blood_moon) {
        return this.enableSnow ? false : (this.enableRain && this.rainfall != 0.0F) || is_blood_moon;
    }

}
