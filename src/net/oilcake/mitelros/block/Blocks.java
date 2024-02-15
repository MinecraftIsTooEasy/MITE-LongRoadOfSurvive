package net.oilcake.mitelros.block;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.util.RecipeRegister;

import static net.xiaoyu233.fml.util.ReflectHelper.createInstance;


public class Blocks extends Block {
    public static final Block blastFurnaceStoneIdle = new BlockBlastFurnace(174, Material.stone, false).setCreativeTab(CreativeModeTab.tabDecorations)
            .setBlockHardness(4.8F).setExplosionResistance(20.0f).setStepSound_(Block.soundStoneFootstep);
    public static final Block blastFurnaceObsidianIdle = new BlockBlastFurnace(175, Material.obsidian, false).setCreativeTab(CreativeModeTab.tabDecorations)
            .setBlockHardness(38.4F).setExplosionResistance(40.0f).setStepSound_(Block.soundStoneFootstep);
    public static final Block blastFurnaceNetherrackIdle = new BlockBlastFurnace(176, Material.netherrack, false).setCreativeTab(CreativeModeTab.tabDecorations)
            .setBlockHardness(153.6F).setExplosionResistance(80.0f).setStepSound_(Block.soundStoneFootstep);

    public static final Block blastFurnaceStoneBurning = new BlockBlastFurnace(177, Material.stone, true)
            .setBlockHardness(4.8F).setExplosionResistance(20.0f).setStepSound_(Block.soundStoneFootstep).setBlockLightLevel(0.875F);
    public static final Block blastFurnaceObsidianBurning = new BlockBlastFurnace(178, Material.obsidian, true)
            .setBlockHardness(38.4F).setExplosionResistance(40.0f).setStepSound_(Block.soundStoneFootstep).setBlockLightLevel(0.875F);
    public static final Block blastFurnaceNetherrackBurning = new BlockBlastFurnace(179, Material.netherrack, true)
            .setBlockHardness(153.6F).setExplosionResistance(80.0f).setStepSound_(Block.soundStoneFootstep).setBlockLightLevel(0.875F);
    public static final Block blockSmokerIdle = new BlockSmoker(180, false)
            .setBlockHardness(2.0F).setExplosionResistance(20.0f).setStepSound_(Block.soundStoneFootstep);
    public static final Block blockSmokerBurning = new BlockSmoker(181, true)
            .setBlockHardness(2.0F).setExplosionResistance(20.0f).setStepSound_(Block.soundStoneFootstep).setBlockLightLevel(0.875F);
    public static final BlockAnvil anvilNickel = createInstance(BlockAnvil.class, new Class[]{int.class, Material.class}, 182, Materials.nickel);
    public static final Block blockEnchantReserver = new BlockEnchantReserver(183)
            .setBlockHardness(8.0F).setExplosionResistance(20.0f).setStepSound_(Block.soundStoneFootstep);
    public static final Block blockNickel = new BlockOreBlockExtend(184, Materials.nickel).setStepSound_(Block.soundMetalFootstep);
    public static final Block fenceNickel = createInstance(BlockThinFence.class, new Class[] {int.class, String.class, String.class, Material.class, boolean.class}
            , 185, "bars/nickel_bars", "bars/nickel_bars", Materials.nickel, false).setStepSound_(soundMetalFootstep).setExplosionResistance(6.0f).setBlockHardness(3.2F).setMinHarvestLevel(3);
    public static final Block doorNickel = createInstance(BlockDoor.class, new Class[] {int.class, Material.class}
            , 186, Materials.nickel).setStepSound_(soundMetalFootstep).setMinHarvestLevel(3);

    public static final Block oreNickel = new BlockOre(187, Materials.nickel, 2).setBlockHardness(3.0F).setExplosionResistance(20.0f);
    public static final Block oreTungsten = new BlockOre(188, Materials.tungsten, 4).setBlockHardness(3.5F).setExplosionResistance(30.0f);
    public static final Block blockTungsten = new BlockOreBlock(189,Materials.tungsten).setStepSound_(Block.soundMetalFootstep);;
    public static final Block fenceTungsten = createInstance(BlockThinFence.class, new Class[] {int.class, String.class, String.class, Material.class, boolean.class}
            , 190, "bars/tungsten_bars", "bars/tungsten_bars", Materials.tungsten, false).setStepSound_(soundMetalFootstep).setExplosionResistance(96.0f).setBlockHardness(51.2F).setMinHarvestLevel(5);
    public static final Block doorTungsten = createInstance(BlockDoor.class, new Class[] {int.class,Material.class}
            , 191, Materials.tungsten).setStepSound_(soundMetalFootstep).setMinHarvestLevel(5);

    public static final BlockAnvil anvilTungsten = createInstance(BlockAnvil.class, new Class[]{int.class, Material.class}, 192, Materials.tungsten);
    public static final BlockFlowerExtend flowerextend = (BlockFlowerExtend) new BlockFlowerExtend(193).setMaxStackSize(32);
    public static final Block blockEnchantEnhancer = new BlockEnchantEnhancer(194).setBlockHardness(8.0F).setExplosionResistance(20.0f).setStepSound_(Block.soundStoneFootstep);
    public static final Block oreUru = new BlockOre(195, Materials.uru, 4).setBlockHardness(5.0F).setExplosionResistance(150.0f);
    public static final Block beetroots = new BlockBeetroots(196).setUnlocalizedName("beetroot");
    public static final Block beetrootsDead = new BlockBeetrootsDead(197).setUnlocalizedName("beetroot");
    public static final Block flowerPotExtend = new BlockFlowerPotExtend(160).setBlockHardness(0.0F).setStepSound_(soundPowderFootstep).setUnlocalizedName("flowerPot");
//    public static final Block invisibleLight = new BlockInvisibleLight(160).setBlockLightLevel(0.5F);

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
        registerItemBlock("block_enchant_enhancer",blockEnchantEnhancer);
        registerItemBlock("ore/uru_ore",oreUru);
        registerItemBlock("beetroot",beetroots);
        registerItemBlock("beetroot",beetrootsDead);
        registerItemBlock("flower_pot",flowerPotExtend);
//        registerItemBlock("invisible",invisibleLight);

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
        register.registerShapedRecipe(new ItemStack(blockEnchantEnhancer), true,
                "CAC",
                "DBD",
                "AAA",
                'A', Block.obsidian, 'B', Item.diamond, 'C', Item.ingotAncientMetal,'D',Item.expBottle);
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
        register.registerShapelessRecipe(new ItemStack(Items.glowberries,1),true,
                new ItemStack(Blocks.flowerextend,1,0));
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder,1,7),true,
                new ItemStack(Blocks.flowerextend,1,1));
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder,1,4),true,
                new ItemStack(Blocks.flowerextend,1,2));
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder,1,7),true,
                new ItemStack(Blocks.flowerextend,1,3));
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder,1,9),true,
                new ItemStack(Blocks.flowerextend,1,4));
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder,1,7),true,
                new ItemStack(Blocks.flowerextend,1,5));
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder,1,1),true,
                new ItemStack(Blocks.flowerextend,1,6));
        register.registerShapelessRecipe(new ItemStack(Items.Agave,1,1),true,
                new ItemStack(Blocks.flowerextend,1,7));


        RecipesFurnace.smelting().addSmelting(oreTungsten.blockID, new ItemStack(Items.tungstenIngot));
        RecipesFurnace.smelting().addSmelting(oreNickel.blockID, new ItemStack(Items.nickelIngot));
        RecipesFurnace.smelting().addSmelting(oreUru.blockID, new ItemStack(Items.UruIngot));

    }



    protected Block setTextureName(String par1Str)
    {
        this.textureName = par1Str;
        return this;
    }

}