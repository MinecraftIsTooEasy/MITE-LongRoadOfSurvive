package net.oilcake.mitelros.mixins.world;

import net.minecraft.GenLayer;
import net.minecraft.GenLayerAddIsland;
import net.minecraft.GenLayerAddMushroomIsland;
import net.minecraft.GenLayerAddSnow;
import net.minecraft.GenLayerBiome;
import net.minecraft.GenLayerFuzzyZoom;
import net.minecraft.GenLayerHills;
import net.minecraft.GenLayerIsland;
import net.minecraft.GenLayerRiver;
import net.minecraft.GenLayerRiverInit;
import net.minecraft.GenLayerRiverMix;
import net.minecraft.GenLayerShore;
import net.minecraft.GenLayerSmooth;
import net.minecraft.GenLayerSwampRivers;
import net.minecraft.GenLayerVoronoiZoom;
import net.minecraft.GenLayerZoom;
import net.minecraft.WorldType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({GenLayer.class})
public class GenLayerMixin {
   @Overwrite
   public static GenLayer[] initializeAllBiomeGenerators(long par0, WorldType par2WorldType) {
      GenLayerIsland var3 = new GenLayerIsland(1L);
      GenLayerFuzzyZoom var9 = new GenLayerFuzzyZoom(2000L, var3);
      GenLayerAddIsland var10 = new GenLayerAddIsland(1L, var9);
      GenLayerZoom var11 = new GenLayerZoom(2001L, var10);
      var10 = new GenLayerAddIsland(2L, var11);
      GenLayerAddSnow var12 = new GenLayerAddSnow(2L, var10);
      var11 = new GenLayerZoom(2002L, var12);
      var10 = new GenLayerAddIsland(3L, var11);
      var11 = new GenLayerZoom(2003L, var10);
      var10 = new GenLayerAddIsland(4L, var11);
      GenLayerAddMushroomIsland var16 = new GenLayerAddMushroomIsland(5L, var10);
      byte var4 = 6;
      if (par2WorldType == WorldType.LARGE_BIOMES) {
         var4 = 6;
      }

      GenLayer var5 = GenLayerZoom.magnify(1000L, var16, 0);
      GenLayerRiverInit var13 = new GenLayerRiverInit(100L, var5);
      var5 = GenLayerZoom.magnify(1000L, var13, var4 + 2);
      GenLayerRiver var14 = new GenLayerRiver(1L, var5);
      GenLayerSmooth var15 = new GenLayerSmooth(1000L, var14);
      GenLayer var6 = GenLayerZoom.magnify(1000L, var16, 0);
      GenLayerBiome var17 = new GenLayerBiome(200L, var6, par2WorldType);
      var6 = GenLayerZoom.magnify(1000L, var17, 2);
      Object var18 = new GenLayerHills(1000L, var6);

      for(int var7 = 0; var7 < var4; ++var7) {
         var18 = new GenLayerZoom((long)(1000 + var7), (GenLayer)var18);
         if (var7 == 0) {
            var18 = new GenLayerAddIsland(3L, (GenLayer)var18);
         }

         if (var7 == 1) {
            var18 = new GenLayerShore(1000L, (GenLayer)var18);
         }

         if (var7 == 1) {
            var18 = new GenLayerSwampRivers(1000L, (GenLayer)var18);
         }
      }

      GenLayerSmooth var19 = new GenLayerSmooth(1000L, (GenLayer)var18);
      GenLayerRiverMix var20 = new GenLayerRiverMix(100L, var19, var15);
      GenLayerVoronoiZoom var8 = new GenLayerVoronoiZoom(10L, var20);
      var20.initWorldGenSeed(par0);
      var8.initWorldGenSeed(par0);
      return new GenLayer[]{var20, var8, var20};
   }
}
