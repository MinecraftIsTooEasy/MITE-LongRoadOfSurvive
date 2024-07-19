package net.oilcake.mitelros.client.render;

import net.minecraft.Entity;
import net.minecraft.EntityLiving;
import net.minecraft.EntitySkeleton;
import net.minecraft.ModelSkeleton;
import net.minecraft.RenderBiped;
import net.minecraft.ResourceLocation;

public class RenderLichShadow extends RenderBiped {
   public RenderLichShadow() {
      super(new ModelSkeleton(), 0.5F);
   }

   protected boolean forceGlowOverride() {
      return true;
   }

   protected void setTextures() {
      this.setTexture(0, "textures/entity/skeleton/lich_shadow");
   }

   protected ResourceLocation func_110856_a(EntityLiving par1EntityLiving) {
      return this.textures[0];
   }

   protected ResourceLocation getEntityTexture(Entity par1Entity) {
      return this.func_110856_a((EntitySkeleton)par1Entity);
   }
}
