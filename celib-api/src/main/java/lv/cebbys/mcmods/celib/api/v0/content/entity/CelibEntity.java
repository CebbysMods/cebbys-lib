package lv.cebbys.mcmods.celib.api.v0.content.entity;

import lv.cebbys.mcmods.celib.api.v0.level.CelibLevel;
import lv.cebbys.mcmods.celib.api.v0.math.Vector3;
import lv.cebbys.mcmods.celib.api.v0.utility.lazy.MinecraftInstance;

public interface CelibEntity extends MinecraftInstance {
    CelibLevel getLevel();

    Vector3<Double> getPosition();
}
