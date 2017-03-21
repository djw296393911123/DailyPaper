package com.djw.dailypaper.presenter;

import com.djw.dailypaper.contracts.AllContracts;
import com.djw.dailypaper.model.data.GankBaseReponse;
import com.djw.dailypaper.model.data.gank.AndroidData;
import com.djw.dailypaper.retrofit.GankUtil;
import com.djw.dailypaper.util.CommonSubscriber;
import com.djw.dailypaper.util.RxUtil;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JasonDong on 2017/3/16.
 */

public class AllPresenter implements AllContracts.Presenter {

    private AllContracts.View view;


    public AllPresenter(AllContracts.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void getDataFromModel(String... args) {
        view.showProgress();
        GankUtil.getDefault().getAll(args[0])
                .compose(RxUtil.<GankBaseReponse<List<AndroidData.ResultsBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<AndroidData.ResultsBean>>handleResult())
                .subscribe(new CommonSubscriber<List<AndroidData.ResultsBean>>(view) {
                    @Override
                    public void onNext(List<AndroidData.ResultsBean> list) {
                        view.hideProgress();
                        view.getAll(list);
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
    public void getMore(String page) {
        view.showProgress();
        GankUtil.getDefault().getAll(page)
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
