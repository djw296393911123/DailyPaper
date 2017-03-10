package com.djw.dailypaper.model;

import com.djw.dailypaper.contracts.ThemContracts;
import com.djw.dailypaper.contracts.ZhuanlanContracts;
import com.djw.dailypaper.interfaces.RequestListener;
import com.djw.dailypaper.model.data.Them.ThemData;
import com.djw.dailypaper.model.data.ZhuanlanData;
import com.djw.dailypaper.retrofit.RetrofitUtil;

import rx.Subscriber;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class ZhuanlanModel implements ZhuanlanContracts.Model {

    @Override
    public void loadData(final RequestListener listener, String... args) {
        RetrofitUtil.getInstance().getZhuanlan(new Subscriber<ZhuanlanData>() {
            @Override
            public void onCompleted() {
                listener.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                listener.onFail();
            }

            @Override
            public void onNext(ZhuanlanData data) {
                listener.onSuccessful(data);
            }
        });
    }
}
