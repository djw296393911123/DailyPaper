package com.djw.dailypaper.model.data.paper;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class PaperData extends PaperBaseData {
    private String title;

    private String url;

    private int id;

    public PaperData(String title, String url, int id) {
        super(PaperBaseData.PAPER);
        this.title = title;
        this.url = url;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PaperData{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
