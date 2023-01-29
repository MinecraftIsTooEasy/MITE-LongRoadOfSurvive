package net.oilcake.mitelros.mixins.network;

import net.minecraft.Minecraft;
import net.minecraft.NetClientHandler;
import net.minecraft.awe;
import net.minecraft.bdd;
import net.oilcake.mitelros.block.enchantreserver.GuiEnchantReserver;
import net.oilcake.mitelros.util.network.PacketEnchantReserverInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.SoftOverride;

@Mixin(NetClientHandler.class)
public class NetClientHandlerMixin extends ConnectionMixin{
    @Shadow
    private Minecraft h;

    @SoftOverride
    public void processEnchantReserverInfo(PacketEnchantReserverInfo packet) {
        awe openingGUI = this.h.n;
        if (openingGUI instanceof GuiEnchantReserver) {
            if (packet.getInfo() instanceof PacketEnchantReserverInfo.EnchantInfo) {
                PacketEnchantReserverInfo.EnchantInfo info = (PacketEnchantReserverInfo.EnchantInfo)packet.getInfo();
                ((GuiEnchantReserver)openingGUI).setEnchantInfo(info.getEXP());
            } else {
                ((GuiEnchantReserver)openingGUI).setInfo(packet.getInfo().getColor());
            }
        }

    }
}
