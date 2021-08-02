package lv.cebbys.mcmods.celib.dataloader.resources;

import com.google.gson.JsonElement;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public interface JsonResource<T extends JsonElement> extends CelibResource<T> {
    @Override
    default InputStream getInputStream() {
        return new ByteArrayInputStream(this.getData().toString().getBytes(StandardCharsets.UTF_8));
    }
}
