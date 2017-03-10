package com.djw.dailypaper.model.data.paper;

import java.util.List;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class BannerData extends PaperBaseData {

    private String title;

    private List<String> url;

    private int id;

    public BannerData(String title, List<String> url, int id) {
        super(PaperBaseData.BANNER);
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

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
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
        return "BannerData{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
