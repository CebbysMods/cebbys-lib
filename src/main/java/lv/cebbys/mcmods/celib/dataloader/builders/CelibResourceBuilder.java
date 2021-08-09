package lv.cebbys.mcmods.celib.dataloader.builders;

import lv.cebbys.mcmods.celib.loggers.CelibLogger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class CelibResourceBuilder<T> {
    protected abstract Class<T> builderClass();
}
