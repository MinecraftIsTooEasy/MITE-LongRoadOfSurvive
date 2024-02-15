package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EntityShadow.class)
public class EntityShadowMixin extends EntityMonster{
    public EntityShadowMixin(World par1World) {
        super(par1World);
    }
    private boolean cursed_player = false;
//    @Overwrite
//    public boolean isImmuneTo(DamageSource damage_source) {
//        boolean temp = !damage_source.hasSilverAspect() && !damage_source.hasMagicAspect() && !damage_source.isSunlight();
//        return damage_source.getItemAttackedWith() != null ? temp && !(damage_source.getItemAttackedWith().getMaterialForRepairs() == Materials.nickel) : temp;
//    }
    @Overwrite
    public GroupDataEntity onSpawnWithEgg(GroupDataEntity par1EntityLivingData) {
        this.setCanPickUpLoot(true);
        return super.onSpawnWithEgg(par1EntityLivingData);
    }
    @Overwrite
    public void onLivingUpdate() {
        if (!this.worldObj.isRemote) {
            if (this.isInSunlight()) {
                this.attackEntityFrom(new Damage(DamageSource.sunlight, 1000.0F));
            } else if (this.ticksExisted % 40 == 0) {
                float brightness = this.getBrightness(1.0F);
                int amount_to_heal = (int)((0.4F - brightness) * 10.0F);
                if (amount_to_heal > 0) {
                    this.heal((float)amount_to_heal);
                }
            }
            if(this.getTarget()!=null && !this.cursed_player && StuckTagConfig.TagConfig.TagPseudovision.ConfigValue){
                Entity target = this.getTarget();
                if(target instanceof EntityPlayer){
                    this.cursed_player = true;
                    target.getAsPlayer().vision_dimming += target.getAsEntityLivingBase().getAmountAfterResistance(2.0F, 4);
                }
            }
        }
        this.tryDisableNearbyLightSource();
        super.onLivingUpdate();
    }
//    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
//        super.readEntityFromNBT(par1NBTTagCompound);
//        this.cursed_player = par1NBTTagCompound.getBoolean("HasCursedPlayer");
//    }
//    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
//        super.writeEntityToNBT(par1NBTTagCompound);
//        par1NBTTagCompound.setBoolean("HasCursedPlayer", this.cursed_player);
//    }
}
