package net.oilcake.mitelros.mixins.render;

import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityWandFireball;
import net.oilcake.mitelros.entity.EntityWandIceBall;
import net.oilcake.mitelros.entity.EntityWandShockWave;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(bgx.class)
public class RenderSnowballMixin extends bgm {
    @Shadow
    private Item a;
    @Shadow
    private int f;
    @Overwrite
    public void a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        if (par1Entity instanceof EntityBrick) {
            this.a = ((EntityBrick)par1Entity).getModelItem();
        } else if (par1Entity instanceof EntityWandIceBall) {
            this.a = ((EntityWandIceBall)par1Entity).getModelItem();
        } else if (par1Entity instanceof EntityWandFireball) {
            this.a = ((EntityWandFireball)par1Entity).getModelItem();
        } else if (par1Entity instanceof EntityWandShockWave) {
            this.a = ((EntityWandShockWave)par1Entity).getModelItem();
        } else if (par1Entity instanceof EntityGelatinousSphere) {
            this.f = ((EntityGelatinousSphere)par1Entity).getModelSubtype();
        }

        IIcon var10 = this.a.getIconFromSubtype(this.f);
        if (var10 != null) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)par2, (float)par4, (float)par6);
            GL11.glEnable(32826);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            this.b(par1Entity);
            bfq var11 = bfq.a;
            if (var10 == ItemPotion.e("bottle_splash")) {
                int var12 = PotionBrewer.a(((EntityPotion)par1Entity).getPotionType(), false);
                float var13 = (float)(var12 >> 16 & 255) / 255.0F;
                float var14 = (float)(var12 >> 8 & 255) / 255.0F;
                float var15 = (float)(var12 & 255) / 255.0F;
                GL11.glColor3f(var13, var14, var15);
                GL11.glPushMatrix();
                this.a(var11, ItemPotion.e("overlay"));
                GL11.glPopMatrix();
                GL11.glColor3f(1.0F, 1.0F, 1.0F);
            }

            this.a(var11, var10);
            GL11.glDisable(32826);
            GL11.glPopMatrix();
        }

    }

    @Shadow
    protected bjo a(Entity entity) {
        return null;
    }
    @Shadow
    private void a(bfq par1Tessellator, IIcon par2Icon) {
    }
}
