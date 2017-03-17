package com.djw.dailypaper.model;

import com.djw.dailypaper.contracts.IosContracts;
import com.djw.dailypaper.model.data.gank.AndroidData;
import com.djw.dailypaper.retrofit.GankUtil;

import rx.Observable;

/**
 * Created by JasonDong on 2017/3/16.
 */

public class IosModel implements IosContracts.Model {

    @Override
    public Observable<AndroidData> loadData(String... args) {
        return GankUtil.getDefault().getIos(args[0]);
    }

    @Override
    public Observable<AndroidData> getMeizi() {
        return GankUtil.getDefault().getMeiziRadom();
    }

    @Override
    public Observable<AndroidData> getMore(String page) {
        return GankUtil.getDefault().getAll(page);
    }
}
