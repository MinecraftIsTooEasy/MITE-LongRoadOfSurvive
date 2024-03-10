package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collections;
import java.util.Random;

@Mixin(EntityVillager.class)
public class EntityVillagerMixin extends EntityAgeable implements IMerchant, NPC {
    private MerchantRecipeList buyingList;
    private float field_82191_bN;

    public EntityVillagerMixin(World par1World) {
        super(par1World);
    }
    @Shadow
    public void setProfession(int par1) {

    }
    @Overwrite
    public GroupDataEntity onSpawnWithEgg(GroupDataEntity par1EntityLivingData) {
        par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
        this.setProfession(1 + this.worldObj.rand.nextInt(14));
        return par1EntityLivingData;
    }

    @Overwrite
    private void addDefaultEquipmentAndRecipies(int par1) {
        if (this.buyingList != null) {
            this.field_82191_bN = MathHelper.sqrt_float((float)this.buyingList.size()) * 0.2F;
        } else {
            this.field_82191_bN = 0.0F;
        }

        MerchantRecipeList var2;
        var2 = new MerchantRecipeList();
        int var6;
        label51:
        switch (this.getProfession()) {
            //农民
            case 1:
                if (this.rand.nextFloat() < this.adjustProbability(0.25F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.wheat, 14 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.25F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.carrot, 14 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.25F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.potato, 14 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.25F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.onion, 14 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.4F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.chickenRaw, 10 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.chickenCooked.itemID, 5 + this.rand.nextInt(3), 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.9F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.bread.itemID, 6 + this.rand.nextInt(3), 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.melon.itemID, 4 + this.rand.nextInt(2), 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.pumpkin.blockID, 1 + this.rand.nextInt(2), 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.15F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 4), new ItemStack(Item.appleGold.itemID, 2 + this.rand.nextInt(2), 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.15F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 4), new ItemStack(Item.goldenCarrot.itemID, 2 + this.rand.nextInt(2), 0)));
                }
                break;
            //牧羊人
            case 2:
                if (this.rand.nextFloat() < this.adjustProbability(0.5F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Block.cloth.blockID, 7 + rand.nextInt(2)), new ItemStack(Item.emerald.itemID, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 3), new ItemStack(Item.shears, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 2), new ItemStack(Item.shearsCopper, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 1)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 2)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 3)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 4)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 5)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 6)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 7)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 8)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 9)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 10)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 11)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 12)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 13)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 14)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Block.cloth.blockID, 3 + this.rand.nextInt(2), 15)));
                }
                break;
            //制箭师
            case 3:
                if (this.rand.nextFloat() < this.adjustProbability(0.5F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Block.gravel, 4), new ItemStack(Item.emerald), new ItemStack(Item.flint.itemID, 4 + this.rand.nextInt(2), 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.5F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.chipFlint, 4), new ItemStack(Item.emerald), new ItemStack(Item.arrowFlint.itemID, 4, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.5F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.copperNugget, 4), new ItemStack(Item.emerald), new ItemStack(Item.arrowCopper.itemID, 4, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.5F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.ironNugget, 4), new ItemStack(Item.emerald), new ItemStack(Item.arrowIron.itemID, 4, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.5F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.silverNugget, 4), new ItemStack(Item.emerald), new ItemStack(Item.arrowSilver.itemID, 4, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 2), new ItemStack(Item.bow, 1, 15)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.5F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.feather, 12 + rand.nextInt(4)), new ItemStack(Item.emerald.itemID, 1, 0)));
                }
                break;
            //烘焙师
            case 4:
                if (this.rand.nextFloat() < this.adjustProbability(0.15F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 3), new ItemStack(Item.pumpkinPie.itemID, 2 + this.rand.nextInt(2), 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.15F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 3), new ItemStack(Items.lemonPie.itemID, 2 + this.rand.nextInt(2), 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.9F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.cookie.itemID, 6 + this.rand.nextInt(3), 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.9F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.chocolate.itemID, 6 + this.rand.nextInt(3), 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.5F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.dough, 4 + rand.nextInt(2)), new ItemStack(Item.emerald.itemID, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.bowlSorbet, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.bowlIceCream, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Items.bowlLemonade, 2, 0)));
                }
                break;
            //大厨
            case 5:
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 3), new ItemStack(Items.ignitionIron, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 2), new ItemStack(Items.ignitionCopper, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 2), new ItemStack(Item.bowlMashedPotato, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.bowlCereal, 2, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 2), new ItemStack(Item.bowlVegetableSoup, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.2F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 3), new ItemStack(Item.bowlChickenSoup, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.2F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 3), new ItemStack(Items.bowlPorkchopStew, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.2F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.bowlEmpty, 16), new ItemStack(Item.shardEmerald, 1, 0)));
                }
                break;
            //渔夫
            case 6:
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 3), new ItemStack(Item.fishingRodIron, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 2), new ItemStack(Items.fishingRodCopper, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.2F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.wormRaw, 14 + rand.nextInt(2)), new ItemStack(Item.emerald, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.5F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.fishRaw, 14 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.5F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.fishCooked.itemID, 7 + rand.nextInt(3), 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.fishLargeRaw, 12 + rand.nextInt(2)), new ItemStack(Item.emerald.itemID, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Item.fishLargeCooked.itemID, 7 + rand.nextInt(3), 0)));
                }
                break;
            //抄录员
            case 7:
                if (this.rand.nextFloat() < this.adjustProbability(0.07F)) {
                    Enchantment var8 = Enchantment.enchantmentsBookList[this.rand.nextInt(Enchantment.enchantmentsBookList.length)];
                    int var10 = MathHelper.getRandomIntegerInRange(this.rand, 1, var8.getNumLevels());
                    ItemStack var11 = Item.enchantedBook.getEnchantedItemStack(new EnchantmentInstance(var8, var10));
                    var6 = 2 + this.rand.nextInt(5 + var10 * 2) + 3 * var10;
                    var2.add(new MerchantRecipe(new ItemStack(Item.book), new ItemStack(Item.emerald, var6), var11));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.8F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.paper, 24 + rand.nextInt(10)), new ItemStack(Item.emerald.itemID, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.8F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.book, 6 + rand.nextInt(4)), new ItemStack(Item.emerald.itemID, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.8F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.feather, 12 + rand.nextInt(4)), new ItemStack(Item.emerald.itemID, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.2F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 3), new ItemStack(Block.bookShelf, 1, 0)));
                }
                break;
            //学者
            case 8:
                if (this.rand.nextFloat() < this.adjustProbability(0.2F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 3), new ItemStack(Item.compass, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.2F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 3), new ItemStack(Item.pocketSundial, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.2F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 4), new ItemStack(Block.glass, 3, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.8F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.writtenBook, 1), new ItemStack(Item.emerald.itemID, 1, 0)));
                }
                break;
            //祭司
            case 9:
                if (this.rand.nextFloat() < this.adjustProbability(0.5F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald.itemID, 1), new ItemStack(Item.expBottle.itemID, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald.itemID, 3), new ItemStack(Item.eyeOfEnder.itemID, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.25F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.rottenFlesh, 14 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.25F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.bone, 14 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.1F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald.itemID, 7 + rand.nextInt(3)), new ItemStack(Items.totemoffecund.itemID, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.1F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald.itemID, 7 + rand.nextInt(3)), new ItemStack(Items.totemofhunting.itemID, 1, 0)));
                }
                int[] var3 = new int[]{Items.nickelSword.itemID, Items.nickelPickaxe.itemID, Item.swordIron.itemID, Item.pickaxeIron.itemID};
                int[] var4 = var3;
                int var5 = var3.length;
                var6 = 0;

                while(true) {
                    if (var6 >= var5) {
                        break label51;
                    }

                    int var7 = var4[var6];
                    if (this.rand.nextFloat() < this.adjustProbability(0.05F)) {
                        var2.add(new MerchantRecipe(new ItemStack(var7, 1, 0), new ItemStack(Item.emerald, 1 + this.rand.nextInt(2), 0), EnchantmentManager.addRandomEnchantment(this.rand, new ItemStack(var7, 1, 0), 5 + this.rand.nextInt(15))));
                    }

                    ++var6;
                }
            //炼药师
            case 10:
                if (this.rand.nextFloat() < this.adjustProbability(0.25F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.netherStalkSeeds, 14 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.4F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.redstone, 5 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.glowstone, 5 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.3F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 2 + rand.nextInt(3)), new ItemStack(Item.potato.itemID, 1, 32696)));
                }
                break;
            //工具匠
            case 11:
                addMerchantItem(var2, Item.coal.itemID, this.rand, this.adjustProbability(0.7F));
                addMerchantItem(var2, Item.ingotIron.itemID, this.rand, this.adjustProbability(0.5F));
                addMerchantItem(var2, Item.ingotGold.itemID, this.rand, this.adjustProbability(0.5F));
                addBlacksmithItem(var2, Item.swordIron.itemID, this.rand, this.adjustProbability(0.5F));
                addBlacksmithItem(var2, Item.axeIron.itemID, this.rand, this.adjustProbability(0.3F));
                addBlacksmithItem(var2, Item.pickaxeIron.itemID, this.rand, this.adjustProbability(0.5F));
                addBlacksmithItem(var2, Item.shovelIron.itemID, this.rand, this.adjustProbability(0.2F));
                addBlacksmithItem(var2, Item.hoeIron.itemID, this.rand, this.adjustProbability(0.2F));
                addBlacksmithItem(var2, Item.pickaxeCopper.itemID, this.rand, this.adjustProbability(0.5F));
                addBlacksmithItem(var2, Item.shovelCopper.itemID, this.rand, this.adjustProbability(0.2F));
                addBlacksmithItem(var2, Item.axeCopper.itemID, this.rand, this.adjustProbability(0.3F));
                addBlacksmithItem(var2, Item.hoeCopper.itemID, this.rand, this.adjustProbability(0.2F));
                addBlacksmithItem(var2, Item.daggerCopper.itemID, this.rand, this.adjustProbability(0.5F));
                addBlacksmithItem(var2, Item.swordCopper.itemID, this.rand, this.adjustProbability(0.5F));
                addBlacksmithItem(var2, Item.daggerIron.itemID, this.rand, this.adjustProbability(0.5F));
                break;
            //盔甲匠
            case 12:
                addMerchantItem(var2, Item.coal.itemID, this.rand, this.adjustProbability(0.7F));
                addMerchantItem(var2, Item.ingotIron.itemID, this.rand, this.adjustProbability(0.5F));
                addMerchantItem(var2, Item.ingotGold.itemID, this.rand, this.adjustProbability(0.5F));
                addBlacksmithItem(var2, Item.helmetCopper.itemID, this.rand, this.adjustProbability(0.2F));
                addBlacksmithItem(var2, Item.plateCopper.itemID, this.rand, this.adjustProbability(0.2F));
                addBlacksmithItem(var2, Item.legsCopper.itemID, this.rand, this.adjustProbability(0.2F));
                addBlacksmithItem(var2, Item.bootsCopper.itemID, this.rand, this.adjustProbability(0.2F));
                addBlacksmithItem(var2, Item.helmetChainCopper.itemID, this.rand, this.adjustProbability(0.1F));
                addBlacksmithItem(var2, Item.plateChainCopper.itemID, this.rand, this.adjustProbability(0.1F));
                addBlacksmithItem(var2, Item.legsChainCopper.itemID, this.rand, this.adjustProbability(0.1F));
                addBlacksmithItem(var2, Item.bootsChainCopper.itemID, this.rand, this.adjustProbability(0.1F));
                addBlacksmithItem(var2, Item.helmetChainIron.itemID, this.rand, this.adjustProbability(0.1F));
                addBlacksmithItem(var2, Item.plateChainIron.itemID, this.rand, this.adjustProbability(0.1F));
                addBlacksmithItem(var2, Item.legsChainIron.itemID, this.rand, this.adjustProbability(0.1F));
                addBlacksmithItem(var2, Item.bootsChainIron.itemID, this.rand, this.adjustProbability(0.1F));
                addBlacksmithItem(var2, Item.helmetIron.itemID, this.rand, this.adjustProbability(0.2F));
                addBlacksmithItem(var2, Item.plateIron.itemID, this.rand, this.adjustProbability(0.2F));
                addBlacksmithItem(var2, Item.legsIron.itemID, this.rand, this.adjustProbability(0.2F));
                addBlacksmithItem(var2, Item.bootsIron.itemID, this.rand, this.adjustProbability(0.2F));
                break;
            //屠夫
            case 13:
                addMerchantItem(var2, Item.coal.itemID, this.rand, this.adjustProbability(0.7F));
                addMerchantItem(var2, Item.porkRaw.itemID, this.rand, this.adjustProbability(0.5F));
                addMerchantItem(var2, Item.beefRaw.itemID, this.rand, this.adjustProbability(0.5F));
                addMerchantItem(var2, Item.lambchopRaw.itemID, this.rand, this.adjustProbability(0.5F));
                addBlacksmithItem(var2, Item.porkCooked.itemID, this.rand, this.adjustProbability(0.3F));
                addBlacksmithItem(var2, Item.beefCooked.itemID, this.rand, this.adjustProbability(0.3F));
                addBlacksmithItem(var2, Item.lambchopCooked.itemID, this.rand, this.adjustProbability(0.3F));
                if (this.rand.nextFloat() < this.adjustProbability(0.5F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Items.horse_meat, 12 + rand.nextInt(2)), new ItemStack(Item.emerald.itemID, 1, 0)));
                }
                if (this.rand.nextFloat() < this.adjustProbability(0.5F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Item.emerald, 1), new ItemStack(Items.horse_meat_cooked.itemID, 5 + rand.nextInt(2), 0)));
                }
                break;
            //皮匠
            case 14:
                addBlacksmithItem(var2, Item.saddle.itemID, this.rand, this.adjustProbability(0.1F));
                addBlacksmithItem(var2, Item.plateLeather.itemID, this.rand, this.adjustProbability(0.3F));
                addBlacksmithItem(var2, Item.bootsLeather.itemID, this.rand, this.adjustProbability(0.3F));
                addBlacksmithItem(var2, Item.helmetLeather.itemID, this.rand, this.adjustProbability(0.3F));
                addBlacksmithItem(var2, Item.legsLeather.itemID, this.rand, this.adjustProbability(0.3F));
                if (this.rand.nextFloat() < this.adjustProbability(0.8F)) {
                    var2.add(new MerchantRecipe(new ItemStack(Items.leather, 14 + rand.nextInt(3)), new ItemStack(Item.emerald.itemID, 1, 0)));
                }
                break;
        }

        if (var2.isEmpty()) {
            var2.add(new MerchantRecipe(new ItemStack(Item.ingotGold, 8), new ItemStack(Items.emerald.itemID, 1, 0)));
        }

        Collections.shuffle(var2);
        if (this.buyingList == null) {
            this.buyingList = new MerchantRecipeList();
        }

        for(int var9 = 0; var9 < par1 && var9 < var2.size(); ++var9) {
            this.buyingList.addToListWithCheck((MerchantRecipe)var2.get(var9));
        }

    }
    @Shadow
    private float adjustProbability(float par1) {
        return par1;
    }
    @Shadow
    private static void addMerchantItem(MerchantRecipeList par0MerchantRecipeList, int par1, Random par2Random, float par3) {
    }
    @Shadow
    private static void addBlacksmithItem(MerchantRecipeList par0MerchantRecipeList, int par1, Random par2Random, float par3) {
    }
    @Shadow
    private int getProfession() {
        return 0;
    }

    @Shadow
    public EntityAgeable createChild(EntityAgeable entityAgeable) {
        return null;
    }

    @Shadow
    public void setCustomer(EntityPlayer entityPlayer) {

    }

    @Shadow
    public EntityPlayer getCustomer() {
        return null;
    }

    @Shadow
    public MerchantRecipeList getRecipes(EntityPlayer entityPlayer) {
        return null;
    }

    @Shadow
    public void a(MerchantRecipeList merchantRecipeList) {

    }

    @Shadow
    public void useRecipe(MerchantRecipe merchantRecipe) {

    }

    @Shadow
    public void func_110297_a_(ItemStack itemStack) {

    }
}
