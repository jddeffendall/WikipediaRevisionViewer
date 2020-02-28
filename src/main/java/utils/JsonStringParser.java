package utils;

import com.google.gson.*;
import domain.Edit;
import domain.Redirect;
import domain.WikiPage;
import exceptions.NoWikipediaPageForWordException;
import exceptions.ParameterIsNotJsonStringException;

import java.util.Map;

public class JsonStringParser {

    public static WikiPage ParseJsonToObjects(String jsonString) throws ParameterIsNotJsonStringException, NoWikipediaPageForWordException {
        if (jsonString.charAt(0) != '{') {
            throw new ParameterIsNotJsonStringException();
        }

        JsonParser jsonParser = new JsonParser();
        JsonElement rootElement = jsonParser.parse(jsonString);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject query = rootObject.getAsJsonObject("query");
        JsonObject pages = query.getAsJsonObject("pages");

        int length = pages.keySet().toString().length();
        String pageIdName = pages.keySet().toString().substring(1, length-1);

        if (pageIdName.contentEquals("-1")) {
            throw new NoWikipediaPageForWordException();
        }

        JsonObject pageIdObj = pages.getAsJsonObject(pageIdName);
        var pageId = pageIdObj.getAsJsonPrimitive("pageid").getAsInt();
        var pageTitle = pageIdObj.getAsJsonPrimitive("title").getAsString();
        JsonArray revisions = null;

        for (Map.Entry<String, JsonElement> edit : pages.entrySet()) {
            JsonObject editObject = edit.getValue().getAsJsonObject();
            revisions = editObject.getAsJsonArray("revisions");
            String title = editObject.getAsJsonPrimitive("title").toString();
        }

        String revisionsString = revisions.toString();
        Gson newGson = new Gson();
        Edit[] editArray = newGson.fromJson(revisionsString, Edit[].class);

        if (query.keySet().contains("redirects")) {
            JsonArray redirects = query.getAsJsonArray("redirects");
            JsonObject redirectObj = redirects.get(0).getAsJsonObject();
            Gson gson = new Gson();
            Redirect redirect = gson.fromJson(redirectObj, Redirect.class);
            return new WikiPage(pageTitle, pageId, editArray, redirect);
        }
        return new WikiPage(pageTitle, pageId, editArray);
    }
}