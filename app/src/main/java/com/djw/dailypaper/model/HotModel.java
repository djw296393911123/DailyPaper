package com.djw.dailypaper.model;

import com.djw.dailypaper.contracts.HotContracts;
import com.djw.dailypaper.interfaces.RequestListener;
import com.djw.dailypaper.model.data.HotData;
import com.djw.dailypaper.retrofit.RetrofitUtil;

import rx.Subscriber;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class HotModel implements HotContracts.Model {

    @Override
    public void loadData(final RequestListener listener, String... args) {
        RetrofitUtil.getInstance().getHot(new Subscriber<HotData>() {
            @Override
            public void onCompleted() {
                listener.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                listener.onFail();
            }

            @Override
            public void onNext(HotData hotData) {
                listener.onSuccessful(hotData);
            }
        });
    }
}
