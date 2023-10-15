package net.oilcake.mitelros.mixins.util;

import net.minecraft.*;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PlayerAbilities.class)
public class PlayerAbilitiesMixin {
    @Shadow
    private float flySpeed = 0.05F;
    @Shadow
    private float walkSpeed = 0.1F;
    @Shadow
    public EntityPlayer player;
    @Overwrite
    public float getFlySpeed() {
        return (player.isSprinting() || (player.getPlayerController() != null && player.getPlayerController().isRunToggledOn(player)) && player.inCreativeMode()) ? this.flySpeed * 2.5F : this.flySpeed;
    }

    @Overwrite
    public float getWalkSpeed() {
        float speed_boost_or_slow_down_factor = this.player.getSpeedBoostOrSlowDownFactor();
        if(ExperimentalConfig.TagConfig.Realistic.ConfigValue){
            float speed;
            speed = (this.player.hasFoodEnergy() ? this.walkSpeed : this.walkSpeed * 0.25F);
            speed *= EnchantmentManager.getSpeedModifier(this.player);
            speed *= speed_boost_or_slow_down_factor;
            speed *=Math.min((float) Math.pow(player.getHealth() , 2) / 25.0F, 1.0F);
            if(!this.player.isPotionActive(MobEffectList.nightVision)) {
                float light_modifier = (this.player.worldObj.getBlockLightValue(MathHelper.floor_double(this.player.posX), MathHelper.floor_double(this.player.posY + this.player.yOffset), MathHelper.floor_double(this.player.posZ)) + 3) / 15.0F;
                speed *= Math.min(light_modifier, 1.0F);
            }
            if(speed <= 0.0F){
                speed = 0.0F;
            }
            return speed;
        }
        return (this.player.hasFoodEnergy() ? this.walkSpeed : this.walkSpeed * 0.25F) * EnchantmentManager.getSpeedModifier(this.player) * speed_boost_or_slow_down_factor;
    }
}
