package com.djw.dailypaper.presenter;

import com.djw.dailypaper.contracts.SportsContracts;
import com.djw.dailypaper.model.SportsModel;
import com.djw.dailypaper.model.data.sports.SportsData;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JasonDong on 2017/3/17.
 */

public class SportsPresenter implements SportsContracts.Presenter {

    private SportsContracts.View view;

    private SportsModel model;

    public SportsPresenter(SportsContracts.View view) {
        this.view = view;
        this.model = new SportsModel();
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
                .subscribe(new Subscriber<SportsData>() {
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
                    public void onNext(SportsData data) {
                        view.getSports(data);
                    }
                });
    }

    @Override
    public void getMore(String page) {
        view.showProgress();
        model.getMore(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SportsData>() {
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
                    public void onNext(SportsData data) {
                        view.getMore(data);
                    }
                });
    }

    @Override
    public void getFootball() {
        view.showProgress();
        model.getFootball()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SportsData>() {
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
                    public void onNext(SportsData data) {
                        view.getFootball(data);
                    }
                });
    }

    @Override
    public void getMoreFootball(String page) {
        view.showProgress();
        model.getMoreFootball(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SportsData>() {
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
                    public void onNext(SportsData data) {
                        view.getMoreFootball(data);
                    }
                });
    }

    @Override
    public void getSocial() {
        view.showProgress();
        model.getSocial()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SportsData>() {
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
                    public void onNext(SportsData data) {
                        view.getSocial(data);
                    }
                });
    }

    @Override
    public void getMoreSocial(String page) {
        view.showProgress();
        model.getMoreSocial(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SportsData>() {
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
                    public void onNext(SportsData data) {
                        view.getMoreSocial(data);
                    }
                });
    }

    @Override
    public void getMeizi() {
        view.showProgress();
        model.getMeizi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SportsData>() {
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
                    public void onNext(SportsData data) {
                        view.getMeizi(data);
                    }
                });
    }

    @Override
    public void getMoreMeizi(String page) {
        view.showProgress();
        model.getMoreMeizi(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SportsData>() {
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
                    public void onNext(SportsData data) {
                        view.getMoreMeizi(data);
                    }
                });
    }
}
