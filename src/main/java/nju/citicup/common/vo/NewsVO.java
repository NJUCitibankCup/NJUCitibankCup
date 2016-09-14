package nju.citicup.common.vo;

/**
 * Created by nians on 2016/9/14.
 */
public class NewsVO {
    private String title;
    private String link;

    public NewsVO() {
    }

    public NewsVO(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
