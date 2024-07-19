package ink.huix.main

import net.fabricmc.api.ModInitializer
import net.oilcake.mitelros.ITFEvent
import net.oilcake.mitelros.util.Constant
import net.oilcake.mitelros.util.ExperimentalConfig
import net.oilcake.mitelros.util.StuckTagConfig
import net.xiaoyu233.fml.reload.event.MITEEvents

class IsTooFalse : ModInitializer {

    override fun onInitialize() {
        Constant.initItemArray()

        StuckTagConfig.loadConfigs()
        ExperimentalConfig.loadConfigs()
        MITEEvents.MITE_EVENT_BUS.register(ITFEvent())
    }


}