package com.djw.dailypaper.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.view.menu.MenuItemWrapperICS;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.djw.dailypaper.R;
import com.djw.dailypaper.adapter.WxAdapter;
import com.djw.dailypaper.base.BaseFragment;
import com.djw.dailypaper.contracts.WxContracts;
import com.djw.dailypaper.model.data.WxData;
import com.djw.dailypaper.presenter.WxPresenter;
import com.djw.dailypaper.util.SearchPopWindows;

import static com.djw.dailypaper.util.RecyclerViewUtil.isSlideToBottom;

/**
 * A simple {@link Fragment} subclass.
 */
public class WXFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, WxContracts.View {

    private SwipeRefreshLayout swipeRefreshLayout;
    private WxPresenter presenter;
    private int index = 1;
    private WxAdapter adapter;
    private boolean isSuccess = false;
    private String keyword = "";
    private int page = 1;

    @Override
    protected void lazyLoad() {
//        if (!isVisible || !isSuccess)
//            return;
//        presenter.start();
    }

    @Override
    protected void initView(View view) {
        isSuccess = true;
        swipeRefreshLayout = ((SwipeRefreshLayout) view.findViewById(R.id.srl_wx));
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_wx);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (isSlideToBottom(recyclerView)) {
                    if (keyword.equals(""))
                        presenter.getMore(String.valueOf(++index));
                    else
                        presenter.getSearchMore(String.valueOf(++page), keyword);
                }
            }
        });
        adapter = new WxAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        presenter = new WxPresenter(this);
        presenter.start();
    }

    @Override
    protected void doBusiness() {
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_wx;
    }

    @Override
    public void onRefresh() {
        index = 1;
        page = 1;
        keyword = "";
        presenter.start();
    }

    @Override
    public void setPresenter(WxContracts.Presenter presenter) {

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

    }

    @Override
    public void showComplete() {

    }

    @Override
    public void showDataEmpty() {

    }

    @Override
    public void showError(String s) {

    }

    @Override
    public void getMore(WxData wxData) {
        adapter.notifyListChange(wxData.getNewslist(), true);
    }

    @Override
    public void getWxData(WxData wxData) {
        adapter.notifyListChange(wxData.getNewslist(), false);
    }

    @Override
    public void getSearchData(WxData wxData) {
        adapter.notifyListChange(wxData.getNewslist(), false);
    }

    @Override
    public void getSearchMore(WxData wxData) {
        adapter.notifyListChange(wxData.getNewslist(), true);
    }

    @Override
    public void getSearchWord(String word) {
        keyword = word;
        presenter.getSearch("1", word);
    }
}
