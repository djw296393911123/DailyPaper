package com.djw.dailypaper.model;

import com.djw.dailypaper.contracts.AndroidContracts;
import com.djw.dailypaper.model.data.gank.AndroidData;
import com.djw.dailypaper.retrofit.GankUtil;

import rx.Observable;

/**
 * Created by JasonDong on 2017/3/16.
 */

public class AndroidModel implements AndroidContracts.Model {
    @Override
    public Observable<AndroidData> loadData(String... args) {
        return GankUtil.getDefault().getAndroid(args[0]);
    }

    @Override
    public Observable<AndroidData> getMeizi() {
        return GankUtil.getDefault().getMeiziRadom();
    }

    @Override
    public Observable<AndroidData> getMoreData(String page) {
        return GankUtil.getDefault().getAndroid(page);
    }
}
