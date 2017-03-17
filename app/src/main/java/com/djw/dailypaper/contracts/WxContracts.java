package com.djw.dailypaper.contracts;

import com.djw.dailypaper.base.BaseModel;
import com.djw.dailypaper.base.BasePresenter;
import com.djw.dailypaper.base.BaseView;
import com.djw.dailypaper.model.data.WxData;

import rx.Observable;

/**
 * Created by JasonDong on 2017/3/17.
 */

public interface WxContracts {

    interface View extends BaseView<Presenter> {

        void getMore(WxData wxData);

        void getWxData(WxData wxData);

        void getSearchData(WxData wxData);

        void getSearchMore(WxData wxData);

        void getSearchWord(String word);

    }

    interface Presenter extends BasePresenter {

        void getMore(String page);

        void getSearch(String page, String word);

        void getSearchMore(String page, String word);

    }

    interface Model extends BaseModel {

        Observable<WxData> getMore(String page);

        Observable<WxData> getSearch(String page, String word);

    }

}
