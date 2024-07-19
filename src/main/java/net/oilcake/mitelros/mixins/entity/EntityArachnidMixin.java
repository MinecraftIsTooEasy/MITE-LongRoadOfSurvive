package net.oilcake.mitelros.mixins.entity;


import net.minecraft.EntityArachnid;
import net.minecraft.EntityMob;
import net.minecraft.NBTTagCompound;
import net.minecraft.World;
import net.oilcake.mitelros.iinjected.EntityArachnidII;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityArachnid.class)
public class EntityArachnidMixin extends EntityMob implements EntityArachnidII {
   private int frenzied_counter;

   public EntityArachnidMixin(World par1World) {
      super(par1World);
   }

   @Inject(
      method = {"readEntityFromNBT(Lnet/minecraft/NBTTagCompound;)V"},
      at = {@At("RETURN")}
   )
   public void injectReadNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
      this.frenzied_counter = par1NBTTagCompound.getInteger("frenzied_counter");
   }

   @Inject(
      method = {"writeEntityToNBT(Lnet/minecraft/NBTTagCompound;)V"},
      at = {@At("RETURN")}
   )
   public void injectWriteNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
      par1NBTTagCompound.setInteger("frenzied_counter", this.frenzied_counter);
   }

   @Inject(
      method = {"onUpdate()V"},
      at = {@At("RETURN")}
   )
   public void injectUpdate(CallbackInfo callbackInfo) {
      if (this.frenzied_counter > 0) {
         --this.frenzied_counter;
      }

   }

   @Override
   @Unique
   public void setFrenzied_counter(int counter) {
      this.frenzied_counter = counter;
   }

   @Override
   public boolean isFrenzied() {
      return this.frenzied_counter > 0 || super.isFrenzied();
   }
}
