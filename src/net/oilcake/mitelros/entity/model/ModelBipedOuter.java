package net.oilcake.mitelros.entity.model;

import net.minecraft.*;
import org.lwjgl.opengl.GL11;

public class ModelBipedOuter extends bbo{
    //bcu -> bcu
    public bcu bipedHead;
    public bcu bipedHeadwear;
    public bcu bipedBody;
    public bcu bipedBodywear;
    public bcu bipedRightArm;
    public bcu bipedRightArmwear;
    public bcu bipedLeftArm;
    public bcu bipedLeftArmwear;
    public bcu bipedRightLeg;
    public bcu bipedRightLegwear;
    public bcu bipedLeftLeg;
    public bcu bipedLeftLegwear;
    public bcu bipedEars;
    public bcu bipedCloak;
    public int heldItemLeft;
    public int heldItemRight;
    public boolean isSneak;
    public boolean aimedBow;
    public ModelBipedOuter()
    {
        this(0.0F);
    }

    public ModelBipedOuter(float par1)
    {
        this(par1, 0.0F, 64, 64);
    }
    public ModelBipedOuter(float par1, float par2, int par3, int par4)
    {
        //bcu.a(FFFIIIF) -> addBox
        //bcu.a(FFF) -> setRotationPoint
        //bcu.i -> mirror

        //width
        this.t = par3;
        //height
        this.u = par4;

        this.bipedCloak = new bcu(this, 0, 0);
        this.bipedCloak.a(-5.0F, 0.0F, -1.0F, 10, 16, 1, par1);


        this.bipedEars = new bcu(this, 24, 0);
        this.bipedEars.a(-3.0F, -6.0F, -1.0F, 6, 6, 1, par1);


        this.bipedHead = new bcu(this, 0, 0);
        this.bipedHead.a(-4.0F, -8.0F, -4.0F, 8, 8, 8, par1);
        this.bipedHead.a(0.0F, 0.0F + par2, 0.0F);


        this.bipedHeadwear = new bcu(this, 32, 0);
        this.bipedHeadwear.a(-4.0F, -8.0F, -4.0F, 8, 8, 8, par1 + 0.5F);
        this.bipedHeadwear.a(0.0F, 0.0F + par2, 0.0F);

        this.bipedBody = new bcu(this, 16, 16);
        this.bipedBody.a(-4.0F, 0.0F, -2.0F, 8, 12, 4, par1);
        this.bipedBody.a(0.0F, 0.0F + par2, 0.0F);

        this.bipedBodywear = new bcu(this, 16, 32);
        this.bipedBodywear.a(-4.0F, 0.0F, -2.0F, 8, 12, 4, par1 + 0.25F);
        this.bipedBodywear.a(0.0F, 0.0F + par2, 0.0F);

        this.bipedRightArm = new bcu(this, 40, 16);
        this.bipedRightArm.a(-3.0F, -2.0F, -2.0F, 4, 12, 4, par1);
        this.bipedRightArm.a(-5.0F, 2.0F + par2, 0.0F);

        this.bipedRightArmwear = new bcu(this, 40, 32);
        this.bipedRightArmwear.a(-3.0F, -2.0F, -2.0F, 4, 12, 4, par1 + 0.25F);
        this.bipedRightArmwear.a(-5.0F, 2.0F + par2, 0.0F);

        this.bipedLeftArm = new bcu(this, 32, 48);
        this.bipedLeftArm.a(-1.0F, -2.0F, -2.0F, 4, 12, 4, par1);
        this.bipedLeftArm.a(5.0F, 2.0F + par2, 0.0F);

        this.bipedLeftArmwear = new bcu(this, 48, 48);
        this.bipedLeftArmwear.a(-1.0F, -2.0F, -2.0F, 4, 12, 4, par1 + 0.25F);
        this.bipedLeftArmwear.a(5.0F, 2.0F + par2, 0.0F);


        this.bipedRightLeg = new bcu(this, 0, 16);
        this.bipedRightLeg.a(-2.0F, 0.0F, -2.0F, 4, 12, 4, par1);
        this.bipedRightLeg.a(-1.9F, 12.0F + par2, 0.0F);

        this.bipedRightLegwear = new bcu(this, 0, 32);
        this.bipedRightLegwear.a(-2.0F, 0.0F, -2.0F, 4, 12, 4, par1 + 0.25F);
        this.bipedRightLegwear.a(-1.9F, 12.0F + par2, 0.0F);

        this.bipedLeftLeg = new bcu(this, 16, 48);
        this.bipedLeftLeg.a(-2.0F, 0.0F, -2.0F, 4, 12, 4, par1);
        this.bipedLeftLeg.a(1.9F, 12.0F + par2, 0.0F);

        this.bipedLeftLegwear = new bcu(this, 0, 48);
        this.bipedLeftLegwear.a(-2.0F, 0.0F, -2.0F, 4, 12, 4, par1 + 0.25F);
        this.bipedLeftLegwear.a(1.9F, 12.0F + par2, 0.0F);
    }

    public void a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        this.a(par2, par3, par4, par5, par6, par7, par1Entity);

        if (this.s)
        {
            float var8 = 2.0F;
            GL11.glPushMatrix();
            GL11.glScalef(1.5F / var8, 1.5F / var8, 1.5F / var8);
            GL11.glTranslatef(0.0F, 16.0F * par7, 0.0F);
            this.bipedHead.a(par7);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
            GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
            this.bipedBody.a(par7);
            this.bipedRightArm.a(par7);
            this.bipedLeftArm.a(par7);
            this.bipedRightLeg.a(par7);
            this.bipedLeftLeg.a(par7);
            this.bipedHeadwear.a(par7);
            this.bipedBodywear.a(par7);
            this.bipedRightArmwear.a(par7);
            this.bipedLeftArmwear.a(par7);
            this.bipedRightLegwear.a(par7);
            this.bipedLeftLegwear.a(par7);
            GL11.glPopMatrix();
        }
        else
        {
            this.bipedHead.a(par7);
            this.bipedBody.a(par7);
            this.bipedRightArm.a(par7);
            this.bipedLeftArm.a(par7);
            this.bipedRightLeg.a(par7);
            this.bipedLeftLeg.a(par7);
            this.bipedHeadwear.a(par7);
            this.bipedBodywear.a(par7);
            this.bipedRightArmwear.a(par7);
            this.bipedLeftArmwear.a(par7);
            this.bipedRightLegwear.a(par7);
            this.bipedLeftLegwear.a(par7);
        }
    }
    public void a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
        this.bipedHead.g = par4 / (180F / (float)Math.PI);
        this.bipedHead.f = par5 / (180F / (float)Math.PI);
        this.bipedHeadwear.g = this.bipedHead.g;
        this.bipedHeadwear.f = this.bipedHead.f;

        this.bipedRightArm.f = this.bipedRightArmwear.f = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
        this.bipedLeftArm.f = this.bipedLeftArmwear.f = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.bipedRightArm.h = this.bipedRightArmwear.h = 0.0F;
        this.bipedLeftArm.h = this.bipedLeftArmwear.h = 0.0F;
        this.bipedRightLeg.f = this.bipedRightLegwear.f = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.bipedLeftLeg.f = this.bipedLeftLegwear.f = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
        this.bipedRightLeg.g = this.bipedRightLegwear.g = 0.0F;
        this.bipedLeftLeg.g = this.bipedLeftLegwear.g = 0.0F;
        boolean var8 = par7Entity instanceof EntitySkeleton && par7Entity.getAsEntityLiving().isWearingCuirass(true);

        if (var8)
        {
            this.bipedLeftArm.f = this.bipedLeftArmwear.f = 0.0F;
            this.bipedRightArm.f = this.bipedRightArmwear.f = 0.0F;
        }

        this.q = par7Entity.isRiding();

        if (this.q)
        {
            this.bipedRightArm.f += -((float)Math.PI / 5F);
            this.bipedLeftArm.f += -((float)Math.PI / 5F);
            this.bipedRightLeg.f = -((float)Math.PI * 2F / 5F);
            this.bipedLeftLeg.f = -((float)Math.PI * 2F / 5F);
            this.bipedRightLeg.g = ((float)Math.PI / 10F);
            this.bipedLeftLeg.g = -((float)Math.PI / 10F);

            this.bipedRightArmwear.f += -((float)Math.PI / 5F);
            this.bipedLeftArmwear.f += -((float)Math.PI / 5F);
            this.bipedRightLegwear.f = -((float)Math.PI * 2F / 5F);
            this.bipedLeftLegwear.f = -((float)Math.PI * 2F / 5F);
            this.bipedRightLegwear.g = ((float)Math.PI / 10F);
            this.bipedLeftLegwear.g = -((float)Math.PI / 10F);
        }

        if (this.heldItemLeft != 0)
        {
            this.bipedLeftArm.f = this.bipedLeftArm.f * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemLeft;
            this.bipedLeftArmwear.f = this.bipedLeftArmwear.f * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemLeft;
        }

        if (this.heldItemRight != 0)
        {
            this.bipedRightArm.f = this.bipedRightArm.f * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemRight;
            this.bipedRightArmwear.f = this.bipedRightArmwear.f * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemRight;
        }

        this.bipedRightArm.g = 0.0F;
        this.bipedLeftArm.g = 0.0F;
        this.bipedRightArmwear.g = 0.0F;
        this.bipedLeftArmwear.g = 0.0F;
        float var9;
        float var10;

        if (this.p > -9990.0F)
        {
            var9 = this.p;
            this.bipedBody.g = MathHelper.sin(MathHelper.sqrt_float(var9) * (float)Math.PI * 2.0F) * 0.2F;
            this.bipedRightArm.e = this.bipedRightArmwear.e = MathHelper.sin(this.bipedBody.g) * 5.0F;
            this.bipedRightArm.c = this.bipedRightArmwear.c = -MathHelper.cos(this.bipedBody.g) * 5.0F;
            this.bipedLeftArm.e = this.bipedLeftArmwear.e = -MathHelper.sin(this.bipedBody.g) * 5.0F;
            this.bipedLeftArm.c = this.bipedLeftArmwear.c = MathHelper.cos(this.bipedBody.g) * 5.0F;
            this.bipedRightArm.g += this.bipedBody.g;
            this.bipedLeftArm.g += this.bipedBody.g;
            this.bipedLeftArm.f += this.bipedBody.g;
            this.bipedRightArmwear.g += this.bipedBody.g;
            this.bipedLeftArmwear.g += this.bipedBody.g;
            this.bipedLeftArmwear.f += this.bipedBody.g;
            var9 = 1.0F - this.p;
            var9 *= var9;
            var9 *= var9;
            var9 = 1.0F - var9;
            var10 = MathHelper.sin(var9 * (float)Math.PI);
            float var11 = MathHelper.sin(this.p * (float)Math.PI) * -(this.bipedHead.f - 0.7F) * 0.75F;
            this.bipedRightArm.f = this.bipedRightArmwear.f = (float)((double)this.bipedRightArm.f - ((double)var10 * 1.2D + (double)var11));
            this.bipedRightArm.g += this.bipedBody.g * 2.0F;
            this.bipedRightArmwear.g += this.bipedBody.g * 2.0F;
            this.bipedRightArm.h = this.bipedRightArmwear.h = MathHelper.sin(this.p * (float)Math.PI) * -0.4F;

            if (var8)
            {
                --this.bipedLeftArm.f;
                this.bipedRightArm.f = this.bipedLeftArm.f;
                this.bipedRightArm.g = -this.bipedLeftArm.g;
                this.bipedRightArm.h = this.bipedLeftArm.h;
                --this.bipedLeftArmwear.f;
                this.bipedRightArmwear.f = this.bipedLeftArmwear.f;
                this.bipedRightArmwear.g = -this.bipedLeftArmwear.g;
                this.bipedRightArmwear.h = this.bipedLeftArmwear.h;
            }
        }

        if (this.isSneak)
        {
            this.bipedBody.f = 0.5F;
            this.bipedRightArm.f += 0.4F;
            this.bipedLeftArm.f += 0.4F;
            this.bipedRightLeg.e = 4.0F;
            this.bipedLeftLeg.e = 4.0F;
            this.bipedRightLeg.d = 9.0F;
            this.bipedLeftLeg.d = 9.0F;
            this.bipedHead.d = 1.0F;
            this.bipedHeadwear.d = 1.0F;
            this.bipedBodywear.f = 0.5F;
            this.bipedRightArmwear.f += 0.4F;
            this.bipedLeftArmwear.f += 0.4F;
            this.bipedRightLegwear.e = 4.0F;
            this.bipedLeftLegwear.e = 4.0F;
            this.bipedRightLegwear.d = 9.0F;
            this.bipedLeftLegwear.d = 9.0F;
        }
        else
        {
            this.bipedBody.f = 0.0F;
            this.bipedRightLeg.e = 0.1F;
            this.bipedLeftLeg.e = 0.1F;
            this.bipedRightLeg.d = 12.0F;
            this.bipedLeftLeg.d = 12.0F;
            this.bipedHead.d = 0.0F;
            this.bipedHeadwear.d = 0.0F;
            this.bipedBodywear.f = 0.0F;
            this.bipedRightLegwear.e = 0.1F;
            this.bipedLeftLegwear.e = 0.1F;
            this.bipedRightLegwear.d = 12.0F;
            this.bipedLeftLegwear.d = 12.0F;
        }

        this.bipedRightArm.h += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.h -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.f += MathHelper.sin(par3 * 0.067F) * 0.05F;
        this.bipedLeftArm.f -= MathHelper.sin(par3 * 0.067F) * 0.05F;
        this.bipedRightArmwear.h += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArmwear.h -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArmwear.f += MathHelper.sin(par3 * 0.067F) * 0.05F;
        this.bipedLeftArmwear.f -= MathHelper.sin(par3 * 0.067F) * 0.05F;

        if (this.aimedBow)
        {
            var9 = 0.0F;
            var10 = 0.0F;
            this.bipedRightArm.h = 0.0F;
            this.bipedLeftArm.h = 0.0F;
            this.bipedRightArm.g = -(0.1F - var9 * 0.6F) + this.bipedHead.g;
            this.bipedLeftArm.g = 0.1F - var9 * 0.6F + this.bipedHead.g + 0.4F;
            this.bipedRightArm.f = -((float)Math.PI / 2F) + this.bipedHead.f;
            this.bipedLeftArm.f = -((float)Math.PI / 2F) + this.bipedHead.f;
            this.bipedRightArm.f -= var9 * 1.2F - var10 * 0.4F;
            this.bipedLeftArm.f -= var9 * 1.2F - var10 * 0.4F;
            this.bipedRightArm.h += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
            this.bipedLeftArm.h -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
            this.bipedRightArm.f += MathHelper.sin(par3 * 0.067F) * 0.05F;
            this.bipedLeftArm.f -= MathHelper.sin(par3 * 0.067F) * 0.05F;

            this.bipedRightArmwear.h = 0.0F;
            this.bipedLeftArmwear.h = 0.0F;
            this.bipedRightArmwear.g = -(0.1F - var9 * 0.6F) + this.bipedHead.g;
            this.bipedLeftArmwear.g = 0.1F - var9 * 0.6F + this.bipedHead.g + 0.4F;
            this.bipedRightArmwear.f = -((float)Math.PI / 2F) + this.bipedHead.f;
            this.bipedLeftArmwear.f = -((float)Math.PI / 2F) + this.bipedHead.f;
            this.bipedRightArmwear.f -= var9 * 1.2F - var10 * 0.4F;
            this.bipedLeftArmwear.f -= var9 * 1.2F - var10 * 0.4F;
            this.bipedRightArmwear.h += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
            this.bipedLeftArmwear.h -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
            this.bipedRightArmwear.f += MathHelper.sin(par3 * 0.067F) * 0.05F;
            this.bipedLeftArmwear.f -= MathHelper.sin(par3 * 0.067F) * 0.05F;
        }
    }
    public void b(float par1) {
        this.bipedEars.g = this.bipedHead.g;
        this.bipedEars.f = this.bipedHead.f;
        this.bipedEars.c = 0.0F;
        this.bipedEars.d = 0.0F;
        this.bipedEars.a(par1);
    }

    public void c(float par1) {
        this.bipedCloak.a(par1);
    }
}
