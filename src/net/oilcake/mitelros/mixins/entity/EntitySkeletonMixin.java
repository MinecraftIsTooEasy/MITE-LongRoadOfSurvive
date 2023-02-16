package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.minecraft.server.MinecraftServer;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Calendar;

@Mixin(EntitySkeleton.class)

public class EntitySkeletonMixin extends EntityMonster{
    public EntitySkeletonMixin(World par1World) {
        super(par1World);
    }
    @Shadow
    private PathfinderGoalArrowAttack aiArrowAttack = new PathfinderGoalArrowAttack((IRangedEntity) this, 1.0, 20, 60, 15.0F);
    @Shadow
    private PathfinderGoalMeleeAttack aiAttackOnCollide = new PathfinderGoalMeleeAttack(this, EntityPlayer.class, 1.2, false);
    @Overwrite
    public GroupDataEntity onSpawnWithEgg(GroupDataEntity par1EntityLivingData) {
        par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
        int skeleton_type = this.forced_skeleton_type >= 0 ? this.forced_skeleton_type : this.getRandomSkeletonType(this.worldObj);
        if (skeleton_type == 1) {
            this.tasks.addTask(4, this.aiAttackOnCollide);
            this.setSkeletonType(1);
            this.setCurrentItemOrArmor(0, (new ItemStack(Items.tungstenSword)).randomizeForMob(this, false));
            this.setCurrentItemOrArmor(1, (new ItemStack(Items.tungstenBootsChain)).randomizeForMob(this, false));
            this.setCurrentItemOrArmor(2, (new ItemStack(Items.tungstenLeggingsChain)).randomizeForMob(this, false));
            this.setCurrentItemOrArmor(3, (new ItemStack(Items.tungstenChestplateChain)).randomizeForMob(this, false));
            this.setCurrentItemOrArmor(4, (new ItemStack(Items.tungstenHelmetChain)).randomizeForMob(this, false));
            this.getEntityAttribute(GenericAttributes.attackDamage).setAttribute(6.0);
        } else {
            if (skeleton_type == 2) {
                this.setSkeletonType(2);
                this.tasks.addTask(4, this.aiAttackOnCollide);
            } else if (skeleton_type == 0) {
                this.tasks.addTask(4, this.aiArrowAttack);
            } else {
                Minecraft.setErrorMessage("onSpawnWithEgg: Unrecognized skeleton type " + skeleton_type);
            }

            this.addRandomEquipment();
        }

        this.setCanPickUpLoot(true);
        if (this.getEquipmentInSlot(4) == null) {
            Calendar var2 = this.worldObj.getCurrentDate();
            if (var2.get(2) + 1 == 10 && var2.get(5) == 31 && this.rand.nextFloat() < 0.25F) {
                this.setCurrentItemOrArmor(4, new ItemStack(this.rand.nextFloat() < 0.1F ? Block.pumpkinLantern : Block.pumpkin));
                this.equipmentDropChances[4] = 0.0F;
            }
        }

        return par1EntityLivingData;
    }
    @Shadow
    public void setSkeletonType(int par1) {
        this.dataWatcher.updateObject(13, (byte)par1);
        if (par1 == 1) {
            this.setSize(0.72F, 2.34F);
        } else {
            this.setSize(0.6F, 1.8F);
        }

    }
    @Shadow
    public int forced_skeleton_type = -1;
    @Shadow
    public int getRandomSkeletonType(World world) {
    return -1;
    }
    @Shadow
    protected void addRandomEquipment() {
        this.addRandomWeapon();
        this.addRandomArmor();
    }
    @Shadow
    public void addRandomWeapon() {
        if (this.getSkeletonType() == 2 && this.rand.nextInt(20) == 0) {
            int day_of_world = MinecraftServer.F().getOverworld().getDayOfWorld();
            if (day_of_world >= 10) {
                this.setCurrentItemOrArmor(0, (new ItemStack(day_of_world >= 20 && !this.rand.nextBoolean() ? Item.swordRustedIron : Item.daggerRustedIron)).randomizeForMob(this, false));
                return;
            }
        }

        this.setCurrentItemOrArmor(0, (new ItemStack((Item)(this.getSkeletonType() == 2 ? Item.clubWood : Item.bow))).randomizeForMob(this, true));
    }
    @Shadow
    public int getSkeletonType() {
        return this.dataWatcher.getWatchableObjectByte(13);
    }
}
