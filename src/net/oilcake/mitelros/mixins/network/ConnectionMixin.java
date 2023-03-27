package net.oilcake.mitelros.mixins.network;

import net.minecraft.Connection;
import net.minecraft.Packet;
import net.oilcake.mitelros.util.network.PacketDecreaseWater;
import net.oilcake.mitelros.util.network.PacketEnchantReserverInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Connection.class)
public class ConnectionMixin {
    @Shadow
    public void unexpectedPacket(Packet par1Packet) {
    }

    public void processEnchantReserverInfo(PacketEnchantReserverInfo packet) {
    }
    public void handleDecreaseWater(PacketDecreaseWater packet) {
        this.unexpectedPacket(packet);
    }
//    public void handleReadFreezeCooldown(PacketReadFreezeCooldown packet){
//    }
}
