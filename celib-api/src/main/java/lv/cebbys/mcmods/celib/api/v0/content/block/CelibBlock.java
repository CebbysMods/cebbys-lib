package lv.cebbys.mcmods.celib.api.v0.content.block;

import lv.cebbys.mcmods.celib.api.v0.content.block.properties.CelibBlockProperties;
import lv.cebbys.mcmods.celib.api.v0.content.blockstate.CelibBlockState;
import lv.cebbys.mcmods.celib.api.v0.content.item.CelibBlockItem;
import lv.cebbys.mcmods.celib.api.v0.content.item.CelibItemStack;
import lv.cebbys.mcmods.celib.api.v0.level.CelibLevel;
import lv.cebbys.mcmods.celib.api.v0.math.CelibDirection;
import lv.cebbys.mcmods.celib.api.v0.math.Vector3;
import lv.cebbys.mcmods.celib.api.v0.bridge.CelibMappers;
import lv.cebbys.mcmods.celib.api.v0.utility.lazy.MinecraftInstance;

public interface CelibBlock extends MinecraftInstance {
    interface PlacementContext extends MinecraftInstance {
        static PlacementContext of(Object ctx) {
            return CelibMappers.BLOCK_PLACEMENT_CONTEXT_MAPPER.get().apply(ctx);
        }

        CelibItemStack<CelibBlockItem> getItemInHand();

        Vector3<Integer> getBlockPos();

        Vector3<Double> getHitPos();

        CelibDirection getHitFace();

        CelibLevel getLevel();
    }

    CelibBlockProperties getProperties();

    CelibBlockState getDefaultBlockState();
}
