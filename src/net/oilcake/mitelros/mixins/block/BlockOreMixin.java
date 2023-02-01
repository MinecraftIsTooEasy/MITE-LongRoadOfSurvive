package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.*;

import static net.oilcake.mitelros.block.Blocks.blockNickelOre;
import static net.oilcake.mitelros.block.Blocks.blockTungstenOre;
@Mixin(BlockOre.class)
public class BlockOreMixin extends Block {
        @Shadow
        public Material vein_material;
        public BlockOreMixin(int par1, Material vein_material, int min_harvest_level) {
            super(par1, Material.stone, new BlockConstants());
            this.vein_material = vein_material;
            this.setCreativeTab(CreativeModeTab.tabBlock);
            this.setMinHarvestLevel(min_harvest_level);
        }
        @Shadow
        public String getMetadataNotes() {
            return "0=Natural, 1=Placed";
        }
        @Shadow
        public boolean isValidMetadata(int metadata) {
            return metadata >= 0 && metadata < 2;
        }
        @Overwrite
        public int dropBlockAsEntityItem(BlockBreakInfo info) {
            int metadata_dropped = -1;
            int quantity_dropped = 1;
            int id_dropped;
            if (info.wasExploded()) {
                if (this == Block.oreEmerald) {
                    id_dropped = -1;
                } else if (this == Block.oreDiamond) {
                    id_dropped = -1;
                } else if (this == Block.oreLapis) {
                    id_dropped = Item.dyePowder.itemID;
                    metadata_dropped = 4;
                    quantity_dropped = 1 + info.world.rand.nextInt(1);
                } else if (this == Block.oreNetherQuartz) {
                    id_dropped = -1;
                } else if (this == Block.oreCoal) {
                    id_dropped = -1;
                }  else if (this == Block.oreRedstone) {
                    id_dropped = -1;
                } else if (this == Block.oreCopper) {
                    id_dropped = Items.pieceCopper.itemID;
                    quantity_dropped = 1 + info.world.rand.nextInt(2);
                } else if (this == Block.oreSilver) {
                    id_dropped = Items.pieceSilver.itemID;
                    quantity_dropped = 1 + info.world.rand.nextInt(2);
                } else if (this == Block.oreGold) {
                    id_dropped = Items.pieceGold.itemID;
                    quantity_dropped = 1 + info.world.rand.nextInt(2);
                }
//            else if (block == Block.oreGold && Block.oreGold.isValidMetadata(2)){
//                id_dropped = Items.pieceGoldNether.itemID;
//                quantity_dropped = 1 + info.world.rand.nextInt(2);
//            }
                else if (this == Block.oreIron) {
                    id_dropped = Items.pieceIron.itemID;
                    quantity_dropped = 1 + info.world.rand.nextInt(2);
                } else if (this == blockNickelOre) {
                    id_dropped = Items.pieceNickel.itemID;
                    quantity_dropped = 1 + info.world.rand.nextInt(2);
                } else if (this == Block.oreMithril) {
                    id_dropped = Items.pieceMithril.itemID;
                    quantity_dropped = 1 + info.world.rand.nextInt(2);
                } else if (this == blockTungstenOre) {
                    id_dropped = Items.pieceTungsten.itemID;
                    quantity_dropped = 1 + info.world.rand.nextInt(2);
                } else if (this == Block.oreAdamantium) {
                    id_dropped = Items.pieceAdamantium.itemID;
                    quantity_dropped = 1 + info.world.rand.nextInt(2);
                } else {
                    id_dropped = this.blockID;
                }
            } else {
                if (this == Block.oreCoal) {
                    id_dropped = Item.coal.itemID;
                } else if (this == Block.oreDiamond) {
                    id_dropped = Item.shardDiamond.itemID;
                    quantity_dropped = 3 + info.world.rand.nextInt(4);
                } else if (this == Block.oreLapis) {
                    id_dropped = Item.dyePowder.itemID;
                    metadata_dropped = 4;
                    quantity_dropped = 2 + info.world.rand.nextInt(2);
                } else if (this == Block.oreRedstone) {
                    id_dropped = Item.redstone.itemID;
                    quantity_dropped = 3 + info.world.rand.nextInt(2);
                } else if (this == Block.oreEmerald) {
                    id_dropped = Item.shardEmerald.itemID;
                    quantity_dropped = 3 + info.world.rand.nextInt(4);
                } else if (this == Block.oreNetherQuartz) {
                    id_dropped = Item.shardNetherQuartz.itemID;
                    quantity_dropped = 3 + info.world.rand.nextInt(4);
                } else if (this == Block.oreCopper) {
                    id_dropped = Items.pieceCopper.itemID;
                    quantity_dropped = 3 + info.world.rand.nextInt(4);
                } else if (this == Block.oreSilver) {
                    id_dropped = Items.pieceSilver.itemID;
                    quantity_dropped = 3 + info.world.rand.nextInt(4);
                } else if (this == Block.oreGold) {
                    id_dropped = Items.pieceGold.itemID;
                    quantity_dropped = 3 + info.world.rand.nextInt(4);
                }
//            else if (block  == Block.oreGold) {
//                id_dropped = Items.pieceGoldNether.itemID;
//                quantity_dropped = 3 + info.world.rand.nextInt(4);
//            }
                else if (this == Block.oreIron) {
                    id_dropped = Items.pieceIron.itemID;
                    quantity_dropped = 3 + info.world.rand.nextInt(4);
                } else if (this == blockNickelOre) {
                    id_dropped = Items.pieceNickel.itemID;
                    quantity_dropped = 3 + info.world.rand.nextInt(4);
                } else if (this == Block.oreMithril) {
                    id_dropped = Items.pieceMithril.itemID;
                    quantity_dropped = 3 + info.world.rand.nextInt(4);
                } else if (this == blockTungstenOre) {
                    id_dropped = Items.pieceTungsten.itemID;
                    quantity_dropped = 3 + info.world.rand.nextInt(4);
                } else if (this == Block.oreAdamantium) {
                    id_dropped = Items.pieceAdamantium.itemID;
                    quantity_dropped = 3 + info.world.rand.nextInt(4);
                } else {
                    id_dropped = this.blockID;
                }
            }

            if (metadata_dropped == -1) {
                metadata_dropped = id_dropped == this.blockID ? this.getItemSubtype(info.getMetadata()) : 0;
            }

            boolean suppress_fortune = id_dropped == this.blockID && BitHelper.isBitSet(info.getMetadata(), 1);
            if (id_dropped != -1 && info.getMetadata() == 0) {
                DedicatedServer.incrementTournamentScoringCounter(info.getResponsiblePlayer(), Item.getItem(id_dropped));
            }

            float chance = suppress_fortune ? 1.0F : 1.0F + (float) info.getHarvesterFortune() * 0.1F;
            return super.dropBlockAsEntityItem(info, id_dropped, metadata_dropped, quantity_dropped, chance);
        }
        @Shadow
    public void addItemBlockMaterials(ItemBlock item_block) {
        item_block.addMaterial(new Material[]{this.blockMaterial, this.vein_material});
    }
    @Shadow
    public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
        return 1;
    }
    }
