package net.oilcake.mitelros.util.render;

import net.minecraft.*;

public class RenderLich extends bgu {
    public RenderLich() {
        super(new bbz(), 0.5F);
    }

    protected boolean forceGlowOverride(){
        return true;
    }

    @Override
    protected void setTextures() {
        this.setTexture(0, "textures/entity/skeleton/longdead_guardian");
    }
    protected bjo a(EntityInsentient par1EntityLiving) {
        return this.textures[0];
    }

    protected bjo a(Entity par1Entity) {
        return this.a((EntitySkeleton)par1Entity);
    }
}
