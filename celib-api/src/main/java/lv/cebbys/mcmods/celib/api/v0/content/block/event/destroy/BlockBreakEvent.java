package lv.cebbys.mcmods.celib.api.v0.content.block.event.destroy;

import lv.cebbys.mcmods.celib.api.v0.content.blockstate.CelibBlockState;
import lv.cebbys.mcmods.celib.api.v0.level.CelibLevel;
import lv.cebbys.mcmods.celib.api.v0.math.Vector3;

public interface BlockBreakEvent {
    Vector3<Integer> getBlockPos();

    CelibBlockState getBlockState();

    CelibLevel getLevel();
}
