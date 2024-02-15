package net.oilcake.mitelros.item.enchantment;

import net.minecraft.CreativeModeTab;
import net.minecraft.Enchantment;
import net.minecraft.Item;
import net.minecraft.yq;
import net.oilcake.mitelros.item.ItemMorningStar;

public class EnchantmentThresher extends Enchantment {
    protected EnchantmentThresher(int id, yq rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @Override
    public int getNumLevels() {
        return 5;
    }
    @Override
    public String getNameSuffix() {
        return "thresher";
    }

    @Override
    public boolean canEnchantItem(Item item) {
        return item instanceof ItemMorningStar;
    }

    @Override
    public boolean isOnCreativeTab(CreativeModeTab creativeModeTab) {
        return creativeModeTab == CreativeModeTab.tabCombat;
    }
}
