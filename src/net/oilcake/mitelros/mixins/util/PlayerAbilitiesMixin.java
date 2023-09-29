package net.oilcake.mitelros.mixins.util;

import net.minecraft.EnchantmentManager;
import net.minecraft.EntityPlayer;
import net.minecraft.PlayerAbilities;
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
            return (this.player.hasFoodEnergy() ? this.walkSpeed : this.walkSpeed * 0.25F) * EnchantmentManager.getSpeedModifier(this.player) * speed_boost_or_slow_down_factor * Math.min((float) Math.pow(player.getHealth() , 2) / 25.0F, 1.0F);
        }
        return (this.player.hasFoodEnergy() ? this.walkSpeed : this.walkSpeed * 0.25F) * EnchantmentManager.getSpeedModifier(this.player) * speed_boost_or_slow_down_factor;
    }
}
