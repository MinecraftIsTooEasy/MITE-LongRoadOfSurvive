package net.oilcake.mitelros.mixins.item;

import net.minecraft.Item;
import net.minecraft.ItemArrow;
import net.minecraft.Material;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.oilcake.mitelros.item.Items.arrowTungsten;

@Mixin(ItemArrow.class)
public class ItemArrowMixin extends Item{
    @Shadow
    @Final
    @Mutable
    public static Material[] material_types;
    public ItemArrow itemArrow;
    @Inject(method = "<clinit>",at = @At("RETURN"))
    private static void injectClinit(CallbackInfo callback){
        material_types = new Material[]{Material.flint, Material.obsidian, Material.copper, Material.silver, Material.rusted_iron, Material.gold, Material.iron, Material.mithril, Material.adamantium, Material.ancient_metal,
        Materials.nickel};
    }


    @Overwrite
    public float getChanceOfRecovery() {
        if (itemArrow == arrowFlint) {
            return 0.3F;
        } else if (itemArrow == arrowObsidian) {
            return 0.4F;
        } else if (itemArrow == arrowCopper) {
            return 0.6F;
        } else if (itemArrow == arrowSilver) {
            return 0.6F;
        } else if (itemArrow == arrowRustedIron) {
            return 0.5F;
        } else if (itemArrow == arrowGold) {
            return 0.5F;
        } else if (itemArrow == arrowIron) {
            return 0.7F;
        } else if (itemArrow == arrowTungsten){
            return 0.7F;
        }
        else if (itemArrow == Items.arrowNickel) {
            return 0.7F;
        } else if (itemArrow != arrowMithril && itemArrow != arrowAncientMetal) {
            return itemArrow == arrowAdamantium ? 0.9F : 0.3F;
        } else {
            return 0.8F;
        }
    }

}
