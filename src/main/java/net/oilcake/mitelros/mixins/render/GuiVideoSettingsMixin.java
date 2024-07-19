package net.oilcake.mitelros.mixins.render;

import net.minecraft.EnumOptions;
import net.minecraft.GameSettings;
import net.minecraft.GuiButton;
import net.minecraft.GuiScreen;
import net.minecraft.GuiSlider;
import net.minecraft.GuiSmallButton;
import net.minecraft.GuiVideoSettings;
import net.minecraft.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({GuiVideoSettings.class})
public class GuiVideoSettingsMixin extends GuiScreen {
   @Shadow
   protected String screenTitle = "Video Settings";
   @Shadow
   private GameSettings guiGameSettings;
   @Shadow
   private boolean is64bit;
   @Shadow
   private static EnumOptions[] videoOptions;

   @Overwrite
   public void initGui() {
      this.screenTitle = I18n.getString("options.videoTitle");
      this.buttonList.clear();
      this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.getString("gui.done")));
      this.is64bit = false;
      String[] var1 = new String[]{"sun.arch.data.model", "com.ibm.vm.bitmode", "os.arch"};
      String[] var2 = var1;
      int var3 = var1.length;

      int var8;
      for(var8 = 0; var8 < var3; ++var8) {
         String var5 = var2[var8];
         String var6 = System.getProperty(var5);
         if (var6 != null && var6.contains("64")) {
            this.is64bit = true;
            break;
         }
      }

      var8 = 0;
      var3 = this.is64bit ? 0 : -15;
      EnumOptions[] var9 = videoOptions;
      int var10 = var9.length;

      for(int var11 = 0; var11 < var10; ++var11) {
         EnumOptions var7 = var9[var11];
         if (var7.getEnumFloat()) {
            GuiSlider slider = new GuiSlider(var7.returnEnumOrdinal(), this.width / 2 - 155 + var8 % 2 * 160, this.height / 7 + var3 + 24 * (var8 >> 1), var7, this.guiGameSettings.getKeyBinding(var7), this.guiGameSettings.getOptionFloatValue(var7));
            this.buttonList.add(slider);
         } else {
            this.buttonList.add(new GuiSmallButton(var7.returnEnumOrdinal(), this.width / 2 - 155 + var8 % 2 * 160, this.height / 7 + var3 + 24 * (var8 >> 1), var7, this.guiGameSettings.getKeyBinding(var7)));
         }

         ++var8;
      }

   }
}
