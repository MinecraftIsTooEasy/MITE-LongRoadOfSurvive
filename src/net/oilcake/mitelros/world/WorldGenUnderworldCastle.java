package net.oilcake.mitelros.world;

import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityLich;
import net.oilcake.mitelros.item.Items;

import java.util.Random;

public class WorldGenUnderworldCastle extends WorldGenerator {
    private static String[] Layer = new String[12];
    private static final StructurePieceTreasure[] chest_contents_for_underworld_castle;
    @Override
    public boolean generate(World par1World, Random random, int x, int y, int z) {
        while ((par1World.isAirBlock(x, y, z) && y > 144) || y > 160)
        {
            --y;
        }

        int var6 = par1World.getBlockId(x, y, z);
        if (!par1World.isUnderworld() && var6 != Block.stone.blockID)
        {
            return false;
        }
        else{
            for(int par1 = -10; par1<=10; par1++){
                for(int par2 = -10; par2<=10; par2++){
                    par1World.setBlock(x + par1, y, z + par2, getRandomStone(random.nextInt(3)), 0, 2);
                }
            }
            for(int par0 = 0; par0 < 12; par0++){
                ++y;
                int id = 0;
                int Builder = 0;
                for(int par1 = -10; par1<=0; par1++){
                    for(int par2 = -10; par2<=10; par2++){
                        id = getFlitteredBlockID(Layer[par0].charAt(Builder));
                        if(id == Block.mobSpawner.blockID){
                            par1World.setBlock(x + par1, y, z + par2, Block.mobSpawner.blockID, 0, 2);
                            TileEntityMobSpawner var18 = (TileEntityMobSpawner)par1World.getBlockTileEntity(x + par1, y, z + par2);
                            if (var18 != null) {
                                var18.getSpawnerLogic().setMobID("LongdeadGuardian");
                            } else {
                                System.err.println("Failed to fetch mob spawner entity at (" + (x + par1) + ", " + y + ", " + (z + par2) + ")");
                            }
                        }else if(id == Block.chestAncientMetal.blockID){
                            par1World.setBlock(x + par1, y, z + par2, Block.chestAncientMetal.blockID, Block.chestAncientMetal.getMetadataForDirectionFacing(0, getRandomDirection(random)), 2);
                            StructurePieceTreasure[] var16 = StructurePieceTreasure.func_92080_a(getChestContentsForWorld(par1World), Item.enchantedBook.func_92114_b(random));
                            TileEntityChest var17 = (TileEntityChest)par1World.getBlockTileEntity(x + par1, y, z + par2);
                            if (var17 != null) {
                                StructurePieceTreasure.generateChestContents(par1World, y, random, var16, var17, 8, (float[])null);
                            }
                        }else if(id == Block.fenceAncientMetal.blockID){
                            par1World.setBlock(x + par1, y, z + par2, getRandomFence(random.nextInt(4)), 0, 2);
                        }else if(id == Block.stoneBrick.blockID){
                            par1World.setBlock(x + par1, y, z + par2, Block.stoneBrick.blockID, getRandomBrickSubtype(random.nextInt(7)), 2);
                        }else {
                            par1World.setBlockToAir(x + par1, y, z + par2);
                        }
                        Builder++;
                    }
                }
                Builder = 0;
                for(int par1 = 10; par1>=0; par1--){
                    for(int par2 = -10; par2<=10; par2++){
                        id = getFlitteredBlockID(Layer[par0].charAt(Builder));
                        if(id == Block.mobSpawner.blockID){
                            par1World.setBlock(x + par1, y, z + par2, Block.mobSpawner.blockID, 0, 2);
                            TileEntityMobSpawner var18 = (TileEntityMobSpawner)par1World.getBlockTileEntity(x + par1, y, z + par2);
                            if (var18 != null) {
                                var18.getSpawnerLogic().setMobID("LongdeadGuardian");
                            } else {
                                System.err.println("Failed to fetch mob spawner entity at (" + (x + par1) + ", " + y + ", " + (z + par2) + ")");
                            }
                        }else if(id == Block.chestAncientMetal.blockID){
                            par1World.setBlock(x + par1, y, z + par2, Block.chestAncientMetal.blockID, Block.chestAncientMetal.getMetadataForDirectionFacing(0, getRandomDirection(random)), 2);
                            StructurePieceTreasure[] var16 = StructurePieceTreasure.func_92080_a(getChestContentsForWorld(par1World), Item.enchantedBook.func_92114_b(random));
                            TileEntityChest var17 = (TileEntityChest)par1World.getBlockTileEntity(x + par1, y, z + par2);
                            if (var17 != null) {
                                StructurePieceTreasure.generateChestContents(par1World, y, random, var16, var17, 8, (float[])null);
                            }
                        }else if(id == Block.fenceAncientMetal.blockID){
                            par1World.setBlock(x + par1, y, z + par2, getRandomFence(random.nextInt(4)), 0, 2);
                        }else if(id == Block.stoneBrick.blockID){
                            par1World.setBlock(x + par1, y, z + par2, Block.stoneBrick.blockID, getRandomBrickSubtype(random.nextInt(7)), 2);
                        }else {
                            par1World.setBlockToAir(x + par1, y, z + par2);
                        }
                        Builder++;
                    }
                }
            }
            EntityLich var11 = new EntityLich(par1World);
            var11.setLocationAndAngles((double)x + 0.5, (double)y-4, (double)z + 0.5, 0.0F, 0.0F);
            var11.onSpawnWithEgg((GroupDataEntity)null);
            var11.func_110163_bv();
            par1World.spawnEntityInWorld(var11);
            return true;
        }
    }
    private EnumDirection getRandomDirection(Random rand){
        int result = rand.nextInt(4);
        switch (result){
            case 1:
                return EnumDirection.SOUTH;
            case 2:
                return EnumDirection.EAST;
            case 3:
                return EnumDirection.WEST;
            default:
                return EnumDirection.NORTH;
        }
    }
    private int getRandomBrickSubtype(int index){
        return index == 0 ? 1 : index >= 6 ? 2 : 0;
    }
    private int getRandomStone(int index){
        return index == 0 ? Block.cobblestoneMossy.blockID : Block.cobblestone.blockID;
    }
    private int getRandomFence(int index){
        return index == 0 ? Block.fenceAncientMetal.blockID : 0;
    }
    private int getFlitteredBlockID(char index){
        if(index == 'S'){
            return Block.stoneBrick.blockID;
        }else if(index == 'M'){
            return Block.mobSpawner.blockID;
        }else if(index == 'B'){
            return Block.chestAncientMetal.blockID;
        }else if(index == 'F'){
            return getRandomFence(0);
        }else {
            return 0;
        }
    }
    private static StructurePieceTreasure[] getChestContentsForWorld(World world) {
        return chest_contents_for_underworld_castle;
    }
    static {
        Layer[0]  = "        S   S          SSS SS     SS SSS   S   S         S   S  S M F         F M S  S   S         S   S   SFS           SFS   S        S        S  S                 S S                   S                           S       S      ";
        Layer[1]  = "        S   S          SSS S       S SSS   S   S         S   S  S   F         F   S  S   S         S   S   SFS           SFS   S        S        S                      S                   S                           S       S      ";
        Layer[2]  = "        S   S          SSS S       S SSS   S   S         S   S  S   S         S   S  S   S         S   S   SSS           SSS   S        S        S                      S                   S                           S       S      ";
        Layer[3]  = "        S   S          SSS SS     SS SSS   SSSSS         SSSSS  SSSSS         SSSSS  SSSSS         SSSSS   SSS           SSS   S                 S  S        S        S S                   S                            S     S       ";
        Layer[4]  = "         SSS           SSS   SSSSS   SSS   S   SSSS   SSSS   S  S B  S       S  B S  S    S       S    S   S  SS       SS  S    SSSSSS     SSSSSS    S   SSS   SSS   S   SS    SSSSSSS    SS SS      SSSSS      SSSS      SSSSS      SS";
        Layer[5]  = "                       SSS   SFSFS   SSS   S   SSS     SSS   S  S                 S  S                 S   S               S    S               S    S      SSS      S   S                 S  F     S     S     F  S     S     S     S ";
        Layer[6]  = "                       SSS   SFSFS   SSS   S   SSS     SSS   S  S                 S  S                 S   S               S    S      SSS      S    S               S   S                 S  F    S       S    F  S    S       S    S ";
        Layer[7]  = "                       S S   SSSSS   S S   S   SSS     SSS   S                       S                 S   S      SSS      S    S               S    S               S   S                 S  S   S         S   S  S   S         S   S ";
        Layer[8]  = "                       S S    SSS    S S   S   SSSS   SSSS   S                       S       SSS       S   S               S    S               S    S               S    S               S   S  S           S  S  S  S           S  S ";
        Layer[9]  = "                       SSS           SSS   SSSSSSSSSSSSSSSSSSS  SSSSSSSSSSSSSSSSSSS  SSSSSSSS   SSSSSSSS   SSS           SSS    SSS           SSS    SSS           SSS    SSS           SSS    SS             SS    SS             SS  ";
        Layer[10] = "                       SSS           SSS   S   SSSSSSSSSSS   S  S M             M S  S                 S   S               S    S               S    S               S    S               S    S               S    S               S  ";
        Layer[11] = "                       S S           S S   S   S S SSS S S   S                       S                 S   S               S                         S               S                         S               S    S               S  ";
        chest_contents_for_underworld_castle = new StructurePieceTreasure[]{
                new StructurePieceTreasure(Item.ingotAncientMetal.itemID, 0, 3, 5, 10),
                new StructurePieceTreasure(Item.ingotGold.itemID, 0, 3, 5, 10),
                new StructurePieceTreasure(Item.diamond.itemID, 0, 2, 4, 10),
                new StructurePieceTreasure(Items.AncientmetalArmorPiece.itemID, 0, 6, 9, 20),
                new StructurePieceTreasure(Item.coinAncientMetal.itemID, 0, 2, 4, 15),
                new StructurePieceTreasure(Item.horseArmorMithril.itemID, 0, 1, 1, 2),
                new StructurePieceTreasure(Item.horseArmorAncientMetal.itemID, 0, 1, 1, 2),
                new StructurePieceTreasure(Items.FreezeWand.itemID, 0, 1, 1, 2),
                new StructurePieceTreasure(Items.LavaWand.itemID, 0, 1, 1, 2),
                new StructurePieceTreasure(Item.warHammerAncientMetal.itemID, 0, 1, 1, 2),
                new StructurePieceTreasure(Item.battleAxeAncientMetal.itemID, 0, 1, 1, 2),
                new StructurePieceTreasure(Item.swordAncientMetal.itemID, 0, 1, 1, 2),
                new StructurePieceTreasure(Item.bootsAncientMetal.itemID, 0, 1, 1, 2),
                new StructurePieceTreasure(Item.legsAncientMetal.itemID, 0, 1, 1, 2),
                new StructurePieceTreasure(Item.plateAncientMetal.itemID, 0, 1, 1, 2),
                new StructurePieceTreasure(Item.helmetAncientMetal.itemID, 0, 1, 1, 2),
                new StructurePieceTreasure(Items.ChestplateAncientmetalsacred.itemID, 0, 1, 1, 1),
                new StructurePieceTreasure(Items.HelmetAncientmetalsacred.itemID, 0, 1, 1, 1),
                new StructurePieceTreasure(Items.BootsAncientmetalsacred.itemID, 0, 1, 1, 1),
                new StructurePieceTreasure(Items.LeggingsAncientmetalsacred.itemID, 0, 1, 1, 1)};
    }
}
