package com.djw.dailypaper.base;


import rx.Observable;

/**
 * Created by JasonDong on 2017/3/9.
 */

public interface BaseModel<T> {

    Observable<T> loadData(String... args);

}
