package com.djw.dailypaper.model;

import com.djw.dailypaper.contracts.HotContracts;
import com.djw.dailypaper.model.data.gank.HotData;
import com.djw.dailypaper.retrofit.RetrofitUtil;

import rx.Observable;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class HotModel implements HotContracts.Model {

    @Override
    public Observable<HotData> loadData(String... args) {
        return RetrofitUtil.getDefault().getHot();
    }
}
