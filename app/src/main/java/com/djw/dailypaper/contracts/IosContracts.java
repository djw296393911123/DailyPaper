package com.djw.dailypaper.contracts;

import com.djw.dailypaper.base.BaseModel;
import com.djw.dailypaper.base.BasePresenter;
import com.djw.dailypaper.base.BaseView;
import com.djw.dailypaper.model.data.gank.AndroidData;

import rx.Observable;

/**
 * Created by JasonDong on 2017/3/16.
 */

public interface IosContracts {

    interface View extends BaseView<Presenter> {
        void getIos(AndroidData data);

        void getMeizi(AndroidData data);

        void getMore(AndroidData data);

    }

    interface Presenter extends BasePresenter {

        void getMeizi();

        void getMore(String page);
    }

    interface Model extends BaseModel {

        Observable<AndroidData> getMeizi();

        Observable<AndroidData> getMore(String page);

    }

}
