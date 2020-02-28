import domain.Edit;
import domain.WikiPage;
import exceptions.NetworkErrorException;
import exceptions.NoWikipediaPageForWordException;
import exceptions.ParameterIsNotJsonStringException;
import utils.EditSorter;
import utils.JsonGetter;
import utils.JsonStringParser;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ParameterIsNotJsonStringException, NetworkErrorException, NoWikipediaPageForWordException {
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter a word to view its Wikipedia page editors: ");
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

            List<Edit> countedEdits = EditSorter.getEditCounts(userWiki.getPageEditors());

            System.out.println("\n\nEditors with their counts:");

            for (Edit i : countedEdits) {
                i.increaeEditCount();
                System.out.println("--------------------------------------");
                System.out.println("Editor :" + i.getUser());
                System.out.println("Edit Count: " + i.getEditCount());
            }

            
        } catch (ParameterIsNotJsonStringException e) {
            System.out.println("Parameter is not Json string exception.");
        } catch (NoWikipediaPageForWordException e) {
            System.out.println("No Wikipedia page for word exception.");
        }
        catch (NetworkErrorException e) {
            System.out.println("Network error; can't connect.");
        }
    }
}