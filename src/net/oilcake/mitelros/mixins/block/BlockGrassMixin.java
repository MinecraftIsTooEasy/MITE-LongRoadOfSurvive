package net.oilcake.mitelros.mixins.block;

import net.minecraft.BlockGrass;
import net.minecraft.IBlockAccess;
import net.minecraft.MathHelper;
import net.oilcake.mitelros.util.SeasonColorizer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockGrass.class)
public class BlockGrassMixin {
    @Overwrite
    public int c(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        int var5 = 0;
        int var6 = 0;
        int var7 = 0;

        int r;
        int g;
        int b;
        for(r = -1; r <= 1; ++r) {
            for(g = -1; g <= 1; ++g) {
                b = par1IBlockAccess.getBiomeGenForCoords(par2 + g, par4 + r).k();
                var5 += SeasonColorizer.getSeasonColorizerModifierRed(par1IBlockAccess.getWorld(),(b & 16711680) >> 16);
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
    @Shadow
    public static float getTramplingEffect(int tramplings) {
        return 0;
    }
    @Shadow
    public static int getTramplings(int metadata) {
        return 0;
    }
}
