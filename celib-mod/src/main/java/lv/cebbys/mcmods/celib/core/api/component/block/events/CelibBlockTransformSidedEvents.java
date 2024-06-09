package lv.cebbys.mcmods.celib.core.api.component.block.events;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import static lv.cebbys.mcmods.celib.core.api.component.block.CelibBlock.BRIDGE;
import static lv.cebbys.mcmods.celib.core.api.component.block.CelibBlock.LOGGER;

public interface CelibBlockTransformSidedEvents {
    default void onBreakEvent(ServerLevel level, BlockPos pos, BlockState state) {
        LOGGER.info("[SERVER] {}.onBreakEvent", BRIDGE);
    }

    default void onBreakEvent(ClientLevel level, BlockPos pos, BlockState state) {
        LOGGER.info("[CLIENT] {}.onBreakEvent", BRIDGE);
    }

    default void afterBreakEvent(ServerLevel level, BlockPos pos, BlockState oldState, BlockState newState) {
        LOGGER.info("[SERVER] {}.afterBreakEvent", BRIDGE);
    }

    default void afterBreakEvent(ClientLevel level, BlockPos pos, BlockState oldState, BlockState newState) {
        LOGGER.info("[CLIENT] {}.afterBreakEvent", BRIDGE);
    }

    default void onBreakByPlayerEvent(ServerLevel level, BlockPos pos, BlockState state, ServerPlayer player, ItemStack held) {
        LOGGER.info("[SERVER] {}.onBreakByPlayerEvent", BRIDGE);
    }

    default void onBreakByPlayerEvent(ClientLevel level, BlockPos pos, BlockState state, LocalPlayer player, ItemStack held) {
        LOGGER.info("[CLIENT] {}.onBreakByPlayerEvent", BRIDGE);
    }

    default void afterBreakByPlayerEvent(ServerLevel level, BlockPos pos, BlockState oldState, BlockState newState, ServerPlayer player, ItemStack held) {
        LOGGER.info("[SERVER] {}.afterBreakByPlayerEvent", BRIDGE);
    }

    default void afterBreakByPlayerEvent(ClientLevel level, BlockPos pos, BlockState oldState, BlockState newState, LocalPlayer player, ItemStack held) {
        LOGGER.info("[CLIENT] {}.afterBreakByPlayerEvent", BRIDGE);
    }
}
