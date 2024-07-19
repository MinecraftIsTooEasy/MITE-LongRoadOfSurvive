package net.oilcake.mitelros.entity;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.EntityInvisibleStalker;
import net.minecraft.EntityLivingData;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.Minecraft;
import net.minecraft.RandomItemListEntry;
import net.minecraft.SharedMonsterAttributes;
import net.minecraft.WeightedRandom;
import net.minecraft.World;

public class EntityGhost extends EntityInvisibleStalker {
   public EntityGhost(World par1World) {
      super(par1World);
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.25999999046325684);
      this.setEntityAttribute(SharedMonsterAttributes.attackDamage, 5.0);
      this.setEntityAttribute(SharedMonsterAttributes.maxHealth, 20.0);
   }

   protected float getSoundVolume(String sound) {
      return 0.75F;
   }

   public void addRandomWeapon() {
      List items = new ArrayList();
      items.add(new RandomItemListEntry(Item.swordRustedIron, 1));
      if (this.worldObj.getDayOfWorld() >= 10 && !Minecraft.isInTournamentMode()) {
         items.add(new RandomItemListEntry(Item.battleAxeRustedIron, 3));
      }

      RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, items);
      this.setHeldItemStack((new ItemStack(entry.item)).randomizeForMob(this, true));
   }

   protected void addRandomEquipment() {
      this.addRandomWeapon();
      this.setCuirass((new ItemStack(Item.plateRustedIron)).randomizeForMob(this, true));
      this.setHelmet((new ItemStack(Item.helmetRustedIron)).randomizeForMob(this, true));
   }

   public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
      this.setCanPickUpLoot(true);
      this.addRandomEquipment();
      return super.onSpawnWithEgg(par1EntityLivingData);
   }
}
