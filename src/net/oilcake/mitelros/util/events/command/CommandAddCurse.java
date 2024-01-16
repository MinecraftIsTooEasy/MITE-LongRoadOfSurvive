package net.oilcake.mitelros.util.events.command;

import net.minecraft.*;

import java.util.List;
import java.util.Random;

public class CommandAddCurse extends CommandAbstract {

    @Override
    public String getCommandName() {
        return "curse";
    }

    @Override
    public String getCommandUsage(ICommandListener iCommandListener) {
        return "commands.curse.usage";
    }
    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public void processCommand(ICommandListener iCommandListener, String[] strings) {
        if(Integer.parseInt(strings[0]) >= 0 && Integer.parseInt(strings[0]) <= Curse.cursesList.length){
            ServerPlayer player = getCommandSenderAsPlayer(iCommandListener);
            EntityWitch temp = new EntityWitch(player.worldObj);
            player.worldObj.getAsWorldServer().addCurse(player.getAsEntityPlayerMP(),temp, Curse.cursesList[Integer.parseInt(strings[0])], 0);
            player.learnCurseEffect();
        } else {
            iCommandListener.sendChatToPlayer(ChatMessage.createFromText("用法:/curse <num>" + ";<num> 为 0 ~ " + Curse.cursesList.length).setColor(EnumChatFormat.RED));
        }

    }
}