package com.djw.dailypaper.model;

import com.djw.dailypaper.contracts.ZhihuContracts;
import com.djw.dailypaper.interfaces.RequestListener;
import com.djw.dailypaper.model.data.DaypaperData;
import com.djw.dailypaper.retrofit.RetrofitUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import rx.Subscriber;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class ZhihuModel implements ZhihuContracts.Model {

    @Override
    public void loadData(final RequestListener listener, String... args) {
        RetrofitUtil.getInstance().getDaypaper(new Subscriber<DaypaperData>() {
            @Override
            public void onCompleted() {
                listener.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                listener.onFail();
            }

            @Override
            public void onNext(DaypaperData daypaperData) {
                listener.onSuccessful(daypaperData);
            }
        });
    }

    @Override
    public void loadBeforeData(String data, final RequestListener<DaypaperData> listener) {
        RetrofitUtil.getInstance().getBeforepaper(new Subscriber<DaypaperData>() {
            @Override
            public void onCompleted() {
                listener.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                listener.onFail();
            }

            @Override
            public void onNext(DaypaperData daypaperData) {
                listener.onSuccessful(daypaperData);
            }
        }, data);
    }

    @Override
    public int getDateToday(long date) {
        long l = System.currentTimeMillis() - date;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return Integer.parseInt(format.format(new Date(l)));
    }

}
