package net.oilcake.mitelros.client.render;

import net.minecraft.Entity;
import net.minecraft.EntityLiving;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntitySkeleton;
import net.minecraft.ModelSkeleton;
import net.minecraft.RenderBiped;
import net.minecraft.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderWitherBoneLord extends RenderBiped {
   public RenderWitherBoneLord() {
      super(new ModelSkeleton(), 0.5F);
   }

   protected boolean forceGlowOverride() {
      return true;
   }

   protected void setTextures() {
      this.setTexture(0, "textures/entity/skeleton/wither_bone_lord");
   }

   protected ResourceLocation func_110856_a(EntityLiving par1EntityLiving) {
      return this.textures[0];
   }

   protected ResourceLocation getEntityTexture(Entity par1Entity) {
      return this.func_110856_a((EntitySkeleton)par1Entity);
   }

   protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2) {
      this.a((EntitySkeleton)par1EntityLivingBase, par2);
   }

   protected void a(EntitySkeleton par1EntitySkeleton, float par2) {
      if (par1EntitySkeleton.getSkeletonType() == 1) {
         GL11.glScalef(1.2F, 1.2F, 1.2F);
      }

   }
}
