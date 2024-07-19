package net.oilcake.mitelros.mixins.util;

import net.minecraft.GameSettings;
import net.minecraft.Minecraft;
import net.minecraft.MovementInput;
import net.minecraft.MovementInputFromOptions;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({MovementInputFromOptions.class})
public class MovementInputFromOptionsMixin extends MovementInput {
   @Shadow
   private GameSettings gameSettings;

   public void updatePlayerMoveState() {
      if (!(Boolean)ExperimentalConfig.TagConfig.TagMovingV2.ConfigValue) {
         this.moveStrafe = 0.0F;
         this.moveForward = 0.0F;
         if (!Minecraft.theMinecraft.thePlayer.isGhost()) {
            if (this.gameSettings.keyBindForward.pressed) {
               ++this.moveForward;
            }

            if (this.gameSettings.keyBindBack.pressed) {
               --this.moveForward;
            }

            if (this.gameSettings.keyBindLeft.pressed) {
               ++this.moveStrafe;
            }

            if (this.gameSettings.keyBindRight.pressed) {
               --this.moveStrafe;
            }

            this.jump = this.gameSettings.keyBindJump.pressed;
            this.sneak = this.gameSettings.keyBindSneak.pressed;
            if (this.sneak) {
               this.moveStrafe = (float)((double)this.moveStrafe * 0.3);
               this.moveForward = (float)((double)this.moveForward * 0.3);
            }
         }
      } else {
         if (this.moveStrafe >= 0.2F) {
            this.moveStrafe -= 0.2F;
         } else if (this.moveStrafe < -0.2F) {
            this.moveStrafe += 0.2F;
         } else {
            this.moveStrafe = 0.0F;
         }

         if (this.moveForward >= 0.2F) {
            this.moveForward -= 0.2F;
         } else if (this.moveForward < -0.2F) {
            this.moveForward += 0.2F;
         } else {
            this.moveForward = 0.0F;
         }

         if (!Minecraft.theMinecraft.thePlayer.isGhost()) {
            if (this.gameSettings.keyBindForward.pressed && this.moveForward < 1.0F) {
               this.moveForward += 0.5F;
            }

            if (this.gameSettings.keyBindBack.pressed && this.moveForward > -1.0F) {
               this.moveForward -= 0.5F;
            }

            if (this.gameSettings.keyBindLeft.pressed && this.moveStrafe < 1.0F) {
               this.moveStrafe += 0.5F;
            }

            if (this.gameSettings.keyBindRight.pressed && this.moveStrafe > -1.0F) {
               this.moveStrafe -= 0.5F;
            }

            label71: {
               if (!Minecraft.theMinecraft.thePlayer.isInWater()) {
                  Minecraft var10000 = Minecraft.theMinecraft;
                  if (!Minecraft.inDevMode()) {
                     this.jump = this.gameSettings.keyBindJump.isPressed();
                     break label71;
                  }
               }

               this.jump = this.gameSettings.keyBindJump.pressed;
            }

            this.sneak = this.gameSettings.keyBindSneak.pressed;
            if (this.sneak) {
               this.moveStrafe = (float)((double)this.moveStrafe * 0.5);
               this.moveForward = (float)((double)this.moveForward * 0.5);
            }
         }
      }

   }
}
