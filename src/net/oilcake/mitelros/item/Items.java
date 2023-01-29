package net.oilcake.mitelros.item;

import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.util.RecipeRegister;
import net.oilcake.mitelros.util.ReflectHelper;
import net.minecraft.*;


public class Items extends Item {
    public static final ItemArmor nickelHelmet = new ItemHelmet(Constant.getNextItemID(),Materials.nickel,false);
    public static final ItemArmor nickelChestplate = new ItemCuirass(Constant.getNextItemID(),Materials.nickel,false);
    public static final ItemArmor nickelLeggings = new ItemLeggings(Constant.getNextItemID(),Materials.nickel,false);
    public static final ItemArmor nickelBoots = new ItemBoots(Constant.getNextItemID(),Materials.nickel,false);
    public static final ItemArmor nickelHelmetChain = new ItemHelmet(Constant.getNextItemID(),Materials.nickel,true);
    public static final ItemArmor nickelChestplateChain = new ItemCuirass(Constant.getNextItemID(),Materials.nickel,true);
    public static final ItemArmor nickelLeggingsChain = new ItemLeggings(Constant.getNextItemID(),Materials.nickel,true);
    public static final ItemArmor nickelBootsChain = new ItemBoots(Constant.getNextItemID(),Materials.nickel,true);
    public static final ItemNugget nickelNugget = ReflectHelper.createInstance(ItemNugget.class, new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final ItemAxe nickelAxe = ReflectHelper.createInstance(ItemAxe.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final ItemBattleAxe nickelBattleAxe = ReflectHelper.createInstance(ItemBattleAxe.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final ItemDagger nickelDagger = ReflectHelper.createInstance(ItemDagger.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final Item nickelIngot = ReflectHelper.createInstance(ItemIngot.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel).setXPReward(30);
    public static final ItemPickaxe nickelPickaxe = ReflectHelper.createInstance(ItemPickaxe.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final ItemShovel nickelShovel = ReflectHelper.createInstance(ItemShovel.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final ItemSword nickelSword = ReflectHelper.createInstance(ItemSword.class,new Class[]{int.class,Material.class},Constant.getNextItemID(), Materials.nickel);
    public static final ItemWarHammer nickelWarHammer = ReflectHelper.createInstance(ItemWarHammer.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final ItemHatchet nickelHatchet = ReflectHelper.createInstance(ItemHatchet.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final ItemHoe nickelHoe = ReflectHelper.createInstance(ItemHoe.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final ItemKnife nickelKnife = ReflectHelper.createInstance(ItemKnife.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final ItemMattock nickelMattock = ReflectHelper.createInstance(ItemMattock.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final ItemScythe nickelScythe = ReflectHelper.createInstance(ItemScythe.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final ItemShears nickelShears = ReflectHelper.createInstance(ItemShears.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final Item doorNickel= new ItemDoor(Constant.getNextItemID(), Materials.nickel);
    public static final ItemChain nickelChain = ReflectHelper.createInstance(ItemChain.class,new Class[]{int.class,Material.class},Constant.getNextItemID(),Materials.nickel);
    public static final ItemCoin nickelCoin = ReflectHelper.createInstance(ItemCoin.class, new Class[]{int.class, Material.class}, Constant.getNextItemID(), Materials.nickel);
    public static final ItemArrow arrowNickel = new ItemArrow(Constant.getNextItemID(), Materials.nickel);





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



        Constant.initItemArray();
    }

    public static void registerRecipes(RecipeRegister register) {
        register.registerShapedRecipe(new ItemStack(arrowNickel), false,
                " C ",
                " B ",
                " A ",
                'A', Item.feather,
                'B', Item.stick,
                'C', nickelNugget);
        register.registerShapedRecipe(new ItemStack(nickelChain), false,
                " A ",
                "A A",
                " A ",
                'A', nickelNugget);
        register.registerShapedRecipe(new ItemStack(nickelShears), false,
                " A ",
                "  A",
                "   ",
                'A', nickelIngot);
//        register.registerShapedRecipe(new ItemStack(nickelShears), false,
//                "  ",
//                "  A",
//                " A ",
//                'A', nickelIngot);
        register.registerShapedRecipe(new ItemStack(nickelScythe), false,
                "BA ",
                "B A",
                "B  ",
                'A', nickelIngot,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(nickelMattock), false,
                "AAA",
                " BA",
                " B ",
                'A', nickelIngot,
                'B', Item.stick);
//        register.registerShapedRecipe(new ItemStack(nickelKnife), false,
//                "AA",
//                " B ",
//                " B ",
//                'A', nickelIngot,
//                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(nickelHoe), false,
                "AA ",
                " B ",
                " B ",
                'A', nickelIngot,
                'B', Item.stick);
//        register.registerShapedRecipe(new ItemStack(nickelHoe), false,
//                " AA",
//                " B ",
//                " B ",
//                'A', nickelIngot,
//                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(nickelHatchet), false,
                "   ",
                " BA",
                " B ",
                'A', nickelIngot,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(nickelWarHammer), false,
                "AAA",
                "ABA",
                " B ",
                'A', nickelIngot,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(nickelSword), false,
                " A ",
                " A ",
                " B ",
                'A', nickelIngot,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(nickelShovel), false,
                " A ",
                " B ",
                " B ",
                'A', nickelIngot,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(nickelPickaxe), false,
                "AAA",
                " B ",
                " B ",
                'A', nickelIngot,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(nickelDagger), false,
                "   ",
                " A ",
                " B ",
                'A', nickelIngot,
                'B', Item.stick);
//        register.registerShapedRecipe(new ItemStack(nickelDagger), false,
//                "   ",
//                " A ",
//                " B ",
//                'A', nickelIngot,
//                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(nickelBattleAxe), false,
                "A A",
                "ABA",
                " B ",
                'A', nickelIngot,
                'B', Item.stick);
        register.registerShapedRecipe(new ItemStack(nickelAxe), false,
                "AA ",
                "AB ",
                " B ",
                'A', nickelIngot,
                'B', Item.stick);
//        register.registerShapedRecipe(new ItemStack(nickelAxe), false,
//                " AA",
//                " BA",
//                " B ",
//                'A', nickelIngot,
//                'B', Item.stick);
        register.registerShapelessRecipe(new ItemStack(nickelIngot, 9), false,
                Blocks.blockNickel);
        register.registerShapelessRecipe(new ItemStack(nickelNugget, 9), false,
                nickelIngot);
//        register.registerShapedRecipe(new ItemStack(nickelBootsChain), false,
//                "A A",
//                "A A",
//                "   ",
//                'A', nickelChain);
        register.registerShapedRecipe(new ItemStack(nickelBootsChain), false,
                "   ",
                "A A",
                "A A",
                'A', nickelChain);
        register.registerShapedRecipe(new ItemStack(nickelLeggingsChain), false,
                "AAA",
                "A A",
                "A A",
                'A', nickelChain);
        register.registerShapedRecipe(new ItemStack(nickelChestplateChain), false,
                "A A",
                "AAA",
                "AAA",
                'A', nickelChain);
        register.registerShapedRecipe(new ItemStack(nickelHelmetChain), false,
                "AAA",
                "A A",
                "   ",
                'A', nickelChain);
//        register.registerShapedRecipe(new ItemStack(nickelHelmetChain), false,
//                "   ",
//                "AAA",
//                "A A",
//                'A', nickelChain);
//        register.registerShapedRecipe(new ItemStack(nickelBoots), false,
//                "A A",
//                "A A",
//                "   ",
//                'A', nickelIngot);
        register.registerShapedRecipe(new ItemStack(nickelBoots), false,
                "   ",
                "A A",
                "A A",
                'A', nickelIngot);
        register.registerShapedRecipe(new ItemStack(nickelLeggings), false,
                "AAA",
                "A A",
                "A A",
                'A', nickelIngot);
        register.registerShapedRecipe(new ItemStack(nickelChestplate), false,
                "A A",
                "AAA",
                "AAA",
                'A', nickelIngot);
//        register.registerShapedRecipe(new ItemStack(nickelHelmet), false,
//                "AAA",
//                "A A",
//                "   ",
//                'A', nickelIngot);
        register.registerShapedRecipe(new ItemStack(nickelHelmet), false,
                "   ",
                "AAA",
                "A A",
                'A', nickelIngot);
        register.registerShapedRecipe(new ItemStack(doorNickel), false,
                " AA",
                " AA",
                " AA",
                'A', nickelIngot);


        ItemCoin[] coins = new ItemCoin[]{Item.coinCopper, Item.coinSilver, Item.coinGold, Item.coinAncientMetal, Item.coinMithril, Item.coinAdamantium, nickelCoin};
        for (ItemCoin coin : coins) {
            for (int plank_subtype = 1; plank_subtype <= 9; ++plank_subtype) {
                register.registerShapelessRecipe(new ItemStack(coin.getNuggetPeer(), plank_subtype), true, new Object[]{new ItemStack(coin, plank_subtype)});
            }
            register.registerShapelessRecipe(new ItemStack(coin), true, new ItemStack(coin.getNuggetPeer()));
        }

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
