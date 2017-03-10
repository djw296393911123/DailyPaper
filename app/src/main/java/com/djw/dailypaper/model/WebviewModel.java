package com.djw.dailypaper.model;

import com.djw.dailypaper.contracts.WebviewContracts;
import com.djw.dailypaper.interfaces.RequestListener;
import com.djw.dailypaper.model.data.WebviewData;
import com.djw.dailypaper.retrofit.RetrofitUtil;

import rx.Subscriber;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class WebviewModel implements WebviewContracts.Model {

    @Override
    public void loadData(final RequestListener listener, String... args) {
        RetrofitUtil.getInstance().getWebview(args[0], new Subscriber<WebviewData>() {
            @Override
            public void onCompleted() {
                listener.onComplete();
            }

            @Override
            public void onError(Throwable e) {
                listener.onFail();
            }

            @Override
            public void onNext(WebviewData data) {
                listener.onSuccessful(data);
            }
        });
    }
}
