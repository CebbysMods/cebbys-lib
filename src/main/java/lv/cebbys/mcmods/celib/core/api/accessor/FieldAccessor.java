package lv.cebbys.mcmods.celib.core.api.accessor;

import java.lang.reflect.Field;

@SuppressWarnings("all")
public class FieldAccessor<P, F> {
    private final Field field;

    public FieldAccessor(Class<P> parentClass, String fieldName) {
        try {
            field = parentClass.getDeclaredField(fieldName);
            field.setAccessible(true);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    public void set(P parent, F value) {
        try {
            field.set(parent, value);
        } catch (Exception ignore) {
        }
    }

    public F get(P parent) {
        try {
            return (F) field.get(parent);
        } catch (Exception ignored) {
            return null;
        }
    }
}
