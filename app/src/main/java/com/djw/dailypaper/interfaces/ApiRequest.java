package com.djw.dailypaper.interfaces;

import com.djw.dailypaper.model.data.DaypaperData;
import com.djw.dailypaper.model.data.HotData;
import com.djw.dailypaper.model.data.Them.ThemData;
import com.djw.dailypaper.model.data.WebviewData;
import com.djw.dailypaper.model.data.ZhuanlanData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by JasonDong on 2017/3/10.
 */

public interface ApiRequest {

    String SERVICE = "";

    String ZHIHU_SERVICE = "http://news-at.zhihu.com/api/4/";

    @GET("news/latest")
    Observable<DaypaperData> getDaypaper();

    @GET("news/before/{data}")
    Observable<DaypaperData> getBeforPaper(@Path("data") String data);

    @GET("themes")
    Observable<ThemData> getThemData();

    @GET("sections")
    Observable<ZhuanlanData> getZhuanlan();

    @GET("news/hot")
    Observable<HotData> getHot();

    @GET("news/{id}")
    Observable<WebviewData> getWebview(@Path("id") String id);

}
