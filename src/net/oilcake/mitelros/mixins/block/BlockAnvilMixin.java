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
        TileEntityAnvil tile_entity_anvil = (TileEntityAnvil)info.tile_entity;

        float centesimal = this.getAnvilDurabilityByCentesimal(tile_entity_anvil.damage);
        if (centesimal == 100){
           super.dropBlockAsEntityItem(info.setDamage(tile_entity_anvil.damage));
        } else if (centesimal <= 66 && centesimal >= 33){
            this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemNugget.class, metal_type).itemID, 0, 30, 1.4F);
            this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, metal_type).itemID, 0, 10, 1.4F);
        } else if (centesimal <= 33){
            this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemNugget.class, metal_type).itemID, 0, 20, 1.3F);
            this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, metal_type).itemID, 0, 5, 1.3F);
        }
        return -1;
    }

    public float getAnvilDurabilityByCentesimal(int damage){
        float nowDurability = this.getDurability() - damage;
        return (nowDurability / this.getDurability()) * 100;
    }


    @Shadow
    public Material metal_type;
    @Shadow
    public int getMinimumDamageForStage(int stage) {
        return 0;
    }
    @Shadow
    public int getDurability() {
        return 0;
    }


}
