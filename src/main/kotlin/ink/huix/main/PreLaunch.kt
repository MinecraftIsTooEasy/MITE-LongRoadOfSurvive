package ink.huix.main

import ink.huix.EnumExtends
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint
import net.minecraft.EnumQuality

class PreLaunch : PreLaunchEntrypoint {
    override fun onPreLaunch() {
        EnumExtends.ENCHANTMENT_RARITY.addEnum("ULTIMATE", 13, "Ultimate", 0)

        net.xiaoyu233.fml.util.EnumExtends.EQUIPMENT_MATERIAL.addEnum(
            "NICKEL") {
            arrayOf<Any>(
                8.0F,
                30,
                EnumQuality.masterwork,
                "nickel"
            )
        }

        net.xiaoyu233.fml.util.EnumExtends.EQUIPMENT_MATERIAL.addEnum(
            "TUNGSTEN") {
            arrayOf<Any>(
                128.0F,
                50,
                EnumQuality.legendary,
                "tungsten"
            )
        }

        net.xiaoyu233.fml.util.EnumExtends.EQUIPMENT_MATERIAL.addEnum(
            "URU") {
            arrayOf<Any>(
                192.0F,
                100,
                EnumQuality.legendary,
                "uru"
            )
        }

        net.xiaoyu233.fml.util.EnumExtends.EQUIPMENT_MATERIAL.addEnum(
            "WOLF_FUR") {
            arrayOf<Any>(
                4.0F,
                20,
                EnumQuality.excellent,
                "wolf_fur"
            )
        }

        net.xiaoyu233.fml.util.EnumExtends.EQUIPMENT_MATERIAL.addEnum(
            "CUSTOM_A") {
            arrayOf<Any>(
                0.0625f,
                0,
                EnumQuality.average,
                "custom_a"
            )
        }

        net.xiaoyu233.fml.util.EnumExtends.EQUIPMENT_MATERIAL.addEnum(
            "CUSTOM_B") {
            arrayOf<Any>(
                0.0625f,
                0,
                EnumQuality.average,
                "custom_b"
            )
        }

        net.xiaoyu233.fml.util.EnumExtends.EQUIPMENT_MATERIAL.addEnum(
            "VIBRANIUM") {
            arrayOf<Any>(
                4.0F,
                0,
                EnumQuality.poor,
                "vibranium"
            )
        }

        net.xiaoyu233.fml.util.EnumExtends.EQUIPMENT_MATERIAL.addEnum(
            "MAGICAL") {
            arrayOf<Any>(
                0.125f,
                100,
                EnumQuality.wretched,
                "magical"
            )
        }

        net.xiaoyu233.fml.util.EnumExtends.EQUIPMENT_MATERIAL.addEnum(
            "ANCIENT_METAL_SACRED") {
            arrayOf<Any>(
                16.0F,
                60,
                EnumQuality.masterwork,
                "ancient_metal_sacred"
            )
        }

    }
}