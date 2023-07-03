package net.oilcake.mitelros.item;

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
    public static final ItemPieces pieceCopper = (ItemPieces) (new ItemPieces(Constant.getNextItemID(), Materials.orePieces, "pieceCopper").setXPReward(1));
    public static final ItemPieces pieceSilver = (ItemPieces) (new ItemPieces(Constant.getNextItemID(), Materials.orePieces, "pieceSilver").setXPReward(1));
    public static final ItemPieces pieceGold = (ItemPieces) (new ItemPieces(Constant.getNextItemID(), Materials.orePieces, "pieceGold").setXPReward(2));
    public static final ItemPieces pieceGoldNether = (ItemPieces) (new ItemPieces(Constant.getNextItemID(), Materials.orePieces, "pieceGoldNether").setXPReward(2));
    public static final ItemPieces pieceIron = (ItemPieces) (new ItemPieces(Constant.getNextItemID(), Materials.orePieces, "pieceIron").setXPReward(1));
    public static final ItemPieces pieceNickel = (ItemPieces) (new ItemPieces(Constant.getNextItemID(), Materials.orePieces, "pieceNickel").setXPReward(1));
    public static final ItemPieces pieceMithril = (ItemPieces) (new ItemPieces(Constant.getNextItemID(), Materials.orePieces, "pieceMithril").setXPReward(4));
    public static final ItemPieces pieceTungsten = (ItemPieces) (new ItemPieces(Constant.getNextItemID(), Materials.orePieces, "pieceTungsten").setXPReward(7));
    public static final ItemPieces pieceAdamantium = (ItemPieces) (new ItemPieces(Constant.getNextItemID(), Materials.orePieces, "pieceAdamantium").setXPReward(10));
    public static final ItemFood mashedCactus = (ItemFood) (new ItemFood(Constant.getNextItemID(), Materials.mashedCactus,2, 0,false,true,false,"mashedCactus")).setMaxStackSize(4);
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
    public static final Item Wolf_fur = createInstance(ItemNugget.class, new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.wolf_fur).setCraftingDifficultyAsComponent(100.0F).setUnlocalizedName("small_leather").setCreativeTab(CreativeModeTab.tabMaterials).setMaxStackSize(16);
    public static final Item horse_meat = (new ItemMeat(Constant.getNextItemID(),6,6,true,false,"horse_meat"));
    public static final Item horse_meat_cooked = (new ItemMeat(Constant.getNextItemID(),12,12,true,true,"horse_meat_cooked"));
    public static final ItemArmor WolfHelmet = new ItemHelmet(Constant.getNextItemID(),Materials.wolf_fur,false);
    public static final ItemArmor WolfChestplate = new ItemCuirass(Constant.getNextItemID(),Materials.wolf_fur,false);
    public static final ItemArmor WolfLeggings = new ItemLeggings(Constant.getNextItemID(),Materials.wolf_fur,false);
    public static final ItemArmor WolfBoots = new ItemBoots(Constant.getNextItemID(),Materials.wolf_fur,false);
    public static final ItemGoldenApple Goldenapple = (ItemGoldenApple) (new ItemGoldenApple(66, 2, 1, "VANILLA")).setAlwaysEdible().setPotionEffect(MobEffectList.regeneration.id, 30, 0, 1.0F).setUnlocalizedName("appleGold").useVanillaTexture("apple_golden");
    public static final Item Goldenapplelegend = (ItemGoldenAppleLegend)(new ItemGoldenAppleLegend(Constant.getNextItemID(),2,1, "goldapple")).setAlwaysEdible().setPotionEffect(MobEffectList.regeneration.id, 30, 4, 1.0F).setUnlocalizedName("wtfk").useVanillaTexture("apple_golden_legend");
    public static final ItemBowl bowlLemonade = (ItemBowl)new ItemBowl(Constant.getNextItemID(), Materials.lemonade,"lemonade").setFoodValue(4, 1, false, true, true).setPlantProduct().setUnlocalizedName("lemonade");
    public static final ItemBowl bowlEmpty = (ItemBowl)(new ItemBowl(25, (Material)null, "VANILLA")).setUnlocalizedName("bowl").useVanillaTexture("bowl").setMaxStackSize(4);
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
    public static final ItemShardAT shardDiamond = (ItemShardAT) new ItemShardAT(862, Material.diamond).setUnlocalizedName("shardDiamond").setXPReward(4);
    public static final ItemShardAT shardEmerald = (ItemShardAT) (new ItemShardAT(861, Material.emerald)).setUnlocalizedName("shardEmerald").setXPReward(3);
    public static final ItemShardAT shardNetherQuartz = (ItemShardAT) (new ItemShardAT(863, Material.quartz)).setUnlocalizedName("shardNetherQuartz").setXPReward(2);
    public static final ItemRecordExtend recordDamnation = (ItemRecordExtend) new ItemRecordExtend(2024, "imported.damnation", "record_damnation", "Damnation", "Mwk feat. Hatsune Miku").setUnlocalizedName("record");
    public static final ItemRecordExtend recordConnected = (ItemRecordExtend) new ItemRecordExtend(2025, "imported.connected", "record_connected", "Connected", "Mwk feat. Hatsune Miku").setUnlocalizedName("record");
    public static final ItemArmor VibraniumHelmet = new ItemHelmet(Constant.getNextItemID(),Materials.vibranium,false);
    public static final ItemArmor VibraniumChestplate = new ItemCuirass(Constant.getNextItemID(),Materials.vibranium,false);
    public static final ItemArmor VibraniumLeggings = new ItemLeggings(Constant.getNextItemID(),Materials.vibranium,false);
    public static final ItemArmor VibraniumBoots = new ItemBoots(Constant.getNextItemID(),Materials.vibranium,false);
    public static final ItemSword VibraniumSword = createInstance(ItemSword.class,new Class[]{int.class,Material.class},Constant.getNextItemID(), Materials.vibranium);
    public static final ItemRockExtend lapis = (ItemRockExtend) new ItemRockExtend(Constant.getNextItemID(),Material.lapis_lazuli,"lapis_lazuli").setXPReward(5);
    public static final ItemArmor MaidHelmet = new ItemHelmet(Constant.getNextItemID(),Materials.maid,false);
    public static final ItemArmor MaidChestplate = new ItemCuirass(Constant.getNextItemID(),Materials.maid,false);
    public static final ItemArmor MaidLeggings = new ItemLeggings(Constant.getNextItemID(),Materials.maid,false);
    public static final ItemArmor MaidBoots = new ItemBoots(Constant.getNextItemID(),Materials.maid,false);
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
    public static final ItemPieces pieceUru = (ItemPieces) (new ItemPieces(Constant.getNextItemID(), Materials.orePieces, "pieceAdamantium").setXPReward(15));
    public static final Item forgingnote = (ItemStandard) (new ItemStandard(Constant.getNextItemID(),Materials.paper,"forging_note"));
    public static final ItemSeeds seedsBeetroot = new ItemSeeds(Constant.getNextItemID(), 1, 1, false, false,false, Blocks.beetroots.blockID, Block.tilledField.blockID, "Beetrootseeds");
    public static final ItemFood beetroot = (new ItemFood(Constant.getNextItemID(), Materials.beetroot, 2, 1, 1000, false, false, true, "Beetroot")).setPlantProduct();
    public static final ItemBowl bowlSalmonSoup = (ItemBowl)(new ItemBowl(Constant.getNextItemID(), Materials.fish_soup, "salmon_soup")).setFoodValue(14, 14, true, true, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("salmonSoup");
    public static final ItemBowl bowlBeetrootSoup = (ItemBowl) (new ItemBowl(Constant.getNextItemID(), Materials.beetroot, "beetroot_soup")).setFoodValue(15,6,6000,false,true,true).setPlantProduct().setAnimalProduct().setUnlocalizedName("beetrootSoup");
    public static final ItemStandard claybowlRaw = (ItemStandard) new ItemStandard(Constant.getNextItemID(),Material.clay,"bowlclayRaw").setMaxStackSize(4);
    public static final ItemBowlClay claybowlEmpty = (ItemBowlClay) (new ItemBowlClay(Constant.getNextItemID(), null, "VANILLA")).setUnlocalizedName("bowlclay").useVanillaTexture("bowlclay").setMaxStackSize(4);
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
    public static final Item totemofundying = (ItemStandard) (new ItemStandard(Constant.getNextItemID(),Materials.ancient_metal,"totem_of_undying")).setMaxStackSize(1);
    public static final ItemArmor helmetCustom_a = new ItemHelmet(Constant.getNextItemID(),Materials.custom_a,false);
    public static final ItemArmor chestplateCustom_a = new ItemCuirass(Constant.getNextItemID(),Materials.custom_a,false);
    public static final ItemArmor leggingsCustom_a = new ItemLeggings(Constant.getNextItemID(),Materials.custom_a,false);
    public static final ItemArmor bootsCustom_a = new ItemBoots(Constant.getNextItemID(),Materials.custom_a,false);
    public static final ItemFishingRod fishingRodNickel = (ItemFishingRod)(new ItemFishingRod(Constant.getNextItemID(), Materials.nickel)).setUnlocalizedName("fishingRod");
    public static final ItemFishingRod fishingRodTungsten = (ItemFishingRod)(new ItemFishingRod(Constant.getNextItemID(), Materials.tungsten)).setUnlocalizedName("fishingRod");
    public static final ItemCarrotStick carrotOnAStickNickel = (ItemCarrotStick) (new ItemCarrotStick(Constant.getNextItemID(), Materials.nickel)).setUnlocalizedName("carrotOnAStick");
    public static final ItemCarrotStick carrotOnAStickTungsten = (ItemCarrotStick) (new ItemCarrotStick(Constant.getNextItemID(), Materials.tungsten)).setUnlocalizedName("carrotOnAStick");
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
        register("ingot/nickel", nickelIngot);
        register("ingot/nugget/nickel", nickelNugget);
        register("tool/nickel/nickel_axe", nickelAxe);
        register("tool/nickel/nickel_battle_axe", nickelBattleAxe);
        register("tool/nickel/nickel_dagger", nickelDagger);
        register("tool/nickel/nickel_hatchet", nickelHatchet);
        register("tool/nickel/nickel_hoe", nickelHoe);
        register("tool/nickel/nickel_knife", nickelKnife);
        register("tool/nickel/nickel_pickaxe", nickelPickaxe);
        register("tool/nickel/nickel_mattock", nickelMattock);
        register("tool/nickel/nickel_scythe", nickelScythe);
        register("tool/nickel/nickel_shears", nickelShears);
        register("tool/nickel/nickel_shovel", nickelShovel);
        register("tool/nickel/nickel_sword", nickelSword);
        register("tool/nickel/nickel_war_hammer", nickelWarHammer);
        register("door/nickel", doorNickel);
        register("chain/nickel", nickelChain);
        register("coin/nickel", nickelCoin);
        register("arrows/nickel_arrow", arrowNickel);
        register("armor/tungsten_helmet", tungstenHelmet);
        register("armor/tungsten_chestplate", tungstenChestplate);
        register("armor/tungsten_leggings", tungstenLeggings);
        register("armor/tungsten_boots", tungstenBoots);
        register("armor/tungsten_chainmail_helmet", tungstenHelmetChain);
        register("armor/tungsten_chainmail_chestplate", tungstenChestplateChain);
        register("armor/tungsten_chainmail_leggings", tungstenLeggingsChain);
        register("armor/tungsten_chainmail_boots", tungstenBootsChain);
        register("ingot/tungsten", tungstenIngot);
        register("ingot/nugget/tungsten", tungstenNugget);
        register("tool/tungsten/tungsten_axe", tungstenAxe);
        register("tool/tungsten/tungsten_battle_axe", tungstenBattleAxe);
        register("tool/tungsten/tungsten_dagger", tungstenDagger);
        register("tool/tungsten/tungsten_hatchet", tungstenHatchet);
        register("tool/tungsten/tungsten_hoe", tungstenHoe);
        register("tool/tungsten/tungsten_knife", tungstenKnife);
        register("tool/tungsten/tungsten_pickaxe", tungstenPickaxe);
        register("tool/tungsten/tungsten_mattock", tungstenMattock);
        register("tool/tungsten/tungsten_scythe", tungstenScythe);
        register("tool/tungsten/tungsten_shears", tungstenShears);
        register("tool/tungsten/tungsten_shovel", tungstenShovel);
        register("tool/tungsten/tungsten_sword", tungstenSword);
        register("tool/tungsten/tungsten_war_hammer", tungstenWarHammer);
        register("door/tungsten", doorTungsten);
        register("chain/tungsten", tungstenChain);
        register("coin/tungsten", tungstenCoin);
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
        register("tool/copper/copper_club",morningStarCopper);
        register("tool/silver/silver_club",morningStarSilver);
        register("tool/gold/gold_club",morningStarGold);
        register("tool/iron/iron_club",morningStarIron);
        register("tool/nickel/nickel_club",morningStarNickel);
        register("tool/ancient_metal/ancient_metal_club",morningStarAncientMetal);
        register("tool/mithril/mithril_club",morningStarMithril);
        register("tool/tungsten/tungsten_club",morningStarTungsten);
        register("tool/adamantium/adamantium_club",morningStarAdamantium);
        register("frag/stalker_creeper",fragStalkerCreeper);
        register("food/glow_berries",glowberries);
        register("arrows/magical_arrow", arrowMagical);
        register("wand/lava", LavaWand);
        register("wand/ice", FreezeWand);
        register("wand/thunder", ShockWand);
        register("experimental_potion",ExperimentalPotion);
        register("shards/diamond",shardDiamond);
        register("shards/emerald",shardEmerald);
        register("shards/quartz",shardNetherQuartz);
        register("records/record_damnation",recordDamnation);
        register("records/record_connected",recordConnected);
        register("lapis_lazuli",lapis);
        register("tool/vibranium/vibranium_sword",VibraniumSword);
        register("armor/vibranium_helmet",VibraniumHelmet);
        register("armor/vibranium_chestplate",VibraniumChestplate);
        register("armor/vibranium_leggings",VibraniumLeggings);
        register("armor/vibranium_boots",VibraniumBoots);
        register("armor/maid_helmet",MaidHelmet);
        register("armor/maid_chestplate",MaidChestplate);
        register("armor/maid_leggings",MaidLeggings);
        register("armor/maid_boots",MaidBoots);
        register("armor/maid_helmet",helmetCustom_a);
        register("armor/maid_chestplate",chestplateCustom_a);
        register("armor/maid_leggings",leggingsCustom_a);
        register("armor/maid_boots",bootsCustom_a);
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
        register("ingot/uru", UruIngot);
        register("ingot/nugget/uru", UruNugget);
        register("tool/uru/uru_battle_axe", UruBattleAxe);
        register("tool/uru/uru_mattock", UruMattock);
        register("tool/uru/uru_scythe", UruScythe);
        register("tool/uru/uru_sword", UruSword);
        register("tool/uru/uru_war_hammer", UruWarHammer);
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
        register("totem_of_undying",totemofundying);
        Constant.initItemArray();
    }

    public static void registerRecipes(RecipeRegister register) {
        register.registerShapelessRecipe(new ItemStack(lemonPie), true,
                Item.sugar, Item.egg, Item.flour, lemon);
        register.registerShapedRecipe(new ItemStack(arrowNickel), true,
                " C ",
                " B ",
                " A ",
                'A', Item.feather,
                'B', Item.stick,
                'C', nickelNugget);
        register.registerShapedRecipe(new ItemStack(nickelChain), true,
                " A ",
                "A A",
                " A ",
                'A', nickelNugget);
        register.registerShapedRecipe(new ItemStack(nickelShears), true,
                " A ",
                "  A",
                'A', nickelIngot);
        register.registerShapedRecipe(new ItemStack(nickelBucket), true,
                "A A",
                " A ",
                'A', nickelIngot);
//        register.registerShapedRecipe(new ItemStack(nickelShears), true,
//                "  ",
//                "  A",
//                " A ",
//                'A', nickelIngot);
        register.registerShapedRecipe(new ItemStack(nickelScythe), true,
                "BA ",
                "B A",
                "B  ",
                'A', nickelIngot,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(nickelMattock), true,
                "AAA",
                " BA",
                " B ",
                'A', nickelIngot,
                'B', Item.stick);
//        register.registerShapedRecipe(new ItemStack(nickelKnife), true,
//                "AA",
//                " B ",
//                " B ",
//                'A', nickelIngot,
//                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(nickelHoe), true,
                "AA ",
                " B ",
                " B ",
                'A', nickelIngot,
                'B', Item.stick);
//        register.registerShapedRecipe(new ItemStack(nickelHoe), true,
//                " AA",
//                " B ",
//                " B ",
//                'A', nickelIngot,
//                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(nickelHatchet), true,
                " BA",
                " B ",
                'A', nickelIngot,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(nickelWarHammer), true,
                "AAA",
                "ABA",
                " B ",
                'A', nickelIngot,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(nickelSword), true,
                " A ",
                " A ",
                " B ",
                'A', nickelIngot,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(nickelShovel), true,
                " A ",
                " B ",
                " B ",
                'A', nickelIngot,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(nickelPickaxe), true,
                "AAA",
                " B ",
                " B ",
                'A', nickelIngot,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(nickelDagger), true,
                " A ",
                " B ",
                'A', nickelIngot,
                'B', Item.stick);
//        register.registerShapedRecipe(new ItemStack(nickelDagger), true,
//                "   ",
//                " A ",
//                " B ",
//                'A', nickelIngot,
//                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(nickelBattleAxe), true,
                "A A",
                "ABA",
                " B ",
                'A', nickelIngot,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(nickelAxe), true,
                "AA ",
                "AB ",
                " B ",
                'A', nickelIngot,
                'B', Item.stick);
//        register.registerShapedRecipe(new ItemStack(nickelAxe), true,
//                " AA",
//                " BA",
//                " B ",
//                'A', nickelIngot,
//                'B', Item.stick);
        register.registerShapelessRecipe(new ItemStack(nickelIngot, 9), true,
                Blocks.blockNickel);
        register.registerShapelessRecipe(new ItemStack(nickelNugget, 9), true,
                nickelIngot);
//        register.registerShapedRecipe(new ItemStack(nickelBootsChain), true,
//                "A A",
//                "A A",
//                "   ",
//                'A', nickelChain);
        register.registerShapedRecipe(new ItemStack(nickelBootsChain), true,
                "A A",
                "A A",
                'A', nickelChain);
        register.registerShapedRecipe(new ItemStack(nickelLeggingsChain), true,
                "AAA",
                "A A",
                "A A",
                'A', nickelChain);
        register.registerShapedRecipe(new ItemStack(nickelChestplateChain), true,
                "A A",
                "AAA",
                "AAA",
                'A', nickelChain);
        register.registerShapedRecipe(new ItemStack(nickelHelmetChain), true,
                "AAA",
                "A A",
                'A', nickelChain);
//        register.registerShapedRecipe(new ItemStack(nickelHelmetChain), true,
//                "   ",
//                "AAA",
//                "A A",
//                'A', nickelChain);
//        register.registerShapedRecipe(new ItemStack(nickelBoots), true,
//                "A A",
//                "A A",
//                "   ",
//                'A', nickelIngot);
        register.registerShapedRecipe(new ItemStack(nickelBoots), true,
                "A A",
                "A A",
                'A', nickelIngot);
        register.registerShapedRecipe(new ItemStack(nickelLeggings), true,
                "AAA",
                "A A",
                "A A",
                'A', nickelIngot);
        register.registerShapedRecipe(new ItemStack(nickelChestplate), true,
                "A A",
                "AAA",
                "AAA",
                'A', nickelIngot);
//        register.registerShapedRecipe(new ItemStack(nickelHelmet), true,
//                "AAA",
//                "A A",
//                "   ",
//                'A', nickelIngot);
        register.registerShapedRecipe(new ItemStack(nickelHelmet), true,
                "AAA",
                "A A",
                'A', nickelIngot);
        register.registerShapedRecipe(new ItemStack(doorNickel), true,
                " AA",
                " AA",
                " AA",
                'A', nickelIngot);
        register.registerShapedRecipe(new ItemStack(arrowTungsten), true,
                " C ",
                " B ",
                " A ",
                'A', Item.feather,
                'B', Item.stick,
                'C', tungstenNugget);
        register.registerShapedRecipe(new ItemStack(tungstenChain), true,
                " A ",
                "A A",
                " A ",
                'A', tungstenNugget);
        register.registerShapedRecipe(new ItemStack(tungstenShears), true,
                " A ",
                "  A",
                'A', tungstenIngot);
        register.registerShapedRecipe(new ItemStack(tungstenScythe), true,
                "BA ",
                "B A",
                "B  ",
                'A', tungstenIngot,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(tungstenMattock), true,
                "AAA",
                " BA",
                " B ",
                'A', tungstenIngot,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(tungstenHoe), true,
                "AA ",
                " B ",
                " B ",
                'A', tungstenIngot,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(tungstenHatchet), true,
                " BA",
                " B ",
                'A', tungstenIngot,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(tungstenWarHammer), true,
                "AAA",
                "ABA",
                " B ",
                'A', tungstenIngot,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(tungstenSword), true,
                " A ",
                " A ",
                " B ",
                'A', tungstenIngot,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(tungstenShovel), true,
                " A ",
                " B ",
                " B ",
                'A', tungstenIngot,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(tungstenPickaxe), true,
                "AAA",
                " B ",
                " B ",
                'A', tungstenIngot,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(tungstenDagger), true,
                " A ",
                " B ",
                'A', tungstenIngot,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(tungstenBattleAxe), true,
                "A A",
                "ABA",
                " B ",
                'A', tungstenIngot,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(tungstenAxe), true,
                "AA ",
                "AB ",
                " B ",
                'A', tungstenIngot,
                'B', Item.stick);
        register.registerShapelessRecipe(new ItemStack(tungstenIngot, 9), true,
                Blocks.blockTungsten);
        register.registerShapelessRecipe(new ItemStack(tungstenNugget, 9), true,
                tungstenIngot);
        register.registerShapedRecipe(new ItemStack(tungstenBootsChain), true,
                "A A",
                "A A",
                'A', tungstenChain);
        register.registerShapedRecipe(new ItemStack(tungstenLeggingsChain), true,
                "AAA",
                "A A",
                "A A",
                'A', tungstenChain);
        register.registerShapedRecipe(new ItemStack(tungstenBucket), true,
                "A A",
                " A ",
                'A', tungstenIngot);
        register.registerShapedRecipe(new ItemStack(tungstenChestplateChain), true,
                "A A",
                "AAA",
                "AAA",
                'A', tungstenChain);
        register.registerShapedRecipe(new ItemStack(tungstenHelmetChain), true,
                "AAA",
                "A A",
                'A', tungstenChain);
        register.registerShapedRecipe(new ItemStack(tungstenBoots), true,
                "A A",
                "A A",
                'A', tungstenIngot);
        register.registerShapedRecipe(new ItemStack(tungstenLeggings), true,
                "AAA",
                "A A",
                "A A",
                'A', tungstenIngot);
        register.registerShapedRecipe(new ItemStack(tungstenChestplate), true,
                "A A",
                "AAA",
                "AAA",
                'A', tungstenIngot);
        register.registerShapedRecipe(new ItemStack(tungstenHelmet), true,
                "AAA",
                "A A",
                'A', tungstenIngot);
        register.registerShapedRecipe(new ItemStack(doorTungsten), true,
                " AA",
                " AA",
                " AA",
                'A', tungstenIngot);
        register.registerShapedRecipe(new ItemStack(WolfHelmet),true,
                "AAA",
                "ABA",
                'A',Wolf_fur,
                'B',helmetLeather);
        register.registerShapedRecipe(new ItemStack(WolfChestplate),true,
                "A A",
                "ABA",
                "AAA",
                'A',Wolf_fur,
                'B',plateLeather);
        register.registerShapedRecipe(new ItemStack(WolfLeggings),true,
                "AAA",
                "ABA",
                "A A",
                'A',Wolf_fur,
                'B',legsLeather);

        register.registerShapedRecipe(new ItemStack(WolfBoots),true,
                "ABA",
                "A A",
                'A',Wolf_fur,
                'B',bootsLeather);
        register.registerShapedRecipe(new ItemStack(Goldenapplelegend),true,
                "AAA",
                "ASA",
                "AAA",
                'A',Block.blockGold,
                'S',Item.appleRed
        );
        register.registerShapedRecipe(new ItemStack(morningStarCopper, 1), true,
                "###",
                "#*#",
                " # ",
                '#', copperNugget,
                '*', ingotCopper);
        register.registerShapedRecipe(new ItemStack(morningStarSilver, 1), true,
                "###",
                "#*#",
                " # ",
                '#', silverNugget,
                '*', ingotSilver);
        register.registerShapedRecipe(new ItemStack(morningStarGold, 1), true,
                "###",
                "#*#",
                " # ",
                '#', goldNugget,
                '*', ingotGold);
        register.registerShapedRecipe(new ItemStack(morningStarIron, 1), true,
                "###",
                "#*#",
                " # ",
                '#', ironNugget,
                '*', ingotIron);
        register.registerShapedRecipe(new ItemStack(morningStarNickel, 1), true,
                "###",
                "#*#",
                " # ",
                '#', nickelNugget,
                '*', nickelIngot);
        register.registerShapedRecipe(new ItemStack(morningStarAncientMetal, 1), true,
                "###",
                "#*#",
                " # ",
                '#', ancientMetalNugget,
                '*', ingotAncientMetal);
        register.registerShapedRecipe(new ItemStack(morningStarMithril, 1), true,
                "###",
                "#*#",
                " # ",
                '#', mithrilNugget,
                '*', ingotMithril);
        register.registerShapedRecipe(new ItemStack(morningStarTungsten, 1), true,
                "###",
                "#*#",
                " # ",
                '#', tungstenNugget,
                '*', tungstenIngot);
        register.registerShapedRecipe(new ItemStack(morningStarAdamantium, 1), true,
                "###",
                "#*#",
                " # ",
                '#', adamantiumNugget,
                '*', ingotAdamantium);
        register.registerShapedRecipe(new ItemStack(bowTungsten, 1), true,
                "#C ",
                "#EC",
                "#C ",
                '#', silk,
                'E', tungstenIngot,
                'C', stick);
        register.registerShapedRecipe(new ItemStack(fishingRodNickel, 1), true,
                "  C",
                " C#",
                "CE#",
                '#', silk,
                'E', nickelNugget,
                'C', stick);
        register.registerShapedRecipe(new ItemStack(fishingRodTungsten, 1), true,
                "  C",
                " C#",
                "CE#",
                '#', silk,
                'E', tungstenNugget,
                'C', stick);
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
        register.registerShapelessRecipe(new ItemStack(Item.leather,1),true,
                Items.Wolf_fur,Items.Wolf_fur,Items.Wolf_fur,Items.Wolf_fur
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
                        water_buckets[n], new ItemStack(Item.bowlEmpty, i)
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
                        sus_water_buckets[n], new ItemStack(Item.bowlEmpty, i)
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
                        smp_water_buckets[n], new ItemStack(Item.bowlEmpty, i)
                ).resetDifficulty(25);
            }
            register.registerShapelessRecipe(new ItemStack(smp_water_buckets[n]), true, smp_water_buckets[n].getEmptyVessel(), new ItemStack(Items.bowlWaterSwampland, 4)
            ).resetDifficulty(25);
            register.registerShapelessRecipe(new ItemStack(smp_water_buckets[n]), true, smp_water_buckets[n].getEmptyVessel(), new ItemStack(Items.claybowlWaterSwampland, 4)
            ).resetDifficulty(25);
        }
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
        RecipesFurnace.smelting().addSmelting(bowlWaterSuspicious.itemID, new ItemStack(bowlWater));
        RecipesFurnace.smelting().addSmelting(bowlWaterSwampland.itemID, new ItemStack(bowlWater));
        RecipesFurnace.smelting().addSmelting(claybowlWaterSuspicious.itemID, new ItemStack(claybowlWater));
        RecipesFurnace.smelting().addSmelting(claybowlWaterSwampland.itemID, new ItemStack(claybowlWater));
        RecipesFurnace.smelting().addSmelting(horse_meat.itemID, new ItemStack(horse_meat_cooked));
        RecipesFurnace.smelting().addSmelting(claybowlRaw.itemID, new ItemStack(claybowlEmpty));
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
