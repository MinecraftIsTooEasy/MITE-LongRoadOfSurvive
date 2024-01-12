package net.oilcake.mitelros.mixins.item.food;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.util.ExperimentalConfig;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Random;


@Mixin(ItemBowl.class)
public class ItemBowlMixin extends ItemVessel {

    @Inject(method = "<init>",at = @At("RETURN"))
    private void injectCtor(CallbackInfo callback){
        if (this.contains(Material.water)) {
            this.setWater(2);
        } else if(this.contains(Materials.dangerous_water) || this.contains(Materials.unsafe_water) || this.contains(Material.milk)){
            this.setWater(1);
        } else if(this.contains(Material.mashed_potato)){
            this.setWater(0);
        } else if(this.contains(Material.cereal)){
            this.setWater(2);
        } else if(this.contains(Material.ice_cream)){
            this.setWater(2);
        } else if(this.contains(Material.cream_of_mushroom_soup)){
            this.setWater(2);
        } else if(this.contains(Materials.beetroot)){
            this.setWater(6);
        } else if(this.contains(Materials.salad)){
            this.setWater(0);
        } else{
            this.setWater(4);
        }
    }
    @Overwrite
    public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
        if (player.onServer()) {
            if(ExperimentalConfig.TagConfig.Realistic.ConfigValue){
                if (this.contains(Materials.dangerous_water)) {
                    double rand = Math.random();
                    player.addPotionEffect((new MobEffect(MobEffectList.poison.id, (int) (450 * (1 + rand)), 0)));
                    player.addPotionEffect((new MobEffect(PotionExtend.dehydration.id, (int) (320 * (1 + rand)), 0)));
                }
                if (this.contains(Materials.unsafe_water)) {
                    double rand = Math.random();
                    if(rand > 0.5){
                        player.addPotionEffect((new MobEffect(MobEffectList.poison.id, (int) (450 * (1 + rand)), 0)));
                    }
                    player.addPotionEffect((new MobEffect(PotionExtend.dehydration.id, (int) (320 * (1 + rand)), 0)));
                }
            }else {
                if (this.contains(Materials.dangerous_water)) {
                    double rand = Math.random();
                    if(rand > 0.2){
                        player.addPotionEffect((new MobEffect(MobEffectList.poison.id, 450, 0)));
                    }
                    player.addPotionEffect((new MobEffect(PotionExtend.dehydration.id, (int) (320 * (1 + rand)), 0)));
                }
                if (this.contains(Materials.unsafe_water)) {
                    double rand = Math.random();
                    if(rand > 0.8){
                        player.addPotionEffect((new MobEffect(MobEffectList.poison.id, 450, 0)));
                    }
                    player.addPotionEffect((new MobEffect(PotionExtend.dehydration.id, (int) (320 * (1 + rand)), 0)));
                }
            }

            if (this.contains(Material.milk)) {
                player.clearActivePotions();
            }
            if (this.contains(Material.water) || this.contains(Material.milk)) {
            }else{
                if(this.contains(Material.beef_stew)){
                    player.Feast_trigger_beef_stew = true;
                }
                if(this.contains(Material.chicken_soup)){
                    player.Feast_trigger_chicken_soup = true;
                }
                if(this.contains(Material.cereal)){
                    player.Feast_trigger_cereal = true;
                }
                if(this.contains(Materials.chestnut_soup)){
                    player.Feast_trigger_chestnut_soup = true;
                }
                if(this.contains(Material.ice_cream)){
                    player.Feast_trigger_ice_cream = true;
                }
                if(this.contains(Materials.lemonade)){
                    player.Feast_trigger_lemonade = true;
                }
                if(this.contains(Material.mashed_potato)){
                    player.Feast_trigger_mashed_potatoes = true;
                }
                if(this.contains(Material.mushroom_stew)){
                    player.Feast_trigger_mushroom_soup = true;
                }
                if(this.contains(Material.cream_of_mushroom_soup)){
                    player.Feast_trigger_cream_mushroom_soup = true;
                }
                if(this.contains(Material.cream_of_vegetable_soup)){
                    player.Feast_trigger_cream_vegetable_soup = true;
                }
                if(this.contains(Material.porridge)){
                    player.Feast_trigger_porridge = true;
                }
                if(this.contains(Materials.porkchop_stew)){
                    player.Feast_trigger_porkchop_stew = true;
                }
                if(this.contains(Material.pumpkin_soup)){
                    player.Feast_trigger_pumpkin_soup = true;
                }
                if(this.contains(Material.sorbet)){
                    player.Feast_trigger_sorbet = true;
                }
                if(this.contains(Material.salad)){
                    player.Feast_trigger_salad = true;
                }
                if(this.contains(Material.vegetable_soup)){
                    player.Feast_trigger_vegetable_soup = true;
                }
                if(this.contains(Materials.fish_soup)){
                    player.Feast_trigger_salmon_soup = true;
                }
                if(this.contains(Materials.beetroot)){
                    player.Feast_trigger_beetroot_soup = true;
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
                        player.convertOneOfHeldItem(new ItemStack(this.getPeerForContents(Materials.dangerous_water)));
                    } else if(player.onServer() && (biome == BiomeBase.river || biome == BiomeBase.desertRiver)){
                        player.convertOneOfHeldItem(new ItemStack(this.getPeerForContents(Material.water)));
                    } else if(player.onServer()){
                        player.convertOneOfHeldItem(new ItemStack(this.getPeerForContents(Materials.unsafe_water)));
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

                if (this.contains(Material.water) || this.contains(Materials.unsafe_water) || this.contains(Materials.dangerous_water)) {
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
            if (contents == Materials.chestnut_soup) {
                return Items.bowlChestnutSoup;
            }
            if (contents == Materials.porkchop_stew) {
                return Items.bowlPorkchopStew;
            }
            if (contents == Materials.unsafe_water) {
                return Items.bowlWaterSuspicious;
            }
            if (contents == Materials.dangerous_water){
                return Items.bowlWaterSwampland;
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
