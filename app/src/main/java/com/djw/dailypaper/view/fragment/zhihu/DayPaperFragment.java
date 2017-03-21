package com.djw.dailypaper.view.fragment.zhihu;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.djw.dailypaper.R;
import com.djw.dailypaper.adapter.PaperAdapter;
import com.djw.dailypaper.base.BaseFragment;
import com.djw.dailypaper.contracts.ZhihuContracts;
import com.djw.dailypaper.model.data.DaypaperData;
import com.djw.dailypaper.presenter.ZhihuPresenter;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * A simple {@link Fragment} subclass.
 */
public class DayPaperFragment extends BaseFragment implements ZhihuContracts.View, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;

    private boolean isSuccess = false;
    private ZhihuPresenter presenter;
    private PaperAdapter adapter;
    private int index = 1;
    private String date;
    private boolean isLoading = false;

    @Override
    protected void lazyLoad() {
        if (!isSuccess || !isVisible)
            return;
        presenter.start();
    }

    @Override
    protected void initView(View view) {
        isSuccess = true;
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_paper);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new PaperAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {


            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (isSlideToBottom(recyclerView) && !isLoading) {
                    date = String.valueOf(presenter.loadTodayDate(1000 * 24 * 60 * 60 * index++));
                    presenter.getBeforePaper(date);
                    isLoading = true;
                }
            }
        });
        swipeRefreshLayout = ((SwipeRefreshLayout) view.findViewById(R.id.srl_paper));
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
        presenter = new ZhihuPresenter(this);
        lazyLoad();
    }

    public static boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange() && recyclerView.canScrollVertically(-1))
            return true;
        return false;
    }

    @Override
    protected void doBusiness() {

    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_day_paper;
    }

    @Override
    public void setPresenter(ZhihuContracts.Presenter presenter) {

    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showFail() {
        isLoading = false;
    }

    @Override
    public void showComplete() {
        isLoading = false;
    }

    @Override
    public void showDataEmpty() {

    }

    @Override
    public void showError(String s) {

    }

    @Override
    public void getDayPaper(DaypaperData daypaperData) {
        adapter.notifyListChange(daypaperData, "今日资讯", false);
    }

    @Override
    public void getBeforePaper(DaypaperData daypaperData) {
        adapter.notifyListChange(daypaperData, date, true);
    }

    @Override
    public void onRefresh() {
        index = 1;
        presenter.start();
    }
}
