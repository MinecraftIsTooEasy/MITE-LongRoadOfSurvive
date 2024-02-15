package net.oilcake.mitelros.mixins.world;

import net.minecraft.Debug;
import net.minecraft.WeatherEvent;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(WeatherEvent.class)
public class WeatherEventMixin {
    @Shadow
    public boolean hasStorm() {
        return this.start_of_storm > 0L;
    }
    @Shadow
    public int type;
    @Shadow
    public long start;
    @Shadow
    public int duration;
    @Shadow
    public long end;
    @Shadow
    public long start_of_storm;
    @Shadow
    public int duration_of_storm;
    @Shadow
    public long end_of_storm;
    @Overwrite
    public void addStorm() {
        if (!this.hasStorm()) {
            Random random = new Random(this.start);
            random.nextInt();
            int day = World.getDayOfWorld(this.start);
            int season = day / 64 % 4;
            if (random.nextInt(season == 3 ? 2 : 4) == 0) {
                this.duration_of_storm = Math.min((int) (random.nextInt(2400) * this.getStormDurationModify(season) + 2400), this.duration);
                if (random.nextInt(3) == 0) {
                    if (random.nextBoolean()) {
                        this.start_of_storm = this.start;
                    } else {
                        this.start_of_storm = this.end - (long)this.duration_of_storm;
                    }
                } else {
                    this.start_of_storm = (long)random.nextInt(this.duration - this.duration_of_storm + 1) + this.start;
                }

                this.end_of_storm = this.start_of_storm + (long)this.duration_of_storm;
                this.type = 3;
            }
        }
    }
    public float getStormDurationModify(int Season) {
        switch (Season) {
            case 0:
                return 1.0F;
            case 1:
                return 0.5F;
            case 2:
                return 2.5F;
            case 3:
                return 1.0F;
            default:
                Debug.setErrorMessage("getStormDurationModify: called for num " + Season + " for calculating. Use the default.");
                return 1.0F;
        }
    }
}
