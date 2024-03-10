package net.oilcake.mitelros.mixins.render;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(bhu.class)
public abstract class RenderVillagerMixin extends bhe{
    public RenderVillagerMixin(bbo par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }
    @Overwrite
    protected void setTextures() {
        this.setTexture(0, "textures/entity/villager/villager");
        this.setTexture(1, "textures/entity/villager/farmer");
        this.setTexture(2, "textures/entity/villager/librarian");
        this.setTexture(3, "textures/entity/villager/priest");
        this.setTexture(4, "textures/entity/villager/smith");
        this.setTexture(5, "textures/entity/villager/butcher");
    }
    @Overwrite
    protected bjo a(EntityVillager par1EntityVillager) {
        switch (par1EntityVillager.getProfession()) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return this.textures[1];
            case 7:
            case 8:
                return this.textures[2];
            case 9:
            case 10:
                return this.textures[3];
            case 11:
            case 12:
                return this.textures[4];
            case 13:
            case 14:
                return this.textures[5];
            default:
                return this.textures[0];
        }
    }
}
