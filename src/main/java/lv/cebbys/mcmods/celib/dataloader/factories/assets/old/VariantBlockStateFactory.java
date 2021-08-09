package lv.cebbys.mcmods.celib.dataloader.factories.assets.old;

import com.google.gson.JsonObject;
import lv.cebbys.mcmods.celib.dataloader.resources.JsonObjectResource;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class VariantBlockStateFactory extends BlockStateFactory {

    private final Map<String, JsonObjectResource> variants = new HashMap<>();

    public void variant(Consumer<VariantFactory> variant) {
        this.variant("", variant);
    }


    public void variant(String name, String value, Consumer<VariantFactory> variant) {
        this.variant(name + "=" + value, variant);
    }

    public void variant(String name, Consumer<VariantFactory> variant) {
        VariantFactory factory = new VariantFactory();
        variant.accept(factory);
        this.variants.put(name, factory.createResource());
    }

    @Override
    public JsonObjectResource createResource() {
        JsonObject blockstate = new JsonObject();
        JsonObject vars = new JsonObject();
        this.variants.forEach((key, variant) -> {
            vars.add(key, variant.getData());
        });
        blockstate.add("variants", vars);
        return () -> blockstate;
    }
}
