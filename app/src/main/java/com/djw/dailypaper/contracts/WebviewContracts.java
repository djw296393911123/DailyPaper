package com.djw.dailypaper.contracts;

import com.djw.dailypaper.base.BaseModel;
import com.djw.dailypaper.base.BasePresenter;
import com.djw.dailypaper.base.BaseView;
import com.djw.dailypaper.model.data.WebviewData;

/**
 * Created by JasonDong on 2017/3/10.
 */

public interface WebviewContracts  {
    interface View extends BaseView<Presenter>{

        void getWebviewData(WebviewData data);

    }

    interface Presenter extends BasePresenter{

    }

    interface Model extends BaseModel{

    }

}
