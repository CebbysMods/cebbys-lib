package lv.cebbys.mcmods.celib.core.api.component.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import static lv.cebbys.mcmods.celib.core.api.component.block.CelibBlock.BRIDGE;
import static lv.cebbys.mcmods.celib.core.api.component.block.CelibBlock.LOGGER;

public interface CelibBlockServerEvents extends CelibBlockCommonEvents {
    /**
     * Event occurs when block is broken/removed/moved by piston.
     *
     * @param level    Level of the block
     * @param pos      Position of the block
     * @param state    State of the block
     * @param newState New block state
     * @param moved    True if block was moved instead of removed
     */
    default void onRemoveEvent(ServerLevel level, BlockPos pos, BlockState state, BlockState newState, boolean moved) {
        LOGGER.info("[SERVER]{}.onRemoveEvent({}, {})", BRIDGE, newState.getBlock(), moved);
        if (state.hasBlockEntity() && !state.is(newState.getBlock())) {
            level.removeBlockEntity(pos);
        }
    }

    /**
     * Event occurs after the onBreakEvent has occurred as the last event when block was broken.
     *
     * @param level     Level of the block
     * @param pos       Position of the block
     * @param state     State of the block
     * @param itemStack Item in hand of player
     * @param bl
     */
    default void afterDestroyEvent(ServerLevel level, BlockPos pos, BlockState state, ItemStack itemStack, boolean bl) {
        LOGGER.info("[SERVER]{}.afterDestroyEvent({}, {})", BRIDGE, itemStack, bl);
    }

    /**
     * Event occurs when player breaks the block.
     *
     * @param level       Level of the block
     * @param pos         Position of the block
     * @param state       State of the block
     * @param player      Player breaking the block
     * @param blockEntity Entity of the block
     * @param itemStack   Item in hand of player
     */
    default void onBreakByPlayerEvent(ServerLevel level, BlockPos pos, BlockState state, Player player, @Nullable BlockEntity blockEntity, ItemStack itemStack) {
        LOGGER.info("[SERVER]{}.onBreakByPlayerEvent({})", BRIDGE, itemStack);
        player.awardStat(Stats.BLOCK_MINED.get(state.getBlock()));
        player.causeFoodExhaustion(0.005F);
        Block.dropResources(state, level, pos, blockEntity, player, itemStack);
    }


}
