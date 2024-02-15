package net.oilcake.mitelros.world;

import net.minecraft.Block;
import net.minecraft.Minecraft;
import net.minecraft.World;
import net.minecraft.WorldGenerator;
import net.oilcake.mitelros.block.Blocks;

import java.util.Random;

public class WorldGenFlowersExtend extends WorldGenerator {

    private int plantBlockId;
    private Block block;
    private int metadata;

    public WorldGenFlowersExtend(int par1) {
        this.plantBlockId = par1;
        this.block = Block.getBlock(this.plantBlockId);
    }
    @Override
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
        int attempts = 64;

        for(int var6 = 0; var6 < attempts; ++var6) {
            int var8 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
            if (var8 >= 0 && var8 <= 255) {
                int var7 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
                int var9 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);
                if (par1World.isAirBlock(var7, var8, var9) && (!par1World.provider.hasNoSky || var8 < 127) && this.block.canOccurAt(par1World, var7, var8, var9, this.metadata)) {
                    par1World.setBlock(var7, var8, var9, this.plantBlockId, this.metadata, 2);
                }
            }
        }

        return true;
    }
    public void setMetadata(int metadata) {
        if (this.block != Blocks.flowerextend) {
            Minecraft.setErrorMessage("setMetadata: only allowed for extended flower block");
        } else {
            this.metadata = metadata;
        }
    }
}
