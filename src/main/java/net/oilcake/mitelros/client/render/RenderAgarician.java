package net.oilcake.mitelros.client.render;

import net.minecraft.Entity;
import net.minecraft.EntityLivingBase;
import net.minecraft.RenderLiving;
import net.minecraft.ResourceLocation;
import net.oilcake.mitelros.entity.EntityAgarician;
import net.oilcake.mitelros.entity.model.ModelAgarician;
import org.lwjgl.opengl.GL11;

public class RenderAgarician extends RenderLiving {
   public RenderAgarician() {
      super(new ModelAgarician(), 1.0F);
   }

   protected void a(EntityAgarician par1) {
      if (par1.isChild()) {
         GL11.glScalef(0.75F, 0.75F, 0.75F);
      } else {
         GL11.glScalef(1.5F, 1.5F, 1.5F);
      }

   }

   protected ResourceLocation getEntityTexture(Entity entity) {
      return this.textures[((EntityAgarician)entity).getType()];
   }

   protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
      this.a((EntityAgarician)par1EntityLivingBase);
   }

   protected boolean forceGlowOverride() {
      return true;
   }

   protected void setTextures() {
      this.setTexture(0, "textures/entity/agarician_brown", "textures/entity/agarician");
      this.setTexture(1, "textures/entity/agarician_red", "textures/entity/agarician");
      this.setTexture(2, "textures/entity/agarician_luminescent", "textures/entity/agarician");
   }
}
