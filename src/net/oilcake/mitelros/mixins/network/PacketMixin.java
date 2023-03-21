//package net.oilcake.mitelros.mixins.network;
//
//import net.minecraft.Packet;
//import net.oilcake.mitelros.util.Constant;
//import net.oilcake.mitelros.util.network.PacketDecreaseWater;
//import net.oilcake.mitelros.util.network.PacketEnchantReserverInfo;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//import static net.minecraft.Packet.packetIdToClassMap;
//
//@Mixin(Packet.class)
//public abstract class PacketMixin {
//    @Shadow
//    private static Map packetClassToIdMap = new HashMap();
//    @Shadow
//    private static Set clientPacketIdList = new HashSet();
//    @Shadow
//    private static Set serverPacketIdList = new HashSet();
//    @Shadow
//    static void addIdClassMapping(int par0, boolean par1, boolean par2, Class par3Class) {
//        if (packetIdToClassMap.containsItem(par0)) {
//            throw new IllegalArgumentException("Duplicate packet id:" + par0);
//        } else if (packetClassToIdMap.containsKey(par3Class)) {
//            throw new IllegalArgumentException("Duplicate packet class:" + par3Class);
//        } else {
//            packetIdToClassMap.addKey(par0, par3Class);
//            packetClassToIdMap.put(par3Class, par0);
//            if (par1) {
//                clientPacketIdList.add(par0);
//            }
//
//            if (par2) {
//                serverPacketIdList.add(par0);
//            }
//
//        }
//    }
//
//    static{
//        addIdClassMapping(110,true,true,PacketEnchantReserverInfo.class);
//        addIdClassMapping(111,true,true, PacketDecreaseWater.class);
//    }
//}
