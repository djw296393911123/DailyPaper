package com.djw.dailypaper.model.data.paper;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class TypeData extends PaperBaseData{

    private String data;

    public TypeData(String data) {
        super(PaperBaseData.TYPE);
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TypeData{" +
                "data='" + data + '\'' +
                '}';
    }
}
