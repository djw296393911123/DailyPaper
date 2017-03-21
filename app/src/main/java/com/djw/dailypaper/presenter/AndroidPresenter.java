package com.djw.dailypaper.presenter;

import com.djw.dailypaper.contracts.AndroidContracts;
import com.djw.dailypaper.model.data.GankBaseReponse;
import com.djw.dailypaper.model.data.gank.AndroidData;
import com.djw.dailypaper.retrofit.GankUtil;
import com.djw.dailypaper.util.CommonSubscriber;
import com.djw.dailypaper.util.RxUtil;

import java.util.List;

/**
 * Created by JasonDong on 2017/3/16.
 */

public class AndroidPresenter implements AndroidContracts.Presenter {

    private AndroidContracts.View view;


    public AndroidPresenter(AndroidContracts.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void getDataFromModel(String... args) {
        view.showProgress();
        GankUtil.getDefault().getAndroid(args[0])
                .compose(RxUtil.<GankBaseReponse<List<AndroidData.ResultsBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<AndroidData.ResultsBean>>handleResult())
                .subscribe(new CommonSubscriber<List<AndroidData.ResultsBean>>(view) {
                    @Override
                    public void onNext(List<AndroidData.ResultsBean> list) {
                        view.hideProgress();
                        view.getAndroid(list);
                    }
                });
    }

    @Override
    public void getMeizi() {
        GankUtil.getDefault().getMeiziRadom()
                .compose(RxUtil.<GankBaseReponse<List<AndroidData.ResultsBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<AndroidData.ResultsBean>>handleResult())
                .subscribe(new CommonSubscriber<List<AndroidData.ResultsBean>>(view) {
                    @Override
                    public void onNext(List<AndroidData.ResultsBean> list) {
                        view.getMeizi(list);
                    }
                });
    }

    @Override
    public void getMoreData(String page) {
        view.showProgress();
        GankUtil.getDefault().getAndroid(page)
                .compose(RxUtil.<GankBaseReponse<List<AndroidData.ResultsBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<AndroidData.ResultsBean>>handleResult())
                .subscribe(new CommonSubscriber<List<AndroidData.ResultsBean>>(view) {
                    @Override
                    public void onNext(List<AndroidData.ResultsBean> list) {
                        view.hideProgress();
                        view.getAndroid(list);
                    }
                });
    }
}
