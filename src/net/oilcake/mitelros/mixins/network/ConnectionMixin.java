package net.oilcake.mitelros.mixins.network;

import net.minecraft.Connection;
import net.oilcake.mitelros.util.network.PacketEnchantReserverInfo;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Connection.class)
public class ConnectionMixin {
    public void processEnchantReserverInfo(PacketEnchantReserverInfo packet) {
    }
}
