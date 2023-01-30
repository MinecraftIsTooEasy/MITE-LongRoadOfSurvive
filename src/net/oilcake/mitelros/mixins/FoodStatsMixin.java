package net.oilcake.mitelros.mixins;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FoodMetaData.class)
public class FoodStatsMixin {
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
    @Shadow
    private EntityPlayer player;

    @Overwrite
    public void onUpdate(ServerPlayer par1EntityPlayer) {
        if (!par1EntityPlayer.isGhost() && !par1EntityPlayer.isZevimrgvInTournament()) {
            if (!par1EntityPlayer.isDead && !(par1EntityPlayer.getHealth() <= 0.0F)) {
                par1EntityPlayer.decrementNutrients();
                par1EntityPlayer.decrementInsulinResistance();
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
                            --this.satiation;
                        }
                    }
                }

                if (par1EntityPlayer.inBed() && par1EntityPlayer.isOnHitList()) {
                    par1EntityPlayer.addHungerServerSide(getHungerPerTick() * 20.0F);
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
                } else {
                    this.heal_progress += (4.0E-4F + (float)this.nutrition * 2.0E-5F)
                           * (par1EntityPlayer.isMalnourishedLv1() ? 0.25F : (par1EntityPlayer.isMalnourishedLv2() ? 0.0F : (par1EntityPlayer.isMalnourishedLv3() ? 0.0F : 1.0F)))
                            * (par1EntityPlayer.inBed() ? 4.0F : 1.0F) * EnchantmentManager.getRegenerationModifier(this.player);
                    this.heal_progress += (4.0E-4F + (float)this.nutrition * 2.0E-5F) * (par1EntityPlayer.isMalnourished() ? 0.25F : 1.0F) * (par1EntityPlayer.inBed() ? 4.0F : 1.0F) * EnchantmentManager.getRegenerationModifier(this.player);
                    this.starve_progress = 0.0F;
                    if (par1EntityPlayer.worldObj.getGameRules().getGameRuleBooleanValue("naturalRegeneration") && par1EntityPlayer.shouldHeal()) {
                        if (this.heal_progress >= 1.0F) {
                            par1EntityPlayer.heal(1.0F);
                            this.addHungerServerSide(1.0F);
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
    public static float getHungerPerFoodUnit() {
        return 0.0F;
    }
    @Shadow
    public void addHungerServerSide(float hunger) {}
    @Shadow
    public static float getHungerPerTick() {
        return 0.0F;
    }

}
