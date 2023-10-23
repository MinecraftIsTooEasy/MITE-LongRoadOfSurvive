package net.oilcake.mitelros.mixins.util;

import net.minecraft.Curse;
import net.minecraft.Minecraft;
import net.oilcake.mitelros.util.CurseExtend;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Random;

@Mixin(Curse.class)
public class CurseMixin {
    //DEV
//    @Overwrite
//    public static Curse getRandomCurse(Random rand) {
//        return rand.nextInt(2) == 0 ? CurseExtend.fear_of_light : CurseExtend.fear_of_darkness;
//    }
}
