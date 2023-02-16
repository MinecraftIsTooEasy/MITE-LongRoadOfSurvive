package net.oilcake.mitelros.mixins.network;

import net.minecraft.Connection;
import net.minecraft.INetworkManager;
import net.minecraft.PlayerConnection;
import net.minecraft.ServerPlayer;
import net.oilcake.mitelros.util.network.PacketDecreaseWater;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PlayerConnection.class)
public class NetServerHandlerMixin extends Connection {

    public void handleDecreaseWater(PacketDecreaseWater packet)
    {
        this.playerEntity.decreaseWaterServerSide(packet.hungerWater);
    }
    @Shadow
    public ServerPlayer playerEntity;
    @Shadow
    public boolean isServerHandler() {
        return false;
    }

    @Shadow
    public INetworkManager getNetManager() {
        return null;
    }
}
