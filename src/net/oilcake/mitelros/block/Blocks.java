package net.oilcake.mitelros.block;

import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.util.RecipeRegister;
import net.oilcake.mitelros.util.ReflectHelper;
import net.minecraft.*;


public class Blocks extends Block {
    public static final Block blastFurnaceStoneIdle = new BlockBlastFurnace(getNextBlockID(), Material.stone, false).setCreativeTab(CreativeModeTab.tabDecorations)
            .setBlockHardness(8.0F).setExplosionResistance(0.875f).setStepSound_(Block.soundStoneFootstep);
    public static final Block blastFurnaceObsidianIdle = new BlockBlastFurnace(getNextBlockID(), Material.obsidian, false).setCreativeTab(CreativeModeTab.tabDecorations)
            .setBlockHardness(8.0F).setExplosionResistance(0.875f).setStepSound_(Block.soundStoneFootstep);
    public static final Block blastFurnaceNetherrackIdle = new BlockBlastFurnace(getNextBlockID(), Material.netherrack, false).setCreativeTab(CreativeModeTab.tabDecorations)
            .setBlockHardness(8.0F).setExplosionResistance(0.875f).setStepSound_(Block.soundStoneFootstep);

    public static final Block blastFurnaceStoneBurning = new BlockBlastFurnace(getNextBlockID(), Material.stone, true)
            .setBlockHardness(8.0F).setExplosionResistance(0.875f).setStepSound_(Block.soundStoneFootstep);
    public static final Block blastFurnaceObsidianBurning = new BlockBlastFurnace(getNextBlockID(), Material.obsidian, true)
            .setBlockHardness(8.0F).setExplosionResistance(0.875f).setStepSound_(Block.soundStoneFootstep);
    public static final Block blastFurnaceNetherrackBurning = new BlockBlastFurnace(getNextBlockID(), Material.netherrack, true)
            .setBlockHardness(8.0F).setExplosionResistance(0.875f).setStepSound_(Block.soundStoneFootstep);
    public static final Block blockSmokerIdle = new BlockSmoker(getNextBlockID(), false)
            .setBlockHardness(8.0F).setExplosionResistance(0.875f).setStepSound_(Block.soundStoneFootstep);
    public static final Block blockSmokerBurning = new BlockSmoker(getNextBlockID(), true)
            .setBlockHardness(8.0F).setExplosionResistance(0.875f).setStepSound_(Block.soundStoneFootstep);
    public static final BlockAnvil anvilNickel = ReflectHelper.createInstance(BlockAnvil.class, new Class[]{int.class, Material.class}, getNextBlockID(), Materials.nickel);
    public static final Block blockEnchantReserver = new BlockEnchantReserver(getNextBlockID())
            .setBlockHardness(8.0F).setExplosionResistance(0.875f).setStepSound_(Block.soundStoneFootstep);
    public static final BlockOreBlock blockNickel = new BlockOreBlock(getNextBlockID(), Materials.nickel);
    public static final Block fenceNickel = new NewBlockThinFence(getNextBlockID(), Materials.nickel);
    public static final Block doorNickel = ReflectHelper.createInstance(BlockDoor.class, new Class[] {int.class,Material.class}
            , getNextBlockID(), Materials.nickel).setStepSound_(soundMetalFootstep);

    public static final Block blockNickelOre = new BlockOre(getNextBlockID(), Materials.nickel, 3);




    protected Blocks(int par1, Material par2Material, BlockConstants constants) {
        super(par1, par2Material, constants);
    }

    private static void registerAnvil(String resourceLocation, BlockAnvil block){
        block.setUnlocalizedName(resourceLocation);
        block.setResourceLocation(resourceLocation);
        Item item = new ItemAnvil(block).setUnlocalizedName(resourceLocation);
        Item.itemsList[Constant.getNextItemID()] = item;
        item.setMaxStackSize(block.getItemStackLimit());
    }
    private static void registerItemBlock(String resourceLocation, Block block){
        block.setUnlocalizedName(resourceLocation);
        block.setResourceLocation(resourceLocation);
        Item item = new ItemBlock(block).setUnlocalizedName(resourceLocation);
        item.setMaxStackSize(block.getItemStackLimit());
        Item.itemsList[Constant.getNextItemID()] = item;
    }
    public static void registerBlocks() {
        registerAnvil("nickel_anvil", anvilNickel);
        anvilNickel.stepSound = Block.soundAnvilFootstep;
        registerItemBlock("blastfurnace_stone_idle", blastFurnaceStoneIdle);
        registerItemBlock("blastfurnace_obsidian_idle", blastFurnaceObsidianIdle);
        registerItemBlock("blastfurnace_netherrack_idle", blastFurnaceNetherrackIdle);
        registerItemBlock("blastfurnace_stone_burning", blastFurnaceStoneBurning);
        registerItemBlock("blastfurnace_obsidian_burning", blastFurnaceObsidianBurning);
        registerItemBlock("blastfurnace_netherrack_burning", blastFurnaceNetherrackBurning);
        registerItemBlock("block_enchant_reserver", blockEnchantReserver);
        registerItemBlock("ore/nickel_ore", blockNickelOre);
        registerItemBlock("block/nickel_block", blockNickel);
        registerItemBlock("bars/nickel_bars", fenceNickel);
        registerItemBlock("door/door_nickel", doorNickel);
        registerItemBlock("block_smoker_idle", blockSmokerIdle);
        registerItemBlock("block_smoker_burning", blockSmokerBurning);
    }

    public static void registerRecipes(RecipeRegister register) {
        register.registerShapedRecipe(new ItemStack(blockSmokerBurning), false,
                "CDC",
                "AEA",
                "BBB",
                'A', Block.wood,
                'B', Block.cobblestone,
                'C', Item.stick,
                'D', Block.blockIron,
                'E', Block.furnaceIdle);
        register.registerShapedRecipe(new ItemStack(fenceNickel), false,
                "   ",
                "AAA",
                "AAA",
                'A', Items.nickelIngot);
        register.registerShapelessRecipe(new ItemStack(blockNickel), false,
                Items.nickelIngot, Items.nickelIngot, Items.nickelIngot,
                Items.nickelIngot, Items.nickelIngot, Items.nickelIngot,
                Items.nickelIngot, Items.nickelIngot, Items.nickelIngot);

        register.registerShapedRecipe(new ItemStack(blockEnchantReserver), false,
                "CBC",
                "ABA",
                "CAC",
                'A', Block.obsidian, 'B', Item.diamond, 'C', Item.ingotAncientMetal);
        register.registerShapedRecipe(new ItemStack(blastFurnaceStoneIdle), false,
                "AAA",
                "ABA",
                "CCC",
                'A', Block.stone, 'B', Block.furnaceIdle, 'C', Item.ingotIron);
        register.registerShapedRecipe(new ItemStack(blastFurnaceObsidianIdle), false,
                "AAA",
                "ABA",
                "CCC",
                'A', Block.obsidian, 'B', Block.furnaceObsidianIdle, 'C', Item.ingotMithril);
        register.registerShapedRecipe(new ItemStack(blastFurnaceNetherrackIdle), false,
                "AAA",
                "ABA",
                "CCC",
                'A', Block.netherrack, 'B', Block.furnaceNetherrackIdle, 'C', Item.ingotAdamantium);
        register.registerShapedRecipe(new ItemStack(anvilNickel),false,
                "AAA",
                " I ",
                "III",
                'A', blockNickel,
                'I', Items.nickelIngot);

        RecipesFurnace.smelting().addSmelting(blockNickelOre.blockID, new ItemStack(Items.nickelIngot));
    }

    public static int getNextBlockID() {
        return Constant.nextBlockID++;
    }
}
