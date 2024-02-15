package net.oilcake.mitelros.mixins.world;

import net.minecraft.*;
import net.oilcake.mitelros.entity.*;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

@Mixin(WorldServer.class)
public class WorldServerMixin extends World {
    private Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
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
                    if(y <= 40){
                        return StuckTagConfig.TagConfig.TagInvisibleFollower.ConfigValue ? EntityStalkerCreeper.class : entity_class;
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
                    if((calendar.get(Calendar.MONTH) + 1) == 4 && calendar.get(Calendar.DATE) == 1){
                        return EntityZombieLord.class;
                    }else{
                        return entity_class;
                    }
                }
            }
            //new
            else if (entity_class == EntityClusterSpider.class){
                return entity_class;
            }
            //new
            else if (entity_class == EntityInvisibleStalker.class) {
                if (!check_depth || y <= 40) {
                    if (this.rand.nextInt(40) >= y && this.rand.nextFloat() < 0.5F) {
                        return EntityGhost.class;
                    }
                    return entity_class;
                }
            } else if (entity_class == EntityEarthElemental.class) {
                if (!check_depth || y <= 40) {
                    return entity_class;
                }
            } else if (entity_class == EntityRetinueZombie.class) {
                if (!check_depth || y <= 32) {
                    return entity_class;
                }
            } else if (entity_class == EntityBoneBodyguard.class) {
                if (!check_depth || y <= 32) {
                    return entity_class;
                }
            } else if (entity_class == EntityZombie.class) {
                if (!check_depth || (y <= 32 && this.rand.nextFloat() <= 0.25F ) || is_blood_moon_up) {
                    return EntityRetinueZombie.class;
                }
                return entity_class;
            } else if (entity_class == EntitySkeleton.class) {
                if (!check_depth || (y <= 32 && this.rand.nextFloat() <= 0.25F ) || is_blood_moon_up) {
                    return EntityBoneBodyguard.class;
                }
                return entity_class;
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
    @Overwrite
    protected void tickBlocksAndAmbiance() {
        super.tickBlocksAndAmbiance();
        int var1 = 0;
        int var2 = 0;
        Iterator var3 = this.activeChunkSet.iterator();
        boolean perform_random_block_ticks = this.shouldRandomBlockTicksBePerformed();
        boolean is_blood_moon = this.isBloodMoon24HourPeriod();

        for(int rarity_of_lightning = is_blood_moon ? (StuckTagConfig.TagConfig.TagUnstableConvection.ConfigValue ? 5000 : 20000) :(StuckTagConfig.TagConfig.TagUnstableConvection.ConfigValue ? 25000 : 100000); var3.hasNext(); this.theProfiler.endSection()) {
            ChunkCoordIntPair var4 = (ChunkCoordIntPair)var3.next();
            int var5 = var4.chunkXPos * 16;
            int var6 = var4.chunkZPos * 16;
            this.theProfiler.startSection("getChunk");
            Chunk var7 = this.getChunkFromChunkCoords(var4.chunkXPos, var4.chunkZPos);
            this.moodSoundAndLightCheck(var5, var6, var7);
            this.theProfiler.endStartSection("tickChunk");
            var7.updateSkylight(false);
            var7.performPendingSandFallsIfPossible();
            this.theProfiler.endStartSection("thunder");
            int var8;
            int var9;
            int var10;
            int var11;
            if (this.rand.nextInt(rarity_of_lightning) == 0 && this.isPrecipitating(true) && this.isThundering(true)) {
                this.updateLCG = this.updateLCG * 3 + 1013904223;
                var8 = this.updateLCG >> 2;
                var9 = var5 + (var8 & 15);
                var10 = var6 + (var8 >> 8 & 15);
                var11 = this.getPrecipitationHeight(var9, var10);
                if (this.canLightningStrikeAt(var9, var11, var10)) {
                    this.addWeatherEffect(new EntityLightning(this, (double)var9, (double)var11, (double)var10));
                }
            }

            this.theProfiler.endStartSection("iceandsnow");
            int var13;
            if (this.rand.nextInt(16) == 0) {
                this.updateLCG = this.updateLCG * 3 + 1013904223;
                var8 = this.updateLCG >> 2;
                var9 = var8 & 15;
                var10 = var8 >> 8 & 15;
                var11 = this.getPrecipitationHeight(var9 + var5, var10 + var6);
                if (this.isBlockFreezableNaturally(var9 + var5, var11 - 1, var10 + var6)) {
                    this.setBlock(var9 + var5, var11 - 1, var10 + var6, Block.ice.blockID);
                }

                if (this.isPrecipitating(true) && this.canSnowAt(var9 + var5, var11, var10 + var6)) {
                    this.placeSnowfallAt(var9 + var5, var11, var10 + var6);
                }

                if (this.isPrecipitating(true)) {
                    BiomeBase var12 = this.getBiomeGenForCoords(var9 + var5, var10 + var6);
                    if (var12.canSpawnLightningBolt(is_blood_moon)) {
                        var13 = this.getBlockId(var9 + var5, var11 - 1, var10 + var6);
                        if (var13 != 0) {
                            Block.blocksList[var13].fillWithRain(this, var9 + var5, var11 - 1, var10 + var6);
                        }
                    }
                }
            }

            this.theProfiler.endStartSection("tickTiles");
            ChunkSection[] var19 = var7.getBlockStorageArray();
            var9 = var19.length;

            for(var10 = 0; var10 < var9; ++var10) {
                ChunkSection var21 = var19[var10];
                if (var21 != null && var21.getNeedsRandomTick()) {
                    int y_location = var21.getYLocation();

                    for(int var20 = 0; var20 < 3; ++var20) {
                        this.updateLCG = this.updateLCG * 3 + 1013904223;
                        var13 = this.updateLCG >> 2;
                        int var14 = var13 & 15;
                        int var15 = var13 >> 8 & 15;
                        int var16 = var13 >> 16 & 15;
                        int var17 = var21.getExtBlockID(var14, var16, var15);
                        ++var2;
                        Block var18 = Block.blocksList[var17];
                        if (var18 != null && var18.getTickRandomly()) {
                            ++var1;
                            if (perform_random_block_ticks) {
                                var18.updateTick(this, var14 + var5, var16 + y_location, var15 + var6, this.rand);
                            }
                        }
                    }
                }
            }

            if (var7.last_total_world_time == 0L) {
                var7.last_total_world_time = this.getTotalWorldTime();
            } else {
                ++var7.last_total_world_time;
            }
        }

    }
    @Shadow
    private boolean shouldRandomBlockTicksBePerformed() {
        return false;
    }
    @Shadow
    public boolean placeSnowfallAt(int x, int y, int z) {
        return false;
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
