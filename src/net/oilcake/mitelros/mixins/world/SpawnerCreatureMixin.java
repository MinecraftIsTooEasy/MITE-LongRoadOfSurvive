package net.oilcake.mitelros.mixins.world;

import net.minecraft.*;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
@Mixin(SpawnerCreature.class)
public class SpawnerCreatureMixin {
    @Shadow
    private final HashMap<ChunkCoordIntPair, Boolean> eligibleChunksForSpawning = new HashMap();
    @Shadow
    private final Random random = new Random();

    @Shadow
    public static boolean canCreatureTypeSpawnAtLocation(EnumCreatureType creature_type, World world, int x, int y, int z, boolean initial_spawn, double[] resulting_y_pos) {
        return false;
    }

    @Shadow
    protected static ChunkPosition getRandomSpawningPointInChunk(World par0World, int par1, int par2) {
        return null;
    }

    @Shadow
    private Class<EntityInsentient> getSubstituteClassToSpawn(World world, int y, Class<?> suitable_creature_class) {
        return null;
    }

    @Shadow
    private float tryHangBatFromCeiling(World world, EntityBat bat, int x, int y, int z, float pos_x, float pos_y, float pos_z) {
        return 0.0F;
    }

    @Overwrite
    public int trySpawningHostileMobs(WorldServer world, boolean deep_only) {
        EnumCreatureType creature_type = EnumCreatureType.monster;
        boolean is_overworld = world.isOverworld();
        boolean is_new_moon = world.isNewMoon();
        boolean is_full_moon = world.isFullMoon();
        boolean is_blood_moon = world.isBloodMoon(false);
        boolean is_blue_moon = world.isBlueMoon(false);
        float min_distance_from_players = 24.0F;
        float min_distance_from_spawn_sq = 576.0F;
        boolean is_daytime = world.isDaytime();
        int creature_limit = Math.min(4096 , Math.max(32 , world.getDayOfWorld()) + creature_type.getMaxNumberOfCreature() * this.eligibleChunksForSpawning.size() / 64);
        if (deep_only) {
            creature_limit *= 2;
        }

        if (deep_only) {
            world.last_mob_spawn_limit_under_60 = creature_limit;
        } else {
            world.last_mob_spawn_limit_at_60_or_higher = creature_limit;
        }

        if (world.countMobs(deep_only, !deep_only) >= creature_limit) {
            return 0;
        } else {
            boolean try_to_hang_bats_from_ceiling = world.rand.nextBoolean();
            int total_spawned = 0;
            ChunkCoordinates spawn_point = world.getSpawnPoint();
            Iterator eligible_chunk_iterator = this.eligibleChunksForSpawning.keySet().iterator();

            label251:
            while(true) {
                int x;
                int y;
                int z;
                do {
                    while(true) {
                        do {
                            do {
                                ChunkCoordIntPair chunk_coord;
                                do {
                                    if (!eligible_chunk_iterator.hasNext()) {
                                        return total_spawned;
                                    }

                                    chunk_coord = (ChunkCoordIntPair)eligible_chunk_iterator.next();
                                } while((Boolean)this.eligibleChunksForSpawning.get(chunk_coord));

                                ChunkPosition chunk_pos = getRandomSpawningPointInChunk(world, chunk_coord.chunkXPos, chunk_coord.chunkZPos);
                                if (deep_only && chunk_pos.y >= 60) {
                                    chunk_pos = getRandomSpawningPointInChunk(world, chunk_coord.chunkXPos, chunk_coord.chunkZPos);
                                }

                                x = chunk_pos.x;
                                y = chunk_pos.y;
                                z = chunk_pos.z;
                            } while(world.isOverworld() && y == 63 && world.rand.nextInt(4) > 0 && world.getBlock(x, y - 1, z) == Block.ice);
                        } while(world.getClosestPlayer((double)x, (double)y, (double)z, 48.0, true) == null && world.rand.nextInt(2) == 0);

                        if (deep_only) {
                            if (y < 60 && world.countMobs(true, false) < creature_limit) {
                                break;
                            }
                        } else if (y >= 60 && world.countMobs(false, true) < creature_limit) {
                            break;
                        }
                    }
                } while(!canCreatureTypeSpawnAtLocation(creature_type, world, x, y, z, false, (double[])null));

                int num_spawned_below_60 = 0;
                int num_spawned_at_60_or_higher = 0;

                label249:
                for(int var18 = 0; var18 < 3; ++var18) {
                    int x_with_random_offset = x;
                    int z_with_random_offset = z;
                    byte random_offset_range = 6;
                    Class suitable_creature_class = null;
                    GroupDataEntity entity_living_data = null;
                    int var25 = 0;
                    int max_spawn_attempts = 4;

                    while(true) {
                        while(true) {
                            if (var25 >= max_spawn_attempts) {
                                continue label249;
                            }

                            x_with_random_offset += world.rand.nextInt(random_offset_range) - world.rand.nextInt(random_offset_range);
                            z_with_random_offset += world.rand.nextInt(random_offset_range) - world.rand.nextInt(random_offset_range);
                            double[] resulting_y_pos = new double[1];
                            if (!canCreatureTypeSpawnAtLocation(creature_type, world, x_with_random_offset, y, z_with_random_offset, false, resulting_y_pos)) {
                                ++var25;
                            } else {
                                float pos_x = (float)x_with_random_offset + 0.5F;
                                float pos_y = (float)y;
                                float pos_z = (float)z_with_random_offset + 0.5F;
                                pos_y = (float)resulting_y_pos[0];
                                boolean can_spawn_close_to_player = (world.isOverworld() || world.isUnderworld()) && world.getClosestPlayer((double)pos_x, (double)pos_y, (double)pos_z, 24.0, true) != null && world.getBlockLightValue(x_with_random_offset, MathHelper.floor_float(pos_y), z_with_random_offset) == 0 && world.getBlockLightValue(x_with_random_offset, MathHelper.floor_float(pos_y) + 1, z_with_random_offset) == 0;
                                if (can_spawn_close_to_player) {
                                    if (world.getClosestPlayer((double)pos_x, (double)pos_y, (double)pos_z, 8.0, false) != null) {
                                        ++var25;
                                        continue;
                                    }
                                } else if (world.getClosestPlayer((double)pos_x, (double)pos_y, (double)pos_z, 24.0, false) != null) {
                                    ++var25;
                                    continue;
                                }

                                float delta_x = pos_x - (float)spawn_point.posX;
                                float delta_y = pos_y - (float)spawn_point.posY;
                                float delta_z = pos_z - (float)spawn_point.posZ;
                                float distance_from_spawn_point_sq = delta_x * delta_x + delta_y * delta_y + delta_z * delta_z;
                                if (distance_from_spawn_point_sq < 576.0F) {
                                    ++var25;
                                } else {
                                    if (suitable_creature_class == null) {
                                        suitable_creature_class = world.getSuitableCreature(creature_type, x_with_random_offset, y, z_with_random_offset);
                                        if (suitable_creature_class == null) {
                                            continue label249;
                                        }
                                    }

                                    if (suitable_creature_class == EntityGhast.class && world.getClosestPlayer((double)pos_x, (double)pos_y, (double)pos_z, 48.0, false) != null) {
                                        ++var25;
                                    } else {
                                        EntityInsentient entity_living;
                                        try {
                                            entity_living = (EntityInsentient)this.getSubstituteClassToSpawn(world, y, suitable_creature_class).getConstructor(World.class).newInstance(world);
                                        } catch (Exception var43) {
                                            var43.printStackTrace();
                                            return total_spawned;
                                        }

                                        if (entity_living instanceof EntityBat && try_to_hang_bats_from_ceiling) {
                                            pos_y = this.tryHangBatFromCeiling(world, (EntityBat)entity_living, x_with_random_offset, y, z_with_random_offset, pos_x, pos_y, pos_z);
                                        }

                                        if (is_overworld && Entity.isClass(entity_living, EntityPhaseSpider.class)) {
                                            max_spawn_attempts = 64;
                                        } else {
                                            max_spawn_attempts = 4;
                                        }

                                        entity_living.setLocationAndAngles((double)pos_x, (double)pos_y, (double)pos_z, world.rand.nextFloat() * 360.0F, 0.0F);
                                        if (world.isOverworld() && world.isBlueMoonNight() && world.a(EnumSkyBlock.Sky, entity_living.getBlockPosX(), entity_living.getEyeBlockPosY(), entity_living.getBlockPosZ()) > 0) {
                                            ++var25;
                                        } else {
                                            if (entity_living.width < 1.0F) {
                                                if (!entity_living.getCanSpawnHere(true)) {
                                                    ++var25;
                                                    continue;
                                                }
                                            } else {
                                                boolean can_spawn_here = entity_living.getCanSpawnHere(true);
                                                if (!can_spawn_here) {
                                                    entity_living.setLocationAndAngles((double)(pos_x - 0.5F), (double)pos_y, (double)pos_z, world.rand.nextFloat() * 360.0F, 0.0F);
                                                    can_spawn_here = entity_living.getCanSpawnHere(true);
                                                }

                                                if (!can_spawn_here) {
                                                    entity_living.setLocationAndAngles((double)(pos_x + 0.5F), (double)pos_y, (double)pos_z, world.rand.nextFloat() * 360.0F, 0.0F);
                                                    can_spawn_here = entity_living.getCanSpawnHere(true);
                                                }

                                                if (!can_spawn_here) {
                                                    entity_living.setLocationAndAngles((double)pos_x, (double)pos_y, (double)(pos_z - 0.5F), world.rand.nextFloat() * 360.0F, 0.0F);
                                                    can_spawn_here = entity_living.getCanSpawnHere(true);
                                                }

                                                if (!can_spawn_here) {
                                                    entity_living.setLocationAndAngles((double)pos_x, (double)pos_y, (double)(pos_z + 0.5F), world.rand.nextFloat() * 360.0F, 0.0F);
                                                    can_spawn_here = entity_living.getCanSpawnHere(true);
                                                }

                                                if (!can_spawn_here) {
                                                    ++var25;
                                                    continue;
                                                }
                                            }

                                            if (is_daytime) {
                                                if (entity_living.isEntityUndead() && world.isOutdoors(MathHelper.floor_double((double)pos_x), MathHelper.floor_double((double)pos_y), MathHelper.floor_double((double)pos_z))) {
                                                    continue label251;
                                                }
                                            } else {
                                                int chance_of_skipping = is_blue_moon ? 54 : (is_blood_moon ? 2 : (is_full_moon ? 3 : (is_new_moon ? 6 : 4)));
                                                if (world.rand.nextInt(chance_of_skipping) != 0 && world.isOutdoors(MathHelper.floor_double((double)pos_x), MathHelper.floor_double((double)pos_y), MathHelper.floor_double((double)pos_z))) {
                                                    continue label251;
                                                }
                                            }

                                            if (y < 60) {
                                                ++num_spawned_below_60;
                                            } else {
                                                ++num_spawned_at_60_or_higher;
                                            }

                                            entity_living_data = entity_living.onSpawnWithEgg(entity_living_data);
                                            world.spawnEntityInWorld(entity_living);
                                            ++total_spawned;
                                            if (y < 60) {
                                                if (num_spawned_below_60 >= entity_living.getMaxSpawnedInChunk()) {
                                                    continue label251;
                                                }
                                            } else if (num_spawned_at_60_or_higher >= entity_living.getMaxSpawnedInChunk()) {
                                                continue label251;
                                            }

                                            ++var25;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    @Overwrite
    public float calcEffectiveHostileMobSpawningRateModifier(WorldServer world) {
        if (world.provider.dimensionId != 0) {
            return 0.25F;
        } else {
            float hostile_mob_spawning_rate_modifier;
            if(ExperimentalConfig.TagConfig.TagSpawningV2.ConfigValue){
                hostile_mob_spawning_rate_modifier = Math.abs((float)world.getTimeOfDay() - 12000.0F) / 6000.0F ;
                if (hostile_mob_spawning_rate_modifier < 1.0F && (world.isBloodMoon(false) || world.isThundering(true))) {
                    hostile_mob_spawning_rate_modifier = 1.0F;
                }
            }
            else{
                hostile_mob_spawning_rate_modifier = 1.0F;
                if (world.decreased_hostile_mob_spawning_counter > 0) {
                    --world.decreased_hostile_mob_spawning_counter;
                    hostile_mob_spawning_rate_modifier *= 0.5F;
                } else if (this.random.nextInt(24000) == 0) {
                    world.decreased_hostile_mob_spawning_counter = this.random.nextInt(4000) + 1;
                }

                if (world.increased_hostile_mob_spawning_counter > 0) {
                    --world.increased_hostile_mob_spawning_counter;
                    hostile_mob_spawning_rate_modifier *= 2.0F;
                } else if (this.random.nextInt(24000) == 0) {
                    world.increased_hostile_mob_spawning_counter = this.random.nextInt(2000);
                }

                if (world.no_hostile_mob_spawning_counter > 0) {
                    --world.no_hostile_mob_spawning_counter;
                    hostile_mob_spawning_rate_modifier = 0.0F;
                } else if (this.random.nextInt(24000) == 0) {
                    world.no_hostile_mob_spawning_counter = this.random.nextInt(2000) + this.random.nextInt(2000);
                }

                if (hostile_mob_spawning_rate_modifier < 1.0F && (world.isBloodMoon(false) || world.isThundering(true))) {
                    hostile_mob_spawning_rate_modifier = 1.0F;
                }
            }
            return hostile_mob_spawning_rate_modifier;
        }
    }
}
