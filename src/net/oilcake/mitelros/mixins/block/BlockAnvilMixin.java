package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockAnvil.class)
public class BlockAnvilMixin extends BlockFalling{

    public BlockAnvilMixin(int par1, Material material, BlockConstants constants) {
        super(par1, material, constants);
    }

    protected void dropXpOnBlockBreak(World par1World, int par2, int par3, int par4, int par5) {

    }

    @Overwrite
    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        TileEntityAnvil tile_entity_anvil = (TileEntityAnvil)info.tile_entity;
        if(ExperimentalConfig.TagConfig.TagBenchingV2.ConfigValue || info.wasExploded()){
            float centesimal = this.getAnvilDurabilityByCentesimal(tile_entity_anvil.damage);
            if(centesimal <= 0.5){
                int expecting_nuggets = (int) (31 * 9 * centesimal);
                if(info.wasExploded()){
                    expecting_nuggets *= 0.5;
                }
                while (expecting_nuggets > 80){
                    expecting_nuggets -= 81;
                    this.dropBlockAsEntityItem(info, this.getMatchingBlock(BlockOreBlock.class, metal_type).blockID);
                }
                while (expecting_nuggets > 8){
                    expecting_nuggets -= 9;
                    this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, metal_type).itemID);
                }
                while (expecting_nuggets > 0){
                    expecting_nuggets -= 1;
                    this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemNugget.class, metal_type).itemID);
                }
                return 0;
            }
            return super.dropBlockAsEntityItem(info.setDamage(tile_entity_anvil.damage));
        }
        return super.dropBlockAsEntityItem(info.setDamage(tile_entity_anvil.damage));
    }

    public float getAnvilDurabilityByCentesimal(int damage){
        float nowDurability = this.getDurability() - damage;
        return (nowDurability / this.getDurability());
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
