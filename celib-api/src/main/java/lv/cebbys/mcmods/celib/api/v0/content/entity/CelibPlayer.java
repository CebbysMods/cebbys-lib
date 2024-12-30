package lv.cebbys.mcmods.celib.api.v0.content.entity;

import lv.cebbys.mcmods.celib.api.v0.constant.game.GameMode;
import lv.cebbys.mcmods.celib.api.v0.content.blockstate.CelibBlockState;

public interface CelibPlayer extends CelibEntity {
    GameMode getGameMode();

    boolean canHarvest(CelibBlockState state);

    default boolean isGameMode(GameMode mode) {
        return getGameMode().equals(mode);
    }
}
