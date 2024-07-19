package net.oilcake.mitelros.mixins.item;

import java.util.List;
import java.util.Random;
import net.minecraft.DamageSource;
import net.minecraft.Enchantment;
import net.minecraft.EnchantmentHelper;
import net.minecraft.EntityArrow;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumChatFormatting;
import net.minecraft.EnumSignal;
import net.minecraft.Item;
import net.minecraft.ItemArrow;
import net.minecraft.ItemBow;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.Packet85SimpleSignal;
import net.minecraft.Slot;
import net.minecraft.Translator;
import net.minecraft.World;
import net.oilcake.mitelros.enchantment.Enchantments;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ItemBow.class})
public class ItemBowMixin extends Item {
   @Final
   @Shadow
   @Mutable
   private static Material[] possible_arrow_materials;
   @Shadow
   private Material reinforcement_material;

   public ItemBowMixin(int id, Material reinforcement_material) {
      super(id, Material.wood, "bows/" + reinforcement_material.toString() + "/");
   }

   @Inject(
      method = {"<clinit>()V"},
      at = {@At("FIELD")}
   )
   private static void injectClinit(CallbackInfo callback) {
      possible_arrow_materials = new Material[]{Material.flint, Material.obsidian, Material.copper, Material.silver, Material.rusted_iron, Material.gold, Material.iron, Material.mithril, Material.adamantium, Material.ancient_metal, Materials.nickel, Materials.tungsten, Materials.magical};
   }

   @Overwrite
   public static int getTicksForMaxPull(ItemStack item_stack) {
      Material material = item_stack.getMaterialForRepairs();
      byte TicksPull;
      if (material == Materials.tungsten) {
         TicksPull = 30;
      } else if (material == Material.mithril) {
         TicksPull = 27;
      } else if (material == Material.ancient_metal) {
         TicksPull = 24;
      } else {
         TicksPull = 20;
      }

      return (int)((float)TicksPull * (1.0F - 0.5F * EnchantmentHelper.getEnchantmentLevelFraction(Enchantment.quickness, item_stack)));
   }

   @Overwrite
   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
      if (!player.inCreativeMode() && player.inventory.getReadiedArrow() == null && !EnchantmentHelper.hasEnchantment(player.getHeldItemStack(), Enchantments.enchantmentInfinity)) {
         return false;
      } else {
         player.nocked_arrow = player.inventory.getReadiedArrow();
         if (player.nocked_arrow == null && player.inCreativeMode() || EnchantmentHelper.hasEnchantment(player.getHeldItemStack(), Enchantments.enchantmentInfinity)) {
            player.nocked_arrow = Items.arrowMagical;
         }

         if (player.onServer()) {
            player.sendPacketToAssociatedPlayers((new Packet85SimpleSignal(EnumSignal.nocked_arrow)).setShort(player.nocked_arrow.itemID).setEntityID(player), false);
         }

         player.setHeldItemInUse();
         return true;
      }
   }

   @Overwrite
   public void onPlayerStoppedUsing(ItemStack item_stack, World world, EntityPlayer player, int item_in_use_count) {
      if (!world.isRemote) {
         ItemArrow arrow = player.inventory.getReadiedArrow();
         if (arrow == null) {
            arrow = player.nocked_arrow;
         }

         float fraction_pulled = ItemBow.getFractionPulled(item_stack, item_in_use_count);
         fraction_pulled = (fraction_pulled * fraction_pulled + fraction_pulled * 2.0F) / 3.0F;
         if (!(fraction_pulled < 0.1F)) {
            if (fraction_pulled > 1.0F) {
               fraction_pulled = 1.0F;
            }

            EntityArrow entity_arrow = new EntityArrow(world, player, fraction_pulled * 2.0F, arrow, item_stack.isItemEnchanted());
            player.nocked_arrow = null;
            if (fraction_pulled == 1.0F) {
               entity_arrow.setIsCritical(true);
            }

            Material material = item_stack.getMaterialForRepairs();
            if (material == Materials.tungsten) {
               entity_arrow.setDamage(entity_arrow.getDamage() * 1.149999976158142);
            } else if (material == Material.mithril) {
               entity_arrow.setDamage(entity_arrow.getDamage() * 1.100000023841858);
            } else if (material == Material.ancient_metal) {
               entity_arrow.setDamage(entity_arrow.getDamage() * 1.0499999523162842);
            } else {
               entity_arrow.setDamage(entity_arrow.getDamage() * 0.75);
            }

            int power = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, item_stack);
            if (power > 0) {
               entity_arrow.setDamage(entity_arrow.getDamage() + (double)((float)power * 0.5F) + 0.5);
            }

            int punch = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, item_stack);
            if (punch > 0) {
               entity_arrow.setKnockbackStrength(punch);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, item_stack) > 0) {
               entity_arrow.setFire(100);
            }

            player.tryDamageHeldItem(DamageSource.generic, 1);
            Random rand = new Random();
            world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 1.2F) + fraction_pulled * 0.5F);
            if (player.inCreativeMode()) {
               entity_arrow.canBePickedUp = 2;
            } else {
               player.inventory.consumeArrow();
            }

            if (!world.isRemote) {
               world.spawnEntityInWorld(entity_arrow);
            }
         }
      }

   }

   @Inject(
      method = {"<init>(ILnet/minecraft/Material;)V"},
      at = {@At("RETURN")}
   )
   private void injectInit(CallbackInfo callbackInfo) {
      this.setMaxDamage(this.reinforcement_material == Materials.tungsten ? 256 : (this.reinforcement_material == Material.mithril ? 128 : (this.reinforcement_material == Material.ancient_metal ? 64 : 32)));
   }

   @Overwrite
   public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
      if (extended_info && this.reinforcement_material.isMetal()) {
         int bonus = this.reinforcement_material == Material.mithril ? 25 : (this.reinforcement_material == Materials.tungsten ? 35 : 10);
         info.add("");
         info.add(EnumChatFormatting.BLUE + Translator.getFormatted("item.tooltip.velocityBonus", new Object[]{bonus}));
      }

      super.addInformation(item_stack, player, info, extended_info, slot);
   }

   @Redirect(
      method = {"<init>(ILnet/minecraft/Material;)V"},
      at = @At(
   value = "INVOKE",
   target = "Lnet/minecraft/Item;setMaxDamage(I)Lnet/minecraft/Item;"
)
   )
   private void redirectSetMaxDamage() {
      if (this.reinforcement_material == Materials.tungsten) {
         this.setMaxDamage(256);
      } else if (this.reinforcement_material == Material.mithril) {
         this.setMaxDamage(128);
      } else if (this.reinforcement_material == Material.ancient_metal) {
         this.setMaxDamage(64);
      } else {
         this.setMaxDamage(32);
      }

   }
}
