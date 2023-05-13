package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityClusterSpider;
import net.oilcake.mitelros.entity.EntityMinerZombie;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EntityRevenant.class)
public class EntityRevenantMixin extends EntityZombie {
    public EntityRevenantMixin(World world) {
        super(world);
    }
    @Overwrite
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.setEntityAttribute(GenericAttributes.followRange, 40.0);
        this.setEntityAttribute(GenericAttributes.movementSpeed, 0.25999999046325684);
        this.setEntityAttribute(GenericAttributes.attackDamage, StuckTagConfig.TagConfig.TagFallenInMineLVL2.ConfigValue ? 8.75 : 7.0);
        this.setEntityAttribute(field_110186_bp, this.rand.nextDouble() * 0.10000000149011612);
        this.setEntityAttribute(GenericAttributes.maxHealth, StuckTagConfig.TagConfig.TagFallenInMineLVL2.ConfigValue ? 45.0 : 30.0);
    }
    private int spawnCounter;
    private int spawnSums;
    public void onUpdate()
    {
        super.onUpdate();
        if (!getWorld().isRemote)
        {
            if (spawnSums <= 8)
                if (spawnCounter < 20)
                {
                    if(StuckTagConfig.TagConfig.TagFallenInMineLVL2.ConfigValue) spawnCounter++;
                } else
                {
                    EntityMinerZombie Belongings = new EntityMinerZombie(worldObj);
                    Belongings.setPosition(posX, posY, posZ);
                    Belongings.refreshDespawnCounter(-9600);
                    worldObj.spawnEntityInWorld(Belongings);
                    Belongings.onSpawnWithEgg(null);
                    Belongings.entityFX(EnumEntityFX.summoned);
                    spawnCounter = 0;
                    spawnSums++;
                }
        }
    }
}
