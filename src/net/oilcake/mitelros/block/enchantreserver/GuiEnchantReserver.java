package net.oilcake.mitelros.block.enchantreserver;

import net.minecraft.*;
import net.oilcake.mitelros.util.network.PacketEnchantReserverInfo;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class GuiEnchantReserver extends awy implements ICrafting {
    private static final bjo ENCHANT_RESERVER_TEXTURE = new bjo("textures/gui/container/enchant_reserver.png");
    private final ContainerEnchantReserver containerEnchantReserver;
    private final TileEntityEnchantReserver tileEntityEnchantReserver = new TileEntityEnchantReserver();
    private final int x;
    private final int y;
    private final int z;
    private final PlayerInventory inventory;
    private final EntityPlayer player;


    public GuiEnchantReserver(EntityPlayer player, int x, int y, int z,EnchantReserverSlots slots) {
        super(new ContainerEnchantReserver(slots,player,x, y, z));
        this.containerEnchantReserver = (ContainerEnchantReserver) super.e;
        this.x = x;
        this.y = y;
        this.z = z;
        this.inventory = player.inventory;
        this.player = player;
    }


    @Override
    protected void b(int par1, int par2)
    {
        String var3 = this.tileEntityEnchantReserver.hasCustomName() ? this.tileEntityEnchantReserver.getCustomNameOrUnlocalized() : bkb.a(this.tileEntityEnchantReserver.getCustomNameOrUnlocalized());
        this.o.b(var3, this.c / 2 - this.o.a(var3) / 2, 6, 4210752);
        this.o.b(bkb.a("container.inventory"), 8, this.d - 96 + 2, 4210752);
    }
    @Override
    protected void a(float v, int i, int i1) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.e();
        this.f.J().a(ENCHANT_RESERVER_TEXTURE);
        int var4 = (this.g - this.c) / 2;
        int var5 = (this.h - this.d) / 2;
        this.b(var4, var5, 0, 0, this.c, this.d);
        int exp = tileEntityEnchantReserver.getEXP();
        int maxExp = tileEntityEnchantReserver.getMAXEXP();
        //if (exp != 0) {
        //x, y, u, v, width, height
        this.b(this.g/2+8, this.h/2-36, 17, 0, 16, (int) (30 * (float) 123 / (float)maxExp));
        //}
        this.o.b(exp + "/" + maxExp , this.g/2+8, this.h/2-70, 7048739);

    }

    @Override
    public void updateCraftingInventory(Container container, List list) {

    }

    @Override
    public void sendSlotContents(Container container, int i, ItemStack itemStack) {

    }

    @Override
    public void sendProgressBarUpdate(Container container, int i, int i1) {
        this.player.sendPacket(new PacketEnchantReserverInfo());
    }


    public void setEnchantInfo(int exp){
        tileEntityEnchantReserver.setEXP(exp);
    }
}
