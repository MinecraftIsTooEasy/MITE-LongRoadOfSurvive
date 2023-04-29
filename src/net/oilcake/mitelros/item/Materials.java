package net.oilcake.mitelros.item;

import net.minecraft.*;

public class Materials extends Material {
    public static final Materials nickel = (Materials) new Materials(EnumEquipmentMaterials.nickel).setMetal(true).setMinHarvestLevel(3);
    public static final Materials tungsten = (Materials) new Materials(EnumEquipmentMaterials.tungsten).setMetal(true).setMinHarvestLevel(5);
    public static final Materials vibranium = (Materials) new Materials(EnumEquipmentMaterials.vibranium).setMetal(true).setMinHarvestLevel(1);
    public static final Materials uru = (Materials) new Materials(EnumEquipmentMaterials.uru).setMetal(true).setMinHarvestLevel(5);
    public static final Material porkchop_stew = (new MaterialFood("porkchop_stew")).setHarmedByPepsin();
    public static final Material chestnut_soup = (new MaterialFood("chestnut_soup")).setHarmedByPepsin();
    public static final Material mashedCactus = (new MaterialFood("mashed_cactus"));
    public static final Material glowberries = (new MaterialFood("glowberries"));
    public static final Material orePieces = (new Material("Pieces"));
    public static final Materials wolf_fur = (Materials) new Materials(EnumEquipmentMaterials.wolf_fur).setMetal(false).setMinHarvestLevel(0);
    public static final Materials maid = (Materials) new Materials(EnumEquipmentMaterials.maid).setMetal(false).setMinHarvestLevel(0);
    public static final Material lemonade = (new MaterialFood("lemonade")).setDrinkable();
    public static final Material unsafe_water = (new MaterialLiquid("suspicious_water", MaterialMapColor.waterColor)).setDrinkable();
    public static final Material dangerous_water = (new MaterialLiquid("swampland_water", MaterialMapColor.waterColor)).setDrinkable();
    public static final Materials magical = (Materials) new Materials(EnumEquipmentMaterials.magical).setMetal(false).setMinHarvestLevel(0);
    public static final Materials ancient_metal_sacred = (Materials) new Materials(EnumEquipmentMaterials.ancient_metal_sacred).setMetal(true).setMinHarvestLevel(3);
    public static final Material agave = (new MaterialFood("agave"));
    public Materials(EnumEquipmentMaterial enum_crafting_material) {
        super(enum_crafting_material);
    }
    public Materials(String name, MaterialMapColor map_color) {
        super(name, map_color);
    }

    public Materials(String name) {
        super(name);
    }

    public String getName() {
        return super.name;
    }

    public float getDurability(){
        return super.durability;
    }

    public float getDamageVsEntity() {
        if (this == wood) {
            return 0.0F;
        } else if (this == flint) {
            return 1.0F;
        } else if (this == obsidian) {
            return 2.0F;
        } else if (this == rusted_iron) {
            return 2.0F;
        } else if (this == vibranium) {
            return 2.0F;
        } else if (this == copper) {
            return 3.0F;
        } else if (this == silver) {
            return 3.0F;
        } else if (this == gold) {
            return 2.0F;
        } else if (this == iron) {
            return 4.0F;
        } else if (this == ancient_metal) {
            return 4.0F;
        } else if (this == mithril) {
            return 5.0F;
        } else if (this == adamantium) {
            return 6.0F;
        } else if (this == uru) {
            return 6.0F;
        } else if (this == diamond) {
            return 4.0F;
        } else if (this == nickel) {
            return 4.0F;
        } else if (this == tungsten){
            return 5.0F;
        } else if (this == magical){
            return 0.0F;
        }
        else {
            Minecraft.setErrorMessage("getDamageVsEntity: unhandled material " + this.name);
            return 0.0F;
        }
    }
}
