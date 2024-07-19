package ink.huix.iinjected

import net.minecraft.Debug
import net.oilcake.mitelros.mixins.world.WorldMixin
import org.spongepowered.asm.mixin.Unique
import kotlin.math.sin

interface WorldKt {
    fun getWorldSeason(): Int {
        return 0
    }

    fun getSeasonType(day: Int): Int {
        return 0
    }

    fun getRainDurationModify(Season: Int): Float {
        return 0f
    }

    fun getSeasonGrowthModifier(): Float {
        return 0f
    }
}