package net.oilcake.mitelros.item;

import net.minecraft.*;
import net.oilcake.mitelros.item.potion.PotionExtend;

import java.util.List;

public class ItemWine extends Item{
    public ItemWine(int id) {
        super(id, Material.glass, "alcohol");
        this.setMaxStackSize(1);
        this.setCraftingDifficultyAsComponent(512.0F);
        this.setCreativeTab(CreativeModeTab.tabMisc);
        this.setWater(2);
    }

    public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
        if (player.onServer()) {
            player.addFreezingCooldown(-6000);
            player.Hasdrunked = true;
            player.addPotionEffect((new MobEffect(MobEffectList.confusion.id, 400, 0)));
            player.addPotionEffect((new MobEffect(PotionExtend.thirsty.id,2560,0)));
            player.addWater(this.getWater());
        }
        super.onItemUseFinish(item_stack, world, player);
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 32;
    }

    public boolean isDrinkable(int item_subtype) {
        return true;
    }

    public Item getItemProducedOnItemUseFinish() {
        return glassBottle;
    }
    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        if (extended_info) {
            info.add(EnumChatFormat.RED + Translator.getFormatted("未成年人禁止饮酒！", new Object[0]));
        }
    }
}
