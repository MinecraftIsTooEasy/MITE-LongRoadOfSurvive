package net.oilcake.mitelros.mixins.world;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(WorldGenDungeons.class)
public class WorldGenDungeonsMixin extends WorldGenerator {
    private static final StructurePieceTreasure[] field_111189_a = new StructurePieceTreasure[]{
            new StructurePieceTreasure(Item.bread.itemID, 0, 1, 1, 10),
            new StructurePieceTreasure(Item.carrot.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.potato.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.onion.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.wheat.itemID, 0, 1, 4, 10),
            new StructurePieceTreasure(Items.beetroot.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.appleGold.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.coinCopper.itemID, 0, 1, 4, 10),
            new StructurePieceTreasure(Item.coinSilver.itemID, 0, 1, 2, 2),
            new StructurePieceTreasure(Item.coinGold.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.saddle.itemID, 0, 1, 1, 10),
            new StructurePieceTreasure(Item.gunpowder.itemID, 0, 1, 4, 10),
            new StructurePieceTreasure(Item.silk.itemID, 0, 1, 4, 10),
            new StructurePieceTreasure(Item.bowlEmpty.itemID, 0, 1, 1, 3),
            new StructurePieceTreasure(Item.bucketCopperEmpty.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.bucketIronEmpty.itemID, 0, 1, 1, 3),
            new StructurePieceTreasure(Item.record13.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.recordCat.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.nameTag.itemID, 0, 1, 1, 10),
            new StructurePieceTreasure(Item.horseArmorGold.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.horseArmorCopper.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.horseArmorIron.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.shearsRustedIron.itemID, 0, 1, 1, 3),
            new StructurePieceTreasure(Item.fishingRodFlint.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.fishingRodCopper.itemID, 0, 1, 1, 3),
            new StructurePieceTreasure(Item.fishingRodIron.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.shovelWood.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.shovelRustedIron.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.shovelCopper.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.hoeRustedIron.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.hoeCopper.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.mattockRustedIron.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.mattockCopper.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.daggerRustedIron.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.daggerCopper.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.daggerSilver.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.swordRustedIron.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.swordCopper.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.battleAxeRustedIron.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.battleAxeCopper.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.warHammerRustedIron.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.warHammerCopper.itemID, 0, 1, 1, 1)};
    private static final StructurePieceTreasure[] chest_contents_for_underworld = new StructurePieceTreasure[]{
            new StructurePieceTreasure(Item.ancientMetalNugget.itemID, 0, 1, 4, 10),
            new StructurePieceTreasure(Item.ingotAncientMetal.itemID, 0, 1, 5, 10),
            new StructurePieceTreasure(Item.goldNugget.itemID, 0, 1, 5, 10),
            new StructurePieceTreasure(Items.AncientmetalArmorPiece.itemID, 0, 1, 2, 10),
            new StructurePieceTreasure(Item.gunpowder.itemID, 0, 1, 4, 10),
            new StructurePieceTreasure(Item.silk.itemID, 0, 1, 4, 10),
            new StructurePieceTreasure(Item.bowlEmpty.itemID, 0, 1, 1, 3),
            new StructurePieceTreasure(Item.bucketAncientMetalEmpty.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.coinAncientMetal.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.recordUnderworld.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.recordDescent.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.recordWanderer.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.recordLegends.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.nameTag.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.horseArmorAncientMetal.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.fishingRodAncientMetal.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.pickaxeAncientMetal.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.shovelAncientMetal.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.daggerAncientMetal.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.swordAncientMetal.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.bowAncientMetal.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.bootsChainAncientMetal.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.legsChainAncientMetal.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.plateChainAncientMetal.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.helmetChainAncientMetal.itemID, 0, 1, 1, 2)};
    @Shadow
    public boolean generate(World world, Random random, int i, int i1, int i2) {
        return false;
    }
    @Overwrite
    private String pickMobSpawner(World world, Random par1Random, int y) {
        if (world.isUnderworld()) {
            return par1Random.nextInt(6) == 0 ? "LongdeadGuardian" : "Longdead";
        } else {
            int danger;
            if(StuckTagConfig.TagConfig.TagMiracleDisaster.ConfigValue){
                danger = par1Random.nextInt(7);
            }
            else{
                if (par1Random.nextInt(2) == 0) {
                    danger = par1Random.nextInt(4);
                } else {
                    danger = (int) Math.max(1.0F - (float) y / 64.0F, 0.0F) * 4 + par1Random.nextInt(3) - par1Random.nextInt(3);
                }

                if (danger < 0) {
                    danger = par1Random.nextInt(4);
                }
            }

            switch (danger) {
                case 0:
                    return "Zombie";
                case 1:
                    return "Ghoul";
                case 2:
                    return "Skeleton";
                case 3:
                    return "Spider";
                case 4:
                    return "Wight";
                case 5:
                    return "DemonSpider";
                default:
                    return "Hellhound";
            }
        }
    }

}
