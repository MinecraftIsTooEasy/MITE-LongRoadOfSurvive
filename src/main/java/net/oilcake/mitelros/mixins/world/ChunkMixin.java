package net.oilcake.mitelros.mixins.world;

import net.minecraft.Chunk;
import net.minecraft.ExtendedBlockStorage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({Chunk.class})
public class ChunkMixin {
   @Shadow
   @Final
   private boolean is_empty;
   @Shadow
   public ExtendedBlockStorage[] storageArrays;

   public ChunkMixin(boolean isEmpty) {
      this.is_empty = isEmpty;
   }

   @Overwrite
   public final int getBlockIDOptimized(int xz_index, int y) {
      int local_x = xz_index % 16;
      int local_z = xz_index / 16;
      return this.getBlockID(local_x, y, local_z);
   }

   @Overwrite
   public final int getBlockID(int par1, int par2, int par3) {
      if (this.is_empty) {
         return 0;
      } else {
         int par2_shifted = par2 >> 4;
         if (par2_shifted < this.storageArrays.length) {
            ExtendedBlockStorage extended_block_storage = this.storageArrays[par2_shifted];
            if (extended_block_storage != null) {
               int par2_and_15 = par2 & 15;
               int var7 = extended_block_storage.getExtBlockID(par1 & 15, par2_and_15, par3 & 15);
               return var7;
            }
         }

         return 0;
      }
   }
}
