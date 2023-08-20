package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(BlockAnvil.class)
public class BlockAnvilMixin extends BlockFalling{

    public BlockAnvilMixin(int par1, Material material, BlockConstants constants) {
        super(par1, material, constants);
    }

    @Overwrite
    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        if (info.getMetadata() == 0){
            this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemNugget.class, metal_type).itemID, 0, 40, 1.5F);
            this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, metal_type).itemID, 0, 15, 1.5F);
        } else if (info.damage <= this.getMinimumDamageForStage(1) && info.damage >= this.getMinimumDamageForStage(2)){
            this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemNugget.class, metal_type).itemID, 0, 30, 1.4F);
            this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, metal_type).itemID, 0, 10, 1.4F);
        } else if (info.damage <= this.getMinimumDamageForStage(2)){
            this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemNugget.class, metal_type).itemID, 0, 20, 1.3F);
            this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, metal_type).itemID, 0, 5, 1.3F);
        }


        TileEntityAnvil tile_entity_anvil = (TileEntityAnvil)info.tile_entity;
        info.setDamage(tile_entity_anvil.damage);
        return -1;
    }


    @Shadow
    public Material metal_type;
    @Shadow
    public int getMinimumDamageForStage(int stage) {
        return 0;
    }



}
