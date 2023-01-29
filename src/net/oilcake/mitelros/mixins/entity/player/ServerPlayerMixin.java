package net.oilcake.mitelros.mixins.entity.player;

import net.minecraft.*;
import net.minecraft.server.MinecraftServer;
import net.oilcake.mitelros.block.enchantreserver.ContainerEnchantReserver;
import net.oilcake.mitelros.block.enchantreserver.EnchantReserverSlots;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends EntityPlayer implements ICrafting {
    @Shadow
    private int protein;
    @Shadow
    private int essential_fats;
    @Shadow
    private int phytonutrients;
    @Shadow
    private float field_130068_bO;
    @Shadow
    private float lastHealth;
    @Shadow
    private int last_experience;
    @Shadow
    private int last_nutrition;
    private int last_phytonutrients;
    private int last_protein;
    @Shadow
    private int last_satiation;
    @Shadow
    public PlayerConnection playerNetServerHandler;
    public ServerPlayerMixin(World par1World, String par2Str) {
        super(par1World, par2Str);
    }
    @Inject(method = "<init>",at = @At("RETURN"))
    private void injectInit(MinecraftServer par1MinecraftServer, World par2World, String par3Str,
                                     PlayerInteractManager par4ItemInWorldManager, CallbackInfo callback){
        this.protein = this.essential_fats = this.phytonutrients = 960000;
    }
    @Shadow
    private int currentWindowId;

    @Shadow
    protected abstract void getNextWindowId();
    public void displayGUIEnchantReserver(int x, int y, int z, EnchantReserverSlots slots) {
        this.getNextWindowId();
        TileEntity tile_entity = this.worldObj.getBlockTileEntity(x, y, z);
        this.playerNetServerHandler.sendPacket((new Packet100OpenWindow(this.currentWindowId, 14, tile_entity.getCustomInvName(), 9, tile_entity.hasCustomName())).setCoords(x, y, z));
        this.openContainer = new ContainerEnchantReserver(slots, this, x, y, z);
        this.openContainer.windowId = this.currentWindowId;
        ReflectHelper.dyCast(ServerPlayer.class, this).updateCraftingInventory(this.openContainer, ((ContainerEnchantReserver)this.openContainer).getInventory());
        this.openContainer.onCraftGuiOpened(this);
    }




    public boolean isMalnourishedLv1() {
        if(this.protein <= 800000 && this.protein >= 320000){
            return true;
        }else
            return this.phytonutrients <= 800000 && this.phytonutrients >= 320000;
    }
    public boolean isMalnourishedLv2() {
        if(this.protein < 320000 && this.protein >= 160000){
            return true;
        }else
            return this.phytonutrients < 320000 && this.phytonutrients >= 160000;
    }

    public boolean isMalnourishedLv3() {
        if(this.protein < 160000 && this.protein >= 0){
            return true;
        }else
            return this.phytonutrients < 160000 && this.phytonutrients >= 0;
    }

    @Overwrite
    public float getWetnessAndMalnourishmentHungerMultiplier() {
        int x = this.getBlockPosX();
        int y = this.getFootBlockPosY();
        int z = this.getBlockPosZ();
        float rain_factor = this.isInRain() ? (this.worldObj.isThundering(true) ? 0.5F : 0.25F) : 0.0F;
        float immersion_factor = this.worldObj.getBlockMaterial(x, y + 1, z) == Material.water ? 0.5F : (this.worldObj.getBlockMaterial(x, y, z) == Material.water ? 0.25F : 0.0F);
        float wetness_factor = Math.max(rain_factor, immersion_factor);
        if (this.isInRain() && !this.worldObj.isThundering(true) && immersion_factor == 0.25F) {
            wetness_factor += 0.125F;
        }

        if (this.worldObj.isBiomeFreezing(x, z)) {
            wetness_factor *= 2.0F;
        } else if (this.worldObj.getBiomeGenForCoords(x, z).temperature >= BiomeBase.desertRiver.temperature) {
            wetness_factor = 0.0F;
        }

        //float malnourishment_factor = this.isMalnourishedLv1() ? 0.5F : 0.0F;
        float malnourishment_factor = this.isMalnourishedLv1() ? 0.5F : (this.isMalnourishedLv2() ? 1.0F : (this.isMalnourishedLv3() ? 1.5F : 0.0F));
        return 1.0F + wetness_factor + malnourishment_factor;
    }



    @Shadow
    public INetworkManager getNetManager() {
        return null;
    }

    @Shadow
    public void sendChatToPlayer(ChatMessage chatMessage) {

    }

    @Shadow
    public boolean canCommandSenderUseCommand(int i, String s) {
        return false;
    }

    @Shadow
    public ChunkCoordinates getCommandSenderPosition() {
        return null;
    }

    @Shadow
    public void updateCraftingInventory(Container container, List list) {

    }

    @Shadow
    public void sendSlotContents(Container container, int i, ItemStack itemStack) {

    }

    @Shadow
    public void sendProgressBarUpdate(Container container, int i, int i1) {

    }
}
