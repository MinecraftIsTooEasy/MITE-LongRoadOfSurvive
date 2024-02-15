package net.oilcake.mitelros.item;

import net.minecraft.*;
import net.oilcake.mitelros.achivements.AchievementExtend;

public class ItemTotem extends Item {
    public ItemTotem(int id, Material material, String texture) {
        super(id, material, texture);
        this.setMaxStackSize(16);
        this.setCraftingDifficultyAsComponent(100.0F);
        this.setCreativeTab(CreativeModeTab.tabTools);
    }
    private void performEffectCommon(EntityPlayer player, ItemTotem totem) {
        player.clearActivePotions();
        player.setHealth(Math.max(player.getHealth(),2),true,player.getHealFX());
        player.makeSound("imported.random.totem_use", 3.0F, 1.0F+ player.getRand().nextFloat()*0.1F);
        player.addPotionEffect(new MobEffect(MobEffectList.blindness.id, 40, 4));
        player.vision_dimming = 1.0F;
    }
    private void performEffectSpecified(EntityPlayer player, ItemTotem totem){
        Material totem_material = totem.getHardestMetalMaterial();
        if(totem_material == Material.gold){
            player.setHealth(player.getMaxHealth(),true,player.getHealFX());
            for(int i = 0;i < 8;i++){
                player.entityFX(EnumEntityFX.heal);
            }
        } else if(totem_material == Material.ancient_metal){
            for(int i = 0;i < 8;i++){
                player.entityFX(EnumEntityFX.curse_effect_learned);
            }
            player.addExperience(player.experience / 5);
        } else if(totem_material == Material.iron){
            for(int i = 0;i < 8;i++){
                player.entityFX(EnumEntityFX.smoke_and_steam);
            }
            player.addPotionEffect((new MobEffect(MobEffectList.resistance.id, 400, (int) ((1.0F - player.getHealthFraction()) * 4))));
        } else if(totem_material == Materials.nickel){
            for(int i = 0;i < 8;i++){
                player.entityFX(EnumEntityFX.summoned);
            }
            player.setHunt_counter(400);
            player.hunt_cap = true;
            player.addPotionEffect((new MobEffect(MobEffectList.damageBoost.id, 400, (int) ((1.0F - player.getHealthFraction()) * 2))));
        } else if(totem_material == Materials.tungsten){
            float delta = player.getHealthFraction() - 0.5F;
            for (int i = 0; i<8;i++){
                player.entityFX(EnumEntityFX.smoke);
            }
            player.worldObj.createExplosion(player, player.posX, player.posY + 1.5, player.posZ, 0.0F, (4.0F - 4.0F * delta), true);
            player.setHealth(player.getMaxHealth() / 2.0F,true,player.getHealFX());
        } else {
            Minecraft.setErrorMessage("effectSpecified(): Undefined Material "+totem_material.toString()+".");
        }
    }
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        ItemStack totem = player.getHeldItemStack();
        if(totem.getItem() instanceof ItemTotem){
            performEffectCommon(player, (ItemTotem) totem.getItem());
            performEffectSpecified(player, (ItemTotem) totem.getItem());
            player.convertOneOfHeldItem((ItemStack) null);
            return true;
        }
        return false;
    }
    public void performNegativeEffect(EntityPlayer player){
        ItemStack totem = player.getHeldItemStack();
        if(totem.getItem() instanceof ItemTotem){
            performEffectCommon(player, (ItemTotem) totem.getItem());
            performEffectSpecified(player, (ItemTotem) totem.getItem());
            player.triggerAchievement(AchievementExtend.cheatdeath);
            player.convertOneOfHeldItem((ItemStack) null);
        }
    }
}
