package utils;

import com.google.gson.*;
import domain.Edit;
import exceptions.ParameterIsNotJsonStringException;

import java.util.Map;

public class JsonStringParser {

    public static JsonArray ParseToJsonArray(String jsonString) throws ParameterIsNotJsonStringException {
        if (jsonString.charAt(0) != '{') {
            throw new ParameterIsNotJsonStringException();
        }

        JsonParser jsonParser = new JsonParser();
        JsonElement rootElement = jsonParser.parse(jsonString);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray revisions = null;

        for (Map.Entry<String, JsonElement> edit : pages.entrySet()) {
            JsonObject editObject = edit.getValue().getAsJsonObject();
            revisions = editObject.getAsJsonArray("revisions");
            String title = editObject.getAsJsonPrimitive("title").toString();
            System.out.println("Page Title: " + title);
        }
        return revisions;
    }

    public static Edit[] ParseArrayToObjects(String jsonString) {
        Gson gson = new Gson();
        Edit[] editArray = gson.fromJson(jsonString, Edit[].class);
        return editArray;
    }
}