package net.oilcake.mitelros.block;

import java.lang.reflect.Field;
import net.minecraft.Block;
import net.minecraft.BlockAnvil;
import net.minecraft.BlockConstants;
import net.minecraft.BlockDoor;
import net.minecraft.BlockOre;
import net.minecraft.BlockOreStorage;
import net.minecraft.BlockPane;
import net.minecraft.CreativeTabs;
import net.minecraft.FurnaceRecipes;
import net.minecraft.Item;
import net.minecraft.ItemAnvilBlock;
import net.minecraft.ItemBlock;
import net.minecraft.ItemIngot;
import net.minecraft.ItemNugget;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.oilcake.mitelros.block.observer.BlockObserver;
import net.oilcake.mitelros.block.receiver.BlockReceiver;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.Constant;
import net.xiaoyu233.fml.reload.event.ItemRegistryEvent;
import net.xiaoyu233.fml.reload.event.RecipeRegistryEvent;
import net.xiaoyu233.fml.reload.utils.IdUtil;
import net.xiaoyu233.fml.util.ReflectHelper;

public class Blocks extends Block {
   public static final Block blastFurnaceStoneIdle;
   public static final Block blastFurnaceObsidianIdle;
   public static final Block blastFurnaceNetherrackIdle;
   public static final Block blastFurnaceStoneBurning;
   public static final Block blastFurnaceObsidianBurning;
   public static final Block blastFurnaceNetherrackBurning;
   public static final Block blockSmokerIdle;
   public static final Block blockSmokerBurning;
   public static final BlockAnvil anvilNickel;
   public static final Block blockEnchantReserver;
   public static final Block blockNickel;
   public static final Block fenceNickel;
   public static final Block doorNickel;
   public static final Block oreNickel;
   public static final Block oreTungsten;
   public static final Block blockTungsten;
   public static final Block fenceTungsten;
   public static final Block doorTungsten;
   public static final BlockAnvil anvilTungsten;
   public static final BlockFlowerExtend flowerextend;
   public static final Block blockEnchantEnhancer;
   public static final Block oreUru;
   public static final Block beetroots;
   public static final Block beetrootsDead;
   public static final Block flowerPotExtend;
   public static final Block blockAzurite;
   public static final Block azuriteCluster;
   public static final Block torchWoodIdle;
   public static final Block torchWoodExtinguished;
   public static final Block blockObserver;
   public static final Block blockReceiver;
   public static final Block blockSulphur;
   public static final Block blockAzuriteLantern;
   public static final Block invisibleLight;
   public static final Block metalbench;
   public static final Block woodbench;

   protected Blocks(int par1, Material par2Material, BlockConstants constants) {
      super(par1, par2Material, constants);
   }

   public static void registerBlocks(ItemRegistryEvent registryEvent) {
      anvilNickel.stepSound = Block.soundAnvilFootstep;
      registryEvent.registerAnvil("nickel_anvil", "nickel_anvil", anvilNickel);
      anvilTungsten.stepSound = Block.soundAnvilFootstep;
      registryEvent.registerAnvil("tungsten_anvil", "tungsten_anvil", anvilTungsten);

      registryEvent.registerItemBlock("blastfurnace_stone_idle", "blastfurnace_stone_idle", blastFurnaceStoneIdle);
      registryEvent.registerItemBlock("blastfurnace_obsidian_idle", "blastfurnace_obsidian_idle", blastFurnaceObsidianIdle);
      registryEvent.registerItemBlock("blastfurnace_netherrack_idle", "blastfurnace_netherrack_idle", blastFurnaceNetherrackIdle);
      registryEvent.registerItemBlock("blastfurnace_stone_burning", "blastfurnace_stone_burning", blastFurnaceStoneBurning);
      registryEvent.registerItemBlock("blastfurnace_obsidian_burning", "blastfurnace_obsidian_burning", blastFurnaceObsidianBurning);
      registryEvent.registerItemBlock("blastfurnace_netherrack_burning", "blastfurnace_netherrack_burning", blastFurnaceNetherrackBurning);
      registryEvent.registerItemBlock("block_enchant_reserver", "block_enchant_reserver", blockEnchantReserver);
      registryEvent.registerItemBlock("block_observer", "block_observer", blockObserver);
      registryEvent.registerItemBlock("block_receiver", "block_receiver", blockReceiver);
      registryEvent.registerItemBlock("ore/nickel_ore", "ore/nickel_ore", oreNickel);
      registryEvent.registerItemBlock("block/nickel_block", "block/nickel_block", blockNickel);
      registryEvent.registerItemBlock("bars/nickel_bars", "bars/nickel_bars", fenceNickel);
      registryEvent.registerItemBlock("door/door_nickel", "door/door_nickel", doorNickel);
      registryEvent.registerItemBlock("block_smoker_idle", "block_smoker_idle", blockSmokerIdle);
      registryEvent.registerItemBlock("block_smoker_burning", "block_smoker_burning", blockSmokerBurning);
      registryEvent.registerItemBlock("ore/tungsten_ore", "ore/tungsten_ore", oreTungsten);
      registryEvent.registerItemBlock("block/tungsten_block", "block/tungsten_block", blockTungsten);
      registryEvent.registerItemBlock("bars/tungsten_bars", "bars/tungsten_bars", fenceTungsten);
      registryEvent.registerItemBlock("door/door_tungsten", "door/door_tungsten", doorTungsten);
      registryEvent.registerItemBlock("flowers/", "flowers/", flowerextend);
      registryEvent.registerItemBlock("block_enchant_enhancer", "block_enchant_enhancer", blockEnchantEnhancer);
      registryEvent.registerItemBlock("ore/uru_ore", "ore/uru_ore", oreUru);
      registryEvent.registerItemBlock("beetroot", "beetroot", beetroots);
      registryEvent.registerItemBlock("beetroot", "beetroot", beetrootsDead);
      registryEvent.registerItemBlock("flower_pot", "flower_pot", flowerPotExtend);
      registryEvent.registerItemBlock("azurite_block", "azurite_block", blockAzurite);
      registryEvent.registerItemBlock("azurite_cluster", "azurite_cluster", azuriteCluster);
      registryEvent.registerItemBlock("torch_idle", "torch_idle", torchWoodIdle);
      registryEvent.registerItemBlock("torch_off", "torch_off", torchWoodExtinguished);
      registryEvent.registerItemBlock("sulphur", "sulphur", blockSulphur);
      registryEvent.registerItemBlock("lantern", "lantern", blockAzuriteLantern);
      registryEvent.registerItemBlock("invisible", "invisible", invisibleLight);
      registryEvent.registerItemBlock("toolbench", "toolbench", metalbench);
      registryEvent.registerItemBlock("toolbench", "toolbench", woodbench);
   }

   public static void registerRecipes(RecipeRegistryEvent register) {
      register.registerShapedRecipe(new ItemStack(blockSmokerIdle), true, " A ", "ABA", " A ", 'A', Block.wood, 'B', Block.furnaceIdle);
      register.registerShapedRecipe(new ItemStack(fenceNickel, 16), true, "AAA", "AAA", 'A', Items.nickelIngot);
      register.registerShapelessRecipe(new ItemStack(blockNickel), true, Items.nickelIngot, Items.nickelIngot, Items.nickelIngot, Items.nickelIngot, Items.nickelIngot, Items.nickelIngot, Items.nickelIngot, Items.nickelIngot, Items.nickelIngot);
      register.registerShapedRecipe(new ItemStack(blockEnchantReserver), true, "CBC", "ABA", "CAC", 'A', Block.obsidian, 'B', Item.diamond, 'C', Item.ingotAncientMetal);
      register.registerShapedRecipe(new ItemStack(blockEnchantEnhancer), true, "CAC", "DBD", "AAA", 'A', Block.obsidian, 'B', Item.diamond, 'C', Item.ingotAncientMetal, 'D', Item.expBottle);
      register.registerShapedRecipe(new ItemStack(blastFurnaceStoneIdle), true, "AAA", "ABA", "CCC", 'A', Block.stone, 'B', Block.furnaceIdle, 'C', Item.ingotIron);
      register.registerShapedRecipe(new ItemStack(blastFurnaceObsidianIdle), true, "AAA", "ABA", "CCC", 'A', Block.obsidian, 'B', Block.furnaceObsidianIdle, 'C', Item.ingotMithril);
      register.registerShapedRecipe(new ItemStack(blastFurnaceNetherrackIdle), true, "AAA", "ABA", "CCC", 'A', Block.netherrack, 'B', Block.furnaceNetherrackIdle, 'C', Item.ingotAdamantium);
      register.registerShapedRecipe(new ItemStack(anvilNickel), true, "AAA", " I ", "III", 'A', blockNickel, 'I', Items.nickelIngot);
      register.registerShapedRecipe(new ItemStack(anvilTungsten), true, "AAA", " I ", "III", 'A', blockTungsten, 'I', Items.tungstenIngot);
      register.registerShapedRecipe(new ItemStack(blockTungsten), true, "XXX", "XXX", "XXX", 'X', Items.tungstenIngot);
      register.registerShapedRecipe(new ItemStack(fenceTungsten, 16), true, "XXX", "XXX", 'X', Items.tungstenIngot);
      register.registerShapedRecipe(new ItemStack(azuriteCluster), true, "EE", 'E', Items.shardAzurite);
      register.registerShapedRecipe(new ItemStack(blockAzurite), true, "XXX", "XXX", "XXX", 'X', Items.shardAzurite);
      register.registerShapedRecipe(new ItemStack(blockObserver), true, "XXX", "ABS", "XXX", 'X', Block.cobblestone, 'A', Item.netherQuartz, 'B', Item.redstone, 'S', Items.shardAzurite);
      register.registerShapedRecipe(new ItemStack(blockReceiver), true, "XSX", "SBS", "XSX", 'X', Block.cobblestone, 'S', Items.shardAzurite, 'B', Item.redstone);

      int bench_subtype;
      int i;
      for(bench_subtype = 0; bench_subtype < 4; ++bench_subtype) {
         for(i = 0; i < metalbench.getNumSubBlocks(); ++i) {
            Material tool_material = BlockMetalbench.getToolMaterial(i);
            register.registerShapedRecipe(new ItemStack(metalbench, 1, i), true, "IL", "s#", 'I', ItemIngot.getMatchingItem(ItemIngot.class, tool_material), 'L', Item.leather, 's', Item.stick, '#', new ItemStack(Block.planks, 1, bench_subtype));
         }
      }

      for(bench_subtype = 0; bench_subtype < woodbench.getNumSubBlocks(); ++bench_subtype) {
         register.registerShapedRecipe(new ItemStack(woodbench, 1, bench_subtype), true, "IL", "s#", 'I', bench_subtype < 4 ? Item.flint : Block.obsidian, 'L', Item.sinew, 's', Item.stick, '#', new ItemStack(Block.wood, 1, bench_subtype % 4));
         register.registerShapedRecipe(new ItemStack(woodbench, 1, bench_subtype), true, "IL", "s#", 'I', bench_subtype < 4 ? Item.flint : Block.obsidian, 'L', Item.silk, 's', Item.stick, '#', new ItemStack(Block.wood, 1, bench_subtype % 4));
         register.registerShapedRecipe(new ItemStack(woodbench, 1, bench_subtype), true, "I", "#", 'I', bench_subtype < 4 ? Item.knifeFlint : Item.knifeObsidian, '#', new ItemStack(Block.wood, 1, bench_subtype % 4));
         register.registerShapelessRecipe(new ItemStack(bench_subtype < 4 ? Item.knifeFlint : Item.knifeObsidian, 1), false, new ItemStack(woodbench, 1, bench_subtype));
      }

      ItemNugget[] nuggets = new ItemNugget[]{Item.copperNugget, Item.silverNugget, Item.goldNugget, Item.ironNugget, Items.nickelNugget, Item.ancientMetalNugget, Item.mithrilNugget, Items.tungstenNugget, Item.adamantiumNugget};

      for(i = 0; i < nuggets.length; ++i) {
         for(int j = 1; j <= 4; ++j) {
            register.registerShapelessRecipe(new ItemStack(blockAzuriteLantern, j, i), true, new ItemStack(nuggets[i].itemID, j), new ItemStack(Items.shardAzurite, j));
         }
      }

      FurnaceRecipes.smelting().addSmelting(oreTungsten.blockID, new ItemStack(Items.tungstenIngot));
      FurnaceRecipes.smelting().addSmelting(oreNickel.blockID, new ItemStack(Items.nickelIngot));
      FurnaceRecipes.smelting().addSmelting(oreUru.blockID, new ItemStack(Items.UruIngot));
   }
   
   static {
      blastFurnaceStoneIdle = (new BlockBlastFurnace(IdUtil.getNextBlockID(), Material.stone, false)).setCreativeTab(CreativeTabs.tabDecorations).setHardness(4.8F).setStepSound(Block.soundStoneFootstep);
      blastFurnaceObsidianIdle = (new BlockBlastFurnace(IdUtil.getNextBlockID(), Material.obsidian, false)).setCreativeTab(CreativeTabs.tabDecorations).setHardness(38.4F).setStepSound(Block.soundStoneFootstep);
      blastFurnaceNetherrackIdle = (new BlockBlastFurnace(IdUtil.getNextBlockID(), Material.netherrack, false)).setCreativeTab(CreativeTabs.tabDecorations).setHardness(153.6F).setStepSound(Block.soundStoneFootstep);
      blastFurnaceStoneBurning = (new BlockBlastFurnace(IdUtil.getNextBlockID(), Material.stone, true)).setHardness(4.8F).setStepSound(Block.soundStoneFootstep).setLightValue(0.875F);
      blastFurnaceObsidianBurning = (new BlockBlastFurnace(IdUtil.getNextBlockID(), Material.obsidian, true)).setHardness(38.4F).setStepSound(Block.soundStoneFootstep).setLightValue(0.875F);
      blastFurnaceNetherrackBurning = (new BlockBlastFurnace(IdUtil.getNextBlockID(), Material.netherrack, true)).setHardness(153.6F).setStepSound(Block.soundStoneFootstep).setLightValue(0.875F);
      blockSmokerIdle = (new BlockSmoker(IdUtil.getNextBlockID(), false)).setHardness(2.0F).setStepSound(Block.soundStoneFootstep).setCreativeTab(CreativeTabs.tabDecorations);
      blockSmokerBurning = (new BlockSmoker(IdUtil.getNextBlockID(), true)).setHardness(2.0F).setStepSound(Block.soundStoneFootstep).setLightValue(0.875F);
      anvilNickel = (BlockAnvil)ReflectHelper.createInstance(BlockAnvil.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextBlockID(), Materials.nickel});
      blockEnchantReserver = (new BlockEnchantReserver(IdUtil.getNextBlockID())).setHardness(8.0F).setStepSound(Block.soundStoneFootstep);
      blockNickel = (new BlockOreBlockExtend(IdUtil.getNextBlockID(), Materials.nickel)).setStepSound(Block.soundMetalFootstep);
      fenceNickel = ((BlockPane)ReflectHelper.createInstance(BlockPane.class, new Class[]{Integer.TYPE, String.class, String.class, Material.class, Boolean.TYPE}, new Object[]{IdUtil.getNextBlockID(), "bars/nickel_bars", "bars/nickel_bars", Materials.nickel, false})).setStepSound(soundMetalFootstep).setHardness(3.2F).setMinHarvestLevel(3);
      doorNickel = ((BlockDoor)ReflectHelper.createInstance(BlockDoor.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextBlockID(), Materials.nickel})).setStepSound(soundMetalFootstep).setMinHarvestLevel(3);
      oreNickel = (new BlockOre(IdUtil.getNextBlockID(), Materials.nickel, 2)).setHardness(3.0F);
      oreTungsten = (new BlockOre(IdUtil.getNextBlockID(), Materials.tungsten, 4)).setHardness(3.5F);
      blockTungsten = (new BlockOreStorage(IdUtil.getNextBlockID(), Materials.tungsten)).setStepSound(Block.soundMetalFootstep);
      fenceTungsten = ((BlockPane)ReflectHelper.createInstance(BlockPane.class, new Class[]{Integer.TYPE, String.class, String.class, Material.class, Boolean.TYPE}, new Object[]{IdUtil.getNextBlockID(), "bars/tungsten_bars", "bars/tungsten_bars", Materials.tungsten, false})).setStepSound(soundMetalFootstep).setHardness(51.2F).setMinHarvestLevel(5);
      doorTungsten = ((BlockDoor)ReflectHelper.createInstance(BlockDoor.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextBlockID(), Materials.tungsten})).setStepSound(soundMetalFootstep).setMinHarvestLevel(5);
      anvilTungsten = (BlockAnvil)ReflectHelper.createInstance(BlockAnvil.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextBlockID(), Materials.tungsten});
      flowerextend = (BlockFlowerExtend)(new BlockFlowerExtend(IdUtil.getNextBlockID())).setMaxStackSize(32);
      blockEnchantEnhancer = (new BlockEnchantEnhancer(IdUtil.getNextBlockID())).setHardness(8.0F).setStepSound(Block.soundStoneFootstep);
      oreUru = (new BlockOre(IdUtil.getNextBlockID(), Materials.uru, 4)).setHardness(5.0F);
      beetroots = (new BlockBeetroots(IdUtil.getNextBlockID())).setUnlocalizedName("beetroot");
      beetrootsDead = (new BlockBeetrootsDead(IdUtil.getNextBlockID())).setUnlocalizedName("beetroot");
      flowerPotExtend = (new BlockFlowerPotExtend(IdUtil.getNextBlockID())).setHardness(0.0F).setStepSound(soundPowderFootstep).setUnlocalizedName("flowerPot");
      blockAzurite = (new BlockGrowableOre(IdUtil.getNextBlockID(), Materials.crystal, 2)).setStepSound(soundGlassFootstep).setHardness(1.2F).setLightValue(0.9375F);
      azuriteCluster = (new BlockCaveMisc(IdUtil.getNextBlockID(), Materials.crystal)).setLightValue(0.75F).setHardness(0.6F).setMinHarvestLevel(1).setStepSound(soundGlassFootstep);
      torchWoodIdle = (new BlockTorchIdle(IdUtil.getNextBlockID())).setHardness(0.0F).setLightValue(0.5F).setStepSound(soundWoodFootstep).setUnlocalizedName("torch");
      torchWoodExtinguished = (new BlockTorchIdle(IdUtil.getNextBlockID())).setHardness(0.0F).setLightValue(0.0F).setStepSound(soundWoodFootstep).setUnlocalizedName("torch");
      blockObserver = (new BlockObserver(IdUtil.getNextBlockID(), Material.stone)).setHardness(2.5F).setStepSound(Block.soundStoneFootstep);
      blockReceiver = (new BlockReceiver(IdUtil.getNextBlockID())).setHardness(2.5F).setStepSound(Block.soundStoneFootstep);
      blockSulphur = (new BlockOre(IdUtil.getNextBlockID(), Materials.sulphur, 1)).setHardness(1.2F).setStepSound(Block.soundStoneFootstep);
      blockAzuriteLantern = ((BlockAzuriteLantern)ReflectHelper.createInstance(BlockAzuriteLantern.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextBlockID(), Materials.circuits})).setUnlocalizedName("lantern").setStepSound(Block.soundMetalFootstep);
      invisibleLight = (new BlockInvisibleLight(IdUtil.getNextBlockID())).setLightValue(0.75F);
      metalbench = (new BlockMetalbench(IdUtil.getNextBlockID())).setStepSound(soundWoodFootstep).setUnlocalizedName("workbench");
      woodbench = (new BlockWoodbench(IdUtil.getNextBlockID())).setStepSound(soundWoodFootstep).setUnlocalizedName("workbench");

      try {
         Field field = Block.class.getDeclaredField("is_normal_cube_lookup");
         field.setAccessible(true);
         Field modifiers = field.getClass().getDeclaredField("modifiers");
         modifiers.setAccessible(true);
         modifiers.setInt(field, field.getModifiers() & -17);
         field.set((Object)null, new boolean[4096]);
         boolean[] is_normal_block = (boolean[])((boolean[])field.get((Object)null));
         Block[] var3 = Block.blocksList;
         int var4 = var3.length;

          for (Block block : var3) {
              if (block != null) {
                  is_normal_block[block.blockID] = block.is_normal_cube;
              }
          }

         modifiers.setInt(field, field.getModifiers() & -17);
      } catch (NoSuchFieldException | IllegalAccessException var7) {
         ReflectiveOperationException e = var7;
         e.printStackTrace();
      }

   }
}
