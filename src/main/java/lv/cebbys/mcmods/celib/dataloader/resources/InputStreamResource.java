package lv.cebbys.mcmods.celib.dataloader.resources;

import java.io.InputStream;

public interface InputStreamResource extends CelibResource<InputStream> {
    @Deprecated
    @Override
    default InputStream getData() {
        return this.getInputStream();
    }
}
