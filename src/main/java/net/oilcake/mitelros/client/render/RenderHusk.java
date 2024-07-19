package net.oilcake.mitelros.client.render;

import net.minecraft.Entity;
import net.minecraft.EntityLiving;
import net.minecraft.EntityZombie;
import net.minecraft.ModelBiped;
import net.minecraft.ModelZombie;
import net.minecraft.RenderBiped;
import net.minecraft.ResourceLocation;

public class RenderHusk extends RenderBiped {
   private ModelBiped r;

   public RenderHusk() {
      super(new ModelZombie(), 0.5F, 1.0F);
      this.r = this.modelBipedMain;
   }

   protected void setTextures() {
      this.setTexture(0, "textures/entity/zombie/husk");
   }

   protected ResourceLocation func_110856_a(EntityLiving par1EntityLiving) {
      return this.textures[0];
   }

   protected ResourceLocation getEntityTexture(Entity par1Entity) {
      return this.func_110856_a((EntityZombie)par1Entity);
   }
}
