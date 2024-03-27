package net.oilcake.mitelros.block.enchantreserver;

import net.minecraft.*;
import net.oilcake.mitelros.network.PacketEnchantReserverInfo;
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
        int var6 = this.p;
        int var7 = this.q;
        this.b(var4, var5, 0, 0, this.c, this.d);
        int exp = Math.max(0 , tileEntityEnchantReserver.getEXP() - 2000);
        int maxExp = tileEntityEnchantReserver.getMAXEXP() - 2000;
        //if (exp != 0) {
        //x, y, u, v, width, height
        int r;
        int g;
        int b;
        if(tileEntityEnchantReserver.getEXP() < 2000){
            r = 160 + (int)(24.0F * ((float) tileEntityEnchantReserver.getEXP() / 2000.0F));
            g = 30 + (int)(196.0F * ((float) tileEntityEnchantReserver.getEXP() / 2000.0F));
            b = 30 - (int)(27.0F * ((float) tileEntityEnchantReserver.getEXP() / 2000.0F));
        }else {
            r = 184 - (int)(120.0F * ((float) exp / (float)maxExp));
            g = 226 - (int)(66.0F * ((float) exp / (float)maxExp));
            b = 3 + (int)(29.0F * ((float) exp / (float)maxExp));
        }
        int color = (r << 16) + (g << 8) + b;
        this.b(var6 + 99, var7 + 21, 176, 0, 16, (int) (43 * (float) exp / (float)maxExp));
        //}
        if(tileEntityEnchantReserver.getEXP() < 2000){
            this.o.b(tileEntityEnchantReserver.getEXP() + "/" + 2000 , this.g/2+8, this.h/2-70, color);
        }else {
            this.o.b(exp + "/" + maxExp , this.g/2+8, this.h/2-70, color);
        }


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
