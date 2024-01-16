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
    public static Achievement stormStriker;
    public static Achievement cheatdeath;
    public static Achievement pull;
    public static Achievement lichHunter;
    public static Achievement decimator;

    public AchievementExtend() {
    }

    public static void registerAchievements() {
        FragofMine = (new Achievement(getNextAchievementID(), "MinePieces", 2, 3, Items.pieceCopper, AchievementList.buildPickaxe)).registerAchievement();
        BravetheCold = (new Achievement(getNextAchievementID(), "WearAllWolfArmor", 11, -3, Items.WolfHelmet, AchievementList.wearLeather)).registerAchievement();
        Arbalistic = (new Achievement(getNextAchievementID(), "BuildTungstenBow", -4, 9, Items.bowTungsten, AchievementList.mithrilIngot)).registerAchievement();
        getWitherSkull = (new Achievement(getNextAchievementID(), "getWitherSkull", 4, 11, new ItemStack(Block.skull, 1, 1), AchievementList.portalToNether)).registerAchievement();
        getBeacon = (new Achievement(getNextAchievementID(), "getBeacon", 6, 11, Block.beacon, AchievementExtend.getWitherSkull)).setSpecial().registerAchievement();
        mashedCactus = (new Achievement(getNextAchievementID(), "mashedCactus", -3, -3, Items.glowberries, AchievementList.seeds)).registerAchievement();
        hypothermia = (new Achievement(getNextAchievementID(),"Hypothermia",-4,2, Block.ice,AchievementList.openInventory)).registerAchievement();
        feast = (new Achievement(getNextAchievementID(),"Feast",1,-3, Item.bowlBeefStew,AchievementList.fineDining)).setSpecial().registerAchievement();
        SoundofUnder = (new Achievement(getNextAchievementID(),"SoundofUnder",6,13,Item.recordUnderworld,AchievementList.portal)).setSpecial().registerAchievement();
        nochoice = (new Achievement(getNextAchievementID(),"NoChoice",0,11,Items.ExperimentalPotion,AchievementList.portal)).registerAchievement();
        cheersforMinecraft = (new Achievement(getNextAchievementID(),"CheersforMinecraft",-3,-5,Items.Ale,AchievementExtend.mashedCactus)).registerAchievement();
        lichHunter = new Achievement(getNextAchievementID(),"LichHunter",-2,11,Items.ShockWand,AchievementList.mithrilIngot).registerAchievement();
        copying = (new Achievement(getNextAchievementID(),"Copying",-4,11,Items.forgingnote,AchievementExtend.lichHunter)).registerAchievement();
        decimator = new Achievement(getNextAchievementID(),"Decimator",-6,12,Item.appleGold,AchievementExtend.copying).registerAchievement();
        neverEnds = (new Achievement(getNextAchievementID(),"NeverEnds",-2,17,Items.UruIngot,AchievementList.adamantiumIngot)).registerAchievement();
        forgingLegend = (new Achievement(getNextAchievementID(),"ForgingLegend",-6,10,Items.ChestplateAncientmetalsacred,AchievementExtend.copying)).registerAchievement();
        invincible = (new Achievement(getNextAchievementID(),"Invincible",-3,12,Items.Pulque,AchievementList.potion)).registerAchievement().setSpecial();
        stormStriker = (new Achievement(getNextAchievementID(),"StormStriker",9,16,new ItemStack(Block.skull, 1, 3),AchievementList.theEnd2)).registerAchievement().setSpecial();
        cheatdeath = (new Achievement(getNextAchievementID(),"DeathCheater",-3,14,Items.totemoffecund,AchievementList.potion)).registerAchievement();
        pull = (new Achievement(getNextAchievementID(),"Pull",-3,-5,Item.manure,AchievementExtend.mashedCactus)).registerAchievement();


    }

    private static int getNextAchievementID() {
        return Constant.getNextAchievementID();
    }
}
