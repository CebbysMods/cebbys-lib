package lv.cebbys.mcmods.celib.core.api.component.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public interface CelibBlockProperties {
    boolean canBeAnimated(BlockState state);

    boolean canBeTicked(BlockState state);

    boolean canBeTickedRandomly(BlockState state);

    BlockBehaviour.Properties asBlockProperties();

    float getExplosionResistance();

    boolean canDropFromExplosion(Explosion explosion);

    float getHardness(BlockState blockState, Player player, BlockGetter blockGetter, BlockPos blockPos);

    float getMiningSpeed(BlockState blockState, Player player, BlockGetter blockGetter, BlockPos blockPos);

    float getHarvestSpeed(BlockState blockState, Player player, BlockGetter blockGetter, BlockPos blockPos);

    default float getBreakingSpeed(BlockState blockState, Player player, BlockGetter blockGetter, BlockPos blockPos) {
        float hardness = getHardness(blockState, player, blockGetter, blockPos);
        float speed = getMiningSpeed(blockState, player, blockGetter, blockPos);
        float harvest = getHarvestSpeed(blockState, player, blockGetter, blockPos);
        return hardness == -1 ? 0.0F : speed / hardness / harvest;
    }
}
