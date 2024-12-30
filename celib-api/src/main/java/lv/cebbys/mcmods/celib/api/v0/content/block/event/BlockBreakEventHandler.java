package lv.cebbys.mcmods.celib.api.v0.content.block.event;

import lv.cebbys.mcmods.celib.api.v0.content.block.event.destroy.BlockBreakEvent;

public interface BlockBreakEventHandler {
    void onDestroyed(BlockBreakEvent event);
}
