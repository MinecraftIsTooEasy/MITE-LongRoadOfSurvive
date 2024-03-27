package net.oilcake.mitelros.mixins.network;

import net.minecraft.*;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Mixin(Packet97MultiBlockChange.class)
public class Packet97MultiBlockChangeMixin extends Packet {
    @Shadow
    public int chunk_x;
    @Shadow
    public int chunk_z;
    @Shadow
    public int num_blocks;
    @Shadow
    private PacketComponentBytes bytes;

    @Inject(method = "<init>(II[SILnet/minecraft/World;)V", at = @At("RETURN"), cancellable = true)
    public void injectHead(int chunk_x, int chunk_z, short[] local_coords, int num_blocks, World world, CallbackInfo callbackInfo) {
        Chunk chunk = world.getChunkFromChunkCoords(chunk_x, chunk_z);
        byte[] bytes = new byte[num_blocks * 6];
        for (int i = 0; i < num_blocks; ++i)
        {
            int offset = i * 6;
            int x = local_coords[i] >> 12 & 15;
            int y = local_coords[i] & 255;
            int z = local_coords[i] >> 8 & 15;
            int block_id = chunk.getBlockID(x, y, z) >> 8;
            int id_extra = (chunk.getBlockID(x, y, z) - block_id * 256);
            int metadata = chunk.getBlockMetadata(x, y, z);
            bytes[offset] = (byte)x;
            bytes[offset + 1] = (byte)y;
            bytes[offset + 2] = (byte)z;
            bytes[offset + 3] = (byte)block_id;
            bytes[offset + 4] = (byte)id_extra;
            bytes[offset + 5] = (byte)metadata;
        }
        this.bytes = new PacketComponentBytes(bytes, ReflectHelper.dyCast(Packet97MultiBlockChange.class,this));
        callbackInfo.cancel();
    }

    @Shadow
    public void readPacketData(DataInput dataInput) throws IOException {

    }

    @Shadow
    public void writePacketData(DataOutput dataOutput) throws IOException {

    }

    @Shadow
    public void processPacket(Connection connection) {

    }

    @Shadow
    public int getPacketSize() {
        return 0;
    }
}

