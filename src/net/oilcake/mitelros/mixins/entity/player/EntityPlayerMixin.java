package net.oilcake.mitelros.mixins.entity.player;

import net.minecraft.*;
import net.oilcake.mitelros.achivements.AchievementExtend;
import net.oilcake.mitelros.block.enchantreserver.EnchantReserverSlots;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.enchantment.Enchantments;
import net.oilcake.mitelros.util.DamageSourceExtend;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;

import static net.xiaoyu233.fml.util.ReflectHelper.dyCast;

@Mixin(EntityPlayer.class)
public class EntityPlayerMixin extends EntityLiving{

//    private static int getWaterLimit(int level) {
//        return Math.max(Math.min(6 + level / 5 * 2, 20), 6);
//    }
//    public float getWaterLimit() {
//        return (float)getWaterLimit(this.getExperienceLevel());
//    }

    @Overwrite
    public void attackTargetEntityWithCurrentItem(Entity target) {
        if (!this.isImmuneByGrace()) {
            if (target.canAttackWithItem()) {
                boolean critical = this.willDeliverCriticalStrike();
                float damage = this.calcRawMeleeDamageVs(target, critical, this.isSuspendedInLiquid());
                if (damage <= 0.0F) {
                    return;
                }
                ItemStack heldItemStack = this.getHeldItemStack();
                if(EnchantmentManager.hasEnchantment(heldItemStack, Enchantments.enchantmentDestroying)){
                    int destorying = EnchantmentManager.getEnchantmentLevel(Enchantments.enchantmentDestroying, heldItemStack);
                    target.worldObj.createExplosionC(target, target.posX, target.posY, target.posZ, destorying, destorying);
                    //System.out.println("判断为enchantmentDestorying player");
                    //target.setFire(120);
                }

                int knockback = 0;
                if (target instanceof EntityLiving) {
                    knockback += EnchantmentManager.getKnockbackModifier(this, (EntityLiving)target);
                }

                if (this.isSprinting()) {
                    ++knockback;
                }

                boolean was_set_on_fire = false;
                int fire_aspect = EnchantmentManager.getFireAspectModifier(this);
                if (target instanceof EntityLiving && fire_aspect > 0 && !target.isBurning()) {
                    was_set_on_fire = true;
                    target.setFire(1);
                }

                if (this.onServer() && target instanceof EntityLiving) {
                    EntityLiving entity_living_base = (EntityLiving)target;
                    ItemStack item_stack_to_drop = entity_living_base.getHeldItemStack();
                    if (item_stack_to_drop != null && this.rand.nextFloat() < EnchantmentManager.getEnchantmentLevelFraction(Enchantment.disarming, this.getHeldItemStack()) && entity_living_base instanceof EntityInsentient) {
                        EntityInsentient entity_living = (EntityInsentient)entity_living_base;
                        entity_living.dropItemStack(item_stack_to_drop, entity_living.height / 2.0F);
                        entity_living.clearMatchingEquipmentSlot(item_stack_to_drop);
                        entity_living.ticks_disarmed = 40;
                    }
                }

                EntityDamageResult result = target.attackEntityFrom(new Damage(DamageSource.causePlayerDamage(dyCast(this)).setFireAspectC(fire_aspect > 0), damage));
                boolean target_was_harmed = result != null && result.entityWasNegativelyAffected();
                target.onMeleeAttacked(this, result);
                if (target_was_harmed) {
                    if (target instanceof EntityLiving) {
                        int stunning = EnchantmentManager.getStunModifier(this, (EntityLiving)target);
                        if ((double)stunning > Math.random() * 10.0) {
                            ((EntityLiving)target).addPotionEffect(new MobEffect(MobEffectList.moveSlowdown.id, stunning * 50, stunning * 5));
                        }

                        this.heal((float)EnchantmentManager.getVampiricTransfer(this, (EntityLiving)target, damage), EnumEntityFX.vampiric_gain);
                    }

                    if (knockback > 0) {
                        target.addVelocity((double)(-MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F) * (float)knockback * 0.5F), 0.1, (double)(MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F) * (float)knockback * 0.5F));
                        this.motionX *= 0.6;
                        this.motionZ *= 0.6;
                        this.setSprinting(false);
                    }

                    if (critical) {
                        this.onCriticalHit(target);
                    }

                    if (target instanceof EntityLiving && EnchantmentWeaponDamage.getDamageModifiers(this.getHeldItemStack(), (EntityLiving)target) > 0.0F) {
                        this.onEnchantmentCritical(target);
                    }

                    if (damage >= 18.0F) {
                        this.triggerAchievement(AchievementList.overkill);
                    }

                    this.setLastAttackTarget(target);
                    if (target instanceof EntityLiving) {
                        if (this.worldObj.isRemote) {
                            System.out.println("EntityPlayer.attackTargetEntityWithCurrentItem() is calling EnchantmentThorns.func_92096_a() on client");
                            Minecraft.temp_debug = "player";
                        }

                        EnchantmentThorns.func_92096_a(this, (EntityLiving)target, this.rand);
                    }
                }

                ItemStack held_item_stack = this.getHeldItemStack();
                Object var10 = target;
                if (target instanceof EntityComplexPart) {
                    IComplex var11 = ((EntityComplexPart)target).entityDragonObj;
                    if (var11 != null && var11 instanceof EntityLiving) {
                        var10 = (EntityLiving)var11;
                    }
                }

                if (target_was_harmed && held_item_stack != null && var10 instanceof EntityLiving) {
                    held_item_stack.hitEntity((EntityLiving)var10, dyCast(this));
                }

                if (target instanceof EntityLiving) {
                    this.addStat(StatisticList.damageDealtStat, Math.round(damage * 10.0F));
                    if (fire_aspect > 0 && target_was_harmed) {
                        target.setFire(fire_aspect * 4);
                    } else if (was_set_on_fire) {
                        target.extinguish();
                    }
                }

                if (this.onServer()) {
                    this.addHungerServerSide(0.3F * EnchantmentManager.getEnduranceModifier(this));
                }
            }

        }
    }

    @Inject(method = "onDeath", at = @At(value = "INVOKE"))
    public void onDeath(DamageSource par1DamageSource, CallbackInfo callbackInfo) {
        if (!this.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {
            this.inventory.vanishingItems();
        }
    }

    public InventoryEnderChest getTheInventoryEnderChest(){
        return this.theInventoryEnderChest;
    }
    public int getWater() {
        return this.getFoodStats().getWater();
    }
    public int addWater(int water){
        return this.getFoodStats().addWater(water);
    }

    public void decreaseWaterServerSide(float hungerWater)
    {
        if (!this.capabilities.isCreativeMode && !this.capabilities.disableDamage)
        {
            this.foodStats.decreaseWaterServerSide(hungerWater);
            //System.out.println("发送buff数据 player");
        }
    }
    @Overwrite
    public boolean isStarving() {
        return this.getNutrition() == 0;
    }

    public boolean DuringDehydration() {
        return this.getWater() == 0;
    }
    @Overwrite
    public boolean hasFoodEnergy() {
        return this.getSatiation() + this.getNutrition() != 0 && this.getWater() != 0;
    }

    public boolean InFreeze(){
        BiomeBase biome = this.worldObj.getBiomeGenForCoords(this.getBlockPosX(), this.getBlockPosZ());
        ItemStack wearingItemStack = this.getCuirass();
        if (biome.temperature <= 0.16F){
            if((this.getHelmet()!= null && this.getHelmet().itemID == Items.WolfHelmet.itemID &&
                    this.getCuirass()!= null && this.getCuirass().itemID == Items.WolfChestplate.itemID &&
                    this.getLeggings()!= null && this.getLeggings().itemID == Items.WolfLeggings.itemID &&
                    this.getBoots()!= null && this.getBoots().itemID == Items.WolfBoots.itemID )|| EnchantmentManager.hasEnchantment(wearingItemStack, Enchantments.enchantmentCallofNether) ){
                return false;
            }
            return true;
        }
        return false;
    }

    private int FreezingCooldown;
    private int FreezingWarning;
    private int reduce_weight;
    private float dry_resist;
    public int getReduce_weight(){
        if((this.getHelmet()!= null && this.getHelmet().itemID == Items.WolfHelmet.itemID &&
                this.getCuirass()!= null && this.getCuirass().itemID == Items.WolfChestplate.itemID &&
                this.getLeggings()!= null && this.getLeggings().itemID == Items.WolfLeggings.itemID &&
                this.getBoots()!= null && this.getBoots().itemID == Items.WolfBoots.itemID )){
            return 8;
        }else if((this.getHelmet()!= null && this.getHelmet().itemID == Item.helmetLeather.itemID &&
                this.getCuirass()!= null && this.getCuirass().itemID == Item.plateLeather.itemID &&
                this.getLeggings()!= null && this.getLeggings().itemID == Item.legsLeather.itemID &&
                this.getBoots()!= null && this.getBoots().itemID == Item.bootsLeather.itemID )){
            return 3;
        }else if((this.getHelmet()!= null && this.getCuirass()!= null && this.getLeggings()!= null && this.getBoots()!= null)){
            return 1;
        }else {
            return 0;
        }
    }
    @Inject(method = "onLivingUpdate",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/EntityLiving;onLivingUpdate()V",
                    shift = At.Shift.AFTER))
    private void injectTick(CallbackInfo c){
        if (!this.worldObj.isRemote) {
            BiomeBase biome = this.worldObj.getBiomeGenForCoords(this.getBlockPosX(), this.getBlockPosZ());
            dry_resist += 1.0f + biome.getFloatTemperature();
            if(dry_resist > 14400) {
                this.getFoodStats().addWater(-1);
                dry_resist = 0;
            }
            int freezelevel = Math.max(((FreezingCooldown - (12000 * getReduce_weight())) / 12000), 0);
            if(this.FreezingCooldown > 12000 * getReduce_weight()){
                if(freezelevel >= 1){
                    if (freezelevel >= 4) {
                        ++FreezingWarning;
                        this.triggerAchievement(AchievementExtend.hypothermia);
                    }
                    if (FreezingWarning > 500) {
                        this.attackEntityFrom(new Damage(DamageSourceExtend.freeze, 1.0F));
                        FreezingWarning = 0;
                    }
                    this.addPotionEffect(new MobEffect(MobEffectList.moveSlowdown.id, FreezingCooldown, this.isInRain() ? freezelevel : freezelevel - 1));
                    this.addPotionEffect(new MobEffect(MobEffectList.digSlowdown.id, FreezingCooldown, this.isInRain() ? freezelevel : freezelevel - 1));
                }
            }
            if(this.InFreeze()){
                ++FreezingCooldown;
            }
            else{
                if(FreezingCooldown > 0){
                    --FreezingCooldown;
                }
            }
        }
        if(Feast_trigger_sorbet &&Feast_trigger_cereal &&Feast_trigger_chestnut_soup &&Feast_trigger_chicken_soup &&Feast_trigger_beef_stew &&Feast_trigger_cream_mushroom_soup &&Feast_trigger_cream_vegetable_soup &&Feast_trigger_ice_cream &&Feast_trigger_lemonade &&Feast_trigger_mashed_potatoes &&Feast_trigger_porkchop_stew &&Feast_trigger_salad &&Feast_trigger_pumpkin_soup &&Feast_trigger_porridge &&Feast_trigger_mushroom_soup &&Feast_trigger_vegetable_soup){
            this.triggerAchievement(AchievementExtend.feast);
        }
    }
    public int getFreezingCooldown(){
        return FreezingCooldown;
    }

    public float getCurrentBiomeTemperature(){
        BiomeBase biome = this.worldObj.getBiomeGenForCoords(this.getBlockPosX(), this.getBlockPosZ());
        return biome.getFloatTemperature();
    }


//    @Overwrite
//    public void addExperience(int amount, boolean suppress_healing, boolean suppress_sound) {
//        suppress_healing = true;
//        if (amount < 0) {
//            if (!suppress_sound) {
//                this.worldObj.playSoundAtEntity(this, "imported.random.level_drain");
//            }
//        } else if (amount > 0) {
//            this.addScore(amount);
//            if (!suppress_sound) {
//                this.worldObj.playSoundAtEntity(this, "random.orb", 0.1F, 0.5F * ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.8F));
//            }
//        }
//
//        float health_limit_before = this.getHealthLimit();
//        int level_before = this.getExperienceLevel();
//        this.experience += amount;
//        if (this.experience < getExperienceRequired(-40)) {
//            this.experience = getExperienceRequired(-40);
//        }
//
//        int level_after = this.getExperienceLevel();
//        int level_change = level_after - level_before;
//        if (level_change < 0) {
//            this.setHealth(this.getHealth());
//            this.foodStats.setSatiation(this.foodStats.getSatiation(), true);
//            this.foodStats.setNutrition(this.foodStats.getNutrition(), true);
//        } else if (level_change > 0) {
//            if (this.getHealthLimit() > health_limit_before && (float)this.field_82249_h < (float)this.ticksExisted - 100.0F) {
//                float volume = level_after > 30 ? 1.0F : (float)level_after / 30.0F;
//                if (!suppress_sound) {
//                    this.worldObj.playSoundAtEntity(this, "random.levelup", volume * 0.75F, 1.0F);
//                }
//
//                this.field_82249_h = this.ticksExisted;
//            }
//
//            if (!suppress_healing) {
//                this.setHealth(this.getHealth() + this.getHealthLimit() - health_limit_before);
//            }
//        }
//
//        if (level_change != 0 && !this.worldObj.isRemote) {
//            MinecraftServer.a(MinecraftServer.F()).sendPlayerInfoToAllPlayers(true);
//        }
//
//        if (entityPlayer instanceof ServerPlayer && DedicatedServer.tournament_type == EnumTournamentType.score) {
//            DedicatedServer.getOrCreateTournamentStanding(entityPlayer).experience = this.experience;
//            DedicatedServer.updateTournamentScoreOnClient(entityPlayer, true);
//        }
//    }
    public FoodMetaData getFoodStats(){
    return foodStats;
}
    public EntityPlayerMixin(World par1World) {
        super(par1World);
    }

    public void displayGUIEnchantReserver(int x, int y, int z, EnchantReserverSlots slots) {
    }
    @Overwrite
    public EnumQuality getMaxCraftingQuality(float unadjusted_crafting_difficulty_to_produce, Item item, int[] applicable_skillsets) {
        if (!this.worldObj.areSkillsEnabled()) {
            applicable_skillsets = null;
        }

        if (this.experience <= 0) {
            return this.getMinCraftingQuality(item, applicable_skillsets);
        } else if (applicable_skillsets != null && !this.hasAnyOfTheseSkillsets(applicable_skillsets)) {
            return this.getMinCraftingQuality(item, applicable_skillsets);
        } else {
            if (item.getLowestCraftingDifficultyToProduce() == Float.MAX_VALUE) {
                //Minecraft.setErrorMessage("getMaxCraftingQuality: item has no recipes! " + item.getItemDisplayName((ItemStack)null));
            }

            for(int i = item.getMaxQuality().ordinal(); i > EnumQuality.average.ordinal(); --i) {
                if (this.getCraftingExperienceCost(CraftingResult.getQualityAdjustedDifficulty(unadjusted_crafting_difficulty_to_produce, EnumQuality.values()[i])) <= this.experience) {
                    return EnumQuality.values()[i];
                }
            }

            return this.getMinCraftingQuality(item, applicable_skillsets);
        }
    }

    @Overwrite
    private void checkForArmorAchievements() {
        boolean wearing_leather = false;
        boolean wearing_full_suit_plate = true;
        boolean wearing_full_suit_adamantium_plate = true;
        boolean wearing_full_suit_wolf_fur = true;

        for(int i = 0; i < 4; ++i) {
            if (this.inventory.armorInventory[i] != null && this.inventory.armorInventory[i].getItem() instanceof ItemArmor) {
                ItemArmor armor = (ItemArmor)this.inventory.armorInventory[i].getItem();
                Material material = armor.getArmorMaterial();
                if (material == Material.leather) {
                    wearing_leather = true;
                }

                if (material != Material.copper && material != Material.silver && material != Material.gold && material != Material.iron && material != Material.mithril && material != Material.adamantium && material != Material.ancient_metal) {
                    wearing_full_suit_plate = false;
                }

                if (material != Material.adamantium) {
                    wearing_full_suit_adamantium_plate = false;
                }

                if (material != Materials.wolf_fur) {
                    wearing_full_suit_wolf_fur = false;
                }

            } else {
                wearing_full_suit_plate = false;
                wearing_full_suit_adamantium_plate = false;
                wearing_full_suit_wolf_fur = false;
            }
        }

        if (wearing_leather) {
            this.triggerAchievement(AchievementList.wearLeather);
        }

        if (wearing_full_suit_plate) {
            this.triggerAchievement(AchievementList.wearAllPlateArmor);
        }

        if (wearing_full_suit_adamantium_plate) {
            this.triggerAchievement(AchievementList.wearAllAdamantiumPlateArmor);
        }

        if (wearing_full_suit_wolf_fur) {
            this.triggerAchievement(AchievementExtend.BravetheCold);
        }

    }

    @Overwrite
    public EntityDamageResult attackEntityFrom(Damage damage) {
        if(this.getHelmet()!= null && this.getHelmet().itemID == Items.nickelHelmet.itemID &&
            this.getCuirass()!= null && this.getCuirass().itemID == Items.nickelChestplate.itemID &&
                this.getLeggings()!= null && this.getLeggings().itemID == Items.nickelLeggings.itemID &&
                    this.getBoots()!= null && this.getBoots().itemID == Items.nickelBoots.itemID &&
                        damage.getResponsibleEntityC() instanceof EntityGelatinousCube ){
            return null;
        }
        if (this.ticksExisted < 1000 && Damage.wasCausedByPlayer(damage) && this.isWithinTournamentSafeZone()) {
            return null;
        } else if (this.capabilities.disableDamage && !damage.canHarmInCreative()) {
            return null;
        } else {
            if (this.inBed()) {
                this.wakeUpPlayer(true, damage.getResponsibleEntityC());
            }
            if (damage.isExplosion()) {
                damage.scaleAmount(1.5F);
            }

            EntityDamageResult result = super.attackEntityFrom(damage);
            if (result != null) {
            }

            return result;
        }
    }
    @Shadow
    public int getNutrition() {return 1;}
    @Shadow
    public int getSatiation() {return 1;}
    @Shadow
    protected FoodMetaData foodStats;
    @Shadow
    public ItemStack[] getWornItems() {
        return new ItemStack[0];
    }
    @Shadow
    public boolean isWearing(ItemStack itemStack) {
        return false;
    }
    @Shadow
    public boolean setWornItem(int i, ItemStack itemStack) {
        return false;
    }
    @Shadow
    public void setHeldItemStack(ItemStack itemStack) {}
    @Shadow
    public ItemStack getHeldItemStack() {
        return null;
    }
    @Shadow
    public ItemStack getEquipmentInSlot(int i) {
        return null;
    }
    @Shadow
    public void setCurrentItemOrArmor(int i, ItemStack itemStack) {}
    @Shadow
    public ItemStack[] getInventory() {
        return new ItemStack[0];
    }
    @Shadow
    public PlayerAbilities capabilities;
    @Shadow
    public void wakeUpPlayer(boolean get_out_of_bed, Entity entity_to_look_at) {}
    @Shadow
    public int experience;
    @Shadow
    public EnumQuality getMinCraftingQuality(Item item, int[] applicable_skillsets) {
        return null;
    }
    @Shadow
    public boolean hasAnyOfTheseSkillsets(int[] skillsets) {
        return false;
    }
    @Shadow
    public int getCraftingExperienceCost(float quality_adjusted_crafting_difficulty) {
        return 1;
    }
    @Shadow
    public void setSizeNormal() {}
    @Shadow
    public void setSizeProne() {}
    @Shadow
    public EntityItem dropPlayerItemWithRandomChoice(ItemStack par1ItemStack, boolean par2) {return null;}
    @Shadow
    @Final
    protected String username;
    @Shadow
    public PlayerInventory inventory;
    @Shadow
    public void addStat(Statistic par1StatBase, int par2) {}
    @Shadow
    private InventoryEnderChest theInventoryEnderChest;
    @Shadow
    public void setScore(int par1) {}
    @Shadow
    public boolean isImmuneByGrace() {
        return false;
    }
    @Shadow
    public boolean willDeliverCriticalStrike() {return false;}
    @Shadow
    public float calcRawMeleeDamageVs(Entity target, boolean critical, boolean suspended_in_liquid) {
        return 0;
    }
    @Shadow
    public void onCriticalHit(Entity par1Entity) {}
    @Shadow
    public void onEnchantmentCritical(Entity par1Entity) {}
    @Shadow
    public void addHungerServerSide(float hunger) {}
    @Shadow
    public void triggerAchievement(Statistic par1StatBase) {}

    //try to trigger Achievement - Feast
    public boolean Feast_trigger_salad = false;
    public boolean Feast_trigger_porridge = false;
    public boolean Feast_trigger_beef_stew = false;
    public boolean Feast_trigger_cereal = false;
    public boolean Feast_trigger_chicken_soup = false;
    public boolean Feast_trigger_mushroom_soup = false;
    public boolean Feast_trigger_cream_mushroom_soup = false;
    public boolean Feast_trigger_vegetable_soup = false;
    public boolean Feast_trigger_cream_vegetable_soup = false;
    public boolean Feast_trigger_ice_cream = false;
    public boolean Feast_trigger_chestnut_soup = false;
    public boolean Feast_trigger_lemonade = false;
    public boolean Feast_trigger_mashed_potatoes = false;
    public boolean Feast_trigger_porkchop_stew = false;
    public boolean Feast_trigger_pumpkin_soup = false;
    public boolean Feast_trigger_sorbet = false;


}
