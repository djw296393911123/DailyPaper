package com.djw.dailypaper.model;

import com.djw.dailypaper.contracts.ZhihuContracts;
import com.djw.dailypaper.interfaces.RequestListener;
import com.djw.dailypaper.model.data.DaypaperData;
import com.djw.dailypaper.retrofit.RetrofitUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by JasonDong on 2017/3/10.
 */

public class ZhihuModel implements ZhihuContracts.Model {


    @Override
    public Observable<DaypaperData> loadBeforeData(String data) {
        return RetrofitUtil.getDefault().getBeforPaper(data);
    }

    @Override
    public int getDateToday(long date) {
        long l = System.currentTimeMillis() - date;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return Integer.parseInt(format.format(new Date(l)));
    }

    @Override
    public Observable<DaypaperData> loadData(String... args) {
        return RetrofitUtil.getDefault().getDaypaper();
    }
}
