package net.oilcake.mitelros.item;

import net.minecraft.*;

import java.util.List;

public class ItemGoldenAppleLegend extends ItemFood{
    public ItemGoldenAppleLegend(int id, int satiation, int nutrition, String texture) {
        super(id, Material.fruit, satiation, nutrition, 1000, false, false, true, texture);
        this.addMaterial(Material.gold);
        this.setPlantProduct();
        this.setWater(-8);
    }
    public boolean e(ItemStack par1ItemStack) {
        return par1ItemStack.getItemSubtype() > 0;
    }

    public yq f(ItemStack par1ItemStack) {
        return par1ItemStack.getItemSubtype() == 0 ? yq.c : yq.b;
    }

    public void a(int par1, CreativeModeTab par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
    }

    public String getUnlocalizedName(ItemStack item_stack) {
        return isEnchantedGoldenApple(item_stack) ? "item.appleGold.enchanted" : super.getUnlocalizedName(item_stack);
    }

    public static boolean isGoldenApple(ItemStack item_stack) {
        return item_stack != null && item_stack.itemID == Items.Goldenapplelegend.itemID;
    }

    public static boolean isUnenchantedGoldenApple(ItemStack item_stack) {
        return isGoldenApple(item_stack) && item_stack.getItemSubtype() == 0;
    }

    public static boolean isEnchantedGoldenApple(ItemStack item_stack) {
        return isGoldenApple(item_stack) && item_stack.getItemSubtype() > 0;
    }
    protected void onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par1ItemStack.getItemSubtype() == 0 && !par2World.isRemote) {
            par3EntityPlayer.addPotionEffect(new MobEffect(MobEffectList.regeneration.id, 600, 4));
        }

        if (par1ItemStack.getItemSubtype() > 0) {
            if (!par2World.isRemote) {
                par3EntityPlayer.addPotionEffect(new MobEffect(MobEffectList.regeneration.id, 600, 4));
                par3EntityPlayer.addPotionEffect(new MobEffect(MobEffectList.resistance.id, 6000, 1));
                par3EntityPlayer.addPotionEffect(new MobEffect(MobEffectList.fireResistance.id, 6000, 1));
            }
        } else {
            super.onEaten(par1ItemStack, par2World, par3EntityPlayer);
        }

    }
    public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
        super.onItemUseFinish(item_stack, world, player);
    }
}
