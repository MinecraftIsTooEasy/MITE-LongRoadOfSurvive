package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityRetinueZombie;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EntityRevenant.class)
public class EntityRevenantMixin extends EntityZombie {
    private boolean gathering_troops = false;
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
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.spawnSums = par1NBTTagCompound.getByte("num_troops_summoned");
    }

    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        if (this.spawnSums > 0) {
            par1NBTTagCompound.setByte("num_troops_summoned", (byte)this.spawnSums);
        }

    }
    public void onUpdate()
    {
        super.onUpdate();
        if (!getWorld().isRemote)
        {
            if(this.getTarget()!=null){
                if(!this.isNoDespawnRequired() && this.getTarget() != null){
                    this.gathering_troops = true;
                    this.func_110163_bv();
                }
            }
            if (spawnSums <= 8 && gathering_troops) {
                if (spawnCounter < 20) {
                    if (StuckTagConfig.TagConfig.TagFallenInMineLVL2.ConfigValue) spawnCounter++;
                } else {
                    EntityRetinueZombie Belongings = new EntityRetinueZombie(worldObj);
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
}
