package net.oilcake.mitelros.mixins.util;

import net.minecraft.Entity;
import net.minecraft.EntityTypes;
import net.minecraft.World;
import net.oilcake.mitelros.entity.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityTypes.class)
public class EntityTypesMixin {
    @Shadow
    public static void addMapping(Class par0Class, String par1Str, int par2) {
    }

    @Shadow
    public static Entity createEntityByID(int par0, World par1World) {
        return null;
    }
    @Inject(method = "<clinit>",at = @At("RETURN"))
    private static void injectClinit(CallbackInfo callbackInfo) {
        addMapping(EntityWitherBoneLord.class, "EntityWitherBoneLord", 541);
        addMapping(EntityClusterSpider.class, "EntityClusterSpider", 542);
        addMapping(EntityWitherBodyguard.class,"EntityWitherBodyguard",543);
        addMapping(EntitySpiderKing.class,"EntitySpiderKing",544);
        addMapping(EntityStray.class,"EntityStray",545);
        addMapping(EntityHusk.class,"EntityHusk",546);
        addMapping(EntityPigmanLord.class,"EntityPigManlord",547);
        addMapping(EntityLich.class,"EntityLich",548);
        addMapping(EntityLichShadow.class,"EntityLichShadow",549);
        addMapping(EntityStalkerCreeper.class,"EntityStalkerCreeper",550);
        addMapping(EntityWandFireball.class,"EntityWandFireball",551);
        addMapping(EntityWandIceBall.class,"EntityWandIceball",552);
        addMapping(EntityWandShockWave.class,"EntityWandShockWave",553);
        addMapping(EntityZombieLord.class,"EntityZombieLord?",554);
    }

}
