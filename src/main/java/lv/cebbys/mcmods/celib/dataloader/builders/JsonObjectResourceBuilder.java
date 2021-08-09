package lv.cebbys.mcmods.celib.dataloader.builders;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lv.cebbys.mcmods.celib.dataloader.utils.BuilderExclude;
import lv.cebbys.mcmods.celib.loggers.CelibLogger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class JsonObjectResourceBuilder<T> extends CelibResourceBuilder<T> {

    public JsonObject toJson() {
        Field[] fields = this.builderClass().getDeclaredFields();
        JsonObject json = new JsonObject();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Annotation[] annotations = field.getAnnotations();
                boolean include = true;
                for (Annotation a : annotations) {
                    if (a instanceof BuilderExclude) {
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
    public String toString() {
        return this.toJson().toString();
    }

    @SuppressWarnings("unchecked")
    private void appendObjectToJson(JsonObject json, String key, Object object) {
        if (object != null) {
            if (object instanceof Integer) {
                json.addProperty(key, (Integer) object);
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
            } else if (object instanceof JsonObjectResourceBuilder<?> builder) {
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
            } else if (object instanceof JsonObjectResourceBuilder<?> builder) {
                array.add(builder.toJson());
            } else {
                CelibLogger.warn("Not appending object to json. Undefined object type: " + object.getClass());
            }
        }
    }
}
