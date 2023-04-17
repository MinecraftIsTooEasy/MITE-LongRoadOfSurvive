package net.oilcake.mitelros.mixins.util;

import net.minecraft.*;
import net.oilcake.mitelros.item.ItemMorningStar;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Damage.class)
public class DamageMixin{
    @Shadow
    private float amount;
    public Entity getResponsibleEntityC() {
        return this.getResponsibleEntity();
    }
    @Shadow
    Entity getResponsibleEntity() {
        return null;
    }
}
