package net.oilcake.mitelros.mixins;

import net.minecraft.*;
import net.oilcake.mitelros.achivements.AchievementExtend;
import net.oilcake.mitelros.item.ItemBowlClay;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SlotResult.class)
public class SlotResultMixin extends Slot {
    @Shadow
    private EntityPlayer thePlayer;
    @Shadow
    private int amountCrafted;

    public SlotResultMixin(IInventory inventory, int slot_index, int display_x, int display_y) {
        super(inventory, slot_index, display_x, display_y);
    }
    public int getNumCraftingResultsC(EntityPlayer player) {
        return this.getNumCraftingResults(player);
    }
    @Shadow
    private int getNumCraftingResults(EntityPlayer player) {
        return 0;
    }

    @Overwrite
    protected void onCrafting(ItemStack par1ItemStack) {
        par1ItemStack.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.amountCrafted);
        this.amountCrafted = 0;
        Item item = par1ItemStack.getItem();
        Block block = item instanceof ItemBlock ? ((ItemBlock)item).getBlock() : null;
        if (item == Items.LeggingsAncientmetalsacred || item == Items.ChestplateAncientmetalsacred ||item == Items.HelmetAncientmetalsacred ||item == Items.BootsAncientmetalsacred ){
            this.thePlayer.addStat(AchievementExtend.forgingLegend,1);
        }
        if (item == Items.forgingnote){
            this.thePlayer.addStat(AchievementExtend.copying,1);
        }
        if (item == Items.bowTungsten){
            this.thePlayer.addStat(AchievementExtend.Arbalistic,1);
        }
        if (block == Block.beacon){
            this.thePlayer.addStat(AchievementExtend.getBeacon,1);
        }
        if (item == Items.mashedCactus){
            this.thePlayer.addStat(AchievementList.seeds,1);
        }
        if (item == Items.glowberries || item == Items.Agave){
            this.thePlayer.addStat(AchievementExtend.mashedCactus,1);
        }
        if (item == Items.Pulque || item == Items.Ale){
            this.thePlayer.addStat(AchievementExtend.cheersforMinecraft,1);
        }
        if (item == Items.ExperimentalPotion){
            this.thePlayer.addStat(AchievementExtend.nochoice,1);
        }
        if (block instanceof BlockFurnace && ((BlockFurnace)block).isOven()) {
            this.thePlayer.addStat(AchievementList.buildOven, 1);
        } else if (par1ItemStack.itemID == Block.workbench.blockID) {
            Material tool_material = BlockWorkbench.getToolMaterial(par1ItemStack.getItemSubtype());
            if (tool_material.isMetal()) {
                this.thePlayer.addStat(AchievementList.betterTools, 1);
            } else {
                this.thePlayer.addStat(AchievementList.buildWorkBench, 1);
            }
        } else if (block == Block.torchWood) {
            this.thePlayer.addStat(AchievementList.buildTorches, 1);
        } else if (item != Item.pickaxeCopper && item != Item.pickaxeSilver && item != Item.pickaxeGold) {
            if (par1ItemStack.itemID == Block.furnaceIdle.blockID) {
                this.thePlayer.addStat(AchievementList.buildFurnace, 1);
            } else if (par1ItemStack.itemID == Block.furnaceObsidianIdle.blockID) {
                this.thePlayer.triggerAchievement(AchievementList.obsidianFurnace);
            } else if (par1ItemStack.itemID == Block.furnaceNetherrackIdle.blockID) {
                this.thePlayer.triggerAchievement(AchievementList.netherrackFurnace);
            } else if (!(item instanceof ItemHoe) && !(item instanceof ItemMattock)) {
                if (par1ItemStack.itemID == Item.cake.itemID) {
                    this.thePlayer.addStat(AchievementList.bakeCake, 1);
                } else if (item instanceof ItemTool && item.getAsTool().isEffectiveAgainstBlock(Block.obsidian, 0)) {
                    this.thePlayer.addStat(AchievementList.buildBetterPickaxe, 1);
                    if (this.thePlayer.worldObj instanceof WorldServer) {
                        this.thePlayer.worldObj.getWorldInfo().fullfillVillageCondition(16, (WorldServer)this.thePlayer.worldObj);
                    }

                    if (item.getAsTool().isEffectiveAgainstBlock(Block.blockMithril, 0)) {
                        this.thePlayer.triggerAchievement(AchievementList.crystalBreaker);
                    }
                } else if (item != Item.hatchetFlint && item != Item.knifeFlint) {
                    if (item == Item.clubWood) {
                        this.thePlayer.addStat(AchievementList.buildClub, 1);
                    } else if (item instanceof ItemAxe && !(item instanceof ItemHatchet)) {
                        this.thePlayer.addStat(AchievementList.buildAxe, 1);
                    } else if (par1ItemStack.itemID != Block.enchantmentTable.blockID && par1ItemStack.itemID != Block.enchantmentTableEmerald.blockID) {
                        if (par1ItemStack.itemID == Block.bookShelf.blockID) {
                            this.thePlayer.addStat(AchievementList.bookcase, 1);
                        } else if (item instanceof ItemShovel && !(item instanceof ItemMattock)) {
                            this.thePlayer.addStat(AchievementList.buildShovel, 1);
                        } else if (item instanceof ItemScythe) {
                            this.thePlayer.addStat(AchievementList.buildScythe, 1);
                        } else if (item instanceof ItemArmor && ((ItemArmor)item).isChainMail()) {
                            this.thePlayer.addStat(AchievementList.buildChainMail, 1);
                        } else if (item instanceof ItemFishingRod) {
                            this.thePlayer.triggerAchievement(AchievementList.fishingRod);
                        } else if (item == Item.flour) {
                            this.thePlayer.triggerAchievement(AchievementList.flour);
                        } else if (item instanceof ItemBowl && (item == Item.bowlSalad || ItemBowl.isSoupOrStew(item))) {
                            this.thePlayer.triggerAchievement(AchievementList.fineDining);
                        } else if (item instanceof ItemBowlClay && (item == Items.claybowlSalad || ItemBowlClay.isSoupOrStew(item))) {
                            this.thePlayer.triggerAchievement(AchievementList.fineDining);
                        }
                    } else {
                        this.thePlayer.addStat(AchievementList.enchantments, 1);
                    }
                } else {
                    this.thePlayer.addStat(AchievementList.cuttingEdge, 1);
                }
            } else {
                this.thePlayer.addStat(AchievementList.buildHoe, 1);
            }
        } else {
            if (!this.thePlayer.worldObj.isRemote) {
                DedicatedServer.checkForTournamentWinner(this.thePlayer, EnumTournamentType.pickaxe);
            }

            this.thePlayer.addStat(AchievementList.buildPickaxe, 1);
        }

    }
}