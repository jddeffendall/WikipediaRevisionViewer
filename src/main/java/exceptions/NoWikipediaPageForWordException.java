package exceptions;

public class NoWikipediaPageForWordException extends Throwable {

    public NoWikipediaPageForWordException() {
        System.out.println("No Wikipedia page was found for that word.");
    }

}
