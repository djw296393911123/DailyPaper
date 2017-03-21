package com.djw.dailypaper.view.fragment.zhihu;


import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.djw.dailypaper.R;
import com.djw.dailypaper.adapter.ThemAdapter;
import com.djw.dailypaper.base.BaseFragment;
import com.djw.dailypaper.contracts.ThemContracts;
import com.djw.dailypaper.model.data.Them.ThemData;
import com.djw.dailypaper.presenter.ThemPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThemFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, ThemContracts.View {

    private SwipeRefreshLayout swipeRefreshLayout;
    private ThemAdapter adapter;
    private ThemPresenter presenter;
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
        swipeRefreshLayout = ((SwipeRefreshLayout) view.findViewById(R.id.srl_them));
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_them);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new ThemAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        presenter = new ThemPresenter(this);
        lazyLoad();
    }

    @Override
    protected void doBusiness() {

    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_them;
    }

    @Override
    public void onRefresh() {
        presenter.start();
    }

    @Override
    public void setPresenter(ThemContracts.Presenter presenter) {

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
    public void getThemData(ThemData data) {
        adapter.notifyListChange(data.getOthers());
    }
}
