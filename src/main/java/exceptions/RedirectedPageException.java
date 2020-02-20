package exceptions;

public class RedirectedPageException extends Throwable {

    public RedirectedPageException() {
        System.out.println("The word you typed was redirected to another similar page.");
    }

}
