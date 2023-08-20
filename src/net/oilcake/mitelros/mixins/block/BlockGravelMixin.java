package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(BlockGravel.class)
public class BlockGravelMixin extends Block {
    protected BlockGravelMixin(int par1, Material par2Material, BlockConstants constants) {
        super(par1, par2Material, constants);
    }

    protected void dropXpOnBlockBreak(World par1World, int par2, int par3, int par4, int par5) {

    }

    @Overwrite
    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        if (info.getMetadata() == 1) {
            return super.dropBlockAsEntityItem(info);
        } else if (!info.wasExploded() && info.wasHarvestedByPlayer()) {
            int fortune = info.getHarvesterFortune();
            if (fortune > 3) {
                fortune = 3;
            }

            Random rand = info.world.rand;
            if (rand.nextInt(12 - fortune * 2) > 2) {
                return super.dropBlockAsEntityItem(info);
            } else {
                int id_dropped;
                if (rand.nextInt(3) > 0) {
                    if (rand.nextInt(16) == 0) {
                        id_dropped = info.wasExploded() ? Item.chipFlint.itemID : Item.flint.itemID;
                    } else {
                        if (info.wasExploded()) {
                            return super.dropBlockAsEntityItem(info);
                        }

                        id_dropped = Item.chipFlint.itemID;
                    }
                } else if (rand.nextInt(3) > 0) {
                    id_dropped = Item.copperNugget.itemID;
                } else if (rand.nextInt(3) > 0) {
                    id_dropped = Item.silverNugget.itemID;
                } else if (rand.nextInt(3) > 0) {
                    id_dropped = Item.goldNugget.itemID;
                } else if (rand.nextInt(3) > 0) {
                    id_dropped = Items.nickelNugget.itemID;
                } else if (rand.nextInt(3) > 0) {
                    id_dropped = info.wasExploded() ? -1 : Item.shardObsidian.itemID;
                } else if (rand.nextInt(3) > 0) {
                    id_dropped = info.wasExploded() ? -1 : Item.shardEmerald.itemID;
                } else if (rand.nextInt(3) > 0) {
                    id_dropped = info.wasExploded() ? -1 : Item.shardDiamond.itemID;
                } else if (rand.nextInt(3) > 0) {
                    id_dropped = Item.mithrilNugget.itemID;
                } else if (rand.nextInt(3) > 0) {
                    id_dropped = Items.tungstenNugget.itemID;
                }else {
                    id_dropped = Item.adamantiumNugget.itemID;
                }

                if (this.isNetherGravel(info.getMetadata())) {
                    if (rand.nextInt(12 - fortune * 2) > 4) {
                        return super.dropBlockAsEntityItem(info);
                    } else if (rand.nextInt(8) > 0) {
                        id_dropped = Item.shardNetherQuartz.itemID;
                    } else if (rand.nextInt(8) > 0) {
                        id_dropped = Item.goldNugget.itemID;
                    } else {
                        id_dropped = Items.tungstenNugget.itemID;
                    }
                }

                if (id_dropped != -1) {
                    DedicatedServer.incrementTournamentScoringCounter(info.getResponsiblePlayer(), Item.getItem(id_dropped));
                }

                if (info.wasHarvestedByPlayer() && (id_dropped == Item.chipFlint.itemID || id_dropped == Item.flint.itemID)) {
                    info.getResponsiblePlayer().triggerAchievement(AchievementList.flintFinder);
                }

                return this.dropBlockAsEntityItem(info, id_dropped);
            }
        } else {
            return super.dropBlockAsEntityItem(info);
        }
    }
    @Shadow
    public boolean isNetherGravel(int metadata) {
        return false;
    }

}
