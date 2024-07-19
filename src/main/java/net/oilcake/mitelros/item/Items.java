package net.oilcake.mitelros.item;

import net.minecraft.Block;
import net.minecraft.CreativeTabs;
import net.minecraft.FurnaceRecipes;
import net.minecraft.Item;
import net.minecraft.ItemAppleGold;
import net.minecraft.ItemArmor;
import net.minecraft.ItemArrow;
import net.minecraft.ItemAxe;
import net.minecraft.ItemBattleAxe;
import net.minecraft.ItemBoots;
import net.minecraft.ItemBow;
import net.minecraft.ItemBowl;
import net.minecraft.ItemBucket;
import net.minecraft.ItemBucketMilk;
import net.minecraft.ItemCarrotOnAStick;
import net.minecraft.ItemChain;
import net.minecraft.ItemClub;
import net.minecraft.ItemCoin;
import net.minecraft.ItemCuirass;
import net.minecraft.ItemDagger;
import net.minecraft.ItemDoor;
import net.minecraft.ItemFishingRod;
import net.minecraft.ItemFood;
import net.minecraft.ItemHatchet;
import net.minecraft.ItemHelmet;
import net.minecraft.ItemHoe;
import net.minecraft.ItemIngot;
import net.minecraft.ItemKnife;
import net.minecraft.ItemLeggings;
import net.minecraft.ItemMattock;
import net.minecraft.ItemMeat;
import net.minecraft.ItemNugget;
import net.minecraft.ItemPickaxe;
import net.minecraft.ItemScythe;
import net.minecraft.ItemSeeds;
import net.minecraft.ItemShears;
import net.minecraft.ItemShovel;
import net.minecraft.ItemStack;
import net.minecraft.ItemSword;
import net.minecraft.ItemWarHammer;
import net.minecraft.Material;
import net.minecraft.Potion;
import net.oilcake.mitelros.block.Blocks;
import net.xiaoyu233.fml.reload.event.ItemRegistryEvent;
import net.xiaoyu233.fml.reload.event.RecipeRegistryEvent;
import net.xiaoyu233.fml.reload.utils.IdUtil;
import net.xiaoyu233.fml.util.ReflectHelper;

public class Items extends Item {
   public static final ItemArmor nickelHelmet;
   public static final ItemArmor nickelChestplate;
   public static final ItemArmor nickelLeggings;
   public static final ItemArmor nickelBoots;
   public static final ItemArmor nickelHelmetChain;
   public static final ItemArmor nickelChestplateChain;
   public static final ItemArmor nickelLeggingsChain;
   public static final ItemArmor nickelBootsChain;
   public static final ItemNugget nickelNugget;
   public static final ItemAxe nickelAxe;
   public static final ItemBattleAxe nickelBattleAxe;
   public static final ItemDagger nickelDagger;
   public static final Item nickelIngot;
   public static final ItemPickaxe nickelPickaxe;
   public static final ItemShovel nickelShovel;
   public static final ItemSword nickelSword;
   public static final ItemWarHammer nickelWarHammer;
   public static final ItemHatchet nickelHatchet;
   public static final ItemHoe nickelHoe;
   public static final ItemKnife nickelKnife;
   public static final ItemMattock nickelMattock;
   public static final ItemScythe nickelScythe;
   public static final ItemShears nickelShears;
   public static final Item doorNickel;
   public static final ItemChain nickelChain;
   public static final ItemCoin nickelCoin;
   public static final ItemArrow arrowNickel;
   public static final ItemArmor tungstenHelmet;
   public static final ItemArmor tungstenChestplate;
   public static final ItemArmor tungstenLeggings;
   public static final ItemArmor tungstenBoots;
   public static final ItemArmor tungstenHelmetChain;
   public static final ItemArmor tungstenChestplateChain;
   public static final ItemArmor tungstenLeggingsChain;
   public static final ItemArmor tungstenBootsChain;
   public static final ItemNugget tungstenNugget;
   public static final ItemAxe tungstenAxe;
   public static final ItemBattleAxe tungstenBattleAxe;
   public static final ItemDagger tungstenDagger;
   public static final Item tungstenIngot;
   public static final ItemPickaxe tungstenPickaxe;
   public static final ItemShovel tungstenShovel;
   public static final ItemSword tungstenSword;
   public static final ItemWarHammer tungstenWarHammer;
   public static final ItemHatchet tungstenHatchet;
   public static final ItemHoe tungstenHoe;
   public static final ItemKnife tungstenKnife;
   public static final ItemMattock tungstenMattock;
   public static final ItemScythe tungstenScythe;
   public static final ItemShears tungstenShears;
   public static final Item doorTungsten;
   public static final ItemChain tungstenChain;
   public static final ItemCoin tungstenCoin;
   public static final ItemArrow arrowTungsten;
   public static final ItemBowl bowlPorkchopStew;
   public static final ItemBowl bowlChestnutSoup;
   public static final ItemBowl bowlWaterSuspicious;
   public static final ItemBowl bowlWaterSwampland;
   public static final ItemPieces pieceCopper;
   public static final ItemPieces pieceSilver;
   public static final ItemPieces pieceGold;
   public static final ItemPieces pieceGoldNether;
   public static final ItemPieces pieceIron;
   public static final ItemPieces pieceNickel;
   public static final ItemPieces pieceMithril;
   public static final ItemPieces pieceTungsten;
   public static final ItemPieces pieceAdamantium;
   public static final ItemFood mashedCactus;
   public static final ItemFood lemon;
   public static final Item lemonPie;
   public static final ItemBucket nickelBucket;
   public static final ItemBucket nickelBucketWater;
   public static final ItemBucket nickelBucketLava;
   public static final ItemBucket nickelBucketStone;
   public static final ItemBucketMilk nickelBucketMilk;
   public static final ItemBucket tungstenBucket;
   public static final ItemBucket tungstenBucketWater;
   public static final ItemBucket tungstenBucketLava;
   public static final ItemBucket tungstenBucketStone;
   public static final ItemBucketMilk tungstenBucketMilk;
   public static final ItemBucket copperBucketWaterSuspicious;
   public static final ItemBucket silverBucketWaterSuspicious;
   public static final ItemBucket goldBucketWaterSuspicious;
   public static final ItemBucket ironBucketWaterSuspicious;
   public static final ItemBucket nickelBucketWaterSuspicious;
   public static final ItemBucket ancientmetalBucketWaterSuspicious;
   public static final ItemBucket mithrilBucketWaterSuspicious;
   public static final ItemBucket tungstenBucketWaterSuspicious;
   public static final ItemBucket adamantiumBucketWaterSuspicious;
   public static final ItemBucket copperBucketWaterSwampland;
   public static final ItemBucket silverBucketWaterSwampland;
   public static final ItemBucket goldBucketWaterSwampland;
   public static final ItemBucket ironBucketWaterSwampland;
   public static final ItemBucket nickelBucketWaterSwampland;
   public static final ItemBucket ancientmetalBucketWaterSwampland;
   public static final ItemBucket mithrilBucketWaterSwampland;
   public static final ItemBucket tungstenBucketWaterSwampland;
   public static final ItemBucket adamantiumBucketWaterSwampland;
   public static final Item Wolf_fur;
   public static final Item horse_meat;
   public static final Item horse_meat_cooked;
   public static final ItemArmor WolfHelmet;
   public static final ItemArmor WolfChestplate;
   public static final ItemArmor WolfLeggings;
   public static final ItemArmor WolfBoots;
   public static final ItemAppleGold Goldenapple;
   public static final Item Goldenapplelegend;
   public static final ItemBowl bowlLemonade;
   public static final ItemBowl bowlEmpty;
   public static final ItemMorningStar morningStarCopper;
   public static final ItemMorningStar morningStarSilver;
   public static final ItemMorningStar morningStarGold;
   public static final ItemMorningStar morningStarIron;
   public static final ItemMorningStar morningStarNickel;
   public static final ItemMorningStar morningStarAncientMetal;
   public static final ItemMorningStar morningStarMithril;
   public static final ItemMorningStar morningStarTungsten;
   public static final ItemMorningStar morningStarAdamantium;
   public static final Item fragStalkerCreeper;
   public static final ItemFood glowberries;
   public static final ItemArrow arrowMagical;
   public static final ItemWand LavaWand;
   public static final ItemWand FreezeWand;
   public static final ItemWand ShockWand;
   public static final Item ExperimentalPotion;
   public static final ItemRecordExtend recordDamnation;
   public static final ItemRecordExtend recordConnected;
   public static final ItemArmor VibraniumHelmet;
   public static final ItemArmor VibraniumChestplate;
   public static final ItemArmor VibraniumLeggings;
   public static final ItemArmor VibraniumBoots;
   public static final ItemSword VibraniumSword;
   public static final ItemRockExtend lapis;
   public static final ItemArmor helmetCustom_a;
   public static final ItemArmor chestplateCustom_a;
   public static final ItemArmor leggingsCustom_a;
   public static final ItemArmor bootsCustom_a;
   public static final ItemArmor HelmetAncientmetalsacred;
   public static final ItemArmor ChestplateAncientmetalsacred;
   public static final ItemArmor LeggingsAncientmetalsacred;
   public static final ItemArmor BootsAncientmetalsacred;
   public static final Item AncientmetalArmorPiece;
   public static final ItemFood Agave;
   public static final Item Pulque;
   public static final Item Ale;
   public static final ItemBow bowTungsten;
   public static final ItemArmor UruHelmet;
   public static final ItemArmor UruChestplate;
   public static final ItemArmor UruLeggings;
   public static final ItemArmor UruBoots;
   public static final ItemNugget UruNugget;
   public static final ItemBattleAxe UruBattleAxe;
   public static final Item UruIngot;
   public static final ItemSword UruSword;
   public static final ItemWarHammer UruWarHammer;
   public static final ItemMattock UruMattock;
   public static final ItemScythe UruScythe;
   public static final ItemPieces pieceUru;
   public static final Item forgingnote;
   public static final ItemSeeds seedsBeetroot;
   public static final ItemFood beetroot;
   public static final ItemBowl bowlSalmonSoup;
   public static final ItemBowl bowlBeetrootSoup;
   public static final ItemStandard claybowlRaw;
   public static final ItemBowlClay claybowlEmpty;
   public static final ItemBowlClay claybowlMushroomStew;
   public static final ItemBowlClay claybowlMilk;
   public static final ItemBowlClay claybowlWater;
   public static final ItemBowlClay claybowlBeefStew;
   public static final ItemBowlClay claybowlChickenSoup;
   public static final ItemBowlClay claybowlVegetableSoup;
   public static final ItemBowlClay claybowlIceCream;
   public static final ItemBowlClay claybowlSalad;
   public static final ItemBowlClay claybowlCreamOfMushroomSoup;
   public static final ItemBowlClay claybowlCreamOfVegetableSoup;
   public static final ItemBowlClay claybowlPumpkinSoup;
   public static final ItemBowlClay claybowlMashedPotato;
   public static final ItemBowlClay claybowlSorbet;
   public static final ItemBowlClay claybowlPorridge;
   public static final ItemBowlClay claybowlCereal;
   public static final ItemBowlClay claybowlLemonade;
   public static final ItemBowlClay claybowlPorkchopStew;
   public static final ItemBowlClay claybowlChestnutSoup;
   public static final ItemBowlClay claybowlWaterSuspicious;
   public static final ItemBowlClay claybowlWaterSwampland;
   public static final ItemBowlClay claybowlSalmonSoup;
   public static final ItemBowlClay claybowlBeetrootSoup;
   public static final Item totemoffecund;
   public static final ItemArmor helmetCustom_b;
   public static final ItemArmor chestplateCustom_b;
   public static final ItemArmor leggingsCustom_b;
   public static final ItemArmor bootsCustom_b;
   public static final ItemFishingRod fishingRodNickel;
   public static final ItemFishingRod fishingRodTungsten;
   public static final ItemCarrotOnAStick carrotOnAStickNickel;
   public static final ItemCarrotOnAStick carrotOnAStickTungsten;
   public static final ItemPotionSuspicious SuspiciousPotion;
   public static final Item totemofdestroy;
   public static final Item totemofpreserve;
   public static final Item totemofknowledge;
   public static final ItemIgnition ignitionCopper;
   public static final ItemIgnition ignitionSilver;
   public static final ItemIgnition ignitionGold;
   public static final ItemIgnition ignitionIron;
   public static final ItemIgnition ignitionNickel;
   public static final ItemIgnition ignitionTungsten;
   public static final ItemIgnition ignitionMithril;
   public static final ItemIgnition ignitionAncientMetal;
   public static final ItemIgnition ignitionAdamantium;
   public static final ItemIgnition ignitionWood;
   public static final ItemBrewingMisc wither_branch;
   public static final ItemGuideBook guide;
   public static final Item totemofhunting;
   public static final ItemClub UruMorningStar;
   public static final ItemPickaxe UruPickaxe;
   public static final ItemRockExtend shardAzurite;
   public static final Item detectorEmerald;
   public static final Item detectorDiamond;
   public static final Item sulphur;
   public static final Item hellhoundFur;
   public static final ItemArmor HellhoundHelmet;
   public static final ItemArmor HellhoundChestplate;
   public static final ItemArmor HellhoundLeggings;
   public static final ItemArmor HellhoundBoots;
   public static final ItemKettle leatherKettle;
   public static final ItemKettle leatherKettleSuspicious;
   public static final ItemKettle leatherKettleSwampland;
   public static final ItemStandard clayJug;
   public static final ItemKettle hardenedClayJug;
   public static final ItemKettle hardenedClayJugSuspicious;
   public static final ItemKettle hardenedClayJugSwampland;

   public static Item register(ItemRegistryEvent event, String name, Item item) {
      return event.register(name, name, item);
   }


   public static void registerItems(ItemRegistryEvent event) {
      register(event,"armor/nickel_helmet", nickelHelmet);
      register(event,"armor/nickel_chestplate", nickelChestplate);
      register(event,"armor/nickel_leggings", nickelLeggings);
      register(event,"armor/nickel_boots", nickelBoots);
      register(event,"armor/nickel_chainmail_helmet", nickelHelmetChain);
      register(event,"armor/nickel_chainmail_chestplate", nickelChestplateChain);
      register(event,"armor/nickel_chainmail_leggings", nickelLeggingsChain);
      register(event,"armor/nickel_chainmail_boots", nickelBootsChain);
      register(event,"ingots/nickel", nickelIngot);
      register(event,"nuggets/nickel", nickelNugget);
      register(event,"tools/nickel_axe", nickelAxe);
      register(event,"tools/nickel_battle_axe", nickelBattleAxe);
      register(event,"tools/nickel_dagger", nickelDagger);
      register(event,"tools/nickel_hatchet", nickelHatchet);
      register(event,"tools/nickel_hoe", nickelHoe);
      register(event,"tools/nickel_knife", nickelKnife);
      register(event,"tools/nickel_pickaxe", nickelPickaxe);
      register(event,"tools/nickel_mattock", nickelMattock);
      register(event,"tools/nickel_scythe", nickelScythe);
      register(event,"tools/nickel_shears", nickelShears);
      register(event,"tools/nickel_shovel", nickelShovel);
      register(event,"tools/nickel_sword", nickelSword);
      register(event,"tools/nickel_war_hammer", nickelWarHammer);
      register(event,"doors/nickel", doorNickel);
      register(event,"chains/nickel", nickelChain);
      register(event,"coins/nickel", nickelCoin);
      register(event,"arrows/nickel_arrow", arrowNickel);
      register(event,"armor/tungsten_helmet", tungstenHelmet);
      register(event,"armor/tungsten_chestplate", tungstenChestplate);
      register(event,"armor/tungsten_leggings", tungstenLeggings);
      register(event,"armor/tungsten_boots", tungstenBoots);
      register(event,"armor/tungsten_chainmail_helmet", tungstenHelmetChain);
      register(event,"armor/tungsten_chainmail_chestplate", tungstenChestplateChain);
      register(event,"armor/tungsten_chainmail_leggings", tungstenLeggingsChain);
      register(event,"armor/tungsten_chainmail_boots", tungstenBootsChain);
      register(event,"ingots/tungsten", tungstenIngot);
      register(event,"nuggets/tungsten", tungstenNugget);
      register(event,"tools/tungsten_axe", tungstenAxe);
      register(event,"tools/tungsten_battle_axe", tungstenBattleAxe);
      register(event,"tools/tungsten_dagger", tungstenDagger);
      register(event,"tools/tungsten_hatchet", tungstenHatchet);
      register(event,"tools/tungsten_hoe", tungstenHoe);
      register(event,"tools/tungsten_knife", tungstenKnife);
      register(event,"tools/tungsten_pickaxe", tungstenPickaxe);
      register(event,"tools/tungsten_mattock", tungstenMattock);
      register(event,"tools/tungsten_scythe", tungstenScythe);
      register(event,"tools/tungsten_shears", tungstenShears);
      register(event,"tools/tungsten_shovel", tungstenShovel);
      register(event,"tools/tungsten_sword", tungstenSword);
      register(event,"tools/tungsten_war_hammer", tungstenWarHammer);
      register(event,"doors/tungsten", doorTungsten);
      register(event,"chains/tungsten", tungstenChain);
      register(event,"coins/tungsten", tungstenCoin);
      register(event,"arrows/tungsten_arrow", arrowTungsten);
      register(event,"bowls/porkchop_stew", bowlPorkchopStew);
      register(event,"bowls/lampchop_stew", bowlChestnutSoup);
      register(event,"bowls/salmon_soup", bowlSalmonSoup);
      register(event,"bowls/beetroot_soup", bowlBeetrootSoup);
      register(event,"pieces/copper", pieceCopper);
      register(event,"pieces/silver", pieceSilver);
      register(event,"pieces/gold", pieceGold);
      register(event,"pieces/gold_nether", pieceGoldNether);
      register(event,"pieces/iron", pieceIron);
      register(event,"pieces/nickel", pieceNickel);
      register(event,"pieces/tungsten", pieceTungsten);
      register(event,"pieces/mithril", pieceMithril);
      register(event,"pieces/adamantium", pieceAdamantium);
      register(event,"food/mashed_cactus", mashedCactus);
      register(event,"food/lemon", lemon);
      register(event,"food/lemon_pie", lemonPie);
      register(event,"bowls/lemonade", bowlLemonade);
      register(event,"buckets/nickel/empty", nickelBucket);
      register(event,"buckets/nickel/lava", nickelBucketLava);
      register(event,"buckets/nickel/milk", nickelBucketMilk);
      register(event,"buckets/nickel/stone", nickelBucketStone);
      register(event,"buckets/nickel/water", nickelBucketWater);
      register(event,"buckets/tungsten/empty", tungstenBucket);
      register(event,"buckets/tungsten/lava", tungstenBucketLava);
      register(event,"buckets/tungsten/milk", tungstenBucketMilk);
      register(event,"buckets/tungsten/stone", tungstenBucketStone);
      register(event,"buckets/tungsten/water", tungstenBucketWater);
      register(event,"bowls/bowl_water_suspicious", bowlWaterSuspicious);
      register(event,"bowls/bowl_water_swampland", bowlWaterSwampland);
      register(event,"buckets/copper/water_suspicious", copperBucketWaterSuspicious);
      register(event,"buckets/silver/water_suspicious", silverBucketWaterSuspicious);
      register(event,"buckets/gold/water_suspicious", goldBucketWaterSuspicious);
      register(event,"buckets/iron/water_suspicious", ironBucketWaterSuspicious);
      register(event,"buckets/nickel/water_suspicious", nickelBucketWaterSuspicious);
      register(event,"buckets/mithril/water_suspicious", mithrilBucketWaterSuspicious);
      register(event,"buckets/tungsten/water_suspicious", tungstenBucketWaterSuspicious);
      register(event,"buckets/adamantium/water_suspicious", adamantiumBucketWaterSuspicious);
      register(event,"buckets/ancient_metal/water_suspicious", ancientmetalBucketWaterSuspicious);
      register(event,"buckets/copper/water_swampland", copperBucketWaterSwampland);
      register(event,"buckets/silver/water_swampland", silverBucketWaterSwampland);
      register(event,"buckets/gold/water_swampland", goldBucketWaterSwampland);
      register(event,"buckets/iron/water_swampland", ironBucketWaterSwampland);
      register(event,"buckets/nickel/water_swampland", nickelBucketWaterSwampland);
      register(event,"buckets/mithril/water_swampland", mithrilBucketWaterSwampland);
      register(event,"buckets/tungsten/water_swampland", tungstenBucketWaterSwampland);
      register(event,"buckets/adamantium/water_swampland", adamantiumBucketWaterSwampland);
      register(event,"buckets/ancient_metal/water_swampland", ancientmetalBucketWaterSwampland);
      register(event,"wolf_fur", Wolf_fur);
      register(event,"food/horse_meat", horse_meat);
      register(event,"food/horse_meat_cooked", horse_meat_cooked);
      register(event,"armor/wolf_helmet", WolfHelmet);
      register(event,"armor/wolf_jacket", WolfChestplate);
      register(event,"armor/wolf_leggings", WolfLeggings);
      register(event,"armor/wolf_boots", WolfBoots);
      register(event,"apple_golden", Goldenapple);
      register(event,"apple_golden", Goldenapplelegend);
      register(event,"bowl", bowlEmpty);
      register(event,"tools/copper_club", morningStarCopper);
      register(event,"tools/silver_club", morningStarSilver);
      register(event,"tools/gold_club", morningStarGold);
      register(event,"tools/iron_club", morningStarIron);
      register(event,"tools/nickel_club", morningStarNickel);
      register(event,"tools/ancient_metal_club", morningStarAncientMetal);
      register(event,"tools/mithril_club", morningStarMithril);
      register(event,"tools/tungsten_club", morningStarTungsten);
      register(event,"tools/adamantium_club", morningStarAdamantium);
      register(event,"frag/stalker_creeper", fragStalkerCreeper);
      register(event,"food/glow_berries", glowberries);
      register(event,"arrows/magical_arrow", arrowMagical);
      register(event,"wand/lava", LavaWand);
      register(event,"wand/ice", FreezeWand);
      register(event,"wand/thunder", ShockWand);
      register(event,"suspicious_potion", SuspiciousPotion);
      register(event,"experimental_potion", ExperimentalPotion);
      register(event,"shards/diamond", shardDiamond);
      register(event,"shards/emerald", shardEmerald);
      register(event,"shards/quartz", shardNetherQuartz);
      register(event,"azurite", shardAzurite);
      register(event,"records/record_damnation", recordDamnation);
      register(event,"records/record_connected", recordConnected);
      register(event,"lapis_lazuli", lapis);
      register(event,"tools/vibranium_sword", VibraniumSword);
      register(event,"armor/vibranium_helmet", VibraniumHelmet);
      register(event,"armor/vibranium_chestplate", VibraniumChestplate);
      register(event,"armor/vibranium_leggings", VibraniumLeggings);
      register(event,"armor/vibranium_boots", VibraniumBoots);
      register(event,"armor/null_helmet", helmetCustom_a);
      register(event,"armor/null_chestplate", chestplateCustom_a);
      register(event,"armor/null_leggings", leggingsCustom_a);
      register(event,"armor/null_boots", bootsCustom_a);
      register(event,"armor/null_helmet", helmetCustom_b);
      register(event,"armor/null_chestplate", chestplateCustom_b);
      register(event,"armor/null_leggings", leggingsCustom_b);
      register(event,"armor/null_boots", bootsCustom_b);
      register(event,"armor/ancient_metal_sacred_helmet", HelmetAncientmetalsacred);
      register(event,"armor/ancient_metal_sacred_chestplate", ChestplateAncientmetalsacred);
      register(event,"armor/ancient_metal_sacred_leggings", LeggingsAncientmetalsacred);
      register(event,"armor/ancient_metal_sacred_boots", BootsAncientmetalsacred);
      register(event,"ancient_metal_armor_piece", AncientmetalArmorPiece);
      register(event,"food/agave", Agave);
      register(event,"pulque", Pulque);
      register(event,"ale", Ale);
      register(event,"armor/uru_helmet", UruHelmet);
      register(event,"armor/uru_chestplate", UruChestplate);
      register(event,"armor/uru_leggings", UruLeggings);
      register(event,"armor/uru_boots", UruBoots);
      register(event,"forging_note", forgingnote);
      register(event,"ingots/uru", UruIngot);
      register(event,"nuggets/uru", UruNugget);
      register(event,"tools/uru_battle_axe", UruBattleAxe);
      register(event,"tools/uru_mattock", UruMattock);
      register(event,"tools/uru_scythe", UruScythe);
      register(event,"tools/uru_sword", UruSword);
      register(event,"tools/uru_war_hammer", UruWarHammer);
      register(event,"tools/uru_club", UruMorningStar);
      register(event,"tools/uru_pickaxe", UruPickaxe);
      register(event,"pieces/uru", pieceUru);
      register(event,"bows/tungsten/", bowTungsten).setUnlocalizedName("tungsten_bow");
      register(event,"food/beetroot", beetroot);
      register(event,"food/beetroot_seeds", seedsBeetroot);
      register(event,"hardened_clay_bowls/raw", claybowlRaw);
      register(event,"hardened_clay_bowls/beef_stew", claybowlBeefStew);
      register(event,"hardened_clay_bowls/beetroot_soup", claybowlBeetrootSoup);
      register(event,"hardened_clay_bowls/bowl_milk", claybowlMilk);
      register(event,"hardened_clay_bowls/bowl_salad", claybowlSalad);
      register(event,"hardened_clay_bowls/bowl_water", claybowlWater);
      register(event,"hardened_clay_bowls/bowl_water_suspicious", claybowlWaterSuspicious);
      register(event,"hardened_clay_bowls/bowl_water_swampland", claybowlWaterSwampland);
      register(event,"hardened_clay_bowls/cereal", claybowlCereal);
      register(event,"hardened_clay_bowls/chicken_soup", claybowlChickenSoup);
      register(event,"hardened_clay_bowls/cream_of_mushroom_soup", claybowlCreamOfMushroomSoup);
      register(event,"hardened_clay_bowls/cream_of_vegetable_soup", claybowlCreamOfVegetableSoup);
      register(event,"hardened_clay_bowls/empty", claybowlEmpty);
      register(event,"hardened_clay_bowls/ice_cream", claybowlIceCream);
      register(event,"hardened_clay_bowls/lampchop_stew", claybowlChestnutSoup);
      register(event,"hardened_clay_bowls/lemonade", claybowlLemonade);
      register(event,"hardened_clay_bowls/mashed_potato", claybowlMashedPotato);
      register(event,"hardened_clay_bowls/mushroom_stew", claybowlMushroomStew);
      register(event,"hardened_clay_bowls/porkchop_stew", claybowlPorkchopStew);
      register(event,"hardened_clay_bowls/porridge", claybowlPorridge);
      register(event,"hardened_clay_bowls/pumpkin_soup", claybowlPumpkinSoup);
      register(event,"hardened_clay_bowls/salmon_soup", claybowlSalmonSoup);
      register(event,"hardened_clay_bowls/sorbet", claybowlSorbet);
      register(event,"hardened_clay_bowls/vegetable_soup", claybowlVegetableSoup);
      register(event,"totem/totem_of_fecund", totemoffecund);
      register(event,"totem/totem_of_destroy", totemofdestroy);
      register(event,"totem/totem_of_knowledge", totemofknowledge);
      register(event,"totem/totem_of_preserve", totemofpreserve);
      register(event,"totem/totem_of_hunting", totemofhunting);
      register(event,"ignition/wood", ignitionWood);
      register(event,"ignition/copper", ignitionCopper);
      register(event,"ignition/silver", ignitionSilver);
      register(event,"ignition/gold", ignitionGold);
      register(event,"ignition/iron", ignitionIron);
      register(event,"ignition/nickel", ignitionNickel);
      register(event,"ignition/ancient_metal", ignitionAncientMetal);
      register(event,"ignition/mithril", ignitionMithril);
      register(event,"ignition/tungsten", ignitionTungsten);
      register(event,"ignition/adamantium", ignitionAdamantium);
      register(event,"wither_branch", wither_branch);
      register(event,"tools/detector", detectorDiamond);
      register(event,"tools/detector_emerald", detectorEmerald);
      register(event,"sulphur_sphere", sulphur);
      register(event,"wolf_fur_hellhound", hellhoundFur);
      register(event,"armor/hellhound_helmet", HellhoundHelmet);
      register(event,"armor/hellhound_jacket", HellhoundChestplate);
      register(event,"armor/hellhound_leggings", HellhoundLeggings);
      register(event,"armor/hellhound_boots", HellhoundBoots);
      register(event,"clay_jug", clayJug);
   }

   public static void registerBasicToolRecipes(RecipeRegistryEvent register, Material material) {
      Item item = Item.getMatchingItem(ItemIngot.class, material);
      register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemSword.class, material)), true, "A", "A", "S", 'A', item, 'S', Item.stick);
      register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemHoe.class, material)), true, "AA", "S ", "S ", 'A', item, 'S', Item.stick);
      register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemAxe.class, material)), true, "AA", "SA", "S ", 'A', item, 'S', Item.stick);
      register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemShovel.class, material)), true, "A", "S", "S", 'A', item, 'S', Item.stick);
      register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemPickaxe.class, material)), true, "AAA", " S ", " S ", 'A', item, 'S', Item.stick);
      register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemFishingRod.class, material)), true, "  S", " SG", "SAG", 'A', getMatchingItem(ItemNugget.class, item.getHardestMetalMaterial()), 'S', Item.stick, 'G', Item.silk);
      register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemShears.class, material)), true, "A ", " A", 'A', item);
      registerArmorRecipe(register, item, material);
   }

   public static void registerMITEToolRecipe(RecipeRegistryEvent register, Material material) {
      Item item = Item.getMatchingItem(ItemIngot.class, material);
      Item item_nugget = Item.getMatchingItem(ItemNugget.class, item.getHardestMetalMaterial());
      Item item_chain = Item.getMatchingItem(ItemChain.class, material);
      register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemArrow.class, material)), true, "C", "B", "A", 'A', Item.feather, 'B', Item.stick, 'C', item_nugget);
      register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemChain.class, material)), true, " A ", "A A", " A ", 'A', item_nugget);
      registerArmorRecipe(register, item_chain, material);
      register.registerShapedRecipe(new ItemStack(ItemBucket.getPeer(material, (Material)null)), true, "A A", " A ", 'A', item);
      register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemScythe.class, material)), true, "SA ", "S A", "S  ", 'A', item, 'S', Item.stick);
      register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemMattock.class, material)), true, "AAA", " SA", " S ", 'A', item, 'S', Item.stick);
      register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemHatchet.class, material)), true, " BA", " B ", 'A', item, 'B', Item.stick);
      register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemWarHammer.class, material)), true, "AAA", "ABA", " B ", 'A', item, 'B', Item.stick);
      register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemDagger.class, material)), true, " A ", " B ", 'A', item, 'B', Item.stick);
      register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemBattleAxe.class, material)), true, "A A", "ABA", " B ", 'A', item, 'B', Item.stick);
      register.registerShapedRecipe(new ItemStack(Item.getMatchingItem(ItemDoor.class, material)), true, "AA", "AA", "AA", 'A', item);
   }

   public static void registerITFToolRecipe(RecipeRegistryEvent register) {
      Material[] materials = new Material[]{Material.copper, Material.silver, Material.gold, Material.iron, Materials.nickel, Material.ancient_metal, Material.mithril, Materials.tungsten, Material.adamantium};

       for (Material material : materials) {
           Item item = Item.getMatchingItem(ItemIngot.class, material);
           Item item_nugget = getMatchingItem(ItemNugget.class, material);
           register.registerShapedRecipe(new ItemStack(getMatchingItem(ItemMorningStar.class, material), 1), true, "###", "#*#", " # ", '#', item_nugget, '*', item);
           register.registerShapedRecipe(new ItemStack(getMatchingItem(ItemIgnition.class, material)), true, "C ", " F", 'C', item_nugget, 'F', flint);
           register.registerShapedRecipe((new ItemStack(leatherKettle, 1)).setItemDamage(leatherKettle.getMaxDamage() - 1), false, "#N", "JL", 'J', Item.sinew, '#', Item.silk, 'N', item_nugget, 'L', Item.leather).difficulty(2000);
       }

   }

   public static void registerArmorRecipe(RecipeRegistryEvent register, Item item, Material material) {
      register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemHelmet.class, material, item instanceof ItemChain)), true, "AAA", "A A", 'A', item);
      register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemCuirass.class, material, item instanceof ItemChain)), true, "A A", "AAA", "AAA", 'A', item);
      register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemLeggings.class, material, item instanceof ItemChain)), true, "AAA", "A A", "A A", 'A', item);
      register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemBoots.class, material, item instanceof ItemChain)), true, "A A", "A A", 'A', item);
   }

   public static void registerLeatherArmorRecipe(RecipeRegistryEvent register, Item item, Material material) {
      register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemHelmet.class, material, item instanceof ItemChain)), true, "AAA", "AHA", 'A', item, 'H', helmetLeather);
      register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemCuirass.class, material, item instanceof ItemChain)), true, "A A", "ACA", "AAA", 'A', item, 'C', plateLeather);
      register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemLeggings.class, material, item instanceof ItemChain)), true, "AAA", "ALA", "A A", 'A', item, 'L', Item.legsLeather);
      register.registerShapedRecipe(new ItemStack(ItemArmor.getMatchingArmor(ItemBoots.class, material, item instanceof ItemChain)), true, "ABA", "A A", 'A', item, 'B', Item.bootsLeather);
   }

   public static void registerFullMetalToolRecipe(RecipeRegistryEvent register, Material material) {
      registerBasicToolRecipes(register, material);
      registerMITEToolRecipe(register, material);
   }

   public static void registerRecipes(RecipeRegistryEvent register) {
      register.registerShapelessRecipe(new ItemStack(lemonPie), true, Item.sugar, Item.egg, Item.flour, lemon);
      register.registerShapelessRecipe(new ItemStack(nickelIngot, 9), true, Blocks.blockNickel);
      register.registerShapelessRecipe(new ItemStack(nickelNugget, 9), true, nickelIngot);
      register.registerShapelessRecipe(new ItemStack(tungstenIngot, 9), true, Blocks.blockTungsten);
      register.registerShapelessRecipe(new ItemStack(tungstenNugget, 9), true, tungstenIngot);
      registerLeatherArmorRecipe(register, Wolf_fur, Materials.wolf_fur);
      registerLeatherArmorRecipe(register, hellhoundFur, Materials.wolf_fur);
      registerITFToolRecipe(register);
      registerFullMetalToolRecipe(register, Materials.nickel);
      registerFullMetalToolRecipe(register, Materials.tungsten);
      register.registerShapelessRecipe(new ItemStack(glowberries, 1), true, new ItemStack(Blocks.flowerextend, 1, 0));
      register.registerShapelessRecipe(new ItemStack(Item.dyePowder, 1, 7), true, new ItemStack(Blocks.flowerextend, 1, 1));
      register.registerShapelessRecipe(new ItemStack(Item.dyePowder, 1, 4), true, new ItemStack(Blocks.flowerextend, 1, 2));
      register.registerShapelessRecipe(new ItemStack(Item.dyePowder, 1, 7), true, new ItemStack(Blocks.flowerextend, 1, 3));
      register.registerShapelessRecipe(new ItemStack(Item.dyePowder, 1, 9), true, new ItemStack(Blocks.flowerextend, 1, 4));
      register.registerShapelessRecipe(new ItemStack(Item.dyePowder, 1, 7), true, new ItemStack(Blocks.flowerextend, 1, 5));
      register.registerShapelessRecipe(new ItemStack(Item.dyePowder, 1, 1), true, new ItemStack(Blocks.flowerextend, 1, 6));
      register.registerShapelessRecipe(new ItemStack(Agave, 1, 1), true, new ItemStack(Blocks.flowerextend, 1, 7));

      int i;
      for(i = 0; i <= 4; ++i) {
         register.registerShapelessRecipe(new ItemStack(Item.stick, 1), true, new ItemStack(Blocks.torchWoodIdle, i), new ItemStack(Blocks.torchWoodExtinguished, 4 - i));
      }

      register.registerShapedRecipe(new ItemStack(clayJug, 1), true, " C ", "C C", " C ", 'C', clay);
      register.registerShapedRecipe(new ItemStack(bowTungsten, 1), true, "#C ", "#EC", "#C ", '#', silk, 'E', tungstenIngot, 'C', stick);
      register.registerShapedRecipe(new ItemStack(ignitionWood, 1), true, "SW", "WW", 'S', silk, 'W', stick);
      register.registerShapedRecipe(new ItemStack(ignitionWood, 1), true, "SW", "WW", 'S', sinew, 'W', stick);
      register.registerShapedRecipe(new ItemStack(detectorEmerald, 1), true, "FAF", "ANA", "FAF", 'A', Item.goldNugget, 'F', Item.ancientMetalNugget, 'N', Item.emerald);
      register.registerShapedRecipe(new ItemStack(detectorDiamond, 1), true, "FAF", "ANA", "FAF", 'A', Item.goldNugget, 'F', Item.ancientMetalNugget, 'N', Item.diamond);
      register.registerShapelessRecipe(new ItemStack(sulphur, 9), true, new ItemStack(Blocks.blockSulphur, 1));
      register.registerShapelessRecipe(new ItemStack(Item.gunpowder, 5), true, new ItemStack(sulphur, 8), new ItemStack(Item.coal, 1, 1));
      register.registerShapelessRecipe(new ItemStack(forgingnote, 2), false, forgingnote, Item.writableBook);
      register.registerShapelessRecipe(new ItemStack(UruHelmet, 1), true, forgingnote, UruIngot, Item.helmetMithril, Item.ingotMithril);
      register.registerShapelessRecipe(new ItemStack(UruChestplate, 1), true, forgingnote, UruIngot, Item.plateMithril, Item.ingotMithril);
      register.registerShapelessRecipe(new ItemStack(UruLeggings, 1), true, forgingnote, UruIngot, Item.legsMithril, Item.ingotMithril);
      register.registerShapelessRecipe(new ItemStack(UruBoots, 1), true, forgingnote, UruIngot, Item.bootsMithril, Item.ingotMithril);
      register.registerShapelessRecipe(new ItemStack(UruSword, 1), true, forgingnote, UruIngot, Item.swordMithril, Item.ingotMithril);
      register.registerShapelessRecipe(new ItemStack(UruScythe, 1), true, forgingnote, UruIngot, Item.scytheMithril, Item.ingotMithril);
      register.registerShapelessRecipe(new ItemStack(UruBattleAxe, 1), true, forgingnote, UruIngot, Item.battleAxeMithril, Item.ingotMithril);
      register.registerShapelessRecipe(new ItemStack(UruWarHammer, 1), true, forgingnote, UruIngot, Item.warHammerMithril, Item.ingotMithril);
      register.registerShapelessRecipe(new ItemStack(UruMattock, 1), true, forgingnote, UruIngot, Item.mattockMithril, Item.ingotMithril);
      register.registerShapelessRecipe(new ItemStack(UruMorningStar, 1), true, forgingnote, UruIngot, morningStarMithril, Item.ingotMithril);
      register.registerShapelessRecipe(new ItemStack(UruPickaxe, 1), true, forgingnote, UruIngot, Item.pickaxeMithril, Item.ingotMithril);
      register.registerShapelessRecipe(new ItemStack(UruNugget, 9), true, UruIngot);
      register.registerShapelessRecipe(new ItemStack(HelmetAncientmetalsacred, 1), true, forgingnote, Item.ingotGold, Item.helmetAncientMetal);
      register.registerShapelessRecipe(new ItemStack(ChestplateAncientmetalsacred, 1), true, forgingnote, Item.ingotGold, Item.plateAncientMetal);
      register.registerShapelessRecipe(new ItemStack(LeggingsAncientmetalsacred, 1), true, forgingnote, Item.ingotGold, Item.legsAncientMetal);
      register.registerShapelessRecipe(new ItemStack(BootsAncientmetalsacred, 1), true, forgingnote, Item.ingotGold, Item.bootsAncientMetal);
      register.registerShapelessRecipe(new ItemStack(tungstenNugget, 1), false, arrowTungsten);
      register.registerShapelessRecipe(new ItemStack(nickelNugget, 1), false, arrowNickel);
      register.registerShapelessRecipe(new ItemStack(mashedCactus, 1), true, Block.cactus);
      register.registerShapelessRecipe(new ItemStack(nickelIngot, 1), true, nickelNugget, nickelNugget, nickelNugget, nickelNugget, nickelNugget, nickelNugget, nickelNugget, nickelNugget, nickelNugget);
      register.registerShapelessRecipe(new ItemStack(tungstenIngot, 1), true, tungstenNugget, tungstenNugget, tungstenNugget, tungstenNugget, tungstenNugget, tungstenNugget, tungstenNugget, tungstenNugget, tungstenNugget);
      register.registerShapelessRecipe(new ItemStack(UruIngot, 1), true, UruNugget, UruNugget, UruNugget, UruNugget, UruNugget, UruNugget, UruNugget, UruNugget, UruNugget);
      register.registerShapelessRecipe(new ItemStack(Item.leather, 1), true, Wolf_fur, Wolf_fur, Wolf_fur, Wolf_fur);
      register.registerShapelessRecipe(new ItemStack(Item.leather, 1), true, hellhoundFur, hellhoundFur, hellhoundFur, hellhoundFur);
      register.registerShapelessRecipe(new ItemStack(Item.dyePowder, 1, 4), false, lapis);
      register.registerShapelessRecipe(new ItemStack(seedsBeetroot, 1), false, beetroot, beetroot);
      register.registerShapelessRecipe(new ItemStack(Item.dyePowder, 1, 1), false, beetroot);
      register.registerShapelessRecipe(new ItemStack(Pulque, 1), true, Item.sugar, Agave, new ItemStack(Item.potion, 1, 0));
      register.registerShapelessRecipe(new ItemStack(Ale, 1), true, Item.sugar, Item.wheat, new ItemStack(Item.potion, 1, 0));
      register.registerShapelessRecipe(new ItemStack(claybowlRaw, 1), false, Item.clay);
      register.registerShapelessRecipe(new ItemStack(tungstenBucket, 1), false, tungstenBucketStone).difficulty(100);
      register.registerShapelessRecipe(new ItemStack(nickelBucket, 1), false, nickelBucketStone).difficulty(100);

      for(i = 1; i <= 9; ++i) {
         register.registerShapelessRecipe(new ItemStack(Item.glassBottle, i), false, new ItemStack(SuspiciousPotion, i));
      }

      register.registerShapelessRecipe(new ItemStack(bowlBeetrootSoup, 1, 0), false, beetroot, beetroot, beetroot, beetroot, beetroot, beetroot, Item.bowlWater);
      register.registerShapelessRecipe(new ItemStack(bowlPorkchopStew, 1), true, Item.bowlWater, Item.porkCooked, Item.carrot, Item.potato, Block.mushroomBrown);
      register.registerShapelessRecipe(new ItemStack(bowlChestnutSoup, 1), true, Item.bowlWater, Item.lambchopCooked, Item.onion, Item.potato);
      register.registerShapelessRecipe(new ItemStack(bowlSalmonSoup, 1), true, Item.fishLargeCooked, beetroot, Block.mushroomBrown, Item.bowlWater);
      register.registerShapelessRecipe(new ItemStack(bowlLemonade, 1), true, Item.sugar, lemon, Item.bowlWater);
      register.registerShapelessRecipe(new ItemStack(carrotOnAStickNickel, 1), false, Item.carrot, fishingRodNickel);
      register.registerShapelessRecipe(new ItemStack(carrotOnAStickTungsten, 1), false, Item.carrot, fishingRodTungsten);

      for(i = 1; i <= 9; ++i) {
         register.registerShapelessRecipe(new ItemStack(Item.bowlEmpty, i), false, new ItemStack(bowlWaterSuspicious, i));
         register.registerShapelessRecipe(new ItemStack(Item.bowlEmpty, i), false, new ItemStack(bowlWaterSwampland, i));
      }

      register.registerShapelessRecipe(new ItemStack(claybowlBeefStew), false, Item.beefCooked, Block.mushroomBrown, Item.potato, claybowlWater);
      register.registerShapelessRecipe(new ItemStack(claybowlChickenSoup), false, Item.chickenCooked, Item.carrot, Item.onion, claybowlWater);
      register.registerShapelessRecipe(new ItemStack(claybowlVegetableSoup), false, Item.potato, Item.carrot, Item.onion, claybowlWater);
      register.registerShapelessRecipe(new ItemStack(claybowlIceCream), false, Item.chocolate, claybowlMilk, Item.snowball);
      register.registerShapelessRecipe(new ItemStack(claybowlIceCream), false, new ItemStack(Item.dyePowder, 1, 3), Item.sugar, claybowlMilk, Item.snowball);
      register.registerShapelessRecipe(new ItemStack(claybowlSalad), false, Block.plantYellow, Block.plantYellow, Block.plantYellow, claybowlEmpty);
      register.registerShapelessRecipe(new ItemStack(claybowlCreamOfMushroomSoup), false, Block.mushroomBrown, Block.mushroomBrown, claybowlMilk);
      register.registerShapelessRecipe(new ItemStack(claybowlCreamOfVegetableSoup), false, Item.potato, Item.carrot, Item.onion, claybowlMilk);
      register.registerShapelessRecipe(new ItemStack(claybowlPumpkinSoup), false, Block.pumpkin, claybowlWater);
      register.registerShapelessRecipe(new ItemStack(claybowlMashedPotato), false, Item.bakedPotato, Item.cheese, claybowlMilk);
      register.registerShapelessRecipe(new ItemStack(claybowlSorbet), false, Item.orange, Item.sugar, Item.snowball, claybowlEmpty);
      register.registerShapelessRecipe(new ItemStack(claybowlPorridge), false, Item.seeds, Item.blueberries, Item.sugar, claybowlWater);
      register.registerShapelessRecipe(new ItemStack(claybowlCereal), false, Item.wheat, Item.sugar, claybowlMilk);
      register.registerShapelessRecipe(new ItemStack(claybowlMushroomStew), false, Block.mushroomBrown, Block.mushroomRed, claybowlWater);
      register.registerShapelessRecipe(new ItemStack(claybowlBeetrootSoup, 1, 0), false, beetroot, beetroot, beetroot, beetroot, beetroot, beetroot, claybowlWater);
      register.registerShapelessRecipe(new ItemStack(claybowlPorkchopStew, 1), true, claybowlWater, Item.porkCooked, Item.carrot, Item.potato, Block.mushroomBrown);
      register.registerShapelessRecipe(new ItemStack(claybowlChestnutSoup, 1), true, claybowlWater, Item.lambchopCooked, Item.onion, Item.potato);
      register.registerShapelessRecipe(new ItemStack(claybowlSalmonSoup, 1), true, Item.fishLargeCooked, beetroot, Block.mushroomBrown, claybowlWater);
      register.registerShapelessRecipe(new ItemStack(claybowlLemonade, 1), true, Item.sugar, lemon, claybowlWater);
      register.registerShapelessRecipe(new ItemStack(ExperimentalPotion, 1), true, Item.blazePowder, Item.netherStalkSeeds, new ItemStack(Item.potion, 1, 0), new ItemStack(Item.appleGold, 1, 0));
      register.registerShapelessRecipe(new ItemStack(Item.cheese, 1), false, new ItemStack(claybowlMilk, 4)).difficulty(6400);
      register.registerShapelessRecipe(new ItemStack(Item.cheese, 2), false, new ItemStack(claybowlMilk, 8)).difficulty(6400);

      for(i = 1; i <= 9; ++i) {
         register.registerShapelessRecipe(new ItemStack(claybowlEmpty, i), false, new ItemStack(claybowlWaterSuspicious, i));
         register.registerShapelessRecipe(new ItemStack(claybowlEmpty, i), false, new ItemStack(claybowlWaterSwampland, i));
      }

      ItemBucketMilk[] milk_buckets = new ItemBucketMilk[]{Item.bucketCopperMilk, Item.bucketSilverMilk, Item.bucketGoldMilk, Item.bucketIronMilk, Item.bucketAncientMetalMilk, Item.bucketMithrilMilk, Item.bucketAdamantiumMilk, tungstenBucketMilk, nickelBucketMilk};

      int n;
      for(n = 0; n < milk_buckets.length; ++n) {
         register.registerShapelessRecipe(new ItemStack(Item.cake), false, Item.flour, Item.sugar, Item.egg, milk_buckets[n]);

         for(n = 1; n <= 9; ++n) {
            register.registerShapelessRecipe(new ItemStack(Item.cheese, n), false, new ItemStack(milk_buckets[n], n)).difficulty(6400);
         }

         for(n = 1; n <= 4; ++n) {
            register.registerShapelessRecipe(new ItemStack(Item.bowlMilk, n), true, milk_buckets[n], new ItemStack(Item.bowlEmpty, n)).difficulty(25);
         }

         for(n = 1; n <= 4; ++n) {
            register.registerShapelessRecipe(new ItemStack(claybowlMilk, n), true, milk_buckets[n], new ItemStack(claybowlEmpty, n)).difficulty(25);
         }

         register.registerShapelessRecipe(new ItemStack(milk_buckets[n]), true, milk_buckets[n].getEmptyVessel(), Item.bowlMilk, Item.bowlMilk, Item.bowlMilk, Item.bowlMilk).difficulty(25);
         register.registerShapelessRecipe(new ItemStack(milk_buckets[n]), true, milk_buckets[n].getEmptyVessel(), claybowlMilk, claybowlMilk, claybowlMilk, claybowlMilk).difficulty(25);
      }

      register.registerShapelessRecipe(new ItemStack(Item.dough, 1), false, Item.flour, Item.bowlWater);
      register.registerShapelessRecipe(new ItemStack(Item.dough, 1), false, Item.flour, claybowlWater);
      ItemBucket[] water_buckets = new ItemBucket[]{Item.bucketCopperWater, Item.bucketSilverWater, Item.bucketGoldWater, Item.bucketIronWater, Item.bucketAncientMetalWater, Item.bucketMithrilWater, Item.bucketAdamantiumWater, nickelBucketWater, tungstenBucketWater};
      
      for(n = 0; n < water_buckets.length; ++n) {
         for(n = 1; n <= 4; ++n) {
            register.registerShapelessRecipe(new ItemStack(Item.dough, n), false, water_buckets[n], new ItemStack(Item.flour, n));
            register.registerShapelessRecipe(new ItemStack(Item.cookie, n * 4), false, water_buckets[n], new ItemStack(Item.flour, n), new ItemStack(Item.chocolate, n));
            register.registerShapelessRecipe(new ItemStack(Item.bowlWater, n), true, water_buckets[n], new ItemStack(Item.bowlEmpty, n)).difficulty(25);
            register.registerShapelessRecipe(new ItemStack(claybowlWater, n), true, water_buckets[n], new ItemStack(claybowlEmpty, n)).difficulty(25);
         }

         for(n = 1; n <= 2; ++n) {
            register.registerShapelessRecipe(new ItemStack(Item.cookie, n * 4), false, water_buckets[n], new ItemStack(Item.flour, n), new ItemStack(Item.dyePowder, n, 3), new ItemStack(Item.sugar, n));
         }

         register.registerShapelessRecipe(new ItemStack(water_buckets[n]), true, water_buckets[n].getEmptyVessel(), new ItemStack(Item.bowlWater, 4)).difficulty(25);
         register.registerShapelessRecipe(new ItemStack(water_buckets[n]), true, water_buckets[n].getEmptyVessel(), new ItemStack(claybowlWater, 4)).difficulty(25);
      }

      ItemBucket[] sus_water_buckets = new ItemBucket[]{copperBucketWaterSuspicious, silverBucketWaterSuspicious, goldBucketWaterSuspicious, ironBucketWaterSuspicious, ancientmetalBucketWaterSuspicious, mithrilBucketWaterSuspicious, adamantiumBucketWaterSuspicious, nickelBucketWaterSuspicious, tungstenBucketWaterSuspicious};
      
      for(n = 0; n < sus_water_buckets.length; ++n) {
         for(n = 1; n <= 4; ++n) {
            register.registerShapelessRecipe(new ItemStack(bowlWaterSuspicious, n), true, sus_water_buckets[n], new ItemStack(Item.bowlEmpty, n)).difficulty(25);
            register.registerShapelessRecipe(new ItemStack(claybowlWaterSuspicious, n), true, sus_water_buckets[n], new ItemStack(claybowlEmpty, n)).difficulty(25);
         }

         register.registerShapelessRecipe(new ItemStack(sus_water_buckets[n]), true, sus_water_buckets[n].getEmptyVessel(), new ItemStack(bowlWaterSuspicious, 4)).difficulty(25);
         register.registerShapelessRecipe(new ItemStack(sus_water_buckets[n]), true, sus_water_buckets[n].getEmptyVessel(), new ItemStack(claybowlWaterSuspicious, 4)).difficulty(25);
      }

      ItemBucket[] smp_water_buckets = new ItemBucket[]{copperBucketWaterSwampland, silverBucketWaterSwampland, goldBucketWaterSwampland, ironBucketWaterSwampland, ancientmetalBucketWaterSwampland, mithrilBucketWaterSwampland, adamantiumBucketWaterSwampland, nickelBucketWaterSwampland, tungstenBucketWaterSwampland};

      for(n = 0; n < smp_water_buckets.length; ++n) {
         for(i = 1; i <= 4; ++i) {
            register.registerShapelessRecipe(new ItemStack(bowlWaterSwampland, i), true, smp_water_buckets[n], new ItemStack(Item.bowlEmpty, i)).difficulty(25);
            register.registerShapelessRecipe(new ItemStack(claybowlWaterSwampland, i), true, smp_water_buckets[n], new ItemStack(claybowlEmpty, i)).difficulty(25);
         }

         register.registerShapelessRecipe(new ItemStack(smp_water_buckets[n]), true, smp_water_buckets[n].getEmptyVessel(), new ItemStack(bowlWaterSwampland, 4)).difficulty(25);
         register.registerShapelessRecipe(new ItemStack(smp_water_buckets[n]), true, smp_water_buckets[n].getEmptyVessel(), new ItemStack(claybowlWaterSwampland, 4)).difficulty(25);
      }

      register.registerShapelessRecipe(new ItemStack(Item.cake), false, Item.flour, Item.sugar, Item.egg, claybowlMilk);
      ItemCoin[] coins = new ItemCoin[]{nickelCoin, tungstenCoin};
       int var7 = coins.length;

      int plank_subtype;
      for(int var8 = 0; var8 < var7; ++var8) {
         ItemCoin coin = coins[var8];

         for(plank_subtype = 1; plank_subtype <= 9; ++plank_subtype) {
            register.registerShapelessRecipe(new ItemStack(coin.getNuggetPeer(), plank_subtype), true, new ItemStack(coin, plank_subtype)).difficulty(25);
         }

         register.registerShapelessRecipe(new ItemStack(coin), true, new ItemStack(coin.getNuggetPeer()));
      }

      FurnaceRecipes.smelting().addSmelting(pieceAdamantium.itemID, new ItemStack(adamantiumNugget));
      FurnaceRecipes.smelting().addSmelting(pieceCopper.itemID, new ItemStack(copperNugget));
      FurnaceRecipes.smelting().addSmelting(pieceGold.itemID, new ItemStack(goldNugget));
      FurnaceRecipes.smelting().addSmelting(pieceGoldNether.itemID, new ItemStack(goldNugget));
      FurnaceRecipes.smelting().addSmelting(pieceSilver.itemID, new ItemStack(silverNugget));
      FurnaceRecipes.smelting().addSmelting(pieceIron.itemID, new ItemStack(ironNugget));
      FurnaceRecipes.smelting().addSmelting(pieceNickel.itemID, new ItemStack(nickelNugget));
      FurnaceRecipes.smelting().addSmelting(pieceMithril.itemID, new ItemStack(mithrilNugget));
      FurnaceRecipes.smelting().addSmelting(pieceTungsten.itemID, new ItemStack(tungstenNugget));
      FurnaceRecipes.smelting().addSmelting(pieceUru.itemID, new ItemStack(UruNugget));
      FurnaceRecipes.smelting().addSmelting(AncientmetalArmorPiece.itemID, new ItemStack(ancientMetalNugget));
      FurnaceRecipes.smelting().addSmelting(claybowlWaterSuspicious.itemID, new ItemStack(claybowlWater));
      FurnaceRecipes.smelting().addSmelting(claybowlWaterSwampland.itemID, new ItemStack(claybowlWater));
      FurnaceRecipes.smelting().addSmelting(SuspiciousPotion.itemID, new ItemStack(potion, 1, 0));
      FurnaceRecipes.smelting().addSmelting(horse_meat.itemID, new ItemStack(horse_meat_cooked));
      FurnaceRecipes.smelting().addSmelting(claybowlRaw.itemID, new ItemStack(claybowlEmpty));
      FurnaceRecipes.smelting().addSmelting(leatherKettleSuspicious.itemID, new ItemStack(leatherKettle));
      FurnaceRecipes.smelting().addSmelting(leatherKettleSwampland.itemID, new ItemStack(leatherKettle));
      FurnaceRecipes.smelting().addSmelting(hardenedClayJugSuspicious.itemID, new ItemStack(hardenedClayJug));
      FurnaceRecipes.smelting().addSmelting(hardenedClayJugSwampland.itemID, new ItemStack(hardenedClayJug));
      FurnaceRecipes.smelting().addSmelting(clayJug.itemID, (new ItemStack(hardenedClayJug)).setItemDamage(hardenedClayJug.getMaxDamage() - 1));
      Class[] tools = new Class[]{ItemSword.class, ItemAxe.class, ItemPickaxe.class, ItemHoe.class, ItemShovel.class, ItemWarHammer.class, ItemBattleAxe.class, ItemScythe.class, ItemDagger.class, ItemKnife.class, ItemMorningStar.class, ItemHatchet.class, ItemShears.class, ItemMattock.class};
      Class[] armors = new Class[]{ItemHelmet.class, ItemBoots.class, ItemLeggings.class, ItemCuirass.class};
      Material[] available_material = new Material[]{Material.copper, Material.silver, Material.gold, Material.iron, Materials.nickel, Materials.tungsten, Material.ancient_metal, Material.rusted_iron};
      Class[] var28 = tools;
      plank_subtype = tools.length;

      int var11;
      Class armor;
      Material[] var13;
      int var14;
      int var15;
      Material material;
      for(var11 = 0; var11 < plank_subtype; ++var11) {
         armor = var28[var11];
         var13 = available_material;
         var14 = available_material.length;

         for(var15 = 0; var15 < var14; ++var15) {
            material = var13[var15];
            Item matchingitem = Item.getMatchingItem(armor, material);
            if (matchingitem != null) {
               FurnaceRecipes.smelting().addSmelting(matchingitem.itemID, new ItemStack(appleRed));
            }
         }
      }

      var28 = armors;
      plank_subtype = armors.length;

      for(var11 = 0; var11 < plank_subtype; ++var11) {
         armor = var28[var11];
         var13 = available_material;
         var14 = available_material.length;

         for(var15 = 0; var15 < var14; ++var15) {
            material = var13[var15];
            Item matching_plate = ItemArmor.getMatchingArmor(armor, material, false);
            FurnaceRecipes.smelting().addSmelting(matching_plate.itemID, new ItemStack(appleRed));
            Item matching_chain = ItemArmor.getMatchingArmor(armor, material, true);
            FurnaceRecipes.smelting().addSmelting(matching_chain.itemID, new ItemStack(appleRed));
         }
      }

      ItemFood.setCookingResult((ItemFood)horse_meat, (ItemFood)horse_meat_cooked, 6);
   }

   static {
      nickelHelmet = new ItemHelmet(IdUtil.getNextItemID(), Materials.nickel, false);
      nickelChestplate = new ItemCuirass(IdUtil.getNextItemID(), Materials.nickel, false);
      nickelLeggings = new ItemLeggings(IdUtil.getNextItemID(), Materials.nickel, false);
      nickelBoots = new ItemBoots(IdUtil.getNextItemID(), Materials.nickel, false);
      nickelHelmetChain = new ItemHelmet(IdUtil.getNextItemID(), Materials.nickel, true);
      nickelChestplateChain = new ItemCuirass(IdUtil.getNextItemID(), Materials.nickel, true);
      nickelLeggingsChain = new ItemLeggings(IdUtil.getNextItemID(), Materials.nickel, true);
      nickelBootsChain = new ItemBoots(IdUtil.getNextItemID(), Materials.nickel, true);
      nickelNugget = (ItemNugget)ReflectHelper.createInstance(ItemNugget.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.nickel});
      nickelAxe = (ItemAxe)ReflectHelper.createInstance(ItemAxe.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.nickel});
      nickelBattleAxe = (ItemBattleAxe)ReflectHelper.createInstance(ItemBattleAxe.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.nickel});
      nickelDagger = (ItemDagger)ReflectHelper.createInstance(ItemDagger.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.nickel});
      nickelIngot = ((ItemIngot)ReflectHelper.createInstance(ItemIngot.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.nickel})).setXPReward(15);
      nickelPickaxe = (ItemPickaxe)ReflectHelper.createInstance(ItemPickaxe.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.nickel});
      nickelShovel = (ItemShovel)ReflectHelper.createInstance(ItemShovel.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.nickel});
      nickelSword = (ItemSword)ReflectHelper.createInstance(ItemSword.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.nickel});
      nickelWarHammer = (ItemWarHammer)ReflectHelper.createInstance(ItemWarHammer.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.nickel});
      nickelHatchet = (ItemHatchet)ReflectHelper.createInstance(ItemHatchet.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.nickel});
      nickelHoe = (ItemHoe)ReflectHelper.createInstance(ItemHoe.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.nickel});
      nickelKnife = (ItemKnife)ReflectHelper.createInstance(ItemKnife.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.nickel});
      nickelMattock = (ItemMattock)ReflectHelper.createInstance(ItemMattock.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.nickel});
      nickelScythe = (ItemScythe)ReflectHelper.createInstance(ItemScythe.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.nickel});
      nickelShears = (ItemShears)ReflectHelper.createInstance(ItemShears.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.nickel});
      doorNickel = new ItemDoor(IdUtil.getNextItemID(), Materials.nickel);
      nickelChain = (ItemChain)ReflectHelper.createInstance(ItemChain.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.nickel});
      nickelCoin = (ItemCoin)ReflectHelper.createInstance(ItemCoin.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.nickel});
      arrowNickel = new ItemArrow(IdUtil.getNextItemID(), Materials.nickel);
      tungstenHelmet = new ItemHelmet(IdUtil.getNextItemID(), Materials.tungsten, false);
      tungstenChestplate = new ItemCuirass(IdUtil.getNextItemID(), Materials.tungsten, false);
      tungstenLeggings = new ItemLeggings(IdUtil.getNextItemID(), Materials.tungsten, false);
      tungstenBoots = new ItemBoots(IdUtil.getNextItemID(), Materials.tungsten, false);
      tungstenHelmetChain = new ItemHelmet(IdUtil.getNextItemID(), Materials.tungsten, true);
      tungstenChestplateChain = new ItemCuirass(IdUtil.getNextItemID(), Materials.tungsten, true);
      tungstenLeggingsChain = new ItemLeggings(IdUtil.getNextItemID(), Materials.tungsten, true);
      tungstenBootsChain = new ItemBoots(IdUtil.getNextItemID(), Materials.tungsten, true);
      tungstenNugget = (ItemNugget)ReflectHelper.createInstance(ItemNugget.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.tungsten});
      tungstenAxe = (ItemAxe)ReflectHelper.createInstance(ItemAxe.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.tungsten});
      tungstenBattleAxe = (ItemBattleAxe)ReflectHelper.createInstance(ItemBattleAxe.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.tungsten});
      tungstenDagger = (ItemDagger)ReflectHelper.createInstance(ItemDagger.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.tungsten});
      tungstenIngot = ((ItemIngot)ReflectHelper.createInstance(ItemIngot.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.tungsten})).setXPReward(75);
      tungstenPickaxe = (ItemPickaxe)ReflectHelper.createInstance(ItemPickaxe.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.tungsten});
      tungstenShovel = (ItemShovel)ReflectHelper.createInstance(ItemShovel.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.tungsten});
      tungstenSword = (ItemSword)ReflectHelper.createInstance(ItemSword.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.tungsten});
      tungstenWarHammer = (ItemWarHammer)ReflectHelper.createInstance(ItemWarHammer.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.tungsten});
      tungstenHatchet = (ItemHatchet)ReflectHelper.createInstance(ItemHatchet.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.tungsten});
      tungstenHoe = (ItemHoe)ReflectHelper.createInstance(ItemHoe.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.tungsten});
      tungstenKnife = (ItemKnife)ReflectHelper.createInstance(ItemKnife.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.tungsten});
      tungstenMattock = (ItemMattock)ReflectHelper.createInstance(ItemMattock.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.tungsten});
      tungstenScythe = (ItemScythe)ReflectHelper.createInstance(ItemScythe.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.tungsten});
      tungstenShears = (ItemShears)ReflectHelper.createInstance(ItemShears.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.tungsten});
      doorTungsten = new ItemDoor(IdUtil.getNextItemID(), Materials.tungsten);
      tungstenChain = (ItemChain)ReflectHelper.createInstance(ItemChain.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.tungsten});
      tungstenCoin = (ItemCoin)ReflectHelper.createInstance(ItemCoin.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.tungsten});
      arrowTungsten = new ItemArrow(IdUtil.getNextItemID(), Materials.tungsten);
      bowlPorkchopStew = (ItemBowl)(new ItemBowl(IdUtil.getNextItemID(), Materials.porkchop_stew, "porkchop_stew")).setFoodValue(14, 14, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("porkchopStew");
      bowlChestnutSoup = (ItemBowl)(new ItemBowl(IdUtil.getNextItemID(), Materials.chestnut_soup, "lampchop_stew")).setFoodValue(12, 12, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("lampchopStew");
      bowlWaterSuspicious = (ItemBowl)(new ItemBowl(IdUtil.getNextItemID(), Materials.unsafe_water, "suspicious_water")).setUnlocalizedName("SuspiciousWater");
      bowlWaterSwampland = (ItemBowl)(new ItemBowl(IdUtil.getNextItemID(), Materials.dangerous_water, "swampland_water")).setUnlocalizedName("SwamplandWater");
      pieceCopper = new ItemPieces(IdUtil.getNextItemID(), Materials.orePieces, "pieceCopper");
      pieceSilver = new ItemPieces(IdUtil.getNextItemID(), Materials.orePieces, "pieceSilver");
      pieceGold = new ItemPieces(IdUtil.getNextItemID(), Materials.orePieces, "pieceGold");
      pieceGoldNether = new ItemPieces(IdUtil.getNextItemID(), Materials.orePieces, "pieceGoldNether");
      pieceIron = new ItemPieces(IdUtil.getNextItemID(), Materials.orePieces, "pieceIron");
      pieceNickel = new ItemPieces(IdUtil.getNextItemID(), Materials.orePieces, "pieceNickel");
      pieceMithril = new ItemPieces(IdUtil.getNextItemID(), Materials.orePieces, "pieceMithril");
      pieceTungsten = new ItemPieces(IdUtil.getNextItemID(), Materials.orePieces, "pieceTungsten");
      pieceAdamantium = new ItemPieces(IdUtil.getNextItemID(), Materials.orePieces, "pieceAdamantium");
      mashedCactus = (ItemFood)(new ItemFood(IdUtil.getNextItemID(), Materials.mashedCactus, 1, 0, false, true, false, "mashedCactus")).setMaxStackSize(4);
      lemon = (new ItemFood(IdUtil.getNextItemID(), Material.fruit, 2, 1, 1000, false, false, true, "lemon")).setPlantProduct();
      lemonPie = (new ItemFood(IdUtil.getNextItemID(), Material.pie, 10, 6, 1000, true, true, true, "lemon_pie")).setMaxStackSize(8).setPlantProduct().setAnimalProduct();
      nickelBucket = new ItemBucket(IdUtil.getNextItemID(), Materials.nickel, (Material)null);
      nickelBucketWater = (ItemBucket)(new ItemBucket(IdUtil.getNextItemID(), Materials.nickel, Material.water)).setContainerItem(nickelBucket);
      nickelBucketLava = (ItemBucket)(new ItemBucket(IdUtil.getNextItemID(), Materials.nickel, Material.lava)).setContainerItem(nickelBucket);
      nickelBucketStone = (ItemBucket)(new ItemBucket(IdUtil.getNextItemID(), Materials.nickel, Material.stone)).setContainerItem(nickelBucket);
      nickelBucketMilk = (ItemBucketMilk)(new ItemBucketMilk(IdUtil.getNextItemID(), Materials.nickel)).setContainerItem(nickelBucket);
      tungstenBucket = new ItemBucket(IdUtil.getNextItemID(), Materials.tungsten, (Material)null);
      tungstenBucketWater = (ItemBucket)(new ItemBucket(IdUtil.getNextItemID(), Materials.tungsten, Material.water)).setContainerItem(tungstenBucket);
      tungstenBucketLava = (ItemBucket)(new ItemBucket(IdUtil.getNextItemID(), Materials.tungsten, Material.lava)).setContainerItem(tungstenBucket);
      tungstenBucketStone = (ItemBucket)(new ItemBucket(IdUtil.getNextItemID(), Materials.tungsten, Material.stone)).setContainerItem(tungstenBucket);
      tungstenBucketMilk = (ItemBucketMilk)(new ItemBucketMilk(IdUtil.getNextItemID(), Materials.tungsten)).setContainerItem(tungstenBucket);
      copperBucketWaterSuspicious = (ItemBucket)(new ItemBucket(IdUtil.getNextItemID(), Material.copper, Materials.unsafe_water)).setContainerItem(bucketCopperEmpty);
      silverBucketWaterSuspicious = (ItemBucket)(new ItemBucket(IdUtil.getNextItemID(), Material.silver, Materials.unsafe_water)).setContainerItem(bucketSilverEmpty);
      goldBucketWaterSuspicious = (ItemBucket)(new ItemBucket(IdUtil.getNextItemID(), Material.gold, Materials.unsafe_water)).setContainerItem(bucketGoldEmpty);
      ironBucketWaterSuspicious = (ItemBucket)(new ItemBucket(IdUtil.getNextItemID(), Material.iron, Materials.unsafe_water)).setContainerItem(bucketIronEmpty);
      nickelBucketWaterSuspicious = (ItemBucket)(new ItemBucket(IdUtil.getNextItemID(), Materials.nickel, Materials.unsafe_water)).setContainerItem(nickelBucket);
      ancientmetalBucketWaterSuspicious = (ItemBucket)(new ItemBucket(IdUtil.getNextItemID(), Material.ancient_metal, Materials.unsafe_water)).setContainerItem(bucketAncientMetalEmpty);
      mithrilBucketWaterSuspicious = (ItemBucket)(new ItemBucket(IdUtil.getNextItemID(), Material.mithril, Materials.unsafe_water)).setContainerItem(bucketMithrilEmpty);
      tungstenBucketWaterSuspicious = (ItemBucket)(new ItemBucket(IdUtil.getNextItemID(), Materials.tungsten, Materials.unsafe_water)).setContainerItem(tungstenBucket);
      adamantiumBucketWaterSuspicious = (ItemBucket)(new ItemBucket(IdUtil.getNextItemID(), Material.adamantium, Materials.unsafe_water)).setContainerItem(bucketAdamantiumEmpty);
      copperBucketWaterSwampland = (ItemBucket)(new ItemBucket(IdUtil.getNextItemID(), Material.copper, Materials.dangerous_water)).setContainerItem(bucketCopperEmpty);
      silverBucketWaterSwampland = (ItemBucket)(new ItemBucket(IdUtil.getNextItemID(), Material.silver, Materials.dangerous_water)).setContainerItem(bucketSilverEmpty);
      goldBucketWaterSwampland = (ItemBucket)(new ItemBucket(IdUtil.getNextItemID(), Material.gold, Materials.dangerous_water)).setContainerItem(bucketGoldEmpty);
      ironBucketWaterSwampland = (ItemBucket)(new ItemBucket(IdUtil.getNextItemID(), Material.iron, Materials.dangerous_water)).setContainerItem(bucketIronEmpty);
      nickelBucketWaterSwampland = (ItemBucket)(new ItemBucket(IdUtil.getNextItemID(), Materials.nickel, Materials.dangerous_water)).setContainerItem(nickelBucket);
      ancientmetalBucketWaterSwampland = (ItemBucket)(new ItemBucket(IdUtil.getNextItemID(), Material.ancient_metal, Materials.dangerous_water)).setContainerItem(bucketAncientMetalEmpty);
      mithrilBucketWaterSwampland = (ItemBucket)(new ItemBucket(IdUtil.getNextItemID(), Material.mithril, Materials.dangerous_water)).setContainerItem(bucketMithrilEmpty);
      tungstenBucketWaterSwampland = (ItemBucket)(new ItemBucket(IdUtil.getNextItemID(), Materials.tungsten, Materials.dangerous_water)).setContainerItem(tungstenBucket);
      adamantiumBucketWaterSwampland = (ItemBucket)(new ItemBucket(IdUtil.getNextItemID(), Material.adamantium, Materials.dangerous_water)).setContainerItem(bucketAdamantiumEmpty);
      Wolf_fur = ((ItemChain)ReflectHelper.createInstance(ItemChain.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.wolf_fur})).setCraftingDifficultyAsComponent(100.0F).setUnlocalizedName("small_leather").setCreativeTab(CreativeTabs.tabMaterials).setMaxStackSize(16);
      horse_meat = new ItemMeat(IdUtil.getNextItemID(), 6, 6, true, false, "horse_meat");
      horse_meat_cooked = new ItemMeat(IdUtil.getNextItemID(), 12, 12, true, true, "horse_meat_cooked");
      WolfHelmet = new ItemHelmet(IdUtil.getNextItemID(), Materials.wolf_fur, true);
      WolfChestplate = new ItemCuirass(IdUtil.getNextItemID(), Materials.wolf_fur, true);
      WolfLeggings = new ItemLeggings(IdUtil.getNextItemID(), Materials.wolf_fur, true);
      WolfBoots = new ItemBoots(IdUtil.getNextItemID(), Materials.wolf_fur, true);
      Goldenapple = (ItemAppleGold)(new ItemAppleGold(66, 2, 1, "VANILLA")).setAlwaysEdible().setPotionEffect(Potion.regeneration.id, 30, 0, 1.0F).setUnlocalizedName("appleGold").useVanillaTexture("apple_golden");
      Goldenapplelegend = (ItemGoldenAppleLegend)(new ItemGoldenAppleLegend(IdUtil.getNextItemID(), 2, 1, "goldapple")).setAlwaysEdible().setPotionEffect(Potion.regeneration.id, 30, 4, 1.0F).setUnlocalizedName("wtfk").useVanillaTexture("apple_golden_legend");
      bowlLemonade = (ItemBowl)(new ItemBowl(IdUtil.getNextItemID(), Materials.lemonade, "lemonade")).setFoodValue(4, 1, false, true, true).setPlantProduct().setUnlocalizedName("lemonade");
      bowlEmpty = (ItemBowl)(new ItemBowl(25, (Material)null, "VANILLA")).setUnlocalizedName("bowl").useVanillaTexture("bowl").setMaxStackSize(8);
      morningStarCopper = new ItemMorningStar(IdUtil.getNextItemID(), Material.copper);
      morningStarSilver = new ItemMorningStar(IdUtil.getNextItemID(), Material.silver);
      morningStarGold = new ItemMorningStar(IdUtil.getNextItemID(), Material.gold);
      morningStarIron = new ItemMorningStar(IdUtil.getNextItemID(), Material.iron);
      morningStarNickel = new ItemMorningStar(IdUtil.getNextItemID(), Materials.nickel);
      morningStarAncientMetal = new ItemMorningStar(IdUtil.getNextItemID(), Material.ancient_metal);
      morningStarMithril = new ItemMorningStar(IdUtil.getNextItemID(), Material.mithril);
      morningStarTungsten = new ItemMorningStar(IdUtil.getNextItemID(), Materials.tungsten);
      morningStarAdamantium = new ItemMorningStar(IdUtil.getNextItemID(), Material.adamantium);
      fragStalkerCreeper = new ItemStandard(IdUtil.getNextItemID(), Material.frags, "fragStalkerCreeper");
      glowberries = (ItemFood)(new ItemFood(IdUtil.getNextItemID(), Materials.glowberries, 1, 0, false, false, false, "glow_berries")).setMaxStackSize(16).setAlwaysEdible();
      arrowMagical = new ItemArrow(IdUtil.getNextItemID(), Materials.magical);
      LavaWand = new ItemWand(IdUtil.getNextItemID(), Materials.tungsten, "wandlava");
      FreezeWand = new ItemWand(IdUtil.getNextItemID(), Materials.nickel, "wandfreeze");
      ShockWand = new ItemWand(IdUtil.getNextItemID(), Material.ancient_metal, "wandshock");
      ExperimentalPotion = (new ItemPotionExperimental(IdUtil.getNextItemID())).setUnlocalizedName("experimentalPotion").setCreativeTab(CreativeTabs.tabMisc);
      recordDamnation = (ItemRecordExtend)(new ItemRecordExtend(2024, "imported.damnation", "record_damnation", "Damnation", "Mwk feat. Hatsune Miku")).setUnlocalizedName("record");
      recordConnected = (ItemRecordExtend)(new ItemRecordExtend(2025, "imported.connected", "record_connected", "Connected", "Mwk feat. Hatsune Miku")).setUnlocalizedName("record");
      VibraniumHelmet = new ItemHelmet(IdUtil.getNextItemID(), Materials.vibranium, false);
      VibraniumChestplate = new ItemCuirass(IdUtil.getNextItemID(), Materials.vibranium, false);
      VibraniumLeggings = new ItemLeggings(IdUtil.getNextItemID(), Materials.vibranium, false);
      VibraniumBoots = new ItemBoots(IdUtil.getNextItemID(), Materials.vibranium, false);
      VibraniumSword = (ItemSword)ReflectHelper.createInstance(ItemSword.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.vibranium});
      lapis = (ItemRockExtend)(new ItemRockExtend(IdUtil.getNextItemID(), Material.lapis_lazuli, "lapis_lazuli")).setXPReward(5);
      helmetCustom_a = new ItemHelmet(IdUtil.getNextItemID(), Materials.custom_a, false);
      chestplateCustom_a = new ItemCuirass(IdUtil.getNextItemID(), Materials.custom_a, false);
      leggingsCustom_a = new ItemLeggings(IdUtil.getNextItemID(), Materials.custom_a, false);
      bootsCustom_a = new ItemBoots(IdUtil.getNextItemID(), Materials.custom_a, false);
      HelmetAncientmetalsacred = new ItemHelmet(IdUtil.getNextItemID(), Materials.ancient_metal_sacred, false);
      ChestplateAncientmetalsacred = new ItemCuirass(IdUtil.getNextItemID(), Materials.ancient_metal_sacred, false);
      LeggingsAncientmetalsacred = new ItemLeggings(IdUtil.getNextItemID(), Materials.ancient_metal_sacred, false);
      BootsAncientmetalsacred = new ItemBoots(IdUtil.getNextItemID(), Materials.ancient_metal_sacred, false);
      AncientmetalArmorPiece = ((ItemNugget)ReflectHelper.createInstance(ItemNugget.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.ancient_metal_sacred})).setCraftingDifficultyAsComponent(800.0F).setUnlocalizedName("ancient_metal_sacred_piece").setCreativeTab(CreativeTabs.tabMaterials).setMaxStackSize(16);
      Agave = (ItemFood)(new ItemFood(IdUtil.getNextItemID(), Materials.agave, 1, 0, false, false, false, "agave")).setMaxStackSize(16).setAlwaysEdible();
      Pulque = (new ItemWine(IdUtil.getNextItemID())).setUnlocalizedName("pulque").setCreativeTab(CreativeTabs.tabFood);
      Ale = (new ItemWine(IdUtil.getNextItemID())).setUnlocalizedName("ale").setCreativeTab(CreativeTabs.tabFood);
      bowTungsten = new ItemBow(IdUtil.getNextItemID(), Materials.tungsten);
      UruHelmet = new ItemHelmet(IdUtil.getNextItemID(), Materials.uru, false);
      UruChestplate = new ItemCuirass(IdUtil.getNextItemID(), Materials.uru, false);
      UruLeggings = new ItemLeggings(IdUtil.getNextItemID(), Materials.uru, false);
      UruBoots = new ItemBoots(IdUtil.getNextItemID(), Materials.uru, false);
      UruNugget = (ItemNugget)ReflectHelper.createInstance(ItemNugget.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.uru});
      UruBattleAxe = (ItemBattleAxe)ReflectHelper.createInstance(ItemBattleAxe.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.uru});
      UruIngot = ((ItemIngot)ReflectHelper.createInstance(ItemIngot.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.uru})).setXPReward(150);
      UruSword = (ItemSword)ReflectHelper.createInstance(ItemSword.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.uru});
      UruWarHammer = (ItemWarHammer)ReflectHelper.createInstance(ItemWarHammer.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.uru});
      UruMattock = (ItemMattock)ReflectHelper.createInstance(ItemMattock.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.uru});
      UruScythe = (ItemScythe)ReflectHelper.createInstance(ItemScythe.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.uru});
      pieceUru = new ItemPieces(IdUtil.getNextItemID(), Materials.orePieces, "pieceAdamantium");
      forgingnote = new ItemStandard(IdUtil.getNextItemID(), Materials.paper, "forging_note");
      seedsBeetroot = new ItemSeeds(IdUtil.getNextItemID(), 1, 1, false, false, false, Blocks.beetroots.blockID, Block.tilledField.blockID, "Beetrootseeds");
      beetroot = (new ItemFood(IdUtil.getNextItemID(), Materials.beetroot, 2, 1, 1000, false, false, true, "Beetroot")).setPlantProduct();
      bowlSalmonSoup = (ItemBowl)(new ItemBowl(IdUtil.getNextItemID(), Materials.fish_soup, "salmon_soup")).setFoodValue(14, 14, true, true, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("salmonSoup");
      bowlBeetrootSoup = (ItemBowl)(new ItemBowl(IdUtil.getNextItemID(), Materials.beetroot, "beetroot_soup")).setFoodValue(15, 6, 6000, false, true, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("beetrootSoup");
      claybowlRaw = (ItemStandard)(new ItemStandard(IdUtil.getNextItemID(), Material.clay, "bowlclayRaw")).setMaxStackSize(4);
      claybowlEmpty = (ItemBowlClay)(new ItemBowlClay(IdUtil.getNextItemID(), (Material)null, "VANILLA")).setUnlocalizedName("bowlclay").useVanillaTexture("bowlclay").setMaxStackSize(8);
      claybowlMushroomStew = (ItemBowlClay)(new ItemBowlClay(IdUtil.getNextItemID(), Material.mushroom_stew, "mushroom_stew")).setFoodValue(2, 4, false, false, false).setPlantProduct().setUnlocalizedName("mushroomStew");
      claybowlMilk = (ItemBowlClay)(new ItemBowlClay(IdUtil.getNextItemID(), Material.milk, "bowl_milk")).setFoodValue(0, 1, true, false, false).setAnimalProduct().setContainerItem(claybowlEmpty).setAlwaysEdible().setUnlocalizedName("bowlMilk");
      claybowlWater = (ItemBowlClay)(new ItemBowlClay(IdUtil.getNextItemID(), Material.water, "bowl_water")).setContainerItem(claybowlEmpty).setUnlocalizedName("bowlWater");
      claybowlBeefStew = (ItemBowlClay)(new ItemBowlClay(IdUtil.getNextItemID(), Material.beef_stew, "beef_stew")).setFoodValue(16, 16, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("beefStew");
      claybowlChickenSoup = (ItemBowlClay)(new ItemBowlClay(IdUtil.getNextItemID(), Material.chicken_soup, "chicken_soup")).setFoodValue(10, 10, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("chickenSoup");
      claybowlVegetableSoup = (ItemBowlClay)(new ItemBowlClay(IdUtil.getNextItemID(), Material.vegetable_soup, "vegetable_soup")).setFoodValue(6, 6, false, false, true).setPlantProduct().setUnlocalizedName("vegetableSoup");
      claybowlIceCream = (ItemBowlClay)(new ItemBowlClay(IdUtil.getNextItemID(), Material.ice_cream, "ice_cream")).setFoodValue(5, 4, 1000, true, false, false).setPlantProduct().setAnimalProduct().setUnlocalizedName("iceCream");
      claybowlSalad = (ItemBowlClay)(new ItemBowlClay(IdUtil.getNextItemID(), Material.salad, "bowl_salad")).setFoodValue(1, 1, false, false, true).setPlantProduct().setUnlocalizedName("salad");
      claybowlCreamOfMushroomSoup = (ItemBowlClay)(new ItemBowlClay(IdUtil.getNextItemID(), Material.cream_of_mushroom_soup, "cream_of_mushroom_soup")).setFoodValue(3, 5, true, false, false).setPlantProduct().setAnimalProduct().setUnlocalizedName("creamOfMushroomSoup");
      claybowlCreamOfVegetableSoup = (ItemBowlClay)(new ItemBowlClay(IdUtil.getNextItemID(), Material.cream_of_vegetable_soup, "cream_of_vegetable_soup")).setFoodValue(7, 7, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("creamOfVegetableSoup");
      claybowlPumpkinSoup = (ItemBowlClay)(new ItemBowlClay(IdUtil.getNextItemID(), Material.pumpkin_soup, "pumpkin_soup")).setFoodValue(1, 2, false, false, true).setPlantProduct().setUnlocalizedName("pumpkinSoup");
      claybowlMashedPotato = (ItemBowlClay)(new ItemBowlClay(IdUtil.getNextItemID(), Material.mashed_potato, "mashed_potato")).setFoodValue(12, 8, true, false, false).setPlantProduct().setAnimalProduct().setUnlocalizedName("mashedPotato");
      claybowlSorbet = (ItemBowlClay)(new ItemBowlClay(IdUtil.getNextItemID(), Material.sorbet, "sorbet")).setFoodValue(4, 2, 2000, false, false, true).setPlantProduct().setUnlocalizedName("sorbet");
      claybowlPorridge = (ItemBowlClay)(new ItemBowlClay(IdUtil.getNextItemID(), Material.porridge, "porridge")).setFoodValue(4, 2, 2000, false, false, true).setPlantProduct().setUnlocalizedName("porridge");
      claybowlCereal = (ItemBowlClay)(new ItemBowlClay(IdUtil.getNextItemID(), Material.cereal, "cereal")).setFoodValue(4, 2, 1000, true, false, false).setPlantProduct().setAnimalProduct().setUnlocalizedName("cereal");
      claybowlLemonade = (ItemBowlClay)(new ItemBowlClay(IdUtil.getNextItemID(), Materials.lemonade, "lemonade")).setFoodValue(4, 1, false, true, true).setPlantProduct().setUnlocalizedName("lemonade");
      claybowlPorkchopStew = (ItemBowlClay)(new ItemBowlClay(IdUtil.getNextItemID(), Materials.porkchop_stew, "porkchop_stew")).setFoodValue(14, 14, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("porkchopStew");
      claybowlChestnutSoup = (ItemBowlClay)(new ItemBowlClay(IdUtil.getNextItemID(), Materials.chestnut_soup, "lampchop_stew")).setFoodValue(12, 12, true, false, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("lampchopStew");
      claybowlWaterSuspicious = (ItemBowlClay)(new ItemBowlClay(IdUtil.getNextItemID(), Materials.unsafe_water, "suspicious_water")).setUnlocalizedName("SuspiciousWater");
      claybowlWaterSwampland = (ItemBowlClay)(new ItemBowlClay(IdUtil.getNextItemID(), Materials.dangerous_water, "swampland_water")).setUnlocalizedName("SwamplandWater");
      claybowlSalmonSoup = (ItemBowlClay)(new ItemBowlClay(IdUtil.getNextItemID(), Materials.fish_soup, "salmon_soup")).setFoodValue(14, 14, true, true, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("salmonSoup");
      claybowlBeetrootSoup = (ItemBowlClay)(new ItemBowlClay(IdUtil.getNextItemID(), Materials.beetroot, "beetroot_soup")).setFoodValue(15, 6, 6000, false, true, true).setPlantProduct().setAnimalProduct().setUnlocalizedName("beetrootSoup");
      totemoffecund = (ItemTotem)(new ItemTotem(IdUtil.getNextItemID(), Material.gold, "totem")).setMaxStackSize(1);
      helmetCustom_b = new ItemHelmet(IdUtil.getNextItemID(), Materials.custom_b, false);
      chestplateCustom_b = new ItemCuirass(IdUtil.getNextItemID(), Materials.custom_b, false);
      leggingsCustom_b = new ItemLeggings(IdUtil.getNextItemID(), Materials.custom_b, false);
      bootsCustom_b = new ItemBoots(IdUtil.getNextItemID(), Materials.custom_b, false);
      fishingRodNickel = (ItemFishingRod)(new ItemFishingRod(IdUtil.getNextItemID(), Materials.nickel)).setUnlocalizedName("fishingRod");
      fishingRodTungsten = (ItemFishingRod)(new ItemFishingRod(IdUtil.getNextItemID(), Materials.tungsten)).setUnlocalizedName("fishingRod");
      carrotOnAStickNickel = (ItemCarrotOnAStick)(new ItemCarrotOnAStick(IdUtil.getNextItemID(), Materials.nickel)).setUnlocalizedName("carrotOnAStick");
      carrotOnAStickTungsten = (ItemCarrotOnAStick)(new ItemCarrotOnAStick(IdUtil.getNextItemID(), Materials.tungsten)).setUnlocalizedName("carrotOnAStick");
      SuspiciousPotion = (ItemPotionSuspicious)(new ItemPotionSuspicious(IdUtil.getNextItemID())).setUnlocalizedName("suspiciousPotion").setCreativeTab(CreativeTabs.tabMisc);
      totemofdestroy = (ItemTotem)(new ItemTotem(IdUtil.getNextItemID(), Materials.tungsten, "totem")).setMaxStackSize(1);
      totemofpreserve = (ItemTotem)(new ItemTotem(IdUtil.getNextItemID(), Material.iron, "totem")).setMaxStackSize(1);
      totemofknowledge = (ItemTotem)(new ItemTotem(IdUtil.getNextItemID(), Material.ancient_metal, "totem")).setMaxStackSize(1);
      ignitionCopper = new ItemIgnition(IdUtil.getNextItemID(), Material.copper);
      ignitionSilver = new ItemIgnition(IdUtil.getNextItemID(), Material.silver);
      ignitionGold = new ItemIgnition(IdUtil.getNextItemID(), Material.gold);
      ignitionIron = new ItemIgnition(3, Material.iron);
      ignitionNickel = new ItemIgnition(IdUtil.getNextItemID(), Materials.nickel);
      ignitionTungsten = new ItemIgnition(IdUtil.getNextItemID(), Materials.tungsten);
      ignitionMithril = new ItemIgnition(IdUtil.getNextItemID(), Material.mithril);
      ignitionAncientMetal = new ItemIgnition(IdUtil.getNextItemID(), Material.ancient_metal);
      ignitionAdamantium = new ItemIgnition(IdUtil.getNextItemID(), Material.adamantium);
      ignitionWood = new ItemIgnition(IdUtil.getNextItemID(), Material.wood);
      wither_branch = (new ItemBrewingMisc(IdUtil.getNextItemID(), Material.wood, "wither_wood")).setPotionEffectExtend("+0-1+2+3+13&4-4");
      guide = new ItemGuideBook(IdUtil.getNextItemID());
      totemofhunting = (ItemTotem)(new ItemTotem(IdUtil.getNextItemID(), Materials.nickel, "totem")).setMaxStackSize(1);
      UruMorningStar = (ItemClub)ReflectHelper.createInstance(ItemClub.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.uru});
      UruPickaxe = (ItemPickaxe)ReflectHelper.createInstance(ItemPickaxe.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.uru});
      shardAzurite = (ItemRockExtend)(new ItemRockExtend(IdUtil.getNextItemID(), Materials.crystal, "azurite")).setXPReward(1);
      detectorEmerald = (new ItemDetector(IdUtil.getNextItemID(), Material.emerald, "emerald")).setUnlocalizedName("detector");
      detectorDiamond = (new ItemDetector(IdUtil.getNextItemID(), Material.diamond, "diamond")).setUnlocalizedName("detector");
      sulphur = (new ItemStandard(IdUtil.getNextItemID(), Materials.sulphur, "sulphur_sphere")).setMaxStackSize(16);
      hellhoundFur = ((ItemIngot)ReflectHelper.createInstance(ItemIngot.class, new Class[]{Integer.TYPE, Material.class}, new Object[]{IdUtil.getNextItemID(), Materials.wolf_fur})).setCraftingDifficultyAsComponent(100.0F).setUnlocalizedName("small_leather").setCreativeTab(CreativeTabs.tabMaterials).setMaxStackSize(16);
      HellhoundHelmet = new ItemHelmet(IdUtil.getNextItemID(), Materials.wolf_fur, false);
      HellhoundChestplate = new ItemCuirass(IdUtil.getNextItemID(), Materials.wolf_fur, false);
      HellhoundLeggings = new ItemLeggings(IdUtil.getNextItemID(), Materials.wolf_fur, false);
      HellhoundBoots = new ItemBoots(IdUtil.getNextItemID(), Materials.wolf_fur, false);
      leatherKettle = (ItemKettle)(new ItemKettle(IdUtil.getNextItemID(), 13, Material.water, Material.leather)).setUnlocalizedName("leather_kettle");
      leatherKettleSuspicious = (ItemKettle)(new ItemKettle(IdUtil.getNextItemID(), 13, Materials.unsafe_water, Material.leather)).setUnlocalizedName("leather_kettle_sus");
      leatherKettleSwampland = (ItemKettle)(new ItemKettle(IdUtil.getNextItemID(), 13, Materials.dangerous_water, Material.leather)).setUnlocalizedName("leather_kettle_danger");
      clayJug = (ItemStandard)(new ItemStandard(IdUtil.getNextItemID(), Material.clay, "clayJug")).setMaxStackSize(1);
      hardenedClayJug = (ItemKettle)(new ItemKettle(IdUtil.getNextItemID(), 19, Material.water, Material.hardened_clay)).setUnlocalizedName("hardened_clay_jug");
      hardenedClayJugSuspicious = (ItemKettle)(new ItemKettle(IdUtil.getNextItemID(), 19, Materials.unsafe_water, Material.hardened_clay)).setUnlocalizedName("hardened_clay_jug_sus");
      hardenedClayJugSwampland = (ItemKettle)(new ItemKettle(IdUtil.getNextItemID(), 19, Materials.dangerous_water, Material.hardened_clay)).setUnlocalizedName("hardened_clay_jug_danger");
   }
}
