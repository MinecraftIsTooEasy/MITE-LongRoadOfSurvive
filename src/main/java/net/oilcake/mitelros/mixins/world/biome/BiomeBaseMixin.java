package net.oilcake.mitelros.mixins.world.biome;

import java.util.List;
import net.minecraft.BiomeGenBase;
import net.minecraft.EntityChicken;
import net.minecraft.EntityCow;
import net.minecraft.EntityHorse;
import net.minecraft.EntityPig;
import net.minecraft.EntitySheep;
import net.minecraft.SpawnListEntry;
import net.oilcake.mitelros.entity.EntityBoneBodyguard;
import net.oilcake.mitelros.entity.EntityRetinueZombie;
import net.oilcake.mitelros.entity.EntityUnknown;
import net.oilcake.mitelros.util.ExperimentalConfig;
import net.oilcake.mitelros.util.StuckTagConfig;
import net.oilcake.mitelros.world.BiomeBases;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({BiomeGenBase.class})
public class BiomeBaseMixin {
   @Shadow
   private boolean enableSnow;
   @Shadow
   private boolean enableRain;
   @Shadow
   protected List spawnableMonsterList;
   @Shadow
   protected List spawnableCreatureList;
   @Shadow
   @Final
   public int biomeID;
   @Shadow
   public float rainfall;

   public void RegenAnimals() {
      this.removeEntityFromSpawnableLists(EntityCow.class);
      this.removeEntityFromSpawnableLists(EntityChicken.class);
      this.removeEntityFromSpawnableLists(EntitySheep.class);
      this.removeEntityFromSpawnableLists(EntityPig.class);
      this.spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 5, 4, 6));
      this.spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 5, 4, 6));
      this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 5, 4, 6));
      this.spawnableCreatureList.add(new SpawnListEntry(EntityCow.class, 5, 4, 6));
      this.spawnableCreatureList.add(new SpawnListEntry(EntityUnknown.class, 110, 0, 0));
   }

   public void DisgenAnimals() {
      this.removeEntityFromSpawnableLists(EntityCow.class);
      this.removeEntityFromSpawnableLists(EntityChicken.class);
      this.removeEntityFromSpawnableLists(EntitySheep.class);
      this.removeEntityFromSpawnableLists(EntityPig.class);
      this.removeEntityFromSpawnableLists(EntityHorse.class);
   }

   @Inject(
      method = {"<init>(I)V"},
      at = {@At("RETURN")}
   )
   public void injectCtor(CallbackInfo callbackInfo) {
      this.spawnableMonsterList.add(new SpawnListEntry(EntityRetinueZombie.class, !(Boolean)StuckTagConfig.TagConfig.TagFallenInMineLVL2.ConfigValue && !(Boolean)StuckTagConfig.TagConfig.TagFallenInMineLVL1.ConfigValue ? 10 : 35, 4, 4));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityBoneBodyguard.class, !(Boolean)StuckTagConfig.TagConfig.TagBattleSufferLVL2.ConfigValue && !(Boolean)StuckTagConfig.TagConfig.TagBattleSufferLVL1.ConfigValue ? 10 : 35, 4, 4));
      if ((Boolean)ExperimentalConfig.TagConfig.TagCreaturesV2.ConfigValue) {
         this.RegenAnimals();
      }

      if ((Boolean)StuckTagConfig.TagConfig.TagApocalypse.ConfigValue) {
         this.DisgenAnimals();
      }

   }

   @Shadow
   public void removeEntityFromSpawnableLists(Class _class) {
   }

   @Overwrite
   public boolean isHillyOrMountainous() {
      return this.biomeID == BiomeGenBase.extremeHills.biomeID || this.biomeID == BiomeGenBase.iceMountains.biomeID || this.biomeID == BiomeGenBase.desertHills.biomeID || this.biomeID == BiomeGenBase.forestHills.biomeID || this.biomeID == BiomeGenBase.taigaHills.biomeID || this.biomeID == BiomeGenBase.extremeHillsEdge.biomeID || this.biomeID == BiomeGenBase.jungleHills.biomeID || this.biomeID == BiomeBases.windsweptpleatu.biomeID;
   }

   @Overwrite
   public boolean canSpawnLightningBolt(boolean is_blood_moon) {
      return this.enableSnow ? false : this.enableRain && this.rainfall != 0.0F || is_blood_moon;
   }
}
