package com.djw.dailypaper.contracts;

import com.djw.dailypaper.base.BaseModel;
import com.djw.dailypaper.base.BasePresenter;
import com.djw.dailypaper.base.BaseView;
import com.djw.dailypaper.model.data.gank.AndroidData;

import rx.Observable;

/**
 * Created by JasonDong on 2017/3/16.
 */

public interface MeiziContracts {

    interface View extends BaseView<Presenter> {

        void getMeizi(AndroidData data);

        void getMore(AndroidData data);
    }

    interface Presenter extends BasePresenter {
        void getMore(String page);
    }

    interface Model extends BaseModel {
        Observable<AndroidData> getMore(String page);
    }

}
