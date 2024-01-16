package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import static net.minecraft.BlockGoldOre.isGoldOreNetherrack;
import static net.oilcake.mitelros.block.Blocks.oreNickel;
import static net.oilcake.mitelros.block.Blocks.oreTungsten;
@Mixin(BlockOre.class)
public class BlockOreMixin extends Block {
    protected BlockOreMixin(int par1, Material par2Material, BlockConstants constants) {
        super(par1, par2Material, constants);
    }
    @Overwrite
    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        int metadata_dropped = -1;
        int quantity_dropped = 1;
        int id_dropped = 0;
        if (info.wasExploded()) {
            if (this == Block.oreEmerald) {
                id_dropped = -1;
            } else if (this == Block.oreDiamond) {
                id_dropped = -1;
            } else if (this == Block.oreLapis) {
                id_dropped = Items.lapis.itemID;
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
                id_dropped = isGoldOreNetherrack(this,1) ? Items.pieceGoldNether.itemID : Items.pieceGold.itemID;
                quantity_dropped = 1 + info.world.rand.nextInt(2);
            } else if (this == Block.oreIron) {
                id_dropped = Items.pieceIron.itemID;
                quantity_dropped = 1 + info.world.rand.nextInt(2);
            } else if (this == oreNickel) {
                id_dropped = Items.pieceNickel.itemID;
                quantity_dropped = 1 + info.world.rand.nextInt(2);
            } else if (this == Block.oreMithril) {
                id_dropped = Items.pieceMithril.itemID;
                quantity_dropped = 1 + info.world.rand.nextInt(2);
            } else if (this == oreTungsten) {
                id_dropped = Items.pieceTungsten.itemID;
                quantity_dropped = 1 + info.world.rand.nextInt(2);
            } else if (this == Block.oreAdamantium) {
                id_dropped = Items.pieceAdamantium.itemID;
                quantity_dropped = 1 + info.world.rand.nextInt(2);
            }  else if (this == Blocks.oreUru) {
                id_dropped = Items.pieceUru.itemID;
                quantity_dropped = 1 + info.world.rand.nextInt(2);
            } else {
                id_dropped = this.blockID;
            }
        }
        else {
            boolean HasAbsorb = EnchantmentManager.hasEnchantment(info.responsible_item_stack, Enchantments.enchantmentAbsorb);
            if (this == Block.oreEmerald) {
                id_dropped = HasAbsorb ? 0 : Item.shardEmerald.itemID;
                info.getResponsiblePlayer().triggerAchievement(AchievementList.emeralds);
                quantity_dropped = 3 + info.world.rand.nextInt(5);
            } else if (this == Block.oreNetherQuartz) {
                id_dropped = HasAbsorb ? 0 : Item.shardNetherQuartz.itemID;
                quantity_dropped = 3 + info.world.rand.nextInt(5);
            } else if (this == Block.oreDiamond) {
                id_dropped = HasAbsorb ? 0 : Item.shardDiamond.itemID;
                info.getResponsiblePlayer().triggerAchievement(AchievementList.diamonds);
                quantity_dropped = 3 + info.world.rand.nextInt(5);
            } else if (this == Block.oreLapis) {
                id_dropped = HasAbsorb ? 0 : Items.lapis.itemID;
                quantity_dropped = 2 + info.world.rand.nextInt(2);
            } else if (this == Block.oreCoal) {
                id_dropped = Item.coal.itemID;
            } else if (this == Block.oreRedstone) {
                id_dropped = Item.redstone.itemID;
                quantity_dropped = 3 + info.world.rand.nextInt(2);
            } else if (this == Block.oreCopper) {
                id_dropped = Items.pieceCopper.itemID;
                quantity_dropped = 3 + info.world.rand.nextInt(5);
            } else if (this == Block.oreSilver) {
                id_dropped = Items.pieceSilver.itemID;
                quantity_dropped = 3 + info.world.rand.nextInt(5);
            } else if (this == Block.oreGold) {
                id_dropped = Items.pieceGold.itemID;
                quantity_dropped = 3 + info.world.rand.nextInt(5);
            } else if (this == Block.oreIron) {
                id_dropped = Items.pieceIron.itemID;
                quantity_dropped = 3 + info.world.rand.nextInt(5);
            } else if (this == oreNickel) {
                id_dropped = Items.pieceNickel.itemID;
                quantity_dropped = 3 + info.world.rand.nextInt(5);
            } else if (this == Block.oreMithril) {
                id_dropped = Items.pieceMithril.itemID;
                quantity_dropped = 3 + info.world.rand.nextInt(5);
            } else if (this == oreTungsten) {
                id_dropped = Items.pieceTungsten.itemID;
                quantity_dropped = 3 + info.world.rand.nextInt(5);
            } else if (this == Block.oreAdamantium) {
                id_dropped = Items.pieceAdamantium.itemID;
                quantity_dropped = 3 + info.world.rand.nextInt(5);
            }  else if (this == Blocks.oreUru) {
                id_dropped = Items.pieceUru.itemID;
                quantity_dropped = 3 + info.world.rand.nextInt(5);
            } else {
                id_dropped = this.blockID;
            }
        }
        boolean suppress_fortune = id_dropped == this.blockID && BitHelper.isBitSet(info.getMetadata(), 1);
        if (id_dropped != -1 && info.getMetadata() == 0) {
            DedicatedServer.incrementTournamentScoringCounter(info.getResponsiblePlayer(), Item.getItem(id_dropped));
        }
        float chance = suppress_fortune ? 1.0F : 1.0F + (float) info.getHarvesterFortune() * 0.2F;
        if (EnchantmentManager.hasEnchantment(info.responsible_item_stack, Enchantments.enchantmentAbsorb)) {
            if (this == Block.oreDiamond) {
                this.dropXpOnBlockBreak(info.world, info.x, info.y, info.z, (int)(530*chance));
            } else if (this == Block.oreEmerald) {
                this.dropXpOnBlockBreak(info.world, info.x, info.y, info.z, (int)(270*chance));
            } else if (this == Block.oreNetherQuartz) {
                this.dropXpOnBlockBreak(info.world, info.x, info.y, info.z, (int)(60*chance));
            } else if (this == Block.oreLapis) {
                this.dropXpOnBlockBreak(info.world, info.x, info.y, info.z, (int)(((3 + info.world.rand.nextInt(3)) * 30)*chance));
            }
        }
        boolean HasMelting = EnchantmentManager.hasEnchantment(info.responsible_item_stack, Enchantments.enchantmentMelting);
        if(HasMelting){
            float melting_chance = EnchantmentManager.getEnchantmentLevelFraction(Enchantments.enchantmentMelting, info.responsible_item_stack);
            melting_chance *= info.responsible_item_stack.getItemAsTool().getMaterialHarvestLevel() - this.getMinHarvestLevel(0);
            if(info.world.rand.nextFloat() < melting_chance){
                if (this == Block.oreCopper) {
                    id_dropped = Item.copperNugget.itemID;
                } else if (this == Block.oreSilver) {
                    id_dropped = Item.silverNugget.itemID;
                } else if (this == Block.oreGold) {
                    id_dropped = Item.goldNugget.itemID;
                } else if (this == Block.oreIron) {
                    id_dropped = Items.ironNugget.itemID;
                } else if (this == oreNickel) {
                    id_dropped = Items.nickelNugget.itemID;
                } else if (this == Block.oreMithril) {
                    id_dropped = Item.mithrilNugget.itemID;
                } else if (this == oreTungsten) {
                    id_dropped = Items.tungstenNugget.itemID;
                } else if (this == Block.oreAdamantium) {
                    id_dropped = Item.adamantiumNugget.itemID;
                }  else if (this == Blocks.oreUru) {
                    id_dropped = Items.UruNugget.itemID;
                }
            }
        }
        return super.dropBlockAsEntityItem(info, id_dropped, metadata_dropped, quantity_dropped, chance);
    }
    @Shadow
    public String getMetadataNotes() {
        return "1";
    }
    @Shadow
    public boolean isValidMetadata(int metadata) {
        return metadata >= 0 && metadata < 2;
    }
    @Shadow
    public void addItemBlockMaterials(ItemBlock item_block) {}

    @Shadow
    public int getMetadataForPlacement(World world, int x, int y, int z, ItemStack item_stack, Entity entity, EnumFace face, float offset_x, float offset_y, float offset_z) {
        return 1;
    }
}
