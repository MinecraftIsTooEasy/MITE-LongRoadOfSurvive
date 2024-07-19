package net.oilcake.mitelros.client.render;

import net.minecraft.Entity;
import net.minecraft.EntityLiving;
import net.minecraft.EntitySkeleton;
import net.minecraft.ModelSkeleton;
import net.minecraft.RenderBiped;
import net.minecraft.ResourceLocation;

public class RenderStray extends RenderBiped {
   public RenderStray() {
      super(new ModelSkeleton(), 0.5F);
   }

   protected void setTextures() {
      this.setTexture(0, "textures/entity/skeleton/stray");
   }

   protected ResourceLocation func_110856_a(EntityLiving par1EntityLiving) {
      return this.textures[0];
   }

   protected ResourceLocation getEntityTexture(Entity par1Entity) {
      return this.func_110856_a((EntitySkeleton)par1Entity);
   }
}
