package net.oilcake.mitelros.util.render;

import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityLich;

public class RenderLich extends bgu {
    public RenderLich() {
        super(new bbz(), 0.5F);
    }

    protected boolean forceGlowOverride(){
        return true;
    }

    @Override
    protected void setTextures() {
        this.setTexture(0, "textures/entity/skeleton/lich");
    }
    protected bjo a(EntityInsentient par1EntityLiving) {
        return this.textures[0];
    }

    protected bjo a(Entity par1Entity) {
        return this.a((EntitySkeleton)par1Entity);
    }
    public void renderBoss(EntityLich par1EntityLich, double par2, double par4, double par6, float par8, float par9) {
        bez.a(par1EntityLich, false);
        super.a(par1EntityLich, par2, par4, par6, par8, par9);
        this.b(par1EntityLich, par2, par4, par6, par8, par9);
    }
    @Override
    public void a(EntityLiving par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
        this.renderBoss((EntityLich) par1EntityLivingBase, par2, par4, par6, par8, par9);
    }
    @Override
    public void a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderBoss((EntityLich) par1Entity, par2, par4, par6, par8, par9);
    }
    @Override
    public void a(EntityInsentient par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
        this.renderBoss((EntityLich) par1EntityLiving, par2, par4, par6, par8, par9);
    }
}
