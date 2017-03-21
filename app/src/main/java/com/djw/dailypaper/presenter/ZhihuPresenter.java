package com.djw.dailypaper.presenter;

import com.djw.dailypaper.contracts.ZhihuContracts;
import com.djw.dailypaper.interfaces.RequestListener;
import com.djw.dailypaper.model.ZhihuModel;
import com.djw.dailypaper.model.data.DaypaperData;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        model.loadData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DaypaperData>() {
                    @Override
                    public void onCompleted() {
                        view.hideProgress();
                        view.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgress();
                        view.showFail();
                    }

                    @Override
                    public void onNext(DaypaperData daypaperData) {
                        view.getDayPaper(daypaperData);
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
        model.loadBeforeData(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DaypaperData>() {
                    @Override
                    public void onCompleted() {
                        view.hideProgress();
                        view.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgress();
                        view.showFail();
                    }

                    @Override
                    public void onNext(DaypaperData daypaperData) {
                        view.getBeforePaper(daypaperData);
                    }
                });
    }
}
