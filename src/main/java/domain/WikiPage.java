package domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WikiPage {

    @SerializedName(value = "title", alternate = "pageTitle")
    private String pageTitle;

    @SerializedName(value = "redirects", alternate = "redirect")
    private String redirect;

    @SerializedName("pages")
    private String pages;

    @SerializedName(value = "revisions", alternate = "editList")
    private List<Edit> editList;

    public WikiPage(String title, String redirect, String pages, List<Edit> edits) {
        this.pageTitle = title;
        this.redirect = redirect;
        this.pages = pages;
        this.editList = edits;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public String getRedirect() {
        return redirect;
    }

    public String getPages() {
        return pages;
    }

    public List<Edit> getEditList() {
        return editList;
    }

}
