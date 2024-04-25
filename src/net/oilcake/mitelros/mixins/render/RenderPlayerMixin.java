package net.oilcake.mitelros.mixins.render;

import net.minecraft.*;
import net.oilcake.mitelros.entity.model.ModelBipedOuter;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
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
    private ModelBipedOuter modelBipedNew;

    public RenderPlayerMixin(bbo par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }
    @Inject(method = "<init>",at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        this.SpecializedRenderBody = new bbj(0.25F);
        this.SpecializedRenderLegs = new bbj(0.0125F);
        this.SpecializedRenderHead = new bbj(0.5F);
        this.g = new bbj(0.75F);
        this.h = new bbj(0.375F);
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
                    var7 = par2 == 2 ? this.h : this.g;
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
        this.f.m = this.g.m = this.h.m = this.modelBipedNew.heldItemRight = this.SpecializedRenderBody.m = this.SpecializedRenderLegs.m = this.SpecializedRenderHead.m = var11 != null ? 1 : 0;
        if (var11 != null && par1AbstractClientPlayer.bq() > 0) {
            EnumItemInUseAction var12 = var11.getItemInUseAction(par1AbstractClientPlayer);
            if (var12 == EnumItemInUseAction.BLOCK) {
                this.f.m = this.g.m = this.h.m = this.modelBipedNew.heldItemRight = this.SpecializedRenderBody.m = this.SpecializedRenderLegs.m = this.SpecializedRenderHead.m = 3;
            } else if (var12 == EnumItemInUseAction.BOW) {
                this.f.o = this.g.o = this.h.o = this.modelBipedNew.aimedBow = this.SpecializedRenderBody.o = this.SpecializedRenderLegs.o = this.SpecializedRenderHead.o = true;
            }
        }

        this.f.n = this.g.n = this.h.n = this.modelBipedNew.isSneak  = this.SpecializedRenderBody.n = this.SpecializedRenderLegs.n = this.SpecializedRenderHead.n = par1AbstractClientPlayer.isSneaking();
        double var14 = par4 - (double)par1AbstractClientPlayer.yOffset;
        if (par1AbstractClientPlayer.isSneaking() && !(par1AbstractClientPlayer instanceof bex)) {
            var14 -= 0.125;
        }

        super.a(par1AbstractClientPlayer, par2, var14, par6, par8, par9);
        this.f.o = this.g.o = this.h.o = this.modelBipedNew.aimedBow = this.SpecializedRenderBody.o = this.SpecializedRenderLegs.o = this.SpecializedRenderHead.o = false;
        this.f.n = this.g.n = this.h.n = this.modelBipedNew.isSneak = this.SpecializedRenderBody.n = this.SpecializedRenderLegs.n = this.SpecializedRenderHead.n = false;
        this.f.m = this.g.m = this.h.m = this.modelBipedNew.heldItemRight = this.SpecializedRenderBody.m = this.SpecializedRenderLegs.m = this.SpecializedRenderHead.m = 0;
    }
    @Overwrite
    public void a(EntityPlayer player) {
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        float alpha = this.getModelOpacity(player);
        if (alpha < 0.99F) {
            GL11.glPushMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
            GL11.glDepthMask(false);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glAlphaFunc(516, 0.003921569F);
        }


        if(ExperimentalConfig.TagConfig.TagNewRenderPlayer.ConfigValue){
            this.modelBipedNew.p = 0.0F;
            this.modelBipedNew.a(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
            this.modelBipedNew.bipedRightArm.a(0.0625F);
            this.modelBipedNew.bipedRightArmwear.a(0.0625F);
        }else {
            this.f.p = 0.0F;
            this.f.a(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
            this.f.f.a(0.0625F);
        }

//        this.h.p = 0.0F;
//        this.h.a(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
//        this.h.f.a(0.0625F);

        if (alpha < 0.99F) {
            GL11.glDisable(3042);
            GL11.glAlphaFunc(516, 0.1F);
            GL11.glPopMatrix();
            GL11.glDepthMask(true);
        }

    }
    @Overwrite
    protected void a(beu par1AbstractClientPlayer, float par2) {
        float var3 = 1.0F;
        GL11.glColor3f(var3, var3, var3);
        super.c(par1AbstractClientPlayer, par2);
        super.e(par1AbstractClientPlayer, par2);
        ItemStack var4 = par1AbstractClientPlayer.inventory.armorItemInSlot(3);
        if (var4 != null) {
            GL11.glPushMatrix();
            if(ExperimentalConfig.TagConfig.TagNewRenderPlayer.ConfigValue){
                this.modelBipedNew.bipedHead.c(0.0625F);
            }else {
                this.f.c.c(0.0625F);
            }
            float var5;
            if (var4.getItem().itemID < net.oilcake.mitelros.util.Constant.Extended_Block_ID) {
                if (bfr.a(Block.blocksList[var4.itemID].getRenderType())) {
                    var5 = 0.625F;
                    GL11.glTranslatef(0.0F, -0.25F, 0.0F);
                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScalef(var5, -var5, -var5);
                }

                this.getRenderManager().f.a(par1AbstractClientPlayer, var4, 0);
            } else if (var4.getItem().itemID == Item.skull.itemID) {
                var5 = 1.0625F;
                GL11.glScalef(var5, -var5, -var5);
                String var6 = "";
                if (var4.hasTagCompound() && var4.getTagCompound().hasKey("SkullOwner")) {
                    var6 = var4.getTagCompound().getString("SkullOwner");
                }

                bjb.a.a(-0.5F, 0.0F, -0.5F, 1, 180.0F, var4.getItemSubtype(), var6);
            }

            GL11.glPopMatrix();
        }

        float var14;
        if (par1AbstractClientPlayer.getCommandSenderName().equals("deadmau5") && par1AbstractClientPlayer.p().a()) {
            this.a((bjo)par1AbstractClientPlayer.r());

            for(int var23 = 0; var23 < 2; ++var23) {
                float var27 = par1AbstractClientPlayer.prevRotationYaw + (par1AbstractClientPlayer.rotationYaw - par1AbstractClientPlayer.prevRotationYaw) * par2 - (par1AbstractClientPlayer.prevRenderYawOffset + (par1AbstractClientPlayer.renderYawOffset - par1AbstractClientPlayer.prevRenderYawOffset) * par2);
                float var7 = par1AbstractClientPlayer.prevRotationPitch + (par1AbstractClientPlayer.rotationPitch - par1AbstractClientPlayer.prevRotationPitch) * par2;
                GL11.glPushMatrix();
                GL11.glRotatef(var27, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(var7, 1.0F, 0.0F, 0.0F);
                GL11.glTranslatef(0.375F * (float)(var23 * 2 - 1), 0.0F, 0.0F);
                GL11.glTranslatef(0.0F, -0.375F, 0.0F);
                GL11.glRotatef(-var7, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-var27, 0.0F, 1.0F, 0.0F);
                var14 = 1.3333334F;
                GL11.glScalef(var14, var14, var14);
                if(ExperimentalConfig.TagConfig.TagNewRenderPlayer.ConfigValue){
                    this.modelBipedNew.b(0.0625F);
                }else {
                    this.f.b(0.0625F);
                }

                GL11.glPopMatrix();
            }
        }

        boolean var24 = par1AbstractClientPlayer.q().a();
        boolean var25 = !par1AbstractClientPlayer.isInvisible();
        boolean var26 = !par1AbstractClientPlayer.bL();
        if (var24 && var25 && var26) {
            this.a((bjo)par1AbstractClientPlayer.s());
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 0.0F, 0.125F);
            double var29 = par1AbstractClientPlayer.field_71091_bM + (par1AbstractClientPlayer.field_71094_bP - par1AbstractClientPlayer.field_71091_bM) * (double)par2 - (par1AbstractClientPlayer.prevPosX + (par1AbstractClientPlayer.posX - par1AbstractClientPlayer.prevPosX) * (double)par2);
            double var10 = par1AbstractClientPlayer.field_71096_bN + (par1AbstractClientPlayer.field_71095_bQ - par1AbstractClientPlayer.field_71096_bN) * (double)par2 - (par1AbstractClientPlayer.prevPosY + (par1AbstractClientPlayer.posY - par1AbstractClientPlayer.prevPosY) * (double)par2);
            double var12 = par1AbstractClientPlayer.field_71097_bO + (par1AbstractClientPlayer.field_71085_bR - par1AbstractClientPlayer.field_71097_bO) * (double)par2 - (par1AbstractClientPlayer.prevPosZ + (par1AbstractClientPlayer.posZ - par1AbstractClientPlayer.prevPosZ) * (double)par2);
            var14 = par1AbstractClientPlayer.prevRenderYawOffset + (par1AbstractClientPlayer.renderYawOffset - par1AbstractClientPlayer.prevRenderYawOffset) * par2;
            double var15 = (double)MathHelper.sin(var14 * 3.1415927F / 180.0F);
            double var17 = (double)(-MathHelper.cos(var14 * 3.1415927F / 180.0F));
            float var19 = (float)var10 * 10.0F;
            if (var19 < -6.0F) {
                var19 = -6.0F;
            }

            if (var19 > 32.0F) {
                var19 = 32.0F;
            }

            float var20 = (float)(var29 * var15 + var12 * var17) * 100.0F;
            float var21 = (float)(var29 * var17 - var12 * var15) * 100.0F;
            if (var20 < 0.0F) {
                var20 = 0.0F;
            }

            float var22 = par1AbstractClientPlayer.prevCameraYaw + (par1AbstractClientPlayer.cameraYaw - par1AbstractClientPlayer.prevCameraYaw) * par2;
            var19 += MathHelper.sin((par1AbstractClientPlayer.prevDistanceWalkedModified + (par1AbstractClientPlayer.distanceWalkedModified - par1AbstractClientPlayer.prevDistanceWalkedModified) * par2) * 6.0F) * 32.0F * var22;
            if (par1AbstractClientPlayer.isSneaking()) {
                var19 += 25.0F;
            }

            GL11.glRotatef(6.0F + var20 / 2.0F + var19, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(var21 / 2.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-var21 / 2.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            if(ExperimentalConfig.TagConfig.TagNewRenderPlayer.ConfigValue){
                this.modelBipedNew.c(0.0625F);
            }else {
                this.f.c(0.0625F);
            }

            GL11.glPopMatrix();
        }

        ItemStack var28 = par1AbstractClientPlayer.inventory.getCurrentItemStack();
        if (var28 != null) {
            GL11.glPushMatrix();
            if(ExperimentalConfig.TagConfig.TagNewRenderPlayer.ConfigValue){
                this.modelBipedNew.bipedRightArm.c(0.0625F);
            }else {
                this.f.f.c(0.0625F);
            }

            GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
            bfj var10000;
            if (var28.getItem() instanceof ItemFishingRod && par1AbstractClientPlayer.fishEntity != null) {
                var10000 = this.getRenderManager().f;
                bfj.render_icon_override = ((ItemFishingRod)var28.getItem()).g();
            }

            EnumItemInUseAction var9 = null;
            if (par1AbstractClientPlayer.bq() > 0) {
                var9 = var28.getItemInUseAction(par1AbstractClientPlayer);
            }

            float var31;
            if (var28.itemID < net.oilcake.mitelros.util.Constant.Extended_Block_ID && bfr.a(Block.blocksList[var28.itemID].getRenderType())) {
                var31 = 0.5F;
                GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
                var31 *= 0.75F;
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(-var31, -var31, var31);
            } else if (var28.getItem() instanceof ItemBow) {
                var31 = 0.625F;
                GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(var31, -var31, var31);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            } else if (Item.itemsList[var28.itemID].n_()) {
                var31 = 0.625F;
                if (Item.itemsList[var28.itemID].o_()) {
                    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glTranslatef(0.0F, -0.125F, 0.0F);
                }

                if (par1AbstractClientPlayer.bq() > 0 && var9 == EnumItemInUseAction.BLOCK) {
                    GL11.glTranslatef(0.05F, 0.0F, -0.1F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
                }

                GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
                GL11.glScalef(var31, -var31, var31);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            } else {
                var31 = 0.375F;
                GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
                GL11.glScalef(var31, var31, var31);
                GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
            }

            float var13;
            int var33;
            float var32;
            if (var28.getItem().b()) {
                for(var33 = 0; var33 <= 1; ++var33) {
                    int var11 = var28.getItem().a(var28, var33);
                    var32 = (float)(var11 >> 16 & 255) / 255.0F;
                    var13 = (float)(var11 >> 8 & 255) / 255.0F;
                    var14 = (float)(var11 & 255) / 255.0F;
                    GL11.glColor4f(var32, var13, var14, 1.0F);
                    this.getRenderManager().f.a(par1AbstractClientPlayer, var28, var33);
                }
            } else {
                var33 = var28.getItem().a(var28, 0);
                float var30 = (float)(var33 >> 16 & 255) / 255.0F;
                var32 = (float)(var33 >> 8 & 255) / 255.0F;
                var13 = (float)(var33 & 255) / 255.0F;
                GL11.glColor4f(var30, var32, var13, 1.0F);
                this.getRenderManager().f.a(par1AbstractClientPlayer, var28, 0);
            }

            var10000 = this.getRenderManager().f;
            bfj.render_icon_override = null;
            GL11.glPopMatrix();
        }

    }
    @Shadow
    protected bjo a(Entity entity) {
        return null;
    }
//    @ModifyConstant(method = {
//            "a(Lnet/minecraft/beu;F)V",
//    }, constant = @Constant(intValue = 256))
//    private static int ExtendedBlockID(int value) {
//        return net.oilcake.mitelros.util.Constant.Extended_Block_ID;
//    }
    @Redirect(method = "a(Lnet/minecraft/beu;F)V", at = @At(value = "NEW", target = "(F)Lnet/minecraft/bbj;", ordinal = 0))
    private bbj ctorNewRender(float par1){
        return new bbj(par1, 0.0F, 64, 64);
    }
    @Inject(method = "<init>()V", at = @At(value = "RETURN"))
    private void ctorNewRender(CallbackInfo callbackInfo){
        if(ExperimentalConfig.TagConfig.TagNewRenderPlayer.ConfigValue){
            this.i = new ModelBipedOuter(0.0F, 0.0F, 64, 64);
            this.modelBipedNew = (ModelBipedOuter) this.i;
        }else {
            this.modelBipedNew = new ModelBipedOuter();
        }
    }
}
