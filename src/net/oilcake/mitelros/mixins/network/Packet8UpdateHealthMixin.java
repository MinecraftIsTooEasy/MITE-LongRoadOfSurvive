package net.oilcake.mitelros.mixins.network;

import net.minecraft.Connection;
import net.minecraft.Packet;
import net.minecraft.Packet8UpdateHealth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import static net.xiaoyu233.fml.util.ReflectHelper.dyCast;

@Mixin(Packet8UpdateHealth.class)
public class Packet8UpdateHealthMixin extends Packet {
    public int water;
    public int FreezingCooldown;

    public Packet8UpdateHealthMixin(float health, int satiation, int nutrition, float vision_dimming) {
        this.healthMP = health;
        this.satiation = satiation;
        this.nutrition = nutrition;
        this.vision_dimming = vision_dimming;
    }
    public int setWater(int water) {
        return this.water = water;
    }
    public int setFreezingCooldown(int FreezingCooldown) {
        return this.FreezingCooldown = FreezingCooldown;
    }

    @Inject(method = "readPacketData",
            at = @At("RETURN"))
    private void injectReadPacketData(DataInput par1DataInput, CallbackInfo c) throws IOException {
        this.water = par1DataInput.readInt();
    }

    @Inject(method = "writePacketData",
            at = @At("RETURN"))
    private void injectWritePacketData(DataOutput par1DataOutput, CallbackInfo c) throws IOException {
        par1DataOutput.writeInt(this.water);
    }


    @Shadow
    public float healthMP;
    @Shadow
    public int nutrition;
    @Shadow
    public int satiation;
    @Shadow
    public float vision_dimming;
    @Shadow
    public boolean containsSameEntityIDAs(Packet par1Packet) {
        return true;
    }
    @Shadow
    public int getPacketSize() {
        return 1;
    }
    @Shadow
    public boolean isRealPacket() {
        return true;
    }
    @Shadow
    public void processPacket(Connection par1NetHandler) {
        par1NetHandler.handleUpdateHealth(dyCast(this));
    }
    @Shadow
    public void readPacketData(DataInput dataInput) throws IOException {
    }
    @Shadow
    public void writePacketData(DataOutput dataOutput) throws IOException {
    }
}
