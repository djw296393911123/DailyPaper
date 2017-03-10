package com.djw.dailypaper.presenter;

import com.djw.dailypaper.contracts.WebviewContracts;
import com.djw.dailypaper.interfaces.RequestListener;
import com.djw.dailypaper.model.WebviewModel;
import com.djw.dailypaper.model.data.WebviewData;

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
        model.loadData(new RequestListener() {
            @Override
            public void onSuccessful(Object o) {
                view.getWebviewData(((WebviewData) o));
            }

            @Override
            public void onFail() {
                view.showFail();
            }

            @Override
            public void onComplete() {
                view.showComplete();
            }
        }, args[0]);
    }
}
