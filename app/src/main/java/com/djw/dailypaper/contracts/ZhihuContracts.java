package com.djw.dailypaper.contracts;

import com.djw.dailypaper.base.BaseModel;
import com.djw.dailypaper.base.BasePresenter;
import com.djw.dailypaper.base.BaseView;
import com.djw.dailypaper.interfaces.RequestListener;
import com.djw.dailypaper.model.data.DaypaperData;

import rx.Observable;

/**
 * Created by JasonDong on 2017/3/10.
 */

public interface ZhihuContracts {

    interface View extends BaseView<Presenter> {

        void getDayPaper(DaypaperData daypaperData);

        void getBeforePaper(DaypaperData daypaperData);
    }

    interface Presenter extends BasePresenter {

        int loadTodayDate(long date);

        void getBeforePaper(String date);

    }

    interface Model extends BaseModel {

        Observable<DaypaperData> loadBeforeData(String data);

        int getDateToday(long date);
    }

}
