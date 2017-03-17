package com.djw.dailypaper.presenter;

import com.djw.dailypaper.contracts.ZhuanlanInfoContracts;
import com.djw.dailypaper.model.ZhilanInfoModel;
import com.djw.dailypaper.model.data.gank.ZhuanlanInfoData;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JasonDong on 2017/3/16.
 */

public class ZhuanlanInfoPresenter implements ZhuanlanInfoContracts.Presenter {

    private ZhuanlanInfoContracts.View view;

    private ZhilanInfoModel model;

    public ZhuanlanInfoPresenter(ZhuanlanInfoContracts.View view) {
        this.view = view;
        this.model = new ZhilanInfoModel();
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
                .subscribe(new Subscriber<ZhuanlanInfoData>() {
                    @Override
                    public void onCompleted() {
                        view.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgress();
                    }

                    @Override
                    public void onNext(ZhuanlanInfoData zhuanlanInfoData) {
                        view.getThemData(zhuanlanInfoData);
                    }
                });
    }
}
