package lv.cebbys.mcmods.celib.core.api.component.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import static lv.cebbys.mcmods.celib.core.api.component.block.CelibBlock.BRIDGE;
import static lv.cebbys.mcmods.celib.core.api.component.block.CelibBlock.LOGGER;

public interface CelibBlockCommonEvents {
    /**
     * Event occurs multiple times while block is being broken by player in survival mode.
     * Event occurs even if the block is not broken at the end.
     *
     * @param level  Level of the block
     * @param pos    Position of the block
     * @param state  BlockState of the block
     * @param player Player breaking the block
     */
    default void whileBreakByPlayerEvent(Level level, BlockPos pos, BlockState state, Player player) {
        LOGGER.info("[COMMON]{}.whileBreakByPlayerEvent", BRIDGE);
    }

    /**
     * Event occurs right after the block is broken but not yet removed by player.
     *
     * @param level  Level of the block
     * @param pos    Position of the block
     * @param state  BlockState of the block
     * @param player Player breaking the block
     */
    default void onBreakByPlayerEvent(Level level, BlockPos pos, BlockState state, Player player) {
        LOGGER.info("[COMMON]{}.onBreakByPlayerEvent", BRIDGE);
        // Spawn break particles
        level.levelEvent(player, 2001, pos, Block.getId(state));
        if (state.is(BlockTags.GUARDED_BY_PIGLINS)) {
            PiglinAi.angerNearbyPiglins(player, false);
        }
        level.gameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Context.of(player, state));
    }


    /**
     * Event occurs after the block has been broken and removed by player.
     *
     * @param level Level of the block
     * @param pos   Position of the block
     * @param state BlockState of the block
     */
    default void afterBreakByPlayerEvent(LevelAccessor level, BlockPos pos, BlockState state) {
        LOGGER.info("[COMMON]{}.afterBreakByPlayerEvent", BRIDGE);
    }


    /**
     * Event occurs when entity is standing on the block.
     *
     * @param level  Level of the block
     * @param pos    Position of the block
     * @param state  BlockState of the block
     * @param entity Entity standing on the block
     */
    default void onStandingOnEvent(Level level, BlockPos pos, BlockState state, Entity entity) {
//        LOGGER.info("[COMMON]{}.onStandingOnEvent", BRIDGE);
    }
}
