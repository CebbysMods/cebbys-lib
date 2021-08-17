package lv.cebbys.mcmods.celib.handlers.datagen;

import net.minecraft.util.Identifier;

public class IdentifierUtils {
    private IdentifierUtils() {}

    public static Identifier wrap(String prefix, Identifier id, String suffix) {
        return new Identifier(id.getNamespace(), String.format("%s%s%s", prefix, id.getPath(), suffix));
    }

    public static Identifier prefix(String prefix, Identifier id) {
        return new Identifier(id.getNamespace(), String.format("%s%s", prefix, id.getPath()));
    }

    public static Identifier suffix(Identifier id, String suffix) {
        return new Identifier(id.getNamespace(), String.format("%s%s", id.getPath(), suffix));
    }
}
