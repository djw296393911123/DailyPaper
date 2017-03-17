package com.djw.dailypaper.model;

import com.djw.dailypaper.contracts.ThemInfoContracts;
import com.djw.dailypaper.model.data.Them.ThemInfoData;
import com.djw.dailypaper.retrofit.RetrofitUtil;

import rx.Observable;

/**
 * Created by JasonDong on 2017/3/16.
 */

public class ThemInfoModel implements ThemInfoContracts.Model {
    @Override
    public Observable<ThemInfoData> loadData(String... args) {
        return RetrofitUtil.getDefault().getInfoThem(args[0]);
    }
}
