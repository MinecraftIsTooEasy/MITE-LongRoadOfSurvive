package ink.huix.iinjected

import org.spongepowered.asm.mixin.Unique

interface FoodStatsKt {
    fun addWater(water: Int): Int {
        return water
    }
    fun getWater(): Int {
        return 0
    }
    fun decreaseWaterServerSide(hungerWater: Float) {

    }


    fun getHeal_progress(): Float {
        return 0f
    }

}