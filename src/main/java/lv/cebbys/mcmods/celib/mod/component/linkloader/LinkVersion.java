package lv.cebbys.mcmods.celib.mod.component.linkloader;

import lv.cebbys.mcmods.celib.api.maybe.Maybe;
import lv.cebbys.mcmods.celib.mod.exception.LinkException;
import lv.cebbys.mcmods.celib.mod.utilities.FormatUtils;

import java.util.Arrays;

public enum LinkVersion {
    INVALID(-1, -1),
    V16(1, 16),
    V17(1, 17),
    V18(1, 18),
    V19(1, 19);

    private final int main;
    private final int major;

    LinkVersion(int main, int major) {
        this.main = main;
        this.major = major;
    }

    public String toPackagePart() {
        return "v" + main + "_" + major;
    }

    public static LinkVersion toLinkVersion(String version) {
        LinkVersion out = new Maybe<>(version)
                .continueIfNot(""::equals)
                .continueIf(v ->
                        v.matches("^[\\d]+\\.[\\d]+\\.[\\d]+$") || v.matches("^[\\d]+\\.[\\d]+$")
                )
                .transform(v -> {
                    int mainInt = FormatUtils.toInt(v.substring(0, v.indexOf(".")));
                    v = v.substring(v.indexOf(".") + 1);
                    if (v.contains(".")) v = v.substring(0, v.indexOf("."));
                    int majorInt = FormatUtils.toInt(v);
                    return toLinkVersion(mainInt, majorInt);
                }).get();
        if (out == null) throw new LinkException("Failed to convert Minecraft version: " + version);
        return out;
    }

    public static LinkVersion toLinkVersion(int main, int major) {
        return Arrays.stream(LinkVersion.values())
                .filter(v -> v.main == main)
                .filter(v -> v.major == major)
                .findFirst().orElse(INVALID);
    }
}
