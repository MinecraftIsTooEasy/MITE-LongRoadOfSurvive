package net.oilcake.mitelros.world;

import net.minecraft.BiomeGenBase;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.world.biome.BiomeGenUnderworldInFreeze;
import net.oilcake.mitelros.world.biome.BiomeSavanna;
import net.oilcake.mitelros.world.biome.BiomeSavannaPleatu;
import net.oilcake.mitelros.world.biome.BiomeWindsweptPleatu;

public class BiomeBases extends BiomeGenBase {
   public static final BiomeGenBase windsweptpleatu = new BiomeWindsweptPleatu(Constant.getNextBiomeID());
   public static final BiomeGenBase underworldinfreeze = new BiomeGenUnderworldInFreeze(Constant.getNextBiomeID());
   public static final BiomeGenBase savanna = new BiomeSavanna(Constant.getNextBiomeID());
   public static final BiomeGenBase savannapleatu = new BiomeSavannaPleatu(Constant.getNextBiomeID());

   protected BiomeBases(int par1) {
      super(par1);
   }
}
