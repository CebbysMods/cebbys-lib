package lv.cebbys.mcmods.celib.dataloader.factories.resource.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lv.cebbys.mcmods.celib.dataloader.factories.resource.JsonObjectResourceFactory;
import lv.cebbys.mcmods.celib.dataloader.resources.JsonObjectResource;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;

public class TagFactory implements JsonObjectResourceFactory {

    private Boolean replace = null;
    private final JsonArray values = new JsonArray();

    public void replace(boolean b) {
        this.replace = b;
    }

    public void add(Identifier id) {
        this.values.add(id.toString());
    }

    public void add(String tagId) {
        this.values.add(tagId);
    }

    public void add(Identifier id, boolean required) {
        JsonObject tag = new JsonObject();
        tag.addProperty("id", id.toString());
        tag.addProperty("required", required);
        this.values.add(tag);
    }

    public void add(Block block) {
        this.add(Registry.BLOCK.getId(block));
    }
    public void add(Block block, boolean required) {
        this.add(Registry.BLOCK.getId(block), required);
    }

    @Override
    public JsonObjectResource createResource() {
        JsonObject tag = new JsonObject();
        if(this.replace != null) tag.addProperty("replace", this.replace);
        tag.add("values", this.values);
        return () -> tag;
    }
}
