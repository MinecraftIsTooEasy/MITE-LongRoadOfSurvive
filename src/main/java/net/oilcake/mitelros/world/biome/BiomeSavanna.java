package net.oilcake.mitelros.world.biome;

import java.util.Random;
import net.minecraft.BiomeGenBase;
import net.minecraft.Block;
import net.minecraft.EntityHorse;
import net.minecraft.SpawnListEntry;
import net.minecraft.WorldGenerator;
import net.oilcake.mitelros.util.StuckTagConfig;

public class BiomeSavanna extends BiomeGenBase {
   private boolean enableRain;

   public BiomeSavanna(int par1) {
      super(par1);
      this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 1, 2));
      this.theBiomeDecorator.setTreesPerChunk(0);
      this.theBiomeDecorator.setFlowersPerChunk(3);
      this.theBiomeDecorator.setGrassPerChunk(10);
      this.theBiomeDecorator.setFlowersExtendPerChunk(1);
      this.setBiomeName("Savanna");
      this.setColor(16421912);
      this.topBlock = (byte)Block.grass.blockID;
      this.fillerBlock = (byte)Block.dirt.blockID;
      this.setMinMaxHeight(0.1F, 0.4F);
      this.setTemperatureRainfall(1.6F, 0.0F);
      this.setDisableRain();
      if ((Boolean)StuckTagConfig.TagConfig.TagApocalypse.ConfigValue) {
         this.removeEntityFromSpawnableLists(EntityHorse.class);
      }

   }

   public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
      return (WorldGenerator)(par1Random.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.worldGeneratorTrees);
   }

   private BiomeGenBase setMinMaxHeight(float par1, float par2) {
      this.minHeight = par1;
      this.maxHeight = par2;
      return this;
   }

   private BiomeGenBase setTemperatureRainfall(float par1, float par2) {
      if (par1 > 0.1F && par1 < 0.2F) {
         throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
      } else {
         this.temperature = par1;
         this.rainfall = par2;
         return this;
      }
   }

   private BiomeGenBase setDisableRain() {
      this.enableRain = false;
      return this;
   }
}
