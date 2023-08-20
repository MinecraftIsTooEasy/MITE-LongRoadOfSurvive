//package net.oilcake.mitelros.mixins.block;
//
//import net.minecraft.*;
//import net.oilcake.mitelros.item.Items;
//import net.oilcake.mitelros.item.Materials;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Overwrite;
//import org.spongepowered.asm.mixin.Shadow;
//
//import java.util.Random;
//
//@Mixin(BlockAnvil.class)
//public class BlockAnvilMixin extends BlockFalling{
//    @Shadow
//    public Material metal_type;
//    @Shadow
//    public static final String[] statuses = new String[]{"intact", "slightlyDamaged", "veryDamaged"};
//
//    public BlockAnvilMixin(int par1, Material material, BlockConstants constants) {
//        super(par1, material, constants);
//    }
//    protected void dropXpOnBlockBreak(World par1World, int par2, int par3, int par4, int par5) {
//    }
//    @Overwrite
//    public int dropBlockAsEntityItem(BlockBreakInfo info) {
//        Random random = new Random();
//        if(statuses[0].equals("intact")){
//            return this.dropBlockAsEntityItem(info, this.getIngotID(), 0, random.nextInt(3) + 13, 1F);
//        }else if (statuses[1].equals("slightlyDamaged")){
//            return this.dropBlockAsEntityItem(info, this.getIngotID(), 0, random.nextInt(2) + 6, 1F);
//        }else if (statuses[2].equals("veryDamaged")){
//            return this.dropBlockAsEntityItem(info, this.getIngotID(), 0, random.nextInt(2), 1F);
//        }else{
//            return 0;
//        }
//    }
//    public int getIngotID() {
//        if(this.metal_type == Material.copper){
//            return Item.ingotCopper.itemID;
//        } else if (this.metal_type == Material.silver) {
//            return Item.ingotSilver.itemID;
//        }else if (this.metal_type == Material.gold) {
//            return Item.ingotGold.itemID;
//        }else if (this.metal_type == Material.iron) {
//            return Item.ingotIron.itemID;
//        }else if (this.metal_type == Material.ancient_metal) {
//            return Item.ingotAncientMetal.itemID;
//        }else if (this.metal_type == Material.mithril) {
//            return Item.ingotMithril.itemID;
//        }else if (this.metal_type == Material.adamantium) {
//            return Item.ingotAdamantium.itemID;
//        }else if (this.metal_type == Materials.nickel) {
//            return Items.nickelIngot.itemID;
//        }else if (this.metal_type == Materials.tungsten) {
//            return Items.tungstenIngot.itemID;
//        }else{
//            return 0;
//        }
//    }
//    public int getNuggetID() {
//        if(this.metal_type == Material.copper){
//            return Item.copperNugget.itemID;
//        } else if (this.metal_type == Material.silver) {
//            return Item.silverNugget.itemID;
//        }else if (this.metal_type == Material.gold) {
//            return Item.goldNugget.itemID;
//        }else if (this.metal_type == Material.iron) {
//            return Item.ironNugget.itemID;
//        }else if (this.metal_type == Material.ancient_metal) {
//            return Item.ancientMetalNugget.itemID;
//        }else if (this.metal_type == Material.mithril) {
//            return Item.mithrilNugget.itemID;
//        }else if (this.metal_type == Material.adamantium) {
//            return Item.adamantiumNugget.itemID;
//        }else if (this.metal_type == Materials.nickel) {
//            return Items.nickelNugget.itemID;
//        }else if (this.metal_type == Materials.tungsten) {
//            return Items.tungstenNugget.itemID;
//        }else{
//            return 0;
//        }
//    }
//}
