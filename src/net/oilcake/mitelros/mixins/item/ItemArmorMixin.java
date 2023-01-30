package net.oilcake.mitelros.mixins.item;

import net.oilcake.mitelros.item.Materials;
import net.minecraft.ItemArmor;
import net.minecraft.Material;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemArmor.class)
public class ItemArmorMixin {
    @Shadow
    private Material effective_material;
    @Shadow
    @Final
    private boolean is_chain_mail;

    @Overwrite
    public int getMaterialProtection() {
        int protection;
        if (this.effective_material == Material.leather) {
            protection = 2;
        } else if (this.effective_material == Material.rusted_iron) {
            protection = 6;
        } else if (this.effective_material == Material.copper) {
            protection = 7;
        } else if (this.effective_material == Material.silver) {
            protection = 7;
        } else if (this.effective_material == Material.gold) {
            protection = 6;
        } else if (this.effective_material != Material.iron && this.effective_material != Material.ancient_metal && this.effective_material != Materials.nickel) {
            if (this.effective_material == Material.mithril || this.effective_material == Materials.tungsten) {
                protection = 9;
            } else {
                if (this.effective_material != Material.adamantium) {
                    return 0;
                }

                protection = 10;
            }
        } else {
            protection = 8;
        }

        if (this.is_chain_mail) {
            protection -= 2;
        }

        return protection;
    }
}
