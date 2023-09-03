package net.oilcake.mitelros.item;

import net.minecraft.*;

import static net.oilcake.mitelros.item.Items.*;

public class ItemBowlClay extends ItemVessel {

    public ItemBowlClay(int id, Material contents, String texture) {
        super(id, Material.hardened_clay, contents, 1, 4, 4, "hardened_clay_bowls/" + texture);
        this.setCraftingDifficultyAsComponent(25.0F);
        this.setCreativeTab(CreativeModeTab.tabMisc);
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 32;
    }

    public EnumItemInUseAction getItemInUseAction(ItemStack item_stack, EntityPlayer player) {
        return !this.isIngestable(item_stack) ? null : super.getItemInUseAction(item_stack, player);
    }

    public int getSimilarityToItem(Item item) {
        if (item instanceof ItemBowlClay) {
            ItemBowlClay item_bowl = (ItemBowlClay) item;
            if (item_bowl.isEmpty() || this.isEmpty()) {
                return 2;
            }
        }

        return super.getSimilarityToItem(item);
    }

    public ItemBowlClay setAnimalProduct() {
        super.setAnimalProduct();
        return this;
    }

    public ItemBowlClay setPlantProduct() {
        super.setPlantProduct();
        return this;
    }

    public int getBurnTime(ItemStack item_stack) {
        return 0;
    }

    public ItemVessel getPeerForContents(Material contents) {
        return getPeer(this.getVesselMaterial(), contents);
    }

    public ItemVessel getPeerForVesselMaterial(Material vessel_material) {
        return getPeer(vessel_material, this.getContents());
    }

    public boolean hasIngestionPriority(ItemStack item_stack, boolean ctrl_is_down) {
        return !this.contains(Material.water);
    }
    public static boolean isSoupOrStew(Item item) {
        if (!(item instanceof ItemBowlClay)) {
            return false;
        } else {
            Material contents = ((ItemBowlClay)item).getContents();
            return contents instanceof MaterialSoup || contents instanceof MaterialStew;
        }
    }

    public float getCompostingValue() {
        return this == claybowlMilk ? 0.0F : super.getCompostingValue();
    }

    public Item getCompostingRemains(ItemStack item_stack) {
        return this.getEmptyVessel();
    }

    public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
        if (player.onServer()) {
            if (this.contains(Materials.dangerous_water)) {
                player.getFoodStats().addWater(1);
                player.addPotionEffect(new MobEffect(MobEffectList.poison.id, 450, 0));
            }
            if (this.contains(Materials.unsafe_water)) {
                player.getFoodStats().addWater(1);
                player.addPotionEffect(new MobEffect(MobEffectList.hunger.id, 1200, 0));
            }
            if (this.contains(Material.milk)) {
                player.clearActivePotions();
            }
            if (this.contains(Material.water) || this.contains(Material.milk)) {
                player.getFoodStats().addWater(2);
            }else{
                if(this.contains(Material.beef_stew)){
                    player.Feast_trigger_beef_stew = true;
                    player.getFoodStats().addWater(4);
                }
                if(this.contains(Material.chicken_soup)){
                    player.Feast_trigger_chicken_soup = true;
                    player.getFoodStats().addWater(4);
                }
                if(this.contains(Material.cereal)){
                    player.Feast_trigger_cereal = true;
                    player.getFoodStats().addWater(2);
                }
                if(this.contains(Materials.chestnut_soup)){
                    player.Feast_trigger_chestnut_soup = true;
                    player.getFoodStats().addWater(4);
                }
                if(this.contains(Material.ice_cream)){
                    player.Feast_trigger_ice_cream = true;
                    player.getFoodStats().addWater(2);
                }
                if(this.contains(Materials.lemonade)){
                    player.Feast_trigger_lemonade = true;
                    player.getFoodStats().addWater(2);
                }
                if(this.contains(Material.mashed_potato)){
                    player.Feast_trigger_mashed_potatoes = true;
                }
                if(this.contains(Material.mushroom_stew)){
                    player.Feast_trigger_mushroom_soup = true;
                    player.getFoodStats().addWater(4);
                }
                if(this.contains(Material.cream_of_mushroom_soup)){
                    player.Feast_trigger_cream_mushroom_soup = true;
                    player.getFoodStats().addWater(2);
                }
                if(this.contains(Material.cream_of_vegetable_soup)){
                    player.Feast_trigger_cream_vegetable_soup = true;
                    player.getFoodStats().addWater(2);
                }
                if(this.contains(Material.porridge)){
                    player.Feast_trigger_porridge = true;
                    player.getFoodStats().addWater(4);
                }
                if(this.contains(Materials.porkchop_stew)){
                    player.Feast_trigger_porkchop_stew = true;
                    player.getFoodStats().addWater(4);
                }
                if(this.contains(Material.pumpkin_soup)){
                    player.Feast_trigger_pumpkin_soup = true;
                    player.getFoodStats().addWater(4);
                }
                if(this.contains(Material.sorbet)){
                    player.Feast_trigger_sorbet = true;
                    player.getFoodStats().addWater(4);
                }
                if(this.contains(Material.salad)){
                    player.Feast_trigger_salad = true;
                }
                if(this.contains(Material.vegetable_soup)){
                    player.Feast_trigger_vegetable_soup = true;
                    player.getFoodStats().addWater(4);
                }
                if(this.contains(Materials.fish_soup)){
                    player.Feast_trigger_salmon_soup = true;
                    player.getFoodStats().addWater(4);
                }
                if(this.contains(Materials.beetroot)){
                    player.Feast_trigger_beetroot_soup = true;
                    player.getFoodStats().addWater(6);
                }
            }

            player.addFoodValue(this);
            if (this.isEatable(item_stack)) {
                world.playSoundAtEntity(player, "random.burp", 0.5F, player.getRand().nextFloat() * 0.1F + 0.9F);
            }
        }

        super.onItemUseFinish(item_stack, world, player);
    }

    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        RaycastCollision rc = player.getSelectedObject(partial_tick, true);
        BiomeBase biome = player.worldObj.getBiomeGenForCoords(player.getBlockPosX(), player.getBlockPosZ());
        if (rc != null && rc.isBlock()) {
            if (this.isEmpty()) {
                if (rc.getBlockHitMaterial() == Material.water || rc.getNeighborOfBlockHitMaterial() == Material.water) {
                    if (player.onServer() && (biome == BiomeBase.swampRiver || biome == BiomeBase.swampland)) {
                        player.convertOneOfHeldItem(new ItemStack(this.getPeerForContents(Materials.dangerous_water)));
                    } else if (player.onServer() && (biome == BiomeBase.river || biome == BiomeBase.desertRiver)) {
                        player.convertOneOfHeldItem(new ItemStack(this.getPeerForContents(Material.water)));
                    } else if (player.onServer()) {
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

    public static ItemVessel getPeer(Material vessel_material, Material contents) {
        if (vessel_material == Material.hardened_clay) {
            if (contents == null) {
                return claybowlEmpty;
            }

            if (contents == Material.mushroom_stew) {
                return claybowlMushroomStew;
            }

            if (contents == Material.milk) {
                return claybowlMilk;
            }

            if (contents == Material.water) {
                return claybowlWater;
            }

            if (contents == Material.beef_stew) {
                return claybowlBeefStew;
            }

            if (contents == Material.chicken_soup) {
                return claybowlChickenSoup;
            }

            if (contents == Material.vegetable_soup) {
                return claybowlVegetableSoup;
            }

            if (contents == Material.ice_cream) {
                return claybowlIceCream;
            }

            if (contents == Material.salad) {
                return claybowlSalad;
            }

            if (contents == Material.cream_of_mushroom_soup) {
                return claybowlCreamOfMushroomSoup;
            }

            if (contents == Material.cream_of_vegetable_soup) {
                return claybowlCreamOfVegetableSoup;
            }

            if (contents == Material.mashed_potato) {
                return claybowlMashedPotato;
            }

            if (contents == Material.porridge) {
                return claybowlPorridge;
            }

            if (contents == Material.cereal) {
                return claybowlCereal;
            }

            if (contents == Materials.chestnut_soup) {
                return claybowlChestnutSoup;
            }

            if (contents == Materials.porkchop_stew) {
                return claybowlPorkchopStew;
            }

            if (contents == Materials.unsafe_water) {
                return claybowlWaterSuspicious;
            }

            if (contents == Materials.dangerous_water) {
                return claybowlWaterSwampland;
            }
        }

        return null;
    }
}
