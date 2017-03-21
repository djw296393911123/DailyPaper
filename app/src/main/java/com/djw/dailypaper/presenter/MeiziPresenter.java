package com.djw.dailypaper.presenter;

import com.djw.dailypaper.contracts.MeiziContracts;
import com.djw.dailypaper.model.data.GankBaseReponse;
import com.djw.dailypaper.model.data.gank.AndroidData;
import com.djw.dailypaper.retrofit.GankUtil;
import com.djw.dailypaper.util.CommonSubscriber;
import com.djw.dailypaper.util.RxUtil;

import java.util.List;

/**
 * Created by JasonDong on 2017/3/17.
 */

public class MeiziPresenter implements MeiziContracts.Presenter {

    private MeiziContracts.View view;


    public MeiziPresenter(MeiziContracts.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void getDataFromModel(String... args) {
        view.showProgress();
        GankUtil.getDefault().getMeizi(args[0])
                .compose(RxUtil.<GankBaseReponse<List<AndroidData.ResultsBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<AndroidData.ResultsBean>>handleResult())
                .subscribe(new CommonSubscriber<List<AndroidData.ResultsBean>>(view) {
                    @Override
                    public void onNext(List<AndroidData.ResultsBean> list) {
                        view.hideProgress();
                        view.getMeizi(list);
                    }
                });
    }

    @Override
    public void getMore(String page) {
        view.showProgress();
        GankUtil.getDefault().getMeizi(page)
                .compose(RxUtil.<GankBaseReponse<List<AndroidData.ResultsBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<AndroidData.ResultsBean>>handleResult())
                .subscribe(new CommonSubscriber<List<AndroidData.ResultsBean>>(view) {
                    @Override
                    public void onNext(List<AndroidData.ResultsBean> list) {
                        view.hideProgress();
                        view.getMore(list);
                    }
                });
    }
}
