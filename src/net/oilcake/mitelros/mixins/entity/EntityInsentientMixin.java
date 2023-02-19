//package net.oilcake.mitelros.mixins.entity;
//
//import net.minecraft.*;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.gen.Accessor;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//
//@Mixin(EntityInsentient.class)
//public abstract class EntityInsentientMixin extends EntityLiving {
//    public boolean[] picked_up_a_held_item_array;
//    private final Map<EntityPlayer,Integer> playerSteppedCountMap = new HashMap<>();
//
//    public EntityInsentientMixin(World par1World) {
//        super(par1World);
//    }
//
//    @Inject(method = "applyEntityAttributes",at = @At("RETURN"))
//    private void injectEntityAttributes(CallbackInfo c){
//        this.picked_up_a_held_item_array = new boolean[5];
//    }
//
//    @Inject(locals = LocalCapture.CAPTURE_FAILHARD,
//            method = "tryPickUpItems",
//            at = @At(value = "FIELD",
//                    target = "Lnet/minecraft/EntityInsentient;persistenceRequired:Z",
//                    shift = At.Shift.BEFORE))
//    private void injectPickUpItems(CallbackInfo ci, List entity_items, Iterator iterator, EntityItem entity_item, ItemStack item_stack_on_ground, Item item_on_ground, int var5, boolean pickup, ItemStack current_item_stack, boolean set_dead){
//        if (entity_item.canBePickUpByPlayer()){
//            this.picked_up_a_held_item_array[var5] = true;
//        }
//        this.picked_up_a_held_item = true;
//    }
//
//    @Inject(method = "readEntityFromNBT",at = @At("RETURN"))
//    private void injectReadNBT(NBTTagCompound par1NBTTagCompound,CallbackInfo callbackInfo){
//        if (par1NBTTagCompound.hasKey("picked_up_held_items")) {
//            if (this.picked_up_a_held_item_array == null) {
//                this.picked_up_a_held_item_array = new boolean[5];
//            }
//
//            byte[] picked_up_held_items = par1NBTTagCompound.getByteArray("picked_up_held_items");
//            for (int i = 0, jLength = picked_up_held_items.length; i < jLength; i++) {
//                if (picked_up_held_items[i] == 1) {
//                    this.picked_up_a_held_item_array[i] = true;
//                }
//            }
//        }
//
//        if (par1NBTTagCompound.hasKey("PlayerSteppedCountMap")) {
//            NBTTagList attackCountMap = par1NBTTagCompound.getTagList("PlayerSteppedCountMap");
//            int count = attackCountMap.tagCount();
//
//            for(int i = 0; i < count; ++i) {
//                NBTTagCompound a = (NBTTagCompound)attackCountMap.tagAt(i);
//                Entity attacker = this.getWorldServer().getEntityByID(a.getInteger("Attacker"));
//                if (attacker instanceof EntityPlayer) {
//                    this.playerSteppedCountMap.put((EntityPlayer) attacker, a.getInteger("Count"));
//                }
//            }
//        }
//    }
//
//    @Inject(method = "writeEntityToNBT",at = @At("RETURN"))
//    private void injectWriteNBT(NBTTagCompound par1NBTTagCompound,CallbackInfo c){
//        byte[] picked_up_held_items = new byte[this.picked_up_a_held_item_array.length];
//        boolean[] pickedUpAHeldItemArray = this.picked_up_a_held_item_array;
//
//        for(int i = 0; i < pickedUpAHeldItemArray.length; ++i) {
//            if (pickedUpAHeldItemArray[i]) {
//                picked_up_held_items[i] = 1;
//            }
//        }
//        NBTTagList nbtTagList = new NBTTagList();
//        for (Map.Entry<EntityPlayer, Integer> integerEntry : this.playerSteppedCountMap.entrySet()) {
//            NBTTagCompound compound = new NBTTagCompound();
//            compound.setInteger("Attacker", ( integerEntry).getKey().entityId);
//            compound.setInteger("Count", (integerEntry).getValue());
//            nbtTagList.appendTag(compound);
//        }
//
//        par1NBTTagCompound.setTag("PlayerSteppedCountMap", nbtTagList);
//
//        par1NBTTagCompound.setByteArray("picked_up_held_items", picked_up_held_items);
//    }
//
//    @Accessor("came_from_spawner")
//    public abstract boolean isCame_from_spawner();
//
//    public boolean isDead() {
//        return this.isDead;
//    }
//
//    @Shadow
//    public boolean isWearing(ItemStack itemStack) {
//        return false;
//    }
//
//    @Shadow
//    public void setCanPickUpLoot(boolean par1) {
//    }
//
//    @Shadow
//    public void setCurrentItemOrArmor(int var5, ItemStack setStackSize) {
//    }
//
//    @Shadow
//    public boolean setWornItem(int i, ItemStack itemStack) {
//        return false;
//    }
//    @Shadow
//    public ItemStack getEquipmentInSlot(int var5) {
//        return null;
//    }
//
//    @Shadow
//    public ItemStack getHeldItemStack() {
//        return null;
//    }
//
//    @Shadow
//    public void setHeldItemStack(ItemStack itemStack) {
//    }
//
//    @Shadow
//    public ItemStack[] getInventory() {
//        return new ItemStack[0];
//    }
//
//
//    @Shadow
//    public ItemStack[] getWornItems() {
//        return new ItemStack[0];
//    }
//
//    @Shadow
//    public int livingSoundTime;
//
//    @Shadow
//    public int getTalkInterval() {
//        return 80;
//    }
//
//    @Shadow
//    public double distanceToNearestPlayer() {
//        return (double)this.worldObj.distanceToNearestPlayer(this.posX, this.posY, this.posZ);
//    }
//    @Shadow
//    public boolean picked_up_a_held_item;
//    @Shadow
//    public void makeLivingSound() {}
//
//    @Shadow
//    public void makeLongDistanceLivingSound() {}
//}
