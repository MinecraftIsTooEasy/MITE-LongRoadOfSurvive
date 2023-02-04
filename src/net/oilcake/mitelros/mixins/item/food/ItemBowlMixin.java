package net.oilcake.mitelros.mixins.item.food;

import net.minecraft.ItemBowl;
import net.minecraft.ItemVessel;
import net.minecraft.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ItemBowl.class)
public class ItemBowlMixin extends ItemVessel {
    @Inject(method = "<init>",at = @At("RETURN"))
    private void injectCtor(CallbackInfo callback){
        if (this.contains(Material.milk)) {
            this.setWater(2);
        }else{
            this.setWater(4);
        }
    }


    public ItemBowlMixin(int id, Material vessel_material, Material contents_material, int standard_volume, int max_stack_size_empty, int max_stack_size_full, String texture) {
        super(id, vessel_material, contents_material, standard_volume, max_stack_size_empty, max_stack_size_full, texture);
    }
    @Shadow
    public ItemVessel getPeerForContents(Material material) {
        return null;
    }
    @Shadow
    public ItemVessel getPeerForVesselMaterial(Material material) {
        return null;
    }
}
