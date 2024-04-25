package net.oilcake.mitelros.client.render;

import net.minecraft.Entity;
import net.minecraft.EntityLiving;
import net.minecraft.bhe;
import net.minecraft.bjo;
import net.oilcake.mitelros.entity.EntityAgarician;
import net.oilcake.mitelros.entity.model.ModelAgarician;
import org.lwjgl.opengl.GL11;

public class RenderAgarician extends bhe{
    public RenderAgarician() {
        super(new ModelAgarician(), 1.0F);
    }

    protected void a(EntityAgarician par1) {
        if(par1.isChild()){
            GL11.glScalef(0.75F, 0.75F, 0.75F);
        }else {
            GL11.glScalef(1.5F, 1.5F, 1.5F);
        }

    }

    protected bjo a(Entity entity) {
        return this.textures[((EntityAgarician)entity).getType()];
    }

    protected void a(EntityLiving par1EntityLivingBase, float par2) {
        this.a((EntityAgarician) par1EntityLivingBase);
    }

    protected void setTextures() {
        this.setTexture(0, "textures/entity/agarician_brown");
        this.setTexture(1, "textures/entity/agarician_red");
        this.setTexture(2, "textures/entity/agarician_luminescent");
    }
}
