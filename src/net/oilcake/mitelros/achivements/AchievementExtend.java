package net.oilcake.mitelros.achivements;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.util.Constant;



public class AchievementExtend {
    public static Achievement FragofMine;
    public static Achievement BravetheCold;
    public static Achievement Arbalistic;
    public static Achievement getWitherSkull;
    public static Achievement getBeacon;
    public static Achievement mashedCactus;
    public static Achievement hypothermia;
    public static Achievement feast;
    public static Achievement SoundofUnder;
    public static Achievement nochoice;
    public static Achievement cheersforMinecraft;
    public static Achievement copying;
    public static Achievement neverEnds;
    public static Achievement forgingLegend;
    public static Achievement invincible;
    public AchievementExtend() {
    }

    public static void registerAchievements() {
        FragofMine = (new Achievement(getNextAchievementID(), "MinePieces", 2, 3, Items.pieceCopper, AchievementList.buildPickaxe)).registerAchievement();
        BravetheCold = (new Achievement(getNextAchievementID(), "WearAllWolfArmor", 11, -3, Items.WolfHelmet, AchievementList.wearLeather)).registerAchievement();
        Arbalistic = (new Achievement(getNextAchievementID(), "BuildMithrilBow", -4, 9, Items.bowTungsten, AchievementList.mithrilIngot)).registerAchievement();
        getWitherSkull = (new Achievement(getNextAchievementID(), "getWitherSkull", 4, 11, new ItemStack(Block.skull, 1, 1), AchievementList.portalToNether)).registerAchievement();
        getBeacon = (new Achievement(getNextAchievementID(), "getBeacon", 6, 11, Block.beacon, AchievementExtend.getWitherSkull)).setSpecial().registerAchievement();
        mashedCactus = (new Achievement(getNextAchievementID(), "mashedCactus", -3, -3, Items.mashedCactus, AchievementList.seeds)).registerAchievement();
        hypothermia = (new Achievement(getNextAchievementID(),"Hypothermia",-4,2, Block.ice,AchievementList.openInventory)).registerAchievement();
        feast = (new Achievement(getNextAchievementID(),"Feast",1,-3, Item.bowlBeefStew,AchievementList.fineDining)).setSpecial().registerAchievement();
        SoundofUnder = (new Achievement(getNextAchievementID(),"SoundofUnder",-4,11,Item.recordUnderworld,AchievementList.portal)).setSpecial().registerAchievement();
        nochoice = (new Achievement(getNextAchievementID(),"NoChoice",0,11,Items.ExperimentalPotion,AchievementList.portal)).registerAchievement();
        cheersforMinecraft = (new Achievement(getNextAchievementID(),"CheersforMinecraft",-3,-5,Items.Ale,AchievementExtend.mashedCactus)).registerAchievement();
        copying = (new Achievement(getNextAchievementID(),"Copying",-2,11,Items.forgingnote,AchievementExtend.SoundofUnder)).registerAchievement();
        neverEnds = (new Achievement(getNextAchievementID(),"NeverEnds",-2,17,Items.UruIngot,AchievementList.adamantiumIngot)).registerAchievement();
        forgingLegend = (new Achievement(getNextAchievementID(),"ForgingLegend",-5,13,Items.ChestplateAncientmetalsacred,AchievementExtend.copying)).registerAchievement();
        invincible = (new Achievement(getNextAchievementID(),"Invincible",-3,13,new ItemStack(Item.potion, 1, 8271),AchievementList.potion)).registerAchievement().setSpecial();
    }

    private static int getNextAchievementID() {
        return Constant.getNextAchievementID();
    }
}
