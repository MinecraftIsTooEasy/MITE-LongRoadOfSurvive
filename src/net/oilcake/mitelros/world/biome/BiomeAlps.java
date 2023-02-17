package net.oilcake.mitelros.world.biome;

import net.minecraft.BiomeBase;
import net.minecraft.Block;
import net.minecraft.World;

import java.util.Random;

public class BiomeAlps extends BiomeBase{
    public BiomeAlps(int par1) {
        super(par1);
        this.spawnableCreatureList.clear();
        this.topBlock = (byte)Block.stone.blockID;
        this.fillerBlock = (byte)Block.stone.blockID;
    }

    public void decorate(World par1World, Random par2Random, int par3, int par4) {
        super.decorate(par1World, par2Random, par3, par4);
        int var5 = 3 + par2Random.nextInt(6);

        int count;
        int temp;
        int x;
        int y;
        for (int var6 = 0; var6 < var5; ++var6) {
            count = par3 + par2Random.nextInt(4);
            temp = par2Random.nextInt(255);
            x = par4 + par2Random.nextInt(16);
            y = par1World.getBlockId(count, temp, x);
            if (y == Block.stone.blockID) {
                par1World.setBlock(count, temp, x, Block.oreEmerald.blockID, 0, 2);
            }
        }
    }

}
