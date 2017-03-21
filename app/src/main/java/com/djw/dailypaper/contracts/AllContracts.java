package com.djw.dailypaper.contracts;

import com.djw.dailypaper.base.BaseModel;
import com.djw.dailypaper.base.BasePresenter;
import com.djw.dailypaper.base.BaseView;
import com.djw.dailypaper.model.data.gank.AndroidData;

import java.util.List;

import rx.Observable;

/**
 * Created by JasonDong on 2017/3/16.
 */

public interface AllContracts {

    interface View extends BaseView<Presenter> {
        void getAll(List<AndroidData.ResultsBean> data);

        void getMeizi(List<AndroidData.ResultsBean> data);

        void getMore(List<AndroidData.ResultsBean> data);

    }

    interface Presenter extends BasePresenter {

        void getMeizi();

        void getMore(String pgae);

    }

}
