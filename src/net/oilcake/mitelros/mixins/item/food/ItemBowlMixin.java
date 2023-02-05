package net.oilcake.mitelros.mixins.item.food;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;


@Mixin(ItemBowl.class)
public class ItemBowlMixin extends ItemVessel {

//    @Inject(method = "<init>",at = @At("RETURN"))
//    private void injectCtor(CallbackInfo callback){
//        if (this.contains(Material.milk)) {
//            this.setWater(2);
//        }else{
//            this.setWater(4);
//        }
//    }
    @Overwrite
    public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
        if (player.onServer()) {
            if (this.contains(Material.milk)) {
                player.clearActivePotions();
            }
            if (this.contains(Material.water) || this.contains(Material.milk)) {
                player.getFoodStats().addWater(2);
            }else{
                player.getFoodStats().addWater(4);
            }

            player.addFoodValue(this);
            if (this.isEatable(item_stack)) {
                world.playSoundAtEntity(player, "random.burp", 0.5F, player.getRand().nextFloat() * 0.1F + 0.9F);
            }
        }

        super.onItemUseFinish(item_stack, world, player);
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
