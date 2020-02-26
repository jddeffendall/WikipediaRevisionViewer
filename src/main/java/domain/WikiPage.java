package domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WikiPage {

    @SerializedName(value = "continue", alternate = "continues")
    private String continues;

    private String rvcontinue;

    @SerializedName(value = "continue", alternate = "continues2")
    private String continues2;

    private String query;
    private String pages;
    private List<Edit> revisions;

    public WikiPage(String continues, String rvcontinue, String continues2, String query, String pages, List<Edit> revisions) {
        this.continues = continues;
        this.rvcontinue = rvcontinue;
        this.continues2 = continues2;
        this.query = query;
        this.pages = pages;
        this.revisions = revisions;
    }

    public String getContinues() {
        return continues;
    }

    public String getRvcontinue() {
        return rvcontinue;
    }

    public String getContinues2() {
        return continues2;
    }

    public String getQuery() {
        return query;
    }

    public String getPages() {
        return pages;
    }

    public List<Edit> getRevisions() {
        return revisions;
    }

}
