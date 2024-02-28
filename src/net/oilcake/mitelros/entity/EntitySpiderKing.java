package net.oilcake.mitelros.entity;

import net.minecraft.*;

import java.util.Iterator;
import java.util.List;

public class EntitySpiderKing extends EntityArachnid {
    private final int num_webs;
    public EntitySpiderKing(World par1World) {
        super(par1World, 1.45F);
        this.num_webs = 4;
    }

    protected String getLivingSound() {
        return "imported.mob.spiderking.say";
    }

    protected String getHurtSound() {
        return "imported.mob.spiderking.hit";
    }

    protected String getDeathSound() {
        return "imported.mob.spiderking.death";
    }

    protected float getSoundVolume(String sound) {
        return super.getSoundVolume(sound) * 1.3F;
    }

    protected float getSoundPitch(String sound) {
        return super.getSoundPitch(sound) * 0.6F;
    }
    public boolean peacefulDuringDay() {
        return false;
    }
    public float getNaturalDefense(DamageSource damage_source) {
        return super.getNaturalDefense(damage_source) + (damage_source.bypassesMundaneArmor() ? 0.0F : 3.0F);
    }
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.setEntityAttribute(GenericAttributes.maxHealth, 28.0);
        this.setEntityAttribute(GenericAttributes.followRange, 56.0);
        this.setEntityAttribute(GenericAttributes.attackDamage, 13.0);
        this.setEntityAttribute(GenericAttributes.movementSpeed, 0.92);
    }
    public EntityDamageResult attackEntityAsMob(Entity target) {
        EntityDamageResult result = super.attackEntityAsMob(target);
        if (result != null && !result.entityWasDestroyed()) {
            if (result.entityLostHealth() && target instanceof EntityLiving) {
                target.getAsEntityLivingBase().addPotionEffect(new MobEffect(MobEffectList.moveSlowdown.id, 200, 5));
                target.getAsEntityLivingBase().addPotionEffect(new MobEffect(MobEffectList.poison.id, 850, 0));
            }

            return result;
        } else {
            return result;
        }
    }
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
    private int spawnCounter;
    private int spawnSums;
    private boolean gathering_troops = false;
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        if (!getWorld().isRemote)
        {
            List nearby_spiders = this.worldObj.getEntitiesWithinAABB(EntityArachnid.class, this.boundingBox.expand(16.0D, 8.0D, 16.0D));
            Iterator i = nearby_spiders.iterator();
            if(this.getTicksExistedWithOffset() % 100 == 0){
                while (i.hasNext()){
                    EntityArachnid spiders = (EntityArachnid) i.next();
                    if(spiders != this){
                        spiders.setFrenzied_counter(200);
                        if(spiders.getHealthFraction() < 1.0F){
                            spiders.healByPercentage(0.2F);
                            spiders.entityFX(EnumEntityFX.repair);
                        }
                    }
                }
            }

            if(this.getTarget()!=null){
                if(!this.isNoDespawnRequired() && this.getTarget() != null){
                    this.gathering_troops = true;
                    this.func_110163_bv();
                }
            }
                if (spawnSums < 8 && gathering_troops) {
                    if (spawnCounter < 20) {
                        spawnCounter++;
                    } else {
                        EntityClusterSpider clusterSpider = new EntityClusterSpider(worldObj);
                        clusterSpider.setPosition(posX + this.rand.nextInt(4) - this.rand.nextInt(4),posY, posZ - this.rand.nextInt(4) + this.rand.nextInt(4));
                        clusterSpider.refreshDespawnCounter(-9600);
                        worldObj.spawnEntityInWorld(clusterSpider);
                        clusterSpider.onSpawnWithEgg(null);
                        clusterSpider.entityFX(EnumEntityFX.summoned);
                        spawnCounter = 0;
                        spawnSums++;
                    }
                }
        }
    }
    @Override
    public void onDeathUpdate(){
        super.onDeathUpdate();
        if (this.deathTime == 20) {
            EntityPotion potion = new EntityPotion(this.worldObj,this,16388);
            potion.setPosition(this.posX,this.posY - 1,this.posZ);
            this.worldObj.spawnEntityInWorld(potion);
            potion = new EntityPotion(this.worldObj,this,16426);
            potion.setPosition(this.posX,this.posY - 1,this.posZ);
            this.worldObj.spawnEntityInWorld(potion);
        }
    }
    public int getExperienceValue() {
        return super.getExperienceValue() * 3;
    }
}
