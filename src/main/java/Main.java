import domain.Edit;
import domain.WikiPage;
import exceptions.NetworkErrorException;
import exceptions.NoWikipediaPageForWordException;
import exceptions.ParameterIsNotJsonStringException;
import utils.JsonGetter;
import utils.JsonStringParser;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ParameterIsNotJsonStringException, NetworkErrorException, NoWikipediaPageForWordException {
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter a word to view its Wikipedia editors: ");
            String userWord = input.next();

            String userJsonString = new JsonGetter().JsonStringGetter(userWord);
            WikiPage userWiki = JsonStringParser.ParseJsonToObjects(userJsonString);

            if (userWiki.getRedirect() != null) {
                System.out.println("Redirected from " + userWord + " to " + userWiki.getPageTitle());
            }
            System.out.println("Recent editors: ");
            for (int i = 0; i < userWiki.getPageEditors().size(); i++) {
                System.out.println("--------------------------------------------");
                System.out.println("User: " + userWiki.getPageEditors().get(i).getUser());
                System.out.println("Time of Edit: " + userWiki.getPageEditors().get(i).getTimestamp());
            }

            System.out.println("\nEditors with their counts:");
            List<Edit> editors = userWiki.getPageEditors();

            
        } catch (ParameterIsNotJsonStringException e) {
            System.out.println("Parameter is not Json string exception.");
        } catch (NoWikipediaPageForWordException e) {
            System.out.println("No Wikipedia page for word exception.");
        }
    }
}