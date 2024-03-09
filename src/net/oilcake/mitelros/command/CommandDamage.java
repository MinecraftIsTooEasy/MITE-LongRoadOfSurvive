package net.oilcake.mitelros.command;

import net.minecraft.*;

public class CommandDamage extends CommandAbstract {
    @Override
    public String getCommandName() {
        return "damage";
    }
    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
    @Override
    public String getCommandUsage(ICommandListener iCommandListener) {
        return "commands.damage.usage";
    }

    @Override
    public void processCommand(ICommandListener iCommandListener, String[] strings) {
        EntityPlayer player = getCommandSenderAsPlayer(iCommandListener);
        ItemStack holding = player.getHeldItemStack();
        if(holding != null && Integer.parseInt(strings[0]) >= 0 && Integer.parseInt(strings[0]) < holding.getMaxDamage()){
            holding.setItemDamage(Integer.parseInt(strings[0]));
        } else {
            iCommandListener.sendChatToPlayer(ChatMessage.createFromText("用法:/damage <num>" + ";<num> 为 0 ~ " + player.getHeldItemStack().getMaxDamage() + ";请确认手中物品合法。").setColor(EnumChatFormat.RED));
        }
    }
}
