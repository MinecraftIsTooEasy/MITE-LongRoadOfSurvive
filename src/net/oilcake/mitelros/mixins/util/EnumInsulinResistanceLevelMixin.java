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
}
