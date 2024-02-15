package net.oilcake.mitelros.mixins.server;

import net.minecraft.DebugAttack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(DebugAttack.class)
public class DebugAttackMixin {

    @Overwrite
    public String toString() {
        return "";
    }
}
