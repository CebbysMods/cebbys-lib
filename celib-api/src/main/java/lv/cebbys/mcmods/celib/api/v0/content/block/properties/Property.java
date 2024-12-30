package lv.cebbys.mcmods.celib.api.v0.content.block.properties;

public interface Property<T> {
    static <T> Property<T> ofEnum(Class<T> clazz) {
        return null;
    }

    static Property<Integer> ofInt(String name, int min, int max) {
        return null;
    }
}
