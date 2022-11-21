package lv.cebbys.mcmods.celib.core.api.component.block.events;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import static lv.cebbys.mcmods.celib.core.api.component.block.CelibBlock.BRIDGE;
import static lv.cebbys.mcmods.celib.core.api.component.block.CelibBlock.LOGGER;

public interface CelibBlockTransformCommonEvents {
    default void onBreakEvent(Level level, BlockPos pos, BlockState state) {
        LOGGER.info("[COMMON] {}.onBreakEvent", BRIDGE);
    }

    default void afterBreakEvent(Level level, BlockPos pos, BlockState oldState, BlockState newState) {
        LOGGER.info("[COMMON] {}.onBreakEvent", BRIDGE);
    }

    default void onBreakByPlayerEvent(Level level, BlockPos pos, BlockState state, Player player, ItemStack held) {
        LOGGER.info("[COMMON] {}.onBreakByPlayerEvent", BRIDGE);
    }

    default void afterBreakByPlayerEvent(Level level, BlockPos pos, BlockState oldState, BlockState newState, Player player, ItemStack held) {
        LOGGER.info("[COMMON] {}.afterBreakByPlayerEvent", BRIDGE);
    }
}
