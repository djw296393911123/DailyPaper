package com.djw.dailypaper.presenter;

import com.djw.dailypaper.contracts.ThemContracts;
import com.djw.dailypaper.interfaces.RequestListener;
import com.djw.dailypaper.model.ThemModel;
import com.djw.dailypaper.model.data.Them.ThemData;

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
        model.loadData(new RequestListener<ThemData>() {
            @Override
            public void onSuccessful(ThemData o) {
                view.getThemData(o);
            }

            @Override
            public void onFail() {
                view.showFail();
                view.hideProgress();
            }

            @Override
            public void onComplete() {
                view.showComplete();
                view.hideProgress();
            }
        });
    }
}
