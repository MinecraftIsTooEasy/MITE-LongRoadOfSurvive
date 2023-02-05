package net.oilcake.mitelros.mixins.world.biome;

import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.util.Constant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Random;

@Mixin(BiomeJungle.class)
public class BiomeJungleMixin extends BiomeBase {
    protected BiomeJungleMixin(int par1) {
        super(par1);
    }

    @Overwrite
    public void decorate(World par1World, Random par2Random, int par3, int par4) {
        super.decorate(par1World, par2Random, par3, par4);
        WorldGenVines var5 = new WorldGenVines();

        for(int var6 = 0; var6 < 50; ++var6) {
            int var7 = par3 + par2Random.nextInt(16) + 8;
            byte var8 = 64;
            int var9 = par4 + par2Random.nextInt(16) + 8;
            var5.generate(par1World, par2Random, var7, var8, var9);
        }

        WorldGenMinable genMinable = (new WorldGenMinable(Blocks.blockNickelOre.blockID, 8 , Block.stone.blockID)).setMinableBlockMetadata(0);
        int count = par2Random.nextInt(Constant.nickelNUM) + 1;
        for(int temp = 0; temp < count; ++temp) {
            int x = par3 + par2Random.nextInt(16);
            int y = par2Random.nextInt(36)+12;
            int z = par4 + par2Random.nextInt(16);
            genMinable.generate(par1World, par2Random, x, y, z);
        }

    }
}
