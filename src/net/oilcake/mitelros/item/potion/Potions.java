//package net.oilcake.mitelros.item.potion;
//
//import net.minecraft.EntityLiving;
//import net.minecraft.EntityPlayer;
//import net.minecraft.MobEffectList;
//import net.oilcake.mitelros.util.Constant;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class Potions extends MobEffectList{
//    public static final MobEffectList dehydration = new Potions(getNextPotionID(), true, 36428215).setPotionName("potion.dehydration");
//
//
//    public static List<MobEffectList> individualMobEffectLists = new ArrayList<>();
//
//    protected Potions(int par1, boolean par2, int par3) {
//        super(par1, par2, par3);
//    }
//
//    public static void registerPoints(){
//        Potions.registerPoints(dehydration);
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
//
//    public static int getNextPotionID(){
//        return Constant.nextPotionID++;
//    }
//}
//
