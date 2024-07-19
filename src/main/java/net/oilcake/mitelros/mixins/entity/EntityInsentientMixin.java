package net.oilcake.mitelros.mixins.entity;

import net.minecraft.Block;
import net.minecraft.EntityAnimal;
import net.minecraft.EntityItem;
import net.minecraft.EntityLiving;
import net.minecraft.EntityLivingBase;
import net.minecraft.ItemStack;
import net.minecraft.MathHelper;
import net.minecraft.Minecraft;
import net.minecraft.World;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({EntityLiving.class})
public abstract class EntityInsentientMixin extends EntityLivingBase {
   public EntityInsentientMixin(World par1World) {
      super(par1World);
   }

   @Overwrite
   public boolean isTargetWithinStrikingDistance(EntityLivingBase target) {
      if (!this.isAIEnabled()) {
         Minecraft.setErrorMessage("isTargetWithinStrikingDistance: doesn't handle old AI mobs yet");
         return false;
      } else if (this.getAsEntityLiving() instanceof EntityAnimal) {
         double var2 = (double)(this.width * 1.75F * this.width * 1.75F + target.width);
         if (this.getHeldItemStack() != null) {
            var2 += (double)this.getHeldItemStack().getItem().getReachBonus();
         }

         return this.getDistanceSq(target.posX, target.boundingBox.minY, target.posZ) <= var2;
      } else if ((Boolean)ExperimentalConfig.TagConfig.Realistic.ConfigValue) {
         boolean condition1 = this.getDistance(target.posX, target.boundingBox.minY - (this.boundingBox.maxY - this.boundingBox.minY) * 2.0 / 3.0, target.posZ) <= (double)this.getReach();
         boolean condition2 = this.getDistance(target.posX, target.boundingBox.maxY + (this.boundingBox.maxY - this.boundingBox.minY) * 2.0 / 3.0, target.posZ) <= (double)this.getReach();
         return condition1 || condition2;
      } else {
         return this.getDistance(target.posX, target.boundingBox.minY, target.posZ) <= (double)this.getReach();
      }
   }

   @Overwrite
   public float getReach() {
      if (!this.isAIEnabled()) {
         Minecraft.setErrorMessage("getReach: doesn't handle old AI mobs yet");
         return 0.0F;
      } else {
         return (Boolean)ExperimentalConfig.TagConfig.Realistic.ConfigValue ? 1.5F + this.getHeldItemReachBonus() : 1.5F + this.getHeldItemReachBonus() * 0.6F;
      }
   }

   @Overwrite
   public boolean tryDisableNearbyLightSource() {
      if (!this.worldObj.isRemote && this.recentlyHit == 0 && this.distanceToNearestPlayer() > 4.0) {
         int x = MathHelper.floor_double(this.posX);
         int y = MathHelper.floor_double(this.posY);
         int z = MathHelper.floor_double(this.posZ);

         for(int dx = -1; dx <= 1; ++dx) {
            for(int dy = -1; dy <= 1 + (int)this.height; ++dy) {
               for(int dz = -1; dz <= 1; ++dz) {
                  int block_id = this.worldObj.getBlockId(x + dx, y + dy, z + dz);
                  if (block_id != Block.torchWood.blockID && block_id != Block.torchRedstoneActive.blockID && block_id != Blocks.torchWoodIdle.blockID) {
                     if (block_id == Block.pumpkinLantern.blockID && this.worldObj.setBlock(x + dx, y + dy, z + dz, Block.pumpkin.blockID, this.worldObj.getBlockMetadata(x + dx, y + dy, z + dz), 3)) {
                        EntityItem entity_item = new EntityItem(this.worldObj, (double)(x + dx), (double)(y + dy), (double)(z + dz), new ItemStack(Blocks.torchWoodExtinguished));
                        entity_item.delayBeforeCanPickup = 10;
                        this.worldObj.spawnEntityInWorld(entity_item);
                        this.playSound("random.fizz", 0.5F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                        return true;
                     }
                  } else if (this.worldObj.setBlock(x + dx, y + dy, z + dz, Blocks.torchWoodExtinguished.blockID, this.worldObj.getBlockMetadata(x + dx, y + dy, z + dz), 2)) {
                     this.playSound("random.fizz", 0.5F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   @Shadow
   public double distanceToNearestPlayer() {
      return 0.0;
   }
}
