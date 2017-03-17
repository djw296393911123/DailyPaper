package com.djw.dailypaper.model;

import com.djw.dailypaper.contracts.ZhuanlanContracts;
import com.djw.dailypaper.model.data.gank.ZhuanlanData;
import com.djw.dailypaper.retrofit.RetrofitUtil;

import rx.Observable;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class ZhuanlanModel implements ZhuanlanContracts.Model {



    @Override
    public Observable<ZhuanlanData> loadData(String... args) {
        return RetrofitUtil.getDefault().getZhuanlan();
    }
}
