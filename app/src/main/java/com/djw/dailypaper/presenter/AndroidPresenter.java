package com.djw.dailypaper.presenter;

import com.djw.dailypaper.contracts.AndroidContracts;
import com.djw.dailypaper.model.AndroidModel;
import com.djw.dailypaper.model.data.gank.AndroidData;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JasonDong on 2017/3/16.
 */

public class AndroidPresenter implements AndroidContracts.Presenter {

    private AndroidContracts.View view;

    private AndroidModel model;

    public AndroidPresenter(AndroidContracts.View view) {
        this.view = view;
        this.model = new AndroidModel();
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
                .subscribe(new Subscriber<AndroidData>() {
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
                    public void onNext(AndroidData data) {
                        view.getAndroid(data);
                    }
                });
    }

    @Override
    public void getMeizi() {
        model.getMeizi().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<AndroidData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(AndroidData data) {
                view.getMeizi(data);
            }
        });
    }

    @Override
    public void getMoreData(String page) {
        view.showProgress();
        model.loadData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AndroidData>() {
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
                    public void onNext(AndroidData data) {
                        view.getMoreData(data);
                    }
                });
    }
}
