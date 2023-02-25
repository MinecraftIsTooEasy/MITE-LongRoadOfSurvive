package net.oilcake.mitelros.util;

import net.minecraft.DamageSource;

public class DamageSourceExtend extends DamageSource{
    private boolean is_unblockable;
    private boolean bypasses_mundane_armor;
    private boolean is_absolute;
    private float hungerDamage = 0.3F;
    public String damageType;
    protected DamageSourceExtend(String par1Str) {
        super(par1Str);
        this.damageType = par1Str;
    }
    public static DamageSourceExtend freeze = (new DamageSourceExtend("freeze")).setUnblockable();
    public static DamageSourceExtend thirsty = (new DamageSourceExtend("thirsty")).setUnblockable();
    protected DamageSourceExtend setUnblockable() {
        this.is_unblockable = true;
        return this.setDamageBypassesMundaneArmor();
    }
    protected DamageSourceExtend setDamageBypassesMundaneArmor() {
        this.bypasses_mundane_armor = true;
        this.hungerDamage = 0.0F;
        return this;
    }
    public boolean bypassesMundaneArmor() {
        return this.bypasses_mundane_armor || this.is_unblockable || this.is_absolute;
    }
}
