package net.oilcake.mitelros.mixins.network;

import net.minecraft.NetHandler;
import net.minecraft.Packet;
import net.oilcake.mitelros.network.PacketDecreaseWater;
import net.oilcake.mitelros.network.PacketEnchantReserverInfo;
import net.oilcake.mitelros.network.PacketUpdateTemperature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({NetHandler.class})
public class ConnectionMixin {
   @Shadow
   public void unexpectedPacket(Packet par1Packet) {
   }

   public void processEnchantReserverInfo(PacketEnchantReserverInfo packet) {
   }

   public void handleDecreaseWater(PacketDecreaseWater packet) {
      this.unexpectedPacket(packet);
   }

   public void handleUpdateTemperature(PacketUpdateTemperature packet) {
   }
}
