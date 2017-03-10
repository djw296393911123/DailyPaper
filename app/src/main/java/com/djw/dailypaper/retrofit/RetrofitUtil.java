package com.djw.dailypaper.retrofit;


import com.djw.dailypaper.interfaces.ApiRequest;
import com.djw.dailypaper.model.data.DaypaperData;
import com.djw.dailypaper.model.data.HotData;
import com.djw.dailypaper.model.data.Them.ThemData;
import com.djw.dailypaper.model.data.WebviewData;
import com.djw.dailypaper.model.data.ZhuanlanData;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JasonDong on 2017/3/9.
 */

public class RetrofitUtil {

    private static final int DEFAULT_TIMEOUT = 5;
    private ApiRequest apiServices;

    private static RetrofitUtil mInstance;

    private RetrofitUtil() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        Retrofit mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(ApiRequest.ZHIHU_SERVICE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        apiServices = mRetrofit.create(ApiRequest.class);
    }

    public static RetrofitUtil getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitUtil.class) {
                mInstance = new RetrofitUtil();
            }
        }
        return mInstance;
    }

    public void getDaypaper(Subscriber<DaypaperData> subscriber) {
        apiServices.getDaypaper().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(subscriber);
    }

    public void getBeforepaper(Subscriber<DaypaperData> subscriber, String date) {
        apiServices.getBeforPaper(date).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(subscriber);
    }

    public void getThem(Subscriber<ThemData> subscriber) {
        apiServices.getThemData().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(subscriber);
    }

    public void getZhuanlan(Subscriber<ZhuanlanData> subscriber) {
        apiServices.getZhuanlan().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(subscriber);
    }

    public void getHot(Subscriber<HotData> subscriber) {
        apiServices.getHot().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(subscriber);
    }

    public void getWebview(String id, Subscriber<WebviewData> subscriber) {
        apiServices.getWebview(id).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(subscriber);
    }

}
