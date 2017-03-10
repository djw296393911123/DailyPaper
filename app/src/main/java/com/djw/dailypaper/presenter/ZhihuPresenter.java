package com.djw.dailypaper.presenter;

import com.djw.dailypaper.contracts.ZhihuContracts;
import com.djw.dailypaper.interfaces.RequestListener;
import com.djw.dailypaper.model.ZhihuModel;
import com.djw.dailypaper.model.data.DaypaperData;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class ZhihuPresenter implements ZhihuContracts.Presenter {

    private ZhihuContracts.View view;

    private ZhihuModel model;

    public ZhihuPresenter(ZhihuContracts.View view) {
        this.view = view;
        this.model = new ZhihuModel();
    }

    @Override
    public void start() {
        getDataFromModel();
    }

    @Override
    public void getDataFromModel(String... args) {
        view.showProgress();
        model.loadData(new RequestListener<DaypaperData>() {
            @Override
            public void onSuccessful(DaypaperData daypaperData) {
                view.getDayPaper(daypaperData);
            }

            @Override
            public void onFail() {
                view.showFail();
                view.hideProgress();
            }

            @Override
            public void onComplete() {
                view.showComplete();
                view.hideProgress();
            }
        });
    }

    @Override
    public int loadTodayDate(long date) {
        return model.getDateToday(date);
    }

    @Override
    public void getBeforePaper(String date) {
        view.showProgress();
        model.loadBeforeData(date, new RequestListener<DaypaperData>() {
            @Override
            public void onSuccessful(DaypaperData daypaperData) {
                view.getBeforePaper(daypaperData);
            }

            @Override
            public void onFail() {
                view.hideProgress();
            }

            @Override
            public void onComplete() {
                view.hideProgress();
            }
        });
    }
}
