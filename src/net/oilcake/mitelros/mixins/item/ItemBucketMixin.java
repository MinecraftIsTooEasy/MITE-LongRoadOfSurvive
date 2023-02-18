package net.oilcake.mitelros.mixins.item;

import net.minecraft.Item;
import net.minecraft.ItemBucket;
import net.minecraft.ItemVessel;
import net.minecraft.Material;
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
