package com.djw.dailypaper.view.fragment.zhihu;


import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.djw.dailypaper.R;
import com.djw.dailypaper.adapter.ZhuanlanAdapter;
import com.djw.dailypaper.base.BaseFragment;
import com.djw.dailypaper.contracts.ZhuanlanContracts;
import com.djw.dailypaper.model.data.gank.ZhuanlanData;
import com.djw.dailypaper.presenter.ZhuanlanPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhulanFragment extends BaseFragment implements ZhuanlanContracts.View, SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private ZhuanlanAdapter adapter;
    private ZhuanlanPresenter presenter;
    private boolean isSuccess = false;

    @Override
    protected void lazyLoad() {
        if (!isVisible || !isSuccess)
            return;
        presenter.start();
    }

    @Override
    protected void initView(View view) {
        isSuccess = true;
        swipeRefreshLayout = ((SwipeRefreshLayout) view.findViewById(R.id.srl_zhuanlan));
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_zhuanlan);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new ZhuanlanAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        presenter = new ZhuanlanPresenter(this);
        lazyLoad();
    }

    @Override
    protected void doBusiness() {

    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_zhulan;
    }

    @Override
    public void setPresenter(ZhuanlanContracts.Presenter presenter) {

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
    public void getThemData(ZhuanlanData data) {
        adapter.notifyListChange(data.getData());
    }

    @Override
    public void onRefresh() {
        presenter.start();
    }
}
