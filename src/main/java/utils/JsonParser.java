package utils;

import com.google.gson.Gson;
import domain.WikiPage;
import exceptions.ParameterIsNotJsonStringException;

public class JsonParser {

    public static WikiPage ParseJson(String jsonString) throws ParameterIsNotJsonStringException {

        if (jsonString.charAt(0) != '{') {
            throw new ParameterIsNotJsonStringException();
        }

        Gson gson = new Gson();
        WikiPage wiki = gson.fromJson(jsonString, WikiPage.class);

        return wiki;
    }


/*    public static WikiPage ParseRecent(String jsonString) {
        JsonParser jsonParser = new JsonParser();
        JsonElement rootElement = jsonParser.parse(jsonString);

        JsonObject rootObject = rootElement.getAsJsonObject();
        var pageTitle = rootObject.getAsJsonPrimitive("title").getAsString();
        var redirect = rootObject.getAsJsonPrimitive("redirects").getAsString();

        return new WikiPage(pageTitle, redirect, editList);
    }*/
}
