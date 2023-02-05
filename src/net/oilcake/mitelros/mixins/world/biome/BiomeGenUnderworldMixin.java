package net.oilcake.mitelros.mixins.world.biome;

import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(BiomeGenUnderworld.class)
public class BiomeGenUnderworldMixin extends BiomeBase {
    protected BiomeGenUnderworldMixin(int par1) {
        super(par1);
    }
    @Shadow
    private void placeMycelium(World world, int chunk_origin_x, int chunk_origin_z) {
    }

    public void decorate(World par1World, Random par2Random, int par3, int par4) {
        this.placeMycelium(par1World, par3, par4);
        super.decorate(par1World, par2Random, par3, par4);

        WorldGenMinable genMinableNickel = (new WorldGenMinable(Blocks.blockNickelOre.blockID, par2Random.nextInt(5) + 3 , Block.stone.blockID)).setMinableBlockMetadata(0);
        int countNickel = par2Random.nextInt(14) + 1;
        for(int temp = 0; temp < countNickel; ++temp) {
            int x = par3 + par2Random.nextInt(16);
            int y = par2Random.nextInt(255);
            int z = par4 + par2Random.nextInt(16);
            genMinableNickel.generate(par1World, par2Random, x, y, z);
        }
        WorldGenMinable genMinable = (new WorldGenMinable(Blocks.blockTungstenOre.blockID, par2Random.nextInt(3) + 3, Block.stone.blockID)).setMinableBlockMetadata(0);
        int countTungsten = par2Random.nextInt(5) + 1;
        for(int temp = 0; temp < countTungsten; ++temp) {
            int x = par3 + par2Random.nextInt(16);
            int y = par2Random.nextInt(90) + 20;
            int z = par4 + par2Random.nextInt(16);
            genMinable.generate(par1World, par2Random, x, y, z);
        }
    }
}
