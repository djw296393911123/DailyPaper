package com.djw.dailypaper.model;

import com.djw.dailypaper.contracts.ThemContracts;
import com.djw.dailypaper.interfaces.RequestListener;
import com.djw.dailypaper.model.data.Them.ThemData;
import com.djw.dailypaper.retrofit.RetrofitUtil;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class ThemModel implements ThemContracts.Model {


    @Override
    public Observable<ThemData> loadData(String... args) {
        return RetrofitUtil.getDefault().getThemData();
    }
}
