package net.oilcake.mitelros.mixins.entity;

import net.minecraft.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(Entity.class)
public class EntityMixin {
    @Shadow
    protected Random rand;
    public Random getRand(){
        return rand;
    }
}
