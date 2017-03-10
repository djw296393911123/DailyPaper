package com.djw.dailypaper.model.data.paper;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class PaperBaseData {
    public static final int TYPE = 0x9001;
    public static final int PAPER = 0x9002;
    public static final int BANNER = 0x9003;

    private int type;

    public PaperBaseData(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PaperBaseData{" +
                "type=" + type +
                '}';
    }
}
