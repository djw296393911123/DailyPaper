package com.djw.dailypaper.presenter;

import com.djw.dailypaper.contracts.HotContracts;
import com.djw.dailypaper.contracts.ZhuanlanContracts;
import com.djw.dailypaper.interfaces.RequestListener;
import com.djw.dailypaper.model.HotModel;
import com.djw.dailypaper.model.ZhuanlanModel;
import com.djw.dailypaper.model.data.HotData;
import com.djw.dailypaper.model.data.ZhuanlanData;

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
        model.loadData(new RequestListener<HotData>() {
            @Override
            public void onSuccessful(HotData o) {
                view.getHotData(o);
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
