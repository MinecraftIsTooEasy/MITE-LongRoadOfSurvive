package net.oilcake.mitelros.util;

import net.minecraft.Entity;
import net.minecraft.IEntitySelector;
import net.minecraft.IMonster;
import net.oilcake.mitelros.entity.EntityUndeadGuard;

public final class EntitySelectorHunter implements IEntitySelector {
    public EntitySelectorHunter() {
    }

    public boolean isEntityApplicable(Entity var1) {
        return var1 instanceof IMonster && !(var1 instanceof EntityUndeadGuard);
    }
}
