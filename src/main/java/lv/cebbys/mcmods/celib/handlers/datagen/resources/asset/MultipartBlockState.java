package lv.cebbys.mcmods.celib.handlers.datagen.resources.asset;

import lv.cebbys.mcmods.celib.handlers.datagen.DataType;
import lv.cebbys.mcmods.celib.handlers.datagen.resources.helper.JsonObjectResource;
import lv.cebbys.mcmods.celib.handlers.datagen.ResourceExclude;
import lv.cebbys.mcmods.celib.structures.Two;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultipartBlockState extends BlockState<MultipartBlockState> {

    private final List<Map<String, Object>> multipart = new ArrayList<>();

    public VariantBuilder apply() {
        Map<String, Object> entry = new HashMap<>();
        VariantBuilder builder = new VariantBuilder();
        entry.put("apply", builder);
        this.multipart.add(entry);
        return builder;
    }

    public WhenOr when() {
        Map<String, Object> entry = new HashMap<>();
        VariantBuilder builder = new VariantBuilder();
        WhenOr when = new WhenOr(builder);
        entry.put("when", when);
        entry.put("apply", builder);
        this.multipart.add(entry);
        return when;
    }

    @SafeVarargs
    public final When when(Two<String, String>... pairs) {
        Map<String, Object> entry = new HashMap<>();
        Map<String, String> when = new HashMap<>();
        for (Two<String, String> pair : pairs) {
            when.put(pair.getFirst(), pair.getSecond());
        }
        VariantBuilder builder = new VariantBuilder();
        entry.put("when", when);
        entry.put("apply", builder);
        this.multipart.add(entry);
        return new When(builder);
    }

    @Override
    protected Class<MultipartBlockState> builderClass() {
        return MultipartBlockState.class;
    }

    public static class When {
        private final VariantBuilder builder;

        public When(VariantBuilder parent) {
            this.builder = parent;
        }

        public VariantBuilder apply() {
            return this.builder;
        }
    }

    public static class WhenOr extends JsonObjectResource<WhenOr> {
        @ResourceExclude
        private final VariantBuilder builder;
        private final List<Map<String, String>> or = new ArrayList<>();

        public WhenOr(VariantBuilder b) {
            this.builder = b;
        }

        public WhenOr or(String key, String value) {
            Map<String, String> entry = new HashMap<>();
            entry.put(key, value);
            this.or.add(entry);
            return this;
        }

        public WhenOr or(Two<String, String> pair) {
            Map<String, String> entry = new HashMap<>();
            entry.put(pair.getFirst(), pair.getSecond());
            this.or.add(entry);
            return this;
        }

        @SafeVarargs
        public final WhenOr or(Two<String, String> pair, Two<String, String>... pairs) {
            Map<String, String> entry = new HashMap<>();
            if (pair != null) {
                entry.put(pair.getFirst(), pair.getSecond());
            }
            for (Two<String, String> p : pairs) {
                if (p != null) {
                    entry.put(p.getFirst(), p.getSecond());
                }
            }
            this.or.add(entry);
            return this;
        }

        public VariantBuilder apply() {
            return this.builder;
        }

        @Override
        public DataType dataType() {
            return DataType.ASSETS;
        }

        @Override
        protected Class<WhenOr> builderClass() {
            return WhenOr.class;
        }
    }
}
