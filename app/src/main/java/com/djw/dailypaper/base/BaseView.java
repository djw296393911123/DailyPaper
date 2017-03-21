package com.djw.dailypaper.base;

/**
 * Created by JasonDong on 2017/3/9.
 */

public interface BaseView<T> {

    void setPresenter(T presenter);

    void showProgress();

    void hideProgress();

    void showFail();

    void showComplete();

    void showDataEmpty();


}
