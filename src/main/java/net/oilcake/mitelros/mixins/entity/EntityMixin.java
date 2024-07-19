package net.oilcake.mitelros.mixins.entity;

import java.util.Random;
import net.minecraft.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({Entity.class})
public class EntityMixin {
   @Shadow
   protected Random rand;

   public Random getRand() {
      return this.rand;
   }
}
