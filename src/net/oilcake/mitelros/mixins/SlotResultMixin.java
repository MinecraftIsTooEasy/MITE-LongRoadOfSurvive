package net.oilcake.mitelros.mixins;

import net.minecraft.*;
import net.oilcake.mitelros.achivements.AchievementExtend;
import net.oilcake.mitelros.block.BlockWoodbench;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.ItemBowlClay;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
    public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack) {
        int consumption = this.crafting_result.consumption;
        this.amountCrafted = par2ItemStack.stackSize;
        this.onCrafting(par2ItemStack);
        par1EntityPlayer.inventory.addItemStackToInventoryOrDropIt(par2ItemStack.copy());
        int xp_reclaimed = 0;

        for(int var3 = 0; var3 < this.craftMatrix.getSizeInventory(); ++var3) {
            ItemStack var4 = this.craftMatrix.getStackInSlot(var3);
            if (var4 != null) {
                Item item = var4.getItem();
                if (item instanceof ItemCoin) {
                    ItemCoin coin = (ItemCoin)item;
                    xp_reclaimed += coin.getExperienceValue();
                }

                this.craftMatrix.decrStackSize(var3, consumption);
                if (var4.getItem().hasContainerItem()) {
                    ItemStack var5 = new ItemStack(var4.getItem().getContainerItem());
                    Item container_item = var5.getItem();
                    if (container_item.getClass() != par2ItemStack.getItem().getClass() && (!var4.getItem().doesContainerItemLeaveCraftingGrid(var4) || !this.thePlayer.inventory.addItemStackToInventory(var5))) {
                        if (this.craftMatrix.getStackInSlot(var3) == null) {
                            this.craftMatrix.setInventorySlotContents(var3, var5);
                        } else {
                            this.thePlayer.dropPlayerItem(var5);
                        }
                    }
                } else if (var4.itemID == Block.workbench.blockID) {
                    this.thePlayer.inventory.addItemStackToInventoryOrDropIt(BlockWorkbench.getBlockComponent(var4.getItemSubtype()));
                } else if (var4.itemID == Blocks.woodbench.blockID) {
                    this.thePlayer.inventory.addItemStackToInventoryOrDropIt(BlockWoodbench.getBlockComponent(var4.getItemSubtype()));
                }
            }
        }

        if (xp_reclaimed > 0) {
            par1EntityPlayer.addExperience(xp_reclaimed, true, false);
        }

    }
    @Shadow
    public CraftingResult crafting_result;
    @Shadow
    @Final
    private IInventory craftMatrix;
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
        } else if (par1ItemStack.itemID == Block.workbench.blockID){
            Material tool_material = BlockWorkbench.getToolMaterial(par1ItemStack.getItemSubtype());
            if (tool_material.isMetal()) {
                this.thePlayer.addStat(AchievementList.betterTools, 1);
            } else {
                this.thePlayer.addStat(AchievementList.buildWorkBench, 1);
            }
        } else if (par1ItemStack.itemID == Blocks.woodbench.blockID){
            this.thePlayer.addStat(AchievementList.buildWorkBench, 1);
        } else if (par1ItemStack.itemID == Blocks.metalbench.blockID){
            this.thePlayer.addStat(AchievementList.betterTools, 1);
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