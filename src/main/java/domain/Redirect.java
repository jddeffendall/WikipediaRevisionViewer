package domain;

public class Redirect {

    private String from;
    private String to;

    public Redirect(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
