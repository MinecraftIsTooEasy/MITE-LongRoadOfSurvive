package net.oilcake.mitelros.item;

import net.minecraft.EnumEquipmentMaterial;
import net.minecraft.MapColor;
import net.minecraft.Material;
import net.minecraft.MaterialFood;
import net.minecraft.MaterialLiquid;
import net.minecraft.Minecraft;

public class Materials extends Material {
   public static final Materials nickel;
   public static final Materials tungsten;
   public static final Materials vibranium;
   public static final Materials uru;
   public static final Material porkchop_stew;
   public static final Material chestnut_soup;
   public static final Material fish_soup;
   public static final Material mashedCactus;
   public static final Material glowberries;
   public static final Material orePieces;
   public static final Materials wolf_fur;
   public static final Materials custom_a;
   public static final Materials custom_b;
   public static final Material lemonade;
   public static final Material unsafe_water;
   public static final Material dangerous_water;
   public static final Materials magical;
   public static final Materials ancient_metal_sacred;
   public static final Material agave;
   public static final Material beetroot;
   public static final Material crystal;
   public static final Material sulphur;

   public Materials(EnumEquipmentMaterial enum_crafting_material) {
      super(enum_crafting_material);
   }

   public Materials(String name, MapColor map_color) {
      super(name, map_color);
   }

   public Materials(String name) {
      super(name);
   }

   public String getName() {
      return super.name;
   }

   public float getDurability() {
      return super.durability;
   }

   public float getDamageVsEntity() {
      if (this == vibranium) {
         return 2.0F;
      } else if (this == uru) {
         return 6.0F;
      } else if (this == nickel) {
         return 4.0F;
      } else if (this == tungsten) {
         return 5.0F;
      } else if (this == magical) {
         return 0.0F;
      } else {
         Minecraft.setErrorMessage("getDamageVsEntity: unhandled material " + this.name);
         return 0.0F;
      }
   }

   static {
      nickel = (Materials)(new Materials(EnumEquipmentMaterials.nickel)).setRequiresTool().setMetal(false).setMinHarvestLevel(3);
      tungsten = (Materials)(new Materials(EnumEquipmentMaterials.tungsten)).setRequiresTool().setMetal(true).setHarmedByLava(false).setMinHarvestLevel(4);
      vibranium = (Materials)(new Materials(EnumEquipmentMaterials.vibranium)).setRequiresTool().setMetal(true).setMinHarvestLevel(1);
      uru = (Materials)(new Materials(EnumEquipmentMaterials.uru)).setRequiresTool().setMetal(true).setMinHarvestLevel(5);
      porkchop_stew = (new MaterialFood("porkchop_stew")).setHarmedByPepsin();
      chestnut_soup = (new MaterialFood("chestnut_soup")).setHarmedByPepsin();
      fish_soup = (new MaterialFood("chestnut_soup")).setHarmedByPepsin();
      mashedCactus = new MaterialFood("mashed_cactus");
      glowberries = new MaterialFood("glowberries");
      orePieces = new Material("Pieces");
      wolf_fur = (Materials)(new Materials(EnumEquipmentMaterials.wolf_fur)).setMetal(false).setMinHarvestLevel(0);
      custom_a = (Materials)(new Materials(EnumEquipmentMaterials.custom_a)).setMetal(false).setMinHarvestLevel(0);
      custom_b = (Materials)(new Materials(EnumEquipmentMaterials.custom_b)).setMetal(false).setMinHarvestLevel(0);
      lemonade = (new MaterialFood("lemonade")).setDrinkable();
      unsafe_water = (new MaterialLiquid("suspicious_water", MapColor.waterColor)).setDrinkable();
      dangerous_water = (new MaterialLiquid("swampland_water", MapColor.waterColor)).setDrinkable();
      magical = (Materials)(new Materials(EnumEquipmentMaterials.magical)).setMetal(false).setMinHarvestLevel(0);
      ancient_metal_sacred = (Materials)(new Materials(EnumEquipmentMaterials.ancient_metal_sacred)).setRequiresTool().setMetal(true).setMinHarvestLevel(3);
      agave = new MaterialFood("agave");
      beetroot = new MaterialFood("beetroot");
      crystal = (new Material("crystal")).setDurability(4.0F);
      sulphur = (new Material("sulphur")).setDurability(2.0F);
   }
}
