package domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Edit {

    private String user;
    private Date timestamp;
    private int editCount;

    public Edit(String user, String time) throws ParseException {
        this.user = user;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-ddThh:mm:ssZ");
        this.timestamp = simpleDateFormat.parse(time);

    }

    public String getUser() {
        return user;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getEditCount() {
        return editCount;
    }

    public void setEditCount(int editCount) {
        this.editCount = editCount;
    }

}
