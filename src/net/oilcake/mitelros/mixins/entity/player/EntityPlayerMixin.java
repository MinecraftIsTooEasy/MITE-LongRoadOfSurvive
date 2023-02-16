package net.oilcake.mitelros.mixins.entity.player;

import net.minecraft.*;
import net.oilcake.mitelros.block.enchantreserver.EnchantReserverSlots;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityPlayer.class)
public class EntityPlayerMixin extends EntityLiving{
    public EntityPlayer entityPlayer;


//    private static int getWaterLimit(int level) {
//        return Math.max(Math.min(6 + level / 5 * 2, 20), 6);
//    }
//    public float getWaterLimit() {
//        return (float)getWaterLimit(this.getExperienceLevel());
//    }


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
    @Shadow
    public int getNutrition() {
        return this.foodStats.getNutrition();
    }
    @Overwrite
    public boolean isStarving() {
        return this.getNutrition() == 0 || this.getWater() == 0;
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
    public float getHealthLimit() {
        return 1.0F;
    }
    @Shadow
    @Final
    public static int getHighestPossibleLevel() {
        return 0;
    }
    @Shadow
    @Final
    private static int[] experience_for_level;
    @Shadow
    public final int getExperienceLevel() {
        return 1;
    }

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
    public void setHeldItemStack(ItemStack itemStack) {

    }

    @Shadow
    public ItemStack getHeldItemStack() {
        return null;
    }

    @Shadow
    public ItemStack getEquipmentInSlot(int i) {
        return null;
    }

    @Shadow
    public void setCurrentItemOrArmor(int i, ItemStack itemStack) {

    }

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

}
