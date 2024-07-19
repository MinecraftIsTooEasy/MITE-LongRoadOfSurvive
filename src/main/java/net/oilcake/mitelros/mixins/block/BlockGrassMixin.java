package net.oilcake.mitelros.mixins.block;

import java.util.Random;
import net.minecraft.Block;
import net.minecraft.BlockConstants;
import net.minecraft.BlockGrass;
import net.minecraft.IBlockAccess;
import net.minecraft.Item;
import net.minecraft.ItemDye;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.World;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.util.SeasonColorizer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({BlockGrass.class})
public class BlockGrassMixin extends Block {
   protected BlockGrassMixin(int par1, Material par2Material, BlockConstants constants) {
      super(par1, par2Material, constants);
   }

   @Overwrite
   public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
      int var5 = 0;
      int var6 = 0;
      int var7 = 0;

      int r;
      int g;
      int b;
      for(r = -1; r <= 1; ++r) {
         for(g = -1; g <= 1; ++g) {
            b = par1IBlockAccess.getBiomeGenForCoords(par2 + g, par4 + r).getBiomeGrassColor();
            var5 += SeasonColorizer.getSeasonColorizerModifierRed(par1IBlockAccess.getWorld(), (b & 16711680) >> 16);
            var6 += (b & '\uff00') >> 8;
            var7 += b & 255;
         }
      }

      r = var5 / 9 & 255;
      g = var6 / 9 & 255;
      b = var7 / 9 & 255;
      float trampling_effect = getTramplingEffect(getTramplings(par1IBlockAccess.getBlockMetadata(par2, par3, par4)));
      if (trampling_effect > 0.0F) {
         float weight_grass = 1.0F - trampling_effect;
         r = (int)((float)r * weight_grass + 134.0F * trampling_effect);
         g = (int)((float)g * weight_grass + 96.0F * trampling_effect);
         b = (int)((float)b * weight_grass + 67.0F * trampling_effect);
      }

      return r << 16 | g << 8 | b;
   }

   @Overwrite
   public boolean fertilize(World world, int x, int y, int z, ItemStack item_stack) {
      Item item = item_stack.getItem();
      if (item != Item.dyePowder) {
         return false;
      } else {
         world.getBlockMetadata(x, y, z);
         Random itemRand = new Random();
         int dx;
         int var7;
         int var8;
         if (!world.isRemote) {
            ItemDye var10000 = (ItemDye)item;
            ItemDye.suppress_standard_particle_effect = true;

            for(dx = -2; dx <= 2; ++dx) {
               for(var7 = -1; var7 <= 1; ++var7) {
                  for(var8 = -2; var8 <= 2; ++var8) {
                     if (world.getBlock(x + dx, y + var7, z + var8) == Block.grass && world.isAirBlock(x + dx, y + var7 + 1, z + var8)) {
                        world.playAuxSFX(2005, x + dx, y + var7 + 1, z + var8, 1);
                     }
                  }
               }
            }
         }

         if (!world.isRemote) {
            label79:
            for(dx = 0; dx < 128; ++dx) {
               var7 = x;
               var8 = y + 1;
               int var9 = z;

               int subtype;
               for(subtype = 0; subtype < dx / 16; ++subtype) {
                  var7 += itemRand.nextInt(3) - 1;
                  var8 += (itemRand.nextInt(3) - 1) * itemRand.nextInt(3) / 2;
                  var9 += itemRand.nextInt(3) - 1;
                  if (world.getBlockId(var7, var8 - 1, var9) != Block.grass.blockID || world.isBlockNormalCube(var7, var8, var9)) {
                     continue label79;
                  }
               }

               if (world.getBlockId(var7, var8, var9) == 0 && itemRand.nextInt(2) == 0) {
                  if (itemRand.nextInt(10) != 0) {
                     if (Block.tallGrass.isLegalAt(world, var7, var8, var9, 1)) {
                        world.setBlock(var7, var8, var9, Block.tallGrass.blockID, 1, 3);
                     }
                  } else if (itemRand.nextInt(2) != 0) {
                     if (Block.plantYellow.isLegalAt(world, var7, var8, var9, 0)) {
                        world.setBlock(var7, var8, var9, Block.plantYellow.blockID);
                     }
                  } else if (itemRand.nextInt(2) != 0) {
                     subtype = Blocks.flowerextend.getRandomSubtypeThatCanOccurAt(world, var7, var8, var9);
                     if (itemRand.nextFloat() < 0.5F) {
                        subtype = -1;
                     }

                     if (subtype >= 0) {
                        world.setBlock(var7, var8, var9, Blocks.flowerextend.blockID, subtype, 3);
                     }
                  } else {
                     subtype = Block.plantRed.getRandomSubtypeThatCanOccurAt(world, var7, var8, var9);
                     if (subtype == 2 && itemRand.nextFloat() < 0.8F) {
                        subtype = -1;
                     }

                     if (subtype >= 0) {
                        world.setBlock(var7, var8, var9, Block.plantRed.blockID, subtype, 3);
                     }
                  }
               }
            }
         }

         return true;
      }
   }

   @Shadow
   public static float getTramplingEffect(int tramplings) {
      return 0.0F;
   }

   @Shadow
   public static int getTramplings(int metadata) {
      return 0;
   }
}
