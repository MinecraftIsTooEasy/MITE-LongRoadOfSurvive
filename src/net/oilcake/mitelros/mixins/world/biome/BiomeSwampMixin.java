//package net.oilcake.mitelros.mixins.world.biome;
//
//import net.minecraft.*;
//import net.oilcake.mitelros.block.Blocks;
//import net.oilcake.mitelros.util.Constant;
//import org.spongepowered.asm.mixin.Mixin;
//
//import java.util.Random;
//
//@Mixin(BiomeSwamp.class)
//public class BiomeSwampMixin extends BiomeBase {
//    protected BiomeSwampMixin(int par1) {
//        super(par1);
//    }
//
//    public void decorate(World par1World, Random par2Random, int par3, int par4) {
//        super.decorate(par1World, par2Random, par3, par4);
//        int var5 = 3 + par2Random.nextInt(6);
//
////        for (int var6 = 0; var6 < var5; ++var6) {
////            int var7 = par3 + par2Random.nextInt(16);
////            int var8 = par2Random.nextInt(28) + 4;
////            int var9 = par4 + par2Random.nextInt(16);
////            int var10 = par1World.getBlockId(var7, var8, var9);
////            if (var10 == Block.stone.blockID) {
////                par1World.setBlock(var7, var8, var9, Block.oreEmerald.blockID, 0, 2);
////            }
////        }
//
//        WorldGenMinable genMinable = (new WorldGenMinable(Blocks.blockNickelOre.blockID, 8, Block.stone.blockID)).setMinableBlockMetadata(0);
//        int count = par2Random.nextInt(Constant.nickelNUM) + 1;
//        for (int temp = 0; temp < count; ++temp) {
//            int x = par3 + par2Random.nextInt(16);
//            int y = par2Random.nextInt(36)+12;
//            int z = par4 + par2Random.nextInt(16);
//            genMinable.generate(par1World, par2Random, x, y, z);
//        }
//    }
//}
