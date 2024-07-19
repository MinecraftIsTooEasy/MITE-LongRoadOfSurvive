package ink.huix.iinjected

import net.oilcake.mitelros.block.enchantreserver.EnchantReserverSlots

interface EntityPlayerKt {

    fun addWater(water: Int): Int {
        return water
    }
    fun getWater(): Int {
        return 0
    }

    fun getBodyTemperature(): Float {
        return 0f
    }

    fun setBodyTemperature(value: Float) {

    }

    fun getTemperatureTolerance(): Array<String> {
        return arrayOf()
    }

    fun getEnvironmentTemperature(base_temperature: Float, daytime: Long): String {
        return ""
    }

    fun displayGUIEnchantReserver(x: Int, y: Int, z: Int, slots: EnchantReserverSlots) {

    }

}