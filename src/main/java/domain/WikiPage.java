package domain;

import java.util.List;

public class WikiPage {

    private String pageTitle;
    private int pageId;
    private List<Edit> pageEditors;
    private Redirect redirect;

    public WikiPage(String pageTitle, int pageId, List<Edit> pageEditors) {
        this.pageTitle = pageTitle;
        this.pageId = pageId;
        this.pageEditors = pageEditors;
    }

    public WikiPage(String pageTitle, int pageId, List<Edit> pageEditors, Redirect redirect) {
        this.pageTitle = pageTitle;
        this.pageId = pageId;
        this.pageEditors = pageEditors;
        this.redirect = redirect;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public int getPageId() {
        return pageId;
    }

    public List<Edit> getPageEditors() {
        return pageEditors;
    }

    public Redirect getRedirect() {
        return redirect;
    }

    @Override
    public String toString() {
        return "WikiPage{" +
                ", pageTitle=" + pageTitle + '\'' +
                ", pageId=" + pageId + '\'' +
                ", pageEditors=" + pageEditors + '\'' +
                ", redirect=" + redirect + '}';
    }

}
