package net.oilcake.mitelros.item.potion;

import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;
import net.minecraft.Potion;
import net.minecraft.SharedMonsterAttributes;
import net.oilcake.mitelros.util.Constant;

public class PotionExtend extends Potion {
   public static final Potion dehydration = (new PotionExtend(getNextPotionID(), true, 4251856)).setIconIndex(3, 2).setPotionName("potion.extend.dehydration");
   public static final Potion thirsty = (new PotionExtend(getNextPotionID(), true, 16761125)).setIconIndex(3, 2).setPotionName("potion.extend.thirsty");
   public static final Potion freeze;
   public static final Potion stunning;
   public static final Potion heat_stroke;

   public PotionExtend(int par1, boolean par2, int par3) {
      super(par1, par2, par3);
   }

   public void performEffect(EntityLivingBase par1EntityLivingBase, int par2) {
      if (!par1EntityLivingBase.onClient() && this.id == dehydration.id && par1EntityLivingBase instanceof EntityPlayer && !par1EntityLivingBase.worldObj.isRemote) {
         ((EntityPlayer)par1EntityLivingBase).getFoodStats().addHungerServerSide(0.025F * (float)(par2 + 1));
      }

   }

   public int getEffectInterval(int amplifier) {
      return this.id == dehydration.id ? 1 : -1;
   }

   public static int getNextPotionID() {
      return Constant.nextPotionID++;
   }

   static {
      freeze = (new PotionExtend(getNextPotionID(), true, 65535)).setIconIndex(6, 2).setPotionName("potion.extend.freeze").func_111184_a(SharedMonsterAttributes.movementSpeed, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.4, 2);
      stunning = (new PotionExtend(getNextPotionID(), true, 9145210)).setIconIndex(4, 2).setPotionName("potion.extend.stunning").func_111184_a(SharedMonsterAttributes.movementSpeed, "7107DE5E-7CE8-4030-940E-514C1F160890", -1.0, 2);
      heat_stroke = (new PotionExtend(getNextPotionID(), true, 11141120)).setIconIndex(5, 2).setPotionName("potion.extend.heatstroke");
   }
}
