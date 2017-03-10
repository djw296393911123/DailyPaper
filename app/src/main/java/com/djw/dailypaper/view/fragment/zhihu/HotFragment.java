package com.djw.dailypaper.view.fragment.zhihu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.djw.dailypaper.R;
import com.djw.dailypaper.adapter.HotAdapter;
import com.djw.dailypaper.base.BaseFragment;
import com.djw.dailypaper.contracts.HotContracts;
import com.djw.dailypaper.model.data.HotData;
import com.djw.dailypaper.presenter.HotPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends BaseFragment implements HotContracts.View, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private HotAdapter adapter;
    private boolean isSuccess = false;
    private HotPresenter presenter;

    @Override
    protected void lazyLoad() {
        if (!isVisible || !isSuccess)
            return;
        presenter.start();
    }

    @Override
    protected void initView(View view) {
        isSuccess = true;
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_hot);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HotAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout = ((SwipeRefreshLayout) view.findViewById(R.id.srl_hot));
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
        presenter = new HotPresenter(this);
        lazyLoad();
    }

    @Override
    protected void doBusiness() {

    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_hot;
    }

    @Override
    public void setPresenter(HotContracts.Presenter presenter) {

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
    public void getHotData(HotData data) {
        adapter.notifyListChange(data.getRecent());
    }

    @Override
    public void onRefresh() {
        presenter.start();
    }
}
