package net.oilcake.mitelros.item;

import net.minecraft.*;
import net.oilcake.mitelros.item.enchantment.Enchantments;

import java.util.Random;

public class ItemWand extends Item implements IDamageableItem{
    private Material reinforcement_material;
    public ItemWand(int id, Material material) {
        this.setMaxStackSize(1);
        this.setMaxDamage(192);
        this.setCreativeTab(CreativeModeTab.tabCombat);
    }
    @Override
    public int getNumComponentsForDurability() {
        return 3;
    }
    @Override
    public int getRepairCost() {
        return 2;
    };
    public Material getMaterialForDurability()
    {
        return Material.diamond;
    }
    public Material getMaterialForRepairs()
    {
        return this.reinforcement_material == null ? Material.diamond : this.reinforcement_material;
    }
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        player.setHeldItemInUse();
        return true;
    }
    public static int getTicksForMaxPull(ItemStack item_stack) {
        return 40;
    }

    public static int getTicksPulled(ItemStack item_stack, int item_in_use_count) {
        return item_stack.getMaxItemUseDuration() - item_in_use_count;
    }

    public static float getFractionPulled(ItemStack item_stack, int item_in_use_count) {
        return Math.min((float)getTicksPulled(item_stack, item_in_use_count) / (float)getTicksForMaxPull(item_stack), 1.0F);
    }
    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 72000;
    }
    public EnumItemInUseAction getItemInUseAction(ItemStack par1ItemStack, EntityPlayer player) {
        return EnumItemInUseAction.BOW;
    }
    public int getItemEnchantability() {
        return 0;
    }
    public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
    }
    public void onPlayerStoppedUsing(ItemStack item_stack, World world, EntityPlayer player, int item_in_use_count) {
        if (!world.isRemote) {
            float fraction_pulled = getFractionPulled(item_stack, item_in_use_count);
            fraction_pulled = (fraction_pulled * fraction_pulled + fraction_pulled * 2.0F) / 3.0F;
            if (!(fraction_pulled < 0.1F)) {
                if (fraction_pulled > 1.0F) {
                    fraction_pulled = 1.0F;
                }
                EntitySmallFireball fireball = new EntitySmallFireball(player.worldObj, player, player.posX,player.posY, player.posZ) {
                    @Override
                    protected void onImpact(RaycastCollision raycastCollision) {

                    }

                    @Override
                    public void setCollisionPolicies(Raycast raycast) {

                    }
                };
                if (!world.isRemote) {
                    world.spawnEntityInWorld(fireball);
                }
            }
        }

    }
}
