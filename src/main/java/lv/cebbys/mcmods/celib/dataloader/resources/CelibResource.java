package lv.cebbys.mcmods.celib.dataloader.resources;

import java.io.InputStream;

public interface CelibResource<T> {
    T getData();
    InputStream getInputStream();
}
