package net.oilcake.mitelros.mixins.world;

import net.minecraft.*;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static net.xiaoyu233.fml.util.ReflectHelper.dyCast;

@Mixin(World.class)
public abstract class WorldMixin {
    @Shadow public abstract World getWorld();

    @Shadow public abstract void removeBlockTileEntity(int par1, int par2, int par3);

    @Shadow
    public final WorldProvider provider;

    public WorldMixin(WorldProvider provider) {
        this.provider = provider;
    }

    public Explosion createExplosionC(Entity exploder, double posX, double posY, double posZ, float explosion_size_vs_blocks, float explosion_size_vs_living_entities) {
        return this.newExplosionC(exploder, posX, posY, posZ, explosion_size_vs_blocks, explosion_size_vs_living_entities);
    }

    public Explosion newExplosionC(Entity exploder, double posX, double posY, double posZ, float explosion_size_vs_blocks, float explosion_size_vs_living_entities) {
        Explosion explosion = new Explosion(dyCast(this), exploder, posX, posY, posZ, explosion_size_vs_blocks, explosion_size_vs_living_entities);
        explosion.doExplosionA();
        explosion.affectedBlockPositions.clear();
        explosion.doExplosionB(false);
        return explosion;
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
                int duration_static = 6000 * (StuckTagConfig.TagConfig.TagEternalRaining.ConfigValue ? 6 : 1);
                int duration_random = random.nextInt(12000) * (StuckTagConfig.TagConfig.TagEternalRaining.ConfigValue ? 2 : 1);
                int duration = duration_random + duration_static;
                duration *= getRainDurationModify(getWorldSeason());
                WeatherEvent event = new WeatherEvent(first_tick_of_day + (long)random.nextInt(24000), duration);
                if (!isHarvestMoon(event.start, true) && !isHarvestMoon(event.end, true) && !isHarvestMoon(event.start + 6000L, true) && !isHarvestMoon(event.end - 6000L, true) && !isBloodMoon(event.start, false) && !isBloodMoon(event.end, false) && !isBlueMoon(event.start, false) && !isBlueMoon(event.end, false)) {
                    events.add(event);
                }
            }

            if (isBloodMoon(first_tick_of_day + 6000L, false)) {
                WeatherEvent event = new WeatherEvent(first_tick_of_day + 5000L, 14000);
                event.setStorm(event.start, event.end);
                events.add(event);
            }

            return events;
        }
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
    public static final boolean isBlueMoon(long unadjusted_tick, boolean exclusively_at_night) {
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
        return 1;
    }
    @Shadow
    public final int getDimensionId() {
        return this.provider.dimensionId;
    }
    @Shadow
    private long getWorldCreationTime() {
        return 0;
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

    /*Return int ranged in 0 to 3
    * 0 Means Spring
    * 1 Means Summer etc.
    * 256 days in a row
    */
    public int getWorldSeason(){
        return getSeasonType(this.getDayOfWorld());
    }
    public int getSeasonType(int day){
        return day / 32 % 4;
    }
    private static final int SPRING = 0;
    private static final int SUMMER = 1;
    private static final int AUTUMN = 2;
    private static final int WINTER = 3;
    public float getRainDurationModify(int Season){
        switch (Season){
            case SPRING:
                return 1.0F;
            case SUMMER:
                return 2.25F;
            case AUTUMN:
                return 0.75F;
            case WINTER:
                return 0.5F;
            default:
                Debug.setErrorMessage("getRainDurationModify: called for num "+ Season+" for calculating. Use the default.");
                return 1.0F;
        }
    }
    @Overwrite
    public final boolean canSnowAt(int par1, int par2, int par3) {
        BiomeBase var4 = this.getBiomeGenForCoords(par1, par3);
        float var5 = var4.getFloatTemperature();
        if (var5 > (getWorldSeason() == WINTER ? 1.0F : 0.15F)) {
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
                if (var7 == 0 && Block.snow.isLegalAt(this.getWorld(), par1, par2, par3, 0) && var6 != Block.ice.blockID) {
                    return true;
                }
            }
            return false;
        }
    }
    @Overwrite
    public boolean isFreezing(int x, int z) {
        return this.getBiomeGenForCoords(x, z).temperature <= (getWorldSeason() == WINTER ? 1.0F : 0.15F);
    }
    public final boolean chunkExistsAndIsNotEmptyFromBlockCoordsC(int x, int z) {
        return this.chunkExistsAndIsNotEmptyFromBlockCoords(x, z);
    }

    @Shadow
    @Final
    protected boolean chunkExistsAndIsNotEmptyFromBlockCoords(int x, int z) {
        return false;
    }
    @Shadow
    @Final
    public int getSavedLightValue(EnumSkyBlock block, int par1, int par2, int par3) {
        return 0;
    }
    @Shadow
    public BiomeBase getBiomeGenForCoords(int par1, int par3) {
        return null;
    }
    @Shadow
    @Final
    public int getBlockId(int par1, int par2, int par3) {
        return 0;
    }
}
