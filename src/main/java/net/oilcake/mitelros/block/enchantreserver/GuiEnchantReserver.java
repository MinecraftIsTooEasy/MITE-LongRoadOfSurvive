package net.oilcake.mitelros.block.enchantreserver;

import java.util.List;
import net.minecraft.Container;
import net.minecraft.EntityPlayer;
import net.minecraft.GuiContainer;
import net.minecraft.I18n;
import net.minecraft.ICrafting;
import net.minecraft.InventoryPlayer;
import net.minecraft.ItemStack;
import net.minecraft.ResourceLocation;
import net.oilcake.mitelros.network.PacketEnchantReserverInfo;
import org.lwjgl.opengl.GL11;

public class GuiEnchantReserver extends GuiContainer implements ICrafting {
   private static final ResourceLocation ENCHANT_RESERVER_TEXTURE = new ResourceLocation("textures/gui/container/enchant_reserver.png");
   private final ContainerEnchantReserver containerEnchantReserver;
   private final TileEntityEnchantReserver tileEntityEnchantReserver = new TileEntityEnchantReserver();
   private final int x;
   private final int y;
   private final int z;
   private final InventoryPlayer inventory;
   private final EntityPlayer player;

   public GuiEnchantReserver(EntityPlayer player, int x, int y, int z, EnchantReserverSlots slots) {
      super(new ContainerEnchantReserver(slots, player, x, y, z));
      this.containerEnchantReserver = (ContainerEnchantReserver)super.inventorySlots;
      this.x = x;
      this.y = y;
      this.z = z;
      this.inventory = player.inventory;
      this.player = player;
   }

   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
      String var3 = this.tileEntityEnchantReserver.hasCustomName() ? this.tileEntityEnchantReserver.getCustomNameOrUnlocalized() : I18n.getString(this.tileEntityEnchantReserver.getCustomNameOrUnlocalized());
      this.fontRenderer.drawString(var3, this.xSize / 2 - this.fontRenderer.getStringWidth(var3) / 2, 6, 4210752);
      this.fontRenderer.drawString(I18n.getString("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
   }

   protected void drawGuiContainerBackgroundLayer(float v, int i, int i1) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.drawDefaultBackground();
      this.mc.getTextureManager().bindTexture(ENCHANT_RESERVER_TEXTURE);
      int var4 = (this.width - this.xSize) / 2;
      int var5 = (this.height - this.ySize) / 2;
      int var6 = this.guiLeft;
      int var7 = this.guiTop;
      this.drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
      int exp = Math.max(0, this.tileEntityEnchantReserver.getEXP() - 2000);
      int maxExp = this.tileEntityEnchantReserver.getMAXEXP() - 2000;
      int r;
      int g;
      int b;
      if (this.tileEntityEnchantReserver.getEXP() < 2000) {
         r = 160 + (int)(24.0F * ((float)this.tileEntityEnchantReserver.getEXP() / 2000.0F));
         g = 30 + (int)(196.0F * ((float)this.tileEntityEnchantReserver.getEXP() / 2000.0F));
         b = 30 - (int)(27.0F * ((float)this.tileEntityEnchantReserver.getEXP() / 2000.0F));
      } else {
         r = 184 - (int)(120.0F * ((float)exp / (float)maxExp));
         g = 226 - (int)(66.0F * ((float)exp / (float)maxExp));
         b = 3 + (int)(29.0F * ((float)exp / (float)maxExp));
      }

      int color = (r << 16) + (g << 8) + b;
      this.drawTexturedModalRect(var6 + 99, var7 + 21, 176, 0, 16, (int)(43.0F * (float)exp / (float)maxExp));
      if (this.tileEntityEnchantReserver.getEXP() < 2000) {
         this.fontRenderer.drawString(this.tileEntityEnchantReserver.getEXP() + "/" + 2000, this.width / 2 + 8, this.height / 2 - 70, color);
      } else {
         this.fontRenderer.drawString(exp + "/" + maxExp, this.width / 2 + 8, this.height / 2 - 70, color);
      }

   }

   public void sendContainerAndContentsToPlayer(Container container, List list) {
   }

   public void sendSlotContents(Container container, int i, ItemStack itemStack) {
   }

   public void sendProgressBarUpdate(Container container, int i, int i1) {
      this.player.sendPacket(new PacketEnchantReserverInfo());
   }

   public void setEnchantInfo(int exp) {
      this.tileEntityEnchantReserver.setEXP(exp);
   }
}
