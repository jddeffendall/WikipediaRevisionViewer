import domain.Edit;
import domain.WikiPage;
import exceptions.NoWikipediaPageForWordException;
import exceptions.ParameterIsNotJsonStringException;
import utils.EditAnalyzer;
import utils.JsonGetter;
import utils.JsonParser;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ParameterIsNotJsonStringException, NoWikipediaPageForWordException {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a word to see its past editors: ");
        String userWord = input.next();

        String userJsonString = new JsonGetter().JsonStringGetter(userWord);

        WikiPage userWiki = JsonParser.ParseJson(userJsonString);

        EditAnalyzer.AnalyzeEdits(userWiki);

        List<Edit> editList = userWiki.getEditList();

        for (Edit i : editList) {
            System.out.println("-------------------------------------");
            System.out.println("Editor name: " + i.getName());
            System.out.println("Edit timestamp: " + i.getTimestamp());
            System.out.println("-------------------------------------");
        }

    }
}