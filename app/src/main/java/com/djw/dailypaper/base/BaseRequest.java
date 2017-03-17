package com.djw.dailypaper.base;

/**
 * Created by JasonDong on 2017/3/17.
 */

public class BaseRequest<T> {
    private int code;

    private String msg;

    private T newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getNewslist() {
        return newslist;
    }

    public void setNewslist(T newslist) {
        this.newslist = newslist;
    }
}
