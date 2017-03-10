package com.djw.dailypaper.contracts;

import com.djw.dailypaper.base.BaseModel;
import com.djw.dailypaper.base.BasePresenter;
import com.djw.dailypaper.base.BaseView;
import com.djw.dailypaper.model.data.Them.ThemData;

/**
 * Created by JasonDong on 2017/3/10.
 */

public interface ThemContracts {
    interface View extends BaseView<Presenter> {

        void getThemData(ThemData data);

    }

    interface Presenter extends BasePresenter {


    }

    interface Model extends BaseModel {

    }
}
