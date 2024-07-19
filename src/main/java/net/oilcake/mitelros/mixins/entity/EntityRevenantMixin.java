package net.oilcake.mitelros.mixins.entity;

import net.minecraft.EntityLivingData;
import net.minecraft.EntityRevenant;
import net.minecraft.EntityZombie;
import net.minecraft.EnumEntityFX;
import net.minecraft.NBTTagCompound;
import net.minecraft.SharedMonsterAttributes;
import net.minecraft.World;
import net.oilcake.mitelros.entity.EntityRetinueZombie;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({EntityRevenant.class})
public class EntityRevenantMixin extends EntityZombie {
   private boolean gathering_troops = false;
   private int spawnCounter;
   private int spawnSums;

   public EntityRevenantMixin(World world) {
      super(world);
   }

   @Overwrite
   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.setEntityAttribute(SharedMonsterAttributes.followRange, 40.0);
      this.setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.25999999046325684);
      this.setEntityAttribute(SharedMonsterAttributes.attackDamage, (Boolean)StuckTagConfig.TagConfig.TagFallenInMineLVL2.ConfigValue ? 8.75 : 7.0);
      this.setEntityAttribute(field_110186_bp, this.rand.nextDouble() * 0.10000000149011612);
      this.setEntityAttribute(SharedMonsterAttributes.maxHealth, (Boolean)StuckTagConfig.TagConfig.TagFallenInMineLVL2.ConfigValue ? 45.0 : 30.0);
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

   public void onUpdate() {
      super.onUpdate();
      if (!this.getWorld().isRemote) {
         if (this.getTarget() != null && !this.isNoDespawnRequired() && this.getTarget() != null) {
            this.gathering_troops = true;
            this.func_110163_bv();
         }

         if (this.spawnSums <= 8 && this.gathering_troops) {
            if (this.spawnCounter < 20) {
               if ((Boolean)StuckTagConfig.TagConfig.TagFallenInMineLVL2.ConfigValue) {
                  ++this.spawnCounter;
               }
            } else {
               EntityRetinueZombie Belongings = new EntityRetinueZombie(this.worldObj);
               Belongings.setPosition(this.posX, this.posY, this.posZ);
               Belongings.refreshDespawnCounter(-9600);
               this.worldObj.spawnEntityInWorld(Belongings);
               Belongings.onSpawnWithEgg((EntityLivingData)null);
               Belongings.entityFX(EnumEntityFX.summoned);
               this.spawnCounter = 0;
               ++this.spawnSums;
            }
         }
      }

   }
}
