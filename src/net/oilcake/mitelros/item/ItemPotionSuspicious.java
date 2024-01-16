package net.oilcake.mitelros.item;

import net.minecraft.*;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.util.ExperimentalConfig;
import net.oilcake.mitelros.util.StuckTagConfig;

public class ItemPotionSuspicious extends Item{
    public ItemPotionSuspicious(int id) {
        super(id, Material.glass, "suspicious_potion");
        this.setMaxStackSize(1);
        this.setCraftingDifficultyAsComponent(25.0F);
        this.setCreativeTab(CreativeModeTab.tabMisc);
        this.setWater(1);
    }

    public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
        double rand = Math.random();
        if (player.onServer()) {
            if(ExperimentalConfig.TagConfig.Realistic.ConfigValue){
                player.addPotionEffect((new MobEffect(MobEffectList.poison.id, (int) (450 * (1 + rand)), 0)));
                player.addPotionEffect((new MobEffect(PotionExtend.dehydration.id, (int) (160 * (1 + rand)), 0)));
            }else {
                if(rand > (StuckTagConfig.TagConfig.TagDigest.ConfigValue ? 1 : 0.8)){
                    player.addPotionEffect((new MobEffect(MobEffectList.poison.id, 450, 0)));
                }
                player.addPotionEffect((new MobEffect(PotionExtend.dehydration.id, (int) (160 * (1 + rand)), 0)));
            }
            if(rand == 0){
                player.getFoodStats().addNutrition(1);
                player.sendChatToPlayer(ChatMessage.createFromTranslationKey("欢迎来到Double随机等于0的欧皇大殿").setColor(EnumChatFormat.AQUA));
            }
            player.addWater(this.getWater());
        }
        super.onItemUseFinish(item_stack, world, player);
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 32;
    }

    public boolean isDrinkable(int item_subtype) {
        return true;
    }

    public Item getItemProducedOnItemUseFinish() {
        return glassBottle;
    }
}
