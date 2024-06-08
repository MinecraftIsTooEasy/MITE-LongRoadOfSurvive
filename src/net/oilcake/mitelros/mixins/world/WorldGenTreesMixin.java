//package net.oilcake.mitelros.mixins.world;
//
//import net.minecraft.*;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Overwrite;
//import org.spongepowered.asm.mixin.Shadow;
//
//import java.util.Random;
//
//@Mixin(WorldGenTrees.class)
//public class WorldGenTreesMixin extends WorldGenerator {
//    @Shadow
//    @Final
//    //最小树高
//    private int minTreeHeight;
//    @Shadow
//    @Final
//    //是否长藤蔓
//    private boolean vinesGrow;
//    @Shadow
//    @Final
//    //木头种类
//    private int metaWood;
//    @Shadow
//    @Final
//    //树叶种类
//    private int metaLeaves;
//    @Overwrite
//    public boolean generate(World par1World, Random par2Random, int posx, int posy, int posz) {
//        //默认的树高
//        int default_height = par2Random.nextInt(3) + this.minTreeHeight;
//        boolean var7 = true;
//        //世界高度确认
//        if (posy >= 1 && posy + default_height + 1 <= 256) {
//            //y向检查游标
//            int var8;
//            //这玩意说实话没啥用
//            byte var9;
//            //z向检查游标
//            int var11;
//            //检查方块id
//            int var12;
//            //x向检查游标
//            int radius;
//            //检查这棵树是否被挡死
//            for(var8 = posy; var8 <= posy + 1 + default_height; ++var8) {
//                var9 = 1;
//                if (var8 == posy) {
//                    var9 = 0;
//                }
//
//                if (var8 >= posy + 1 + default_height - 2) {
//                    var9 = 2;
//                }
//
//                for(radius = posx - var9; radius <= posx + var9 && var7; ++radius) {
//                    for(var11 = posz - var9; var11 <= posz + var9 && var7; ++var11) {
//                        if (var8 >= 0 && var8 < 256) {
//                            var12 = par1World.getBlockId(radius, var8, var11);
//                            if (var12 != 0 && var12 != Block.leaves.blockID && var12 != Block.grass.blockID && var12 != Block.dirt.blockID && var12 != Block.wood.blockID) {
//                                var7 = false;
//                            }
//                        } else {
//                            var7 = false;
//                        }
//                    }
//                }
//            }
//            if (!var7) {
//                return false;
//            }
//            //开始生成
//            else {
//                radius = 0;
//                //检查下方是否为土块，否则生成失败
//                var8 = par1World.getBlockId(posx, posy - 1, posz);
//                if ((var8 == Block.grass.blockID || var8 == Block.dirt.blockID) && posy < 256 - default_height - 1) {
//                    this.setBlock(par1World, posx, posy - 1, posz, Block.dirt.blockID);
//                    //现在应该在生成树叶
//                    var9 = 3;
//                    //这个没用
//                    byte var19 = 0;
//                    //树叶半径大小
//                    int var13;
//                    //x向游标
//                    int var14;
//                    //不知道有啥用
//                    int var15;
//                    //y向游标 var9 -> 0
//                    //生成树叶
//                    for(var11 = posy - var9 + default_height; var11 <= posy + default_height; ++var11) {
//                        //检查高度
//                        var12 = var11 - (posy + default_height);
//                        //树叶半径大小
//                        var13 = var19 + 1 - var12 / 2;
//                        if (var13 > radius) {
//                            radius = var13;
//                        }
//                        //x向游标
//                        for(var14 = posx - var13; var14 <= posx + var13; ++var14) {
//                            var15 = var14 - posx;
//                            //z向游标
//                            for(int var16 = posz - var13; var16 <= posz + var13; ++var16) {
//                                int var17 = var16 - posz;
//                                if (var15 != var13 || -var15 != var13 || var17 != var13 || -var17 != var13 || par2Random.nextInt(2) != 0 && var12 != 0) {
//                                    int var18 = par1World.getBlockId(var14, var11, var16);
//                                    if (var18 == 0 || var18 == Block.leaves.blockID) {
//                                        this.setBlockAndMetadata(par1World, var14, var11, var16, Block.leaves.blockID, this.metaLeaves);
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    //树干生长藤蔓
//                    for(var11 = 0; var11 < default_height; ++var11) {
//                        var12 = par1World.getBlockId(posx, posy + var11, posz);
//                        if (var12 == 0 || var12 == Block.leaves.blockID) {
//                            this.setBlockAndMetadata(par1World, posx, posy + var11, posz, Block.wood.blockID, this.metaWood);
//                            if (this.vinesGrow && var11 > 0) {
//                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(posx - 1, posy + var11, posz)) {
//                                    this.setBlockAndMetadata(par1World, posx - 1, posy + var11, posz, Block.vine.blockID, 8);
//                                }
//
//                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(posx + 1, posy + var11, posz)) {
//                                    this.setBlockAndMetadata(par1World, posx + 1, posy + var11, posz, Block.vine.blockID, 2);
//                                }
//
//                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(posx, posy + var11, posz - 1)) {
//                                    this.setBlockAndMetadata(par1World, posx, posy + var11, posz - 1, Block.vine.blockID, 1);
//                                }
//
//                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(posx, posy + var11, posz + 1)) {
//                                    this.setBlockAndMetadata(par1World, posx, posy + var11, posz + 1, Block.vine.blockID, 4);
//                                }
//                            }
//                        }
//                    }
//                    //树叶生长藤蔓
//                    if (this.vinesGrow) {
//                        for(var11 = posy - 3 + default_height; var11 <= posy + default_height; ++var11) {
//                            var12 = var11 - (posy + default_height);
//                            var13 = 2 - var12 / 2;
//
//                            for(var14 = posx - var13; var14 <= posx + var13; ++var14) {
//                                for(var15 = posz - var13; var15 <= posz + var13; ++var15) {
//                                    if (par1World.getBlockId(var14, var11, var15) == Block.leaves.blockID) {
//                                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14 - 1, var11, var15) == 0) {
//                                            this.growVines(par1World, var14 - 1, var11, var15, 8);
//                                        }
//
//                                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14 + 1, var11, var15) == 0) {
//                                            this.growVines(par1World, var14 + 1, var11, var15, 2);
//                                        }
//
//                                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14, var11, var15 - 1) == 0) {
//                                            this.growVines(par1World, var14, var11, var15 - 1, 1);
//                                        }
//
//                                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14, var11, var15 + 1) == 0) {
//                                            this.growVines(par1World, var14, var11, var15 + 1, 4);
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                        //放置可可豆
//                        if (par2Random.nextInt(5) == 0 && default_height > 5) {
//                            for(var11 = 0; var11 < 2; ++var11) {
//                                for(var12 = 0; var12 < 4; ++var12) {
//                                    if (par2Random.nextInt(4 - var11) == 0) {
//                                        var13 = par2Random.nextInt(3);
//                                        this.setBlockAndMetadata(par1World, posx + Direction.offsetX[Direction.rotateOpposite[var12]], posy + default_height - 5 + var11, posz + Direction.offsetZ[Direction.rotateOpposite[var12]], Block.cocoaPlant.blockID, var13 << 2 | var12);
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    //覆雪
//                    if (par1World.decorating) {
//                        par1World.placeNaturallyOccurringSnow(posx - radius, posz - radius, posx + radius, posz + radius);
//                    }
//
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        } else {
//            return false;
//        }
//    }
//    @Shadow
//    private void growVines(World par1World, int par2, int par3, int par4, int par5) {
//    }
//}
