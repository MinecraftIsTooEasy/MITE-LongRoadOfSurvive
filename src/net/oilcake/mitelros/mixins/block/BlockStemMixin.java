package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(BlockStem.class)
public abstract class BlockStemMixin extends BlockGrowingPlant {
    public BlockStemMixin(int block_id) {
        super(block_id);
    }
    @Overwrite
    public float getGrowthRate(World par1World, int par2, int par3, int par4) {
        Block block_below = Block.blocksList[par1World.getBlockId(par2, par3 - 1, par4)];
        int block_below_metadata = par1World.getBlockMetadata(par2, par3 - 1, par4);
        if (block_below == Block.tilledField && BlockSoil.getWetness(block_below_metadata) == 0) {
            return 0.0F;
        } else {
            float var5 = 1.0F + par1World.getSeasonGrowthModifier();
            int var6 = par1World.getBlockId(par2, par3, par4 - 1);
            int var7 = par1World.getBlockId(par2, par3, par4 + 1);
            int var8 = par1World.getBlockId(par2 - 1, par3, par4);
            int var9 = par1World.getBlockId(par2 + 1, par3, par4);
            int var10 = par1World.getBlockId(par2 - 1, par3, par4 - 1);
            int var11 = par1World.getBlockId(par2 + 1, par3, par4 - 1);
            int var12 = par1World.getBlockId(par2 + 1, par3, par4 + 1);
            int var13 = par1World.getBlockId(par2 - 1, par3, par4 + 1);
            boolean var14 = var8 == this.blockID || var9 == this.blockID;
            boolean var15 = var6 == this.blockID || var7 == this.blockID;
            boolean var16 = var10 == this.blockID || var11 == this.blockID || var12 == this.blockID || var13 == this.blockID;

            for(int var17 = par2 - 1; var17 <= par2 + 1; ++var17) {
                for(int var18 = par4 - 1; var18 <= par4 + 1; ++var18) {
                    int var19 = par1World.getBlockId(var17, par3 - 1, var18);
                    float var20 = 0.0F;
                    if (var19 == Block.tilledField.blockID) {
                        var20 = 1.0F;
                        if (par1World.getBlockMetadata(var17, par3 - 1, var18) > 0) {
                            var20 = 3.0F;
                        }
                    }

                    if (var17 != par2 || var18 != par4) {
                        var20 /= 4.0F;
                    }

                    var5 += var20;
                }
            }

            if (var16 || var14 && var15) {
                var5 /= 2.0F;
            }

            if (block_below == Block.tilledField && BlockSoil.isFertilized(block_below_metadata)) {
                var5 *= 1.5F;
            }

            BiomeBase biome = par1World.getBiomeGenForCoords(par2, par4);
            var5 *= this.getTemperatureGrowthRateModifier(biome.temperature);
            var5 *= this.getHumidityGrowthRateModifier(biome.isHighHumidity());
            var5 *= this.getGlobalGrowthRateModifierFromMITE();
            return var5;
        }
    }
}
