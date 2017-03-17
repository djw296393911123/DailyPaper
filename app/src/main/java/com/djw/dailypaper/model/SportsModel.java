package com.djw.dailypaper.model;

import com.djw.dailypaper.contracts.SportsContracts;
import com.djw.dailypaper.model.data.sports.SportsData;
import com.djw.dailypaper.retrofit.WxUtil;

import rx.Observable;

/**
 * Created by JasonDong on 2017/3/17.
 */

public class SportsModel implements SportsContracts.Model {
    @Override
    public Observable<SportsData> loadData(String... args) {
        return WxUtil.getDefault().getNBA("1f4051e5e61866d7ead573e2a39d857c", "20", "1");
    }

    @Override
    public Observable<SportsData> getMore(String page) {
        return WxUtil.getDefault().getNBA("1f4051e5e61866d7ead573e2a39d857c", "20", page);
    }

    @Override
    public Observable<SportsData> getFootball() {
        return WxUtil.getDefault().getFootball("1f4051e5e61866d7ead573e2a39d857c", "20", "1");
    }

    @Override
    public Observable<SportsData> getMoreFootball(String page) {
        return WxUtil.getDefault().getFootball("1f4051e5e61866d7ead573e2a39d857c", "20", page);
    }

    @Override
    public Observable<SportsData> getSocial() {
        return WxUtil.getDefault().getSocial("1f4051e5e61866d7ead573e2a39d857c", "20", "1");
    }

    @Override
    public Observable<SportsData> getMoreSocial(String page) {
        return WxUtil.getDefault().getSocial("1f4051e5e61866d7ead573e2a39d857c", "20", page);
    }

    @Override
    public Observable<SportsData> getMeizi() {
        return WxUtil.getDefault().getGirl("1f4051e5e61866d7ead573e2a39d857c", "20", "1");
    }

    @Override
    public Observable<SportsData> getMoreMeizi(String page) {
        return WxUtil.getDefault().getGirl("1f4051e5e61866d7ead573e2a39d857c", "20", page);
    }


}
