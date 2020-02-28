import domain.Edit;
import domain.WikiPage;
import exceptions.NetworkErrorException;
import exceptions.NoWikipediaPageForWordException;
import exceptions.ParameterIsNotJsonStringException;
import utils.JsonGetter;
import utils.JsonStringParser;

import java.io.IOException;
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
            for (Edit i : userWiki.getPageEditors()) {
                System.out.println(i);
            }

            System.out.println("\nEditors with their counts:");

            
        } catch (ParameterIsNotJsonStringException e) {
            System.out.println("Parameter is not Json string exception.");
        } catch (NoWikipediaPageForWordException e) {
            System.out.println("No Wikipedia page for word exception.");
        }
    }
}