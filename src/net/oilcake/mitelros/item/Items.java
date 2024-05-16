package net.oilcake.mitelros.item;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.util.RecipeRegister;

import static net.xiaoyu233.fml.util.ReflectHelper.createInstance;

public class Items extends Item {
    public static final ItemArmor nickelHelmet = new ItemHelmet(Constant.getNextItemID(),Materials.nickel,false);
    public static final ItemArmor nickelChestplate = new ItemCuirass(Constant.getNextItemID(),Materials.nickel,false);
    public static final ItemArmor nickelLeggings = new ItemLeggings(Constant.getNextItemID(),Materials.nickel,false);
    public static final ItemArmor nickelBoots = new ItemBoots(Constant.getNextItemID(),Materials.nickel,false);
    public static final ItemArmor nickelHelmetChain = new ItemHelmet(Constant.getNextItemID(),Materials.nickel,true);
    public static final ItemArmor nickelChestplateChain = new ItemCuirass(Constant.getNextItemID(),Materials.nickel,true);
    public static final ItemArmor nickelLeggingsChain = new ItemLeggings(Constant.getNextItemID(),Materials.nickel,true);
    public static final ItemArmor nickelBootsChain = new ItemBoots(Constant.getNextItemID(),Materials.nickel,true);
    public static final ItemNugget nickelNugget = createInstance(ItemNugget.class, new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final ItemAxe nickelAxe = createInstance(ItemAxe.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final ItemBattleAxe nickelBattleAxe = createInstance(ItemBattleAxe.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final ItemDagger nickelDagger = createInstance(ItemDagger.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final Item nickelIngot = createInstance(ItemIngot.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel).setXPReward(15);
    public static final ItemPickaxe nickelPickaxe = createInstance(ItemPickaxe.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final ItemShovel nickelShovel = createInstance(ItemShovel.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final ItemSword nickelSword = createInstance(ItemSword.class,new Class[]{int.class,Material.class},Constant.getNextItemID(), Materials.nickel);
    public static final ItemWarHammer nickelWarHammer = createInstance(ItemWarHammer.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final ItemHatchet nickelHatchet = createInstance(ItemHatchet.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final ItemHoe nickelHoe = createInstance(ItemHoe.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final ItemKnife nickelKnife = createInstance(ItemKnife.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final ItemMattock nickelMattock = createInstance(ItemMattock.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final ItemScythe nickelScythe = createInstance(ItemScythe.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final ItemShears nickelShears = createInstance(ItemShears.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final Item doorNickel= new ItemDoor(Constant.getNextItemID(), Materials.nickel);
    public static final ItemChain nickelChain = createInstance(ItemChain.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final ItemCoin nickelCoin = createInstance(ItemCoin.class, new Class[]{int.class, Material.class}, Constant.getNextItemID(), Materials.nickel);
    public static final ItemArrow arrowNickel = new ItemArrow(Constant.getNextItemID(), Materials.nickel);
    public static final ItemArmor tungstenHelmet = new ItemHelmet(Constant.getNextItemID(),Materials.tungsten,false);
    public static final ItemArmor tungstenChestplate = new ItemCuirass(Constant.getNextItemID(),Materials.tungsten,false);
    public static final ItemArmor tungstenLeggings = new ItemLeggings(Constant.getNextItemID(),Materials.tungsten,false);
    public static final ItemArmor tungstenBoots = new ItemBoots(Constant.getNextItemID(),Materials.tungsten,false);
    public static final ItemArmor tungstenHelmetChain = new ItemHelmet(Constant.getNextItemID(),Materials.tungsten,true);
    public static final ItemArmor tungstenChestplateChain = new ItemCuirass(Constant.getNextItemID(),Materials.tungsten,true);
    public static final ItemArmor tungstenLeggingsChain = new ItemLeggings(Constant.getNextItemID(),Materials.tungsten,true);
    public static final ItemArmor tungstenBootsChain = new ItemBoots(Constant.getNextItemID(),Materials.tungsten,true);
    public static final ItemNugget tungstenNugget = createInstance(ItemNugget.class, new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.tungsten);
    public static final ItemAxe tungstenAxe = createInstance(ItemAxe.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.tungsten);
    public static final ItemBattleAxe tungstenBattleAxe = createInstance(ItemBattleAxe.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.tungsten);
    public static final ItemDagger tungstenDagger = createInstance(ItemDagger.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.tungsten);
    public static final Item tungstenIngot = createInstance(ItemIngot.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.tungsten).setXPReward(75);
    public static final ItemPickaxe tungstenPickaxe = createInstance(ItemPickaxe.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.tungsten);
    public static final ItemShovel tungstenShovel = createInstance(ItemShovel.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.tungsten);
    public static final ItemSword tungstenSword = createInstance(ItemSword.class,new Class[]{int.class,Material.class},Constant.getNextItemID(), Materials.tungsten);
    public static final ItemWarHammer tungstenWarHammer = createInstance(ItemWarHammer.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.tungsten);
    public static final ItemHatchet tungstenHatchet = createInstance(ItemHatchet.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.tungsten);
    public static final ItemHoe tungstenHoe = createInstance(ItemHoe.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.tungsten);
    public static final ItemKnife tungstenKnife = createInstance(ItemKnife.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.tungsten);
    public static final ItemMattock tungstenMattock = createInstance(ItemMattock.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.tungsten);
    public static final ItemScythe tungstenScythe = createInstance(ItemScythe.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.tungsten);
    public static final ItemShears tungstenShears = createInstance(ItemShears.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.tungsten);
    public static final Item doorTungsten= new ItemDoor(Constant.getNextItemID(), Materials.tungsten);
    public static final ItemChain tungstenChain = createInstance(ItemChain.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.tungsten);
    public static final ItemCoin tungstenCoin = createInstance(ItemCoin.class, new Class[]{int.class, Material.class}, Constant.getNextItemID(), Materials.tungsten);
    public static final ItemArrow arrowTungsten = new ItemArrow(Constant.getNextItemID(), Materials.tungsten);

    public static final ItemBowl bowlPorkchopStew = (ItemBowl)(new ItemBowl(Constant.getNextItemID(), Materials.porkchop_stew, "porkchop_stew")).setFoodValue(14, 14, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("porkchopStew");
    public static final ItemBowl bowlChestnutSoup = (ItemBowl)(new ItemBowl(Constant.getNextItemID(), Materials.chestnut_soup, "lampchop_stew")).setFoodValue(12, 12, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("lampchopStew");
    public static final ItemBowl bowlWaterSuspicious = (ItemBowl)(new ItemBowl(Constant.getNextItemID(), Materials.unsafe_water, "suspicious_water")).setUnlocalizedName("SuspiciousWater");
    public static final ItemBowl bowlWaterSwampland = (ItemBowl)(new ItemBowl(Constant.getNextItemID(), Materials.dangerous_water, "swampland_water")).setUnlocalizedName("SwamplandWater");
    public static final ItemPieces pieceCopper = (ItemPieces) (new ItemPieces(Constant.getNextItemID(), Materials.orePieces, "pieceCopper"));
    public static final ItemPieces pieceSilver = (ItemPieces) (new ItemPieces(Constant.getNextItemID(), Materials.orePieces, "pieceSilver"));
    public static final ItemPieces pieceGold = (ItemPieces) (new ItemPieces(Constant.getNextItemID(), Materials.orePieces, "pieceGold"));
    public static final ItemPieces pieceGoldNether = (ItemPieces) (new ItemPieces(Constant.getNextItemID(), Materials.orePieces, "pieceGoldNether"));
    public static final ItemPieces pieceIron = (ItemPieces) (new ItemPieces(Constant.getNextItemID(), Materials.orePieces, "pieceIron"));
    public static final ItemPieces pieceNickel = (ItemPieces) (new ItemPieces(Constant.getNextItemID(), Materials.orePieces, "pieceNickel"));
    public static final ItemPieces pieceMithril = (ItemPieces) (new ItemPieces(Constant.getNextItemID(), Materials.orePieces, "pieceMithril"));
    public static final ItemPieces pieceTungsten = (ItemPieces) (new ItemPieces(Constant.getNextItemID(), Materials.orePieces, "pieceTungsten"));
    public static final ItemPieces pieceAdamantium = (ItemPieces) (new ItemPieces(Constant.getNextItemID(), Materials.orePieces, "pieceAdamantium"));
    public static final ItemFood mashedCactus = (ItemFood) (new ItemFood(Constant.getNextItemID(), Materials.mashedCactus,1, 0,false,true,false,"mashedCactus")).setMaxStackSize(4);
    public static final ItemFood lemon = (ItemFood)(new ItemFood(Constant.getNextItemID(), Material.fruit, 2, 1, 1000, false, false, true, "lemon")).setPlantProduct();
    public static final Item lemonPie = (new ItemFood(Constant.getNextItemID(), Material.pie, 10, 6, 1000, true, true, true, "lemon_pie")).setMaxStackSize(8).setPlantProduct().setAnimalProduct();
    public static final ItemBucket nickelBucket = new ItemBucket(Constant.getNextItemID(), Materials.nickel, null);
    public static final ItemBucket nickelBucketWater = (ItemBucket)new ItemBucket(Constant.getNextItemID(), Materials.nickel, Material.water).setContainerItem(nickelBucket);
    public static final ItemBucket nickelBucketLava = (ItemBucket)new ItemBucket(Constant.getNextItemID(), Materials.nickel, Material.lava).setContainerItem(nickelBucket);
    public static final ItemBucket nickelBucketStone = (ItemBucket)new ItemBucket(Constant.getNextItemID(), Materials.nickel, Material.stone).setContainerItem(nickelBucket);
    public static final yi nickelBucketMilk = (yi)(new yi(Constant.getNextItemID(), Materials.nickel)).setContainerItem(nickelBucket);
    public static final ItemBucket tungstenBucket = new ItemBucket(Constant.getNextItemID(), Materials.tungsten, null);
    public static final ItemBucket tungstenBucketWater = (ItemBucket)new ItemBucket(Constant.getNextItemID(), Materials.tungsten, Material.water).setContainerItem(tungstenBucket);
    public static final ItemBucket tungstenBucketLava = (ItemBucket)new ItemBucket(Constant.getNextItemID(), Materials.tungsten, Material.lava).setContainerItem(tungstenBucket);
    public static final ItemBucket tungstenBucketStone = (ItemBucket)new ItemBucket(Constant.getNextItemID(), Materials.tungsten, Material.stone).setContainerItem(tungstenBucket);
    public static final yi tungstenBucketMilk = (yi)(new yi(Constant.getNextItemID(), Materials.tungsten)).setContainerItem(tungstenBucket);
    public static final ItemBucket copperBucketWaterSuspicious = (ItemBucket)new ItemBucket(Constant.getNextItemID(),Material.copper,Materials.unsafe_water).setContainerItem(bucketCopperEmpty);
    public static final ItemBucket silverBucketWaterSuspicious = (ItemBucket)new ItemBucket(Constant.getNextItemID(),Material.silver,Materials.unsafe_water).setContainerItem(bucketSilverEmpty);
    public static final ItemBucket goldBucketWaterSuspicious = (ItemBucket)new ItemBucket(Constant.getNextItemID(),Material.gold,Materials.unsafe_water).setContainerItem(bucketGoldEmpty);
    public static final ItemBucket ironBucketWaterSuspicious = (ItemBucket)new ItemBucket(Constant.getNextItemID(),Material.iron,Materials.unsafe_water).setContainerItem(bucketIronEmpty);
    public static final ItemBucket nickelBucketWaterSuspicious = (ItemBucket) new ItemBucket(Constant.getNextItemID(),Materials.nickel,Materials.unsafe_water).setContainerItem(nickelBucket);
    public static final ItemBucket ancientmetalBucketWaterSuspicious = (ItemBucket) new ItemBucket(Constant.getNextItemID(),Material.ancient_metal,Materials.unsafe_water).setContainerItem(bucketAncientMetalEmpty);
    public static final ItemBucket mithrilBucketWaterSuspicious = (ItemBucket) new ItemBucket(Constant.getNextItemID(),Material.mithril,Materials.unsafe_water).setContainerItem(bucketMithrilEmpty);
    public static final ItemBucket tungstenBucketWaterSuspicious = (ItemBucket) new ItemBucket(Constant.getNextItemID(), Materials.tungsten, Materials.unsafe_water).setContainerItem(tungstenBucket);
    public static final ItemBucket adamantiumBucketWaterSuspicious = (ItemBucket) new ItemBucket(Constant.getNextItemID(),Material.adamantium,Materials.unsafe_water).setContainerItem(bucketAdamantiumEmpty);
    public static final ItemBucket copperBucketWaterSwampland = (ItemBucket)new ItemBucket(Constant.getNextItemID(),Material.copper,Materials.dangerous_water).setContainerItem(bucketCopperEmpty);
    public static final ItemBucket silverBucketWaterSwampland = (ItemBucket)new ItemBucket(Constant.getNextItemID(),Material.silver,Materials.dangerous_water).setContainerItem(bucketSilverEmpty);
    public static final ItemBucket goldBucketWaterSwampland = (ItemBucket)new ItemBucket(Constant.getNextItemID(),Material.gold,Materials.dangerous_water).setContainerItem(bucketGoldEmpty);
    public static final ItemBucket ironBucketWaterSwampland = (ItemBucket)new ItemBucket(Constant.getNextItemID(),Material.iron,Materials.dangerous_water).setContainerItem(bucketIronEmpty);
    public static final ItemBucket nickelBucketWaterSwampland = (ItemBucket) new ItemBucket(Constant.getNextItemID(),Materials.nickel,Materials.dangerous_water).setContainerItem(nickelBucket);
    public static final ItemBucket ancientmetalBucketWaterSwampland = (ItemBucket) new ItemBucket(Constant.getNextItemID(),Material.ancient_metal,Materials.dangerous_water).setContainerItem(bucketAncientMetalEmpty);
    public static final ItemBucket mithrilBucketWaterSwampland = (ItemBucket) new ItemBucket(Constant.getNextItemID(),Material.mithril,Materials.dangerous_water).setContainerItem(bucketMithrilEmpty);
    public static final ItemBucket tungstenBucketWaterSwampland = (ItemBucket) new ItemBucket(Constant.getNextItemID(), Materials.tungsten, Materials.dangerous_water).setContainerItem(tungstenBucket);
    public static final ItemBucket adamantiumBucketWaterSwampland = (ItemBucket) new ItemBucket(Constant.getNextItemID(),Material.adamantium,Materials.dangerous_water).setContainerItem(bucketAdamantiumEmpty);
    public static final Item Wolf_fur = createInstance(ItemChain.class, new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.wolf_fur).setCraftingDifficultyAsComponent(100.0F).setUnlocalizedName("small_leather").setCreativeTab(CreativeModeTab.tabMaterials).setMaxStackSize(16);
    public static final Item horse_meat = (new ItemMeat(Constant.getNextItemID(),6,6,true,false,"horse_meat"));
    public static final Item horse_meat_cooked = (new ItemMeat(Constant.getNextItemID(),12,12,true,true,"horse_meat_cooked"));
    public static final ItemArmor WolfHelmet = new ItemHelmet(Constant.getNextItemID(),Materials.wolf_fur,true);
    public static final ItemArmor WolfChestplate = new ItemCuirass(Constant.getNextItemID(),Materials.wolf_fur,true);
    public static final ItemArmor WolfLeggings = new ItemLeggings(Constant.getNextItemID(),Materials.wolf_fur,true);
    public static final ItemArmor WolfBoots = new ItemBoots(Constant.getNextItemID(),Materials.wolf_fur,true);
    public static final ItemGoldenApple Goldenapple = (ItemGoldenApple) (new ItemGoldenApple(66, 2, 1, "VANILLA")).setAlwaysEdible().setPotionEffect(MobEffectList.regeneration.id, 30, 0, 1.0F).setUnlocalizedName("appleGold").useVanillaTexture("apple_golden")/*.setPotionEffectC("+0+1+2-3+13&4-4")*/;
    public static final Item Goldenapplelegend = (ItemGoldenAppleLegend)(new ItemGoldenAppleLegend(Constant.getNextItemID(),2,1, "goldapple")).setAlwaysEdible().setPotionEffect(MobEffectList.regeneration.id, 30, 4, 1.0F).setUnlocalizedName("wtfk").useVanillaTexture("apple_golden_legend");
    public static final ItemBowl bowlLemonade = (ItemBowl)new ItemBowl(Constant.getNextItemID(), Materials.lemonade,"lemonade").setFoodValue(4, 1, false, true, true).setPlantProduct().setUnlocalizedName("lemonade");
    public static final ItemBowl bowlEmpty = (ItemBowl)(new ItemBowl(25, (Material)null, "VANILLA")).setUnlocalizedName("bowl").useVanillaTexture("bowl").setMaxStackSize(8);
    public static final ItemMorningStar morningStarCopper = new ItemMorningStar(Constant.getNextItemID(), Material.copper);
    public static final ItemMorningStar morningStarSilver = new ItemMorningStar(Constant.getNextItemID(), Material.silver);
    public static final ItemMorningStar morningStarGold = new ItemMorningStar(Constant.getNextItemID(), Material.gold);
    public static final ItemMorningStar morningStarIron = new ItemMorningStar(Constant.getNextItemID(), Material.iron);
    public static final ItemMorningStar morningStarNickel = new ItemMorningStar(Constant.getNextItemID(), Materials.nickel);
    public static final ItemMorningStar morningStarAncientMetal = new ItemMorningStar(Constant.getNextItemID(), Material.ancient_metal);
    public static final ItemMorningStar morningStarMithril = new ItemMorningStar(Constant.getNextItemID(), Material.mithril);
    public static final ItemMorningStar morningStarTungsten = new ItemMorningStar(Constant.getNextItemID(), Materials.tungsten);
    public static final ItemMorningStar morningStarAdamantium = new ItemMorningStar(Constant.getNextItemID(), Material.adamantium);
    public static final Item fragStalkerCreeper = new ItemStandard(Constant.getNextItemID(),Material.frags,"fragStalkerCreeper");
    public static final ItemFood glowberries = (ItemFood) new ItemFood(Constant.getNextItemID(),Materials.glowberries,1,0,false,false,false,"glow_berries").setMaxStackSize(16).setAlwaysEdible();
    public static final ItemArrow arrowMagical = new ItemArrow(Constant.getNextItemID(), Materials.magical);
    public static final ItemWand LavaWand = new ItemWand(Constant.getNextItemID(),Materials.tungsten,"wandlava");
    public static final ItemWand FreezeWand = new ItemWand(Constant.getNextItemID(),Materials.nickel,"wandfreeze");
    public static final ItemWand ShockWand = new ItemWand(Constant.getNextItemID(),Material.ancient_metal,"wandshock");
    public static final Item ExperimentalPotion = (new ItemPotionExperimental(Constant.getNextItemID())).setUnlocalizedName("experimentalPotion").setCreativeTab(CreativeModeTab.tabMisc);
    public static final ItemRecordExtend recordDamnation = (ItemRecordExtend) new ItemRecordExtend(2024, "imported.damnation", "record_damnation", "Damnation", "Mwk feat. Hatsune Miku").setUnlocalizedName("record");
    public static final ItemRecordExtend recordConnected = (ItemRecordExtend) new ItemRecordExtend(2025, "imported.connected", "record_connected", "Connected", "Mwk feat. Hatsune Miku").setUnlocalizedName("record");
    public static final ItemArmor VibraniumHelmet = new ItemHelmet(Constant.getNextItemID(),Materials.vibranium,false);
    public static final ItemArmor VibraniumChestplate = new ItemCuirass(Constant.getNextItemID(),Materials.vibranium,false);
    public static final ItemArmor VibraniumLeggings = new ItemLeggings(Constant.getNextItemID(),Materials.vibranium,false);
    public static final ItemArmor VibraniumBoots = new ItemBoots(Constant.getNextItemID(),Materials.vibranium,false);
    public static final ItemSword VibraniumSword = createInstance(ItemSword.class,new Class[]{int.class,Material.class},Constant.getNextItemID(), Materials.vibranium);
    public static final ItemRockExtend lapis = (ItemRockExtend) new ItemRockExtend(Constant.getNextItemID(),Material.lapis_lazuli,"lapis_lazuli").setXPReward(5);
    public static final ItemArmor helmetCustom_a = new ItemHelmet(Constant.getNextItemID(),Materials.custom_a,false);
    public static final ItemArmor chestplateCustom_a = new ItemCuirass(Constant.getNextItemID(),Materials.custom_a,false);
    public static final ItemArmor leggingsCustom_a = new ItemLeggings(Constant.getNextItemID(),Materials.custom_a,false);
    public static final ItemArmor bootsCustom_a = new ItemBoots(Constant.getNextItemID(),Materials.custom_a,false);
    public static final ItemArmor HelmetAncientmetalsacred = new ItemHelmet(Constant.getNextItemID(),Materials.ancient_metal_sacred,false);
    public static final ItemArmor ChestplateAncientmetalsacred = new ItemCuirass(Constant.getNextItemID(),Materials.ancient_metal_sacred,false);
    public static final ItemArmor LeggingsAncientmetalsacred = new ItemLeggings(Constant.getNextItemID(),Materials.ancient_metal_sacred,false);
    public static final ItemArmor BootsAncientmetalsacred = new ItemBoots(Constant.getNextItemID(),Materials.ancient_metal_sacred,false);
    public static final Item AncientmetalArmorPiece = createInstance(ItemNugget.class, new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.ancient_metal_sacred).setCraftingDifficultyAsComponent(800.0F).setUnlocalizedName("ancient_metal_sacred_piece").setCreativeTab(CreativeModeTab.tabMaterials).setMaxStackSize(16);
    public static final ItemFood Agave = (ItemFood) new ItemFood(Constant.getNextItemID(),Materials.agave,1,0,false,false,false,"agave").setMaxStackSize(16).setAlwaysEdible();
    public static final Item Pulque = (new ItemWine(Constant.getNextItemID())).setUnlocalizedName("pulque").setCreativeTab(CreativeModeTab.tabFood);
    public static final Item Ale = (new ItemWine(Constant.getNextItemID())).setUnlocalizedName("ale").setCreativeTab(CreativeModeTab.tabFood);
    public static final ItemBow bowTungsten = (new ItemBow(Constant.getNextItemID(),Materials.tungsten));
    public static final ItemArmor UruHelmet = new ItemHelmet(Constant.getNextItemID(),Materials.uru,false);
    public static final ItemArmor UruChestplate = new ItemCuirass(Constant.getNextItemID(),Materials.uru,false);
    public static final ItemArmor UruLeggings = new ItemLeggings(Constant.getNextItemID(),Materials.uru,false);
    public static final ItemArmor UruBoots = new ItemBoots(Constant.getNextItemID(),Materials.uru,false);
    public static final ItemNugget UruNugget = createInstance(ItemNugget.class, new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.uru);
    public static final ItemBattleAxe UruBattleAxe = createInstance(ItemBattleAxe.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.uru);
    public static final Item UruIngot = createInstance(ItemIngot.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.uru).setXPReward(150);
    public static final ItemSword UruSword = createInstance(ItemSword.class,new Class[]{int.class,Material.class},Constant.getNextItemID(), Materials.uru);
    public static final ItemWarHammer UruWarHammer = createInstance(ItemWarHammer.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.uru);
    public static final ItemMattock UruMattock = createInstance(ItemMattock.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.uru);
    public static final ItemScythe UruScythe = createInstance(ItemScythe.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.uru);
    public static final ItemPieces pieceUru = (ItemPieces) (new ItemPieces(Constant.getNextItemID(), Materials.orePieces, "pieceAdamantium"));
    public static final Item forgingnote = (ItemStandard) (new ItemStandard(Constant.getNextItemID(),Materials.paper,"forging_note"));
    public static final ItemSeeds seedsBeetroot = new ItemSeeds(Constant.getNextItemID(), 1, 1, false, false,false, Blocks.beetroots.blockID, Block.tilledField.blockID, "Beetrootseeds");
    public static final ItemFood beetroot = (new ItemFood(Constant.getNextItemID(), Materials.beetroot, 2, 1, 1000, false, false, true, "Beetroot")).setPlantProduct();
    public static final ItemBowl bowlSalmonSoup = (ItemBowl)(new ItemBowl(Constant.getNextItemID(), Materials.fish_soup, "salmon_soup")).setFoodValue(14, 14, true, true, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("salmonSoup");
    public static final ItemBowl bowlBeetrootSoup = (ItemBowl) (new ItemBowl(Constant.getNextItemID(), Materials.beetroot, "beetroot_soup")).setFoodValue(15,6,6000,false,true,true).setPlantProduct().setAnimalProduct().setUnlocalizedName("beetrootSoup");
    public static final ItemStandard claybowlRaw = (ItemStandard) new ItemStandard(Constant.getNextItemID(),Material.clay,"bowlclayRaw").setMaxStackSize(4);
    public static final ItemBowlClay claybowlEmpty = (ItemBowlClay) (new ItemBowlClay(Constant.getNextItemID(), null, "VANILLA")).setUnlocalizedName("bowlclay").useVanillaTexture("bowlclay").setMaxStackSize(8);
    public static final ItemBowlClay claybowlMushroomStew = (ItemBowlClay) (new ItemBowlClay(Constant.getNextItemID(), Material.mushroom_stew, "mushroom_stew")).setFoodValue(2, 4, false, false, false).setPlantProduct().setUnlocalizedName("mushroomStew");
    public static final ItemBowlClay claybowlMilk = (ItemBowlClay)(new ItemBowlClay(Constant.getNextItemID(), Material.milk, "bowl_milk")).setFoodValue(0, 1, true, false, false).setAnimalProduct().setContainerItem(claybowlEmpty).setAlwaysEdible().setUnlocalizedName("bowlMilk");
    public static final ItemBowlClay claybowlWater = (ItemBowlClay) (new ItemBowlClay(Constant.getNextItemID(), Material.water, "bowl_water")).setContainerItem(claybowlEmpty).setUnlocalizedName("bowlWater");
    public static final ItemBowlClay claybowlBeefStew = (ItemBowlClay) (new ItemBowlClay(Constant.getNextItemID(), Material.beef_stew, "beef_stew")).setFoodValue(16, 16, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("beefStew");
    public static final ItemBowlClay claybowlChickenSoup = (ItemBowlClay) (new ItemBowlClay(Constant.getNextItemID(), Material.chicken_soup, "chicken_soup")).setFoodValue(10, 10, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("chickenSoup");
    public static final ItemBowlClay claybowlVegetableSoup = (ItemBowlClay) (new ItemBowlClay(Constant.getNextItemID(), Material.vegetable_soup, "vegetable_soup")).setFoodValue(6, 6, false, false, true).setPlantProduct().setUnlocalizedName("vegetableSoup");
    public static final ItemBowlClay claybowlIceCream = (ItemBowlClay) (new ItemBowlClay(Constant.getNextItemID(), Material.ice_cream, "ice_cream")).setFoodValue(5, 4, 1000, true, false, false).setPlantProduct().setAnimalProduct().setUnlocalizedName("iceCream");
    public static final ItemBowlClay claybowlSalad = (ItemBowlClay) (new ItemBowlClay(Constant.getNextItemID(), Material.salad, "bowl_salad")).setFoodValue(1, 1, false, false, true).setPlantProduct().setUnlocalizedName("salad");
    public static final ItemBowlClay claybowlCreamOfMushroomSoup = (ItemBowlClay) (new ItemBowlClay(Constant.getNextItemID(), Material.cream_of_mushroom_soup, "cream_of_mushroom_soup")).setFoodValue(3, 5, true, false, false).setPlantProduct().setAnimalProduct().setUnlocalizedName("creamOfMushroomSoup");
    public static final ItemBowlClay claybowlCreamOfVegetableSoup = (ItemBowlClay) (new ItemBowlClay(Constant.getNextItemID(), Material.cream_of_vegetable_soup, "cream_of_vegetable_soup")).setFoodValue(7, 7, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("creamOfVegetableSoup");
    public static final ItemBowlClay claybowlPumpkinSoup = (ItemBowlClay) (new ItemBowlClay(Constant.getNextItemID(), Material.pumpkin_soup, "pumpkin_soup")).setFoodValue(1, 2, false, false, true).setPlantProduct().setUnlocalizedName("pumpkinSoup");
    public static final ItemBowlClay claybowlMashedPotato = (ItemBowlClay) (new ItemBowlClay(Constant.getNextItemID(), Material.mashed_potato, "mashed_potato")).setFoodValue(12, 8, true, false, false).setPlantProduct().setAnimalProduct().setUnlocalizedName("mashedPotato");
    public static final ItemBowlClay claybowlSorbet = (ItemBowlClay) (new ItemBowlClay(Constant.getNextItemID(), Material.sorbet, "sorbet")).setFoodValue(4, 2, 2000, false, false, true).setPlantProduct().setUnlocalizedName("sorbet");
    public static final ItemBowlClay claybowlPorridge = (ItemBowlClay) (new ItemBowlClay(Constant.getNextItemID(), Material.porridge, "porridge")).setFoodValue(4, 2, 2000, false, false, true).setPlantProduct().setUnlocalizedName("porridge");
    public static final ItemBowlClay claybowlCereal = (ItemBowlClay) (new ItemBowlClay(Constant.getNextItemID(), Material.cereal, "cereal")).setFoodValue(4, 2, 1000, true, false, false).setPlantProduct().setAnimalProduct().setUnlocalizedName("cereal");
    public static final ItemBowlClay claybowlLemonade = (ItemBowlClay) (new ItemBowlClay(Constant.getNextItemID(), Materials.lemonade,"lemonade")).setFoodValue(4, 1, false, true, true).setPlantProduct().setUnlocalizedName("lemonade");
    public static final ItemBowlClay claybowlPorkchopStew = (ItemBowlClay) (new ItemBowlClay(Constant.getNextItemID(), Materials.porkchop_stew, "porkchop_stew")).setFoodValue(14, 14, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("porkchopStew");
    public static final ItemBowlClay claybowlChestnutSoup = (ItemBowlClay) (new ItemBowlClay(Constant.getNextItemID(), Materials.chestnut_soup, "lampchop_stew")).setFoodValue(12, 12, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("lampchopStew");
    public static final ItemBowlClay claybowlWaterSuspicious = (ItemBowlClay) (new ItemBowlClay(Constant.getNextItemID(), Materials.unsafe_water, "suspicious_water")).setUnlocalizedName("SuspiciousWater");
    public static final ItemBowlClay claybowlWaterSwampland = (ItemBowlClay) (new ItemBowlClay(Constant.getNextItemID(), Materials.dangerous_water, "swampland_water")).setUnlocalizedName("SwamplandWater");
    public static final ItemBowlClay claybowlSalmonSoup = (ItemBowlClay) (new ItemBowlClay(Constant.getNextItemID(), Materials.fish_soup, "salmon_soup")).setFoodValue(14, 14, true, true, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("salmonSoup");
    public static final ItemBowlClay claybowlBeetrootSoup = (ItemBowlClay) (new ItemBowlClay(Constant.getNextItemID(), Materials.beetroot, "beetroot_soup")).setFoodValue(15,6,6000,false,true,true).setPlantProduct().setAnimalProduct().setUnlocalizedName("beetrootSoup");
    public static final Item totemoffecund = (ItemTotem) (new ItemTotem(Constant.getNextItemID(),Material.gold,"totem")).setMaxStackSize(1);
    public static final ItemArmor helmetCustom_b = new ItemHelmet(Constant.getNextItemID(),Materials.custom_b,false);
    public static final ItemArmor chestplateCustom_b = new ItemCuirass(Constant.getNextItemID(),Materials.custom_b,false);
    public static final ItemArmor leggingsCustom_b = new ItemLeggings(Constant.getNextItemID(),Materials.custom_b,false);
    public static final ItemArmor bootsCustom_b = new ItemBoots(Constant.getNextItemID(),Materials.custom_b,false);
    public static final ItemFishingRod fishingRodNickel = (ItemFishingRod)(new ItemFishingRod(Constant.getNextItemID(), Materials.nickel)).setUnlocalizedName("fishingRod");
    public static final ItemFishingRod fishingRodTungsten = (ItemFishingRod)(new ItemFishingRod(Constant.getNextItemID(), Materials.tungsten)).setUnlocalizedName("fishingRod");
    public static final ItemCarrotStick carrotOnAStickNickel = (ItemCarrotStick) (new ItemCarrotStick(Constant.getNextItemID(), Materials.nickel)).setUnlocalizedName("carrotOnAStick");
    public static final ItemCarrotStick carrotOnAStickTungsten = (ItemCarrotStick) (new ItemCarrotStick(Constant.getNextItemID(), Materials.tungsten)).setUnlocalizedName("carrotOnAStick");
    public static final ItemPotionSuspicious SuspiciousPotion = (ItemPotionSuspicious) new ItemPotionSuspicious(Constant.getNextItemID()).setUnlocalizedName("suspiciousPotion").setCreativeTab(CreativeModeTab.tabMisc);
    public static final Item totemofdestroy = (ItemTotem) (new ItemTotem(Constant.getNextItemID(),Materials.tungsten,"totem")).setMaxStackSize(1);
    public static final Item totemofpreserve = (ItemTotem) (new ItemTotem(Constant.getNextItemID(),Material.iron,"totem")).setMaxStackSize(1);
    public static final Item totemofknowledge = (ItemTotem) (new ItemTotem(Constant.getNextItemID(),Material.ancient_metal,"totem")).setMaxStackSize(1);
    public static final ItemIgnition ignitionCopper = new ItemIgnition(Constant.getNextItemID(),Material.copper);
    public static final ItemIgnition ignitionSilver = new ItemIgnition(Constant.getNextItemID(),Material.silver);
    public static final ItemIgnition ignitionGold = new ItemIgnition(Constant.getNextItemID(),Material.gold);
    public static final ItemIgnition ignitionIron = new ItemIgnition(3,Material.iron);
    public static final ItemIgnition ignitionNickel = new ItemIgnition(Constant.getNextItemID(),Materials.nickel);
    public static final ItemIgnition ignitionTungsten = new ItemIgnition(Constant.getNextItemID(),Materials.tungsten);
    public static final ItemIgnition ignitionMithril = new ItemIgnition(Constant.getNextItemID(),Material.mithril);
    public static final ItemIgnition ignitionAncientMetal = new ItemIgnition(Constant.getNextItemID(),Material.ancient_metal);
    public static final ItemIgnition ignitionAdamantium = new ItemIgnition(Constant.getNextItemID(),Material.adamantium);
    public static final ItemIgnition ignitionWood = new ItemIgnition(Constant.getNextItemID(),Material.wood);
    public static final ItemBrewingMisc wither_branch = new ItemBrewingMisc(Constant.getNextItemID(),Material.wood,"wither_wood").setPotionEffectExtend("+0-1+2+3+13&4-4");
    public static final ItemGuideBook guide = new ItemGuideBook(Constant.getNextItemID());
    public static final Item totemofhunting = (ItemTotem) (new ItemTotem(Constant.getNextItemID(),Materials.nickel,"totem")).setMaxStackSize(1);
    public static final ItemClub UruMorningStar = createInstance(ItemClub.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.uru);
    public static final ItemPickaxe UruPickaxe = createInstance(ItemPickaxe.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.uru);
    public static final ItemRockExtend shardAzurite = (ItemRockExtend) (new ItemRockExtend(Constant.getNextItemID(),Materials.crystal,"azurite")).setXPReward(1);
    public static final Item detectorEmerald = new ItemDetector(Constant.getNextItemID(),Material.emerald,"emerald").setUnlocalizedName("detector");
    public static final Item detectorDiamond = new ItemDetector(Constant.getNextItemID(),Material.diamond,"diamond").setUnlocalizedName("detector");
    public static final Item sulphur = new ItemStandard(Constant.getNextItemID(),Materials.sulphur,"sulphur_sphere").setMaxStackSize(16);
    public static final Item hellhoundFur = createInstance(ItemIngot.class, new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.wolf_fur).setCraftingDifficultyAsComponent(100.0F).setUnlocalizedName("small_leather").setCreativeTab(CreativeModeTab.tabMaterials).setMaxStackSize(16);
    public static final ItemArmor HellhoundHelmet = new ItemHelmet(Constant.getNextItemID(),Materials.wolf_fur,false);
    public static final ItemArmor HellhoundChestplate = new ItemCuirass(Constant.getNextItemID(),Materials.wolf_fur,false);
    public static final ItemArmor HellhoundLeggings = new ItemLeggings(Constant.getNextItemID(),Materials.wolf_fur,false);
    public static final ItemArmor HellhoundBoots = new ItemBoots(Constant.getNextItemID(),Materials.wolf_fur,false);
    public static final ItemKettle leatherKettle = (ItemKettle) new ItemKettle(Constant.getNextItemID(), 13, Material.water, Material.leather).setUnlocalizedName("leather_kettle");
    public static final ItemKettle leatherKettleSuspicious = (ItemKettle) new ItemKettle(Constant.getNextItemID(), 13, Materials.unsafe_water, Material.leather).setUnlocalizedName("leather_kettle_sus");
    public static final ItemKettle leatherKettleSwampland = (ItemKettle) new ItemKettle(Constant.getNextItemID(), 13, Materials.dangerous_water, Material.leather).setUnlocalizedName("leather_kettle_danger");
    public static final ItemStandard clayJug = (ItemStandard) new ItemStandard(Constant.getNextItemID(),Material.clay,"clayJug").setMaxStackSize(1);
    public static final ItemKettle hardenedClayJug = (ItemKettle) new ItemKettle(Constant.getNextItemID(), 19, Material.water, Material.hardened_clay).setUnlocalizedName("hardened_clay_jug");
    public static final ItemKettle hardenedClayJugSuspicious = (ItemKettle) new ItemKettle(Constant.getNextItemID(), 19, Materials.unsafe_water, Material.hardened_clay).setUnlocalizedName("hardened_clay_jug_sus");
    public static final ItemKettle hardenedClayJugSwampland = (ItemKettle) new ItemKettle(Constant.getNextItemID(), 19, Materials.dangerous_water, Material.hardened_clay).setUnlocalizedName("hardened_clay_jug_danger");

    //    public static PotionBrewer potionBrewer;
//    public static final Item test = (ItemPieces) new ItemPieces(Constant.getNextItemID(), Materials.orePieces, "pieceTungsten1").setPotionEffectC(potionBrewer.getAttackEffect());


        public static void registerItems() {
        register("armor/nickel_helmet", nickelHelmet);
        register("armor/nickel_chestplate", nickelChestplate);
        register("armor/nickel_leggings", nickelLeggings);
        register("armor/nickel_boots", nickelBoots);
        register("armor/nickel_chainmail_helmet", nickelHelmetChain);
        register("armor/nickel_chainmail_chestplate", nickelChestplateChain);
        register("armor/nickel_chainmail_leggings", nickelLeggingsChain);
        register("armor/nickel_chainmail_boots", nickelBootsChain);
        register("ingots/nickel", nickelIngot);
        register("nuggets/nickel", nickelNugget);
        register("tools/nickel_axe", nickelAxe);
        register("tools/nickel_battle_axe", nickelBattleAxe);
        register("tools/nickel_dagger", nickelDagger);
        register("tools/nickel_hatchet", nickelHatchet);
        register("tools/nickel_hoe", nickelHoe);
        register("tools/nickel_knife", nickelKnife);
        register("tools/nickel_pickaxe", nickelPickaxe);
        register("tools/nickel_mattock", nickelMattock);
        register("tools/nickel_scythe", nickelScythe);
        register("tools/nickel_shears", nickelShears);
        register("tools/nickel_shovel", nickelShovel);
        register("tools/nickel_sword", nickelSword);
        register("tools/nickel_war_hammer", nickelWarHammer);
        register("doors/nickel", doorNickel);
        register("chains/nickel", nickelChain);
        register("coins/nickel", nickelCoin);
        register("arrows/nickel_arrow", arrowNickel);
        register("armor/tungsten_helmet", tungstenHelmet);
        register("armor/tungsten_chestplate", tungstenChestplate);
        register("armor/tungsten_leggings", tungstenLeggings);
        register("armor/tungsten_boots", tungstenBoots);
        register("armor/tungsten_chainmail_helmet", tungstenHelmetChain);
        register("armor/tungsten_chainmail_chestplate", tungstenChestplateChain);
        register("armor/tungsten_chainmail_leggings", tungstenLeggingsChain);
        register("armor/tungsten_chainmail_boots", tungstenBootsChain);
        register("ingots/tungsten", tungstenIngot);
        register("nuggets/tungsten", tungstenNugget);
        register("tools/tungsten_axe", tungstenAxe);
        register("tools/tungsten_battle_axe", tungstenBattleAxe);
        register("tools/tungsten_dagger", tungstenDagger);
        register("tools/tungsten_hatchet", tungstenHatchet);
        register("tools/tungsten_hoe", tungstenHoe);
        register("tools/tungsten_knife", tungstenKnife);
        register("tools/tungsten_pickaxe", tungstenPickaxe);
        register("tools/tungsten_mattock", tungstenMattock);
        register("tools/tungsten_scythe", tungstenScythe);
        register("tools/tungsten_shears", tungstenShears);
        register("tools/tungsten_shovel", tungstenShovel);
        register("tools/tungsten_sword", tungstenSword);
        register("tools/tungsten_war_hammer", tungstenWarHammer);
        register("doors/tungsten", doorTungsten);
        register("chains/tungsten", tungstenChain);
        register("coins/tungsten", tungstenCoin);
        register("arrows/tungsten_arrow", arrowTungsten);
        register("bowls/porkchop_stew",bowlPorkchopStew);
        register("bowls/lampchop_stew",bowlChestnutSoup);
        register("bowls/salmon_soup",bowlSalmonSoup);
        register("bowls/beetroot_soup",bowlBeetrootSoup);
        register("pieces/copper",pieceCopper);
        register("pieces/silver",pieceSilver);
        register("pieces/gold",pieceGold);
        register("pieces/gold_nether",pieceGoldNether);
        register("pieces/iron",pieceIron);
        register("pieces/nickel",pieceNickel);
        register("pieces/tungsten",pieceTungsten);
        register("pieces/mithril",pieceMithril);
        register("pieces/adamantium",pieceAdamantium);
        register("food/mashed_cactus",mashedCactus);
        register("food/lemon",lemon);
        register("food/lemon_pie",lemonPie);
        register("bowls/lemonade",bowlLemonade);
        register("buckets/nickel/empty",nickelBucket);
        register("buckets/nickel/lava",nickelBucketLava);
        register("buckets/nickel/milk",nickelBucketMilk);
        register("buckets/nickel/stone",nickelBucketStone);
        register("buckets/nickel/water",nickelBucketWater);
        register("buckets/tungsten/empty",tungstenBucket);
        register("buckets/tungsten/lava",tungstenBucketLava);
        register("buckets/tungsten/milk",tungstenBucketMilk);
        register("buckets/tungsten/stone",tungstenBucketStone);
        register("buckets/tungsten/water",tungstenBucketWater);
        register("bowls/bowl_water_suspicious",bowlWaterSuspicious);
        register("bowls/bowl_water_swampland",bowlWaterSwampland);
        register("buckets/copper/water_suspicious",copperBucketWaterSuspicious);
        register("buckets/silver/water_suspicious",silverBucketWaterSuspicious);
        register("buckets/gold/water_suspicious",goldBucketWaterSuspicious);
        register("buckets/iron/water_suspicious",ironBucketWaterSuspicious);
        register("buckets/nickel/water_suspicious",nickelBucketWaterSuspicious);
        register("buckets/mithril/water_suspicious",mithrilBucketWaterSuspicious);
        register("buckets/tungsten/water_suspicious",tungstenBucketWaterSuspicious);
        register("buckets/adamantium/water_suspicious",adamantiumBucketWaterSuspicious);
        register("buckets/ancient_metal/water_suspicious",ancientmetalBucketWaterSuspicious);
        register("buckets/copper/water_swampland",copperBucketWaterSwampland);
        register("buckets/silver/water_swampland",silverBucketWaterSwampland);
        register("buckets/gold/water_swampland",goldBucketWaterSwampland);
        register("buckets/iron/water_swampland",ironBucketWaterSwampland);
        register("buckets/nickel/water_swampland",nickelBucketWaterSwampland);
        register("buckets/mithril/water_swampland",mithrilBucketWaterSwampland);
        register("buckets/tungsten/water_swampland",tungstenBucketWaterSwampland);
        register("buckets/adamantium/water_swampland",adamantiumBucketWaterSwampland);
        register("buckets/ancient_metal/water_swampland",ancientmetalBucketWaterSwampland);
        register("wolf_fur",Wolf_fur);
        register("food/horse_meat",horse_meat);
        register("food/horse_meat_cooked",horse_meat_cooked);
        register("armor/wolf_helmet", WolfHelmet);
        register("armor/wolf_jacket", WolfChestplate);
        register("armor/wolf_leggings", WolfLeggings);
        register("armor/wolf_boots", WolfBoots);
        register("apple_golden",Goldenapple);
        register("apple_golden",Goldenapplelegend);
        register("bowl",bowlEmpty);
        register("tools/copper_club",morningStarCopper);
        register("tools/silver_club",morningStarSilver);
        register("tools/gold_club",morningStarGold);
        register("tools/iron_club",morningStarIron);
        register("tools/nickel_club",morningStarNickel);
        register("tools/ancient_metal_club",morningStarAncientMetal);
        register("tools/mithril_club",morningStarMithril);
        register("tools/tungsten_club",morningStarTungsten);
        register("tools/adamantium_club",morningStarAdamantium);
        register("frag/stalker_creeper",fragStalkerCreeper);
        register("food/glow_berries",glowberries);
        register("arrows/magical_arrow", arrowMagical);
        register("wand/lava", LavaWand);
        register("wand/ice", FreezeWand);
        register("wand/thunder", ShockWand);
        register("suspicious_potion",SuspiciousPotion);
        register("experimental_potion",ExperimentalPotion);
        register("shards/diamond",shardDiamond);
        register("shards/emerald",shardEmerald);
        register("shards/quartz",shardNetherQuartz);
        register("azurite",shardAzurite);
        register("records/record_damnation",recordDamnation);
        register("records/record_connected",recordConnected);
        register("lapis_lazuli",lapis);
        register("tools/vibranium_sword",VibraniumSword);
        register("armor/vibranium_helmet",VibraniumHelmet);
        register("armor/vibranium_chestplate",VibraniumChestplate);
        register("armor/vibranium_leggings",VibraniumLeggings);
        register("armor/vibranium_boots",VibraniumBoots);
        register("armor/null_helmet",helmetCustom_a);
        register("armor/null_chestplate",chestplateCustom_a);
        register("armor/null_leggings",leggingsCustom_a);
        register("armor/null_boots",bootsCustom_a);
        register("armor/null_helmet",helmetCustom_b);
        register("armor/null_chestplate",chestplateCustom_b);
        register("armor/null_leggings",leggingsCustom_b);
        register("armor/null_boots",bootsCustom_b);
        register("armor/ancient_metal_sacred_helmet",HelmetAncientmetalsacred);
        register("armor/ancient_metal_sacred_chestplate",ChestplateAncientmetalsacred);
        register("armor/ancient_metal_sacred_leggings",LeggingsAncientmetalsacred);
        register("armor/ancient_metal_sacred_boots",BootsAncientmetalsacred);
        register("ancient_metal_armor_piece",AncientmetalArmorPiece);
        register("food/agave",Agave);
        register("pulque",Pulque);
        register("ale",Ale);
        register("armor/uru_helmet",UruHelmet);
        register("armor/uru_chestplate",UruChestplate);
        register("armor/uru_leggings",UruLeggings);
        register("armor/uru_boots",UruBoots);
        register("forging_note",forgingnote);
        register("ingots/uru", UruIngot);
        register("nuggets/uru", UruNugget);
        register("tools/uru_battle_axe", UruBattleAxe);
        register("tools/uru_mattock", UruMattock);
        register("tools/uru_scythe", UruScythe);
        register("tools/uru_sword", UruSword);
        register("tools/uru_war_hammer", UruWarHammer);
        register("tools/uru_club", UruMorningStar);
        register("tools/uru_pickaxe", UruPickaxe);
        register("pieces/uru",pieceUru);
        register("bows/tungsten/", bowTungsten).setUnlocalizedName("tungsten_bow");
        register("food/beetroot",beetroot);
        register("food/beetroot_seeds",seedsBeetroot);
        register("hardened_clay_bowls/raw",claybowlRaw);
        register("hardened_clay_bowls/beef_stew",claybowlBeefStew);
        register("hardened_clay_bowls/beetroot_soup",claybowlBeetrootSoup);
        register("hardened_clay_bowls/bowl_milk",claybowlMilk);
        register("hardened_clay_bowls/bowl_salad",claybowlSalad);
        register("hardened_clay_bowls/bowl_water",claybowlWater);
        register("hardened_clay_bowls/bowl_water_suspicious",claybowlWaterSuspicious);
        register("hardened_clay_bowls/bowl_water_swampland",claybowlWaterSwampland);
        register("hardened_clay_bowls/cereal",claybowlCereal);
        register("hardened_clay_bowls/chicken_soup",claybowlChickenSoup);
        register("hardened_clay_bowls/cream_of_mushroom_soup",claybowlCreamOfMushroomSoup);
        register("hardened_clay_bowls/cream_of_vegetable_soup",claybowlCreamOfVegetableSoup);
        register("hardened_clay_bowls/empty",claybowlEmpty);
        register("hardened_clay_bowls/ice_cream",claybowlIceCream);
        register("hardened_clay_bowls/lampchop_stew",claybowlChestnutSoup);
        register("hardened_clay_bowls/lemonade",claybowlLemonade);
        register("hardened_clay_bowls/mashed_potato",claybowlMashedPotato);
        register("hardened_clay_bowls/mushroom_stew",claybowlMushroomStew);
        register("hardened_clay_bowls/porkchop_stew",claybowlPorkchopStew);
        register("hardened_clay_bowls/porridge",claybowlPorridge);
        register("hardened_clay_bowls/pumpkin_soup",claybowlPumpkinSoup);
        register("hardened_clay_bowls/salmon_soup",claybowlSalmonSoup);
        register("hardened_clay_bowls/sorbet",claybowlSorbet);
        register("hardened_clay_bowls/vegetable_soup",claybowlVegetableSoup);
        register("totem/totem_of_fecund",totemoffecund);
        register("totem/totem_of_destroy",totemofdestroy);
        register("totem/totem_of_knowledge",totemofknowledge);
        register("totem/totem_of_preserve",totemofpreserve);
        register("totem/totem_of_hunting",totemofhunting);
        register("ignition/wood",ignitionWood);
        register("ignition/copper",ignitionCopper);
        register("ignition/silver",ignitionSilver);
        register("ignition/gold",ignitionGold);
        register("ignition/iron",ignitionIron);
        register("ignition/nickel",ignitionNickel);
        register("ignition/ancient_metal",ignitionAncientMetal);
        register("ignition/mithril",ignitionMithril);
        register("ignition/tungsten",ignitionTungsten);
        register("ignition/adamantium",ignitionAdamantium);
        register("wither_branch",wither_branch);
        register("tools/detector",detectorDiamond);
        register("tools/detector_emerald",detectorEmerald);
        register("sulphur_sphere",sulphur);
        register("wolf_fur_hellhound",hellhoundFur);
        register("armor/hellhound_helmet", HellhoundHelmet);
        register("armor/hellhound_jacket", HellhoundChestplate);
        register("armor/hellhound_leggings", HellhoundLeggings);
        register("armor/hellhound_boots", HellhoundBoots);
        register("clay_jug",clayJug);
        Constant.initItemArray();
    }
    public static void registerBasicToolRecipes(RecipeRegister register,Material material){
            Item item = Item.getMatchingItem(ItemIngot.class,material);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemSword.class, material)),true,
                "A",
                "A",
                "S",
                'A',item,
                'S',Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemHoe.class, material)),true,
                "AA",
                "S ",
                "S ",
                'A',item,
                'S',Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemAxe.class, material)),true,
                "AA",
                "SA",
                "S ",
                'A',item,
                'S',Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemShovel.class, material)),true,
                "A",
                "S",
                "S",
                'A',item,
                'S',Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemPickaxe.class, material)),true,
                "AAA",
                " S ",
                " S ",
                'A',item,
                'S',Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemFishingRod.class, material)),true,
                "  S",
                " SG",
                "SAG",
                'A',getMatchingItem(ItemNugget.class,item.getHardestMetalMaterial()),
                'S',Item.stick,
                'G',Item.silk);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemShears.class, material)),true,
                "A ",
                " A",
                'A',item);
        registerArmorRecipe(register,item,material);
    }
    public static void registerMITEToolRecipe(RecipeRegister register, Material material){
        Item item = Item.getMatchingItem(ItemIngot.class,material);
        Item item_nugget = Item.getMatchingItem(ItemNugget.class,item.getHardestMetalMaterial());
        Item item_chain = Item.getMatchingItem(ItemChain.class,material);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemArrow.class,material)), true,
                "C",
                "B",
                "A",
                'A', Item.feather,
                'B', Item.stick,
                'C', item_nugget);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemChain.class,material)), true,
                " A ",
                "A A",
                " A ",
                'A', item_nugget);
        registerArmorRecipe(register,item_chain,material);
        register.registerShapedRecipe(new ItemStack(ItemBucket.getPeer(material,null)), true,
                "A A",
                " A ",
                'A', item);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemScythe.class,material)), true,
                "SA ",
                "S A",
                "S  ",
                'A', item,
                'S', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemMattock.class,material)), true,
                "AAA",
                " SA",
                " S ",
                'A', item,
                'S', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemHatchet.class,material)), true,
                " BA",
                " B ",
                'A', item,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemWarHammer.class,material)), true,
                "AAA",
                "ABA",
                " B ",
                'A', item,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemDagger.class,material)), true,
                " A ",
                " B ",
                'A', item,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemBattleAxe.class,material)), true,
                "A A",
                "ABA",
                " B ",
                'A', item,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemDoor.class,material)), true,
                "AA",
                "AA",
                "AA",
                'A', item);
    }
    public static void registerITFToolRecipe(RecipeRegister register){
        Material[] materials = new Material[]{Material.copper,Material.silver,Material.gold,Material.iron,Materials.nickel,Material.ancient_metal,Material.mithril,Materials.tungsten,Material.adamantium};
        for(int i = 0;i< materials.length;i++){
            Item item = Item.getMatchingItem(ItemIngot.class,materials[i]);
            Item item_nugget = getMatchingItem(ItemNugget.class,materials[i]);
            register.registerShapedRecipe(new ItemStack(getMatchingItem(ItemMorningStar.class,materials[i]), 1), true,
                    "###",
                    "#*#",
                    " # ",
                    '#', item_nugget,
                    '*', item);
            register.registerShapedRecipe(new ItemStack(getMatchingItem(ItemIgnition.class,materials[i])),true,
                    "C ",
                    " F",
                    'C',item_nugget,
                    'F',flint
            );
            register.registerShapedRecipe(new ItemStack(leatherKettle, 1).setItemDamage(leatherKettle.getMaxDamage() - 1), false,
                    "#N",
                    "JL",
                    'J', Item.sinew,
                    '#', Item.silk,
                    'N', item_nugget,
                    'L', Item.leather).resetDifficulty(2000);
        }

    }
    public static void registerArmorRecipe(RecipeRegister register,Item item, Material material){
            register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemHelmet.class,material,item instanceof ItemChain)),true,
                    "AAA",
                    "A A",
                    'A',item);
            register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemCuirass.class,material,item instanceof ItemChain)),true,
                    "A A",
                    "AAA",
                    "AAA",
                    'A',item);
            register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemLeggings.class,material,item instanceof ItemChain)),true,
                    "AAA",
                    "A A",
                    "A A",
                    'A',item);
            register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemBoots.class,material,item instanceof ItemChain)),true,
                    "A A",
                    "A A",
                    'A',item);
    }
    public static void registerLeatherArmorRecipe(RecipeRegister register,Item item, Material material){
        register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemHelmet.class,material,item instanceof ItemChain)),true,
                "AAA",
                "AHA",
                'A',item,
                'H',helmetLeather);
        register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemCuirass.class,material,item instanceof ItemChain)),true,
                "A A",
                "ACA",
                "AAA",
                'A',item,
                'C',plateLeather);
        register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemLeggings.class,material,item instanceof ItemChain)),true,
                "AAA",
                "ALA",
                "A A",
                'A',item,
                'L',Item.legsLeather);
        register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemBoots.class,material,item instanceof ItemChain)),true,
                "ABA",
                "A A",
                'A',item,
                'B',Item.bootsLeather);
    }
    public static void registerFullMetalToolRecipe(RecipeRegister register, Material material){
            registerBasicToolRecipes(register,material);
            registerMITEToolRecipe(register,material);
    }
    public static void registerRecipes(RecipeRegister register) {
        register.registerShapelessRecipe(new ItemStack(lemonPie), true,
                Item.sugar, Item.egg, Item.flour, lemon);
        register.registerShapelessRecipe(new ItemStack(nickelIngot, 9), true,
                Blocks.blockNickel);
        register.registerShapelessRecipe(new ItemStack(nickelNugget, 9), true,
                nickelIngot);
        register.registerShapelessRecipe(new ItemStack(tungstenIngot, 9), true,
                Blocks.blockTungsten);
        register.registerShapelessRecipe(new ItemStack(tungstenNugget, 9), true,
                tungstenIngot);
        registerLeatherArmorRecipe(register,Wolf_fur,Materials.wolf_fur);
        registerLeatherArmorRecipe(register,hellhoundFur,Materials.wolf_fur);
        registerITFToolRecipe(register);
        registerFullMetalToolRecipe(register,Materials.nickel);
        registerFullMetalToolRecipe(register,Materials.tungsten);
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
        for(int i = 0; i <= 4; i++){
            register.registerShapelessRecipe(new ItemStack(Item.stick,1),true,
                    new ItemStack(Blocks.torchWoodIdle,i),new ItemStack(Blocks.torchWoodExtinguished, 4 - i));
        }
        register.registerShapedRecipe(new ItemStack(clayJug, 1), true,
                " C ",
                "C C",
                " C ",
                'C', clay);
        register.registerShapedRecipe(new ItemStack(bowTungsten, 1), true,
                "#C ",
                "#EC",
                "#C ",
                '#', silk,
                'E', tungstenIngot,
                'C', stick);
        register.registerShapedRecipe(new ItemStack(ignitionWood, 1),true,
                "SW",
                "WW",
                'S',silk,
                'W',stick);
        register.registerShapedRecipe(new ItemStack(ignitionWood, 1),true,
                "SW",
                "WW",
                'S',sinew,
                'W',stick);
        register.registerShapedRecipe(new ItemStack(detectorEmerald, 1),true,
                "FAF",
                "ANA",
                "FAF",
                'A',Item.goldNugget,
                'F',Item.ancientMetalNugget,
                'N',Item.emerald);
        register.registerShapedRecipe(new ItemStack(detectorDiamond, 1),true,
                "FAF",
                "ANA",
                "FAF",
                'A',Item.goldNugget,
                'F',Item.ancientMetalNugget,
                'N',Item.diamond);

        register.registerShapelessRecipe(new ItemStack(sulphur,9),true,
                new ItemStack(Blocks.blockSulphur,1));
        register.registerShapelessRecipe(new ItemStack(Item.gunpowder,5),true,
                new ItemStack(Items.sulphur, 8),new ItemStack(Item.coal, 1, 1));
        register.registerShapelessRecipe(new ItemStack(forgingnote,2),false,
                Items.forgingnote,Item.writableBook
        );
        register.registerShapelessRecipe(new ItemStack(UruHelmet,1),true,
                Items.forgingnote,Items.UruIngot,Item.helmetMithril,Item.ingotMithril
        );
        register.registerShapelessRecipe(new ItemStack(UruChestplate,1),true,
                Items.forgingnote,Items.UruIngot,Item.plateMithril,Item.ingotMithril
        );
        register.registerShapelessRecipe(new ItemStack(UruLeggings,1),true,
                Items.forgingnote,Items.UruIngot,Item.legsMithril,Item.ingotMithril
        );
        register.registerShapelessRecipe(new ItemStack(UruBoots,1),true,
                Items.forgingnote,Items.UruIngot,Item.bootsMithril,Item.ingotMithril
        );
        register.registerShapelessRecipe(new ItemStack(UruSword,1),true,
                Items.forgingnote,Items.UruIngot,Item.swordMithril,Item.ingotMithril
        );
        register.registerShapelessRecipe(new ItemStack(UruScythe,1),true,
                Items.forgingnote,Items.UruIngot,Item.scytheMithril,Item.ingotMithril
        );
        register.registerShapelessRecipe(new ItemStack(UruBattleAxe,1),true,
                Items.forgingnote,Items.UruIngot,Item.battleAxeMithril,Item.ingotMithril
        );
        register.registerShapelessRecipe(new ItemStack(UruWarHammer,1),true,
                Items.forgingnote,Items.UruIngot,Item.warHammerMithril,Item.ingotMithril
        );
        register.registerShapelessRecipe(new ItemStack(UruMattock,1),true,
                Items.forgingnote,Items.UruIngot,Item.mattockMithril,Item.ingotMithril
        );
        register.registerShapelessRecipe(new ItemStack(UruMorningStar,1),true,
                Items.forgingnote,Items.UruIngot,Items.morningStarMithril,Item.ingotMithril
        );
        register.registerShapelessRecipe(new ItemStack(UruPickaxe,1),true,
                Items.forgingnote,Items.UruIngot,Item.pickaxeMithril,Item.ingotMithril
        );
        register.registerShapelessRecipe(new ItemStack(UruNugget, 9), true,
                UruIngot);
        register.registerShapelessRecipe(new ItemStack(HelmetAncientmetalsacred,1),true,
                Items.forgingnote,Item.ingotGold,Item.helmetAncientMetal
        );
        register.registerShapelessRecipe(new ItemStack(ChestplateAncientmetalsacred,1),true,
                Items.forgingnote,Item.ingotGold,Item.plateAncientMetal
        );
        register.registerShapelessRecipe(new ItemStack(LeggingsAncientmetalsacred,1),true,
                Items.forgingnote,Item.ingotGold,Item.legsAncientMetal
        );
        register.registerShapelessRecipe(new ItemStack(BootsAncientmetalsacred,1),true,
                Items.forgingnote,Item.ingotGold,Item.bootsAncientMetal
        );
        register.registerShapelessRecipe(new ItemStack(tungstenNugget,1),false,
                Items.arrowTungsten
        );
        register.registerShapelessRecipe(new ItemStack(nickelNugget,1),false,
                Items.arrowNickel
        );

        register.registerShapelessRecipe(new ItemStack(mashedCactus,1),true,
                Block.cactus
        );
        register.registerShapelessRecipe(new ItemStack(nickelIngot,1),true,
                Items.nickelNugget,Items.nickelNugget,Items.nickelNugget,
                Items.nickelNugget,Items.nickelNugget,Items.nickelNugget,
                Items.nickelNugget,Items.nickelNugget,Items.nickelNugget
        );
        register.registerShapelessRecipe(new ItemStack(tungstenIngot,1),true,
                Items.tungstenNugget,Items.tungstenNugget,Items.tungstenNugget,
                Items.tungstenNugget,Items.tungstenNugget,Items.tungstenNugget,
                Items.tungstenNugget,Items.tungstenNugget,Items.tungstenNugget
        );
        register.registerShapelessRecipe(new ItemStack(UruIngot,1),true,
                Items.UruNugget,Items.UruNugget,Items.UruNugget,
                Items.UruNugget,Items.UruNugget,Items.UruNugget,
                Items.UruNugget,Items.UruNugget,Items.UruNugget
        );
        register.registerShapelessRecipe(new ItemStack(Item.leather,1),true,
                Items.Wolf_fur,Items.Wolf_fur,Items.Wolf_fur,Items.Wolf_fur
        );
        register.registerShapelessRecipe(new ItemStack(Item.leather,1),true,
                Items.hellhoundFur,Items.hellhoundFur,Items.hellhoundFur,Items.hellhoundFur
        );
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder,1,4),false,
                Items.lapis);
        register.registerShapelessRecipe(new ItemStack(Items.seedsBeetroot,1),false,
                Items.beetroot,Items.beetroot);
        register.registerShapelessRecipe(new ItemStack(Item.dyePowder,1,1),false,
                Items.beetroot);
        register.registerShapelessRecipe(new ItemStack(Items.Pulque,1),true,
                Item.sugar,Items.Agave,new ItemStack(Item.potion,1,0)
        ).resetDifficulty(3200);
        register.registerShapelessRecipe(new ItemStack(Items.Ale,1),true,
                Item.sugar,Item.wheat,new ItemStack(Item.potion,1,0)
        ).resetDifficulty(3200);
        register.registerShapelessRecipe(new ItemStack(Items.claybowlRaw,1),false,
                Item.clay);
        register.registerShapelessRecipe(new ItemStack(Items.tungstenBucket,1),false,
                Items.tungstenBucketStone).resetDifficulty(100);
        register.registerShapelessRecipe(new ItemStack(Items.nickelBucket,1),false,
                Items.nickelBucketStone).resetDifficulty(100);
        for(int i = 1;i<=9;++i){
            register.registerShapelessRecipe(new ItemStack(Item.glassBottle,i),false,
                    new ItemStack(Items.SuspiciousPotion,i));
        }


        register.registerShapelessRecipe(new ItemStack(Items.bowlBeetrootSoup,1,0),false,
                Items.beetroot,Items.beetroot,Items.beetroot,
                Items.beetroot,Items.beetroot,Items.beetroot,
                Item.bowlWater
        );
        register.registerShapelessRecipe(new ItemStack(bowlPorkchopStew,1),true,
                Item.bowlWater,Item.porkCooked,Item.carrot,Item.potato,Block.mushroomBrown
        );
        register.registerShapelessRecipe(new ItemStack(bowlChestnutSoup,1),true,
                Item.bowlWater,Item.lambchopCooked,Item.onion,Item.potato
        );
        register.registerShapelessRecipe(new ItemStack(bowlSalmonSoup,1),true,
                Item.fishLargeCooked,Items.beetroot,Block.mushroomBrown,Item.bowlWater
        );
        register.registerShapelessRecipe(new ItemStack(bowlLemonade,1),true,
                Item.sugar,Items.lemon,Item.bowlWater
        );
        register.registerShapelessRecipe(new ItemStack(carrotOnAStickNickel,1),false,
                Item.carrot,Items.fishingRodNickel);
        register.registerShapelessRecipe(new ItemStack(carrotOnAStickTungsten,1),false,
                Item.carrot,Items.fishingRodTungsten);

        for(int i = 1;i<=9;++i){
            register.registerShapelessRecipe(new ItemStack(Item.bowlEmpty,i),false,
                    new ItemStack(Items.bowlWaterSuspicious,i));
            register.registerShapelessRecipe(new ItemStack(Item.bowlEmpty,i),false,
                    new ItemStack(Items.bowlWaterSwampland,i));
        }
        register.registerShapelessRecipe(new ItemStack(Items.claybowlBeefStew),false,
                Item.beefCooked, Block.mushroomBrown, Item.potato, Items.claybowlWater);
        register.registerShapelessRecipe(new ItemStack(Items.claybowlChickenSoup),false,
                Item.chickenCooked, Item.carrot, Item.onion, Items.claybowlWater);
        register.registerShapelessRecipe(new ItemStack(Items.claybowlVegetableSoup),false,
                Item.potato, Item.carrot, Item.onion, Items.claybowlWater);
        register.registerShapelessRecipe(new ItemStack(Items.claybowlIceCream),false,
                Item.chocolate, Items.claybowlMilk, Item.snowball);
        register.registerShapelessRecipe(new ItemStack(Items.claybowlIceCream),false,
                new ItemStack(Item.dyePowder, 1, 3), Item.sugar, Items.claybowlMilk, Item.snowball);
        register.registerShapelessRecipe(new ItemStack(Items.claybowlSalad),false,
                Block.plantYellow, Block.plantYellow, Block.plantYellow, Items.claybowlEmpty);
        register.registerShapelessRecipe(new ItemStack(Items.claybowlCreamOfMushroomSoup),false,
                Block.mushroomBrown, Block.mushroomBrown, Items.claybowlMilk);
        register.registerShapelessRecipe(new ItemStack(Items.claybowlCreamOfVegetableSoup),false,
                Item.potato, Item.carrot, Item.onion, Items.claybowlMilk);
        register.registerShapelessRecipe(new ItemStack(Items.claybowlPumpkinSoup),false,
                Block.pumpkin, Items.claybowlWater);
        register.registerShapelessRecipe(new ItemStack(Items.claybowlMashedPotato),false,
                Item.bakedPotato, Item.cheese, Items.claybowlMilk);
        register.registerShapelessRecipe(new ItemStack(Items.claybowlSorbet),false,
                Item.orange, Item.sugar, Item.snowball, Items.claybowlEmpty);
        register.registerShapelessRecipe(new ItemStack(Items.claybowlPorridge),false,
                Item.seeds, Item.blueberries, Item.sugar, Items.claybowlWater);
        register.registerShapelessRecipe(new ItemStack(Items.claybowlCereal),false,
                Item.wheat, Item.sugar, Items.claybowlMilk);
        register.registerShapelessRecipe(new ItemStack(Items.claybowlMushroomStew),false,
                Block.mushroomBrown, Block.mushroomRed, Items.claybowlWater);
        register.registerShapelessRecipe(new ItemStack(Items.claybowlBeetrootSoup,1,0),false,
                Items.beetroot,Items.beetroot,Items.beetroot,
                Items.beetroot,Items.beetroot,Items.beetroot,
                Items.claybowlWater
        );
        register.registerShapelessRecipe(new ItemStack(claybowlPorkchopStew,1),true,
                Items.claybowlWater,Item.porkCooked,Item.carrot,Item.potato,Block.mushroomBrown
        );
        register.registerShapelessRecipe(new ItemStack(claybowlChestnutSoup,1),true,
                Items.claybowlWater,Item.lambchopCooked,Item.onion,Item.potato
        );
        register.registerShapelessRecipe(new ItemStack(claybowlSalmonSoup,1),true,
                Item.fishLargeCooked,Items.beetroot,Block.mushroomBrown,Items.claybowlWater
        );
        register.registerShapelessRecipe(new ItemStack(claybowlLemonade,1),true,
                Item.sugar,Items.lemon,Items.claybowlWater
        );
        register.registerShapelessRecipe(new ItemStack(Items.ExperimentalPotion,1),true,
                Item.blazePowder,Item.netherStalkSeeds,new ItemStack(Item.potion,1,0),new ItemStack(Item.appleGold,1,0)
        );
        register.registerShapelessRecipe(new ItemStack(Item.cheese, 1),false,
                new ItemStack(Items.claybowlMilk, 4)
        ).resetDifficulty(6400);
        register.registerShapelessRecipe(new ItemStack(Item.cheese, 2),false,
                new ItemStack(Items.claybowlMilk, 8)
        ).resetDifficulty(6400);
        for(int i = 1;i<=9;++i){
            register.registerShapelessRecipe(new ItemStack(Items.claybowlEmpty,i),false,
                    new ItemStack(Items.claybowlWaterSuspicious,i));
            register.registerShapelessRecipe(new ItemStack(Items.claybowlEmpty,i),false,
                    new ItemStack(Items.claybowlWaterSwampland,i));
        }
        yi[] milk_buckets = new yi[]{Item.bucketCopperMilk, Item.bucketSilverMilk, Item.bucketGoldMilk, Item.bucketIronMilk, Item.bucketAncientMetalMilk, Item.bucketMithrilMilk, Item.bucketAdamantiumMilk, Items.tungstenBucketMilk, Items.nickelBucketMilk};
        for(int n = 0; n < milk_buckets.length; n++) {
            register.registerShapelessRecipe(new ItemStack(Item.cake), false,
                    Item.flour, Item.sugar, Item.egg, milk_buckets[n]
            );

            for(int i = 1; i <= 9; ++i) {
                register.registerShapelessRecipe(new ItemStack(Item.cheese, i),false,
                        new ItemStack(milk_buckets[n], i)
                ).resetDifficulty(6400);
            }

            for(int i = 1; i <= 4; ++i) {
                register.registerShapelessRecipe(new ItemStack(Item.bowlMilk, i), true,
                        milk_buckets[n], new ItemStack(Item.bowlEmpty, i)
                ).resetDifficulty(25);
            }
            for(int i = 1; i <= 4; ++i) {
                register.registerShapelessRecipe(new ItemStack(Items.claybowlMilk, i), true,
                        milk_buckets[n], new ItemStack(Items.claybowlEmpty, i)
                ).resetDifficulty(25);
            }
            register.registerShapelessRecipe(new ItemStack(milk_buckets[n]), true,
                    milk_buckets[n].getEmptyVessel(), Item.bowlMilk, Item.bowlMilk, Item.bowlMilk, Item.bowlMilk
            ).resetDifficulty(25);
            register.registerShapelessRecipe(new ItemStack(milk_buckets[n]), true,
                    milk_buckets[n].getEmptyVessel(), Items.claybowlMilk, Items.claybowlMilk, Items.claybowlMilk, Items.claybowlMilk
            ).resetDifficulty(25);
        }

        register.registerShapelessRecipe(new ItemStack(Item.dough, 1),false,
                Item.flour, Item.bowlWater
        );
        register.registerShapelessRecipe(new ItemStack(Item.dough, 1),false,
                Item.flour, Items.claybowlWater
        );
        ItemBucket[] water_buckets = new ItemBucket[]{Item.bucketCopperWater, Item.bucketSilverWater, Item.bucketGoldWater, Item.bucketIronWater, Item.bucketAncientMetalWater, Item.bucketMithrilWater, Item.bucketAdamantiumWater, Items.nickelBucketWater, Items.tungstenBucketWater};
        for(int n = 0; n < water_buckets.length; ++n) {
            for(int i = 1; i <= 4; ++i) {
                register.registerShapelessRecipe(new ItemStack(Item.dough, i), false,
                        water_buckets[n], new ItemStack(Item.flour, i)
                );
                register.registerShapelessRecipe(new ItemStack(Item.cookie, i * 4), false,
                        water_buckets[n], new ItemStack(Item.flour, i), new ItemStack(Item.chocolate, i)
                );
                register.registerShapelessRecipe(new ItemStack(Item.bowlWater, i), true,
                        water_buckets[n], new ItemStack(Item.bowlEmpty, i)
                ).resetDifficulty(25);
                register.registerShapelessRecipe(new ItemStack(Items.claybowlWater, i), true,
                        water_buckets[n], new ItemStack(Items.claybowlEmpty, i)
                ).resetDifficulty(25);
            }

            for(int i = 1; i <= 2; ++i) {
                register.registerShapelessRecipe(new ItemStack(Item.cookie, i * 4), false,
                        water_buckets[n], new ItemStack(Item.flour, i), new ItemStack(Item.dyePowder, i, 3), new ItemStack(Item.sugar, i)
                );
            }

            register.registerShapelessRecipe(new ItemStack(water_buckets[n]), true, water_buckets[n].getEmptyVessel(), new ItemStack(Item.bowlWater, 4)
            ).resetDifficulty(25);
            register.registerShapelessRecipe(new ItemStack(water_buckets[n]), true, water_buckets[n].getEmptyVessel(), new ItemStack(Items.claybowlWater, 4)
            ).resetDifficulty(25);
        }
        ItemBucket[] sus_water_buckets = new ItemBucket[]{Items.copperBucketWaterSuspicious, Items.silverBucketWaterSuspicious, Items.goldBucketWaterSuspicious, Items.ironBucketWaterSuspicious, Items.ancientmetalBucketWaterSuspicious, Items.mithrilBucketWaterSuspicious, Items.adamantiumBucketWaterSuspicious, Items.nickelBucketWaterSuspicious, Items.tungstenBucketWaterSuspicious};
        for(int n = 0; n < sus_water_buckets.length; ++n) {
            for(int i = 1; i <= 4; ++i) {
                register.registerShapelessRecipe(new ItemStack(Items.bowlWaterSuspicious, i), true,
                        sus_water_buckets[n], new ItemStack(Item.bowlEmpty, i)
                ).resetDifficulty(25);
                register.registerShapelessRecipe(new ItemStack(Items.claybowlWaterSuspicious, i), true,
                        sus_water_buckets[n], new ItemStack(Items.claybowlEmpty, i)
                ).resetDifficulty(25);
            }
            register.registerShapelessRecipe(new ItemStack(sus_water_buckets[n]), true, sus_water_buckets[n].getEmptyVessel(), new ItemStack(Items.bowlWaterSuspicious, 4)
            ).resetDifficulty(25);
            register.registerShapelessRecipe(new ItemStack(sus_water_buckets[n]), true, sus_water_buckets[n].getEmptyVessel(), new ItemStack(Items.claybowlWaterSuspicious, 4)
            ).resetDifficulty(25);
        }
        ItemBucket[] smp_water_buckets = new ItemBucket[]{Items.copperBucketWaterSwampland, Items.silverBucketWaterSwampland, Items.goldBucketWaterSwampland, Items.ironBucketWaterSwampland, Items.ancientmetalBucketWaterSwampland, Items.mithrilBucketWaterSwampland, Items.adamantiumBucketWaterSwampland, Items.nickelBucketWaterSwampland, Items.tungstenBucketWaterSwampland};
        for(int n = 0; n < smp_water_buckets.length; ++n) {
            for(int i = 1; i <= 4; ++i) {
                register.registerShapelessRecipe(new ItemStack(Items.bowlWaterSwampland, i), true,
                        smp_water_buckets[n], new ItemStack(Item.bowlEmpty, i)
                ).resetDifficulty(25);
                register.registerShapelessRecipe(new ItemStack(Items.claybowlWaterSwampland, i), true,
                        smp_water_buckets[n], new ItemStack(Items.claybowlEmpty, i)
                ).resetDifficulty(25);
            }
            register.registerShapelessRecipe(new ItemStack(smp_water_buckets[n]), true, smp_water_buckets[n].getEmptyVessel(), new ItemStack(Items.bowlWaterSwampland, 4)
            ).resetDifficulty(25);
            register.registerShapelessRecipe(new ItemStack(smp_water_buckets[n]), true, smp_water_buckets[n].getEmptyVessel(), new ItemStack(Items.claybowlWaterSwampland, 4)
            ).resetDifficulty(25);
        }
        register.registerShapelessRecipe(new ItemStack(Item.cake),false,
                Item.flour, Item.sugar, Item.egg, Items.claybowlMilk);
        ItemCoin[] coins = new ItemCoin[]{
                nickelCoin, tungstenCoin};
        for (ItemCoin coin : coins) {
            for (int plank_subtype = 1; plank_subtype <= 9; ++plank_subtype) {
                register.registerShapelessRecipe(new ItemStack(coin.getNuggetPeer(), plank_subtype), true, new ItemStack(coin, plank_subtype)).resetDifficulty(25);
            }
            register.registerShapelessRecipe(new ItemStack(coin), true, new ItemStack(coin.getNuggetPeer()));
        }


        //RecipesFurnace.smelting().addSmelting(???.itemID, new ItemStack(Items.));
        RecipesFurnace.smelting().addSmelting(pieceAdamantium.itemID, new ItemStack(Items.adamantiumNugget));
        RecipesFurnace.smelting().addSmelting(pieceCopper.itemID, new ItemStack(Items.copperNugget));
        RecipesFurnace.smelting().addSmelting(pieceGold.itemID, new ItemStack(Items.goldNugget));
        RecipesFurnace.smelting().addSmelting(pieceGoldNether.itemID, new ItemStack(Items.goldNugget));
        RecipesFurnace.smelting().addSmelting(pieceSilver.itemID, new ItemStack(Items.silverNugget));
        RecipesFurnace.smelting().addSmelting(pieceIron.itemID, new ItemStack(Items.ironNugget));
        RecipesFurnace.smelting().addSmelting(pieceNickel.itemID, new ItemStack(nickelNugget));
        RecipesFurnace.smelting().addSmelting(pieceMithril.itemID, new ItemStack(Items.mithrilNugget));
        RecipesFurnace.smelting().addSmelting(pieceTungsten.itemID, new ItemStack(tungstenNugget));
        RecipesFurnace.smelting().addSmelting(pieceUru.itemID, new ItemStack(UruNugget));
        RecipesFurnace.smelting().addSmelting(AncientmetalArmorPiece.itemID, new ItemStack(ancientMetalNugget));
//        RecipesFurnace.smelting().addSmelting(bowlWaterSuspicious.itemID, new ItemStack(bowlWater));
//        RecipesFurnace.smelting().addSmelting(bowlWaterSwampland.itemID, new ItemStack(bowlWater));
        RecipesFurnace.smelting().addSmelting(claybowlWaterSuspicious.itemID, new ItemStack(claybowlWater));
        RecipesFurnace.smelting().addSmelting(claybowlWaterSwampland.itemID, new ItemStack(claybowlWater));
        RecipesFurnace.smelting().addSmelting(SuspiciousPotion.itemID, new ItemStack(potion,1,0));
        RecipesFurnace.smelting().addSmelting(horse_meat.itemID, new ItemStack(horse_meat_cooked));
        RecipesFurnace.smelting().addSmelting(claybowlRaw.itemID, new ItemStack(claybowlEmpty));
        RecipesFurnace.smelting().addSmelting(leatherKettleSuspicious.itemID, new ItemStack(leatherKettle));
        RecipesFurnace.smelting().addSmelting(leatherKettleSwampland.itemID, new ItemStack(leatherKettle));
        RecipesFurnace.smelting().addSmelting(hardenedClayJugSuspicious.itemID, new ItemStack(hardenedClayJug));
        RecipesFurnace.smelting().addSmelting(hardenedClayJugSwampland.itemID, new ItemStack(hardenedClayJug));
        RecipesFurnace.smelting().addSmelting(clayJug.itemID, new ItemStack(hardenedClayJug).setItemDamage(hardenedClayJug.getMaxDamage() - 1));
        Class[] tools = new Class[]{ItemSword.class, ItemAxe.class,ItemPickaxe.class,ItemHoe.class, ItemShovel.class, ItemWarHammer.class, ItemBattleAxe.class, ItemScythe.class, ItemDagger.class, ItemKnife.class, ItemMorningStar.class, ItemHatchet.class, ItemShears.class, ItemMattock.class};
        Class[] armors = new Class[]{ItemHelmet.class, ItemBoots.class, ItemLeggings.class, ItemCuirass.class};
        Material[] available_material = new Material[]{Material.copper, Material.silver, Material.gold, Material.iron, Materials.nickel, Materials.tungsten, Material.ancient_metal, Material.rusted_iron};
        for (Class tool : tools) {
            for (Material material : available_material) {
                Item matchingitem = Item.getMatchingItem(tool, material);
                if(matchingitem != null){
                    RecipesFurnace.smelting().addSmelting(matchingitem.itemID, new ItemStack(appleRed));
                }
            }
        }
        for (Class armor : armors){
            for(Material material : available_material) {
                Item matching_plate = ItemArmor.getMatchingArmor(armor, material, false);
                RecipesFurnace.smelting().addSmelting(matching_plate.itemID, new ItemStack(appleRed));
                Item matching_chain = ItemArmor.getMatchingArmor(armor, material, true);
                RecipesFurnace.smelting().addSmelting(matching_chain.itemID, new ItemStack(appleRed));
            }
        }



        ItemFood.setCookingResult((ItemFood) horse_meat, (ItemFood) horse_meat_cooked, 6);
    }
    private static Item register(String resourceLocation, Item item, CreativeModeTab tab) {
        item.setResourceLocation(item.getResourceLocationPrefix() + resourceLocation);
        item.setUnlocalizedName(resourceLocation);
        item.setCreativeTab(tab);
        return item;
    }
    private static Item register(String resourceLocation, Item item) {
        item.setResourceLocation(item.getResourceLocationPrefix() + resourceLocation);
        item.setUnlocalizedName(resourceLocation);
        return item;
    }


}
