package domain;

public class Edit {

    private String name;
    private String timestamp;

    public Edit(String name, String time) {
        this.name = name;
        this.timestamp = time;
    }

    public String getName() {
        return name;
    }

    public String getTimestamp() {
        return timestamp;
    }

}
