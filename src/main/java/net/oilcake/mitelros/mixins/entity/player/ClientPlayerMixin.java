package net.oilcake.mitelros.mixins.entity.player;

import net.minecraft.AbstractClientPlayer;
import net.minecraft.BlockWorkbench;
import net.minecraft.ClientPlayer;
import net.minecraft.Container;
import net.minecraft.ContainerWorkbench;
import net.minecraft.EntityPlayer;
import net.minecraft.IRecipe;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.Minecraft;
import net.minecraft.MovementInput;
import net.minecraft.SlotCrafting;
import net.minecraft.World;
import net.oilcake.mitelros.block.BlockMetalbench;
import net.oilcake.mitelros.block.BlockWoodbench;
import net.oilcake.mitelros.block.EnumBenchType;
import net.oilcake.mitelros.block.enchantreserver.EnchantReserverSlots;
import net.oilcake.mitelros.block.enchantreserver.GuiEnchantReserver;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.ExperimentalConfig;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayer.class)
public abstract class ClientPlayerMixin extends AbstractClientPlayer {
   @Shadow
   public MovementInput movementInput;
   @Shadow
   protected Minecraft mc;
   @Shadow
   public int sprintingTicksLeft;
   private int phytonutrients;
   private int protein;
   private float heal_progress;
   private int temperaturePoint;

   public ClientPlayerMixin(World par1World, String par2Str) {
      super(par1World, par2Str);
   }

   public void displayGUIEnchantReserver(int x, int y, int z, EnchantReserverSlots slots) {
      this.mc.displayGuiScreen(new GuiEnchantReserver(ReflectHelper.dyCast(this), x, y, z, slots));
   }

   @Overwrite
   private float getBenchAndToolsModifier(Container container) {
      if (!(container instanceof ContainerWorkbench)) {
         return 0.0F;
      } else {
         ContainerWorkbench container_workbench = (ContainerWorkbench)container;
         SlotCrafting slot_crafting = (SlotCrafting)container_workbench.getSlot(0);
         ItemStack item_stack = slot_crafting.getStack();
         Item item = item_stack == null ? null : item_stack.getItem();
         IRecipe recipe = container_workbench.getRecipe();
         Material material_to_check_tool_bench_hardness_against = recipe == null ? item.getHardestMetalMaterial() : recipe.getMaterialToCheckToolBenchHardnessAgainst();
         int metadata = container_workbench.getBlockMetadata();
         int benchType = container_workbench.benchType;
         Material tool_material;
         if (benchType == EnumBenchType.WOOD.ordinal()) {
            tool_material = BlockWoodbench.getToolMaterial(metadata);
         } else if (benchType == EnumBenchType.METAL.ordinal()) {
            tool_material = BlockMetalbench.getToolMaterial(metadata);
         } else {
            tool_material = BlockWorkbench.getToolMaterial(metadata);
         }

         if (material_to_check_tool_bench_hardness_against != null) {
            if (tool_material != Material.flint && tool_material != Material.obsidian) {
               if (tool_material != Material.copper && tool_material != Material.silver && tool_material != Material.gold) {
                  if (tool_material != Material.iron && tool_material != Materials.nickel) {
                     if (tool_material == Material.ancient_metal) {
                        return 0.75F;
                     } else if (tool_material == Material.mithril) {
                        return 1.0F;
                     } else if (tool_material == Materials.tungsten) {
                        return 1.5F;
                     } else if (tool_material == Material.adamantium) {
                        return 2.5F;
                     } else {
                        Minecraft.setErrorMessage("getBenchAndToolsModifier: unrecognized tool material " + tool_material);
                        return 0.0F;
                     }
                  } else {
                     return 0.5F;
                  }
               } else {
                  return 0.4F;
               }
            } else {
               return 0.25F;
            }
         } else {
            return tool_material == Material.flint && tool_material == Material.obsidian ? (tool_material == Material.copper && tool_material == Material.silver && tool_material == Material.gold ? (tool_material != Material.iron && tool_material != Materials.nickel ? (tool_material == Material.ancient_metal ? 0.75F : (tool_material == Material.mithril ? 1.0F : (tool_material == Materials.tungsten ? 1.5F : (tool_material == Material.adamantium ? 2.5F : 0.25F)))) : 0.5F) : 0.4F) : 0.25F;
         }
      }
   }

   @Inject(
      method = {"setSprinting(Z)V"},
      at = {@At("HEAD")}
   )
   public void InjectCannotRun(boolean par1, CallbackInfo callbackInfo) {
      if (this.getHealth() / 5.0F < 1.0F && (Boolean)ExperimentalConfig.TagConfig.Realistic.ConfigValue) {
         par1 = false;
      }

   }

   public int getProtein() {
      return this.protein;
   }

   public int getPhytonutrients() {
      return this.phytonutrients;
   }

   public float getHealProgress() {
      return this.heal_progress;
   }

   public int getTemperaturePoint() {
      return this.temperaturePoint;
   }

   public void setProtein(int protein) {
      this.protein = protein;
   }

   public void setHeal_progress(float heal_progress) {
      this.heal_progress = heal_progress;
   }

   public void setPhytonutrients(int phytonutrients) {
      this.phytonutrients = phytonutrients;
   }

   public void setTemperaturePoint(int tp) {
      this.temperaturePoint = tp;
   }
}
