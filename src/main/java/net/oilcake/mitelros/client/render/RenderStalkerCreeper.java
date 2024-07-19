package net.oilcake.mitelros.client.render;

import net.minecraft.RenderCreeper;
import net.oilcake.mitelros.entity.EntityStalkerCreeper;

public class RenderStalkerCreeper extends RenderCreeper {
   public RenderStalkerCreeper() {
      this.shadowSize *= this.scale = EntityStalkerCreeper.getScale();
   }

   public String getSubtypeName() {
      return "stalker_creeper";
   }
}
