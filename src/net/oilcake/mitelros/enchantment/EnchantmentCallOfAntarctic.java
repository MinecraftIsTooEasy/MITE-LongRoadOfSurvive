package net.oilcake.mitelros.enchantment;

import net.minecraft.*;

public class EnchantmentCallOfAntarctic extends Enchantment {
    protected EnchantmentCallOfAntarctic(int id, yq rarity, int difficulty) {
        super(id, rarity, difficulty);
    }

    @Override
    public int getNumLevels() {
        return 1;
    }
    @Override
    public String getNameSuffix() {
        return "callofantarctic";
    }

    @Override
    public boolean canEnchantItem(Item item) {
        return item instanceof ItemCuirass;
    }

    @Override
    public boolean isOnCreativeTab(CreativeModeTab creativeModeTab) {
        return creativeModeTab == CreativeModeTab.tabTools;
    }
}
