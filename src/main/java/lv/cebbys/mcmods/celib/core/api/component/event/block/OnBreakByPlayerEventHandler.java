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

public interface OnBreakByPlayerEventHandler<L extends Level, P extends Player> {
    static <T extends Level, K extends Player> OnBreakByPlayerEventHandler<T, K> createNoop() {
        return (level, pos, state, player, held) -> {
        };
    }

    static OnBreakByPlayerEventHandler<ServerLevel, ServerPlayer> createServer(CelibBlockTransformSidedEvents handler) {
        if (handler == null) return createNoop();
        return handler::onBreakByPlayerEvent;
    }

    static OnBreakByPlayerEventHandler<ClientLevel, LocalPlayer> createClient(CelibBlockTransformSidedEvents handler) {
        if (handler == null) return createNoop();
        return handler::onBreakByPlayerEvent;
    }

    static OnBreakByPlayerEventHandler<ServerLevel, ServerPlayer> createServer(CelibBlockTransformCommonEvents handler) {
        if (handler == null) return createNoop();
        return handler::onBreakByPlayerEvent;
    }

    static OnBreakByPlayerEventHandler<ClientLevel, LocalPlayer> createClient(CelibBlockTransformCommonEvents handler) {
        if (handler == null) return createNoop();
        return handler::onBreakByPlayerEvent;
    }

    void onBreakByPlayerEvent(L level, BlockPos pos, BlockState oldState, P player, ItemStack held);
}
