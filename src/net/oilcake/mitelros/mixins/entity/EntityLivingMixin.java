package net.oilcake.mitelros.mixins.entity;

import net.minecraft.EntityLiving;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityLiving.class)
public class EntityLivingMixin {
    public EntityLiving entityLiving;
//    public final float getMaxHealth() {
//        return entityLiving instanceof EntityPlayer ? ((EntityPlayer)entityLiving).getWaterLimit() : (float)this.getEntityAttribute(GenericAttributes.maxHealth).getAttributeValue();
//    }
}
