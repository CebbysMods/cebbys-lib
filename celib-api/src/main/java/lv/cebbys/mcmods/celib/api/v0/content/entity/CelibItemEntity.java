package lv.cebbys.mcmods.celib.api.v0.content.entity;

import jakarta.annotation.Nonnull;
import lv.cebbys.mcmods.celib.api.v0.content.item.CelibItem;
import lv.cebbys.mcmods.celib.api.v0.content.item.CelibItemStack;
import lv.cebbys.mcmods.celib.api.v0.level.CelibLevel;
import lv.cebbys.mcmods.celib.api.v0.math.Vector3;

public interface CelibItemEntity extends CelibEntity {
    @Nonnull
    static CelibItemEntity of(CelibLevel level, Vector3<? extends Number> pos, CelibItem item) {
        var doublePos = Vector3.of(pos, Number::doubleValue);
        return null;
    }

    @Nonnull
    static <I extends CelibItem> CelibItemEntity of(CelibLevel level, Vector3<? extends Number> pos, CelibItemStack<I> item) {
        var doublePos = Vector3.of(pos, Number::doubleValue);
        return null;
    }
}
