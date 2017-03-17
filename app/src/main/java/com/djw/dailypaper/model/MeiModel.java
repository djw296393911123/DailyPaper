package com.djw.dailypaper.model;

import com.djw.dailypaper.contracts.MeiziContracts;
import com.djw.dailypaper.model.data.gank.AndroidData;
import com.djw.dailypaper.retrofit.GankUtil;

import rx.Observable;

/**
 * Created by JasonDong on 2017/3/17.
 */

public class MeiModel implements MeiziContracts.Model {
    @Override
    public Observable<AndroidData> loadData(String... args) {
        return GankUtil.getDefault().getMeizi(args[0]);
    }

    @Override
    public Observable<AndroidData> getMore(String page) {
        return GankUtil.getDefault().getAll(page);
    }
}
