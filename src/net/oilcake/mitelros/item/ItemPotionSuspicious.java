package net.oilcake.mitelros.item;

import net.minecraft.*;
import net.oilcake.mitelros.item.potion.PotionExtend;

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
            if(rand>0.9){
                player.addPotionEffect((new MobEffect(MobEffectList.poison.id, 400, 0)));
            }
            if(rand > 0.05){
                player.addPotionEffect((new MobEffect(PotionExtend.dehydration.id, 300, 0)));
            }
            if(rand == 0){
                player.getFoodStats().addNutrition(1);
                player.sendChatToPlayer(ChatMessage.createFromTranslationKey("欢迎来到Double随机等于0的欧皇大殿").setColor(EnumChatFormat.AQUA));
            }
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
