package net.oilcake.mitelros.util;

import net.minecraft.Item;
import net.minecraft.ItemArmor;
import net.minecraft.ResourceLocation;
import net.oilcake.mitelros.item.Items;

public class Constant {
   public static final ResourceLocation icons_itf = new ResourceLocation("textures/gui/icons_mitf.png");
   public static ItemArmor[] HELMETS = null;
   public static ItemArmor[] CHESTPLATES = null;
   public static ItemArmor[] LEGGINGS = null;
   public static ItemArmor[] BOOTS = null;
   public static Item[] SWORDS = null;
   public static ItemArmor[][] ARMORS = null;


   public static void initItemArray() {
      HELMETS = new ItemArmor[]{Item.helmetLeather, Item.helmetChainCopper, Item.helmetCopper, Item.helmetRustedIron, Item.helmetChainIron, Item.helmetIron, Item.helmetChainAncientMetal, Item.helmetAncientMetal, Item.helmetChainMithril, Item.helmetMithril, Item.helmetAdamantium, Items.nickelHelmet, Items.nickelHelmetChain, Items.tungstenHelmet, Items.tungstenHelmetChain, Items.WolfHelmet, Items.VibraniumHelmet, Items.helmetCustom_a, Items.HelmetAncientmetalsacred, Items.UruHelmet, Items.helmetCustom_a, Items.HellhoundHelmet};
      CHESTPLATES = new ItemArmor[]{Item.plateLeather, Item.plateChainCopper, Item.plateCopper, Item.plateRustedIron, Item.plateChainIron, Item.plateIron, Item.plateChainAncientMetal, Item.plateAncientMetal, Item.plateChainMithril, Item.plateMithril, Item.plateAdamantium, Items.nickelChestplate, Items.nickelChestplateChain, Items.tungstenChestplate, Items.tungstenChestplateChain, Items.WolfChestplate, Items.VibraniumChestplate, Items.chestplateCustom_a, Items.ChestplateAncientmetalsacred, Items.UruChestplate, Items.chestplateCustom_a, Items.HellhoundChestplate};
      LEGGINGS = new ItemArmor[]{Item.legsLeather, Item.legsChainCopper, Item.legsCopper, Item.legsRustedIron, Item.legsChainIron, Item.legsIron, Item.legsChainAncientMetal, Item.legsAncientMetal, Item.legsChainMithril, Item.legsMithril, Item.legsAdamantium, Items.nickelLeggings, Items.nickelLeggingsChain, Items.tungstenLeggings, Items.tungstenLeggings, Items.WolfLeggings, Items.VibraniumLeggings, Items.leggingsCustom_a, Items.LeggingsAncientmetalsacred, Items.UruLeggings, Items.leggingsCustom_a, Items.HellhoundLeggings};
      BOOTS = new ItemArmor[]{Item.bootsLeather, Item.bootsChainCopper, Item.bootsCopper, Item.bootsRustedIron, Item.bootsChainIron, Item.bootsIron, Item.bootsChainAncientMetal, Item.bootsAncientMetal, Item.bootsChainMithril, Item.bootsMithril, Item.bootsAdamantium, Items.nickelBoots, Items.nickelBootsChain, Items.tungstenBoots, Items.tungstenBootsChain, Items.WolfBoots, Items.VibraniumBoots, Items.bootsCustom_a, Items.BootsAncientmetalsacred, Items.UruBoots, Items.bootsCustom_a, Items.HellhoundBoots};
      ARMORS = new ItemArmor[][]{HELMETS, CHESTPLATES, LEGGINGS, BOOTS};
      SWORDS = new Item[]{Item.swordRustedIron, Item.swordIron, Item.swordAncientMetal, Item.swordMithril, Item.swordAdamantium, Items.nickelSword, Items.tungstenSword, Items.VibraniumSword, Items.UruSword};
   }

   public static int CalculateCurrentDiff() {
      int Diff = 0;
      if ((Boolean)StuckTagConfig.TagConfig.TagFallenInMineLVL1.ConfigValue && !(Boolean)StuckTagConfig.TagConfig.TagFallenInMineLVL2.ConfigValue) {
         ++Diff;
      }

      if ((Boolean)StuckTagConfig.TagConfig.TagBattleSufferLVL1.ConfigValue && !(Boolean)StuckTagConfig.TagConfig.TagBattleSufferLVL2.ConfigValue) {
         ++Diff;
      }

      if ((Boolean)StuckTagConfig.TagConfig.TagFallenInMineLVL2.ConfigValue) {
         Diff += 2;
      }

      if ((Boolean)StuckTagConfig.TagConfig.TagBattleSufferLVL2.ConfigValue) {
         Diff += 2;
      }

      if ((Boolean)StuckTagConfig.TagConfig.TagHeatStroke.ConfigValue) {
         ++Diff;
      }

      if ((Boolean)StuckTagConfig.TagConfig.TagNoWeatherPredict.ConfigValue) {
         ++Diff;
      }

      if ((Boolean)StuckTagConfig.TagConfig.TagInstinctSurvival.ConfigValue) {
         ++Diff;
      }

      if ((Boolean)StuckTagConfig.TagConfig.TagInvisibleFollower.ConfigValue) {
         ++Diff;
      }

      if ((Boolean)StuckTagConfig.TagConfig.TagEternalRaining.ConfigValue) {
         Diff += 2;
      }

      if ((Boolean)StuckTagConfig.TagConfig.TagUnstableConvection.ConfigValue) {
         ++Diff;
      }

      if ((Boolean)StuckTagConfig.TagConfig.TagDryDilemma.ConfigValue) {
         ++Diff;
      }

      if ((Boolean)StuckTagConfig.TagConfig.TagDeadgeothermy.ConfigValue) {
         Diff += 2;
      }

      if ((Boolean)StuckTagConfig.TagConfig.TagApocalypse.ConfigValue) {
         Diff += 3;
      }

      if ((Boolean)StuckTagConfig.TagConfig.TagWorshipDark.ConfigValue) {
         Diff += 2;
      }

      if ((Boolean)StuckTagConfig.TagConfig.TagMiracleDisaster.ConfigValue) {
         ++Diff;
      }

      if ((Boolean)StuckTagConfig.TagConfig.TagAcousma.ConfigValue) {
         ++Diff;
      }

      if ((Boolean)StuckTagConfig.TagConfig.TagPseudovision.ConfigValue) {
         ++Diff;
      }

      if ((Boolean)StuckTagConfig.TagConfig.TagRejection.ConfigValue) {
         Diff += 2;
      }

      if ((Boolean)StuckTagConfig.TagConfig.TagUnderAlliance.ConfigValue) {
         ++Diff;
      }

      if ((Boolean)StuckTagConfig.TagConfig.TagTempSensitivity.ConfigValue) {
         Diff += 2;
      }

      if ((Boolean)StuckTagConfig.TagConfig.TagArmament.ConfigValue) {
         Diff -= 2;
      }

      if ((Boolean)StuckTagConfig.TagConfig.TagDistortion.ConfigValue) {
         Diff -= 2;
      }

      if ((Boolean)StuckTagConfig.TagConfig.TagDigest.ConfigValue) {
         Diff -= 2;
      }

      return Diff;
   }
}
