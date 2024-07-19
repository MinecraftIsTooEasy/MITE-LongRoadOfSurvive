package ink.huix.iinjected

interface TileEntityFurnaceKt {

    fun isBlastFurnace(): Boolean {
        return true
    }

    fun isSmoker(): Boolean {
        return false
    }

    fun canBurnbyItself(): Boolean {
        return false
    }

    fun activateFurnace() {

    }
}