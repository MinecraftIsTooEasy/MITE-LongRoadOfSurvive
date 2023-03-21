package net.oilcake.mitelros.mixins.world;

import net.minecraft.WorldProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(WorldProvider.class)
public class WorldProviderMixin {
    @Overwrite
    public float f() {
        return 224.0F;
    }
}
