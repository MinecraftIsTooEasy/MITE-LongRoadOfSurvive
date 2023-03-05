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


    public AchievementExtend() {
    }

    public static void registerAchievements() {
        FragofMine = (new Achievement(getNextAchievementID(), "MinePieces", 2, 3, Items.pieceCopper, AchievementList.buildPickaxe)).registerAchievement();
        BravetheCold = (new Achievement(getNextAchievementID(), "WearAllWolfArmor", 11, -3, Items.WolfHelmet, AchievementList.wearLeather)).registerAchievement();
        Arbalistic = (new Achievement(getNextAchievementID(), "BuildMithrilBow", -4, 9, Item.bowMithril, AchievementList.mithrilIngot)).registerAchievement();
        getWitherSkull = (new Achievement(getNextAchievementID(), "getWitherSkull", 4, 11, new ItemStack(Block.skull, 1, 1), AchievementList.portalToNether)).registerAchievement();
        getBeacon = (new Achievement(getNextAchievementID(), "getBeacon", 6, 11, Block.beacon, AchievementExtend.getWitherSkull)).setSpecial().registerAchievement();
        mashedCactus = (new Achievement(getNextAchievementID(), "mashedCactus", -3, -3, Items.mashedCactus, AchievementList.seeds)).registerAchievement();
        hypothermia = (new Achievement(getNextAchievementID(),"Hypothermia",-4,2, Block.ice,AchievementList.openInventory)).registerAchievement();
        feast = (new Achievement(getNextAchievementID(),"Feast",1,-3, Item.bowlBeefStew,AchievementList.fineDining)).setSpecial().registerAchievement();

    }

    private static int getNextAchievementID() {
        return Constant.getNextAchievementID();
    }
}
