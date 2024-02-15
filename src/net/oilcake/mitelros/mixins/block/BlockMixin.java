package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.block.BlockFlowerExtend;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static net.minecraft.Block.*;

@Mixin(Block.class)
public abstract class BlockMixin{

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void injectClinit(CallbackInfo callback) {
        Item.itemsList[Blocks.flowerextend.blockID] = (new ItemMultiTexture(Blocks.flowerextend, BlockFlowerExtend.types)).setUnlocalizedName("flowers");
    }

    @Overwrite
    public int dropBlockAsItself(BlockBreakInfo info) {
        if (info.block != block) {
            //Minecraft.setErrorMessage("dropBlockAsItself: info.block!=this");
        }

        if (!info.block.canBeCarried()) {
            Minecraft.setErrorMessage("dropBlockAsItself: " + this + " cannot be carried");
        }

        return this.dropBlockAsEntityItem(info, this.createStackedBlock(info.getMetadata()));
    }
    @Overwrite
    public final int dropBlockAsEntityItem(BlockBreakInfo info, ItemStack item_stack) {
        if (info.block != block) {
            //Minecraft.setErrorMessage("dropBlockAsEntityItem: info.block!=this");
        }

        if (item_stack == null) {
            return 0;
        } else {
            if (item_stack.isItemDamaged() && info.damage != item_stack.getItemDamage()) {
                if (info.damage != 0) {
                    Minecraft.setErrorMessage("dropBlockAsEntityItem: info.damage was already set to " + info.damage + " (vs " + item_stack.getItemDamage() + ")");
                }

                info.damage = item_stack.getItemDamage();
            }

            return this.dropBlockAsEntityItem(info, item_stack.itemID, item_stack.getItemSubtype(), item_stack.stackSize, 1.0F);
        }
    }
    @Overwrite
    @Final
    private void validate() {
        if (this.is_always_solid && this.is_never_solid) {
            Minecraft.setErrorMessage("validate: is_always_solid and is_never_solid");
        }

        if (this.is_always_standard_form_cube && this.is_never_standard_form_cube) {
            Minecraft.setErrorMessage("validate: is_always_standard_form_cube and is_never_standard_form_cube");
        }

        if (this.is_always_solid_standard_form_cube && !this.is_always_solid) {
            Minecraft.setErrorMessage("validate: is_always_solid_standard_form_cube and !is_always_solid " + this);
        }

        if (this.is_always_solid_standard_form_cube && !this.is_always_standard_form_cube) {
            Minecraft.setErrorMessage("validate: is_always_solid_standard_form_cube and !is_always_standard_form_cube " + this);
        }

        if (this.is_always_solid_opaque_standard_form_cube && !this.is_always_solid) {
            Minecraft.setErrorMessage("validate: is_always_solid_opaque_standard_form_cube and !is_always_solid");
        }

        if (this.is_always_solid_opaque_standard_form_cube && !this.is_always_standard_form_cube) {
            Minecraft.setErrorMessage("validate: is_always_solid_opaque_standard_form_cube and !is_always_standard_form_cube");
        }

        if (this.is_always_solid_opaque_standard_form_cube && !this.is_always_solid_standard_form_cube) {
            Minecraft.setErrorMessage("validate: is_always_solid_opaque_standard_form_cube and !is_always_solid_standard_form_cube");
        }

        if (this.num_item_subtypes != this.item_subtypes.length) {
            Debug.setErrorMessage("validate: num_item_subtypes=" + this.num_item_subtypes + " vs item_subtypes.length=" + this.item_subtypes.length + " for " + this + " (id=" + this.blockID + ")");
        }

        if (this.num_item_subtypes != this.getItemStacks().size()) {
            Debug.setErrorMessage("validate: num_item_subtypes=" + this.num_item_subtypes + " vs " + this.getItemStacks().size() + " for " + this + " (id=" + this.blockID + ")");
        }

        if (this.num_item_subtypes < 1) {
            Debug.setErrorMessage("validate: num_item_subtypes==" + this.num_item_subtypes + " for " + this + "?");
        } else {
            List list = this.getItemStacks();

            for(int i = 0; i < this.item_subtypes.length; ++i) {
                ItemStack item_stack = (ItemStack)list.get(i);
                if (item_stack.getItemSubtype() != this.item_subtypes[i]) {
                    Debug.setErrorMessage("validate: subtype value mismatch, " + item_stack.getItemSubtype() + " vs " + this.item_subtypes[i]);
                }
            }
        }

        int metadata;
        if (this.has_item_subtypes) {
            for(metadata = 0; metadata < this.item_subtypes.length; ++metadata) {
                if (!this.isValidMetadata(this.item_subtypes[metadata])) {
                    Debug.setErrorMessage("validate: " + this + " has a subtype metadata of " + this.item_subtypes[metadata] + " but isValidMetadata() returns false for it");
                }
            }
        }

        if (this.canBeCarried()) {
            if (this.D() == null) {
                //Minecraft.setErrorMessage("No creative tab for [" + this.blockID + "] " + this);
            }
        } else if (block != bedrock && block != mobSpawner && block != dragonEgg && block != endPortalFrame && block != mantleOrCore && this.D() != null) {
            //Minecraft.setErrorMessage("Creative tab for [" + this.blockID + "] " + this);
        }

        for(metadata = 0; metadata < 16; ++metadata) {
            if (this.isValidMetadata(metadata)) {
                ItemStack item_stack = this.createStackedBlock(metadata);
                if (item_stack == null) {
                    if (this.canBeCarried()) {
                        Minecraft.setErrorMessage("validate: " + this + " can be carried but createStackedBlock() returns null");
                        break;
                    }
                } else if (item_stack.isBlock() && !item_stack.getItemAsBlock().getBlock().canBeCarried()) {
                    Minecraft.setErrorMessage("validate: createStackedBlock() returns a block that cannot be carried for " + this);
                    break;
                }

                if (this.canSilkHarvest(metadata) && !this.canBeCarried()) {
                    Minecraft.setErrorMessage("validate: " + this + " canSilkHarvest but cannot be carried");
                }
            }
        }

        boolean can_be_solid = false;
        boolean can_be_not_solid = false;

        for(metadata = 0; metadata < 16; ++metadata) {
            if (this.isValidMetadata(metadata)) {
                if (this.isSolid(metadata)) {
                    can_be_solid = true;
                } else {
                    can_be_not_solid = true;
                }
            }
        }

        if (!can_be_solid && !can_be_not_solid) {
            Minecraft.setErrorMessage("validate: " + this + " can neither be solid or not solid?");
        } else if (can_be_solid && can_be_not_solid) {
            if (this.is_always_solid) {
                Minecraft.setErrorMessage("validate: " + this + " can be solid or not solid but is_always_solid==true");
            } else if (this.is_never_solid) {
                Minecraft.setErrorMessage("validate: " + this + " can be solid or not solid but is_never_solid==true");
            }
        } else if (can_be_solid) {
            if (this.is_never_solid) {
                Minecraft.setErrorMessage("validate: " + this + " can only be solid but is_never_solid==true");
            } else if (!this.is_always_solid) {
                Minecraft.setErrorMessage("validate: " + this + " can only be solid but is_always_solid==false");
            }
        } else if (this.is_always_solid) {
            Minecraft.setErrorMessage("validate: " + this + " can only be not solid but is_always_solid==true");
        } else if (!this.is_never_solid) {
            Minecraft.setErrorMessage("validate: " + this + " can only be not solid but is_never_solid==false");
        }

        if (this.getRenderType() == 1) {
            if (!this.neverHidesAdjacentFaces()) {
                Minecraft.setErrorMessage("validate: " + this + " has render type " + this.getRenderType() + " but never_hides_adjacent_faces==false");
            }

            if (!this.is_never_standard_form_cube) {
                Minecraft.setErrorMessage("validate: " + this + " has render type " + this.getRenderType() + " but is_never_standard_form_cube==false");
            }
        }

        if (!this.is_always_standard_form_cube && this.renderAsNormalBlock()) {
            Minecraft.setErrorMessage("validate: " + this + " renders as normal block but is_always_standard_form_cube==false");
        }

        if (is_normal_cube_lookup[this.blockID] != this.is_normal_cube) {
            Minecraft.setErrorMessage("validate: " + this + " normal cube lookup discrepency");
        }

        if (!this.is_always_opaque_standard_form_cube && !this.is_never_opaque_standard_form_cube) {
            Minecraft.setErrorMessage("validate: " + this + " is neither always opaque standard form cube or never opaque standard form cube");
        }

        if (this.renderAsNormalBlock() != this.isAlwaysStandardFormCube()) {
            Debug.println("validate: " + this + ", renderAsNormalBlock=" + this.renderAsNormalBlock() + " vs isAlwaysStandardFormCube=" + this.isAlwaysStandardFormCube());
        }

        if (lightOpacity[this.blockID] == 0 && !canHaveLightValue[this.blockID]) {
            Debug.println("validate: " + this + " has light opacity 0 but canBlockGrass==false");
        }

        if (lightOpacity[this.blockID] >= 15 && canHaveLightValue[this.blockID]) {
            Debug.println("validate: " + this + " has light opacity 255 but canBlockGrass==true");
        }

        if (lightOpacity[this.blockID] >= 15 && this.isAlwaysStandardFormCube() && !this.isAlwaysOpaqueStandardFormCube()) {
            Debug.println("validate: " + this + " has light opacity >= 15 and is always standard form but is_always_opaque_standard_form_cube==false");
        }

        if (lightOpacity[this.blockID] >= 15 && !this.isAlwaysStandardFormCube() && !useNeighborBrightness[this.blockID]) {
            Debug.println("validate: " + this + " has light opacity 255 and is not always standard form but useNeighborBrightness==false");
        }

        if (lightOpacity[this.blockID] >= 15 && this.isAlwaysStandardFormCube() && useNeighborBrightness[this.blockID]) {
            Debug.println("validate: " + this + " has light opacity 255 and is always standard form but useNeighborBrightness==true");
        }

        if (canHaveLightValue[this.blockID] && useNeighborBrightness[this.blockID]) {
            Debug.println("validate: " + this + " canHaveLightValue and useNeighborBrightness are mutually exclusive");
        }

        if (useNeighborBrightness[this.blockID] && this.neverHidesAdjacentFaces()) {
            Debug.println("validate: " + this + " uses neighbor brightness but neverHidesAdjacentFaces");
        }

        boolean uses_neighbor_brightness = false;
        boolean always_uses_neighbor_brightness = true;

        for(int i = 0; i < 96; ++i) {
            if (this.use_neighbor_brightness[i]) {
                uses_neighbor_brightness = true;
            } else {
                always_uses_neighbor_brightness = false;
            }
        }

        if (useNeighborBrightness[this.blockID] != uses_neighbor_brightness) {
            Debug.println("validate: " + this + " useNeighborBrightness mismatch");
        }

        if (always_uses_neighbor_brightness) {
            Debug.println("validate: " + this + " always uses neighbor brightness");
        }

    }

    public Block getMatchingBlock(Class item_class, Material material) {
        Block matching_block = null;
        for(int i = 0; i < 256; ++i) {
            Block block = getBlock(i);
            if (block != null && block.getClass() == item_class && block.blockMaterial == material) {
                if (matching_block == null) {
                    matching_block = block;
                } else {
                    Minecraft.setErrorMessage("getMatchingItem: more than one item matched " + item_class + ", " + material);
                }
            }
        }
        return matching_block;
    }
//    @Overwrite
//    public final int dropBlockAsEntityItem(BlockBreakInfo info, int id_dropped, int subtype, int quantity, float chance) {
//        if (info.world.isRemote) {
//            Minecraft.setErrorMessage("dropBlockAsEntityItem: not meant to be called on client " + info.x + "," + info.y + "," + info.z);
//            return 0;
//        } else {
//            if (info.block != block) {
//                //Minecraft.setErrorMessage("dropBlockAsEntityItem: info.block!=this");
//            }
//
//            if (id_dropped >= 1 && quantity >= 1 && !(chance <= 0.0F)) {
//                if (info.wasExploded()) {
//                    if (this.blockMaterial != Material.snow && this.blockMaterial != Material.craftedSnow && this.blockMaterial != Material.glass) {
//                        if (this.blockMaterial != Material.plants && this.blockMaterial != Material.cactus && this.blockMaterial != Material.vine) {
//                            if (this.blockMaterial == Material.cake) {
//                                chance = 0.0F;
//                            }
//                        } else {
//                            chance = 0.0F;
//                        }
//                    } else {
//                        chance *= 0.4F;
//                    }
//                }
//
//                if (chance <= 0.0F) {
//                    return 0;
//                } else {
//                    int damage = info.damage;
//                    int num_drops = 0;
//
//                    int xp_reward_per_unit;
//                    for(xp_reward_per_unit = 0; xp_reward_per_unit < quantity; ++xp_reward_per_unit) {
//                        if (info.world.rand.nextFloat() < chance) {
//                            ItemStack item_stack = new ItemStack(id_dropped, 1, subtype);
//                            if (item_stack.isBlock() && !item_stack.getItemAsBlock().getBlock().canBeCarried()) {
//                                ItemStack substitute = item_stack.getItemAsBlock().getBlock().createStackedBlock(subtype);
//                                Minecraft.setErrorMessage("dropBlockAsEntityItem: " + item_stack + " can not be carried, substituting with " + substitute);
//                                if (substitute == null) {
//                                    Minecraft.setErrorMessage("dropBlockAsEntityItem: createStackedBlock returned null for " + item_stack);
//                                    return num_drops;
//                                }
//
//                                if (substitute.isBlock() && !substitute.getItemAsBlock().getBlock().canBeCarried()) {
//                                    Minecraft.setErrorMessage("dropBlockAsEntityItem: substitute " + substitute + " can not be carried either. Aborting");
//                                    return num_drops;
//                                }
//
//                                item_stack = substitute;
//                            }
//
//                            if (id_dropped > 0) {
//                                EntityItem entity_item = this.dropBlockAsItem_do(info, item_stack.copy());
//                                if (damage != 0) {
//                                    entity_item.getEntityItem().setItemDamage(damage);
//                                }
//
//                                if (quantity == 1 && chance <= 1.0F && info.tile_entity != null && info.tile_entity.getCustomInvName() != null) {
//                                    entity_item.getEntityItem().setItemName(info.tile_entity.getCustomInvName());
//                                }
//
//                                if (chance > 1.0F && info.world.rand.nextFloat() < chance - 1.0F) {
//                                    entity_item = this.dropBlockAsItem_do(info, item_stack.copy());
//                                    if (damage != 0) {
//                                        entity_item.getEntityItem().setItemDamage(damage);
//                                    }
//                                }
//
//                                ++num_drops;
//                            }
//                        }
//                    }
//
//                    if (this.canDropExperienceOrbs()) {
//                        xp_reward_per_unit = Item.getItem(id_dropped).getExperienceReward(subtype);
//                        if (xp_reward_per_unit > 0) {
//                            this.dropXpOnBlockBreak(info.world, info.x, info.y, info.z, xp_reward_per_unit * num_drops);
//                        }
//                    }
//
//                    return num_drops;
//                }
//            } else {
//                return 0;
//            }
//        }
//    }
    @Shadow
    @Final
    public Material blockMaterial;
    public Block block;
    @Shadow
    public final int dropBlockAsEntityItem(BlockBreakInfo info, int id_dropped, int subtype, int quantity, float chance) {
        return 1;
    }
    @Shadow
    private EntityItem dropBlockAsItem_do(BlockBreakInfo info, ItemStack item_stack) {
        return null;
    }
    @Shadow
    @Final
    private boolean has_item_subtypes;
    @Shadow
    @Final
    private int num_item_subtypes;
    @Shadow
    @Final
    private int[] item_subtypes;
    @Shadow
    @Final
    public List getItemStacks() {
        return null;
    }
    @Shadow
    public boolean isSolid(boolean[] is_solid, int metadata) {
        return true;
    }
    @Shadow
    @Final
    public boolean isSolid(int metadata) {
        return this.is_solid[metadata];
    }
    @Shadow
    public CreativeModeTab D() {
        return null;
    }
    @Shadow
    public boolean canSilkHarvest(int metadata) {
        return false;
    }
    @Shadow
    public boolean canBeCarried() {
        return true;
    }
    @Shadow
    public int getRenderType() {
        return 0;
    }

    @Shadow
    @Final
    public boolean is_normal_cube;
    @Shadow
    @Final
    public boolean renderAsNormalBlock() {
        return false;
    }
    @Shadow
    public boolean isValidMetadata(int metadata) {
        return false;
    }
    @Shadow
    @Final
    public boolean isAlwaysOpaqueStandardFormCube() {
        return false;
    }
    @Shadow
    @Final
    public boolean neverHidesAdjacentFaces() {
        return false;
    }
    @Shadow
    @Final
    public final boolean isAlwaysStandardFormCube() {
        return false;
    }
    @Shadow
    @Final
    public int blockID;
    @Shadow
    @Final
    private boolean[] use_neighbor_brightness;
    @Shadow
    private int max_stack_size = 4;
    @Shadow
    public boolean has_grass_top_icon;
    @Shadow
    @Final
    private boolean[] is_solid;
    @Shadow
    @Final
    public boolean is_always_solid;
    @Shadow
    @Final
    public boolean is_never_solid;
    @Shadow
    @Final
    private boolean[] is_standard_form_cube;
    @Shadow
    @Final
    public boolean is_always_standard_form_cube;
    @Shadow
    @Final
    public boolean is_never_standard_form_cube;
    @Shadow
    @Final
    private boolean[] is_solid_and_standard_form_cube;
    @Shadow
    @Final
    public boolean is_always_solid_standard_form_cube;
    @Shadow
    @Final
    public boolean is_never_solid_standard_form_cube;
    @Shadow
    @Final
    public boolean is_always_opaque_standard_form_cube;
    @Shadow
    @Final
    public boolean is_never_opaque_standard_form_cube;
    @Shadow
    @Final
    public boolean is_always_solid_opaque_standard_form_cube;
    @Shadow
    @Final
    public boolean is_never_solid_opaque_standard_form_cube;
    @Shadow
    @Final
    public boolean never_hides_adjacent_faces;
    @Shadow
    @Final
    public boolean is_always_legal;
    @Shadow
    @Final
    public boolean is_always_immutable;

    @Shadow
    protected void dropXpOnBlockBreak(World par1World, int par2, int par3, int par4, int par5) {
    }

    @Shadow
    public boolean canDropExperienceOrbs() {
        return false;
    }

    @Shadow
    public ItemStack createStackedBlock(int metadata) {
        return null;
    }


    @Shadow protected Block setResistance(float par1){
        return null;
    };

    public String getItemDisplayName(ItemStack itemStack){
        return ("" + LocaleI18n.translateToLocal(itemStack.getItem().getUnlocalizedNameInefficiently(itemStack) + ".name")).trim();
    }

    public Block setBlockHardness(float resistance) {
        return this.setHardness(resistance);
    }

    public Block setExplosionResistance(float v) {
        return this.setResistance(v);
    }

    public Block setBlockLightLevel(float v){
        return this.setLightValue(v);
    }

    @Shadow
    protected Block setHardness(float par1) {
        return null;
    }

    @Shadow
    protected Block setLightValue(float exp) {
        return null;
    }

    public Block setResourceLocation(String location) {
        return this.setTextureName(location);
    }

    @Shadow
    protected Block setStepSound(StepSound par1StepSound) {
        return null;
    }

    public Block setStepSound_(StepSound stepSound) {
        return this.setStepSound(stepSound);
    }

    @Shadow
    protected Block setTextureName(String par1Str) {
        return null;
    }

}
