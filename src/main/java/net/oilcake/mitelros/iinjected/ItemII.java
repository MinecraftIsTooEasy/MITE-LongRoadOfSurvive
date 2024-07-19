package net.oilcake.mitelros.iinjected;

import net.minecraft.Item;

public interface ItemII {
     default int getWater() {
        return 0;
    }

    default Item setWater(int water) {
        return null;
    }
}
