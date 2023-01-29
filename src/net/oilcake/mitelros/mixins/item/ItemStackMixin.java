package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    private ItemStack itemStack;

    public boolean isItemStackEqualC(ItemStack par1ItemStack, boolean ignore_stack_size, boolean ignore_quality, boolean ignore_damage_but_not_subtype, boolean ignore_tag_compound) {
        if (par1ItemStack == itemStack) {
            return true;
        } else if (this.itemID != par1ItemStack.itemID) {
            return false;
        } else if (this.getItemSubtype() != par1ItemStack.getItemSubtype()) {
            if (!this.getHasSubtypes()) {
                Minecraft.setErrorMessage("isItemStackEqual: subtypes are different but item does not have subtypes");
            }

            return false;
        } else {
            if (this.getItemDamage() != par1ItemStack.getItemDamage()) {
                if (!this.isItemStackDamageable()) {
                    Minecraft.setErrorMessage("isItemStackEqual: damages are different but item is not damageable");
                }

                if (!ignore_damage_but_not_subtype) {
                    return false;
                }
            }

            if (!ignore_quality && par1ItemStack.getQuality() != this.getQuality()) {
                return false;
            } else if (par1ItemStack.getIsArtifact() != this.is_artifact) {
                return false;
            } else if (!ignore_stack_size && this.stackSize != par1ItemStack.stackSize) {
                return false;
            } else {
                return ignore_tag_compound || areItemStackTagsEqual(itemStack, par1ItemStack);
            }
        }
    }
    @Shadow
    public boolean isItemStackDamageable() {
        return false;
    }
    @Shadow
    public static boolean areItemStackTagsEqual(ItemStack par0ItemStack, ItemStack par1ItemStack) {
        return false;
    }
    @Shadow
    public EnumQuality getQuality() {
        return null;
    }
    @Shadow
    public boolean getHasSubtypes() {
        return false;
    }
    @Shadow
    public int getItemDamageForDisplay() {
        return 1;
    }
    @Shadow
    public int getItemSubtype() {
        return 1;
    }
    @Shadow
    public int getItemDamage() {
        return 1;
    }
    @Shadow
    private boolean is_artifact;
    public boolean getIsArtifact(){
        return is_artifact;
    }
    @Shadow
    public int stackSize;
    @Shadow
    public int animationsToGo;
    @Shadow
    public int itemID;
    @Shadow
    public NBTTagCompound stackTagCompound;
    @Shadow
    private int subtype;
    @Shadow
    private int damage;
}
