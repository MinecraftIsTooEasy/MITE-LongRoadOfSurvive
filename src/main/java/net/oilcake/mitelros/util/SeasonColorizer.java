package net.oilcake.mitelros.util;

import net.minecraft.World;

public class SeasonColorizer {
   private static final double pi = Math.acos(-1.0);

   public static int getSeasonColorizerModifierRed(World par1World, int par2) {
      int day_in_row = par1World.getDayOfWorld();
      float ColorModifier = (float)Math.sin((double)(day_in_row - 48) / 64.0 * pi) * 0.125F;
      int MixedColor = (int)(255.0F * ColorModifier);
      return Math.min(MixedColor + par2, 255);
   }
}
