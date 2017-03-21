package com.djw.dailypaper.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.djw.dailypaper.R;
import com.djw.dailypaper.base.BaseActivity;
import com.djw.dailypaper.model.data.GankBaseReponse;
import com.djw.dailypaper.model.data.gank.AndroidData;
import com.djw.dailypaper.retrofit.GankUtil;
import com.djw.dailypaper.util.RxUtil;
import com.djw.dailypaper.util.SearchPopWindows;
import com.djw.dailypaper.view.fragment.CardFragment;
import com.djw.dailypaper.view.fragment.GankFragment;
import com.djw.dailypaper.view.fragment.SportsFragment;
import com.djw.dailypaper.view.fragment.WXFragment;
import com.djw.dailypaper.view.fragment.ZhihuFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, TextView.OnEditorActionListener, OnBannerClickListener {

    private ZhihuFragment zhihuFragment;

    private GankFragment gankFragment;

    private SportsFragment sportsFragment;

    private CardFragment cardFragment;

    private WXFragment wxFragment;
    private Banner head;
    private Toolbar toolbar;
    private int itemId;
    private SearchPopWindows searchPopWindows;
    private long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBackEnable(false);
        setContentView(R.layout.activity_home);

    }

    @Override
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("知乎日报");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View view = navigationView.getHeaderView(0);
        head = ((Banner) view.findViewById(R.id.iv_head));
        head.setBannerStyle(BannerConfig.NUM_INDICATOR);
        head.setOnBannerClickListener(this);
        head.setImageLoader(new GlideImageLoader());
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(0);
    }

    @Override
    public void OnBannerClick(int position) {
        initFragment(2);
        gankFragment.setPager(3);

    }

    private class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).asBitmap().into(imageView);
        }

        @Override
        public ImageView createImageView(Context context) {
            return new ImageView(context);
        }
    }

    @Override
    public void doBusiness() {
        getHead();
        initFragment(1);
    }

    private void getHead() {
        Log.i("11111111", "1111111111");
        GankUtil.getDefault().getMeiziRadom(10)
                .compose(RxUtil.<GankBaseReponse<List<AndroidData.ResultsBean>>>rxSchedulerHelper())
                .subscribe(new Subscriber<GankBaseReponse<List<AndroidData.ResultsBean>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GankBaseReponse<List<AndroidData.ResultsBean>> listGankBaseReponse) {
                        List<String> imgs = new ArrayList<String>();
                        for (int i = 0; i < listGankBaseReponse.getResults().size(); i++) {
                            imgs.add(listGankBaseReponse.getResults().get(i).getUrl());
                        }
//                        Glide.with(context).load(listGankBaseReponse.getResults().get(0).getUrl()).asBitmap().into(head);
                        head.setImages(imgs);
                        head.start();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.search && itemId == R.id.wx) {
            searchPopWindows = new SearchPopWindows(this, this);
            searchPopWindows.showAsDropDown(toolbar, 5, 5);
        }
        if (item.getItemId() == R.id.search && itemId == R.id.zhihu) {
            getHead();
        }
        if (item.getItemId() == R.id.search && itemId == R.id.ex) {
            cardFragment.initData();
        }
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        itemId = item.getItemId();
        switch (itemId) {
            case R.id.zhihu:
                initFragment(1);
                break;
            case R.id.gank:
                initFragment(2);
                break;
            case R.id.wx:
                initFragment(3);
                break;
            case R.id.xitu:
                initFragment(4);
                break;
            case R.id.ex:
                initFragment(5);
                break;
        }
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initFragment(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        switch (index) {
            case 1:
                toolbar.setTitle("知乎日报");
                if (zhihuFragment != null) {
                    transaction.show(zhihuFragment);
                } else {
                    zhihuFragment = new ZhihuFragment();
                    transaction.add(R.id.fl_home, zhihuFragment);
                }
                break;
            case 2:
                toolbar.setTitle("干货集中营");
                if (gankFragment != null) {
                    transaction.show(gankFragment);
                } else {
                    gankFragment = new GankFragment();
                    transaction.add(R.id.fl_home, gankFragment);
                }
                break;

            case 3:
                toolbar.setTitle("微信精选");
                if (wxFragment != null) {
                    transaction.show(wxFragment);
                } else {
                    wxFragment = new WXFragment();
                    transaction.add(R.id.fl_home, wxFragment);
                }
                break;

            case 4:
                toolbar.setTitle("体育新闻");
                if (sportsFragment != null) {
                    transaction.show(sportsFragment);
                } else {
                    sportsFragment = new SportsFragment();
                    transaction.add(R.id.fl_home, sportsFragment);
                }
                break;
            case 5:
                toolbar.setTitle("Card");
                if (cardFragment != null) {
                    transaction.show(cardFragment);
                } else {
                    cardFragment = new CardFragment();
                    transaction.add(R.id.fl_home, cardFragment);
                }
                break;

        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (zhihuFragment != null) {
            transaction.hide(zhihuFragment);
        }
        if (gankFragment != null) {
            transaction.hide(gankFragment);
        }

        if (wxFragment != null) {
            transaction.hide(wxFragment);
        }

        if (sportsFragment != null) {
            transaction.hide(sportsFragment);
        }

        if (cardFragment != null) {
            transaction.hide(cardFragment);
        }

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (wxFragment != null && itemId == R.id.wx)
            wxFragment.getSearchWord(v.getText().toString());
        searchPopWindows.dismiss();
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(this, "再次点击退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
