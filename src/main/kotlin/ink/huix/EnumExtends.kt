package ink.huix

import com.chocohead.mm.api.ClassTinkerers
import com.chocohead.mm.api.EnumAdder

class EnumExtends {

    companion object {
        val ENCHANTMENT_RARITY: EnumAdder = ClassTinkerers.enumBuilder(
                "net.minecraft.EnumRarity", *arrayOf<Any>(
                Integer.TYPE, String::class.java, Integer.TYPE
                )
            )

    }

}