package net.oilcake.mitelros.mixins.network;

import net.minecraft.Connection;
import net.minecraft.Packet;
import net.minecraft.Packet8UpdateHealth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import static net.xiaoyu233.fml.util.ReflectHelper.dyCast;

@Mixin(Packet8UpdateHealth.class)
public abstract class Packet8UpdateHealthMixin extends Packet {
    public int water;
    public int phytonutrients;
    public int protein;
    public float heal_progress;
    public Packet8UpdateHealthMixin() {
    }
    public int setWater(int water) {
        return this.water = water;
    }
    public void setHealProgress(float heal_progress){
        this.heal_progress = heal_progress;
    }

    @Inject(method = "readPacketData",
            at = @At("RETURN"))
    private void injectReadPacketData(DataInput par1DataInput, CallbackInfo c) throws IOException {
        this.water = par1DataInput.readInt();
        this.protein = par1DataInput.readInt();
        this.phytonutrients = par1DataInput.readInt();
        this.heal_progress = par1DataInput.readFloat();
    }

    @Inject(method = "writePacketData",
            at = @At("RETURN"))
    private void injectWritePacketData(DataOutput par1DataOutput, CallbackInfo c) throws IOException {
        par1DataOutput.writeInt(this.water);
        par1DataOutput.writeInt(this.protein);
        par1DataOutput.writeInt(this.phytonutrients);
        par1DataOutput.writeFloat(this.heal_progress);
    }
    public void setPhytonutrients(int phytonutrients) {
        this.phytonutrients = phytonutrients;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }
    @Overwrite
    public int getPacketSize() {
        return 22;
    }
}
