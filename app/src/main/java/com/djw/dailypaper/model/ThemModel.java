package com.djw.dailypaper.model;

import com.djw.dailypaper.contracts.ThemContracts;
import com.djw.dailypaper.interfaces.RequestListener;
import com.djw.dailypaper.model.data.Them.ThemData;
import com.djw.dailypaper.retrofit.RetrofitUtil;

import rx.Subscriber;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class ThemModel implements ThemContracts.Model {

    @Override
    public void loadData(final RequestListener listener, String... args) {
        RetrofitUtil.getInstance().getThem(new Subscriber<ThemData>() {
            @Override
            public void onCompleted() {
                listener.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                listener.onFail();
            }

            @Override
            public void onNext(ThemData data) {
                listener.onSuccessful(data);
            }
        });
    }
}
