package lv.cebbys.mcmods.celib.dataloader.factories.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lv.cebbys.mcmods.celib.dataloader.resources.JsonObjectResource;

import java.util.function.Consumer;

public class ShapelessRecipeFactory extends RecipeFactory {

    private JsonArray ingredients;
    private JsonObject result;

    public void ingredients(Consumer<IngredientsFactory> ingredients) {
        IngredientsFactory factory = new IngredientsFactory();
        ingredients.accept(factory);
        this.ingredients = factory.createResource().getData();
    }

    public void result(Consumer<ResultFactory> result) {
        ResultFactory factory = new ResultFactory();
        result.accept(factory);
        this.result = factory.createResource().getData();
    }

    @Override
    public JsonObjectResource createResource(JsonObject recipe) {
        if (this.ingredients != null && this.result != null) {
            recipe.add("ingredients", this.ingredients);
            recipe.add("result", this.result);
        }
        return () -> recipe;
    }
}
