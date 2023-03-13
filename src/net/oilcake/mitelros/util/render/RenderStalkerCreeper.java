package net.oilcake.mitelros.util.render;

import net.minecraft.bgg;
import net.oilcake.mitelros.entity.EntityStalkerCreeper;

public class RenderStalkerCreeper extends bgg {
    public RenderStalkerCreeper() {
        this.d *= this.scale = EntityStalkerCreeper.getScale();
    }

    public String getSubtypeName() {
        return "stalker_creeper";
    }
}
