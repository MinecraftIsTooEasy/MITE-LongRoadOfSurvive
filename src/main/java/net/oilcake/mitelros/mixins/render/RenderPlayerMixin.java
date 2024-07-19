package net.oilcake.mitelros.mixins.render;

import net.minecraft.AbstractClientPlayer;
import net.minecraft.Block;
import net.minecraft.ClientPlayer;
import net.minecraft.Entity;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumItemInUseAction;
import net.minecraft.Item;
import net.minecraft.ItemArmor;
import net.minecraft.ItemBow;
import net.minecraft.ItemFishingRod;
import net.minecraft.ItemRenderer;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.MathHelper;
import net.minecraft.ModelBase;
import net.minecraft.ModelBiped;
import net.minecraft.RenderBiped;
import net.minecraft.RenderBlocks;
import net.minecraft.RenderPlayer;
import net.minecraft.RendererLivingEntity;
import net.minecraft.ResourceLocation;
import net.minecraft.TileEntitySkullRenderer;
import net.oilcake.mitelros.entity.model.ModelBipedOuter;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({RenderPlayer.class})
public class RenderPlayerMixin extends RendererLivingEntity {
   @Shadow
   private ModelBiped modelBipedMain;
   @Shadow
   private ModelBiped modelArmorChestplate;
   @Shadow
   private ModelBiped modelArmor;
   private ModelBiped SpecializedRenderBody;
   private ModelBiped SpecializedRenderLegs;
   private ModelBiped SpecializedRenderHead;
   private ModelBipedOuter modelBipedNew;

   public RenderPlayerMixin(ModelBase par1ModelBase, float par2) {
      super(par1ModelBase, par2);
   }

   @Inject(
      method = {"<init>()V"},
      at = {@At("RETURN")}
   )
   public void injectCtor(CallbackInfo callbackInfo) {
      this.SpecializedRenderBody = new ModelBiped(0.25F);
      this.SpecializedRenderLegs = new ModelBiped(0.0125F);
      this.SpecializedRenderHead = new ModelBiped(0.5F);
      this.modelArmorChestplate = new ModelBiped(0.75F);
      this.modelArmor = new ModelBiped(0.375F);
   }

   @Overwrite
   protected int setArmorModel(AbstractClientPlayer par1AbstractClientPlayer, int par2, float par3) {
      ItemStack var4 = par1AbstractClientPlayer.inventory.armorItemInSlot(3 - par2);
      if (var4 != null) {
         Item var5 = var4.getItem();
         if (var5 instanceof ItemArmor) {
            ItemArmor var6 = (ItemArmor)var5;
            this.bindTexture(RenderBiped.func_110857_a(var6, par2));
            ModelBiped var7;
            if (var6.getArmorMaterial() != Materials.custom_b && var6.getArmorMaterial() != Materials.custom_a) {
               var7 = par2 == 2 ? this.modelArmor : this.modelArmorChestplate;
               var7.bipedHead.showModel = par2 == 0;
               var7.bipedHeadwear.showModel = par2 == 0;
               var7.bipedBody.showModel = par2 == 1 || par2 == 2;
               var7.bipedRightArm.showModel = par2 == 1 || par2 == 2;
               var7.bipedLeftArm.showModel = par2 == 1 || par2 == 2;
               var7.bipedRightLeg.showModel = par2 == 2 || par2 == 3;
               var7.bipedLeftLeg.showModel = par2 == 2 || par2 == 3;
            } else {
               var7 = par2 == 2 ? this.SpecializedRenderLegs : (par2 == 0 ? this.SpecializedRenderHead : this.SpecializedRenderBody);
               var7.bipedHead.showModel = par2 == 0;
               var7.bipedHeadwear.showModel = par2 == 0;
               var7.bipedBody.showModel = par2 == 1 || par2 == 2;
               var7.bipedRightArm.showModel = par2 == 1;
               var7.bipedLeftArm.showModel = par2 == 1;
               var7.bipedRightLeg.showModel = par2 == 2 || par2 == 3;
               var7.bipedLeftLeg.showModel = par2 == 2 || par2 == 3;
            }

            this.setRenderPassModel(var7);
            var7.onGround = this.mainModel.onGround;
            var7.isRiding = this.mainModel.isRiding;
            var7.isChild = this.mainModel.isChild;
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
   public void func_130009_a(AbstractClientPlayer par1AbstractClientPlayer, double par2, double par4, double par6, float par8, float par9) {
      float var10 = 1.0F;
      GL11.glColor3f(var10, var10, var10);
      ItemStack var11 = par1AbstractClientPlayer.inventory.getCurrentItemStack();
      this.modelBipedMain.heldItemRight = this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedNew.heldItemRight = this.SpecializedRenderBody.heldItemRight = this.SpecializedRenderLegs.heldItemRight = this.SpecializedRenderHead.heldItemRight = var11 != null ? 1 : 0;
      if (var11 != null && par1AbstractClientPlayer.getItemInUseCount() > 0) {
         EnumItemInUseAction var12 = var11.getItemInUseAction(par1AbstractClientPlayer);
         if (var12 == EnumItemInUseAction.BLOCK) {
            this.modelBipedMain.heldItemRight = this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedNew.heldItemRight = this.SpecializedRenderBody.heldItemRight = this.SpecializedRenderLegs.heldItemRight = this.SpecializedRenderHead.heldItemRight = 3;
         } else if (var12 == EnumItemInUseAction.BOW) {
            this.modelBipedMain.aimedBow = this.modelArmorChestplate.aimedBow = this.modelArmor.aimedBow = this.modelBipedNew.aimedBow = this.SpecializedRenderBody.aimedBow = this.SpecializedRenderLegs.aimedBow = this.SpecializedRenderHead.aimedBow = true;
         }
      }

      this.modelBipedMain.isSneak = this.modelArmorChestplate.isSneak = this.modelArmor.isSneak = this.modelBipedNew.isSneak = this.SpecializedRenderBody.isSneak = this.SpecializedRenderLegs.isSneak = this.SpecializedRenderHead.isSneak = par1AbstractClientPlayer.isSneaking();
      double var14 = par4 - (double)par1AbstractClientPlayer.yOffset;
      if (par1AbstractClientPlayer.isSneaking() && !(par1AbstractClientPlayer instanceof ClientPlayer)) {
         var14 -= 0.125;
      }

      super.doRenderLiving(par1AbstractClientPlayer, par2, var14, par6, par8, par9);
      this.modelBipedMain.aimedBow = this.modelArmorChestplate.aimedBow = this.modelArmor.aimedBow = this.modelBipedNew.aimedBow = this.SpecializedRenderBody.aimedBow = this.SpecializedRenderLegs.aimedBow = this.SpecializedRenderHead.aimedBow = false;
      this.modelBipedMain.isSneak = this.modelArmorChestplate.isSneak = this.modelArmor.isSneak = this.modelBipedNew.isSneak = this.SpecializedRenderBody.isSneak = this.SpecializedRenderLegs.isSneak = this.SpecializedRenderHead.isSneak = false;
      this.modelBipedMain.heldItemRight = this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedNew.heldItemRight = this.SpecializedRenderBody.heldItemRight = this.SpecializedRenderLegs.heldItemRight = this.SpecializedRenderHead.heldItemRight = 0;
   }

   @Overwrite
   public void renderFirstPersonArm(EntityPlayer player) {
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

      if ((Boolean)ExperimentalConfig.TagConfig.TagNewRenderPlayer.ConfigValue) {
         this.modelBipedNew.onGround = 0.0F;
         this.modelBipedNew.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
         this.modelBipedNew.bipedRightArm.render(0.0625F);
         this.modelBipedNew.bipedRightArmwear.render(0.0625F);
      } else {
         this.modelBipedMain.onGround = 0.0F;
         this.modelBipedMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
         this.modelBipedMain.bipedRightArm.render(0.0625F);
      }

      if (alpha < 0.99F) {
         GL11.glDisable(3042);
         GL11.glAlphaFunc(516, 0.1F);
         GL11.glPopMatrix();
         GL11.glDepthMask(true);
      }

   }

   @Overwrite
   protected void renderSpecials(AbstractClientPlayer par1AbstractClientPlayer, float par2) {
      float var3 = 1.0F;
      GL11.glColor3f(var3, var3, var3);
      super.renderEquippedItems(par1AbstractClientPlayer, par2);
      super.renderArrowsStuckInEntity(par1AbstractClientPlayer, par2);
      ItemStack var4 = par1AbstractClientPlayer.inventory.armorItemInSlot(3);
      float var14;
      if (var4 != null) {
         GL11.glPushMatrix();
         if ((Boolean)ExperimentalConfig.TagConfig.TagNewRenderPlayer.ConfigValue) {
            this.modelBipedNew.bipedHead.postRender(0.0625F);
         } else {
            this.modelBipedMain.bipedHead.postRender(0.0625F);
         }

         if (var4.getItem().itemID < 512) {
            if (RenderBlocks.renderItemIn3d(Block.blocksList[var4.itemID].getRenderType())) {
               var14 = 0.625F;
               GL11.glTranslatef(0.0F, -0.25F, 0.0F);
               GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
               GL11.glScalef(var14, -var14, -var14);
            }

            this.getRenderManager().itemRenderer.renderItem(par1AbstractClientPlayer, var4, 0);
         } else if (var4.getItem().itemID == Item.skull.itemID) {
            var14 = 1.0625F;
            GL11.glScalef(var14, -var14, -var14);
            String var6 = "";
            if (var4.hasTagCompound() && var4.getTagCompound().hasKey("SkullOwner")) {
               var6 = var4.getTagCompound().getString("SkullOwner");
            }

            TileEntitySkullRenderer.skullRenderer.func_82393_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, var4.getItemSubtype(), var6);
         }

         GL11.glPopMatrix();
      }

      if (par1AbstractClientPlayer.getCommandSenderName().equals("deadmau5") && par1AbstractClientPlayer.getTextureSkin().isTextureUploaded()) {
         this.bindTexture(par1AbstractClientPlayer.getLocationSkin());

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
            if ((Boolean)ExperimentalConfig.TagConfig.TagNewRenderPlayer.ConfigValue) {
               this.modelBipedNew.b(0.0625F);
            } else {
               this.modelBipedMain.renderEars(0.0625F);
            }

            GL11.glPopMatrix();
         }
      }

      boolean var24 = par1AbstractClientPlayer.getTextureCape().isTextureUploaded();
      boolean var25 = !par1AbstractClientPlayer.isInvisible();
      boolean var26 = !par1AbstractClientPlayer.getHideCape();
      if (var24 && var25 && var26) {
         this.bindTexture(par1AbstractClientPlayer.getLocationCape());
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
         if ((Boolean)ExperimentalConfig.TagConfig.TagNewRenderPlayer.ConfigValue) {
            this.modelBipedNew.c(0.0625F);
         } else {
            this.modelBipedMain.renderCloak(0.0625F);
         }

         GL11.glPopMatrix();
      }

      ItemStack var28 = par1AbstractClientPlayer.inventory.getCurrentItemStack();
      if (var28 != null) {
         GL11.glPushMatrix();
         if ((Boolean)ExperimentalConfig.TagConfig.TagNewRenderPlayer.ConfigValue) {
            this.modelBipedNew.bipedRightArm.postRender(0.0625F);
         } else {
            this.modelBipedMain.bipedRightArm.postRender(0.0625F);
         }

         GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
         ItemRenderer var10000;
         if (var28.getItem() instanceof ItemFishingRod && par1AbstractClientPlayer.fishEntity != null) {
            var10000 = this.getRenderManager().itemRenderer;
            ItemRenderer.render_icon_override = ((ItemFishingRod)var28.getItem()).func_94597_g();
         }

         EnumItemInUseAction var9 = null;
         if (par1AbstractClientPlayer.getItemInUseCount() > 0) {
            var9 = var28.getItemInUseAction(par1AbstractClientPlayer);
         }

         float var31;
         if (var28.itemID < 512 && RenderBlocks.renderItemIn3d(Block.blocksList[var28.itemID].getRenderType())) {
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
         } else if (Item.itemsList[var28.itemID].isFull3D()) {
            var31 = 0.625F;
            if (Item.itemsList[var28.itemID].shouldRotateAroundWhenRendering()) {
               GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
               GL11.glTranslatef(0.0F, -0.125F, 0.0F);
            }

            if (par1AbstractClientPlayer.getItemInUseCount() > 0 && var9 == EnumItemInUseAction.BLOCK) {
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

         int var33;
         float var13;
         float var32;
         if (var28.getItem().requiresMultipleRenderPasses()) {
            for(var33 = 0; var33 <= 1; ++var33) {
               int var11 = var28.getItem().getColorFromItemStack(var28, var33);
               var32 = (float)(var11 >> 16 & 255) / 255.0F;
               var13 = (float)(var11 >> 8 & 255) / 255.0F;
               var14 = (float)(var11 & 255) / 255.0F;
               GL11.glColor4f(var32, var13, var14, 1.0F);
               this.getRenderManager().itemRenderer.renderItem(par1AbstractClientPlayer, var28, var33);
            }
         } else {
            var33 = var28.getItem().getColorFromItemStack(var28, 0);
            float var30 = (float)(var33 >> 16 & 255) / 255.0F;
            var32 = (float)(var33 >> 8 & 255) / 255.0F;
            var13 = (float)(var33 & 255) / 255.0F;
            GL11.glColor4f(var30, var32, var13, 1.0F);
            this.getRenderManager().itemRenderer.renderItem(par1AbstractClientPlayer, var28, 0);
         }

         var10000 = this.getRenderManager().itemRenderer;
         ItemRenderer.render_icon_override = null;
         GL11.glPopMatrix();
      }

   }

   @Shadow
   protected ResourceLocation getEntityTexture(Entity entity) {
      return null;
   }

   @Redirect(
      method = {"renderSpecials(Lnet/minecraft/AbstractClientPlayer;F)V"},
      at = @At(
   value = "NEW",
   target = "(F)Lnet/minecraft/ModelBiped;",
   ordinal = 0
)
   )
   private ModelBiped ctorNewRender(float par1) {
      return new ModelBiped(par1, 0.0F, 64, 64);
   }

   @Inject(
      method = {"<init>()V"},
      at = {@At("RETURN")}
   )
   private void ctorNewRender(CallbackInfo callbackInfo) {
      if ((Boolean)ExperimentalConfig.TagConfig.TagNewRenderPlayer.ConfigValue) {
         this.mainModel = new ModelBipedOuter(0.0F, 0.0F, 64, 64);
         this.modelBipedNew = (ModelBipedOuter)this.mainModel;
      } else {
         this.modelBipedNew = new ModelBipedOuter();
      }

   }
}
