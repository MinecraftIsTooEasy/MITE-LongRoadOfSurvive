package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;


@Mixin(EntityInsentient.class)
public abstract class EntityInsentientMixin extends EntityLiving {
    public EntityInsentientMixin(World par1World) {
        super(par1World);
    }
    @Overwrite
    public boolean isTargetWithinStrikingDistance(EntityLiving target) {
        if (!this.isAIEnabled()) {
            Minecraft.setErrorMessage("isTargetWithinStrikingDistance: doesn't handle old AI mobs yet");
            return false;
        } else if (this.getAsEntityLiving() instanceof EntityAnimal) {
            double var2 = (double)(this.width * 1.75F * this.width * 1.75F + target.width);
            if (this.getHeldItemStack() != null) {
                var2 += (double)this.getHeldItemStack().getItem().getReachBonus();
            }
            return this.getDistanceSq(target.posX, target.boundingBox.minY, target.posZ) <= var2;
        } else {
            if(ExperimentalConfig.TagConfig.Realistic.ConfigValue){
                boolean condition1 = this.getDistance(target.posX, target.boundingBox.minY - (this.boundingBox.maxY - this.boundingBox.minY) * 2 / 3, target.posZ) <= (double) this.getReach();
                boolean condition2 = this.getDistance(target.posX, target.boundingBox.maxY + (this.boundingBox.maxY - this.boundingBox.minY) * 2 / 3, target.posZ) <= (double) this.getReach();
                return condition1 || condition2;
            }
            return this.getDistance(target.posX, target.boundingBox.minY, target.posZ) <= (double)this.getReach();
        }
    }
    @Overwrite
    public float getReach() {
        if (!this.isAIEnabled()) {
            Minecraft.setErrorMessage("getReach: doesn't handle old AI mobs yet");
            return 0.0F;
        } else {
            if(ExperimentalConfig.TagConfig.Realistic.ConfigValue){
                return 1.5F + this.getHeldItemReachBonus();
            }
            return 1.5F + this.getHeldItemReachBonus() * 0.6F;
        }
    }
}
