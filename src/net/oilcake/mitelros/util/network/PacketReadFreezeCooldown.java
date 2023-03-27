//package net.oilcake.mitelros.util.network;
//
//import net.minecraft.Connection;
//import net.minecraft.Packet;
//
//import java.io.DataInput;
//import java.io.DataOutput;
//import java.io.IOException;
//
//public class PacketReadFreezeCooldown extends Packet {
//    private int FreezingCooldown;
//    @Override
//    public void readPacketData(DataInput dataInput) throws IOException {
//        this.FreezingCooldown = dataInput.readInt();
//    }
//
//    public int setFreezingCooldown(int FreezingCooldownT){
//        return this.FreezingCooldown = FreezingCooldownT;
//    }
//
//    @Override
//    public void writePacketData(DataOutput dataOutput) throws IOException {
//        dataOutput.writeInt(this.FreezingCooldown);
//    }
//
//    @Override
//    public void processPacket(Connection connection) {
//        connection.handleReadFreezeCooldown(this);
//    }
//
//    public int GetFreezingCooldown(){
//        return this.FreezingCooldown;
//    }
//
//    @Override
//    public int getPacketSize() {
//        return 2;
//    }
//}
