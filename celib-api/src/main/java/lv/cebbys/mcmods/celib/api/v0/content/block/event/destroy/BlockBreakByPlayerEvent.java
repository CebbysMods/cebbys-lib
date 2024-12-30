package lv.cebbys.mcmods.celib.api.v0.content.block.event.destroy;

import lv.cebbys.mcmods.celib.api.v0.content.entity.CelibPlayer;

public interface BlockBreakByPlayerEvent extends BlockBreakEvent {
    CelibPlayer getPlayer();
}
