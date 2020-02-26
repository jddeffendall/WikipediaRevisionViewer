package domain;

public class Edit {

    private String user;
    private String timestamp;

    public Edit(String user, String time) {
        this.user = user;
        this.timestamp = time;
    }

    public String getUser() {
        return user;
    }

    public String getTimestamp() {
        return timestamp;
    }

}
