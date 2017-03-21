package com.djw.dailypaper.model;

import com.djw.dailypaper.contracts.WebviewContracts;
import com.djw.dailypaper.model.data.WebviewData;
import com.djw.dailypaper.model.data.OtherData;
import com.djw.dailypaper.retrofit.RetrofitUtil;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class WebviewModel implements WebviewContracts.Model {


    @Override
    public Observable<WebviewData> loadData(String... args) {
        return RetrofitUtil.getDefault().getWebview(args[0]);
    }

    @Override
    public Observable<OtherData> getInfo(String id) {
        return RetrofitUtil.getDefault().getInfo(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
