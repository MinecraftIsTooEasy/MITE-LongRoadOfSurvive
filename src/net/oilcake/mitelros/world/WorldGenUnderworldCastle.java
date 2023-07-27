package net.oilcake.mitelros.world;

import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityLich;
import net.oilcake.mitelros.item.Items;

import java.util.Random;

public class WorldGenUnderworldCastle extends WorldGenerator {
    private static String[] Layer = new String[22];
    private static final StructurePieceTreasure[] chest_contents_for_underworld_castle;
    @Override
    public boolean generate(World par1World, Random random, int x, int y, int z) {
        while ((par1World.isAirBlock(x, y, z) && y > 144) || y > 160)
        {
            --y;
        }

        int var6;
        var6 = par1World.getBlockId(x + 25, y, z);
        if (!par1World.isUnderworld() && var6 != Block.stone.blockID)
        {
            return false;
        }
        var6 = par1World.getBlockId(x - 25, y, z);
        if (!par1World.isUnderworld() && var6 != Block.stone.blockID)
        {
            return false;
        }
        var6 = par1World.getBlockId(x, y, z + 25);
        if (!par1World.isUnderworld() && var6 != Block.stone.blockID)
        {
            return false;
        }
        var6 = par1World.getBlockId(x, y, z - 25);
        if (!par1World.isUnderworld() && var6 != Block.stone.blockID)
        {
            return false;
        }
        var6 = par1World.getBlockId(x, y + 30, z);
        if (!par1World.isUnderworld() && var6 != 0)
        {
            return false;
        }
        var6 = par1World.getBlockId(x, y, z);
        if (!par1World.isUnderworld() && var6 != Block.stone.blockID)
        {
            return false;
        }else{
            for(int par1 = -25; par1<=25; par1++){
                for(int par2 = -25; par2<=25; par2++){
                    par1World.setBlock(x + par1, y, z + par2, getRandomStone(random.nextInt(3)), 0, 2);
                }
            }
            for(int par0 = 0; par0 < 22; par0++){
                ++y;
                char id;
                int Builder = 0;
                for(int par1 = 25; par1>=0; par1--){
                    for(int par2 = 25; par2 >= -25; par2--){
                        id = Layer[par0].charAt(Builder);
                        if(id != ' '){
                            par1World.setBlock(x + par2, y, z + par1, getRandomStone(random.nextInt(3)), 0, 2);
                        }
                        else{
                            par1World.setBlockToAir(x + par2, y, z + par1);
                        }
                        Builder++;
                    }
                }
                Builder = 0;
                for(int par1 = -25; par1<=0; par1++){
                    for(int par2 = -25; par2 <= 25; par2++){
                        id = Layer[par0].charAt(Builder);
                        if(id != ' '){
                            par1World.setBlock(x + par2, y, z + par1, getRandomStone(random.nextInt(3)), 0, 2);
                        }
                        else{
                            par1World.setBlockToAir(x + par2, y, z + par1);
                        }
                        Builder++;
                    }
                }
            }

            y-=21;

            fillBlocksWithMetadata(par1World,x+19,x+19,y,y,z-2,z+2,Block.stairsCobblestone.blockID,1);
            fillBlocksWithMetadata(par1World,x-2,x+2,y,y,z-19,z-19,Block.stairsCobblestone.blockID,2);
            fillBlocksWithMetadata(par1World,x-19,x-19,y,y,z-2,z+2,Block.stairsCobblestone.blockID,0);
            fillBlocksWithMetadata(par1World,x-2,x+2,y,y,z+19,z+19,Block.stairsCobblestone.blockID,3);
            fillBlocksWithMetadata(par1World,x-3,x+3,y,y,z-14,z+14,Block.cloth.blockID,8);
            fillBlocksWithMetadata(par1World,x-14,x+14,y,y,z-3,z+3,Block.cloth.blockID,8);
            fillBlocksWithMetadata(par1World,x-2,x+2,y,y,z-14,z+14,Block.cloth.blockID,9);
            fillBlocksWithMetadata(par1World,x-14,x+14,y,y,z-2,z+2,Block.cloth.blockID,9);
            fillBlocksWithMetadata(par1World,x-2,x+2,y,y,z-2,z+2,Block.cloth.blockID,3);
            y++;

            par1World.setBlock(x,y,z,Block.mobSpawner.blockID);
            TileEntityMobSpawner var18 = (TileEntityMobSpawner)par1World.getBlockTileEntity(x , y, z );
            if (var18 != null) {
                var18.getSpawnerLogic().setMobID("Longdead");
            } else {
                System.err.println("Failed to fetch mob spawner entity at (" + (x ) + ", " + y + ", " + (z ) + ")");
            }
            par1World.setBlock(x+6,y,z-6,Block.mobSpawner.blockID);
            var18 = (TileEntityMobSpawner)par1World.getBlockTileEntity(x + 6, y, z - 6);
            if (var18 != null) {
                var18.getSpawnerLogic().setMobID("Longdead");
            } else {
                System.err.println("Failed to fetch mob spawner entity at (" + (x + 6) + ", " + y + ", " + (z - 6) + ")");
            }
            par1World.setBlock(x-6,y,z+6,Block.mobSpawner.blockID);
            var18 = (TileEntityMobSpawner)par1World.getBlockTileEntity(x - 6, y, z + 6);
            if (var18 != null) {
                var18.getSpawnerLogic().setMobID("Longdead");
            } else {
                System.err.println("Failed to fetch mob spawner entity at (" + (x - 6) + ", " + y + ", " + (z + 6) + ")");
            }
            par1World.setBlock(x-6,y,z-6,Block.stairsCobblestone.blockID,3,2);
            par1World.setBlock(x+6,y,z+6,Block.stairsCobblestone.blockID,2,2);
            par1World.setBlock(x-8,y,z-5,Block.chestAncientMetal.blockID,Block.chestAncientMetal.getMetadataForDirectionFacing(0, getRandomDirection(random)),2);
            StructurePieceTreasure[] var16 = StructurePieceTreasure.func_92080_a(getChestContentsForWorld(par1World), Item.enchantedBook.func_92114_b(random));
            TileEntityChest var17 = (TileEntityChest)par1World.getBlockTileEntity(x - 8, y, z - 5);
            if (var17 != null) {
                StructurePieceTreasure.generateChestContents(par1World, y, random, var16, var17, 8, null);
            }
            par1World.setBlock(x+8,y,z+5,Block.chestAncientMetal.blockID,Block.chestAncientMetal.getMetadataForDirectionFacing(0, getRandomDirection(random)),2);
            var17 = (TileEntityChest)par1World.getBlockTileEntity(x + 8, y, z + 5);
            if (var17 != null) {
                StructurePieceTreasure.generateChestContents(par1World, y, random, var16, var17, 8, null);
            }
            fillBlocksWithMetadata(par1World,x-14,x-10,y,y,z-5,z-5,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x+10,x+14,y,y,z+5,z+5,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x-14,x-10,y,y,z-6,z-6,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x+10,x+14,y,y,z+6,z+6,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x+14,x+15,y,y,z+20,z+20,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x+20,x+20,y,y,z+14,z+15,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x-14,x-15,y,y,z-20,z-20,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x-20,x-20,y,y,z-14,z-15,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x+14,x+15,y,y,z-20,z-20,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x+20,x+20,y,y,z-14,z-15,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x-14,x-15,y,y,z+20,z+20,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x-20,x-20,y,y,z+14,z+15,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x+14,x+15,y,y,z+21,z+21,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x+21,x+21,y,y,z+14,z+15,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-14,x-15,y,y,z-21,z-21,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-21,x-21,y,y,z-14,z-15,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x+14,x+15,y,y,z-21,z-21,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x+21,x+21,y,y,z-14,z-15,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-14,x-15,y,y,z+21,z+21,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-21,x-21,y,y,z+14,z+15,Block.stoneSingleSlab.blockID,11);
            y++;

            fillBlocksWithMetadata(par1World,x-4,x-5,y,y,z-4,z-4,Block.stairsCobblestone.blockID,3);
            par1World.setBlock(x-4,y,z-5,Block.stairsCobblestone.blockID,1,2);
            fillBlocksWithMetadata(par1World,x+4,x+5,y,y,z+4,z+4,Block.stairsCobblestone.blockID,2);
            par1World.setBlock(x+4,y,z+5,Block.stairsCobblestone.blockID,0,2);
            par1World.setBlock(x-6,y,z-7,Block.stairsCobblestone.blockID,0,3);
            par1World.setBlock(x+6,y,z+7,Block.stairsCobblestone.blockID,0,2);
            fillBlocksWithMetadata(par1World,x-10,x-12,y,y,z-7,z-7,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x-10,x-12,y,y,z-8,z-8,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x+10,x+12,y,y,z+7,z+7,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x+10,x+12,y,y,z+8,z+8,Block.stoneSingleSlab.blockID,11);
            par1World.setBlock(x+22,y,z+15,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x+23,y,z+15,Block.stoneSingleSlab.blockID,11,2);
            par1World.setBlock(x+22,y,z-15,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x+23,y,z-15,Block.stoneSingleSlab.blockID,11,2);
            par1World.setBlock(x-15,y,z+22,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x-15,y,z+23,Block.stoneSingleSlab.blockID,11,2);
            par1World.setBlock(x-15,y,z-22,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x-15,y,z-23,Block.stoneSingleSlab.blockID,11,2);
            par1World.setBlock(x+10,y,z-5,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x+11,y,z-5,Block.stoneSingleSlab.blockID,11,2);
            par1World.setBlock(x-10,y,z+5,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x-11,y,z+5,Block.stoneSingleSlab.blockID,11,2);
            y++;

            par1World.setBlock(x+3,y,z+6,Block.stairsCobblestone.blockID,4,2);
            par1World.setBlock(x+3,y,z+8,Block.stairsCobblestone.blockID,4,2);
            par1World.setBlock(x+3,y,z+10,Block.stairsCobblestone.blockID,4,2);
            par1World.setBlock(x+3,y,z+12,Block.stairsCobblestone.blockID,4,2);
            par1World.setBlock(x+6,y,z+3,Block.stairsCobblestone.blockID,2,2);
            par1World.setBlock(x+8,y,z+3,Block.stairsCobblestone.blockID,2,2);
            par1World.setBlock(x+10,y,z+4,Block.stairsCobblestone.blockID,5,2);
            par1World.setBlock(x+13,y,z+4,Block.stairsCobblestone.blockID,4,2);
            par1World.setBlock(x+6,y,z+8,Block.stairsCobblestone.blockID,2,2);
            par1World.setBlock(x+7,y,z+9,Block.stairsCobblestone.blockID,5,2);
            par1World.setBlock(x+8,y,z+9,Block.stairsCobblestone.blockID,4,2);
            par1World.setBlock(x-3,y,z+6,Block.stairsCobblestone.blockID,5,2);
            par1World.setBlock(x-3,y,z+8,Block.stairsCobblestone.blockID,5,2);
            par1World.setBlock(x-3,y,z+10,Block.stairsCobblestone.blockID,5,2);
            par1World.setBlock(x-3,y,z+12,Block.stairsCobblestone.blockID,5,2);
            par1World.setBlock(x-6,y,z+3,Block.stairsCobblestone.blockID,2,2);
            par1World.setBlock(x-8,y,z+3,Block.stairsCobblestone.blockID,2,2);
            par1World.setBlock(x-10,y,z+3,Block.stairsCobblestone.blockID,2,2);
            par1World.setBlock(x-12,y,z+3,Block.stairsCobblestone.blockID,2,2);
            par1World.setBlock(x-3,y,z-6,Block.stairsCobblestone.blockID,5,2);
            par1World.setBlock(x-3,y,z-8,Block.stairsCobblestone.blockID,5,2);
            par1World.setBlock(x-3,y,z-10,Block.stairsCobblestone.blockID,5,2);
            par1World.setBlock(x-3,y,z-12,Block.stairsCobblestone.blockID,5,2);
            par1World.setBlock(x-6,y,z-3,Block.stairsCobblestone.blockID,3,2);
            par1World.setBlock(x-8,y,z-3,Block.stairsCobblestone.blockID,3,2);
            par1World.setBlock(x-10,y,z-4,Block.stairsCobblestone.blockID,4,2);
            par1World.setBlock(x-13,y,z-4,Block.stairsCobblestone.blockID,5,2);
            par1World.setBlock(x-6,y,z-8,Block.stairsCobblestone.blockID,3,2);
            par1World.setBlock(x-7,y,z-9,Block.stairsCobblestone.blockID,4,2);
            par1World.setBlock(x-8,y,z-9,Block.stairsCobblestone.blockID,5,2);
            par1World.setBlock(x+3,y,z-6,Block.stairsCobblestone.blockID,4,2);
            par1World.setBlock(x+3,y,z-8,Block.stairsCobblestone.blockID,4,2);
            par1World.setBlock(x+3,y,z-10,Block.stairsCobblestone.blockID,4,2);
            par1World.setBlock(x+3,y,z-12,Block.stairsCobblestone.blockID,4,2);
            par1World.setBlock(x+6,y,z-3,Block.stairsCobblestone.blockID,3,2);
            par1World.setBlock(x+8,y,z-3,Block.stairsCobblestone.blockID,3,2);
            par1World.setBlock(x+10,y,z-3,Block.stairsCobblestone.blockID,3,2);
            par1World.setBlock(x+12,y,z-3,Block.stairsCobblestone.blockID,3,2);
            par1World.setBlock(x+4,y,z+4,Block.cobblestoneWall.blockID);
            par1World.setBlock(x-4,y,z-4,Block.cobblestoneWall.blockID);
            fillBlocksWithMetadata(par1World,x-13,x-12,y,y,z+5,z+5,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x-13,x-12,y,y,z+6,z+6,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x+12,x+13,y,y,z-5,z-5,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x+12,x+13,y,y,z-6,z-6,Block.stoneSingleSlab.blockID,11);
            par1World.setBlock(x+10,y,z+9,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x+11,y,z+9,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x+10,y,z+10,Block.stoneSingleSlab.blockID,11,2);
            par1World.setBlock(x-10,y,z-9,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x-11,y,z-9,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x-10,y,z-10,Block.stoneSingleSlab.blockID,11,2);
            fillBlocksWithMetadata(par1World,x+23,x+24,y,y,z+17,z+17,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x+17,x+17,y,y,z+23,z+24,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-24,x-23,y,y,z-17,z-17,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-17,x-17,y,y,z-24,z-23,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-24,x-23,y,y,z+17,z+17,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-17,x-17,y,y,z+23,z+24,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x+23,x+24,y,y,z-17,z-17,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x+17,x+17,y,y,z-24,z-23,Block.stoneSingleSlab.blockID,11);
            par1World.setBlock(x+23,y,z+16,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x+16,y,z+23,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x-23,y,z-16,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x-16,y,z-23,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x-23,y,z+16,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x-16,y,z+23,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x+23,y,z-16,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x+16,y,z-23,Block.stoneSingleSlab.blockID,3,2);
            y++;

            par1World.setBlock(x+11,y,z+4,Block.stairsCobblestone.blockID,5,2);
            par1World.setBlock(x+12,y,z+4,Block.stairsCobblestone.blockID,4,2);
            par1World.setBlock(x+6,y,z+5,Block.stairsCobblestone.blockID,0,2);
            par1World.setBlock(x+4,y,z+4,Block.cobblestoneWall.blockID);
            par1World.setBlock(x+3,y,z+6,Block.cobblestoneWall.blockID);
            par1World.setBlock(x+3,y,z+8,Block.cobblestoneWall.blockID);
            par1World.setBlock(x+3,y,z+10,Block.cobblestoneWall.blockID);
            par1World.setBlock(x+3,y,z+12,Block.cobblestoneWall.blockID);
            par1World.setBlock(x+6,y,z+3,Block.cobblestoneWall.blockID);
            par1World.setBlock(x+8,y,z+3,Block.cobblestoneWall.blockID);
            fillBlocksWithMetadata(par1World,x+8,x+8,y,y,z+10,z+12,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x+9,x+9,y,y,z+10,z+11,Block.stoneSingleSlab.blockID,3);
            par1World.setBlock(x-3,y,z+6,Block.cobblestoneWall.blockID);
            par1World.setBlock(x-3,y,z+8,Block.cobblestoneWall.blockID);
            par1World.setBlock(x-3,y,z+10,Block.cobblestoneWall.blockID);
            par1World.setBlock(x-3,y,z+12,Block.cobblestoneWall.blockID);
            par1World.setBlock(x-6,y,z+3,Block.cobblestoneWall.blockID);
            par1World.setBlock(x-8,y,z+3,Block.cobblestoneWall.blockID);
            par1World.setBlock(x-10,y,z+3,Block.cobblestoneWall.blockID);
            par1World.setBlock(x-12,y,z+3,Block.cobblestoneWall.blockID);
            par1World.setBlock(x-12,y,z+7,Block.stoneSingleSlab.blockID,3,2);
            fillBlocksWithMetadata(par1World,x-11,x-12,y,y,z+8,z+8,Block.stoneSingleSlab.blockID,11);
            par1World.setBlock(x-11,y,z-4,Block.stairsCobblestone.blockID,4,2);
            par1World.setBlock(x-12,y,z-4,Block.stairsCobblestone.blockID,5,2);
            par1World.setBlock(x-6,y,z-5,Block.stairsCobblestone.blockID,1,2);
            par1World.setBlock(x-4,y,z-4,Block.cobblestoneWall.blockID);
            par1World.setBlock(x-3,y,z-6,Block.cobblestoneWall.blockID);
            par1World.setBlock(x-3,y,z-8,Block.cobblestoneWall.blockID);
            par1World.setBlock(x-3,y,z-10,Block.cobblestoneWall.blockID);
            par1World.setBlock(x-3,y,z-12,Block.cobblestoneWall.blockID);
            par1World.setBlock(x-6,y,z-3,Block.cobblestoneWall.blockID);
            par1World.setBlock(x-8,y,z-3,Block.cobblestoneWall.blockID);
            fillBlocksWithMetadata(par1World,x-8,x-8,y,y,z-10,z-12,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-9,x-9,y,y,z-10,z-11,Block.stoneSingleSlab.blockID,3);
            par1World.setBlock(x+3,y,z-6,Block.cobblestoneWall.blockID);
            par1World.setBlock(x+3,y,z-8,Block.cobblestoneWall.blockID);
            par1World.setBlock(x+3,y,z-10,Block.cobblestoneWall.blockID);
            par1World.setBlock(x+3,y,z-12,Block.cobblestoneWall.blockID);
            par1World.setBlock(x+6,y,z-3,Block.cobblestoneWall.blockID);
            par1World.setBlock(x+8,y,z-3,Block.cobblestoneWall.blockID);
            par1World.setBlock(x+10,y,z-3,Block.cobblestoneWall.blockID);
            par1World.setBlock(x+12,y,z-3,Block.cobblestoneWall.blockID);
            par1World.setBlock(x+12,y,z-7,Block.stoneSingleSlab.blockID,3,2);
            fillBlocksWithMetadata(par1World,x+11,x+12,y,y,z-8,z-8,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x+23,x+24,y,y,z+19,z+19,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x+23,x+24,y,y,z+18,z+18,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x+19,x+19,y,y,z+23,z+24,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x+18,x+18,y,y,z+23,z+24,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x-24,x-23,y,y,z-19,z-19,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-24,x-23,y,y,z-18,z-18,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x-19,x-19,y,y,z-24,z-23,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-18,x-18,y,y,z-24,z-23,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x+23,x+24,y,y,z-19,z-19,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x+23,x+24,y,y,z-18,z-18,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x+19,x+19,y,y,z-24,z-23,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x+18,x+18,y,y,z-24,z-23,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x-24,x-23,y,y,z+19,z+19,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-24,x-23,y,y,z+18,z+18,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x-19,x-19,y,y,z+23,z+24,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-18,x-18,y,y,z+23,z+24,Block.stoneSingleSlab.blockID,3);
            y++;

            par1World.setBlock(x+4,y,z+4,Block.cobblestoneWall.blockID);
            par1World.setBlock(x+6,y,z+3,Block.torchWood.blockID,5,2);
            par1World.setBlock(x+8,y,z+3,Block.torchWood.blockID,5,2);
            par1World.setBlock(x+3,y,z+6,Block.torchWood.blockID,5,2);
            par1World.setBlock(x+3,y,z+8,Block.torchWood.blockID,5,2);
            par1World.setBlock(x+3,y,z+10,Block.torchWood.blockID,5,2);
            par1World.setBlock(x+3,y,z+12,Block.torchWood.blockID,5,2);
            fillBlocksWithMetadata(par1World,x+6,x+6,y,y,z+10,z+13,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x+7,x+7,y,y,z+10,z+12,Block.stoneSingleSlab.blockID,3);
            par1World.setBlock(x-3,y,z+6,Block.torchWood.blockID,5,2);
            par1World.setBlock(x-3,y,z+8,Block.torchWood.blockID,5,2);
            par1World.setBlock(x-3,y,z+10,Block.torchWood.blockID,5,2);
            par1World.setBlock(x-3,y,z+12,Block.torchWood.blockID,5,2);
            par1World.setBlock(x-6,y,z+3,Block.torchWood.blockID,5,2);
            par1World.setBlock(x-8,y,z+3,Block.torchWood.blockID,5,2);
            par1World.setBlock(x-10,y,z+3,Block.torchWood.blockID,5,2);
            par1World.setBlock(x-12,y,z+3,Block.torchWood.blockID,5,2);
            fillBlocksWithMetadata(par1World,x-10,x-11,y,y,z+9,z+9,Block.stoneSingleSlab.blockID,3);
            par1World.setBlock(x-10,y,z+10,Block.stoneSingleSlab.blockID,11,2);
            par1World.setBlock(x-4,y,z-4,Block.cobblestoneWall.blockID);
            par1World.setBlock(x-6,y,z-3,Block.torchWood.blockID,5,2);
            par1World.setBlock(x-8,y,z-3,Block.torchWood.blockID,5,2);
            par1World.setBlock(x-3,y,z-6,Block.torchWood.blockID,5,2);
            par1World.setBlock(x-3,y,z-8,Block.torchWood.blockID,5,2);
            par1World.setBlock(x-3,y,z-10,Block.torchWood.blockID,5,2);
            par1World.setBlock(x-3,y,z-12,Block.torchWood.blockID,5,2);
            fillBlocksWithMetadata(par1World,x-6,x-6,y,y,z-10,z-13,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-7,x-7,y,y,z-10,z-12,Block.stoneSingleSlab.blockID,3);
            par1World.setBlock(x+3,y,z-6,Block.torchWood.blockID,5,2);
            par1World.setBlock(x+3,y,z-8,Block.torchWood.blockID,5,2);
            par1World.setBlock(x+3,y,z-10,Block.torchWood.blockID,5,2);
            par1World.setBlock(x+3,y,z-12,Block.torchWood.blockID,5,2);
            par1World.setBlock(x+6,y,z-3,Block.torchWood.blockID,5,2);
            par1World.setBlock(x+8,y,z-3,Block.torchWood.blockID,5,2);
            par1World.setBlock(x+10,y,z-3,Block.torchWood.blockID,5,2);
            par1World.setBlock(x+12,y,z-3,Block.torchWood.blockID,5,2);
            fillBlocksWithMetadata(par1World,x+10,x+11,y,y,z-9,z-9,Block.stoneSingleSlab.blockID,3);
            par1World.setBlock(x+10,y,z-10,Block.stoneSingleSlab.blockID,11,2);
            fillBlocksWithMetadata(par1World,x+21,x+21,y,y,z+23,z+24,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x+20,x+20,y,y,z+23,z+24,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x+23,x+24,y,y,z+20,z+20,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x+23,x+24,y,y,z+21,z+21,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x-21,x-21,y,y,z-24,z-23,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-20,x-20,y,y,z-24,z-23,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x-24,x-23,y,y,z-20,z-20,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-24,x-23,y,y,z-21,z-21,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x-21,x-21,y,y,z+23,z+24,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-20,x-20,y,y,z+23,z+24,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x-24,x-23,y,y,z+20,z+20,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-24,x-23,y,y,z+21,z+21,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x+21,x+21,y,y,z-24,z-23,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x+20,x+20,y,y,z-24,z-23,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x+23,x+24,y,y,z-20,z-20,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x+23,x+24,y,y,z-21,z-21,Block.stoneSingleSlab.blockID,3);
            y++;

            par1World.setBlock(x+4,y,z+4,Block.stairsCobblestone.blockID,4,2);
            par1World.setBlock(x+4,y,z+5,Block.stairsCobblestone.blockID,4,2);
            par1World.setBlock(x+5,y,z+4,Block.stairsCobblestone.blockID,6,2);
            par1World.setBlock(x+6,y,z+3,Block.stairsCobblestone.blockID,6,2);
            par1World.setBlock(x+8,y,z+3,Block.stairsCobblestone.blockID,6,2);
            par1World.setBlock(x+8,y,z+5,Block.stairsCobblestone.blockID,0,2);
            par1World.setBlock(x+3,y,z+6,Block.stairsCobblestone.blockID,0,2);
            par1World.setBlock(x+3,y,z+8,Block.stairsCobblestone.blockID,0,2);
            par1World.setBlock(x+3,y,z+10,Block.stairsCobblestone.blockID,0,2);
            par1World.setBlock(x+3,y,z+12,Block.stairsCobblestone.blockID,0,2);
            fillBlocksWithMetadata(par1World,x+5,x+5,y,y,z+10,z+13,Block.stoneSingleSlab.blockID,3);
            par1World.setBlock(x+23,y,z+23,Block.stoneSingleSlab.blockID,11,2);
            par1World.setBlock(x+22,y,z+23,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x+23,y,z+22,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x-3,y,z+6,Block.stairsCobblestone.blockID,1,2);
            par1World.setBlock(x-3,y,z+8,Block.stairsCobblestone.blockID,1,2);
            par1World.setBlock(x-3,y,z+10,Block.stairsCobblestone.blockID,1,2);
            par1World.setBlock(x-3,y,z+12,Block.stairsCobblestone.blockID,1,2);
            par1World.setBlock(x-6,y,z+3,Block.stairsCobblestone.blockID,6,2);
            par1World.setBlock(x-8,y,z+3,Block.stairsCobblestone.blockID,6,2);
            par1World.setBlock(x-10,y,z+3,Block.stairsCobblestone.blockID,6,2);
            par1World.setBlock(x-12,y,z+3,Block.stairsCobblestone.blockID,6,2);
            fillBlocksWithMetadata(par1World,x-9,x-9,y,y,z+10,z+11,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x-8,x-8,y,y,z+11,z+12,Block.stoneSingleSlab.blockID,11);
            par1World.setBlock(x-23,y,z+23,Block.stoneSingleSlab.blockID,11,2);
            par1World.setBlock(x-22,y,z+23,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x-23,y,z+22,Block.stoneSingleSlab.blockID,3,2);

            par1World.setBlock(x-4,y,z-4,Block.stairsCobblestone.blockID,5,2);
            par1World.setBlock(x-4,y,z-5,Block.stairsCobblestone.blockID,5,2);
            par1World.setBlock(x-5,y,z-4,Block.stairsCobblestone.blockID,7,2);
            par1World.setBlock(x-6,y,z-3,Block.stairsCobblestone.blockID,7,2);
            par1World.setBlock(x-8,y,z-3,Block.stairsCobblestone.blockID,7,2);
            par1World.setBlock(x-8,y,z-5,Block.stairsCobblestone.blockID,1,2);
            par1World.setBlock(x-3,y,z-6,Block.stairsCobblestone.blockID,1,2);
            par1World.setBlock(x-3,y,z-8,Block.stairsCobblestone.blockID,1,2);
            par1World.setBlock(x-3,y,z-10,Block.stairsCobblestone.blockID,1,2);
            par1World.setBlock(x-3,y,z-12,Block.stairsCobblestone.blockID,1,2);
            fillBlocksWithMetadata(par1World,x-5,x-5,y,y,z-10,z-13,Block.stoneSingleSlab.blockID,3);
            par1World.setBlock(x-23,y,z-23,Block.stoneSingleSlab.blockID,11,2);
            par1World.setBlock(x-22,y,z-23,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x-23,y,z-22,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x+3,y,z-6,Block.stairsCobblestone.blockID,0,2);
            par1World.setBlock(x+3,y,z-8,Block.stairsCobblestone.blockID,0,2);
            par1World.setBlock(x+3,y,z-10,Block.stairsCobblestone.blockID,0,2);
            par1World.setBlock(x+3,y,z-12,Block.stairsCobblestone.blockID,0,2);
            par1World.setBlock(x+6,y,z-3,Block.stairsCobblestone.blockID,7,2);
            par1World.setBlock(x+8,y,z-3,Block.stairsCobblestone.blockID,7,2);
            par1World.setBlock(x+10,y,z-3,Block.stairsCobblestone.blockID,7,2);
            par1World.setBlock(x+12,y,z-3,Block.stairsCobblestone.blockID,7,2);
            fillBlocksWithMetadata(par1World,x+9,x+9,y,y,z-10,z-11,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x+8,x+8,y,y,z-11,z-12,Block.stoneSingleSlab.blockID,11);
            par1World.setBlock(x+23,y,z-23,Block.stoneSingleSlab.blockID,11,2);
            par1World.setBlock(x+22,y,z-23,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x+23,y,z-22,Block.stoneSingleSlab.blockID,3,2);
            y++;

            par1World.setBlock(x+21,y,z+21,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x+22,y,z+21,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x+21,y,z+22,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x-21,y,z+21,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x-22,y,z+21,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x-21,y,z+22,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x+21,y,z-21,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x+22,y,z-21,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x+21,y,z-22,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x-21,y,z-21,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x-22,y,z-21,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x-21,y,z-22,Block.stoneSingleSlab.blockID,3,2);
            fillBlocksWithMetadata(par1World,x+2,x+2,y,y,z+10,z+14,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x+3,x+3,y,y,z+10,z+14,Block.stoneSingleSlab.blockID,3);
            par1World.setBlock(x+7,y,z+8,Block.stairsCobblestone.blockID,1,2);
            fillBlocksWithMetadata(par1World,x-6,x-6,y,y,z+12,z+13,Block.stoneSingleSlab.blockID,11);
            par1World.setBlock(x-7,y,z+12,Block.stoneSingleSlab.blockID,3,2);
            fillBlocksWithMetadata(par1World,x-2,x-2,y,y,z-10,z-14,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-3,x-3,y,y,z-10,z-14,Block.stoneSingleSlab.blockID,3);
            par1World.setBlock(x-7,y,z-8,Block.stairsCobblestone.blockID,0,2);
            fillBlocksWithMetadata(par1World,x+6,x+6,y,y,z-12,z-13,Block.stoneSingleSlab.blockID,11);
            par1World.setBlock(x+7,y,z-12,Block.stoneSingleSlab.blockID,3,2);
            y++;

            par1World.setBlock(x+6,y,z+8,Block.stairsCobblestone.blockID,1,2);
            fillBlocksWithMetadata(par1World,x+1,x+1,y,y,z+10,z+14,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x,x-3,y,y,z+10,z+14,Block.stoneSingleSlab.blockID,11);
            par1World.setBlock(x-5,y,z+13,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x-6,y,z-8,Block.stairsCobblestone.blockID,0,2);
            fillBlocksWithMetadata(par1World,x-1,x-1,y,y,z-10,z-14,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x,x+3,y,y,z-10,z-14,Block.stoneSingleSlab.blockID,11);
            par1World.setBlock(x+5,y,z-13,Block.stoneSingleSlab.blockID,3,2);
            y++;

            par1World.setBlock(x+19,y,z+19,Block.mobSpawner.blockID);
            var18 = (TileEntityMobSpawner)par1World.getBlockTileEntity(x + 19, y, z + 19);
            if (var18 != null) {
                var18.getSpawnerLogic().setMobID("Longdead");
            } else {
                System.err.println("Failed to fetch mob spawner entity at (" + (x + 19) + ", " + y + ", " + (z + 19) + ")");
            }
            par1World.setBlock(x-19,y,z-19,Block.mobSpawner.blockID);
            var18 = (TileEntityMobSpawner)par1World.getBlockTileEntity(x - 19, y, z - 19);
            if (var18 != null) {
                var18.getSpawnerLogic().setMobID("Longdead");
            } else {
                System.err.println("Failed to fetch mob spawner entity at (" + (x - 19) + ", " + y + ", " + (z - 19) + ")");
            }
            par1World.setBlock(x-19,y,z+19,Block.mobSpawner.blockID);
            var18 = (TileEntityMobSpawner)par1World.getBlockTileEntity(x - 19, y, z + 19);
            if (var18 != null) {
                var18.getSpawnerLogic().setMobID("Longdead");
            } else {
                System.err.println("Failed to fetch mob spawner entity at (" + (x - 19) + ", " + y + ", " + (z + 19) + ")");
            }
            par1World.setBlock(x+19,y,z-19,Block.mobSpawner.blockID);
            var18 = (TileEntityMobSpawner)par1World.getBlockTileEntity(x + 19, y, z - 19);
            if (var18 != null) {
                var18.getSpawnerLogic().setMobID("Longdead");
            } else {
                System.err.println("Failed to fetch mob spawner entity at (" + (x + 19) + ", " + y + ", " + (z - 19) + ")");
            }
            par1World.setBlock(x+5,y,z+8,Block.stairsCobblestone.blockID,1,2);
            par1World.setBlock(x-5,y,z-8,Block.stairsCobblestone.blockID,0,2);
            par1World.setBlock(x-5,y,z+6,Block.stairsCobblestone.blockID,0,2);
            par1World.setBlock(x-5,y,z+7,Block.stairsCobblestone.blockID,0,2);
            par1World.setBlock(x-7,y,z+6,Block.mobSpawner.blockID);
            var18 = (TileEntityMobSpawner)par1World.getBlockTileEntity(x - 7, y, z + 6);
            if (var18 != null) {
                var18.getSpawnerLogic().setMobID("LongdeadGuardian");
            } else {
                System.err.println("Failed to fetch mob spawner entity at (" + (x - 7) + ", " + y + ", " + (z + 6) + ")");
            }
            par1World.setBlock(x-5,y,z-8,Block.stairsCobblestone.blockID,0,2);
            par1World.setBlock(x+5,y,z+8,Block.stairsCobblestone.blockID,1,2);
            par1World.setBlock(x+5,y,z-6,Block.stairsCobblestone.blockID,1,2);
            par1World.setBlock(x+5,y,z-7,Block.stairsCobblestone.blockID,1,2);
            par1World.setBlock(x+7,y,z-6,Block.mobSpawner.blockID);
            var18 = (TileEntityMobSpawner)par1World.getBlockTileEntity(x + 7, y, z - 6);
            if (var18 != null) {
                var18.getSpawnerLogic().setMobID("LongdeadGuardian");
            } else {
                System.err.println("Failed to fetch mob spawner entity at (" + (x + 7) + ", " + y + ", " + (z - 6) + ")");
            }
            y++;
            EntityLich var11 = new EntityLich(par1World);
            var11.setLocationAndAngles((double)x + 0.5, (double)y+2, (double)z + 0.5, 0.0F, 0.0F);
            var11.onSpawnWithEgg(null);
            var11.func_110163_bv();
            par1World.spawnEntityInWorld(var11);
            par1World.setBlock(x+6,y,z,Block.mobSpawner.blockID);
            var18 = (TileEntityMobSpawner)par1World.getBlockTileEntity(x + 6, y, z);
            if (var18 != null) {
                var18.getSpawnerLogic().setMobID("LongdeadGuardian");
            } else {
                System.err.println("Failed to fetch mob spawner entity at (" + (x + 6) + ", " + y + ", " + (z) + ")");
            }
            par1World.setBlock(x-6,y,z,Block.mobSpawner.blockID);
            var18 = (TileEntityMobSpawner)par1World.getBlockTileEntity(x - 6, y, z);
            if (var18 != null) {
                var18.getSpawnerLogic().setMobID("LongdeadGuardian");
            } else {
                System.err.println("Failed to fetch mob spawner entity at (" + (x - 6) + ", " + y + ", " + (z) + ")");
            }
            par1World.setBlock(x+13,y,z,Block.chestAncientMetal.blockID,Block.chestAncientMetal.getMetadataForDirectionFacing(0, getRandomDirection(random)),2);
            var17 = (TileEntityChest)par1World.getBlockTileEntity(x + 13, y, z);
            if (var17 != null) {
                StructurePieceTreasure.generateChestContents(par1World, y, random, var16, var17, 8, null);
            }
            par1World.setBlock(x-13,y,z,Block.chestAncientMetal.blockID,Block.chestAncientMetal.getMetadataForDirectionFacing(0, getRandomDirection(random)),2);
            var17 = (TileEntityChest)par1World.getBlockTileEntity(x - 13, y, z);
            if (var17 != null) {
                StructurePieceTreasure.generateChestContents(par1World, y, random, var16, var17, 8, null);
            }
            fillBlocksWithMetadata(par1World,x+7,x+7,y,y,z+2,z+3,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x+8,x+8,y,y,z+2,z+3,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-7,x-7,y,y,z+2,z+3,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x-8,x-8,y,y,z+2,z+3,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x+7,x+7,y,y,z-2,z-3,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x+8,x+8,y,y,z-2,z-3,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-7,x-7,y,y,z-2,z-3,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x-8,x-8,y,y,z-2,z-3,Block.stoneSingleSlab.blockID,11);
            y++;

            fillBlocksWithMetadata(par1World,x+9,x+9,y,y,z+2,z+3,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x+10,x+10,y,y,z+2,z+3,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-9,x-9,y,y,z+2,z+3,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x-10,x-10,y,y,z+2,z+3,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x+9,x+9,y,y,z-2,z-3,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x+10,x+10,y,y,z-2,z-3,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-9,x-9,y,y,z-2,z-3,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x-10,x-10,y,y,z-2,z-3,Block.stoneSingleSlab.blockID,11);
            par1World.setBlock(x-4,y,z+10,Block.stairsCobblestone.blockID,7,2);
            par1World.setBlock(x-4,y,z+13,Block.stairsCobblestone.blockID,6,2);
            par1World.setBlock(x+4,y,z-10,Block.stairsCobblestone.blockID,6,2);
            par1World.setBlock(x+4,y,z-13,Block.stairsCobblestone.blockID,7,2);
            y++;

            fillBlocksWithMetadata(par1World,x+11,x+11,y,y,z+2,z+3,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x+12,x+12,y,y,z+2,z+3,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-11,x-11,y,y,z+2,z+3,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x-12,x-12,y,y,z+2,z+3,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x+11,x+11,y,y,z-2,z-3,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x+12,x+12,y,y,z-2,z-3,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-11,x-11,y,y,z-2,z-3,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x-12,x-12,y,y,z-2,z-3,Block.stoneSingleSlab.blockID,11);
            par1World.setBlock(x-4,y,z+11,Block.stairsCobblestone.blockID,7,2);
            par1World.setBlock(x-4,y,z+12,Block.stairsCobblestone.blockID,6,2);
            par1World.setBlock(x+4,y,z-11,Block.stairsCobblestone.blockID,6,2);
            par1World.setBlock(x+4,y,z-12,Block.stairsCobblestone.blockID,7,2);
            par1World.setBlock(x+19,y,z+13,Block.stairsCobblestone.blockID,3,2);
            par1World.setBlock(x+13,y,z+19,Block.stairsCobblestone.blockID,1,2);
            par1World.setBlock(x-19,y,z+13,Block.stairsCobblestone.blockID,3,2);
            par1World.setBlock(x-13,y,z+19,Block.stairsCobblestone.blockID,0,2);
            par1World.setBlock(x-19,y,z-13,Block.stairsCobblestone.blockID,2,2);
            par1World.setBlock(x-13,y,z-19,Block.stairsCobblestone.blockID,0,2);
            par1World.setBlock(x+19,y,z-13,Block.stairsCobblestone.blockID,2,2);
            par1World.setBlock(x+13,y,z-19,Block.stairsCobblestone.blockID,1,2);
            y++;

            fillBlocksWithMetadata(par1World,x+13,x+13,y,y,z+2,z+3,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x+14,x+14,y,y,z+2,z+3,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-13,x-13,y,y,z+2,z+3,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x-14,x-14,y,y,z+2,z+3,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x+13,x+13,y,y,z-2,z-3,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x+14,x+14,y,y,z-2,z-3,Block.stoneSingleSlab.blockID,11);
            fillBlocksWithMetadata(par1World,x-13,x-13,y,y,z-2,z-3,Block.stoneSingleSlab.blockID,3);
            fillBlocksWithMetadata(par1World,x-14,x-14,y,y,z-2,z-3,Block.stoneSingleSlab.blockID,11);
            par1World.setBlock(x,y,z + 9,Block.mobSpawner.blockID);
            var18 = (TileEntityMobSpawner)par1World.getBlockTileEntity(x, y, z + 9);
            if (var18 != null) {
                var18.getSpawnerLogic().setMobID("LongdeadGuardian");
            } else {
                System.err.println("Failed to fetch mob spawner entity at (" + (x) + ", " + y + ", " + (z + 9) + ")");
            }
            par1World.setBlock(x,y,z - 9,Block.mobSpawner.blockID);
            var18 = (TileEntityMobSpawner)par1World.getBlockTileEntity(x, y, z - 9);
            if (var18 != null) {
                var18.getSpawnerLogic().setMobID("LongdeadGuardian");
            } else {
                System.err.println("Failed to fetch mob spawner entity at (" + (x) + ", " + y + ", " + (z - 9) + ")");
            }
            par1World.setBlock(x+5,y,z+8,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x+6,y,z+8,Block.stoneSingleSlab.blockID,11,2);
            par1World.setBlock(x-5,y,z+8,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x-6,y,z+8,Block.stoneSingleSlab.blockID,11,2);
            par1World.setBlock(x+5,y,z-8,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x+6,y,z-8,Block.stoneSingleSlab.blockID,11,2);
            par1World.setBlock(x-5,y,z-8,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x-6,y,z-8,Block.stoneSingleSlab.blockID,11,2);
            par1World.setBlock(x+4,y,z+2,Block.stairsCobblestone.blockID,0,2);
            par1World.setBlock(x+4,y,z,Block.stairsCobblestone.blockID,0,2);
            par1World.setBlock(x+4,y,z-2,Block.stairsCobblestone.blockID,0,2);
            par1World.setBlock(x+2,y,z-4,Block.stairsCobblestone.blockID,3,2);
            par1World.setBlock(x,y,z-4,Block.stairsCobblestone.blockID,3,2);
            par1World.setBlock(x-2,y,z-4,Block.stairsCobblestone.blockID,3,2);
            par1World.setBlock(x-4,y,z+2,Block.stairsCobblestone.blockID,1,2);
            par1World.setBlock(x-4,y,z,Block.stairsCobblestone.blockID,1,2);
            par1World.setBlock(x-4,y,z-2,Block.stairsCobblestone.blockID,1,2);
            par1World.setBlock(x+2,y,z+4,Block.stairsCobblestone.blockID,2,2);
            par1World.setBlock(x,y,z+4,Block.stairsCobblestone.blockID,2,2);
            par1World.setBlock(x-2,y,z+4,Block.stairsCobblestone.blockID,2,2);
            y++;

            par1World.setBlock(x+7,y,z+8,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x+8,y,z+8,Block.stoneSingleSlab.blockID,11,2);
            par1World.setBlock(x-7,y,z+8,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x-8,y,z+8,Block.stoneSingleSlab.blockID,11,2);
            par1World.setBlock(x+7,y,z-8,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x+8,y,z-8,Block.stoneSingleSlab.blockID,11,2);
            par1World.setBlock(x-7,y,z-8,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x-8,y,z-8,Block.stoneSingleSlab.blockID,11,2);
            y++;

            par1World.setBlock(x+8,y,z+7,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x-8,y,z+7,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x+8,y,z-7,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x-8,y,z-7,Block.stoneSingleSlab.blockID,3,2);
            par1World.setBlock(x,y,z+15,Block.stairsCobblestone.blockID,3,2);
            par1World.setBlock(x,y,z-15,Block.stairsCobblestone.blockID,2,2);
            par1World.setBlock(x+15,y,z,Block.stairsCobblestone.blockID,1,2);
            par1World.setBlock(x-15,y,z,Block.stairsCobblestone.blockID,0,2);
            y++;

            par1World.setBlock(x+5,y,z+2,Block.stairsCobblestone.blockID,3,2);
            par1World.setBlock(x+2,y,z+5,Block.stairsCobblestone.blockID,1,2);
            par1World.setBlock(x-5,y,z+2,Block.stairsCobblestone.blockID,3,2);
            par1World.setBlock(x-2,y,z+5,Block.stairsCobblestone.blockID,0,2);
            par1World.setBlock(x-5,y,z-2,Block.stairsCobblestone.blockID,2,2);
            par1World.setBlock(x-2,y,z-5,Block.stairsCobblestone.blockID,0,2);
            par1World.setBlock(x+5,y,z-2,Block.stairsCobblestone.blockID,2,2);
            par1World.setBlock(x+2,y,z-5,Block.stairsCobblestone.blockID,1,2);
            y++;

            par1World.setBlock(x+5,y,z+1,Block.stairsCobblestone.blockID,3,2);
            par1World.setBlock(x+1,y,z+5,Block.stairsCobblestone.blockID,1,2);
            par1World.setBlock(x-5,y,z+1,Block.stairsCobblestone.blockID,3,2);
            par1World.setBlock(x-1,y,z+5,Block.stairsCobblestone.blockID,0,2);
            par1World.setBlock(x-5,y,z-1,Block.stairsCobblestone.blockID,2,2);
            par1World.setBlock(x-1,y,z-5,Block.stairsCobblestone.blockID,0,2);
            par1World.setBlock(x+5,y,z-1,Block.stairsCobblestone.blockID,2,2);
            par1World.setBlock(x+1,y,z-5,Block.stairsCobblestone.blockID,1,2);
            par1World.setBlock(x,y,z+5,Block.stairsCobblestone.blockID,6,2);
            par1World.setBlock(x+5,y,z,Block.stairsCobblestone.blockID,4,2);
            par1World.setBlock(x,y,z+5,Block.stairsCobblestone.blockID,7,2);
            par1World.setBlock(x+5,y,z,Block.stairsCobblestone.blockID,5,2);
            y++;

            y++;

            par1World.setBlock(x+1,y,z+6,Block.fenceAncientMetal.blockID);
            par1World.setBlock(x+6,y,z+1,Block.fenceAncientMetal.blockID);
            par1World.setBlock(x-1,y,z+6,Block.fenceAncientMetal.blockID);
            par1World.setBlock(x-6,y,z+1,Block.fenceAncientMetal.blockID);
            par1World.setBlock(x+1,y,z-6,Block.fenceAncientMetal.blockID);
            par1World.setBlock(x+6,y,z-1,Block.fenceAncientMetal.blockID);
            par1World.setBlock(x-1,y,z-6,Block.fenceAncientMetal.blockID);
            par1World.setBlock(x-6,y,z-1,Block.fenceAncientMetal.blockID);
            y++;

            par1World.setBlock(x+1,y,z+6,Block.fenceAncientMetal.blockID);
            par1World.setBlock(x+6,y,z+1,Block.fenceAncientMetal.blockID);
            par1World.setBlock(x-1,y,z+6,Block.fenceAncientMetal.blockID);
            par1World.setBlock(x-6,y,z+1,Block.fenceAncientMetal.blockID);
            par1World.setBlock(x+1,y,z-6,Block.fenceAncientMetal.blockID);
            par1World.setBlock(x+6,y,z-1,Block.fenceAncientMetal.blockID);
            par1World.setBlock(x-1,y,z-6,Block.fenceAncientMetal.blockID);
            par1World.setBlock(x-6,y,z-1,Block.fenceAncientMetal.blockID);

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
    private int getRandomFence(){
        Random rand = new Random();
        return rand.nextInt(4) == 0 ? Block.fenceAncientMetal.blockID : 0;
    }
    private int getFlitteredBlockID(char index){
        if(index == 'S'){
            return Block.stoneBrick.blockID;
        }else if(index == 'O'){
            return Block.cobblestone.blockID;
        }else if(index == 'M'){
            return Block.mobSpawner.blockID;
        }else if(index == 'B'){
            return Block.chestAncientMetal.blockID;
        }else if(index == 'F'){
            return getRandomFence();
        }else {
            return 0;
        }
    }

    protected void fillBlocksWithMetadata(World world, int FromX, int ToX, int FromY, int ToY, int FromZ, int ToZ, int BlockID, int subtype){
        if(FromX > ToX){
            int temp = ToX;
            ToX = FromX;
            FromX = temp;
        }
        if(FromY > ToY){
            int temp = ToY;
            ToY = FromY;
            FromY = temp;
        }
        if(FromZ > ToZ){
            int temp = ToZ;
            ToZ = FromZ;
            FromZ = temp;
        }
        for(int i = FromX; i <= ToX; i++){
            for(int j = FromY; j <= ToY; j++){
                for(int k = FromZ; k <= ToZ; k++){
                    world.setBlock(i, j, k, BlockID, subtype, 2);
                }
            }
        }
    }
    private static StructurePieceTreasure[] getChestContentsForWorld(World world) {
        return chest_contents_for_underworld_castle;
    }
    static {
        Layer[0]  = "    OOOOO                                 OOOOO      OOOOOOOOO                             OOOOOOOOO   OOOOOOOOOOO                           OOOOOOOOOOO  OOOOOOOOOOO                           OOOOOOOOOOO OOOOOOOOOOOOO                         OOOOOOOOOOOOOOOOOOOOOOOOOOO O O O O O O O O O O O OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO     OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO  OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO   OOOOOOOOOOOOOOOOOOOO     OOOOOOOOOOOOOOOOOOOOOO      OOOOOOOOOOOOOOOOOO     OOOOOOOOOOOOOOOOOOOO         OOOOOOOOOOOOOOOOO     OOOOOOOOOOOOOOOOOOO           OOOOOOOOOOOOOOOO     OOOOOOOOOOOOOOOOOO           OOOOOOOOOOOOOOOOO     OOOOOOOOOOOOOOOOOOO           OOOOOOOOOOOOOOOO     OOOOOOOOOOOOOOOOOO           OOOOOOOOOOOOOOOOO     OOOOOOOOOOOOOOOOOOO           OOOOOOOOOOOOOOOO     OOOOOOOOOOOOOOOOOO           OOOOOOOOOOOOOOOOO     OOOOOOOOOOOOOOOOOOO           OOOOOOOOOOOOOOOO     OOOOOOOOOOOOOOOOOO           OOOOOOOOOOOOOOOOO     OOOOOOOOOOOOOOOOOOO           OOOOO                             OOOOO             OOOO                             OOOO              OOOO                             OOOO              OOOO                             OOOO       ";
        Layer[1]  = "    OOOOO                                 OOOOO      OO     OO                             OO     OO   O         O                           O         O  O O     O O                           O O     O O O           O                         O           OO           OO O O O O       O O O O OO           OO           OOOOOOOOOOO     OOOOOOOOOOO           OO                                                 OO           O                         O           O O O     O O                           O O     O O  O         O          O     O          O         O   OO     OO        OOO       OOO        OO     OO      OOO O        OO  O       O  OO        O OOO         OO         O    O       O    O         OO           O        O     O       O     O        O           OO       O      O       O      O       OO           O      O  O  OOO       O       O      O           OO     O   O    O       O        O     OO           O     O   O    O       O        O     O           OO    O    O    O       O         O    OO           O    O    O    O       O         O    O           OO    O    OOOOOO       OOOOOOOOOOO    OO           O   O                             O   O                                                                                                                                                               ";
        Layer[2]  = "    OOOOO                                 OOOOO      OO     OO                             OO     OO   O         O                           O         O  O O     O O                           O O     O O O           O                         O           OO           OO O O O O       O O O O OO           OO           OOOOOOOOOOO     OOOOOOOOOOO           OO                                                 OO           O                         O           O O O     O O                           O O     O O  O         O          O     O          O         O   OO     OO        OOO       OOO        OO     OO      OOO O        OO  O       O  OO        O OOO         OO         O    O       O    O         OO           O        O     O       O     O        O           OO       O      O       O      O       OO           O      O  O  OOO       O       O      O           OO     O   O    O       O        O     OO           O     O   O    O       O        O     O           OO    O    O    O       O         O    OO           O    O    O                      O    O           OO    O    OOOO           OOOOOOOOO    OO           O   O                             O   O                                                                                                                                                               ";
        Layer[3]  = "    OOOOO                                 OOOOO      OO     OO                             OO     OO   O         O                           O         O  O O     O O                           O O     O O O           O                         O           OO           OO O O O O       O O O O OO           OO           OOOOOOOOOOO     OOOOOOOOOOO           OO           O                         O           OO           O                         O           O O O     O O                           O O     O O  O         O          O     O          O         O   OO     OO        OOO       OOO        OO     OO      OOOOO        OO  O       O  OO        OOOOO         OO         O    O       O    O         OO           O        O     O       O     O        O           OO       O      O       O      O       OO           O      O  O  OOO       O       O      O           OO     O   O   OO       O        O     OO           O     O   O   OO       O        O     O           OO    O    O   OO       O         O    OO           O    O    O   OO       O         O    O           OO    O    OOOOO        OOOOOOOOOOO    OO           O   O                             O   O                                                                                                                                                               ";
        Layer[4]  = "    OOOOO                                 OOOOO      OO     OO                             OO     OO   O         O                           O         O  O O     O O                           O O     O O O           O                         O           OO           O O O O  O       O  O O O O           OO           OOOOOOOOOOO     OOOOOOOOOOO           OO           O                         O           OO           O                         O           O O O     O O                           O O     O O  O         O          O     O          O         O   OO     OO        OOO       OOO        OO     OO      OOOOO        OO  O       O  OO        OOOOO          O         O    O       O    O         O           OO        O     O       O     O        OO           O       O      O       O      O       O           OO      O  OOOOOO       O       O      OO           O     O   O    O       O        O     O           OO     O   O    O       O        O     OO           O    O    O    O       O         O    O            O    O    O    O       O         O    O           OO    OO  OOOOOO        OOOOOOOOOOO    OO           O   O                             O   O                                                                                                                                                               ";
        Layer[5]  = "    OOOOO                                 OOOOO      OO     OO                             OO     OO   O         O                           O         O  O O     O O                           O O     O O O           O                         O           OO           O O O O  O       O  O O O O           OO           OOOOOOOOOOOO   OOOOOOOOOOOO           OO           O                         O           OO           O                         O           O O O     O O                           O O     O O  O         O          OO   OO          O         O   OO     OO        OOO       OOO        OO     OO      OOOOO        OO  O       O  OO        OOOOO          O         O    O       O    O         O           OO        O     O       O     O        OO           O       O      O       O      O       O           OO      O  OOOOOO       O       O      OO           O     O   O    O       O        O     O           OO     O   O    O       O        O     OO           O    O    O    O       O         O    O            O    O    O    O       O         O    O           OO    OO  OOOOOO        OOOOOOOOOOO    OO           O   O                             O   O            O   O                             O   O                                                                                                            ";
        Layer[6]  = "    OOOOO                                 OOOOO      OO     OO                             OO     OO   O         O                           O         O  O O     O O                           O O     O O O           O                         O           OO           O  OOO    O     O    OOO  O           OO           OOOOOOOOOOOOOOOOOOOOOOOOOOO           OO           O                         O           OO           O                         O           O O O     O O                           O O     O O  O         O          OOOOOOO          O         O   OO     OO        OOO       OOO        OO     OO      OOOOO        OO  O       O  OO        OOOOO          O         O    O       O    O         O            O        O     O       O     O        O           OO       O      O       O      O       OO          OO      O  OOOOOO       O       O      OO          OO     O   OOOOOO       O        O     OO           O     O   OOOOOO       O        O     O            O    O    OOOOOO       O         O    O            O    O    O            O         O    O            O    OO  OOOOO         OOOOOOOOOOO    O           OO   O                             O   OO           O   O                             O   O            O   O                             O   O            O   O                             O   O      ";
        Layer[7]  = "    OOOOO                                 OOOOO      OO     OO                             OO     OO   O         O                           O         O  O   O   O O                           O O   O   O O    O      O                         O      O    OO  OOO      O          OOOOO          O      OOO  OO           OOOOOOOOOOOOOOOOOOOOOOOOOOO           OO           O                         O           OO           O                         O           O O O     O O                           O O     O O  O         O          OOOOOOO          O         O   OO     OO        OOO       OOO        OO     OO      OOOOO        OO          O  OO        OOOOO          O         O            O    O         O            O        O             O     O        O            O       O              O      O       O            O      O  OOOOOOO      O       O      O            O     O   O    O       O        O     O            O     O   O    O       O        O     O            O    O    O    O       O         O    O            O    O    O    O       O         O    O            O    OOOOOOOOOOO       OOOOOOOOOOOO   O            O   O                             O   O           OO   O                             O   OO          OO   O                             O   OO          OO   O                             O   OO     ";
        Layer[8]  = "    OOOOO                                 OOOOO      OOOOOOOOO                             OOOOO  OO   O   OOOOOOO                           OOOOOOO   O  O    OOOOOOO                          OOOOOO    O O     OOOOOOO                         OOOOOOO    OOO O   OOOOOOO          OOOOO          OOOOOOO   OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO  OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO   OOOOOOOOOOOOOOOOOOOO       OOOOOOOOOOOOOOOOOOOO      OOOOOOOOOOOOOOO          O  OOOOOOOOOOOOOOO          OOOOOOOOOOO            OO   OOOOOOOOOOO            OOOOOOOOOO             OOOO  OOOOOOOOOO            OOOOOOOOO              OOOOO  OOOOOOOOO            OOOOOOOO  OOOOOOOOO    OOOOOO  OOOOOOOO            OOOOOOO   O    O       OOOOOOOOOOOOOOOO            OOOOOOO   O    O       OOOOOOOOOOOOOOOO            OOOOOO    O    O       OOOOOOOOOOOOOOOO            OOOOOO    O    O       OOOOOOOOOOOOOOOO            OOOOOOOOOOOOOOOO       OOOOOOOOOOOOOOOO            OOOOO                             OOOOO            OOOOO                             OOOOO            OOOOO                             OOOOO            OOOOO                             OOOOO      ";
        Layer[9]  = "    OOOOO                                 OOOOO      OO     OO                             OO     OO   O         O                           O         O  O         O                           O         O O           O                         O           OO           O                         O           OO           OOOOOOOOOOOOOOOOOOOOOOOOOOO           OO           O                         O           OO           O                         O           O O         O          OOOOOOO          O         O  O         O       OOOOOOOOOOOOO       O         O   OO     OO      OOOOO       OOOOO      OO     OO      OOOOO       OOO             OOO       OOOOO          O        OO                 OO        O            O       OO                   OO       O            O      OO                     OO      O            O     OO  OOOOOOOOOOOOOO       OO     O            O    OO   O    OOOOOOOOO        OO    O            O    OO   OOOOOOOOOOOOOO        OO    O            O   OO    OOOOOOOOOOOOOO         OO   O            O   OO    OOOOOOOOOOOOOO         OO   O            O   OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO   O            O  OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO  O            O  OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO  O            O  OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO  O            O  OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO  O      ";
        Layer[10] = "    OOOOO                                 OOOOO      OO     OO                             OO     OO   O         O                           O         O  O         O                           O         O O           O                         O           OO           O                         O           OO           OO O O O O O O O O O O O OO           OO           O                         O           OO           O                         O           O O         O                           O         O  O         O          OOOOOOO          O         O   OO     OO        OOO       OOO        OO     OO      OOOOO        OO             OO        OOOOO          O         O                 O         O                     O                   O                     O       O                     O       O                   O  OOOOOOOOOOOOOO       O                   O     O   O    O       O        O     O                  O   O                     O                  O    O    O                      O    O                 O    O    O       O         O                 O    OOOOOOOOOOO O O O OOOOOOOOOOOO   O                O                             O                O   O          O       O          O   O                O    O                   O    O                O   O          O       O          O   O      ";
        Layer[11] = "    O O O                                 O O O      O       O                             O       O   O         O                           O         O                                                    O           O                         O           O                                                   O           OO O O O O O O O O O O O OO           O            O                         O            O           O                         O           O           O                           O            O         O          OOOOOOO          O         O   O      OO        OOO       OOO        OO      O      O OOO        OO             OO        OOO O          O         O                 O         O                     O                   O                     O       O                     O       O                   O  OOOOOOOOOOOOOO       O                   O     O   O    O       O        O     O                  O   O                     O                  O    O    O                      O    O                 O    O    O       O         O                 O    OOOOOOOOOOO O O O OOOOOOOOOOOO   O                O                             O                O   O          O       O          O   O                O    O                   O    O                O   O          O       O          O   O      ";
        Layer[12] = "                                                                                                                                                                                                                                                                                                                               OOOOOOOOOOOOOOOOOOOOOOOOO                                                                                                                                                                                            OOOOOOO                                         OOOOOOOOOOOOO                                    OOOO OOOOOOOOOOOO                       O         OOOOO OOOOOOO OOOOO         O            O        OOOOOO OOOOOOO OOOOOO        O            O       OOOOOOO OOOOOOOOOOOOOOO       O            O      OOOOOOOOOOOOOOOOO     OOO      O            O     OOOOOOOOOOOOOOOOOOOOOO OOOO     O            O     OOOOOOOOOOOOOOOOOOOOOO OOOO     O            O    OOOOOOOOOOOOOOOOOOOOOOO OOOOO    O            O    OOOOOOOOOOO       OOOOO OOOOO    O            O    OOOOOOOOOOO O O O OOOOOOOOOOOO   O            O   O                             O   O            O   O          O       O          O   O            O   O    O                   O    O   O            O   O          O       O          O   O      ";
        Layer[13] = "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    OOOOOOO                                         OOO       OOO                                    OO  O       O  OO                                 O    O       O    O                               O     O       O     O                             O      O       O      O                           O  OOOOOO       OOOOOO  O                         O   O                 O   O                        O   O                 O   O                       O    O    OOOOOOOOO    O    O                      O    O    OOOOOOOOO    O    O                      OOOOOOOOOOO       OOOOOOOOOOO                     O        OO         OO        O                    O        OO         OO        O                    OOOOOOOOOOO         OOOOOOOOOOO                    OOOOOOOOOOO         OOOOOOOOOOO          ";
        Layer[14] = "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    OOOOOOO                                         OOO       OOO                                    OO             OO                                 O                 O                               O                   O                             O                     O                           O  OOOOOO       OOOOOO  O                         O   O                 O   O                        O   O                 O   O                       O    O    OOOOOOOOO    O    O                      O    O    O       O    O    O                      O    OOOOOO       OOOOOO    O                     O        O           O        O                    O        O           O        O                    O        O           O        O                    O        O           O        O          ";
        Layer[15] = "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    O O O O                                         O O       O O                                     O             O                                  O                 O                               O                   O                                                                               O  OOOOOO       OOOOOO  O                             O    O       O    O                            O   O    O       O    O   O                       O    OOOOOOOOOOOOOOOOOOO    O                           OOOOOOOOOOOOOOOOOOO                           O    OOOOOO       OOOOOO    O                     O        OO         OO        O                             OO         OO                             O        OO         OO        O                             OO         OO                   ";
        Layer[16] = "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                OOOOOO       OOOOOO                                O    O       O    O                                O    O O O O O    O                                O    OOOOOOOOO    O                                O                 O                                OOOO O       O  OOO                                   O           O                                     OO           OO                                     O           O                                     OO           OO                  ";
        Layer[17] = "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                OOOOOO       OOOOOO                                O    O       O    O                                O    O O O O O    O                                O    OOOOOOOOO    O                                O                 O                                OOOO O       O  OOO                                   O           O                                     OO           OO                                     O           O                                     OO           OO                  ";
        Layer[18] = "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                OOOOOO       OOOOOO                                O    O       O    O                                O    O       O    O                                O    OOOOOOOOO    O                                O    O       O    O                                OOOOOO       OOOOOO                                   O           O                                      O           O                                      O           O                                      O           O                   ";
        Layer[19] = "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                O OO O       O OO O                                                                                   O    O       O    O                                O    O O O O O    O                                                                                   O OO O       O OO O                                                                                      O           O                                                                                         O           O                   ";
        Layer[20] = "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                O O O                                                                                                                                                                                                   O           O                                                                                         O           O                   ";
        Layer[21] = "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 OOO                                                                                                                                                                                                                                                       O           O                                      O           O                   ";
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
