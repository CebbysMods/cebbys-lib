package lv.cebbys.mcmods.celib.dataloader.factories.assets.old;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lv.cebbys.mcmods.celib.dataloader.factories.JsonObjectResourceFactory;
import lv.cebbys.mcmods.celib.dataloader.resources.JsonObjectResource;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ItemModelFactory implements JsonObjectResourceFactory {

    private Identifier parent;
    private JsonObjectResource display;

    public void parent(Identifier id) {
        this.parent = id;
    }

    public void display(Consumer<DisplayFactory> display) {
        DisplayFactory factory = new DisplayFactory();
        display.accept(factory);
        this.display = factory.createResource();
    }

    @Override
    public JsonObjectResource createResource() {
        JsonObject json = new JsonObject();

        if(this.parent != null) json.addProperty("parent", this.parent.toString());
        if(this.display != null) json.add("display", this.display.getData());

        return () -> json;
    }

    public static class DisplayFactory implements JsonObjectResourceFactory {
        private final Map<String, JsonObjectResource> elements = new HashMap<>();

        public void thirdPerson(Consumer<DisplayElementFactory> element) {
            this.thirdPersonRightHand(element);
            this.thirdPersonLeftHand(element);
        }

        public void thirdPersonRightHand(Consumer<DisplayElementFactory> element) {
            this.display("thirdperson_righthand", element);
        }

        public void thirdPersonLeftHand(Consumer<DisplayElementFactory> element) {
            this.display("thirdperson_lefthand", element);
        }

        public void firstPerson(Consumer<DisplayElementFactory> element) {
            this.firstPersonRightHand(element);
            this.firstPersonLeftHand(element);
        }

        public void firstPersonRightHand(Consumer<DisplayElementFactory> element) {
            this.display("firstperson_righthand", element);
        }

        public void firstPersonLeftHand(Consumer<DisplayElementFactory> element) {
            this.display("firstperson_lefthand", element);
        }

        public void ground(Consumer<DisplayElementFactory> element) {
            this.display("ground", element);
        }

        public void gui(Consumer<DisplayElementFactory> element) {
            this.display("gui", element);
        }

        public void fixed(Consumer<DisplayElementFactory> element) {
            this.display("fixed", element);
        }

        public void display(String name, Consumer<DisplayElementFactory> element) {
            DisplayElementFactory factory = new DisplayElementFactory();
            element.accept(factory);
            this.elements.put(name, factory.createResource());
        }

        @Override
        public JsonObjectResource createResource() {
            JsonObject display = new JsonObject();
            if(this.elements.size() > 0) {
                this.elements.forEach((key, element) -> {
                    display.add(key, element.getData());
                });
            }
            return () -> display;
        }
    }

    public static class DisplayElementFactory implements JsonObjectResourceFactory {

        private int[] rotation;
        private float[] translation;
        private float[] scale;

        public void rotation(int x, int y, int z) {
            this.rotation = new int[3];
            this.rotation[0] = x;
            this.rotation[1] = y;
            this.rotation[2] = z;
        }

        public void translation(float x, float y, float z) {
            this.translation = new float[3];
            this.translation[0] = x;
            this.translation[1] = y;
            this.translation[2] = z;
        }

        public void scale(float x, float y, float z) {
            this.scale = new float[3];
            this.scale[0] = x;
            this.scale[1] = y;
            this.scale[2] = z;
        }

        @Override
        public JsonObjectResource createResource() {
            JsonObject object = new JsonObject();
            if(this.rotation != null) object.add("rotation", this.arrayToJson(this.rotation));
            if(this.translation != null) object.add("translation", this.arrayToJson(this.translation));
            if(this.scale != null) object.add("scale", this.arrayToJson(this.scale));
            return () -> object;
        }

        private JsonArray arrayToJson(int[] array) {
            JsonArray json = new JsonArray();
            for (int i : array) {
                json.add(i);
            }
            return json;
        }

        private JsonArray arrayToJson(float[] array) {
            JsonArray json = new JsonArray();
            for (float v : array) {
                json.add(v);
            }
            return json;
        }
    }
}
