package net.oilcake.mitelros.item.potion;

import net.minecraft.EntityLiving;
import net.minecraft.EntityPlayer;
import net.minecraft.GenericAttributes;
import net.minecraft.MobEffectList;
import net.oilcake.mitelros.util.Constant;

import java.util.ArrayList;
import java.util.List;


public class PotionExtend extends MobEffectList{
    public static final MobEffectList dehydration = new PotionExtend(getNextPotionID(), true, 4251856).setIconIndex(3,2).setPotionName("potion.extend.dehydration");
    public static final MobEffectList thirsty = new PotionExtend(getNextPotionID(), true, 16761125).setIconIndex(3,2).setPotionName("potion.extend.thirsty").func_111184_a(GenericAttributes.movementSpeed, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.4, 2);
    public static final MobEffectList freeze = new PotionExtend(getNextPotionID(), true, 65535).setIconIndex(4,2).setPotionName("potion.extend.freeze");
    public static final MobEffectList stunning = new PotionExtend(getNextPotionID(), true, 9145210).setIconIndex(5,2).setPotionName("potion.extend.stunning").func_111184_a(GenericAttributes.movementSpeed, "7107DE5E-7CE8-4030-940E-514C1F160890", -1, 2);
    public PotionExtend(int par1, boolean par2, int par3) {
        super(par1, par2, par3);
    }

    @Override
    public void performEffect(EntityLiving par1EntityLivingBase, int par2) {
        if (!par1EntityLivingBase.onClient()) {
            if (this.id == dehydration.id && par1EntityLivingBase instanceof EntityPlayer) {
                if (!par1EntityLivingBase.worldObj.isRemote) {
                    ((EntityPlayer)par1EntityLivingBase).getFoodStats().addHungerServerSide(0.05F * (float)(par2 + 1));
                }
            }
        }
    }

    @Override
    public int getEffectInterval(int amplifier) {
        if(this.id == dehydration.id){
            return 1;
        }else {
            return -1;
        }
    }

    public static int getNextPotionID(){
        return Constant.nextPotionID++;
    }
}

