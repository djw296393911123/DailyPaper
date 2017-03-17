package com.djw.dailypaper.presenter;

import com.djw.dailypaper.contracts.AllContracts;
import com.djw.dailypaper.model.AllModel;
import com.djw.dailypaper.model.data.gank.AndroidData;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JasonDong on 2017/3/16.
 */

public class AllPresenter implements AllContracts.Presenter {

    private AllContracts.View view;

    private AllModel model;

    public AllPresenter(AllContracts.View view) {
        this.view = view;
        this.model = new AllModel();
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
                        view.getAll(data);
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
    public void getMore(String page) {
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
                        view.getMore(data);
                    }
                });
    }
}
