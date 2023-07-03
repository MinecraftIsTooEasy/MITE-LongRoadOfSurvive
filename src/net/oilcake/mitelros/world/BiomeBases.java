package net.oilcake.mitelros.world;

import net.minecraft.BiomeBase;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.world.biome.BiomeGenUnderworldInFreeze;
import net.oilcake.mitelros.world.biome.BiomeSavanna;
import net.oilcake.mitelros.world.biome.BiomeSavannaPleatu;
import net.oilcake.mitelros.world.biome.BiomeWindsweptPleatu;

public class BiomeBases extends BiomeBase {
    public static final BiomeBase windsweptpleatu = new BiomeWindsweptPleatu(Constant.getNextBiomeID());
    public static final BiomeBase underworldinfreeze = new BiomeGenUnderworldInFreeze(Constant.getNextBiomeID());
    public static final BiomeBase savanna = new BiomeSavanna(Constant.getNextBiomeID());
    public static final BiomeBase savannapleatu = new BiomeSavannaPleatu(Constant.getNextBiomeID());
    protected BiomeBases(int par1) {
        super(par1);
    }
}