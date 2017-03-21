package com.djw.dailypaper.contracts;

import com.djw.dailypaper.base.BaseModel;
import com.djw.dailypaper.base.BasePresenter;
import com.djw.dailypaper.base.BaseView;
import com.djw.dailypaper.model.data.OtherData;
import com.djw.dailypaper.model.data.WebviewData;

import rx.Observable;

/**
 * Created by JasonDong on 2017/3/10.
 */

public interface WebviewContracts {
    interface View extends BaseView<Presenter> {

        void getWebviewData(WebviewData data);

        void getInfo(OtherData data);

    }

    interface Presenter extends BasePresenter {
        void getInfo(String id);
    }

    interface Model extends BaseModel {

        Observable<OtherData> getInfo(String id);

    }

}
