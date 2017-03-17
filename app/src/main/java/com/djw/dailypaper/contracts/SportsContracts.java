package com.djw.dailypaper.contracts;

import com.djw.dailypaper.base.BaseModel;
import com.djw.dailypaper.base.BasePresenter;
import com.djw.dailypaper.base.BaseView;
import com.djw.dailypaper.model.data.sports.SportsData;

import rx.Observable;

/**
 * Created by JasonDong on 2017/3/17.
 */

public interface SportsContracts {

    interface View extends BaseView<Presenter> {

        void getSports(SportsData data);

        void getMore(SportsData data);

        void getFootball(SportsData data);

        void getMoreFootball(SportsData data);

        void getSocial(SportsData data);

        void getMoreSocial(SportsData data);

        void getMeizi(SportsData data);

        void getMoreMeizi(SportsData data);

    }

    interface Presenter extends BasePresenter {

        void getMore(String page);

        void getFootball();

        void getMoreFootball(String page);

        void getSocial();

        void getMoreSocial(String page);

        void getMeizi();

        void getMoreMeizi(String page);
    }

    interface Model extends BaseModel {

        Observable<SportsData> getMore(String page);

        Observable<SportsData> getFootball();

        Observable<SportsData> getMoreFootball(String page);

        Observable<SportsData> getSocial();

        Observable<SportsData> getMoreSocial(String page);

        Observable<SportsData> getMeizi();

        Observable<SportsData> getMoreMeizi(String page);

    }

}
