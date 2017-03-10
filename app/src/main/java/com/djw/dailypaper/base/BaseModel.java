package com.djw.dailypaper.base;

import com.djw.dailypaper.interfaces.RequestListener;

/**
 * Created by JasonDong on 2017/3/9.
 */

public interface BaseModel<T> {

    void loadData(RequestListener<T> listener, String... args);

}
