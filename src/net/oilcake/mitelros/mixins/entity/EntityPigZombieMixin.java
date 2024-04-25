package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPigZombie.class)
public class EntityPigZombieMixin extends EntityMonster {
    private int frenzied_counter = 0;

    public EntityPigZombieMixin(World par1World) {
        super(par1World);
    }

    @Shadow
    private void becomeAngryAt(Entity par1Entity) {

    }
    @Inject(method = "readEntityFromNBT", at = @At("RETURN"))
    public void injectReadNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
        this.frenzied_counter = par1NBTTagCompound.getInteger("frenzied_counter");
    }
    @Inject(method = "writeEntityToNBT", at = @At("RETURN"))
    public void injectWriteNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
        par1NBTTagCompound.setInteger("frenzied_counter", this.frenzied_counter);
    }
    @Inject(method = "onUpdate", at = @At("RETURN"))
    public void injectUpdate(CallbackInfo callbackInfo){
        if(this.frenzied_counter > 0){
            --this.frenzied_counter;
        }
    }
    public void respondCallingToAttack(Entity par1Entity){
        this.becomeAngryAt(par1Entity);
        this.frenzied_counter = 600;
    }
    @Override
    public boolean isFrenzied() {
        return this.frenzied_counter > 0 || super.isFrenzied();
    }
}
