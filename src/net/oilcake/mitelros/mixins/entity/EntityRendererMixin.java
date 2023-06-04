package net.oilcake.mitelros.mixins.entity;

import net.minecraft.EntityPlayer;
import net.minecraft.EntityRenderer;
import net.minecraft.MathHelper;
import net.minecraft.MobEffectList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EntityRenderer.class)
public final class EntityRendererMixin {
//    private float a(EntityPlayer par1EntityPlayer, float par2) {
//        int var3 = par1EntityPlayer.getActivePotionEffect(MobEffectList.nightVision).getDuration();
//        return var3 > 1200 ? 1.0F : 0.7F + MathHelper.sin(((float)var3 - par2) * 3.1415927F * 0.2F) * 0.3F;
//    }
    public float pi = 3.1415927F;
    @Overwrite
    private float a(EntityPlayer par1EntityPlayer, float par2) {
        int var3 = par1EntityPlayer.getActivePotionEffect(MobEffectList.nightVision).getDuration();
        return var3 > 99 ? (0.8F + (MathHelper.sin(((float)var3 - par2) * pi * 0.01F) * 0.2F)) : Math.min((0.8F + (MathHelper.sin(((float)var3 - par2) * pi * 0.01F) * 0.2F)),((float)var3 * 0.01F));
    }
}
