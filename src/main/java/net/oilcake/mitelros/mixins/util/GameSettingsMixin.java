package net.oilcake.mitelros.mixins.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import net.minecraft.EnumOptions;
import net.minecraft.GameSettings;
import net.minecraft.KeyBinding;
import net.minecraft.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({GameSettings.class})
public class GameSettingsMixin {
   public float ReportedGamma;
   @Shadow
   public float musicVolume = 1.0F;
   @Shadow
   public float soundVolume = 1.0F;
   @Shadow
   public float mouseSensitivity = 0.5F;
   @Shadow
   public boolean invertMouse;
   @Shadow
   private int renderDistance;
   @Shadow
   public boolean viewBobbing = true;
   @Shadow
   public boolean anaglyph;
   @Shadow
   public boolean advancedOpengl;
   @Shadow
   public int limitFramerate = 3;
   @Shadow
   private boolean fancyGraphics = true;
   @Shadow
   public int ambientOcclusion = 2;
   @Shadow
   public boolean clouds = true;
   @Shadow
   public String skin = "Default";
   @Shadow
   public int chatVisibility;
   @Shadow
   public boolean chatColours = true;
   @Shadow
   public boolean chatLinks = true;
   @Shadow
   public boolean chatLinksPrompt = true;
   @Shadow
   public float chatOpacity = 1.0F;
   @Shadow
   public boolean serverTextures = true;
   @Shadow
   public boolean snooperEnabled = true;
   @Shadow
   private boolean fullScreen;
   @Shadow
   private boolean enableVsync = true;
   @Shadow
   public boolean hideServerAddress;
   @Shadow
   public boolean advancedItemTooltips;
   @Shadow
   public boolean pauseOnLostFocus = true;
   @Shadow
   public boolean showCape = true;
   @Shadow
   public boolean touchscreen;
   @Shadow
   public int overrideWidth;
   @Shadow
   public int overrideHeight;
   @Shadow
   public boolean heldItemTooltips = true;
   @Shadow
   public float chatScale = 1.0F;
   @Shadow
   public float F = 1.0F;
   @Shadow
   public float chatHeightUnfocused = 0.44366196F;
   @Shadow
   public float chatHeightFocused = 1.0F;
   @Shadow
   public KeyBinding keyBindPlayerList = new KeyBinding("key.playerlist", 41);
   @Shadow
   public KeyBinding keyBindCommand = new KeyBinding("key.command", 53);
   @Shadow
   public KeyBinding keyBindToggleRun = new KeyBinding("key.toggleRun", 15);
   @Shadow
   public KeyBinding[] keyBindings;
   @Shadow
   protected Minecraft mc;
   @Shadow
   private File optionsFile;
   @Shadow
   public int difficulty;
   @Shadow
   public String lastServer;
   @Shadow
   public float fovSetting;
   @Shadow
   public float gammaSetting;
   @Shadow
   public int guiScale;
   @Shadow
   public int particleSetting;
   @Shadow
   public String language;

   @Shadow
   private float parseFloat(String par1Str) {
      return 0.0F;
   }

   @Overwrite
   public float getOptionFloatValue(EnumOptions par1EnumOptions) {
      return par1EnumOptions == EnumOptions.FOV ? this.fovSetting : (par1EnumOptions == EnumOptions.GAMMA ? this.ReportedGamma : (par1EnumOptions == EnumOptions.MUSIC ? this.musicVolume : (par1EnumOptions == EnumOptions.SOUND ? this.soundVolume : (par1EnumOptions == EnumOptions.SENSITIVITY ? this.mouseSensitivity : (par1EnumOptions == EnumOptions.CHAT_OPACITY ? this.chatOpacity : (par1EnumOptions == EnumOptions.CHAT_HEIGHT_FOCUSED ? this.chatHeightFocused : (par1EnumOptions == EnumOptions.CHAT_HEIGHT_UNFOCUSED ? this.chatHeightUnfocused : (par1EnumOptions == EnumOptions.CHAT_SCALE ? this.chatScale : (par1EnumOptions == EnumOptions.CHAT_WIDTH ? this.F : 0.0F)))))))));
   }

   @Overwrite
   public void setOptionFloatValue(EnumOptions par1EnumOptions, float par2) {
      if (par1EnumOptions == EnumOptions.MUSIC) {
         this.musicVolume = par2;
         this.mc.sndManager.onSoundOptionsChanged();
      }

      if (par1EnumOptions == EnumOptions.SOUND) {
         this.soundVolume = par2;
         this.mc.sndManager.onSoundOptionsChanged();
      }

      if (par1EnumOptions == EnumOptions.SENSITIVITY) {
         this.mouseSensitivity = par2;
      }

      if (par1EnumOptions == EnumOptions.FOV) {
         this.fovSetting = par2;
      }

      if (par1EnumOptions == EnumOptions.GAMMA) {
         this.ReportedGamma = par2;
      }

      if (par1EnumOptions == EnumOptions.CHAT_OPACITY) {
         this.chatOpacity = par2;
         this.mc.ingameGUI.getChatGUI().func_96132_b();
      }

      if (par1EnumOptions == EnumOptions.CHAT_HEIGHT_FOCUSED) {
         this.chatHeightFocused = par2;
         this.mc.ingameGUI.getChatGUI().func_96132_b();
      }

      if (par1EnumOptions == EnumOptions.CHAT_HEIGHT_UNFOCUSED) {
         this.chatHeightUnfocused = par2;
         this.mc.ingameGUI.getChatGUI().func_96132_b();
      }

      if (par1EnumOptions == EnumOptions.CHAT_WIDTH) {
         this.F = par2;
         this.mc.ingameGUI.getChatGUI().func_96132_b();
      }

      if (par1EnumOptions == EnumOptions.CHAT_SCALE) {
         this.chatScale = par2;
         this.mc.ingameGUI.getChatGUI().func_96132_b();
      }

   }

   @Overwrite
   public void loadOptions() {
      try {
         if (!this.optionsFile.exists()) {
            return;
         }

         BufferedReader var1 = new BufferedReader(new FileReader(this.optionsFile));
         String var2 = "";

         while((var2 = var1.readLine()) != null) {
            try {
               String[] var3 = var2.split(":");
               if (var3[0].equals("music")) {
                  this.musicVolume = this.parseFloat(var3[1]);
               }

               if (var3[0].equals("sound")) {
                  this.soundVolume = this.parseFloat(var3[1]);
               }

               if (var3[0].equals("mouseSensitivity")) {
                  this.mouseSensitivity = this.parseFloat(var3[1]);
               }

               if (var3[0].equals("fov")) {
                  this.fovSetting = this.parseFloat(var3[1]);
               }

               if (var3[0].equals("gamma")) {
                  this.ReportedGamma = this.parseFloat(String.valueOf(Math.min(1.0F, Float.parseFloat(var3[1]))));
               }

               if (var3[0].equals("invertYMouse")) {
                  this.invertMouse = var3[1].equals("true");
               }

               if (var3[0].equals("viewDistance")) {
                  this.renderDistance = Integer.parseInt(var3[1]);
               }

               if (var3[0].equals("guiScale")) {
                  this.guiScale = Integer.parseInt(var3[1]);
               }

               if (var3[0].equals("particles")) {
                  this.particleSetting = Integer.parseInt(var3[1]);
               }

               if (var3[0].equals("bobView")) {
                  this.viewBobbing = var3[1].equals("true");
               }

               if (var3[0].equals("anaglyph3d")) {
                  this.anaglyph = var3[1].equals("true");
               }

               if (var3[0].equals("advancedOpengl")) {
                  this.advancedOpengl = var3[1].equals("true");
               }

               if (var3[0].equals("fpsLimit")) {
                  this.limitFramerate = Integer.parseInt(var3[1]);
                  if (this.limitFramerate == 0) {
                     this.limitFramerate = 3;
                  }
               }

               if (var3[0].equals("difficulty")) {
                  this.difficulty = 3;
               }

               if (var3[0].equals("fancyGraphics")) {
                  this.fancyGraphics = var3[1].equals("true");
               }

               if (var3[0].equals("ao")) {
                  if (var3[1].equals("true")) {
                     this.ambientOcclusion = 2;
                  } else if (var3[1].equals("false")) {
                     this.ambientOcclusion = 0;
                  } else {
                     this.ambientOcclusion = Integer.parseInt(var3[1]);
                  }
               }

               if (var3[0].equals("clouds")) {
                  this.clouds = var3[1].equals("true");
               }

               if (var3[0].equals("skin")) {
                  this.skin = var3[1];
               }

               if (var3[0].equals("lastServer") && var3.length >= 2) {
                  this.lastServer = var2.substring(var2.indexOf(58) + 1);
               }

               if (var3[0].equals("lang") && var3.length >= 2) {
                  this.language = var3[1];
               }

               if (var3[0].equals("chatVisibility")) {
                  this.chatVisibility = Integer.parseInt(var3[1]);
               }

               if (var3[0].equals("chatColors")) {
                  this.chatColours = var3[1].equals("true");
               }

               if (var3[0].equals("chatLinks")) {
                  this.chatLinks = var3[1].equals("true");
               }

               if (var3[0].equals("chatLinksPrompt")) {
                  this.chatLinksPrompt = var3[1].equals("true");
               }

               if (var3[0].equals("chatOpacity")) {
                  this.chatOpacity = this.parseFloat(var3[1]);
               }

               if (var3[0].equals("serverTextures")) {
                  this.serverTextures = var3[1].equals("true");
               }

               if (var3[0].equals("snooperEnabled")) {
                  this.snooperEnabled = var3[1].equals("true");
               }

               if (var3[0].equals("fullscreen")) {
                  this.fullScreen = var3[1].equals("true");
               }

               if (var3[0].equals("enableVsync")) {
                  this.enableVsync = var3[1].equals("true");
               }

               if (var3[0].equals("hideServerAddress")) {
                  this.hideServerAddress = var3[1].equals("true");
               }

               if (var3[0].equals("advancedItemTooltips")) {
                  this.advancedItemTooltips = var3[1].equals("true");
               }

               if (var3[0].equals("pauseOnLostFocus")) {
                  this.pauseOnLostFocus = var3[1].equals("true");
               }

               if (var3[0].equals("showCape")) {
                  this.showCape = var3[1].equals("true");
               }

               if (var3[0].equals("touchscreen")) {
                  this.touchscreen = var3[1].equals("true");
               }

               if (var3[0].equals("overrideHeight")) {
                  this.overrideHeight = Integer.parseInt(var3[1]);
               }

               if (var3[0].equals("overrideWidth")) {
                  this.overrideWidth = Integer.parseInt(var3[1]);
               }

               if (var3[0].equals("heldItemTooltips")) {
                  this.heldItemTooltips = var3[1].equals("true");
               }

               if (var3[0].equals("chatHeightFocused")) {
                  this.chatHeightFocused = this.parseFloat(var3[1]);
               }

               if (var3[0].equals("chatHeightUnfocused")) {
                  this.chatHeightUnfocused = this.parseFloat(var3[1]);
               }

               if (var3[0].equals("chatScale")) {
                  this.chatScale = this.parseFloat(var3[1]);
               }

               if (var3[0].equals("chatWidth")) {
                  this.F = this.parseFloat(var3[1]);
               }

               for(int var4 = 0; var4 < this.keyBindings.length; ++var4) {
                  if (var3[0].equals("key_" + this.keyBindings[var4].keyDescription)) {
                     this.keyBindings[var4].keyCode = Integer.parseInt(var3[1]);
                  }
               }
            } catch (Exception var5) {
               this.mc.getLogAgent().logWarning("Skipping bad option: " + var2);
            }
         }

         KeyBinding.resetKeyBindingArrayAndHash();
         var1.close();
      } catch (Exception var6) {
         Exception var6 = var6;
         this.mc.getLogAgent().logWarning("Failed to load options");
         var6.printStackTrace();
      }

      if (this.keyBindPlayerList.keyCode == 15 && this.keyBindToggleRun.keyCode == 15) {
         this.keyBindPlayerList.keyCode = 41;
      }

   }

   @Overwrite
   public void saveOptions() {
      try {
         PrintWriter var1 = new PrintWriter(new FileWriter(this.optionsFile));
         var1.println("music:" + this.musicVolume);
         var1.println("sound:" + this.soundVolume);
         var1.println("invertYMouse:" + this.invertMouse);
         var1.println("mouseSensitivity:" + this.mouseSensitivity);
         var1.println("fov:" + this.fovSetting);
         var1.println("gamma:" + this.ReportedGamma);
         var1.println("viewDistance:" + this.renderDistance);
         var1.println("guiScale:" + this.guiScale);
         var1.println("particles:" + this.particleSetting);
         var1.println("bobView:" + this.viewBobbing);
         var1.println("anaglyph3d:" + this.anaglyph);
         var1.println("advancedOpengl:" + this.advancedOpengl);
         var1.println("fpsLimit:" + this.limitFramerate);
         var1.println("difficulty:" + this.difficulty);
         var1.println("fancyGraphics:" + this.fancyGraphics);
         var1.println("ao:" + this.ambientOcclusion);
         var1.println("clouds:" + this.clouds);
         var1.println("skin:" + this.skin);
         var1.println("lastServer:" + this.lastServer);
         var1.println("lang:" + this.language);
         var1.println("chatVisibility:" + this.chatVisibility);
         var1.println("chatColors:" + this.chatColours);
         var1.println("chatLinks:" + this.chatLinks);
         var1.println("chatLinksPrompt:" + this.chatLinksPrompt);
         var1.println("chatOpacity:" + this.chatOpacity);
         var1.println("serverTextures:" + this.serverTextures);
         var1.println("snooperEnabled:" + this.snooperEnabled);
         var1.println("fullscreen:" + this.fullScreen);
         var1.println("enableVsync:" + this.enableVsync);
         var1.println("hideServerAddress:" + this.hideServerAddress);
         var1.println("advancedItemTooltips:" + this.advancedItemTooltips);
         var1.println("pauseOnLostFocus:" + this.pauseOnLostFocus);
         var1.println("showCape:" + this.showCape);
         var1.println("touchscreen:" + this.touchscreen);
         var1.println("overrideWidth:" + this.overrideWidth);
         var1.println("overrideHeight:" + this.overrideHeight);
         var1.println("heldItemTooltips:" + this.heldItemTooltips);
         var1.println("chatHeightFocused:" + this.chatHeightFocused);
         var1.println("chatHeightUnfocused:" + this.chatHeightUnfocused);
         var1.println("chatScale:" + this.chatScale);
         var1.println("chatWidth:" + this.F);

         for(int var2 = 0; var2 < this.keyBindings.length; ++var2) {
            var1.println("key_" + this.keyBindings[var2].keyDescription + ":" + this.keyBindings[var2].keyCode);
         }

         var1.close();
      } catch (Exception var3) {
         Exception var3 = var3;
         this.mc.getLogAgent().logWarning("Failed to save options");
         var3.printStackTrace();
      }

      this.sendSettingsToServer();
   }

   @Shadow
   public void sendSettingsToServer() {
   }
}
