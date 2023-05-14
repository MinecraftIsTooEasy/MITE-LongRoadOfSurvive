package net.oilcake.mitelros.mixins.util;

import net.minecraft.CraftingResult;
import net.minecraft.EnumQuality;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(CraftingResult.class)
public class CraftingResultMixin {
    @Overwrite
    public static float getQualityAdjustedDifficulty(float difficulty, EnumQuality quality) {
        if (quality == null) {
            return difficulty;
        } else {
            int quality_levels_above_average = quality.ordinal() - EnumQuality.average.ordinal();
            float modified_difficulty = difficulty;

            for(int i = 0; i < quality_levels_above_average; ++i) {
                modified_difficulty += difficulty;
            }

            return modified_difficulty;
        }
    }
}
