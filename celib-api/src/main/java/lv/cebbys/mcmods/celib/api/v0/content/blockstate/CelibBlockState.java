package lv.cebbys.mcmods.celib.api.v0.content.blockstate;

import lv.cebbys.mcmods.celib.api.v0.content.block.CelibBlock;
import lv.cebbys.mcmods.celib.api.v0.content.block.properties.Property;

public interface CelibBlockState {
    CelibBlock getBlock();

    Object getInstance();

    <T> T get(Property<T> property);

    <T> CelibBlockState with(Property<T> property, T value);

    <T> boolean has(Property<T> property);
}
