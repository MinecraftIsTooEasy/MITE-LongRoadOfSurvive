package net.oilcake.mitelros.mixins.util.command;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(CommandEffect.class)
public class CommandEffectMixin extends CommandAbstract {
    @Shadow
    public String getCommandName() {
        return null;
    }

    @Shadow
    public String getCommandUsage(ICommandListener iCommandListener) {
        return null;
    }

    @Overwrite
    public void processCommand(ICommandListener par1ICommandSender, String[] par2ArrayOfStr) {
        EntityPlayer player = getCommandSenderAsPlayer(par1ICommandSender);
        if(par2ArrayOfStr[0] != null && par2ArrayOfStr[1] != null && par2ArrayOfStr[2] != null){
            player.addPotionEffect(new MobEffect(Integer.parseInt(par2ArrayOfStr[0]), Integer.parseInt(par2ArrayOfStr[1]), Integer.parseInt(par2ArrayOfStr[2])));
            par1ICommandSender.sendChatToPlayer(ChatMessage.createFromText("给予buffID：" + Integer.parseInt(par2ArrayOfStr[0]))
                    .appendComponent(ChatMessage.createFromText("时间: " + Integer.parseInt(par2ArrayOfStr[1]))
                            .appendComponent(ChatMessage.createFromText("等级+1: " + Integer.parseInt(par2ArrayOfStr[2])).setColor(EnumChatFormat.LIGHT_GRAY))));
        }
    }

}
