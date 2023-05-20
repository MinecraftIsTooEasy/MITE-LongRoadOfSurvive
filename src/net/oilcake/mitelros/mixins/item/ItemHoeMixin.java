package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemHoe.class)
public class ItemHoeMixin extends ItemTool {
    protected ItemHoeMixin(int par1, Material material) {
        super(par1, material);
    }

    @Inject(method = "<init>",at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        this.addMaterialsEffectiveAgainst(new Material[]{Material.cake, Material.craftedSnow, Material.grass, Material.dirt, Material.sand, Material.snow});
        this.addBlocksEffectiveAgainst(new Block[]{Block.carrot, Block.potato, Block.onions, Blocks.beetroots});
    }

    @Shadow
    public float getBaseDamageVsEntity() {
        return 0;
    }

    @Shadow
    public float getBaseDecayRateForBreakingBlock(Block block) {
        return 0;
    }

    @Shadow
    public float getBaseDecayRateForAttackingEntity(ItemStack itemStack) {
        return 0;
    }

    @Shadow
    public String getToolType() {
        return null;
    }

    @Shadow
    public int getNumComponentsForDurability() {
        return 0;
    }
}
