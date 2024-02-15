package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.achivements.AchievementExtend;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityItem.class)
public abstract class EntityItemMixin extends Entity{
    public EntityItemMixin(World par1World) {
        super(par1World);
    }
    private boolean isExploded;
    private boolean canBePickUpByPlayer;
    @Overwrite
    public EntityDamageResult attackEntityFrom(Damage damage) {
        EntityDamageResult result = super.attackEntityFrom(damage);
        if (result != null && !result.entityWasDestroyed()) {
            ItemStack item_stack = this.getEntityItem();
            if (item_stack == null) {
                Minecraft.setErrorMessage("attackEntityFrom: EntityItem had null item_stack");
                return null;
            } else {
                Item item = item_stack.getItem();
                if (item == null) {
                    Minecraft.setErrorMessage("attackEntityFrom: EntityItem had null item");
                    return null;
                } else if (item == Item.netherStar && damage.isExplosion()) {
                    return null;
                } else if (damage.isLavaDamage() && this.isHarmedByLava()) {
                    return this.destroyItem(damage.getSource()) ? result.setEntityWasDestroyed() : result.setEntityWasAffected();
                } else if (damage.isFireDamage() && this.getEntityItem().canDouseFire()) {
                    return this.destroyItem(damage.getSource()) ? result.setEntityWasDestroyed() : result.setEntityWasAffected();
                } else if (damage.getSource() == DamageSource.pepsin && !this.isHarmedByPepsin()) {
                    return null;
                } else if (damage.getSource() == DamageSource.acid && !this.isHarmedByAcid()) {
                    return null;
                } else {
                    this.setBeenAttacked();
                    if (item_stack.isItemStackDamageable()) {
                        float scaled_damage = damage.getAmount() * 20.0F * 5.0F;
                        if (item instanceof ItemArmor) {
                            scaled_damage *= (float)Item.plateIron.getMaxDamage(EnumQuality.average) / (float)Item.swordIron.getMaxDamage(EnumQuality.average);
                        } else if (!(item instanceof ItemTool)) {
                            scaled_damage = damage.getAmount();
                        }

                        if (scaled_damage < 1.0F) {
                            scaled_damage = 1.0F;
                        }

                        result.startTrackingHealth((float)item_stack.getRemainingDurability());
                        ItemDamageResult idr = item_stack.tryDamageItem(this.worldObj, Math.round(scaled_damage), false);
                        result.finishTrackingHealth((float)item_stack.getRemainingDurability());
                        if (idr != null && idr.itemWasDestroyed()) {
                            this.health = 0;
                        } else {
                            this.health = 5 * item_stack.getItemDamage() / item_stack.getMaxDamage();
                            if (this.health < 1) {
                                this.health = 1;
                            }
                        }
                    } else {
                        if (damage.isFireDamage() && item instanceof ItemFood) {
                            ItemFood item_food = (ItemFood)item;
                            if (item_food.getCookedItem() != null || item_food.getUncookedItem() != null) {
                                int xp_reward;
                                int xp_share;
                                if (item_food.getCookedItem() != null) {
                                    int x = this.getBlockPosX();
                                    xp_reward = this.getBlockPosY();
                                    xp_share = this.getBlockPosZ();

                                    for(int dx = -1; dx <= 1; ++dx) {
                                        for(int dz = -1; dz <= 1; ++dz) {
                                            Block block = this.worldObj.getBlock(x + dx, xp_reward, xp_share + dz);
                                            if (block == Block.fire) {
                                                this.worldObj.getAsWorldServer().addScheduledBlockOperation(EnumBlockOperation.try_extinguish_by_items, x + dx, xp_reward, xp_share + dz, (this.worldObj.getTotalWorldTime() / 10L + 1L) * 10L, false);
                                            }
                                        }
                                    }
                                }

                                this.cooking_progress += damage.getAmount() * 3.0F;
                                if (this.cooking_progress >= 100.0F) {
                                    ItemStack cooked_item_stack = item.getItemProducedWhenDestroyed(item_stack, damage.getSource());
                                    if (cooked_item_stack == null) {
                                        this.setDead();
                                        return result.setEntityWasDestroyed();
                                    }

                                    if (item instanceof ItemMeat) {
                                        this.playSound("imported.random.sizzle", 1.0F, 1.0F);
                                    }

                                    this.setEntityItemStack(cooked_item_stack);
                                    xp_reward = cooked_item_stack.getExperienceReward();

                                    while(xp_reward > 0) {
                                        xp_share = EntityExperienceOrb.getXPSplit(xp_reward);
                                        xp_reward -= xp_share;
                                        this.worldObj.spawnEntityInWorld(new EntityExperienceOrb(this.worldObj, this.posX, this.posY + 0.5, this.posZ + 0.5, xp_share));
                                    }
                                }

                                return result.setEntityWasAffected();
                            }
                        }

                        result.startTrackingHealth((float)this.health);
                        this.health = (int)((float)this.health - damage.getAmount());
                        result.finishTrackingHealth((float)this.health);
                    }

                    if (result.entityWasNegativelyAffected() && (damage.isPepsinDamage() || damage.isAcidDamage())) {
                        if (this.health <= 0) {
                            this.entityFX(damage.isAcidDamage() ? EnumEntityFX.smoke_and_steam_with_hiss : EnumEntityFX.steam_with_hiss);
                        } else {
                            this.entityFX(EnumEntityFX.item_vanish);
                        }
                    }

                    if (this.health <= 0) {
                        if (damage.isFireDamage()) {
                            this.entityFX(EnumEntityFX.smoke);
                        }

                        if (!this.getEntityItem().hasSignature() && this.getEntityItem().getItem().hasContainerItem()) {
                            Item container = this.getEntityItem().getItem().getContainerItem();
                            if (!container.isHarmedBy(damage.getSource())) {
                                this.convertItem(container);
                                return result;
                            }
                        }

                        this.setDead();
                        if (item_stack.hasSignatureThatHasBeenAddedToWorld(this.worldObj)) {
                            this.tryRemoveFromWorldUniques();
                        }

                        result.setEntityWasDestroyed();
                    }

                    return result;
                }
            }
        } else {
            return result;
        }
    }

    @Overwrite
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
        if (!par1EntityPlayer.isGhost() && !par1EntityPlayer.isZevimrgvInTournament()) {
            if (!(par1EntityPlayer.ridingEntity instanceof EntityHorse) || !(this.posY - par1EntityPlayer.getFootPosY() < -0.5)) {
                if (!this.worldObj.isRemote) {
                    boolean was_empty_handed_before = !par1EntityPlayer.hasHeldItem();
                    ItemStack var2 = this.getEntityItem();
                    int var3 = var2.stackSize;
                    if (this.canBePickedUpBy(par1EntityPlayer) && par1EntityPlayer.inventory.addItemStackToInventory(var2)) {
                        if (var2.itemID == Block.wood.blockID) {
                            par1EntityPlayer.triggerAchievement(AchievementList.mineWood);
                        }

                        if (var2.itemID == Item.leather.itemID) {
                            par1EntityPlayer.triggerAchievement(AchievementList.killCow);
                        }

                        if (var2.itemID == Item.diamond.itemID) {
                            par1EntityPlayer.triggerAchievement(AchievementList.diamonds);
                        }

                        if (var2.itemID == Item.emerald.itemID) {
                            par1EntityPlayer.triggerAchievement(AchievementList.emeralds);
                        }

                        if (var2.itemID == Item.blazeRod.itemID) {
                            par1EntityPlayer.triggerAchievement(AchievementList.blazeRod);
                        }

                        if (var2.itemID == Item.seeds.itemID || var2.itemID == Item.blueberries.itemID || var2.itemID == Item.wormRaw.itemID) {
                            par1EntityPlayer.triggerAchievement(AchievementList.seeds);
                        }

                        if (var2.itemID == Item.stick.itemID) {
                            par1EntityPlayer.triggerAchievement(AchievementList.stickPicker);
                        }

                        if (var2.itemID == Item.copperNugget.itemID || var2.itemID == Item.silverNugget.itemID || var2.itemID == Item.goldNugget.itemID || var2.itemID == Item.ironNugget.itemID || var2.itemID == Item.mithrilNugget.itemID || var2.itemID == Item.adamantiumNugget.itemID || var2.itemID == Items.tungstenNugget.itemID || var2.itemID == Items.nickelNugget.itemID) {
                            par1EntityPlayer.triggerAchievement(AchievementList.nuggets);
                        }

                        if (var2.itemID == Items.pieceCopper.itemID || var2.itemID == Items.pieceGold.itemID || var2.itemID == Items.pieceSilver.itemID || var2.itemID == Items.pieceIron.itemID) {
                            par1EntityPlayer.triggerAchievement(AchievementExtend.FragofMine);
                        }
                        if (var2.itemID == Items.pieceCopper.itemID || var2.itemID == Items.pieceGold.itemID || var2.itemID == Items.pieceSilver.itemID || var2.itemID == Items.pieceIron.itemID) {
                            par1EntityPlayer.triggerAchievement(AchievementExtend.FragofMine);
                        }
                        if (var2.itemID == Item.recordUnderworld.itemID || var2.itemID == Item.recordDescent.itemID || var2.itemID == Item.recordWanderer.itemID ||var2.itemID == Item.recordLegends.itemID){
                            par1EntityPlayer.triggerAchievement(AchievementExtend.SoundofUnder);
                        }
                        if (var2.itemID == Item.skull.itemID){
                            par1EntityPlayer.triggerAchievement(AchievementExtend.getWitherSkull);
                        }

                        if (var2.itemID == Item.wheat.itemID) {
                            this.worldObj.getWorldInfo().fullfillVillageCondition(1, (WorldServer)this.worldObj);
                        }

                        if (var2.itemID == Item.carrot.itemID) {
                            this.worldObj.getWorldInfo().fullfillVillageCondition(2, (WorldServer)this.worldObj);
                        }

                        if (var2.itemID == Item.potato.itemID) {
                            this.worldObj.getWorldInfo().fullfillVillageCondition(4, (WorldServer)this.worldObj);
                        }

                        if (var2.itemID == Item.onion.itemID) {
                            this.worldObj.getWorldInfo().fullfillVillageCondition(8, (WorldServer)this.worldObj);
                        }

                        this.playSound("random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                        par1EntityPlayer.onItemPickup(this, var3);
                        if (var2.stackSize <= 0) {
                            this.setDead();
                        }

                        if (was_empty_handed_before && par1EntityPlayer.hasHeldItem()) {
                            par1EntityPlayer.sendPacket(new Packet85SimpleSignal(EnumSignal.picked_up_held_item));
                        }
                    }
                }

            }
        }
    }
    @Shadow
    public boolean canBePickedUpBy(EntityLiving entity_living_base) {
        return false;
    }


    @Overwrite
    public void spentTickInWater() {
        BiomeBase biome = this.worldObj.getBiomeGenForCoords(this.getBlockPosX(), this.getBlockPosZ());
        Item item = this.getEntityItem().getItem();
        if (item instanceof ItemVessel) {
            ItemVessel vessel = (ItemVessel)item;
            if (vessel.contains(Material.lava)) {
                if (!this.worldObj.isRemote) {
                    this.entityFX(EnumEntityFX.steam_with_hiss);
                    this.convertItem(vessel.getPeerForContents(Material.stone));
                }

                return;
            }

            if (!this.worldObj.isRemote && !vessel.contains(Material.stone) && (biome == BiomeBase.river || biome == BiomeBase.desertRiver)) {
                this.convertItem(vessel.getPeerForContents(Material.water));
            }
            else if (!this.worldObj.isRemote && !vessel.contains(Material.stone) && (biome == BiomeBase.swampRiver || biome == BiomeBase.swampland)) {
                this.convertItem(vessel.getPeerForContents(Materials.dangerous_water));
            }
            else if (!this.worldObj.isRemote && !vessel.contains(Material.stone)){
                this.convertItem(vessel.getPeerForContents(Materials.unsafe_water));
            }
        } else if (this.onServer() && item.hasMaterial(Material.water, true)) {
            if (!this.isDead) {
                this.setDead();
            }
        } else if (this.onServer() && item.isDissolvedByWater() && !this.isDead && this.ticksExisted % 20 == 0) {
            this.attackEntityFrom(new Damage(DamageSource.melt, 1.0F));
            if (this.isDead) {
                this.entityFX(EnumEntityFX.item_vanish);
            }
        }

        super.spentTickInWater();
    }
    @Inject(method = "handleExplosion",
            cancellable = true,
            at = @At(value = "INVOKE",
                    shift = At.Shift.AFTER,
                    target = "Lnet/minecraft/EntityItem;calcExplosionForce(FD)F"))
    private void injectCancelExplosionCopy(CallbackInfoReturnable<Boolean> callback){
        if (this.isExploded) {
            this.setDead();
            this.tryRemoveFromWorldUniques();
            callback.setReturnValue(true);
            callback.cancel();
        }
    }
    @Redirect(method = "handleExplosion",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/EntityItem;tryRemoveFromWorldUniques()V"))
    private void injectUpdateExploded(EntityItem caller){
        this.isExploded = true;
        this.tryRemoveFromWorldUniques();
    }

    @Shadow
    public EntityItem applyExplosionMotion(Explosion explosion) {
        return null;
    }

    @Shadow
    protected abstract boolean canTriggerWalking();
    @Shadow
    protected void entityInit() {
    }
    @Shadow
    public ItemStack getEntityItem() {
        return null;
    }
    @Shadow
    private boolean destroyItem(DamageSource damage_source) {
        return false;
    }
    @Shadow
    public int age;
    @Shadow
    public int delayBeforeCanPickup;
    @Shadow
    private int health;
    @Shadow
    public float hoverStart;
    @Shadow
    public boolean dropped_by_player;
    @Shadow
    private float cooking_progress;

    @Shadow
    public void setEntityItemStack(ItemStack par1ItemStack) {
    }
    @Shadow
    public void tryRemoveFromWorldUniques() {
    }
    @Shadow
    public void convertItem(Item item) {
    }

    @Shadow
    protected void readEntityFromNBT(NBTTagCompound nbtTagCompound) {

    }

    @Shadow
    protected void writeEntityToNBT(NBTTagCompound nbtTagCompound) {

    }
}
