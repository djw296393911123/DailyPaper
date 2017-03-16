package com.djw.dailypaper.model;

import com.djw.dailypaper.contracts.WebviewContracts;
import com.djw.dailypaper.interfaces.RequestListener;
import com.djw.dailypaper.model.data.WebviewData;
import com.djw.dailypaper.retrofit.RetrofitUtil;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class WebviewModel implements WebviewContracts.Model {


    @Override
    public Observable<WebviewData> loadData(String... args) {
        return RetrofitUtil.getDefault().getWebview(args[0]);
    }
}
