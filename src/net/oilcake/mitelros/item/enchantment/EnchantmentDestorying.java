package net.oilcake.mitelros.item.enchantment;

import net.minecraft.*;

import static net.oilcake.mitelros.item.enchantment.Enchantments.enchantmentAbsorb;
import static net.oilcake.mitelros.item.enchantment.Enchantments.enchantmentVanishing;

public class EnchantmentDestorying extends Enchantment {
    protected EnchantmentDestorying(int id, yq rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @Override
    public int getNumLevels() {
        return 3;
    }
    @Override
    public String getNameSuffix() {
        return "destorying";
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
