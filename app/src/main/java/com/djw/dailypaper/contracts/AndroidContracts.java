package com.djw.dailypaper.contracts;

import com.djw.dailypaper.base.BaseModel;
import com.djw.dailypaper.base.BasePresenter;
import com.djw.dailypaper.base.BaseView;
import com.djw.dailypaper.model.data.gank.AndroidData;

import rx.Observable;

/**
 * Created by JasonDong on 2017/3/16.
 */

public interface AndroidContracts {

    interface View extends BaseView<Presenter> {
        void getAndroid(AndroidData data);

        void getMeizi(AndroidData data);

        void getMoreData(AndroidData data);
    }

    interface Presenter extends BasePresenter {
        void getMeizi();

        void getMoreData(String page);
    }

    interface Model extends BaseModel {

        Observable<AndroidData> getMeizi();

        Observable<AndroidData> getMoreData(String page);
    }

}
