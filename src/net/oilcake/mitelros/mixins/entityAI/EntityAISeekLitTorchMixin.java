package net.oilcake.mitelros.mixins.entityAI;

import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityAISeekLitTorch.class)
public class EntityAISeekLitTorchMixin extends PathfinderGoal {
    @Shadow
    private EntityInsentient task_owner;
    @Overwrite
    protected PathEntity findPathToLitTorch() {
        int max_candidates = 8;
        int[] candidate_x = new int[max_candidates];
        int[] candidate_y = new int[max_candidates];
        int[] candidate_z = new int[max_candidates];
        double[] candidate_distance_sq = new double[max_candidates];
        int max_distance = 16;
        int[] block_ids = new int[]{Block.torchWood.blockID, Block.torchRedstoneActive.blockID, Block.pumpkinLantern.blockID, Blocks.torchWoodIdle.blockID};
        int candidates = this.task_owner.worldObj.getNearestBlockCandidates(this.task_owner.posX, this.task_owner.posY + (double)(this.task_owner.height * 0.75F), this.task_owner.posZ, max_distance, max_distance / 4, max_candidates, block_ids, candidate_x, candidate_y, candidate_z, candidate_distance_sq);
        if (candidates == 0) {
            return null;
        } else {
            for(int candidate_index = 0; candidate_index < candidates; ++candidate_index) {
                PathEntity path = this.task_owner.getNavigator().getPathToXYZ(candidate_x[candidate_index], candidate_y[candidate_index], candidate_z[candidate_index], max_distance);
                if (path != null) {
                    PathPoint final_point = path.getFinalPathPoint();
                    if (this.task_owner.isNearLitTorch(final_point.xCoord, final_point.yCoord, final_point.zCoord)) {
                        return path;
                    }
                }
            }

            return null;
        }
    }

    @Shadow
    public boolean shouldExecute() {
        return false;
    }
}
