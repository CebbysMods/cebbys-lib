package lv.cebbys.mcmods.celib.handlers.datagen.resources.helper;

import lv.cebbys.mcmods.celib.handlers.datagen.DataType;

import java.io.InputStream;

public abstract class CelibResource<T> {
    public abstract DataType dataType();
    public abstract InputStream toStream();
    protected abstract Class<T> builderClass();
}
