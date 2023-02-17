package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityWitherBoneLord;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityZombie.class)
public class EntityZombieMixin extends EntityAnimalWatcher{
    public EntityZombieMixin(World par1World) {
        super(par1World);
    }
    @Inject(method = "<init>",at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        this.rare_drops_standard = new Item[]{Item.copperNugget, Item.silverNugget, Item.goldNugget, Item.ironNugget};
        this.rare_drops_villager = new Item[]{Item.seeds, Item.pumpkinSeeds, Item.melonSeeds, Item.carrot, Item.potato, Item.onion};
        this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, new PathfinderGoalFloat(this));
        this.tasks.addTask(1, new PathfinderGoalBreakDoor(this));
        this.tasks.addTask(2, new PathfinderGoalMeleeAttack(this, EntityPlayer.class, 1.0, false));
        this.tasks.addTask(3, new PathfinderGoalMeleeAttack(this, EntityVillager.class, 1.0, true));
        this.tasks.addTask(4, new PathfinderGoalMoveTowardsRestriction(this, 1.0));
        this.tasks.addTask(5, new PathfinderGoalMoveThroughVillage(this, 1.0, false));
        this.tasks.addTask(6, new PathfinderGoalRandomStroll(this, 1.0));
        this.tasks.addTask(7, new PathfinderGoalLookAtPlayer(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(7, new PathfinderGoalRandomLookaround(this));
        this.targetTasks.addTask(1, new PathfinderGoalHurtByTarget(this, true));
        this.targetTasks.addTask(2, new PathfinderGoalNearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(2, new PathfinderGoalNearestAttackableTarget(this, EntityVillager.class, 0, false));
        this.tasks.addTask(2, new EntityAIMoveToFoodItem(this, 1.0F, true));
        this.tasks.addTask(4, new PathfinderGoalMeleeAttack(this, EntityAnimal.class, 1.0, true));
        this.targetTasks.addTask(3, new PathfinderGoalNearestAttackableTarget(this, EntityAnimal.class, 10, true));
        this.tasks.addTask(3, new EntityAIMoveToTree(this, 1.0F));
        this.is_smart = true;
    }
    @Shadow
    Item[] rare_drops_standard;
    @Shadow
    Item[] rare_drops_villager;
    @Shadow
    private boolean is_smart;
}

