package com.djw.dailypaper.model;

import com.djw.dailypaper.contracts.WxContracts;
import com.djw.dailypaper.model.data.WxData;
import com.djw.dailypaper.retrofit.WxUtil;

import rx.Observable;

/**
 * Created by JasonDong on 2017/3/17.
 */

public class WxModel implements WxContracts.Model {
    @Override
    public Observable<WxData> loadData(String... args) {
        return WxUtil.getDefault().getWx("1f4051e5e61866d7ead573e2a39d857c", "20", "1");
    }

    @Override
    public Observable<WxData> getMore(String page) {
        return WxUtil.getDefault().getWx("1f4051e5e61866d7ead573e2a39d857c", "20", page);
    }

    @Override
    public Observable<WxData> getSearch(String page,String word) {
        return WxUtil.getDefault().getSearchWx("1f4051e5e61866d7ead573e2a39d857c", "20", page,word);
    }
}
