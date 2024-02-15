package net.oilcake.mitelros.item;

import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityWandFireball;
import net.oilcake.mitelros.entity.EntityWandIceBall;
import net.oilcake.mitelros.entity.EntityWandShockWave;

public class ItemWand extends ItemTool implements IDamageableItem{
    private Material reinforcement_material;
    public ItemWand(int id, Material material, String texture) {
        super(id, material);
        this.setMaxStackSize(1);
        this.setMaxDamage(192);
        this.setCreativeTab(CreativeModeTab.tabCombat);
    }
    @Override
    public int getNumComponentsForDurability() {
        return 3;
    }
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
            if (!(fraction_pulled < 0.25F)) {
                if (fraction_pulled > 1.0F) {
                    fraction_pulled = 1.0F;
                }

                if (this.itemID == Items.LavaWand.itemID) {
                    world.playSoundAtEntity(player, "mob.ghast.fireball", 1.0F, 1.0F);
                    world.spawnEntityInWorld(new EntityWandFireball(world, player));
//                        EntityArrow entity_arrow = new EntityArrow(world, player, fraction_pulled * 2.0F, Item.arrowAdamantium, item_stack.isItemEnchanted());
//                        world.spawnEntityInWorld(entity_arrow);
                    //System.out.println("Player tried to spawn Fireball of Wand.");
                }
                if(this.itemID == Items.FreezeWand.itemID){
                    world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                    world.spawnEntityInWorld(new EntityWandIceBall(world,player));
                    //System.out.println("Player tried to spawn Iceball of Wand.");
                }
                if(this.itemID == Items.ShockWand.itemID){
                    world.playSoundAtEntity(player, "ambient.weather.thunder", 1.0F, 1.0F);
                    world.spawnEntityInWorld(new EntityWandShockWave(world,player));
                    //System.out.println("Player tried to spawn Shockwave of Wand.");
                }

            }
            if(!player.isPlayerInCreative()) {
                player.tryDamageHeldItem(DamageSource.generic, 1);
            }
        }
    }

    @Override
    public float getBaseDamageVsEntity() {
        return 0;
    }

    @Override
    public float getBaseDecayRateForBreakingBlock(Block block) {
        return 0;
    }

    @Override
    public float getBaseDecayRateForAttackingEntity(ItemStack itemStack) {
        return 0;
    }
    @Override
    public String getToolType() {
        return "wand";
    }
    public boolean canBlock() {
        return false;
    }
}
