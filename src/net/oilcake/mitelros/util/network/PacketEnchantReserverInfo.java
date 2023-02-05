package net.oilcake.mitelros.util.network;

import net.minecraft.Connection;
import net.minecraft.Packet;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Supplier;

public class PacketEnchantReserverInfo extends Packet{

        private int infoTypeIndex;
        private PacketEnchantReserverInfo.InfoType info;

        public PacketEnchantReserverInfo() {
        }

        public PacketEnchantReserverInfo(PacketEnchantReserverInfo.InfoType info) {
            this.info = info;
            this.infoTypeIndex = this.info.getType().ordinal();
        }

        public PacketEnchantReserverInfo.InfoType getInfo() {
            return this.info;
        }

        public int getPacketSize() {
            return 4 + 4 + this.info.getDataLength();
        }

        public void processPacket(Connection var1) {
            var1.processEnchantReserverInfo(this);
        }

        public void readPacketData(DataInput var1) throws IOException {
            int infoTypeIndex = var1.readInt();
            this.infoTypeIndex = infoTypeIndex;

            try {
                PacketEnchantReserverInfo.InfoType info = InfoTypes.values()[infoTypeIndex].getCtor().get();
                info.readData(var1);
                this.info = info;
            } catch (ArrayIndexOutOfBoundsException var5) {
                var5.printStackTrace();
            }

        }

        public void writePacketData(DataOutput var1) throws IOException {
            var1.writeInt(this.infoTypeIndex);
            this.info.writeData(var1);
        }

        enum InfoTypes {
            Enchant(EnchantInfo::new);

            private final Supplier<InfoType> ctor;
            InfoTypes(Supplier<InfoType> ctor) {
                this.ctor = ctor;
            }
            public Supplier<InfoType> getCtor() {
                return ctor;
            }
        }

        public interface InfoType {
            String asString();
            int getDataLength();
            int getColor();

            InfoTypes getType();

            void readData(DataInput var1) throws IOException;

            void writeData(DataOutput var1) throws IOException;
        }

        public static class EnchantInfo implements PacketEnchantReserverInfo.InfoType {
            private int EXP;
            private EnchantInfo() {
            }

            public int getDataLength(){
                return 4;
            }

            @Override
            public int getColor() {
                return 0;
            }

            private EnchantInfo(int exp) {
                this.EXP = exp;
            }

            public static PacketEnchantReserverInfo.EnchantInfo getInstance(int exp) {
                return new PacketEnchantReserverInfo.EnchantInfo(exp);
            }

            @Override
            public InfoTypes getType() {
                return InfoTypes.Enchant;
            }

            public int getEXP() {
                return this.EXP;
            }

            public String asString() {
                return "";
            }


            public void readData(DataInput dataInput) throws IOException {
                this.EXP = dataInput.readInt();
            }

            public void writeData(DataOutput dataOutput) throws IOException {
                dataOutput.writeInt(this.EXP);
            }
        }

}
