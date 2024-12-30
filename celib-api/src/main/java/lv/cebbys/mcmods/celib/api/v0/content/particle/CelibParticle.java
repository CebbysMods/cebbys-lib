package lv.cebbys.mcmods.celib.api.v0.content.particle;

import lv.cebbys.mcmods.celib.api.v0.math.Vector3;

public interface CelibParticle {
    Vector3<Double> getPosition();

    Vector3<Double> getDirection();
}
