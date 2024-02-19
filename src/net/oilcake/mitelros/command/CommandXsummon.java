package net.oilcake.mitelros.command;

import net.minecraft.*;

public class CommandXsummon extends CommandAbstract {
    @Override
    public String getCommandName() {
        return "xsummon";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
    @Override
    public String getCommandUsage(ICommandListener iCommandListener) {
        return "commands.xsummon.usage";
    }

    @Override
    public void processCommand(ICommandListener iCommandListener, String[] strings) {
        EntityPlayer player = getCommandSenderAsPlayer(iCommandListener);
        World world = iCommandListener.getEntityWorld();

        Entity entity = EntityTypes.createEntityByID(Integer.parseInt(strings[0]), world);

        if (entity != null) {
            entity.setPosition(player.posX, player.posY, player.posZ);
            if (entity instanceof EntityInsentient) {
                ((EntityInsentient) entity).onSpawnWithEgg(null);
            }

            world.spawnEntityInWorld(entity);
            iCommandListener.sendChatToPlayer(ChatMessage.createFromText("已生成实体 " + entity).setColor(EnumChatFormat.WHITE));
        } else {
            iCommandListener.sendChatToPlayer(ChatMessage.createFromText("无法生成实体:ID为 " + Integer.valueOf(strings[0]) + " 的实体不存在!").setColor(EnumChatFormat.RED));
        }
    }
}
