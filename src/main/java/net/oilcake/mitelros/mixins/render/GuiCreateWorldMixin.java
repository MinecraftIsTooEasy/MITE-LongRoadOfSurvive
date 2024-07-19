package net.oilcake.mitelros.mixins.render;

import java.io.FileWriter;
import java.util.Date;
import java.util.Random;
import net.minecraft.EnumGameType;
import net.minecraft.GuiButton;
import net.minecraft.GuiCreateWorld;
import net.minecraft.GuiScreen;
import net.minecraft.GuiTextField;
import net.minecraft.I18n;
import net.minecraft.MITEConstant;
import net.minecraft.MathHelper;
import net.minecraft.StatList;
import net.minecraft.WorldSettings;
import net.minecraft.WorldType;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({GuiCreateWorld.class})
public class GuiCreateWorldMixin extends GuiScreen {
   private GuiScreen b;
   private GuiTextField c;
   private GuiTextField d;
   private String e;
   private String p = "survival";
   private boolean q = true;
   private boolean r;
   private boolean s;
   private boolean t;
   private boolean u;
   private boolean v;
   private boolean w;
   private String E;
   private String F;
   private GuiButton x;
   private GuiButton y;
   private GuiButton z;
   private GuiButton A;
   private GuiButton B;
   private GuiButton C;
   private GuiButton D;
   private String G;
   private String H;
   private int I = 2;
   public String a = "";
   private GuiButton button_cancel;
   private GuiButton button_skills;
   private boolean are_skills_enabled;

   @Overwrite
   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      this.buttonList.clear();
      this.buttonList.add(new GuiButton(0, this.width / 2 - 154, this.height - 28, 152, 20, I18n.getString("selectWorld.create")));
      this.buttonList.add(this.button_cancel = new GuiButton(1, this.width / 2 + 2, this.height - 28, 152, 20, I18n.getString("gui.cancel")));
      this.buttonList.add(this.x = new GuiButton(2, this.width / 2 - 74, 114, 152, 20, I18n.getString("selectWorld.gameMode")));
      this.buttonList.add(this.y = new GuiButton(3, this.width / 2 - 74, this.height - 52, 152, 20, I18n.getString("selectWorld.moreWorldOptions")));
      this.buttonList.add(this.z = new GuiButton(4, this.width / 2 - 154, 114, 152, 20, I18n.getString("selectWorld.mapFeatures")));
      this.z.drawButton = false;
      this.z.enabled = false;
      this.buttonList.add(this.A = new GuiButton(7, this.width / 2 + 2, 150, 152, 20, I18n.getString("selectWorld.bonusItems")));
      this.A.drawButton = false;
      this.buttonList.add(this.B = new GuiButton(5, this.width / 2 + 2, 114, 152, 20, I18n.getString("selectWorld.mapType")));
      this.B.drawButton = false;
      this.buttonList.add(this.C = new GuiButton(6, this.width / 2 - 154, 150, 152, 20, I18n.getString("selectWorld.allowCommands")));
      this.C.drawButton = false;
      this.buttonList.add(this.button_skills = new GuiButton(9, this.width / 2 + 2, 114, 152, 20, I18n.getString("selectWorld.professions")));
      this.button_skills.drawButton = false;
      this.B.enabled = true;
      this.C.enabled = false;
      this.A.enabled = false;
      this.buttonList.add(this.D = new GuiButton(8, this.width / 2 + 5, 120, 150, 20, I18n.getString("selectWorld.customizeType")));
      this.D.drawButton = false;
      this.D.enabled = false;
      this.c = new GuiTextField(this.fontRenderer, this.width / 2 - 100, 60, 200, 20);
      this.c.setFocused(true);
      this.c.setText(this.H);
      this.d = new GuiTextField(this.fontRenderer, this.width / 2 - 100, 60, 200, 20);
      this.d.setText(this.G);
      this.func_82288_a(this.w);
      this.makeUseableName();
      this.updateButtonText();
   }

   @Shadow
   private void func_82288_a(boolean par1) {
   }

   @Shadow
   private void makeUseableName() {
   }

   @Overwrite
   protected void actionPerformed(GuiButton par1GuiButton) {
      if (par1GuiButton.enabled) {
         if (par1GuiButton.id == 1) {
            this.mc.displayGuiScreen(this.b);
         } else if (par1GuiButton.id == 0) {
            this.mc.displayGuiScreen((GuiScreen)null);
            if (this.v) {
               return;
            }

            this.v = true;
            long var2 = (new Random()).nextLong();
            String var4 = this.d.getText();
            if (!MathHelper.stringNullOrLengthZero(var4)) {
               try {
                  long var5 = Long.parseLong(var4);
                  if (var5 != 0L) {
                     var2 = var5;
                  }
               } catch (NumberFormatException var10) {
                  var2 = (long)var4.hashCode();
               }
            }

            EnumGameType var8 = EnumGameType.getByName(this.p);
            WorldSettings var6 = new WorldSettings(var2, var8, this.q, this.u, WorldType.worldTypes[this.I], this.are_skills_enabled);
            var6.func_82750_a(this.a);
            if (this.t && !this.u) {
               var6.enableBonusChest();
            }

            if (this.r && !this.u) {
               var6.enableCommands();
            }

            this.mc.launchIntegratedServer(this.e, this.c.getText().trim(), var6);
            this.mc.statFileWriter.readStat(StatList.createWorldStat, 1);

            try {
               FileWriter fw = new FileWriter("MITE/world_seeds.txt", true);
               StringBuffer sb = new StringBuffer();
               sb.append(this.c.getText().trim());
               sb.append(": ");
               sb.append(var6.getSeed());
               sb.append(" (");
               sb.append(new Date());
               sb.append(")" + MITEConstant.newline);
               fw.append(sb.toString());
               fw.close();
            } catch (Exception var9) {
            }
         } else if (par1GuiButton.id == 3) {
            this.func_82287_i();
         } else if (par1GuiButton.id == 2) {
            if (this.p.equals("survival")) {
               if (!this.s) {
                  this.r = false;
               }

               this.u = false;
               this.p = "hardcore";
               this.u = true;
               this.C.enabled = false;
               this.A.enabled = false;
               this.updateButtonText();
            } else {
               if (!this.s) {
                  this.r = false;
               }

               this.p = "survival";
               this.updateButtonText();
               this.u = false;
            }

            this.updateButtonText();
         } else if (par1GuiButton.id == 4) {
            this.q = !this.q;
            this.updateButtonText();
         } else if (par1GuiButton.id == 7) {
            this.t = !this.t;
            this.updateButtonText();
         } else if (par1GuiButton.id != 5) {
            if (par1GuiButton.id == 6) {
               this.s = true;
               this.r = false;
               this.updateButtonText();
            } else if (par1GuiButton.id == 8) {
               this.updateButtonText();
            } else if (par1GuiButton == this.button_skills) {
               this.are_skills_enabled = !this.are_skills_enabled;
               this.updateButtonText();
            }
         } else {
            int z = this.I + 1;

            while(WorldType.worldTypes[z] == null) {
               if (z < 15) {
                  ++z;
               } else {
                  z = 1;
               }
            }

            this.I = z;
            this.updateButtonText();
         }
      }

   }

   @Overwrite
   private void updateButtonText() {
      this.x.displayString = I18n.getString("selectWorld.gameMode") + " " + I18n.getString("selectWorld.gameMode." + this.p);
      this.E = I18n.getString("selectWorld.gameMode." + this.p + ".line1");
      this.F = I18n.getString("selectWorld.gameMode." + this.p + ".line2");
      this.z.displayString = I18n.getString("selectWorld.mapFeatures") + " ";
      if (this.q) {
         this.z.displayString = this.z.displayString + I18n.getString("options.on");
      } else {
         this.z.displayString = this.z.displayString + I18n.getString("options.off");
      }

      this.A.displayString = I18n.getString("selectWorld.bonusItems") + " ";
      this.A.displayString = this.A.displayString + (this.t ? I18n.getString("options.on") : I18n.getString("options.off"));
      this.B.displayString = I18n.getString("selectWorld.mapType") + " " + I18n.getString(WorldType.worldTypes[this.I].getTranslateName());
      this.C.displayString = I18n.getString("selectWorld.allowCommands") + " ";
      this.C.displayString = this.C.displayString + I18n.getString("options.off");
      this.button_skills.displayString = I18n.getString("selectWorld.professions") + " " + I18n.getString(this.are_skills_enabled ? "options.enabled" : "options.disabled");
   }

   @Shadow
   private void func_82287_i() {
   }
}
