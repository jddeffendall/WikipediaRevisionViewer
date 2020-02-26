package utils;

import com.google.gson.*;
import domain.WikiPage;
import exceptions.ParameterIsNotJsonStringException;

public class JsonStringParser {

    public static WikiPage ParseJson(String jsonString) throws ParameterIsNotJsonStringException {

        if (jsonString.charAt(0) != '{') {
            throw new ParameterIsNotJsonStringException();
        }

        Gson gson = new Gson();
        WikiPage wiki = gson.fromJson(jsonString, WikiPage.class);

        return wiki;
    }


    public static WikiPage ParseRecent(String jsonString) {
        JsonParser jsonParser = new JsonParser();
        JsonElement rootElement = jsonParser.parse(jsonString);
        JsonObject rootObject =rootElement.getAsJsonObject();
        var continues = rootObject.getAsJsonPrimitive("continue");
        var query = rootObject.getAsJsonPrimitive("query");
        var pageTitle = rootObject.getAsJsonPrimitive("title");
        var redirect = rootObject.getAsJsonPrimitive("redirects");
        var pages = rootObject.getAsJsonPrimitive("pages");
        JsonArray editArray = new JsonArray(30);
        editArray.addAll(editArray);
        return null;
    }
}