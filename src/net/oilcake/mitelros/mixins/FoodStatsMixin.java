package net.oilcake.mitelros.mixins;

import net.minecraft.*;
import net.oilcake.mitelros.util.DamageSourceExtend;
import net.oilcake.mitelros.util.network.PacketDecreaseWater;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodMetaData.class)
public class FoodStatsMixin {

    private float water_for_nutrition_only;
    private int water;
    private float hungerWater;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void injectInit(CallbackInfo callbackInfo){
        this.water = getWaterLimit();
    }

//    private static float getWaterPerTick()
//    {
//        return 0.002F;
//    }
//
//    private static float getWaterPerFoodUnit()
//    {
//        return 4.0F;
//    }
    @Inject(method = "readNBT", at = @At("RETURN"))
    public void injectReadNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
        //else if (par1NBTTagCompound.hasKey("water")){
            this.water_for_nutrition_only = par1NBTTagCompound.getFloat("water_for_nutrition_only");
            this.hungerWater = par1NBTTagCompound.getFloat("hungerWater");
            this.water = par1NBTTagCompound.getInteger("water");
      //  }
    }
    @Inject(method = "writeNBT", at = @At("RETURN"))
    public void injectWriteNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo callbackInfo) {
        par1NBTTagCompound.setInteger("water", this.water);
        par1NBTTagCompound.setFloat("hungerwater", this.hungerWater);
        par1NBTTagCompound.setFloat("water_for_nutrition_only", this.water_for_nutrition_only);
    }

    public int getWater() {
        return water;
    }

    @Overwrite
    public void addFoodValue(Item item) {
        this.addSatiation(item.getSatiation(this.player));
        this.addNutrition(item.getNutrition());
        this.addWater(item.getWater());
        if (this.player instanceof ServerPlayer) {
            this.player.getAsEntityPlayerMP().addInsulinResistance(item.getInsulinResponse());
            this.player.getAsEntityPlayerMP().addNutrients(item);
        }
    }


    public void setSatiationWater(int water, boolean check_limit) {
        if (check_limit) {
            this.water = Math.min(water, this.getWaterValueLimit());
        } else {
            this.water = water;
        }
    }
    public int addWater(int water)
    {
        this.setSatiationWater(this.water + water, true);
        return this.water;
    }
//    public int removeWater(int water)
//    {
//        this.setSatiationWater(this.water - water, true);
//        return this.water;
//    }


    private float global_water_rate = 1.0F;
    private static float getWaterPerTick() {
        return 0.002F;
    }

    private static float getWaterPerFoodUnit() {
        return 4.0F;
    }

    private void decreaseWater(float water)
    {
    //System.out.println("OM");

        if (!this.player.capabilities.isCreativeMode && !this.player.capabilities.disableDamage && !this.player.isGhost() && !this.player.isZevimrgvInTournament())
        {
            water *= this.global_water_rate;
            this.hungerWater = Math.min(this.hungerWater + water, 40);


//             if(this.hungerWater > 20){
//                 System.out.println("hungerWater" + hungerWater);
//             }

            if (this.player.worldObj.isRemote && this.hungerWater > 0.2F) {
                Minecraft.w().h.netClientHandler.c(new PacketDecreaseWater(this.hungerWater));
                //System.out.println("O2M");
                this.hungerWater = 0;
            }
        }
    }

//    @Overwrite
//    private void addHunger(float hunger) {
//        if (!this.player.capabilities.isCreativeMode && !this.player.capabilities.disableDamage && !this.player.isGhost() && !this.player.isZevimrgvInTournament()) {
//            hunger *= this.global_hunger_rate;
//            this.hunger = Math.min(this.hunger + hunger, 40.0F);
//
//            if(this.hunger > 20){
//                System.out.println("hunger: " + this.hunger);
//            }
//
//            if (this.player.worldObj.isRemote && this.hunger > 0.2F) {
//                Minecraft.w().h.netClientHandler.c(new Packet82AddHunger(this.hunger));
//                //Minecraft.w().h.netClientHandler.c(new PacketDecreaseWater(this.water));
//                //System.out.println("OMHunger");
//                this.hunger = 0.0F;
//            }
//
//        }
//    }
    public void decreaseWaterClientSide(float water)
    {
        if (!this.player.worldObj.isRemote)
        {
            Minecraft.setErrorMessage("addHungerClientSide: cannot decrease Water to client if not remote");
        }
        else
        {
            this.decreaseWater(water);
        }
    }

    public void decreaseWaterServerSide(float hungerWater)
    {
        //System.out.println("packet OK");
        if (this.player.worldObj.isRemote)
        {
            Minecraft.setErrorMessage("addHungerServerSide: cannot decrease Water to server if remote");
        }
        else
        {
            this.decreaseWater(hungerWater);
            //System.out.println("发送buff数据 food");
        }
    }

    public int getWaterValueLimit()
    {
        return this.getWaterLimit();
    }
    public int getWaterLimit()
    {
        return Math.max(Math.min(6 + this.player.getExperienceLevel() / 5 * 2, 20), 6);
    }

    @Overwrite
    public void onUpdate(ServerPlayer par1EntityPlayer) {
        if (!par1EntityPlayer.isGhost() && !par1EntityPlayer.isZevimrgvInTournament()) {
            if (!par1EntityPlayer.isDead && !(par1EntityPlayer.getHealth() <= 0.0F)) {
                par1EntityPlayer.decrementNutrients();
                par1EntityPlayer.decrementInsulinResistance();

                this.decreaseWaterServerSide(getWaterPerTick());
                if (!par1EntityPlayer.inCreativeMode()) {
                    this.water_for_nutrition_only += getWaterPerTick() * 0.3F;
                }

                float hunger_factor = par1EntityPlayer.getWetnessAndMalnourishmentHungerMultiplier();
                this.addHungerServerSide(getHungerPerTick() * hunger_factor);
                if (!par1EntityPlayer.inCreativeMode()) {
                    this.hunger_for_nutrition_only += getHungerPerTick() * 0.25F;
                }

                if (this.hunger >= getHungerPerFoodUnit()) {
                    this.hunger -= getHungerPerFoodUnit();
                    if (this.satiation > 0 || this.nutrition > 0) {
                        if (this.satiation < 1 || this.hunger_for_nutrition_only + 0.001F >= getHungerPerFoodUnit() && this.nutrition > 0) {
                            --this.nutrition;
                            this.hunger_for_nutrition_only = 0.0F;
                        } else {
                            this.satiation -= 1;
                        }
                    }
                }
                if(this.satiation < 0){
                    this.satiation = 0;
                }

//                if(this.hungerWater >= getWaterPerFoodUnit()) {
//                    this.hungerWater -= getWaterPerFoodUnit();
//                    //System.out.println("hungerWater: " + hungerWater);
//                    if (this.water > 0) {
//                        if (this.satiation < 1 || this.water_for_nutrition_only + 0.001F >= getWaterPerFoodUnit()) {
//                            --this.water;
//                            this.water_for_nutrition_only = 0.0F;
//                        }
//                    }
//                }
                if(this.water < 0){
                    this.water = 0;
                }

                if (par1EntityPlayer.inBed() && par1EntityPlayer.isOnHitList()) {
                    par1EntityPlayer.addHungerServerSide(getHungerPerTick() * 20.0F);
                    par1EntityPlayer.decreaseWaterServerSide(getWaterPerTick() * 20.0F);
                }

                if (this.player.isStarving()) {
                    this.heal_progress = 0.0F;
                    this.starve_progress += 0.002F;
                    if (this.starve_progress >= 1.0F) {
                        if (par1EntityPlayer.getHealth() > 10.0F || this.player.worldObj.difficultySetting >= 3 || par1EntityPlayer.getHealth() > 1.0F && this.player.worldObj.difficultySetting >= 2) {
                            par1EntityPlayer.attackEntityFrom(new Damage(DamageSource.starve, 1.0F));
                        }

                        --this.starve_progress;
                        this.hunger_for_nutrition_only = 0.0F;
                    }
                } else if (this.player.DuringDehydration()) {
                    this.heal_progress = 0.0F;
                    this.dehydration_progress += 0.002F;
                    if (this.dehydration_progress >= 1.0F) {
                        par1EntityPlayer.attackEntityFrom(new Damage(DamageSourceExtend.thirsty, 1.0F));
                        --this.dehydration_progress;
                        this.water_for_nutrition_only = 0.0F;
                    }
                } else if (par1EntityPlayer.isMalnourishedFin()) {
                    this.heal_progress = 0.0F;
                    this.malnourished_progress += 0.002F;
                    if (this.malnourished_progress >= 1.0F) {
                        par1EntityPlayer.attackEntityFrom(new Damage(DamageSourceExtend.malnourished, 1.0F));
                        --this.malnourished_progress;
                    }
                } else {
                    this.heal_progress += (4.0E-4F + (float)this.nutrition * 2.0E-5F)
                           * (par1EntityPlayer.isMalnourishedLv1() ? 0.25F : (par1EntityPlayer.isMalnourishedLv2() ? 0.0F : (par1EntityPlayer.isMalnourishedLv3() ? 0.0F : 1.0F)))
                            * (par1EntityPlayer.inBed() ? 8.0F : 1.0F) * EnchantmentManager.getRegenerationModifier(this.player);
                    this.starve_progress = 0.0F;
                    if (par1EntityPlayer.worldObj.getGameRules().getGameRuleBooleanValue("naturalRegeneration") && par1EntityPlayer.shouldHeal()) {
                        if (this.heal_progress >= 1.0F) {
                            par1EntityPlayer.heal(1.0F);
                            this.addHungerServerSide(1.0F);
                            this.decreaseWaterServerSide(1.0F);
                            --this.heal_progress;
                        }
                    } else {
                        this.heal_progress = 0.0F;
                    }
                }

            }
        }
    }
    @Shadow
    private float global_hunger_rate = 1.0F;
    @Shadow
    private int satiation;
    @Shadow
    private int nutrition;
    @Shadow
    private float hunger;
    @Shadow
    private float hunger_for_nutrition_only;
    @Shadow
    private float heal_progress;
    @Shadow
    private float starve_progress;
    private float dehydration_progress;
    private float malnourished_progress;
    @Shadow
    public int addNutrition(int nutrition) {
        return this.nutrition;
    }
    @Shadow
    public int addSatiation(int satiation) {
        return this.satiation;
    }
    @Shadow
    public static float getHungerPerFoodUnit() {
        return 0.0F;
    }
    @Shadow
    public void addHungerServerSide(float hunger) {}
    @Shadow
    public static float getHungerPerTick() {
        return 0.0F;
    }
    @Shadow
    public int getNutritionLimit() {
        return 1;
    }
    @Shadow
    private EntityPlayer player;
}
