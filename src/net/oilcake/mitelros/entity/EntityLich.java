package net.oilcake.mitelros.entity;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;

import java.util.ArrayList;
import java.util.List;

public class EntityLich extends EntityBoneLord implements IBossbarEntity {
    private PathfinderGoalAvoidPlayer aiAvoidPlayerStrategic = new PathfinderGoalAvoidPlayer(this, EntityPlayer.class, 6.0F, 1.1, 1.4);
    private PathfinderGoalAvoidPlayer aiAvoidPlayerPanic = new PathfinderGoalAvoidPlayer(this, EntityPlayer.class, 32.0F, 1.3, 1.5);
    private PathfinderGoalMeleeAttack aiAttackOnCollide = new PathfinderGoalMeleeAttack(this, EntityPlayer.class, 1.2, false);
    private int max_num_evasions;
    private int num_evasions;
    private int spawnCounter;
    ItemStack stowed_item_stack;
    private boolean attack_mode = true;
    public void addRandomWeapon() {
        List items = new ArrayList();
        items.add(new RandomItemListEntry(Item.swordGold, 2));
        this.stowed_item_stack = (new ItemStack(Items.ShockWand)).randomizeForMob(this, true);
        RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, items);
        this.setHeldItemStack((new ItemStack(entry.item)).randomizeForMob(this, true));
    }
    public EntityLich(World par1World) {
        super(par1World);
        if (par1World != null && this.onServer()) {
            this.max_num_evasions = this.num_evasions = 6;
        }
    }

    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setByte("max_num_evasions", (byte)this.max_num_evasions);
        par1NBTTagCompound.setByte("num_evasions", (byte)this.num_evasions);
    }

    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.max_num_evasions = par1NBTTagCompound.getByte("max_num_evasions");
        this.num_evasions = par1NBTTagCompound.getByte("num_evasions");
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.setEntityAttribute(GenericAttributes.followRange, 128.0);
        this.setEntityAttribute(GenericAttributes.movementSpeed, 0.27000001072883606);
        this.setEntityAttribute(GenericAttributes.attackDamage, 9.0);
        this.setEntityAttribute(GenericAttributes.maxHealth, 75.0);
    }
    protected void addRandomEquipment() {
        this.addRandomWeapon();
        this.setBoots((new ItemStack(Items.BootsAncientmetalsacred)).randomizeForMob(this, true));
        this.setLeggings((new ItemStack(Items.LeggingsAncientmetalsacred)).randomizeForMob(this, true));
        this.setCuirass((new ItemStack(Items.ChestplateAncientmetalsacred)).randomizeForMob(this, true));
        this.setHelmet((new ItemStack(Items.HelmetAncientmetalsacred)).randomizeForMob(this, true));
    }

    public void onUpdate(){
        super.onUpdate();
        if(!getWorld().isRemote){
            spawnCounter++;
            if (spawnCounter > 300 && !attack_mode) {
                if(this.getTarget()!=null){
                    this.worldObj.playSoundEffect(this.getTarget().posX + 0.5, this.getTarget().posY + 0.5, this.getTarget().posZ + 0.5, "ambient.weather.thunder", 50.0F + this.rand.nextFloat(), 0.8F + this.rand.nextFloat() * 0.2F);
                    this.worldObj.playSoundEffect(this.getTarget().posX, this.getTarget().posY, this.getTarget().posZ, "random.explode", 2.0F, 0.5F + this.rand.nextFloat() * 0.2F);
                    this.worldObj.spawnParticle(EnumParticle.witchMagic, this.posX + (this.rand.nextDouble() - 0.5) * (double) this.width, this.posY + this.rand.nextDouble() * (double) this.height, this.posZ + (this.rand.nextDouble() - 0.5) * (double) this.width, 0.0, 0.0, 0.0);
                    this.getTarget().attackEntityFrom(new Damage(DamageSource.divine_lightning, 5));
                }
                EntityLichShadow guardian = new EntityLichShadow(worldObj);
                guardian.setPosition(posX + this.rand.nextInt(8) - this.rand.nextInt(8), posY, posZ - this.rand.nextInt(8) + this.rand.nextInt(8));
                guardian.refreshDespawnCounter(-9600);
                worldObj.spawnEntityInWorld(guardian);
                guardian.onSpawnWithEgg(null);
                guardian.entityFX(EnumEntityFX.summoned);
                spawnCounter = 0;
            }
        }
    }
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.onServer() && this.getHealth() > 0.0F) {
            int ticks_existed_with_offset = this.getTicksExistedWithOffset();
            if (this.num_evasions < this.max_num_evasions && ticks_existed_with_offset % 600 == 0) {
                ++this.num_evasions;
            }

            if (this.hasPath() && (this.getTarget() != null || this.fleeing) && ticks_existed_with_offset % 10 == 0 && this.rand.nextInt(3) == 0) {
                PathEntity path = this.getPathToEntity();
                if (!path.isFinished()) {
                    int n = path.getNumRemainingPathPoints();
                    if (n > 1) {
                        int path_index_advancement = MathHelper.clamp_int(this.rand.nextInt(n), 1, 4);
                        PathPoint path_point = path.getPathPointFromCurrentIndex(path_index_advancement);
                        if ((double)path_point.distanceSqTo(this) > 3.0 && this.tryTeleportTo((double)path_point.xCoord + 0.5, (double)path_point.yCoord, (double)path_point.zCoord + 0.5)) {
                            path.setCurrentPathIndex(path.getCurrentPathIndex() + path_index_advancement - 1);
                        }
                    }
                }
            }
        }
        if (this.stowed_item_stack != null && (this.getHeldItemStack() == null || this.getTicksExistedWithOffset() % 10 == 0)) {
            if (this.getHeldItemStack() == null) {
                this.swapHeldItemStackWithStowed();
            } else {
                Entity target = this.getTarget();
                if (target != null && this.canSeeTarget(true)) {
                    double distance = (double)this.getDistanceToEntity(target);
                    if (this.getHeldItemStack().itemID == Items.ShockWand.itemID) {
                        if (distance < 3.0) {
                            if(this.getHealth() >= 20.0F){
                                this.swapHeldItemStackWithStowed();
                                attack_mode = true;
                                this.tasks.removeTask(aiAvoidPlayerStrategic);
                                this.tasks.addTask(3,aiAttackOnCollide);
                            }
                        }
                    } else if (distance > 3.0) {
                        this.tasks.removeTask(aiAttackOnCollide);
                        if(this.getHealth() < 20.0F){
                            this.tasks.addTask(3,aiAvoidPlayerPanic);
                        }else{
                            this.tasks.addTask(3,aiAvoidPlayerStrategic);
                        }
                        this.swapHeldItemStackWithStowed();
                        attack_mode = false;
                    }
                }
            }
        }
    }
    public boolean tryTeleportTo(double pos_x, double pos_y, double pos_z) {
        if (!this.isDead && !(this.getHealth() <= 0.0F)) {
            int x = MathHelper.floor_double(pos_x);
            int y = MathHelper.floor_double(pos_y);
            int z = MathHelper.floor_double(pos_z);
            if (y >= 1 && this.worldObj.blockExists(x, y, z)) {
                while(true) {
                    --y;
                    if (this.worldObj.isBlockSolid(x, y, z)) {
                        ++y;
                        if (!this.worldObj.isBlockSolid(x, y, z) && !this.worldObj.isLiquidBlock(x, y, z)) {
                            double delta_pos_x = pos_x - this.posX;
                            double delta_pos_y = pos_y - this.posY;
                            double delta_pos_z = pos_z - this.posZ;
                            AxisAlignedBB bb = this.boundingBox.translateCopy(delta_pos_x, delta_pos_y, delta_pos_z);
                            if (this.worldObj.getCollidingBoundingBoxes(this, bb).isEmpty() && !this.worldObj.isAnyLiquid(bb)) {
                                World var10000 = this.worldObj;
                                double distance = (double)World.getDistanceFromDeltas(delta_pos_x, delta_pos_y, delta_pos_z);
                                this.worldObj.blockFX(EnumBlockFX.particle_trail, x, y, z, (new SignalData()).setByte(EnumParticle.runegate.ordinal()).setShort((int)(16.0 * distance)).setApproxPosition((double)MathHelper.floor_double(this.posX), (double)MathHelper.floor_double(this.posY), (double)MathHelper.floor_double(this.posZ)));
                                this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "mob.endermen.portal", 1.0F, 1.0F);
                                this.setPosition(pos_x, pos_y, pos_z);
                                this.send_position_update_immediately = true;
                                return true;
                            }

                            return false;
                        }

                        return false;
                    }

                    if (y < 1) {
                        return false;
                    }

                    --pos_y;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean tryTeleportAwayFrom(Entity entity, double min_distance) {
        if (!this.isDead && !(this.getHealth() <= 0.0F)) {
            double min_distance_sq = min_distance * min_distance;
            int x = this.getBlockPosX();
            int y = this.getFootBlockPosY();
            int z = this.getBlockPosZ();
            double threat_pos_x = entity == null ? this.posX : entity.posX;
            double threat_pos_z = entity == null ? this.posZ : entity.posZ;

            for(int attempts = 0; attempts < 64; ++attempts) {
                int dx = this.rand.nextInt(11) - 5;
                int dy = this.rand.nextInt(9) - 4;
                int dz = this.rand.nextInt(11) - 5;
                if (Math.abs(dx) >= 3 || Math.abs(dz) >= 3) {
                    int try_x = x + dx;
                    int try_y = y + dy;
                    int try_z = z + dz;
                    double try_pos_x = (double)try_x + 0.5;
                    double try_pos_z = (double)try_z + 0.5;
                    World var10000 = this.worldObj;
                    if (!(World.getDistanceSqFromDeltas(try_pos_x - threat_pos_x, try_pos_z - threat_pos_z) < min_distance_sq) && try_y >= 1 && this.worldObj.blockExists(try_x, try_y, try_z)) {
                        do {
                            --try_y;
                        } while(!this.worldObj.isBlockSolid(try_x, try_y, try_z) && try_y >= 1);

                        if (try_y >= 1) {
                            ++try_y;
                            if (!this.worldObj.isBlockSolid(try_x, try_y, try_z) && !this.worldObj.isLiquidBlock(try_x, try_y, try_z) && this.tryTeleportTo(try_pos_x, (double)try_y, try_pos_z)) {
                                EntityPlayer target = this.findPlayerToAttack(Math.min(this.getMaxTargettingRange(), 24.0F));
                                if (target != null && target != this.getTarget()) {
                                    this.setTarget(target);
                                }

                                return true;
                            }
                        }
                    }
                }
            }

            return false;
        } else {
            return false;
        }
    }
    public EntityDamageResult attackEntityAsMob(Entity target) {
        EntityDamageResult result = super.attackEntityAsMob(target);
        if (result != null && !result.entityWasDestroyed()) {
            this.tryTeleportAwayFrom(this.getTarget(), 8.0);
            return result;
        } else {
            return result;
        }
    }

    public EntityDamageResult attackEntityFrom(Damage damage) {
        boolean can_evade = !damage.isFallDamage() && !damage.isFireDamage() && !damage.isPoison();
        if (can_evade && (this.num_evasions > 0 || (this.getHealth() < 20.0F && this.rand.nextInt(8) != 0))) {
            if(this.num_evasions > 0){
                --this.num_evasions;
            }
            if (this.tryTeleportAwayFrom(this.getTarget(), 6.0)) {
                if(this.getHealth() >= 20.0F) {
                    EntityLichShadow shadow = new EntityLichShadow(worldObj);
                    shadow.setPosition(posX, posY, posZ);
                    shadow.refreshDespawnCounter(-9600);
                    worldObj.spawnEntityInWorld(shadow);
                    shadow.onSpawnWithEgg(null);
                    shadow.entityFX(EnumEntityFX.summoned);
                }
                return null;
            }
        }
        return super.attackEntityFrom(damage);
    }

    public void swapHeldItemStackWithStowed() {
        ItemStack item_stack = this.stowed_item_stack;
        this.stowed_item_stack = this.getHeldItemStack();
        this.setHeldItemStack(item_stack);
    }
    public int getExperienceValue() {
        return super.getExperienceValue() * 20;
    }
    @Override
    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        this.dropItemStack(new ItemStack(Items.forgingnote.itemID, 1, 0), 0.0F);
        int looting = damage_source.getLootingModifier();
        int num_drops;
        int i;
        num_drops = this.rand.nextInt(3 + looting) - 1;
        if (num_drops > 0 && !recently_hit_by_player) {
            num_drops -= this.rand.nextInt(num_drops + 1);
        }

        for(i = 0; i < num_drops; ++i) {
            this.dropItem(Items.AncientmetalArmorPiece.itemID, 1);
        }

        if (recently_hit_by_player && !this.has_taken_massive_fall_damage && this.rand.nextInt(this.getBaseChanceOfRareDrop()) < 5 + looting * 2) {
            this.dropItemStack(new ItemStack(Item.skull.itemID, 1, 0), 0.0F);
        }
    }
    @Override
    public Class getTroopClass() {
        return EntityLichShadow.class;
    }
}
