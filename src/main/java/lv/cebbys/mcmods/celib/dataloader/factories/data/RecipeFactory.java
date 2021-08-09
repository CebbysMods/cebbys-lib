package lv.cebbys.mcmods.celib.dataloader.factories.data;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lv.cebbys.mcmods.celib.dataloader.factories.JsonArrayResourceFactory;
import lv.cebbys.mcmods.celib.dataloader.factories.JsonObjectResourceFactory;
import lv.cebbys.mcmods.celib.dataloader.resources.JsonArrayResource;
import lv.cebbys.mcmods.celib.dataloader.resources.JsonObjectResource;
import lv.cebbys.mcmods.celib.loggers.CelibLogger;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// TODO Implement validations
public abstract class RecipeFactory implements JsonObjectResourceFactory {

    private String type;
    private String group;

    public abstract JsonObjectResource createResource(JsonObject recipe);

    public void type(String t) {
        this.type = t;
    }

    public void group(String g) {
        this.group = g;
    }

    @Override
    public JsonObjectResource createResource() {
        JsonObject recipe = new JsonObject();
        recipe.addProperty("type", this.type);
        recipe.addProperty("group", this.group);
        return this.createResource(recipe);
    }

    public static class ResultFactory implements JsonObjectResourceFactory {

        private Integer count;
        private Identifier item;

        public void count(int c) {
            if(c < 1 || c > 64) {
                CelibLogger.error("Failed to set recipe result count to invalid number");
            } else {
                this.count = c;
            }
        }

        public void item(Item i) {
            this.item(Registry.ITEM.getId(i));
        }

        public void item(Identifier i) {
            if(i == null || i.getNamespace().equals("") || i.getPath().equals("")) {
                CelibLogger.error("Tried to set recipe result to invalid value: " + i);
                return;
            }
            this.item = i;
        }

        public void item(String i) {
            this.item(new Identifier(i));
        }

        @Override
        public JsonObjectResource createResource() {
            JsonObject result = new JsonObject();
            if(this.item != null) {
                result.addProperty("count", this.count == null ? 1 : this.count);
                result.addProperty("item", this.item.toString());
            }
            return () -> result;
        }
    }
    public static class IngredientsFactory implements JsonArrayResourceFactory {

        private final List<List<Identifier>> ingredients = new ArrayList<>();

        public void ingredient(Identifier id) {
            if (this.validateElement(id)) {
                List<Identifier> ids = new ArrayList<>();
                ids.add(id);
                this.ingredient(ids);
            }
        }

        public void ingredient(Identifier... ids) {
            List<Identifier> id = Arrays.stream(ids).filter(this::validateElement).collect(Collectors.toList());
            this.ingredient(id);
        }

        public void ingredient(List<Identifier> ids) {
            if (this.ingredients.size() < 9) {
                if (ids.size() > 0) {
                    this.ingredients.add(List.copyOf(ids));
                } else {
                    CelibLogger.warn("Tried to add empty list to shapeless recipe ingredients list");
                }
            } else {
                CelibLogger.warn("Tried to add to shapeless recipe more than 10 ingredients / ingredient lists");
            }
        }

        @Override
        public JsonArrayResource createResource() {
            JsonArray ingredients = new JsonArray();
            for (List<Identifier> ingredient : this.ingredients) {
                if (ingredient.size() == 1) {
                    ingredients.add(ingredient.toString());
                } else {
                    JsonArray subList = new JsonArray();
                    for (Identifier sub : ingredient) {
                        subList.add(sub.toString());
                    }
                    ingredients.add(subList);
                }
            }
            return () -> ingredients;
        }

        private boolean validateElement(Identifier element) {
            if (element != null && !(element.getPath().equals("") || element.getNamespace().equals(""))) {
                return true;
            } else {
                CelibLogger.warn("Tried to add invalid element to shapeless recipe ingredients");
                return false;
            }
        }
    }
}
