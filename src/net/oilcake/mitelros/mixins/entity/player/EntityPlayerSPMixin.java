package net.oilcake.mitelros.mixins.entity.player;

import net.oilcake.mitelros.block.enchantreserver.EnchantReserverSlots;
import net.oilcake.mitelros.block.enchantreserver.GuiEnchantReserver;
import net.minecraft.Minecraft;
import net.minecraft.bex;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(bex.class)
public class EntityPlayerSPMixin {
    @Shadow
    protected Minecraft d;
    public void displayGUIEnchantReserver(int x, int y, int z, EnchantReserverSlots slots) {
        this.d.a(new GuiEnchantReserver(ReflectHelper.dyCast(this), x, y, z, slots));
    }
}
