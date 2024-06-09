package lv.cebbys.mcmods.celib.core.mod.bridge;

import lv.cebbys.mcmods.celib.core.api.maybe.MaybeOld;
import lv.cebbys.mcmods.celib.core.mod.utility.FormatUtils;

import java.util.Arrays;

public enum BridgeVersion {
    INVALID(-1, -1),
    V19(1, 19);

    private final int main;
    private final int major;

    BridgeVersion(int main, int major) {
        this.main = main;
        this.major = major;
    }

    public String toPackagePart() {
        return "v" + main + "_" + major;
    }

    public static BridgeVersion toLinkVersion(String version) {
        BridgeVersion out = new MaybeOld<>(version)
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
        if (out == null) out = BridgeVersion.INVALID;
        return out;
    }

    public static BridgeVersion toLinkVersion(int main, int major) {
        return Arrays.stream(BridgeVersion.values())
                .filter(v -> v.main == main)
                .filter(v -> v.major == major)
                .findFirst().orElse(INVALID);
    }
}
