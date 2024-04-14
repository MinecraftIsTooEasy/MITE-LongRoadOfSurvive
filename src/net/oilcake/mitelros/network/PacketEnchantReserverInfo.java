package net.oilcake.mitelros.network;

import net.minecraft.Connection;
import net.minecraft.Packet;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class PacketEnchantReserverInfo extends Packet {

    private int EXP;

    public PacketEnchantReserverInfo() {

    }

    public PacketEnchantReserverInfo(int exp) {
        this.EXP = exp;
    }

    public int getEXP() {
        return this.EXP;
    }

    @Override
    public void readPacketData(DataInput dataInput) throws IOException {
        this.EXP = dataInput.readInt();
    }

    @Override
    public void writePacketData(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(this.EXP);
    }
    @Override
    public int getPacketSize() {
        return 4;
    }

    @Override
    public void processPacket(Connection var1) {
        var1.processEnchantReserverInfo(this);
    }

}
