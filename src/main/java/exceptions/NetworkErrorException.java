package exceptions;

public class NetworkErrorException extends Throwable {
    public NetworkErrorException() {
        System.out.println("A network error stopped the revisions from being found.");
    }
}
