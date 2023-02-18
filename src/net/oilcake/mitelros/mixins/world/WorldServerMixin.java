package net.oilcake.mitelros.mixins.world;

import net.minecraft.*;
import net.minecraft.server.MinecraftServer;
import net.oilcake.mitelros.entity.EntityClusterSpider;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Iterator;
import java.util.List;

@Mixin(WorldServer.class)
public class WorldServerMixin extends World {
    public WorldServerMixin(IDataManager par1ISaveHandler, String par2Str, WorldProvider par3WorldProvider, WorldSettings par4WorldSettings, MethodProfiler par5Profiler, IConsoleLogManager par6ILogAgent, long world_creation_time, long total_world_time) {
        super(par1ISaveHandler, par2Str, par3WorldProvider, par4WorldSettings, par5Profiler, par6ILogAgent, world_creation_time, total_world_time);
    }

    @Overwrite
    public Class getSuitableCreature(EnumCreatureType creature_type, int x, int y, int z) {
        boolean check_depth = this.isOverworld();
        boolean is_blood_moon_up = this.isBloodMoon(true);
        boolean is_freezing_biome = this.getBiomeGenForCoords(x, z).isFreezing();
        boolean is_desert_biome = this.getBiomeGenForCoords(x, z).isDesertBiome();
        boolean can_spawn_ghouls_on_surface = is_blood_moon_up;
        boolean can_spawn_wights_on_surface = is_blood_moon_up && is_freezing_biome;
        boolean can_spawn_shadows_on_surface = is_blood_moon_up && is_desert_biome;
        boolean can_spawn_revenants_on_surface = is_blood_moon_up;
        boolean can_spawn_bone_lords_on_surface = is_blood_moon_up;

        for(int attempt = 0; attempt < 16; ++attempt) {
            List possible_creatures = this.getChunkProvider().getPossibleCreatures(creature_type, x, y, z);
            if (possible_creatures == null || possible_creatures.isEmpty()) {
                return null;
            }

            BiomeMeta entry = (BiomeMeta) WeightedRandom.getRandomItem(this.rand, possible_creatures);
            Class entity_class = entry.entityClass;
            if (entity_class == EntityCreeper.class) {
                if (!this.hasSkylight() || this.isDaytime() || this.rand.nextInt(4) == 0 || !this.isOutdoors(x, y, z)) {
                    if (this.rand.nextInt(40) >= y && this.rand.nextFloat() < 0.5F) {
                        return EntityInfernalCreeper.class;
                    }

                    return entity_class;
                }
            } else if (entity_class == EntitySlime.class) {
                if (!this.blockTypeIsAbove(Block.stone, x, y, z)) {
                    return entity_class;
                }
            } else if (entity_class == EntityGhoul.class) {
                if (!check_depth || y <= 56 || can_spawn_ghouls_on_surface) {
                    return entity_class;
                }
            } else if (entity_class == EntityJelly.class) {
                if (this.blockTypeIsAbove(Block.stone, x, y, z)) {
                    return entity_class;
                }
            } else if (entity_class == EntityWight.class) {
                if (!check_depth || y <= 48 || can_spawn_wights_on_surface) {
                    return entity_class;
                }
            } else if (entity_class == EntityVampireBat.class) {
                if (!check_depth || y <= 48 || is_blood_moon_up) {
                    return entity_class;
                }
            } else if (entity_class == EntityRevenant.class) {
                if (!check_depth || y <= 44 || can_spawn_revenants_on_surface) {
                    return entity_class;
                }
            }
            //new
            else if (entity_class == EntityClusterSpider.class){
                return entity_class;
            }
            //new
            else if (entity_class == EntityInvisibleStalker.class) {
                if (!check_depth || y <= 40) {
                    return entity_class;
                }
            } else if (entity_class == EntityEarthElemental.class) {
                if (!check_depth || y <= 40) {
                    return entity_class;
                }
            } else if (entity_class == EntityBlob.class) {
                if ((!check_depth || y <= 40) && this.blockTypeIsAbove(Block.stone, x, y, z)) {
                    return entity_class;
                }
            } else if (entity_class == EntityOoze.class) {
                if ((!check_depth || y <= 32) && this.getBlock(x, y - 1, z) == Block.stone && this.blockTypeIsAbove(Block.stone, x, y, z)) {
                    return entity_class;
                }
            } else if (entity_class == EntityNightwing.class) {
                if (!check_depth || y <= 32 || is_blood_moon_up) {
                    return entity_class;
                }
            } else if (entity_class == EntityBoneLord.class) {
                if (!check_depth || y <= 32 || can_spawn_bone_lords_on_surface) {
                    return entity_class;
                }
            } else if (entity_class == EntityPudding.class) {
                if ((!check_depth || y <= 24) && this.getBlock(x, y - 1, z) == Block.stone && this.blockTypeIsAbove(Block.stone, x, y, z)) {
                    return entity_class;
                }
            } else if (entity_class != EntityDemonSpider.class && entity_class != EntityPhaseSpider.class) {
                if (entity_class == EntityHellhound.class) {
                    if (!check_depth || y <= 32) {
                        return entity_class;
                    }
                } else if (entity_class == EntityShadow.class) {
                    if (!check_depth || y <= 32 || can_spawn_shadows_on_surface) {
                        return entity_class;
                    }
                } else if (entity_class == EntitySpider.class) {
                    if (!this.hasSkylight() || this.rand.nextInt(4) != 0 || !this.isOutdoors(x, y, z)) {
                        return entity_class;
                    }
                } else if (entity_class == EntityWoodSpider.class) {
                    if ((this.canBlockSeeTheSky(x, y, z) || this.blockTypeIsAbove(Block.leaves, x, y, z) || this.blockTypeIsAbove(Block.wood, x, y, z)) && this.blockTypeIsNearTo(Block.wood.blockID, x, y, z, 5, 2) && this.blockTypeIsNearTo(Block.leaves.blockID, x, y + 5, z, 5, 5)) {
                        return entity_class;
                    }
                } else {
                    if (entity_class != EntityBlackWidowSpider.class) {
                        if (entity_class == EntityGhast.class) {
                            Iterator i = this.loadedEntityList.iterator();

                            while(i.hasNext()) {
                                Entity entity = (Entity)i.next();
                                if (entity instanceof EntityGhast && entity.getDistanceSqToBlock(x, y, z) < 2304.0 && this.rand.nextFloat() < 0.8F) {
                                    entity_class = null;
                                }
                            }
                        }

                        return entity_class;
                    }

                    if (!(this.rand.nextFloat() < 0.5F)) {
                        return entity_class;
                    }
                }
            } else if (!check_depth || y <= 32) {
                return entity_class;
            }
        }

        return null;
    }
    @Shadow
    protected IChunkProvider createChunkProvider() {
        return null;
    }
    @Shadow
    public Entity getEntityByID(int i) {
        return null;
    }
}
