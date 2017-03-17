package com.djw.dailypaper.model;

import com.djw.dailypaper.contracts.ZhuanlanInfoContracts;
import com.djw.dailypaper.model.data.gank.ZhuanlanInfoData;
import com.djw.dailypaper.retrofit.RetrofitUtil;

import rx.Observable;

/**
 * Created by JasonDong on 2017/3/16.
 */

public class ZhilanInfoModel implements ZhuanlanInfoContracts.Model {
    @Override
    public Observable<ZhuanlanInfoData> loadData(String... args) {
        return RetrofitUtil.getDefault().getInfoZhuanlan(args[0]);
    }
}
