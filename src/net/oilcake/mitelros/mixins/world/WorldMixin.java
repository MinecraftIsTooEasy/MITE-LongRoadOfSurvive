package net.oilcake.mitelros.mixins.world;

import net.minecraft.*;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static net.xiaoyu233.fml.util.ReflectHelper.dyCast;

@Mixin(World.class)
public abstract class WorldMixin {
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
                WeatherEvent event = new WeatherEvent(first_tick_of_day + (long)random.nextInt(24000), random.nextInt((StuckTagConfig.TagConfig.TagEternalRaining.ConfigValue ? 24000 : 12000)) + (StuckTagConfig.TagConfig.TagEternalRaining.ConfigValue ? 48000 : 6000));
                if (!isHarvestMoon(event.start, true) && !isHarvestMoon(event.end, true) && !isHarvestMoon(event.start + 6000L, true) && !isHarvestMoon(event.end - 6000L, true) && !isBloodMoon(event.start, false) && !isBloodMoon(event.end, false) && !isBlueMoon(event.start, false) && !isBlueMoon(event.end, false)) {
                    events.add(event);
                }
            }

            if (isBloodMoon(first_tick_of_day + 5000L, false)) {
                WeatherEvent event = new WeatherEvent(first_tick_of_day + 5000L, 14000);
                event.setStorm(event.start, event.end);
                events.add(event);
            }

            return events;
        }
    }
    @Shadow
    public static final boolean isBloodMoon(long unadjusted_tick, boolean exclusively_at_night) {
        if (exclusively_at_night && isDaytime(unadjusted_tick)) {
            return false;
        } else {
            return (unadjusted_tick / 24000L + 1L) % 32L == 0L && !isBlueMoon(unadjusted_tick, exclusively_at_night);
        }
    }
    @Shadow
    public final boolean isBloodMoon(boolean exclusively_at_night) {
        if (!this.isOverworld()) {
            return false;
        } else if (exclusively_at_night && this.isDaytime()) {
            return false;
        } else {
            return (this.getTotalWorldTime() / 24000L + 1L) % 32L == 0L && !this.isBlueMoon(exclusively_at_night);
        }
    }
    @Shadow
    public static final boolean isBlueMoon(long unadjusted_tick, boolean exclusively_at_night) {
        if (exclusively_at_night && isDaytime(unadjusted_tick)) {
            return false;
        } else {
            return (unadjusted_tick / 24000L + 1L) % 128L == 0L;
        }
    }
    @Shadow
    public final boolean isBlueMoon(boolean exclusively_at_night) {
        if (!this.isOverworld()) {
            return false;
        } else if (exclusively_at_night && this.isDaytime()) {
            return false;
        } else {
            return (this.getTotalWorldTime() / 24000L + 1L) % 128L == 0L;
        }
    }
    @Shadow
    public static final boolean isHarvestMoon(long unadjusted_tick, boolean exclusively_at_night) {
        return exclusively_at_night && isDaytime(unadjusted_tick) ? false : isBloodMoon(unadjusted_tick + 192000L, exclusively_at_night);
    }

    public final boolean isHarvestMoon(boolean exclusively_at_night) {
        return isHarvestMoon(this.getTotalWorldTime(), exclusively_at_night);
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
}
