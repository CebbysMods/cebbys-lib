package lv.cebbys.mcmods.celib.core.bridge;

import lv.cebbys.mcmods.celib.core.api.component.block.events.CelibBlockTransformCommonEvents;
import lv.cebbys.mcmods.celib.core.api.component.block.events.CelibBlockTransformSidedEvents;
import lv.cebbys.mcmods.celib.core.api.component.event.block.AfterBreakByPlayerEventHandler;
import lv.cebbys.mcmods.celib.core.api.component.event.block.AfterBreakEventHandler;
import lv.cebbys.mcmods.celib.core.api.component.event.block.OnBreakByPlayerEventHandler;
import lv.cebbys.mcmods.celib.core.api.component.event.block.OnBreakEventHandler;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class BridgeBlockEventHandler implements CelibBlockTransformSidedEvents {
    private final OnBreakEventHandler<ServerLevel> onServerBreakEvent;
    private final OnBreakEventHandler<ClientLevel> onClientBreakEvent;
    private final AfterBreakEventHandler<ServerLevel> afterServerBreakEvent;
    private final AfterBreakEventHandler<ClientLevel> afterClientBreakEvent;
    private final OnBreakByPlayerEventHandler<ServerLevel, ServerPlayer> onServerBreakByPlayerEvent;
    private final OnBreakByPlayerEventHandler<ClientLevel, LocalPlayer> onClientBreakByPlayerEvent;
    private final AfterBreakByPlayerEventHandler<ServerLevel, ServerPlayer> afterServerBreakByPlayerEvent;
    private final AfterBreakByPlayerEventHandler<ClientLevel, LocalPlayer> afterClientBreakByPlayerEvent;

    public static BridgeBlockEventHandler createDefault(Object object) {
        if (object instanceof CelibBlockTransformCommonEvents common) {
            return new BridgeBlockEventHandler(common);
        } else if (object instanceof CelibBlockTransformSidedEvents sided) {
            return new BridgeBlockEventHandler(sided);
        } else {
            return new BridgeBlockEventHandler();
        }
    }

    public BridgeBlockEventHandler() {
        onServerBreakEvent = OnBreakEventHandler.createNoop();
        onClientBreakEvent = OnBreakEventHandler.createNoop();
        afterServerBreakEvent = AfterBreakEventHandler.createNoop();
        afterClientBreakEvent = AfterBreakEventHandler.createNoop();
        onServerBreakByPlayerEvent = OnBreakByPlayerEventHandler.createNoop();
        onClientBreakByPlayerEvent = OnBreakByPlayerEventHandler.createNoop();
        afterServerBreakByPlayerEvent = AfterBreakByPlayerEventHandler.createNoop();
        afterClientBreakByPlayerEvent = AfterBreakByPlayerEventHandler.createNoop();
    }

    public BridgeBlockEventHandler(@NotNull CelibBlockTransformSidedEvents handler) {
        onServerBreakEvent = OnBreakEventHandler.createServer(handler);
        onClientBreakEvent = OnBreakEventHandler.createClient(handler);
        afterServerBreakEvent = AfterBreakEventHandler.createServer(handler);
        afterClientBreakEvent = AfterBreakEventHandler.createClient(handler);
        onServerBreakByPlayerEvent = OnBreakByPlayerEventHandler.createServer(handler);
        onClientBreakByPlayerEvent = OnBreakByPlayerEventHandler.createClient(handler);
        afterServerBreakByPlayerEvent = AfterBreakByPlayerEventHandler.createServer(handler);
        afterClientBreakByPlayerEvent = AfterBreakByPlayerEventHandler.createClient(handler);
    }

    public BridgeBlockEventHandler(@NotNull CelibBlockTransformCommonEvents handler) {
        onServerBreakEvent = OnBreakEventHandler.createServer(handler);
        onClientBreakEvent = OnBreakEventHandler.createClient(handler);
        afterServerBreakEvent = AfterBreakEventHandler.createServer(handler);
        afterClientBreakEvent = AfterBreakEventHandler.createClient(handler);
        onServerBreakByPlayerEvent = OnBreakByPlayerEventHandler.createServer(handler);
        onClientBreakByPlayerEvent = OnBreakByPlayerEventHandler.createClient(handler);
        afterServerBreakByPlayerEvent = AfterBreakByPlayerEventHandler.createServer(handler);
        afterClientBreakByPlayerEvent = AfterBreakByPlayerEventHandler.createClient(handler);
    }

    @Override
    public void onBreakEvent(ServerLevel level, BlockPos pos, BlockState state) {
        onServerBreakEvent.onBreakEvent(level, pos, state);
    }

    @Override
    public void onBreakEvent(ClientLevel level, BlockPos pos, BlockState state) {
        onClientBreakEvent.onBreakEvent(level, pos, state);
    }

    @Override
    public void afterBreakEvent(ServerLevel level, BlockPos pos, BlockState oldState, BlockState newState) {
        afterServerBreakEvent.afterBreakEvent(level, pos, oldState, newState);
    }

    @Override
    public void afterBreakEvent(ClientLevel level, BlockPos pos, BlockState oldState, BlockState newState) {
        afterClientBreakEvent.afterBreakEvent(level, pos, oldState, newState);
    }

    @Override
    public void onBreakByPlayerEvent(ServerLevel level, BlockPos pos, BlockState state, ServerPlayer player, ItemStack held) {
        onServerBreakByPlayerEvent.onBreakByPlayerEvent(level, pos, state, player, held);
    }

    @Override
    public void onBreakByPlayerEvent(ClientLevel level, BlockPos pos, BlockState state, LocalPlayer player, ItemStack held) {
        onClientBreakByPlayerEvent.onBreakByPlayerEvent(level, pos, state, player, held);
    }

    @Override
    public void afterBreakByPlayerEvent(ServerLevel level, BlockPos pos, BlockState oldState, BlockState newState, ServerPlayer player, ItemStack held) {
        afterServerBreakByPlayerEvent.afterBreakByPlayerEvent(level, pos, oldState, newState, player, held);
    }

    @Override
    public void afterBreakByPlayerEvent(ClientLevel level, BlockPos pos, BlockState oldState, BlockState newState, LocalPlayer player, ItemStack held) {
        afterClientBreakByPlayerEvent.afterBreakByPlayerEvent(level, pos, oldState, newState, player, held);
    }
}
