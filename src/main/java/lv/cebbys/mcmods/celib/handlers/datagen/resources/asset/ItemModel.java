package lv.cebbys.mcmods.celib.handlers.datagen.resources.asset;

import lv.cebbys.mcmods.celib.handlers.datagen.DataType;
import lv.cebbys.mcmods.celib.handlers.datagen.resources.helper.JsonObjectResource;
import lv.cebbys.mcmods.celib.loggers.CelibLogger;
import lv.cebbys.mcmods.celib.processor.api.ResourceElement;
import lv.cebbys.mcmods.celib.processor.api.Resource;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Resource
public class ItemModel extends JsonObjectResource<ItemModel> {

    @ResourceElement
    private String parent;
    @ResourceElement(singleCall = false)
    private Map<String, DisplayElement> display;

    public ItemModel parent(Identifier path) {
        if(this.parent != null) {
            CelibLogger.warn("Overriding already initialized parent element");
        }
        this.parent = path.toString();
        return this;
    }

    public ItemModel display(Consumer<DisplayElements> display) {
        if(this.display != null) {
            CelibLogger.warn("Overriding already initialized display element");
        }
        DisplayElements elements = new DisplayElements();
        display.accept(elements);
        this.display = elements.getElements();
        return this;
    }

    public static class DisplayElements {

        public Map<String, DisplayElement> display = new HashMap<>();

        public DisplayElement thirdPersonRightHand() {
            return this.display("thirdperson_righthand");
        }

        public DisplayElement thirdPersonLeftHand() {
            return this.display("thirdperson_lefthand");
        }

        public DisplayElement thirdPersonHand() {
            DisplayElement element = new DisplayElement();
            this.display("thirdperson_lefthand", element);
            this.display("thirdperson_righthand", element);
            return element;
        }

        public DisplayElement firstPersonRightHand() {
            return this.display("firstperson_righthand");
        }

        public DisplayElement firstPersonLeftHand() {
            return this.display("firstperson_lefthand");
        }

        public DisplayElement firstPersonHand() {
            DisplayElement element = new DisplayElement();
            this.display("firstperson_lefthand", element);
            this.display("firstperson_righthand", element);
            return element;
        }

        public DisplayElement gui() {
            return this.display("gui");
        }

        public DisplayElement head() {
            return this.display("head");
        }

        public DisplayElement ground() {
            return this.display("ground");
        }

        public DisplayElement fixed() {
            return this.display("fixed");
        }

        private DisplayElement display(String type, DisplayElement element) {
            if(this.display.containsKey(type)) {
                CelibLogger.warn("Overriding already initialized [" + type + "] display element");
            }
            this.display.put(type, element);
            return element;
        }

        public DisplayElement display(String type) {
            DisplayElement element = new DisplayElement();
            return this.display(type, element);
        }

        protected Map<String, DisplayElement> getElements() {
            return this.display;
        }
    }

    public static class DisplayElement extends JsonObjectResource<DisplayElement> {
        private Integer[] rotation;
        private Float[] translation;
        private Float[] scale;

        public DisplayElement rotation(int x, int y, int z) {
            this.rotation = new Integer[3];
            this.rotation[0] = x;
            this.rotation[1] = y;
            this.rotation[2] = z;
            return this;
        }

        public DisplayElement translation(float x, float y, float z) {
            this.translation = new Float[3];
            this.translation[0] = x;
            this.translation[1] = y;
            this.translation[2] = z;
            return this;
        }

        public DisplayElement scale(float x, float y, float z) {
            this.scale = new Float[3];
            this.scale[0] = x;
            this.scale[1] = y;
            this.scale[2] = z;
            return this;
        }

        @Override
        public DataType dataType() {
            return DataType.ASSETS;
        }

        @Override
        protected Class<DisplayElement> builderClass() {
            return DisplayElement.class;
        }
    }

    @Override
    public DataType dataType() {
        return DataType.ASSETS;
    }

    @Override
    protected Class<ItemModel> builderClass() {
        return ItemModel.class;
    }
}
