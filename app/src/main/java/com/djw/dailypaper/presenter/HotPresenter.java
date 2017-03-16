package com.djw.dailypaper.presenter;

import com.djw.dailypaper.contracts.HotContracts;
import com.djw.dailypaper.contracts.ZhuanlanContracts;
import com.djw.dailypaper.interfaces.RequestListener;
import com.djw.dailypaper.model.HotModel;
import com.djw.dailypaper.model.ZhuanlanModel;
import com.djw.dailypaper.model.data.HotData;
import com.djw.dailypaper.model.data.ZhuanlanData;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class HotPresenter implements HotContracts.Presenter {

    private HotContracts.View view;

    private HotModel model;

    public HotPresenter(HotContracts.View view) {
        this.view = view;
        this.model = new HotModel();
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
                .subscribe(new Subscriber<HotData>() {
                    @Override
                    public void onCompleted() {
                        view.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgress();
                    }

                    @Override
                    public void onNext(HotData hotData) {
                        view.getHotData(hotData);
                    }
                });
    }
}
