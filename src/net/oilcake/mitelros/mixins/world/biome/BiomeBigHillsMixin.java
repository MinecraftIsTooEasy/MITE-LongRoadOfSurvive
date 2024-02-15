package net.oilcake.mitelros.mixins.world.biome;

import net.minecraft.BiomeBase;
import net.minecraft.BiomeBigHills;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Random;

@Mixin(BiomeBigHills.class)
public class BiomeBigHillsMixin extends BiomeBase {
    protected BiomeBigHillsMixin(int par1) {
        super(par1);
    }


    @Overwrite
    public void decorate(World par1World, Random par2Random, int par3, int par4) {
        super.decorate(par1World, par2Random, par3, par4);
    }
}
