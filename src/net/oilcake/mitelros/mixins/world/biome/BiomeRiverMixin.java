//package net.oilcake.mitelros.mixins.world.biome;
//
//import net.minecraft.*;
//import net.oilcake.mitelros.block.Blocks;
//import net.oilcake.mitelros.util.Constant;
//import org.spongepowered.asm.mixin.Mixin;
//
//import java.util.Random;
//
//@Mixin(BiomeRiver.class)
//public class BiomeRiverMixin extends BiomeBase {
//    protected BiomeRiverMixin(int par1) {
//        super(par1);
//    }
//    public void decorate(World par1World, Random par2Random, int par3, int par4) {
//        super.decorate(par1World, par2Random, par3, par4);
//
//        WorldGenMinable genMinable = (new WorldGenMinable(Blocks.blockNickelOre.blockID, 8 , Block.stone.blockID)).setMinableBlockMetadata(0);
//        int count = par2Random.nextInt(Constant.nickelNUM) + 1;
//        for(int temp = 0; temp < count; ++temp) {
//            int x = par3 + par2Random.nextInt(16);
//            int y = par2Random.nextInt(36)+12;
//            int z = par4 + par2Random.nextInt(16);
//            genMinable.generate(par1World, par2Random, x, y, z);
//        }
//    }
//}
