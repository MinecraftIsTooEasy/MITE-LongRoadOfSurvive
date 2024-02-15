package net.oilcake.mitelros.mixins.render;

import net.minecraft.*;
import net.oilcake.mitelros.item.Materials;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(bhj.class)
public class RenderPlayerMixin extends bhb {
    @Shadow
    private bbj f;
    @Shadow
    private bbj g;
    @Shadow
    private bbj h;
    private bbj SpecializedRenderBody;
    private bbj SpecializedRenderLegs;
    private bbj SpecializedRenderHead;
    private bbj newRenderBody;
    private bbj newRenderLegs;
    public RenderPlayerMixin(bbo par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }
    @Inject(method = "<init>",at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        this.SpecializedRenderBody = new bbj(0.25F);
        this.SpecializedRenderLegs = new bbj(0.0125F);
        this.SpecializedRenderHead = new bbj(0.5F);
        this.newRenderBody = new bbj(0.75F);
        this.newRenderLegs = new bbj(0.375F);
    }

    @Overwrite
    protected int a(beu par1AbstractClientPlayer, int par2, float par3) {
        ItemStack var4 = par1AbstractClientPlayer.inventory.armorItemInSlot(3 - par2);
        if (var4 != null) {
            Item var5 = var4.getItem();
            if (var5 instanceof ItemArmor) {
                ItemArmor var6 = (ItemArmor)var5;
                this.a((bjo)bgu.a(var6, par2));
                bbj var7;
                if (var6.getArmorMaterial() == Materials.custom_b || var6.getArmorMaterial() == Materials.custom_a) {
                    var7 = par2 == 2 ? this.SpecializedRenderLegs : par2 == 0 ? this.SpecializedRenderHead : this.SpecializedRenderBody;
                    var7.c.j = par2 == 0;
                    var7.d.j = par2 == 0;
                    var7.e.j = par2 == 1 || par2 == 2;
                    var7.f.j = par2 == 1;
                    var7.g.j = par2 == 1;
                    var7.h.j = par2 == 2 || par2 == 3;
                    var7.i.j = par2 == 2 || par2 == 3;
                }
                else{
                    var7 = par2 == 2 ? this.newRenderLegs : this.newRenderBody;
                    var7.c.j = par2 == 0;
                    var7.d.j = par2 == 0;
                    var7.e.j = par2 == 1 || par2 == 2;
                    var7.f.j = par2 == 1 || par2 == 2;
                    var7.g.j = par2 == 1 || par2 == 2;
                    var7.h.j = par2 == 2 || par2 == 3;
                    var7.i.j = par2 == 2 || par2 == 3;
                }
                this.a((bbo)var7);
                var7.p = this.i.p;
                var7.q = this.i.q;
                var7.s = this.i.s;
                float var8 = 1.0F;

                if (var6.getArmorMaterial() == Material.leather) {
                    int var9 = var6.getColor(var4);
                    float var10 = (float)(var9 >> 16 & 255) / 255.0F;
                    float var11 = (float)(var9 >> 8 & 255) / 255.0F;
                    float var12 = (float)(var9 & 255) / 255.0F;
                    GL11.glColor3f(var8 * var10, var8 * var11, var8 * var12);
                    if (var4.isItemEnchanted()) {
                        return 31;
                    }

                    return 16;
                }

                GL11.glColor3f(var8, var8, var8);
                if (var4.isItemEnchanted()) {
                    return 15;
                }

                return 1;
            }
        }

        return -1;
    }
    @Overwrite
    public void a(beu par1AbstractClientPlayer, double par2, double par4, double par6, float par8, float par9) {
        float var10 = 1.0F;
        GL11.glColor3f(var10, var10, var10);
        ItemStack var11 = par1AbstractClientPlayer.inventory.getCurrentItemStack();
        this.g.m = this.h.m = this.f.m = this.SpecializedRenderBody.m = this.SpecializedRenderLegs.m = this.newRenderBody.m = this.newRenderLegs.m = this.SpecializedRenderHead.m = var11 != null ? 1 : 0;
        if (var11 != null && par1AbstractClientPlayer.bq() > 0) {
            EnumItemInUseAction var12 = var11.getItemInUseAction(par1AbstractClientPlayer);
            if (var12 == EnumItemInUseAction.BLOCK) {
                this.g.m = this.h.m = this.f.m = this.SpecializedRenderBody.m = this.SpecializedRenderLegs.m = this.newRenderBody.m = this.newRenderLegs.m = this.SpecializedRenderHead.m = 3;
            } else if (var12 == EnumItemInUseAction.BOW) {
                this.g.o = this.h.o = this.f.o = this.SpecializedRenderBody.o = this.SpecializedRenderLegs.o = this.newRenderBody.o = this.newRenderLegs.o = this.SpecializedRenderHead.o = true;
            }
        }

        this.g.n = this.h.n = this.f.n  = this.SpecializedRenderBody.n = this.SpecializedRenderLegs.n = this.newRenderBody.n = this.newRenderLegs.n = this.SpecializedRenderHead.n = par1AbstractClientPlayer.isSneaking();
        double var14 = par4 - (double)par1AbstractClientPlayer.yOffset;
        if (par1AbstractClientPlayer.isSneaking() && !(par1AbstractClientPlayer instanceof bex)) {
            var14 -= 0.125;
        }

        super.a(par1AbstractClientPlayer, par2, var14, par6, par8, par9);
        this.g.o = this.h.o = this.f.o = this.SpecializedRenderBody.o = this.SpecializedRenderLegs.o = this.newRenderBody.o = this.newRenderLegs.o = this.SpecializedRenderHead.o = false;
        this.g.n = this.h.n = this.f.n = this.SpecializedRenderBody.n = this.SpecializedRenderLegs.n = this.newRenderBody.n = this.newRenderLegs.n = this.SpecializedRenderHead.n = false;
        this.g.m = this.h.m = this.f.m = this.SpecializedRenderBody.m = this.SpecializedRenderLegs.m = this.newRenderBody.m = this.newRenderLegs.m = this.SpecializedRenderHead.m = 0;
    }

    @Shadow
    protected bjo a(Entity entity) {
        return null;
    }
}
