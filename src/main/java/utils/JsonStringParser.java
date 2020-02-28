package utils;

import com.google.gson.*;
import domain.Edit;
import domain.Redirect;
import domain.WikiPage;
import exceptions.NoWikipediaPageForWordException;
import exceptions.ParameterIsNotJsonStringException;

import java.util.ArrayList;
import java.util.List;

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
        JsonArray revisions = pageIdObj.getAsJsonArray("revisions");

        List<Edit> editors = new ArrayList<>();
        for (int i=0; i < revisions.size(); i++) {
            JsonObject obj = revisions.get(i).getAsJsonObject();
            Gson gson = new Gson();
            Edit edit = gson.fromJson(obj, Edit.class);
            edit.setEditCount(1);
            editors.add(edit);

            for (int j=0; j < editors.size()-1; j++) {
                if (editors.get(j).getUser().contentEquals(edit.getUser())) {
                    editors.get(j).setEditCount(editors.get(j).getEditCount()+1);
                }
            }
        }
        if (query.keySet().contains("redirects")) {
            JsonArray redirects = query.getAsJsonArray("redirects");
            JsonObject redirectObj = redirects.get(0).getAsJsonObject();
            Gson gson = new Gson();
            Redirect redirect = gson.fromJson(redirectObj, Redirect.class);
            return new WikiPage(pageTitle, pageId, editors, redirect);
        }
        return new WikiPage(pageTitle, pageId, editors);
    }
}