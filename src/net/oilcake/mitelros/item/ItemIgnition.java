package net.oilcake.mitelros.item;

import net.minecraft.*;

public class ItemIgnition extends Item implements IDamageableItem {
    public ItemIgnition(int par1, Material material) {
        super(par1, material, "ignition");
        this.setMaxDamage((int)(2.0F * material.getDurability() + (float)(material == Material.wood ? 3 : 0)));
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeModeTab.tabTools);
        this.addMaterial(Material.flint);
    }

    @Override
    public int getNumComponentsForDurability() {
        return 1;
    }
    public int getRepairCost() {
        return 0;
    }

    private void makeIgniteSoundAndApplyDamage(EntityPlayer player) {
        if (player.onClient()) {
            Minecraft.setErrorMessage("makeIgniteSoundAndApplyDamage: not meant to be called on client");
        } else {
            player.worldObj.playSoundAtEntity(player, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
            player.tryDamageHeldItem(DamageSource.generic, 1);
        }
    }

    @Override
    public boolean tryEntityInteraction(Entity entity, EntityPlayer player, ItemStack item_stack) {
        if (player.onServer()) {
            boolean entity_can_be_ignited = false;
            if (entity instanceof EntityChicken) {
                entity_can_be_ignited = true;
            } else if (entity instanceof EntitySheep) {
                EntitySheep sheep = (EntitySheep)entity;
                if (!sheep.getSheared()) {
                    entity_can_be_ignited = true;
                }
            } else if (entity instanceof EntityWolf) {
                if (entity instanceof EntityHellhound) {
                    entity.getAsEntityLiving().setTarget(player);
                } else if (entity instanceof EntityDireWolf) {
                    if (entity.getAsEntityTameable().isTamed()) {
                        entity_can_be_ignited = true;
                    }

                    entity.getAsEntityLiving().setTarget(player);
                } else {
                    entity_can_be_ignited = true;
                }
            }

            if (entity_can_be_ignited) {
                entity.setFire(6);
            }

            this.makeIgniteSoundAndApplyDamage(player);
        }

        return true;
    }
    @Override
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        RaycastCollision rc = player.getSelectedObject(partial_tick, false);
        if (rc == null) {
            return false;
        } else if (!rc.isBlock()) {
            return false;
        } else {
            if (rc.getBlockHit() == Block.tnt) {
                if (player.onServer()) {
                    BlockTNT.ignite(rc.world, rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, player);
                }
            }else if( rc.getBlockHit() instanceof BlockFurnace){
                TileEntityFurnace furnace = (TileEntityFurnace) rc.world.getBlockTileEntity(rc.block_hit_x,rc.block_hit_y,rc.block_hit_z);
                furnace.activateFurnace();
            } else {
                if (!rc.isNeighborAirBlock() || !rc.canPlayerEditNeighborOfBlockHit(player, player.getHeldItemStack())) {
                    return false;
                }

                if (player.onServer()) {
                    rc.setNeighborBlock(Block.spark);
                }
            }

            if (player.onClient()) {
                player.swingArm();
            } else {
                rc.world.playSoundAtEntity(player, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
                player.tryDamageHeldItem(DamageSource.generic, 1);
            }

            return true;
        }
    }
}
