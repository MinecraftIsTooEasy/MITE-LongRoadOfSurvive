package net.oilcake.mitelros.block;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.util.RecipeRegister;

import java.util.Random;

import static net.xiaoyu233.fml.util.ReflectHelper.createInstance;


public class Blocks extends Block {
    public static final Block blastFurnaceStoneIdle = new BlockBlastFurnace(Constant.getNextBlockID(), Material.stone, false).setCreativeTab(CreativeModeTab.tabDecorations)
            .setBlockHardness(4.8F).setExplosionResistance(20.0f).setStepSound_(Block.soundStoneFootstep);
    public static final Block blastFurnaceObsidianIdle = new BlockBlastFurnace(Constant.getNextBlockID(), Material.obsidian, false).setCreativeTab(CreativeModeTab.tabDecorations)
            .setBlockHardness(38.4F).setExplosionResistance(40.0f).setStepSound_(Block.soundStoneFootstep);
    public static final Block blastFurnaceNetherrackIdle = new BlockBlastFurnace(Constant.getNextBlockID(), Material.netherrack, false).setCreativeTab(CreativeModeTab.tabDecorations)
            .setBlockHardness(153.6F).setExplosionResistance(80.0f).setStepSound_(Block.soundStoneFootstep);

    public static final Block blastFurnaceStoneBurning = new BlockBlastFurnace(Constant.getNextBlockID(), Material.stone, true)
            .setBlockHardness(4.8F).setExplosionResistance(20.0f).setStepSound_(Block.soundStoneFootstep);
    public static final Block blastFurnaceObsidianBurning = new BlockBlastFurnace(Constant.getNextBlockID(), Material.obsidian, true)
            .setBlockHardness(38.4F).setExplosionResistance(40.0f).setStepSound_(Block.soundStoneFootstep);
    public static final Block blastFurnaceNetherrackBurning = new BlockBlastFurnace(Constant.getNextBlockID(), Material.netherrack, true)
            .setBlockHardness(153.6F).setExplosionResistance(80.0f).setStepSound_(Block.soundStoneFootstep);
    public static final Block blockSmokerIdle = new BlockSmoker(Constant.getNextBlockID(), false)
            .setBlockHardness(2.0F).setExplosionResistance(20.0f).setStepSound_(Block.soundStoneFootstep);
    public static final Block blockSmokerBurning = new BlockSmoker(Constant.getNextBlockID(), true)
            .setBlockHardness(2.0F).setExplosionResistance(20.0f).setStepSound_(Block.soundStoneFootstep);
    public static final BlockAnvil anvilNickel = createInstance(BlockAnvil.class, new Class[]{int.class, Material.class}, Constant.getNextBlockID(), Materials.nickel);
    public static final Block blockEnchantReserver = new BlockEnchantReserver(Constant.getNextBlockID())
            .setBlockHardness(8.0F).setExplosionResistance(20.0f).setStepSound_(Block.soundStoneFootstep);
    public static final BlockOreBlock blockNickel = new BlockOreBlock(Constant.getNextBlockID(), Materials.nickel);
    public static final Block fenceNickel = createInstance(BlockThinFence.class, new Class[] {int.class, String.class, String.class, Material.class, boolean.class}
            , Constant.getNextBlockID(), "bars/nickel_bars", "bars/nickel_bars", Materials.nickel, false).setStepSound_(soundMetalFootstep).setExplosionResistance(6.0f).setBlockHardness(3.2F);
    public static final Block doorNickel = createInstance(BlockDoor.class, new Class[] {int.class, Material.class}
            , Constant.getNextBlockID(), Materials.nickel).setStepSound_(soundMetalFootstep);

    public static final Block oreNickel = new BlockOre(Constant.getNextBlockID(), Materials.nickel, 1).setBlockHardness(3.0F).setExplosionResistance(20.0f);
    public static final Block oreTungsten = new BlockOre(Constant.getNextBlockID(), Materials.tungsten, 3).setBlockHardness(3.5F).setExplosionResistance(30.0f);
    public static final BlockOreBlock blockTungsten = new BlockOreBlock(Constant.getNextBlockID(),Materials.tungsten);
    public static final Block fenceTungsten = createInstance(BlockThinFence.class, new Class[] {int.class, String.class, String.class, Material.class, boolean.class}
            , Constant.getNextBlockID(), "bars/tungsten_bars", "bars/tungsten_bars", Materials.tungsten, false).setStepSound_(soundMetalFootstep).setExplosionResistance(96.0f).setBlockHardness(51.2F);
    public static final Block doorTungsten = createInstance(BlockDoor.class, new Class[] {int.class,Material.class}
            , Constant.getNextBlockID(), Materials.tungsten).setStepSound_(soundMetalFootstep);

    public static final BlockAnvil anvilTungsten = createInstance(BlockAnvil.class, new Class[]{int.class, Material.class}, Constant.getNextBlockID(), Materials.tungsten);
    public static final Block flowerextend = new BlockFlowerExtend(Constant.getNextBlockID());



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
        registerAnvil("tungsten_anvil", anvilTungsten);
        anvilTungsten.stepSound = Block.soundAnvilFootstep;
        registerItemBlock("blastfurnace_stone_idle", blastFurnaceStoneIdle);
        registerItemBlock("blastfurnace_obsidian_idle", blastFurnaceObsidianIdle);
        registerItemBlock("blastfurnace_netherrack_idle", blastFurnaceNetherrackIdle);
        registerItemBlock("blastfurnace_stone_burning", blastFurnaceStoneBurning);
        registerItemBlock("blastfurnace_obsidian_burning", blastFurnaceObsidianBurning);
        registerItemBlock("blastfurnace_netherrack_burning", blastFurnaceNetherrackBurning);
        registerItemBlock("block_enchant_reserver", blockEnchantReserver);
        registerItemBlock("ore/nickel_ore", oreNickel);
        registerItemBlock("block/nickel_block", blockNickel);
        registerItemBlock("bars/nickel_bars", fenceNickel);
        registerItemBlock("door/door_nickel", doorNickel);
        registerItemBlock("block_smoker_idle", blockSmokerIdle);
        registerItemBlock("block_smoker_burning", blockSmokerBurning);
        registerItemBlock("ore/tungsten_ore", oreTungsten);
        registerItemBlock("block/tungsten_block", blockTungsten);
        registerItemBlock("bars/tungsten_bars", fenceTungsten);
        registerItemBlock("door/door_tungsten", doorTungsten);
        registerItemBlock("flowers/" , flowerextend);
    }

    public static void registerRecipes(RecipeRegister register) {
        register.registerShapedRecipe(new ItemStack(blockSmokerIdle), true,
                " A ",
                "ABA",
                " A ",
                'A', Block.wood,
                'B', Block.furnaceIdle);
        register.registerShapedRecipe(new ItemStack(fenceNickel, 16), true,
                "   ",
                "AAA",
                "AAA",
                'A', Items.nickelIngot);
        register.registerShapelessRecipe(new ItemStack(blockNickel), true,
                Items.nickelIngot, Items.nickelIngot, Items.nickelIngot,
                Items.nickelIngot, Items.nickelIngot, Items.nickelIngot,
                Items.nickelIngot, Items.nickelIngot, Items.nickelIngot);

        register.registerShapedRecipe(new ItemStack(blockEnchantReserver), true,
                "CBC",
                "ABA",
                "CAC",
                'A', Block.obsidian, 'B', Item.diamond, 'C', Item.ingotAncientMetal);
        register.registerShapedRecipe(new ItemStack(blastFurnaceStoneIdle), true,
                "AAA",
                "ABA",
                "CCC",
                'A', Block.stone, 'B', Block.furnaceIdle, 'C', Item.ingotIron);
        register.registerShapedRecipe(new ItemStack(blastFurnaceObsidianIdle), true,
                "AAA",
                "ABA",
                "CCC",
                'A', Block.obsidian, 'B', Block.furnaceObsidianIdle, 'C', Item.ingotMithril);
        register.registerShapedRecipe(new ItemStack(blastFurnaceNetherrackIdle), true,
                "AAA",
                "ABA",
                "CCC",
                'A', Block.netherrack, 'B', Block.furnaceNetherrackIdle, 'C', Item.ingotAdamantium);
        register.registerShapedRecipe(new ItemStack(anvilNickel),true,
                "AAA",
                " I ",
                "III",
                'A', blockNickel,
                'I', Items.nickelIngot);
        register.registerShapedRecipe(new ItemStack(anvilTungsten),true,
                "AAA",
                " I ",
                "III",
                'A', blockTungsten,
                'I', Items.tungstenIngot);
        register.registerShapedRecipe(new ItemStack(blockTungsten),true,
                "XXX",
                "XXX",
                "XXX",
                'X',Items.tungstenIngot);
        register.registerShapedRecipe(new ItemStack(fenceTungsten, 16),true,
                "XXX",
                "XXX",
                "   ",
                'X',Items.tungstenIngot);
//        register.registerShapedRecipe(new ItemStack(doorTungsten),true,
//                "XX ",
//                "XX ",
//                "XX ",
//                'X',Items.tungstenIngot);
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder,1,14),false,
                new ItemStack(Blocks.flowerextend,1,0));
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder,1,7),false,
                new ItemStack(Blocks.flowerextend,1,1));
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder,1,12),false,
                new ItemStack(Blocks.flowerextend,1,2));
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder,1,7),false,
                new ItemStack(Blocks.flowerextend,1,3));
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder,1,9),false,
                new ItemStack(Blocks.flowerextend,1,4));
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder,1,7),false,
                new ItemStack(Blocks.flowerextend,1,5));
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder,1,1),false,
                new ItemStack(Blocks.flowerextend,1,6));

        RecipesFurnace.smelting().addSmelting(oreTungsten.blockID, new ItemStack(Items.tungstenIngot));
        RecipesFurnace.smelting().addSmelting(oreNickel.blockID, new ItemStack(Items.nickelIngot));
    }



    protected Block setTextureName(String par1Str)
    {
        this.textureName = par1Str;
        return this;
    }

}