package net.oilcake.mitelros.util;

import net.minecraft.Entity;
import net.minecraft.IEntitySelector;
import net.minecraft.IMob;
import net.oilcake.mitelros.entity.EntityUndeadGuard;

public final class EntitySelectorHunter implements IEntitySelector {
   public boolean isEntityApplicable(Entity var1) {
      return var1 instanceof IMob && !(var1 instanceof EntityUndeadGuard);
   }
}
