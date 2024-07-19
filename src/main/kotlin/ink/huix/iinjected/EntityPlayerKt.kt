package ink.huix.iinjected

import net.minecraft.ItemStack
import net.oilcake.mitelros.block.enchantreserver.EnchantReserverSlots
import org.spongepowered.asm.mixin.Unique

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

    fun decreaseWaterServerSide(hungerWater: Float) {

    }

    fun modifyTemperature(tp: Int) {

    }

    fun getTemperature(): Int {
        return 0
    }

    fun willRepair(holding: ItemStack?): Boolean {
        return false
    }

    @Unique
    fun getHunt_counter(): Int {
        return 0
    }

    fun setHunt_counter(counter: Int) {

    }

    fun getCurrent_insulin_resistance_lvl(): Int {
        return 0
    }

    fun getFreezingCooldown(): Int {
        return 0
    }

    fun setFreezingCooldown(iptfreezingCooldown: Int) {

    }


    fun addFreezingCooldown(dummy: Int) {

    }

    fun getCurrentBiomeTemperature(): Float {
        return 0f
    }

    fun isFeast_trigger_salad(): Boolean {
        return false
    }

    fun setFeast_trigger_salad(feast_trigger_salad: Boolean) {

    }

    fun isFeast_trigger_porridge(): Boolean {
        return false
    }

    fun setFeast_trigger_porridge(feast_trigger_porridge: Boolean) {

    }

    fun isFeast_trigger_beef_stew(): Boolean {
        return false
    }

    fun setFeast_trigger_beef_stew(feast_trigger_beef_stew: Boolean) {

    }

    fun isFeast_trigger_cereal(): Boolean {
        return false
    }

    fun setFeast_trigger_cereal(feast_trigger_cereal: Boolean) {

    }

    fun isFeast_trigger_chicken_soup(): Boolean {
        return false
    }

    fun setFeast_trigger_chicken_soup(feast_trigger_chicken_soup: Boolean) {

    }

    fun isFeast_trigger_mushroom_soup(): Boolean {
        return false
    }

    fun setFeast_trigger_mushroom_soup(feast_trigger_mushroom_soup: Boolean) {
    }

    fun isFeast_trigger_cream_mushroom_soup(): Boolean {
        return false
    }

    fun setFeast_trigger_cream_mushroom_soup(feast_trigger_cream_mushroom_soup: Boolean) {

    }

    fun isFeast_trigger_vegetable_soup(): Boolean {
        return false
    }

    fun setFeast_trigger_vegetable_soup(feast_trigger_vegetable_soup: Boolean) {

    }

    fun isFeast_trigger_cream_vegetable_soup(): Boolean {
        return false
    }

    fun setFeast_trigger_cream_vegetable_soup(feast_trigger_cream_vegetable_soup: Boolean) {

    }

    fun isFeast_trigger_ice_cream(): Boolean {
        return false
    }

    fun setFeast_trigger_ice_cream(feast_trigger_ice_cream: Boolean) {

    }

    fun isFeast_trigger_chestnut_soup(): Boolean {
        return false
    }

    fun setFeast_trigger_chestnut_soup(feast_trigger_chestnut_soup: Boolean) {

    }

    fun isFeast_trigger_mashed_potatoes(): Boolean {
        return false
    }

    fun setFeast_trigger_mashed_potatoes(feast_trigger_mashed_potatoes: Boolean) {

    }

    fun isFeast_trigger_lemonade(): Boolean {
        return false
    }

    fun setFeast_trigger_lemonade(feast_trigger_lemonade: Boolean) {

    }

    fun isFeast_trigger_porkchop_stew(): Boolean {
        return false
    }

    fun setFeast_trigger_porkchop_stew(feast_trigger_porkchop_stew: Boolean) {

    }

    fun isFeast_trigger_pumpkin_soup(): Boolean {
        return false
    }

    fun setFeast_trigger_pumpkin_soup(feast_trigger_pumpkin_soup: Boolean) {

    }

    fun isFeast_trigger_sorbet(): Boolean {
        return false
    }

    fun setFeast_trigger_sorbet(feast_trigger_sorbet: Boolean) {

    }

    fun isFeast_trigger_salmon_soup(): Boolean {
        return false
    }

    fun setFeast_trigger_salmon_soup(feast_trigger_salmon_soup: Boolean) {

    }

    fun isFeast_trigger_beetroot_soup(): Boolean {
        return false
    }

    fun setFeast_trigger_beetroot_soup(feast_trigger_beetroot_soup: Boolean) {

    }

    fun displayGUIEnchantReserver(x: Int, y: Int, z: Int, slots: EnchantReserverSlots) {

    }

}