package com.djw.dailypaper.model.data.paper;

import java.util.List;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class BannerData extends PaperBaseData {

    private List<String> title;

    private List<String> url;

    private List<String> id;

    public BannerData(List<String> title, List<String> url, List<String> id) {
        super(PaperBaseData.BANNER);
        this.title = title;
        this.url = url;
        this.id = id;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public List<String> getId() {
        return id;
    }

    public void setId(List<String> id) {
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
