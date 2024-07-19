package net.oilcake.mitelros.mixins.render;

import net.minecraft.Entity;
import net.minecraft.EntityBrick;
import net.minecraft.EntityGelatinousSphere;
import net.minecraft.EntityPotion;
import net.minecraft.Icon;
import net.minecraft.Item;
import net.minecraft.ItemPotion;
import net.minecraft.PotionHelper;
import net.minecraft.Render;
import net.minecraft.RenderSnowball;
import net.minecraft.ResourceLocation;
import net.minecraft.Tessellator;
import net.oilcake.mitelros.entity.EntityWandFireball;
import net.oilcake.mitelros.entity.EntityWandIceBall;
import net.oilcake.mitelros.entity.EntityWandShockWave;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({RenderSnowball.class})
public class RenderSnowballMixin extends Render {
   @Shadow
   private Item field_94151_a;
   @Shadow
   private int field_94150_f;

   @Overwrite
   public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
      if (par1Entity instanceof EntityBrick) {
         this.field_94151_a = ((EntityBrick)par1Entity).getModelItem();
      } else if (par1Entity instanceof EntityWandIceBall) {
         this.field_94151_a = ((EntityWandIceBall)par1Entity).getModelItem();
      } else if (par1Entity instanceof EntityWandFireball) {
         this.field_94151_a = ((EntityWandFireball)par1Entity).getModelItem();
      } else if (par1Entity instanceof EntityWandShockWave) {
         this.field_94151_a = ((EntityWandShockWave)par1Entity).getModelItem();
      } else if (par1Entity instanceof EntityGelatinousSphere) {
         this.field_94150_f = ((EntityGelatinousSphere)par1Entity).getModelSubtype();
      }

      Icon var10 = this.field_94151_a.getIconFromSubtype(this.field_94150_f);
      if (var10 != null) {
         GL11.glPushMatrix();
         GL11.glTranslatef((float)par2, (float)par4, (float)par6);
         GL11.glEnable(32826);
         GL11.glScalef(0.5F, 0.5F, 0.5F);
         this.bindEntityTexture(par1Entity);
         Tessellator var11 = Tessellator.instance;
         if (var10 == ItemPotion.func_94589_d("bottle_splash")) {
            int var12 = PotionHelper.func_77915_a(((EntityPotion)par1Entity).getPotionType(), false);
            float var13 = (float)(var12 >> 16 & 255) / 255.0F;
            float var14 = (float)(var12 >> 8 & 255) / 255.0F;
            float var15 = (float)(var12 & 255) / 255.0F;
            GL11.glColor3f(var13, var14, var15);
            GL11.glPushMatrix();
            this.func_77026_a(var11, ItemPotion.func_94589_d("overlay"));
            GL11.glPopMatrix();
            GL11.glColor3f(1.0F, 1.0F, 1.0F);
         }

         this.func_77026_a(var11, var10);
         GL11.glDisable(32826);
         GL11.glPopMatrix();
      }

   }

   @Shadow
   protected ResourceLocation getEntityTexture(Entity entity) {
      return null;
   }

   @Shadow
   private void func_77026_a(Tessellator par1Tessellator, Icon par2Icon) {
   }
}
