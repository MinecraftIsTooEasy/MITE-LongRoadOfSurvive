package net.oilcake.mitelros.entity.model;

import net.minecraft.*;
import org.lwjgl.opengl.GL11;

public class ModelBipedOuter extends bbj{
    //bcu -> bcu
    public bcu c;
    public bcu d;
    public bcu e;
    public bcu bipedBodywear;
    public bcu f;
    public bcu bipedRightArmwear;
    public bcu g;
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


        this.c = new bcu(this, 0, 0);
        this.c.a(-4.0F, -8.0F, -4.0F, 8, 8, 8, par1);
        this.c.a(0.0F, 0.0F + par2, 0.0F);


        this.d = new bcu(this, 32, 0);
        this.d.a(-4.0F, -8.0F, -4.0F, 8, 8, 8, par1 + 0.5F);
        this.d.a(0.0F, 0.0F + par2, 0.0F);

        this.e = new bcu(this, 16, 16);
        this.e.a(-4.0F, 0.0F, -2.0F, 8, 12, 4, par1);
        this.e.a(0.0F, 0.0F + par2, 0.0F);

        this.bipedBodywear = new bcu(this, 16, 32);
        this.bipedBodywear.a(-4.0F, 0.0F, -2.0F, 8, 12, 4, par1 + 0.25F);
        this.bipedBodywear.a(0.0F, 0.0F + par2, 0.0F);

        this.f = new bcu(this, 40, 16);
        this.f.a(-3.0F, -2.0F, -2.0F, 4, 12, 4, par1);
        this.f.a(-5.0F, 2.0F + par2, 0.0F);

        this.bipedRightArmwear = new bcu(this, 40, 32);
        this.bipedRightArmwear.a(-3.0F, -2.0F, -2.0F, 4, 12, 4, par1 + 0.25F);
        this.bipedRightArmwear.a(-5.0F, 2.0F + par2, 0.0F);

        this.g = new bcu(this, 32, 48);
        this.g.a(-1.0F, -2.0F, -2.0F, 4, 12, 4, par1);
        this.g.a(5.0F, 2.0F + par2, 0.0F);

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
            this.c.a(par7);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
            GL11.glTranslatef(0.0F, 24.0F * par7, 0.0F);
            this.e.a(par7);
            this.f.a(par7);
            this.g.a(par7);
            this.bipedRightLeg.a(par7);
            this.bipedLeftLeg.a(par7);
            this.d.a(par7);
            this.bipedBodywear.a(par7);
            this.bipedRightArmwear.a(par7);
            this.bipedLeftArmwear.a(par7);
            this.bipedRightLegwear.a(par7);
            this.bipedLeftLegwear.a(par7);
            GL11.glPopMatrix();
        }
        else
        {
            this.c.a(par7);
            this.e.a(par7);
            this.f.a(par7);
            this.g.a(par7);
            this.bipedRightLeg.a(par7);
            this.bipedLeftLeg.a(par7);
            this.d.a(par7);
            this.bipedBodywear.a(par7);
            this.bipedRightArmwear.a(par7);
            this.bipedLeftArmwear.a(par7);
            this.bipedRightLegwear.a(par7);
            this.bipedLeftLegwear.a(par7);
        }
    }
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
    {
        this.c.g = par4 / (180F / (float)Math.PI);
        this.c.f = par5 / (180F / (float)Math.PI);
        this.d.g = this.c.g;
        this.d.f = this.c.f;

        this.f.f = this.bipedRightArmwear.f = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
        this.g.f = this.bipedLeftArmwear.f = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.f.h = this.bipedRightArmwear.h = 0.0F;
        this.g.h = this.bipedLeftArmwear.h = 0.0F;
        this.bipedRightLeg.f = this.bipedRightLegwear.f = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.bipedLeftLeg.f = this.bipedLeftLegwear.f = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
        this.bipedRightLeg.g = this.bipedRightLegwear.g = 0.0F;
        this.bipedLeftLeg.g = this.bipedLeftLegwear.g = 0.0F;
        boolean var8 = par7Entity instanceof EntitySkeleton && par7Entity.getAsEntityLiving().isWearingCuirass(true);

        if (var8)
        {
            this.g.f = this.bipedLeftArmwear.f = 0.0F;
            this.f.f = this.bipedRightArmwear.f = 0.0F;
        }

        this.q = par7Entity.isRiding();

        if (this.q)
        {
            this.f.f += -((float)Math.PI / 5F);
            this.g.f += -((float)Math.PI / 5F);
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
            this.g.f = this.g.f * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemLeft;
            this.bipedLeftArmwear.f = this.bipedLeftArmwear.f * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemLeft;
        }

        if (this.heldItemRight != 0)
        {
            this.f.f = this.f.f * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemRight;
            this.bipedRightArmwear.f = this.bipedRightArmwear.f * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemRight;
        }

        this.f.g = 0.0F;
        this.g.g = 0.0F;
        this.bipedRightArmwear.g = 0.0F;
        this.bipedLeftArmwear.g = 0.0F;
        float var9;
        float var10;

        if (this.p > -9990.0F)
        {
            var9 = this.p;
            this.e.g = MathHelper.sin(MathHelper.sqrt_float(var9) * (float)Math.PI * 2.0F) * 0.2F;
            this.f.e = this.bipedRightArmwear.e = MathHelper.sin(this.e.g) * 5.0F;
            this.f.c = this.bipedRightArmwear.c = -MathHelper.cos(this.e.g) * 5.0F;
            this.g.e = this.bipedLeftArmwear.e = -MathHelper.sin(this.e.g) * 5.0F;
            this.g.c = this.bipedLeftArmwear.c = MathHelper.cos(this.e.g) * 5.0F;
            this.f.g += this.e.g;
            this.g.g += this.e.g;
            this.g.f += this.e.g;
            this.bipedRightArmwear.g += this.e.g;
            this.bipedLeftArmwear.g += this.e.g;
            this.bipedLeftArmwear.f += this.e.g;
            var9 = 1.0F - this.p;
            var9 *= var9;
            var9 *= var9;
            var9 = 1.0F - var9;
            var10 = MathHelper.sin(var9 * (float)Math.PI);
            float var11 = MathHelper.sin(this.p * (float)Math.PI) * -(this.c.f - 0.7F) * 0.75F;
            this.f.f = this.bipedRightArmwear.f = (float)((double)this.f.f - ((double)var10 * 1.2D + (double)var11));
            this.f.g += this.e.g * 2.0F;
            this.bipedRightArmwear.g += this.e.g * 2.0F;
            this.f.h = this.bipedRightArmwear.h = MathHelper.sin(this.p * (float)Math.PI) * -0.4F;

            if (var8)
            {
                --this.g.f;
                this.f.f = this.g.f;
                this.f.g = -this.g.g;
                this.f.h = this.g.h;
                --this.bipedLeftArmwear.f;
                this.bipedRightArmwear.f = this.bipedLeftArmwear.f;
                this.bipedRightArmwear.g = -this.bipedLeftArmwear.g;
                this.bipedRightArmwear.h = this.bipedLeftArmwear.h;
            }
        }

        if (this.isSneak)
        {
            this.e.f = 0.5F;
            this.f.f += 0.4F;
            this.g.f += 0.4F;
            this.bipedRightLeg.e = 4.0F;
            this.bipedLeftLeg.e = 4.0F;
            this.bipedRightLeg.d = 9.0F;
            this.bipedLeftLeg.d = 9.0F;
            this.c.d = 1.0F;
            this.d.d = 1.0F;
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
            this.e.f = 0.0F;
            this.bipedRightLeg.e = 0.1F;
            this.bipedLeftLeg.e = 0.1F;
            this.bipedRightLeg.d = 12.0F;
            this.bipedLeftLeg.d = 12.0F;
            this.c.d = 0.0F;
            this.d.d = 0.0F;
            this.bipedBodywear.f = 0.0F;
            this.bipedRightLegwear.e = 0.1F;
            this.bipedLeftLegwear.e = 0.1F;
            this.bipedRightLegwear.d = 12.0F;
            this.bipedLeftLegwear.d = 12.0F;
        }

        this.f.h += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.g.h -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.f.f += MathHelper.sin(par3 * 0.067F) * 0.05F;
        this.g.f -= MathHelper.sin(par3 * 0.067F) * 0.05F;
        this.bipedRightArmwear.h += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArmwear.h -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArmwear.f += MathHelper.sin(par3 * 0.067F) * 0.05F;
        this.bipedLeftArmwear.f -= MathHelper.sin(par3 * 0.067F) * 0.05F;

        if (this.aimedBow)
        {
            var9 = 0.0F;
            var10 = 0.0F;
            this.f.h = 0.0F;
            this.g.h = 0.0F;
            this.f.g = -(0.1F - var9 * 0.6F) + this.c.g;
            this.g.g = 0.1F - var9 * 0.6F + this.c.g + 0.4F;
            this.f.f = -((float)Math.PI / 2F) + this.c.f;
            this.g.f = -((float)Math.PI / 2F) + this.c.f;
            this.f.f -= var9 * 1.2F - var10 * 0.4F;
            this.g.f -= var9 * 1.2F - var10 * 0.4F;
            this.f.h += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
            this.g.h -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
            this.f.f += MathHelper.sin(par3 * 0.067F) * 0.05F;
            this.g.f -= MathHelper.sin(par3 * 0.067F) * 0.05F;

            this.bipedRightArmwear.h = 0.0F;
            this.bipedLeftArmwear.h = 0.0F;
            this.bipedRightArmwear.g = -(0.1F - var9 * 0.6F) + this.c.g;
            this.bipedLeftArmwear.g = 0.1F - var9 * 0.6F + this.c.g + 0.4F;
            this.bipedRightArmwear.f = -((float)Math.PI / 2F) + this.c.f;
            this.bipedLeftArmwear.f = -((float)Math.PI / 2F) + this.c.f;
            this.bipedRightArmwear.f -= var9 * 1.2F - var10 * 0.4F;
            this.bipedLeftArmwear.f -= var9 * 1.2F - var10 * 0.4F;
            this.bipedRightArmwear.h += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
            this.bipedLeftArmwear.h -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
            this.bipedRightArmwear.f += MathHelper.sin(par3 * 0.067F) * 0.05F;
            this.bipedLeftArmwear.f -= MathHelper.sin(par3 * 0.067F) * 0.05F;
        }
    }
}
