package lv.cebbys.mcmods.celib.handlers;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonLoader {

    public static JsonElement loadJson(File file) {
        try {
            return (new JsonParser()).parse(new FileReader(file));
        } catch (JsonSyntaxException | FileNotFoundException e) {
            return null;
        }
    }

}
