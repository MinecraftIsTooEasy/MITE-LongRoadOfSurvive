package net.oilcake.mitelros.util.render;
import net.minecraft.*;
public class RenderWitherBodyguard extends bgu {
    public RenderWitherBodyguard() {
        super(new bbz(), 0.5F);
    }

    @Override
    protected void setTextures() {
        this.setTexture(0, "textures/entity/skeleton/wither_skeleton");
    }
    protected bjo a(EntityInsentient par1EntityLiving) {
        return this.textures[0];
    }

    protected bjo a(Entity par1Entity) {
        return this.a((EntitySkeleton)par1Entity);
    }
}