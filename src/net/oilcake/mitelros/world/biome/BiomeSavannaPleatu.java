package net.oilcake.mitelros.world.biome;

import net.minecraft.BiomeBase;
import net.minecraft.Block;
import net.minecraft.WorldGenerator;

import java.util.Random;

public class BiomeSavannaPleatu extends BiomeBase{
    private boolean enableRain;
    public BiomeSavannaPleatu(int par1) {
        super(par1);
        this.theBiomeDecorator.setTreesPerChunk(1);
        this.theBiomeDecorator.setFlowersPerChunk(3);
        this.theBiomeDecorator.setGrassPerChunk(10);
        this.theBiomeDecorator.setFlowersExtendPerChunk(1);
        this.setBiomeName("Savanna");
        this.setColor(16421912);
        this.topBlock = (byte) Block.grass.blockID;
        this.fillerBlock = (byte)Block.dirt.blockID;
        this.setMinMaxHeight(0.9F, 1.5F);
        this.setTemperatureRainfall(1.6F, 0.0F);
        this.setDisableRain();
    }
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
        return (WorldGenerator)(par1Random.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.worldGeneratorTrees);
    }
    private BiomeBase setMinMaxHeight(float par1, float par2) {
        this.minHeight = par1;
        this.maxHeight = par2;
        return this;
    }
    private BiomeBase setTemperatureRainfall(float par1, float par2) {
        if (par1 > 0.1F && par1 < 0.2F) {
            throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
        } else {
            this.temperature = par1;
            this.rainfall = par2;
            return this;
        }
    }
    private BiomeBase setDisableRain() {
        this.enableRain = false;
        return this;
    }
}
