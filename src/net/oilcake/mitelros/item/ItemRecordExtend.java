package net.oilcake.mitelros.item;

import net.minecraft.Item;
import net.minecraft.ItemRecord;
import net.minecraft.ItemStack;
import org.spongepowered.asm.mixin.Overwrite;

public class ItemRecordExtend extends ItemRecord {

    protected ItemRecordExtend(int par1, String par2Str, String texture, String title, String composer) {
        super(par1, par2Str, texture, title, composer);
    }

}
