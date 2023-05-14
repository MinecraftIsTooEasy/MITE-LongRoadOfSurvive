package net.oilcake.mitelros.util.events.command;

import net.minecraft.CommandAbstract;
import net.minecraft.ICommandListener;
import net.minecraft.server.MinecraftServer;

import java.util.List;

public class CommandTPA extends CommandAbstract {
    public CommandTPA(){
    }
    @Override
    public String getCommandName()
    {
        return "tpa";
    }
    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }

    @Override
    public String getCommandUsage(ICommandListener iCommandListener) {
        return "commands.tpa.usage";
    }

    @Override
    public void processCommand(ICommandListener iCommandListener, String[] strings) {
        notifyAdmins(iCommandListener, "Command \'" + this.getCommandName() + "\' not available!", new Object[0]);
    }
    public List addTabCompletionOptions(ICommandListener par1ICommandSender, String[] par2ArrayOfStr)
    {
        return par2ArrayOfStr.length != 1 && par2ArrayOfStr.length != 2 ? null : getListOfStringsMatchingLastWord(par2ArrayOfStr, MinecraftServer.F().getAllUsernames());
    }

    /**
     * Return whether the specified command parameter index is a username parameter.
     */
    public boolean isUsernameIndex(String[] par1ArrayOfStr, int par2)
    {
        return par2 == 0;
    }
}
