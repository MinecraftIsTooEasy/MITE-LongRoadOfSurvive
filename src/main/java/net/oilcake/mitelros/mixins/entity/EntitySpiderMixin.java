package net.oilcake.mitelros.mixins.entity;

import net.minecraft.EntityArachnid;
import net.minecraft.EntityLivingData;
import net.minecraft.EntityLongdead;
import net.minecraft.EntitySkeleton;
import net.minecraft.EntitySpider;
import net.minecraft.World;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({EntitySpider.class})
public class EntitySpiderMixin extends EntityArachnid {
   public EntitySpiderMixin(World par1World, float scaling) {
      super(par1World, scaling);
   }

   @Overwrite
   private EntitySkeleton getMountedSkeleton() {
      Object var2;
      do {
         var2 = this.worldObj.isUnderworld() ? new EntityLongdead(this.worldObj) : new EntitySkeleton(this.worldObj);
         ((EntitySkeleton)var2).setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
         ((EntitySkeleton)var2).onSpawnWithEgg((EntityLivingData)null);
      } while(((EntitySkeleton)var2).getSkeletonType() != 0 && !(Boolean)StuckTagConfig.TagConfig.TagUnderAlliance.ConfigValue);

      return (EntitySkeleton)var2;
   }
}
