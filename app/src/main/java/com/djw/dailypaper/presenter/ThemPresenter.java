package com.djw.dailypaper.presenter;

import com.djw.dailypaper.contracts.ThemContracts;
import com.djw.dailypaper.interfaces.RequestListener;
import com.djw.dailypaper.model.ThemModel;
import com.djw.dailypaper.model.data.Them.ThemData;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class ThemPresenter implements ThemContracts.Presenter {

    private ThemContracts.View view;

    private ThemModel model;

    public ThemPresenter(ThemContracts.View view) {
        this.view = view;
        this.model = new ThemModel();
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
                .subscribe(new Subscriber<ThemData>() {
                    @Override
                    public void onCompleted() {
                        view.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgress();
                    }

                    @Override
                    public void onNext(ThemData themData) {
                        view.getThemData(themData);
                    }
                });
    }
}
