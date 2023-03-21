package net.oilcake.mitelros.world;

import net.minecraft.BiomeBase;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.world.biome.BiomeWindsweptPleatu;

public class BiomeBases extends BiomeBase {
    public static final BiomeBase windsweptpleatu = new BiomeWindsweptPleatu(Constant.getNextBiomeID());
    protected BiomeBases(int par1) {
        super(par1);
    }
}