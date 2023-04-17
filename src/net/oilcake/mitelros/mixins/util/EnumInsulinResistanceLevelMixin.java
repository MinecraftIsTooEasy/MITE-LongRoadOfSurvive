package net.oilcake.mitelros.mixins.util;

import net.minecraft.EnumChatFormat;
import net.minecraft.EnumInsulinResistanceLevel;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;


@Mixin(EnumInsulinResistanceLevel.class)
public class EnumInsulinResistanceLevelMixin {
    @Shadow
    @Final
    private EnumChatFormat color;
    public EnumChatFormat getColorC() {
        return this.color;
    }
    public float getRedAsFloatC() {
        return this.color.getRedAsFloat();
    }

    public  float getGreenAsFloatC() {
        return this.color.getGreenAsFloat();
    }

    public float getBlueAsFloatC() {
        return this.color.getBlueAsFloat();
    }
    public int getOrdinalForTransmissionC(EnumInsulinResistanceLevel insulin_resistance_level) {
        return getOrdinalForTransmission(insulin_resistance_level);
    }
    @Shadow
    static int getOrdinalForTransmission(EnumInsulinResistanceLevel insulin_resistance_level) {
        return 0;
    }
    @Shadow
    int getOrdinalForTransmission() {
        return 0;
    }

}