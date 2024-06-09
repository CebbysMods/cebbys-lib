package lv.cebbys.mcmods.celib.core.api.component.event.block;

import lv.cebbys.mcmods.celib.core.api.component.block.events.CelibBlockTransformCommonEvents;
import lv.cebbys.mcmods.celib.core.api.component.block.events.CelibBlockTransformSidedEvents;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface AfterBreakByPlayerEventHandler<L extends Level, P extends Player> {
    static <T extends Level, K extends Player> AfterBreakByPlayerEventHandler<T, K> createNoop() {
        return (level, pos, oldState, newState, player, held) -> {
        };
    }

    static AfterBreakByPlayerEventHandler<ServerLevel, ServerPlayer> createServer(CelibBlockTransformSidedEvents handler) {
        if (handler == null) return createNoop();
        return handler::afterBreakByPlayerEvent;
    }

    static AfterBreakByPlayerEventHandler<ClientLevel, LocalPlayer> createClient(CelibBlockTransformSidedEvents handler) {
        if (handler == null) return createNoop();
        return handler::afterBreakByPlayerEvent;
    }

    static AfterBreakByPlayerEventHandler<ServerLevel, ServerPlayer> createServer(CelibBlockTransformCommonEvents handler) {
        if (handler == null) return createNoop();
        return handler::afterBreakByPlayerEvent;
    }

    static AfterBreakByPlayerEventHandler<ClientLevel, LocalPlayer> createClient(CelibBlockTransformCommonEvents handler) {
        if (handler == null) return createNoop();
        return handler::afterBreakByPlayerEvent;
    }

    void afterBreakByPlayerEvent(L level, BlockPos pos, BlockState oldState, BlockState newState, P player, ItemStack held);
}
