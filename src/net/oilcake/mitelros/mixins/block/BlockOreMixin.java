package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
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
            } else {
                id_dropped = this.blockID;
            }
        }
        else {
            boolean HasAbsorb = EnchantmentManager.hasEnchantment(info.responsible_item_stack, Enchantments.enchantmentAbsorb);
            boolean HasRestore = EnchantmentManager.hasEnchantment(info.responsible_item_stack, Enchantments.enchantmentRestore);
            if (this == Block.oreEmerald) {
                id_dropped = HasAbsorb ? 0 : HasRestore ? Item.emerald.itemID : Item.shardEmerald.itemID;
                quantity_dropped = HasRestore ? 1 : 3 + info.world.rand.nextInt(5);
            } else if (this == Block.oreNetherQuartz) {
                id_dropped = HasAbsorb ? 0 : HasRestore ? Item.netherQuartz.itemID : Item.shardNetherQuartz.itemID;
                quantity_dropped = HasRestore ? 1 : 3 + info.world.rand.nextInt(5);
            } else if (this == Block.oreDiamond) {
                id_dropped = HasAbsorb ? 0 : HasRestore ? Item.diamond.itemID : Item.shardDiamond.itemID;
                quantity_dropped = HasRestore ? 1 : 3 + info.world.rand.nextInt(5);
            } else if (this == Block.oreLapis) {
                id_dropped = HasAbsorb ? 0 : Items.lapis.itemID;
                metadata_dropped = 4;
                quantity_dropped = HasRestore ? 3 + info.world.rand.nextInt(3) : 2 + info.world.rand.nextInt(2);
            } else if (this == Block.oreCoal) {
                id_dropped = Item.coal.itemID;
            } else if (this == Block.oreRedstone) {
                id_dropped = Item.redstone.itemID;
                quantity_dropped = 3 + info.world.rand.nextInt(2);
            } else if (this == Block.oreCopper) {
                id_dropped = HasRestore ? this.blockID : Items.pieceCopper.itemID;
                quantity_dropped = HasRestore ? 1 : 3 + info.world.rand.nextInt(5);
            } else if (this == Block.oreSilver) {
                id_dropped = HasRestore ? this.blockID : Items.pieceSilver.itemID;
                quantity_dropped = HasRestore ? 1 : 3 + info.world.rand.nextInt(5);
            } else if (this == Block.oreGold) {
                id_dropped = HasRestore ? this.blockID : Items.pieceGold.itemID;
                quantity_dropped = HasRestore ? 1 : 3 + info.world.rand.nextInt(5);
            } else if (this == Block.oreIron) {
                id_dropped = HasRestore ? this.blockID : Items.pieceIron.itemID;
                quantity_dropped = HasRestore ? 1 : 3 + info.world.rand.nextInt(5);
            } else if (this == oreNickel) {
                id_dropped = HasRestore ? this.blockID : Items.pieceNickel.itemID;
                quantity_dropped = HasRestore ? 1 : 3 + info.world.rand.nextInt(5);
            } else if (this == Block.oreMithril) {
                id_dropped = HasRestore ? this.blockID : Items.pieceMithril.itemID;
                quantity_dropped = HasRestore ? 1 : 3 + info.world.rand.nextInt(5);
            } else if (this == oreTungsten) {
                id_dropped = HasRestore ? this.blockID : Items.pieceTungsten.itemID;
                quantity_dropped = HasRestore ? 1 : 3 + info.world.rand.nextInt(5);
            } else if (this == Block.oreAdamantium) {
                id_dropped = HasRestore ? this.blockID : Items.pieceAdamantium.itemID;
                quantity_dropped = HasRestore ? 1 : 3 + info.world.rand.nextInt(5);
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
                this.dropXpOnBlockBreak(info.world, info.x, info.y, info.z, (int)(500*chance));
            } else if (this == Block.oreEmerald) {
                this.dropXpOnBlockBreak(info.world, info.x, info.y, info.z, (int)(250*chance));
            } else if (this == Block.oreNetherQuartz) {
                this.dropXpOnBlockBreak(info.world, info.x, info.y, info.z, (int)(50*chance));
            } else if (this == Block.oreLapis) {
                this.dropXpOnBlockBreak(info.world, info.x, info.y, info.z, (int)(((2 + info.world.rand.nextInt(2)) * 25)*chance));
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
