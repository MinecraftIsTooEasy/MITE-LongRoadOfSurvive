package net.oilcake.mitelros.mixins.world;

import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityWitherBoneLord;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.List;

@Mixin(WorldGenNether.class)
public class WorldGenNetherMixin extends StructureGenerator {
    @Shadow
    public String func_143025_a() {
        return "123";
    }

//    public List getSpawnList() {
//        return this.spawnList;
//    }
    @Shadow
    private List spawnList;
    @Shadow
    protected boolean canSpawnStructureAtCoords(int par1, int par2) {
        return false;
    }
    @Shadow
    protected StructureStart getStructureStart(int par1, int par2) {
        return null;
    }
}
