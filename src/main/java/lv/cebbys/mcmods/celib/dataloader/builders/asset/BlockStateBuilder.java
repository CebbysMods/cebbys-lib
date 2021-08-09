package lv.cebbys.mcmods.celib.dataloader.builders.asset;

import lv.cebbys.mcmods.celib.dataloader.builders.JsonObjectResourceBuilder;
import net.minecraft.util.Identifier;

public abstract class BlockStateBuilder<B> extends  JsonObjectResourceBuilder<B> {

    public static class VariantKey {
        private final String key;
        private final String value;

        public VariantKey(String k, String v) {
            this.key = k;
            this.value = v;
        }

        @Override
        public String toString() {
            return this.key + "=" + this.value;
        }
    }

    public static class VariantBuilder extends JsonObjectResourceBuilder<VariantBuilder> {
        private String model;
        private Integer x;
        private Integer y;
        private Integer weight;
        private Boolean uvlock;

        public VariantBuilder model(Identifier model) {
            this.model = model.toString();
            return this;
        }

        public VariantBuilder x(int x) {
            this.x = x;
            return this;
        }

        public VariantBuilder y(int y) {
            this.y = y;
            return this;
        }

        public VariantBuilder weight(int w) {
            this.weight = w;
            return this;
        }

        public VariantBuilder uvlock(boolean b) {
            this.uvlock = b;
            return this;
        }

        @Override
        protected Class<VariantBuilder> builderClass() {
            return VariantBuilder.class;
        }
    }
}
