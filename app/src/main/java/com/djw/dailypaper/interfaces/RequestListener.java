package com.djw.dailypaper.interfaces;

/**
 * Created by JasonDong on 2017/3/10.
 */

public interface RequestListener<T> {

    void onSuccessful(T t);

    void onFail();

    void onComplete();
}
