package net.oilcake.mitelros.mixins.world;

import net.minecraft.Entity;
import net.minecraft.Explosion;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;

import static net.xiaoyu233.fml.util.ReflectHelper.dyCast;

@Mixin(World.class)
public class WorldMixin {
    public Explosion createExplosionC(Entity exploder, double posX, double posY, double posZ, float explosion_size_vs_blocks, float explosion_size_vs_living_entities) {
        return this.newExplosionC(exploder, posX, posY, posZ, explosion_size_vs_blocks, explosion_size_vs_living_entities);
    }

    public Explosion newExplosionC(Entity exploder, double posX, double posY, double posZ, float explosion_size_vs_blocks, float explosion_size_vs_living_entities) {
        Explosion explosion = new Explosion(dyCast(this), exploder, posX, posY, posZ, explosion_size_vs_blocks, explosion_size_vs_living_entities);
        explosion.doExplosionA();
        explosion.affectedBlockPositions.clear();
        explosion.doExplosionB(false);
        return explosion;
    }
}
