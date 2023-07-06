package net.oilcake.mitelros.world;

import net.minecraft.Block;
import net.minecraft.World;
import net.minecraft.WorldGenerator;

import java.util.Random;

public class WorldGenStoneCone extends WorldGenerator {
    private boolean isSuperLarge = false;
    public void setSuperLarge(){
        this.isSuperLarge = true;
    }
    private boolean CalRadius(int rad,int posx,int posz){
        posx = posx*posx;
        posz = posz*posz;
        rad = rad*rad;
        return posx + posz <= rad;
    }

    @Override
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        while (par1World.isAirBlock(par3, par4, par5) && par4 > 2)
        {
            --par4;
        }

        int var6 = par1World.getBlockId(par3, par4, par5);
        if (var6 != Block.grass.blockID)
        {
            return false;
        }
        else
        {
            Random temp = new Random();
            temp.setSeed(par1World.getSeed());
            int radius = temp.nextInt(4) + (isSuperLarge ? 15 : 6);
            int layer = 0;
            while(radius >= 0 && layer < (isSuperLarge ? 96 : 45)){
                for(int i = -radius;i <= radius;i++){
                    for(int j = -radius;j <= radius;j++){
                        if(CalRadius(radius,i,j) && par1World.getBlock(par3 + i, par4 - 3 + layer, par5 + j)!=Block.bedrock){
                            if(temp.nextInt(3) == 0 && radius >= 1){
                                par1World.setBlock(par3 + i - 1, par4 - 3 + layer, par5 + j, temp.nextInt(4) == 0 ? Block.stone.blockID : Block.cobblestone.blockID, 0, 2);
                                par1World.setBlock(par3 + i + 1, par4 - 3 + layer, par5 + j, temp.nextInt(4) == 0 ? Block.stone.blockID : Block.cobblestone.blockID, 0, 2);
                                par1World.setBlock(par3 + i, par4 - 3 + layer, par5 + j - 1, temp.nextInt(4) == 0 ? Block.stone.blockID : Block.cobblestone.blockID, 0, 2);
                                par1World.setBlock(par3 + i, par4 - 3 + layer, par5 + j + 1, temp.nextInt(4) == 0 ? Block.stone.blockID : Block.cobblestone.blockID, 0, 2);
                            }
                            par1World.setBlock(par3 + i, par4 - 3 + layer, par5 + j, temp.nextInt(4) == 0 ? Block.stone.blockID : Block.cobblestone.blockID, 0, 2);
                        }
                    }
                }
                layer++;
                if(temp.nextFloat() < (float)radius / ((float)radius + 5.0F ) || (radius == 0) && (temp.nextFloat() < 0.25F)){
                    radius--;
                }
            }
            return true;
        }
    }
}
