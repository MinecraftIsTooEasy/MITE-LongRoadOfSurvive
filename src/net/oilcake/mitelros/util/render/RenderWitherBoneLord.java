package net.oilcake.mitelros.util.render;
import net.minecraft.*;
import org.lwjgl.opengl.GL11;

public class RenderWitherBoneLord extends bgu {
    public RenderWitherBoneLord() {
        super(new bbz(), 0.5F);
    }

    protected boolean forceGlowOverride(){
        return true;
    }

    @Override
    protected void setTextures() {
        this.setTexture(0, "textures/entity/skeleton/wither_bone_lord");
    }
    protected bjo a(EntityInsentient par1EntityLiving) {
        return this.textures[0];
    }

    protected bjo a(Entity par1Entity) {
        return this.a((EntitySkeleton)par1Entity);
    }
    protected void a(EntityLiving par1EntityLivingBase, float par2) {
        this.a((EntitySkeleton)par1EntityLivingBase, par2);
    }
    protected void a(EntitySkeleton par1EntitySkeleton, float par2) {
        if (par1EntitySkeleton.getSkeletonType() == 1) {
            GL11.glScalef(1.2F, 1.2F, 1.2F);
        }

    }
}
