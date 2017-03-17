package com.djw.dailypaper.presenter;

import com.djw.dailypaper.contracts.ThemInfoContracts;
import com.djw.dailypaper.model.ThemInfoModel;
import com.djw.dailypaper.model.data.Them.ThemInfoData;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JasonDong on 2017/3/16.
 */

public class ThemInfoPresenter implements ThemInfoContracts.Presenter {

    private ThemInfoContracts.View view;

    private ThemInfoModel model;

    public ThemInfoPresenter(ThemInfoContracts.View view) {
        this.view = view;
        this.model = new ThemInfoModel();
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getDataFromModel(String... args) {
        view.showProgress();
        model.loadData(args[0])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ThemInfoData>() {
                    @Override
                    public void onCompleted() {
                        view.showComplete();
                        view.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showFail();
                        view.hideProgress();
                    }

                    @Override
                    public void onNext(ThemInfoData themInfoData) {
                        view.getThemData(themInfoData);
                    }
                });
    }
}
