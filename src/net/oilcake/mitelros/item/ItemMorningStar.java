package net.oilcake.mitelros.item;

import net.minecraft.*;

import java.util.List;

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

    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        if (extended_info) {
            info.add(EnumChatFormat.RED + Translator.getFormatted("秽土转生", new Object[0]));
        }
    }
}
