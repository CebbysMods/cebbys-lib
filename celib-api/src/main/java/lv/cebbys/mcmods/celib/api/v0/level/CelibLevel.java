package lv.cebbys.mcmods.celib.api.v0.level;

import lv.cebbys.mcmods.celib.api.v0.content.block.CelibBlock;
import lv.cebbys.mcmods.celib.api.v0.content.blockstate.CelibBlockState;
import lv.cebbys.mcmods.celib.api.v0.content.particle.CelibParticle;
import lv.cebbys.mcmods.celib.api.v0.math.Vector3;
import lv.cebbys.mcmods.celib.api.v0.random.CelibRandom;
import lv.cebbys.mcmods.celib.api.v0.content.entity.CelibEntity;
import lv.cebbys.mcmods.celib.api.v0.utility.lazy.MinecraftInstance;

public interface CelibLevel extends MinecraftInstance {
    CelibBlockState getBlockState(Vector3<Integer> blockPos);

    CelibRandom getRandom();

    boolean canPlace(Vector3<Integer> pos, CelibBlockState state);

    void placeEntity(CelibEntity entity);

    void placeParticle(CelibParticle particle);

    default CelibBlock getBlock(Vector3<Integer> blockPos) {
        return getBlockState(blockPos).getBlock();
    }
}
