package net.oilcake.mitelros.client.render;

import net.minecraft.BossStatus;
import net.minecraft.Entity;
import net.minecraft.EntityLiving;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntitySkeleton;
import net.minecraft.ModelSkeleton;
import net.minecraft.RenderBiped;
import net.minecraft.ResourceLocation;
import net.oilcake.mitelros.entity.EntityLich;

public class RenderLich extends RenderBiped {
   public RenderLich() {
      super(new ModelSkeleton(), 0.5F);
   }

   protected boolean forceGlowOverride() {
      return true;
   }

   protected void setTextures() {
      this.setTexture(0, "textures/entity/skeleton/lich");
   }

   protected ResourceLocation func_110856_a(EntityLiving par1EntityLiving) {
      return this.textures[0];
   }

   protected ResourceLocation getEntityTexture(Entity par1Entity) {
      return this.func_110856_a((EntitySkeleton)par1Entity);
   }

   public void renderBoss(EntityLich par1EntityLich, double par2, double par4, double par6, float par8, float par9) {
      BossStatus.setBossStatus(par1EntityLich, false);
      super.doRenderLiving(par1EntityLich, par2, par4, par6, par8, par9);
      this.func_110827_b(par1EntityLich, par2, par4, par6, par8, par9);
   }

   public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
      this.renderBoss((EntityLich)par1EntityLivingBase, par2, par4, par6, par8, par9);
   }

   public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
      this.renderBoss((EntityLich)par1Entity, par2, par4, par6, par8, par9);
   }

   public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
      this.renderBoss((EntityLich)par1EntityLiving, par2, par4, par6, par8, par9);
   }
}
