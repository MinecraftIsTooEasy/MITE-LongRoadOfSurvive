package net.oilcake.mitelros.item;

import net.minecraft.ItemClub;
import net.minecraft.Material;

public class ItemMorningStar extends ItemClub {
    protected ItemMorningStar(int par1, Material material) {
        super(par1, material);
        this.setReachBonus(0.5F);
    }
    public String getToolType() {
        return "morningstar";
    }

    public float getBaseDamageVsEntity() {
        return super.getBaseDamageVsEntity();
    }

    public boolean canBlock() {
        return true;
    }

    public int getNumComponentsForDurability() {
        return 1;
    }
}
