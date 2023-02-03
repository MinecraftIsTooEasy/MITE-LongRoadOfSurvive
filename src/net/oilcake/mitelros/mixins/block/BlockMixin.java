package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import static net.minecraft.Block.*;

@Mixin(Block.class)
public class BlockMixin{
    @Shadow
    @Final
    public Material blockMaterial;

    public Block block;
    @Shadow
    private EntityItem dropBlockAsItem_do(BlockBreakInfo info, ItemStack item_stack) {
        return null;
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
    public final int dropBlockAsEntityItem(BlockBreakInfo info, int id_dropped, int subtype, int quantity, float chance) {
        if (info.world.isRemote) {
            Minecraft.setErrorMessage("dropBlockAsEntityItem: not meant to be called on client " + info.x + "," + info.y + "," + info.z);
            return 0;
        } else {
            if (info.block != block) {
                //Minecraft.setErrorMessage("dropBlockAsEntityItem: info.block!=this");
            }

            if (id_dropped >= 1 && quantity >= 1 && !(chance <= 0.0F)) {
                if (info.wasExploded()) {
                    if (this.blockMaterial != Material.snow && this.blockMaterial != Material.craftedSnow && this.blockMaterial != Material.glass) {
                        if (this.blockMaterial != Material.plants && this.blockMaterial != Material.cactus && this.blockMaterial != Material.vine) {
                            if (this.blockMaterial == Material.cake) {
                                chance = 0.0F;
                            }
                        } else {
                            chance = 0.0F;
                        }
                    } else {
                        chance *= 0.4F;
                    }
                }

                if (chance <= 0.0F) {
                    return 0;
                } else {
                    int damage = info.damage;
                    int num_drops = 0;

                    int xp_reward_per_unit;
                    for(xp_reward_per_unit = 0; xp_reward_per_unit < quantity; ++xp_reward_per_unit) {
                        if (info.world.rand.nextFloat() < chance) {
                            ItemStack item_stack = new ItemStack(id_dropped, 1, subtype);
                            if (item_stack.isBlock() && !item_stack.getItemAsBlock().getBlock().canBeCarried()) {
                                ItemStack substitute = item_stack.getItemAsBlock().getBlock().createStackedBlock(subtype);
                                Minecraft.setErrorMessage("dropBlockAsEntityItem: " + item_stack + " can not be carried, substituting with " + substitute);
                                if (substitute == null) {
                                    Minecraft.setErrorMessage("dropBlockAsEntityItem: createStackedBlock returned null for " + item_stack);
                                    return num_drops;
                                }

                                if (substitute.isBlock() && !substitute.getItemAsBlock().getBlock().canBeCarried()) {
                                    Minecraft.setErrorMessage("dropBlockAsEntityItem: substitute " + substitute + " can not be carried either. Aborting");
                                    return num_drops;
                                }

                                item_stack = substitute;
                            }

                            if (id_dropped > 0) {
                                EntityItem entity_item = this.dropBlockAsItem_do(info, item_stack.copy());
                                if (damage != 0) {
                                    entity_item.getEntityItem().setItemDamage(damage);
                                }

                                if (quantity == 1 && chance <= 1.0F && info.tile_entity != null && info.tile_entity.getCustomInvName() != null) {
                                    entity_item.getEntityItem().setItemName(info.tile_entity.getCustomInvName());
                                }

                                if (chance > 1.0F && info.world.rand.nextFloat() < chance - 1.0F) {
                                    entity_item = this.dropBlockAsItem_do(info, item_stack.copy());
                                    if (damage != 0) {
                                        entity_item.getEntityItem().setItemDamage(damage);
                                    }
                                }

                                ++num_drops;
                            }
                        }
                    }

//                    if (this.canDropExperienceOrbs() && !(block instanceof BlockAnvil)) {
//                        xp_reward_per_unit = Item.getItem(id_dropped).getExperienceReward(subtype);
//                        if (xp_reward_per_unit > 0) {
//                            this.dropXpOnBlockBreak(info.world, info.x, info.y, info.z, xp_reward_per_unit * num_drops);
//                        }
//                    }

                    return num_drops;
                }
            } else {
                return 0;
            }
        }
    }

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
