package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityBoneBodyguard;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EntityBoneLord.class)
public class EntityBoneLordMixin extends EntitySkeleton {

    public EntityBoneLordMixin(World par1World) {
        super(par1World);
    }
    @Overwrite
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.setEntityAttribute(GenericAttributes.followRange, 128.0);
        this.setEntityAttribute(GenericAttributes.movementSpeed, 0.25999999046325684);
        this.setEntityAttribute(GenericAttributes.attackDamage, StuckTagConfig.TagConfig.TagBattleSufferLVL2.ConfigValue ? 7.0 : 5.0);
        this.setEntityAttribute(GenericAttributes.maxHealth, StuckTagConfig.TagConfig.TagBattleSufferLVL2.ConfigValue ? 30.0 : 20.0);
    }
    @Overwrite
    public Class getTroopClass() {
        return this.isAncientBoneLord() ? (StuckTagConfig.TagConfig.TagBattleSufferLVL2.ConfigValue ? EntityLongdeadGuardian.class : EntityLongdead.class) : (StuckTagConfig.TagConfig.TagBattleSufferLVL2.ConfigValue ? EntityBoneBodyguard.class : EntitySkeleton.class );
    }
}
