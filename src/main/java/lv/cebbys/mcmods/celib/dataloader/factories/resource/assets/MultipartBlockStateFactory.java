package lv.cebbys.mcmods.celib.dataloader.factories.resource.assets;

import lv.cebbys.mcmods.celib.dataloader.resources.JsonObjectResource;

import java.util.ArrayList;
import java.util.List;

public class MultipartBlockStateFactory extends BlockStateFactory {

    private final List<JsonObjectResource> multipart = new ArrayList<>();

    public void multipart() {

    }

    @Override
    public JsonObjectResource createResource() {
        return null;
    }

}
