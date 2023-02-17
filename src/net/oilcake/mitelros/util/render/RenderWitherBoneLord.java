package net.oilcake.mitelros.util.render;
import net.minecraft.*;
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
}
