package noteBook.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Note {
    private String title;
    private String body;
    private Date date; //date of creation or modification


    public Note(String title, String body) {
        this.title = title;
        this.body = body;
        this.date = new Date();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        String date = new SimpleDateFormat("dd.MM.yyyy.HH.mm").format(getDate());
        return String.format("Title: %s. Note: %s. Date: %s", title, body, date);
    }
}
