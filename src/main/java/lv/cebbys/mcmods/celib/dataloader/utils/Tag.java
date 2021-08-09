package lv.cebbys.mcmods.celib.dataloader.utils;

import net.minecraft.util.Identifier;

public class Tag extends Identifier {

    public Tag(String path) {
        super(path);
    }

    public Tag(String namespace, String path) {
        super(namespace, path);
    }

    @Override
    public String toString() {
        return "#" + super.toString();
    }
}
