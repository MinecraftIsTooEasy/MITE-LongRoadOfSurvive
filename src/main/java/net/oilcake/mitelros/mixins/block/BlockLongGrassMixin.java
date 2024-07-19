package net.oilcake.mitelros.mixins.block;

import net.minecraft.Block;
import net.minecraft.BlockTallGrass;
import net.minecraft.IBlockAccess;
import net.oilcake.mitelros.util.SeasonColorizer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({BlockTallGrass.class})
public class BlockLongGrassMixin {
   @Overwrite
   public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
      int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
      if (var5 == 0) {
         return 16777215;
      } else {
         int OriColor = par1IBlockAccess.getBiomeGenForCoords(par2, par4).getBiomeGrassColor();
         int MixedColor = SeasonColorizer.getSeasonColorizerModifierRed(par1IBlockAccess.getWorld(), OriColor >> 16);
         int FinalColor = '\uffff' & OriColor;
         FinalColor += MixedColor << 16;
         return FinalColor;
      }
   }

   @Overwrite
   public boolean showDestructionParticlesWhenReplacedBy(int metadata, Block other_block, int other_block_metadata) {
      return false;
   }
}
