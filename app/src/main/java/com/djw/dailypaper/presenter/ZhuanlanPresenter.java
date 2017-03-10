package com.djw.dailypaper.presenter;

import com.djw.dailypaper.contracts.ThemContracts;
import com.djw.dailypaper.contracts.ZhuanlanContracts;
import com.djw.dailypaper.interfaces.RequestListener;
import com.djw.dailypaper.model.ThemModel;
import com.djw.dailypaper.model.ZhuanlanModel;
import com.djw.dailypaper.model.data.Them.ThemData;
import com.djw.dailypaper.model.data.ZhuanlanData;

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
        model.loadData(new RequestListener<ZhuanlanData>() {
            @Override
            public void onSuccessful(ZhuanlanData o) {
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
