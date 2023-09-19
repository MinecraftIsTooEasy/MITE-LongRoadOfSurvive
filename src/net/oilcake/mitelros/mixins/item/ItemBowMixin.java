package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Random;

import static net.minecraft.ItemBow.getFractionPulled;

@Mixin(ItemBow.class)
public class ItemBowMixin extends Item{
    public ItemBowMixin(int id, Material reinforcement_material) {
        super(id, Material.wood, "bows/" + reinforcement_material.toString() + "/");
    }
    @Inject(method = "<clinit>", at = @At("FIELD"))
    private static void injectClinit(CallbackInfo callback) {
        possible_arrow_materials = new Material[]{Material.flint, Material.obsidian, Material.copper, Material.silver, Material.rusted_iron, Material.gold, Material.iron, Material.mithril, Material.adamantium, Material.ancient_metal,Materials.nickel,Materials.tungsten,Materials.magical};
    }
    @Final
    @Shadow
    @Mutable
    private static Material[] possible_arrow_materials;
    @Overwrite
    public static int getTicksForMaxPull(ItemStack item_stack) {
        int TicksPull;
        Material material = item_stack.getMaterialForRepairs();
        if (material == Materials.tungsten) {
            TicksPull = 30;
        } else {
            if (material == Material.mithril) {
                TicksPull = 27;
            } else {
                if (material == Material.ancient_metal) {
                    TicksPull = 24;
                } else {
                    TicksPull = 20;
                }
            }
        }
        return (int) ((float) TicksPull * (1.0F - 0.5F * EnchantmentManager.getEnchantmentLevelFraction(Enchantment.quickness, item_stack)));
    }

    @Overwrite
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        if (!player.inCreativeMode() && player.inventory.getReadiedArrow() == null && !(EnchantmentManager.hasEnchantment(player.getHeldItemStack(), Enchantments.enchantmentInfinity))) {
            return false;
        } else {
            player.nocked_arrow = player.inventory.getReadiedArrow();
            if (player.nocked_arrow == null && player.inCreativeMode() || (EnchantmentManager.hasEnchantment(player.getHeldItemStack(), Enchantments.enchantmentInfinity))) {
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
//                if (!player.inCreativeMode()) {
//                    return;
//                }
                arrow = player.nocked_arrow;
            }

            float fraction_pulled = getFractionPulled(item_stack, item_in_use_count);
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
                    entity_arrow.setDamage(entity_arrow.getDamage() * 1.15F);
                } else {
                    if (material == Material.mithril) {
                        entity_arrow.setDamage(entity_arrow.getDamage() * 1.1F);
                    } else {
                        if (material == Material.ancient_metal) {
                            entity_arrow.setDamage(entity_arrow.getDamage() * 1.05F);
                        } else {
                            entity_arrow.setDamage(entity_arrow.getDamage() * 0.75F);
                        }
                    }
                }

                int power = EnchantmentManager.getEnchantmentLevel(Enchantment.power.effectId, item_stack);
                if (power > 0) {
                    entity_arrow.setDamage(entity_arrow.getDamage() + (double)((float)power * 0.5F) + 0.5);
                }

                int punch = EnchantmentManager.getEnchantmentLevel(Enchantment.punch.effectId, item_stack);
                if (punch > 0) {
                    entity_arrow.setKnockbackStrength(punch);
                }

                if (EnchantmentManager.getEnchantmentLevel(Enchantment.flame.effectId, item_stack) > 0) {
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
    @Shadow
    private Material reinforcement_material;

    @Inject(method = "<init>",at = @At("RETURN"))
    private void injectInit(CallbackInfo callbackInfo){
        this.setMaxDamage(reinforcement_material == Materials.tungsten ? 256 : (reinforcement_material == Material.mithril ? 128 : (reinforcement_material == Material.ancient_metal ? 64 : 32)));
    }

    @Overwrite
    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        if (extended_info && this.reinforcement_material.isMetal()) {
            int bonus = this.reinforcement_material == Material.mithril ? 25 : (this.reinforcement_material == Materials.tungsten ? 35 : 10);
            info.add("");
            info.add(EnumChatFormat.BLUE + Translator.getFormatted("item.tooltip.velocityBonus", new Object[]{bonus}));
        }

        super.addInformation(item_stack, player, info, extended_info, slot);
    }

    @Redirect(method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/Item;setMaxDamage(I)Lnet/minecraft/Item;"))
    private void redirectSetMaxDamage(){
        if (reinforcement_material == Materials.tungsten) {
            this.setMaxDamage(256);
        } else {
            if (reinforcement_material == Material.mithril) {
                this.setMaxDamage(128);
            } else {
                if (reinforcement_material == Material.ancient_metal) {
                    this.setMaxDamage(64);
                } else {
                    this.setMaxDamage(32);
                }
            }
        }
    }
}
