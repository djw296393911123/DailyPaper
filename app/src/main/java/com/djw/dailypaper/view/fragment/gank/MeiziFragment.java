package com.djw.dailypaper.view.fragment.gank;


import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.djw.dailypaper.R;
import com.djw.dailypaper.adapter.MeiziAdapter;
import com.djw.dailypaper.base.BaseFragment;
import com.djw.dailypaper.contracts.MeiziContracts;
import com.djw.dailypaper.model.data.gank.AndroidData;
import com.djw.dailypaper.presenter.MeiziPresenter;

import static com.djw.dailypaper.util.RecyclerViewUtil.isSlideToBottom;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeiziFragment extends BaseFragment implements MeiziContracts.View, SwipeRefreshLayout.OnRefreshListener {

    private MeiziAdapter adapter;
    private MeiziPresenter presenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isSuccess = false;
    private int index = 1;

    @Override
    protected void lazyLoad() {
        if (!isVisible || !isSuccess)
            return;
        presenter.getDataFromModel("1");
    }

    @Override
    protected void initView(View view) {
        isSuccess = true;
        swipeRefreshLayout = ((SwipeRefreshLayout) view.findViewById(R.id.srl_meizi));
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_meizi);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (isSlideToBottom(recyclerView))
                    presenter.getMore(String.valueOf(++index));
            }
        });
        adapter = new MeiziAdapter(getActivity());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void doBusiness() {
        presenter = new MeiziPresenter(this);
        lazyLoad();
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_meizi;
    }

    @Override
    public void setPresenter(MeiziContracts.Presenter presenter) {

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
    public void getMeizi(AndroidData data) {
        adapter.notifyDataChange(data.getResults(), false);
    }

    @Override
    public void getMore(AndroidData data) {
        adapter.notifyDataChange(data.getResults(), true);
    }

    @Override
    public void onRefresh() {
        index = 1;
        presenter.getDataFromModel("1");
    }
    @Override
    public void showDataEmpty() {
        Toast.makeText(getActivity(), "没有更多数据", Toast.LENGTH_SHORT).show();
    }
}
