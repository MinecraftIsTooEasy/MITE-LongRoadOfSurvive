package net.oilcake.mitelros.mixins.entity;

import net.minecraft.EntityEnderman;
import net.minecraft.EntityMonster;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EntityEnderman.class)
public class EntityEndermanMixin extends EntityMonster {
    public EntityEndermanMixin(World par1World) {
        super(par1World);
    }
    @ModifyConstant(method = "<clinit>", constant = @Constant(intValue = 256))
    private static int ExtendedBlockID(int value) {
        return net.oilcake.mitelros.util.Constant.Extended_Block_ID;
    }
}
