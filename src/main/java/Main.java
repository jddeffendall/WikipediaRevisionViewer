import domain.Edit;
import exceptions.NetworkErrorException;
import exceptions.ParameterIsNotJsonStringException;
import utils.EditAnalyzer;
import utils.JsonGetter;
import utils.JsonStringParser;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ParameterIsNotJsonStringException, NetworkErrorException {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a word to see the past 30 editors for its Wikipedia page: ");
        String userWord = input.next();

        String userJsonString = new JsonGetter().JsonStringGetter(userWord);

        String revisions = JsonStringParser.ParseToJsonArray(userJsonString).toString();
        Edit[] editArray = JsonStringParser.ParseArrayToObjects(revisions);

        Edit mostFrequentEditor = EditAnalyzer.mostFrequentEditor(editArray);
        System.out.println(mostFrequentEditor);

         for (Edit editor : editArray) {
             System.out.println(editor);
         }
    }
}