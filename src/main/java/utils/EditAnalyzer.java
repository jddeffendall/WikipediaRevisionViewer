package utils;

import domain.WikiPage;
import exceptions.NoWikipediaPageForWordException;

public class EditAnalyzer {

    public static void AnalyzeEdits(WikiPage wikiPage) throws NoWikipediaPageForWordException {

        if (wikiPage.getPages() == "-1") {
            throw new NoWikipediaPageForWordException();
        }

    }

}
