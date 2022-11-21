package lv.cebbys.mcmods.celib.core.api.component.event.block;

import lv.cebbys.mcmods.celib.core.api.component.block.events.CelibBlockTransformCommonEvents;
import lv.cebbys.mcmods.celib.core.api.component.block.events.CelibBlockTransformSidedEvents;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface AfterBreakEventHandler<L extends Level> {
    static <T extends Level> AfterBreakEventHandler<T> createNoop() {
        return (level, pos, oldState, newState) -> {
        };
    }

    static AfterBreakEventHandler<ServerLevel> createServer(CelibBlockTransformSidedEvents handler) {
        if (handler == null) return createNoop();
        return handler::afterBreakEvent;
    }

    static AfterBreakEventHandler<ClientLevel> createClient(CelibBlockTransformSidedEvents handler) {
        if (handler == null) return createNoop();
        return handler::afterBreakEvent;
    }

    static AfterBreakEventHandler<ServerLevel> createServer(CelibBlockTransformCommonEvents handler) {
        if (handler == null) return createNoop();
        return handler::afterBreakEvent;
    }

    static AfterBreakEventHandler<ClientLevel> createClient(CelibBlockTransformCommonEvents handler) {
        if (handler == null) return createNoop();
        return handler::afterBreakEvent;
    }

    void afterBreakEvent(L level, BlockPos pos, BlockState oldState, BlockState newState);
}
