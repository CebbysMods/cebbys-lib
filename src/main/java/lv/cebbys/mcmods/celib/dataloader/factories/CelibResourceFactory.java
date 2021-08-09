package lv.cebbys.mcmods.celib.dataloader.factories;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lv.cebbys.mcmods.celib.loggers.CelibLogger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class CelibResourceFactory<F> {

    protected abstract Class<F> builderClass();
    public abstract JsonElement toJson();

    @SuppressWarnings("unchecked")
    protected F createBuilder() {
        Class<F> factoryClass = this.builderClass();
        Constructor<?>[] constructors = factoryClass.getDeclaredConstructors();
        Constructor<?> constructor = null;
        for (Constructor<?> test : constructors) {
            if (test.getParameterCount() == 0) {
                constructor = test;
                break;
            }
        }
        if (constructor != null) {
            try {
                constructor.setAccessible(true);
                return (F) constructor.newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                CelibLogger.error(e, "Failed to create resource factory with constructor " + constructor);
            }
        }
        return null;
    }

    protected JsonElement toJson(Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return new JsonParser().parse(json);
    }
}
