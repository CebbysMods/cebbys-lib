package lv.cebbys.mcmods.celib.handlers.datagen.resources.helper;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lv.cebbys.mcmods.celib.handlers.datagen.ResourceExclude;
import lv.cebbys.mcmods.celib.loggers.CelibLogger;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public abstract class JsonObjectResource<T> extends CelibResource<T> {

    public JsonObject toJson() {
        Field[] fields = this.builderClass().getDeclaredFields();
        JsonObject json = new JsonObject();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Annotation[] annotations = field.getAnnotations();
                boolean include = true;
                for (Annotation a : annotations) {
                    if (a instanceof ResourceExclude) {
                        include = false;
                        break;
                    }
                }
                if (include) {
                    this.appendObjectToJson(json, field.getName(), field.get(this));
                }
            } catch (Exception e) {
                CelibLogger.error(e, "Failed to access field");
            }
        }
        return json;
    }

    @Override
    public InputStream toStream() {
        return new ByteArrayInputStream(this.toJson().toString().getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String toString() {
        return this.toJson().toString();
    }

    @SuppressWarnings("unchecked")
    private void appendObjectToJson(JsonObject json, String key, Object object) {
        if (object != null) {
            if (object instanceof Number) {
                json.addProperty(key, (Number) object);
            } else if (object instanceof String) {
                json.addProperty(key, (String) object);
            } else if (object instanceof Boolean) {
                json.addProperty(key, (Boolean) object);
            } else if (object instanceof Map) {
                Map<Object, Object> map = (Map<Object, Object>) object;
                JsonObject jsonMap = new JsonObject();
                for (Object mapKey : map.keySet()) {
                    this.appendObjectToJson(jsonMap, mapKey.toString(), map.get(mapKey));
                }
                json.add(key, jsonMap);
            } else if (object instanceof List<?> list) {
                JsonArray jsonArray = new JsonArray();
                for (Object listObject : list) {
                    this.appendToJsonArray(jsonArray, listObject);
                }
                json.add(key, jsonArray);
            } else if (object instanceof JsonObjectResource<?> builder) {
                json.add(key, builder.toJson());
            } else {
                CelibLogger.warn("Not appending object to json. Undefined object type: " + object.getClass());
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void appendToJsonArray(JsonArray array, Object object) {
        if (object != null) {
            if (object instanceof Number n) {
                array.add(n);
            } else if (object instanceof String s) {
                array.add(s);
            } else if (object instanceof Character c) {
                array.add(c);
            } else if (object instanceof Boolean b) {
                array.add(b);
            } else if (object instanceof Map) {
                Map<Object, Object> map = (Map<Object, Object>) object;
                JsonObject jsonMap = new JsonObject();
                for (Object mapKey : map.keySet()) {
                    this.appendObjectToJson(jsonMap, mapKey.toString(), map.get(mapKey));
                }
                array.add(jsonMap);
            } else if (object instanceof List<?> list) {
                JsonArray jsonArray = new JsonArray();
                for (Object listObject : list) {
                    this.appendToJsonArray(jsonArray, listObject);
                }
                array.add(jsonArray);
            } else if (object instanceof JsonObjectResource<?> builder) {
                array.add(builder.toJson());
            } else {
                CelibLogger.warn("Not appending object to json. Undefined object type: " + object.getClass());
            }
        }
    }
}
