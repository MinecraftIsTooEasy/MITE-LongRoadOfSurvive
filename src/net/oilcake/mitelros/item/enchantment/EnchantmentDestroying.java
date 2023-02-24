package net.oilcake.mitelros.item.enchantment;

import net.minecraft.*;

import static net.oilcake.mitelros.item.enchantment.Enchantments.enchantmentAbsorb;
import static net.oilcake.mitelros.item.enchantment.Enchantments.enchantmentVanishing;

public class EnchantmentDestroying extends Enchantment {
    protected EnchantmentDestroying(int id, yq rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @Override
    public int getNumLevels() {
        return 1;
    }
    @Override
    public String getNameSuffix() {
        return "destroying";
    }

    @Override
    public boolean canEnchantItem(Item item) {
        return item instanceof ItemWarHammer;
    }

    @Override
    public boolean isOnCreativeTab(CreativeModeTab creativeModeTab) {
        return creativeModeTab == CreativeModeTab.tabCombat;
    }
}
