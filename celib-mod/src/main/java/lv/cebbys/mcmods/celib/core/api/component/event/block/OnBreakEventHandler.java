package lv.cebbys.mcmods.celib.core.api.component.event.block;

import lv.cebbys.mcmods.celib.core.api.component.block.events.CelibBlockTransformCommonEvents;
import lv.cebbys.mcmods.celib.core.api.component.block.events.CelibBlockTransformSidedEvents;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface OnBreakEventHandler<L extends Level> {
    static <T extends Level> OnBreakEventHandler<T> createNoop() {
        return (level, pos, state) -> {
        };
    }

    static OnBreakEventHandler<ServerLevel> createServer(CelibBlockTransformSidedEvents handler) {
        if (handler == null) return createNoop();
        return handler::onBreakEvent;
    }

    static OnBreakEventHandler<ClientLevel> createClient(CelibBlockTransformSidedEvents handler) {
        if (handler == null) return createNoop();
        return handler::onBreakEvent;
    }

    static OnBreakEventHandler<ServerLevel> createServer(CelibBlockTransformCommonEvents handler) {
        if (handler == null) return createNoop();
        return handler::onBreakEvent;
    }

    static OnBreakEventHandler<ClientLevel> createClient(CelibBlockTransformCommonEvents handler) {
        if (handler == null) return createNoop();
        return handler::onBreakEvent;
    }

    void onBreakEvent(L level, BlockPos pos, BlockState state);
}
