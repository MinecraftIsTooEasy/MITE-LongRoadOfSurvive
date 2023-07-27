package net.oilcake.mitelros.mixins.item;

import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(yi.class)
public class ItemBucketMilkMixin extends ItemVessel{
    public ItemBucketMilkMixin(int id, Material vessel_material, Material contents_material, int standard_volume, int max_stack_size_empty, int max_stack_size_full, String texture) {
        super(id, vessel_material, contents_material, standard_volume, max_stack_size_empty, max_stack_size_full, texture);
    }

    @Overwrite
    public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
        if (player.onServer()) {
            player.clearActivePotions();
            player.getFoodStats().addFoodValue(this);
            player.addWater(8);
        }

        super.onItemUseFinish(item_stack, world, player);
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
