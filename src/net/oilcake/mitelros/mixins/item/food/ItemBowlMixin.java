package net.oilcake.mitelros.mixins.item.food;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;


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
            if (this.contains(Materials.dangerous_water)) {
                player.getFoodStats().addWater(2);
                player.addPotionEffect(new MobEffect(MobEffectList.poison.id, 450, 0));
            }
            if (this.contains(Materials.unsafe_water)) {
                player.getFoodStats().addWater(2);
                player.addPotionEffect(new MobEffect(MobEffectList.hunger.id, 1200, 0));
            }
            if (this.contains(Material.milk)) {
                player.clearActivePotions();
            }
            if (this.contains(Material.water) || this.contains(Material.milk)) {
                player.getFoodStats().addWater(2);
            }else{
                if(!this.contains(Material.salad)){
                    player.getFoodStats().addWater(4);
                }
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

    @Overwrite
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        RaycastCollision rc = player.getSelectedObject(partial_tick, true);
        BiomeBase biome = player.worldObj.getBiomeGenForCoords(player.getBlockPosX(), player.getBlockPosZ());
        if (rc != null && rc.isBlock()) {
            if (this.isEmpty()) {
                if (rc.getBlockHitMaterial() == Material.water || rc.getNeighborOfBlockHitMaterial() == Material.water) {
                    if (player.onServer() && (biome == BiomeBase.swampRiver || biome == BiomeBase.swampland)) {
                        player.convertOneOfHeldItem(new ItemStack(Items.waterswampland));
                    } else if(player.onServer() && (biome == BiomeBase.river || biome == BiomeBase.desertRiver)){
                        player.convertOneOfHeldItem(new ItemStack(this.getPeerForContents(Material.water)));
                    } else if(player.onServer()){
                        player.convertOneOfHeldItem(new ItemStack(Items.watersuspicious));
                    }
                    return true;
                }
            } else {
                if (rc.getNeighborOfBlockHit() == Block.fire && this.canContentsDouseFire()) {
                    if (player.onServer()) {
                        rc.world.douseFire(rc.neighbor_block_x, rc.neighbor_block_y, rc.neighbor_block_z, (Entity)null);
                        player.convertOneOfHeldItem(new ItemStack(this.getEmptyVessel()));
                    }

                    return true;
                }

                if (this.contains(Material.water)) {
                    Block block = rc.getBlockHit();
                    int x = rc.block_hit_x;
                    int y = rc.block_hit_y;
                    int z = rc.block_hit_z;
                    EnumFace face_hit = rc.face_hit;
                    if (block instanceof BlockCrops || block instanceof BlockStem || block == Block.mushroomBrown) {
                        --y;
                        block = rc.world.getBlock(x, y, z);
                        face_hit = EnumFace.TOP;
                    }

                    if (block == Block.tilledField && face_hit == EnumFace.TOP && BlockSoil.fertilize(rc.world, x, y, z, player.getHeldItemStack(), player)) {
                        if (player.onServer() && !player.inCreativeMode()) {
                            player.convertOneOfHeldItem(new ItemStack(this.getEmptyVessel()));
                        }

                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Overwrite
    public static ItemVessel getPeer(Material vessel_material, Material contents) {
        if (vessel_material == Material.wood) {
            if (contents == null) {
                return bowlEmpty;
            }

            if (contents == Material.mushroom_stew) {
                return bowlMushroomStew;
            }

            if (contents == Material.milk) {
                return bowlMilk;
            }

            if (contents == Material.water) {
                return bowlWater;
            }

            if (contents == Material.beef_stew) {
                return bowlBeefStew;
            }

            if (contents == Material.chicken_soup) {
                return bowlChickenSoup;
            }

            if (contents == Material.vegetable_soup) {
                return bowlVegetableSoup;
            }

            if (contents == Material.ice_cream) {
                return bowlIceCream;
            }

            if (contents == Material.salad) {
                return bowlSalad;
            }

            if (contents == Material.cream_of_mushroom_soup) {
                return bowlCreamOfMushroomSoup;
            }

            if (contents == Material.cream_of_vegetable_soup) {
                return bowlCreamOfVegetableSoup;
            }

            if (contents == Material.mashed_potato) {
                return bowlMashedPotato;
            }

            if (contents == Material.porridge) {
                return bowlPorridge;
            }

            if (contents == Material.cereal) {
                return bowlCereal;
            }
        }

        return null;
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
