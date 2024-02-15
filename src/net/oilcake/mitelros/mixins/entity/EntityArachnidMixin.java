package net.oilcake.mitelros.mixins.entity;

import net.minecraft.EntityArachnid;
import net.minecraft.EntityMonster;
import net.minecraft.NBTTagCompound;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityArachnid.class)
public class EntityArachnidMixin extends EntityMonster {
    private int frenzied_counter;

    public EntityArachnidMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "readNBT", at = @At("RETURN"))
    public void injectReadNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
        par1NBTTagCompound.setInteger("frenzied_counter", this.frenzied_counter);
    }
    @Inject(method = "writeNBT", at = @At("RETURN"))
    public void injectWriteNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
        par1NBTTagCompound.setInteger("frenzied_counter", this.frenzied_counter);
    }
    @Inject(method = "onUpdate", at = @At("INVOKE"))
    public void injectUpdate(CallbackInfo callbackInfo){
        if(this.frenzied_counter > 0){
            --this.frenzied_counter;
        }
    }
    public void setFrenzied_counter(int counter){
        this.frenzied_counter = counter;
    }
    @Override
    public boolean isFrenzied(){
        return super.isFrenzied() || this.frenzied_counter > 0;
    }
}
