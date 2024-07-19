package net.oilcake.mitelros.mixins.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ink.huix.iinjected.WorldKt;
import net.minecraft.BiomeGenBase;
import net.minecraft.Block;
import net.minecraft.Chunk;
import net.minecraft.Debug;
import net.minecraft.Entity;
import net.minecraft.EnumSkyBlock;
import net.minecraft.Explosion;
import net.minecraft.ExtendedBlockStorage;
import net.minecraft.WeatherEvent;
import net.minecraft.World;
import net.minecraft.WorldProvider;
import net.oilcake.mitelros.util.StuckTagConfig;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(World.class)
public abstract class WorldMixin implements WorldKt {
   private static final double pi = Math.acos(-1.0);
   @Mutable
   @Shadow
   @Final
   public WorldProvider provider;

   private static final int SPRING = 0;
   private static final int SUMMER = 1;
   private static final int AUTUMN = 2;
   private static final int WINTER = 3;

   @Shadow
   public abstract float getRainStrength(float var1);

   @Shadow
   public abstract World getWorld();

   @Shadow
   public abstract void removeBlockTileEntity(int var1, int var2, int var3);

   public WorldMixin(WorldProvider provider) {
      this.provider = provider;
   }

   public Explosion createExplosionC(Entity exploder, double posX, double posY, double posZ, float explosion_size_vs_blocks, float explosion_size_vs_living_entities) {
      return this.newExplosionC(exploder, posX, posY, posZ, explosion_size_vs_blocks, explosion_size_vs_living_entities);
   }

   public Explosion newExplosionC(Entity exploder, double posX, double posY, double posZ, float explosion_size_vs_blocks, float explosion_size_vs_living_entities) {
      Explosion explosion = new Explosion((World)ReflectHelper.dyCast(this), exploder, posX, posY, posZ, explosion_size_vs_blocks, explosion_size_vs_living_entities);
      explosion.doExplosionA();
      explosion.affectedBlockPositions.clear();
      explosion.doExplosionB(false);
      return explosion;
   }

   @Inject(
      locals = LocalCapture.CAPTURE_FAILHARD,
      method = {"getBlockId(III)I"},
      at = {@At(
   value = "FIELD",
   target = "Lnet/minecraft/Chunk;storageArrays:[Lnet/minecraft/ExtendedBlockStorage;",
   shift = Shift.AFTER
)},
      cancellable = true
   )
   private void injectGetBlockId(int par1, int par2, int par3, CallbackInfoReturnable cir, Chunk var4) {
      ExtendedBlockStorage extended_block_storage = var4.storageArrays[par2 >> 4];
      if (extended_block_storage == null) {
         cir.setReturnValue(0);
         cir.cancel();
      } else {
         int par1_and_15 = par1 & 15;
         int par2_and_15 = par2 & 15;
         int par3_and_15 = par3 & 15;
         cir.setReturnValue(extended_block_storage.getExtBlockID(par1_and_15, par2_and_15, par3_and_15));
         cir.cancel();
      }

   }

   @Overwrite
   public final List generateWeatherEvents(int day) {
      if (!this.isOverworld()) {
         Debug.setErrorMessage("generateWeatherEvents: called for " + this.getDimensionName());
      }

      List events = new ArrayList();
      if (day < 2) {
         return events;
      } else {
         long first_tick_of_day = (long)((day - 1) * 24000 - 6000);
         Random random = new Random(this.getWorldCreationTime() + (long)(this.getDimensionId() * 938473) + (long)day);
         random.nextInt();

         for(int i = 0; i < 3 && random.nextInt(4) <= 0; ++i) {
            int duration_static = 6000 * ((Boolean)StuckTagConfig.TagConfig.TagEternalRaining.ConfigValue ? 6 : 1);
            int duration_random = random.nextInt(12000) * ((Boolean)StuckTagConfig.TagConfig.TagEternalRaining.ConfigValue ? 2 : 1);
            int duration = duration_random + duration_static;
            duration = (int)((float)duration * this.getRainDurationModify(this.getWorldSeason()));
            WeatherEvent event = new WeatherEvent(first_tick_of_day + (long)random.nextInt(24000), duration);
            if (!isHarvestMoon(event.start, true) && !isHarvestMoon(event.end, true) && !isHarvestMoon(event.start + 6000L, true) && !isHarvestMoon(event.end - 6000L, true) && !isBloodMoon(event.start, false) && !isBloodMoon(event.end, false) && !isBlueMoon(event.start, false) && !isBlueMoon(event.end, false)) {
               events.add(event);
            }
         }

         WeatherEvent event;
         if (isBloodMoon(first_tick_of_day + 30000L, false)) {
            event = new WeatherEvent(first_tick_of_day + 5000L, 24000);
            events.add(event);
         }

         if (isBloodMoon(first_tick_of_day + 6000L, false)) {
            event = new WeatherEvent(first_tick_of_day + 5000L, 14000);
            event.setStorm(event.start, event.end);
            events.add(event);
         }

         return events;
      }
   }

   @Override
   @Unique
   public int getWorldSeason() {
      return this.getSeasonType(this.getDayOfWorld());
   }

   @Override
   @Unique
   public int getSeasonType(int day) {
      return day % 128 / 32;
   }

   @Override
   @Unique
   public float getRainDurationModify(int Season) {
       return switch (Season) {
           case 0 -> 1.0F;
           case 1 -> 2.25F;
           case 2 -> 0.75F;
           case 3 -> 0.5F;
           default -> {
               Debug.setErrorMessage("getRainDurationModify: called for num " + Season + " for calculating. Use the default.");
               yield 1.0F;
           }
       };
   }

   @Override
   @Unique
   public float getSeasonGrowthModifier() {
      int day_in_row = this.getDayOfWorld();
       return (float)Math.sin((double)(day_in_row - 16) / 64.0 * pi);
   }

   @Overwrite
   public final boolean canSnowAt(int par1, int par2, int par3) {
      BiomeGenBase var4 = this.getBiomeGenForCoords(par1, par3);
      float var5 = var4.getFloatTemperature();
      if (var5 > (this.getWorldSeason() == 3 ? 1.0F : 0.15F)) {
         return false;
      } else {
         if (par2 >= 0 && par2 < 256 && this.getSavedLightValue(EnumSkyBlock.Block, par1, par2, par3) < 10) {
            int var6 = this.getBlockId(par1, par2 - 1, par3);
            int var7 = this.getBlockId(par1, par2, par3);
            Block block_below = Block.getBlock(var6);
            Block block = Block.getBlock(var7);
            if (block_below == Block.tilledField && block != Block.pumpkinStem) {
               return true;
            }

             return var7 == 0 && Block.snow.isLegalAt(this.getWorld(), par1, par2, par3, 0) && var6 != Block.ice.blockID;
         }

         return false;
      }
   }

   @Overwrite
   public boolean isFreezing(int x, int z) {
      return this.getBiomeGenForCoords(x, z).temperature <= (this.getWorldSeason() == 3 ? 1.0F : 0.15F);
   }

   public final boolean chunkExistsAndIsNotEmptyFromBlockCoordsC(int x, int z) {
      return this.chunkExistsAndIsNotEmptyFromBlockCoords(x, z);
   }


   @Shadow
   public static final boolean isBloodMoon(long unadjusted_tick, boolean exclusively_at_night) {
      return exclusively_at_night;
   }

   @Shadow
   public final boolean isBloodMoon(boolean exclusively_at_night) {
      return exclusively_at_night;
   }

   @Shadow
   @Final
   public static boolean isBlueMoon(long unadjusted_tick, boolean exclusively_at_night) {
      return exclusively_at_night;
   }

   @Shadow
   public final boolean isBlueMoon(boolean exclusively_at_night) {
      return exclusively_at_night;
   }

   @Shadow
   public static final boolean isHarvestMoon(long unadjusted_tick, boolean exclusively_at_night) {
      return exclusively_at_night;
   }

   @Shadow
   public final boolean isHarvestMoon(boolean exclusively_at_night) {
      return exclusively_at_night;
   }

   @Shadow
   public static boolean isDaytime(long unadjusted_tick) {
      return false;
   }

   @Shadow
   public boolean isDaytime() {
      return false;
   }

   @Shadow
   public final long getTotalWorldTime() {
      return 1L;
   }

   @Shadow
   public final int getDimensionId() {
      return this.provider.dimensionId;
   }

   @Shadow
   private long getWorldCreationTime() {
      return 0L;
   }

   @Shadow
   private String getDimensionName() {
      return null;
   }

   @Shadow
   private boolean isOverworld() {
      return false;
   }

   @Shadow
   @Final
   public int getDayOfWorld() {
      return 0;
   }

   @Shadow
   @Final
   public boolean chunkExistsAndIsNotEmptyFromBlockCoords(int x, int z) {
      return false;
   }

   @Shadow
   @Final
   public int getSavedLightValue(EnumSkyBlock block, int par1, int par2, int par3) {
      return 0;
   }

   @Shadow
   public BiomeGenBase getBiomeGenForCoords(int par1, int par3) {
      return null;
   }

   @Shadow
   @Final
   public int getBlockId(int par1, int par2, int par3) {
      return 0;
   }
}
