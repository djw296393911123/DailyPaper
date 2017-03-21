package com.djw.dailypaper.interfaces;

import com.djw.dailypaper.model.data.GankBaseReponse;
import com.djw.dailypaper.model.data.OtherData;
import com.djw.dailypaper.model.data.gank.AndroidData;
import com.djw.dailypaper.model.data.DaypaperData;
import com.djw.dailypaper.model.data.gank.HotData;
import com.djw.dailypaper.model.data.Them.ThemData;
import com.djw.dailypaper.model.data.Them.ThemInfoData;
import com.djw.dailypaper.model.data.WebviewData;
import com.djw.dailypaper.model.data.WxData;
import com.djw.dailypaper.model.data.gank.ZhuanlanData;
import com.djw.dailypaper.model.data.gank.ZhuanlanInfoData;
import com.djw.dailypaper.model.data.sports.SportsData;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by JasonDong on 2017/3/10.
 */

public interface ApiRequest {

    String SERVICE = "";

    String ZHIHU_SERVICE = "http://news-at.zhihu.com/api/4/";

    String Gank_SERVICE = "http://gank.io/api/";

    String WX_SERVICE = "http://api.tianapi.com/";

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

    @GET("theme/{id}")
    Observable<ThemInfoData> getInfoThem(@Path("id") String id);

    @GET("section/{id}")
    Observable<ZhuanlanInfoData> getInfoZhuanlan(@Path("id") String id);

    @GET("random/data/福利/1")
    Observable<GankBaseReponse<List<AndroidData.ResultsBean>>> getMeiziRadom();

    @GET("random/data/福利/{num}")
    Observable<GankBaseReponse<List<AndroidData.ResultsBean>>> getMeiziRadom(@Path("num") int num);

    @GET("data/Android/20/{page}")
    Observable<GankBaseReponse<List<AndroidData.ResultsBean>>> getAndroid(@Path("page") String page);

    @GET("data/all/20/{page}")
    Observable<GankBaseReponse<List<AndroidData.ResultsBean>>> getAll(@Path("page") String page);

    @GET("data/福利/20/{page}")
    Observable<GankBaseReponse<List<AndroidData.ResultsBean>>> getMeizi(@Path("page") String page);

    @GET("data/iOS/20/{page}")
    Observable<GankBaseReponse<List<AndroidData.ResultsBean>>> getIos(@Path("page") String page);

    @GET("wxnew/")
    Observable<WxData> getWx(@Query("key") String key, @Query("num") String num, @Query("page") String page);

    @GET("wxnew/")
    Observable<WxData> getSearchWx(@Query("key") String key, @Query("num") String num, @Query("page") String page, @Query("word") String wrod);

    @GET("nba/")
    Observable<SportsData> getNBA(@Query("key") String key, @Query("num") String num, @Query("page") String page);

    @GET("football/")
    Observable<SportsData> getFootball(@Query("key") String key, @Query("num") String num, @Query("page") String page);

    @GET("startup/")
    Observable<SportsData> getSocial(@Query("key") String key, @Query("num") String num, @Query("page") String page);

    @GET("meinv/")
    Observable<SportsData> getGirl(@Query("key") String key, @Query("num") String num, @Query("page") String page);

    @GET("story-extra/{id}")
    Observable<OtherData> getInfo(@Path("id") String id);

}
