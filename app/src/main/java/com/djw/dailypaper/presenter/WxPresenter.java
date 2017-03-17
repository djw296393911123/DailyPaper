package com.djw.dailypaper.presenter;

import com.djw.dailypaper.contracts.WxContracts;
import com.djw.dailypaper.model.WxModel;
import com.djw.dailypaper.model.data.WxData;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JasonDong on 2017/3/17.
 */

public class WxPresenter implements WxContracts.Presenter {

    private WxContracts.View view;

    private WxModel model;

    public WxPresenter(WxContracts.View view) {
        this.view = view;
        this.model = new WxModel();
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
                .subscribe(new Subscriber<WxData>() {
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
                    public void onNext(WxData wxData) {
                        view.getWxData(wxData);
                    }
                });
    }

    @Override
    public void getMore(String page) {
        view.showProgress();
        model.getMore(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WxData>() {
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
                    public void onNext(WxData wxData) {
                        view.getMore(wxData);
                    }
                });
    }

    @Override
    public void getSearch(String page, String word) {
        model.getSearch(page,word)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WxData>() {
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
                    public void onNext(WxData wxData) {
                        view.getSearchData(wxData);
                    }
                });
    }

    @Override
    public void getSearchMore(String page, String word) {
        model.getSearch(page,word)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WxData>() {
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
                    public void onNext(WxData wxData) {
                        view.getSearchMore(wxData);
                    }
                });
    }
}
