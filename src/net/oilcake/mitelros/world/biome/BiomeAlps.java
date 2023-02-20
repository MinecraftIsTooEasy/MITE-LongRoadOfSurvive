package net.oilcake.mitelros.world.biome;

import net.minecraft.*;
import net.oilcake.mitelros.world.BiomeBases;

import java.util.Random;

public class BiomeAlps extends BiomeBase {
    public BiomeAlps(int par1) {
        super(par1);
        this.spawnableCreatureList.clear();
        this.topBlock = (byte)Block.cobblestoneMossy.blockID;
        this.fillerBlock = (byte)Block.cobblestone.blockID;
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
    }


}
