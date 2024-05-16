package net.oilcake.mitelros.item;

import net.minecraft.*;
import net.oilcake.mitelros.item.potion.PotionExtend;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.spongepowered.asm.mixin.Overwrite;

import static net.oilcake.mitelros.item.Items.claybowlWaterSuspicious;
import static net.oilcake.mitelros.item.Items.claybowlWaterSwampland;

public class ItemKettle extends Item implements IDamageableItem {
    public final int itemUseDuration;
    public static final int drinkUnit = 3;
    public static final int douseUnit = 1;
    public Material vessel_material;
    public ItemKettle(int id, int volume, Material contents, Material vessel_material){
        super(id, Material.silk, "kettle");
        this.setAlwaysEdible();
        this.addMaterial(vessel_material, contents);
        this.itemUseDuration = 32;
        this.setMaxDamage(volume);
        this.setCreativeTab(CreativeModeTab.tabTools);
        this.vessel_material = vessel_material;
        if(this.contains(Material.leather)){
            this.setResourceLocation("leather_kettle");
        } else if (this.contains(Material.hardened_clay)) {
            this.setResourceLocation("hardened_clay_jug");
        }
    }
    public ItemKettle getPeer(Material vessel_material, Material contents) {
        if (vessel_material == Material.leather) {
            if (contents == Materials.unsafe_water) {
                return Items.leatherKettleSuspicious;
            }
            if (contents == Materials.dangerous_water) {
                return Items.leatherKettleSwampland;
            }
            if (contents == Materials.water) {
                return Items.leatherKettle;
            }
        }else if(vessel_material == Material.hardened_clay) {
            if (contents == Materials.unsafe_water) {
                return Items.hardenedClayJugSuspicious;
            }
            if (contents == Materials.dangerous_water) {
                return Items.hardenedClayJugSwampland;
            }
            if (contents == Materials.water) {
                return Items.hardenedClayJug;
            }
        }
        return null;
    }
    @Override
    public int getNumComponentsForDurability() {
        return 8;
    }

    @Override
    public int getRepairCost() {
        return 16;
    }
    public int getMaxDamage(){
        ItemStack stack = this.getItemStackForStatsIcon();
        return stack.getMaxDamage();
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 32;
    }
    public boolean isDrinkable(int item_subtype) {
        return true;
    }
    protected void onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
    }

    public float getCompostingValue() {
        return 0.0F;
    }
    public boolean contains(Material material) {
        return this.hasMaterial(material);
    }
    public void onItemUseFinish(ItemStack item_stack, World world, EntityPlayer player) {
        if (player.onServer()) {
            player.addWater(2);
            if(ExperimentalConfig.TagConfig.Realistic.ConfigValue){
                if (this.contains(Materials.dangerous_water)) {
                    double rand = Math.random();
                    player.addPotionEffect((new MobEffect(MobEffectList.poison.id, (int) (450 * (1 + rand)), 0)));
                    player.addPotionEffect((new MobEffect(PotionExtend.dehydration.id, (int) (160 * (1 + rand)), 0)));
                }
                if (this.contains(Materials.unsafe_water)) {
                    double rand = Math.random();
                    if(rand > 0.5){
                        player.addPotionEffect((new MobEffect(MobEffectList.poison.id, (int) (450 * (1 + rand)), 0)));
                    }
                    player.addPotionEffect((new MobEffect(PotionExtend.dehydration.id, (int) (160 * (1 + rand)), 0)));
                }
            }else {
                if (this.contains(Materials.dangerous_water)) {
                    double rand = Math.random();
                    if(rand > 0.2){
                        player.addPotionEffect((new MobEffect(MobEffectList.poison.id, 450, 0)));
                    }
                    player.addPotionEffect((new MobEffect(PotionExtend.dehydration.id, (int) (160 * (1 + rand)), 0)));
                }
                if (this.contains(Materials.unsafe_water)) {
                    double rand = Math.random();
                    if(rand > 0.8){
                        player.addPotionEffect((new MobEffect(MobEffectList.poison.id, 450, 0)));
                    }
                    player.addPotionEffect((new MobEffect(PotionExtend.dehydration.id, (int) (160 * (1 + rand)), 0)));
                }
            }
            player.getHeldItemStack().tryDamageItem(world, drinkUnit,true);
            this.onEaten(item_stack, world, player);
        }
    }
    public EnumItemInUseAction getItemInUseAction(ItemStack item_stack, EntityPlayer player)
    {
        return (item_stack.getItemDamage() + drinkUnit <= item_stack.getMaxDamage()) ? EnumItemInUseAction.DRINK : null;
    }
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        RaycastCollision rc = player.getSelectedObject(partial_tick, true);
        BiomeBase biome = player.worldObj.getBiomeGenForCoords(player.getBlockPosX(), player.getBlockPosZ());
        if (rc != null && rc.isBlock()) {
            ItemStack item_stack = player.getHeldItemStack();

            if (item_stack.getItemDamage() > 0) {
                if (rc.getBlockHitMaterial() == Material.water || rc.getNeighborOfBlockHitMaterial() == Material.water) {
                    if (player.onServer() && (biome == BiomeBase.swampRiver || biome == BiomeBase.swampland)) {
                        player.convertOneOfHeldItem(new ItemStack(this.getPeer(this.vessel_material, Materials.dangerous_water)));
                    } else if(player.onServer() && (biome == BiomeBase.river || biome == BiomeBase.desertRiver)){
                        player.convertOneOfHeldItem(new ItemStack(this.getPeer(this.vessel_material, Materials.water)));
                    } else if(player.onServer()){
                        player.convertOneOfHeldItem(new ItemStack(this.getPeer(this.vessel_material, Materials.unsafe_water)));
                    }
                    return true;
                }
            } else {
                if (rc.getNeighborOfBlockHit() == Block.fire && item_stack.getItemDamage() + douseUnit < item_stack.getMaxDamage()) {
                    if (player.onServer()) {
                        rc.world.douseFire(rc.neighbor_block_x, rc.neighbor_block_y, rc.neighbor_block_z, (Entity)null);
                        player.getHeldItemStack().tryDamageItem(player.worldObj, douseUnit,true);
                    }

                    return true;
                }

                if (this.contains(Material.water) || this.contains(Materials.unsafe_water) || this.contains(Materials.dangerous_water)) {
                    Block block = rc.getBlockHit();
                    int x = rc.block_hit_x;
                    int y = rc.block_hit_y;
                    int z = rc.block_hit_z;
                    EnumFace face_hit = rc.face_hit;
                    if (block instanceof BlockCrops || block instanceof BlockStem || block == Block.mushroomBrown) {
                        --y;
                        block = rc.world.getBlock(x, y, z);
                        face_hit = EnumFace.TOP;
                    }
                    if (block == Block.tilledField && face_hit == EnumFace.TOP && BlockSoil.fertilize(rc.world, x, y, z, player.getHeldItemStack(), player)) {
                        if (player.onServer() && !player.inCreativeMode()) {
                            player.getHeldItemStack().tryDamageItem(player.worldObj, douseUnit,true);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
