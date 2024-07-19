package net.oilcake.mitelros.mixins.block;

import java.util.Random;
import net.minecraft.BitHelper;
import net.minecraft.Block;
import net.minecraft.BlockBreakInfo;
import net.minecraft.BlockLeaves;
import net.minecraft.BlockLeavesBase;
import net.minecraft.ColorizerFoliage;
import net.minecraft.IBlockAccess;
import net.minecraft.Item;
import net.minecraft.Material;
import net.minecraft.RNG;
import net.minecraft.World;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.util.SeasonColorizer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({BlockLeaves.class})
public class BlockLeavesMixin extends BlockLeavesBase {
   @Shadow
   int[] adjacentTreeBlocks;

   protected BlockLeavesMixin(int par1, Material par2Material, boolean par3) {
      super(par1, par2Material, par3);
   }

   @Overwrite
   public boolean updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
      int var6 = par1World.getBlockMetadata(par2, par3, par4);
      int subtype;
      if ((var6 & 8) != 0 && (var6 & 4) == 0) {
         byte var7 = 4;
         subtype = var7 + 1;
         byte var9 = 32;
         int var10 = var9 * var9;
         int var11 = var9 / 2;
         if (this.adjacentTreeBlocks == null) {
            this.adjacentTreeBlocks = new int[var9 * var9 * var9];
         }

         int var12;
         if (par1World.checkChunksExist(par2 - subtype, par3 - subtype, par4 - subtype, par2 + subtype, par3 + subtype, par4 + subtype)) {
            int var13;
            int var14;
            int var15;
            int var12_plus_var11_times_var10_plus_var11;
            int var13_plus_var11_minus_1_times_var10;
            for(var12 = -var7; var12 <= var7; ++var12) {
               var12_plus_var11_times_var10_plus_var11 = (var12 + var11) * var10 + var11;

               for(var13 = -var7; var13 <= var7; ++var13) {
                  var13_plus_var11_minus_1_times_var10 = (var13 + var11) * var9;

                  for(var14 = -var7; var14 <= var7; ++var14) {
                     var15 = par1World.getBlockId(par2 + var12, par3 + var13, par4 + var14);
                     if (var15 == Block.wood.blockID) {
                        this.adjacentTreeBlocks[var12_plus_var11_times_var10_plus_var11 + var13_plus_var11_minus_1_times_var10 + var14] = 0;
                     } else if (var15 == Block.leaves.blockID) {
                        this.adjacentTreeBlocks[var12_plus_var11_times_var10_plus_var11 + var13_plus_var11_minus_1_times_var10 + var14] = -2;
                     } else {
                        this.adjacentTreeBlocks[var12_plus_var11_times_var10_plus_var11 + var13_plus_var11_minus_1_times_var10 + var14] = -1;
                     }
                  }
               }
            }

            for(var12 = 1; var12 <= 4; ++var12) {
               for(var13 = -var7; var13 <= var7; ++var13) {
                  var12_plus_var11_times_var10_plus_var11 = var13 + var11;
                  var13_plus_var11_minus_1_times_var10 = (var12_plus_var11_times_var10_plus_var11 - 1) * var10;
                  int var13_plus_var11_plus_1_times_var10 = (var12_plus_var11_times_var10_plus_var11 + 1) * var10;
                  int var13_plus_var11_times_var10 = var12_plus_var11_times_var10_plus_var11 * var10;

                  for(var14 = -var7; var14 <= var7; ++var14) {
                     int var14_plus_var11 = var14 + var11;
                     int var14_plus_var11_times_var9 = var14_plus_var11 * var9;

                     for(var15 = -var7; var15 <= var7; ++var15) {
                        int index1 = var13_plus_var11_times_var10 + var14_plus_var11_times_var9 + var15 + var11;
                        if (this.adjacentTreeBlocks[index1] == var12 - 1) {
                           int index2 = var13_plus_var11_minus_1_times_var10 + var14_plus_var11_times_var9 + var15 + var11;
                           if (this.adjacentTreeBlocks[index2] == -2) {
                              this.adjacentTreeBlocks[index2] = var12;
                           }

                           index2 = var13_plus_var11_plus_1_times_var10 + var14_plus_var11_times_var9 + var15 + var11;
                           if (this.adjacentTreeBlocks[index2] == -2) {
                              this.adjacentTreeBlocks[index2] = var12;
                           }

                           index2 = var13_plus_var11_times_var10 + (var14_plus_var11 - 1) * var9 + var15 + var11;
                           if (this.adjacentTreeBlocks[index2] == -2) {
                              this.adjacentTreeBlocks[index2] = var12;
                           }

                           index2 = var13_plus_var11_times_var10 + (var14_plus_var11 + 1) * var9 + var15 + var11;
                           if (this.adjacentTreeBlocks[index2] == -2) {
                              this.adjacentTreeBlocks[index2] = var12;
                           }

                           index2 = var13_plus_var11_times_var10 + var14_plus_var11_times_var9 + (var15 + var11 - 1);
                           if (this.adjacentTreeBlocks[index2] == -2) {
                              this.adjacentTreeBlocks[index2] = var12;
                           }

                           if (this.adjacentTreeBlocks[index1 + 1] == -2) {
                              this.adjacentTreeBlocks[index1 + 1] = var12;
                           }
                        }
                     }
                  }
               }
            }
         }

         var12 = this.adjacentTreeBlocks[var11 * var10 + var11 * var9 + var11];
         if (var12 >= 0) {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, var6 & -9, 4);
            return true;
         } else {
            this.removeLeaves(par1World, par2, par3, par4);
            return true;
         }
      } else {
         if (RNG.chance_in_100[++RNG.random_number_index + (int)par1World.total_time & 32767] && !wasPlaced(var6) && (!par1World.getAsWorldServer().fast_forwarding || RNG.chance_in_32[++RNG.random_number_index & 32767]) && par5Random.nextInt(par1World.getWorldSeason() == 2 ? 200 : 500) == 0) {
            if (par1World.getBiomeGenForCoords(par2, par4).isSwampBiome() && par5Random.nextInt(2) == 0) {
               return false;
            }

            Item item = Item.stick;
            subtype = this.getBlockSubtype(var6);
            if (subtype == 0) {
               if (par5Random.nextInt(3) > 0) {
                  item = par1World.getBiomeGenForCoords(par2, par4).isJungleBiome() ? Item.orange : Item.appleRed;
               }
            } else if (subtype == 3 && par5Random.nextInt(3) > 0) {
               if (par5Random.nextInt(2) > 0) {
                  item = Items.lemon;
               } else {
                  item = Item.banana;
               }
            }

            this.dropBlockAsEntityItem((new BlockBreakInfo(par1World, par2, par3, par4)).setWindfall(), (Item)item);
         }

         return false;
      }
   }

   @Overwrite
   public int dropBlockAsEntityItem(BlockBreakInfo info) {
      if (BitHelper.isBitSet(info.getMetadata(), 4)) {
         return 0;
      } else if (info.getBiome().isSwampBiome() && info.world.rand.nextInt(2) == 0) {
         return 0;
      } else {
         int leaf_kind = this.getBlockSubtype(info.getMetadata());
         int num_drops;
         if ((num_drops = this.dropBlockAsEntityItem(info, Block.sapling.blockID, leaf_kind, 1, 0.01F)) > 0) {
            return num_drops;
         } else if ((num_drops = this.dropBlockAsEntityItem(info, Item.stick.itemID, 0, 1, 0.05F)) > 0) {
            return num_drops;
         } else if (leaf_kind == 0) {
            return this.dropBlockAsEntityItem(info, info.getBiome().isJungleBiome() ? Item.orange.itemID : Item.appleRed.itemID, 0, 1, 0.005F);
         } else if (info.world.rand.nextInt(2) > 0) {
            return leaf_kind == 4 ? this.dropBlockAsEntityItem(info, Items.lemon.itemID, 0, 1, 0.005F) : 0;
         } else {
            return leaf_kind == 4 ? this.dropBlockAsEntityItem(info, Item.banana.itemID, 0, 1, 0.005F) : 0;
         }
      }
   }

   @Overwrite
   public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
      int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
      if ((var5 & 3) == 1) {
         return ColorizerFoliage.getFoliageColorPine();
      } else if ((var5 & 3) == 2) {
         return ColorizerFoliage.getFoliageColorBirch();
      } else {
         int var6 = 0;
         int var7 = 0;
         int var8 = 0;

         for(int var9 = -1; var9 <= 1; ++var9) {
            for(int var10 = -1; var10 <= 1; ++var10) {
               int var11 = par1IBlockAccess.getBiomeGenForCoords(par2 + var10, par4 + var9).getBiomeFoliageColor();
               var6 += SeasonColorizer.getSeasonColorizerModifierRed(par1IBlockAccess.getWorld(), (var11 & 16711680) >> 16);
               var7 += (var11 & '\uff00') >> 8;
               var8 += var11 & 255;
            }
         }

         return (var6 / 9 & 255) << 16 | (var7 / 9 & 255) << 8 | var8 / 9 & 255;
      }
   }

   @Shadow
   public static boolean wasPlaced(int metadata) {
      return false;
   }

   @Shadow
   private void removeLeaves(World par1World, int par2, int par3, int par4) {
   }
}
