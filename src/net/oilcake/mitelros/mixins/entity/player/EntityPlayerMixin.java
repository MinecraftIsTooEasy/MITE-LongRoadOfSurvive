package net.oilcake.mitelros.mixins.entity.player;

import net.oilcake.mitelros.block.enchantreserver.EnchantReserverSlots;
import net.oilcake.mitelros.item.Items;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityPlayer.class)
public class EntityPlayerMixin extends EntityLiving{

//    public static int getWaterLimit(int level) {
//        return Math.max(Math.min(6 + level / 5 * 2, 20), 6);
//    }
//
//    public float getWaterLimit() {
//        return (float)getWaterLimit(this.getExperienceLevel());
//    }
    @Shadow
    public final int getExperienceLevel() {
        return 1;
    }

    @Shadow
    protected FoodMetaData foodStats;
    public FoodMetaData getFoodStats(){
        return foodStats;
    }

    public EntityPlayerMixin(World par1World) {
        super(par1World);
    }

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
