package net.oilcake.mitelros.network;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import net.minecraft.NetHandler;
import net.minecraft.Packet;

public class PacketUpdateTemperature extends Packet {
   private int temperaturePoint;

   public PacketUpdateTemperature() {
   }

   public PacketUpdateTemperature(int tp) {
      this.temperaturePoint = tp;
   }

   public void readPacketData(DataInput par1DataInput) throws IOException {
      this.temperaturePoint = par1DataInput.readInt();
   }

   public void writePacketData(DataOutput par1DataOutput) throws IOException {
      par1DataOutput.writeInt(this.temperaturePoint);
   }

   public void processPacket(NetHandler net_handler) {
      net_handler.handleUpdateTemperature(this);
   }

   public int getPacketSize() {
      return 4;
   }

   public void setTemperaturePoint(int tp) {
      this.temperaturePoint = tp;
   }

   public int getTemperaturePoint() {
      return this.temperaturePoint;
   }
}
