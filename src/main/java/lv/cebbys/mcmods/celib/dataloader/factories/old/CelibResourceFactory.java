package lv.cebbys.mcmods.celib.dataloader.factories.old;

import lv.cebbys.mcmods.celib.dataloader.resources.CelibResource;
import net.minecraft.util.Identifier;

import java.io.InputStream;
import java.util.function.Consumer;

public interface CelibResourceFactory<T extends CelibResource> {
    T createResource();
}
