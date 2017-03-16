package com.djw.dailypaper.presenter;

import com.djw.dailypaper.contracts.WebviewContracts;
import com.djw.dailypaper.interfaces.RequestListener;
import com.djw.dailypaper.model.WebviewModel;
import com.djw.dailypaper.model.data.WebviewData;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class WebviewPresenter implements WebviewContracts.Presenter {

    private WebviewContracts.View view;

    private WebviewModel model;

    public WebviewPresenter(WebviewContracts.View view) {
        this.view = view;
        this.model = new WebviewModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void getDataFromModel(String... args) {
        model.loadData(args[0])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WebviewData>() {
                    @Override
                    public void onCompleted() {
                        view.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgress();
                    }

                    @Override
                    public void onNext(WebviewData data) {
                        view.getWebviewData(data);
                    }
                });
    }
}
