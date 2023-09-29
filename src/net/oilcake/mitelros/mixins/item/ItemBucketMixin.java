package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemBucket.class)
public class ItemBucketMixin extends ItemVessel {
    public ItemBucketMixin(int id, Material vessel_material, Material contents_material, int standard_volume, int max_stack_size_empty, int max_stack_size_full, String texture) {
        super(id, vessel_material, contents_material, standard_volume, max_stack_size_empty, max_stack_size_full, texture);
    }
    @Overwrite
    public float getChanceOfMeltingWhenFilledWithLava() {
        Material material = this.getVesselMaterial();
        return (material == Material.adamantium || material == Materials.tungsten) ? 0.0F : (material == Material.gold ? 0.5F : 0.025F * (Material.mithril.getDurability() / material.getDurability()));
    }
    @Overwrite
    public Block getBlockForContents() {
        if (this.contains(Material.water)||this.contains(Materials.dangerous_water)||this.contains(Materials.unsafe_water)) {
            return Block.waterMoving;
        } else if (this.contains(Material.lava)) {
            return Block.lavaMoving;
        } else {
            Minecraft.setErrorMessage("getBlockForContents: no handler for contents " + this.getContents());
            return null;
        }
    }
    @Overwrite
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        RaycastCollision rc = player.getSelectedObject(partial_tick, true);
        BiomeBase biome = player.worldObj.getBiomeGenForCoords(player.getBlockPosX(), player.getBlockPosZ());
        if (rc != null && rc.isBlock()) {
            int x;
            int y;
            int z;
            if (this.isEmpty()) {
                Material material;
                if (rc.getBlockHitMaterial().isLiquid()) {
                    x = rc.block_hit_x;
                    y = rc.block_hit_y;
                    z = rc.block_hit_z;
                    material = rc.getBlockHitMaterial();
                } else {
                    x = rc.neighbor_block_x;
                    y = rc.neighbor_block_y;
                    z = rc.neighbor_block_z;
                    material = rc.getNeighborOfBlockHitMaterial();
                }

                if (material != null && material.isLiquid()) {
                    if (player.inCreativeMode() && !player.canMineAndEditBlock(x, y, z)) {
                        return false;
                    } else {
                        if (player.onServer()) {
                            if (player.inCreativeMode() || ctrl_is_down) {
                                rc.world.setBlockToAir(x, y, z);
                            }

                            if (!player.inCreativeMode()) {
                                if (material == Material.lava && rc.world.rand.nextFloat() < this.getChanceOfMeltingWhenFilledWithLava()) {
                                    player.addStat(StatisticList.objectBreakStats[this.itemID], 1);
                                    ItemStack held_item_stack = player.getHeldItemStack();
                                    ItemStack item_stack = this.getItemProducedWhenDestroyed(held_item_stack, DamageSource.lava);
                                    if (item_stack == null) {
                                        rc.world.blockFX(EnumBlockFX.item_consumed_by_lava, x, y, z);
                                    }

                                    player.convertOneOfHeldItem(item_stack);
                                    if (!player.hasHeldItem()) {
                                        player.getAsEntityPlayerMP().prevent_item_pickup_due_to_held_item_breaking_until = System.currentTimeMillis() + 1500L;
                                    }
                                } else {
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
                                    player.convertOneOfHeldItem(new ItemStack(this.getPeerForContents(material)));
                                }
                            }
                        }

                        return true;
                    }
                } else {
                    return false;
                }
            } else if (this.contains(Material.stone)) {
                return false;
            } else {
                ItemStack item_stack = player.getHeldItemStack();
                if (this.contains(Material.water)||this.contains(Materials.dangerous_water)||this.contains(Materials.unsafe_water)) {
                    Block block = rc.getBlockHit();
                    x = rc.block_hit_x;
                    y = rc.block_hit_y;
                    z = rc.block_hit_z;
                    EnumFace face_hit = rc.face_hit;
                    if (rc.world.getBlock(x, y - 1, z) == Block.tilledField) {
                        --y;
                        block = rc.world.getBlock(x, y, z);
                        face_hit = EnumFace.TOP;
                    }

                    if (block == Block.tilledField && face_hit == EnumFace.TOP) {
                        if (BlockSoil.fertilize(rc.world, x, y, z, player.getHeldItemStack(), player)) {
                            if (player.onServer() && !player.inCreativeMode()) {
                                player.convertOneOfHeldItem(new ItemStack(this.getEmptyVessel()));
                            }

                            return true;
                        }

                        return false;
                    }
                }
                if (player.inCreativeMode() || rc.getBlockHitMaterial() != this.classifyMaterialForm(this.getContents()) && rc.getNeighborOfBlockHitMaterial() != this.classifyMaterialForm(this.getContents())) {
                    if (!rc.getBlockHit().isLiquid() && !rc.isBlockHitReplaceableBy(this.getBlockForContents(), 0)) {
                        x = rc.neighbor_block_x;
                        y = rc.neighbor_block_y;
                        z = rc.neighbor_block_z;
                    } else {
                        x = rc.block_hit_x;
                        y = rc.block_hit_y;
                        z = rc.block_hit_z;
                    }

                    if (!player.canPlayerEdit(x, y, z, item_stack)) {
                        return false;
                    } else if (this.tryPlaceContainedLiquid(rc.world, player, x, y, z, shouldContainedLiquidBePlacedAsSourceBlock(player, ctrl_is_down))) {
                        if (player.onServer() && !player.inCreativeMode()) {
                            player.convertOneOfHeldItem(new ItemStack(this.getEmptyVessel()));
                        }

                        return true;
                    } else {
                        return false;
                    }
                } else {
                    if (player.onServer()) {
                        player.convertOneOfHeldItem(new ItemStack(this.getEmptyVessel()));
                    }

                    return true;
                }
            }
        } else {
            return false;
        }
    }
    @Overwrite
    public boolean tryPlaceContainedLiquid(World world, EntityPlayer player, int x, int y, int z, boolean allow_placement_of_source_block) {
        if (this.isEmpty()) {
            Minecraft.setErrorMessage("tryPlaceContainedLiquid: bucket is empty");
            return false;
        } else {
            Material material_in_bucket = this.getContents();
            if (material_in_bucket == null) {
                Minecraft.setErrorMessage("tryPlaceContainedLiquid: material in bucket is null");
                return false;
            } else {
                Material target_block_material = world.getBlockMaterial(x, y, z);
                if (target_block_material.isSolid()) {
                    return false;
                } else {
                    boolean placement_prevented = false;
                    if (material_in_bucket.canDouseFire() && world.getBlock(x, y, z) == Block.fire) {
                        if (!world.isRemote) {
                            world.douseFire(x, y, z, (Entity)null);
                        }

                        placement_prevented = true;
                    } else if ((this.contains(Material.water)||this.contains(Materials.dangerous_water)||this.contains(Materials.unsafe_water))&& world.provider.isHellWorld) {
                        if (!world.isRemote) {
                            world.blockFX(EnumBlockFX.steam, x, y, z);
                        }

                        placement_prevented = true;
                    }

                    if (!placement_prevented) {
                        if (player != null && !player.inCreativeMode() && material_in_bucket == target_block_material) {
                            return true;
                        }

                        if (!world.isRemote) {
                            WorldServer world_server = (WorldServer)world;
                            if (!target_block_material.isSolid() && !target_block_material.isLiquid() && !world.isAirBlock(x, y, z)) {
                                world.destroyBlock((new BlockBreakInfo(world, x, y, z)).setFlooded((BlockFluids)this.getBlockForContents()), true);
                            }

                            if ((material_in_bucket == Material.water||material_in_bucket == Materials.unsafe_water||material_in_bucket==Materials.dangerous_water)&& world.getBlockMaterial(x, y, z) == Material.lava) {
                                world.tryConvertLavaToCobblestoneOrObsidian(x, y, z);
                            } else {
                                if ((material_in_bucket == Material.water||material_in_bucket == Materials.unsafe_water||material_in_bucket==Materials.dangerous_water)&& world.getBlock(x, y - 1, z) == Block.mantleOrCore) {
                                    world.blockFX(EnumBlockFX.steam, x, y, z);
                                    return true;
                                }

                                if (material_in_bucket == Material.lava && world.getBlockMaterial(x, y, z) == Material.water) {
                                    world.tryConvertWaterToCobblestone(x, y, z);
                                } else {
                                    if (player == null || !player.inCreativeMode()) {
                                        if ((material_in_bucket == Material.water||material_in_bucket == Materials.unsafe_water||material_in_bucket==Materials.dangerous_water)) {
                                            if (!allow_placement_of_source_block) {
                                                world.scheduleBlockChange(x, y, z, Block.waterStill.blockID, this.getBlockForContents().blockID, 1, 16);
                                            } else if (!player.inCreativeMode()) {
                                                player.addExperience(-250);
                                            }
                                        } else if (material_in_bucket == Material.lava) {
                                            if (!allow_placement_of_source_block) {
                                                world.scheduleBlockChange(x, y, z, Block.lavaMoving.blockID, this.getBlockForContents().blockID, 1, 48);
                                            } else if (!player.inCreativeMode()) {
                                                player.addExperience(-250);
                                            }
                                        }
                                    }
                                    world.setBlock(x, y, z, this.getBlockForContents().blockID, 0, 3);
                                }
                            }
                        }
                    }

                    return true;
                }
            }
        }
    }
    @Overwrite
    public static boolean shouldContainedLiquidBePlacedAsSourceBlock(EntityPlayer player, boolean ctrl_is_down) {
        if (player == null) {
            return false;
        } else if (player.inCreativeMode()) {
            return true;
        } else {
            return ctrl_is_down && player.experience >= 250;
        }
    }
    @Overwrite
    public static ItemVessel getPeer(Material vessel_material, Material contents) {
        if (contents == null) {
            if (vessel_material == Material.copper) {
                return Item.bucketCopperEmpty;
            } else if (vessel_material == Material.silver) {
                return Item.bucketSilverEmpty;
            } else if (vessel_material == Material.gold) {
                return Item.bucketGoldEmpty;
            } else if (vessel_material == Material.iron) {
                return Item.bucketIronEmpty;
            } else if (vessel_material == Material.mithril) {
                return Item.bucketMithrilEmpty;
            } else if (vessel_material == Material.adamantium) {
                return Item.bucketAdamantiumEmpty;
            } else if (vessel_material == Materials.nickel) {
                return Items.nickelBucket;
            }  else if (vessel_material == Materials.tungsten) {
                return Items.tungstenBucket;
            } else {
                return vessel_material == Material.ancient_metal ? Item.bucketAncientMetalEmpty : null;
            }
        } else if (contents == Material.water) {
            if (vessel_material == Material.copper) {
                return Item.bucketCopperWater;
            } else if (vessel_material == Material.silver) {
                return Item.bucketSilverWater;
            } else if (vessel_material == Material.gold) {
                return Item.bucketGoldWater;
            } else if (vessel_material == Material.iron) {
                return Item.bucketIronWater;
            } else if (vessel_material == Material.mithril) {
                return Item.bucketMithrilWater;
            } else if (vessel_material == Material.adamantium) {
                return Item.bucketAdamantiumWater;
            }  else if (vessel_material == Materials.nickel) {
                return Items.nickelBucketWater;
            }  else if (vessel_material == Materials.tungsten) {
                return Items.tungstenBucketWater;
            } else {
                return vessel_material == Material.ancient_metal ? Item.bucketAncientMetalWater : null;
            }
        }  else if (contents == Materials.unsafe_water) {
            if (vessel_material == Material.copper) {
                return Items.copperBucketWaterSuspicious;
            } else if (vessel_material == Material.silver) {
                return Items.silverBucketWaterSuspicious;
            } else if (vessel_material == Material.gold) {
                return Items.goldBucketWaterSuspicious;
            } else if (vessel_material == Material.iron) {
                return Items.ironBucketWaterSuspicious;
            } else if (vessel_material == Material.mithril) {
                return Items.mithrilBucketWaterSuspicious;
            } else if (vessel_material == Material.adamantium) {
                return Items.adamantiumBucketWaterSuspicious;
            }  else if (vessel_material == Materials.nickel) {
                return Items.nickelBucketWaterSuspicious;
            }  else if (vessel_material == Materials.tungsten) {
                return Items.tungstenBucketWaterSuspicious;
            } else {
                return vessel_material == Material.ancient_metal ? Items.ancientmetalBucketWaterSuspicious : null;
            }
        }  else if (contents == Materials.dangerous_water) {
            if (vessel_material == Material.copper) {
                return Items.copperBucketWaterSwampland;
            } else if (vessel_material == Material.silver) {
                return Items.silverBucketWaterSwampland;
            } else if (vessel_material == Material.gold) {
                return Items.goldBucketWaterSwampland;
            } else if (vessel_material == Material.iron) {
                return Items.ironBucketWaterSwampland;
            } else if (vessel_material == Material.mithril) {
                return Items.mithrilBucketWaterSwampland;
            } else if (vessel_material == Material.adamantium) {
                return Items.adamantiumBucketWaterSwampland;
            }  else if (vessel_material == Materials.nickel) {
                return Items.nickelBucketWaterSwampland;
            }  else if (vessel_material == Materials.tungsten) {
                return Items.tungstenBucketWaterSwampland;
            } else {
                return vessel_material == Material.ancient_metal ? Items.ancientmetalBucketWaterSwampland : null;
            }
        } else if (contents == Material.lava) {
            if (vessel_material == Material.copper) {
                return Item.bucketCopperLava;
            } else if (vessel_material == Material.silver) {
                return Item.bucketSilverLava;
            } else if (vessel_material == Material.gold) {
                return Item.bucketGoldLava;
            } else if (vessel_material == Material.iron) {
                return Item.bucketIronLava;
            } else if (vessel_material == Material.mithril) {
                return Item.bucketMithrilLava;
            } else if (vessel_material == Material.adamantium) {
                return Item.bucketAdamantiumLava;
            }  else if (vessel_material == Materials.nickel) {
                return Items.nickelBucketLava;
            }  else if (vessel_material == Materials.tungsten) {
                return Items.tungstenBucketLava;
            } else {
                return vessel_material == Material.ancient_metal ? Item.bucketAncientMetalLava : null;
            }
        } else if (contents == Material.milk) {
            if (vessel_material == Material.copper) {
                return Item.bucketCopperMilk;
            } else if (vessel_material == Material.silver) {
                return Item.bucketSilverMilk;
            } else if (vessel_material == Material.gold) {
                return Item.bucketGoldMilk;
            } else if (vessel_material == Material.iron) {
                return Item.bucketIronMilk;
            } else if (vessel_material == Material.mithril) {
                return Item.bucketMithrilMilk;
            } else if (vessel_material == Material.adamantium) {
                return Item.bucketAdamantiumMilk;
            }  else if (vessel_material == Materials.nickel) {
                return Items.nickelBucketMilk;
            }  else if (vessel_material == Materials.tungsten) {
                return Items.tungstenBucketMilk;
            } else {
                return vessel_material == Material.ancient_metal ? Item.bucketAncientMetalMilk : null;
            }
        } else if (contents == Material.stone) {
            if (vessel_material == Material.copper) {
                return Item.bucketCopperStone;
            } else if (vessel_material == Material.silver) {
                return Item.bucketSilverStone;
            } else if (vessel_material == Material.gold) {
                return Item.bucketGoldStone;
            } else if (vessel_material == Material.iron) {
                return Item.bucketIronStone;
            } else if (vessel_material == Material.mithril) {
                return Item.bucketMithrilStone;
            } else if (vessel_material == Material.adamantium) {
                return Item.bucketAdamantiumStone;
            }  else if (vessel_material == Materials.nickel) {
                return Items.nickelBucketStone;
            }  else if (vessel_material == Materials.tungsten) {
                return Items.tungstenBucketStone;
            } else {
                return vessel_material == Material.ancient_metal ? Item.bucketAncientMetalStone : null;
            }
        } else {
            return null;
        }
    }
    private Material classifyMaterialForm(Material material){
        return material.classifyMaterialForm(material);
    }

//    @Overwrite
//    public float getChanceOfMeltingWhenFilledWithLava() {
//        Material material = this.getVesselMaterial();
//        return material == Material.adamantium ? 0.0F : (material == Material.gold ? 0.2F : 0.01F * (Material.mithril.getDurability() / material.getDurability()));
//    }
    @Shadow
    public ItemVessel getPeerForContents(Material material) {
        return null;
    }
    @Shadow
    public ItemVessel getPeerForVesselMaterial(Material material) {
        return null;
    }
}
