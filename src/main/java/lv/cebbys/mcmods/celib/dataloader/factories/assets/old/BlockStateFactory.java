package lv.cebbys.mcmods.celib.dataloader.factories.assets.old;

import com.google.gson.JsonObject;
import lv.cebbys.mcmods.celib.dataloader.factories.JsonObjectResourceFactory;
import lv.cebbys.mcmods.celib.dataloader.resources.JsonObjectResource;
import net.minecraft.util.Identifier;

public abstract class BlockStateFactory implements JsonObjectResourceFactory {

    public static class VariantFactory implements JsonObjectResourceFactory {

        private Identifier model = null;
        private Integer x = null;
        private Integer y = null;
        private Boolean uvlock = null;
        private Integer weight = null;

        public void model(Identifier id) {
            this.model = id;
        }

        public void x(int number) {
            this.x = number;
        }

        public void y(int number) {
            this.y = number;
        }

        public void uvlock(boolean b) {
            this.uvlock = b;
        }

        public void weight(int number) {
            this.weight = number;
        }

        @Override
        public JsonObjectResource createResource() {
            JsonObject variant = new JsonObject();
            if(this.model != null) variant.addProperty("model", this.model.toString());
            if(this.x != null) variant.addProperty("x", this.x);
            if(this.y != null) variant.addProperty("y", this.y);
            if(this.uvlock != null) variant.addProperty("uvlock", this.uvlock);
            if(this.weight != null) variant.addProperty("weight", this.weight);
            return () -> variant;
        }
    }
}
