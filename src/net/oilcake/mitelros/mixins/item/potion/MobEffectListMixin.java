//package net.oilcake.mitelros.mixins.item.potion;
//
//import net.minecraft.*;
//import net.oilcake.mitelros.item.potion.Potions;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Overwrite;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import static net.minecraft.MobEffectList.potionTypes;
//
//@Mixin(MobEffectList.class)
//public class MobEffectListMixin {
//    @Inject(method = "<clinit>",at = @At("RETURN"))
//    private static void injectClinit(CallbackInfo callbackInfo){
//        Potions.registerPoints();
//        for (MobEffectList point : potionTypes) {
//            if (point != null && !Potions.individualMobEffectLists.contains(point)) {
//                Potions.individualMobEffectLists.add(point);
//            }
//        }
//    }
//    public MobEffectList setIconIndexC(int par1, int par2) {
//        this.statusIconIndex = par1 + par2 * 8;
//        return mobEffectList;
//    }
//    @Overwrite
//    public void performEffect(EntityLiving par1EntityLivingBase, int par2) {
//        if (!par1EntityLivingBase.onClient()) {
//            if (this.id == regeneration.id) {
//                if (par1EntityLivingBase.getHealth() < par1EntityLivingBase.getMaxHealth()) {
//                    par1EntityLivingBase.heal(1.0F);
//                }
//            } else if (this.id == poison.id) {
//                par1EntityLivingBase.attackEntityFrom(new Damage(DamageSource.poison, 1.0F));
//            } else if (this.id == wither.id) {
//                par1EntityLivingBase.attackEntityFrom(new Damage(DamageSource.wither, 1.0F));
//            } else if (this.id == hunger.id && par1EntityLivingBase instanceof EntityPlayer) {
//                if (!par1EntityLivingBase.worldObj.isRemote) {
//                    ((EntityPlayer)par1EntityLivingBase).addHungerServerSide(0.025F * (float)(par2 + 1));
//                }
//                //System.out.println("检测饥饿buff数据");
//            } else if (this.id == Potions.dehydration.id && par1EntityLivingBase instanceof EntityPlayer) {
//                if (!par1EntityLivingBase.worldObj.isRemote) {
//                    ((EntityPlayer)par1EntityLivingBase).decreaseWaterServerSide(0.025F * (par2 + 1));
//                    //System.out.println("发送buff数据");
//                }
//                //System.out.println("检测buff数据");
//            }else if (this.id == field_76443_y.id && par1EntityLivingBase instanceof EntityPlayer) {
//                if (!par1EntityLivingBase.worldObj.isRemote) {
//                    ((EntityPlayer)par1EntityLivingBase).addFoodValue(new ItemFood(par2 + 1, par2 + 1, false, false, false));
//                }
//            } else if ((this.id != heal.id || par1EntityLivingBase.isEntityUndead()) && (this.id != harm.id || !par1EntityLivingBase.isEntityUndead())) {
//                if (this.id == harm.id && !par1EntityLivingBase.isEntityUndead() || this.id == heal.id && par1EntityLivingBase.isEntityUndead()) {
//                    par1EntityLivingBase.attackEntityFrom(new Damage(DamageSource.magic, (float)(6 << par2)));
//                }
//            } else {
//                par1EntityLivingBase.heal((float)Math.max(4 << par2, 0));
//            }
//
//        }
//    }
//    @Shadow
//    @Final
//    public static MobEffectList heal;
//    @Shadow
//    @Final
//    public static MobEffectList harm;
//    @Shadow
//    @Final
//    public static MobEffectList regeneration;
//    @Shadow
//    @Final
//    public static MobEffectList hunger;
//    @Shadow
//    @Final
//    public static MobEffectList poison;
//    @Shadow
//    @Final
//    public static MobEffectList wither;
//    @Shadow
//    @Final
//    public static MobEffectList field_76443_y;
//    @Shadow
//    @Final
//    public int id;
//
//    public MobEffectList mobEffectList;
//    @Shadow
//    private int statusIconIndex;
//}
