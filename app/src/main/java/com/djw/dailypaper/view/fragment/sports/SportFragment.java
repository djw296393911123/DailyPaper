package com.djw.dailypaper.view.fragment.sports;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.djw.dailypaper.R;
import com.djw.dailypaper.adapter.SportsAdapter;
import com.djw.dailypaper.base.BaseFragment;
import com.djw.dailypaper.contracts.SportsContracts;
import com.djw.dailypaper.model.data.sports.SportsData;
import com.djw.dailypaper.presenter.SportsPresenter;

import static com.djw.dailypaper.util.RecyclerViewUtil.isSlideToBottom;

/**
 * A simple {@link Fragment} subclass.
 */
public class SportFragment extends BaseFragment implements SportsContracts.View, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private SportsPresenter presenter;
    private SportsAdapter adapter;
    private boolean isSuccess = false;
    private int index = 1;
    private boolean isLoading = false;

    public static SportFragment newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt("position", position);
        SportFragment fragment = new SportFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void lazyLoad() {
        if (!isVisible || !isSuccess)
            return;
        switch (getArguments().getInt("position")) {
            case 0:
                presenter.getDataFromModel();
                break;
            case 1:
                presenter.getFootball();
                break;
            case 2:
                presenter.getSocial();
                break;
            case 3:
                presenter.getMeizi();
                break;
        }
    }

    @Override
    protected void initView(View view) {
        isSuccess = true;
        index = 1;
        swipeRefreshLayout = ((SwipeRefreshLayout) view.findViewById(R.id.srl_sport));
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_sport);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (isSlideToBottom(recyclerView) && !isLoading) {
                    switch (getArguments().getInt("position")) {
                        case 0:
                            presenter.getMore(String.valueOf(++index));
                            break;
                        case 1:
                            presenter.getMoreFootball(String.valueOf(++index));
                            break;
                        case 2:
                            presenter.getMoreSocial(String.valueOf(++index));
                            break;
                        case 3:
                            presenter.getMoreMeizi(String.valueOf(++index));
                            break;
                    }
                    isLoading = true;
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new SportsAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        presenter = new SportsPresenter(this);
        lazyLoad();
    }

    @Override
    protected void doBusiness() {

    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_sport;
    }

    @Override
    public void setPresenter(SportsContracts.Presenter presenter) {

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
        Toast.makeText(getActivity(), "没有更多数据", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getSports(SportsData data) {
        adapter.notifyDataChange(data.getNewslist(), false);
    }

    @Override
    public void getMore(SportsData data) {
        adapter.notifyDataChange(data.getNewslist(), true);
    }

    @Override
    public void getFootball(SportsData data) {
        adapter.notifyDataChange(data.getNewslist(), false);
    }

    @Override
    public void getMoreFootball(SportsData data) {
        adapter.notifyDataChange(data.getNewslist(), true);
    }

    @Override
    public void getSocial(SportsData data) {
        adapter.notifyDataChange(data.getNewslist(), false);
    }

    @Override
    public void getMoreSocial(SportsData data) {
        adapter.notifyDataChange(data.getNewslist(), true);
    }

    @Override
    public void getMeizi(SportsData data) {
        adapter.notifyDataChange(data.getNewslist(), false);
    }

    @Override
    public void getMoreMeizi(SportsData data) {
        adapter.notifyDataChange(data.getNewslist(), true);
    }

    @Override
    public void onRefresh() {
        index = 1;
        switch (getArguments().getInt("position")) {
            case 0:
                presenter.getDataFromModel();
                break;
            case 1:
                presenter.getFootball();
                break;
            case 2:
                presenter.getSocial();
                break;
            case 3:
                presenter.getMeizi();
                break;
        }
    }
}
