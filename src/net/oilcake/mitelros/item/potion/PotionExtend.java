package net.oilcake.mitelros.item.potion;

import net.minecraft.*;
import net.oilcake.mitelros.util.Constant;

import java.util.ArrayList;
import java.util.List;


public class PotionExtend extends MobEffectList{
    public static final PotionExtend dehydration = new PotionExtend(getNextPotionID(), true, 4251856).setIconIndex(3,2).setPotionName("potion.extend.dehydration");
    public static final PotionExtend thirsty = new PotionExtend(getNextPotionID(), true, 16761125).setIconIndex(3,2).setPotionName("potion.extend.thirsty");
    public static final PotionExtend freeze = new PotionExtend(getNextPotionID(), true, 65535).setIconIndex(4,2).setPotionName("potion.extend.freeze");


    public static List<MobEffectList> individualMobEffectLists = new ArrayList<>();
    private int statusIconIndex = -1;
    private String name;

    protected PotionExtend(int par1, boolean par2, int par3) {
        super(par1, par2, par3);
    }
    public String getName() {
        return this.name;
    }

//    public static void registerPoints(){
//        PotionExtend.registerPoints(dehydration);
//    }
//
//    public static void registerPoints(MobEffectList... buff) {
//        for (int i = 0, pL = potionTypes.length; i < pL; i++) {
//            if (potionTypes[i] == null) {
//                for (int h = 0,potionsLength = buff.length; h < potionsLength; h++) {
//                    potionTypes[i + h] = potionTypes[h];
//                }
//                break;
//            }
//        }
//    }
//    public void performEffect(EntityLiving par1EntityLivingBase, int par2) {
//        if (this.id == dehydration.id && par1EntityLivingBase instanceof EntityPlayer) {
//            if (!par1EntityLivingBase.worldObj.isRemote) {
//                ((EntityPlayer)par1EntityLivingBase).decreaseWaterServerSide(0.025F * (float)(par2 + 1));
//            }
//        }
//    }

    public static int getNextPotionID(){
        return Constant.nextPotionID++;
    }
    protected PotionExtend setIconIndex(int par1, int par2)
    {
        this.statusIconIndex = par1 + par2 * 8;
        return this;
    }
    public boolean hasStatusIcon()
    {
        return this.statusIconIndex >= 0;
    }
    public int getStatusIconIndex()
    {
        return this.statusIconIndex;
    }
    public PotionExtend setPotionName(String par1Str) {
        this.name = par1Str;
        return this;
    }
    public void performEffect(EntityLiving par1EntityLivingBase, int par2) {
        if (!par1EntityLivingBase.onClient()) {
            if (this.id == dehydration.id && par1EntityLivingBase instanceof EntityPlayer) {
                if (!par1EntityLivingBase.worldObj.isRemote) {
                    ((EntityPlayer)par1EntityLivingBase).addHungerServerSide(0.05F * (float)(par2 + 1));
                }
            }
        }
    }
}

