package net.oilcake.mitelros.mixins.render;

import net.minecraft.Minecraft;
import net.minecraft.Render;
import net.minecraft.RenderManager;
import net.minecraft.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({Render.class})
public class BaseRenderMixin {
   @Shadow
   protected RenderManager b;
   @Shadow
   protected ResourceLocation[] textures;
   @Shadow
   protected ResourceLocation[] textures_glowing;

   protected boolean forceGlowOverride() {
      return false;
   }

   @Overwrite
   protected void setTexture(int index, String path, String glow_path) {
      if (this.textures[index] != null) {
         Minecraft.setErrorMessage("setTexture: texture [" + index + "] has already been set for " + this);
      } else {
         ResourceLocation texture = new ResourceLocation(path + ".png");
         this.textures[index] = texture;
         ResourceLocation texture_glowing = new ResourceLocation(glow_path + "_glow.png", false);
         if (Minecraft.MITE_resource_pack != null && Minecraft.MITE_resource_pack.resourceExists(texture_glowing) || this.forceGlowOverride()) {
            this.textures_glowing[index] = texture_glowing;
         }
      }

   }

   public RenderManager getRenderManager() {
      return this.b;
   }
}
