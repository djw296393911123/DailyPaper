package com.djw.dailypaper.presenter;

import com.djw.dailypaper.contracts.ZhuanlanContracts;
import com.djw.dailypaper.model.ZhuanlanModel;
import com.djw.dailypaper.model.data.gank.ZhuanlanData;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class ZhuanlanPresenter implements ZhuanlanContracts.Presenter {

    private ZhuanlanContracts.View view;

    private ZhuanlanModel model;

    public ZhuanlanPresenter(ZhuanlanContracts.View view) {
        this.view = view;
        this.model = new ZhuanlanModel();
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
                .subscribe(new Subscriber<ZhuanlanData>() {
                    @Override
                    public void onCompleted() {
                        view.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgress();
                    }

                    @Override
                    public void onNext(ZhuanlanData zhuanlanData) {
                        view.getThemData(zhuanlanData);
                    }
                });


    }
}
