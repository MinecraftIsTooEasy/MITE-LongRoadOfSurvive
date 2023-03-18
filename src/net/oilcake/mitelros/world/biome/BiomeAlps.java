package net.oilcake.mitelros.world.biome;

import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityStray;

import java.util.Random;

public class BiomeAlps extends BiomeBase {
    public BiomeAlps(int par1) {
        super(par1);
        this.spawnableCreatureList.clear();
        this.removeEntityFromSpawnableLists(EntitySkeleton.class);
        this.spawnableMonsterList.add(new BiomeMeta(EntityStray.class, 100, 1, 4));
        this.topBlock = (byte)Block.grass.blockID;
        this.fillerBlock = (byte)Block.cobblestone.blockID;
        this.setColor(10526880);
        this.setBiomeName("Alps");
        this.setEnableSnow();
        this.setMinMaxHeight(0.9F, 4.2F);
        this.setTemperatureRainfall(0.0F, 0.5F);
    }

    public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
        return par1Random.nextInt(3) == 0 ? new WorldGenTaiga1() : new WorldGenTaiga2(false);
    }

    public void decorate(World par1World, Random par2Random, int par3, int par4) {
        super.decorate(par1World, par2Random, par3, par4);
        int var5 = 3 + par2Random.nextInt(6);
        for (int var6 = 0; var6 < var5; ++var6) {
            int count = par3 + par2Random.nextInt(4);
            int temp = par2Random.nextInt(255);
            int x = par4 + par2Random.nextInt(16);
            int y = par1World.getBlockId(count, temp, x);
            if (y == Block.stone.blockID) {
                par1World.setBlock(count, temp, x, Block.oreEmerald.blockID, 0, 2);
            }
        }
        for (int var6 = 0; var6 < var5; ++var6) {
            int count = par3 + par2Random.nextInt(4);
            int temp = par2Random.nextInt(128);
            int x = par4 + par2Random.nextInt(16);
            int y = par1World.getBlockId(count, temp, x);
            if (y == Block.stone.blockID) {
                par1World.setBlock(count, temp, x, Block.oreEmerald.blockID, 0, 2);
            }
        }
        for (int var6 = 0; var6 < var5; ++var6) {
            int count = par3 + par2Random.nextInt(4);
            int temp = par2Random.nextInt(64);
            int x = par4 + par2Random.nextInt(16);
            int y = par1World.getBlockId(count, temp, x);
            if (y == Block.stone.blockID) {
                par1World.setBlock(count, temp, x, Block.oreEmerald.blockID, 0, 2);
            }
        }
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



}
